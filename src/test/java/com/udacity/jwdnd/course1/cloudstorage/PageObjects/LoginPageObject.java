package com.udacity.jwdnd.course1.cloudstorage.PageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject {
    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "signup-link")
    private WebElement signupLink;


    @FindBy(tagName = "button")
    private WebElement logoutButton;

    public LoginPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setInputUsername(String message) {
        inputUsername.sendKeys(message);
    }

    public void setInputPassword(String message) {
        inputPassword.sendKeys(message);
    }

    public void Login(){
        submitButton.click();
    }

    public void Logout() {logoutButton.click();}

    public void Register(){
        signupLink.click();
    }

    public void login(String username, String password){
        setInputUsername(username);
        setInputPassword(password);
        Login();
    }
}
