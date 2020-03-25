/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.omodmodels;

import java.io.Serializable;
import java.util.List;

/**
 * @author MORRISON.I
 */
public class EIDSampleCollectionBatchManifest implements Serializable {

    private String manifestId;
    private String sendingFacilityID;
    private String sendingFacilityName;
    private String receivingLabID;
    private String receivingLabName;

    private List<EIDSampleInformation> sampleInformation;

    public String getManifestId() {
        return manifestId;
    }

    public void setManifestId(String manifestId) {
        this.manifestId = manifestId;
    }

    public List<EIDSampleInformation> getSampleInformation() {
        return sampleInformation;
    }

    public void setSampleInformation(List<EIDSampleInformation> sampleInformation) {
        this.sampleInformation = sampleInformation;
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

}
