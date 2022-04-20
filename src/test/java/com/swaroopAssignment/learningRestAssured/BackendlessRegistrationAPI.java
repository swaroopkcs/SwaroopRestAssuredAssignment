package com.swaroopAssignment.learningRestAssured;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.swaroopAssignment.BasePackage.TestBase;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BackendlessRegistrationAPI extends TestBase{
	
	@Before
	public void initialization() {
		RestAssured.baseURI = prop.getProperty("baseURL");
//		RestAssured.baseURI = "https://knowingtrade.backendless.app/api/users";
	}
	
	@Test
	public void verifyRegistrationFunctionality() {
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("email", prop.getProperty("email"));
//		jsonObject.put("email", "swaroop45678@gmal.com");
		jsonObject.put("password", prop.getProperty("password"));
		request.body(jsonObject);
		Response response = request.post("/register");
		Assert.assertEquals(200, response.statusCode());
	}

}
