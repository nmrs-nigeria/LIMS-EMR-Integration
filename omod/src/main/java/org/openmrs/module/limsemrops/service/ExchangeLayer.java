/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.RequestBodyEntity;
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

    public void requestManifestResultOnline(ResultRequest resultRequest) throws UnirestException {

        Unirest.setObjectMapper(GeneralMapper.getCustomObjectMapper());

        HttpResponse<VLResultResponse> response = Unirest.post(ConstantUtils.BASE_URL + ConstantUtils.REQUEST_SAMPLE_RESULT)
                .header("Content-Type", "application/json").body(resultRequest).asObject(VLResultResponse.class);

        if (response != null && response.getStatus() == 200) {
           //got results
        }

    }

}
