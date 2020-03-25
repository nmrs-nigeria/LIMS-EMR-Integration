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
public class EIDSampleInformation implements Serializable {

    private List<PatientID> patientID;

    private String firstName;

    private String surName;

    private String sex;

    private Integer ageInMonths;

    private Date dateOfBirth;

    // 1 .. 4
    private Integer reasonForPCR;

    private Boolean rapidTestDone;
    private Date dateRapidTestDone;

    //enum of P, N, I, NA
    private String resultOfRapidTest;
    private List<Integer> artAdministeredToMother;
    private List<Integer> babyReceived;
    private String babyEverBreastFed;
    private String babyBreastFeedingNow;
    private Integer ageBreastFeedingStoppedMonths;

    // enum of N, D, S
    private String cotrimoxazoleGiven;
    private String motherANC;
    private String guardianPhoneNo;
    private String motherEntryPoint;
    private String motherPepfarID;

    private String sampleID;

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

    public Integer getAgeInMonths() {
        return ageInMonths;
    }

    public void setAgeInMonths(Integer ageInMonths) {
        this.ageInMonths = ageInMonths;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getReasonForPCR() {
        return reasonForPCR;
    }

    public void setReasonForPCR(Integer reasonForPCR) {
        this.reasonForPCR = reasonForPCR;
    }

    public Boolean getRapidTestDone() {
        return rapidTestDone;
    }

    public void setRapidTestDone(Boolean rapidTestDone) {
        this.rapidTestDone = rapidTestDone;
    }

    public Date getDateRapidTestDone() {
        return dateRapidTestDone;
    }

    public void setDateRapidTestDone(Date dateRapidTestDone) {
        this.dateRapidTestDone = dateRapidTestDone;
    }

    public String getResultOfRapidTest() {
        return resultOfRapidTest;
    }

    public void setResultOfRapidTest(String resultOfRapidTest) {
        this.resultOfRapidTest = resultOfRapidTest;
    }

    public List<Integer> getArtAdministeredToMother() {
        return artAdministeredToMother;
    }

    public void setArtAdministeredToMother(List<Integer> artAdministeredToMother) {
        this.artAdministeredToMother = artAdministeredToMother;
    }

    public List<Integer> getBabyReceived() {
        return babyReceived;
    }

    public void setBabyReceived(List<Integer> babyReceived) {
        this.babyReceived = babyReceived;
    }

    public String getBabyEverBreastFed() {
        return babyEverBreastFed;
    }

    public void setBabyEverBreastFed(String babyEverBreastFed) {
        this.babyEverBreastFed = babyEverBreastFed;
    }

    public String getBabyBreastFeedingNow() {
        return babyBreastFeedingNow;
    }

    public void setBabyBreastFeedingNow(String babyBreastFeedingNow) {
        this.babyBreastFeedingNow = babyBreastFeedingNow;
    }

    public Integer getAgeBreastFeedingStoppedMonths() {
        return ageBreastFeedingStoppedMonths;
    }

    public void setAgeBreastFeedingStoppedMonths(Integer ageBreastFeedingStoppedMonths) {
        this.ageBreastFeedingStoppedMonths = ageBreastFeedingStoppedMonths;
    }

    public String getCotrimoxazoleGiven() {
        return cotrimoxazoleGiven;
    }

    public void setCotrimoxazoleGiven(String cotrimoxazoleGiven) {
        this.cotrimoxazoleGiven = cotrimoxazoleGiven;
    }

    public String getMotherANC() {
        return motherANC;
    }

    public void setMotherANC(String motherANC) {
        this.motherANC = motherANC;
    }

    public String getGuardianPhoneNo() {
        return guardianPhoneNo;
    }

    public void setGuardianPhoneNo(String guardianPhoneNo) {
        this.guardianPhoneNo = guardianPhoneNo;
    }

    public String getMotherEntryPoint() {
        return motherEntryPoint;
    }

    public void setMotherEntryPoint(String motherEntryPoint) {
        this.motherEntryPoint = motherEntryPoint;
    }

    public String getMotherPepfarID() {
        return motherPepfarID;
    }

    public void setMotherPepfarID(String motherPepfarID) {
        this.motherPepfarID = motherPepfarID;
    }

    public String getSampleID() {
        return sampleID;
    }

    public void setSampleID(String sampleID) {
        this.sampleID = sampleID;
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
