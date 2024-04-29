package utilities;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;


public class PageHelper extends DriverHooks {

    static String txtFilePath = "src/test/java/data/productInfo.txt";
    public static WebDriver driver = getDriver();

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

    public static void clickRandomVariation(List<WebElement> list) {
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        list.get(randomIndex).click();
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

    public static List<String> readTxtFile() {
        try {
            return Files.readAllLines(Paths.get(txtFilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatPriceStr(String priceStr) {
        String price = priceStr.replaceAll("[^0-9-.]", "");
        return price + ",00 TL";
    }

    public WebElement xpathByString(String locator, String var) {
        return driver.findElement(By.xpath((String.format(locator, var))));
    }

}
