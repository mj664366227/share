rd /s /q %cd%\..\share-core\src\java\com\share\core\protocol\protobuf
%cd%\protoc\protoc.exe --java_out=%cd%\..\share-core\src\java\ -I=%cd%\pb %cd%\pb\*.proto