package com.user.selenium;

import com.user.selenium.utils.LoggerUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import com.user.selenium.listeners.ScreenshotListener;

import java.io.File;
import java.util.logging.Logger;

@Listeners(ScreenshotListener.class)
public class BaseTest {


    protected WebDriver driver;
    protected static final Logger logger = LoggerUtil.getLogger();
    
    public WebDriver getDriver() {
        return driver;
    }

    private static final String RESULTS_DIR = "results";
    private static final String LOGS_DIR = RESULTS_DIR + "/logs";
    private static final String SCREENSHOTS_DIR = RESULTS_DIR + "/screenshots";

    @BeforeMethod
    public void setUp() {
        createResultsDirectories();

        logger.info("Starting WebDriver for test");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        if (headless) {
            logger.info("Headless mode ENABLED");
            options.addArguments("--headless=new");
        } else {
            logger.info("Headless mode DISABLED");
        }

        driver = new ChromeDriver(options);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            logger.info("Quitting WebDriver for test");
            driver.quit();
        }
    }

    private void createResultsDirectories() {
        createDir(RESULTS_DIR);
        createDir(LOGS_DIR);
        createDir(SCREENSHOTS_DIR);
    }

    private void createDir(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
