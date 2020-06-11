/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.service;

import java.util.Date;
import java.util.List;
import org.openmrs.Encounter;
import org.openmrs.api.context.Context;
import org.openmrs.module.limsemrops.omodmodels.SampleCollectionManifest;
import org.openmrs.module.limsemrops.omodmodels.VLSampleCollectionBatchManifest;
import org.openmrs.module.limsemrops.omodmodels.VLSampleInformationFrontFacing;
import org.openmrs.module.limsemrops.utility.ConstantUtils;

/**
 * @author MORRISON.I
 */
public class SampleInfo {
	
	private DBUtility dBUtility;
	
	private ViralLoadInfo viralLoadInfo;
	
	public SampleInfo() {
		this.dBUtility = new DBUtility();
		
	}
	
	public List<VLSampleInformationFrontFacing> searchLabEncounters(Date startDate, Date endDate,
	        ConstantUtils.SampleSpace sampleSpace) {
		
		List<Integer> labEncounterIds = dBUtility.getLabEncountersByDate(startDate, endDate, sampleSpace);
		this.viralLoadInfo = new ViralLoadInfo(labEncounterIds, sampleSpace);
		
		//		VLSampleCollectionBatchManifest vLSampleCollectionBatchManifest = new VLSampleCollectionBatchManifest();
		//		vLSampleCollectionBatchManifest = this.viralLoadInfo.getRecentSampleCollectedManifest();
		List<VLSampleInformationFrontFacing> vLSampleInformationFrontFacings = this.viralLoadInfo.searchLabEncounter();
		
		return vLSampleInformationFrontFacings;
		
	}
	
}
