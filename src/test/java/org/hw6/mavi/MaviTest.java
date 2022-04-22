package org.hw6.mavi;

import org.hw5.PropertiesForTest;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MaviTest extends AbstractTest {

    @Test
    @Tag("Positive")
    @DisplayName("Успешная авторизация")
    public void authorizationTestPositive() {

        new HeaderSide(getDriver()).clickHeaderAccBtn();

        //Если авторизован, то выйти
        if (new AccountMenu(getDriver()).isDisplayedLogoutBtn()) {
            new AccountMenu(getDriver()).clickLogoutBtn();
            new HeaderSide(getDriver()).clickHeaderAccBtn();
        }

        Assertions.assertTrue(getDriver().getTitle().contains("Авторизация"), "Страница входа недоступна");

        new AuthorizationPage(getDriver()).clearLogin();
        new AuthorizationPage(getDriver()).clearPassword();

        new AuthorizationPage(getDriver()).loginIn(getL(), getP());

        Assertions.assertEquals("Личные данные", getDriver().findElement(By.xpath(".//div[@class='prTitle']")).getText());
    }
}
