@echo off

cd "%cd%/ebin"
erl +P 1024000 -setcookie s1 -noshell -s s_server start -extra 9901



