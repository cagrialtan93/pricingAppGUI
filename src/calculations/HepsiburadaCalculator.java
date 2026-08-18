package calculations;

import dao.CalculationConstantDAO;
import model.CalculationConstant;

public class HepsiburadaCalculator {
    private final CalculationConstantDAO dao;

    public HepsiburadaCalculator(CalculationConstantDAO dao) {
        this.dao = dao;
    }

    public double calculateHepsiburada(double fiyat, double karOrani) {
        double base = calculateNormal(fiyat, karOrani, "Base");
        double mid = calculateNormal(fiyat, karOrani, "Mid");
        double high = calculateNormal(fiyat, karOrani, "High");
        double result = base;

        if (base >= 200 && base < 400) result = mid;
        if (result >= 400) result = high;

        return result;
    }

    private double calculateNormal(double fiyat, double karOrani, String calculationName) {
        CalculationConstant constant = dao.getConstant("Hepsiburada", calculationName);

        return round((fiyat + constant.getFixedFee()) * constant.getMultiplier() * karOrani);
    }

    public double calculateCase(double fiyat, double karOrani, int paket) {
        CalculationConstant constant = dao.getConstant("Hepsiburada", "Case_" + paket);

        return round((fiyat + constant.getFixedFee()) * constant.getMultiplier() * karOrani);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
