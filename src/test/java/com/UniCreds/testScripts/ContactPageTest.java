package com.UniCreds.testScripts;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import com.UniCreds.base.Base;
import com.UniCreds.pageObjects.ContactPage;
import com.UniCreds.utilities.Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class ContactPageTest extends Base {
	
	public static WebDriver ldriver;
	public ContactPage contactPage;

    Logger log = LogManager.getLogger(ContactPageTest.class.getName());
    
    public void checkData(String logData) {
    	int countOfRequiredElements = contactPage.requiredFieldElements().size();
    	
    	if(countOfRequiredElements == 0) {
    		
    		WebDriverWait wait=new WebDriverWait(ldriver, Duration.ofSeconds(10));
    		wait.until(ExpectedConditions.visibilityOf(contactPage.validateSuccessMessage()));
    		
    		boolean successMessageVisibility = contactPage.validateSuccessMessage().isDisplayed();
    		log.info("Passed : "+logData.toString());
    		Assert.assertTrue(successMessageVisibility);
    		
    	}else if(countOfRequiredElements > 0) {    		
    		log.info("Failed: "+logData.toString());
    		Assert.assertTrue(false);
    	}
    }
    
    
    public int checkMobileNumber(String number) {
    	int phnNumberLength = number.length();
    	return phnNumberLength;
    }

    @BeforeTest
    public void getDriver() throws IOException {
        ldriver = instatiateDriver();
        contactPage = new ContactPage(ldriver);
        ldriver.get(pageUrl());
        log.info("Browser is invoked");
        log.info("URL is passed");
    }
    
    @Test
    public void _0_checkTitle() {
    	String title = ldriver.getTitle();
    	String expectedTitle = "Contact Us | UniCreds";
    	Assert.assertEquals(title, expectedTitle);
        log.info("Page title is checked successfully");
    }
    
    @Test
    public void _1_validateFormVisibility() throws IOException {
    	boolean flag = contactPage.getForm().isDisplayed();
        Assert.assertTrue(flag);
        log.info("Form visibility on page is checked successfully");
    }
    
    @Test 
    public void _2_submitDirectly() {
    	contactPage.clickSubmitButton();
    	checkData("Direct Submission");
    }
    
    
    @Test(dataProvider = "dataSet")
    public void _3_enterDetails(String name,String email,String phoneNumber, String message, String dataType){
    	contactPage.enterFullName(name);
    	contactPage.enterEmail(email);
    	contactPage.setCountryCodeByVisibletext("Bhutan (+975)");
    	contactPage.enterPhoneNumber(phoneNumber);
    	contactPage.enterMessage(message);
    	contactPage.clickSubmitButton();
    	checkData(dataType);
    }  
    
    @Test
    public void _4_checkInputBoxColorOnError() throws IOException, InterruptedException {
    	contactPage.clickSubmitButton();
    	Thread.sleep(3000);
    	List<WebElement> inputBoxes = contactPage.requiredFieldElements();
    	for(WebElement inputBox:inputBoxes) {
    		String actualColor = inputBox.getCssValue("border-color");
        	Assert.assertEquals(actualColor, "rgb(220, 53, 69)");
    	}
    }
    
    @Test
    public void _5_inputFieldsAfterSuccessfulSubmission() {
    	ldriver.navigate().refresh();
    	contactPage.enterFullName("dummy");
    	contactPage.enterEmail("dummy@email.com");
    	contactPage.enterPhoneNumber("123456789");
    	contactPage.enterMessage("This is message");
    	contactPage.clickSubmitButton();
    	
    	String fullname = contactPage.fullName().getText();
    	String email= contactPage.email().getText();
    	String phoneNumber= contactPage.phoneNumber().getText();
    	String message = contactPage.message().getText();
    	
    	Assert.assertEquals(fullname, "");
    	Assert.assertEquals(email, "");
    	Assert.assertEquals(phoneNumber, "");
    	Assert.assertEquals(message, "");	
    }
    
    @DataProvider
	public static Object[][] dataSet(){
		Object[][] data = new Object[4][5];
		
		data[0][0] = Utilities.generateFullName(5);
		data[0][1] = Utilities.generateEmail(5) + "@gmail.com";
		data[0][2] = Utilities.generatePhoneNumber(10);
		data[0][3] = Utilities.generateMessage(10);
		data[0][4] = "Valid data set";
		
		data[1][0] = Utilities.generateFullName(5);
		data[1][1] = Utilities.generateEmail(5) + "@gmail.com";
		data[1][2] = Utilities.generatePhoneNumber(5);
		data[1][3] = Utilities.generateMessage(10);
		data[1][4] = "Mobile Number's length is less than standard format";
		
		data[2][0] = Utilities.generateFullName(5);
		data[2][1] = Utilities.generateEmail(5);
		data[2][2] = Utilities.generatePhoneNumber(6);
		data[2][3] = Utilities.generateMessage(10);
		data[2][4] = "Invalid email format";
		
		data[3][0] = Utilities.generateFullName(5);
		data[3][1] = Utilities.generateEmail(5) + "@gmail.com";
		data[3][2] = "hhhbt757@jgjghg";
		data[3][3] = Utilities.generateMessage(10);
		data[3][4] = "Invalid mobile format i.e alphanumeric is given";
		
		return data;
	}
    
    @AfterTest
    public void teardown() {
    	ldriver.quit();
        log.info("Browser is closed successfully");
    }
}
