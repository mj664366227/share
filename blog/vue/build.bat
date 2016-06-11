@echo off
rd /s /q %cd%\node_modules
del /s /q %cd%\dist\main.js
del /s /q %cd%\dist\main.js.map
npm install && npm run build