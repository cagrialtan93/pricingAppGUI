package calculations;

import dao.CalculationConstantDAO;
import model.CalculationConstant;

public class TrendyolCalculator {

    private final CalculationConstantDAO dao;

    public double calculateTrendyol(double fiyat, double karOrani) {

        CalculationConstant baseTrendyol = dao.getConstant("Trendyol", "Base");
        CalculationConstant midTrendyol = dao.getConstant("Trendyol", "Mid");
        CalculationConstant highTrendyol = dao.getConstant("Trendyol", "High");

        double base = round((fiyat + baseTrendyol.getFixedFee()) * baseTrendyol.getMultiplier() * karOrani);

        double result = base;
        double mid = round((fiyat + midTrendyol.getFixedFee()) * midTrendyol.getMultiplier() * karOrani);
        double high = round((fiyat + highTrendyol.getFixedFee()) * highTrendyol.getMultiplier() * karOrani);

        if (base >= 150 && base < 350) result = mid;
        if (result >= 350) result = high;

        return result;
    }
    public TrendyolCalculator(CalculationConstantDAO dao) {
        this.dao = dao;
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
        CalculationConstant constant = dao.getConstant("Trendyol",  calculationName);

        return round((fiyat + constant.getFixedFee()) * constant.getMultiplier() * karOrani);
    }

    public double calculateCase(double fiyat, double karOrani, int paket){
        CalculationConstant constant = dao.getConstant("Trendyol", "Case_" + paket);

        return round((fiyat + constant.getFixedFee()) * constant.getMultiplier() * karOrani);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
