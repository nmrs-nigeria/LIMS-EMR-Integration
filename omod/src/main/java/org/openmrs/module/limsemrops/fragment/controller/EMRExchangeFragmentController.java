/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.fragment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xalan.xsltc.compiler.util.Type;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.limsemrops.jobs.CheckSampleResult;
import org.openmrs.module.limsemrops.omodmodels.*;
import org.openmrs.module.limsemrops.service.DBUtility;
import org.openmrs.module.limsemrops.service.ExchangeLayer;
import org.openmrs.module.limsemrops.service.LookUpManager;
import org.openmrs.module.limsemrops.service.SampleInfo;
import org.openmrs.module.limsemrops.service.SampleResultManager;
import org.openmrs.module.limsemrops.service.SchemaValidator;
import org.openmrs.module.limsemrops.utility.ConstantUtils.SampleSpace;
import org.openmrs.module.limsemrops.utility.LabFormUtils;
import org.openmrs.module.limsemrops.utility.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author MORRISON.I
 */
public class EMRExchangeFragmentController {
	
	private static final Log LOG = LogFactory.getLog(EMRExchangeFragmentController.class);
	
	private final ExchangeLayer exchangeLayer;
	
	private final DBUtility dBUtility;
	
	private final LookUpManager lookUpManager;
	
	private ObjectMapper mapper;
	
	private final SampleResultManager sampleResultManager;
	
	private SampleInfo sampleInfo;
	
	Gson gson = Utils.getGsonObject();
	
	public EMRExchangeFragmentController() {
		this.exchangeLayer = new ExchangeLayer();
		this.dBUtility = new DBUtility();
		this.lookUpManager = new LookUpManager();
		this.sampleResultManager = new SampleResultManager();
		this.sampleInfo = new SampleInfo();
		
	}
	
	private ObjectMapper getMapper() {
		mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		return mapper;
	}
	
	public void testVLLoad() {
		
		// just for testing
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate stDate = LocalDate.of(2020, Month.MAY, 04);
		LocalDate endDate = LocalDate.of(2020, Month.MAY, 24);
		
		String result = searchVLSamples(Date.from(stDate.atStartOfDay(defaultZoneId).toInstant()),
		    Date.from(endDate.atStartOfDay(defaultZoneId).toInstant()), "VL");
		
		System.out.println(result);
		
		//	performVLRequisition(result, "LIMS150003", "Asokoro Laboratory and Training Center");
		//        ObjectMapper mapper = new ObjectMapper();
		//        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		//
		//        List<Integer> idList = new ArrayList<>();
		//
		//        DBUtility dBUtility = new DBUtility();
		//        idList = dBUtility.getTestLabEncountersByDate(new Date(), new Date());
		//        if (!idList.isEmpty()) {
		//            ViralLoadInfo viralLoadInfo = new ViralLoadInfo(idList);
		//            VLSampleCollectionBatchManifest vLSampleCollectionBatchManifest = new VLSampleCollectionBatchManifest();
		//            vLSampleCollectionBatchManifest = viralLoadInfo.getRecentSampleCollectedManifest();
		//            SampleCollectionManifest sampleCollectionManifest = new SampleCollectionManifest();
		//            sampleCollectionManifest.setViralloadManifest(vLSampleCollectionBatchManifest);
		//
		//            try {
		//                String manifestString = mapper.writeValueAsString(sampleCollectionManifest);
		//                System.out.println(manifestString);
		//                this.exchangeLayer.sendSamplesOnline(manifestString);
		//
		//                System.out.println("FINISHED PROCESSING");
		//
		//            } catch (JsonProcessingException ex) {
		//                System.err.println(ex.getMessage());
		//            } catch (UnirestException ex) {
		//                System.err.println(ex.getMessage());
		//            }
		//
		//        }
	}
	
