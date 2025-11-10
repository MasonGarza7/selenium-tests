from selenium.webdriver.common.by import By
from utils.logger import get_logger
from selenium.webdriver.common.alert import Alert

logger = get_logger(__name__)

def test_javascript_alerts(driver):
    logger.info("STARTING Test --- JavaScript Alerts ---")

    url = "https://the-internet.herokuapp.com/javascript_alerts"
    driver.get(url)
    logger.info(f"Navigated to {url}")

    # Simple alert 
    logger.info("Triggering simple alert")
    driver.find_element(By.XPATH, "//button[text()='Click for JS Alert']").click()
    alert = Alert(driver)
    logger.info(f"Alert text: {alert.text}")
    alert.accept()
    result = driver.find_element(By.ID, "result").text
    logger.info(f"Result after accepting simple alert: {result}")
    assert result == "You successfully clicked an alert"

    # Confirmation alert 
    logger.info("Triggering confirmation alert")
    driver.find_element(By.XPATH, "//button[text()='Click for JS Confirm']").click()
    alert = Alert(driver)
    logger.info(f"Alert text: {alert.text}")
    alert.dismiss()
    result = driver.find_element(By.ID, "result").text
    logger.info(f"Result after dismissing confirm: {result}")
    assert result == "You clicked: Cancel"

    driver.find_element(By.XPATH, "//button[text()='Click for JS Confirm']").click()
    alert = Alert(driver)
    alert.accept()
    result = driver.find_element(By.ID, "result").text
    logger.info(f"Result after accepting confirm: {result}")
    assert result == "You clicked: Ok"

    # Prompt alert 
    logger.info("Triggering prompt alert")
    driver.find_element(By.XPATH, "//button[text()='Click for JS Prompt']").click()
    alert = Alert(driver)
    logger.info(f"Alert text: {alert.text}")
    test_input = "Mason"
    alert.send_keys(test_input)
    alert.accept()
    result = driver.find_element(By.ID, "result").text
    logger.info(f"Result after submitting prompt: {result}")
    assert result == f"You entered: {test_input}"

    logger.info("ENDING Test --- Completed successfully: JavaScript Alerts ---")
