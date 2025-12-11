package com.user.selenium;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SeleniumSmokeTest extends BaseTest {

    @Test
    public void opensTheInternetHomePage() {
        String url = "https://the-internet.herokuapp.com/";
        driver.get(url);

        String title = driver.getTitle();
        assertTrue(title.contains("The Internet"),
                "Expected page title to contain 'The Internet' but got: " + title);
    }
}
