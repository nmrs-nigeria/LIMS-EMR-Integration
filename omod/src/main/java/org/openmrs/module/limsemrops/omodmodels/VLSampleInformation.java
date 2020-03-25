/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.omodmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author MORRISON.I
 */
public class VLSampleInformation implements Serializable {

    private List<PatientID> patientID;

    private String firstName;

    private String surName;

    private String sex;

    //enum 1 .. 4
    private String pregnantBreastFeedingStatus;

    private Integer age;

    private Date dateOfBirth;

    private String sampleID;

    private String sampleType;

    //1 .. 7
    private Integer indicationVLTest;

    private Date artCommencementDate;

    private String drugRegimen;

    private String sampleOrderedBy;

    private Date sampleOrderDate;

    private String sampleCollectedBy;

    private Date sampleCollectionDate;

    private Date sampleCollectionTime;

    private Date dateSampleSent;

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

    public Date getSampleCollectionDate() {
        return sampleCollectionDate;
    }

    public void setSampleCollectionDate(Date sampleCollectionDate) {
        this.sampleCollectionDate = sampleCollectionDate;
    }

    public Date getSampleCollectionTime() {
        return sampleCollectionTime;
    }

    public void setSampleCollectionTime(Date sampleCollectionTime) {
        this.sampleCollectionTime = sampleCollectionTime;
    }

    public Date getDateSampleSent() {
        return dateSampleSent;
    }

    public void setDateSampleSent(Date dateSampleSent) {
        this.dateSampleSent = dateSampleSent;
    }

}
