/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import info.debatty.java.stringsimilarity.Levenshtein;
import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.Concept;
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
	
	private final String[] nonNumericLimsResult = { "tnd", "<20", "<40", ">10000000", "target not detected", "<29", "<400",
	        "<20 cp/ml", "<20 copies/ml", "<40 copies/ml", "<40 cp/ml", "not detected", "< 40", "< 20" };
	
	Map<String, String> nonNumericLimsResultMap = new HashMap();
	
	Map<String, Integer> nonNumericResultConceptMap = new HashMap();
	
	private void loadNonNumericResultMap() {
		nonNumericLimsResultMap.put("<20", "19");
		nonNumericLimsResultMap.put("<29", "28");
		nonNumericLimsResultMap.put("<40", "39");
		nonNumericLimsResultMap.put("<400", "399");
		nonNumericLimsResultMap.put("<80", "79");
		nonNumericLimsResultMap.put("Invalid", null);
		nonNumericLimsResultMap.put(">10000000", null);
		nonNumericLimsResultMap.put("Aborted", null);
		nonNumericLimsResultMap.put("Double entry", null);
		nonNumericLimsResultMap.put("Duplicate", null);
		nonNumericLimsResultMap.put("Failed", null);
		nonNumericLimsResultMap.put("Failed twice", null);
		nonNumericLimsResultMap.put("Incomplete number", null);
		nonNumericLimsResultMap.put("Incomplete entry", null);
		nonNumericLimsResultMap.put("Target Not Detected", "0");
		nonNumericLimsResultMap.put("tnd", "0");
		nonNumericLimsResultMap.put("Wrong entry", null);
		nonNumericLimsResultMap.put("Numeric Value", null);
		
	}
	
	private void loadNonNumericResultConcept() {
		nonNumericResultConceptMap.put("<20", 166407);
		nonNumericResultConceptMap.put("<29", 166408);
		nonNumericResultConceptMap.put("<40", 166409);
		nonNumericResultConceptMap.put("<400", 166410);
		nonNumericResultConceptMap.put("<80", 166411);
		nonNumericResultConceptMap.put("Invalid", 163611);
		nonNumericResultConceptMap.put(">10000000", 166412);
		nonNumericResultConceptMap.put("Aborted", 166413);
		nonNumericResultConceptMap.put("Double entry", 166414);
		nonNumericResultConceptMap.put("Duplicate", 166415);
		nonNumericResultConceptMap.put("Failed", 166416);
		nonNumericResultConceptMap.put("Failed twice", 166417);
		nonNumericResultConceptMap.put("Incomplete number", 166418);
		nonNumericResultConceptMap.put("Incomplete entry", 166419);
		nonNumericResultConceptMap.put("Target Not Detected", 166420);
		nonNumericResultConceptMap.put("tnd", 166420);
		nonNumericResultConceptMap.put("Wrong entry", 166421);
		nonNumericResultConceptMap.put("Numeric Value", 166426);
		
	}
	
	public SampleResultManager() {
        dBManager = new DBManager();
        exchangeLayer = new ExchangeLayer();
        mapper = new ObjectMapper();
        alertService = Context.getService(AlertService.class);
        manifestResultResponses = new ArrayList<>();
        loadNonNumericResultConcept();
        loadNonNumericResultMap();

    }
	
	public List<ManifestResultResponse> pullManifestResultFromLIMS(List<Manifest> pendingManifests, boolean clearOldRecords)
	        throws SQLException {
		
		if (!pendingManifests.isEmpty()) {
			System.out.println("GOT SOME PENDING SAMPLES");
			boolean authFailed = false;
			
			//    AtomicInteger availableResultCount = new AtomicInteger(0);
			for (Manifest a : pendingManifests) {
				
				ResultRequest rr = new ResultRequest();
				rr.setManifestID(a.getManifestID());
				rr.setReceivingPCRLabID(a.getPcrLabCode());
				rr.setReceivingPCRLabName(a.getPcrLabName());
				rr.setSendingFacilityID(Utils.getFacilityDATIMId());
				rr.setSendingFacilityName(Utils.getFacilityName());
				rr.setTestType(a.getTestType());
				
				try {
					
					System.out.println("About to request for token");
					String token = exchangeLayer.requestTokenFromLims();
					if (token != null) {
						rr.setToken(token);
					} else {
						System.out.println("Error: could not process request");
						authFailed = true;
						break;
					}
					
					System.out.println("About to request sample info online");
					
					HttpResponse<String> sampleResponse = exchangeLayer.requestManifestResultOnline(rr);
					
					if (sampleResponse != null && sampleResponse.getStatus() == 200 && sampleResponse.getBody() != null) {
						try {
							
							System.out.println("Got sample results");
							System.out.println(sampleResponse.getBody());
							VLResultResponse resultResponse = mapper.readValue(sampleResponse.getBody(),
							    VLResultResponse.class);
							
							if (clearOldRecords) {
								dBManager.openConnection();
								dBManager.deleteManifestResult(a.getManifestID());
							}
							
							//got a result
							updateManifestResultOnDB(resultResponse);
						}
						catch (Exception ex) {
							Logger.getLogger(SampleResultManager.class.getName()).log(Level.SEVERE, null, ex);
						}
					} else {
						manifestResultResponses.add(new ManifestResultResponse(a.getManifestID(), "No"));
					}
					
				}
				catch (UnirestException ex) {
					Logger.getLogger(SampleResultManager.class.getName()).log(Level.SEVERE, null, ex);
				}
				
			}
			if (authFailed) {
				return null;
				
			}
			
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

                    updatePatientSampleRecordwithResult(encounterId, result);

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
	
	private void updatePatientSampleRecordwithResult(int encounterId, Result result) {
		
		Encounter labEncounter = Context.getEncounterService().getEncounter(encounterId);
		
		Obs dateSampleReceivedAtPCRObs = new Obs();
		dateSampleReceivedAtPCRObs.setConcept(Context.getConceptService().getConcept(
		    LabFormUtils.DATE_SAMPLE_RECEIVED_AT_PCR_LAB));
		dateSampleReceivedAtPCRObs.setValueDate(result.getDateSampleReceivedAtPCRLab());
		dateSampleReceivedAtPCRObs.setObsDatetime(new Date());
		dateSampleReceivedAtPCRObs.setPerson(labEncounter.getPatient());
		dateSampleReceivedAtPCRObs.setEncounter(labEncounter);
		dateSampleReceivedAtPCRObs.setUuid(UUID.randomUUID().toString());
		
		labEncounter.addObs(dateSampleReceivedAtPCRObs);
		
		Obs dateResultDispatchedObs = new Obs();
		dateResultDispatchedObs.setConcept(Context.getConceptService()
		        .getConcept(LabFormUtils.DATE_RESULT_SENT_FROM_PCR_LAB));
		dateResultDispatchedObs.setValueDate(result.getDateResultDispatched());
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
		
		Obs resultDate = new Obs();
		resultDate.setConcept(Context.getConceptService().getConcept(LabFormUtils.RESULT_DATE));
		resultDate.setValueDate(result.getResultDate());
		resultDate.setObsDatetime(new Date());
		resultDate.setPerson(labEncounter.getPatient());
		resultDate.setEncounter(labEncounter);
		resultDate.setUuid(UUID.randomUUID().toString());
		
		labEncounter.addObs(resultDate);
		
		Obs assayDate = new Obs();
		assayDate.setConcept(Context.getConceptService().getConcept(LabFormUtils.ASSAY_DATE));
		assayDate.setValueDate(result.getAssayDate());
		assayDate.setObsDatetime(new Date());
		assayDate.setPerson(labEncounter.getPatient());
		assayDate.setEncounter(labEncounter);
		assayDate.setUuid(UUID.randomUUID().toString());
		
		labEncounter.addObs(assayDate);
		
		Obs approvalDate = new Obs();
		approvalDate.setConcept(Context.getConceptService().getConcept(LabFormUtils.APPROVAL_DATE));
		approvalDate.setValueDate(result.getApprovalDate());
		approvalDate.setObsDatetime(new Date());
		approvalDate.setPerson(labEncounter.getPatient());
		approvalDate.setEncounter(labEncounter);
		approvalDate.setUuid(UUID.randomUUID().toString());
		
		labEncounter.addObs(approvalDate);
		
		try {
			Obs testResultObs = new Obs();
			testResultObs.setConcept(Context.getConceptService().getConcept(LabFormUtils.VIRAL_LOAD_RESULT));
			String alphaNumericValue = null;
			
			if (isNumeric(result.getTestResult())) {
				testResultObs.setValueNumeric(Double.valueOf(result.getTestResult()));
				alphaNumericValue = "Numeric Value";
			} else {
				alphaNumericValue = compareNonNumericString(result.getTestResult());
				String derivedNumericValue = nonNumericLimsResultMap.get(alphaNumericValue);
				if (derivedNumericValue != null) {
					testResultObs.setValueNumeric(Double.valueOf(derivedNumericValue));
					
				}
			}
			
			testResultObs.setObsDatetime(new Date());
			testResultObs.setPerson(labEncounter.getPatient());
			testResultObs.setEncounter(labEncounter);
			testResultObs.setUuid(UUID.randomUUID().toString());
			
			labEncounter.addObs(testResultObs);
			
			Obs alphaNumericResult = new Obs();
			alphaNumericResult.setConcept(Context.getConceptService().getConcept(LabFormUtils.ALPHA_NUMERIC_TEST_RESULT));
			alphaNumericResult.setValueCoded(Context.getConceptService().getConcept(
			    nonNumericResultConceptMap.get(alphaNumericValue)));
			alphaNumericResult.setObsDatetime(new Date());
			alphaNumericResult.setPerson(labEncounter.getPatient());
			alphaNumericResult.setEncounter(labEncounter);
			alphaNumericResult.setUuid(UUID.randomUUID().toString());
			
			labEncounter.addObs(alphaNumericResult);
			
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		Context.getEncounterService().saveEncounter(labEncounter);
		
	}
	
	private String compareNonNumericString(String nonNumerictestResult) {
        Map<Double, String> resultMap = new HashMap<>();
        Levenshtein ld = new Levenshtein();

        nonNumericLimsResultMap.keySet().stream()
                .forEach(a -> {
                    double rr = ld.distance(nonNumerictestResult, a);
                    resultMap.put(rr, a);
                });

        double minDistance = resultMap.keySet().stream()
                .mapToDouble(b -> b)
                .min().orElseThrow(NoSuchElementException::new);

        return resultMap.get(minDistance);

    }
	
	public static boolean isNumeric(String stringResult) {
		if (stringResult == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(stringResult);
		}
		catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
