@echo off
:rd /s /q %cd%\node_modules
rd /s /q %cd%\dist
:npm install && 
npm run build