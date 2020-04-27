import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/*
    Author : Abir 16/04/20
*/

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = "classpath:features",
        glue = "StepDefinitions",
        plugin = {"json:target/cucumber"}

)
public class TestRunner {
}

