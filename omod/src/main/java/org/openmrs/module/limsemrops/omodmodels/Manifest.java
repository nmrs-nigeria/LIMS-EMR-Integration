/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.omodmodels;

/**
 * @author MORRISON.I
 */
public class Manifest {
	
	private String manifestID;
	
	private String sampleSpace;
	
	private String resultStatus;
	
	private String createdBy;
	
	public String getManifestID() {
		return manifestID;
	}
	
	public void setManifestID(String manifestID) {
		this.manifestID = manifestID;
	}
	
	public String getSampleSpace() {
		return sampleSpace;
	}
	
	public void setSampleSpace(String sampleSpace) {
		this.sampleSpace = sampleSpace;
	}
	
	public String getResultStatus() {
		return resultStatus;
	}
	
	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
}
