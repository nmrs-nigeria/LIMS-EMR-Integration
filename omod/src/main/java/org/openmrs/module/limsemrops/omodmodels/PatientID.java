/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.omodmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * @author MORRISON.I
 */
public class PatientID implements Serializable {
	
	private String IDNumber;
	
	private String IDTypeCode;
	
	@JsonProperty("IDNumber")
	public String getIDNumber() {
		return IDNumber;
	}
	
	public void setIDNumber(String IDNumber) {
		this.IDNumber = IDNumber;
	}
	
	@JsonProperty("IDTypeCode")
	public String getIDTypeCode() {
		return IDTypeCode;
	}
	
	public void setIDTypeCode(String IDTypeCode) {
		this.IDTypeCode = IDTypeCode;
	}
	
}
