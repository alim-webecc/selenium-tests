package com.webecc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.webecc.utils.BaseFunctions;

public class DashboardPage extends BaseFunctions {
    public DashboardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //---------------------Dashboard----------------------
    @FindBy(css = "button.app-button-logout")
    private  WebElement logoutButton;

    //--------------------Lexikon---------------------
    @FindBy(id = "menuDropdownTriggerLexicon")
    private WebElement lexikonMenuButton;

    @FindBy(css = "#menuDropdownItemLexiconDetails")
    private WebElement textschluesselDetailansichtMenuItem;

    @FindBy(css = "#menuDropdownItemLexiconList")
    private WebElement lexikonSucheMenuItem;

    @FindBy(css = "#menuDropdownItemLexiconStatistics")
    private WebElement statistikDerUebersetzungMenuItem;

    @FindBy(css = "#menuDropdownItemLexiconExport")
    private WebElement uebersetzungsExportMenuItem;

    @FindBy(css = "#menuDropdownItemLexiconImport")
    private WebElement uebersetzungsImportMenuItem;

    @FindBy(css = "#menuDropdownItemTextkeyStatistics")
    private WebElement textschluesselVerwendungenMenuItem;


    //--------------------Katalog---------------------

    @FindBy(xpath = "//button[@id=\"menuDropdownTriggerCatalog\"]")
    private WebElement katalogMenuButton;

    @FindBy(css = "#menuDropdownCatalogPageDetails")
    private WebElement katalogSeitenDetailansichtMenuItem;


    //---------------------Dashboard----------------------
    public boolean sichtbarkeitDerLogoutButton(){
        waitForOverlayToDisappear();
        System.err.println("Logout-Button sichtbar: " + logoutButton.isDisplayed());
        return isVisible(logoutButton);
    }


    //--------------------Stücklisten---------------------
    @FindBy(css = "#menuDropdownTriggerPartsList")
    private WebElement stueckListenMenuButton;
    //******************Änderungsnummer*******************


    //***************Referenznummer-Stamm*****************


    //********************Baukasten***********************
    @FindBy(css = "menuDropdownItemModuleDetails")
    private WebElement baukastenDetailansichtMenuItem;

    @FindBy(css = "#menuDropdownItemModuleList")
    private WebElement baukastenSucheMenuItem;

    @FindBy(css = "menuDropdownItemModuleStatistics")
    private WebElement snrBaukastenVerwendungenMenuItem;


    //--------------------Lexikon---------------------
    public void openTheTextschluesselDetailansicht() {
        clickWhenClickable(lexikonMenuButton);
        clickWhenClickable(textschluesselDetailansichtMenuItem);
    }

    public void openTheLexikonSuche() {
        clickWhenClickable(lexikonMenuButton);
        clickWhenClickable(lexikonSucheMenuItem);
    }

    public void openTheStatistikDerUebersetzungen() {
        clickWhenClickable(lexikonMenuButton);
        clickWhenClickable(statistikDerUebersetzungMenuItem);
    }

    public void openTheUebersetzungsExport() {
        clickWhenClickable(lexikonMenuButton);
        clickWhenClickable(uebersetzungsExportMenuItem);
    }

    public void openTheUebersetzungsImport() {
        clickWhenClickable(lexikonMenuButton);
        clickWhenClickable(uebersetzungsImportMenuItem);
    }

    public void openTheTextschluesselVerwendungen() {
        clickWhenClickable(lexikonMenuButton);
        clickWhenClickable(textschluesselVerwendungenMenuItem);
    }

    //--------------------Stücklisten---------------------

    //******************Änderungsnummer*******************


    //***************Referenznummer-Stamm*****************


    //********************Baukasten***********************
    public void baukastenDetailansichtOeffnen(){
        clickWhenClickable(stueckListenMenuButton);
        clickWhenClickable(baukastenDetailansichtMenuItem);
    }

    public void baukastenSucheOeffnen(){
        clickWhenClickable(stueckListenMenuButton);
        clickWhenClickable(baukastenSucheMenuItem);
    }

    public void snrBaukastenVerwendungenOeffnen(){
        clickWhenClickable(stueckListenMenuButton);
        clickWhenClickable(snrBaukastenVerwendungenMenuItem);
    }

    //--------------------Katalog---------------------

    //******************Katalogseiten-Detailansicht*******************
    public void katalogSeitenDetailansichtOeffnen(){
        clickWhenClickable(katalogMenuButton);
        clickWhenClickable(katalogSeitenDetailansichtMenuItem);
    }
}
