require("../testSetup.js");
const { By } = require("selenium-webdriver");
const Select = require("../utils/select.js");
const { getDriver } = require("../globalSetup.js");
const { logger } = require("../utils/logger.js");
const { sleep } = require("../utils/sleep.js");

describe("Dropdown", function () {

  it("should select options from the dropdown and verify selections", async function () {
    logger.info("STARTING Test --- Dropdown ---");

    const driver = getDriver();

    // Step 1: Navigate to Dropdown page
    const url = "https://the-internet.herokuapp.com/dropdown";
    logger.info(`Navigating to ${url}`);
    await driver.get(url);
    // await sleep(1000);

    // Step 2: Locate the dropdown element
    logger.info("Locating dropdown element");
    const dropdownElement = await driver.findElement(By.id("dropdown"));

    // Step 3: Build Select helper
    const dropdown = new Select(dropdownElement);

    // Step 4: Select Option 1
    logger.info("Selecting Option 1");
    await dropdown.selectByVisibleText("Option 1");
    // await sleep(1000);

    const selected1 = await dropdown.getFirstSelectedOption();
    const text1 = await selected1.getText();
    logger.info(`Selected option: ${text1}`);

    if (text1 !== "Option 1") {
      throw new Error(`Expected 'Option 1' but got '${text1}'`);
    }

    // Step 5: Select Option 2
    logger.info("Selecting Option 2");
    await dropdown.selectByVisibleText("Option 2");
    // await sleep(1000);

    const selected2 = await dropdown.getFirstSelectedOption();
    const text2 = await selected2.getText();
    logger.info(`Selected option: ${text2}`);

    if (text2 !== "Option 2") {
      throw new Error(`Expected 'Option 2' but got '${text2}'`);
    }

    logger.info("ENDING Test --- Dropdown ---");
  });

});
