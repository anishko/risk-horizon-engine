/**
 * Vehicle
 * --------------------
 * Represents a single investment vehicle such as an ETF, Bond, or REIT.
 * Each has a name, type, annual growth rate, and a simple risk band.
 * 
 * @author Anish Konduri
 * @version 1.2
 */
public class Vehicle {

    private String name;
    private String type;
    private double growthRate;
    private double riskBand;

    /**
     * Creates a new Vehicle.
     * @param name the asset name
     * @param type the asset type
     * @param growthRate yearly growth rate
     * @param riskBand risk variation band
     */
    public Vehicle(String name, String type, double growthRate, double riskBand) {
        this.name = name;
        this.type = type;
        this.growthRate = growthRate;
        this.riskBand = riskBand;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public double getGrowthRate() { return growthRate; }
    public double getRiskBand() { return riskBand; }
}