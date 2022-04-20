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

	public String userToken = "";
	public String userId = "";

	JSONObject jsonObject = new JSONObject();
	Response response;
	JsonPath path;

	@Before
	public void initialization() {
		RestAssured.baseURI = prop.getProperty("baseURL");
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");

		jsonObject.put("login", prop.getProperty("email"));
		jsonObject.put("password", prop.getProperty("password"));
		request.body(jsonObject);
		Response response = request.post("/login");
		path = response.jsonPath();
		String userToken = path.getString("user-token");
		System.out.println(userToken);
		userId = path.getString("objectId");
		System.out.println(userId);

	}

	@Test
	public void verifyUpdateFunctionality() {
		RequestSpecification request = RestAssured.given();
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("user-token", userToken);
		jsonObject = new JSONObject();
		jsonObject.put("email", prop.getProperty("email"));
		jsonObject.put("password", prop.getProperty("newPassword"));
		request.body(jsonObject);
		response = request.put("/" + userId);
		System.out.println(path.get());
		Assert.assertEquals(prop.getProperty("email"), path.getString("email"));

	}

	@Test
	public void verifyUpdateFunctionalityWithoutLoggingIn() {
		RequestSpecification request = RestAssured.given();
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("user-token", userToken);
		jsonObject = new JSONObject();
		jsonObject.put("email", prop.getProperty("email"));
		jsonObject.put("password", prop.getProperty("newPassword"));
		request.body(jsonObject);
		response = request.put("/" + prop.getProperty("invalidUserID"));
		System.out.println(path.get());
		Assert.assertEquals(prop.getProperty("email"), path.getString("email"));

	}

}
