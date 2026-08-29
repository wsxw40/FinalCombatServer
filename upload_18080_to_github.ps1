param(
  [string]$Branch = "",
  [string]$Message = "chore: add 18080 workflow scripts and notes",
  [string]$Remote = "origin"
)

Set-Location -Path (Split-Path -Parent $MyInvocation.MyCommand.Path)

function Invoke-Git {
  param([Parameter(ValueFromRemainingArguments)] [string[]]$GitArgs)

  if (-not (Get-Command git -ErrorAction SilentlyContinue)) {
    Write-Error "Git not found in PATH."
    exit 1
  }

  if (-not (Test-Path ".git")) {
    Write-Error "This directory is not a git repository."
    exit 1
  }

  $gitExe = (Get-Command git).Source
  $gitRoot = Split-Path (Split-Path $gitExe -Parent) -Parent
  $binPath = Join-Path $gitRoot "mingw64\bin"
  $corePath = Join-Path $gitRoot "mingw64\libexec\git-core"
  $execPaths = @()
  if (Test-Path $binPath) { $execPaths += $binPath }
  if (Test-Path $corePath) { $execPaths += $corePath }

  if ($execPaths.Count -gt 0) {
    $gitExecPath = $execPaths -join ";"
    & git "--exec-path=$gitExecPath" @GitArgs
  }
  else {
    & git @GitArgs
  }
}

if (-not $Branch) {
  $Branch = (Invoke-Git branch --show-current).Trim()
  if (-not $Branch) { $Branch = "master" }
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

Invoke-Git remote get-url $Remote *> $null
if ($LASTEXITCODE -ne 0) {
  Write-Error "Remote '$Remote' not found. Set it first: git remote add $Remote https://github.com/<user>/<repo>.git"
  exit 1
}

Invoke-Git config user.email "2929363313@qq.com" | Out-Null

Write-Host "[1/3] Staging files..."
Invoke-Git add -- @files
if ($LASTEXITCODE -ne 0) {
  Write-Warning "Some files were not staged or path contains issues."
}

$staged = (Invoke-Git diff --cached --name-only | Out-String)
if (-not [string]::IsNullOrWhiteSpace($staged)) {
  Write-Host "[2/3] Committing..."
  Invoke-Git commit -m $Message
  if ($LASTEXITCODE -ne 0) {
    Write-Warning "No new changes to commit."
  }
}
else {
  Write-Host "[WARN] No staged files changed, skip commit."
}

Write-Host "[3/3] Push to $Remote / $Branch ..."
Invoke-Git push -u $Remote $Branch
if ($LASTEXITCODE -ne 0) {
  Write-Error "Push failed. Common causes: git SSL/network issue (try again), or credential problem. Re-login to GitHub and retry."
  exit 1
}

Write-Host "[DONE] Upload flow finished."
