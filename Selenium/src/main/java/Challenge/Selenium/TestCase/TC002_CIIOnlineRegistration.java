package Challenge.Selenium.TestCase;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Challenge.Selenium.Base.TestBase;
import Challenge.Selenium.CIIOnlineRegistration.CIIOnlineRegistrationForm;

public class TC002_CIIOnlineRegistration extends TestBase {

	public static final Logger log = Logger.getLogger(TC002_CIIOnlineRegistration.class.getName());
	CIIOnlineRegistrationForm cIIOnlineRegistrationForm;

	
	@Before
	public void setup() {
		init();
		selectBrowser("firefox");
		getUrl("https://www.cii.in/OnlineRegistration.aspx");
	}

	public TC002_CIIOnlineRegistration() {
		// TODO Auto-generated constructor stub

	}

	@Test
	public void verifyCIIOnlineTegistration() throws Exception {

		cIIOnlineRegistrationForm = new CIIOnlineRegistrationForm(driver);
		cIIOnlineRegistrationForm.ciiRegistrationTab();

	}

	@After
	public void endTest() {
		if (driver != null) {
			driver.quit();
		}

	}

}
