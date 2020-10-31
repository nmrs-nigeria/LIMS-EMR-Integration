/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.limsemrops.dbmanager.DBManager;
import org.openmrs.module.limsemrops.omodmodels.Manifest;
import org.openmrs.module.limsemrops.omodmodels.ManifestResultResponse;
import org.openmrs.module.limsemrops.omodmodels.PatientID;
import org.openmrs.module.limsemrops.omodmodels.Result;
import org.openmrs.module.limsemrops.omodmodels.ResultRequest;
import org.openmrs.module.limsemrops.omodmodels.VLResultResponse;
import org.openmrs.module.limsemrops.omodmodels.VLSampleInformationFrontFacing;
import org.openmrs.module.limsemrops.utility.ConstantUtils;
import org.openmrs.module.limsemrops.utility.GeneralMapper;
import org.openmrs.module.limsemrops.utility.LabFormUtils;
import org.openmrs.module.limsemrops.utility.Utils;
import org.openmrs.notification.Alert;
import org.openmrs.notification.AlertService;

/**
 * @author MORRISON.I
 */
public class SampleResultManager {
	
	DBManager dBManager;
	
	ExchangeLayer exchangeLayer;
	
	ObjectMapper mapper;
	
	AlertService alertService;
	
	List<ManifestResultResponse> manifestResultResponses;
	
	public SampleResultManager() {
        dBManager = new DBManager();
        exchangeLayer = new ExchangeLayer();
        mapper = new ObjectMapper();
        alertService = Context.getService(AlertService.class);
        manifestResultResponses = new ArrayList<>();

    }
	
