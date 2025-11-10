import pytest
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from utils.logger import get_logger
from utils.screenshot_helper import take_screenshot
import os

# Initialize shared logger and report manager
logger = get_logger("TestLogger")

def pytest_addoption(parser):
    """Add a command-line option for headless mode."""
    parser.addoption("--headless", action="store_true", help="Run browser in headless mode")

@pytest.fixture(scope="function")
def driver(request):
    """Setup and teardown for WebDriver."""
    headless = request.config.getoption("--headless")

    options = Options()
    if headless:
        options.add_argument("--headless=new")
        options.add_argument("--disable-gpu")
    options.add_argument("--start-maximized")

    logger.info("Starting browser session...")
    driver = webdriver.Chrome(options=options)

    yield driver  # Provide driver to the test

    logger.info("Closing browser session...")
    driver.quit()

@pytest.hookimpl(hookwrapper=True)
def pytest_runtest_makereport(item, call):
    """Hook for taking screenshots on test failure."""
    outcome = yield
    report = outcome.get_result()

    if report.when == "call" and report.failed:
        driver = item.funcargs.get("driver")
        if driver:
            test_name = item.name
            screenshot_path = take_screenshot(driver, test_name)
            logger.error(f"Test failed: {test_name}")
            logger.error(f"Screenshot saved at: {screenshot_path}")

            # Attach screenshot path to the pytest-html report
            if hasattr(report, "extra"):
                from pytest_html import extras
                report.extra.append(extras.image(screenshot_path))
