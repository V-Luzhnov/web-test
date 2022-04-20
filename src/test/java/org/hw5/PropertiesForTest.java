package org.hw5;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Web UI Java. Homework 5
 *
 * @author Vitalii Luzhnov
 * @version 20.04.2022
 */
public class PropertiesForTest {

    final static java.util.Properties prop = new java.util.Properties();

    private PropertiesForTest() {

    }

    private static void loadProperties() throws IOException {
        try(FileInputStream configFile = new FileInputStream("src/test/resources/propertiesForTest.properties")){
            prop.load(configFile);
        }
    }

    public static String getURL() throws IOException {
        loadProperties();
        return prop.getProperty("PATH_URL");
    }

    public static String getURLbryuki() throws IOException {
        loadProperties();
        return prop.getProperty("PATH_URL_BRYUKI");
    }

    public static String getURLfutbolki() throws IOException {
        loadProperties();
        return prop.getProperty("PATH_URL_FUTBOLKI");
    }

    public static String getSearchObject() throws IOException {
        loadProperties();
        return prop.getProperty("SEARCH_OBJECT");
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
