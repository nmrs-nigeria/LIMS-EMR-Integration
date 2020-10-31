//package org.openmrs.module.limsemrops.dbmanager;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.openmrs.module.limsemrops.omodmodels.Result;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
//
//public class Test {
//
//    public static void main(String[] args) throws SQLException, IOException {
//        String manID = "49E830E-EDFA-4";
//        List<Result> result = new DBManager().getResultByManifestId(manID);
//        //ResponseEntity<?> manifest2 = new ManifestListPageController().getAllSavedManifest();
//        System.out.println(result);
//        System.out.println(result.size());
//        ObjectMapper mapper = new ObjectMapper();
//        String response = mapper.writeValueAsString(result);
//        System.out.println("Response from page: " + response);
//       // System.out.println(manifest2);
//        System.out.println(new ResponseEntity<>(response, HttpStatus.OK));
//    }
//
//}
