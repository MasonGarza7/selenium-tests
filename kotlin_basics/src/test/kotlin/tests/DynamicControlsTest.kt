package tests

import base.BaseTest
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import org.testng.annotations.Test
import java.time.Duration

class DynamicControlsTest : BaseTest() {

    @Test
    fun testDynamicControls() {
        logger.info("STARTING Test --- Dynamic Controls ---")

        val url = "https://the-internet.herokuapp.com/dynamic_controls"
        driver.get(url)
        logger.info("Navigated to $url")

        val wait = WebDriverWait(driver, Duration.ofSeconds(10))

        // Remove Checkbox
        logger.info("Clicking 'Remove' button to remove checkbox")
        val removeButton = driver.findElement(By.cssSelector("button[onclick='swapCheckbox()']"))
        removeButton.click()

        logger.info("Waiting for checkbox to be removed")
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")))
        var message = driver.findElement(By.id("message")).text
        logger.info("Message after removal: $message")
        Assert.assertTrue(message.contains("It's gone!"), "Expected removal message to contain \"It's gone!\"")

        // Add Checkbox
        logger.info("Clicking 'Add' button to add checkbox back")
        val addButton = driver.findElement(By.cssSelector("button[onclick='swapCheckbox()']"))
        addButton.click()

        logger.info("Waiting for checkbox to reappear")
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox")))
        message = driver.findElement(By.id("message")).text
        logger.info("Message after adding: $message")
        Assert.assertTrue(message.contains("It's back!"), "Expected add message to contain \"It's back!\"")

        // Enable input field
        logger.info("Clicking 'Enable' button to activate input field")
        val enableButton = driver.findElement(By.cssSelector("button[onclick='swapInput()']"))
        enableButton.click()

        logger.info("Waiting for input field to become enabled")
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#input-example input")))
        message = driver.findElement(By.id("message")).text
        logger.info("Message after enabling: $message")
        Assert.assertTrue(message.contains("It's enabled!"), "Expected enable message to contain \"It's enabled!\"")

        // Disable input field
        logger.info("Clicking 'Disable' button to deactivate input field")
        val disableButton = driver.findElement(By.cssSelector("button[onclick='swapInput()']"))
        disableButton.click()

        logger.info("Waiting for input field to become disabled")
        wait.until { d ->
            !d.findElement(By.cssSelector("#input-example input")).isEnabled
        }
        message = driver.findElement(By.id("message")).text
        logger.info("Message after disabling: $message")
        Assert.assertTrue(message.contains("It's disabled!"), "Expected disable message to contain \"It's disabled!\"")

        logger.info("ENDING Test --- Completed successfully: Dynamic Controls ---")
    }
}
