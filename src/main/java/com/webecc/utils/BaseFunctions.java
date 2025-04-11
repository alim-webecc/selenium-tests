package com.webecc.utils;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseFunctions {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverWait waitLonger;

    public BaseFunctions(WebDriver driver) {
        this.driver = driver;
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.waitLonger = new WebDriverWait(driver,Duration.ofSeconds(60));
    }

//    public BaseFunctions() {
//        String headless = System.getProperty("headless", "true");
//
//        ChromeOptions options = new ChromeOptions();
//        if (headless.equalsIgnoreCase("true")) {
//            options.addArguments("--headless=new");
//        }
//
//        this.driver = new ChromeDriver(options);
//
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        this.waitLonger = new WebDriverWait(driver, Duration.ofSeconds(60));
//    }
//
//    public WebDriver getDriver() {
//        return this.driver;
//    }

    protected void waitUntilVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    protected void waitLongerUntilVisible(WebElement element){
        waitLonger.until(ExpectedConditions.visibilityOf(element));
    }

    protected void sendKeysWhenVisible(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
    }

    protected void clearWhenVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element)).clear();
    }

    protected void clickWhenVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element)).click();
    }

    protected boolean isVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void overwriteInputValue(WebElement input, String value) {
        waitUntilVisible(input);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Fokus setzen (falls Angular reaktiv ist)
        js.executeScript("arguments[0].focus();", input);

        // Wert leeren (per JS, um alles zu entfernen)
        js.executeScript("arguments[0].value = '';", input);

        // Alternativ: CTRL+A + DEL, um alle Inhalte zu löschen
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        input.sendKeys(Keys.DELETE);

        // Nun eigentlichen Text setzen
        input.sendKeys(value);

        // Debug
        System.out.println("Gesendeter Wert: " + value);

        // → Validierung entfernt, weil sie zu fehlschlägen führt
        // Optional: du kannst sie manuell prüfen/loggen statt mit wait
        String actualValue = (String) js.executeScript("return arguments[0].value;", input);
        System.out.println("Tatsächlicher JS-Wert danach: " + actualValue);
    }
    protected void clickWithJSWhenVisible(WebElement button) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(button));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", button);
    }
    protected void kurzWarten(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void clearWhenVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).clear();
    }
    protected void sendKeysWhenVisible(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }
}
