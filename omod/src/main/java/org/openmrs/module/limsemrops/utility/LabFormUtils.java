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
    private final Map<Integer, String> conceptMappings = new HashMap<>();
    private final Map<Integer, Integer> integerConceptMappings = new HashMap<>();
    private final Map<Integer, String> pcrLabs = new HashMap<>();

    public LabFormUtils() {
        this.fillConceptMappings();
        this.fillIntegerConceptMappings();
        this.fillPCRLabs();
    }

    public static final Integer VIRAL_LOAD_REQUEST_RECENCY = 166244;
    public static final Integer VIRAL_LOAD_REQUEST = 165765;
    public static final Integer SAMPLE_ID = 165715;
    public static final Integer SAMPLE_TYPE = 162476;
    public static final Integer INDICATION_FOR_VL = 164980;
    public static final Integer SAMPLE_COLLECTION_DATE = 159951;
    public static final Integer DATE_SAMPLE_SENT_TO_PCR_LAB = 165988;
    public static final Integer DATE_SAMPLE_RECEIVED_AT_PCR_LAB = 165716;
    public static final Integer VIRAL_LOAD_RESULT = 856;
    public static final Integer DATE_RESULT_SENT_FROM_PCR_LAB = 165989;
    public static final Integer DATE_RESULT_WAS_RECEIVED_AT_FACILITY = 165987;
    public static final Integer DATE_SAMPLE_ORDERED = 164989;
    public final static int CHECKED_BY = 164983;
    public final static int DATE_CHECKED = 164984;
    public final static int DATE_REPORTED = 1644984;
    public final static int REPORTED_BY = 164982;
    public final static int RESULT_DATE = 166423;
    public final static int ASSAY_DATE = 166424;
    public final static int APPROVAL_DATE = 166425;
    public final static int ALPHA_NUMERIC_TEST_RESULT = 166422;
    public final static int NUMERIC_VALUE = 166426;
    

    private void fillConceptMappings() {
        conceptMappings.put(1000, "WB");
        conceptMappings.put(165568, "DBS");
        conceptMappings.put(1002, "PLASMA");
    }

    private void fillIntegerConceptMappings() {
        integerConceptMappings.put(162080, 1); //baseline
        integerConceptMappings.put(161236, 2); //routine
        integerConceptMappings.put(162082, 5); //confirmation
        integerConceptMappings.put(162081, 6); // repeat test
        integerConceptMappings.put(163523, 3); //clinical failure
        integerConceptMappings.put(160566, 4); //immunological failure
        //PMTCT conceptMappings.put(165978, "7"); //PMTCT
    }

    private void fillPCRLabs() {
        //PCR Lab
        pcrLabs.put(166217, "LIMS190001");
        pcrLabs.put(166218, "LIMS040001");
        pcrLabs.put(166219, "LIMS330001");
        pcrLabs.put(166220, "LIMS150001");
        pcrLabs.put(166221, "LIMS070001");
        pcrLabs.put(166222, "LIMS160001");
        pcrLabs.put(166223, "LIMS320001");
        pcrLabs.put(166224, "LIMS250001");
        pcrLabs.put(166225, "LIMS150002");
        pcrLabs.put(166226, "LIMS250002");
        pcrLabs.put(166227, "LIMS040002");
        pcrLabs.put(166228, "LIMS300001");
        pcrLabs.put(166229, "LIMS080001");
        pcrLabs.put(166230, "LIMS030001");
        pcrLabs.put(166231, "LIMS340001");
        pcrLabs.put(166232, "LIMS350001");
        pcrLabs.put(166234, "LIMS250003");
        pcrLabs.put(166247, "LIMS150003");
        pcrLabs.put(166248, "LIMS150004");
        pcrLabs.put(166249, "LIMS200001");
        pcrLabs.put(166250, "LIMS320002");
        pcrLabs.put(166251, "LIMS310001");

    }

    public Map<Integer, String> getConceptMappings() {
        return conceptMappings;
    }

    public Map<Integer, Integer> getIntegerConceptMappings() {
        return integerConceptMappings;
    }

    public Map<Integer, String> getPcrLabs() {
        return pcrLabs;
    }

    
    

}
