# Risk Horizon Engine

A simple java-based simulation engine that projects ETF, bond, REIT, and stock growth over 15 years with risk-adjusted bands.  
It also includes an interactive Chart.js web viewer to visualize the results.

---

## Overview

This project simulates long-term investment growth using an annual growth rate and a risk range.  
The program takes your asset data from a CSV file, runs a 15-year projection, and outputs a results file that can be viewed through an interactive chart.

Process:
assets.csv → run Main.java → generates results.csv → open web/index.html to view the chart

---

## How to Run

### Step 1: Make sure Java is installed
You need Java 8 or newer.  
To check, open terminal or command prompt and type: ```java -version```
