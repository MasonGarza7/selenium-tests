package utils

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

object DriverFactory {

    fun createDriver(): WebDriver {
        val options = ChromeOptions()

        val headless = System.getenv("HEADLESS")?.equals("true", ignoreCase = true) == true
        if (headless) {
            options.addArguments("--headless=new")
            options.addArguments("--window-size=1920,1080")
        } else {
            options.addArguments("--start-maximized")
        }

        return ChromeDriver(options)
    }
}
