using NUnit.Framework;
using OpenQA.Selenium;
using Allure.NUnit.Attributes;
using csharp_basics.utils;

namespace csharp_basics.tests
{
    [TestFixture]
    public class FailureExampleTest : BaseTest
    {
        [Test]
        [AllureDescription("Intentional failure to validate screenshot and reporting behavior")]
        public void IntentionalFailureExample()
        {
            Logger.Write("STARTING TEST --- Intentional Failure Example ---");

            string url = "https://the-internet.herokuapp.com/";
            driver.Navigate().GoToUrl(url);
            Logger.Write($"Navigated to {url}");

            Logger.Write("Attempting to locate header and assert incorrect text");
            string headerText = driver.FindElement(By.TagName("h1")).Text;
            Logger.Write($"Found header text: '{headerText}'");

            // This assertion is intentionally wrong to trigger a failure
            Assert.That(
                headerText,
                Is.EqualTo("This text does not exist"),
                "Intentional failure triggered"
            );

            Logger.Write("ENDING TEST --- This line should never execute if the assertion fails ---");
        }
    }
}
