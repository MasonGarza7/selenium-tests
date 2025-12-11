package com.user.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
import org.testng.annotations.Test;

public class AlertsTest extends BaseTest {

    @Test
    public void testJavaScriptAlerts() {
        logger.info("STARTING Test --- JavaScript Alerts ---");

        String url = "https://the-internet.herokuapp.com/javascript_alerts";
        driver.get(url);
        logger.info("Navigated to " + url);

        // Simple alert
        logger.info("Triggering simple alert");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Alert alert = driver.switchTo().alert();
        logger.info("Alert text: " + alert.getText());
        alert.accept();

        String result = driver.findElement(By.id("result")).getText();
        logger.info("Result after accepting simple alert: " + result);
        assert result.equals("You successfully clicked an alert");

        // Confirmation alert (dismiss)
        logger.info("Triggering confirmation alert (dismiss)");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        alert = driver.switchTo().alert();
        logger.info("Alert text: " + alert.getText());
        alert.dismiss();

        result = driver.findElement(By.id("result")).getText();
        logger.info("Result after dismissing confirm: " + result);
        assert result.equals("You clicked: Cancel");

        // Confirmation alert (accept)
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        alert = driver.switchTo().alert();
        alert.accept();
        result = driver.findElement(By.id("result")).getText();
        logger.info("Result after accepting confirm: " + result);
        assert result.equals("You clicked: Ok");

        // Prompt alert
        logger.info("Triggering prompt alert");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        alert = driver.switchTo().alert();
        logger.info("Alert text: " + alert.getText());

        String testInput = "Test";
        alert.sendKeys(testInput);
        alert.accept();

        result = driver.findElement(By.id("result")).getText();
        logger.info("Result after submitting prompt: " + result);
        assert result.equals("You entered: " + testInput);

        logger.info("ENDING Test --- Completed successfully: JavaScript Alerts ---");
    }
}
