package com.demo.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(    
		features = "src/test/resources/features",
		glue = "com.demo.StepDefinitions",
		tags = {"@Assignment1"})
public class Runner {

}
