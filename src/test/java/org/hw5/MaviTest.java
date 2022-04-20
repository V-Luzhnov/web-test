package org.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

/**
 * Web UI Java. Homework 5
 *
 * @author Vitalii Luzhnov
 * @version 20.04.2022
 */
public class MaviTest extends AbstractTest{

    @Test
    @Tag("Positive")
    @DisplayName("Успешная авторизация")
    public void authorizationTestPositive() {

        getDriver().findElement(By.xpath(".//span[@class='headerAccBtn']")).click();

        //Если авторизован, то выйти
        if (isDisplayed(By.xpath(".//a[@href='/?logout=yes']"))) {
            getDriver().findElement(By.xpath(".//a[@href='/?logout=yes']")).click();
            getDriver().findElement(By.xpath(".//span[@class='headerAccBtn']")).click();
        }

        Assertions.assertTrue(getDriver().getTitle().contains("Авторизация"), "Страница входа недоступна");

        getDriver().findElement(By.xpath(".//input[@name='USER_LOGIN']")).clear();
        getDriver().findElement(By.xpath(".//input[@name='USER_PASSWORD']")).clear();

        Actions authorisation = new Actions(getDriver());//
        authorisation
                .sendKeys(getDriver().findElement(By.xpath(".//input[@name='USER_LOGIN']")), PropertiesForTest.getL())
                .sendKeys(getDriver().findElement(By.xpath(".//input[@name='USER_PASSWORD']")), PropertiesForTest.getP())
                .click(getDriver().findElement(By.xpath(".//button[@class='popup-login__btn-login']")))
                .build()
                .perform();

        Assertions.assertEquals("Личные данные", getDriver().findElement(By.xpath(".//div[@class='prTitle']")).getText());
    }

    @Test
    @Tag("Positive")
    @DisplayName("Успешный поиск товара")
    public void searchTestPositive() throws IOException {

        getDriver().findElement(By.id("header-search-input")).clear();

        Actions search = new Actions(getDriver());//
        search.sendKeys(getDriver().findElement(By.id("header-search-input")), PropertiesForTest.getSearchObject())
                .click(getDriver().findElement(By.xpath("(.//button[@type='submit'])[1]")))
                .build()
                .perform();

        Assertions.assertEquals("Результаты поиска: " + PropertiesForTest.getSearchObject(), getDriver().findElement(By.xpath("(.//div[@class='catalogCounter'])[2]")).getText());
    }

    @Test
    @Tag("Positive")
    @DisplayName("Успешный добавление товара в корзину")
    public void addToCartTestPositive() throws InterruptedException, IOException {

        getDriver().navigate().to(PropertiesForTest.getURL() + PropertiesForTest.getURLbryuki());

        WebElement buttonSize = getDriver().findElement(By.xpath("(.//div[@class='cardSize']//*[contains(@type,'radio') and not(@disabled)])[1]"));
        WebElement buttonAddToCard = getDriver().findElement(By.xpath("(.//button[@type='submit'])[2]"));

        buttonSize.click();
        buttonAddToCard.click();

        getDriver().navigate().to(PropertiesForTest.getURL() + PropertiesForTest.getURLfutbolki());

        buttonSize = getDriver().findElement(By.xpath("(.//div[@class='cardSize']//*[contains(@type,'radio') and not(@disabled)])[1]"));
        buttonAddToCard = getDriver().findElement(By.xpath("(.//button[@type='submit'])[2]"));

        buttonSize.click();
        buttonAddToCard.click();

        Thread.sleep(5000);

        WebElement buttonBasket = getDriver().findElement(By.xpath(".//span[@class='headerBasketBtn']"));
        buttonBasket.click();

        WebElement buttonOrder = getDriver().findElement(By.xpath(".//a[@class='basketCompactOrder']"));
        buttonOrder.click();

        Assertions.assertTrue(getDriver().findElement(By.xpath(".//div[@class='basketList']")).isDisplayed());
    }

    @Test
    @Tag("Positive")
    @DisplayName("Успешный добавление товара в избранное")
    public void addToFavoritesTestPositive() throws IOException, InterruptedException {

        getDriver().navigate().to(PropertiesForTest.getURL() + PropertiesForTest.getURLbryuki());

        //если товар в избранном, то убрать его из избраного
        if (isDisplayed(By.xpath(".//div[@class='cardFav is-active']"))) {
            getDriver().findElement(By.xpath(".//div[@class='cardFav is-active']")).click();
        }

        WebElement buttonAddToFavorites = getDriver().findElement(By.xpath(".//div[@class='cardFav']"));
        buttonAddToFavorites.click();

        getDriver().navigate().to(PropertiesForTest.getURL() + PropertiesForTest.getURLfutbolki());

        //если товар в избранном, то убрать его из избраного
        if (isDisplayed(By.xpath(".//div[@class='cardFav is-active']"))) {
            getDriver().findElement(By.xpath(".//div[@class='cardFav is-active']")).click();
        }

        buttonAddToFavorites = getDriver().findElement(By.xpath(".//div[@class='cardFav']"));
        buttonAddToFavorites.click();

        Thread.sleep(1000);

        WebElement buttonFavorites = getDriver().findElement(By.xpath(".//span[@class='headerFavBtn']"));
        buttonFavorites.click();

        Assertions.assertTrue(getDriver().findElement(By.xpath(".//div[@class='catalogList']")).isDisplayed());
    }

    @Test
    @Tag("Positive")
    @DisplayName("Успешный переход на страницу Распродажа")
    public void saleTestPositive() {

        getDriver().findElement(By.xpath(".//a[@class='navElLink navElLink_outlet']")).click();
        Assertions.assertEquals("Распродажа", getDriver().findElement(By.xpath(".//h1[@class='pageTitle']")).getText());
    }

    @Test
    @Tag("Positive")
    @DisplayName("Проверка открытия страниц раздела Покупатели в подвале")
    public void footerBuyersTestPositive() {

        getDriver().findElement(By.xpath(".//a[text() = 'Оплата и доставка']")).click();
        Assertions.assertEquals("Оплата", getDriver().findElement(By.xpath(".//h1[@class='pageTitle']")).getText());

        getDriver().findElement(By.xpath(".//a[text() = 'Обмен и возврат']")).click();
        Assertions.assertEquals("Возврат товара", getDriver().findElement(By.xpath(".//h1[@class='pageTitle']")).getText());

        getDriver().findElement(By.xpath(".//a[text() = 'Таблица размеров']")).click();
        Assertions.assertEquals("Таблица размеров", getDriver().findElement(By.xpath(".//h1[@class='pageTitle']")).getText());

        getDriver().findElement(By.xpath(".//a[text() = 'Защита информации']")).click();
        Assertions.assertEquals("Положение о порядке продаж товаров дистанционным способом в интернет-магазине MAVI", getDriver().findElement(By.xpath(".//h1[@class='pageTitle']")).getText());

        getDriver().findElement(By.xpath(".//a[text() = 'Примерка']")).click();
        Assertions.assertEquals("Примерка", getDriver().findElement(By.xpath(".//h1[@class='pageTitle']")).getText());
    }

    boolean isDisplayed(By by) {
        try {
            return getDriver().findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
