package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.PageHelper;

public class SearchSuggestionPage extends PageHelper {

    public SearchSuggestionPage() {
        WebDriver driver = getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "o-searchSuggestion__input")
    public WebElement suggestionSearchBar;

    public void searchWithKeyword(String keyword) {
        suggestionSearchBar.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        suggestionSearchBar.sendKeys(keyword);
        suggestionSearchBar.sendKeys(Keys.RETURN);
    }
}