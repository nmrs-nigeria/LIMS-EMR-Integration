/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.omodmodels;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author MORRISON.I
 */
public class VLSampleCollectionBatchManifest implements Serializable {
	
	private String token;
	
	private String manifestID;
	
	private String sendingFacilityID;
	
	private String sendingFacilityName;
	
	private String receivingLabID;
	
	private String receivingLabName;
	
	private Date dateScheduledForPickup;
	
	private Integer temperatureAtPickup;
	
	private String samplePackagedBy;
	
	private String courierRiderName;
	
	private String courierContact;
	
	private List<VLSampleInformation> sampleInformation;
	
	public String getManifestID() {
		return manifestID;
	}
	
	public void setManifestID(String manifestID) {
		this.manifestID = manifestID;
	}
	
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
	
	public String getReceivingLabID() {
		return receivingLabID;
	}
	
	public void setReceivingLabID(String receivingLabID) {
		this.receivingLabID = receivingLabID;
	}
	
	public String getReceivingLabName() {
		return receivingLabName;
	}
	
	public void setReceivingLabName(String receivingLabName) {
		this.receivingLabName = receivingLabName;
	}
	
	public List<VLSampleInformation> getSampleInformation() {
		return sampleInformation;
	}
	
	public void setSampleInformation(List<VLSampleInformation> sampleInformation) {
		this.sampleInformation = sampleInformation;
	}
	
	public Date getDateScheduledForPickup() {
		return dateScheduledForPickup;
	}
	
	public void setDateScheduledForPickup(Date dateScheduledForPickup) {
		this.dateScheduledForPickup = dateScheduledForPickup;
	}
	
	public Integer getTemperatureAtPickup() {
		return temperatureAtPickup;
	}
	
	public void setTemperatureAtPickup(Integer temperatureAtPickup) {
		this.temperatureAtPickup = temperatureAtPickup;
	}
	
	public String getSamplePackagedBy() {
		return samplePackagedBy;
	}
	
	public void setSamplePackagedBy(String samplePackagedBy) {
		this.samplePackagedBy = samplePackagedBy;
	}
	
	public String getCourierRiderName() {
		return courierRiderName;
	}
	
	public void setCourierRiderName(String courierRiderName) {
		this.courierRiderName = courierRiderName;
	}
	
	public String getCourierContact() {
		return courierContact;
	}
	
	public void setCourierContact(String courierContact) {
		this.courierContact = courierContact;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
}
