import os
from datetime import datetime
import time

class ReportManager:
    def __init__(self):
        # Base results/ folder
        self.results_dir = os.path.join(os.getcwd(), "results")
        os.makedirs(self.results_dir, exist_ok=True)

        # Current report path
        timestamp = datetime.now().strftime("%Y-%m-%d_%H-%M-%S")
        self.current_report_path = os.path.join(self.results_dir, f"report_{timestamp}.html")

    def get_latest_report_path(self):
        """Return path for the active HTML report."""
        return self.current_report_path
