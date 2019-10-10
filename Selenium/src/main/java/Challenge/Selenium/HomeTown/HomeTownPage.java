package Challenge.Selenium.HomeTown;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

import Challenge.Selenium.MeriPustak.MeriPustakPage;

public class HomeTownPage {

	public static final Logger log = Logger.getLogger(MeriPustakPage.class.getName());

	WebDriver driver;
	JavascriptExecutor executor;

	@FindBy(xpath = "//div[@class='_3vVbLPsO3kllSgsNm4wL2']")
	WebElement verifyHomeTown;

	@FindBy(id = "onesignal-popover-container")
	WebElement popoverVerify;

	@FindBy(id = "onesignal-popover-cancel-button")
	WebElement cancelPopoverButton;

	@FindBy(xpath = "//*[@class='styles_closeIcon__1QwbI']")
	WebElement signUPNotification;

	@FindBy(xpath = "//a[contains(text(),'Electronics')]")
	WebElement electronicsButton;

	@FindBy(xpath = "//button[contains(text(),'Color')]")
	WebElement colorButton;

	@FindBy(xpath = "//label[contains(text(),'Black')]")
	WebElement selectBlackColorButton;

	@FindBy(xpath = "//div[contains(@class,'Row-sc-1mdnrs1-0 hiQTNt')]//div[1]//div[1]//button[2]")
	WebElement itemQuickViewButton;

	@FindBy(xpath = "//h1[@class='Heading-sc-1xt1x3f-0 efiseG']")
	WebElement itemTextButton;

	@FindBy(xpath = "//*[@class='styles_closeIcon__1QwbI']")
	WebElement closeQuickViewButton;

	@FindBy(xpath = "//div[@class='_1C7t6hCkKmMddDs8HIk_KY']")
	WebElement verifyFilterButton;

	public HomeTownPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void homeTownTab() throws Exception {
		
		
		verifyHomeTownPage();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("onesignal-popover-container")));
		
		popoverVerify();

		verifysignUPNotification();

		// Clicking on electronics Button
		log.info("popup work done");

		//Thread.sleep(5000);
		executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", electronicsButton);

		// electronicsButton.click();

		Thread.sleep(5000);

		Actions action = new Actions(driver);
		action.moveToElement(colorButton).perform();

		selectBlackColorButton.click();

		Thread.sleep(5000);

		// Click on quick view item
		executor.executeScript("arguments[0].click();", itemQuickViewButton);
		Thread.sleep(5000);

		log.info(itemTextButton.getText());

		String itemName = itemTextButton.getText();

		// Assert that product name contains Black in a name
		assertEquals(true, itemName.contains("Black"));

		// close Quick View Button
		closeQuickViewButton.click();

		// verify if Black is also present in Applied filters
		assertEquals(true, verifyFilterButton.getText().contains("Black"));

	}

	public void verifyHomeTownPage() {
		if (verifyHomeTown.isDisplayed()) {
			log.info("Successfully verified Home Town Page");
		} else {
			log.info("Failed to verify Home Town Page");
		}
	}

	public void popoverVerify() {

		if (popoverVerify.isDisplayed()) {
			log.info("popover present in Home Town page");
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("document.getElementById('onesignal-popover-cancel-button').style.display='block';");
			cancelPopoverButton.click();
		} else {
			log.info("No popover present in Home Town page");
		}
	}

	public void verifysignUPNotification() {
		if (signUPNotification.isDisplayed()) {
			signUPNotification.click();
			log.info("sign UP Notification available in page");
		} else {
			log.info("sign UP Notification not available in page");
		}
	}
}
