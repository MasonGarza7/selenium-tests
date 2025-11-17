const { By } = require("selenium-webdriver");

class Select {
  constructor(element) {
    this.element = element;
  }

  async selectByVisibleText(text) {
    const options = await this.element.findElements(By.tagName("option"));
    for (let option of options) {
      const optionText = await option.getText();
      if (optionText.trim() === text.trim()) {
        await option.click();
        return;
      }
    }
    throw new Error(`Option with text "${text}" not found`);
  }

  async getFirstSelectedOption() {
    const options = await this.element.findElements(By.tagName("option"));
    for (let option of options) {
      const selected = await option.isSelected();
      if (selected) {
        return option;
      }
    }
    return null;
  }
}

module.exports = Select;
