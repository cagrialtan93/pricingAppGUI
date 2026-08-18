package calculations;

import dao.CalculationConstantDAO;
import model.CalculationConstant;

public class N11Calculator {
    private final CalculationConstantDAO dao;

    public N11Calculator(CalculationConstantDAO dao) {
        this.dao = new CalculationConstantDAO();
    }

    public double calculateN11(double fiyat, double karOrani) {

        CalculationConstant baseTrendyol = dao.getConstant("N11", "Base");
        CalculationConstant midTrendyol = dao.getConstant("N11", "Mid");
        CalculationConstant highTrendyol = dao.getConstant("N11", "High");

        double base = round((fiyat + baseTrendyol.getFixedFee()) * baseTrendyol.getMultiplier() * karOrani);

        double result = base;
        double mid = round((fiyat + midTrendyol.getFixedFee()) * midTrendyol.getMultiplier() * karOrani);
        double high = round((fiyat + highTrendyol.getFixedFee()) * highTrendyol.getMultiplier() * karOrani);

        if (base >= 150 && base < 300) result = mid;
        if (result >= 300) result = high;

        return result;
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
        CalculationConstant constant = dao.getConstant("N11",  calculationName);

        return round((fiyat + constant.getFixedFee()) * constant.getMultiplier() * karOrani);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

}
