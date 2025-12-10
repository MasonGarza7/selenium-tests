require "logger"
require "fileutils"

module TestLogger
  ROOT_DIR = File.expand_path("../..", __dir__)            # => ruby_basics/
  LOGS_DIR = File.join(ROOT_DIR, "results", "logs")        # => ruby_basics/results/logs

  def self.create_logger
    FileUtils.mkdir_p(LOGS_DIR)

    timestamp = Time.now.strftime("%Y%m%d_%H%M%S")
    log_path  = File.join(LOGS_DIR, "test_run_#{timestamp}.log")

    logger = Logger.new(log_path)
    logger.level = Logger::INFO
    logger.datetime_format = "%Y-%m-%d %H:%M:%S"
    logger
  end
end
