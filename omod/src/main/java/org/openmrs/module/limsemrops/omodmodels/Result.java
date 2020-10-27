package org.openmrs.module.limsemrops.omodmodels;

import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
	
	private String id;
	
	private String sampleID;
	
	private String manifestID;
	
	private List<PatientID> patientID;
	
	private String pcrLabSampleNumber;
	
	private Date dateSampleReceivedAtPCRLab;
	
	private String testResult;
	
	private Date resultDate;
	
	private Date approvalDate;
	
	private Date assayDate;
	
	private Date dateResultDispatched;
	
	private String sampleStatus;
	
	private String sampleTestable;
	
	private String createdBy;
	
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
	
	public List<PatientID> getPatientID() {
		return patientID;
	}
	
	public void setPatientID(List<PatientID> patientID) {
		this.patientID = patientID;
	}
	
	public String getPcrLabSampleNumber() {
		return pcrLabSampleNumber;
	}
	
	public void setPcrLabSampleNumber(String pcrLabSampleNumber) {
		this.pcrLabSampleNumber = pcrLabSampleNumber;
	}
	
	public Date getDateSampleReceivedAtPCRLab() {
		return dateSampleReceivedAtPCRLab;
	}
	
	public void setDateSampleReceivedAtPCRLab(Date dateSampleReceivedAtPCRLab) {
		this.dateSampleReceivedAtPCRLab = dateSampleReceivedAtPCRLab;
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
	
	public String getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
}
