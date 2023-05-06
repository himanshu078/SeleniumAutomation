package pages;


import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Steps;
import utils.CommonMethods;
import utils.ReportLog;

public class Product extends PageObject {

	@Steps
	ReportLog report;
	@Steps
	HomePage home;
	@Steps
	CommonMethods common;


	@FindBy(xpath = "//button[@aria-label='pickup']")
	WebElementFacade pickUpButton;

	@FindBy(xpath = "//button[text()='Yes']")
	WebElementFacade confirmLocation;

	@FindBy(xpath ="//span[@class = 'cart-item-count__text']")
	WebElementFacade noOfItemsInCart;		

	List <WebElementFacade> productDetails;
	
	String filterName;
	String filterOption;

	JavascriptExecutor js = ((JavascriptExecutor)getDriver());
	

	public List<WebElementFacade> getListOfProducts() {

		//	product list -  //span[@class= 'product-name product-name--product-tile']
		//	product brand list -  //span[@class= 'product-name product-name--product-tile']/span[@class= 'product-name__item product-name__item--brand']
		//	product name list -  //span[@class= 'product-name product-name--product-tile']/span[@class= 'product-name__item product-name__item--name']
		//	product quantity list -  //span[@class= 'product-name product-name--product-tile']/span[@class= 'product-name__item product-name__item--package-size']

		String productListXpath = "//span[@class= 'product-name product-name--product-tile']/span[@class= 'product-name__item product-name__item--name']";
		productDetails = findAll(By.xpath(productListXpath));
		System.out.println("Total products are  " + productDetails.size());
		for (int i=0; i<productDetails.size(); i++) {
			System.out.println(productDetails.get(i).getText());	
		}
		return productDetails;
	}



	public void AddToCart(String expectedProduct) {

		List<WebElementFacade> listOfProductName = getListOfProducts();
		String beforeXpath  = "//button[@aria-label= 'Add to cart, ";
		String afterXpath = "']";
		String actualProduct;

		for (int i=0; i<listOfProductName.size(); i++) {

			actualProduct= listOfProductName.get(i).getText();

			if (expectedProduct.equalsIgnoreCase(actualProduct)) {

				Assert.assertEquals(expectedProduct,actualProduct);
				waitABit(1000);
				getDriver().findElement(By.xpath(beforeXpath + actualProduct + afterXpath)).click();
				String xpath = 	getDriver().findElement(By.xpath(beforeXpath + actualProduct + afterXpath)).getText();
				System.out.println(xpath);
				report.LOG(expectedProduct + " product found");
				System.out.println(expectedProduct + " product found");
				break;
			} else report.LOG("Requested product not found");		
		}

		
		if (pickUpButton.isDisplayed()) {
			pickUpButton.click();
			System.out.println("pickup clicked");
			waitABit(2000);
		}

		if (confirmLocation.isDisplayed()) {
			confirmLocation.click();
			System.out.println("confirm Location clicked");
			waitABit(2000);
		}

;
		Assert.assertEquals("1", noOfItemsInCart.getText());
		report.LOG("No. of product in cart is " + noOfItemsInCart.getText());
	}
	
	public void getTypeAheaddata() {
		
		
		home.searchbar.clear();
		home.searchbar.sendKeys("Butter");
		List<WebElementFacade> listInTypeAhead = findAll(By.xpath("//span[@class='typeahead-suggestion-list-item__suggestion__content']"));
		
		for (int i=0; i<listInTypeAhead.size(); i++) {
			System.out.println(listInTypeAhead.get(i).getText());	
		}
	}
	
	//Click on dropdown - Ailse, brand, price etc.
	public void searchFilter(String filter) {
		
		filterName = filter;
		
		
		String beforeFilterName = "//button[text()='";
		String afterFilterName = "']";
		
		String fullxpath = beforeFilterName + filterName + afterFilterName;
		WebElement wele = getDriver().findElement(By.xpath(fullxpath));
		
		// check the status of dropdown, whether it is collapsed or expanded
		String beforeClick = getDriver().findElement(By.xpath(fullxpath)).getAttribute("data-cruller");
		System.out.println("status of dropdown for " + filterName + " is " + beforeClick);
		String afterClick = null;
		
		// if collapsed, then expand it
		if (beforeClick.contains("collapsed")) {
		
			wele.click();

			 afterClick= getDriver().findElement(By.xpath(fullxpath)).getAttribute("data-cruller");
			System.out.println("status of dropdown for " + filterName + " is " + afterClick);
		}
		else {
			wele.click();
			System.out.println("status of dropdown for " + filterName + " is " + afterClick);
		}
		
		waitABit(4000);	
	}
	
	
	//Click on options in filter like for Deals - multibuy/price reduction
	public void selectFilterOption(String option) {
		
		filterOption=option;
		
		//input[@name='Multi-Buy']
		
//		String beforeFilterName = "//input[@name='";
//		String afterFilterName = "']";
//		
//		String fullxpath = beforeFilterName + filterOption + afterFilterName;
//		System.out.println(fullxpath);
//		
//		//get the status of checkbox to see if it is already checked or not
//		String beforeClick = getDriver().findElement(By.name(filterOption)).getAttribute("value");
//		System.out.println("status of checkbox for " + filterOption + " is " + beforeClick);
//		
//		//if unchecked, then click on it
//		if (beforeClick.equals("false")) {
//			getDriver().findElement(By.name(filterOption)).click();
//			String afterClick = getDriver().findElement(By.name(filterOption)).getAttribute("value");
//			System.out.println("status of checkbox for " + filterOption + " is " + afterClick);
//		}
//		
//		waitABit(3000);
		
		// contains in xpath - used for Dynamic id
	//	tagname[contains(@attribute,value)]
//		tagname[contains(text(),'Enter Text')]
//		tagname[starts-with(@attribute,value)]
//		tagname[starts-with(@attribute,value)]
		
		//to get list of all the options in a dropdown
		List <WebElement> list = getDriver().findElements(By.xpath("//ul[contains(@aria-label,'Search for product Deals')]//li//label"));
		
		System.out.println(list.size());
		
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).getText());
			
			if (list.get(i).getText().contains(filterOption)) {
				list.get(i).click();
				System.out.println("status of dropdown for " + filterOption + " is " + getDriver().findElement(By.name(filterOption)).getAttribute("value"));
				break;
			}
		}
	}
	
	public void searchableDropdownforBrands(String search) {
		
		
		String searchBarForBrand = "//input[@placeholder='Search for brands']";
		
		WebElement searchbar = getDriver().findElement(By.xpath(searchBarForBrand));
		searchbar.clear();
		searchbar.sendKeys(search);
		waitABit(2000);
		
	//	WebElement Showall = getDriver().findElement(By.xpath("//button[text()='Show all brands']"));
		
//		if (getDriver().findElement(By.xpath("//button[text()='Show all brands']")).isDisplayed()) {
//			getDriver().findElement(By.xpath("//button[text()='Show all brands']")).click();
//			System.out.println("Show all is clicked");
//		}
		
		//to get list of all the results after searching the brands
		List <WebElement> list = getDriver().findElements(By.xpath("//ul[@aria-label='Search for product Brand']//li//label"));
		
		System.out.println(list.size());
		
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).getText());
			
			if (list.get(i).getText().equals(search)) {
				list.get(i).click();
				System.out.println("status of checkbox for is " + getDriver().findElement(By.name(search)).getAttribute("value"));
				break;
			}
		}
		
		waitABit(3000);
		System.out.println(getDriver().getCurrentUrl());
		System.out.println(getDriver().getTitle());
		
	}



}
