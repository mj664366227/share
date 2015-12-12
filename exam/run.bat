@echo off

setlocal=EnableDelayedExpansion

rd /s/q %cd%\log\
md %cd%\log\

cd %cd%\lib\
set classpath=.;%cd%\..\exam\src\resources\;%cd%\..\java\lib\dt.jar;%cd%\..\java\lib\tools.jar
for /f "tokens=*" %%a in ('dir /b') do (
   echo %%a | findstr ".jar" && set classpath=!classpath!;%cd%\%%a|| cd %cd%\..\
)

cd %cd%
java -Xverify:none -server -Xms512m -Xmx3g -Dfile.encoding=utf8 -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCApplicationConcurrentTime -XX:+PrintHeapAtGC -XX:ParallelGCThreads=4 -XX:CompileThreshold=1 -XX:+UseParallelGC -XX:+AggressiveOpts -XX:+UseBiasedLocking -XX:+UseFastAccessorMethods -XX:+DoEscapeAnalysis -Xloggc:%cd%\log\gc.log -Dproject=exam -classpath %classpath% com.exam.start.StartExam