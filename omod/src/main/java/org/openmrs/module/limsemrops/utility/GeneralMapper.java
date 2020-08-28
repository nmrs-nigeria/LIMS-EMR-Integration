/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mashape.unirest.http.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openmrs.module.limsemrops.omodmodels.Result;
import org.openmrs.module.limsemrops.omodmodels.ViralLoadTestReport;

/**
 * @author MORRISON.I
 */
public class GeneralMapper {
	
	public static ObjectMapper getCustomObjectMapper() {
		return new ObjectMapper() {
			
			org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();
			
			public String writeValue(Object value) {
				try {
					return mapper.writeValueAsString(value);
				}
				catch (Exception ex) {
					Logger.getLogger(GeneralMapper.class.getName()).log(Level.SEVERE, null, ex);
				}
				return null;
			}
			
			public <T> T readValue(String value, Class<T> valueType) {
				try {
					return mapper.readValue(value, valueType);
				}
				catch (IOException ex) {
					Logger.getLogger(GeneralMapper.class.getName()).log(Level.SEVERE, null, ex);
				}
				return null;
			}
		};
	}
	
	public static Result mapToSampleResultModel(ViralLoadTestReport viralLoadTestReport) throws JsonProcessingException,
	        IOException {
		com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper().configure(
		    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		return mapper.readValue(mapper.writeValueAsString(viralLoadTestReport), Result.class);
		
	}
	
}
