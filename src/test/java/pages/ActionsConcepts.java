package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.ReportLog;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Steps;

public class ActionsConcepts extends PageObject{

	@Steps
	ReportLog report;

	int noOfSubCategories;
	int noOfItemsInSubCategory;


	public void simpleTestActions() {

		Actions actions = new Actions(getDriver());

		// to hover on category
		WebElement categoryName = getDriver().findElement(By.xpath("//span[text()='Grocery']"));
		actions.moveToElement(categoryName).perform();
		//	actions.moveToElement(categoryName).build().perform();


		// to hover on Subcategory
		WebElement subCategoryName = getDriver().findElement(By.xpath("//span[text()='International Foods']"));
		actions.moveToElement(subCategoryName).perform();

		// click on sub category
		subCategoryName.click();
		waitABit(5000);

		// navigated to the new page
		System.out.println(getDriver().getCurrentUrl());
		System.out.println(getDriver().getTitle());

		Assert.assertEquals("International Foods | Loblaws", getDriver().getTitle());

		// clicking on the item on the new page
		getDriver().findElement(By.xpath("//span[text()='east asian foods']")).click();

		// navigated to a diff page
		System.out.println(getDriver().getCurrentUrl());
		System.out.println(getDriver().getTitle());	


		Assert.assertTrue("Title contains East-Asian-Foods ", getDriver().getCurrentUrl().contains("East-Asian-Foods"));

	}


	public void testActions(String category, String subCategory) {

		Actions actions = new Actions(getDriver());

		String beforeCategoryXpath = "//span[text()='";
		String afterCategoryXpath = "']";

		// to hover on category
		WebElement categoryName = getDriver().findElement(By.xpath(beforeCategoryXpath+category+afterCategoryXpath));
		//	WebElement categoryName = getDriver().findElement(By.xpath("//span[text()='Grocery']"));
		actions.moveToElement(categoryName).perform();
		//	actions.moveToElement(categoryName).build().perform();


		// to hover on Subcategory
		String beforeSubCategoryXpath = "//span[text()='";
		String afterSubCategoryXpath = "']";
		//	WebElement subCategoryName = getDriver().findElement(By.xpath("//span[text()='International Foods']"));
		WebElement subCategoryName = getDriver().findElement(By.xpath(beforeSubCategoryXpath + subCategory + afterSubCategoryXpath));
		actions.moveToElement(subCategoryName).perform();

		// click on sub category
		subCategoryName.click();
		waitABit(5000);

		// navigated to the new page
		System.out.println(getDriver().getCurrentUrl());
		System.out.println(getDriver().getTitle());

		Assert.assertEquals("International Foods | Loblaws", getDriver().getTitle());

		// clicking on the item on the new page
		getDriver().findElement(By.xpath("//span[text()='east asian foods']")).click();

		// navigated to a diff page
		System.out.println(getDriver().getCurrentUrl());
		System.out.println(getDriver().getTitle());	


		Assert.assertTrue("Title contains East-Asian-Foods ", getDriver().getCurrentUrl().contains("East-Asian-Foods"));

	}


	public int listOfItems(String subCategory) {

		Actions actions = new Actions(getDriver());

		report.LOG("Sub Category Entered is " + subCategory);
		// to hover on subcategory
		String beforeSubCategoryXpath = "//span[text()='";
		String afterSubCategoryXpath = "']";
		//	WebElement subCategoryName = getDriver().findElement(By.xpath("//span[text()='International Foods']"));
		WebElement subCategoryName = getDriver().findElement(By.xpath(beforeSubCategoryXpath + subCategory + afterSubCategoryXpath));
		actions.moveToElement(subCategoryName).perform();

		String beforeSubCategoryItemListXpath = "//span[text()='";
		String afterSubCategoryItemListXpath = "']//ancestor::a//following-sibling::ul[@class='primary-nav__list primary-nav__list--level-2']//li[@class='primary-nav__list__item']";

		//	String SubCategoryItemList = "//span[text()='International Foods']//ancestor::a//following-sibling::ul[@class='primary-nav__list primary-nav__list--level-2']//li[@class='primary-nav__list__item']";
		String SubCategoryItemList = beforeSubCategoryItemListXpath + subCategory+ afterSubCategoryItemListXpath;

		// to get list of items under a subcategory
		List<WebElementFacade> listInSubCategory = findAll(By.xpath(SubCategoryItemList));

		noOfItemsInSubCategory = listInSubCategory.size();
		System.out.println(listInSubCategory.size());

		for (int i=0; i<listInSubCategory.size(); i++) {
			System.out.println(listInSubCategory.get(i).getText());	

		}

		return noOfItemsInSubCategory;

	}

