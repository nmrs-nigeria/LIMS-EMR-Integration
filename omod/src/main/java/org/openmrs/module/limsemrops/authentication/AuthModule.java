package org.openmrs.module.limsemrops.authentication;

import java.sql.SQLException;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.*;
import org.openmrs.module.limsemrops.dbmanager.*;
import org.openmrs.module.limsemrops.omodmodels.Auth;
import org.openmrs.module.limsemrops.utility.Utils;
import org.openmrs.module.limsemrops.authentication.AuthModuleUtils;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class AuthModule {
	
	private final DBManager dbManager;
	
	private String cachedToken = null;
	
	private String endPoint = null;
	
	private String userName = null;
	
	private boolean tokenExpired() {
		return false; //TODO: If TG implements expiry, reimplement this
	}
	
	private String computeDefaultPassword(String username) {
		//get the hash of the username
		String userNameHash = AuthModuleUtils.hash(username);
		//get the minute of the current time
		String formattedMinute = LocalTime.now().format(DateTimeFormatter.ofPattern("mm"));
		String concatenation = userNameHash + formattedMinute;
		String finalHash = AuthModuleUtils.hash(concatenation);
		return finalHash;
	}
	
	private String requestToken() throws UnirestException, SQLException {
		//get the access token details
		Auth a = dbManager.getAuthModuleUserNamePassword();
		String u = a.getUsername();
		String p = a.getPassword();
		boolean isInitialRequest = false;
		
		//check if we need to generate a new password or nah
		if (p == null) {
			//compute a default password, but set the username properly first
			u = Utils.getFacilityDATIMId();
			p = this.computeDefaultPassword(u);
			isInitialRequest = true;
		}
		
		String requestBody = "{\"email\": \"USER\",\"password\": \"PASS\"}".replace("USER", u).replace("PASS", p);
		HttpResponse<String> response = Unirest.post(this.endPoint).header("content-type", "application/json")
		        .body(requestBody).asString();
		String responseBody = response.getBody();
		int statusCode = response.getStatus();
		//now, if its an initial request, we should strip out the new password and use the correct method
		if (isInitialRequest) {
			if (statusCode == 200) {
				// find the new password and extract it from the JWT, then request a new,
				// sanitized JWT
				String actualJWT = AuthModuleUtils.extractJWT(responseBody);
				// base64decode the JWT so we can see what's in it.
				String decodedJWT = new String(Base64.getMimeDecoder().decode(actualJWT));
				// somewhere in there, we should have a key called password. Take it out.
				String newPassword = AuthModuleUtils.extractKey("password", decodedJWT);
				// then, persist the new password to the DB
				Auth details = new Auth();
				details.setUsername(Utils.getFacilityDATIMId());
				details.setPassword(newPassword);
				dbManager.initializeAuthModuleUserNamePassword(details); // initial setting
				return requestToken(); // redo it, using the now-saved password
			} else {
				//we didn't get a 200 back, so something is wrong. Would be nice to log it.
				return null;
			}
		} else {
			if (statusCode == 409) {
				//this is a conflict , so we should invalidate our current token and redo
				Auth newAuth = new Auth();
				newAuth.setUsername(Utils.getFacilityDATIMId());
				newAuth.setPassword(null); //set this to null completely
				dbManager.setAuthModuleUserNamePassword(newAuth);
				//ideally, redo password generation here
				return this.requestToken(); //hopefully this one should complete.
			} else if (statusCode == 200) {
				//business as usual
				return AuthModuleUtils.extractJWT(responseBody);
			} else {
				return null; //something suspicious happened.
			}
		}
		
	}
	
	public String getToken() {
		try {
			if (this.cachedToken == null || tokenExpired()) {
				this.cachedToken = requestToken();
			}
		}
		catch (Exception e) {
			return null;
		}
		
		return this.cachedToken;
	}
	
	public AuthModule(String _endpoint) {
		super();
		this.endPoint = _endpoint;
		this.userName = Utils.getFacilityDATIMId();
		this.dbManager = new DBManager();
	}
	
	public void getTempToken() {
		
	}
	
}
