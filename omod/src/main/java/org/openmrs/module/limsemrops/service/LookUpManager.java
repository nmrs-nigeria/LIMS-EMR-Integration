/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.limsemrops.omodmodels.PCRLab;
import org.openmrs.module.limsemrops.utility.ConstantUtils;
import org.openmrs.module.limsemrops.utility.LabFormUtils;

/**
 * @author MORRISON.I
 */
public class LookUpManager {
	
	private ConceptService conceptService;
	
	private LabFormUtils labFormUtils;
	
	public LookUpManager() {
		this.conceptService = Context.getService(ConceptService.class);
		this.labFormUtils = new LabFormUtils();
	}
	
	public List<PCRLab> getPCRLabs() {
        Concept pcrlabconcept = conceptService.getConcept(ConstantUtils.PCR_LABS_CONCEPT);
        List<PCRLab> pCRLabs = new ArrayList<>();

        if (pcrlabconcept != null) {
            pcrlabconcept.getAnswers().forEach((ConceptAnswer a) -> {
                PCRLab pCRLab = new PCRLab();
                pCRLab.setPcrLab(a.getAnswerConcept().getName().getName());
                pCRLab.setPcrLabCode(this.labFormUtils.getPcrLabs().get(a.getAnswerConcept().getId()));
                pCRLabs.add(pCRLab);
            });
        }
        
        return pCRLabs;
        
    }
}
