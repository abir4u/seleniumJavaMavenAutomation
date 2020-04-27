package StepDefinitions;

import PageObjects.Base;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class Hooks extends Base {

    public static Scenario myScenario;

    @Before
    public void start(Scenario scenario) {
        Setup();
    }

    @Before
    public void embedScreenshotStep(Scenario scenario) {
        myScenario = scenario;
    }

    @After
    public void embedScreenshot(Scenario scenario ) {

        WebDriver driver = getDriver();

        try {
            scenario.write("Current Page URL is " + getDriver().getCurrentUrl());
            byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
        driver.quit();

    }

}
