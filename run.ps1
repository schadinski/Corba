javac Client\*.java ChatApplication\*.java
javac Server\ChatServer.java Server\ServerImpl.java ChatApplication\*.java
javac History\ChatHistory.java History\HistoryImpl.java ChatApplication\*.java

Write-Host "Compile end"

start orbd "-ORBInitialPort 1050 -ORBInitialHost localhost"
Write-Host "orb started"
#start java .\Server\ChatServer "-ORBInitialPort 1050 -ORBInitialHost localhost"
#Write-Host "Server started"
#start java History\ChatHistory "-ORBInitialPort 1050 -ORBInitialHost localhost"
#Write-Host "HistoryServer started"
#java  Client\ChatClient "-ORBInitialPort 1050 -ORBInitialHost localhost"