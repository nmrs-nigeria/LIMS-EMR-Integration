/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.omodmodels;

/**
 *
 * @author MORRISON.I
 */
public class SampleCollectionManifest {
    
    private VLSampleCollectionBatchManifest viralloadManifest;
    private EIDSampleCollectionBatchManifest eidManifest;

    public VLSampleCollectionBatchManifest getViralloadManifest() {
        return viralloadManifest;
    }

    public void setViralloadManifest(VLSampleCollectionBatchManifest viralloadManifest) {
        this.viralloadManifest = viralloadManifest;
    }

    public EIDSampleCollectionBatchManifest getEidManifest() {
        return eidManifest;
    }

    public void setEidManifest(EIDSampleCollectionBatchManifest eidManifest) {
        this.eidManifest = eidManifest;
    }
    
    
    
}
