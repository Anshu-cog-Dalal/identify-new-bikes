package org.example.runners;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "features/Login.feature",
        glue = {"org.example.stepdefs"},
        plugin = {"pretty", "html:target/cucumber-report.html"},
        monochrome = true
)
public class CucumberRunner {
}