package pages;


import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.serenitybdd.core.pages.PageObject;
import utils.ReportLog;

public class LoginPage extends PageObject{
	
	private String email1;
	private String password1;
	
	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	
	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	@Steps
	ReportLog report;
		
	@FindBy(id= "email")
	WebElementFacade email;
	
	@FindBy(id= "password")
	WebElementFacade password;
	
	@FindBy(xpath= "//span[text()= 'Forgot Password?']")
	WebElementFacade forgotPassword;
	
	@FindBy(xpath = "//span[text()='Create a ']")
	WebElementFacade clickPcIdLink;
	
	@FindBy(css = "button[type='submit']")
	WebElementFacade signIn;
	
	@FindBy(xpath = "//h2[text()= 'Sign in with ']")
	WebElementFacade signInPageHeader;
	
	
	JavascriptExecutor js = (JavascriptExecutor) getDriver();
	
	
	public void EnterUsername(String emailAddress) {
		email.sendKeys(emailAddress);
		waitABit(1000);
		report.LOG("Email address is entered");
	}
	
	public void EnterUsername1(String emailaddress) {
		
		setEmail1(emailaddress);
		email.sendKeys(getEmail1());
		System.out.println(getEmail1());
		waitABit(1000);
		report.LOG("Email address is entered");
	}
	
	public void Enterpassword(String Password) {
		password.sendKeys(Password);
		waitABit(1000);
		report.LOG("password is entered");
	}
	@Step
	public void clickSignIn() {
	//	js.executeScript("arguments[0].click();", signIn);
		signIn.click();
		waitABit(8000);
		
	}
	@Step
	public void clickCreatePCId() {
		
		Assert.assertEquals("Sign in displayed", "Sign in with", signInPageHeader.getText().substring(0, 12));
		
		clickPcIdLink.click();
		report.LOG("Click on Create PC id successful");
		waitABit(4000);
		

	}
	@Step
	public void clickForgotPassword() {
		forgotPassword.click();

	}

}
