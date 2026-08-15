@echo off
REM =====================================================
REM  FinalCombat 登录脚本 - 账号: test01
REM  用法: start_test01.bat [客户端exe完整路径]
REM  =====================================================

REM ---- 服务器 IP（改成你的服务器 IP） ----
set SERVER_IP=192.168.31.5
set SERVER_PORT=15000
set ACCOUNT=test01

REM ---- 客户端路径(优先取命令行参数) ----
set CLIENT_PATH=%~1
if "%CLIENT_PATH%"=="" set CLIENT_PATH=%~dp0FinalCombat.exe
if not exist "%CLIENT_PATH%" set CLIENT_PATH=%~dp0game\FinalCombat.exe
if not exist "%CLIENT_PATH%" (
    echo 找不到客户端: %CLIENT_PATH%
    echo 请用参数指定客户端路径: start_test01.bat 完整路径
    pause
    exit /b 1
)

REM ---- 从认证服务器获取 token ----
echo 获取登录凭证...
for /f "usebackq delims=" %%i in (`powershell -NoProfile -Command "(Invoke-RestMethod -Uri 'http://%SERVER_IP%:8080/confirm' -Method Post -Body '{}').token"`) do set LOGIN_TOKEN=%%i
if "%LOGIN_TOKEN%"=="" (
    echo 获取 token 失败，请确认服务器 %SERVER_IP%:8080 已启动
    pause
    exit /b 1
)

echo 启动 FinalCombat...
echo   账号:   %ACCOUNT%
echo   服务器: %SERVER_IP%:%SERVER_PORT%
start "" "%CLIENT_PATH%" -info %ACCOUNT% -login %LOGIN_TOKEN% -proxysvrip %SERVER_IP% -proxysvrport %SERVER_PORT%
echo.
pause
