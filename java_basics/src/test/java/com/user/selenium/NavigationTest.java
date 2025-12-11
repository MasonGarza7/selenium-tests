package com.user.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NavigationTest extends BaseTest {

    @Test
    public void testNavigationAndTitle() {
        logger.info("STARTING Test --- Navigation & Page Title Validation ---");

        // Step 1: Navigate to main page
        String url = "https://the-internet.herokuapp.com/";
        logger.info("Navigating to " + url);
        driver.get(url);

        // Step 2: Locate and click the 'Form Authentication' link
        logger.info("Locating 'Form Authentication' link");
        WebElement link = driver.findElement(By.linkText("Form Authentication"));
        logger.info("Clicking 'Form Authentication' link");
        link.click();

        // Step 3: Validate navigation succeeded
        logger.info("Validating that header text equals 'Login Page'");
        String header = driver.findElement(By.tagName("h2")).getText();
        assertEquals(header, "Login Page", "Unexpected header text: " + header);

        // Step 4: Confirm URL
        String currentUrl = driver.getCurrentUrl();
        logger.info("Current URL after navigation: " + currentUrl);
        assertTrue(currentUrl.contains("login"),
                "Expected 'login' in URL but got " + currentUrl);

        logger.info("ENDING TEST --- Completed successfully: Navigation & Page Title Validation ---");
    }
}
