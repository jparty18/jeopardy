

javac -d classes src/com/jeopardy/*.java src/com/jeopardy/client/*.java 

jar --create --file jeopardy.jar -C classes . sample

