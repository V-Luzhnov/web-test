package org.hw6.mavi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HeaderSide extends AbstractPage {

    @FindBy(id = "header-search-input")
    private WebElement headerSearchInput;

    @FindBy(xpath = "(.//button[@type='submit'])[1]")
    private WebElement searchBtn;

    @FindBy(xpath = ".//span[@class='headerAccBtn']")
    private WebElement headerAccBtn;

    public HeaderSide(WebDriver driver) {
        super(driver);
    }

    public HeaderSide setHeaderSearchInput(String headerSearchInput){
        this.headerSearchInput.sendKeys(headerSearchInput);
        return this;
    }

    public HeaderSide clickSearchBtn() {
        this.searchBtn.click();
        return this;
    }

    public void clickHeaderAccBtn() {
        this.headerAccBtn.click();
    }

    public void performSearch(String searchText) {
        headerSearchInput.clear();
        Actions search = new Actions(getDriver());
        search
                .sendKeys(this.headerSearchInput, searchText)
                .click(this.searchBtn)
                .build()
                .perform();
    }
}
