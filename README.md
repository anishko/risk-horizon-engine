# Risk Horizon Engine

A simple java-based simulation engine that projects ETF, bond, REIT, and stock growth over 15 years with risk-adjusted bands.  
It also includes an interactive Chart.js web viewer to visualize the results.

---

## Overview

This project simulates long-term investment growth using an annual growth rate and a risk range.  
The program takes your asset data from a CSV file, runs a 15-year projection, and outputs a results file that can be viewed through an interactive chart.

Process:
```assets.csv → run Main.java → generates results.csv → open web/index.html to view the chart```

---
(If you use Python, ignore these steps. Run .bat file. This improves convenience)
## How to Run

### Step 1: Make sure Java is installed
You need Java 8 or newer.  
To check, open terminal or command prompt and type: ```java -version```

If it says “command not found”, install Java from Oracle’s site (https://www.oracle.com/java/technologies/downloads/) or use your system package manager.

---

### Step 2: Compile and run the Java code
In the folder where Main.java and Vehicle.java are located, type:``` javac Main.java Vehicle.java
java Main```

This will:
- read your assets.csv
- calculate growth for each asset over 15 years
- create a new file called results.csv

---

### Step 3: Open the web viewer
After the results.csv file is created:
1. Go to the web folder
2. Open index.html in your browser

This will load the chart dashboard.  
You can:
- compare multiple assets at once  
- type your own starting investment amount  
- see low, expected, and high projections  
- add your own assets in the CSV to test custom growth rates

If you see a red warning that says results.csv not found, you need to run the Java simulation first.

---

## Folder Structure:
```
RiskHorizonEngine/
├── Main.java
├── Vehicle.java
├── assets.csv
├── web/
│ ├── index.html
│ ├── app.js
│ ├── style.css
│ └── results.csv
└── README.md
```


Main.java runs the simulation  
Vehicle.java defines the asset object  
assets.csv is the input  
results.csv is generated output  
index.html is the web viewer

---

## Editing or Adding Assets

Open assets.csv and add or edit rows in this format:
```
Name,Type,GrowthRate,RiskBand
SPY,ETF,0.08,0.03
BND,Bond,0.03,0.01
VNQ,REIT,0.06,0.04
BTC,Other,0.25,0.20
```

Name → a short symbol like SPY or BTC  
Type → category (ETF, Bond, REIT, Stock, Other)  
GrowthRate → expected yearly return in decimal (0.07 means 7 percent)  
RiskBand → yearly variation above or below growth (0.03 means ±3 percent)

After editing, re-run Main.java to regenerate results.csv

---

## Finding Growth Rate and Risk Band

You can use websites like:
- Yahoo Finance (under Historical Data or Statistics)
- Morningstar
- MarketWatch
- Investopedia

To estimate:
- Stable bonds → growth 0.02 to 0.04, risk 0.01 to 0.02  
- Large ETFs → growth 0.07 to 0.10, risk 0.02 to 0.03  
- REITs → growth 0.05 to 0.07, risk 0.03 to 0.05  
- Individual stocks → growth 0.08 to 0.15, risk 0.03 to 0.10  
- Crypto → growth 0.15 to 0.30, risk 0.10 to 0.25  

Start simple with small ranges for safer assets and larger ones for riskier ones.

---

## Example Output

After running Main.java, the program creates results.csv that looks like:
```
Name,Year,Low,Expected,High
SPY,1,103.0,108.0,113.0
SPY,2,106.5,117.0,128.5
BTC,1,120.0,125.0,130.0
```

Then index.html reads that file and plots the 15-year growth chart.

---

## Common Issues

results.csv not found  
→ Run the Java simulation first.

chart not loading  
→ Check that results.csv is saved in the web folder.

numbers look strange  
→ Make sure assets.csv uses decimals (for example 0.08 not 8).

---

## What This Project Uses

Java for the simulation logic and CSV file writing  
HTML, CSS, and JavaScript for the web viewer  
Chart.js for chart visualization  

---

## Author

Created by Anish Konduri  
Made as a small simulation and visualization project combining Java and basic web tools.
