/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.test;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.openmrs.api.context.Context;
import org.openmrs.module.limsemrops.omodmodels.VLSampleCollectionBatchManifest;
import org.openmrs.module.limsemrops.service.DBUtility;
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
	
	public static void main(String args[]) {
		System.out.println(UUID.randomUUID().toString());
		
		//  Context.getConceptService().getConcept(84884).getAnswers().stream().forEach(a -> {
		//
		// });
		
	}
	
}
