@echo off

cd "%cd%/ebin"
erl +P 1024000 -setcookie s1 -noshell -s c_client start -extra 127.0.0.1 9901 aaaaaaaaaaaaa



