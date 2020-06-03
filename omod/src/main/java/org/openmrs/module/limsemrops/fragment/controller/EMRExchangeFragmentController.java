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
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.limsemrops.omodmodels.Manifest;
import org.openmrs.module.limsemrops.omodmodels.VLSampleCollectionBatchManifest;
import org.openmrs.module.limsemrops.omodmodels.VLSampleInformation;
import org.openmrs.module.limsemrops.omodmodels.VLSampleInformationFrontFacing;
import org.openmrs.module.limsemrops.service.DBUtility;
import org.openmrs.module.limsemrops.service.ExchangeLayer;
import org.openmrs.module.limsemrops.service.SampleInfo;
import org.openmrs.module.limsemrops.utility.LabFormUtils;
import org.openmrs.module.limsemrops.utility.Utils;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author MORRISON.I
 */
public class EMRExchangeFragmentController {
	
	private static final Log LOG = LogFactory.getLog(EMRExchangeFragmentController.class);
	
	private ExchangeLayer exchangeLayer;
	
	private DBUtility dBUtility;
	
	public EMRExchangeFragmentController() {
		this.exchangeLayer = new ExchangeLayer();
		this.dBUtility = new DBUtility();
	}
	
	public void testVLLoad() {
		
		// just for testing
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate stDate = LocalDate.of(2020, Month.MAY, 04);
		LocalDate endDate = LocalDate.of(2020, Month.MAY, 24);
		
		String result = searchVLSamples(Date.from(stDate.atStartOfDay(defaultZoneId).toInstant()),
		    Date.from(endDate.atStartOfDay(defaultZoneId).toInstant()));
		
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
	
	public String searchVLSamples(@RequestParam(value = "startDate") Date startDate, @RequestParam(value = "endDate") Date endDate) {

        String response = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        List<VLSampleInformationFrontFacing> vlSampleInfo = new ArrayList<>();
        SampleInfo sampleInfo = new SampleInfo();

        try {
            vlSampleInfo = sampleInfo.searchLabEncounters(startDate, endDate);

            response = mapper.writeValueAsString(vlSampleInfo);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return response;

    }
	
	//vlsamples is a list of VLSampleInformationFrontFacing and is a json string of Manifest object
	public void performVLRequisition(@RequestParam(value = "vlsamples", required = true) String vlsamples,
	        @RequestParam(value = "manifest", required = true) String manifestDraft) {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		Date dateSampleSent = new Date();
		
		String temString = UUID.randomUUID().toString();
		String manifestID = temString.substring(1, 15).toUpperCase();
		
		VLSampleCollectionBatchManifest vLSampleCollectionBatchManifest = new VLSampleCollectionBatchManifest();
		
		vLSampleCollectionBatchManifest.setManifestID(manifestID);
		vLSampleCollectionBatchManifest.setSendingFacilityID(Utils.getFacilityDATIMId());
		vLSampleCollectionBatchManifest.setSendingFacilityName(Utils.getFacilityName());
		
		// vLSampleCollectionBatchManifest.setSampleInformation(vLSampleInformations);
		try {
			
			List<VLSampleInformationFrontFacing> vLSampleInformations = mapper.readValue(vlsamples,
			    new TypeReference<List<VLSampleInformationFrontFacing>>() {});
			
			Manifest convertManifest = mapper.readValue(manifestDraft, Manifest.class);
			
			System.out.println("about to update date sample sent");
			
			List<VLSampleInformation> convertedSamples = updateDateSampleSent(vLSampleInformations, dateSampleSent);
			System.out.println("finished updating date sample sent");
			
			vLSampleCollectionBatchManifest.setReceivingLabID(convertManifest.getPcrLabCode());
			vLSampleCollectionBatchManifest.setReceivingLabName(convertManifest.getPcrLabName());
			vLSampleCollectionBatchManifest.setSampleInformation(convertedSamples);
			
			String manifestJsonString = mapper.writeValueAsString(vLSampleCollectionBatchManifest);
			
			System.out.println("about to send sample online");
			Boolean response = this.exchangeLayer.sendSamplesOnline(manifestJsonString);
			System.out.println("finished sending sample online");
			
			if (response == true) {
				updateDateSampleSentOnDB(vLSampleInformations, dateSampleSent);
				Manifest manifest = new Manifest();
				manifest.setCreatedBy(Context.getAuthenticatedUser().toString());
				manifest.setManifestID(manifestID);
				manifest.setResultStatus("pending");
				manifest.setSampleSpace("VL");
				
				boolean insertManifestResult = this.dBUtility.insertManifestEntry(manifest);
				if (insertManifestResult) {
					boolean insertSamplesResult = this.dBUtility.insertManifestSaplesEntry(vLSampleInformations, manifestID,
					    Context.getAuthenticatedUser().toString());
					
					System.out.println("finished logging samples");
				}
				
			}
			
			//List<VLSampleInformation> vlSamples = mapper.readValue(vlsamples, List<VLSampleInformation>);
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		
		//TODO: confirm with Mubarak what details will be return to frontend
	}
	
	public String getAllSavedManifest() {
		
		ObjectMapper mapper = new ObjectMapper();
		List<Manifest> manifests = dBUtility.getManifests();
		String response = null;
		try {
			response = mapper.writeValueAsString(manifests);
		}
		catch (JsonProcessingException ex) {
			Logger.getLogger(EMRExchangeFragmentController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return response;
	}
	
	public String getManifestSamples(@RequestParam(value = "manifestId") String manifestId) {
		ObjectMapper mapper = new ObjectMapper();
		List<VLSampleInformationFrontFacing> manifestSamples = dBUtility.getSamplesByManifestId(manifestId);
		String response = null;
		try {
			response = mapper.writeValueAsString(manifestSamples);
		}
		catch (JsonProcessingException ex) {
			Logger.getLogger(EMRExchangeFragmentController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return response;
	}
	
	private List<VLSampleInformation> updateDateSampleSent(List<VLSampleInformationFrontFacing> allVLSamplefromUI, Date dateSampleSent) {

        List<VLSampleInformation> allConvertedSamples = new ArrayList<>();

        ObjectMapper mapper
                = new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        allVLSamplefromUI.stream().forEach(a -> {
            try {
                VLSampleInformation vLSampleInformation = mapper.readValue(mapper.writeValueAsString(a), VLSampleInformation.class);
                vLSampleInformation.setDateSampleSent(dateSampleSent);
                allConvertedSamples.add(vLSampleInformation);
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
}
