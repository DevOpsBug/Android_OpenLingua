param (
    [string]$csvFile
)

# Ensure the input file exists
if (-Not (Test-Path $csvFile)) {
    Write-Host "Error: File '$csvFile' not found!" -ForegroundColor Red
    exit 1
}

# Determine output JSON file name (replace .csv with .json)
$jsonFile = [System.IO.Path]::ChangeExtension($csvFile, ".json")

# Read the CSV file
$csvData = Import-Csv -Path $csvFile -Delimiter ";" 

# Convert CSV data to JSON with formatting options
$jsonData = $csvData | ConvertTo-Json -Depth 10 -Compress | Out-String

# Ensure proper encoding (UTF-8 without BOM to prevent formatting issues)
$jsonData | Set-Content -Path $jsonFile -Encoding utf8

Write-Host "Conversion successful! JSON saved to: $jsonFile" -ForegroundColor Green
