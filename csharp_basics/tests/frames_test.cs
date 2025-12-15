using NUnit.Framework;
using OpenQA.Selenium;
using csharp_basics.utils;

namespace csharp_basics.tests
{
    [TestFixture]
    public class FramesTest : BaseTest
    {
        [Test]
        public void TestSwitchToIFrameAndBack()
        {
            Logger.Write("STARTING Test --- Frame Switching ---");

            string url = "https://www.w3schools.com/html/tryit.asp?filename=tryhtml_iframe_height_width";
            driver.Navigate().GoToUrl(url);
            Logger.Write($"Navigated to {url}");

            // Switch to outer frame that holds the preview content
            Logger.Write("Switching to outer frame: 'iframeResult'");
            driver.SwitchTo().Frame("iframeResult");

            // Locate and switch into nested iframe
            Logger.Write("Locating nested iframe element inside outer frame");
            IWebElement nestedIframe = driver.FindElement(By.TagName("iframe"));
            driver.SwitchTo().Frame(nestedIframe);
            Logger.Write("Switched into nested iframe");

            // Validate content inside nested frame
            string header = driver.FindElement(By.TagName("h1")).Text;
            Logger.Write($"Header text inside nested iframe: '{header}'");
            Assert.That(header, Does.Contain("This page is displayed in an iframe"));

            // Switch back to outer frame
            driver.SwitchTo().ParentFrame();
            Logger.Write("Returned to outer frame (iframeResult)");

            // Switch back to main page
            driver.SwitchTo().DefaultContent();
            Logger.Write("Returned to main page context");

            Logger.Write("ENDING Test --- Completed successfully: Frame Switching ---");
        }
    }
}
