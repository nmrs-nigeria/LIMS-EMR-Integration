/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.utility;

import java.util.List;
import java.util.Properties;
import org.openmrs.Obs;
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
	
	
}
