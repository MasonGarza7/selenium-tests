require "rspec"

RSpec.describe "Frame Switching" do
  it "switches into nested frames and back to main page" do
    LOGGER.info("STARTING Test --- Frame Switching ---")

    url = "https://www.w3schools.com/html/tryit.asp?filename=tryhtml_iframe_height_width"
    driver.navigate.to(url)
    LOGGER.info("Navigated to #{url}")

    # Switch to main preview frame
    LOGGER.info("Switching to outer frame: 'iframeResult'")
    driver.switch_to.frame("iframeResult")

    # Find nested iframe element
    LOGGER.info("Locating nested iframe element inside outer frame")
    nested_iframe = driver.find_element(tag_name: "iframe")
    driver.switch_to.frame(nested_iframe)
    LOGGER.info("Switched into nested iframe")

    # Validate content inside nested frame
    header = driver.find_element(tag_name: "h1").text
    LOGGER.info("Header text inside nested iframe: '#{header}'")
    expect(header).to include("This page is displayed in an iframe")

    # Switch back to outer frame
    driver.switch_to.parent_frame
    LOGGER.info("Returned to outer frame (iframeResult)")

    # Switch back to main page
    driver.switch_to.default_content
    LOGGER.info("Returned to main page context")

    LOGGER.info("ENDING Test --- Completed successfully: Frame Switching ---")
  end
end
