require "rspec"
require "fileutils"

RSpec.describe "File Upload" do
  it "uploads a file and verifies success" do
    LOGGER.info("STARTING Test --- File Upload ---")

    url = "https://the-internet.herokuapp.com/upload"
    driver.navigate.to(url)
    LOGGER.info("Navigated to #{url}")

    # Project root (ruby_basics/)
    root_dir = File.expand_path("..", __dir__)

    # Create temp/ directory if missing
    temp_dir = File.join(root_dir, "temp")
    FileUtils.mkdir_p(temp_dir)

    # Create temporary file
    temp_file_path = File.join(temp_dir, "sample_upload.txt")
    File.open(temp_file_path, "w") do |file|
      file.write("This is a test file for Selenium upload automation.")
    end

    LOGGER.info("Temporary file created at: #{temp_file_path}")

    # Upload file
    file_input = driver.find_element(id: "file-upload")
    file_input.send_keys(temp_file_path)
    LOGGER.info("File path sent to file input field")

    upload_button = driver.find_element(id: "file-submit")
    upload_button.click
    LOGGER.info("Clicked Upload button")

    # Validate upload header
    success_header = driver.find_element(tag_name: "h3").text
    LOGGER.info("Upload confirmation header: '#{success_header}'")
    expect(success_header).to eq("File Uploaded!")

    # Validate uploaded file name
    uploaded_file_name = driver.find_element(id: "uploaded-files").text
    LOGGER.info("Uploaded file name: '#{uploaded_file_name}'")
    expect(uploaded_file_name).to eq("sample_upload.txt")

    # Cleanup
    File.delete(temp_file_path) if File.exist?(temp_file_path)
    LOGGER.info("Temporary file deleted")

    LOGGER.info("ENDING Test --- Completed successfully: File Upload ---")
  end
end
