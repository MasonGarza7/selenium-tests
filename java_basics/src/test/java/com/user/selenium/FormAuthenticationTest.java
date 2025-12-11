package com.user.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class FormAuthenticationTest extends BaseTest {

    @Test
    public void testFormAuthenticationValidAndInvalid() {
        logger.info("STARTING Test --- Form Authentication ---");

        String baseUrl = "https://the-internet.herokuapp.com/login";
        driver.get(baseUrl);
        logger.info("Navigated to " + baseUrl);

        // Invalid login
        logger.info("Attempting invalid login...");
        driver.findElement(By.id("username")).sendKeys("invalidUser");
        driver.findElement(By.id("password")).sendKeys("wrongPass");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String errorMsg = driver.findElement(By.id("flash")).getText();
        logger.info("Invalid login message: " + errorMsg);
        assertTrue(errorMsg.contains("Your username is invalid!"));

        // Valid login
        logger.info("Attempting valid login...");
        driver.get(baseUrl);
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        logger.info("Waiting for success message to appear...");
        WebElement successElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("flash"))
        );

        String successMsg = successElement.getText();
        logger.info("Valid login message: " + successMsg);
        assertTrue(successMsg.contains("You logged into a secure area!"));

        logger.info("ENDING Test --- Completed successfully: Form Authentication ---");
    }
}
