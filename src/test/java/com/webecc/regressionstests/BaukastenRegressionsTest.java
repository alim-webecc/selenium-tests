package com.webecc.regressionstests;

import com.webecc.pages.DashboardPage;
import com.webecc.pages.LoginPage;
import com.webecc.pages.stuecklisten.baukasten.BaukastenSuchePage;
import com.webecc.utils.ScreenshotListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ScreenshotListener.class)
public class BaukastenRegressionsTest {
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    BaukastenSuchePage baukastenSuchePage;


//    @BeforeTest
//    public void setup(){
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//
//        // Jetzt ist der Driver bereit → PageObjects initialisieren
//        loginPage = new LoginPage(driver);
//        dashboardPage = new DashboardPage(driver);
//        baukastenSuchePage = new BaukastenSuchePage(driver);
//    }
@BeforeTest
public void setup(){
    WebDriverManager.chromedriver().setup();

    ChromeOptions options = new ChromeOptions();
    String headless = System.getProperty("headless", "false");

    if (headless.equalsIgnoreCase("true")) {
        options.addArguments("--headless=new");   // Für moderne Browser
        options.addArguments("--headless");       // Fallback für ältere
        options.addArguments("window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
    }

    driver = new ChromeDriver(options);
    Reporter.getCurrentTestResult().getTestContext().setAttribute("driver", driver);

    if (!headless.equalsIgnoreCase("true")) {
        driver.manage().window().maximize();
    }

    // Page Objects
    loginPage = new LoginPage(driver);
    dashboardPage = new DashboardPage(driver);
    baukastenSuchePage = new BaukastenSuchePage(driver);
    driver.get("https://dev.webecc.com/webecc/#/login");
}

    @Test(priority = 1)
    public void baukastenSuchen(){
        loginPage.login("ALIM01");
        String ersteNummer = "81#1*";
        baukastenSuchePage.baukastenSuchen(ersteNummer);
        Assert.assertTrue(baukastenSuchePage.istDieBaukastenSucheErfolgreich());
    }
    @Test(priority = 2)
    public void bugTicketWEBECC3699(){
        String zweiteNummer = "81#2010*";
        Assert.assertTrue(baukastenSuchePage.bugTicketWEBECC3699(zweiteNummer));
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
