using NUnit.Framework;
using OpenQA.Selenium;
using csharp_basics.utils;

namespace csharp_basics.tests
{
    [TestFixture]
    public class NavigationTest : BaseTest
    {
        [Test]
        public void TestNavigationAndTitle()
        {
            Logger.Write("STARTING Test --- Navigation & Page Title Validation ---");

            // Step 1: Navigate to main page
            string url = "https://the-internet.herokuapp.com/";
            Logger.Write($"Navigating to {url}");
            driver.Navigate().GoToUrl(url);

            // Step 2: Locate and click the 'Form Authentication' link
            Logger.Write("Locating 'Form Authentication' link");
            IWebElement link = driver.FindElement(By.LinkText("Form Authentication"));
            Logger.Write("Clicking 'Form Authentication' link");
            link.Click();

            // Step 3: Validate that navigation succeeded
            Logger.Write("Validating that header text equals 'Login Page'");
            string header = driver.FindElement(By.TagName("h2")).Text;
            Assert.That(header, Is.EqualTo("Login Page"));

            // Step 4: Confirm URL contains "login"
            string currentUrl = driver.Url;
            Logger.Write($"Current URL after navigation: {currentUrl}");
            Assert.That(currentUrl, Does.Contain("login"));

            Logger.Write("ENDING TEST --- Completed successfully: Navigation & Page Title Validation ---");
        }
    }
}
