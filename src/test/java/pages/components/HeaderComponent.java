package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.BasePage;

import java.util.List;

public class HeaderComponent extends BasePage {

    public HeaderComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#react-burger-menu-btn")
    WebElement menu;

    @FindBy(xpath = "//div//span[@class='shopping_cart_badge']")
    WebElement cartExist;

    @FindBy(xpath = "//div//a[@class='shopping_cart_link']")
    WebElement cartNotExist;

    @FindBy(css = ".bm-item-list")
    WebElement menuItems;

    @FindBy(css = "#logout_sidebar_link")
    WebElement logout;

    @FindBy(css = ".submit-button.btn_action,[value]:nth-child(2)")
    WebElement logoutDescriptionPass;

    @FindBy(xpath = "//span[text()='Products']")
    WebElement logoutDescriptionFail;

    public void clickMenu(){
        clickElement(menu,"Menu button");
    }

    public void clickLogoutButton(){
        clickElement(logout,"Logout button");
    }

    public void logout(String testLogType, String logoutMessage) throws InterruptedException {
        clickLogoutButton();
        Thread.sleep(2000);
        if (testLogType.equals("positive")) {
            String LoginText = driver.findElement(By.cssSelector(".submit-button.btn_action,[value]:nth-child(2)")).getAttribute("value");
            Assert.assertEquals(LoginText, logoutMessage);
        } else {
            compareText(logoutDescriptionFail,logoutMessage);
        }
    }

    public void checkHeaderShopingCart(String expectedCarts, String emptyYesNo){
        scroll("0", "0");

        if(emptyYesNo.equalsIgnoreCase("No")){
            compareText(cartExist,expectedCarts);
        } else {
            scrollToElement(cartNotExist);
            compareText(cartNotExist,expectedCarts);
        }
    }


}
