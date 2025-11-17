require("../testSetup.js");
const { By } = require("selenium-webdriver");
const { getDriver } = require("../globalSetup.js");
const { logger } = require("../utils/logger.js");
const { sleep } = require("../utils/sleep.js");

describe("Failure Example", function () {

  it("should intentionally fail to test screenshot-on-failure", async function () {

    const driver = getDriver();
    logger.info("STARTING Test --- Intentional Failure Example ---");

    //
    // STEP 1 — Navigate to homepage
    //
    const url = "https://the-internet.herokuapp.com/";
    logger.info(`Navigating to ${url}`);
    await driver.get(url);
    // await sleep(1000);


    //
    // STEP 2 — Intentionally locate something that does not exist
    //
    logger.info("Attempting to locate a NON-EXISTENT element to force failure");

    // This selector is INVALID ON PURPOSE
    await driver.findElement(By.id("this-element-does-not-exist"));
    // await sleep(1000);


    //
    // This line will never be reached (failure occurs above)
    //
    logger.info("This log should never appear, the test should fail earlier");

  });

});
