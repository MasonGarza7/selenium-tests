const { buildDriver, getDriver } = require("./globalSetup.js");
const { captureScreenshot } = require("./utils/screenshot.js");
const { logger } = require("./utils/logger.js");

/**
 * Driver-per-test:
 * - beforeEach → create a brand new Chrome instance
 * - afterEach  → capture screenshot (if failed) + quit driver
 */

beforeEach(async function () {
  logger.info(`Starting test: ${this.currentTest.title}`);

  // Create a fresh WebDriver for THIS TEST
  await buildDriver();
});

afterEach(async function () {
  const driver = getDriver();
  const testName = this.currentTest.title;

  if (this.currentTest.state === "failed") {
    logger.error(`Test failed: ${testName}`);
    await captureScreenshot(driver, testName);
  } else {
    logger.info(`Test passed: ${testName}`);
  }

  // Always quit driver at end of each test
  if (driver) {
    try {
      await driver.quit();
    } catch (err) {
      logger.error(`Error quitting WebDriver: ${err.message}`);
    }
  }
});

after(function () {
  logger.info("All tests completed.");
});
