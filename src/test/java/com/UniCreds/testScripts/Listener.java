package com.UniCreds.testScripts;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.UniCreds.base.Base;
import com.UniCreds.base.Extent_Reports;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listener extends Base implements ITestListener {
	
	ExtentReports extentReports; 
    ExtentTest test;
    String testName;
    
    @Override
	public void onStart(ITestContext context) {
    	extentReports = Extent_Reports.reports();
	}

    @Override
    public void onTestStart(ITestResult result) {
        testName = result.getMethod().getMethodName();
        test = extentReports.createTest(testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS,testName+" test is passed");
    }

    WebDriver ldriver = null;
    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
        String className = result.getInstanceName();
        String methodName = result.getMethod().getMethodName();
        try {
            ldriver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("ldriver").get(result.getInstanceName());
        } catch (Exception e) {
        }
        try{
            test.addScreenCaptureFromPath(ScreenshotOnFailure(ldriver,className,methodName),result.getMethod().getMethodName());
        }catch (Exception e) {
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }

}
