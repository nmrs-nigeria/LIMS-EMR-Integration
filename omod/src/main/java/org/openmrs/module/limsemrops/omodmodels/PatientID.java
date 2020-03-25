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
public class PatientID implements Serializable {

    private String idNumber;

    private String idTypeCode;

    @JsonProperty("idNumber")
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    
     @JsonProperty("idTypeCode")
    public String getIdTypeCode() {
        return idTypeCode;
    }

    public void setIdTypeCode(String idTypeCode) {
        this.idTypeCode = idTypeCode;
    }

}
