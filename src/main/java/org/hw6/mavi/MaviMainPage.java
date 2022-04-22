package org.hw6.mavi;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class MaviMainPage extends AbstractPage {

    @FindBy(xpath = ".//div[@class='cookiesInformBtn']")
    private WebElement cookiesInformBtn;

    public MaviMainPage(WebDriver driver){
        super(driver);
    }

    public By cookiesInformBtnPath() {
       return By.xpath(".//div[@class='cookiesInformBtn']");
    }

    public boolean isDisplayedCookiesInformBtn() {
        return isDisplayed(cookiesInformBtnPath());
    }

    public void clickCookiesInformBtn() {
        this.cookiesInformBtn.click();
    }
}