	public int listOfSubCategories(String category) {

		Actions actions = new Actions(getDriver());

		String beforeCategoryXpath = "//span[text()='";
		String afterCategoryXpath = "']";

		// to hover subcategory
		WebElement categoryName = getDriver().findElement(By.xpath(beforeCategoryXpath+category+afterCategoryXpath));
		//	WebElement categoryName = getDriver().findElement(By.xpath("//span[text()='Grocery']"));
		actions.moveToElement(categoryName).perform();
		System.out.println("-------Getting details of menu------------------");

		report.LOG("Category Entered is " + category);

		String beforeSubCategoryListXpath = "//span[text()='";
		String afterSubCategoryListXpath = "']//ancestor::button//following-sibling::ul[@class = 'primary-nav__list primary-nav__list--level-1']/li/a/span";

		//String xpathToGetSubCategories = "//span[text()='Grocery']//ancestor::button//following-sibling::ul[@class = 'primary-nav__list primary-nav__list--level-1']/li/a/span";
		String xpathToGetSubCategories = beforeSubCategoryListXpath + category + afterSubCategoryListXpath;


		// to get list of subcategories under a category
		List<WebElementFacade> listInCategory = findAll(By.xpath(xpathToGetSubCategories));

		noOfSubCategories = listInCategory.size();
		System.out.println(listInCategory.size());

		for (int i=0; i<listInCategory.size(); i++) {
			System.out.println(listInCategory.get(i).getText());	
		}
		return noOfSubCategories;

	}


	public void testDragAndDrop(){

		getDriver().get("https://jqueryui.com/droppable/");
		getDriver().manage().window().maximize();
		waitABit(8000);

		getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		// only to switch to frame if it is present on the page
		getDriver().switchTo().frame(0);

		WebElement sourceElement = getDriver().findElement(By.id("draggable"));
		WebElement destinationElement = getDriver().findElement(By.id("droppable"));

		//	WebElement sourceElement = getDriver().findElement(By.xpath("//*[@id='draggable']"));
		//	WebElement destinationElement = getDriver().findElement(By.xpath("//*[@id='droppable']"));

		Actions action = new Actions(getDriver());
		action.dragAndDrop(sourceElement, destinationElement).build().perform();
		//	action.dragAndDropBy(sourceElement, 580,195).build().perform();
		waitABit(3000);


		String finalText = getDriver().findElement(By.xpath("//div[@id='droppable']/p")).getText();

		System.out.println(finalText);
		//	Assert.assertEquals("Dropped!", finalText);


	}

	public void testRightClickOperation(){

		getDriver().get("https://jqueryui.com/droppable/");
		getDriver().manage().window().maximize();
		waitABit(4000);
		Actions action = new Actions(getDriver());
		WebElement elementToBeClicked = getDriver().findElement(By.linkText("API Documentation"));
		action.contextClick(elementToBeClicked).sendKeys(Keys.CONTROL).build().perform();
		
		
		/*
        // Switch to the newly opened tab
		getDriver().switchTo().window(getDriver().getWindowHandles().toArray()[1]);

        // Do something on the newly opened tab

        // Close the newly opened tab
        getDriver().close();
        // Switch back to the original tab
        getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[0]);
        // Close the browser;
        getDriver().quit();
        
        */
    }



	public void noOfItemsInSubCategory(int ExpectedNumberOfItemsInSubCategories) {
		Assert.assertEquals(ExpectedNumberOfItemsInSubCategories,noOfItemsInSubCategory);
	}

	public void noOfCategories(int ExpectedNumberOfSubCategories) {
		Assert.assertEquals(ExpectedNumberOfSubCategories,noOfSubCategories);

	}




}
