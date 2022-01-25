## Start Hub
```
java -jar selenium-server-standalone-3.141.59.jar -role hub
```

## Start HUB | Custom caps Matcher | MacOS
```
java -cp selenium-server-standalone-3.141.59.jar:selenium-grid-custom-matcher-3.141.59.jar org.openqa.grid.selenium.GridLauncherV3 -role hub -hubConfig hubConfig.json
```

## Start Node 01
```
appium -p 6000 --nodeconfig node_6000_config.json
```

## Start Node 02
```
appium -p 7000 --nodeconfig node_7000_config.json
```

## Run by mvn
```
mvn clean test -DsuiteXmlFile=src/main/resources/test-suites/TestBaseTest.xml -Dhub=http://192.168.1.46
```