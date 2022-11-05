package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;
import pages.components.HeaderComponent;

public class SwagLabsTestEndToEnd extends BaseTest{

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
    public void buyProduct(String testLogType, String logoutMessage) throws InterruptedException {
        String[] menuItems = {"ALL ITEMS", "ABOUT", "LOGOUT", "RESET APP STATE"};
        SwagLabsInventoryPage ip = new SwagLabsInventoryPage(driver);

        ip.clickAddProductByIndex(1, "ADD TO CART", "REMOVE");
        ip.clickAddProductByIndex(2, "ADD TO CART", "REMOVE");
        ip.clickAddProductByIndex(3, "ADD TO CART", "REMOVE");
        ip.clickAddProductByIndex(4, "ADD TO CART", "REMOVE");
        ip.clickAddProductByName("Sauce Labs Onesie", "ADD TO CART", "REMOVE");
        ip.clickAddProductByIndex(6, "ADD TO CART", "REMOVE");

        HeaderComponent hp = new HeaderComponent(driver);

        hp.checkHeaderShopingCart("6", "No");
        ip.clickShoppingCart("Your Cart");

        hp.checkHeaderShopingCart("6", "No");
        new SwagLabsCartPage(driver).clickCheckout("Checkout: Your Information");

        hp.checkHeaderShopingCart("6", "No");
        new SwagLabsCheckoutPage(driver).checkout("Test","Test", "11212", "Checkout: Overview");
//        new SwagLabsCheckoutPage(driver).checkout();

        hp.checkHeaderShopingCart("6", "No");
        new SwagLabsCheckoutOverviewPage(driver).clickFinish("Checkout: Complete!");
        hp.checkHeaderShopingCart("", "Yes");

        new SwagLabsCheckoutCompletePage(driver).clickBackToProducts("Products");
        hp.checkHeaderShopingCart("", "Yes");

//        SwagLabsInventoryPage slip =new SwagLabsInventoryPage(driver);
        hp.clickMenu();
        ip.checkItems(menuItems);
        hp.logout(testLogType, logoutMessage);
    }

    //add assertions
}