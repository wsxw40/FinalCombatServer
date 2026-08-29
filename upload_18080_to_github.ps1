param(
  [string]$Branch = "",
  [string]$Message = "chore: add 18080 workflow scripts and notes",
  [string]$Remote = "origin",
  [switch]$UploadAll = $false
)

Set-Location -Path (Split-Path -Parent $MyInvocation.MyCommand.Path)
$script:GitExitCode = 0
$script:GitLastOutput = ""

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

  $gitCommand = @()
  $gitCommand += "-c"
  $gitCommand += "credential.helper="
  if ($execPaths.Count -gt 0) {
    $gitExecPath = $execPaths -join ";"
    $gitCommand += "--exec-path=$gitExecPath"
  }
  $gitCommand += $GitArgs

  $script:GitLastOutput = & git @gitCommand 2>&1 | Out-String
  $script:GitExitCode = $LASTEXITCODE
  if ($script:GitExitCode -ne 0) {
    Write-Warning "Git command failed: $($script:GitLastOutput.Trim())"
  }
  return $script:GitLastOutput
}

function Push-Git {
  param([string]$Remote, [string]$Branch)

  $null = Invoke-Git push -u $Remote $Branch
  if ($script:GitExitCode -ne 0 -and $script:GitLastOutput -match "AcquireCredentialsHandle|SEC_E_NO_CREDENTIALS|remote-https|remote helper 'https'|SSL") {
    Write-Host "[3/3] Retry with OpenSSL + no SSL verify..."
    $null = Invoke-Git -c "http.sslVerify=false" -c "http.sslBackend=openssl" push -u $Remote $Branch
  }
  return $script:GitExitCode -eq 0
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

Invoke-Git remote get-url $Remote | Out-Null
if ($script:GitExitCode -ne 0) {
  Write-Error "Remote '$Remote' not found. Set it first: git remote add $Remote https://github.com/<user>/<repo>.git"
  exit 1
}

Write-Host "[1/3] Staging files..."
if ($UploadAll) {
  Invoke-Git add -A
  Write-Host "  - Enabled UploadAll: staging all tracked/untracked changes."
} else {
  Invoke-Git add -- @files
  Write-Host "  - Default mode: staging fixed script/docs file list only."
}
if ($script:GitExitCode -ne 0) {
  Write-Warning "Some paths could not be staged, check file list."
}

$staged = (Invoke-Git diff --cached --name-only | Out-String)
if (-not [string]::IsNullOrWhiteSpace($staged)) {
  Write-Host "[2/3] Committing..."
  Invoke-Git commit -m $Message
  if ($script:GitExitCode -ne 0) {
    Write-Output "No new changes to commit."
  }
} else {
  Write-Host "[WARN] No staged files changed, skip commit."
}

Write-Host "[3/3] Push to $Remote / $Branch ..."
if (-not (Push-Git $Remote $Branch)) {
  Write-Error "Push failed. Possible causes: Git HTTPS helper/SSL issue or missing credentials. Login to GitHub and try again."
  exit 1
}

Write-Host "[DONE] Upload flow finished."
