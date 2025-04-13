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
}


    @Test(priority = 1)
    public void loginTest() {
        driver.get("https://dev.webecc.com/webecc/#/login");
        loginPage.login("ALIM01", "#Vancouver.Munich0710");
        Assert.assertTrue(dashboardPage.sichtbarkeitDerLogoutButton());
    }
    @Test(priority = 2)
    @Description("Dieser Test ist absichtlich fehlerhaft für Allure")
    @Severity(SeverityLevel.CRITICAL)
    public void testAbsichtlichFehlgeschlagen() {
        Allure.step("Ein Beispiel-Allure-Step");
        Assert.assertTrue(false, "Dieser Test schlägt absichtlich fehl");
    }

    @Test(priority = 3)
    public void baukastenSuchen(){
        String nummer = "81#1*";
        baukastenSuchePage.baukastenSuchen(nummer);
        Assert.assertTrue(baukastenSuchePage.istDieBaukastenSucheErfolgreich());
    }
    @Test(priority = 4)
    public void bugTicketWEBECC3699(){
        String komputer = "81#2010*";
        Assert.assertTrue(baukastenSuchePage.bugTicketWEBECC3699(komputer));
    }
    @Test(priority = 5)
    @Description("Test läuft sicher durch und zeigt sich im Report")
    @Severity(SeverityLevel.MINOR)
    public void dummyVisibleTest() {
        System.out.println("Dummy-Test wird ausgeführt.");
        Assert.assertTrue(true);
    }

    @Test(priority = 6)
    @Description("Allure Smoke Test")
    @Severity(SeverityLevel.NORMAL)
    public void allureSmokeTest() {
        Allure.step("Step 1: Vorbereitung");
        Allure.step("Step 2: Erwartung");
        Allure.step("Step 3: Validierung");

        Allure.addAttachment("Text-Log", "Dies ist ein Allure-Test");
        Assert.assertTrue(true);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
