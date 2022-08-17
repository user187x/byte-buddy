#Build the App & Agent
mvn clean install -U

#Run the Agent w/the App
java -javaagent:agent.jar -jar app.jar