rem 启动脚本
cls
Title [sns-dpocket]
::begin-----------------------------------
call "setclasspath.bat"
java com.pearl.o2o.MockPorxyServer
::end-----------------------------------
pause