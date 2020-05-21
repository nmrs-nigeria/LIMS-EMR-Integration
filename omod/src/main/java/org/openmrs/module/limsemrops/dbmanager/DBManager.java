/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.dbmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openmrs.Patient;
import org.openmrs.module.limsemrops.omodmodels.DBConnection;
import org.openmrs.module.limsemrops.omodmodels.Manifest;
import org.openmrs.module.limsemrops.omodmodels.VLSampleInformationFrontFacing;
import org.openmrs.module.limsemrops.utility.ConstantUtils;
import org.openmrs.module.limsemrops.utility.Utils;

/**
 * @author MORRISON.I
 */
public class DBManager {
	
	Connection conn = null;
	
	PreparedStatement pStatement = null;
	
	private ResultSet resultSet = null;
	
	public DBManager() {
		
	}
	
	public void openConnection() throws SQLException {
		DBConnection openmrsConn = Utils.getNmrsConnectionDetails();
		
		conn = DriverManager.getConnection(openmrsConn.getUrl(), openmrsConn.getUsername(), openmrsConn.getPassword());
	}
	
	public List<Integer> getRecentLabEncounter(Date startDate, Date endDate) throws SQLException {

        String sqlStr = "select encounter_id from " + ConstantUtils.ENCOUNTER_TABLE + " where encounter_type = "
                + ConstantUtils.Laboratory_Encounter_Type_Id
                + " and encounter_datetime >= ? "
                + "and encounter_datetime <= ? and `voided` = 0 ";
        
        pStatement = conn.prepareStatement(sqlStr);
        
        pStatement.setDate(1, new java.sql.Date(startDate.getTime()));
        pStatement.setDate(2, new java.sql.Date(endDate.getTime()));
        resultSet = pStatement.executeQuery();

        List<Integer> idlist = new ArrayList<>();

        while (resultSet.next()) {
            idlist.add(resultSet.getInt("encounter_id"));
        }

        return idlist;

    }
	
	public List<Integer> getTestRecentLabEncounter(Date startDate, Date endDate) throws SQLException {

        //select * from encounter where encounter_type = 14 and encounter_datetime > '2019-10-01 00:00:00' and encounter_datetime < '2019-12-01 00:00:00' and voided = 0
        pStatement = conn.prepareStatement("select * from encounter where encounter_type = 11 "
                + " and encounter_datetime >= '2020-05-04 00:00:00' "
                + "and encounter_datetime <= '2020-05-21 00:00:00' and voided = 0");

//        pStatement = conn.prepareStatement("select encounter_id from "+ConstantUtils.ENCOUNTER_TABLE+ " where encounter_type = 11 "
//                + "and encounter_datetime >= ? "
//                + "and encounter_datetime <= ? and voided = 0");
//        pStatement.setDate(1, new java.sql.Date(startDate.getTime()));
//        pStatement.setDate(2, new java.sql.Date(endDate.getTime()));
        resultSet = pStatement.executeQuery();

        List<Integer> idlist = new ArrayList<>();

        while (resultSet.next()) {
            idlist.add(resultSet.getInt("encounter_id"));
        }

        return idlist;

    }
	
	public List<Integer> getEnrollmentAndPharmacyEncounter(Patient p) throws SQLException {

        pStatement = conn.prepareStatement("select * from encounter where encounter_type in (?,?) and patient_id = ?  and voided = 0");
        pStatement.setInt(1, ConstantUtils.HIV_Enrollment_Encounter_Type_Id);
        pStatement.setInt(2, ConstantUtils.Pharmacy_Encounter_Type_Id);
        pStatement.setInt(3, p.getPatientId());

        resultSet = pStatement.executeQuery();

        List<Integer> idlist = new ArrayList<>();

        while (resultSet.next()) {
            idlist.add(resultSet.getInt("encounter_id"));
        }

        return idlist;

    }
	
	public int insertManifest(Manifest manifest) throws SQLException {
		pStatement = conn.prepareStatement("insert into " + ConstantUtils.MANIFEST_TABLE
		        + "(manifest_id,sample_space,result_status,created_by) values(?,?,?,?)");
		pStatement.setString(1, manifest.getManifestID());
		pStatement.setString(2, manifest.getSampleSpace());
		pStatement.setString(3, manifest.getResultStatus());
		pStatement.setString(4, manifest.getCreatedBy());
		
		int response = pStatement.executeUpdate();
		
		return response;
		
	}
	
	public int insertManifestSamples(List<VLSampleInformationFrontFacing> vLSampleInformationFrontFacings, String manifestID, String createdBy) throws SQLException {

        ObjectMapper mapper = new ObjectMapper();
        AtomicInteger response = new AtomicInteger(0);

        vLSampleInformationFrontFacings.stream().forEach(a -> {

            try {
                pStatement = conn.prepareStatement("insert into " + ConstantUtils.MANIFEST_SAMPLES_TABLE + "(manifest_id, patient_id, firstname, surname, sex, pregnantBreastFeedingStatus, age, "
                        + "dateOfBirth, sample_id, sample_type, indication_vl_test, art_commencement_date, drugregimen, "
                        + "sample_ordered_by, sample_ordered_date, sample_collected_by,"
                        + " sample_collected_date, date_sample_sent, encounter_id, created_by) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                pStatement.setString(1, manifestID);
                pStatement.setString(2, mapper.writeValueAsString(a.getPatientID()));
                pStatement.setString(3, a.getFirstName());
                pStatement.setString(4, a.getSurName());
                pStatement.setString(5, a.getSex());
                pStatement.setString(6, a.getPregnantBreastFeedingStatus());
                pStatement.setInt(7, a.getAge());
                pStatement.setDate(8, new java.sql.Date(a.getDateOfBirth().getTime()));
                pStatement.setString(9, a.getSampleID());
                pStatement.setString(10, a.getSampleType());
                pStatement.setInt(11, a.getIndicationVLTest());
                pStatement.setDate(12, new java.sql.Date(a.getArtCommencementDate().getTime()));
                pStatement.setString(13, a.getDrugRegimen());
                pStatement.setString(14, a.getSampleOrderedBy());
                pStatement.setDate(15, new java.sql.Date(a.getSampleOrderDate().getTime()));
                pStatement.setString(16, a.getSampleCollectedBy());
                pStatement.setDate(17, new java.sql.Date(a.getSampleCollectionDate().getTime()));
                pStatement.setDate(18, new java.sql.Date(a.getDateSampleSent().getTime()));
                pStatement.setInt(19, a.getEncounterId());
                pStatement.setString(20, createdBy);

                pStatement.executeUpdate();

                response.incrementAndGet();
            } catch (Exception ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        return response.intValue();

    }
	
	public void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
			if (pStatement != null) {
				pStatement.close();
			}
		}
		catch (Exception ex) {
			
		}
	}
	
}
