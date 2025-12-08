# Mason Garza - Selenium Automation

## Overview
This project demonstrates my proficiency with [Selenium](https://www.selenium.dev/) for web application automation testing.  
It includes common, practical examples of alerts, checkboxes, dropdowns, dynamic controls, file uploads, form authentication, frames, navigation, and failure handling.  

This project will contain sub-projects, one of each of the main Selenium supported programming languages. Currently, only the Python, JavaScript, and C# sub-projects are complete.  
Each sub-project's framework will be independent, built with its own reporting system, logging, and screenshot capture on test failure.

Note:
This framework is built using Selenium WebDriver, the core automation library that provides direct control over web browsers.  
It does not use Selenium IDE (record/playback tool) or Selenium Grid (distributed execution system).  
All interactions, assertions, and reporting are handled through custom scripts using the WebDriver API.  

--- 

## Getting Started
### Clone the repository 
```bash
git clone https://github.com/your-username/selenium-tests.git
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
rmdir /s /q .venv
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
(NOTE: this installs selenium-webdriver, mocha/mochaawesome, winston, fs-extra, cross-env, etc.)
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


## Running tests

### Running all tests:
#### Python (CD the `python_basics/` folder)
```bash
pytest
```
#### JavaScript (CD the `javascript_basics/` folder)
```bash
npm test
```
#### C# (CD the `csharp_basics/` folder)
```bash
.\run_tests.ps1
```

### Running a single test script:
#### Python (CD the `python_basics/` folder)
```bash
pytest tests/test_navigation.py
```
#### JavaScript (CD the `javascript_basics/` folder)
```bash
npm run test:file -- src/tests/navigation.test.js
```
#### C# (CD the `csharp_basics/` folder)
```bash
dotnet test --filter "FullyQualifiedName~NavigationTest"
```

### Running all JavaScript tests headless:
#### JavaScript (CD the `javascript_basics/` folder)
```bash
npm run test:headless
```

### Running all JavaScript tests in FAST headless (CI-optimized):
#### JavaScript (CD the `javascript_basics/` folder)
```bash
npm run test:fast
```

## File Structure:
```bash
.
├── python_basics/                        # Python subproject
│   ├── .venv/                            # Virtual environment (Python dependencies)
│   ├── .pytest_cache/                    # Pytest's temporary cache for previous test runs
│   ├── __pycache__/                      # Compiled Python bytecode files
│   ├── results/                          # Stores Python test run output artifacts
│   │   ├── logs/                         # Timestamped Python test run logs
│   │   ├── screenshots/                  # Screenshots captured on Python test run failures
│   │   └── latest_report.html            # Pytest HTML report (auto-generated each run)
│   ├── tests/                            # Python Selenium test scripts
│   ├── utils/                            # Utility scripts shared across tests
│   │   ├── logger.py                     # Custom logging handler for all test runs
│   │   ├── report_manager.py             # Configures and manages pytest HTML reporting
│   │   └── screenshot_helper.py          # Captures screenshots on Python test failures
│   ├── conftest.py                       # Pytest fixture setup (browser, teardown, global hooks)
│   ├── pytest.ini                        # Pytest configuration (logging, report setup)
│   └── requirements.txt                  # Project dependencies for pip installation
│
├── javascript_basics/                    # JavaScript subproject
│   ├── node_modules/                     # Installed Node.js dependencies for the JS subproject
│   ├── results/                          # Stores JavaScript test run output artifacts
│   │   ├── logs/                         # Timestamped JavaScript test run logs
│   │   ├── report/                       # Mochaawesome HTML report (auto-generated each run)
│   │   └── screenshots/                  # Screenshots captured on JavaScript test run failures 
│   ├── src/                              # Source code for JS tests and supporting utilities
│   │   ├── tests/                        # JavaScript Selenium test scripts 
│   │   ├── utils/                        # Utility scripts shared across tests 
│   │   │   ├── logger.js                 # Custom Winston/fs-extra logging handler for all test runs
│   │   │   ├── screenshot.js             # Captures screenshots on JavaScript test failures 
│   │   │   ├── select.js                 # Helper for selecting dropdown options and handling <select> elements
│   │   │   └── sleep.js                  # Simple utility for adding manual await-based delay between steps
│   │   ├── globalSetup.js                # Builds a fresh driver instance per test and manages headless mode
│   │   └── testSetup.js                  # Global Mocha hooks: start driver, teardown driver, screenshots, logging 
│   ├── temp/                             # Temporary files used by tests (e.g., file-upload temp files)
│   ├── package.json                      # Project manifest defining scripts, dependencies, and test commands
│   └── package-lock.json                 # Lockfile ensuring repeatable installs for all Node.js dependencies
│
├── csharp_basics/                        # C# subproject
│   ├── bin/                              # Compiled output when project is built or tested 
│   ├── obj/                              # Stores intermediate build artifacts used by .NET during compilation 
│   ├── results/                          # Stores C# test run output artifacts
│   │   ├── logs/                         # Timestamped C# test run logs
│   │   └── screenshots/                  # Screenshots captured on C# test run failures
│   ├── temp/                             # Temporary files used by tests (e.g., file-upload temp files)
│   ├── tests/                            # C# Selenium test scripts
│   │   └── BaseTest.cs                   # The core test harness that all tests inherit from 
│   ├── utils/                            # Utility scripts shared across tests
│   │   ├── logger.cs                     # Custom logging handler for all test runs 
│   │   └── ScreenshotHelper.cs           # Utility class responsible for capturing screenshots on test failures 
│   ├── allureConfig.json                 # Allure reporting configuration file. Defines where raw Allure test result files are written
│   ├── csharp_basics.csproj              # C# project definition file. Declares dependencies, build settings, output behaviors, etc. 
│   └── run_tests.ps1                     # PowerShell automation script for executing all tests and generating reports 
│
├── .gitignore                            # Files and directories excluded from version control 
├── README.md                             # Project overview, setup, instructions, and documentation
```

## Test Cases
| Tests | Purpose |
|-|-|
|Navigation|navigate and title validations|
|Form Authentication|success/failure with valid/invalid credentials|
|Checkboxes|toggling checkboxes on/off|
|Dropdown|making selections|
|Dynamic Controls|Remove/Add and Enable/Disable|
|Alerts|Validate each JavaScript alert|
|Frames|frame switching and text validation|
|File Upload|create, upload, and delete temporary files|


## Logging and Reporting Results:
- All Python tests have logs available in the console, the log file, and generate the report `python_basics/results/latest_report.html`
    - Drag and drop the HTML into your browser and you will see the standard pytest report.
    - Logs are written in both the console and to the `python_basics/logs/DATE_TIME.log` file.
- All JavaScript tests have logs available in the console, the log file, and generate the report `javascript_basics/results/report/mochawesome.html`
    - Drag and drop the HTML into your browser and you will see the standard MochaAwesome report. 
    - Logs are written in both the console and to the `javascript_basics/results/logs/test_run_DATE_TIME.log` file. 
- All C# tests have logs written. Allure reports are only generated when using the `run_tests.ps1` script. 
    - Logs are available in the `csharp_basics/results/logs/` file.
    - When using the `run_tests.ps1` script, reports are generated and opened automatically in a chrome browser. 
        - This is because Allure reports generated with `allure generate` are static files that cannot be dragged into web browsers like the other sub-projects as they pose a security risk.
        - To combat this, I used `allure serve` in the PowerShell script to host a local webserver and open the dynamic report automatically, revealing a standard Allure report. 


## Screenshot on Failure:
- When a Python test fails, a screenshot is automatically captured via a Pytest hook (conftest.py).
    - Screenshots are stored in the `python_basics/results/screenshots/` folder with labeled, timestamped file names. 
- When a JavaScript test fails, Mocha automatically triggers a hook `testSetup.js` that captures a screenshot using `screenshot.js`.
    - Screenshots are stored in the `javascript_basics/results/screenshots/` folder with labeled, timestamped file names. 
- When a C# test fails, a screenshot is automatically captured via my own automation layer (BaseTest + ScreenshotHelper).
    - Screenshots are stored in the `csharp_basics/results/screenshots/` folder with labeled, timestamped file names. 


## Next Steps:
- Begin the Ruby subproject