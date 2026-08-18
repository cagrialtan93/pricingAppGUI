package calculations;

import dao.CalculationConstantDAO;
import model.CalculationConstant;

public class TrendyolCalculator {

    private final CalculationConstantDAO dao;

    public double calculateTrendyol(double fiyat, double karOrani) {


        double base = calculateNormal(fiyat, karOrani, "Base");
        double mid = calculateNormal(fiyat, karOrani, "Mid");
        double high = calculateNormal(fiyat, karOrani, "High");

        double result = base;

        if (base >= 150 && base < 350) result = mid;
        if (result >= 350) result = high;

        return result;
    }
    public TrendyolCalculator(CalculationConstantDAO dao) {
        this.dao = dao;
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
