@echo off
echo Compiling and running Risk Horizon Engine...
javac Main.java Vehicle.java
java Main
echo Simulation complete! Opening viewer...
cd web
start index.html
