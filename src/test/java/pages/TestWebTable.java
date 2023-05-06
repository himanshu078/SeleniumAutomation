package pages;

import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import net.serenitybdd.core.pages.PageObject;

public class TestWebTable extends PageObject {


	public void WebTableTesting() {
		
		String path = System.getProperty("user.dir");
		System.out.println(path);
		String FilePath = path + "\\Data\\countries_with_link.html";
		System.out.println(FilePath);
		
	//	getDriver().get("file:///C:/Users/Swati/Desktop/Automation/countries_with_link.html");
		getDriver().get(FilePath);
		waitABit(2000);
		
		// td - column ; tr - row
				

		//get table heading
		WebElement TableHeading = getDriver().findElement(By.xpath("//h2"));
		System.out.println("Table Heading is " + TableHeading.getText());

		//count number of rows
		List <WebElement> noOfRows = getDriver().findElements(By.xpath("//tbody/tr"));
		System.out.println("noOfRows " + noOfRows.size());
		System.out.println();
		
		//get number of columns and title in header
		List <WebElement> noOfCols = getDriver().findElements(By.xpath("//thead/tr/th"));
		System.out.println("noOfCols " + noOfCols.size());
		System.out.println();
		
		
		System.out.println("getting the header details");
		// getting the header details - using //thead/tr/th
		for (int i=0; i<noOfCols.size(); i++) {
			System.out.println(noOfCols.get(i).getText());
		}
		
		System.out.println();
		System.out.println("Get each row data");
		
		//first row - //tbody/tr[1]/td
		// second row - //tbody/tr[2]/td
		List<WebElement> rowdata = getDriver().findElements(By.xpath("//tbody/tr[1]/td"));
		for (int i=0; i<noOfCols.size(); i++) {
			System.out.println(rowdata.get(i).getText());
		}
		
				// for Country - //tbody/tr/td[1]
				//for Capital - //tbody/tr/td[2]
				//for Currency - //tbody/tr/td[3]
				//for language - //tbody/tr/td[4]
				//for Wikipedia link -//tbody/tr/td[5]
		
		System.out.println();
		System.out.println("Get each column data");
		List<WebElement> coldata = getDriver().findElements(By.xpath("//tbody/tr/td[1]"));
		for (int i=0; i<noOfRows.size(); i++) {
			System.out.println(coldata.get(i).getText());
		}
		
		// if need to filter a field w.r.t first column
		 
		System.out.println();
		System.out.println("Getting currency for each country");
		System.out.println();
		
		for (int i=1; i<=noOfRows.size(); i++) {
			
			WebElement listOfCountries = getDriver().findElement(By.xpath("//tbody/tr/td[1]"));
			
			
			String country = listOfCountries.getText();
			System.out.println(country);
			
			if (country.equals("India")) {
				
				String beforecounter = "//tbody/tr[";
				String aftercounter = "]/td[3]";
				WebElement currency = getDriver().findElement(By.xpath(beforecounter + i+ aftercounter));
				System.out.println("currency is " + currency.getText());
				
				Assert.assertEquals("Indian Rupee", currency.getText());
				
				String beforecounterWiki = "//tbody/tr[";
				String aftercounterWiki = "]/td[5]/a";
				WebElement listOfWiki = getDriver().findElement(By.xpath(beforecounterWiki +i+ aftercounterWiki));
				listOfWiki.click();
				waitABit(3000);
				
				System.out.println("url is " + getDriver().getCurrentUrl());
				System.out.println("page name is  " + getDriver().getTitle());
				
				Assert.assertEquals("India - Wikipedia", getDriver().getTitle());
				break;
			}
		}
		
		getDriver().close();

	}

}
