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

public class BackEndlessUpdateUserApi extends TestBase {

	@Before
	public void initialization() {
		RestAssured.baseURI = prop.getProperty("baseURL");
		

	}

	@Test
	public void verifyUpdateFunctionality() {
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("login", prop.getProperty("email"));
		jsonObject.put("password", prop.getProperty("password"));
		request.body(jsonObject);
		Response response = request.post("/login");
		JsonPath path = response.jsonPath();
		String userToken = path.getString("user-token");
		System.out.println(userToken);
		String userId = path.getString("objectId");
		System.out.println(userId);
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("user-token", userToken);
		jsonObject = new JSONObject();
		jsonObject.put("email", prop.getProperty("email"));
		jsonObject.put("password", prop.getProperty("password"));
		request.body(jsonObject);
		response = request.post("/"+userId);
		Assert.assertEquals(prop.getProperty("email"), path.getString("email"));
		Assert.assertEquals(prop.getProperty("password"), path.getString("password"));

	}

}
