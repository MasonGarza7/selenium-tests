require("../testSetup.js");
const { By, until } = require("selenium-webdriver");
const { getDriver } = require("../globalSetup.js");
const { logger } = require("../utils/logger.js");
const { sleep } = require("../utils/sleep.js");

describe("JavaScript Alerts", function () {
  
  it("should handle and validate JS alert popups", async function () {
    logger.info("STARTING Test --- Alerts ---");

    const driver = getDriver();

    // Step 1: Navigate to Alerts page
    const url = "https://the-internet.herokuapp.com/javascript_alerts";
    logger.info(`Navigating to ${url}`);
    await driver.get(url);
    // await sleep(1000);

    // === ALERT 1: Simple OK Alert ===
    logger.info("Clicking JS Alert button");
    const jsAlertBtn = await driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
    await jsAlertBtn.click();
    // await sleep(1000);

    logger.info("Waiting for JS alert...");
    const alert1 = await driver.switchTo().alert();
    const alert1Text = await alert1.getText();
    // await sleep(1000);

    logger.info(`Alert text: ${alert1Text}`);
    if (alert1Text !== "I am a JS Alert") {
      throw new Error(`Expected alert text to be 'I am a JS Alert' but got '${alert1Text}'`);
    }

    logger.info("Accepting alert");
    await alert1.accept();

    // Validate result text
    const result1 = await driver.findElement(By.id("result")).getText();
    if (result1 !== "You successfully clicked an alert") {
      throw new Error(`Unexpected result text: ${result1}`);
    }
    logger.info("Alert 1 validated successfully");


    // === ALERT 2: Confirmation Alert ===
    logger.info("Clicking JS Confirm button");
    const jsConfirmBtn = await driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
    await jsConfirmBtn.click();
    // await sleep(1000);

    logger.info("Waiting for JS Confirm alert...");
    const alert2 = await driver.switchTo().alert();
    const alert2Text = await alert2.getText();
    // await sleep(1000);

    logger.info(`Alert text: ${alert2Text}`);
    if (alert2Text !== "I am a JS Confirm") {
      throw new Error(`Expected alert text to be 'I am a JS Confirm' but got '${alert2Text}'`);
    }

    logger.info("Dismissing alert (Cancel)");
    await alert2.dismiss();

    const result2 = await driver.findElement(By.id("result")).getText();
    if (result2 !== "You clicked: Cancel") {
      throw new Error(`Unexpected confirm result text: ${result2}`);
    }
    logger.info("Alert 2 validated successfully");


    // === ALERT 3: Prompt Alert ===
    logger.info("Clicking JS Prompt button");
    const jsPromptBtn = await driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
    await jsPromptBtn.click();
    // await sleep(1000);

    logger.info("Waiting for JS Prompt alert...");
    const alert3 = await driver.switchTo().alert();

    const promptText = "Selenium Test";
    logger.info(`Sending text to prompt: ${promptText}`);
    await alert3.sendKeys(promptText);
    // await sleep(1000);

    logger.info("Accepting prompt alert");
    await alert3.accept();

    const result3 = await driver.findElement(By.id("result")).getText();
    if (!result3.includes(promptText)) {
      throw new Error(`Expected prompt result to include '${promptText}' but got '${result3}'`);
    }
    logger.info("Alert 3 validated successfully");

    logger.info("ENDING Test --- Alerts ---");
  });

});
