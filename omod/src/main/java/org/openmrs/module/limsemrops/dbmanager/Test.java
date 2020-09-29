//package org.openmrs.module.limsemrops.dbmanager;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.openmrs.module.limsemrops.omodmodels.Manifest;
//import org.openmrs.module.limsemrops.page.controller.ManifestListPageController;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.List;
//
//public class Test {
//
//    public static void main(String[] args) throws SQLException, JsonProcessingException {
//        List<Manifest> manifest = new DBManager().getAllManifest();
//        //ResponseEntity<?> manifest2 = new ManifestListPageController().getAllSavedManifest();
//        System.out.println(manifest);
//        System.out.println(manifest.size());
//        ObjectMapper mapper = new ObjectMapper();
//        String response = mapper.writeValueAsString(manifest);
//        System.out.println("Response from page: " + response);
//       // System.out.println(manifest2);
//        System.out.println(new ResponseEntity<>(response, HttpStatus.OK));
//    }
//
//}
