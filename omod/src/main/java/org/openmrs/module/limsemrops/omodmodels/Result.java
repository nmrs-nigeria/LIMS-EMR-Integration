package org.openmrs.module.limsemrops.omodmodels;

import java.util.Date;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

public class Result {
	
	private String id;
	
	private String laboratoryRegistrationNumber;
	
	private String pcrLabSampleNumber;
	
	private Date dateSampleReceievedAtPCRLab;
	
	private String testResult;
	
	private Date resultDate;
	
	private Date approvalDate;
	
	private String sampleStatus;
	
	private String sampleTestable;
	
	////////////////////////////////////////
	//TODO
	//	public String getId() {
	//		return id;
	//	}
	//
	//	public void setId(String id) {
	//		this.id = id;
	//	}
	//
	//	public String getName() {
	//		return name;
	//	}
	//
	//	public void setName(String name) {
	//		this.name = name;
	//	}
	
}
