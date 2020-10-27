/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.omodmodels;

import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author MORRISON.I
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ViralLoadTestReport {
	
	private List<PatientID> patientID;
	
	private String firstName;
	
	private String surName;
	
	private String sex;
	
	private Date dateOfBirth;
	
	private String sampleID;
	
	private String pcrLabSampleNumber;
	
	private Date visitDate;
	
	private Date dateSampleReceivedAtPCRLab;
	
	private String testResult;
	
	private Date resultDate;
	
	private Date assayDate;
	
	private Date approvalDate;
	
	private Date dateResultDispatched;
	
	private Integer sampleStatus;
	
	private Boolean sampleTestable;
	
	public List<PatientID> getPatientID() {
		return patientID;
	}
	
	public void setPatientID(List<PatientID> patientID) {
		this.patientID = patientID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getSurName() {
		return surName;
	}
	
	public void setSurName(String surName) {
		this.surName = surName;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getSampleID() {
		return sampleID;
	}
	
	public void setSampleID(String sampleID) {
		this.sampleID = sampleID;
	}
	
	public String getPcrLabSampleNumber() {
		return pcrLabSampleNumber;
	}
	
	public void setPcrLabSampleNumber(String pcrLabSampleNumber) {
		this.pcrLabSampleNumber = pcrLabSampleNumber;
	}
	
	public Date getVisitDate() {
		return visitDate;
	}
	
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
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
	
	public Date getAssayDate() {
		return assayDate;
	}
	
	public void setAssayDate(Date assayDate) {
		this.assayDate = assayDate;
	}
	
	public Date getApprovalDate() {
		return approvalDate;
	}
	
	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}
	
	public Date getDateResultDispatched() {
		return dateResultDispatched;
	}
	
	public void setDateResultDispatched(Date dateResultDispatched) {
		this.dateResultDispatched = dateResultDispatched;
	}
	
	public Integer getSampleStatus() {
		return sampleStatus;
	}
	
	public void setSampleStatus(Integer sampleStatus) {
		this.sampleStatus = sampleStatus;
	}
	
	public Boolean getSampleTestable() {
		return sampleTestable;
	}
	
	public void setSampleTestable(Boolean sampleTestable) {
		this.sampleTestable = sampleTestable;
	}
	
}
