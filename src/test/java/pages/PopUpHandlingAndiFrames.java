package pages;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.*;

import net.serenitybdd.core.pages.PageObject;
import utils.CommonMethods;
import utils.ReportLog;

public class PopUpHandlingAndiFrames extends PageObject {
	
	ReportLog report;
	CommonMethods common;
	
	
public void testJavaScriptAlerts() {
		
		getDriver().get("https://www.rediff.com/"); waitABit(3000);
		getDriver().manage().window().maximize();
		getDriver().findElement(By.className("signin")).click();waitABit(3000);
		getDriver().findElement(By.name("proceed")).click();
		report.LOG("clicked on login and alert is displayed");
		
		Alert alert = getDriver().switchTo().alert(); ;
		waitABit(3000);
		
		String alertmessage = alert.getText();
		System.out.println(alertmessage);
		// to accept the alert
		alert.accept();
		// to cancel alert
		//alert.dismiss();
		
		//to enter data in the alert
	//	alert.sendKeys("text to enter");
	//	alert.accept();
		
		Assert.assertEquals("Please enter a valid user name", alertmessage);
		report.LOG("login alert is accepted");
		System.out.println("login alert is accepted");
		
		
		getDriver().findElement(By.id("login1")).sendKeys("Test12344");waitABit(3000);
		getDriver().findElement(By.name("proceed")).click();waitABit(3000);
		System.out.println("-----------------------------------------");
		String alertmessage2 = alert.getText();
		System.out.println(alertmessage2);
		alert.accept();
		
		Assert.assertEquals("Please enter your password", alertmessage2);
		report.LOG("password alert is accepted");
		System.out.println("password alert is accepted");
		
	}
		

	public void testDesktopPopUp(String fileName) {
		
		String path = System.getProperty("user.dir");
		String filepath = path +"\\Data\\";
		String fullFilePath  = filepath + fileName;
		System.out.println(fullFilePath);
		
		getDriver().get("https://html.com/input-type-file/");
		getDriver().manage().window().maximize();
		waitABit(3000);
		common.scrolldown();
		common.scrolldown();
		
		waitABit(3000);
		getDriver().findElement(By.id("fileupload")).sendKeys(fullFilePath);
		waitABit(5000);
		report.LOG("uploaded " + fileName);
		System.out.println("uploaded " + fileName);
		
		getDriver().findElement(By.xpath("//input[@type='submit']")).click();
		report.LOG("Submit is clicked");
		System.out.println("Submit is clicked");
		
		common.closebrowser();
		
	}
	
	
	public void testiFrames() {
		
		
		getDriver().get("https://demoqa.com/frames");
		getDriver().manage().window().maximize();
		waitABit(3000);
		
		List<WebElement> iframeElements = getDriver().findElements(By.tagName("iframe"));
		System.out.println("The total number of iframes are " + iframeElements.size());
		
		// by using index of frame
		//getDriver().switchTo().frame(0);
		//by using name/id of the frame
		getDriver().switchTo().frame("frame1");
		System.out.println("Switched to frame");
		
		WebElement frame1Heading= getDriver().findElement(By.id("sampleHeading"));
        
        //Finding the text of the heading
        String frame1Text=frame1Heading.getText(); // output - This is a sample page
        
        //Print the heading text
        System.out.println(frame1Text);
        
        getDriver().switchTo().defaultContent();
        waitABit(3000);
		
        
        WebElement mainpage= getDriver().findElement(By.xpath("//div[@id='framesWrapper']/div"));
        System.out.println(mainpage.getText());
		
	}

}
