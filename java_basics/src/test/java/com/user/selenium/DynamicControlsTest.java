package com.user.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class DynamicControlsTest extends BaseTest {

    @Test
    public void testDynamicControls() {
        logger.info("STARTING Test --- Dynamic Controls ---");

        String url = "https://the-internet.herokuapp.com/dynamic_controls";
        driver.get(url);
        logger.info("Navigated to " + url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Remove Checkbox
        logger.info("Clicking 'Remove' button to remove checkbox");
        WebElement removeButton = driver.findElement(By.cssSelector("button[onclick='swapCheckbox()']"));
        removeButton.click();

        logger.info("Waiting for checkbox to be removed");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")));
        String message = driver.findElement(By.id("message")).getText();
        logger.info("Message after removal: " + message);
        assertTrue(message.contains("It's gone!"));

        // Add Checkbox
        logger.info("Clicking 'Add' button to add checkbox back");
        WebElement addButton = driver.findElement(By.cssSelector("button[onclick='swapCheckbox()']"));
        addButton.click();

        logger.info("Waiting for checkbox to reappear");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox")));
        message = driver.findElement(By.id("message")).getText();
        logger.info("Message after adding: " + message);
        assertTrue(message.contains("It's back!"));

        // Enable input field
        logger.info("Clicking 'Enable' button to activate input field");
        WebElement enableButton = driver.findElement(By.cssSelector("button[onclick='swapInput()']"));
        enableButton.click();

        logger.info("Waiting for input field to become enabled");
        By inputLocator = By.cssSelector("#input-example input");
        wait.until(ExpectedConditions.elementToBeClickable(inputLocator));
        message = driver.findElement(By.id("message")).getText();
        logger.info("Message after enabling: " + message);
        assertTrue(message.contains("It's enabled!"));

        // Disable input field
        logger.info("Clicking 'Disable' button to deactivate input field");
        WebElement disableButton = driver.findElement(By.cssSelector("button[onclick='swapInput()']"));
        disableButton.click();

        logger.info("Waiting for input field to become disabled");
        wait.until(driver -> !driver.findElement(inputLocator).isEnabled());
        message = driver.findElement(By.id("message")).getText();
        logger.info("Message after disabling: " + message);
        assertTrue(message.contains("It's disabled!"));

        logger.info("ENDING Test --- Completed successfully: Dynamic Controls ---");
    }
}
