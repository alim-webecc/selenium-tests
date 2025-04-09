package com.webecc.pages.lexikon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.github.javafaker.Faker;
import com.webecc.objects.TextschluesselObjectClass;
import com.webecc.utils.BaseFunctions;

public class TextschluesselDetailansichtPage extends BaseFunctions {
    public TextschluesselDetailansichtPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    Faker faker = new Faker();
    TextschluesselObjectClass textschluesselObjectClass = new TextschluesselObjectClass();

    @FindBy(css = ".clr-control-label.search-input.m-left--10")
    private WebElement textschluesselLabel;

    @FindBy(css = "#searchKey")
    private WebElement textschluesselInputField;

    @FindBy(css = ".primary.stahl")
    private WebElement searchIcon;

    @FindBy(css = "#btnDetailsUsages")
    private WebElement verwendungenButton;

    @FindAll({
            @FindBy(css = "app-label-value div.data-text.truncate.ng-star-inserted")
    })
    private List<WebElement> textschluesselHeaderTexte;

    @FindBy(css = "#btnDetailsNew")
    private WebElement neuanlageButton;

    @FindBy(css = "app-label-value div.data-text.truncate.ng-star-inserted")
    private WebElement falschenTextLabel; // falsche Webelement aus WEBECC-4032ö

    @FindBy(css = "#textType")
    private WebElement textArtDropDown;

    @FindBy(css = "#sedokClassification")
    private WebElement sedokKlassifizierungsDropDown;

    @FindBy(css = "#systemRelevance")
    private WebElement systemKenzeichenDropDown;

    @FindBy(css = "#textLinesForFirstLanguage")
    private WebElement textAreaCad86;

    @FindBy(css = "#btnDetailsSave")
    private WebElement speichernButton;

    @FindBy(css = ".alert.alert-dismissable.alert-success.ng-star-inserted")
    private WebElement successAlert;

    @FindBy(css = ".alert.alert-dismissable.alert-success.ng-star-inserted div")
    private WebElement successAlertContent;

    //@FindBy(css = "input[formcontrolname=\"translationRequired\"]")
    //private WebElement uebersetzungsRelevantCheckBox;

    @FindBy(css = "select[formcontrolname=\"secondLanguage\"]")
    private WebElement zweiteSpracheDropDown;

    public boolean isTheTextschluesselLabelVisible() {
        waitUntilVisible(textschluesselLabel);
        String text = textschluesselLabel.getText();
        // System.out.println(title);
        return text.contains("Textschlüssel");
    }

    public void textschluesselSuchen() {
        int textschluesselId = faker.random().nextInt(100);
        textschluesselObjectClass.setTextschluesselId(textschluesselId);
        sendKeysWhenVisible(textschluesselInputField, String.valueOf(textschluesselId));
        clickWhenVisible(searchIcon);
    }

    public boolean isTheTextschluesselPresent() {
        return isVisible(verwendungenButton);
    }

    public boolean istDasRichtigeTextschluessel() {
        System.out.println("lokalisierte id:" + textschluesselHeaderTexte.get(0).getText());
        System.out.println(
                "In Objectclass gespeicherte id: " + String.valueOf(textschluesselObjectClass.getTextschluesselId()));
        return textschluesselHeaderTexte.get(0).getText().trim()
                .equalsIgnoreCase(String.valueOf(textschluesselObjectClass.getTextschluesselId()));
    }

    public boolean dieTextLabelSollNichtExistieren() {
        clickWhenVisible(neuanlageButton);
        return !isVisible(falschenTextLabel);
    }

    public String mehrZeiligeTextGenerieren() {
        String mehrzeiligeText;
        String ersteZeile = faker.lorem().sentence(15);
        if (ersteZeile.length() <= 40) {
            ersteZeile += faker.lorem().sentence(2);
        }
        textschluesselObjectClass.setTextDeutschErsteZeile(ersteZeile);
        // 2. bis X. Zeile – z. B. 3 weitere Sätze
        StringBuilder rest = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            rest.append(faker.lorem().sentence(8)).append("\n");
        }
        mehrzeiligeText = ersteZeile + "\n" + rest.toString();
        return mehrzeiligeText;
    }

    public boolean kannEineNeueTextschluesselAngelegtWerden() {
        waitUntilVisible(textArtDropDown);
        // clickWhenVisible(textArtDropDown);
        Select selectTextart = new Select(textArtDropDown);
        selectTextart.selectByVisibleText(" CAD (86) ");
        waitUntilVisible(sedokKlassifizierungsDropDown);
        clickWhenVisible(sedokKlassifizierungsDropDown);
        Select selectSedokKlassifizierung = new Select(sedokKlassifizierungsDropDown);
        selectSedokKlassifizierung.selectByValue("0: G");
        waitUntilVisible(systemKenzeichenDropDown);
        clickWhenVisible(systemKenzeichenDropDown);
        Select selectSystemKenzeichen = new Select(systemKenzeichenDropDown);
        selectSystemKenzeichen.selectByValue("2: all");
        sendKeysWhenVisible(textAreaCad86, mehrZeiligeTextGenerieren());
        clickWhenVisible(speichernButton);
        if (isVisible(successAlertContent)) {
            String successAlertText = successAlertContent.getText();
            String[] teile = successAlertText.split(" ");
            List<String> textTeile = new ArrayList<>(Arrays.asList(teile));
            String neueTextschluesselId = textTeile.get(1);
            textschluesselObjectClass.setTextschluesselId(Integer.parseInt(neueTextschluesselId));
        }
        return isVisible(successAlert);
    }

    public boolean istNeuAngelegteTextSchluesselRichtigAngezeigt() {
        int n = 0;
        if (isVisible(textschluesselHeaderTexte.get(0))) {
            if (textschluesselHeaderTexte.get(0).getText()
                    .equalsIgnoreCase(String.valueOf(textschluesselObjectClass.getTextschluesselId()))) {
                n += 1;
                System.out.println("TextschlüsselId wurde Richtig angezeigt.");
            }
        }
        if (isVisible(textschluesselHeaderTexte.get(1))) {
            if (textschluesselObjectClass.getTextDeutschErsteZeile()
                    .contains(textschluesselHeaderTexte.get(1).getText())) {
                n += 1;
                System.out.println("Textschlüsseltext wurde Richtig angezeigt.");
            }
            System.out.println("Text: " + textschluesselHeaderTexte.get(1).getText());
            System.out.println("Text aus Objectclass: " + textschluesselObjectClass.getTextDeutschErsteZeile());
        }
        System.out.println("n ist: " + n);
        return n == 2;
    }

    public WebElement waitForTranslationCheckbox() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[formcontrolname='translationRequired']")
        ));
    }
    public boolean istZweiteSpracheSichtbarkeitKorrekt() {
        try {
            WebElement checkbox = waitForTranslationCheckbox();
            System.out.println("Checkbox wurde gefunden.");

            boolean isSelected = checkbox.isSelected();
            boolean dropdownVisible = isVisible(zweiteSpracheDropDown);

            if (isSelected && dropdownVisible) return true;
            if (!isSelected && !dropdownVisible) return true;
        } catch (TimeoutException e) {
            System.out.println("Checkbox wurde NICHT gefunden.");
        }
        return false;
    }
}
