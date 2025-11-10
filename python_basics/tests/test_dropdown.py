from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import Select
from utils.logger import get_logger

logger = get_logger(__name__)

def test_dropdown_selection(driver):
    logger.info("STARTING Test --- Dropdown Selection ---")

    url = "https://the-internet.herokuapp.com/dropdown"
    driver.get(url)
    logger.info(f"Navigated to {url}")

    dropdown_element = driver.find_element(By.ID, "dropdown")
    dropdown = Select(dropdown_element)
    logger.info("Located dropdown element")

    # Verify default selection
    default_option = dropdown.first_selected_option.text.strip()
    logger.info(f"Default selected option: '{default_option}'")
    assert default_option == "Please select an option"

    # Select Option 1
    logger.info("Selecting 'Option 1'")
    dropdown.select_by_visible_text("Option 1")
    selected_text = dropdown.first_selected_option.text.strip()
    logger.info(f"Selected option: '{selected_text}'")
    assert selected_text == "Option 1"

    # Select Option 2
    logger.info("Selecting 'Option 2'")
    dropdown.select_by_visible_text("Option 2")
    selected_text = dropdown.first_selected_option.text.strip()
    logger.info(f"Selected option: '{selected_text}'")
    assert selected_text == "Option 2"

    logger.info("ENDING Test --- Completed successfully: Dropdown Selection ---")
