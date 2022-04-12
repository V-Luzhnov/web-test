package org.hw3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Web UI Java. Homework 3
 *
 * @author Vitalii Luzhnov
 * @version 12.04.2022
 */
public class App1 {

    static Properties prop = new Properties();

    public static void main(String[] args) throws IOException {

        loadProperties();

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.get(prop.getProperty("PATH_URL") + "account/profile/");

        WebElement login = driver.findElement(By.xpath(".//input[@name='USER_LOGIN']"));
        WebElement password = driver.findElement(By.xpath(".//input[@name='USER_PASSWORD']"));
        WebElement buttonEntry = driver.findElement(By.xpath(".//button[@class='popup-login__btn-login']"));

        login.sendKeys(prop.getProperty("USER_LOGIN"));
        password.sendKeys(prop.getProperty("USER_PASSWORD"));
        buttonEntry.click();

//        driver.quit();
    }

    private static void loadProperties() throws IOException {
        try(FileInputStream configFile = new FileInputStream("src/main/resources/hw3.properties")){
            prop.load(configFile);
        }
    }
}