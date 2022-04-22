package org.hw6.mavi;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountMenu extends AbstractPage {

    @FindBy(xpath = ".//a[@href='/?logout=yes']")
    private WebElement logoutBtn;

    public AccountMenu(WebDriver driver) {
        super(driver);
    }

    By logoutBtnPath() {
        return By.xpath(".//a[@href='/?logout=yes']");
    }

    public boolean isDisplayedLogoutBtn() {
        return isDisplayed(logoutBtnPath());
    }

    public void clickLogoutBtn() {
        this.logoutBtn.click();
    }
}
