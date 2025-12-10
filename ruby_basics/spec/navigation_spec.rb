require "rspec"

RSpec.describe "Navigation and Page Title Validation" do
  it "navigates to Form Authentication and validates header and URL" do
    LOGGER.info("STARTING Test --- Navigation & Page Title Validation ---")

    # Step 1: Navigate to main page
    url = "https://the-internet.herokuapp.com/"
    LOGGER.info("Navigating to #{url}")
    driver.navigate.to(url)

    # Step 2: Locate and click the 'Form Authentication' link
    LOGGER.info("Locating 'Form Authentication' link")
    link = driver.find_element(link_text: "Form Authentication")
    LOGGER.info("Clicking 'Form Authentication' link")
    link.click

    # Step 3: Validate that navigation succeeded
    LOGGER.info("Validating that header text equals 'Login Page'")
    header = driver.find_element(tag_name: "h2").text
    expect(header).to eq("Login Page"), "Unexpected header text: #{header}"

    # Step 4: Confirm URL
    current_url = driver.current_url
    LOGGER.info("Current URL after navigation: #{current_url}")
    expect(current_url).to include("login"), "Expected 'login' in URL but got #{current_url}"

    LOGGER.info("ENDING TEST --- Completed successfully: Navigation & Page Title Validation ---")
  end
end
