package org.hw6.mavi;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

/**
 * Web UI Java. Homework 6
 *
 * @author Vitalii Luzhnov
 * @version 22.04.2022
 */
public class CommonPages extends AbstractPage {

    private final String XPATH_cookiesInformBtn = ".//div[@class='cookiesInformBtn']";
    private final String XPATH_pageTitle = ".//h1[@class='pageTitle']";

    @FindBy(xpath = XPATH_cookiesInformBtn)
    private WebElement cookiesInformBtn;

    @FindBy(xpath = XPATH_pageTitle)
    private WebElement pageTitle;

    public CommonPages(WebDriver driver){
        super(driver);
    }

    public By cookiesInformBtnPath() {
       return By.xpath(XPATH_cookiesInformBtn);
    }

    public boolean isDisplayedCookiesInformBtn() {
        return isDisplayed(cookiesInformBtnPath());
    }

    public void clickCookiesInformBtn() {
        this.cookiesInformBtn.click();
    }

    public By pageTitlePath() {
        return By.xpath(XPATH_pageTitle);
    }
}
