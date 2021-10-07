/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.omodmodels;

/**
 * @author MORRISON.I
 */
public class ManifestResponse {
	
	private int status;
	
	private String body;
	
	private boolean requestStatus;
	
	public ManifestResponse(int status, String body, boolean requestStatus) {
		this.status = status;
		this.body = body;
		this.requestStatus = requestStatus;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public boolean isRequestStatus() {
		return requestStatus;
	}
	
	public void setRequestStatus(boolean requestStatus) {
		this.requestStatus = requestStatus;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
}
