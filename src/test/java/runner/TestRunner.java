package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
    @CucumberOptions(
            plugin={"io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm","json:target/cucumber-report.json",
                    "rerun:target/failedrerun.txt"},
            features = "src/test/java/featureFiles",glue = {"stepDefinition"},
            tags ="@E2E_Flow",
            dryRun = false,
            monochrome = true)


    public class TestRunner extends AbstractTestNGCucumberTests {
    }
