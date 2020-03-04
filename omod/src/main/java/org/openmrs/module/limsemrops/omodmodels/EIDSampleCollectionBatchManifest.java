/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.omodmodels;

import java.util.List;

/**
 *
 * @author MORRISON.I
 */
public class EIDSampleCollectionBatchManifest {

    private String manifestId;
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

}
