package com.user.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class CheckboxesTest extends BaseTest {

    @Test
    public void testCheckboxesToggle() {
        logger.info("STARTING Test --- Checkboxes Toggle ---");

        String url = "https://the-internet.herokuapp.com/checkboxes";
        driver.get(url);
        logger.info("Navigated to " + url);

        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        logger.info("Found " + checkboxes.size() + " checkboxes");

        int index = 1;
        for (WebElement box : checkboxes) {
            boolean initialState = box.isSelected();
            logger.info("Checkbox " + index + " initial state: " + initialState);

            // Toggle
            box.click();
            boolean toggledState = box.isSelected();
            logger.info("Checkbox " + index + " toggled state: " + toggledState);

            assert toggledState != initialState :
                    "Checkbox " + index + " did not change state (was " + initialState + ", now " + toggledState + ")";

            index++;
        }

        logger.info("ENDING Test --- Completed successfully: Checkboxes Toggle ---");
    }
}
