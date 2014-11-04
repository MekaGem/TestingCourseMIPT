package com.github.mekagem.testing.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BaseManager {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BaseManager(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    protected final void justWait(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected final void waitFor(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected final void waitFor(WebElement element) {
        List<WebElement> cover = new ArrayList<WebElement>();
        cover.add(element);
        wait.until(ExpectedConditions.visibilityOfAllElements(cover));
    }

    protected final void click(By locator) {
        waitFor(locator);
        driver.findElement(locator).click();
    }

    protected final void type(By locator, String text) {
        waitFor(locator);
        driver.findElement(locator).sendKeys(text);
    }

    protected final boolean check(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    protected final WebElement find(By locator) {
        waitFor(locator);
        return driver.findElement(locator);
    }
}
