/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openmrs.module.limsemrops.dbmanager.NdrDBManager;

/**
 * @author MORRISON.I
 */
public class DBUtility {
	
	public List<Integer> getLabEncountersByDate(Date startDate, Date endDate) {
        List<Integer> encounters = new ArrayList<>();

        NdrDBManager ndrDBManager = new NdrDBManager();
        try {
            ndrDBManager.openConnection();
            encounters = ndrDBManager.getRecentLabEncounter(startDate, endDate);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            ndrDBManager.closeConnection();
        }

        return encounters;
        
    }
}
