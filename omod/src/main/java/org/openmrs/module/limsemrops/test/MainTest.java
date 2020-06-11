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
		//MainTest mainTest = new MainTest();
		//mainTest.testVLLoad();
		
		//	System.out.println(UUID.randomUUID().toString());
		//		VLSampleInformationFrontFacing vlff = new VLSampleInformationFrontFacing();
		//		vlff.setEncounterId(45);
		//		vlff.setArtCommencementDate(new Date());
		//		vlff.setDrugRegimen("4b");
		//		vlff.setFirstName("Mikado");
		//		vlff.setIndicationVLTest(5);
		//		vlff.setSurName("Ose");
		//		vlff.setSampleID("S7393993");
		//		
		//		VLSampleInformation vlsample = null;
		//		
		//		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//		try {
		//			System.out.println(mapper.writeValueAsString(vlff));
		//			vlsample = mapper.readValue(mapper.writeValueAsString(vlff), VLSampleInformation.class);
		//			System.out.println(mapper.writeValueAsString(vlsample));
		//		}
		//		catch (IOException ex) {
		//			Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
		//		}
		String base64string = "Rk1SACAyMAAAAAECAAABBAEsAMUAxQEAAhBUJkB7ACP7AIBZADKOAIBdAEQGAIApAEoUAEAzAF6aAIAyAHAaAEBvAIb7AECzAJDhAEChAJnkAIBAAJkbAICMAKHoAIC7AKRhAEBdAK0AAICEALHdAIAuALKxAIA6ALMoAEAbALWzAIBeAL30AEBUAMbyAICaAMjTAEBXAMmnAEBZAMq9AICoAMzbAICAANDDAICVANfGAIAgAOC/AECgAOPUAEBmAOMsAIArAObQAEBpAOmRAIC7APRlAEDNAPZoAICfAP0KAIDEAQt5AIB9AQ8VAIDPAR99AICzASABAIByASARAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA==";
		
		byte[] unknownTemplateArray = Base64.getDecoder().decode(base64string);
		
		for (byte a : unknownTemplateArray) {
			System.out.print(a);
		}
		System.out.println("\n");
		System.out.println("#########");
		String byteStr = new String(unknownTemplateArray);
		System.out.println(byteStr);
		
	}
	
}
