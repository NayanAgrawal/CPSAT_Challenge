package Challenge.Selenium.PremierLeague;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import java.util.Set;

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

import Challenge.Selenium.Base.TestBase;
import Challenge.Selenium.MeriPustak.MeriPustakPage;

public class PremierLeaguePage extends TestBase{
	public static final Logger log = Logger.getLogger(MeriPustakPage.class.getName());

	WebDriver driver;

	@FindBy(id = "advertClose")
	WebElement verifyPremierLeagueAdd;
	
	@FindBy(xpath = "//h4[contains(text(),'Club Sites')]")
	WebElement verifyPremierLeague;

	@FindBy(xpath = "//ul[contains(@class,'showMoreEnabled')]//a[contains(@class,'')][contains(text(),'Tables')]")
	WebElement tableButton;

	@FindBy(xpath = "//tr[@class='tableMid']//span[@class='long'][contains(text(),'Arsenal')]")
	WebElement arsenalLink;

	
	@FindBy(id = "mainContent")
	WebElement verifyArsenalOverview;
	
	
	@FindBy(xpath = "//a[@class='u-hide-tab logoContainer']")
	WebElement logoImageButton;

	
	
	public PremierLeaguePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void premierLeagueTab() throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("advertClose")));
		
		verifyAdd();
		
		verifyPremierLeaguePage();

		// Clicking on table from the header
		tableButton.click();

		Thread.sleep(2000);

		verifyAdd();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 600)", "");

		Thread.sleep(1000);
		
		Actions act = new Actions(driver);
		act.contextClick(arsenalLink).perform(); // right click using context click

		Thread.sleep(2000);
		
		//Open Arsenal in new wndow
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_ENTER);

	
		//verify Arsenal Overview page
		verifyArsenalOverviewPage();
		
		logoImageButton.getAttribute("href");

		// Finding official website URL from newly opened window
		log.info("Official website URL is - " + logoImageButton.getAttribute("href"));

		String parentWinHandle = driver.getWindowHandle();
		Set<String> winHandles = driver.getWindowHandles();
		// Loop through all handles
		for (String handle : winHandles) {
			if (!handle.equals(parentWinHandle)) {
				driver.switchTo().window(handle);
				Thread.sleep(1000);
				log.info("Page title of newly opened window is - " + driver.getTitle());
				log.info("Closing the new window...");
				//driver.close();
			}
		}
		
		// Switching the control back to parent window
		driver.switchTo().window(parentWinHandle);

		// Print the URL of main window
		log.info("Parent window URL - " + driver.getCurrentUrl());

		//Title of main window
		log.info("Page title of main window - " + driver.getTitle());

	}
	
	public void verifyAdd() {
		if(verifyPremierLeagueAdd.isDisplayed()) {
			verifyPremierLeagueAdd.click();
			log.info("Add is required to close to proceed to Premier League Page");
		} else {
			log.info("No Add present in Premier League Page to close");
		}
	}

	public void verifyPremierLeaguePage() {
		if (verifyPremierLeague.isDisplayed()) {
			log.info("Successfully verified Premier League Page");
		} else {
			log.info("Failed to verify Premier League Page");
		}
	}
	
	public void verifyArsenalOverviewPage() {
		if (verifyArsenalOverview.isDisplayed()) {
			log.info("Successfully verified Arsenal Overview Page");
		} else {
			log.info("Failed to verify Arsenal Overview Page");
		}
	}

}
