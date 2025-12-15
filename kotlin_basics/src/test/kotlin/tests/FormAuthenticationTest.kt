package tests

import base.BaseTest
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import org.testng.annotations.Test
import java.time.Duration

class FormAuthenticationTest : BaseTest() {

    @Test
    fun testFormAuthenticationValidAndInvalid() {
        logger.info("STARTING Test --- Form Authentication ---")

        val baseUrl = "https://the-internet.herokuapp.com/login"
        driver.get(baseUrl)
        logger.info("Navigated to $baseUrl")

        // Invalid login
        logger.info("Attempting invalid login...")
        driver.findElement(By.id("username")).sendKeys("invalidUser")
        driver.findElement(By.id("password")).sendKeys("wrongPass")
        driver.findElement(By.cssSelector("button[type='submit']")).click()

        var errorMsg = driver.findElement(By.id("flash")).text
        logger.info("Invalid login message: $errorMsg")
        Assert.assertTrue(
            errorMsg.contains("Your username is invalid!"),
            "Expected invalid username error message"
        )

        // Valid login
        logger.info("Attempting valid login...")
        driver.get(baseUrl)

        driver.findElement(By.id("username")).sendKeys("tomsmith")
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!")
        driver.findElement(By.cssSelector("button[type='submit']")).click()

        val wait = WebDriverWait(driver, Duration.ofSeconds(5))
        logger.info("Waiting for success message to appear...")
        val successElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("flash"))
        )

        val successMsg = successElement.text
        logger.info("Valid login message: $successMsg")
        Assert.assertTrue(
            successMsg.contains("You logged into a secure area!"),
            "Expected successful login message"
        )

        logger.info("ENDING Test --- Completed successfully: Form Authentication ---")
    }
}
