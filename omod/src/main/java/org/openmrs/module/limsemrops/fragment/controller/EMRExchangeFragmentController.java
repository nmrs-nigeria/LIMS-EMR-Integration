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
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.limsemrops.omodmodels.SampleCollectionManifest;
import org.openmrs.module.limsemrops.omodmodels.VLSampleCollectionBatchManifest;
import org.openmrs.module.limsemrops.omodmodels.VLSampleInformation;
import org.openmrs.module.limsemrops.omodmodels.VLSampleInformationFrontFacing;
import org.openmrs.module.limsemrops.service.DBUtility;
import org.openmrs.module.limsemrops.service.ExchangeLayer;
import org.openmrs.module.limsemrops.service.SampleInfo;
import org.openmrs.module.limsemrops.service.ViralLoadInfo;
import org.openmrs.module.limsemrops.utility.LabFormUtils;
import org.openmrs.module.limsemrops.utility.Utils;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author MORRISON.I
 */
public class EMRExchangeFragmentController {
	
	private ExchangeLayer exchangeLayer;
        private DBUtility dBUtility;
	
	public EMRExchangeFragmentController() {
		this.exchangeLayer = new ExchangeLayer();
                this.dBUtility = new DBUtility();
	}
	
	public void testVLLoad() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        List<Integer> idList = new ArrayList<>();

        DBUtility dBUtility = new DBUtility();
        idList = dBUtility.getTestLabEncountersByDate(new Date(), new Date());
        if (!idList.isEmpty()) {
            ViralLoadInfo viralLoadInfo = new ViralLoadInfo(idList);
            VLSampleCollectionBatchManifest vLSampleCollectionBatchManifest = new VLSampleCollectionBatchManifest();
            vLSampleCollectionBatchManifest = viralLoadInfo.getRecentSampleCollectedManifest();
            SampleCollectionManifest sampleCollectionManifest = new SampleCollectionManifest();
            sampleCollectionManifest.setViralloadManifest(vLSampleCollectionBatchManifest);

            try {
                String manifestString = mapper.writeValueAsString(sampleCollectionManifest);
                System.out.println(manifestString);
                this.exchangeLayer.sendSamplesOnline(manifestString);

                System.out.println("FINISHED PROCESSING");

            } catch (JsonProcessingException ex) {
                System.err.println(ex.getMessage());
            } catch (UnirestException ex) {
                System.err.println(ex.getMessage());
            }

        }

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
	
	public void performVLRequisition(@RequestParam(value = "vlsamples", required = true) String vlsamples,
	        @RequestParam(value = "labid", required = true) String labid,
	        @RequestParam(value = "labName", required = true) String labName) {
		
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
			
			List<VLSampleInformation> convertedSamples = updateDateSampleSent(vLSampleInformations, dateSampleSent);
			
			vLSampleCollectionBatchManifest.setReceivingLabID(labid);
			vLSampleCollectionBatchManifest.setReceivingLabName(labName);
			vLSampleCollectionBatchManifest.setSampleInformation(convertedSamples);
			
			String manifestJsonString = mapper.writeValueAsString(vLSampleCollectionBatchManifest);
			Boolean response = this.exchangeLayer.sendSamplesOnline(manifestJsonString);
			
			if (response == true) {
				updateDateSampleSentOnDB(vLSampleInformations, dateSampleSent);
                                
                                
			}
			
			//List<VLSampleInformation> vlSamples = mapper.readValue(vlsamples, List<VLSampleInformation>);
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		
		//TODO: confirm with Mubarak what details will be return to frontend
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
