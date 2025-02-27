package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepsDef"},
    tags = "@Test",
    plugin = {
      "html:target/cucumber-reports/cucumber-html-report.html",
      "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    })
public class Runner extends AbstractTestNGCucumberTests {

}