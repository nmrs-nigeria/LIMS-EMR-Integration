/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.RequestBodyEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openmrs.module.limsemrops.omodmodels.ResultRequest;
import org.openmrs.module.limsemrops.omodmodels.SampleCollectionManifest;
import org.openmrs.module.limsemrops.omodmodels.VLResultResponse;
import org.openmrs.module.limsemrops.utility.ConstantUtils;
import org.openmrs.module.limsemrops.utility.GeneralMapper;

/**
 * @author MORRISON.I
 */
public class ExchangeLayer {
	
	public boolean sendSamplesOnline(String sampleCollectionManifest) throws UnirestException {
		
		Unirest.setObjectMapper(GeneralMapper.getCustomObjectMapper());
		
		HttpResponse<String> response = Unirest.post(ConstantUtils.BASE_URL + ConstantUtils.POST_SAMPLES)
		        .header("Content-Type", "application/json").body(sampleCollectionManifest).asString();
		
		if (response != null && response.getStatus() == 200) {
			System.out.println(response.getBody());
			return true;
		}
		System.out.println("REQUEST FAILED");
		return false;
		
	}
	
	public HttpResponse<String> requestManifestResultOnline(ResultRequest resultRequest) throws UnirestException {
		
		Unirest.setObjectMapper(GeneralMapper.getCustomObjectMapper());
		
		HttpResponse<String> response = Unirest.post(ConstantUtils.BASE_URL + ConstantUtils.REQUEST_SAMPLE_RESULT)
		        .header("Content-Type", "application/json").body(resultRequest).asString();
		
		return response;
		
	}
	
	public static void main(String args[]) {
		ResultRequest resultRequest = new ResultRequest();
		resultRequest.setManifestID("34CC7F1-70E6-4");
		resultRequest.setReceivingPCRLabID("LIMS150002");
		resultRequest.setReceivingPCRLabName("National Reference Laboratory Gaduwa (NRL) Abuja");
		resultRequest.setSendingFacilityID("FH7LMnbnVlT");
		resultRequest.setSendingFacilityName("Braithwaite Memorial Specialist Hospital");
		resultRequest.setTestType("VL");
		
		try {
			HttpResponse<String> result = new ExchangeLayer().requestManifestResultOnline(resultRequest);
			System.out.println(result.getStatus());
			System.out.println(result.getBody());
		}
		catch (UnirestException ex) {
			Logger.getLogger(ExchangeLayer.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	
}
