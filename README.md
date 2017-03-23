Messing around with camel

first:
mvn compile

to start the camel folder watcher:

mvn exec:java -PCamelGo

this will take a file from /tmp/camel, log its contents to console and move the file to ./processed/ or to ./working/errors/ if something goes wrong. test it with a valid json (original/file.json) and an empty json (original/empty.json) by moving desired file to ./working

to start the JMS server:

mvn exec:java -PJMSServer

to send messages to the JMS server:

mvn exec:java -PJMSClient
