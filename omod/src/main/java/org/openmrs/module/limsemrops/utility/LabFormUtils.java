/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.utility;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author MORRISON.I
 */
public class LabFormUtils {

    //define all maps
    private Map<Integer, String> conceptMappings = new HashMap<>();

    public LabFormUtils() {
        this.fillConceptMappings();
    }

    public static final Integer VIRAL_LOAD_REQUEST = 165765;
    public static final Integer SAMPLE_ID = 165715;
    public static final Integer SAMPLE_TYPE = 162476;
    public static final Integer INDICATION_FOR_VL = 164980;
    public static final Integer SAMPLE_COLLECTION_DATE = 159951;
    public static final Integer DATE_SAMPLE_SENT_TO_PCR_LAB = 165988;
    public static final Integer DATE_SAMPLE_RECEIVED_TO_PCR_LAB = 165716;
    public static final Integer VIRAL_LOAD_RESULT = 856;
    public static final Integer DATE_SAMPLE_SENT_FROM_PCR_LAB = 165989;
    public static final Integer DATE_RESULT_WAS_RECEIVED_AT_FACILITY = 165987;
    public static final Integer DATE_SAMPLE_ORDERED = 164989;
    public final static int CHECKED_BY = 164983;
    public final static int DATE_CHECKED = 164984;
    public  final static int DATE_REPORTED = 1644984;
    public final static int REPORTED_BY = 164982;
    
    

    private void fillConceptMappings() {
        conceptMappings.put(1000, "WB");
        conceptMappings.put(165568, "DBS");
        conceptMappings.put(162080, "1"); //baseline
        conceptMappings.put(161236, "2"); //routine
        conceptMappings.put(162082, "5"); //confirmation
        conceptMappings.put(162081, "6"); // repeat test
        conceptMappings.put(163523, "3"); //clinical failure
        conceptMappings.put(160566, "4"); //immunological failure
        //PMTCT conceptMappings.put(165978, "7"); //PMTCT

    }

    public Map<Integer, String> getConceptMappings() {
        return conceptMappings;
    }

   
    
    
    
    

}
