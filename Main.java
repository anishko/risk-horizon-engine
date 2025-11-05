/**
 * Risk Horizon Engine
 * --------------------
 * A simple Java simulation that projects investment growth
 * for different vehicles (ETFs, Bonds, REITs, etc.) with risk-adjusted bands.
 * 
 * You can easily add or remove vehicles by editing assets.csv.
 * The results are saved to results.csv and can be visualized with Chart.js.
 * 
 * @author Anish Konduri
 * @version 1.2
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Main driver class for the Risk Horizon simulation.
 */
public class Main {

    public static void main(String[] args) {

        ArrayList<Vehicle> vehicles = new ArrayList<>();

        // Read asset data from CSV file using Scanner
        try {
            File file = new File("assets.csv");
            Scanner input = new Scanner(file);

            // Skip the first line (header)
            if (input.hasNextLine()) {
                input.nextLine();
            }

            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String name = parts[0].trim();
                    String type = parts[1].trim();
                    double growth = Double.parseDouble(parts[2].trim());
                    double risk = Double.parseDouble(parts[3].trim());
                    vehicles.add(new Vehicle(name, type, growth, risk));
                }
            }
            input.close();
        } catch (IOException e) {
            System.out.println("Error: Could not read assets.csv - " + e.getMessage());
        }

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found! Make sure assets.csv is filled in.");
            return;
        }

        int startYear = 2025;
        int years = 15;
        double startValue = 100.0;

        try {
            FileWriter writer = new FileWriter("results.csv");
            writer.write("Asset,Year,Low,Expected,High\n");

            for (Vehicle v : vehicles) {
                double low = startValue;
                double mid = startValue;
                double high = startValue;

                for (int i = 0; i < years; i++) {
                    int year = startYear + i;
                    low *= (1 + v.getGrowthRate() - v.getRiskBand());
                    mid *= (1 + v.getGrowthRate());
                    high *= (1 + v.getGrowthRate() + v.getRiskBand());

                    writer.write(v.getName() + "," + year + "," +
                            String.format(Locale.US, "%.2f", low) + "," +
                            String.format(Locale.US, "%.2f", mid) + "," +
                            String.format(Locale.US, "%.2f", high) + "\n");
                }
            }

            writer.close();
            System.out.println("Simulation complete! Created results.csv");
            System.out.println("Open web/index.html to view the chart.");

        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }
}