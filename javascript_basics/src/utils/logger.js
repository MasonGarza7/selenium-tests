const winston = require("winston");
const fs = require("fs-extra");
const path = require("path");

const logsDir = path.join(process.cwd(), "results", "logs");
fs.ensureDirSync(logsDir);

const timestamp = new Date().toISOString().replace(/[:.]/g, "-");
const logFile = path.join(logsDir, `test_run_${timestamp}.log`);

const logger = winston.createLogger({
  level: "info",
  format: winston.format.combine(
    winston.format.timestamp({ format: "YYYY-MM-DD HH:mm:ss" }),
    winston.format.printf(({ timestamp, level, message }) => {
      return `[${timestamp}] ${level.toUpperCase()}: ${message}`;
    })
  ),
  transports: [
    new winston.transports.File({ filename: logFile }),
    new winston.transports.Console()
  ],
});

module.exports = { logger };
