package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;

public class SwagLabsTestLogin extends BaseTest{

    @BeforeMethod
    @Parameters({"browser", "env"})
    public void setup(String browser, String env) throws Exception {
        init(browser);
        openApp(env);
    }

    @AfterMethod
    @Parameters({"ScrShootName", "ScrShootDesc", "yesOrNo"})
    public void tearDown(String ScrShootName, String ScrShootDesc, String yesOrNo) throws IOException {
        new BasePage(driver).reportScreenshot(ScrShootName, ScrShootDesc, yesOrNo);
        quit();
    }

    @Test(enabled = true, priority = 1)
    @Parameters({"username", "password", "expectedText", "testType",})
    public void swagLabsLogin(String username, String password, String expectedText, String testType) throws InterruptedException {
        new SwagLabsLoginPage(driver).login( username,  password,  expectedText,  testType);
    }
}