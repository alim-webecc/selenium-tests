package com.webecc;

import com.webecc.pages.DashboardPage;
import com.webecc.pages.LoginPage;
import com.webecc.pages.stuecklisten.baukasten.BaukastenSuchePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BaukastenRegressionsTest {
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    BaukastenSuchePage baukastenSuchePage;


    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Jetzt ist der Driver bereit → PageObjects initialisieren
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
    public void baukastenSuchen(){
        String nummer = "81#1*";
        baukastenSuchePage.baukastenSuchen(nummer);
        Assert.assertTrue(baukastenSuchePage.istDieBaukastenSucheErfolgreich());
    }
    @Test(priority = 3)
    public void bugTicketWEBECC3699(){
        String komputer = "81#2010*";
        Assert.assertTrue(baukastenSuchePage.bugTicketWEBECC3699(komputer));
    }
}
