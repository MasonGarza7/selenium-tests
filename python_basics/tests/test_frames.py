from selenium.webdriver.common.by import By
from utils.logger import get_logger

logger = get_logger(__name__)

def test_switch_to_iframe_and_back(driver):
    logger.info("STARTING Test --- Frame Switching ---")

    url = "https://www.w3schools.com/html/tryit.asp?filename=tryhtml_iframe_height_width"
    driver.get(url)
    logger.info(f"Navigated to {url}")

    # Switch to main frame that holds the content preview
    logger.info("Switching to outer frame: 'iframeResult'")
    driver.switch_to.frame("iframeResult")

    # Inside this frame, find the nested iframe element
    logger.info("Locating nested iframe element inside outer frame")
    nested_iframe = driver.find_element(By.TAG_NAME, "iframe")
    driver.switch_to.frame(nested_iframe)
    logger.info("Switched into nested iframe")

    # Validate content inside nested frame
    header = driver.find_element(By.TAG_NAME, "h1").text
    logger.info(f"Header text inside nested iframe: '{header}'")
    assert "This page is displayed in an iframe" in header

    # Switch back to the outer frame
    driver.switch_to.parent_frame()
    logger.info("Returned to outer frame (iframeResult)")

    # Then switch back to main page
    driver.switch_to.default_content()
    logger.info("Returned to main page context")

    logger.info("ENDING Test --- Completed successfully: Frame Switching ---")
