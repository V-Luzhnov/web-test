package org.hw3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Web UI Java. Homework 3
 *
 * @author Vitalii Luzhnov
 * @version 12.04.2022
 */
public class App2 {
    static Properties prop = new Properties();

    public static void main(String[] args) throws IOException {
        loadProperties();

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.get(prop.getProperty("PATH_URL"));

        WebElement search = driver.findElement(By.id("header-search-input"));
        WebElement buttonSearch = driver.findElement(By.xpath(".//button[@type='submit'][1]"));

        search.sendKeys("Рубашка");
        buttonSearch.click();

//        driver.quit();
    }

    private static void loadProperties() throws IOException {
        try(FileInputStream configFile = new FileInputStream("src/main/resources/hw3.properties")){
            prop.load(configFile);
        }
    }
}