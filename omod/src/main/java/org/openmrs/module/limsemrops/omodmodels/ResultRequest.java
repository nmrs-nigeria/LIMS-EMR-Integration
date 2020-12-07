/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.omodmodels;

/**
 * @author MORRISON.I
 */
public class ResultRequest {
	
	private String sendingFacilityID;
	
	private String sendingFacilityName;
	
	private String receivingPCRLabID;
	
	private String receivingPCRLabName;
	
	private String testType;
	
	private String manifestID;
	
	private String token;
	
	public String getSendingFacilityID() {
		return sendingFacilityID;
	}
	
	public void setSendingFacilityID(String sendingFacilityID) {
		this.sendingFacilityID = sendingFacilityID;
	}
	
	public String getSendingFacilityName() {
		return sendingFacilityName;
	}
	
	public void setSendingFacilityName(String sendingFacilityName) {
		this.sendingFacilityName = sendingFacilityName;
	}
	
	public String getReceivingPCRLabID() {
		return receivingPCRLabID;
	}
	
	public void setReceivingPCRLabID(String receivingPCRLabID) {
		this.receivingPCRLabID = receivingPCRLabID;
	}
	
	public String getReceivingPCRLabName() {
		return receivingPCRLabName;
	}
	
	public void setReceivingPCRLabName(String receivingPCRLabName) {
		this.receivingPCRLabName = receivingPCRLabName;
	}
	
	public String getTestType() {
		return testType;
	}
	
	public void setTestType(String testType) {
		this.testType = testType;
	}
	
	public String getManifestID() {
		return manifestID;
	}
	
	public void setManifestID(String manifestID) {
		this.manifestID = manifestID;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
}
