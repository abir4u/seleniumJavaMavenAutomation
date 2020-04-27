package PageObjects;

import Utils.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class Base {

    /*
    Author : Abir 16/04/20
*/

    private static WebDriver driver;

    static protected TradeMePage tradeMePage;


    public WebDriver Setup() {
        setSystemProperties();
        try {
            InitiateDriver();
        } catch (Exception e) {
        }
        InitializePages();
        return driver;
    }

    private void setSystemProperties() {
        String path = System.getProperty("user.dir") + "/drivers/";

        if (System.getProperty("os.name").contains("Win")) {
            System.setProperty("webdriver.chrome.driver", path + "win/chromedriver.exe");
            System.setProperty("webdriver.firefox.marionette", path + "win/geckodriver.exe");

        } else if (System.getProperty("os.name").contains("Mac")) {
            System.setProperty("webdriver.chrome.driver", path + "mac/chromedriver");
            System.setProperty("webdriver.gecko.driver", path + "mac/geckodriver");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public void getPage(String _url) {
        driver.get(_url);
    }

    public void InitializePages() {

        tradeMePage = PageFactory.initElements(driver, TradeMePage.class);
    }

    private WebDriver InitiateDriver() throws Exception {
        String browser;
        System.out.println("Initiating WebDriver");
        DesiredCapabilities cap = new DesiredCapabilities();
        String _browser = System.getProperty("browser");
        if (null != _browser && !_browser.isEmpty()) {
            browser = _browser;
        } else {
            browser = Helper.getConfigValue("default.browser");
        }
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions option = new ChromeOptions();
                option.setExperimentalOption("useAutomationExtension", false);
                option.addArguments("--disable-infobars");
                this.driver = new ChromeDriver(option);
                break;

            case "firefox":
                if (System.getProperty("os.name").contains("Mac")) {
                    FirefoxProfile profile = new FirefoxProfile();
                    this.driver = new FirefoxDriver(profile);
                } else {
                    cap = DesiredCapabilities.firefox();
                    cap.setCapability("platform", "Windows 7");
                    this.driver = new FirefoxDriver(cap);
                }
                break;
            default:
                fail("Unknown browser");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return this.driver;
    }

}


