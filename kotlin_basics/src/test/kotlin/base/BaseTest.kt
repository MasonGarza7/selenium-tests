package base

import listeners.ExtentTestListener
import listeners.ScreenshotListener
import org.openqa.selenium.WebDriver
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Listeners
import utils.DriverFactory
import utils.LoggerUtil
import java.io.File

@Listeners(
    ScreenshotListener::class,
    ExtentTestListener::class
)
open class BaseTest {

    protected val logger = LoggerUtil.getLogger(this::class.java)

    lateinit var driver: WebDriver
        protected set

    @BeforeMethod
    fun setUp() {
        createResultsDirectories()
        logger.info("Starting test")
        driver = DriverFactory.createDriver()
        logger.info("WebDriver initialized")
    }

    @AfterMethod(alwaysRun = true)
    fun tearDown() {
        if (::driver.isInitialized) {
            driver.quit()
            logger.info("WebDriver quit")
        }
        logger.info("Test finished")
    }

    private fun createResultsDirectories() {
        File("results/screenshots").mkdirs()
        File("results/logs").mkdirs()
    }
}
