/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.module.limsemrops.omodmodels.PatientID;
import org.openmrs.module.limsemrops.omodmodels.VLSampleInformation;
import org.openmrs.module.limsemrops.utility.ConstantUtils;

/**
 * @author MORRISON.I
 */
public class PatientDemographics {
	
	private PatientIdentifier pepfarid, pidHospital, pidRecent;
	
	private Patient patient;
	
	public PatientDemographics(Patient p) {
		this.patient = p;
		this.pepfarid = this.patient.getPatientIdentifier(ConstantUtils.PEPFAR_IDENTIFIER_INDEX);
		this.pidHospital = this.patient.getPatientIdentifier(ConstantUtils.HOSPITAL_IDENTIFIER_INDEX);
		this.pidRecent = this.patient.getPatientIdentifier(ConstantUtils.RECENCY_INDENTIFIER_INDEX);
		
	}
	
	public VLSampleInformation fillUpDemographics() {

        VLSampleInformation vLSampleInformation = new VLSampleInformation();

            Set<PatientID> patientIdList = new HashSet<>();

        PatientID patientID = new PatientID();

        if (this.pepfarid != null) {
            patientID.setIdNumber(this.pepfarid.getIdentifier());
            patientID.setIdTypeCode("CLIENTID");
            patientIdList.add(patientID);
        }

        if (this.pidHospital != null) {
            patientID.setIdNumber(this.pidHospital.getIdentifier());
            patientID.setIdTypeCode("HOSPITALNO");
            patientIdList.add(patientID);
        }

        if (this.pidRecent != null) {
            patientID.setIdNumber(this.pidRecent.getIdentifier());
            patientID.setIdTypeCode("RECENT");
            patientIdList.add(patientID);
        }

        List<PatientID> f_patientIds = new ArrayList<PatientID>(patientIdList);
        vLSampleInformation.setPatientID(f_patientIds);

        vLSampleInformation.setAge(this.patient.getAge());
        vLSampleInformation.setDateOfBirth(this.patient.getPerson().getBirthdate());
        vLSampleInformation.setFirstName(this.patient.getGivenName());
        vLSampleInformation.setSex(this.patient.getGender());
        vLSampleInformation.setSurName(this.patient.getFamilyName());

        return vLSampleInformation;

    }
}
