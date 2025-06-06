package com.webecc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webecc.utils.BaseFunctions;

public class LoginPage extends BaseFunctions {

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = ".login-submit.stahl")
    private WebElement loginButton;

    @FindBy(css = ".modal-content.ng-tns-c1772524388-2")
    private WebElement secondLoginSessionPopup;

    @FindBy(css = ".btn-primary.stahl")
    private WebElement okButton;

    public void login(String username) {
        sendKeysWhenVisibleAndClickable(usernameField, username);
        sendKeysWhenVisibleAndClickable(passwordField, "#Vancouver.Munich0710");
        clickWhenClickable(loginButton);
        if (isVisible(secondLoginSessionPopup, By.cssSelector(".modal-content.ng-tns-c1772524388-2"))) {
            clickWhenClickable(okButton);
        }
        waitForOverlayToDisappear();
    }
    public boolean istBenutzerEingabefeldSichtbar(){
        waitLongerUntilVisible(usernameField, By.cssSelector("#username"));
        return isVisible(usernameField, By.cssSelector("#username"));
    }
}
