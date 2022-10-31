package com.UniCreds.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Base {
	
	public WebDriver driver;
	public static Properties prop;
    public String configFilePath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\UniCreds\\base\\configure.properties" ;
    String driverPath = System.getProperty("user.dir") + "\\Drivers";
    
	public WebDriver instatiateDriver() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(configFilePath);
		prop.load(fis);

		String browser = prop.getProperty("browser");

		switch (browser){
			case "chrome":
				WebDriverManager.chromedriver().setup();
				String headless = prop.getProperty("headless");
				ChromeOptions op = new ChromeOptions();
				if(headless.equals("true")){
					op.addArguments("headless");
				}
				driver = new ChromeDriver(op);
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case "msedge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
			default:
				driver = null;
				break;
		}
		return driver;
	}
	


	public String ScreenshotOnFailure(WebDriver driver, String className, String methodName) throws IOException {
		
		Date time = new Date();
        DateFormat dateFormat = new SimpleDateFormat("hh-mm-ss");  
        String strDate = dateFormat.format(time);  
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(100,100)");
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File imageFile = screenshot.getScreenshotAs(OutputType.FILE);
		String imagePath = System.getProperty("user.dir") + "\\reports\\images\\"+className+" - "+methodName+" - "+strDate+".png";
		File filePath = new File(imagePath);
		FileUtils.copyFile(imageFile,filePath);
		return imagePath;
	}

	public String pageUrl(){
		String pageUrl = prop.getProperty("url");
		return pageUrl;
	}
}
