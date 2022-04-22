package org.hw6.mavi;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.hw5.PropertiesForTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

/**
 * Web UI Java. Homework 5
 *
 * @author Vitalii Luzhnov
 * @version 20.04.2022
 */
public abstract class AbstractTest {

    private static WebDriver driver;

    @BeforeAll
    static void init(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @BeforeEach
    void goTo(){
        Assertions.assertDoesNotThrow( ()-> driver.navigate().to(PropertiesForTest.getURL()),
                "Страница недоступна");

        if (isDisplayed(By.xpath(".//div[@class='cookiesInformBtn']"))) {
            getDriver().findElement(By.xpath(".//div[@class='cookiesInformBtn']")).click();
        }
    }

    boolean isDisplayed(By by) {
        try {
            return getDriver().findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @AfterAll
    static void close(){
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
