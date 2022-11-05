package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.components.FooterComponent;
import pages.components.HeaderComponent;

import java.util.List;

public class SwagLabsInventoryPage extends BasePage{

    public HeaderComponent headerComponent;
    FooterComponent footerComponent;

    public SwagLabsInventoryPage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver);
        footerComponent = new FooterComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".shopping_cart_link")
    WebElement shoppingCart;

    @FindBy(xpath = "//div[@class='header_secondary_container']//span[@class='title']")
    WebElement titleAfterLogin;

    public void clickShoppingCart(String expectedText){
        clickElement(shoppingCart, "Shopping cart icon");
        compareText(titleAfterLogin,expectedText.toUpperCase());
    }

    public void clickAddProductByName(String name, String textofButtonBeforePressed, String textofButtonAfterPressed) throws InterruptedException {
        compareText(driver.findElement(By.xpath("//*[text()='"+name+"']/../../..//div[@class='pricebar']//button")),textofButtonBeforePressed.toUpperCase());
        clickElement(driver.findElement(By.xpath("//*[text()='"+name+"']/../../..//div[@class='pricebar']//button")), "Add product "+name);
        compareText(driver.findElement(By.xpath("//*[text()='"+name+"']/../../..//div[@class='pricebar']//button")),textofButtonAfterPressed.toUpperCase());
    }

    public void clickAddProductByIndex(int i,String textofButtonBeforePressed, String textofButtonAfterPressed){
        compareText(driver.findElement(By.xpath("//div[@class='inventory_list']/div["+i+"]//button")),textofButtonBeforePressed.toUpperCase());
        clickElement(driver.findElement(By.xpath("//div[@class='inventory_list']/div["+i+"]//button")), "Add product "+i);
        compareText(driver.findElement(By.xpath("//div[@class='inventory_list']/div["+i+"]//button")),textofButtonAfterPressed.toUpperCase());
    }

    public void checkItems(String[] menuItems) throws InterruptedException {

        List<WebElement> menu = driver.findElements(By.cssSelector(".bm-item-list>a"));
        Thread.sleep(1000);
        for (int i = 0; i < menu.size(); i++) {
            String item = menu.get(i).getText();
            Assert.assertEquals(item, menuItems[i], "Menu item not displayed");
            System.out.println("Menu item: " +item+ " is displayed");
            Assert.assertTrue(menu.get(i).isDisplayed(), menuItems[i]);

        }
    }

}