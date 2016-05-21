@echo off

:处理pb
rd /s /q %cd%\share-core\src\java\com\share\core\protocol\protobuf
%cd%\protobuf\protoc\protoc.exe --java_out=%cd%\share-core\src\java\ -I=%cd%\protobuf\pb %cd%\protobuf\pb\*.proto

cd %cd%
for /f "tokens=*" %%a in ('dir /b') do (
   echo %%a | findstr "\-" && del /s /q %cd%\%%a\.classpath || cd %cd%
)
for /f "tokens=*" %%a in ('dir /b') do (
   echo %%a | findstr "\-" && del /s /q %cd%\%%a\.project || cd %cd%
)
for /f "tokens=*" %%a in ('dir /b') do (
   echo %%a | findstr "\-" && rd /s /q %cd%\%%a\.settings || cd %cd%
)
for /f "tokens=*" %%a in ('dir /b') do (
   echo %%a | findstr "\-" && rd /s /q %cd%\%%a\bin || cd %cd%
)

gradle clean eclipse && rd /s/q %cd%\.gradle\