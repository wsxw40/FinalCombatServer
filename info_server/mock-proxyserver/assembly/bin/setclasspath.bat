echo '使用方法 setclasspath'

@echo off
for %%i in ("..\lib\*.jar") do call "cpappend.bat" %%i
for %%i in ("..\app\*.jar") do call "cpappend.bat" %%i
set CLASSPATH=./;%CLASSPATH%