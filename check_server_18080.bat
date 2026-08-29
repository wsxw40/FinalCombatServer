@echo off
setlocal EnableExtensions

set "WSL_DISTR=Ubuntu-24.04"
set "WSL_PROJECT=/mnt/c/Users/Administrator/Desktop/大冲锋源码/FinalCombatServer"

if not "%~1"=="" set "WSL_DISTR=%~1"

wsl -d "%WSL_DISTR%" -e bash -lc "cd '%WSL_PROJECT%' && bash check_status.sh"
if errorlevel 1 (
  echo [ERROR] 查询失败，请确认 WSL/路径无误
  pause
  exit /b 1
)

