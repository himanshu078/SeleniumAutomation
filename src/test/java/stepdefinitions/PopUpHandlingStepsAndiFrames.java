package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.PopUpHandlingAndiFrames;
import net.thucydides.core.annotations.Steps;

public class PopUpHandlingStepsAndiFrames {

	@Steps
	PopUpHandlingAndiFrames popup;


	@Given("User tests Javascript popup")
	public void user_tests_javascript_popup() {

		//	Web/Browser based alerts are primarily called Javascript alerts and are those alerts that are browser dependant. 
		//	These alerts are majorly called Popups.
		popup.testJavaScriptAlerts();


	}


	@When("User tests windows popup for {string}")
	public void user_tests_windows_popup_for(String fileName) {
		popup.testDesktopPopUp(fileName);

	}
	
	@Given("User tests iframes on the page")
	public void user_tests_iframes_on_the_page() {
		popup.testiFrames();
	    
	}

}
