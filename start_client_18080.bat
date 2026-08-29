@echo off
setlocal EnableExtensions

set "SERVER_IP=192.168.31.5"
set "AUTH_PORT=18080"
set "SERVER_PORT=15000"
set "ACCOUNT=%~1"
set "CLIENT_PATH=%~2"

if "%ACCOUNT%"=="" set "ACCOUNT=test01"
if "%CLIENT_PATH%"=="" set "CLIENT_PATH=%~dp0FinalCombat.exe"
if not exist "%CLIENT_PATH%" set "CLIENT_PATH=%~dp0game\FinalCombat.exe"

if not exist "%CLIENT_PATH%" (
    echo [ERROR] 找不到客户端：%CLIENT_PATH%
    echo 可以这样启动：%~nx0 test01 "D:\xxx\FinalCombat.exe"
    pause
    exit /b 1
)

for /f "delims=" %%i in ('powershell -NoProfile -Command "(Invoke-RestMethod -Uri \"http://%SERVER_IP%:%AUTH_PORT%/confirm\" -Method Post -Body '{}' -ContentType \"application/json\").token"') do set "LOGIN_TOKEN=%%i"
if "%LOGIN_TOKEN%"=="" (
    echo [ERROR] 获取 token 失败，请确认：
    echo 1) 服务端已启动
    echo 2) 认证端口为 %AUTH_PORT%（本地映射到 WSL）
    echo 3) IP 与端口能在本机访问
    pause
    exit /b 1
)

start "" "%CLIENT_PATH%" -info %ACCOUNT% -login %LOGIN_TOKEN% -proxysvrip %SERVER_IP% -proxysvrport %SERVER_PORT%
exit /b 0

