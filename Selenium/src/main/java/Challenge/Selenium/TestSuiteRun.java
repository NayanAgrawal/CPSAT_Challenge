package Challenge.Selenium;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.testng.TestNG;

import Challenge.Selenium.MeriPustak.MeriPustakPage;
import Challenge.Selenium.TestCase.TC001_MeriPustak;
import Challenge.Selenium.TestCase.TC005_WoodLand;

import org.apache.log4j.Logger;
import org.junit.runner.JUnitCore;

public class TestSuiteRun {

	public static final Logger log = Logger.getLogger(MeriPustakPage.class.getName());

	public static void main(String[] args) {

		TestNG testSuite = new TestNG();

		// Test case 1st
		testSuite.setTestClasses(new Class[] { TC001_MeriPustak.class });
		testSuite.run();

		// Test case 2nd 3rd and 4th
		Result result = JUnitCore.runClasses(JUnitTestSuite.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
			failure.getException().printStackTrace();
		}

		// Test case 5th
		testSuite.setTestClasses(new Class[] { TC005_WoodLand.class });
		testSuite.run();

		log.info("Execution completed");

	}
}
