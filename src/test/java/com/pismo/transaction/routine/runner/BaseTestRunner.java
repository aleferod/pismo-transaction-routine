package com.pismo.transaction.routine.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/java/com/pismo/transaction/routine/features"},
        glue = {"src/test/java/com/pismo/transaction/routine/steps", "src/test/java/com/pismo/transaction/routine/configuration"},
        monochrome = true
)
public class BaseTestRunner extends AbstractTestNGCucumberTests {}
