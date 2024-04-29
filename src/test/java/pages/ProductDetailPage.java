package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.PageHelper;

import java.util.List;

public class ProductDetailPage extends PageHelper {

    public ProductDetailPage() {
        WebDriver driver = getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindAll(@FindBy(css = ".m-variation__item:not(.-disabled)"))
    public List<WebElement> productVariations;

    @FindBy(id = "addBasket")
    public WebElement addToCartButton;

    @FindBy(className = "m-notification__title")
    public WebElement productAddedCartText;

    @FindBy(className = "m-notification__button")
    public WebElement goToCartButton;

    public void selectProductVariation() {
        PageHelper.clickRandomVariation(productVariations);
    }

    public void clickAddToCartButton() {
        addToCartButton.click();
    }

    public void verifyProductAddedCart() {
        Assertions.assertEquals(productAddedCartText.getText(), "Sepete Eklendi");
    }

    public void clickGoToCartButton() {
        goToCartButton.click();
    }
}
