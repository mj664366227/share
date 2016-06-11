@echo off
rd /s /q %cd%\node_modules
npm install
npm run build
npm run dev