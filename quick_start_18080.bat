@echo off
setlocal EnableExtensions

set "ACCOUNT=%~2"
if "%ACCOUNT%"=="" set "ACCOUNT=test01"
set "CLIENT_PATH=%~1"

if "%CLIENT_PATH%"=="" (
  echo [INFO] 未指定客户端路径，默认使用当前目录下的 game\\FinalCombat.exe
  set "CLIENT_PATH=%~dp0game\\FinalCombat.exe"
)

echo [1/4] 启动服务...
call "%~dp0start_server_18080.bat"

echo [2/4] 等待服务预热...
timeout /t 6 /nobreak >nul

echo [3/4] 检查服务状态...
call "%~dp0check_server_18080.bat"

echo [4/4] 启动客户端(%ACCOUNT%)...
call "%~dp0start_client_18080.bat" "%ACCOUNT%" "%CLIENT_PATH%"
exit /b %errorlevel%

