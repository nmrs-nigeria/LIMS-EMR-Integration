/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.limsemrops.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openmrs.module.limsemrops.omodmodels.SampleValidationResult;
import org.openmrs.module.limsemrops.omodmodels.VLSampleInformation;

/**
 * @author MORRISON.I
 */
public class SchemaValidator {
	
	static ObjectMapper mapper = new ObjectMapper();
	
	static String error;
	
	public static  List<SampleValidationResult> validateSample(List<VLSampleInformation> VLSampleInformation) {
        List<SampleValidationResult> results = new ArrayList<>();

        VLSampleInformation.stream().forEach(a -> {
            SampleValidationResult sampleValidationResult = new SampleValidationResult();
            try {
                error = null;
                sampleValidationResult.setPatientId(a.getPatientID().toString());
                sampleValidationResult.setSampleId(a.getSampleID());

                validate(mapper.writeValueAsString(a));

            } catch (ValidationException e) {
                error += e.getMessage();
                e.getCausingExceptions().stream()
                        .map(ValidationException::getMessage)
                        .forEach(SchemaValidator::appendError);
            } catch (JsonProcessingException ex) {
                Logger.getLogger(SchemaValidator.class.getName()).log(Level.SEVERE, null, ex);
            }
            sampleValidationResult.setValidateError(error);
            results.add(sampleValidationResult);
        });

        return results;

    }
	
	private static void validate(String input) {
		JSONObject jsonSchema = new JSONObject(new JSONTokener(
		        SchemaValidator.class.getResourceAsStream("/sample-schema-modified.json")));
		JSONObject jsonSubject = new JSONObject(input);
		
		Schema schema = SchemaLoader.load(jsonSchema);
		schema.validate(jsonSubject);
		
	}
	
	private static void appendError(String error) {
		SchemaValidator.error += error;
	}
	
}
