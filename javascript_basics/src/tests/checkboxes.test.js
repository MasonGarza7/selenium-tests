require("../testSetup.js");
const { By } = require("selenium-webdriver");
const { getDriver } = require("../globalSetup.js");
const { logger } = require("../utils/logger.js");
const { sleep } = require("../utils/sleep.js");

describe("Checkboxes", function () {
  
  it("should correctly toggle and validate checkboxes", async function () {
    logger.info("STARTING Test --- Checkboxes ---");

    const driver = getDriver();

    // Step 1: Navigate to page
    const url = "https://the-internet.herokuapp.com/checkboxes";
    logger.info(`Navigating to ${url}`);
    await driver.get(url);
    // await sleep(1000);

    // Step 2: Locate both checkboxes
    logger.info("Locating checkboxes");
    const checkboxes = await driver.findElements(By.css("#checkboxes input"));

    if (checkboxes.length !== 2) {
      throw new Error(`Expected 2 checkboxes but found ${checkboxes.length}`);
    }

    const checkbox1 = checkboxes[0];
    const checkbox2 = checkboxes[1];

    // Step 3: Validate default states
    const checkbox1Selected = await checkbox1.isSelected();
    const checkbox2Selected = await checkbox2.isSelected();

    logger.info(`Initial State -> Checkbox 1: ${checkbox1Selected}, Checkbox 2: ${checkbox2Selected}`);

    // Step 4: Toggle checkbox 1 (should become checked)
    logger.info("Clicking Checkbox 1");
    await checkbox1.click();
    // await sleep(1000);
    const checkbox1After = await checkbox1.isSelected();

    if (!checkbox1After) {
      throw new Error("Checkbox 1 was expected to be checked after clicking, but it was not.");
    }

    // Step 5: Toggle checkbox 2 (should become unchecked)
    logger.info("Clicking Checkbox 2");
    await checkbox2.click();
    // await sleep(1000);
    const checkbox2After = await checkbox2.isSelected();

    if (checkbox2After) {
      throw new Error("Checkbox 2 was expected to be unchecked after clicking, but it remained checked.");
    }

    logger.info("ENDING Test --- Checkboxes ---");
  });

});
