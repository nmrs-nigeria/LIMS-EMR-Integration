/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.openmrs.Cohort;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.limsemrops.omodmodels.VLSampleCollectionBatchManifest;
import org.openmrs.module.limsemrops.omodmodels.VLSampleInformation;
import org.openmrs.module.limsemrops.utility.ConstantUtils;
import org.openmrs.module.limsemrops.utility.LabFormUtils;
import org.openmrs.module.limsemrops.utility.Utils;

/**
 * @author MORRISON.I
 */
public class ViralLoadInfo {
	
	private List<Integer> encounterIDList;
	
	private List<Obs> obsList;
	
	private List<Encounter> encounterList;
	
	private LabFormUtils labFormUtils;
	
	private Map<Integer, String> labMappings;
	
	private Obs rovingObs;
	
	public ViralLoadInfo(List<Integer> encounterList) {

        this.encounterIDList = encounterList;
        this.obsList = new ArrayList<>();
        this.encounterList = new ArrayList<>();

        labFormUtils = new LabFormUtils();

        labMappings = new HashMap<>();

        loadMappings();
        rovingObs = new Obs();

    }
	
	private void loadMappings() {
		labMappings = labFormUtils.getConceptMappings();
	}
	
	private String getMappedAnswerValue(int conceptID) {
		if (labMappings.containsKey(conceptID)) {
			return labMappings.get(conceptID);
		}
		return "";
	}
	
	public VLSampleCollectionBatchManifest getRecentSampleCollectedManifest() {

        Patient patient = null;
        VLSampleCollectionBatchManifest vLSampleCollectionBatchManifest
                = new VLSampleCollectionBatchManifest();

        List<VLSampleInformation> vLSampleInformations = new ArrayList<>();

        fillUpEncounters(encounterIDList);

        for (Encounter e : encounterList) {
            System.out.println("Processing encounter "+e.getEncounterId());
            
            patient = e.getPatient();
            Set<Obs> obsSet = e.getAllObs();
            List<Obs> tempObs = new ArrayList<>();
            tempObs.addAll(obsSet);
            System.out.println("Temp obs contains elements "+tempObs.size());
            
            if (tempObs.stream().map(Obs::getConcept).map(Concept::getConceptId).
                    collect(Collectors.toList())
                    .contains(LabFormUtils.VIRAL_LOAD_REQUEST)) {
                obsList.addAll(obsSet);
               
                System.out.println("Obs list contains VL Load request");
                

                VLSampleInformation vLSampleInformation = extractVLInfo(patient, e);
                vLSampleInformations.add(vLSampleInformation);
            }

        }

        String temString = UUID.randomUUID().toString();
        vLSampleCollectionBatchManifest.setManifestId(temString.substring(1, 15).toUpperCase());
        vLSampleCollectionBatchManifest.setSampleInformation(vLSampleInformations);

        return vLSampleCollectionBatchManifest;

    }
	
	private VLSampleInformation extractVLInfo(Patient p, Encounter e) {
		
		VLSampleInformation vLSampleInformation = new VLSampleInformation();
		PatientDemographics patientDemographics = new PatientDemographics(p);
		vLSampleInformation = patientDemographics.fillUpDemographics();
		
		if (!this.obsList.isEmpty()) {
			//sample ID
			rovingObs = Utils.extractObs(LabFormUtils.SAMPLE_TYPE, this.obsList);
			if (rovingObs != null && rovingObs.getValueCoded() != null) {
				vLSampleInformation.setSampleType(getMappedAnswerValue(rovingObs.getValueCoded().getConceptId()));
			}
			
			// indication for VL
			rovingObs = Utils.extractObs(LabFormUtils.INDICATION_FOR_VL, this.obsList);
			if (rovingObs != null && rovingObs.getValueCoded() != null) {
				vLSampleInformation.setIndicationVLTest(getMappedAnswerValue(rovingObs.getValueCoded().getConceptId()));
			}
			
			//lab ID
			vLSampleInformation.setLabID("LIMS-001-98");
			
			vLSampleInformation.setArtCommencementDate(new Date());
			vLSampleInformation.setDrugRegimen("AZT-2T-DTC");
			vLSampleInformation.setFacilityID(Utils.getFacilityDATIMId());
			//  vLSampleInformation.setPregnantBreastfeadingStatus();
			
			//rovingObs = Utils.extractObs(LabFormUtils., obsList)
			vLSampleInformation.setSampleCollectedBy(e.getEncounterProviders().stream().findFirst().get().getProvider()
			        .getName());
			
			// sample collection date
			rovingObs = Utils.extractObs(LabFormUtils.SAMPLE_COLLECTION_DATE, this.obsList);
			if (rovingObs != null) {
				vLSampleInformation.setSampleCollectionDate(rovingObs.getValueDate());
				vLSampleInformation.setSampleCollectionTime(rovingObs.getValueDatetime());
			}
			
			// sample ID
			rovingObs = Utils.extractObs(LabFormUtils.SAMPLE_ID, this.obsList);
			if (rovingObs != null) {
				vLSampleInformation.setSampleID(rovingObs.getValueText());
			}
			
			//order date
			rovingObs = Utils.extractObs(LabFormUtils.DATE_SAMPLE_ORDERED, this.obsList);
			if (rovingObs != null) {
				vLSampleInformation.setSampleOrderDate(rovingObs.getValueDate());
			}
			
			//order by
			rovingObs = Utils.extractObs(LabFormUtils.REPORTED_BY, this.obsList);
			if (rovingObs != null) {
				vLSampleInformation.setSampleOrderedBy(rovingObs.getValueText());
			}
			
			//date sample sent
			rovingObs = Utils.extractObs(LabFormUtils.DATE_SAMPLE_SENT_TO_PCR_LAB, this.obsList);
			if (rovingObs != null) {
				vLSampleInformation.setDateSampleSent(rovingObs.getValueDate());
			}
			
		}
		
		return vLSampleInformation;
		
	}
	
	private void fillUpEncounters(List<Integer> encs) {

        encounterList.clear();

        encs.stream().forEach(a -> {

            Encounter each = null;
            each = Context.getEncounterService().getEncounter(a);

            if (each != null) {
                encounterList.add(each);
            }

        });

    }
}
