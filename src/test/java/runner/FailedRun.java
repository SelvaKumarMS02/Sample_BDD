package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        plugin={"io.qameta.allure.cucumber6jvm","json:target/cucumber-report.json"},
        features = "@target/failedrerun.txt",glue = {"stepDefinition"},
        dryRun = false,
        monochrome = true)


public class FailedRun extends AbstractTestNGCucumberTests {
}
