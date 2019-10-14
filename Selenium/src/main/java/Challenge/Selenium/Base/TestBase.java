package Challenge.Selenium.Base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;

public class TestBase {

	public static final Logger log = Logger.getLogger(TestBase.class.getName());

	public WebDriver driver;

	public void init() {
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);

	}

	//Select browser method
	public void selectBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			log.info("Creating object of " + browser);
			ChromeDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {
			log.info("Creating object of " + browser);
			FirefoxDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
	}

	//Get application URL method
	public void getUrl(String url) {
		log.info("Navigating to : " + url);
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	//Generate screenshot
	public String getScreenshot(WebDriver driver, String imageName) throws IOException {

		if (imageName.equals("")) {
			imageName = "_blank";
		}

		Calendar calander = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
				+ "/src/main/java/com/test/tech9/Stampinup/screenshot/";

		String actualImageName = reportDirectory + imageName + "_" + format.format(calander.getTime()) + ".png";
		File destFile = new File(actualImageName);
		FileUtils.copyFile(image, destFile);

		Reporter.log("<a href= '" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
				+ "' height='100' width ='100'/></a>");

		return actualImageName;

	}

	//Wait for element to be clickable
	public void explicatWaitTillvisibility(WebDriver driver, WebElement xpathValue) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(xpathValue));

	}
}
