package com.esp11.app;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        dryRun = false,
        features = "src/test/resources",
        glue = "com.esp11.app",
        plugin = {"json:target/Cucumber.json"}
)
public class CucumberTest {
}

