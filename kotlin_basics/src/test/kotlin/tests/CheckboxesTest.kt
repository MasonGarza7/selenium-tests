package tests

import base.BaseTest
import org.openqa.selenium.By
import org.testng.Assert
import org.testng.annotations.Test

class CheckboxesTest : BaseTest() {

    @Test
    fun testCheckboxesToggle() {
        logger.info("STARTING Test --- Checkboxes Toggle ---")

        val url = "https://the-internet.herokuapp.com/checkboxes"
        driver.get(url)
        logger.info("Navigated to $url")

        val checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"))
        logger.info("Found ${checkboxes.size} checkboxes")

        for ((index, box) in checkboxes.withIndex()) {
            val checkboxNumber = index + 1

            val initialState = box.isSelected
            logger.info("Checkbox $checkboxNumber initial state: $initialState")

            // Toggle state
            box.click()
            val toggledState = box.isSelected
            logger.info("Checkbox $checkboxNumber toggled state: $toggledState")

            Assert.assertNotEquals(
                toggledState,
                initialState,
                "Checkbox $checkboxNumber did not change state (was $initialState, now $toggledState)"
            )
        }

        logger.info("ENDING Test --- Completed successfully: Checkboxes Toggle ---")
    }
}
