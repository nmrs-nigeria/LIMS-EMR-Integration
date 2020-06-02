/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.omodmodels;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author MORRISON.I
 */
@JsonIgnoreProperties(value = { "encounterId" })
public class VLSampleInformationFrontFacing extends VLSampleInformation {
	
	private Integer encounterId;
	
	private String sampleSource;
	
	private String sampleStatus;
	
	private String rejectionReason;
	
	public String getSampleStatus() {
		return sampleStatus;
	}
	
	public void setSampleStatus(String sampleStatus) {
		this.sampleStatus = sampleStatus;
	}
	
	public String getRejectionReason() {
		return rejectionReason;
	}
	
	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
	
	public Integer getEncounterId() {
		return encounterId;
	}
	
	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
	
	public String getSampleSource() {
		return sampleSource;
	}
	
	public void setSampleSource(String sampleSource) {
		this.sampleSource = sampleSource;
	}
	
}
