package org.hw3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
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
public class AddToCart {
    static Properties prop = new Properties();

    public static void main(String[] args) throws IOException {
        loadProperties();

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.get(prop.getProperty("PATH_URL") + "/catalog/odezhda/sportivnye-bryuki/M000169-34314/");

        WebElement buttonSize = driver.findElement(By.xpath("(//div[@class='cardSize']//*[contains(@type,'radio') and not(@disabled)])[1]"));
//        WebElement buttonSizeL = driver.findElement(By.id("size_1_1465774"));
//        WebElement buttonSizeM = driver.findElement(By.id("size_1_1465773"));
//        WebElement buttonSizeS = driver.findElement(By.id("size_1_1465772"));
        WebElement buttonAddToCard = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));

        buttonSize.click();
//        buttonSizeL.click();
//        buttonSizeM.click();
//        buttonSizeS.click();
        buttonAddToCard.click();

        driver.get(prop.getProperty("PATH_URL") + "/catalog/odezhda/futbolki/M0610278-70132/");

        buttonSize = driver.findElement(By.xpath("(//div[@class='cardSize']//*[contains(@type,'radio') and not(@disabled)])[1]"));
//        buttonSizeL = driver.findElement(By.id("size_1_1465482"));
//        buttonSizeM = driver.findElement(By.id("size_1_1465481"));
//        buttonSizeS = driver.findElement(By.id("size_1_1465480"));
        buttonAddToCard = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));

        buttonSize.click();
//        buttonSizeL.click();
//        buttonSizeM.click();
//        buttonSizeS.click();
        buttonAddToCard.click();

        driver.get(prop.getProperty("PATH_URL") + "/order/cart/");

//        driver.quit();
    }

    private static void loadProperties() throws IOException {
        try(FileInputStream configFile = new FileInputStream("src/main/resources/hw3.properties")){
            prop.load(configFile);
        }
    }
}