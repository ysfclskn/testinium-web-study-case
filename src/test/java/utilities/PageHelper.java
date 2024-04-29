package utilities;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Random;


public class PageHelper extends DriverHooks {

    static String txtFilePath = "src/test/java/resources/data/productInfo.txt";
    public static WebDriver driver = getDriver();
    static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public static void clickRandomVariation(List<WebElement> list) {
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        list.get(randomIndex).click();
    }
    
    public static boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public static String getKeywordFromCell(String filePath, int sheetIndex, int row, int col) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            Row cellRow = sheet.getRow(row);
            Cell cell = cellRow.getCell(col);
            return cell.getStringCellValue();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getRandomListItemText(List<WebElement> list) {
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex).getText();
    }

    public static String formatPriceDouble(Double priceDouble) {
        return priceDouble + ",00 TL";
    }

    public static List<String> readTxtFile() {
        try {
            return Files.readAllLines(Paths.get(txtFilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement xpathByString(String locator, String var) {
        return driver.findElement(By.xpath((String.format(locator, var))));
    }

    public static void waitUntilText(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static void waitUntilElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void writeTextToTxtFile(String[] texts) {
        try {
            PrintWriter writer = new PrintWriter(txtFilePath);
            for (String text : texts) {
                writer.println(text);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void waitUntilElementInvisibility(WebElement element) {
        int count = 0;
        try {
            while (element.isDisplayed() && count < 10) {
                count++;
                Thread.sleep(500L);
            }
        } catch (StaleElementReferenceException | InterruptedException | NoSuchElementException ignored) {

        }

    }
}
