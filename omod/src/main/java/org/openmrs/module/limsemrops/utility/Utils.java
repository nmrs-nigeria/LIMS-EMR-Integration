/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.limsemrops.omodmodels.DBConnection;
import org.openmrs.util.OpenmrsUtil;

/**
 * @author MORRISON.I
 */
public class Utils {
	
	public static Obs extractObs(int conceptID, List<Obs> obsList) {

        if (obsList == null) {
            return null;
        }
        return obsList.stream().filter(ele -> ele.getConcept().getConceptId() == conceptID).findFirst().orElse(null);
    }
	
	public static DBConnection getNmrsConnectionDetails() {
		
		DBConnection result = new DBConnection();
		
		try {
			
			Properties props = new Properties();
			props = OpenmrsUtil.getRuntimeProperties("openmrs");
			if (props == null) {
				props = OpenmrsUtil.getRuntimeProperties("openmrs-standalone");
				
			}
			
			result.setUsername(props.getProperty("connection.username"));
			result.setPassword(props.getProperty("connection.password"));
			result.setUrl(props.getProperty("connection.url"));
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return result;
		
	}
	
	public static DBConnection getHACKNmrsConnectionDetails() {
		
		DBConnection result = new DBConnection();
		
		try {
			
			result.setUsername("root");
			result.setPassword("faster129");
			result.setUrl("jdbc\\:mysql\\://localhost\\:3306/nmrs-boot?autoReconnect\\=true&sessionVariables\\=default_storage_engine%3DInnoDB&useUnicode\\=true&characterEncoding\\=UTF-8");
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return result;
		
	}
	
	public static String getFacilityDATIMId() {
		return Context.getAdministrationService().getGlobalProperty("facility_datim_code");
	}
	
	public static String getFacilityName() {
		return Context.getAdministrationService().getGlobalProperty("Facility_Name");
	}
	
	public static Date extractARTStartDate(Patient patient, List<Obs> allPatientsObsList) {
		Date artStartDate = null;
		Obs obs = null;
		obs = Utils.extractObs(ConstantUtils.ART_START_DATE_CONCEPT, allPatientsObsList);
		if (obs != null) {
			artStartDate = obs.getValueDate();
		} else {
			obs = getFirstObsOfConceptByDate(allPatientsObsList, ConstantUtils.CURRENT_REGIMEN_LINE_CONCEPT);
			if (obs != null) {
				artStartDate = obs.getObsDatetime();
			}
		}
		return artStartDate;
	}
	
	public static Obs getFirstObsOfConceptByDate(List<Obs> obsList, int conceptID) {
        Obs obs = null;
        List<Obs> regimenLineObsList = new ArrayList<Obs>();
        for (Obs ele : obsList) {
            if (ele.getConcept().getConceptId() == conceptID) {
                regimenLineObsList.add(ele);
            }
        }
        if (!regimenLineObsList.isEmpty()) {
            regimenLineObsList.sort(Comparator.comparing(Obs::getObsDatetime));
            return regimenLineObsList.get(0);
        }
        return null;

    }
	
	public static List<Obs> getObsbyEncounter(List<Encounter> encounters) {

        return encounters.stream().flatMap(encounter -> encounter.getAllObs()
                .stream()).collect(Collectors.toList());

    }
	
	public static Encounter getPatientLastEncounter(Patient p, Integer enconterType) {
        List<Encounter> encounterList = Context.getEncounterService()
                .getEncountersByPatient(p).stream().filter(a -> Objects.equals(a.getEncounterType().getEncounterTypeId(), enconterType))
                .sorted(Comparator.comparing(Encounter::getEncounterDatetime))
                .collect(Collectors.toList());

        if (!encounterList.isEmpty()) {
            return encounterList.get(0);
        }
        return null;
    }
	
	public static String getPatientLastRegimenByEncounter(Encounter lastPharmEncounter) {
        //assuming encounter is pharmacy encounter

        PharmFormUtils pharmFormUtils = new PharmFormUtils();
        Map<Integer, String> regimenMap = pharmFormUtils.getRegimenMap();
        Integer valueCoded;
        String ndrCodeMapping = null;
        List<Obs> allObs = new ArrayList<>();

        if (lastPharmEncounter != null) {
            allObs = new ArrayList<>(lastPharmEncounter.getAllObs());

            Obs obs = extractObs(ConstantUtils.CURRENT_REGIMEN_LINE_CONCEPT, allObs); //PrescribedRegimenLineCode
            if (obs != null && obs.getValueCoded() != null) {
                valueCoded = obs.getValueCoded().getConceptId();
                //   ndrCodeMapping = regimenMap.get(valueCoded); //regimen line code
                //get 
                obs = Utils.extractObs(valueCoded, allObs); // PrescribedRegimen
                if (obs != null) {

                    //   return obs.getValueCoded().getName().getName();
                    ndrCodeMapping = regimenMap.get(obs.getValueCoded().getConceptId());

                }

                return ndrCodeMapping;
            }

        }
        return null;
    }
}
