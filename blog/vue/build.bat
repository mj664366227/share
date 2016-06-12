@echo off
:rd /s /q %cd%\node_modules
del /s /q %cd%\dist\main.js
:npm install && 
npm run build