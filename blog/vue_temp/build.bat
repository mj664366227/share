@echo off
rd /s /q %cd%\node_modules
del /s /q %cd%\dist\*
npm install && npm run build