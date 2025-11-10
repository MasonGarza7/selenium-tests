# Mason Garza - Selenium Automation

## Overview
This project demonstrates my proficiency with [Selenium](https://www.selenium.dev/) for web application automation testing.  
It includes common, practical examples of alerts, checkboxes, dropdowns, dynamic controls, file uploads, form authentication, frames, navigation, and failure handling.  

This project will contain sub-projects, one of each of the main Selenium supported programming languages. Currently, only the Python sub-project is complete.  
Each sub-project's framework will be independent, built with its own reporting system, logging, and screenshot capture on test failure.

--- 

## Getting Started
### Clone the repository 
```bash
git clone https://github.com/your-username/selenium-tests.git
cd selenium-tests
```

### Install Python dependencies:
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

## Running tests (Python)

### Running all tests:
#### Python (CD the `python_basics/` folder)
```bash
pytest
```

### Running a single folder or script containing multiple tests:
#### Python (CD the `python_basics/` folder)
```bash
pytest tests/
```

### Running a single test:
#### Python (CD the project root)
```bash
pytest tests/test_navigation.py
```

## File Structure:
```bash
.
.
├── python_basics/                        # Python sub-project
│   ├── .venv/                            # Virtual environment (Python dependencies)
│   ├── .pytest_cache/                    # Pytest's temporary cache for previous test runs
│   ├── __pycache__/                      # Compiled Python bytecode files
│   ├── results/                          # Stores Python test run output artifacts
│   │   ├── logs/                         # Timestamped test run logs
│   │   ├── screenshots/                  # Screenshots captured on test failures
│   │   └── latest_report.html            # Pytest HTML report (auto-generated each run)
│   ├── tests/                            # Python Selenium test scripts
│   ├── utils/                            # Utility scripts shared across tests
│   │   ├── logger.py                     # Custom logging handler for all test runs
│   │   ├── report_manager.py             # Configures and manages pytest HTML reporting
│   │   └── screenshot_helper.py          # Captures screenshots on test failures
│   ├── conftest.py                       # Pytest fixture setup (browser, teardown, global hooks)
│   ├── pytest.ini                        # Pytest configuration (logging, report setup)
│   └── requirements.txt                  # Project dependencies for pip installation
├── README.md                             # Project overview, setup, instructions, and documentation
```

## Test Cases
| Tests | Purpose |
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
    - Logs are written both in the console and to the `python_basics/logs/DATE_TIME.log` file


## Screenshot on Failure:
- When a Python test fails, a screenshot is automatically captured via a Pytest hook (conftest.py).
    - Screenshots are stored in the `python_basics/results/screenshots/` folder with timestamped file names. 


## Next Steps:
- Begin the JavaScript/TypeScript sub-project