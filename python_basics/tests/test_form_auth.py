from selenium.webdriver.common.by import By
from utils.logger import get_logger
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

logger = get_logger(__name__)

def test_form_authentication_valid_and_invalid(driver):
    logger.info("STARTING Test --- Form Authentication ---")

    base_url = "https://the-internet.herokuapp.com/login"
    driver.get(base_url)
    logger.info(f"Navigated to {base_url}")

    # Invalid login
    logger.info("Attempting invalid login...")
    driver.find_element(By.ID, "username").send_keys("invalidUser")
    driver.find_element(By.ID, "password").send_keys("wrongPass")
    driver.find_element(By.CSS_SELECTOR, "button[type='submit']").click()

    error_msg = driver.find_element(By.ID, "flash").text
    logger.info(f"Invalid login message: {error_msg}")
    assert "Your username is invalid!" in error_msg

    # Valid login
    logger.info("Attempting valid login...")
    driver.get(base_url)
    driver.find_element(By.ID, "username").send_keys("tomsmith")
    driver.find_element(By.ID, "password").send_keys("SuperSecretPassword!")
    driver.find_element(By.CSS_SELECTOR, "button[type='submit']").click()

    wait = WebDriverWait(driver, 5)
    logger.info("Waiting for success message to appear...")
    success_element = wait.until(EC.visibility_of_element_located((By.ID, "flash")))
    success_msg = success_element.text
    logger.info(f"Valid login message: {success_msg}")
    assert "You logged into a secure area!" in success_msg

    logger.info("ENDING Test --- Completed successfully: Form Authentication ---")
