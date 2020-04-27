package PageObjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static Utils.Helper.waitAndClick;
import static Utils.Helper.waitForElementVisibility;
import static org.junit.Assert.assertTrue;

/*
    Author : Abir 16/04/20
*/

public class TradeMePage extends Base {

    @FindBy(id = "SearchTabs1_MotorsLink")
    WebElement motors;

    @FindBy(id = "SiteHeader_SiteTabs_SubNavMotors_LinkUsedCars")
    WebElement usedCars;

    @FindBy(xpath = "//a[contains(text(),'Hyundai')]")
    WebElement hyundai;

    @FindBy(xpath = "//table[@id='makes']//a")
    List<WebElement> availableBrandsList;

    @FindBy(xpath = "//table[@id='makes']//span")
    List<WebElement> carCount;

    public void clickOnMotors() {
        waitAndClick(motors);
    }

    public void clickOnUsedCars() {
        waitAndClick(usedCars);
    }

    public void assertNumberOfBrands(int count) {

        waitForElementVisibility(hyundai);

        List<WebElement> brands = availableBrandsList;

        int brandCount = brands.size() - 1;

        System.out.println("Number of brands available are " + brandCount);

        Assert.assertEquals("Number of brands available are not correct ", brandCount, count);
    }

    public void assertCarBrandName(String brandName, String isAvailable) throws Exception {

        waitForElementVisibility(hyundai);

        List<WebElement> brands = availableBrandsList;

        for (WebElement brandAvailable : brands) {

            String linkText = brandAvailable.getText();

            if (brandName.equalsIgnoreCase(linkText)) {
                if (isAvailable.equals("is")){
                    return;
                }
                else {
                    throw new Exception("The brand Name should not have been available");
                }
            }
        }
        if (isAvailable.equals("is")){
            throw new Exception("The brand Name is not available");
        }
    }

    public void assertNumberOfCarsBrand(int count, String brandName) {

        int brandIndex = -1;

        waitForElementVisibility(hyundai);

        List<WebElement> brands = availableBrandsList;

        List<WebElement> carBrandCount = carCount;

        for (int i = 0; i < brands.size(); i++) {

            String linkText = brands.get(i).getText();
            System.out.println("-------------------");
            System.out.println(linkText);

            if (brandName.equalsIgnoreCase(linkText)) {
                brandIndex = i;
                break;
            }
        }
        String expectedValue = "(" + count + ")";

        assertTrue("The number of Kia brand cars are not correct  ", carBrandCount.get(brandIndex).getText().equals(expectedValue));
    }


}
