package com.webecc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webecc.pages.DashboardPage;
import com.webecc.pages.LoginPage;
import com.webecc.pages.lexikon.TextschluesselDetailansichtPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LexikonRegressionTest {
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    TextschluesselDetailansichtPage textschluesselDetailansichtPage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();

        // Optional: headless steuerbar über System-Property
        String headless = System.getProperty("headless", "false");
        if (headless.equalsIgnoreCase("true")) {
            options.addArguments("--headless=new");
        }

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        // Jetzt ist der Driver bereit → PageObjects initialisieren
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        textschluesselDetailansichtPage = new TextschluesselDetailansichtPage(driver);
    }

    @Test(priority = 1)
    public void loginTest() {
        driver.get("https://dev.webecc.com/webecc/#/login");
        loginPage.login("ALIMHA", "#Vancouver.Munich0710");
        Assert.assertTrue(dashboardPage.sichtbarkeitDerLogoutButton());
    }

    @Test(priority = 2)
    public void openTheTextschluesselDetailansicht() {
        dashboardPage.openTheTextschluesselDetailansicht();
        Assert.assertTrue(textschluesselDetailansichtPage.isTheTextschluesselLabelVisible(),
                "Die Textschlüssellabel nicht gefunden.");
    }

    @Test(priority = 3)
    public void textschluesselSuchen() {
        textschluesselDetailansichtPage.textschluesselSuchen();
        Assert.assertTrue(textschluesselDetailansichtPage.isTheTextschluesselPresent(),
                "Die Textschlüssel ist nicht vorhanden.");
    }

    @Test(priority = 4)
    public void checkTheHeaderText() {
        Assert.assertTrue(textschluesselDetailansichtPage.istDasRichtigeTextschluessel(),
                "Das ist nicht die richtige Textschlüssel.");
    }

    @Test(priority = 5)
    public void dieTextLabelSollNichtExistieren() {
        Assert.assertTrue(textschluesselDetailansichtPage.dieTextLabelSollNichtExistieren(),
                "Höchst Warscheinlich der Bug WEBECC-4032 existiert immer noch.");
    }

    @Test(priority = 6)
    public void istDerAnlegenVonNeueTextSchluesselErfolgreich() {
        Assert.assertTrue(textschluesselDetailansichtPage.kannEineNeueTextschluesselAngelegtWerden(),
                "Die Textschlüssel wurde nicht erfolgreich angelegt!");
    }

    @Test(priority = 7)
    public void istNeuAngelegteTextSchluesselRichtigAngezeigt() {
        Assert.assertTrue(textschluesselDetailansichtPage.istNeuAngelegteTextSchluesselRichtigAngezeigt(),
                "Es gibt mindestens eine Fehler bei Anzeige von neu angelegten Textschlüssel.");
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
