package StepDefinations;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import Runner.ClearTripVerificationPage;
import cucumber.api.java.en.Then;

public class ClearTripVerificationStep{
	
	ClearTripVerificationPage cv;
	
	public ClearTripVerificationStep() throws IOException {
		cv=new ClearTripVerificationPage();
		
	}
	
	@Then("^verify page title in verification Page$")
	public void verify_page_title_in_verification_Page() throws Throwable {
		cv.user_has_landed_on_verification_page_of_application();
	}
	

}
