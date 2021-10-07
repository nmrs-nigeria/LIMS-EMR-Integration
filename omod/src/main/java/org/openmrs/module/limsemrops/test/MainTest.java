/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import info.debatty.java.stringsimilarity.Levenshtein;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
	
	private final static String[] nonNumericLimsResult = { "tnd", "<20", "<40", ">10000000", "target not detected", "<29",
	        "<400", "<20 cp/ml", "<20 copies/ml", "<40 copies/ml", "<40 cp/ml", "not detected", "< 40", "< 20" };
	
	public void testVLLoad() {

        List<Integer> idList = new ArrayList<>();

        DBUtility dBUtility = new DBUtility();
        idList = dBUtility.getLabEncountersByDate(new Date(), new Date(), SampleSpace.VL);
        if (!idList.isEmpty()) {
            ViralLoadInfo viralLoadInfo = new ViralLoadInfo(idList, SampleSpace.VL);
            VLSampleCollectionBatchManifest vLSampleCollectionBatchManifest = new VLSampleCollectionBatchManifest();
            vLSampleCollectionBatchManifest = viralLoadInfo.getRecentSampleCollectedManifest();

            System.out.println(vLSampleCollectionBatchManifest);

        }

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
	
	public static void main(String[] args) {

        String[] sampleID = {"1", "12","b", "3", "20", "5", "6","a", "7", "8", "9", "17", "11", "2","e", "13", "14", "15", "16", "10","d", "18", "19", "4"};

        List<String> asList = Arrays.asList(sampleID)
                .stream()
                .sorted()
                .collect(Collectors.toList());

        asList.stream().forEach(a -> {
            System.out.println(a);
        });

//		System.out.println(isNumeric("22"));
//		System.out.println(isNumeric("<22"));
//		
//		Levenshtein ld = new Levenshtein();
//		System.out.println(ld.distance("target not detected", "target not Dtected"));
//		System.out.println(ld.distance("Invalid", "Undected"));
//		System.out.println(ld.distance("Invalid", "target not detected"));
    }
}
