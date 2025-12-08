using System;
using System.IO;
using NUnit.Framework;
using OpenQA.Selenium;
using csharp_basics.utils;

namespace csharp_basics.tests
{
    [TestFixture]
    public class FileUploadTest : BaseTest
    {
        [Test]
        public void TestFileUpload()
        {
            Logger.Write("STARTING Test --- File Upload ---");

            string url = "https://the-internet.herokuapp.com/upload";
            driver.Navigate().GoToUrl(url);
            Logger.Write($"Navigated to {url}");

            // Resolve project root (three levels up from bin/.../ )
            string projectRoot = AppContext.BaseDirectory;
            projectRoot = Directory.GetParent(projectRoot).Parent.Parent.Parent.FullName;

            // Create temp directory within project root
            string tempDir = Path.Combine(projectRoot, "temp");

            if (!Directory.Exists(tempDir))
            {
                Directory.CreateDirectory(tempDir);
                Logger.Write($"Created temp directory at: {tempDir}");
            }

            // Create file inside temp directory
            string tempFilePath = Path.Combine(tempDir, "sample_upload.txt");
            File.WriteAllText(tempFilePath, "This is a test file for Selenium upload automation.");
            Logger.Write($"Temporary file created at: {tempFilePath}");

            // Upload process
            IWebElement fileInput = driver.FindElement(By.Id("file-upload"));
            fileInput.SendKeys(tempFilePath);
            Logger.Write("File path sent to file input field");

            IWebElement uploadButton = driver.FindElement(By.Id("file-submit"));
            uploadButton.Click();
            Logger.Write("Clicked Upload button");

            // Validation
            string successHeader = driver.FindElement(By.TagName("h3")).Text;
            Logger.Write($"Upload confirmation header: '{successHeader}'");
            Assert.That(successHeader, Is.EqualTo("File Uploaded!"));

            string uploadedFileName = driver.FindElement(By.Id("uploaded-files")).Text;
            Logger.Write($"Uploaded file name: '{uploadedFileName}'");
            Assert.That(uploadedFileName, Is.EqualTo("sample_upload.txt"));

            // Cleanup
            if (File.Exists(tempFilePath))
            {
                File.Delete(tempFilePath);
                Logger.Write("Temporary file deleted");
            }

            Logger.Write("ENDING Test --- Completed successfully: File Upload ---");
        }
    }
}
