package StepDefinitions;


import PageObjects.Base;
import Utils.Helper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class TradeMeStepDefs extends Base {

    @Given("^I navigate to TradeMe Sandbox$")
    public void i_navigate_to_TradeMe_Sandbox() {
        getPage(Helper.getConfigValue("url"));
    }

    @Given("^I go to Used cars category$")
    public void i_go_to_Used_cars_category() {
        tradeMePage.clickOnMotors();
        tradeMePage.clickOnUsedCars();
    }

    @Then("^I verify that (.*) brands are available$")
    public void i_verify_brands_available(int number) {
        tradeMePage.assertNumberOfBrands(number);
    }

    @Then("^I verify that (.*) brand (is|is not) available$")
    public void iVerifyBrandAvailable(String brandName, String isAvailable) throws Exception {
        tradeMePage.assertCarBrandName(brandName, isAvailable);
    }

    @Then("^I verify that (.*) car is available in (.*) brand$")
    public void iVeryfyCountOfAvailableBrands(int number, String name) {
        tradeMePage.assertNumberOfCarsBrand(number, name);
    }
}
