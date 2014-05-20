##Description 

This is a prototype web crawler that uses BFS & DFS strategies to explore the web.

##Build
Go to the source directory and do:
```
mvn clean compile
```

##Usage
After compiling, you should go into the generated target directory and do:
```
java -jar Crawler-<version>.jar <startURL> <BFS|DFS> <maxVisit> 
```
e.g.: 
```
java -jar Crawler-1.0-SNAPSHOT.jar http://eluniversal.com BFS 100
```
