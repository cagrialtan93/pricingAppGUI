package calculations;

import dao.CalculationConstantDAO;
import model.CalculationConstant;

public class HepsiburadaCalculator {
    private final CalculationConstantDAO dao;

    public HepsiburadaCalculator(CalculationConstantDAO dao) {
        this.dao = dao;
    }

    public double calculateHepsiburada(double fiyat, double karOrani) {

        CalculationConstant baseTrendyol = dao.getConstant("Hepsiburada", "Base");
        CalculationConstant midTrendyol = dao.getConstant("Hepsiburada", "Mid");
        CalculationConstant highTrendyol = dao.getConstant("Hepsiburada", "High");

        double base = round((fiyat + baseTrendyol.getFixedFee()) * baseTrendyol.getMultiplier() * karOrani);

        double result = base;
        double mid = round((fiyat + midTrendyol.getFixedFee()) * midTrendyol.getMultiplier() * karOrani);
        double high = round((fiyat + highTrendyol.getFixedFee()) * highTrendyol.getMultiplier() * karOrani);

        if (base >= 200 && base < 400) result = mid;
        if (result >= 400) result = high;

        return result;
    }

    private double calculateNormal(double fiyat, double karOrani, String calculationName) {
        CalculationConstant constant = dao.getConstant("Hepsiburada",  calculationName);

        return round((fiyat + constant.getFixedFee()) * constant.getMultiplier() * karOrani);
    }

    public double calculateCase(double fiyat, double karOrani, int paket){
        CalculationConstant constant = dao.getConstant("Hepsiburada", "Case_" + paket);

        return round((fiyat + constant.getFixedFee()) * constant.getMultiplier() * karOrani);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
