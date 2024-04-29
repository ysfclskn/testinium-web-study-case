package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.PageHelper;
import utilities.ResourceFileReader;


public class CartPage extends PageHelper {

    public CartPage() {
        WebDriver driver = getDriver();
        PageFactory.initElements(driver, this);
    }

    ResourceFileReader fileReader = new ResourceFileReader();

    @FindBy(id = "nextBtn")
    public WebElement buyButton;

    @FindBy(css = "#emtyCart > .m-empty__message > .m-empty__messageTitle")
    public WebElement cartEmptyText;

    @FindBy(className = "m-basket__remove")
    public WebElement deleteProductFromCartButton;

    @FindBy(className = "priceBox__salePrice")
    public WebElement productPriceTextInCart;

    @FindBy(id = "quantitySelect0-key-0")
    public WebElement productQtySelectBox;

    @FindBy(className = "m-basket__content")
    public WebElement productContent;

    @FindBy(className = "-success")
    public WebElement updateCartSuccessMessage;

    public void clickDeleteProductFromCart() {
        waitUntilElementInvisibility(updateCartSuccessMessage);
        deleteProductFromCartButton.click();
    }

    public void increaseProductQty() {
        waitUntilElement(buyButton);
        Select qtySelect = new Select(productQtySelectBox);
        qtySelect.selectByValue("2");
    }

    public void verifyProductPriceQty(int qty) {
        String priceStr = readTxtFile().get(1).replace(" TL", "");
        String formattedPrice = formatPriceDouble(Double.parseDouble(priceStr) * qty);
        waitUntilText(productPriceTextInCart, formattedPrice);
    }

    public void verifyQty(int qty) {
        waitUntilElement(productContent);
        Assertions.assertEquals(qty + " adet", productQtySelectBox.getAttribute("aria-label"));
    }

    public void verifyProductDeleted() {
        Assertions.assertEquals(fileReader.getValidationsData("cart_page", "cart_empty_message"), cartEmptyText.getText());
    }

}
