/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openmrs.module.limsemrops.omodmodels.ResultRequest;
import org.openmrs.module.limsemrops.omodmodels.VLSampleCollectionBatchManifest;
import org.openmrs.module.limsemrops.service.DBUtility;
import org.openmrs.module.limsemrops.service.ExchangeLayer;
import org.openmrs.module.limsemrops.service.ViralLoadInfo;
import org.openmrs.module.limsemrops.utility.ConstantUtils.SampleSpace;

/**
 * @author MORRISON.I
 */
public class MainTest {
	
	public void testVLLoad() {

        List<Integer> idList = new ArrayList<>();

        DBUtility dBUtility = new DBUtility();
        idList = dBUtility.getLabEncountersByDate(new Date(), new Date(),SampleSpace.VL);
        if (!idList.isEmpty()) {
            ViralLoadInfo viralLoadInfo = new ViralLoadInfo(idList,SampleSpace.VL);
            VLSampleCollectionBatchManifest vLSampleCollectionBatchManifest = new VLSampleCollectionBatchManifest();
            vLSampleCollectionBatchManifest = viralLoadInfo.getRecentSampleCollectedManifest();

            System.out.println(vLSampleCollectionBatchManifest);

        }

    }
	
	public static void main(String[] args) {
		//	System.out.println(UUID.randomUUID().toString());
		
		//  Context.getConceptService().getConcept(84884).getAnswers().stream().forEach(a -> {
		//
		// });
		
		ResultRequest resultRequest = new ResultRequest();
		resultRequest.setManifestID("34CC7F1-70E6-4");
		resultRequest.setReceivingPCRLabID("LIMS150002");
		resultRequest.setReceivingPCRLabName("National Reference Laboratory Gaduwa (NRL) Abuja");
		resultRequest.setSendingFacilityID("FH7LMnbnVlT");
		resultRequest.setSendingFacilityName("Braithwaite Memorial Specialist Hospital");
		resultRequest.setTestType("VL");
		
		ExchangeLayer exchangeLayer = new ExchangeLayer();
		try {
			//	HttpResponse<VLResultResponse> vLResultResponse = exchangeLayer.requestManifestResultOnline(resultRequest);
			ObjectMapper mapper = new ObjectMapper();
			System.out.println(mapper.writeValueAsString("kjfk"));
		}
		catch (Exception ex) {
			Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	
}
