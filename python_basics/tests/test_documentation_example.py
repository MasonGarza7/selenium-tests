# This is the example test script from the Selenium Documenation page:
"""
# from selenium import webdriver

# Create a new Chrome browser instance
driver = webdriver.Chrome()

# Navigate to a website
driver.get("https://www.google.com")

# Close the browser
driver.quit()
"""

# This is the example test script from the Selenium Documenation page 
# but updated to work with the my architecture:
def test_open_browser(driver):
    # Use the driver fixture provided by conftest.py
    driver.get("https://www.google.com")
    title = driver.title
    assert "Google" in title
    