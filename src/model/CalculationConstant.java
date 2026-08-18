package model;

public class CalculationConstant {
    private int id;
    private String marketplace;
    private String calculationName;
    private double fixedFee;
    private double multiplier;
    private double profitConstant;

    public CalculationConstant() {
    }

    public CalculationConstant(int id, String marketplace, String calculationName, double fixedFee,  double multiplier, double profit_constant) {
        this.id = id;
        this.marketplace = marketplace;
        this.calculationName = calculationName;
        this.fixedFee = fixedFee;
        this.multiplier = multiplier;
        this.profitConstant = profit_constant;
    }

    public double getProfitConstant() {
        return profitConstant;
    }

    public void setProfitConstant(double profitConstant) {
        this.profitConstant = profitConstant;
    }

    public String getCalculationName() {
        return calculationName;
    }

    public void setCalculationName(String calculationName) {
        this.calculationName = calculationName;
    }

    public double getFixedFee() {
        return fixedFee;
    }

    public void setFixedFee(double fixedFee) {
        this.fixedFee = fixedFee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(String marketplace) {
        this.marketplace = marketplace;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public String toString() {
        return "CalculationConstant{" +
                "calculationName='" + calculationName + '\'' +
                ", id=" + id +
                ", marketplace='" + marketplace + '\'' +
                ", fixedFee=" + fixedFee +
                ", multiplier=" + multiplier +
                '}';
    }
}
