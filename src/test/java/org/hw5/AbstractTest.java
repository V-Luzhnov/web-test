package org.hw5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AbstractTest {

    private static WebDriver driver;
    private static final Properties prop = new Properties();

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
        Assertions.assertDoesNotThrow( ()-> driver.navigate().to("https://ru.mavi.com/"),
                "Страница недоступна");
    }

    @AfterAll
    static void close(){
//        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static StringBuilder getL() { return readFile("l"); }

    public static StringBuilder getP() { return readFile("p"); }

    public static StringBuilder readFile(String n) {

        StringBuilder rez = new StringBuilder();
        try(FileReader reader = new FileReader("src/test/resources/test_" + n + ".txt"))
        {
            int c;
            while((c=reader.read())!=-1) {
                rez.append((char) c);
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        return rez;
    }
}
