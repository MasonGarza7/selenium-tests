package com.user.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DropdownTest extends BaseTest {

    @Test
    public void testDropdownSelection() {
        logger.info("STARTING Test --- Dropdown Selection ---");

        String url = "https://the-internet.herokuapp.com/dropdown";
        driver.get(url);
        logger.info("Navigated to " + url);

        WebElement dropdownElement = driver.findElement(By.id("dropdown"));
        Select dropdown = new Select(dropdownElement);
        logger.info("Located dropdown element");

        // Verify default selection
        String defaultOption = dropdown.getFirstSelectedOption().getText().trim();
        logger.info("Default selected option: '" + defaultOption + "'");
        assertEquals(defaultOption, "Please select an option");

        // Select Option 1
        logger.info("Selecting 'Option 1'");
        dropdown.selectByVisibleText("Option 1");
        String selectedText = dropdown.getFirstSelectedOption().getText().trim();
        logger.info("Selected option: '" + selectedText + "'");
        assertEquals(selectedText, "Option 1");

        // Select Option 2
        logger.info("Selecting 'Option 2'");
        dropdown.selectByVisibleText("Option 2");
        selectedText = dropdown.getFirstSelectedOption().getText().trim();
        logger.info("Selected option: '" + selectedText + "'");
        assertEquals(selectedText, "Option 2");

        logger.info("ENDING Test --- Completed successfully: Dropdown Selection ---");
    }
}
