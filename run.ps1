#Set-ExecutionPolicy RemoteSigned

javac ChatClient.java ClientImpl.java ChatApplication\*.java
javac ChatServer.java ServerImpl.java ChatApplication\*.java
javac ChatHistory.java HistoryImpl.java ChatApplication\*.java

#Write-Host "Compile end"

start orbd "-ORBInitialPort 1050 -ORBInitialHost 192.168.178.81"
Write-Host "orb started"
start-process java -ArgumentList 'ChatServer', ' -ORBInitialPort 1050'#, ' -ORBInitialHost localhost'
Write-Host "Server started"
start-process java -ArgumentList 'ChatHistory', '-ORBInitialPort 1050'#, '-ORBInitialHost localhost'
Write-Host "HistoryServer started"
start-process java -ArgumentList 'ChatClient', '-ORBInitialPort 1050', '-ORBInitialHost localhost'