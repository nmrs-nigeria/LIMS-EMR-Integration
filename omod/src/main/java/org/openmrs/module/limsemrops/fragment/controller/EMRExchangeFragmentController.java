/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.fragment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openmrs.module.limsemrops.omodmodels.SampleCollectionManifest;
import org.openmrs.module.limsemrops.omodmodels.VLSampleCollectionBatchManifest;
import org.openmrs.module.limsemrops.service.DBUtility;
import org.openmrs.module.limsemrops.service.ExchangeLayer;
import org.openmrs.module.limsemrops.service.ViralLoadInfo;

/**
 * @author MORRISON.I
 */
public class EMRExchangeFragmentController {
	
	private ExchangeLayer exchangeLayer;
	
	public EMRExchangeFragmentController() {
		this.exchangeLayer = new ExchangeLayer();
	}
	
	public void testVLLoad() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        List<Integer> idList = new ArrayList<>();

        DBUtility dBUtility = new DBUtility();
        idList = dBUtility.getLabEncountersByDate(new Date(), new Date());
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
}
