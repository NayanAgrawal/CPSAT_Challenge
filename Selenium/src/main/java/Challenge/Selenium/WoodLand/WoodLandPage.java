package Challenge.Selenium.WoodLand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Challenge.Selenium.Base.TestBase;

public class WoodLandPage extends TestBase {
	public static final Logger log = Logger.getLogger(WoodLandPage.class.getName());

	WebDriver driver;
	List<String> list;

	@FindBy(xpath = "//figure[@class='floatleft website-logo']")
	WebElement verifyWoodLand;

	@FindBy(xpath = "//div[@class='floatright spirit-bg search-icon searchIcon']")
	WebElement searchIconButton;

	@FindBy(id = "searchkey")
	WebElement searchkeyText;

	@FindBy(id = "searchBtn")
	WebElement searchButton;

	@FindBy(xpath = "//li[@class='spaceright radioSortBy']//label[@class='spirit-bg radio1']")
	WebElement sortByHighToLowButton;

	@FindBy(xpath = "/html[1]/body[1]/main[1]/div[1]/div[1]/div[1]/section[1]/ul[1]/li[1]/span[1]")
	WebElement priceCheck;

	public WoodLandPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void woodLandPageTab(String productName) throws InterruptedException {
		verifyWoodLandPage();

		Thread.sleep(1000);
		searchIconButton.click();

		searchkeyText.sendKeys(productName);

		searchButton.click();

		Thread.sleep(2000);

		// Clicking on Filter
		sortByHighToLowButton.click();

		list = new ArrayList<String>();
		for (int i = 1; i <= 8; i++) {
			String str = "/html[1]/body[1]/main[1]/div[1]/div[1]/div[1]/section[" + i + "]/ul[1]/li[1]";

			WebElement elementName = driver.findElement(By.xpath(str));

			String productPrice = elementName.getText().split(" ")[1];
			String discountProductPrice = elementName.getText().split(" ")[0];

			if (discountProductPrice.length() > 1) {
				list.add(discountProductPrice.replace("R", ""));
			} else {
				list.add(productPrice);
			}

			if (elementName.getText().replace("R ", "").contains("R")) {
				elementName.getText().replace("R ", "").replace("R", "");
			}

		}

		if (isSorted() == true) {
			log.info("First 8 products are in descending order of the price and the list is - " + list);
		} else {
			log.info("First 8 products are not in descending order of the price " + list);
		}

	}

	public boolean isSorted() {
		boolean sorted = false;
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i - 1).compareTo(list.get(i)) > 0)
				sorted = true;
		}
		return sorted;
	}

	public void verifyWoodLandPage() {
		if (verifyWoodLand.isDisplayed()) {
			log.info("Successfully verified Wood Land Page");
		} else {
			log.info("Failed to verify Wood Land Page");
			try {
				getScreenshot(driver, "WoodLandverificationFailed");
			} catch (IOException e) {
				log.info("Failed to get screenshot");
			}
		}
	}
}
