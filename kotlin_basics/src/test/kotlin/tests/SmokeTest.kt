package tests

import base.BaseTest
import org.testng.annotations.Test

class SmokeTest : BaseTest() {

    @Test
    fun openGoogle() {
        driver.get("https://www.google.com")
    }
}
