package tests;

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
    public void verifyHomePageIsVisible() {
        homePage = new HomePage();
        homePage.verifyHomePageIsVisible();
    }

    @Test
    @Order(2)
    public void addToCartTest() {
        searchSuggestionPage = new SearchSuggestionPage();
        searchResultPage = new SearchResultPage();
        productDetailPage = new ProductDetailPage();
        cartPage = new CartPage();

        String filePath = "src/test/java/data/keywords.xlsx";
        homePage.searchWithKeyword(PageHelper.getKeywordFromCell(filePath, 0, 0, 0));
        searchSuggestionPage.searchWithKeyword(PageHelper.getKeywordFromCell(filePath, 0, 1, 0));
        //TODO Maybe search result verification could be added
        searchResultPage.writeRandomProductInfoToTxtFileAndClick();
        productDetailPage.selectProductVariation();
        productDetailPage.clickAddToCartButton();
        productDetailPage.verifyProductAddedCart();
        productDetailPage.clickGoToCartButton();
        cartPage.verifyProductPrice();
    }

    @Test
    @Order(3)
    public void increaseQuantityTest(){
        cartPage.increaseProductQty();
        cartPage.verifyQty();
    }

    @Test
    @Order(4)
    public void deleteProductFromCartTest(){
        cartPage.clickDeleteProductFromCart();
        cartPage.verifyProductDeleted();
    }
}