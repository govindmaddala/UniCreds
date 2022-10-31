package com.UniCreds.base;


import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Extent_Reports {

    static ExtentReports extentReports = new ExtentReports();

    @BeforeTest
    public static ExtentReports reports(){
        String path = System.getProperty("user.dir")+"\\reports\\ExtentReports\\index.html";
        ExtentSparkReporter esr = new ExtentSparkReporter(path);
        esr.config().setReportName("Unicreds Quality Assurance Assignment");
        esr.config().setDocumentTitle("UniCreds Form Page");
        extentReports.attachReporter(esr);
        extentReports.setSystemInfo("Tester","Govind Maddala");
        return extentReports;
    }
}
