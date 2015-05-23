@echo off
set thrift=%cd%\thrift-0.9.2.exe

cd %cd%\thrift
for /f "tokens=*" %%a in ('dir /b') do (
	%thrift% -r --gen java -out sing-soa\src\main\java sing-soa\src\main\thrift\kvservices.thrift
)

pause