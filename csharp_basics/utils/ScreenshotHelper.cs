using System;
using System.IO;
using OpenQA.Selenium;

namespace csharp_basics.utils
{
    public static class ScreenshotHelper
    {
        public static string CaptureScreenshot(IWebDriver driver, string testName)
        {
            try
            {
                Screenshot screenshot = ((ITakesScreenshot)driver).GetScreenshot();

                // Project root (from bin/Debug/net8.0)
                string root = Path.GetFullPath(Path.Combine(AppContext.BaseDirectory, "..", "..", ".."));
                string screenshotDir = Path.Combine(root, "results", "screenshots");

                if (!Directory.Exists(screenshotDir))
                    Directory.CreateDirectory(screenshotDir);

                string timestamp = DateTime.Now.ToString("yyyyMMdd_HHmmss");
                string filePath = Path.Combine(screenshotDir, $"{testName}_{timestamp}.png");

                screenshot.SaveAsFile(filePath);

                return filePath;
            }
            catch
            {
                return null;
            }
        }
    }
}
