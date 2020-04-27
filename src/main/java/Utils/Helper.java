package Utils;


import PageObjects.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.util.Properties;

public class Helper extends Base {

    public static String getConfigValue(String key) {
        Properties config = new Properties();

        try {
            String filename = "";
            if (System.getProperty("os.name").contains("Win")) {
                filename = "properties\\config";
            } else {
                filename = "properties/config";
            }
            config.load(new FileInputStream("target/classes/" + filename));
        } catch (Throwable t) {
            System.out.print("Issue loading properties file");
            t.printStackTrace();
        }
        return config.getProperty(key);

    }

    public static void waitAndClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static void waitForElementVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementVisibility(WebElement element, long sec) {
        WebDriverWait wait = new WebDriverWait(getDriver(), sec);
        wait.until(ExpectedConditions.visibilityOf(element));
    }


}

