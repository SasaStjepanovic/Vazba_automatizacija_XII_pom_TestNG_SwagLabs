package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.components.FooterComponent;
import pages.components.HeaderComponent;

public class SwagLabsCheckoutCompletePage extends BasePage{

    HeaderComponent headerComponent;
    FooterComponent footerComponent;

    public SwagLabsCheckoutCompletePage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver);
        footerComponent = new FooterComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#back-to-products")
    WebElement backToProducts;

    @FindBy(xpath = "//div[@class='header_secondary_container']//span[@class='title']")
    WebElement titleAfterLogin;

    public void clickBackToProducts(String expectedText){
        clickElement(backToProducts, "Back to products button");
        compareText(titleAfterLogin,expectedText.toUpperCase());
    }

}