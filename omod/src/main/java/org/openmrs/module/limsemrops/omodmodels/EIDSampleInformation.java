/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.omodmodels;

import java.util.Date;
import java.util.List;

/**
 *
 * @author MORRISON.I
 */
public class EIDSampleInformation {

    private List<PatientID> patientID;

    private String firstname;

    private String surname;
    private String sex;
    private String pBStatus;
    private Integer age;
    private Date dob;
    private String facilityID;
    private String labID;
    private String sampleID;
    private String sampleType;
    private String indicationVLTest;
    private Date artCommencementDate;
    private String drugRegimen;
    private String sampleOrderedBy;
    private Date sampleOrderDate;
    private String sampleCollectedBy;
    private Date sampleCollectionDate;
    private Date sampleCollectionTime;
    private Date dateSampleSent;

    public List<PatientID> getPatientID() {
        return patientID;
    }

    public void setPatientID(List<PatientID> patientID) {
        this.patientID = patientID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getpBStatus() {
        return pBStatus;
    }

    public void setpBStatus(String pBStatus) {
        this.pBStatus = pBStatus;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(String facilityID) {
        this.facilityID = facilityID;
    }

    public String getLabID() {
        return labID;
    }

    public void setLabID(String labID) {
        this.labID = labID;
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

    public String getIndicationVLTest() {
        return indicationVLTest;
    }

    public void setIndicationVLTest(String indicationVLTest) {
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
