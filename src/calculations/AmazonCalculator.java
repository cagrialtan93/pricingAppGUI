package calculations;

import dao.CalculationConstantDAO;
import model.CalculationConstant;

public class AmazonCalculator {
    CalculationConstantDAO dao;

    public AmazonCalculator(CalculationConstantDAO dao) {
        this.dao = dao;
    }

    public double calculateAmazon(double fiyat, double karOrani) {
        CalculationConstant calculationConstant = dao.getConstant("Amazon", "Base");

        return round((fiyat + calculationConstant.getFixedFee()) * calculationConstant.getMultiplier() * karOrani);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
