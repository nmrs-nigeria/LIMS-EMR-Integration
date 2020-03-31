/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.openmrs.Patient;
import org.openmrs.module.limsemrops.omodmodels.DBConnection;
import org.openmrs.module.limsemrops.utility.ConstantUtils;
import org.openmrs.module.limsemrops.utility.Utils;

/**
 * @author MORRISON.I
 */
public class NdrDBManager {
	
	Connection conn = null;
	
	PreparedStatement pStatement = null;
	
	private ResultSet resultSet = null;
	
	public NdrDBManager() {
		
	}
	
	public void openConnection() throws SQLException {
		DBConnection openmrsConn = Utils.getNmrsConnectionDetails();
		
		conn = DriverManager.getConnection(openmrsConn.getUrl(), openmrsConn.getUsername(), openmrsConn.getPassword());
	}
	
	public List<Integer> getRecentLabEncounter(Date startDate, Date endDate) throws SQLException {

        //select * from encounter where encounter_type = 14 and encounter_datetime > '2019-10-01 00:00:00' and encounter_datetime < '2019-12-01 00:00:00' and voided = 0
        pStatement = conn.prepareStatement("select * from encounter where encounter_type = 11 "
                + "and encounter_datetime > '2020-03-01 00:00:00' "
                + "and encounter_datetime < '2020-04-01 00:00:00' and voided = 0");

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

        
        pStatement = conn.prepareStatement("select * from encounter where encounter_type in (?,?} and patient_id = ?  and voided = 0");
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
