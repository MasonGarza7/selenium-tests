using NUnit.Framework;
using OpenQA.Selenium;
using csharp_basics.utils;

namespace csharp_basics.tests
{
    [TestFixture]
    public class CheckboxesTest : BaseTest
    {
        [Test]
        public void CheckboxesToggle()
        {
            Logger.Write("STARTING Test --- Checkboxes Toggle ---");

            string url = "https://the-internet.herokuapp.com/checkboxes";
            driver.Navigate().GoToUrl(url);
            Logger.Write($"Navigated to {url}");

            var checkboxes = driver.FindElements(By.CssSelector("input[type='checkbox']"));
            Logger.Write($"Found {checkboxes.Count} checkboxes");

            int index = 1;
            foreach (var box in checkboxes)
            {
                bool initialState = box.Selected;
                Logger.Write($"Checkbox {index} initial state: {initialState}");

                // Toggle
                box.Click();
                bool toggledState = box.Selected;
                Logger.Write($"Checkbox {index} toggled state: {toggledState}");

                Assert.That(toggledState != initialState,
                    $"Checkbox {index} did not change state (was {initialState}, now {toggledState})");

                index++;
            }

            Logger.Write("ENDING Test --- Completed successfully: Checkboxes Toggle ---");
        }
    }
}
