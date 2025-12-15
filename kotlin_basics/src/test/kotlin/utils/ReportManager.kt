package utils

import com.aventstack.extentreports.ExtentReports
import com.aventstack.extentreports.reporter.ExtentSparkReporter
import java.io.File

object ReportManager {

    private lateinit var extent: ExtentReports

    fun initReport() {
        if (!this::extent.isInitialized) {
            File("results").mkdirs()

            val spark = ExtentSparkReporter("results/report.html")
            spark.config().documentTitle = "Kotlin Selenium Automation"
            spark.config().reportName = "Test Execution Report"

            extent = ExtentReports()
            extent.attachReporter(spark)
        }
    }

    fun getExtent(): ExtentReports {
        return extent
    }

    fun flush() {
        if (this::extent.isInitialized) {
            extent.flush()
        }
    }
}
