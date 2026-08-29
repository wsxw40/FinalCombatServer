@echo off
setlocal EnableExtensions

rem FinalCombat 一键启动（WSL，默认认证端口 18080）
set "SERVER_IP=192.168.31.5"
set "AUTH_PORT=18080"
set "INFO_PORT=18081"
set "DUMMY_PORTS=28085,28086,28222"
set "SERVER_PORT=15000"
set "WSL_DISTR=Ubuntu-24.04"
set "WSL_PROJECT=/mnt/c/Users/Administrator/Desktop/大冲锋源码/FinalCombatServer"

if not "%~1"=="" set "WSL_DISTR=%~1"
if not "%~2"=="" set "SERVER_IP=%~2"
if not "%~3"=="" set "SERVER_PORT=%~3"

echo [1/3] 正在启动 FinalCombat 服务器...
echo    分发器: %WSL_DISTR%
echo    SERVER_IP: %SERVER_IP%
echo    AUTH端口: %AUTH_PORT%
echo    GAME端口: %SERVER_PORT%

wsl -d "%WSL_DISTR%" -e bash -lc "cd '%WSL_PROJECT%' && AUTH_PORT=%AUTH_PORT% INFO_PORT=%INFO_PORT DUMMY_PORTS=%DUMMY_PORTS% SERVER_PORT=%SERVER_PORT% SERVER_IP=%SERVER_IP% bash start_all.sh"
if errorlevel 1 (
    echo [ERROR] WSL 启动失败，请确认已安装 Ubuntu-24.04 或指定正确的发行版名称。
    pause
    exit /b 1
)

echo [2/3] WSL 启动命令已下发，稍等服务初始化...
timeout /t 5 /nobreak >nul

echo [3/3] 可用命令：在本机浏览器访问 http://localhost:%AUTH_PORT%/confirm 测试登录接口
echo 或运行 start_test01_18080.bat 进行客户端测试。
exit /b 0

