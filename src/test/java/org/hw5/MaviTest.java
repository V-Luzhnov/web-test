package org.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.BooleanSupplier;

public class MaviTest extends AbstractTest{

    @Test
    @Tag("Positive")
    public void authorizationTestPositive() throws InterruptedException {

        getDriver().findElement(By.xpath("//span[@class='headerAccBtn']")).click();

        //Если авторизован, то выйти
        if (isDisplayed(By.xpath("//a[@href='/?logout=yes']"))) {
            getDriver().findElement(By.xpath("//a[@href='/?logout=yes']")).click();
        }

//        new WebDriverWait(getDriver(), Duration.ofSeconds(3)).until(ExpectedConditions.urlContains("https://ru.mavi.com/account/profile/"));
        Assertions.assertTrue(getDriver().getTitle().contains("Авторизация"), "Страница входа недоступна");

        Actions search = new Actions(getDriver());//
        search.sendKeys(getDriver().findElement(By.xpath(".//input[@name='USER_LOGIN']")), getL())
                .sendKeys(getDriver().findElement(By.xpath(".//input[@name='USER_PASSWORD']")), getP())
                .click(getDriver().findElement(By.xpath(".//button[@class='popup-login__btn-login']")))
                .build()
                .perform();

//        Thread.sleep(100); //время на авторизацию
        Assertions.assertEquals("Личные данные", getDriver().findElement(By.xpath(".//div[@class='prTitle']")).getText());

//        //Выйти, если надо
//        if (isDisplayed(By.xpath("//a[@href='/?logout=yes']"))) {
//            getDriver().findElement(By.xpath("//a[@href='/?logout=yes']")).click();
//        }
    }

    boolean isDisplayed(By by) {
        try {
            return getDriver().findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