	public List<ManifestResultResponse> pullManifestResultFromLIMS(List<Manifest> pendingManifests) throws SQLException {

        if (!pendingManifests.isEmpty()) {
            System.out.println("GOT SOME PENDING SAMPLES");

            //    AtomicInteger availableResultCount = new AtomicInteger(0);
            pendingManifests.stream().forEach(a -> {

                ResultRequest rr = new ResultRequest();
                rr.setManifestID(a.getManifestID());
                rr.setReceivingPCRLabID(a.getPcrLabCode());
                rr.setReceivingPCRLabName(a.getPcrLabName());
                rr.setSendingFacilityID(Utils.getFacilityDATIMId());
                rr.setSendingFacilityName(Utils.getFacilityName());
                rr.setTestType(a.getTestType());

                try {

                    System.out.println("About to request sample info online");

                    HttpResponse<String> sampleResponse
                            = exchangeLayer.requestManifestResultOnline(rr);

                    if (sampleResponse != null && sampleResponse.getStatus() == 200 && sampleResponse.getBody() != null) {
                        try {

                            System.out.println("Got sample results");
                            System.out.println(sampleResponse.getBody());
                            VLResultResponse resultResponse = mapper.readValue(sampleResponse.getBody(), VLResultResponse.class);
                            //got a result
                            updateManifestResultOnDB(resultResponse);
                        } catch (Exception ex) {
                            Logger.getLogger(SampleResultManager.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        manifestResultResponses.add(new ManifestResultResponse(a.getManifestID(), "No"));
                    }

                } catch (UnirestException ex) {
                    Logger.getLogger(SampleResultManager.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

        }
        dBManager.closeConnection();
        return manifestResultResponses;
    }
	
	private void updateManifestResultOnDB(VLResultResponse vLResultResponse) throws SQLException, IOException {
        dBManager.openConnection();
        List<VLSampleInformationFrontFacing> allManifestSamples = dBManager.getManifestSamples(vLResultResponse.getManifestID());
        //log sample result on DB.
        //update manifest info
        //update patient encounter
        String createdBy = Context.getAuthenticatedUser().toString();
        StringBuilder sampleIdbuilder = new StringBuilder();
        StringBuilder patientIdBuilder = new StringBuilder();

        vLResultResponse.getViralLoadTestReport().forEach(vl -> {

            try {
                Result result = GeneralMapper.mapToSampleResultModel(vl);
                result.setManifestID(vLResultResponse.getManifestID());
                result.setSampleTestable(String.valueOf(result.getSampleTestable().charAt(0)));
                result.setCreatedBy(createdBy);

                dBManager.insertSampleResult(result);

                //get any not null pID
                PatientID pi = vl.getPatientID().stream().filter(a -> !(a.getIdNumber().equals(StringUtils.EMPTY))).findFirst().get();
                patientIdBuilder.append(pi.getIdNumber());
                patientIdBuilder.append(",");

                sampleIdbuilder.append(vl.getSampleID());
                sampleIdbuilder.append(",");
                //TODO: update samples and manifest info
                //update patient encounter info
                Integer encounterId = allManifestSamples.stream()
                        .filter(a -> a.getSampleID().equals(vl.getSampleID())).findFirst().get().getEncounterId();

                if (encounterId != null) {

                    updatePatientSampleRecordwithResult(encounterId, result.getDateSampleReceivedAtPCRLab(), result.getDateResultDispatched(), result.getTestResult());

                }

                dBManager.updateSamplesResultStatus(ConstantUtils.ResultStatus.AVAILABLE.toString(), Context.getAuthenticatedUser().toString(), vl.getSampleID());

            } catch (IOException ex) {
                Logger.getLogger(SampleResultManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(SampleResultManager.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        if (!vLResultResponse.getViralLoadTestReport().isEmpty()) {
            dBManager.updateManifestResultStatus(ConstantUtils.ResultStatus.AVAILABLE.toString(), Context.getAuthenticatedUser().toString(), vLResultResponse.getManifestID());
            manifestResultResponses.add(new ManifestResultResponse(vLResultResponse.getManifestID(), "Yes"));
        }

        //send notification
        String alertText = MessageFormat
                .format("You have new sample result for samples with id {0} for patients with identifiers {1} on manifest {2} ",
                        sampleIdbuilder.toString(), patientIdBuilder.toString(), vLResultResponse.getManifestID());
        Alert alert = new Alert(alertText, Context.getAuthenticatedUser());
        alert.setText(alertText);

        alert.setUuid(UUID.randomUUID().toString());

        alertService.saveAlert(alert);

        dBManager.closeConnection();

    }
	
	private void updatePatientSampleRecordwithResult(int encounterId, Date dateSampleReceivedAtPCRLab,
	        Date dateResultDispatched, String testResult) {
		
		Encounter labEncounter = Context.getEncounterService().getEncounter(encounterId);
		
		Obs dateSampleReceivedAtPCRObs = new Obs();
		dateSampleReceivedAtPCRObs.setConcept(Context.getConceptService().getConcept(
		    LabFormUtils.DATE_SAMPLE_RECEIVED_AT_PCR_LAB));
		dateSampleReceivedAtPCRObs.setValueDate(dateSampleReceivedAtPCRLab);
		dateSampleReceivedAtPCRObs.setObsDatetime(new Date());
		dateSampleReceivedAtPCRObs.setPerson(labEncounter.getPatient());
		dateSampleReceivedAtPCRObs.setEncounter(labEncounter);
		dateSampleReceivedAtPCRObs.setUuid(UUID.randomUUID().toString());
		
		labEncounter.addObs(dateSampleReceivedAtPCRObs);
		
		Obs dateResultDispatchedObs = new Obs();
		dateResultDispatchedObs.setConcept(Context.getConceptService()
		        .getConcept(LabFormUtils.DATE_RESULT_SENT_FROM_PCR_LAB));
		dateResultDispatchedObs.setValueDate(dateResultDispatched);
		dateResultDispatchedObs.setObsDatetime(new Date());
		dateResultDispatchedObs.setPerson(labEncounter.getPatient());
		dateResultDispatchedObs.setEncounter(labEncounter);
		dateResultDispatchedObs.setUuid(UUID.randomUUID().toString());
		
		labEncounter.addObs(dateResultDispatchedObs);
		
		Obs dateResultReceivedAtFacilityObs = new Obs();
		dateResultReceivedAtFacilityObs.setConcept(Context.getConceptService().getConcept(
		    LabFormUtils.DATE_RESULT_WAS_RECEIVED_AT_FACILITY));
		dateResultReceivedAtFacilityObs.setValueDate(new Date());
		dateResultReceivedAtFacilityObs.setObsDatetime(new Date());
		dateResultReceivedAtFacilityObs.setPerson(labEncounter.getPatient());
		dateResultReceivedAtFacilityObs.setEncounter(labEncounter);
		dateResultReceivedAtFacilityObs.setUuid(UUID.randomUUID().toString());
		
		labEncounter.addObs(dateResultReceivedAtFacilityObs);
		
		try {
			Obs testResultObs = new Obs();
			testResultObs.setConcept(Context.getConceptService().getConcept(LabFormUtils.VIRAL_LOAD_RESULT));
			testResultObs.setValueNumeric(Double.valueOf(testResult)); //TODO: change test result to text on NMRS Lab form.
			testResultObs.setObsDatetime(new Date());
			testResultObs.setPerson(labEncounter.getPatient());
			testResultObs.setEncounter(labEncounter);
			testResultObs.setUuid(UUID.randomUUID().toString());
			
			labEncounter.addObs(testResultObs);
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		Context.getEncounterService().saveEncounter(labEncounter);
		
	}
	
	//    public void pullManifestResultFromLIMSByClickFromUI(String manifestID) throws SQLException {
	//        dBManager.openConnection();
	//        List<Manifest> pendingManifests = dBManager.getAllPendingManifestById(manifestID);
	//        if (!pendingManifests.isEmpty()) {
	//            System.out.println("GOT SOME PENDING SAMPLES");
	//
	//            pendingManifests.stream().forEach(a -> {
	//
	//                ResultRequest rr = new ResultRequest();
	//                rr.setManifestID(a.getManifestID());
	//                rr.setReceivingPCRLabID(a.getPcrLabCode());
	//                rr.setReceivingPCRLabName(a.getPcrLabName());
	//                rr.setSendingFacilityID(Utils.getFacilityDATIMId());
	//                rr.setSendingFacilityName(Utils.getFacilityName());
	//                rr.setTestType(a.getTestType());
	//
	//                try {
	//
	//                    System.out.println("About to request sample info online");
	//
	//                    HttpResponse<String> sampleResponse
	//                            = exchangeLayer.requestManifestResultOnline(rr);
	//
	//                    if (sampleResponse != null && sampleResponse.getStatus() == 200) {
	//                        try {
	//                            System.out.println("Got sample results");
	//                            //got a result
	//                            VLResultResponse resultResponse = mapper.readValue(sampleResponse.getBody(), VLResultResponse.class);
	//                            updateManifestResultOnDB(resultResponse);
	//
	//                        } catch (SQLException ex) {
	//                            Logger.getLogger(SampleResultManager.class.getName()).log(Level.SEVERE, null, ex);
	//                        } catch (IOException ex) {
	//                            Logger.getLogger(SampleResultManager.class.getName()).log(Level.SEVERE, null, ex);
	//                        }
	//                    }
	//
	//                } catch (UnirestException ex) {
	//                    Logger.getLogger(SampleResultManager.class.getName()).log(Level.SEVERE, null, ex);
	//                }
	//
	//            });
	//
	//        }
	//        dBManager.closeConnection();
	//
	//    }
}
