package Runner;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import applicationpages.leartrip.BasePage;
import applicationpages.leartrip.DriverClass;

public class ClearTripVerificationPage extends BasePage{
	
	WebDriver driver;
	
	public ClearTripVerificationPage() throws IOException{
		DriverClass dc = new DriverClass();
		this.driver=dc.getDriver();
	}
	
	public void user_has_landed_on_verification_page_of_application() throws IOException {
		WebElement homePage = driver.findElement(By.xpath(getXpath("homePage", "ClearTripLoginStep")));
		verifyData(homePage, getTestData("TitleOfHomePage"));

	}
	

}
