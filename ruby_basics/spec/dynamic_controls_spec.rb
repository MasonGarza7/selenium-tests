require "rspec"

RSpec.describe "Dynamic Controls" do
  it "validates add/remove checkbox and enable/disable input behavior" do
    LOGGER.info("STARTING Test --- Dynamic Controls ---")

    url = "https://the-internet.herokuapp.com/dynamic_controls"
    driver.navigate.to(url)
    LOGGER.info("Navigated to #{url}")

    wait = Selenium::WebDriver::Wait.new(timeout: 10)

    # Remove Checkbox
    LOGGER.info("Clicking 'Remove' button to remove checkbox")
    remove_button = driver.find_element(css: "button[onclick='swapCheckbox()']")
    remove_button.click

    LOGGER.info("Waiting for checkbox to be removed")
    wait.until { driver.find_elements(id: "checkbox").empty? }
    message = driver.find_element(id: "message").text
    LOGGER.info("Message after removal: #{message}")
    expect(message).to include("It's gone!")

    # Add Checkbox
    LOGGER.info("Clicking 'Add' button to add checkbox back")
    add_button = driver.find_element(css: "button[onclick='swapCheckbox()']")
    add_button.click

    LOGGER.info("Waiting for checkbox to reappear")
    wait.until { driver.find_elements(id: "checkbox").any? }
    message = driver.find_element(id: "message").text
    LOGGER.info("Message after adding: #{message}")
    expect(message).to include("It's back!")

    # Enable input field
    LOGGER.info("Clicking 'Enable' button to activate input field")
    enable_button = driver.find_element(css: "button[onclick='swapInput()']")
    enable_button.click

    LOGGER.info("Waiting for input field to become enabled")
    wait.until { driver.find_element(css: "#input-example input").enabled? }
    message = driver.find_element(id: "message").text
    LOGGER.info("Message after enabling: #{message}")
    expect(message).to include("It's enabled!")

    # Disable input field
    LOGGER.info("Clicking 'Disable' button to deactivate input field")
    disable_button = driver.find_element(css: "button[onclick='swapInput()']")
    disable_button.click

    LOGGER.info("Waiting for input field to become disabled")
    wait.until { !driver.find_element(css: "#input-example input").enabled? }
    message = driver.find_element(id: "message").text
    LOGGER.info("Message after disabling: #{message}")
    expect(message).to include("It's disabled!")

    LOGGER.info("ENDING Test --- Completed successfully: Dynamic Controls ---")
  end
end
