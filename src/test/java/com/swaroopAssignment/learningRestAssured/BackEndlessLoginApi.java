package com.swaroopAssignment.learningRestAssured;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.swaroopAssignment.BasePackage.TestBase;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BackEndlessLoginApi extends TestBase {
	
	@Before
	public void initialization() {
		RestAssured.baseURI = prop.getProperty("baseURL");
		
	}
	
	@Test
	public void verifyLoginFunctionalityUsingValidCredentials() {
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("login", prop.getProperty("email"));
		jsonObject.put("password", prop.getProperty("password"));
		request.body(jsonObject);
		Response response = request.post("/login");
		JsonPath path = response.jsonPath();
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals(prop.getProperty("validLoginAccountType"), path.getString("accountType"));
		Assert.assertEquals(prop.getProperty("email"), path.getString("email"));
		String userToken = path.getString("user-token");
		System.out.println(userToken);
		String userId = path.getString("objectId");
		System.out.println(userId);
		
	}
	
	@Test
	public void verifyLoginFunctionalityUsingInValidCredentials() {
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
//		JSONObject jsonObject = new JSONObject();
		jsonObject.put("login", prop.getProperty("invalidEmail"));
		jsonObject.put("password", prop.getProperty("invalidPass"));
		request.body(jsonObject);
		Response response = request.post("/login");
		JsonPath path = response.jsonPath();
		Assert.assertEquals(prop.getProperty("inValidLoginMessage"), path.getString("message"));
		Assert.assertEquals(prop.getProperty("inValidLoginCode"), path.getString("code"));
		Assert.assertEquals(401, response.statusCode());
	}
	
	

}
