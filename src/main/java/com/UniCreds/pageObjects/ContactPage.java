package com.UniCreds.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ContactPage {
	
	public static WebDriver ldriver;
	
	public ContactPage(WebDriver driver) {
		ldriver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css = ".navbar-brand")
	WebElement logo;

	@FindBy(linkText = "Student Loan  ")
	WebElement studentLoanButton;

	@FindBy(linkText = "Contact Us  ")
	WebElement contactUsButton;

	@FindBy(css = "li .Navbar_loginButton__oj_-G")
	WebElement loginButton;

	@FindBy(css = "form.contactForm1")
	WebElement contactForm;

	@FindBy(css = "[name='fullname']")
	WebElement fullName;

	@FindBy(css = "[name='email']")
	WebElement email;

	@FindBy(css = "select.form-control.form-control-md")
	WebElement countryCodeSelection;

	@FindBy(css = "[name='phone']")
	WebElement phoneNumber;

	@FindBy(css = "[name='message']")
	WebElement message;

	@FindBy(xpath = "//button[@id='contactButton']")
	WebElement submitButton;

	@FindBy(css = "button[aria-label='Contact us on whatsapp']")
	WebElement whatsappBtn;
	
	@FindBy(css=".is-invalid")
	List<WebElement> requiredFields;

	@FindBy(css = ".invalid-feedback")
	List<WebElement> fieldRequiredMessage;
	
	@FindBy(css=".alert-success")
	WebElement successMessage;
	
	
	public WebElement fullName() {
		return fullName;
	}
	
	public WebElement email() {
		return email;
	}
	
	public WebElement phoneNumber() {
		return phoneNumber;
	}
	
	public WebElement message() {
		return message;
	}
	
	public WebElement getForm() {
		return contactForm;
	}

	public void enterFullName(String fullname){
		fullName.sendKeys(fullname);
	}

	public void enterEmail(String mail){
		email.sendKeys(mail);
	}
	
	public void enterPhoneNumber(String mobileNumber){
		phoneNumber.sendKeys(mobileNumber);
	}
	
	public void enterMessage(String msg){
		message.sendKeys(msg);
	}
	
	public void clickSubmitButton() {
		submitButton.click();
	}
	
	public WebElement validateSuccessMessage() {
		return successMessage;
	}
	
	public List<WebElement> requiredFieldElements(){
		return requiredFields;
	}
	
	public List<WebElement> fieldRequiredElements() {
		return fieldRequiredMessage;
	}
	
	public Select createSelectInstance() {
		Select selectCountryCode = new Select(countryCodeSelection);
		return selectCountryCode;
		
	}

	public void setCountryCodeByValue(String value){
		this.createSelectInstance().selectByValue(value);
	}

	public void setCountryCodeByVisibletext(String visibletext){
		this.createSelectInstance().selectByVisibleText(visibletext);
	}

	public void setCountryCodeByIndex(int index){
		this.createSelectInstance().selectByIndex(index);
	}

	public String getCountryCode(){
		WebElement selectedCountryCodeElement= this.createSelectInstance().getFirstSelectedOption();
		String countryCode = selectedCountryCodeElement.getText();
		return countryCode;
	}
}
