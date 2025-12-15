package tests

import base.BaseTest
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import org.testng.annotations.Test
import java.time.Duration

class AlertsTest : BaseTest() {

    @Test
    fun testJavaScriptAlerts() {
        logger.info("STARTING Test --- JavaScript Alerts ---")

        val url = "https://the-internet.herokuapp.com/javascript_alerts"
        driver.get(url)
        logger.info("Navigated to $url")

        val wait = WebDriverWait(driver, Duration.ofSeconds(5))

        // Simple alert
        logger.info("Triggering simple alert")
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click()

        var alert = wait.until(ExpectedConditions.alertIsPresent())
        logger.info("Alert text: ${alert.text}")
        alert.accept()

        var result = driver.findElement(By.id("result")).text
        logger.info("Result after accepting simple alert: $result")
        Assert.assertEquals(result, "You successfully clicked an alert")

        // Confirmation alert
        logger.info("Triggering confirmation alert")
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click()

        alert = wait.until(ExpectedConditions.alertIsPresent())
        logger.info("Alert text: ${alert.text}")
        alert.dismiss()

        result = driver.findElement(By.id("result")).text
        logger.info("Result after dismissing confirm: $result")
        Assert.assertEquals(result, "You clicked: Cancel")

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click()
        alert = wait.until(ExpectedConditions.alertIsPresent())
        alert.accept()

        result = driver.findElement(By.id("result")).text
        logger.info("Result after accepting confirm: $result")
        Assert.assertEquals(result, "You clicked: Ok")

        // Prompt alert
        logger.info("Triggering prompt alert")
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click()

        alert = wait.until(ExpectedConditions.alertIsPresent())
        logger.info("Alert text: ${alert.text}")

        val testInput = "Mason"
        alert.sendKeys(testInput)
        alert.accept()

        result = driver.findElement(By.id("result")).text
        logger.info("Result after submitting prompt: $result")
        Assert.assertEquals(result, "You entered: $testInput")

        logger.info("ENDING Test --- Completed successfully: JavaScript Alerts ---")
    }
}
