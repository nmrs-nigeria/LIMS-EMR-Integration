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
public class PatientID {
    
    private String IDNumber;
    private String IDTypeCode;

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getIDTypeCode() {
        return IDTypeCode;
    }

    public void setIDTypeCode(String IDTypeCode) {
        this.IDTypeCode = IDTypeCode;
    }
    
    
    
    
}
