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

    @FindBy(className = "priceBox__salePrice")
    public WebElement productPriceTextInCart;

    @FindBy(id = "quantitySelect0-key-0")
    public WebElement productQtySelectBox;

    @FindBy(className = "m-basket__remove")
    public WebElement deleteProductFromCartButton;

    @FindBy(css = "#emtyCart > .m-empty__message > .m-empty__messageTitle")
    public WebElement cartEmptyText;


    public void verifyProductPrice() {
        Assertions.assertEquals(formatPriceStr(readTxtFile().get(1)), productPriceTextInCart.getText());
    }

    public void verifyQty() {
        Assertions.assertEquals("2 adet", productQtySelectBox.getAttribute("aria-label"));
    }

    public void increaseProductQty() {
        Select qtySelect = new Select(productQtySelectBox);
        qtySelect.selectByValue("2");
    }

    public void clickDeleteProductFromCart() {
        deleteProductFromCartButton.click();
    }

    public void verifyProductDeleted() {
        Assertions.assertEquals(fileReader.getValidationsData("cart_page", "cart_empty_message"), cartEmptyText.getText());
    }

}
