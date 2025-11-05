@echo off
cd /d "%~dp0"
cd risk-horizon-engine-Mainrelease
echo Compiling and running Risk Horizon Engine...
javac Main.java Vehicle.java
java Main
cd web
echo Starting local web server...
start http://localhost:8000/index.html
python -m http.server 8000
pause
