package com.user.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FileUploadTest extends BaseTest {

    @Test
    public void testFileUpload() throws Exception {
        logger.info("STARTING Test --- File Upload ---");

        String url = "https://the-internet.herokuapp.com/upload";
        driver.get(url);
        logger.info("Navigated to " + url);

        // Create /java_basics/temp folder if it doesn't exist
        Path tempDir = Path.of(System.getProperty("user.dir"), "temp");
        if (!Files.exists(tempDir)) {
            Files.createDirectory(tempDir);
            logger.info("Created temp directory: " + tempDir);
        }

        // Create temporary file inside temp/
        File tempFile = new File(tempDir.toFile(), "sample_upload.txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("This is a test file for Selenium upload automation.");
        }

        logger.info("Temporary file created at: " + tempFile.getAbsolutePath());

        // Upload file
        WebElement fileInput = driver.findElement(By.id("file-upload"));
        fileInput.sendKeys(tempFile.getAbsolutePath());
        logger.info("File path sent to file input field");

        WebElement uploadButton = driver.findElement(By.id("file-submit"));
        uploadButton.click();
        logger.info("Clicked Upload button");

        // Validate successful upload
        String successHeader = driver.findElement(By.tagName("h3")).getText();
        logger.info("Upload confirmation header: '" + successHeader + "'");
        assertEquals(successHeader, "File Uploaded!");

        String uploadedName = driver.findElement(By.id("uploaded-files")).getText();
        logger.info("Uploaded file name: '" + uploadedName + "'");
        assertEquals(uploadedName, "sample_upload.txt");

        // Cleanup the file
        boolean deleted = tempFile.delete();
        logger.info("Temporary file deleted: " + deleted);

        logger.info("ENDING Test --- Completed successfully: File Upload ---");
    }
}
