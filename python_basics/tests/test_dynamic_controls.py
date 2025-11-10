from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from utils.logger import get_logger

logger = get_logger(__name__)

def test_dynamic_controls(driver):
    logger.info("STARTING Test --- Dynamic Controls ---")

    url = "https://the-internet.herokuapp.com/dynamic_controls"
    driver.get(url)
    logger.info(f"Navigated to {url}")

    wait = WebDriverWait(driver, 10)

    # Remove Checkbox
    logger.info("Clicking 'Remove' button to remove checkbox")
    remove_button = driver.find_element(By.CSS_SELECTOR, "button[onclick='swapCheckbox()']")
    remove_button.click()

    logger.info("Waiting for checkbox to be removed")
    wait.until(EC.invisibility_of_element_located((By.ID, "checkbox")))
    message = driver.find_element(By.ID, "message").text
    logger.info(f"Message after removal: {message}")
    assert "It's gone!" in message

    # Add Checkbox
    logger.info("Clicking 'Add' button to add checkbox back")
    add_button = driver.find_element(By.CSS_SELECTOR, "button[onclick='swapCheckbox()']")
    add_button.click()

    logger.info("Waiting for checkbox to reappear")
    wait.until(EC.visibility_of_element_located((By.ID, "checkbox")))
    message = driver.find_element(By.ID, "message").text
    logger.info(f"Message after adding: {message}")
    assert "It's back!" in message

    # Enable input field
    logger.info("Clicking 'Enable' button to activate input field")
    enable_button = driver.find_element(By.CSS_SELECTOR, "button[onclick='swapInput()']")
    enable_button.click()

    logger.info("Waiting for input field to become enabled")
    wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "#input-example input")))
    message = driver.find_element(By.ID, "message").text
    logger.info(f"Message after enabling: {message}")
    assert "It's enabled!" in message

    # Disable input field
    logger.info("Clicking 'Disable' button to deactivate input field")
    disable_button = driver.find_element(By.CSS_SELECTOR, "button[onclick='swapInput()']")
    disable_button.click()

    logger.info("Waiting for input field to become disabled")
    wait.until(lambda d: not d.find_element(By.CSS_SELECTOR, "#input-example input").is_enabled())
    message = driver.find_element(By.ID, "message").text
    logger.info(f"Message after disabling: {message}")
    assert "It's disabled!" in message

    logger.info("ENDING Test --- Completed successfully: Dynamic Controls ---")
