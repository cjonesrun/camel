Messing around with camel

first:
mvn compile

to start the camel folder watcher:

mvn exec:java -PCamelGo

this will take a file from /tmp/camel, log its contents to console and move the file to .processed or to error/ if something goes wrong

to start the JMS server:

mvn exec:java -PJMSServer

to send messages to the JMS server:

mvn exec:java -PJMSClient
