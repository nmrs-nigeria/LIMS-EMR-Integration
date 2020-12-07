/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.omodmodels;

/**
 * @author MORRISON.I
 */
public class TempAuth {
	
	private String email;
	
	private String password;
	
	public TempAuth() {
		this.email = "nmrs@lims.ng";
		this.password = "nmrs@2020!";
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
