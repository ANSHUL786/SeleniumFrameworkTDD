package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(features="src/test/java/Cucumber",glue="anshFramework.stepDefinition",monochrome=true,plugin= {"html:target/cucumber.html","rerun:target/failed_cucumber.txt"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
