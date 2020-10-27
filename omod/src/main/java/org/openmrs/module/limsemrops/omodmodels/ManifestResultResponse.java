/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.omodmodels;

/**
 * @author MORRISON.I
 */
public class ManifestResultResponse {
	
	private String manifestId;
	
	private String isResultAvailable;
	
	public ManifestResultResponse(String manifestId, String isResultAvailable) {
		this.manifestId = manifestId;
		this.isResultAvailable = isResultAvailable;
	}
	
	public String getManifestId() {
		return manifestId;
	}
	
	public void setManifestId(String manifestId) {
		this.manifestId = manifestId;
	}
	
	public String getIsResultAvailable() {
		return isResultAvailable;
	}
	
	public void setIsResultAvailable(String isResultAvailable) {
		this.isResultAvailable = isResultAvailable;
	}
	
}
