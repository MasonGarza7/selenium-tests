require("../testSetup.js");
const { By } = require("selenium-webdriver");
const { getDriver } = require("../globalSetup.js");
const { logger } = require("../utils/logger.js");
const { sleep } = require("../utils/sleep.js");

describe("Navigation & Page Title Validation", function () {
  it("should navigate to the Form Authentication page and verify header", async function () {
    logger.info("STARTING Test --- Navigation & Page Title Validation ---");

    const url = "https://the-internet.herokuapp.com/";
    logger.info(`Navigating to ${url}`);
    await getDriver().get(url);
    // await sleep(1000);

    logger.info("Locating 'Form Authentication' link");
    const link = await getDriver().findElement(By.linkText("Form Authentication"));

    logger.info("Clicking 'Form Authentication' link");
    await link.click();
    // await sleep(1000);

    logger.info("Validating header equals 'Login Page'");
    const header = await getDriver().findElement(By.tagName("h2"));
    const headerText = await header.getText();
    // await sleep(1000);

    if (headerText !== "Login Page") {
      throw new Error(`Unexpected header text: ${headerText}`);
    }

    const currentUrl = await getDriver().getCurrentUrl();
    logger.info(`Current URL: ${currentUrl}`);

    if (!currentUrl.includes("login")) {
      throw new Error(`Expected URL to contain 'login', but got: ${currentUrl}`);
    }

    logger.info("ENDING TEST --- Navigation & Page Title Validation ---");
  });
});
