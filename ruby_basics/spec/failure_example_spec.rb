require "rspec"

RSpec.describe "Intentional Failure Example" do
  it "intentionally fails to validate screenshot, logging, and reporting" do
    LOGGER.info("--- STARTING Test: Intentional Failure Example ---")

    url = "https://the-internet.herokuapp.com/"
    driver.navigate.to(url)
    LOGGER.info("Navigated to #{url}")

    LOGGER.info("Attempting to locate header and assert incorrect text")
    header_text = driver.find_element(tag_name: "h1").text
    LOGGER.info("Found header text: '#{header_text}'")

    # This assertion is intentionally wrong to trigger a failure
    expect(header_text).to eq("This text does not exist"), "Intentional failure triggered"

    LOGGER.info("--- ENDING TEST: This line should never execute if the assertion fails ---")
  end
end
