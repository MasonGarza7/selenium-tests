using NUnit.Framework;
using OpenQA.Selenium;
using Allure.NUnit.Attributes;
using csharp_basics.utils;

namespace csharp_basics.tests
{
    [TestFixture]
    public class AlertsTest : BaseTest
    {
        [Test]
        [AllureDescription("Validates JavaScript alerts, confirms, and prompt behavior")]
        public void JavaScriptAlerts()
        {
            Logger.Write("STARTING Test --- JavaScript Alerts ---");

            string url = "https://the-internet.herokuapp.com/javascript_alerts";
            driver.Navigate().GoToUrl(url);
            Logger.Write($"Navigated to {url}");

            // Simple Alert
            Logger.Write("Triggering simple alert");
            driver.FindElement(By.XPath("//button[text()='Click for JS Alert']")).Click();

            IAlert alert = driver.SwitchTo().Alert();
            Logger.Write($"Alert text: {alert.Text}");

            alert.Accept();
            string result = driver.FindElement(By.Id("result")).Text;
            Logger.Write($"Result after accepting simple alert: {result}");
            Assert.That(result, Is.EqualTo("You successfully clicked an alert"));

            // Confirmation Alert - dismiss
            Logger.Write("Triggering confirmation alert");
            driver.FindElement(By.XPath("//button[text()='Click for JS Confirm']")).Click();

            alert = driver.SwitchTo().Alert();
            Logger.Write($"Alert text: {alert.Text}");

            alert.Dismiss();
            result = driver.FindElement(By.Id("result")).Text;
            Logger.Write($"Result after dismissing confirm: {result}");
            Assert.That(result, Is.EqualTo("You clicked: Cancel"));

            // Confirmation Alert - accept
            driver.FindElement(By.XPath("//button[text()='Click for JS Confirm']")).Click();

            alert = driver.SwitchTo().Alert();
            alert.Accept();
            result = driver.FindElement(By.Id("result")).Text;
            Logger.Write($"Result after accepting confirm: {result}");
            Assert.That(result, Is.EqualTo("You clicked: Ok"));

            // Prompt Alert
            Logger.Write("Triggering prompt alert");
            driver.FindElement(By.XPath("//button[text()='Click for JS Prompt']")).Click();

            alert = driver.SwitchTo().Alert();
            Logger.Write($"Alert text: {alert.Text}");

            string testInput = "Mason";
            alert.SendKeys(testInput);
            alert.Accept();

            result = driver.FindElement(By.Id("result")).Text;
            Logger.Write($"Result after submitting prompt: {result}");
            Assert.That(result, Is.EqualTo($"You entered: {testInput}"));

            Logger.Write("ENDING Test --- Completed successfully: JavaScript Alerts ---");
        }
    }
}
