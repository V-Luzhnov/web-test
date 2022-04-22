package org.hw6.mavi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.By.xpath;

public class AuthorizationPage extends AbstractPage {

    @FindBy(xpath = ".//input[@name='USER_LOGIN']")
    private WebElement login;

    @FindBy(xpath = ".//input[@name='USER_PASSWORD']")
    private WebElement password;

    @FindBy(xpath = ".//button[@class='popup-login__btn-login']")
    private WebElement login_button;

    public AuthorizationPage(WebDriver driver) {
        super(driver);
    }

    public void clearLogin() {
        this.login.clear();
    }

    public void clearPassword() {
        this.password.clear();
    }

    public void loginIn(StringBuilder login, StringBuilder password){

        Actions loginIn = new Actions(getDriver());
        loginIn
                .click(this.login)
                .sendKeys(login)
                .click(this.password)
                .sendKeys(password)
                .click(this.login_button)
                .build()
                .perform();
    }


}
