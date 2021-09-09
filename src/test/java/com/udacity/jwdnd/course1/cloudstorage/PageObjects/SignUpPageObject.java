package com.udacity.jwdnd.course1.cloudstorage.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPageObject {

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "success-msg")
    private WebElement successMessage;

    @FindBy(linkText = "Back to Login")
    private WebElement loginLink;

    public SignUpPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getSuccessMessage(){
        return successMessage;
    }

    public void clickLoginLink(){
        loginLink.click();
    }
    public void setInputFirstName(String message) {
        inputFirstName.sendKeys(message);
    }

    public void setInputLastName(String message) {
        inputLastName.sendKeys(message);
    }

    public void setInputUsername(String message) {
        inputUsername.sendKeys(message);
    }

    public void setInputPassword(String message) {
        inputPassword.sendKeys(message);
    }

    public void submit(){
        submitButton.submit();
    }

    public void signup(String firstName, String lastName, String username, String password){
        setInputFirstName(firstName);
        setInputLastName(lastName);
        setInputUsername(username);
        setInputPassword(password);
        submit();
    }


}
