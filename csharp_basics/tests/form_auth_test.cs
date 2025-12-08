using System;
using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;
using csharp_basics.utils;

namespace csharp_basics.tests
{
    [TestFixture]
    public class FormAuthTest : BaseTest
    {
        [Test]
        public void TestFormAuthenticationValidAndInvalid()
        {
            Logger.Write("STARTING Test --- Form Authentication ---");

            string baseUrl = "https://the-internet.herokuapp.com/login";
            driver.Navigate().GoToUrl(baseUrl);
            Logger.Write($"Navigated to {baseUrl}");

            // Invalid login
            Logger.Write("Attempting invalid login...");
            driver.FindElement(By.Id("username")).SendKeys("invalidUser");
            driver.FindElement(By.Id("password")).SendKeys("wrongPass");
            driver.FindElement(By.CssSelector("button[type='submit']")).Click();

            string errorMsg = driver.FindElement(By.Id("flash")).Text;
            Logger.Write($"Invalid login message: {errorMsg}");
            Assert.That(errorMsg, Does.Contain("Your username is invalid!"));

            // Valid login
            Logger.Write("Attempting valid login...");
            driver.Navigate().GoToUrl(baseUrl);
            driver.FindElement(By.Id("username")).SendKeys("tomsmith");
            driver.FindElement(By.Id("password")).SendKeys("SuperSecretPassword!");
            driver.FindElement(By.CssSelector("button[type='submit']")).Click();

            var wait = new WebDriverWait(driver, TimeSpan.FromSeconds(5));
            Logger.Write("Waiting for success message to appear...");

            IWebElement successElement = wait.Until(d =>
            {
                try
                {
                    var el = d.FindElement(By.Id("flash"));
                    return el.Displayed ? el : null;
                }
                catch (NoSuchElementException)
                {
                    return null;
                }
            });

            string successMsg = successElement.Text;
            Logger.Write($"Valid login message: {successMsg}");
            Assert.That(successMsg, Does.Contain("You logged into a secure area!"));

            Logger.Write("ENDING Test --- Completed successfully: Form Authentication ---");
        }
    }
}
