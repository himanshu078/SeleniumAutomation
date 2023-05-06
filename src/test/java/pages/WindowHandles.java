package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.*;

import net.serenitybdd.core.pages.PageObject;

public class WindowHandles extends PageObject {
	
	HomePage home;
	
	public void testWindowHandles() {
		
		getDriver().get("https://www.hyrtutorials.com/p/window-handles-practice.html");
		getDriver().manage().window().maximize();
		waitABit(4000);
		
		//for new windows
	//	getDriver().findElement(By.id("newWindowsBtn")).click();
		
		//for new tabs
		getDriver().findElement(By.id("newTabsBtn")).click();
		
		Set<String> allWindowHandles = getDriver().getWindowHandles();
		System.out.println(allWindowHandles.size());
		
		for (String handle : allWindowHandles) {
			System.out.println(handle);	
		}
			
		Iterator<String> it = allWindowHandles.iterator();
		
		String parentWindow = it.next();
		System.out.println("Parent window " + parentWindow);
		
		String childWindow = it.next();
		System.out.println("Child window " + childWindow);
		getDriver().switchTo().window(childWindow);
		waitABit(4000);
		System.out.println("TitleOfChildWindow " + getDriver().getTitle());
		getDriver().close();
		
		String secondChildWindow = it.next();
		System.out.println("Second Child window " + secondChildWindow);
		getDriver().switchTo().window(secondChildWindow);
		waitABit(4000);
		System.out.println("TitleOfSecondChildWindow " + getDriver().getTitle());
		getDriver().close();
		
		getDriver().switchTo().window(parentWindow);
		waitABit(4000);
		System.out.println("TitleOfparentWindow" + getDriver().getTitle());
		getDriver().quit();
		
	}
	
	public void testWindowHandlesInLowblaws() {
		
		
		home.openApplication();
		waitABit(4000);
		
		Actions actions = new Actions(getDriver());

		// to hover on category
		WebElement categoryName = getDriver().findElement(By.xpath("//span[text()='Flyers & Deals']"));
		actions.moveToElement(categoryName).perform();
		
		// to hover on Subcategory
		WebElement subCategoryName = getDriver().findElement(By.xpath("//span[text()='Digital Coupons']"));
		actions.moveToElement(subCategoryName).perform();
		
		subCategoryName.click();
		waitABit(5000);
		
//		String parentWindowId = getDriver().getWindowHandle();
//		String TitlebeforeExecution = getDriver().getTitle();
//		
//		Set<String> allWindowHandles = getDriver().getWindowHandles();
//		System.out.println(allWindowHandles.size());
//		
//		for (String handle : allWindowHandles) {
//			System.out.println(handle);	
//			
//			if (!handle.equals(parentWindowId)) {
//				getDriver().switchTo().window(handle);
//				String text = getDriver().findElement(By.xpath("//h1[@class='type-ds-title-1 loadToCard-header__title']")).getText();
//				System.out.println(text);
//				System.out.println("Child window Title - " + getDriver().getTitle());
//				System.out.println("Child window url - " + getDriver().getCurrentUrl());
//				waitABit(4000);
//				getDriver().close();
//			}
//		}
//		
//		getDriver().switchTo().window(parentWindowId);
//		
//		String TitleAfterExecution = getDriver().getTitle();
//		System.out.println("TitleAfterExecution " + TitleAfterExecution);
//		System.out.println(getDriver().getCurrentUrl());
//			
//		waitABit(4000);
//		
//		getDriver().quit();
//		
//		Assert.assertEquals(TitlebeforeExecution, TitleAfterExecution);
		
		
		
		Set<String> allHandles = getDriver().getWindowHandles();
		System.out.println(allHandles.size());
		
		
		Iterator<String> it = allHandles.iterator();
		
		String parentWindow = it.next();
		System.out.println("Parent window " + parentWindow);
		
		String childWindow = it.next();
		System.out.println("Parent window " + childWindow);
		getDriver().switchTo().window(childWindow);
		waitABit(4000);
		System.out.println("TitleOfChildWindow " + getDriver().getTitle());
		getDriver().close();
		
		getDriver().switchTo().window(parentWindow);
		waitABit(4000);
		System.out.println("TitleOfparentWindow" + getDriver().getTitle());
		getDriver().quit();
		
		
	}

}
