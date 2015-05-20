@echo off
rd /s/q %cd%\.gradle\

cd %cd%
for /f "tokens=*" %%a in ('dir /b') do (
   echo %%a | findstr "\-" && del /s /q %cd%\%%a\.classpath && del /s /q %cd%\%%a\.project && rd /s /q %cd%\%%a\.settings && rd /s /q %cd%\%%a\bin || echo ...
)

gradle clean eclipse