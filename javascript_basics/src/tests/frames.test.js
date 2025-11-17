require("../testSetup.js");
const { By } = require("selenium-webdriver");
const { getDriver } = require("../globalSetup.js");
const { logger } = require("../utils/logger.js");
const { sleep } = require("../utils/sleep.js");

describe("Frames", function () {

  it("should switch between frames and verify frame content", async function () {

    const driver = getDriver();
    logger.info("STARTING Test --- Frames ---");

    const url = "https://the-internet.herokuapp.com/nested_frames";
    logger.info(`Navigating to ${url}`);
    await driver.get(url);
    // await sleep(1000);


    //
    // TOP → MIDDLE FRAME
    //
    logger.info("Switching to top frame");
    await driver.switchTo().frame("frame-top");
    // await sleep(1000);

    logger.info("Switching to middle frame");
    await driver.switchTo().frame("frame-middle");
    // await sleep(1000);

    logger.info("Reading text inside middle frame");
    const middleText = await driver.findElement(By.id("content")).getText();
    // await sleep(1000);

    if (middleText.trim() !== "MIDDLE") {
      throw new Error(`Expected 'MIDDLE' but got '${middleText}'`);
    }

    logger.info("Text verified: MIDDLE");


    //
    // BACK TO MAIN
    //
    logger.info("Switching back to main content");
    await driver.switchTo().defaultContent();
    // await sleep(1000);


    //
    // BOTTOM FRAME
    //
    logger.info("Switching to bottom frame");
    await driver.switchTo().frame("frame-bottom");
    // await sleep(1000);

    logger.info("Reading text inside bottom frame");
    const bottomText = await driver.findElement(By.tagName("body")).getText();
    // await sleep(1000);

    if (bottomText.trim() !== "BOTTOM") {
      throw new Error(`Expected 'BOTTOM' but got '${bottomText}'`);
    }

    logger.info("Text verified: BOTTOM");


    //
    // DONE
    //
    logger.info("ENDING Test --- Frames ---");
  });

});
