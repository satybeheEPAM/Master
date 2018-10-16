package StepDefinations;
import java.io.IOException;

import Runner.ClearTripLoginPage;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class ClearTripLoginStep{
	
	
	ClearTripLoginPage cl;
	
	public ClearTripLoginStep() throws IOException{
		cl= new ClearTripLoginPage();
	}

	@Given("^User has landed on login page of application$")
	public void user_has_landed_on_login_page_of_application() throws Throwable {
		cl. user_has_landed_on_login_page_of_application();
	}
	
	@Then("^User enters data on input page$")
	public void user_enters_data_on_input_page() throws Throwable {
		cl.user_enters_data_on_input_page();
	
	}
	
	@Then("^user enters Travellers details$")
	public void user_enters_Travellers_details() throws Throwable {
		cl.user_enters_Travellers_details();
		
	}

	@Then("^clicks continue$")
	public void clicks_continue() throws Throwable {
		cl.clicks_continue();
		
	}
	
	@After
    public void afterScenario(){
//		driver.quit();
    }
	
	
	

}
