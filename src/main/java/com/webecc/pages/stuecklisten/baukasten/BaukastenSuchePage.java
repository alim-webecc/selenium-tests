package com.webecc.pages.stuecklisten.baukasten;

import com.github.javafaker.Faker;
import com.webecc.pages.DashboardPage;
import com.webecc.utils.BaseFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BaukastenSuchePage extends BaseFunctions {
    public BaukastenSuchePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    Faker faker = new Faker();
    DashboardPage dashboardPage = new DashboardPage(driver);
    protected JavascriptExecutor js = (JavascriptExecutor) driver;
    Actions actions = new Actions(driver);
    //---------------------Suchbereich----------------------
    @FindBy(css = "#moduleId")
    private WebElement referenzNummerEingabeFeld;

    @FindBy(xpath = "//div[contains(@class, \"buttons-right\")]/preceding-sibling::div[contains(@class, \"buttons\")]/button[@class=\"primary stahl z--1\"]")
    private WebElement suchIcon;

    @FindBy(xpath = "//cds-icon[starts-with(@id, 'showHideColumnsButton_')]")
    private WebElement spaltenEinUndAusblendenIcon;

    @FindAll(@FindBy(xpath = "//div[@class=\"d--flex-row ng-star-inserted\"]//label"))
    private List<WebElement> checkBoxList;

    @FindBy(xpath = "//div[@class=\"card-footer\"]/button[1]")
    private WebElement allesAuswaehlenButton;

    @FindBy(xpath = "//div[@class=\"card-footer\"]/button[2]")
    private WebElement sichtbareSpaltenOkButton;

    @FindBy(xpath = "//span[@class=\"results d--flex-item-center\"]/span")
    private WebElement trefferAnzahl;

    @FindBy(xpath = "//div[@class=\"mat-mdc-paginator-range-actions\"]/button[contains(@class, \"mat-mdc-paginator-navigation-next\")]")
    private WebElement naechsteSeiteButton;

    @FindBy(css = "#changeIndicator")
    private WebElement aenderungsKennzeichenDropDown;

    @FindBy(xpath = "//div[@class=\"buttons z--1\"]/button[@class=\"secondary stahl z--1\"]")
    private WebElement suchKriterienLoeschenButton;

    @FindBy(xpath = "//input[@id=\"productiveTypesOnly\"]/following-sibling::label[@class=\"checkbox-input clr-control-label\"]")
    private WebElement nurInPflegeTypenCheckBox;

    //------------------Such Ergebnisse---------------------
    @FindAll(@FindBy(xpath = "//mat-row[starts-with(@class,\"mat-mdc-row mdc-data-table__row cdk-row row_line_idx_\")]//input[@id=\"moduleId\"]"))
    private List<WebElement> referenzNummern;

    @FindBy(xpath = "//mat-row[starts-with(@class,\"mat-mdc-row mdc-data-table__row cdk-row row_line_idx_\")][1]//input[@id=\"moduleId\"]")
    private WebElement ersteReferenzNummer;
    //-----------------------Meldung------------------------
    @FindBy(css = "button.alert-button")
    private WebElement alertOkButton;

    //---------------------Suchbereich----------------------
    public void baukastenSuchen(String referenzNummer){
        dashboardPage.baukastenSucheOeffnen();
        kurzWarten();
        waitForOverlayToDisappear();
        clickWithJSWhenVisible(suchKriterienLoeschenButton);
        kurzWarten();
        waitUntilVisible(referenzNummerEingabeFeld);
        sendKeysWhenVisibleAndClickable(referenzNummerEingabeFeld, referenzNummer);
        kurzWarten();
        waitForOverlayToDisappear();
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        String value = (String) js.executeScript("return arguments[0].value;", referenzNummerEingabeFeld);
//        System.out.println("Feldinhalt vor dem Setzen: " + value);
//        overwriteInputValue(referenzNummerEingabeFeld, referenzNummer);
//        String value1 = (String) js.executeScript("return arguments[0].value;", referenzNummerEingabeFeld);
//        System.out.println("Feldinhalt nach dem Setzen: " + value1);
        Select select = new Select(aenderungsKennzeichenDropDown);
        select.selectByVisibleText("Alle");
        if(faker.random().nextBoolean()){
//            System.out.println("true");
            clickWhenClickable(nurInPflegeTypenCheckBox);
        }else {
//            System.out.println("false");
        }
        clickWhenClickable(suchIcon);
    }
    public boolean bugTicketWEBECC3699(String referenzNummer){
        List<String> kopierteReferenzNummerList = new ArrayList<>();
        clickWhenClickable(spaltenEinUndAusblendenIcon);
        clickWhenClickable(allesAuswaehlenButton);
        for(WebElement checkBox : checkBoxList){
            WebElement checkBoxLabel = checkBox.findElement(By.xpath("../../following-sibling::div"));
            if(!checkBoxLabel.getText().equalsIgnoreCase("Referenznummer")){
                clickWhenClickable(checkBox);
            }
        }
        clickWhenClickable(sichtbareSpaltenOkButton);
        String ersteTreffAnzahl = trefferAnzahl.getText();
//        System.out.println("Warte auf Änderung von: " + ersteTreffAnzahl);
        clearWhenVisible(referenzNummerEingabeFeld);
        if(!referenzNummerEingabeFeld.getText().equalsIgnoreCase("")){
            clearWhenVisible(referenzNummerEingabeFeld);
        }
        sendKeysWhenVisibleAndClickable(referenzNummerEingabeFeld, referenzNummer); //Bug-Ticket WEBECC-3699
        clickWhenClickable(suchIcon);
        waitLonger.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(trefferAnzahl,ersteTreffAnzahl)));
        int treffeAnzahlInt = Integer.parseInt(trefferAnzahl.getText().trim().split(" ")[0]);
//        System.out.println(treffeAnzahlInt);
        int blaettern = treffeAnzahlInt/20;
//        System.out.println(blaettern);
        for(int malBlaettern = 0; malBlaettern <= blaettern; malBlaettern++){
//            System.out.println("aktuelle Seite : " + (malBlaettern+1));
            waitUntilVisible(ersteReferenzNummer);
            for(WebElement referenzNummerElement : referenzNummern){
                actions.moveToElement(referenzNummerElement).perform();
                //js.executeScript("arguments[0].select();", ersteReferenzNummer);
                new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                        d -> !referenzNummerElement.getAttribute("title").isEmpty()
                );
                String kopierteReferenzNummer = referenzNummerElement.getAttribute("title");
//                System.out.println(kopierteReferenzNummer);
                kopierteReferenzNummerList.add(kopierteReferenzNummer);
            }
            if(malBlaettern < blaettern){
                clickWhenClickable(naechsteSeiteButton);
            }
        }
        String referenzNummerMitPunkt = "81.#2010";
//        System.out.println(kopierteReferenzNummerList.size());
        for (String r : kopierteReferenzNummerList) {
            if (!r.contains(referenzNummerMitPunkt)) {
                return false;
            }
        }
        return true;
    }

    //-----------------------Meldung------------------------
    public boolean istDieBaukastenSucheErfolgreich(){
        waitLongerUntilVisible(ersteReferenzNummer, By.xpath("//mat-row[starts-with(@class,\"mat-mdc-row mdc-data-table__row cdk-row row_line_idx_\")][1]//input[@id=\"moduleId\"]"));
        return isVisible(ersteReferenzNummer, By.xpath("//mat-row[starts-with(@class,\"mat-mdc-row mdc-data-table__row cdk-row row_line_idx_\")][1]//input[@id=\"moduleId\"]"));
    }
}