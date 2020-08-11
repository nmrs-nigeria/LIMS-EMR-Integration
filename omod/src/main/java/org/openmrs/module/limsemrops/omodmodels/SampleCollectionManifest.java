/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.omodmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * @author MORRISON.I
 */
public class SampleCollectionManifest implements Serializable {
	
	private VLSampleCollectionBatchManifest viralLoadManifest;
	
	private EIDSampleCollectionBatchManifest eidManifest;
	
	@JsonProperty("viralLoadManifest")
	public VLSampleCollectionBatchManifest getViralloadManifest() {
		return viralLoadManifest;
	}
	
	public void setViralloadManifest(VLSampleCollectionBatchManifest viralloadManifest) {
		this.viralLoadManifest = viralloadManifest;
	}
	
	public EIDSampleCollectionBatchManifest getEidManifest() {
		return eidManifest;
	}
	
	public void setEidManifest(EIDSampleCollectionBatchManifest eidManifest) {
		this.eidManifest = eidManifest;
	}
	
}
