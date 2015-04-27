package org.krzogr.chirper.integrationtest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Helper unit test used only to run all cucumber integration tests.
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber" }, 
  features = { "src/test-integration/" }, dryRun = false, strict = true)
public class IntegrationTest {
}
