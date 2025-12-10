require "selenium-webdriver"

module DriverSetup
  # Headless toggle driven by environment variable:
  #   HEADLESS=true  -> headless mode
  #   anything else  -> headed mode
  HEADLESS = ENV["HEADLESS"] == "true"

  def driver
    @driver ||= begin
      options = Selenium::WebDriver::Chrome::Options.new

      # Always maximize in headed mode
      options.add_argument("--start-maximized")

      if HEADLESS
        options.add_argument("--headless=new")
        options.add_argument("--disable-gpu")
        options.add_argument("--window-size=1920,1080")
      end

      Selenium::WebDriver.for(:chrome, options: options)
    end
  end

  def quit_driver
    @driver&.quit
    @driver = nil
  end
end
