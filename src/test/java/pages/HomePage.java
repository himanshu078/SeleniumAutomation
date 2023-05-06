package pages;


import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Steps;
import setup.Init;
import utils.CommonMethods;
import utils.ReportLog;


public class HomePage extends PageObject{

	@Steps
	ReportLog report;
	@Steps
	Init init;
	@Steps
	Product product;
	@Steps
	CommonMethods common;


	@FindBy(xpath= "//button[text()='No thanks']")	
	//	@FindBy(xpath= "//button[@class='modal-dialog__content__close']")
	WebElementFacade signUpPopUp;

	@FindBy(xpath= "//button[@class='lds__privacy-policy__btnClose']")
	WebElementFacade clearCookiesPopUp;

	@FindBy(tagName= "Loblaw Privacy Policy")
	WebElementFacade PrivacyPolicyText;

	@FindBy(xpath= "//a[@data-auid='account-login-button']")
	WebElementFacade SignInOnHome;

	@FindBy(xpath= "//button[text()= 'My Account']")
	WebElementFacade clickmyAccount;

	@FindBy(linkText= "Orders")
	WebElementFacade MyAccountOrders;

	@FindBy(linkText= "Profile")
	WebElementFacade MyAccountProfile;

	@FindBy(linkText= "PC Optimum")
	WebElementFacade MyAccountPcOptimum;

	@FindBy(linkText= "Payment")
	WebElementFacade MyAccountPayments;

	@FindBy(linkText= "Payment")
	WebElementFacade MyAccountPreferences;

	@FindBy(linkText= "Payment")
	WebElementFacade SignOut;

	@FindBy(xpath= "//button[@class= 'styled-dropdown__selected-item-link styled-dropdown__selected-item-link--filter' and @data-sort-code= 'recommended']")
	WebElementFacade clickSortDropDown;

	@FindBy(xpath= "//input[@data-auid='search-form-input']") 
	WebElementFacade searchbar;

	@FindBy(xpath= "//button[@title='Submit Search']")
	WebElementFacade submitSearch;

	@FindBy(xpath= "//button[text()= 'Relevance']") 
	WebElementFacade sortByRelevance;

	@FindBy(xpath= "//button[text()= 'Price (Low to High)']") 
	WebElementFacade sortByPriceLowToHigh;

	@FindBy(xpath= "//button[text()= 'Price (High to Low)']") 
	WebElementFacade sortByPriceHighToLow;

	@FindBy(xpath= "//button[text()= 'A-Z (alphabetical)']") 
	WebElementFacade sortByAlphabetical;

	@FindBy(xpath= "//button[text()= 'Newest to Oldest Products']") 
	WebElementFacade sortByNewToOld;

	// id, name, xpath, css, 
	//System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
	//WebDriver driver = new ChromeDriver();


	JavascriptExecutor js = (JavascriptExecutor) getDriver();


	public void openApplication() {


		getDriver().get(init.getAppUrl());
		//	getDriver().navigate().to(init.getAppUrl());

		//	It will open the default url based on the env. mentioned in the serenity
		//open();

		waitABit(2000);
		getDriver().manage().window().maximize();
		report.LOG("User launches Loblaws url");
		waitABit(4000);
		if (clearCookiesPopUp.isCurrentlyVisible()) {
			clearCookies();	
			waitABit(2000);
		}

		if (signUpPopUp.isCurrentlyVisible()) {
			clearSignUpPopUp();	
			waitABit(2000);
		}

		System.out.println(getDriver().getCurrentUrl());
		System.out.println(getDriver().getTitle());



		//getpagesource of the url
		String p = getDriver().getPageSource();
		//	System.out.println(p);

		/*
		// to test longos url

					getDriver().get("https://www.longos.com/");
					waitABit(2000);
					getDriver().manage().window().maximize();
					report.LOG("User launches longos url");
					waitABit(4000);
					System.out.println(getDriver().getCurrentUrl());
					System.out.println(getDriver().getTitle());

				//	getDriver().findElement(By.xpath("//input[@aria-label= 'search-input']")).sendKeys("eggs");waitABit(2000);
				//	getDriver().findElement(By.xpath("//button[@type= 'button']")).click();



			getDriver().findElement(By.xpath("//button[text()=' my account ']")).click(); 
			getDriver().findElement(By.xpath("//button[text()=' create an account ']")).click();
			getDriver().findElement(By.id("email")).sendKeys("himanshu_rock1@yahoo.com");
			getDriver().findElement(By.id("firstName")).sendKeys("Ashua"); waitABit(1000);
			getDriver().findElement(By.id("last-name")).sendKeys("Sharmaa");waitABit(1000);
			getDriver().findElement(By.id("mobile")).sendKeys("4162710463");waitABit(1000);
			getDriver().findElement(By.id("postalCode")).sendKeys("L5M0Y3");waitABit(1000);
			getDriver().findElement(By.id("password")).sendKeys("Testing@001");waitABit(1000);
			getDriver().findElement(By.id("confirmPassword")).sendKeys("Testing@001");waitABit(3000);
			getDriver().findElement(By.id("tyrNoWishCheck")).click();waitABit(3000);
			getDriver().findElement(By.id("postalCode")).sendKeys("L5M0Y3");waitABit(3000);
			getDriver().findElement(By.xpath("//button[text()=' create account ']")).click();waitABit(5000);

		 */

	}

