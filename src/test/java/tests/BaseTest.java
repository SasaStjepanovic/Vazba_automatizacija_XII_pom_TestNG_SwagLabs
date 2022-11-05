package tests;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import selenium.DriverManager;
import selenium.DriverManagerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    DriverManager driverManager;

    public void init(String browser) throws Exception {
        driverManager = DriverManagerFactory.getDriverManager(browser);
        driver = driverManager.getWebDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void init(String browser, String wait) throws Exception {
        driverManager = DriverManagerFactory.getDriverManager(browser);
        driver = driverManager.getWebDriver();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(wait), TimeUnit.SECONDS);
    }

    public void init(String browser, String wait, int width, int height) throws Exception {
        driverManager = DriverManagerFactory.getDriverManager(browser);
        driver = driverManager.getWebDriver();
        Dimension dimension = new Dimension(width,height);
        driver.manage().window().setSize(dimension);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(wait), TimeUnit.SECONDS);
    }


    public void openApp(String env) throws Exception {
        switch (env.toUpperCase()){
            case "QA" :{
                driver.get("https://www.saucedemo.com/");
            }
            break;
            case "PROD" :{
                driver.get("https://www.gmail.com/");
            }
            break;
            case "BIZ" :{
                driver.get("https://www.yahoo.com/");
            }
            break;
            default: throw new Exception("No such environment: "+env);
            // dodati jos neki case
            // dodati default blok
        }
    }

    public void quit(){
        driverManager.quitWebDriver();
    }

    public void pause(int sec) throws InterruptedException {
        Thread.sleep(sec*1000);
    }


}