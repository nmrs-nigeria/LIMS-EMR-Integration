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
public class CareCardUtils {

    //define all maps
    private Map<Integer, String> conceptMappings = new HashMap<>();
    private Map<Integer, Integer> integerConceptMappings = new HashMap<>();

    public final static int PREGNANCY_BREASTFEEDING_CONCEPT = 165050;

    public CareCardUtils() {
        this.fillConceptMappings();
        this.fillIntegerConceptMappings();
    }

    private void fillConceptMappings() {
        conceptMappings.put(165048, "1");
        conceptMappings.put(165049, "3");
        conceptMappings.put(165047, "2");
    }

    private void fillIntegerConceptMappings() {
        
    }

    public Map<Integer, String> getConceptMappings() {
        return conceptMappings;
    }

    public Map<Integer, Integer> getIntegerConceptMappings() {
        return integerConceptMappings;
    }

}
