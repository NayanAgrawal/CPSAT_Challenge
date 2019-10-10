package Challenge.Selenium.CIIOnlineRegistration;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import static org.junit.Assert.assertEquals;

import Challenge.Selenium.MeriPustak.MeriPustakPage;

public class CIIOnlineRegistrationForm {

	public static final Logger log = Logger.getLogger(MeriPustakPage.class.getName());

	WebDriver driver;

	@FindBy(xpath = "//div[@class='logo']")
	WebElement displayPageVerification;

	@FindBy(id = "Gridview1")
	WebElement attendeeRowCount;

	@FindBy(id = "drpAttendee")
	WebElement numberOfAtteneeDropDown;

	@FindBy(id = "Gridview1_ctl02_drpTitle")
	WebElement firstRowTitle;

	@FindBy(id = "Gridview1_ctl03_drpTitle")
	WebElement secondRowTitle;

	@FindBy(id = "Gridview1_ctl04_drpTitle")
	WebElement thirdRowTitle;

	public CIIOnlineRegistrationForm(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void ciiRegistrationTab() throws Exception {

		WebElement element = driver.findElement(By.tagName("header"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);

		verifyCIIOnlineRegistrationPage();

		// Select number of attendees as 3
		Select attendeeValue = new Select(numberOfAtteneeDropDown);
		attendeeValue.selectByValue("3");

		Thread.sleep(2000);

		List<WebElement> attendeeRows = driver.findElements(By.xpath("//table[@id='Gridview1']/tbody/tr"));
		int rows = attendeeRows.size() - 1;
		String actualRows = Integer.toString(rows);

		// Assert row count 3 check
		assertEquals(actualRows, "3");

		// Select first title
		Select firstTitle = new Select(firstRowTitle);
		firstTitle.selectByValue("Admiral");

		// Select second title
		Select secondTitle = new Select(secondRowTitle);
		secondTitle.selectByIndex(12);

		// Select third title
		Select thirdTitle = new Select(thirdRowTitle);
		thirdTitle.selectByVisibleText("CS");

		// print all the options from Dropdown
		Select titleTag = new Select(firstRowTitle);
		List<WebElement> titleCount = titleTag.getOptions();
		int iSize = titleCount.size();

		for (int i = 1; i < iSize; i++) {
			String dropdownTitleValue = titleCount.get(i).getText();
			log.info(dropdownTitleValue);
		}

		Thread.sleep(3000);
	}

	public void verifyCIIOnlineRegistrationPage() {
		if (displayPageVerification.isDisplayed()) {
			log.info("Successfully verified CII Online Registration Page");
		} else {
			log.info("Failed to verify CII Online Registration Page");
		}
	}
}
