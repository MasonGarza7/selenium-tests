package tests

import base.BaseTest
import org.openqa.selenium.By
import org.testng.Assert
import org.testng.annotations.Test
import java.io.File

class FileUploadTest : BaseTest() {

    @Test
    fun testFileUpload() {
        logger.info("STARTING Test --- File Upload ---")

        val url = "https://the-internet.herokuapp.com/upload"
        driver.get(url)
        logger.info("Navigated to $url")

        // Ensure temp directory exists at kotlin_basics/temp/
        val tempDir = File("temp")
        if (!tempDir.exists()) {
            tempDir.mkdirs()
            logger.info("Created temp directory at: ${tempDir.absolutePath}")
        }

        // Create temporary file
        val tempFile = File(tempDir, "sample_upload.txt")
        tempFile.writeText("This is a test file for Selenium upload automation.")
        logger.info("Temporary file created at: ${tempFile.absolutePath}")

        // Locate file input and upload the file
        val fileInput = driver.findElement(By.id("file-upload"))
        fileInput.sendKeys(tempFile.absolutePath)
        logger.info("File path sent to file input field")

        // Click the upload button
        val uploadButton = driver.findElement(By.id("file-submit"))
        uploadButton.click()
        logger.info("Clicked Upload button")

        // Validate successful upload
        val successHeader = driver.findElement(By.tagName("h3")).text
        logger.info("Upload confirmation header: '$successHeader'")
        Assert.assertEquals(successHeader, "File Uploaded!")

        val uploadedFileName = driver.findElement(By.id("uploaded-files")).text
        logger.info("Uploaded file name: '$uploadedFileName'")
        Assert.assertEquals(uploadedFileName, "sample_upload.txt")

        // Cleanup
        if (tempFile.exists()) {
            tempFile.delete()
            logger.info("Temporary file deleted")
        }

        logger.info("ENDING Test --- Completed successfully: File Upload ---")
    }
}
