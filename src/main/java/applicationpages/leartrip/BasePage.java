package applicationpages.leartrip;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {

	protected static Logger log = Logger.getLogger(BasePage.class);
	private static Map<String, String> excelData = new HashMap<>();
	List<String> key = new ArrayList<>();
	List<String> value = new ArrayList<>();
	private int returnDate;
	

	public String getPropValues(String name, String path) throws IOException {
		String result = "";
		InputStream inputStream = null;

		try {
			Properties prop = new Properties();
			String propFileName = path + ".properties";
			inputStream = BasePage.class.getClassLoader().getResourceAsStream(propFileName);

			prop.load(inputStream);
			result = prop.getProperty(name);

		} catch (Exception e) {
			log.error("Property file not found:", e);
		} finally {
			if (inputStream != null)
				inputStream.close();
		}
		return result;
	}

	public void setTestData(String key, String value) {
		excelData.put(key, value);
	}

	public static String getTestData(String key) {
		return excelData.get(key);
	}

	public void readExceldata() throws IOException {
		String fileName = "TestData.xlsx";
		String sheetName = "Sheet1";
		InputStream inputStream = BasePage.class.getClassLoader().getResourceAsStream("TestData.xlsx");
		Workbook guru99Workbook = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {
			guru99Workbook = new XSSFWorkbook(inputStream);

		} else if (fileExtensionName.equals(".xls")) {
			guru99Workbook = new HSSFWorkbook(inputStream);
		}

		Sheet guru99Sheet = guru99Workbook.getSheet(sheetName);
		int rowCount = guru99Sheet.getLastRowNum() - guru99Sheet.getFirstRowNum();

		for (int i = 0; i < rowCount + 1; i++) {
			Row row = guru99Sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				if (i == 0) {
					key.add(row.getCell(j).getStringCellValue());
				} else {
					if(row.getCell(j).getCellType()==CellType.NUMERIC) {
						value.add(String.valueOf(new java.text.DecimalFormat("0").format(row.getCell(j).getNumericCellValue())));
		        		}
		        		else
		        			value.add(row.getCell(j).getStringCellValue());
		        	} 
					
				}
			}
		
		for (int i = 0; i < key.size(); i++) {
			String keydata = key.get(i);
			String keyValue = value.get(i);
			setTestData(keydata, keyValue);
		}
		log.info(excelData);

	}

	public void verifyData(WebElement element, Object value) {
		log.info(element.getText().equals(value));
	}

	public String getXpath(String xpathName, String path) throws IOException {

		return getPropValues(xpathName, path);

	}

	public void selectFromDropwown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);

	}

	public void pickDatesfortheTrip(WebDriver driver) throws IOException {
		WebElement dateWidgetFrom = null;
		String departDate;
		departDate = getCurrentDay();
		String finalReturnDate=Integer.toString(returnDate);

		
		try {
			switch (getTestData("ModeOfCommunication")) {
			
			case "One way":
				driver.findElement(By.xpath(getXpath("selectDepartCalenderIcon", "ClearTripLoginStep"))).click();
				dateWidgetFrom = driver.findElement(By.xpath("//div[@class='monthBlock first']//tbody"));
				List<WebElement> oneWayDepartCloumns = dateWidgetFrom.findElements(By.tagName("td"));

				for (WebElement cell : oneWayDepartCloumns) {
					if (cell.getText().equals(departDate)) {
						cell.click();
						break;
					}
				}
				break;
				
			case "Round trip":
				driver.findElement(By.xpath(getXpath("selectDepartCalenderIcon", "ClearTripLoginStep"))).click();
				dateWidgetFrom = driver.findElement(By.xpath("//div[@class='monthBlock first']//tbody"));
				List<WebElement> roundTripDepartCloumns = dateWidgetFrom.findElements(By.tagName("td"));

				for (WebElement cell : roundTripDepartCloumns) {
					if (cell.getText().equals(departDate)) {
						cell.click();
						break;
					}
				}
				driver.findElement(By.xpath(getXpath("selectReturnCalenderIcon", "ClearTripLoginStep"))).click();
				dateWidgetFrom = driver.findElement(By.xpath("//div[@class='monthBlock last']//tbody"));
				List<WebElement> roundTripReturnCloumns = dateWidgetFrom.findElements(By.tagName("td"));

				for (WebElement cell : roundTripReturnCloumns) {
					if (cell.getText().equals(finalReturnDate)) {
						cell.click();
						break;
					}
				}
				break;
			default:
			}

		} catch (Exception e) {
			log.error("Exception in date picker: ", e);
		}
	}

	private String getCurrentDay() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
		 returnDate=todayInt+2;
		return Integer.toString(todayInt);
	}
	
	public void selectFromDropDown(WebElement element, String value)
	{
		Select select = new Select(element);
		select.selectByValue(value);
	}

}
