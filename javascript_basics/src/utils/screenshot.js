const fs = require("fs-extra");
const path = require("path");

async function captureScreenshot(driver, testName) {
  try {
    const screenshotsDir = path.join(process.cwd(), "results", "screenshots");
    await fs.ensureDir(screenshotsDir);

    const timestamp = new Date().toISOString().replace(/[:.]/g, "-");
    const safeName = testName.replace(/[\/\\:*?"<>|]/g, "-");
    const filename = `${safeName}_${timestamp}.png`;

    const filepath = path.join(screenshotsDir, filename);

    const image = await driver.takeScreenshot();
    await fs.writeFile(filepath, image, "base64");

    console.log(`Screenshot saved: ${filepath}`);
  } catch (err) {
    console.error("Error capturing screenshot:", err);
  }
}

module.exports = { captureScreenshot };
