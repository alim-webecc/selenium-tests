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
        clickWhenVisible(lexikonMenuButton);
        clickWhenVisible(textschluesselDetailansichtMenuItem);
    }

    public void openTheLexikonSuche() {
        clickWhenVisible(lexikonMenuButton);
        clickWhenVisible(lexikonSucheMenuItem);
    }

    public void openTheStatistikDerUebersetzungen() {
        clickWhenVisible(lexikonMenuButton);
        clickWhenVisible(statistikDerUebersetzungMenuItem);
    }

    public void openTheUebersetzungsExport() {
        clickWhenVisible(lexikonMenuButton);
        clickWhenVisible(uebersetzungsExportMenuItem);
    }

    public void openTheUebersetzungsImport() {
        clickWhenVisible(lexikonMenuButton);
        clickWhenVisible(uebersetzungsImportMenuItem);
    }

    public void openTheTextschluesselVerwendungen() {
        clickWhenVisible(lexikonMenuButton);
        clickWhenVisible(textschluesselVerwendungenMenuItem);
    }

    //--------------------Stücklisten---------------------

    //******************Änderungsnummer*******************


    //***************Referenznummer-Stamm*****************


    //********************Baukasten***********************
    public void baukastenDetailansichtOeffnen(){
        clickWhenVisible(stueckListenMenuButton);
        clickWhenVisible(baukastenDetailansichtMenuItem);
    }

    public void baukastenSucheOeffnen(){
        clickWhenVisible(stueckListenMenuButton);
        clickWhenVisible(baukastenSucheMenuItem);
    }

    public void snrBaukastenVerwendungenOeffnen(){
        clickWhenVisible(stueckListenMenuButton);
        clickWhenVisible(snrBaukastenVerwendungenMenuItem);
    }

    //--------------------Katalog---------------------

    //******************Katalogseiten-Detailansicht*******************
    public void katalogSeitenDetailansichtOeffnen(){
        clickWhenVisible(katalogMenuButton);
        clickWhenVisible(katalogSeitenDetailansichtMenuItem);
    }
}
