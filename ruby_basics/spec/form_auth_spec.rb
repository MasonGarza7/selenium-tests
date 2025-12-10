require "rspec"

RSpec.describe "Form Authentication" do
  it "validates invalid and valid login attempts" do
    LOGGER.info("STARTING Test --- Form Authentication ---")

    base_url = "https://the-internet.herokuapp.com/login"
    driver.navigate.to(base_url)
    LOGGER.info("Navigated to #{base_url}")

    # Invalid login
    LOGGER.info("Attempting invalid login...")
    driver.find_element(id: "username").send_keys("invalidUser")
    driver.find_element(id: "password").send_keys("wrongPass")
    driver.find_element(css: "button[type='submit']").click

    error_msg = driver.find_element(id: "flash").text
    LOGGER.info("Invalid login message: #{error_msg}")
    expect(error_msg).to include("Your username is invalid!")

    # Valid login
    LOGGER.info("Attempting valid login...")
    driver.navigate.to(base_url)
    driver.find_element(id: "username").send_keys("tomsmith")
    driver.find_element(id: "password").send_keys("SuperSecretPassword!")
    driver.find_element(css: "button[type='submit']").click

    LOGGER.info("Waiting for success message to appear...")
    wait = Selenium::WebDriver::Wait.new(timeout: 5)
    success_element = wait.until { driver.find_element(id: "flash").displayed? && driver.find_element(id: "flash") }

    success_msg = success_element.text
    LOGGER.info("Valid login message: #{success_msg}")
    expect(success_msg).to include("You logged into a secure area!")

    LOGGER.info("ENDING Test --- Completed successfully: Form Authentication ---")
  end
end
