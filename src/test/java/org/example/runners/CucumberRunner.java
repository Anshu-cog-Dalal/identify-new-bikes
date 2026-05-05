package org.example.runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="features",
        glue={"org.example.stepdefs","org.example.Hook"},
        plugin={"pretty", "html:target/cucumber-report.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome=true
)
public class CucumberRunner {
}