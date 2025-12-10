require "rspec"

RSpec.describe "Checkboxes Toggle" do
  it "toggles all checkboxes and verifies their state changes" do
    LOGGER.info("STARTING Test --- Checkboxes Toggle ---")

    url = "https://the-internet.herokuapp.com/checkboxes"
    driver.navigate.to(url)
    LOGGER.info("Navigated to #{url}")

    checkboxes = driver.find_elements(css: "input[type='checkbox']")
    LOGGER.info("Found #{checkboxes.length} checkboxes")

    checkboxes.each_with_index do |box, index|
      checkbox_number = index + 1

      initial_state = box.selected?
      LOGGER.info("Checkbox #{checkbox_number} initial state: #{initial_state}")

      box.click
      toggled_state = box.selected?
      LOGGER.info("Checkbox #{checkbox_number} toggled state: #{toggled_state}")

      expect(toggled_state).not_to eq(initial_state),
        "Checkbox #{checkbox_number} did not change state (was #{initial_state}, now #{toggled_state})"
    end

    LOGGER.info("ENDING Test --- Completed successfully: Checkboxes Toggle ---")
  end
end
