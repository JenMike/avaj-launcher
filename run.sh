find . -name "*.java" > sources.txt
javac -sourcepath . @sources.txt
java -cp ./src main.java.simulator.Simulator scenario.txt