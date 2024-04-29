package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.PageHelper;

import java.util.List;

public class SearchResultPage extends PageHelper {

    public SearchResultPage() {
        WebDriver driver = getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindAll(@FindBy(css = ".m-productCard__desc"))
    public List<WebElement> productNames;

    public void clickProductName(String productName) {
        String productNameXpath = String.format("//span[text()='%s']", productName);
        driver.findElement(By.xpath(productNameXpath)).click();
    }

    public void writeRandomProductInfoToTxtFileAndClick() {
        String randomProductName = " " + getRandomListItemText(productNames);
        WebElement productPriceXpath = xpathByString("//span[text()='%s']//ancestor::div[@class='m-productCard__detail']//span[contains(@class,'m-productCard__newPrice')]", randomProductName);
        String[] productInfo = {randomProductName, productPriceXpath.getText()};
        writeTextToTxtFile(productInfo);
        clickProductName(randomProductName);
    }
}
