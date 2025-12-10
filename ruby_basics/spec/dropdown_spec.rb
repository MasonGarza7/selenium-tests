require "rspec"

RSpec.describe "Dropdown Selection" do
  it "selects multiple options and verifies correct values" do
    LOGGER.info("STARTING Test --- Dropdown Selection ---")

    url = "https://the-internet.herokuapp.com/dropdown"
    driver.navigate.to(url)
    LOGGER.info("Navigated to #{url}")

    dropdown_element = driver.find_element(id: "dropdown")
    LOGGER.info("Located dropdown element")

    # Wrap element with Selenium::WebDriver Select behavior
    dropdown = Selenium::WebDriver::Support::Select.new(dropdown_element)

    # Verify default selection
    default_option = dropdown.first_selected_option.text.strip
    LOGGER.info("Default selected option: '#{default_option}'")
    expect(default_option).to eq("Please select an option")

    # Select Option 1
    LOGGER.info("Selecting 'Option 1'")
    dropdown.select_by(:text, "Option 1")
    selected_text = dropdown.first_selected_option.text.strip
    LOGGER.info("Selected option: '#{selected_text}'")
    expect(selected_text).to eq("Option 1")

    # Select Option 2
    LOGGER.info("Selecting 'Option 2'")
    dropdown.select_by(:text, "Option 2")
    selected_text = dropdown.first_selected_option.text.strip
    LOGGER.info("Selected option: '#{selected_text}'")
    expect(selected_text).to eq("Option 2")

    LOGGER.info("ENDING Test --- Completed successfully: Dropdown Selection ---")
  end
end
