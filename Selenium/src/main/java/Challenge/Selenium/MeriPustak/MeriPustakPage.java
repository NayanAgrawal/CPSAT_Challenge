package Challenge.Selenium.MeriPustak;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Challenge.Selenium.Base.TestBase;

public class MeriPustakPage extends TestBase {

	public static final Logger log = Logger.getLogger(MeriPustakPage.class.getName());

	WebDriver driver;

	@FindBy(xpath = "//a[@id='mpstkLogo']//img[@class='img-responsive']")
	WebElement logoImage;

	@FindBy(xpath = "//i[@class='fa fa-twitter sky_blue']")
	WebElement twitterButton;

	@FindBy(id = "lblNoCartItem")
	WebElement cartValueLink;

	@FindBy(xpath = "//a[contains(text(),'Shopping Cart')]")
	WebElement shoppingCartButton;

	@FindBy(xpath = "//table[@class='table_hed']")
	WebElement verifyShoppingCartPage;

	@FindBy(xpath = "//table[@id='ContentPlaceHolder1_gvCartTable']")
	WebElement verifyShoppingCartNoItem;

	public MeriPustakPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void logoSize() throws Exception {

		verifyMeriPustakPage();

		int ImageWidth = logoImage.getSize().width;
		int ImageHeight = logoImage.getSize().height;

		log.info("Logo Image Width is - " + ImageWidth + " pixels");
		log.info("Logo Image Height is - " + ImageHeight + " pixels");

	}

	public void verifyMeriPustakPage() {
		if (logoImage.isDisplayed()) {
			log.info("Successfully verified Meri Pustak Home Page");
		} else {
			log.info("Failed to verify Meri Pustak Home Page");
		}
	}

	public void getTwitterHref() {

		List<WebElement> allLinks = driver.findElements(By.tagName("a"));

		for (WebElement link : allLinks) {

			if ((link.getAttribute("href") + "").contains("twitter")) {
				log.info("Twitter Href is - " + link.getAttribute("href"));
			}
		}
	}

	//verify Shopping Cart
	public void verifyShoppingCart() {
		log.info(cartValueLink.getText());

		if (cartValueLink.getText().equals("0 Item")) {
			shoppingCartButton.click();
			verifyShoppingCartPage();
		}

		String cartMessage = verifyShoppingCartNoItem.getText();
		log.info("Cart Value - " + cartMessage);
		Assert.assertEquals(cartMessage, "No Item is Added In Cart yet.Cart is Empty!!!");

	}

	//verify Shopping Cart Page availability
	public void verifyShoppingCartPage() {
		if (verifyShoppingCartPage.isDisplayed()) {
			log.info("Successfully verified Shopping Cart Page");
		} else {
			log.info("Failed to verify Shopping Cart Page");
			try {
				getScreenshot(driver, "HomeTownverificationFailed");
			} catch (IOException e) {
				log.info("Failed to get screenshot");
			}
		}
	}

}
