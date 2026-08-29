param(
  [string]$Branch = "",
  [string]$Message = "chore: add 18080 workflow scripts and notes",
  [string]$Remote = "origin"
)

Set-Location -Path (Split-Path -Parent $MyInvocation.MyCommand.Path)

if (-not (Get-Command git -ErrorAction SilentlyContinue)) {
  Write-Error "Git not found in PATH."
  exit 1
}

if (-not (Test-Path ".git")) {
  Write-Error "This directory is not a git repository."
  exit 1
}

if (-not $Branch) {
  $Branch = (& git branch --show-current).Trim()
  if (-not $Branch) { $Branch = "main" }
}

$files = @(
  "README.md",
  "start_all.sh",
  "stop_all.sh",
  "check_status.sh",
  "stop_server_18080.bat",
  "start_server_18080.bat",
  "check_server_18080.bat",
  "start_client_18080.bat",
  "start_test01_18080.bat",
  "start_test02_18080.bat",
  "start_test03_18080.bat",
  "start_test04_18080.bat",
  "start_test05_18080.bat",
  "quick_start_18080.bat",
  "fc_clean.sh",
  "migrate_test_accounts.sh",
  "logserver_dummy.py"
)

& git remote get-url $Remote *> $null
if ($LASTEXITCODE -ne 0) {
  Write-Error "Remote '$Remote' not found. Set it first: git remote add $Remote https://github.com/<user>/<repo>.git"
  exit 1
}

# Required author binding
& git config user.email "2929363313@qq.com" | Out-Null

Write-Host "[1/3] Staging files..."
& git add -- @files
if ($LASTEXITCODE -ne 0) {
  Write-Warning "Some files were not staged or path contains issues."
}

$staged = (& git diff --cached --name-only | Out-String)
if (-not [string]::IsNullOrWhiteSpace($staged)) {
  Write-Host "[2/3] Committing..."
  & git commit -m $Message
  if ($LASTEXITCODE -ne 0) {
    Write-Warning "No new changes to commit."
  }
} else {
  Write-Host "[WARN] No staged files changed, skip commit."
}

Write-Host "[3/3] Push to $Remote / $Branch ..."
& git push -u $Remote $Branch
if ($LASTEXITCODE -ne 0) {
  Write-Error "Push failed. Check token/network/permission."
  exit 1
}

Write-Host "[DONE] Upload flow finished."
