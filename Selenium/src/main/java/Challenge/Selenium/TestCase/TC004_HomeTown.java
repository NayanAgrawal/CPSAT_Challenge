package Challenge.Selenium.TestCase;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.aventstack.extentreports.ExtentTest;

import Challenge.Selenium.Base.TestBase;
import Challenge.Selenium.HomeTown.HomeTownPage;

public class TC004_HomeTown extends TestBase {

	public static final Logger log = Logger.getLogger(TC001_MeriPustak.class.getName());
	public static ExtentTest child;

	HomeTownPage homeTownPage;

	@Before
	public void setup() {
		init();
		selectBrowser("firefox");
		getUrl("https://www.hometown.in/");
	}

	public TC004_HomeTown() {
		// TODO Auto-generated constructor stub

	}

	@Test
	public void verifyHomeTown() throws Exception {
		homeTownPage = new HomeTownPage(driver);
		homeTownPage.homeTownTab();

	}

	@After
	public void endTest() {
		if (driver != null) {
			driver.quit();
		}

	}

}
