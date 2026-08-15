@echo off

set "PROTO_HOME=D:\fc\Protobuf\proto"
set "protoc=D:\fc\Protobuf\protoc.exe"

for /R "%PROTO_HOME%" %%s in (*.proto) do ( 
	%protoc% -I=%PROTO_HOME% --java_out=./src %%s
) 
pause