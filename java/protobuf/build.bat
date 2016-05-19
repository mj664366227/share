rd /s /q %cd%\..\share-core\src\com
%cd%\protoc\protoc.exe --java_out=%cd%\..\share-core\src -I=%cd%\pb %cd%\pb\*.proto