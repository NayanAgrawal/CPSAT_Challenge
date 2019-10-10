package Challenge.Selenium.TestCase;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Challenge.Selenium.Base.TestBase;
import Challenge.Selenium.PremierLeague.PremierLeaguePage;

public class TC003_PremierLeague extends TestBase {

	public static final Logger log = Logger.getLogger(TC003_PremierLeague.class.getName());
	
	PremierLeaguePage premierLeaguePage;

	@Before
	public void setup() {
		init();
		selectBrowser("firefox");
		getUrl("https://www.premierleague.com");
	}

	public TC003_PremierLeague() {
		// TODO Auto-generated constructor stub

	}

	@Test
	public void verifyPremierLeague() throws Exception {
		premierLeaguePage = new PremierLeaguePage(driver);
		premierLeaguePage.premierLeagueTab();

	}

	@After
	public void endTest() {
		if (driver != null) {
			driver.quit();
		}

	}

}
