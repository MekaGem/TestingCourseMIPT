package com.github.mekagem.testing.wikiregistration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void testInit() {
        driver = new FirefoxDriver();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
