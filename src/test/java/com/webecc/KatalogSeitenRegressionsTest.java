package com.webecc;

import com.webecc.pages.DashboardPage;
import com.webecc.pages.Katalog.KatalogSeitenDetailansichtPage;
import com.webecc.pages.LoginPage;
import com.webecc.pages.stuecklisten.baukasten.BaukastenSuchePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class KatalogSeitenRegressionsTest {
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    KatalogSeitenDetailansichtPage katalogSeitenDetailansichtPage;



    @BeforeTest
    public void setup(){
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
        katalogSeitenDetailansichtPage = new KatalogSeitenDetailansichtPage(driver);
    }

    @Test(priority = 1)
    public void loginTest() {
        driver.get("https://dev.webecc.com/webecc/#/login");
        loginPage.login("ALIM01", "#Vancouver.Munich0710");
        Assert.assertTrue(dashboardPage.sichtbarkeitDerLogoutButton());
    }

    @Test(priority = 2)
    public void sachnummernValidierungBeiStueckWWWEBECC3984Szenario3(){//negative Szenarien
        Assert.assertTrue(katalogSeitenDetailansichtPage.sachnummernValidierungBeiStueckWWWEBECC3984Szenario3());
        katalogSeitenDetailansichtPage.clickOnOkButton();
    }
    @Test(priority = 3)
    public void sachnummernValidierungBeiStueckWWWEBECC3984Szenario4(){//negative Szenarien
        Assert.assertTrue(katalogSeitenDetailansichtPage.sachnummernValidierungBeiStueckWWWEBECC3984Szenario4());
        katalogSeitenDetailansichtPage.clickOnOkButton();
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
