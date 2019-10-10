package Challenge.Selenium;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Challenge.Selenium.TestCase.TC002_CIIOnlineRegistration;
import Challenge.Selenium.TestCase.TC003_PremierLeague;
import Challenge.Selenium.TestCase.TC004_HomeTown;

@RunWith(Suite.class)
@SuiteClasses({TC002_CIIOnlineRegistration.class, TC003_PremierLeague.class, TC004_HomeTown.class })
public class JUnitTestSuite {

}
