package Challenge.Selenium.TestCase;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import Challenge.Selenium.Base.TestBase;
import Challenge.Selenium.ExcelReadWrite.ExcelRead;
import Challenge.Selenium.WoodLand.WoodLandPage;

public class TC005_WoodLand extends TestBase {

	public static final Logger log = Logger.getLogger(TC001_MeriPustak.class.getName());
	public static ExtentTest child;
	ExcelRead reader;
	WoodLandPage woodLandPage;

	@BeforeMethod
	public void setup() throws Exception {
		init();
		selectBrowser("chrome");
		getUrl(" https://www.woodlandworldwide.com/");

	}

	public TC005_WoodLand() {
		// TODO Auto-generated constructor stub

	}

	@Test(priority = 1, enabled = false)
	public void verifyWoodLand() throws Exception {
		

	}

	@Test(dataProvider = "testdata", priority = 2, enabled = true)
	public void TestFireFox(String productName) throws InterruptedException {
		
		woodLandPage = new WoodLandPage(driver);
		woodLandPage.woodLandPageTab(productName);
		log.info("productName - " + productName);
	}

	@DataProvider(name = "testdata")
	public Object[][] TestDataFeed() throws Exception {

		reader = new ExcelRead();
		Object[][] data = ExcelRead.readExcel("woodLandProductData.xlsx", "Data");
		return data;

	}

	@AfterMethod
	public void endTest() {
		if (driver != null) {
			driver.quit();
		}

	}

}
