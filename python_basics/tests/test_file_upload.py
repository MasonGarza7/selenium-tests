import os
from selenium.webdriver.common.by import By
from utils.logger import get_logger

logger = get_logger(__name__)

def test_file_upload(driver):
    logger.info("STARTING Test --- File Upload ---")

    url = "https://the-internet.herokuapp.com/upload"
    driver.get(url)
    logger.info(f"Navigated to {url}")

    # Create a temporary file to upload
    temp_file_path = os.path.join(os.getcwd(), "sample_upload.txt")
    with open(temp_file_path, "w") as f:
        f.write("This is a test file for Selenium upload automation.")

    logger.info(f"Temporary file created at: {temp_file_path}")

    # Locate file input and upload the file
    file_input = driver.find_element(By.ID, "file-upload")
    file_input.send_keys(temp_file_path)
    logger.info("File path sent to file input field")

    # Click the upload button
    upload_button = driver.find_element(By.ID, "file-submit")
    upload_button.click()
    logger.info("Clicked Upload button")

    # Validate successful upload
    success_header = driver.find_element(By.TAG_NAME, "h3").text
    logger.info(f"Upload confirmation header: '{success_header}'")
    assert success_header == "File Uploaded!"

    uploaded_file_name = driver.find_element(By.ID, "uploaded-files").text
    logger.info(f"Uploaded file name: '{uploaded_file_name}'")
    assert uploaded_file_name == "sample_upload.txt"

    # Cleanup
    os.remove(temp_file_path)
    logger.info("Temporary file deleted")
    logger.info("ENDING Test --- Completed successfully: File Upload ---")
