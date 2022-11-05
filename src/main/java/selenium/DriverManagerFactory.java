package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManagerFactory {


    public static DriverManager getDriverManager(String type) throws Exception {
        DriverManager driverManager;

        switch (type.toUpperCase()){
            case "CHROME":{
                driverManager = new ChromeDriverManager();
            }
            break;
            case "CHROME_H":{
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--window-size=1920,1080");
                driverManager = new ChromeDriverManager();
            }
            break;
            case "FIREFOX":{
                driverManager = new FirefoxDriverManager();
            }
            break;
            default: throw new Exception("No such Browser configuration!");
        }
        return driverManager;
    }

}



//case "CHROME_H":{
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        options.addArguments("--window-size=1920,1080");
////                WebDriver driver = new ChromeDriver(options);
//        }