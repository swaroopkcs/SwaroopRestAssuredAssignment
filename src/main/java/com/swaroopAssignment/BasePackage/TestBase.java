package com.swaroopAssignment.BasePackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

//	public static WebDriver wd;
	public static Properties prop;
	public JSONObject jsonObject; 

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream fs = new FileInputStream(
					"src\\main\\java\\com\\swaroopAssignment\\Config\\Config.Properties");
			prop.load(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		jsonObject = new JSONObject();
	}

	
//	public void initialization() {
////		String browserName = prop.getProperty("browser");
////
////		switch (browserName) {
////		case "chrome":
////			WebDriverManager.chromedriver().setup();
////			wd = new ChromeDriver();
////			break;
////		case "edge":
////			WebDriverManager.edgedriver().setup();
////			wd = new ChromeDriver();
////			break;
////		case "firefox":
////			WebDriverManager.firefoxdriver().setup();
////			wd = new ChromeDriver();
////			break;
////		default:
////			System.out.println("Please use a valid browser name");
////			break;
////		}
//		
//		
//	
//	}
	
	
}
