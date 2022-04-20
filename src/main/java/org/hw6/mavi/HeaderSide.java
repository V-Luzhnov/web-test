package org.hw6.mavi;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import javax.swing.*;

public class HeaderSide extends AbstractPage {

    @FindBy(id = "header-search-input")
    private WebElement headerSearchInput;

    @FindBy(xpath = "(.//button[@type='submit'])[1]")
    private WebElement buttonSearch;

    public HeaderSide(WebDriver driver) {
        super(driver);
    }

    public HeaderSide setHeaderSearchInput(String headerSearchInput){
        this.headerSearchInput.sendKeys(headerSearchInput, Keys.ENTER);
        return this;
    }

    public HeaderSide clickButtonSearch() {
        buttonSearch.click();
        return this;
    }

    public void performSearch(String searchText) {
        headerSearchInput.clear();
        Actions search = new Actions(getDriver());
        search
                .sendKeys(this.headerSearchInput, searchText)
                .click(this.buttonSearch)
                .build()
                .perform();
    }
}
