package com.user.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class FramesTest extends BaseTest {

    @Test
    public void testSwitchToIframeAndBack() {
        logger.info("STARTING Test --- Frame Switching ---");

        String url = "https://www.w3schools.com/html/tryit.asp?filename=tryhtml_iframe_height_width";
        driver.get(url);
        logger.info("Navigated to " + url);

        // Switch to main frame that holds the content preview
        logger.info("Switching to outer frame: 'iframeResult'");
        driver.switchTo().frame("iframeResult");

        // Inside this frame, find the nested iframe element
        logger.info("Locating nested iframe element inside outer frame");
        WebElement nestedIframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(nestedIframe);
        logger.info("Switched into nested iframe");

        // Validate content inside nested frame
        String header = driver.findElement(By.tagName("h1")).getText();
        logger.info("Header text inside nested iframe: '" + header + "'");
        assertTrue(header.contains("This page is displayed in an iframe"));

        // Switch back to the outer frame
        driver.switchTo().parentFrame();
        logger.info("Returned to outer frame (iframeResult)");

        // Then switch back to main page
        driver.switchTo().defaultContent();
        logger.info("Returned to main page context");

        logger.info("ENDING Test --- Completed successfully: Frame Switching ---");
    }
}