	public void fetchSampleResult() {
		CheckSampleResult checkSampleResult = new CheckSampleResult();
		checkSampleResult.execute();
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> validateSampleInfo(@RequestParam(value = "vlsamples", required = true) String vlsamples,
            @RequestParam(value = "sampleSpace", required = true) String sampleSpace) {
        this.sampleInfo = new SampleInfo();
        mapper = new ObjectMapper();
        
        String response = "";
        try {
            
            List<VLSampleInformationFrontFacing> vLSampleInformations
                    = this.sampleInfo.searchLabEncounters(mapper.readValue(vlsamples, new TypeReference<List<Integer>>() {
                    }), SampleSpace.valueOf(sampleSpace));
            
            List<VLSampleInformation> convertedSamples = updateDateSampleSent(vLSampleInformations, new Date());
            
            List<SampleValidationResult> sampleValidationResults
                    = SchemaValidator.validateSample(convertedSamples);
            //response = mapper.writeValueAsString(sampleValidationResults);
            response = gson.toJson(sampleValidationResults);
            System.out.println(response);
            
        } catch (IOException ex) {
            Logger.getLogger(EMRExchangeFragmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@RequestMapping(method = RequestMethod.GET)
    public String fetchSampleResultFromUI(@RequestParam(value = "manifestId") String manifestId) {
        
        List<Manifest> manifests = new ArrayList<>();
        manifests.add(dBUtility.getManifestsbyId(manifestId));
        List<ManifestResultResponse> manifestResultResponses;
        String response = Type.EMPTYSTRING;
        mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            manifestResultResponses = sampleResultManager.pullManifestResultFromLIMS(manifests, true);
            if (manifestResultResponses.isEmpty()) {
                response = "EMPTY";
            } else {
                // response = mapper.writeValueAsString(manifestResultResponses);
                response = gson.toJson(manifestResultResponses);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EMRExchangeFragmentController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return response;
    }
	
	@RequestMapping(method = RequestMethod.GET)
	public String getResultByManifestId(@RequestParam(value = "manifestId") String manifestId) {
		List<Result> results;
		String response = Type.EMPTYSTRING;
		results = dBUtility.getManifestResult(manifestId);
		mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		try {
			if (results.isEmpty()) {
				response = "EMPTY";
			} else {
				
				// response = mapper.writeValueAsString(results);
				
				response = gson.toJson(results);
			}
		}
		catch (Exception ex) {
			Logger.getLogger(EMRExchangeFragmentController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return response;
	}
	
	public String searchVLSamples(@RequestParam(value = "startDate") Date startDate, @RequestParam(value = "endDate") Date endDate,
            @RequestParam(value = "sampleSpace") String sampleSpace) {
        
        String response = null;
        mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        
        List<VLSampleInformationFrontFacing> vlSampleInfo = new ArrayList<>();
        //   SampleInfo sampleInfo = new SampleInfo();

        try {
            if (sampleSpace.equalsIgnoreCase("VL")) {
                vlSampleInfo = sampleInfo.searchLabEncounters(startDate, endDate, SampleSpace.VL);
            } else if (sampleSpace.equalsIgnoreCase("Recency")) {
                vlSampleInfo = sampleInfo.searchLabEncounters(startDate, endDate, SampleSpace.RECENCY);
            }
            //  System.out.println(vlSampleInfo.get(0).getSampleCollectionDate());

            System.out.println(gson.toJson(vlSampleInfo));
            response = gson.toJson(vlSampleInfo);
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
        return response;
        
    }
	
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getDefaultPCRLabs() {
        
        mapper = new ObjectMapper();
        List<PCRLab> pCRLabs = lookUpManager.getPCRLabs();
        String response = null;
        try {
            response = mapper.writeValueAsString(pCRLabs);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(EMRExchangeFragmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
        
    }
	
	//vlsamples is a list of VLSampleInformationFrontFacing and is a json string of Manifest object
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> performVLRequisition(@RequestParam(value = "vlsamples", required = true) String vlsamples,
            @RequestParam(value = "manifest", required = true) String manifestDraft,
            @RequestParam(value = "sampleSpace", required = true) String sampleSpace) {
        
        SampleInfo sampleInfo = new SampleInfo();
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String responseMessage = "";
        
        Date dateSampleSent = new Date();
        
        String temString = UUID.randomUUID().toString();
        String manifestID = temString.substring(1, 15).toUpperCase();
        
        VLSampleCollectionBatchManifest vLSampleCollectionBatchManifest = new VLSampleCollectionBatchManifest();
        
        vLSampleCollectionBatchManifest.setManifestID(manifestID);
        vLSampleCollectionBatchManifest.setSendingFacilityID(Utils.getFacilityDATIMId());
        vLSampleCollectionBatchManifest.setSendingFacilityName(Utils.getFacilityName());

        // vLSampleCollectionBatchManifest.setSampleInformation(vLSampleInformations);
        try {

//            List<VLSampleInformationFrontFacing> vLSampleInformations = mapper.readValue(vlsamples,
//                    new TypeReference<List<VLSampleInformationFrontFacing>>() {
//            });
            List<VLSampleInformationFrontFacing> vLSampleInformations
                    = sampleInfo.searchLabEncounters(mapper.readValue(vlsamples, new TypeReference<List<Integer>>() {
                    }), SampleSpace.valueOf(sampleSpace));
            
            Manifest convertManifest = mapper.readValue(manifestDraft, Manifest.class);
            //  convertManifest.setPcrLabCode("LIMS150002"); // todo
            //  convertManifest.setPcrLabName("National Reference Laboratory Gaduwa (NRL) Abuja"); // todo

            System.out.println("about to update date sample sent");
            
            List<VLSampleInformation> convertedSamples = updateDateSampleSent(vLSampleInformations, dateSampleSent);
            System.out.println("finished updating date sample sent");
            
            vLSampleCollectionBatchManifest.setReceivingLabID(convertManifest.getPcrLabCode());
            vLSampleCollectionBatchManifest.setReceivingLabName(convertManifest.getPcrLabName());
            vLSampleCollectionBatchManifest.setCourierContact(convertManifest.getRiderPhoneNumber());
            vLSampleCollectionBatchManifest.setCourierRiderName(convertManifest.getRiderName());
            vLSampleCollectionBatchManifest.setDateScheduledForPickup(convertManifest.getDateScheduleForPickup());
            vLSampleCollectionBatchManifest.setSamplePackagedBy(convertManifest.getSamplePackagedBy());
            vLSampleCollectionBatchManifest.setTemperatureAtPickup(Integer.parseInt(convertManifest.getRiderTempAtPickUp()));
            vLSampleCollectionBatchManifest.setSampleInformation(convertedSamples);
            
            SampleCollectionManifest sampleCollectionManifest = new SampleCollectionManifest();
            sampleCollectionManifest.setViralloadManifest(vLSampleCollectionBatchManifest);
            
            String token = this.exchangeLayer.requestTokenFromLims();
            if (token != null) {
                sampleCollectionManifest.getViralloadManifest().setToken(token);
            } else {
                return new ResponseEntity<>("Could not process request:Auth problem", HttpStatus.BAD_REQUEST);
            }
            
          //  String manifestJsonString = mapper.writeValueAsString(sampleCollectionManifest);
            String manifestJsonString = gson.toJson(sampleCollectionManifest);
            
            System.out.println("about to send sample online");
            ManifestResponse response = null;
            try {
                System.out.println("manifest info is ");
                System.out.println(manifestJsonString);
                response = this.exchangeLayer.sendSamplesOnline(manifestJsonString);
                System.out.println("finished sending sample online");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            
            RequisitionResponse requisitionResponse = new RequisitionResponse();
//             try {
//                 System.out.println("about log sample on remote server");
//                this.exchangeLayer.logSamplesOnline(manifestJsonString);
//               System.out.println("finished logging samples");
//            } catch (Exception ex) {
//                System.out.println(ex.getMessage());
//            }

            if (response != null && response.isRequestStatus()) {
                updateDateSampleSentOnDB(vLSampleInformations, dateSampleSent);
                //  Manifest manifest = new Manifest();
                convertManifest.setCreatedBy(Context.getAuthenticatedUser().toString());
                convertManifest.setManifestID(manifestID);
                convertManifest.setResultStatus("pending");
                convertManifest.setSampleSpace(sampleSpace); //either VL, RECENCY OR EID                               
                convertManifest.setTestType("VL");
                convertManifest.setFeedback(response.getBody());
                
                boolean insertManifestResult = this.dBUtility.insertManifestEntry(convertManifest);
                if (insertManifestResult) {
                    boolean insertSamplesResult = this.dBUtility.insertManifestSaplesEntry(vLSampleInformations, manifestID,
                            Context.getAuthenticatedUser().toString(), dateSampleSent);
                    
                    System.out.println("finished logging samples");
                }
                //  responseMessage = "sent sucessfully";

                //   RequisitionResponse requisitionResponse = new RequisitionResponse();
                requisitionResponse.setManifestID(manifestID);
                requisitionResponse.setResponseMessage("Samples successfully registered in Lab. Manifest ID is " + manifestID + " check manifest page for feedback message");
                
                return new ResponseEntity<>(gson.toJson(requisitionResponse), HttpStatus.OK);
            } else {
                
                requisitionResponse.setManifestID(manifestID);
                requisitionResponse.setResponseMessage(response.getBody());
                
                return new ResponseEntity<>(gson.toJson(requisitionResponse), HttpStatus.BAD_REQUEST);
                
            }

            //List<VLSampleInformation> vlSamples = mapper.readValue(vlsamples, List<VLSampleInformation>);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
        return new ResponseEntity<>("Could not process request", HttpStatus.BAD_REQUEST);

        //TODO: confirm with Mubarak what details will be return to frontend
    }
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllSavedManifest() {
		
		mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		List<Manifest> manifests = dBUtility.getManifests();
		String response = null;
		try {
			response = gson.toJson(manifests);
		}
		catch (Exception ex) {
			Logger.getLogger(EMRExchangeFragmentController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllPendingManifest() {
		
		mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		List<Manifest> manifests = dBUtility.getPendingManifests();
		String response = null;
		try {
			response = gson.toJson(manifests);
		}
		catch (Exception ex) {
			Logger.getLogger(EMRExchangeFragmentController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return response;
	}
	
	public String getSavedManifestById(@RequestParam(value = "manifestId", required = true) String manifestId) {
		
		mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		Manifest manifest = dBUtility.getManifestsbyId(manifestId);
		String response = null;
		try {
			response = gson.toJson(manifest);
		}
		catch (Exception ex) {
			Logger.getLogger(EMRExchangeFragmentController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return response;
	}
	
	public String getManifestSamples(@RequestParam(value = "manifestId") String manifestId) {
		mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		List<VLSampleInformationFrontFacing> manifestSamples = dBUtility.getSamplesByManifestId(manifestId);
		String response = null;
		try {
			response = gson.toJson(manifestSamples);
		}
		catch (Exception ex) {
			Logger.getLogger(EMRExchangeFragmentController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return response;
	}
	
	private List<VLSampleInformation> updateDateSampleSent(List<VLSampleInformationFrontFacing> allVLSamplefromUI, Date dateSampleSent) {
        
        List<VLSampleInformation> allConvertedSamples = new ArrayList<>();
        
        mapper
                = new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
     //   Gson gson = Utils.getGsonObject();
        
        allVLSamplefromUI.stream().forEach(a -> {
            try {
                System.out.println("sample collection date before conversion: " + a.getSampleCollectionDate());
                VLSampleInformation vLSampleInformation = mapper.readValue(gson.toJson(a), VLSampleInformation.class);
                vLSampleInformation.setDateSampleSent(dateSampleSent);
                allConvertedSamples.add(vLSampleInformation);
                System.out.println("Sample collection date after conversion: " + vLSampleInformation.getSampleCollectionDate());
            } catch (Exception ex) {
                Logger.getLogger(EMRExchangeFragmentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        return allConvertedSamples;
    }
	
	private void updateDateSampleSentOnDB(List<VLSampleInformationFrontFacing> allVLSamplefromUI, Date dateSampleSent) {
        
        allVLSamplefromUI.stream().forEach(a -> {
            Encounter labEncounter = Context.getEncounterService().getEncounter(a.getEncounterId());
            
            Obs dateSampleSentObs = new Obs();
            dateSampleSentObs.setConcept(Context.getConceptService().getConcept(LabFormUtils.DATE_SAMPLE_SENT_TO_PCR_LAB));
            dateSampleSentObs.setValueDate(dateSampleSent);
            dateSampleSentObs.setObsDatetime(new Date());
            dateSampleSentObs.setPerson(labEncounter.getPatient());
            dateSampleSentObs.setEncounter(labEncounter);
            dateSampleSentObs.setUuid(UUID.randomUUID().toString());
            
            labEncounter.addObs(dateSampleSentObs);
            
            Context.getEncounterService().saveEncounter(labEncounter);
            
        });
        
    }
	
	// Get sample result from LIMS
	RestTemplate restTemplate = new RestTemplate();
	
	//	@RequestMapping(/*value="/template/sample_results", */method = RequestMethod.GET, path = "returned_result")
	public String getResultByManifest() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.exchange("https://lims.ng/api/samples/result.php", HttpMethod.GET, entity, String.class)
		        .getBody();
	}
	
}
