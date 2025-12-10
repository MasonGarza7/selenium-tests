require "rspec"

RSpec.describe "Selenium smoke test" do
  it "opens a page and checks the title" do
    driver.navigate.to "https://the-internet.herokuapp.com/"
    expect(driver.title).to include("The Internet")
  end
end
