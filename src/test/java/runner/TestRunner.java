package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


    @CucumberOptions(
            plugin={"io.qameta.allure.cucumber6jvm","json:target/cucumber-report.json",
                    "rerun:target/failedrerun.txt"},
            features = "src/test/java/featureFiles",glue = {"stepDefinition"},
            tags ="@E2E_Flow",
            dryRun = false,
            monochrome = true)


    public class TestRunner extends AbstractTestNGCucumberTests {
    }
