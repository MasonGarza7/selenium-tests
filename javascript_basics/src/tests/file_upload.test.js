require("../testSetup.js");
const fs = require("fs");
const path = require("path");
const { By, until } = require("selenium-webdriver");
const { getDriver } = require("../globalSetup.js");
const { logger } = require("../utils/logger.js");
const { sleep } = require("../utils/sleep.js");

describe("File Upload", function () {

  it("should upload a file successfully", async function () {

    const driver = getDriver();
    logger.info("STARTING Test --- File Upload ---");

    //
    // CREATE TEMP FILE
    //
    const tempDir = path.join(process.cwd(), "temp");
    const tempFilePath = path.join(tempDir, "upload_test_file.txt");

    logger.info(`Ensuring temp directory exists: ${tempDir}`);
    if (!fs.existsSync(tempDir)) {
      fs.mkdirSync(tempDir);
    }

    logger.info(`Creating temporary file: ${tempFilePath}`);
    fs.writeFileSync(tempFilePath, "This is a temp file for upload testing.");
    // await sleep(1000);


    //
    // OPEN PAGE
    //
    const url = "https://the-internet.herokuapp.com/upload";
    logger.info(`Navigating to ${url}`);
    await driver.get(url);
    // await sleep(1000);


    //
    // SELECT FILE + UPLOAD
    //
    logger.info("Locating file input");
    const fileInput = await driver.findElement(By.id("file-upload"));
    // await sleep(1000);

    logger.info("Sending file path to input");
    await fileInput.sendKeys(tempFilePath);
    // await sleep(1000);

    logger.info("Clicking Upload button");
    const uploadButton = await driver.findElement(By.id("file-submit"));
    await uploadButton.click();
    // await sleep(1000);


    //
    // VERIFY SUCCESS
    //
    logger.info("Waiting for success message");
    await driver.wait(until.elementLocated(By.tagName("h3")), 5000);

    const header = await driver.findElement(By.tagName("h3")).getText();
    // await sleep(1000);

    if (header.trim() !== "File Uploaded!") {
      throw new Error(`Upload failed. Expected 'File Uploaded!' but got '${header}'`);
    }

    logger.info("Upload success confirmed: File Uploaded!");


    //
    // CLEAN UP TEMP FILE
    //
    logger.info(`Deleting temporary file: ${tempFilePath}`);
    if (fs.existsSync(tempFilePath)) {
      fs.unlinkSync(tempFilePath);
    }

    logger.info("ENDING Test --- File Upload ---");
  });

});
