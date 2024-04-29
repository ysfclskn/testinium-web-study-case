package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import pages.*;
import utilities.DriverHooks;
import utilities.PageHelper;


public class BeymenCartTest extends DriverHooks {

    HomePage homePage;
    SearchSuggestionPage searchSuggestionPage;
    SearchResultPage searchResultPage;
    ProductDetailPage productDetailPage;
    CartPage cartPage;

    @Test
    @Order(1)
    @DisplayName("Verify homepage is opened")
    public void verifyHomePageIsVisible() {
        homePage = new HomePage();
        homePage.verifyHomePageIsVisible();
    }

    @Test
    @Order(2)
    @DisplayName("Verify product added to cart and product price")
    public void addToCartTest() {
        searchSuggestionPage = new SearchSuggestionPage();
        searchResultPage = new SearchResultPage();
        productDetailPage = new ProductDetailPage();
        cartPage = new CartPage();

        String filePath = "src/test/java/data/keywords.xlsx";
        homePage.searchWithKeyword(PageHelper.getKeywordFromCell(filePath, 0, 0, 0));
        searchSuggestionPage.searchWithKeyword(PageHelper.getKeywordFromCell(filePath, 0, 1, 0));
        searchResultPage.writeRandomProductInfoToTxtFileAndClick();
        productDetailPage.selectProductVariation();
        productDetailPage.clickAddToCartButton();
        productDetailPage.verifyProductAddedCart();
        productDetailPage.clickGoToCartButton();
        cartPage.verifyProductPriceQty(1);
        cartPage.verifyQty(1);
    }

    @Test
    @Order(3)
    @DisplayName("Verify increased product quantity")
    public void increaseQuantityTest() {
        cartPage.increaseProductQty();
        cartPage.verifyQty(2);
        cartPage.verifyProductPriceQty(2);
    }

    @Test
    @Order(4)
    @DisplayName("Verify product deleted from cart")
    public void deleteProductFromCartTest() {
        cartPage.clickDeleteProductFromCart();
        cartPage.verifyProductDeleted();
    }
}