/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.omodmodels;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author MORRISON.I
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VLSampleInformation implements Serializable {
	
	public List<PatientID> patientID;
	
	public String firstName;
	
	public String surName;
	
	public String sex;
	
	//enum 1 .. 4
	public String pregnantBreastFeedingStatus;
	
	public Integer age;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "WAT")
	public Date dateOfBirth;
	
	public String sampleID;
	
	public String sampleType;
	
	//1 .. 7
	public Integer indicationVLTest;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "WAT")
	public Date artCommencementDate;
	
	public String drugRegimen;
	
	public String sampleOrderedBy;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "WAT")
	public Date sampleOrderDate;
	
	public String sampleCollectedBy;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "WAT")
	public Date sampleCollectionDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "WAT")
	public Date sampleCollectionTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "WAT")
	public Date dateSampleSent;
	
	@JsonProperty("patientID")
	public List<PatientID> getPatientID() {
		return patientID;
	}
	
	public void setPatientID(List<PatientID> patientId) {
		this.patientID = patientId;
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
	
	public String getPregnantBreastFeedingStatus() {
		return pregnantBreastFeedingStatus;
	}
	
	public void setPregnantBreastFeedingStatus(String pregnantBreastFeedingStatus) {
		this.pregnantBreastFeedingStatus = pregnantBreastFeedingStatus;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
	
	public String getSampleType() {
		return sampleType;
	}
	
	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}
	
	public Integer getIndicationVLTest() {
		return indicationVLTest;
	}
	
	public void setIndicationVLTest(Integer indicationVLTest) {
		this.indicationVLTest = indicationVLTest;
	}
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getArtCommencementDate() {
		return artCommencementDate;
	}
	
	public void setArtCommencementDate(Date artCommencementDate) {
		this.artCommencementDate = artCommencementDate;
	}
	
	public String getDrugRegimen() {
		return drugRegimen;
	}
	
	public void setDrugRegimen(String drugRegimen) {
		this.drugRegimen = drugRegimen;
	}
	
	public String getSampleOrderedBy() {
		return sampleOrderedBy;
	}
	
	public void setSampleOrderedBy(String sampleOrderedBy) {
		this.sampleOrderedBy = sampleOrderedBy;
	}
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getSampleOrderDate() {
		return sampleOrderDate;
	}
	
	public void setSampleOrderDate(Date sampleOrderDate) {
		this.sampleOrderDate = sampleOrderDate;
	}
	
	public String getSampleCollectedBy() {
		return sampleCollectedBy;
	}
	
	public void setSampleCollectedBy(String sampleCollectedBy) {
		this.sampleCollectedBy = sampleCollectedBy;
	}
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getSampleCollectionDate() {
		return sampleCollectionDate;
	}
	
	public void setSampleCollectionDate(Date sampleCollectionDate) {
		this.sampleCollectionDate = sampleCollectionDate;
	}
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getSampleCollectionTime() {
		return sampleCollectionTime;
	}
	
	public void setSampleCollectionTime(Date sampleCollectionTime) {
		this.sampleCollectionTime = sampleCollectionTime;
	}
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getDateSampleSent() {
		return dateSampleSent;
	}
	
	public void setDateSampleSent(Date dateSampleSent) {
		this.dateSampleSent = dateSampleSent;
	}
	
}
