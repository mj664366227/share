@echo off
del /s /q %cd%\*.class
javac -encoding UTF-8 CodeCompreser.java
java CodeCompreser -source ../../shareTools\skin\default\view/