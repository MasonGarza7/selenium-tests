# Mason Garza - Selenium Automation

## Overview
This project demonstrates my proficiency with [Selenium](https://www.selenium.dev/) for web application automation testing.  
Each subproject includes common, practical test case examples. These tests include: alerts, checkboxes, dropdowns, dynamic controls, file uploads, form authentication, frames, navigation, and failure handling.  

This project contains subprojects, one of each of the main programming languages supported by Selenium. These languages are Python, JavaScript, C#, Ruby, Java, and Kotlin.    
Each subproject's framework is independent and built with its own industry-standard reporting system, logging, and screenshot capture on test failure.

Notes:
This framework is built using Selenium WebDriver, the core automation library that provides direct control over web browsers.  
It does not use Selenium IDE (record/playback tool) or Selenium Grid (distributed execution system).  
All interactions, assertions, and reporting are handled through custom scripts using the WebDriver API.  

--- 

## Getting Started
(All commands must be run inside their respective subproject folders.)
### Clone the repository 
```bash
git clone https://github.com/MasonGarza7/selenium-tests.git
cd selenium-tests
```

### Install Python dependencies:
0. CD the `python_basics/` subproject folder
1. Create and activate a virtual environment by running: 
```bash
python -m venv .venv
.\.venv\Scripts\Activate.ps1
```
2. Install the dependencies from the `Requirements.txt` file by running: 
```bash
pip install -r requirements.txt
```
3. (OPTIONAL) Deactivate your virtual environment by running:
```bash
deactivate
```

### Install JavaScript dependencies: 
0. CD the `javascript_basics/` subfolder 
1. This project requires Node and NPM. Verify you have them installed by running:  
(NOTE: I'm using Node v24.11.0 and NPM 11.6.1)
```bash
node -v
npm -v
```
2. Install the dependencies by running:  
(NOTE: this installs selenium-webdriver, mocha/mochawesome, winston, fs-extra, cross-env, etc.)
```bash
npm install
```
3.  Have Google Chrome installed 

### Install C# dependencies:
0. CD the `csharp_basics/` subfolder 
1. Restore NuGet packages by running:
```bash
dotnet restore
```

### Install Ruby dependencies:
0. CD the `ruby_basics/` subfolder 
1. Download and install Ruby 3.x from the [official website](https://rubyinstaller.org/downloads/)
2. Ensure Google Chrome is installed 
3. Install Ruby dependencies by running: 
```bash
bundle install
```

### Install Java dependencies: 
0. CD the `java_basics/` subfolder 
1. Download and install Java 21 from the [official website](https://www.oracle.com/java/technologies/downloads/#java21)
2. Download and install Maven 3.9.x from the [official website](https://maven.apache.org/download.cgi)

### Install Kotlin dependencies: 
0. CD the `kotlin_basics/` subfolder 
1. Download and install Java 21 from the [official website](https://www.oracle.com/java/technologies/downloads/#java21)
2. Download and install Gradle from the [official website](https://gradle.org/releases/)
3. Optional: Clean previous artifacts by running: 
```bash
.\gradlew clean
```


## Running tests

### Running all tests:
1. Python (CD the `python_basics/` folder)
```bash
pytest
```
2. JavaScript (CD the `javascript_basics/` folder)
```bash
npm test
```
3. C# (CD the `csharp_basics/` folder)
```bash
.\run_tests.ps1
```
4. Ruby (CD the `ruby_basics/` folder)
```bash
bundle exec rspec spec
```
5. Java (CD the `java_basics/` folder)
```bash
mvn test
```
6. Kotlin (CD the `kotlin_basics/` folder)
```bash
.\gradlew test
```


### Running a single test script:
1. Python (CD the `python_basics/` folder)
```bash
pytest tests/test_navigation.py
```
2. JavaScript (CD the `javascript_basics/` folder)
```bash
npm run test:file -- src/tests/navigation.test.js
```
3. C# (CD the `csharp_basics/` folder)
```bash
dotnet test --filter "FullyQualifiedName~NavigationTest"
```
4. Ruby (CD the `ruby_basics/` folder)
```bash
bundle exec rspec spec/navigation_spec.rb
```
5. Java (CD the `java_basics/` folder)
```bash
mvn -Dtest=NavigationTest test
```
6. Kotlin (CD the `kotlin_basics/` folder)
```bash
.\gradlew test --tests "tests.NavigationTest"
```


### Running all tests in headless mode:
1. Python (CD the `python_basics/` folder)
```bash
pytest --headless
```
2. JavaScript (CD the `javascript_basics/` folder)
```bash
npm run test:headless
npm run test:fast # FAST headless mode (CI-optimized) 
```
3. C# (CD the `csharp_basics/` folder)
```bash
.\run_tests.ps1 -Headless
Remove-Item Env:\HEADLESS # Run this after the headless test run completes
```
4. Ruby (CD the `ruby_basics/` folder)
```bash
$env:HEADLESS = "true"; bundle exec rspec
Remove-Item Env:HEADLESS # Run this after the headless test run completes
```
5. Java (CD the `java_basics/` folder)
```bash
mvn -Dheadless=true test
```
6. Kotlin (CD the `kotlin_basics/` folder)
```bash
$env:HEADLESS="true"; .\gradlew test
Remove-Item Env:HEADLESS # Run this after the headless test run completes 
```


## File Structure:
```bash
.
├── python_basics/                         # Python subproject
│   ├── .venv/                             # Virtual environment (Python dependencies)
│   ├── .pytest_cache/                     # Pytest's temporary cache for previous test runs
│   ├── __pycache__/                       # Compiled Python bytecode files
│   ├── results/                           # Stores Python test run output artifacts
│   │   ├── logs/                          # Timestamped Python test run logs
│   │   ├── screenshots/                   # Screenshots captured on Python test run failures
│   │   └── latest_report.html             # Pytest HTML report (auto-generated each run)
│   ├── tests/                             # Python Selenium test scripts
│   ├── utils/                             # Utility scripts shared across tests
│   │   ├── logger.py                      # Custom logging handler for all test runs
│   │   ├── report_manager.py              # Configures and manages pytest HTML reporting
│   │   └── screenshot_helper.py           # Captures screenshots on Python test failures
│   ├── conftest.py                        # Pytest fixture setup (browser, teardown, global hooks)
│   ├── pytest.ini                         # Pytest configuration (logging, report setup)
│   └── requirements.txt                   # Project dependencies for pip installation
│
├── javascript_basics/                     # JavaScript subproject
│   ├── node_modules/                      # Installed Node.js dependencies for the JS subproject
│   ├── results/                           # Stores JavaScript test run output artifacts
│   │   ├── logs/                          # Timestamped JavaScript test run logs
│   │   ├── report/                        # Mochawesome HTML report (auto-generated each run)
│   │   └── screenshots/                   # Screenshots captured on JavaScript test run failures 
│   ├── src/                               # Source code for JS tests and supporting utilities
│   │   ├── tests/                         # JavaScript Selenium test scripts 
│   │   ├── utils/                         # Utility scripts shared across tests 
│   │   │   ├── logger.js                  # Custom Winston/fs-extra logging handler for all test runs
│   │   │   ├── screenshot.js              # Captures screenshots on JavaScript test failures 
│   │   │   ├── select.js                  # Helper for selecting dropdown options and handling <select> elements
│   │   │   └── sleep.js                   # Simple utility for adding manual await-based delay between steps
│   │   ├── globalSetup.js                 # Builds a fresh driver instance per test and manages headless mode
│   │   └── testSetup.js                   # Global Mocha hooks: start driver, teardown driver, screenshots, logging 
│   ├── temp/                              # Temporary files used by tests (e.g., file-upload temp files)
│   ├── package.json                       # Project manifest defining scripts, dependencies, and test commands
│   └── package-lock.json                  # Lockfile ensuring repeatable installs for all Node.js dependencies
│
├── csharp_basics/                         # C# subproject
│   ├── bin/                               # Compiled output when project is built or tested 
│   ├── obj/                               # Stores intermediate build artifacts used by .NET during compilation 
│   ├── results/                           # Stores C# test run output artifacts
│   │   ├── logs/                          # Timestamped C# test run logs
│   │   └── screenshots/                   # Screenshots captured on C# test run failures
│   ├── temp/                              # Temporary files used by tests (e.g., file-upload temp files)
│   ├── tests/                             # C# Selenium test scripts
│   │   └── BaseTest.cs                    # The core test harness that all tests inherit from 
│   ├── utils/                             # Utility scripts shared across tests
│   │   ├── logger.cs                      # Custom logging handler for all test runs 
│   │   └── ScreenshotHelper.cs            # Utility class responsible for capturing screenshots on test failures 
│   ├── allureConfig.json                  # Allure reporting configuration file. Defines where raw Allure test result files are written
│   ├── csharp_basics.csproj               # C# project definition file. Declares dependencies, build settings, output behaviors, etc. 
│   └── run_tests.ps1                      # PowerShell automation script for executing all tests and generating reports 
│
├── ruby_basics/                           # Ruby subproject
│   ├── results/                           # Stores Ruby test run output artifacts
│   │   ├── logs/                          # Timestamped Ruby test run logs
│   │   ├── screenshots/                   # Screenshots captured on Ruby test run failures 
│   │   └── report.html                    # Standard Ruby HTML report (auto-generated each run)
│   ├── spec/                              # Ruby Selenium test scripts
│   │   └── spec_helper.rb                 # Global test harness configuring RSpec, WebDriver lifecycle, logging, screenshots, HTML report generation, etc.
│   ├── src/support/                       # Utility scripts shared across tests
│   │   ├── driver_setup.rb                # Centralized Selenium WebDriver setup/teardown, including headless mode 
│   │   └── logger.rb                      # Custom logger utility script 
│   ├── temp/                              # Temporary files used by tests (e.g., file-upload temp files) 
│   ├── .rspec                             # RSpec configuration file that defines default options and automatically loads 
│   ├── gemfile                            # Declares Ruby gem dependencies required to run the Selenium test framework 
│   └── gemfile.lock                       # Locks exact gem versions to ensure dependency consistency across different environments  
│
├── java_basics/                           # Java subproject
│   ├── results/                           # Stores Java test run output artifacts
│   │   ├── logs/                          # Timestamped Java test run logs
│   │   └── screenshots/                   # Screenshots captured on Java test run failures 
│   ├── src/test/java/com/user/selenium/   # Java Selenium utility, listener, and test scripts
│   │   ├── listeners/                     # TestNG listeners 
│   │   │   └── ScreenshotListener.java    # Listener that captures screenshots on test failure 
│   │   ├── utils/                         # Utility scripts shared across tests 
│   │   │   └── LoggerUtil.java            # Custom logger used across all Java tests 
│   │   └── BaseTest.java                  # Global test harness configuring TestNG, WebDriver lifecycle, logging, screenshots, HTML report generation, etc.
│   ├── target/                            # Maven output build folder 
│   │   ├── generated-test-sources/        # Auto-generated code used during Maven’s test build phase 
│   │   ├── maven-status/                  # Internal Maven metadata for tracking compilation/test execution
│   │   ├── surefire-reports/              # Default TestNG HTML/XML reports generated by Maven Surefire
│   │   │   └── index.html                 # Maven HTML report (auto-generated each run)
│   │   └── test-classes/                  # Compiled Java test classes ready for execution
│   ├── temp/                              # Temporary files used by tests (e.g., file-upload temp files) 
│   └── pom.xml                            # Maven build file defining dependencies, plugins, and project configuration  
│
├── Kotlin_basics/                         # Kotlin subproject
│   ├── .gradle/                           # Local Gradle cache and metadata (auto-generated)
│   ├── build/                             # Compiled classes, test outputs, and Gradle build artifacts
│   ├── gradle/                            # Gradle wrapper files and configuration
│   ├── results/                           # Stores Kotlin test run output artifacts
│   │   ├── logs/                          # Timestamped Kotlin test run logs
│   │   ├── screenshots/                   # Screenshots captured on Kotlin test run failures 
│   │   └── report.html                    # Kotlin HTML report (auto-generated each run)
│   ├── src/test/                          # Kotlin test source root
│   │   ├── Kotlin/                        # Kotlin test code
│   │   │   ├── base/                      # Base test framework and global configuration
│   │   │   │    └── BaseTest.kt           # Global test harness configuring TestNG, WebDriver lifecycle, logging, screenshots, HTML report generation, etc.
│   │   │   ├── listeners/                 # TestNG listeners for cross-cutting test behavior
│   │   │   │    ├── ExtentTestListener.kt # TestNG listener that manages Extent HTML reporting
│   │   │   │    └── ScreenshotListener.kt # Listener that captures screenshots on test failure 
│   │   │   ├── tests/                     # Kotlin Selenium test scripts 
│   │   │   └── utils/                     # Utility scripts shared across tests 
│   │   │   │    ├── DriverFactory.kt      # Centralized WebDriver creation and browser configuration (headed/headless)
│   │   │   │    ├── LoggerUtil.kt         # Custom logger used across all Kotlin tests
│   │   │   │    └── ReportManager.kt      # Extent report lifecycle and configuration
│   │   ├── resources/                     # Test resources and configuration files
│   │   │   └── logback-test.xml           # Logback configuration for test logging output
│   │   ├── temp/                          # Temporary files used by tests (e.g., file-upload temp files) 
│   ├── build.gradle.kts                   # Gradle build configuration for Kotlin + Selenium + TestNG
│   ├── gradle.properties                  # Gradle project settings (e.g., caching behavior)
│   ├── gradlew                            # Gradle wrapper script (Unix/macOS)
│   ├── gradlew.bat                        # Gradle wrapper script (Windows)
│   └── settings.gradle.kts                # Gradle project name and settings
│
├── .gitignore                             # Files and directories excluded from version control 
├── README.md                              # Project overview, setup, instructions, and documentation
```

## Test Cases
| Tests | Purpose |
|-|-|
|Navigation|navigate and title validations|
|Form Authentication|success/failure with valid/invalid credentials|
|Checkboxes|toggling checkboxes on/off|
|Dropdown|making selections|
|Dynamic Controls|remove/Add and Enable/Disable|
|Alerts|validate each JavaScript alert|
|Frames|frame switching and text validation|
|File Upload|create, upload, and delete temporary files|
|Intentional Failure|validate screenshot-on-failure|


## Logging and Reporting Results:
- All Python tests have logs available in the console, the log file, and generate the report `python_basics/results/latest_report.html`
    - Drag and drop the HTML file into your browser and you will see the standard pytest report.
    - Logs are written in both the console and to the `python_basics/logs/` folder.
- All JavaScript tests have logs available in the console, the log file, and generate the report `javascript_basics/results/report/mochawesome.html`
    - Drag and drop the HTML file into your browser and you will see the standard Mochawesome report. 
    - Logs are written in both the console and to the `javascript_basics/results/logs/` folder. 
- All C# tests have logs written. Allure reports are only generated when using the `run_tests.ps1` script. 
    - Logs are available in the `csharp_basics/results/logs/` folder.
    - When using the `run_tests.ps1` script, reports are generated and opened automatically in a Chrome browser. 
        - This is because Allure reports generated with `allure generate` are static files that cannot be dragged into web browsers like the other subprojects due to browser restrictions on local dynamic content.
        - To combat this, I used `allure serve` in the PowerShell script to host a local webserver and open the dynamic report automatically, revealing a standard Allure report. 
- All Ruby tests have logs available in the log file and generate the report `ruby_basics/results/report.html`
    - Drag and drop the HTML file into your browser and you will see the standard Ruby report 
    - Logs are written to the `ruby_basics/results/logs/` folder.  
- All Java tests have logs available in the console, the log file, and generate the report `java_basics/target/surefire-reports/index.html`  
    - Drag and drop the HTML file into your browser and you will see the standard Maven report.  
    - Logs are written in both the console and to the `java_basics/results/logs/` folder.  
- All Kotlin tests have logs available in the generated log file and generate the report `kotlin_basics/results/report.html`
    - Drag and drop the HTML file into your browser and you will see the standard Kotlin report. 
    - Logs are written to the `kotlin_basics/results/logs/` folder. 


## Screenshot on Failure:
- When a Python test fails, a screenshot is automatically captured via a Pytest hook (conftest.py).
    - Screenshots are stored in the `python_basics/results/screenshots/` folder with labeled, timestamped file names. 
- When a JavaScript test fails, Mocha automatically triggers a hook `testSetup.js` that captures a screenshot using `screenshot.js`.
    - Screenshots are stored in the `javascript_basics/results/screenshots/` folder with labeled, timestamped file names. 
- When a C# test fails, a screenshot is automatically captured via my own automation layer (BaseTest + ScreenshotHelper).
    - Screenshots are stored in the `csharp_basics/results/screenshots/` folder with labeled, timestamped file names. 
- When a Ruby test fails, a screenshot is automatically captured via my own automation layer (spec_helper.rb).
    - Screenshots are stored in the `ruby_basics/results/screenshots/` folder with labeled, timestamped file names.  
- When a Java test fails, a screenshot is automatically captured via my the `ScreenshotListener.java` listener script.  
    - Screenshots are stored in the `java_basics/results/screenshots/` folder with labeled, timestamped file names.  
- When a Kotlin test fails, a screenshot is automatically captured via my `ScreenshotListener.kt` listener script. 
    - Screenshots are stored in the `kotlin_basics/results/screenshots/` folder with labeled, timestamped file names. 


## Final Thoughts: 
This project was fun!  

Four weeks ago, I had very Selenium experience. I had only messed with the IDE to try recording some test scripts. Now I believe I would be comfortable being handed a professional project or creating a new one.  

I also had no experience with Ruby or Kotlin, and funny enough, Ruby was the easiest subproject to setup. I was surprised how similar Kotlin and Java operate as well; the Kotlin subproject is nearly identical to the Java subproject.  

I did not experience any major blockers throughout this project, having learned a lot from my Playwright automation project. I used TestNG for Java/Kotlin from the beginning to avoid the JUnit5 issues I was seeing with Playwright. The C# subproject took the longest as I used Allure reporting for the first time. I was having issues with the HTML report being generated as a static file that could not be dragged into an open browser like I wanted. I ended up having to generate dynamic reports that opened automatically with the .ps1 test runner. I doubt it's a big deal since I imagine many engineers would prefer test reports open automatically, but I personally prefer having report files generated that can be manually opened and stored.  

In conclusion, I am very proud of this project and I will definitely be using it throughout my career as a cheatsheet and I welcome others to copy my homework!  

Thank you for reading,  
Mason Garza 