require "rspec"
require "fileutils"
require_relative "../src/support/driver_setup"
require_relative "../src/support/logger"

ROOT_DIR        = File.expand_path("..", __dir__)           # ruby_basics/
RESULTS_DIR     = File.join(ROOT_DIR, "results")            # ruby_basics/results
SCREENSHOTS_DIR = File.join(RESULTS_DIR, "screenshots")

FileUtils.mkdir_p(SCREENSHOTS_DIR)

LOGGER      = TestLogger.create_logger
RUN_RESULTS = []  # collected per example for HTML report

RSpec.configure do |config|
  config.include DriverSetup

  config.before(:suite) do
    LOGGER.info("Starting test suite")
  end

  config.before(:each) do |example|
    # LOGGER.info("Starting example: #{example.full_description}")
    example.metadata[:start_time] = Time.now
    driver
  end

  config.after(:each) do |example|
    end_time   = Time.now
    start_time = example.metadata[:start_time] || end_time
    duration   = (end_time - start_time).round(3)

    status =
      if example.exception
        "failed"
      elsif example.execution_result.status == :pending
        "pending"
      else
        "passed"
      end

    screenshot_rel = nil

    if example.exception
      timestamp  = Time.now.strftime("%Y%m%d_%H%M%S")
      safe_name  = example.description.gsub(/[^\w]+/, "_").downcase
      file_name  = "#{safe_name}_#{timestamp}.png"
      file_path  = File.join(SCREENSHOTS_DIR, file_name)

      driver.save_screenshot(file_path)

      LOGGER.error("Example failed: #{example.full_description}")
      LOGGER.error("Screenshot saved to: #{file_path}")
      LOGGER.error("Exception: #{example.exception.class} - #{example.exception.message}")

      puts "Saved screenshot: #{file_path}"

      # path relative to results/ so links work from report.html
      screenshot_rel = file_path.sub("#{RESULTS_DIR}#{File::SEPARATOR}", "")
    else
      LOGGER.info("Example #{status}: #{example.full_description}")
    end

    RUN_RESULTS << {
      description: example.full_description,
      file_path:   example.metadata[:file_path],
      status:      status,
      duration:    duration,
      screenshot:  screenshot_rel
    }

    quit_driver
  end

  config.after(:suite) do
    LOGGER.info("Test suite finished")
  end
end

# Generate HTML report when RSpec exits
at_exit do
  FileUtils.mkdir_p(RESULTS_DIR)
  report_path = File.join(RESULTS_DIR, "report.html")

  File.open(report_path, "w") do |file|
    file.puts "<!DOCTYPE html>"
    file.puts "<html><head><meta charset=\"UTF-8\">"
    file.puts "<title>Selenium Ruby Test Report</title>"
    file.puts "<style>"
    file.puts "body{font-family:Arial, sans-serif;margin:20px;}"
    file.puts "table{border-collapse:collapse;width:100%;}"
    file.puts "th,td{border:1px solid #ddd;padding:8px;font-size:14px;}"
    file.puts "th{background:#f5f5f5;text-align:left;}"
    file.puts ".passed{color:green;font-weight:bold;}"
    file.puts ".failed{color:red;font-weight:bold;}"
    file.puts ".pending{color:orange;font-weight:bold;}"
    file.puts "</style>"
    file.puts "</head><body>"

    file.puts "<h1>Selenium Ruby Test Report</h1>"
    file.puts "<p>Generated at: #{Time.now}</p>"

    file.puts "<table>"
    file.puts "<tr><th>Example</th><th>File</th><th>Status</th><th>Duration (s)</th><th>Screenshot</th></tr>"

    RUN_RESULTS.each do |result|
      file.puts "<tr>"
      file.puts "<td>#{result[:description]}</td>"
      file.puts "<td>#{result[:file_path]}</td>"
      file.puts "<td class=\"#{result[:status]}\">#{result[:status]}</td>"
      file.puts "<td>#{result[:duration]}</td>"

      if result[:screenshot]
        file.puts "<td><a href=\"#{result[:screenshot]}\">View</a></td>"
      else
        file.puts "<td>-</td>"
      end

      file.puts "</tr>"
    end

    file.puts "</table>"
    file.puts "</body></html>"
  end
end
