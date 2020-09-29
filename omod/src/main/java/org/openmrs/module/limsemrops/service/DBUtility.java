/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.jackson.map.ObjectMapper;
import org.openmrs.Patient;
import org.openmrs.module.limsemrops.dbmanager.DBManager;
import org.openmrs.module.limsemrops.omodmodels.Manifest;
import org.openmrs.module.limsemrops.omodmodels.VLSampleInformationFrontFacing;
import org.openmrs.module.limsemrops.utility.ConstantUtils;

/**
 * @author MORRISON.I
 */
public class DBUtility {
	
	private final DBManager ndrDBManager;
	
	public DBUtility() {
		this.ndrDBManager = new DBManager();
		
	}
	
	public List<Integer> getLabEncountersByDate(Date startDate, Date endDate, ConstantUtils.SampleSpace sampleSpace) {
        List<Integer> encounters = new ArrayList<>();

        try {
            this.ndrDBManager.openConnection();
            if (sampleSpace.equals(ConstantUtils.SampleSpace.VL)) {
                encounters = this.ndrDBManager.getRecentLabEncounter(startDate, endDate);
            } else if (sampleSpace.equals(ConstantUtils.SampleSpace.RECENCY)) {
                encounters = this.ndrDBManager.getRecentRecencyClientEncounter(startDate, endDate);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            this.ndrDBManager.closeConnection();
        }

        return encounters;

    }
	
	public List<Integer> getTestLabEncountersByDate(Date startDate, Date endDate) {
        List<Integer> encounters = new ArrayList<>();

        try {
            this.ndrDBManager.openConnection();
            encounters = this.ndrDBManager.getTestRecentLabEncounter(startDate, endDate);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            this.ndrDBManager.closeConnection();
        }

        return encounters;

    }
	
	public List<Integer> getEnrollmentAndPharmacy(Patient patient) {
        List<Integer> encounters = new ArrayList<>();

        try {
            this.ndrDBManager.openConnection();
            encounters = this.ndrDBManager.getEnrollmentAndPharmacyEncounter(patient);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            this.ndrDBManager.closeConnection();
        }

        return encounters;

    }
	
	public boolean insertManifestEntry(Manifest manifest) {
		
		int response = 0;
		try {
			this.ndrDBManager.openConnection();
			response = this.ndrDBManager.insertManifest(manifest);
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		finally {
			this.ndrDBManager.closeConnection();
		}
		
		return response > 0;
		
	}
	
	public boolean insertManifestSaplesEntry(List<VLSampleInformationFrontFacing> vLSampleInformationFrontFacings,
	        String manifestId, String createdBy, Date dateSampleSent) {
		
		int response = 0;
		try {
			this.ndrDBManager.openConnection();
			response = this.ndrDBManager.insertManifestSamples(vLSampleInformationFrontFacings, manifestId, createdBy,
			    dateSampleSent);
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		finally {
			this.ndrDBManager.closeConnection();
		}
		
		return response == vLSampleInformationFrontFacings.size();
		
	}
	
	public List<Manifest> getManifests() {
        List<Manifest> manifests = new ArrayList<>();

        try {
            this.ndrDBManager.openConnection();
            manifests = ndrDBManager.getAllManifest();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            this.ndrDBManager.closeConnection();
        }

        return manifests;
    }
	
	public Manifest getManifestsbyId(String manifestId) {
		Manifest manifest = null;
		
		try {
			this.ndrDBManager.openConnection();
			manifest = ndrDBManager.getAllManifestByID(manifestId);
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		finally {
			this.ndrDBManager.closeConnection();
		}
		
		return manifest;
	}
	
	public List<VLSampleInformationFrontFacing> getSamplesByManifestId(String manifestId) {

        List<VLSampleInformationFrontFacing> vLSampleInformationFrontFacings = new ArrayList<>();

        try {
            this.ndrDBManager.openConnection();
            vLSampleInformationFrontFacings = ndrDBManager.getManifestSamples(manifestId);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            this.ndrDBManager.closeConnection();
        }

        return vLSampleInformationFrontFacings;

    }
}
