@echo off
setlocal

set "BRANCH=%~1"
set "MSG=%~2"
if "%MSG%"=="" set "MSG=chore: add 18080 workflow scripts and notes"

if "%BRANCH%"=="" (
  powershell -NoProfile -ExecutionPolicy Bypass -File "%~dp0upload_18080_to_github.ps1" -Message "%MSG%"
) else (
  powershell -NoProfile -ExecutionPolicy Bypass -File "%~dp0upload_18080_to_github.ps1" -Branch "%BRANCH%" -Message "%MSG%"
)
