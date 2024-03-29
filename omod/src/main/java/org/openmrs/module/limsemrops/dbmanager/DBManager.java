package org.openmrs.module.limsemrops.dbmanager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.openmrs.Patient;
import org.openmrs.module.limsemrops.omodmodels.Auth;
import org.openmrs.module.limsemrops.omodmodels.DBConnection;
import org.openmrs.module.limsemrops.omodmodels.Manifest;
import org.openmrs.module.limsemrops.omodmodels.PatientID;
import org.openmrs.module.limsemrops.omodmodels.Result;
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
                + " and DATE(encounter_datetime) between ? "
                + "and ? and `voided` = 0 ";

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
	
	public List<Integer> getRecentRecencyClientEncounter(Date startDate, Date endDate) throws SQLException {

        String sqlStr = "select encounter_id from " + ConstantUtils.ENCOUNTER_TABLE + " where encounter_type = "
                + ConstantUtils.Client_Intake_Form_Encounter_Type_Id
                + " and DATE(encounter_datetime) between ? "
                + "and ? and `voided` = 0 ";

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
	
	//    public String getFacilityName() throws SQLException {
	//
	//        String facility_name = null;
	//
	//        String sqlStr = "select property_value from global_property where property = 'Facility_Name'";
	//
	//        pStatement = conn.prepareStatement(sqlStr);
	//        resultSet = pStatement.executeQuery();
	//
	//        if (resultSet.next()) {
	//            facility_name = resultSet.getString("property_value");
	//        }
	//
	//        return facility_name;
	//    }
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
		pStatement = conn
		        .prepareStatement("insert into "
		                + ConstantUtils.MANIFEST_TABLE
		                + "(manifest_id,sample_space,result_status,created_by,test_type,referring_lab_state,referring_lab_lga,"
		                + "date_schedule_for_pickup,sample_pick_up_on_time,rider_total_samples_picked,rider_temp_at_pick_up,rider_name,"
		                + "rider_phone_number,pcr_lab_name,pcr_lab_code,feedback) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		pStatement.setString(1, manifest.getManifestID());
		pStatement.setString(2, manifest.getSampleSpace());
		pStatement.setString(3, manifest.getResultStatus());
		pStatement.setString(4, manifest.getCreatedBy());
		pStatement.setString(5, manifest.getTestType());
		pStatement.setString(6, manifest.getReferringLabState());
		pStatement.setString(7, manifest.getReferringLabLga());
		if (manifest.getDateScheduleForPickup() != null) {
			pStatement.setDate(8, new java.sql.Date(manifest.getDateScheduleForPickup().getTime()));
		} else {
			pStatement.setDate(8, null);
		}
		
		pStatement.setString(9, manifest.getSamplePickUpOnTime());
		pStatement.setInt(10, manifest.getRiderTotalSamplesPicked());
		pStatement.setString(11, manifest.getRiderTempAtPickUp());
		pStatement.setString(12, manifest.getRiderName());
		pStatement.setString(13, manifest.getRiderPhoneNumber());
		pStatement.setString(14, manifest.getPcrLabName());
		pStatement.setString(15, manifest.getPcrLabCode());
		pStatement.setString(16, manifest.getFeedback());
		
		int response = pStatement.executeUpdate();
		
		return response;
		
	}
	
	public int updateManifestResultStatus(String resultStatus, String creator, String manifestId) throws SQLException {
		
		pStatement = conn.prepareStatement("update " + ConstantUtils.MANIFEST_TABLE
		        + " set result_status = ?, modified_by = ?, date_modified = NOW() where manifest_id = ? ");
		pStatement.setString(1, resultStatus);
		pStatement.setString(2, creator);
		pStatement.setString(3, manifestId);
		
		return pStatement.executeUpdate();
	}
	
	public int insertSampleResult(Result result) throws SQLException {
		
		ObjectMapper mapper = new ObjectMapper();
		AtomicInteger response = new AtomicInteger(0);
		
		try {
			pStatement = conn
			        .prepareStatement("insert into "
			                + ConstantUtils.MANIFEST_SAMPLES_RESULT
			                + "(sample_id,manifest_id, patient_id, pcr_lab_samplenumber,date_sample_receieved_at_pcrlab,test_result,result_date,"
			                + "approval_date,assay_date,date_result_dispatched,sample_status,sample_testable,created_by ) "
			                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			pStatement.setString(1, result.getSampleID());
			pStatement.setString(2, result.getManifestID());
			pStatement.setString(3, mapper.writeValueAsString(result.getPatientID()));
			pStatement.setString(4, result.getPcrLabSampleNumber());
			pStatement.setDate(5, new java.sql.Date(result.getDateSampleReceivedAtPCRLab().getTime()));
			pStatement.setString(6, result.getTestResult());
			pStatement.setDate(7, new java.sql.Date(result.getResultDate().getTime()));
			pStatement.setDate(8, new java.sql.Date(result.getApprovalDate().getTime()));
			pStatement.setDate(9, new java.sql.Date(result.getAssayDate().getTime()));
			pStatement.setDate(10, new java.sql.Date(result.getDateResultDispatched().getTime()));
			pStatement.setString(11, result.getSampleStatus());
			pStatement.setString(12, result.getSampleTestable());
			pStatement.setString(13, result.getCreatedBy());
			
			pStatement.executeUpdate();
			
			response.incrementAndGet();
		}
		catch (Exception ex) {
			Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return response.intValue();
		
	}
	
	public List<Result> getResultByManifestId(String manifestId) throws SQLException, IOException {

        String sql = "SELECT id, manifest_id, patient_id, sample_id, pcr_lab_samplenumber, date_sample_receieved_at_pcrlab, test_result, result_date, assay_date, approval_date, date_result_dispatched, sample_status, sample_testable, date_created, created_by, date_modified, modified_by\n"
                + "FROM " + ConstantUtils.MANIFEST_SAMPLES_RESULT + " where manifest_id = ? ";

        pStatement = conn.prepareStatement(sql);
        pStatement.setString(1, manifestId);
        ResultSet resultSet = pStatement.executeQuery();

//         if (resultSet.getString("patient_id") != null) {
//                vl.setPatientID(mapper.readValue(resultSet.getString("patient_id"), new TypeReference<List<PatientID>>() {
//                }));
//            }
        ObjectMapper mapper = new ObjectMapper();
        List<Result> results = new ArrayList<>();
        while (resultSet.next()) {
            Result result = new Result();
            result.setApprovalDate(resultSet.getDate("approval_date"));
            result.setAssayDate(resultSet.getDate("assay_date"));
            result.setCreatedBy(resultSet.getString("created_by"));
            result.setDateResultDispatched(resultSet.getDate("date_result_dispatched"));
            result.setDateSampleReceivedAtPCRLab(resultSet.getDate("date_sample_receieved_at_pcrlab"));
            result.setId(String.valueOf(resultSet.getInt("id")));
            result.setManifestID(resultSet.getString("manifest_id"));
            if (resultSet.getString("patient_id") != null) {

                List<PatientID> readValue = mapper.readValue(resultSet.getString("patient_id"), new TypeReference<List<PatientID>>() {
                });
                readValue = readValue.stream()
                        .sorted(Comparator.comparing(PatientID::getIdTypeCode))
                        .collect(Collectors.toList());
                result.setPatientID(readValue);
            }

            result.setPcrLabSampleNumber(resultSet.getString("pcr_lab_samplenumber"));
            result.setResultDate(resultSet.getDate("result_date"));
            result.setSampleID(resultSet.getString("sample_id"));
            result.setSampleStatus(resultSet.getString("sample_status"));
            result.setSampleTestable(resultSet.getString("sample_testable"));
            result.setTestResult(resultSet.getString("test_result"));

            results.add(result);

        }

        return results;

    }
	
	public int insertManifestSamples(List<VLSampleInformationFrontFacing> vLSampleInformationFrontFacings, String manifestID, String createdBy, Date dateSampleSent) throws SQLException {

        ObjectMapper mapper = new ObjectMapper();
        AtomicInteger response = new AtomicInteger(0);

//     vLSampleInformationFrontFacings =   vLSampleInformationFrontFacings.stream()
//                .sorted(Comparator.comparing(VLSampleInformationFrontFacing::getSampleID))
//                .collect(Collectors.toList());

        vLSampleInformationFrontFacings.stream().forEach(a -> {

            try {
                pStatement = conn.prepareStatement("insert into " + ConstantUtils.MANIFEST_SAMPLES_TABLE + "(manifest_id, patient_id, firstname, surname, sex, pregnantBreastFeedingStatus, age, "
                        + "dateOfBirth, sample_id, sample_type, indication_vl_test, art_commencement_date, drugregimen, "
                        + "sample_ordered_by, sample_ordered_date, sample_collected_by,"
                        + " sample_collected_date, date_sample_sent, encounter_id, created_by,sample_collection_time,sample_status) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                pStatement.setString(1, manifestID);
                pStatement.setString(2, mapper.writeValueAsString(a.getPatientID()));
                pStatement.setString(3, a.getFirstName());
                pStatement.setString(4, a.getSurName());
                pStatement.setString(5, a.getSex());
                pStatement.setString(6, a.getPregnantBreastFeedingStatus());
                pStatement.setInt(7, a.getAge());
                if (a.getDateOfBirth() != null) {
                    pStatement.setDate(8, new java.sql.Date(a.getDateOfBirth().getTime()));
                }

                pStatement.setString(9, a.getSampleID());
                pStatement.setString(10, a.getSampleType());
                pStatement.setInt(11, a.getIndicationVLTest());
                if (a.getArtCommencementDate() != null) {
                    pStatement.setDate(12, new java.sql.Date(a.getArtCommencementDate().getTime()));
                } else {
                    pStatement.setDate(12, null);
                }

                pStatement.setString(13, a.getDrugRegimen());
                pStatement.setString(14, a.getSampleOrderedBy());
                if (a.getSampleOrderDate() != null) {
                    pStatement.setDate(15, new java.sql.Date(a.getSampleOrderDate().getTime()));
                } else {
                    pStatement.setDate(15, null);
                }

                pStatement.setString(16, a.getSampleCollectedBy());
                if (a.getSampleCollectionDate() != null) {
                    pStatement.setDate(17, new java.sql.Date(a.getSampleCollectionDate().getTime()));
                } else {
                    pStatement.setDate(17, null);
                }

                if (dateSampleSent != null) {
                    pStatement.setDate(18, new java.sql.Date(dateSampleSent.getTime())); //date sample sent
                } else {
                    pStatement.setDate(18, null);
                }

                pStatement.setInt(19, a.getEncounterId());
                pStatement.setString(20, createdBy);
                if (a.getSampleCollectionTime() != null) {
                    pStatement.setDate(21, new java.sql.Date(a.getSampleCollectionTime().getTime()));
                } else {
                    pStatement.setDate(21, null);
                }

                pStatement.setString(22, "pending"); //sample status

                pStatement.executeUpdate();

                response.incrementAndGet();
            } catch (Exception ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        return response.intValue();

    }
	
	public int updateSamplesResultStatus(String sample_status, String creator, String sampleID) throws SQLException {
		
		pStatement = conn.prepareStatement("update " + ConstantUtils.MANIFEST_SAMPLES_TABLE
		        + " set sample_status = ?, modified_by = ?, date_modified = NOW() where sample_id = ? ");
		pStatement.setString(1, sample_status);
		pStatement.setString(2, creator);
		pStatement.setString(3, sampleID);
		
		return pStatement.executeUpdate();
		
	}
	
	public int deleteManifestResult(String manifestId) throws SQLException {
		
		pStatement = conn
		        .prepareStatement("delete from " + ConstantUtils.MANIFEST_SAMPLES_RESULT + " where manifest_id = ?");
		pStatement.setString(1, manifestId);
		return pStatement.executeUpdate();
	}
	
	public List<Manifest> getAllPendingManifest() throws SQLException {
		pStatement = conn
		        .prepareStatement("SELECT id, manifest_id, sample_space, test_type, referring_lab_state, referring_lab_lga, date_schedule_for_pickup, sample_pick_up_on_time, rider_total_samples_picked, rider_temp_at_pick_up, "
		                + "rider_name, rider_phone_number, pcr_lab_name, pcr_lab_code, "
		                + "result_status, date_created, created_by, date_modified, modified_by,feedback FROM lims_manifest where result_status = 'pending' ORDER BY date_created DESC");
		
		resultSet = pStatement.executeQuery();
		
		return convertResultSetToManifestList(resultSet);
		
	}
	
	public List<Manifest> getAllPendingManifestById(String manifestID) throws SQLException {
		pStatement = conn
		        .prepareStatement("SELECT id, manifest_id, sample_space, test_type, referring_lab_state, referring_lab_lga, date_schedule_for_pickup, sample_pick_up_on_time, rider_total_samples_picked, rider_temp_at_pick_up, "
		                + "rider_name, rider_phone_number, pcr_lab_name, pcr_lab_code, "
		                + "result_status, date_created, created_by, date_modified, modified_by,feedback FROM lims_manifest where result_status = 'pending' and manifest_id = ? ORDER BY date_created DESC ");
		
		pStatement.setString(1, manifestID);
		
		resultSet = pStatement.executeQuery();
		
		return convertResultSetToManifestList(resultSet);
		
	}
	
	public List<Manifest> getAllManifest() throws SQLException {
		
		pStatement = conn
		        .prepareStatement("SELECT id, manifest_id, sample_space, test_type, referring_lab_state, referring_lab_lga, date_schedule_for_pickup, sample_pick_up_on_time, rider_total_samples_picked, rider_temp_at_pick_up, "
		                + "rider_name, rider_phone_number, pcr_lab_name, pcr_lab_code, "
		                + "result_status, date_created, created_by, date_modified, modified_by,feedback FROM lims_manifest ORDER BY date_created DESC");
		
		resultSet = pStatement.executeQuery();
		
		return convertResultSetToManifestList(resultSet);
		
	}
	
	public Manifest getAllManifestByID(String manifestID) throws SQLException {
		pStatement = conn
		        .prepareStatement("SELECT id, manifest_id, sample_space, test_type, referring_lab_state, referring_lab_lga, date_schedule_for_pickup, sample_pick_up_on_time, rider_total_samples_picked, rider_temp_at_pick_up, "
		                + "rider_name, rider_phone_number, pcr_lab_name, pcr_lab_code, "
		                + "result_status, date_created, created_by, date_modified, modified_by,feedback FROM lims_manifest where manifest_id = ? ORDER BY date_created DESC ");
		
		pStatement.setString(1, manifestID);
		
		resultSet = pStatement.executeQuery();
		
		List<Manifest> manifests = convertResultSetToManifestList(resultSet);
		if (!manifests.isEmpty()) {
			return manifests.get(0);
		}
		
		return null;
		
	}
	
	public void updateManifestResultStatus() {
		//   pStatement = conn.prepareStatement("update manifest")
	}
	
	private List<Manifest> convertResultSetToManifestList(ResultSet resultSet) throws SQLException {

        List<Manifest> manifests = new ArrayList<>();

        while (resultSet.next()) {

            Manifest m = new Manifest();
            m.setCreatedBy(resultSet.getString("created_by"));
            m.setDateModified(resultSet.getDate("date_modified"));
            m.setDateScheduleForPickup(resultSet.getDate("date_schedule_for_pickup"));
            m.setManifestID(resultSet.getString("manifest_id"));
            m.setModifiedBy(resultSet.getString("modified_by"));
            m.setPcrLabCode(resultSet.getString("pcr_lab_code"));
            m.setPcrLabName(resultSet.getString("pcr_lab_name"));
            m.setReferringLabLga(resultSet.getString("referring_lab_lga"));
            m.setReferringLabState(resultSet.getString("referring_lab_state"));
            m.setResultStatus(resultSet.getString("result_status"));
            m.setRiderName(resultSet.getString("rider_name"));
            m.setRiderPhoneNumber(resultSet.getString("rider_phone_number"));
            m.setRiderTempAtPickUp(resultSet.getString("pcr_lab_name"));
            m.setRiderTotalSamplesPicked(resultSet.getInt("rider_total_samples_picked"));
            m.setSamplePickUpOnTime(resultSet.getString("sample_pick_up_on_time"));
            m.setSampleSpace(resultSet.getString("sample_space"));//VL or RECENT
            m.setTestType(resultSet.getString("test_type"));
            m.setDateCreated(resultSet.getDate("date_created"));
            m.setFeedback(resultSet.getString("feedback"));
            m.setFacility(Utils.getFacilityName());
            manifests.add(m);
        }

        return manifests;

    }
	
	public List<VLSampleInformationFrontFacing> getManifestSamples(String manifestId) throws SQLException, IOException {

        pStatement = conn.prepareStatement("SELECT id, manifest_id, patient_id, firstname, surname, sex, pregnantBreastFeedingStatus, age, dateOfBirth, sample_id, "
                + "sample_type, indication_vl_test, art_commencement_date, drugregimen, "
                + "sample_ordered_by, sample_ordered_date, sample_collected_by, sample_collected_date, "
                + "date_sample_sent, encounter_id, date_created,sample_status,rejection_reason, created_by, sample_collection_time, date_modified, modified_by"
                + " FROM lims_manifest_samples where manifest_id = ? ORDER BY sample_id ASC ");

        pStatement.setString(1, manifestId);

        resultSet = pStatement.executeQuery();

        List<VLSampleInformationFrontFacing> vLSampleInformationFrontFacings = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        while (resultSet.next()) {

            VLSampleInformationFrontFacing vl = new VLSampleInformationFrontFacing();

            vl.setAge(resultSet.getInt("age"));
            vl.setArtCommencementDate(resultSet.getDate("art_commencement_date"));
            vl.setDateOfBirth(resultSet.getDate("dateOfBirth"));
            vl.setDateSampleSent(resultSet.getDate("date_sample_sent"));
            vl.setDrugRegimen(resultSet.getString("drugregimen"));
            vl.setEncounterId(resultSet.getInt("encounter_id"));
            vl.setFirstName(resultSet.getString("firstname"));
            vl.setIndicationVLTest(resultSet.getInt("indication_vl_test"));
            if (resultSet.getString("patient_id") != null) {
                vl.setPatientID(mapper.readValue(resultSet.getString("patient_id"), new TypeReference<List<PatientID>>() {
                }));
            }
            vl.setPregnantBreastFeedingStatus(resultSet.getString("pregnantBreastFeedingStatus"));
            vl.setSampleCollectedBy(resultSet.getString("sample_collected_by"));
            vl.setSampleCollectionDate(resultSet.getDate("sample_collected_date"));
            vl.setSampleCollectionTime(resultSet.getDate("sample_collection_time"));
            vl.setSampleID(resultSet.getString("sample_id"));
            vl.setSampleOrderDate(resultSet.getDate("sample_ordered_date"));
            vl.setSampleOrderedBy(resultSet.getString("sample_ordered_by"));
            vl.setSampleStatus(resultSet.getString("sample_status"));
            vl.setSampleType(resultSet.getString("sample_type"));
            vl.setSex(resultSet.getString("sex"));
            vl.setSurName(resultSet.getString("surname"));

            vLSampleInformationFrontFacings.add(vl);

        }

        return vLSampleInformationFrontFacings;

    }
	
	public Auth getAuthModuleUserNamePassword() throws SQLException {
		
		pStatement = conn.prepareStatement("select * from " + ConstantUtils.AUTHMODULE_TABLE + " where id = 1");
		resultSet = pStatement.executeQuery();
		
		Auth a = new Auth();
		while (resultSet.next()) {
			a.setUsername(resultSet.getString("username"));
			a.setPassword(resultSet.getString("password"));
			break;
		}
		
		return a;
	}
	
	public int initializeAuthModuleUserNamePassword(Auth auth) throws SQLException {
		//used to set the very first username and password pair for the authmodule
		pStatement = conn.prepareStatement("insert into " + ConstantUtils.AUTHMODULE_TABLE
		        + " (username, password) values (?,?)");
		pStatement.setString(1, auth.getUsername());
		pStatement.setString(2, auth.getPassword());
		int response = pStatement.executeUpdate();
		return response;
	}
	
	public int setAuthModuleUserNamePassword(Auth auth) throws SQLException {
		//used when UPDATING the username and password pair for the authmodule
		pStatement = conn.prepareStatement("update " + ConstantUtils.AUTHMODULE_TABLE
		        + " SET username = ?, password = ? where username = ?");
		pStatement.setString(1, auth.getUsername());
		pStatement.setString(2, auth.getPassword());
		pStatement.setString(3, auth.getUsername());
		
		int response = pStatement.executeUpdate();
		return response;
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
