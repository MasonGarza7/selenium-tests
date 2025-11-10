from selenium.webdriver.common.by import By
from utils.logger import get_logger

logger = get_logger(__name__)

def test_intentional_failure_example(driver):
    logger.info("Starting Test: Intentional Failure Example")

    url = "https://the-internet.herokuapp.com/"
    driver.get(url)
    logger.info(f"Navigated to {url}")

    # Intentionally incorrect assertion
    logger.info("Attempting to locate header and assert incorrect text")
    header = driver.find_element(By.TAG_NAME, "h1").text
    logger.info(f"Found header text: '{header}'")

    # This assertion is intentionally wrong to trigger a failure
    assert header == "This text does not exist", "Intentional failure triggered"

    logger.info("This line should never execute if the assertion fails")
