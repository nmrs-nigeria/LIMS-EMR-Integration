/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.omodmodels;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

/**
 * @author MORRISON.I
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VLResultResponse {
	
	private String manifestID;
	
	private String receivingFacilityID;
	
	private String receivingFacilityName;
	
	private String sendingPCRLabID;
	
	private String sendingPCRLabName;
	
	private Integer testType;
	
	private List<ViralLoadTestReport> viralLoadTestReport;
	
	public String getManifestID() {
		return manifestID;
	}
	
	public void setManifestID(String manifestID) {
		this.manifestID = manifestID;
	}
	
	public String getReceivingFacilityID() {
		return receivingFacilityID;
	}
	
	public void setReceivingFacilityID(String receivingFacilityID) {
		this.receivingFacilityID = receivingFacilityID;
	}
	
	public String getReceivingFacilityName() {
		return receivingFacilityName;
	}
	
	public void setReceivingFacilityName(String receivingFacilityName) {
		this.receivingFacilityName = receivingFacilityName;
	}
	
	public String getSendingPCRLabID() {
		return sendingPCRLabID;
	}
	
	public void setSendingPCRLabID(String sendingPCRLabID) {
		this.sendingPCRLabID = sendingPCRLabID;
	}
	
	public String getSendingPCRLabName() {
		return sendingPCRLabName;
	}
	
	public void setSendingPCRLabName(String sendingPCRLabName) {
		this.sendingPCRLabName = sendingPCRLabName;
	}
	
	public Integer getTestType() {
		return testType;
	}
	
	public void setTestType(Integer testType) {
		this.testType = testType;
	}
	
	public List<ViralLoadTestReport> getViralLoadTestReport() {
		return viralLoadTestReport;
	}
	
	public void setViralLoadTestReport(List<ViralLoadTestReport> viralLoadTestReport) {
		this.viralLoadTestReport = viralLoadTestReport;
	}
	
}
