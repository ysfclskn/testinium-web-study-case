package utilities;

import config.BaseConfig;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(TestResultLogger.class)
public class DriverHooks {
    static WebDriver driver;
    static BaseConfig baseConfig = new BaseConfig();

    public static WebDriver getDriver() {
        String browser = baseConfig.getBrowser();
        if (driver == null) {
            switch (browser) {
                case "chrome" -> {
                    ChromeOptions options = new ChromeOptions();
                    if (baseConfig.getHeadless()) options.addArguments("--headless=new");
                    options.addArguments("-incognito");
                    options.setBrowserVersion("stable");
                    driver = new ChromeDriver(options);
                }
                default -> LogUtil.logInfo(browser + "not found");
            }
        }
        return driver;
    }


    @BeforeAll
    public void openBrowserWithUrl() {
        LogUtil.logInfo("Test Started");
        getDriver();
        driver.get(baseConfig.getUrl());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new HomePage().handleAllModals();
    }

    @AfterAll
    public void tearDown() {
        LogUtil.logInfo("Test Ended");
        driver.quit();
    }
}
