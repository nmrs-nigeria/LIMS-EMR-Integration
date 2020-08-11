/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.omodmodels;

import java.util.Date;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author MORRISON.I
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Manifest {
	
	private String manifestID;
	
	private String sampleSpace;
	
	private String testType;
	
	private String referringLabState;
	
	private String referringLabLga;
	
	private Date dateScheduleForPickup;
	
	private Integer riderTotalSamplesPicked;
	
	private String riderTempAtPickUp;
	
	private String riderName;
	
	private String riderPhoneNumber;
	
	private String samplePickUpOnTime; //true or false string ..  did not use boolean .. Create on UI
	
	private String pcrLabName;
	
	private String pcrLabCode;
	
	private String resultStatus;
	
	private String createdBy;
	
	private Date dateModified;
	
	private String modifiedBy;
	
	private Date dateCreated;
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
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
	
	public String getTestType() {
		return testType;
	}
	
	public void setTestType(String testType) {
		this.testType = testType;
	}
	
	public String getReferringLabState() {
		return referringLabState;
	}
	
	public void setReferringLabState(String referringLabState) {
		this.referringLabState = referringLabState;
	}
	
	public String getReferringLabLga() {
		return referringLabLga;
	}
	
	public void setReferringLabLga(String referringLabLga) {
		this.referringLabLga = referringLabLga;
	}
	
	public Date getDateScheduleForPickup() {
		return dateScheduleForPickup;
	}
	
	public void setDateScheduleForPickup(Date dateScheduleForPickup) {
		this.dateScheduleForPickup = dateScheduleForPickup;
	}
	
	public Integer getRiderTotalSamplesPicked() {
		return riderTotalSamplesPicked;
	}
	
	public void setRiderTotalSamplesPicked(Integer riderTotalSamplesPicked) {
		this.riderTotalSamplesPicked = riderTotalSamplesPicked;
	}
	
	public String getRiderTempAtPickUp() {
		return riderTempAtPickUp;
	}
	
	public void setRiderTempAtPickUp(String riderTempAtPickUp) {
		this.riderTempAtPickUp = riderTempAtPickUp;
	}
	
	public String getRiderName() {
		return riderName;
	}
	
	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}
	
	public String getRiderPhoneNumber() {
		return riderPhoneNumber;
	}
	
	public void setRiderPhoneNumber(String riderPhoneNumber) {
		this.riderPhoneNumber = riderPhoneNumber;
	}
	
	public String getSamplePickUpOnTime() {
		return samplePickUpOnTime;
	}
	
	public void setSamplePickUpOnTime(String samplePickUpOnTime) {
		this.samplePickUpOnTime = samplePickUpOnTime;
	}
	
	public String getPcrLabName() {
		return pcrLabName;
	}
	
	public void setPcrLabName(String pcrLabName) {
		this.pcrLabName = pcrLabName;
	}
	
	public String getPcrLabCode() {
		return pcrLabCode;
	}
	
	public void setPcrLabCode(String pcrLabCode) {
		this.pcrLabCode = pcrLabCode;
	}
	
	public Date getDateModified() {
		return dateModified;
	}
	
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
	
	public String getModifiedBy() {
		return modifiedBy;
	}
	
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
}
