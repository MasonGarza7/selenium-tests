package tests

import base.BaseTest
import org.openqa.selenium.By
import org.testng.Assert
import org.testng.annotations.Test

class FailureExampleTest : BaseTest() {

    @Test
    fun testIntentionalFailureExample() {
        logger.info("Starting Test: Intentional Failure Example")

        val url = "https://the-internet.herokuapp.com/"
        driver.get(url)
        logger.info("Navigated to $url")

        // Intentionally incorrect assertion
        logger.info("Attempting to locate header and assert incorrect text")
        val header = driver.findElement(By.tagName("h1")).text
        logger.info("Found header text: '$header'")

        // This assertion is intentionally wrong to trigger a failure
        Assert.assertEquals(
            header,
            "This text does not exist",
            "Intentional failure triggered"
        )

        logger.info("This line should never execute if the assertion fails")
    }
}
