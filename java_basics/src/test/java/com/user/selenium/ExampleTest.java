package com.user.selenium;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ExampleTest {

    @Test
    public void sanityCheck() {
        assertEquals(1 + 1, 2, "Basic arithmetic should work");
    }
}
