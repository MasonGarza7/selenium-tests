package listeners

import base.BaseTest
import com.aventstack.extentreports.ExtentTest
import com.aventstack.extentreports.Status
import org.testng.ITestContext
import org.testng.ITestListener
import org.testng.ITestResult
import utils.ReportManager

class ExtentTestListener : ITestListener {

    private val tests = ThreadLocal<ExtentTest>()

    override fun onStart(context: ITestContext) {
        ReportManager.initReport()
    }

    override fun onTestStart(result: ITestResult) {
        val test = ReportManager
            .getExtent()
            .createTest(result.method.methodName)

        tests.set(test)
    }

    override fun onTestSuccess(result: ITestResult) {
        tests.get().log(Status.PASS, "Test passed")
    }

    override fun onTestFailure(result: ITestResult) {
        val test = tests.get()
        test.log(Status.FAIL, result.throwable)

        val instance = result.instance
        if (instance is BaseTest) {
            val screenshotPath = "screenshots/${result.name}.png"
            test.addScreenCaptureFromPath(screenshotPath)
        }
    }

    override fun onFinish(context: ITestContext) {
        ReportManager.flush()
    }
}
