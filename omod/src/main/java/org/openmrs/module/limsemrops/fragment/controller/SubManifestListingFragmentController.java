/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.fragment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.limsemrops.omodmodels.Manifest;
import org.openmrs.module.limsemrops.service.DBUtility;
import org.openmrs.module.limsemrops.service.ExchangeLayer;
import org.openmrs.module.limsemrops.service.LookUpManager;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author MORRISON.I
 */
public class SubManifestListingFragmentController {
	
	private static final Log LOG = LogFactory.getLog(SubManifestListingFragmentController.class);
	
	private final ExchangeLayer exchangeLayer;
	
	private final DBUtility dBUtility;
	
	private final LookUpManager lookUpManager;
	
	private ObjectMapper mapper;
	
	public SubManifestListingFragmentController() {
		this.exchangeLayer = new ExchangeLayer();
		this.dBUtility = new DBUtility();
		this.lookUpManager = new LookUpManager();
	}
	
	public void controller() {
		System.out.println("Inside SubManifestList fragment controller method");
	}
	
	public SimpleObject getAllSavedManifest() {
		System.out.println("Inside getAllSavedManifest method");
		mapper = new ObjectMapper();
		SimpleObject simpleObject = new SimpleObject();
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		List<Manifest> manifests = dBUtility.getManifests();
		System.out.println("Manifest List from page getAllSavedManifest: " + manifests);
		String response = null;
		try {
			response = mapper.writeValueAsString(manifests);
			System.out.println("Response from page: " + response);
			simpleObject.put("resp", response);
		}
		catch (JsonProcessingException ex) {
			Logger.getLogger(SubManifestListingFragmentController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return simpleObject;
	}
	//@RequestMapping(method = RequestMethod.GET)
	//	public ResponseEntity<?> getAllSavedManifest() throws SQLException {
	//		mapper = new ObjectMapper();
	//		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	//		List<Manifest> manifests = dBUtility.getManifests();
	//		String response = null;
	//		try {
	//			response = mapper.writeValueAsString(manifests);
	//		}
	//		catch (JsonProcessingException ex) {
	//			Logger.getLogger(SubManifestListingFragmentController.class.getName()).log(Level.SEVERE, null, ex);
	//		}
	//		return new ResponseEntity<>(response, HttpStatus.OK);
	//	}
	
}
