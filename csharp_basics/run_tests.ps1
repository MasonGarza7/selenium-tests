# run_tests.ps1
param(
    [string]$Configuration = "Debug",
    [string]$Framework = "net8.0"
)

# Paths
$resultsPath = "bin\$Configuration\$Framework\results\allure-results"

# 0) Clean old raw results before running tests
if (Test-Path $resultsPath) {
    Remove-Item $resultsPath -Recurse -Force
}

# 1) Run tests (NUnit + Allure .NET will recreate raw results)
dotnet test --configuration $Configuration --framework $Framework

# 2) View results with Allure serve
if (Test-Path $resultsPath) {
    allure serve $resultsPath
} else {
    Write-Host "Allure results directory not found: $resultsPath"
}
