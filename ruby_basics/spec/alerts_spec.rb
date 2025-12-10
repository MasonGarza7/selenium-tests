require "rspec"

RSpec.describe "JavaScript Alerts" do
  it "validates alert, confirm, and prompt interactions" do
    LOGGER.info("STARTING Test --- JavaScript Alerts ---")

    url = "https://the-internet.herokuapp.com/javascript_alerts"
    driver.navigate.to(url)
    LOGGER.info("Navigated to #{url}")

    # Simple alert
    LOGGER.info("Triggering simple alert")
    driver.find_element(xpath: "//button[text()='Click for JS Alert']").click
    alert = driver.switch_to.alert
    LOGGER.info("Alert text: #{alert.text}")
    alert.accept
    result = driver.find_element(id: "result").text
    LOGGER.info("Result after accepting simple alert: #{result}")
    expect(result).to eq("You successfully clicked an alert")

    # Confirmation alert
    LOGGER.info("Triggering confirmation alert")
    driver.find_element(xpath: "//button[text()='Click for JS Confirm']").click
    alert = driver.switch_to.alert
    LOGGER.info("Alert text: #{alert.text}")
    alert.dismiss
    result = driver.find_element(id: "result").text
    LOGGER.info("Result after dismissing confirm: #{result}")
    expect(result).to eq("You clicked: Cancel")

    driver.find_element(xpath: "//button[text()='Click for JS Confirm']").click
    alert = driver.switch_to.alert
    alert.accept
    result = driver.find_element(id: "result").text
    LOGGER.info("Result after accepting confirm: #{result}")
    expect(result).to eq("You clicked: Ok")

    # Prompt alert
    LOGGER.info("Triggering prompt alert")
    driver.find_element(xpath: "//button[text()='Click for JS Prompt']").click
    alert = driver.switch_to.alert
    LOGGER.info("Alert text: #{alert.text}")
    test_input = "Mason"
    alert.send_keys(test_input)
    alert.accept
    result = driver.find_element(id: "result").text
    LOGGER.info("Result after submitting prompt: #{result}")
    expect(result).to eq("You entered: #{test_input}")

    LOGGER.info("ENDING Test --- Completed successfully: JavaScript Alerts ---")
  end
end
