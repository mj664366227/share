@echo off
rd /s/q %cd%\.gradle\

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

gradle clean eclipse