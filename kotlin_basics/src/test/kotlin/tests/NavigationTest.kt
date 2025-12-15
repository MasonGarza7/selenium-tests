package tests

import base.BaseTest
import org.openqa.selenium.By
import org.testng.Assert
import org.testng.annotations.Test

class NavigationTest : BaseTest() {

    @Test
    fun testNavigationAndTitle() {
        logger.info("STARTING Test --- Navigation & Page Title Validation ---")

        // Step 1: Navigate to main page
        val url = "https://the-internet.herokuapp.com/"
        logger.info("Navigating to $url")
        driver.get(url)

        // Step 2: Locate and click the 'Form Authentication' link
        logger.info("Locating 'Form Authentication' link")
        val link = driver.findElement(By.linkText("Form Authentication"))
        logger.info("Clicking 'Form Authentication' link")
        link.click()

        // Step 3: Validate that navigation succeeded
        logger.info("Validating that header text equals 'Login Page'")
        val header = driver.findElement(By.tagName("h2")).text
        Assert.assertEquals(
            header,
            "Login Page",
            "Unexpected header text: $header"
        )

        // Step 4: Confirm URL
        val currentUrl = driver.currentUrl
        logger.info("Current URL after navigation: $currentUrl")
        Assert.assertTrue(
            currentUrl.contains("login"),
            "Expected 'login' in URL but got $currentUrl"
        )

        logger.info("ENDING TEST --- Completed successfully: Navigation & Page Title Validation ---")
    }
}
