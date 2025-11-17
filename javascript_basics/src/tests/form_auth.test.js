require("../testSetup.js");
const { By, until } = require("selenium-webdriver");
const { getDriver } = require("../globalSetup.js");
const { logger } = require("../utils/logger.js");
const { sleep } = require("../utils/sleep.js");

describe("Form Authentication", function () {

  //
  // FIRST TEST — INVALID LOGIN
  //
  it("should fail to log in with invalid credentials", async function () {

    const driver = getDriver();
    logger.info("STARTING Test --- Failed Login ---");

    const url = "https://the-internet.herokuapp.com/login";
    logger.info(`Navigating to ${url}`);
    await driver.get(url);
    // await sleep(1000);

    logger.info("Locating username field");
    const usernameField = await driver.findElement(By.id("username"));
    // await sleep(1000);

    logger.info("Entering invalid username");
    await usernameField.sendKeys("wronguser");
    // await sleep(1000);

    logger.info("Locating password field");
    const passwordField = await driver.findElement(By.id("password"));
    // await sleep(1000);

    logger.info("Entering invalid password");
    await passwordField.sendKeys("wrongpassword");
    // await sleep(1000);

    logger.info("Locating Login button");
    const loginButton = await driver.findElement(By.css("button[type='submit']"));
    // await sleep(1000);

    logger.info("Clicking Login button");
    await loginButton.click();
    // await sleep(1000);

    logger.info("Waiting for failure message");
    const flash = await driver.wait(until.elementLocated(By.id("flash")), 5000);
    const message = await flash.getText();
    // await sleep(1000);

    if (
      !message.includes("Your username is invalid!") &&
      !message.includes("Your password is invalid!")
    ) {
      throw new Error(`Expected failure message but got: ${message}`);
    }

    logger.info("ENDING Test --- Failed Login ---");
  });



  //
  // SECOND TEST — VALID LOGIN
  //
  it("should successfully log in with valid credentials", async function () {

    const driver = getDriver();
    logger.info("STARTING Test --- Successful Login ---");

    const url = "https://the-internet.herokuapp.com/login";
    logger.info(`Navigating to ${url}`);
    await driver.get(url);
    // await sleep(1000);

    logger.info("Locating username field");
    const usernameField = await driver.findElement(By.id("username"));
    // await sleep(1000);

    logger.info("Entering username");
    await usernameField.sendKeys("tomsmith");
    // await sleep(1000);

    logger.info("Locating password field");
    const passwordField = await driver.findElement(By.id("password"));
    // await sleep(1000);

    logger.info("Entering password");
    await passwordField.sendKeys("SuperSecretPassword!");
    // await sleep(1000);

    logger.info("Locating Login button");
    const loginButton = await driver.findElement(By.css("button[type='submit']"));
    // await sleep(1000);

    logger.info("Clicking Login button");
    await loginButton.click();
    // await sleep(1000);

    logger.info("Waiting for success message");
    const flash = await driver.wait(until.elementLocated(By.id("flash")), 5000);
    const message = await flash.getText();
    // await sleep(1000);

    if (!message.includes("You logged into a secure area!")) {
      throw new Error(`Expected success message but got: ${message}`);
    }

    logger.info("ENDING Test --- Successful Login ---");
  });

});
