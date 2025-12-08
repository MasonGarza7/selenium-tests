using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;
using csharp_basics.utils;

namespace csharp_basics.tests
{
    [TestFixture]
    public class DropdownTest : BaseTest
    {
        [Test]
        public void DropdownSelection()
        {
            Logger.Write("STARTING Test --- Dropdown Selection ---");

            string url = "https://the-internet.herokuapp.com/dropdown";
            driver.Navigate().GoToUrl(url);
            Logger.Write($"Navigated to {url}");

            IWebElement dropdownElement = driver.FindElement(By.Id("dropdown"));
            var dropdown = new SelectElement(dropdownElement);
            Logger.Write("Located dropdown element");

            // Verify default selection
            string defaultOption = dropdown.SelectedOption.Text.Trim();
            Logger.Write($"Default selected option: '{defaultOption}'");
            Assert.That(defaultOption, Is.EqualTo("Please select an option"));

            // Select Option 1
            Logger.Write("Selecting 'Option 1'");
            dropdown.SelectByText("Option 1");
            string selectedText = dropdown.SelectedOption.Text.Trim();
            Logger.Write($"Selected option: '{selectedText}'");
            Assert.That(selectedText, Is.EqualTo("Option 1"));

            // Select Option 2
            Logger.Write("Selecting 'Option 2'");
            dropdown.SelectByText("Option 2");
            selectedText = dropdown.SelectedOption.Text.Trim();
            Logger.Write($"Selected option: '{selectedText}'");
            Assert.That(selectedText, Is.EqualTo("Option 2"));

            Logger.Write("ENDING Test --- Completed successfully: Dropdown Selection ---");
        }
    }
}
