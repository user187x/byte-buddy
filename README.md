Build the App & Agent
```bash
mvn clean install -U
```

Run the Agent w/the App

```bash
java -javaagent:agent.jar -jar app.jar
```
