package Runner;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import applicationpages.leartrip.BasePage;
import applicationpages.leartrip.DriverClass;

public class ClearTripLoginPage extends BasePage {
	WebDriver driver;
	
	public ClearTripLoginPage() throws IOException{
		DriverClass dc = new DriverClass();
		this.driver = dc.getDriver();
	}

	public void modeOfCommunication() throws IOException {
		switch (getTestData("ModeOfCommunication")) {
		case "One way":
			WebElement oneWayCommunication = driver
					.findElement(By.xpath(getXpath("oneWayCommunication", "ClearTripLoginStep")));
			oneWayCommunication.click();
			break;
		case "Round trip":
			WebElement roundTripCommunication = driver
					.findElement(By.xpath(getXpath("roundTripCommunication", "ClearTripLoginStep")));
			roundTripCommunication.click();
			break;
		case "Multi-city":
			WebElement multiCityCommunication = driver
					.findElement(By.xpath(getXpath("multiCityCommunication", "ClearTripLoginStep")));
			multiCityCommunication.click();
			break;
		default:

		}

	}

	public void user_has_landed_on_login_page_of_application() throws IOException {
		driver.navigate().to("https://www.cleartrip.com/");
		driver.manage().window().maximize();
		WebElement homePage = driver.findElement(By.xpath(getXpath("homePage", "ClearTripLoginStep")));
		verifyData(homePage, getTestData("TitleOfHomePage"));

	}
	
	

	public void user_enters_data_on_input_page() throws Throwable {
		try {
			modeOfCommunication();
			WebElement travelFrom = driver.findElement(By.xpath(getXpath("travelFrom", "ClearTripLoginStep")));
			travelFrom.sendKeys(getTestData("TravelFrom"));
			WebElement travelTo = driver.findElement(By.xpath(getXpath("travelTo", "ClearTripLoginStep")));
			travelTo.sendKeys(getTestData("TravelTo"));
			pickDatesfortheTrip(driver);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void user_enters_Travellers_details() throws Throwable {
		
		try{
			WebElement numberOfAdults = driver.findElement(By.xpath(getXpath("numberOfAdults", "ClearTripLoginStep")));
			selectFromDropDown(numberOfAdults,getTestData("NumberOfAdults"));
			
			WebElement numberOfChildrens = driver.findElement(By.xpath(getXpath("numberOfChildrens", "ClearTripLoginStep")));
			selectFromDropDown(numberOfChildrens,getTestData("NumberOfChildrens"));
			
			WebElement numberOfInfants = driver.findElement(By.xpath(getXpath("numberOfInfants", "ClearTripLoginStep")));
			selectFromDropDown(numberOfInfants,getTestData("NumberOfInfants"));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clicks_continue() throws Throwable {
		WebElement searchButton = driver.findElement(By.xpath(getXpath("searchButton", "ClearTripLoginStep")));
		searchButton.click();
	}

}
