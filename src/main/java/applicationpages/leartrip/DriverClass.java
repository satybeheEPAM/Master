package applicationpages.leartrip;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverClass extends BasePage{
	protected static WebDriver driver;
	
	public WebDriver getDriver() throws IOException {
		if (driver == null) {
			readExceldata();
			try {
				if (getPropValues("Browser", "config").equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.gecko.driver",
							"D:\\software\\geckodriver-v0.23.0-win64\\geckodriver.exe");
					driver = new FirefoxDriver();
				} else if (getPropValues("Browser", "config").equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", "D:\\software\\chromedriver.exe");
					driver = new ChromeDriver();
				}
			} catch (IOException e) {
				log.error("Driver not found:", e);
			}
		}
		return driver;

	}

	
}
