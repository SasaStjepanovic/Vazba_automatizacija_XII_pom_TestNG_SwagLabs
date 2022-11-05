package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.SwagLabsInventoryPage;
import pages.SwagLabsLoginPage;
import pages.components.HeaderComponent;

public class SwagLabsTestLogout extends BaseTest{

    @BeforeMethod
    @Parameters({"browser", "env","username", "password", "expectedText", "testType"})
    public void setup(String browser, String env, String username, String password, String expectedText, String testType) throws Exception {
        init(browser);
        openApp(env);
        new SwagLabsLoginPage(driver).login(username, password, expectedText, testType);
    }

    @AfterMethod
    public void tearDown(){
        quit();
    }

    @Test(enabled = true)
    @Parameters({"testLogType", "logoutMessage"})
    public void swagLabsLogout(String testLogType, String logoutMessage) throws InterruptedException {
        String[] menuItems = {"ALL ITEMS", "ABOUT", "LOGOUT", "RESET APP STATE"};
        SwagLabsInventoryPage slip =new SwagLabsInventoryPage(driver);
        slip.headerComponent.clickMenu();
        slip.checkItems(menuItems);
        slip.headerComponent.logout(testLogType, logoutMessage);
    }

}