	public void clearSignUpPopUp() {
		signUpPopUp.click();
		report.LOG("Sign up popup is clicked");

	}

	public void clickSignIn() {
		SignInOnHome.click();
		report.LOG("User clicks on Sign In page");
		waitABit(4000);
	}

	public void clickMyAccount() {
		clickmyAccount.click();
		report.LOG("My account is clicked");
	}

	public void clearCookies() {
		clearCookiesPopUp.click();
		report.LOG("Clear cookies is clicked");
	}

	public void selectOptionsFromMyAccount(String option) {

		switch (option) {
		case "Orders":
			MyAccountOrders.click();
			report.LOG(option + " is clicked");
			break;
		case "Profile":
			MyAccountProfile.click();
			report.LOG(option + " is clicked");
			break;
		case "PC Optimum":
			MyAccountPcOptimum.click();
			report.LOG(option + " is clicked");
			break;
		case "Payment":
			MyAccountPayments.click();
			report.LOG(option + " is clicked");
			break;
		case "Preferences":
			MyAccountPreferences.click();
			report.LOG(option + " is clicked");
			break;
		case "Sign Out":
			SignOut.click();
			report.LOG(option + " is clicked");
			break;
		}
	}

	public void searchProduct(String product) {

		searchbar.clear();
		//searchbar.sendKeys(product);
		//submitSearch.click();
		searchbar.typeAndEnter(product);
		waitABit(2000);

		report.LOG("Product name is entered in the search bar");

	}


	public void validateSortDropdown() {

		//li[@class ='styled-dropdown__list-item']/button[text()='Price (High to Low)']

		//for relevance - //li[@class ='styled-dropdown__list-item styled-dropdown__list-item--selected']

		sortByDropdown();
		Assert.assertEquals("Relevance", sortByRelevance.getText());
		report.LOG( sortByRelevance.getText() +" verified successfully");
		Assert.assertEquals("Price (Low to High)", sortByPriceLowToHigh.getText());
		report.LOG( sortByPriceLowToHigh.getText() +" verified successfully");
		Assert.assertEquals("Price (High to Low)", sortByPriceHighToLow.getText());
		report.LOG( sortByPriceHighToLow.getText() +" verified successfully");
		Assert.assertEquals("A-Z (alphabetical)", sortByAlphabetical.getText());
		report.LOG( sortByAlphabetical.getText() +" verified successfully");
		Assert.assertEquals("Newest to Oldest Products", sortByNewToOld.getText());
		report.LOG( sortByNewToOld.getText() +" verified successfully");
	}

	public void selectSortByOption(String SortOption) {

		common.scrolldown();
		waitABit(2000);
		if (SortOption.equalsIgnoreCase(sortByRelevance.getText()))
		{
			sortByRelevance.click();
			report.LOG(sortByRelevance.getText() +" is selected");
		}
		else if (SortOption.equalsIgnoreCase("Price (Low to High)"))
		{
			sortByPriceLowToHigh.click();
			report.LOG("Price (Low to High) is selected");
		}
		else if (SortOption.equalsIgnoreCase("Price (High to Low)"))
		{
			sortByPriceHighToLow.click();
			report.LOG("Price (High to Low) is selected");
		}
		else if (SortOption.equalsIgnoreCase("A-Z (alphabetical)"))
		{
			sortByAlphabetical.click();
			report.LOG("A-Z (alphabetical) is selected");
		}
		else if (SortOption.equalsIgnoreCase("Newest to Oldest Products"))
		{	
			//js.executeScript("arguments[0].scrollIntoView();", sortByNewToOld);
			//	js.executeScript("window.scrollBy(0,350)", "");
			sortByNewToOld.click();
			report.LOG("Newest to Oldest Products is selected");
		}
		else report.LOG(SortOption + " is not a valid input");	
		waitABit(5000);

	}

	public void sortByDropdown() {
		clickSortDropDown.click();
		report.LOG("Sort by dropdown is clicked");
	}

}
