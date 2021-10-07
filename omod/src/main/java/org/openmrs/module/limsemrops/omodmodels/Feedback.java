/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.omodmodels;

/**
 * @author MORRISON.I
 */
public class Feedback {
	
	private String manifestID;
	
	private String facilityName;
	
	private String facilityId;
	
	private String receivingPCRLab;
	
	private String receivingPCRLabId;
	
	private Integer totalSamplesProcessed;
	
	private Integer totalSamplesNotProcessed;
	
	private ManifestError errors;
	
	public String getManifestID() {
		return manifestID;
	}
	
	public void setManifestID(String manifestID) {
		this.manifestID = manifestID;
	}
	
	public String getFacilityName() {
		return facilityName;
	}
	
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	
	public String getFacilityId() {
		return facilityId;
	}
	
	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}
	
	public String getReceivingPCRLab() {
		return receivingPCRLab;
	}
	
	public void setReceivingPCRLab(String receivingPCRLab) {
		this.receivingPCRLab = receivingPCRLab;
	}
	
	public String getReceivingPCRLabId() {
		return receivingPCRLabId;
	}
	
	public void setReceivingPCRLabId(String receivingPCRLabId) {
		this.receivingPCRLabId = receivingPCRLabId;
	}
	
	public Integer getTotalSamplesProcessed() {
		return totalSamplesProcessed;
	}
	
	public void setTotalSamplesProcessed(Integer totalSamplesProcessed) {
		this.totalSamplesProcessed = totalSamplesProcessed;
	}
	
	public Integer getTotalSamplesNotProcessed() {
		return totalSamplesNotProcessed;
	}
	
	public void setTotalSamplesNotProcessed(Integer totalSamplesNotProcessed) {
		this.totalSamplesNotProcessed = totalSamplesNotProcessed;
	}
	
	public ManifestError getErrors() {
		return errors;
	}
	
	public void setErrors(ManifestError errors) {
		this.errors = errors;
	}
	
}
