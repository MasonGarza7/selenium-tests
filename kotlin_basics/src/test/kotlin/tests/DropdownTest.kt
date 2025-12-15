package tests

import base.BaseTest
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select
import org.testng.Assert
import org.testng.annotations.Test

class DropdownTest : BaseTest() {

    @Test
    fun testDropdownSelection() {
        logger.info("STARTING Test --- Dropdown Selection ---")

        val url = "https://the-internet.herokuapp.com/dropdown"
        driver.get(url)
        logger.info("Navigated to $url")

        val dropdownElement = driver.findElement(By.id("dropdown"))
        val dropdown = Select(dropdownElement)
        logger.info("Located dropdown element")

        // Verify default selection
        val defaultOption = dropdown.firstSelectedOption.text.trim()
        logger.info("Default selected option: '$defaultOption'")
        Assert.assertEquals(defaultOption, "Please select an option")

        // Select Option 1
        logger.info("Selecting 'Option 1'")
        dropdown.selectByVisibleText("Option 1")
        var selectedText = dropdown.firstSelectedOption.text.trim()
        logger.info("Selected option: '$selectedText'")
        Assert.assertEquals(selectedText, "Option 1")

        // Select Option 2
        logger.info("Selecting 'Option 2'")
        dropdown.selectByVisibleText("Option 2")
        selectedText = dropdown.firstSelectedOption.text.trim()
        logger.info("Selected option: '$selectedText'")
        Assert.assertEquals(selectedText, "Option 2")

        logger.info("ENDING Test --- Completed successfully: Dropdown Selection ---")
    }
}
