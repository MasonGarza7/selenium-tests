require("../testSetup.js");
const { By, until } = require("selenium-webdriver");
const { getDriver } = require("../globalSetup.js");
const { logger } = require("../utils/logger.js");
const { sleep } = require("../utils/sleep.js");

describe("Dynamic Controls", function () {
  it("should remove and add the checkbox and enable/disable input field", async function () {

    const driver = getDriver();
    logger.info("STARTING Test --- Dynamic Controls ---");

    const url = "https://the-internet.herokuapp.com/dynamic_controls";
    logger.info(`Navigating to ${url}`);
    await driver.get(url);
    // await sleep(1000);

    logger.info("Locating Remove button");
    const removeButton = await driver.findElement(By.xpath("//button[text()='Remove']"));
    // await sleep(1000);

    logger.info("Clicking Remove button");
    await removeButton.click();
    // await sleep(1000);

    logger.info("Waiting for checkbox to be removed");
    await driver.wait(until.elementLocated(By.id("message")), 5000);
    const removeMessage = await driver.findElement(By.id("message")).getText();
    // await sleep(1000);

    if (!removeMessage.includes("gone")) {
      throw new Error(`Expected 'gone' message, but got: ${removeMessage}`);
    }

    logger.info("Clicking Add button");
    const addButton = await driver.findElement(By.xpath("//button[text()='Add']"));
    await addButton.click();
    // await sleep(1000);

    logger.info("Waiting for checkbox to return");
    await driver.wait(until.elementLocated(By.id("message")), 5000);
    const addMessage = await driver.findElement(By.id("message")).getText();
    // await sleep(1000);

    if (!addMessage.includes("back")) {
      throw new Error(`Expected 'back' message, but got: ${addMessage}`);
    }

    logger.info("Locating Enable button");
    const enableButton = await driver.findElement(By.xpath("//button[text()='Enable']"));
    // await sleep(1000);

    logger.info("Clicking Enable button");
    await enableButton.click();
    // await sleep(1000);

    logger.info("Waiting for input to be enabled");
    await driver.wait(until.elementLocated(By.id("message")), 5000);
    const enableMessage = await driver.findElement(By.id("message"));
    await driver.wait(until.elementTextIs(enableMessage, "It's enabled!"), 5000);
    // await sleep(1000);

    const inputField = await driver.findElement(By.css("input[type='text']"));
    const enabled = await inputField.isEnabled();
    // await sleep(1000);

    if (!enabled) {
      throw new Error("Input field was not enabled.");
    }

    logger.info("Locating Disable button");
    const disableButton = await driver.findElement(By.xpath("//button[text()='Disable']"));
    // await sleep(1000);

    logger.info("Clicking Disable button");
    await disableButton.click();
    // await sleep(1000);

    logger.info("Waiting for input to be disabled");
    await driver.wait(until.elementLocated(By.id("message")), 5000);
    const disableMessage = await driver.findElement(By.id("message"));
    await driver.wait(until.elementTextIs(disableMessage, "It's disabled!"), 5000);
    // await sleep(1000);

    const disabled = !(await inputField.isEnabled());
    if (!disabled) {
      throw new Error("Input field was not disabled.");
    }

    logger.info("ENDING Test --- Dynamic Controls ---");
  });
});
