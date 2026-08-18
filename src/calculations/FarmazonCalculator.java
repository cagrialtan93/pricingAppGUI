package calculations;

import dao.CalculationConstantDAO;
import model.CalculationConstant;

public class FarmazonCalculator {
    private final CalculationConstantDAO dao;
    public FarmazonCalculator(CalculationConstantDAO calculationConstantDAO) {
        this.dao = calculationConstantDAO;
    }

    public double calculateFarmazon(double fiyat, double karOrani) {
        return calculateNormal(fiyat, karOrani, "Base");
    }

    public double calculateBase(double fiyat, double karOrani){
        return calculateNormal(fiyat, karOrani, "Base");
    }

    public double calculateMid(double fiyat, double karOrani){
        return calculateNormal(fiyat, karOrani, "Mid");
    }

    public double calculateHigh(double fiyat, double karOrani){
        return calculateNormal(fiyat, karOrani, "High");
    }

    private double calculateNormal(double fiyat, double karOrani, String calculationName) {
        CalculationConstant constant = dao.getConstant("Farmazon",  calculationName);
        System.out.println(constant.getMarketplace());
        return round((fiyat + constant.getFixedFee()) * constant.getMultiplier());
    }

    public double calculateCase(double fiyat, double karOrani, int paket){
        CalculationConstant constant = dao.getConstant("Farmazon", "Case_" + paket);

        return round(fiyat * constant.getMultiplier());
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

}
