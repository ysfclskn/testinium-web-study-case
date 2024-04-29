package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.DriverHooks;
import utilities.PageHelper;

public class HomePage extends PageHelper {

    public HomePage() {
        WebDriver driver = DriverHooks.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button#onetrust-accept-btn-handler")
    public WebElement acceptCookieButton;

    @FindBy(css = ".bwi-account-o")
    public WebElement accountButton;

    @FindBy(css = ".bwi-close > svg")
    public WebElement closeGenderSelectPopup;

    @FindBy(css = ".horizontal .dn-slide-deny-btn")
    public WebElement denyNotificationsButton;

    @FindBy(css = ".o-header__search--wrapper > input")
    public WebElement searchBar;

    public void handleAllModals() {
        acceptCookieButton.click();
        closeGenderSelectPopup.click();
        denyNotificationsButton.click();
    }

    public void searchWithKeyword(String keyword) {
        searchBar.sendKeys(keyword);
    }

    public void verifyHomePageIsVisible() {
        isElementVisible(accountButton);
    }

}
