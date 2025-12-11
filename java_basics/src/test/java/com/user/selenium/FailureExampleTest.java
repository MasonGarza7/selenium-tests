package com.user.selenium;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FailureExampleTest extends BaseTest {

    @Test
    public void intentionalFailureExample() {
        logger.info("Starting Test: Intentional Failure Example");

        String url = "https://the-internet.herokuapp.com/";
        driver.get(url);
        logger.info("Navigated to " + url);

        // Intentionally incorrect assertion
        logger.info("Attempting to locate header and assert incorrect text");
        String header = driver.findElement(By.tagName("h1")).getText();
        logger.info("Found header text: '" + header + "'");

        // This assertion is intentionally wrong to trigger a failure
        assertEquals(header, "This text does not exist", "Intentional failure triggered");

        logger.info("This line should never execute if the assertion fails");
    }
}
