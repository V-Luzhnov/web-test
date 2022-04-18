package org.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;

public class MaviTest extends AbstractTest{

    @Test
    @Tag("Positive")
    public void authorizationTestPositive() throws InterruptedException {

        getDriver().findElement(By.xpath("//span[@class='headerAccBtn']")).click();

        //Если авторизован, то выйти
        if (isDisplayed(By.xpath("//a[@href='/?logout=yes']"))) {
            getDriver().findElement(By.xpath("//a[@href='/?logout=yes']")).click();
            getDriver().findElement(By.xpath("//span[@class='headerAccBtn']")).click();
        }

        Assertions.assertTrue(getDriver().getTitle().contains("Авторизация"), "Страница входа недоступна");

        getDriver().findElement(By.xpath(".//input[@name='USER_LOGIN']")).clear();
        getDriver().findElement(By.xpath(".//input[@name='USER_PASSWORD']")).clear();

        Actions search = new Actions(getDriver());//
        search.sendKeys(getDriver().findElement(By.xpath(".//input[@name='USER_LOGIN']")), getL())
                .sendKeys(getDriver().findElement(By.xpath(".//input[@name='USER_PASSWORD']")), getP())
                .click(getDriver().findElement(By.xpath(".//button[@class='popup-login__btn-login']")))
                .build()
                .perform();

        Assertions.assertEquals("Личные данные", getDriver().findElement(By.xpath(".//div[@class='prTitle']")).getText());
    }

    boolean isDisplayed(By by) {
        try {
            return getDriver().findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
