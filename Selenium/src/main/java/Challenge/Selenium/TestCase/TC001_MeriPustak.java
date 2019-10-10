package Challenge.Selenium.TestCase;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Challenge.Selenium.Base.TestBase;
import Challenge.Selenium.MeriPustak.AddItemToCart;
import Challenge.Selenium.MeriPustak.MeriPustakPage;

public class TC001_MeriPustak extends TestBase {

	public static final Logger log = Logger.getLogger(TC001_MeriPustak.class.getName());
	
	MeriPustakPage meriPustakpage;
	AddItemToCart addItemToCart;

	@BeforeMethod
	public void setup() {
		init();
		selectBrowser("chrome");
		getUrl("https://www.meripustak.com/");
	}

	public TC001_MeriPustak() {
		// TODO Auto-generated constructor stub

	}

	@Test
	public void verifyMeriPustak() throws Exception {

		meriPustakpage = new MeriPustakPage(driver);
		meriPustakpage.logoSize();

		meriPustakpage.getTwitterHref();

		meriPustakpage.verifyShoppingCart();

		addItemToCart = new AddItemToCart(driver);
		addItemToCart.AddBookTocart();
	}

	@AfterTest
	public void endTest() {
		if (driver != null) {
			driver.quit();
		}

	}

}
