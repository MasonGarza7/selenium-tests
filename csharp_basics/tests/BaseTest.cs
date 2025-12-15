using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using Allure.Net.Commons;
using System;
using System.IO;
using csharp_basics.utils;
using Allure.NUnit;
using Allure.Net.Commons;


namespace csharp_basics.tests
{
    [AllureNUnit]
    public class BaseTest
    {
        protected IWebDriver driver;

        [OneTimeSetUp]
        public void GlobalSetup()
        {
            Logger.Initialize();
            Logger.Write("Starting test run");
        }

        [SetUp]
        public void Setup()
        {
            Logger.Write("Setting up ChromeDriver via Selenium Manager");

            ChromeOptions options = new ChromeOptions();
            options.AddArgument("--start-maximized");

            bool headless =
                string.Equals(Environment.GetEnvironmentVariable("HEADLESS"), "true", StringComparison.OrdinalIgnoreCase) ||
                Environment.GetEnvironmentVariable("HEADLESS") == "1";

            if (headless)
            {
                options.AddArgument("--headless=new");
                options.AddArgument("--window-size=1920,1080");
            }

            // Selenium Manager will automatically resolve and manage the correct ChromeDriver
            driver = new ChromeDriver(options);

            Logger.Write("Browser launched");
        }

        [TearDown]
        public void Cleanup()
        {
            var testStatus = TestContext.CurrentContext.Result.Outcome.Status;
            var testName = TestContext.CurrentContext.Test.Name;

            if (driver != null)
            {
                if (testStatus == NUnit.Framework.Interfaces.TestStatus.Failed)
                {
                    Logger.Write($"Test FAILED: {testName}");

                    string screenshotPath = ScreenshotHelper.CaptureScreenshot(driver, testName);

                    if (screenshotPath != null)
                    {
                        AllureApi.AddAttachment("Failure Screenshot", "image/png", screenshotPath);
                    }
                }
                else
                {
                    Logger.Write($"Test PASSED: {testName}");
                }

                driver.Quit();
                Logger.Write("Browser closed");
            }
            else
            {
                Logger.Write("Driver was null in Cleanup (setup failed before WebDriver initialization).");
            }
        }

    }
}
