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
import org.openmrs.module.limsemrops.omodmodels.VLSampleInformationFrontFacing;
import org.openmrs.module.limsemrops.utility.CareCardUtils;
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
	
	private Map<Integer, Integer> integerLabMappings;
	
	private Obs rovingObs;
	
	private DBUtility dBUtility;
	
	public ViralLoadInfo(List<Integer> encounterList) {

        this.encounterIDList = encounterList;
        this.obsList = new ArrayList<>();
        this.encounterList = new ArrayList<>();

        labFormUtils = new LabFormUtils();

        labMappings = new HashMap<>();
        integerLabMappings = new HashMap<>();
        this.dBUtility = new DBUtility();

        loadMappings();
        rovingObs = new Obs();

    }
	
	private void loadMappings() {
		labMappings = labFormUtils.getConceptMappings();
		integerLabMappings = labFormUtils.getIntegerConceptMappings();
	}
	
	private String getMappedAnswerValue(int conceptID) {
		if (labMappings.containsKey(conceptID)) {
			return labMappings.get(conceptID);
		}
		return "";
	}
	
	private Integer getIntgerMappedAnswerValue(int conceptID) {
		if (integerLabMappings.containsKey(conceptID)) {
			return integerLabMappings.get(conceptID);
		}
		return null;
	}
	
	public VLSampleCollectionBatchManifest getRecentSampleCollectedManifest() {

        Patient patient = null;
        VLSampleCollectionBatchManifest vLSampleCollectionBatchManifest
                = new VLSampleCollectionBatchManifest();

        List<VLSampleInformation> vLSampleInformations = new ArrayList<>();

        fillUpEncounters(encounterIDList);

        for (Encounter e : encounterList) {
            System.out.println("Processing encounter " + e.getEncounterId());

            patient = e.getPatient();
            Set<Obs> obsSet = e.getAllObs();
            List<Obs> tempObs = new ArrayList<>();
            tempObs.addAll(obsSet);
            System.out.println("Temp obs contains elements " + tempObs.size());

            //check if this lab form if for VL
            if (tempObs.stream().map(Obs::getConcept).map(Concept::getConceptId).
                    collect(Collectors.toList())
                    .contains(LabFormUtils.VIRAL_LOAD_REQUEST)) {
                obsList.clear();
                obsList.addAll(obsSet);

                System.out.println("Obs list contains VL Load request");

                VLSampleInformation vLSampleInformation = extractVLInfo(patient, e);
                vLSampleInformations.add(vLSampleInformation);
            }

        }

        String temString = UUID.randomUUID().toString();
        vLSampleCollectionBatchManifest.setManifestID(temString.substring(1, 15).toUpperCase());
        vLSampleCollectionBatchManifest.setReceivingLabID("LIMS150003"); // todo
        vLSampleCollectionBatchManifest.setReceivingLabName("Asokoro Laboratory and Training Center"); // todo
        vLSampleCollectionBatchManifest.setSendingFacilityID(Utils.getFacilityDATIMId());
        vLSampleCollectionBatchManifest.setSendingFacilityName(Utils.getFacilityName());

        vLSampleCollectionBatchManifest.setSampleInformation(vLSampleInformations);

        return vLSampleCollectionBatchManifest;

    }
	
	public List<VLSampleInformationFrontFacing> searchLabEncounter() {

        Patient patient = null;
     
        List<VLSampleInformationFrontFacing> vLSampleInformations = new ArrayList<>();

        fillUpEncounters(encounterIDList);

        for (Encounter e : encounterList) {
            System.out.println("Processing encounter " + e.getEncounterId());

            patient = e.getPatient();
            Set<Obs> obsSet = e.getAllObs();
            List<Obs> tempObs = new ArrayList<>();
            tempObs.addAll(obsSet);
            System.out.println("Temp obs contains elements " + tempObs.size());

            //check if this lab form if for VL
            if (tempObs.stream().map(Obs::getConcept).map(Concept::getConceptId).
                    collect(Collectors.toList())
                    .contains(LabFormUtils.VIRAL_LOAD_REQUEST)) {
                obsList.clear();
                obsList.addAll(obsSet);

                System.out.println("Obs list contains VL Load request");

                VLSampleInformationFrontFacing vLSampleInformation = extractVLInfoPerPatient(patient, e);
                vLSampleInformations.add(vLSampleInformation);
            }

        }
        
        return vLSampleInformations;

    }
	
	// this is the right method that would be called from the UI
	private VLSampleInformationFrontFacing extractVLInfoPerPatient(Patient p, Encounter e) {
		
		VLSampleInformationFrontFacing vLSampleInformation = new VLSampleInformationFrontFacing();
		PatientDemographics patientDemographics = new PatientDemographics(p);
		vLSampleInformation = patientDemographics.fillUpPatientDemographics();
		
		if (!this.obsList.isEmpty()) {
			//sample ID
			rovingObs = Utils.extractObs(LabFormUtils.SAMPLE_TYPE, this.obsList);
			if (rovingObs != null && rovingObs.getValueCoded() != null) {
				vLSampleInformation.setSampleType(getMappedAnswerValue(rovingObs.getValueCoded().getConceptId()));
			}
			
			// indication for VL
			rovingObs = Utils.extractObs(LabFormUtils.INDICATION_FOR_VL, this.obsList);
			if (rovingObs != null && rovingObs.getValueCoded() != null) {
				vLSampleInformation
				        .setIndicationVLTest(getIntgerMappedAnswerValue(rovingObs.getValueCoded().getConceptId()));
			}
			
			vLSampleInformation.setArtCommencementDate(getPatientARTStartDate(p));
			
			String patientLastRegimen = getPatientLatestRegimen(p);
			if (patientLastRegimen != null) {
				vLSampleInformation.setDrugRegimen(patientLastRegimen);
			}
			
			if (p.getGender().equalsIgnoreCase("F")) {
				String pregnancyStatus = getPatientPregnancyStatus(p);
				
				if (pregnancyStatus != null) {
					vLSampleInformation.setPregnantBreastFeedingStatus(pregnancyStatus);
				}
			}
			
			//order by and sample collected by
			rovingObs = Utils.extractObs(LabFormUtils.REPORTED_BY, this.obsList);
			if (rovingObs != null) {
				vLSampleInformation.setSampleOrderedBy(rovingObs.getValueText());
				vLSampleInformation.setSampleCollectedBy(rovingObs.getValueText());
			}
			
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
			
			//date sample sent
			rovingObs = Utils.extractObs(LabFormUtils.DATE_SAMPLE_SENT_TO_PCR_LAB, this.obsList);
			if (rovingObs != null) {
				vLSampleInformation.setDateSampleSent(rovingObs.getValueDate());
			}
			
			vLSampleInformation.setEncounterId(e.getEncounterId());
			
		}
		
		return vLSampleInformation;
		
	}
	
	//this is for testing purpose with TG
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
				vLSampleInformation
				        .setIndicationVLTest(getIntgerMappedAnswerValue(rovingObs.getValueCoded().getConceptId()));
			}
			
			vLSampleInformation.setArtCommencementDate(getPatientARTStartDate(p));
			
			String patientLastRegimen = getPatientLatestRegimen(p);
			if (patientLastRegimen != null) {
				vLSampleInformation.setDrugRegimen(patientLastRegimen);
			}
			
			if (p.getGender().equalsIgnoreCase("F")) {
				String pregnancyStatus = getPatientPregnancyStatus(p);
				
				if (pregnancyStatus != null) {
					vLSampleInformation.setPregnantBreastFeedingStatus(pregnancyStatus);
				}
			}
			
			//order by and sample collected by
			rovingObs = Utils.extractObs(LabFormUtils.REPORTED_BY, this.obsList);
			if (rovingObs != null) {
				String sampleCollectedBy = rovingObs.getValueText();
				String splitCollectedBy = sampleCollectedBy.split(" - ")[1];
				vLSampleInformation.setSampleOrderedBy(splitCollectedBy);
				vLSampleInformation.setSampleCollectedBy(splitCollectedBy);
			}
			
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
	
	private Date getPatientARTStartDate(Patient patient) {
        List<Integer> encounters = dBUtility.getEnrollmentAndPharmacy(patient);
        List<Encounter> hivPharmEncounters = new ArrayList<>();

        encounters.stream().forEach(a -> {

            Encounter each = null;
            each = Context.getEncounterService().getEncounter(a);

            if (each != null) {
                hivPharmEncounters.add(each);
            }

        });
        List<Obs> obsList = new ArrayList<>();
        obsList = Utils.getObsbyEncounter(hivPharmEncounters);

        return Utils.extractARTStartDate(patient, obsList);

    }
	
	private String getPatientLatestRegimen(Patient p) {
		Encounter latestPharmEncounter = Utils.getPatientLastEncounter(p, ConstantUtils.Pharmacy_Encounter_Type_Id);
		String regimenCode = null;
		if (latestPharmEncounter != null) {
			regimenCode = Utils.getPatientLastRegimenByEncounter(latestPharmEncounter);
		}
		
		return regimenCode;
	}
	
	private String getPatientPregnancyStatus(Patient p) {
        Encounter latestCarecardEncounter = Utils.getPatientLastEncounter(p, ConstantUtils.Care_card_Encounter_Type_Id);
        CareCardUtils cardUtils = new CareCardUtils();
        List<Obs> careObs = new ArrayList<>();
        Map<Integer, String> mappings = cardUtils.getConceptMappings();
        String response = null;
        if (latestCarecardEncounter != null) {

            careObs = new ArrayList<>(latestCarecardEncounter.getAllObs());

            Obs pregObs = Utils.extractObs(CareCardUtils.PREGNANCY_BREASTFEEDING_CONCEPT, careObs);

            if (pregObs != null && pregObs.getValueCoded() != null) {

                response = mappings.get(pregObs.getValueCoded().getConceptId());

            }

        }

        return response;

    }
}
