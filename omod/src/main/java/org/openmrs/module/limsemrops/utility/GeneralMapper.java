/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.utility;

import com.mashape.unirest.http.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	
}
