@echo off

set DIR=%cd%
cd %DIR%
:先备份core工程的package.json
xcopy %DIR%\share-core\package.json %DIR%\ /y

:删除所有老文件
for /f "tokens=*" %%a in ('dir /b') do (
   echo %%a | findstr "share\-" && del /f /q %DIR%\%%a\package.json && xcopy %DIR%\package.json %DIR%\%%a\ /y || cd %DIR%
)

:npm部署
rd /s /q %DIR%\node_modules
npm install && del /f /q %DIR%\package.json