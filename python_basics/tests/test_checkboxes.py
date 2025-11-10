from selenium.webdriver.common.by import By
from utils.logger import get_logger

logger = get_logger(__name__)

def test_checkboxes_toggle(driver):
    logger.info("STARTING Test --- Checkboxes Toggle ---")

    url = "https://the-internet.herokuapp.com/checkboxes"
    driver.get(url)
    logger.info(f"Navigated to {url}")

    checkboxes = driver.find_elements(By.CSS_SELECTOR, "input[type='checkbox']")
    logger.info(f"Found {len(checkboxes)} checkboxes")

    for i, box in enumerate(checkboxes, start=1):
        initial_state = box.is_selected()
        logger.info(f"Checkbox {i} initial state: {initial_state}")

        # Toggle state
        box.click()
        toggled_state = box.is_selected()
        logger.info(f"Checkbox {i} toggled state: {toggled_state}")

        assert toggled_state != initial_state, (
            f"Checkbox {i} did not change state (was {initial_state}, now {toggled_state})"
        )

    logger.info("ENDING Test --- Completed successfully: Checkboxes Toggle ---")
