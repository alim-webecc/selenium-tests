package com.webecc.pages.Katalog;

import com.github.javafaker.Faker;
import com.webecc.pages.DashboardPage;
import com.webecc.utils.BaseFunctions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class KatalogSeitenDetailansichtPage extends BaseFunctions {

    //---------------------------Headerbereich-----------------------------------
    @FindBy(css = "#catalogPageId")
    private WebElement katalogSeitenIdEingabeFeld;

    @FindBy(xpath = "//div[@class=\"buttons z--1 search-navigate\"]/button[@class=\"primary stahl z--1\"]")
    private WebElement suchButton;

    @FindBy(css = "#btnOpenCatalogPageWithOppositeStatus")
    private WebElement entwurfSeiteButton;

    @FindBy(css = "#btnDetailsSave")
    private WebElement speichernButton;

    @FindBy(css = "#btnRelease")
    private WebElement freigebenButton;

    //SachnummerBereich
    @FindAll(@FindBy(xpath = "//app-edit-grid-form[@id=\"gridItemNumbers\"]//mat-row[starts-with(@class, \"mat-mdc-row mdc-data-table__row cdk-row row_line_idx_\")]//input[@id=\"itemNumber\"]"))
    private List<WebElement> sachnummerFelderListe;


    //popup
    @FindBy(css = "#validation-popup")
    private WebElement validationPopup;

    @FindBy(xpath = "//div[@id=\"validation-popup\"]//button[@class=\"btn btn-sm btn-link p-left--0\"]")
    private WebElement okButton;

    public KatalogSeitenDetailansichtPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    Faker faker = new Faker();
    DashboardPage dashboardPage = new DashboardPage(driver);
    protected JavascriptExecutor js = (JavascriptExecutor) driver;
    Actions actions = new Actions(driver);

    public boolean sachnummernValidierungBeiStueckWWWEBECC3984Szenario3(){  //Szenario 3: Zwei WW – eine oben, eine unten – mit anderer Stückzahl dazwischen
        dashboardPage.katalogSeitenDetailansichtOeffnen();
        kurzWarten();
        String katalogSeitenId = "#0000 0100 0068";
//        sendKeysWhenVisibleAndClickable(katalogSeitenIdEingabeFeld, katalogSeitenId);
        robustInput(katalogSeitenIdEingabeFeld,katalogSeitenId);
        kurzWarten();
        clickWhenClickable(suchButton);
        clickWhenClickable(entwurfSeiteButton);
        List<String> sachnummerList = new ArrayList<>();

        for (int i = 0; i < sachnummerFelderListe.size(); i++) {
            try {
                sachnummerList.add(sachnummerFelderListe.get(i).getAttribute("title"));
            } catch (StaleElementReferenceException e) {
                // Element erneut holen und dann versuchen
                WebElement feld = driver.findElements(By.xpath("//input[@id='itemNumber']")).get(i);
                sachnummerList.add(feld.getAttribute("title"));
            }
        }

//        for (WebElement sachnummerFeld : sachnummerFelderListe){
//            sachnummerList.add(sachnummerFeld.getAttribute("title"));
//        }
        String positionsNummer = String.valueOf(faker.random().nextInt(1, 5));
        String stueckZahl = String.valueOf(faker.random().nextInt(1, 100));
        for (int i = 0; i <= sachnummerFelderListe.size() - 3; i++){
            String sachnummer1 = sachnummerList.get(i);
            String sachnummer2 = sachnummerList.get(i+1);
            String sachnummer3 = sachnummerList.get(i+2);
            if (!sachnummer1.isEmpty() && !sachnummer2.isEmpty() && !sachnummer3.isEmpty()){
                for(int j = 0; j < 3; j++){
                    clearWhenVisible(By.xpath(
                            String.format("//app-edit-grid-form[@id='gridItemNumbers']//mat-row[contains(@class, 'row_line_idx_%d')]//input[@id='illustrationPosition']", i+j)));
                    sendKeysWhenVisibleAndClickable(By.xpath(
                            String.format("//app-edit-grid-form[@id='gridItemNumbers']//mat-row[contains(@class, 'row_line_idx_%d')]//input[@id='illustrationPosition']", i+j)), positionsNummer);
                    clearWhenVisible(By.xpath(
                            String.format("//app-edit-grid-form[@id='gridItemNumbers']//mat-row[contains(@class, 'row_line_idx_%d')]//input[@id=\"quantity_1\"]", i+j)));
                    if(j%2 == 0){
                        sendKeysWhenVisibleAndClickable(By.xpath(
                                String.format("//app-edit-grid-form[@id='gridItemNumbers']//mat-row[contains(@class, 'row_line_idx_%d')]//input[@id=\"quantity_1\"]", i+j)), "WW");
                    }else {
                        sendKeysWhenVisibleAndClickable(By.xpath(
                                String.format("//app-edit-grid-form[@id='gridItemNumbers']//mat-row[contains(@class, 'row_line_idx_%d')]//input[@id=\"quantity_1\"]", i+j)), stueckZahl);
                    }
                }
                break;
            }
        }
        clickWhenClickable(speichernButton);
        kurzWarten();
        clickWhenClickable(freigebenButton);
        kurzWarten();
        return isVisible(validationPopup, By.cssSelector("#validation-popup"));
    }
    public void clickOnOkButton(){
        clickWhenClickable(okButton);
    }
    public boolean sachnummernValidierungBeiStueckWWWEBECC3984Szenario4(){ //Szenario 4: Nur eine Position mit WW
        String katalogSeitenId = "#0000 0100 0070";
        sendKeysWhenVisibleAndClickable(katalogSeitenIdEingabeFeld, katalogSeitenId);
        kurzWarten();
        clickWhenClickable(suchButton);
        clickWhenClickable(entwurfSeiteButton);
        waitForOverlayToDisappear();
        kurzWarten();
        List<String> sachnummerList = new ArrayList<>();
        for (WebElement sachnummerFeld : sachnummerFelderListe){
            sachnummerList.add(sachnummerFeld.getAttribute("title"));
        }
        String positionsNummer = String.valueOf(faker.random().nextInt(1, 5));
        String stueckZahl = String.valueOf(faker.random().nextInt(1, 100));
        for (int i = 0; i <= sachnummerFelderListe.size() - 2; i++){
            String sachnummer1 = sachnummerList.get(i);
            String sachnummer2 = sachnummerList.get(i+1);
            if (!sachnummer1.isEmpty() && !sachnummer2.isEmpty()){
                for(int j = 0; j < 2; j++){
                    clearWhenVisible(By.xpath(
                            String.format("//app-edit-grid-form[@id='gridItemNumbers']//mat-row[contains(@class, 'row_line_idx_%d')]//input[@id='illustrationPosition']", i+j)));
                    sendKeysWhenVisibleAndClickable(By.xpath(
                            String.format("//app-edit-grid-form[@id='gridItemNumbers']//mat-row[contains(@class, 'row_line_idx_%d')]//input[@id='illustrationPosition']", i+j)), positionsNummer);
                    clearWhenVisible(By.xpath(
                            String.format("//app-edit-grid-form[@id='gridItemNumbers']//mat-row[contains(@class, 'row_line_idx_%d')]//input[@id=\"quantity_1\"]", i+j)));
                    if(j%2 == 0){
                        sendKeysWhenVisibleAndClickable(By.xpath(
                                String.format("//app-edit-grid-form[@id='gridItemNumbers']//mat-row[contains(@class, 'row_line_idx_%d')]//input[@id=\"quantity_1\"]", i+j)), "WW");
                    }else {
                        sendKeysWhenVisibleAndClickable(By.xpath(
                                String.format("//app-edit-grid-form[@id='gridItemNumbers']//mat-row[contains(@class, 'row_line_idx_%d')]//input[@id=\"quantity_1\"]", i+j)), stueckZahl);
                    }
                }
                break;
            }
        }
        clickWhenClickable(speichernButton);
        kurzWarten();
        clickWhenClickable(freigebenButton);
        kurzWarten();
        return isVisible(validationPopup, By.cssSelector("#validation-popup"));
    }
}