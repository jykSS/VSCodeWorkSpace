@echo off
chcp 65001
setlocal enabledelayedexpansion
set /p port=请输入端口号：
for /f "tokens=1-5" %%a in ('netstat -ano ^| find ":%port%"') do (
    if "%%e%" == "" (
        set pid=%%d
    ) else (
        set pid=%%e
    )
    echo 占用!port!端口的进程为：!pid!
    echo ------------
    taskkill /f /pid !pid!
)
pause