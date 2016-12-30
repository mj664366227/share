@echo off

:npm部署
rd /s /q %cd%\app
rd /s /q %cd%\runtime
rd /s /q %cd%\node_modules
del /s /q %cd%\*.log
npm install && npm run compile