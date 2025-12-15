package listeners

import base.BaseTest
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.testng.ITestListener
import org.testng.ITestResult
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption

class ScreenshotListener : ITestListener {

    override fun onTestFailure(result: ITestResult) {
        val testInstance = result.instance

        if (testInstance is BaseTest) {
            val screenshot = (testInstance.driver as TakesScreenshot)
                .getScreenshotAs(OutputType.FILE)

            val screenshotFile = File(
                "results/screenshots/${result.name}.png"
            )

            Files.copy(
                screenshot.toPath(),
                screenshotFile.toPath(),
                StandardCopyOption.REPLACE_EXISTING
            )
        }
    }
}
