package Challenge.Selenium.MeriPustak;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Challenge.Selenium.TestCase.TC001_MeriPustak;

public class AddItemToCart extends TC001_MeriPustak {

	public static final Logger log = Logger.getLogger(MeriPustakPage.class.getName());

	WebDriver driver;

	@FindBy(xpath = "//a[contains(text(),'Engg & Tech')]")
	WebElement engTechButton;

	@FindBy(xpath = "//span[contains(text(),'Java ')]")
	WebElement javaBookSearchButton;

	@FindBy(xpath = "//b[contains(text(),'Computer Science and Information Technology Books')]")
	WebElement computerScienceITButton;

	@FindBy(xpath = "//input[@id='ContentPlaceHolder1_AddtoCart']")
	WebElement addToCartButton;

	@FindBy(id = "lblNoCartItem")
	WebElement cartValueLink;

	public AddItemToCart(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void AddBookTocart() throws Exception {
		Actions actions = new Actions(driver);

		actions.moveToElement(engTechButton).perform();

		computerScienceITButton.click();

		javaBookSearchButton.click();

		addToCartButton.click();

		String cartMessage = cartValueLink.getText();
		log.info("message is - " + cartMessage);
		Assert.assertEquals(cartMessage, "1 Item");
	}

}
