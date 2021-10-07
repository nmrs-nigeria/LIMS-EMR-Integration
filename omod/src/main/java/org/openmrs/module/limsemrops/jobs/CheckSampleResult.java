/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.jobs;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openmrs.module.limsemrops.dbmanager.DBManager;
import org.openmrs.module.limsemrops.omodmodels.Manifest;
import org.openmrs.module.limsemrops.service.SampleResultManager;
import org.openmrs.scheduler.tasks.AbstractTask;

/**
 * @author MORRISON.I
 */
public class CheckSampleResult extends AbstractTask {
	
	SampleResultManager sampleResultManager = new SampleResultManager();
	
	DBManager dBManager = new DBManager();
	
	@Override
	public void execute() {
		try {
			dBManager.openConnection();
			List<Manifest> pendingManifests = dBManager.getAllPendingManifest();
			System.out.println("STARTING SAMPLE RESULT RETRIEVAL");
			sampleResultManager.pullManifestResultFromLIMS(pendingManifests, true);
		}
		catch (SQLException ex) {
			Logger.getLogger(CheckSampleResult.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
