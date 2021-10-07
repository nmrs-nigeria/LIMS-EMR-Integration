/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.utility;

/**
 * @author MORRISON.I
 */
public class ConstantUtils {
	
	//tables Names
	public final static String ENCOUNTER_TABLE = "encounter";
	
	public final static String MANIFEST_TABLE = "lims_manifest";
	
	public final static String MANIFEST_SAMPLES_TABLE = "lims_manifest_samples";
	
	public final static String MANIFEST_SAMPLES_RESULT = "lims_manifest_result";
	
	public final static String AUTHMODULE_TABLE = "lims_auth_module";
	
	//forms
	public final static int Laboratory_Encounter_Type_Id = 11;
	
	public final static int HIV_Enrollment_Encounter_Type_Id = 14;
	
	public final static int Pharmacy_Encounter_Type_Id = 13;
	
	public final static int Care_card_Encounter_Type_Id = 12;
	
	public final static int Client_Intake_Form_Encounter_Type_Id = 20;
	
	// concepts
	public final static int ART_START_DATE_CONCEPT = 159599;
	
	public final static int PCR_LABS_CONCEPT = 166233;
	
	/* Identifier IDs */
	public static final int PEPFAR_IDENTIFIER_INDEX = 4;
	
	public static final int HOSPITAL_IDENTIFIER_INDEX = 5;
	
	public static final int OTHER_IDENTIFIER_INDEX = 3;
	
	public static final int HTS_IDENTIFIER_INDEX = 8;
	
	public static final int PMTCT_IDENTIFIER_INDEX = 6;
	
	public static final int EXPOSE_INFANT_IDENTIFIER_INDEX = 7;
	
	public static final int PEP_IDENTIFIER_INDEX = 9;
	
	public static final int RECENCY_INDENTIFIER_INDEX = 10;
	
	//URL
	//demo
	public static final String BASE_URL = "https://www.lims.ng/apidemo";
	
	//live
	//	public static final String BASE_URL = "https://www.lims.ng/api";
	
	public static final String POST_SAMPLES = "/samples/create.php";
	
	public static final String REQUEST_SAMPLE_RESULT = "/samples/result.php";
	
	public static final String TOKEN_REQUEST_URL = "/login.php";
	
	public static final String REMOTE_SERVER_URL = "https://www.lims.shieldnigeriaproject.com/api/Emr_Lims";
	
	//public enums
	public enum SampleSpace {
		VL, RECENCY, EID, COVID
	}
	
	public enum ResultStatus {
		PENDING, AVAILABLE
	}
	
}
