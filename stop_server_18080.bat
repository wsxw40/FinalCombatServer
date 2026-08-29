@echo off
setlocal EnableExtensions

set "WSL_DISTR=Ubuntu-24.04"
set "WSL_PROJECT=/mnt/c/Users/Administrator/Desktop/大冲锋源码/FinalCombatServer"

if not "%~1"=="" set "WSL_DISTR=%~1"

echo [1/2] 停止 FinalCombat 服务器...
wsl -d "%WSL_DISTR%" -e bash -lc "cd '%WSL_PROJECT%' && bash stop_all.sh"
if errorlevel 1 (
  echo [ERROR] 停止失败，请确认 WSL/路径无误
  pause
  exit /b 1
)

echo [2/2] 完成
exit /b 0

