using System;
using System.IO;

namespace csharp_basics.utils
{
    public static class Logger
    {
        private static bool _initialized = false;
        private static readonly object _lock = new object();
        private static string _logFilePath;

        public static void Initialize()
        {
            if (_initialized) return;

            lock (_lock)
            {
                if (_initialized) return;

                // Resolve project root (3 levels up from bin/.../)
                string projectRoot = AppContext.BaseDirectory;
                projectRoot = Directory.GetParent(projectRoot).Parent.Parent.Parent.FullName;

                string resultsDir = Path.Combine(projectRoot, "results");
                string logsDir = Path.Combine(resultsDir, "logs");
                Directory.CreateDirectory(logsDir);

                string timestamp = DateTime.Now.ToString("yyyy-MM-dd_HH-mm-ss");
                _logFilePath = Path.Combine(logsDir, $"log_{timestamp}.txt");

                _initialized = true;
            }
        }

        public static void Write(string message)
        {
            Initialize(); // ensure initialized exactly once per run

            string line = $"[{DateTime.Now:yyyy-MM-dd HH:mm:ss}] {message}";
            File.AppendAllText(_logFilePath, line + Environment.NewLine);
        }
    }
}
