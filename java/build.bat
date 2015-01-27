@echo off
rd /s/q %cd%\.gradle\

set PROJECT=share-core
rd /s/q %cd%\%PROJECT%\.settings
del /s/q %cd%\%PROJECT%\.classpath
del /s/q %cd%\%PROJECT%\.project

set PROJECT=share-client
rd /s/q %cd%\%PROJECT%\.settings
del /s/q %cd%\%PROJECT%\.classpath
del /s/q %cd%\%PROJECT%\.project

set PROJECT=share-test
rd /s/q %cd%\%PROJECT%\.settings
del /s/q %cd%\%PROJECT%\.classpath
del /s/q %cd%\%PROJECT%\.project

set PROJECT=share-admin
rd /s/q %cd%\%PROJECT%\.settings
del /s/q %cd%\%PROJECT%\.classpath
del /s/q %cd%\%PROJECT%\.project

gradle eclipse