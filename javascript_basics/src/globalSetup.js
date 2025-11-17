const { Builder } = require("selenium-webdriver");
const chrome = require("selenium-webdriver/chrome");
const fs = require("fs-extra");
const path = require("path");

let currentDriver = null; // holds the driver for the CURRENT test

async function ensureResultsFolders() {
  const resultsDir = path.join(process.cwd(), "results");
  const logsDir = path.join(resultsDir, "logs");
  const screenshotsDir = path.join(resultsDir, "screenshots");

  await fs.ensureDir(resultsDir);
  await fs.ensureDir(logsDir);
  await fs.ensureDir(screenshotsDir);
}

/**
 * Build a fresh WebDriver instance.
 * This will be called once per test (from testSetup.js).
 */
async function buildDriver() {
  await ensureResultsFolders();

  const options = new chrome.Options();
  options.addArguments("--start-maximized");

  // -----------------------------
  // HEADLESS MODE TOGGLES
  // -----------------------------

  // Standard headless mode
  if (
    process.env.HEADLESS === "true" ||
    process.env.HEADLESS === "1" ||
    process.env.CI === "true"
  ) {
    console.log("Running in HEADLESS mode");
    options.addArguments("--headless=new");
    options.addArguments("--disable-gpu");
    options.addArguments("--window-size=1920,1080");
  }

  // Fast CI headless mode (optimized)
  if (process.env.HEADLESS === "fast") {
    console.log("Running in FAST headless mode");
    options.addArguments("--headless=new");
    options.addArguments("--disable-gpu");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--disable-software-rasterizer");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-extensions");
    options.addArguments("--remote-allow-origins=*");
    options.addArguments("--window-size=1280,720");
  }

  // -----------------------------
  // BUILD DRIVER
  // -----------------------------
  currentDriver = await new Builder()
    .forBrowser("chrome")
    .setChromeOptions(options)
    .build();

  return currentDriver;
}

/**
 * Return the driver for the current test.
 */
function getDriver() {
  return currentDriver;
}

module.exports = {
  buildDriver,
  getDriver,
};
