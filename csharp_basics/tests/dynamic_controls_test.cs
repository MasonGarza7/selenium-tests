using System;
using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;
using csharp_basics.utils;

namespace csharp_basics.tests
{
    [TestFixture]
    public class DynamicControlsTest : BaseTest
    {
        [Test]
        public void TestDynamicControls()
        {
            Logger.Write("STARTING Test --- Dynamic Controls ---");

            string url = "https://the-internet.herokuapp.com/dynamic_controls";
            driver.Navigate().GoToUrl(url);
            Logger.Write($"Navigated to {url}");

            var wait = new WebDriverWait(driver, TimeSpan.FromSeconds(10));

            // Remove Checkbox
            Logger.Write("Clicking 'Remove' button to remove checkbox");
            IWebElement removeButton = driver.FindElement(By.CssSelector("button[onclick='swapCheckbox()']"));
            removeButton.Click();

            Logger.Write("Waiting for checkbox to be removed");
            wait.Until(d =>
            {
                try
                {
                    var el = d.FindElement(By.Id("checkbox"));
                    return !el.Displayed;
                }
                catch (NoSuchElementException)
                {
                    return true; // element gone
                }
            });

            string message = driver.FindElement(By.Id("message")).Text;
            Logger.Write($"Message after removal: {message}");
            Assert.That(message, Does.Contain("It's gone!"));

            // Add Checkbox
            Logger.Write("Clicking 'Add' button to add checkbox back");
            IWebElement addButton = driver.FindElement(By.CssSelector("button[onclick='swapCheckbox()']"));
            addButton.Click();

            Logger.Write("Waiting for checkbox to reappear");
            wait.Until(d =>
            {
                try
                {
                    var el = d.FindElement(By.Id("checkbox"));
                    return el.Displayed;
                }
                catch (NoSuchElementException)
                {
                    return false;
                }
            });

            message = driver.FindElement(By.Id("message")).Text;
            Logger.Write($"Message after adding: {message}");
            Assert.That(message, Does.Contain("It's back!"));

            // Enable input field
            Logger.Write("Clicking 'Enable' button to activate input field");
            IWebElement enableButton = driver.FindElement(By.CssSelector("button[onclick='swapInput()']"));
            enableButton.Click();

            Logger.Write("Waiting for input field to become enabled");
            By inputLocator = By.CssSelector("#input-example input");
            wait.Until(d =>
            {
                var el = d.FindElement(inputLocator);
                return el.Displayed && el.Enabled;
            });

            message = driver.FindElement(By.Id("message")).Text;
            Logger.Write($"Message after enabling: {message}");
            Assert.That(message, Does.Contain("It's enabled!"));

            // Disable input field
            Logger.Write("Clicking 'Disable' button to deactivate input field");
            IWebElement disableButton = driver.FindElement(By.CssSelector("button[onclick='swapInput()']"));
            disableButton.Click();

            Logger.Write("Waiting for input field to become disabled");
            wait.Until(d =>
            {
                var el = d.FindElement(inputLocator);
                return !el.Enabled;
            });

            message = driver.FindElement(By.Id("message")).Text;
            Logger.Write($"Message after disabling: {message}");
            Assert.That(message, Does.Contain("It's disabled!"));

            Logger.Write("ENDING Test --- Completed successfully: Dynamic Controls ---");
        }
    }
}
