package tests

import base.BaseTest
import org.openqa.selenium.By
import org.testng.Assert
import org.testng.annotations.Test

class FramesTest : BaseTest() {

    @Test
    fun testSwitchToIframeAndBack() {
        logger.info("STARTING Test --- Frame Switching ---")

        val url = "https://www.w3schools.com/html/tryit.asp?filename=tryhtml_iframe_height_width"
        driver.get(url)
        logger.info("Navigated to $url")

        // Switch to main frame that holds the content preview
        logger.info("Switching to outer frame: 'iframeResult'")
        driver.switchTo().frame("iframeResult")

        // Inside this frame, find the nested iframe element
        logger.info("Locating nested iframe element inside outer frame")
        val nestedIframe = driver.findElement(By.tagName("iframe"))
        driver.switchTo().frame(nestedIframe)
        logger.info("Switched into nested iframe")

        // Validate content inside nested frame
        val header = driver.findElement(By.tagName("h1")).text
        logger.info("Header text inside nested iframe: '$header'")
        Assert.assertTrue(
            header.contains("This page is displayed in an iframe"),
            "Expected header text to contain iframe message"
        )

        // Switch back to the outer frame
        driver.switchTo().parentFrame()
        logger.info("Returned to outer frame (iframeResult)")

        // Then switch back to main page
        driver.switchTo().defaultContent()
        logger.info("Returned to main page context")

        logger.info("ENDING Test --- Completed successfully: Frame Switching ---")
    }
}
