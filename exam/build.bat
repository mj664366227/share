@echo off



rd /s/q %cd%\lib\
md %cd%\lib\



cd %cd%
for /f "tokens=*" %%a in ('dir /b') do (
   echo %%a | findstr "\-" && del /s /q %cd%\%%a\.classpath && del /s /q %cd%\%%a\.project && rd /s /q %cd%\%%a\.settings && rd /s /q %cd%\%%a\bin || cd %cd%
)

gradle clean eclipse 
:gradle clean deploy && xcopy /y %cd%\exam\compile\* %cd%\lib\ /e && rd /s/q %cd%\.gradle\ && rd /s/q %cd%\exam\build\ && rd /s/q %cd%\exam\compile\ && rd /s/q %cd%\exam\.settings\ && rd /s/q %cd%\exam\bin\ && del /s/q %cd%\exam\.classpath && del /s/q %cd%\exam\.project