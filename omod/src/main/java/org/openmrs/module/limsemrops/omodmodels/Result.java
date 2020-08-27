package org.openmrs.module.limsemrops.omodmodels;

import java.util.Date;

public class Result {
	
	private String id;
	
	private String sampleID;
	
	private String manifestID;
	
	private String patientID;
	
	private String pcrLabSampleNumber;
	
	private Date dateSampleReceievedAtPCRLab;
	
	private String testResult;
	
	private Date resultDate;
	
	private Date approvalDate;
	
	private Date assayDate;
	
	private Date dateResultDispatched;
	
	private String sampleStatus;
	
	private String sampleTestable;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSampleID() {
		return sampleID;
	}
	
	public void setSampleID(String sampleID) {
		this.sampleID = sampleID;
	}
	
	public String getManifestID() {
		return manifestID;
	}
	
	public void setManifestID(String manifestID) {
		this.manifestID = manifestID;
	}
	
	public String getPatientID() {
		return patientID;
	}
	
	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}
	
	public String getPcrLabSampleNumber() {
		return pcrLabSampleNumber;
	}
	
	public void setPcrLabSampleNumber(String pcrLabSampleNumber) {
		this.pcrLabSampleNumber = pcrLabSampleNumber;
	}
	
	public Date getDateSampleReceievedAtPCRLab() {
		return dateSampleReceievedAtPCRLab;
	}
	
	public void setDateSampleReceievedAtPCRLab(Date dateSampleReceievedAtPCRLab) {
		this.dateSampleReceievedAtPCRLab = dateSampleReceievedAtPCRLab;
	}
	
	public String getTestResult() {
		return testResult;
	}
	
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}
	
	public Date getResultDate() {
		return resultDate;
	}
	
	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}
	
	public Date getApprovalDate() {
		return approvalDate;
	}
	
	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}
	
	public Date getAssayDate() {
		return assayDate;
	}
	
	public void setAssayDate(Date assayDate) {
		this.assayDate = assayDate;
	}
	
	public Date getDateResultDispatched() {
		return dateResultDispatched;
	}
	
	public void setDateResultDispatched(Date dateResultDispatched) {
		this.dateResultDispatched = dateResultDispatched;
	}
	
	public String getSampleStatus() {
		return sampleStatus;
	}
	
	public void setSampleStatus(String sampleStatus) {
		this.sampleStatus = sampleStatus;
	}
	
	public String getSampleTestable() {
		return sampleTestable;
	}
	
	public void setSampleTestable(String sampleTestable) {
		this.sampleTestable = sampleTestable;
	}
	
}
