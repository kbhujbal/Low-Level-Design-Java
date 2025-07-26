#!/bin/bash

echo "=== Parking Lot System Build Script ==="

# Create target directory
mkdir -p target/classes

# Compile Java files
echo "Compiling Java files..."
javac -d target/classes -cp "src/main/java" src/main/java/com/parkinglot/enums/*.java
javac -d target/classes -cp "target/classes:src/main/java" src/main/java/com/parkinglot/model/*.java
javac -d target/classes -cp "target/classes:src/main/java" src/main/java/com/parkinglot/strategy/*.java
javac -d target/classes -cp "target/classes:src/main/java" src/main/java/com/parkinglot/strategy/impl/*.java
javac -d target/classes -cp "target/classes:src/main/java" src/main/java/com/parkinglot/exception/*.java
javac -d target/classes -cp "target/classes:src/main/java" src/main/java/com/parkinglot/service/*.java
javac -d target/classes -cp "target/classes:src/main/java" src/main/java/com/parkinglot/ParkingLot.java
javac -d target/classes -cp "target/classes:src/main/java" src/main/java/com/parkinglot/demo/*.java

echo "Compilation completed!"

# Run the demo
echo "Running Parking Lot Demo..."
java -cp target/classes com.parkinglot.demo.ParkingLotDemo

echo "Build script completed!" 