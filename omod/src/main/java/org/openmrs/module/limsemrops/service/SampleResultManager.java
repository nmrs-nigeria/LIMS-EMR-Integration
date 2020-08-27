/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.limsemrops.dbmanager.DBManager;
import org.openmrs.module.limsemrops.omodmodels.Manifest;
import org.openmrs.module.limsemrops.omodmodels.Result;
import org.openmrs.module.limsemrops.omodmodels.ResultRequest;
import org.openmrs.module.limsemrops.omodmodels.VLResultResponse;
import org.openmrs.module.limsemrops.omodmodels.VLSampleInformationFrontFacing;
import org.openmrs.module.limsemrops.utility.GeneralMapper;
import org.openmrs.module.limsemrops.utility.LabFormUtils;
import org.openmrs.module.limsemrops.utility.Utils;

/**
 * @author MORRISON.I
 */
public class SampleResultManager {
	
	DBManager dBManager;
	
	ExchangeLayer exchangeLayer;
	
	public SampleResultManager() {
		dBManager = new DBManager();
		exchangeLayer = new ExchangeLayer();
		
	}
	
	public void pullManifestResultFromLIMS() throws SQLException {

        List<Manifest> pendingManifests = dBManager.getAllPendingManifest();
        if (!pendingManifests.isEmpty()) {
            pendingManifests.parallelStream().forEach(a -> {

                ResultRequest rr = new ResultRequest();
                rr.setManifestID(a.getManifestID());
                rr.setReceivingPCRLabID(a.getPcrLabCode());
                rr.setReceivingPCRLabName(a.getPcrLabName());
                rr.setSendingFacilityID(Utils.getFacilityDATIMId());
                rr.setSendingFacilityName(Utils.getFacilityName());
                rr.setTestType(a.getTestType());

                try {

                    HttpResponse<VLResultResponse> sampleResponse
                            = exchangeLayer.requestManifestResultOnline(rr);

                    if (sampleResponse != null && sampleResponse.getStatus() == 200) {
                        try {
                            //got a result
                            updateManifestResultOnDB(sampleResponse.getBody());
                        } catch (SQLException ex) {
                            Logger.getLogger(SampleResultManager.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(SampleResultManager.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } catch (UnirestException ex) {
                    Logger.getLogger(SampleResultManager.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

        }

    }
	
	private void updateManifestResultOnDB(VLResultResponse vLResultResponse) throws SQLException, IOException {

        List<VLSampleInformationFrontFacing> allManifestSamples = dBManager.getManifestSamples(vLResultResponse.getManifestID());
        //log sample result on DB.
        //update manifest info
        //update patient encounter

        vLResultResponse.getViralLoadTestReport().forEach(vl -> {

            try {
                Result result = GeneralMapper.mapToSampleResultModel(vl);
                dBManager.insertSampleResult(result);
                //TODO: update samples and manifest info

                //update patient encounter info
                Integer encounterId = allManifestSamples.stream()
                        .filter(a -> a.getSampleID().equals(vl.getSampleID())).findFirst().get().getEncounterId();

                if (encounterId != null) {

                    updatePatientSampleRecordwithResult(encounterId, result.getDateSampleReceievedAtPCRLab(), result.getDateResultDispatched(), result.getTestResult());

                }
                
                

            } catch (IOException ex) {
                Logger.getLogger(SampleResultManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(SampleResultManager.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        
        if(allManifestSamples.size() == vLResultResponse.getViralLoadTestReport().size()){
            //update manifest result status to result available
        }else{
        
           // update result comment to count of result available.
            
        }

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
		
		Obs testResultObs = new Obs();
		testResultObs.setConcept(Context.getConceptService().getConcept(LabFormUtils.VIRAL_LOAD_RESULT));
		testResultObs.setValueNumeric(Double.valueOf(testResult)); //TODO: change test result to text on NMRS Lab form.
		testResultObs.setObsDatetime(new Date());
		testResultObs.setPerson(labEncounter.getPatient());
		testResultObs.setEncounter(labEncounter);
		testResultObs.setUuid(UUID.randomUUID().toString());
		
		labEncounter.addObs(testResultObs);
		
		Context.getEncounterService().saveEncounter(labEncounter);
		
	}
	
}
