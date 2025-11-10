from selenium.webdriver.common.by import By
from utils.logger import get_logger

logger = get_logger(__name__)

def test_navigation_and_title(driver):
    logger.info("STARTING Test --- Navigation & Page Title Validation ---")

    # Step 1: Navigate to main page
    url = "https://the-internet.herokuapp.com/"
    logger.info(f"Navigating to {url}")
    driver.get(url)

    # Step 2: Locate and click the 'Form Authentication' link
    logger.info("Locating 'Form Authentication' link")
    link = driver.find_element(By.LINK_TEXT, "Form Authentication")
    logger.info("Clicking 'Form Authentication' link")
    link.click()

    # Step 3: Validate that navigation succeeded
    logger.info("Validating that header text equals 'Login Page'")
    header = driver.find_element(By.TAG_NAME, "h2").text
    assert header == "Login Page", f"Unexpected header text: {header}"

    # Step 4: (Optional) Confirm URL
    current_url = driver.current_url
    logger.info(f"Current URL after navigation: {current_url}")
    assert "login" in current_url, f"Expected 'login' in URL but got {current_url}"

    logger.info("ENDING TEST --- Completed successfully: Navigation & Page Title Validation ---")
