package calculations;

import dao.CalculationConstantDAO;
import model.CalculationConstant;

public class TSoftCalculator {
    CalculationConstantDAO dao;

    public TSoftCalculator(CalculationConstantDAO dao) {
        this.dao = dao;
    }

    public double calculateTSoft(double fiyat, double karOrani){
        CalculationConstant baseTSoft = dao.getConstant("TSoft", "Base");
        CalculationConstant midTSoft = dao.getConstant("TSoft", "Mid");

        double base = round((fiyat + baseTSoft.getFixedFee()) * (karOrani + baseTSoft.getProfitConstant()));
        double mid = round((fiyat + midTSoft.getFixedFee()) * (karOrani + midTSoft.getProfitConstant()));

        if (base >= 1000) return mid;
        return base;
    }

    public double calculateCase(double fiyat, double karOrani, int paket){
        CalculationConstant firstConstant = dao.getConstant("TSoft", "Base-" + paket);
        CalculationConstant secondConstant = dao.getConstant("TSoft", "Mid-" + paket);

        double base = round((fiyat + firstConstant.getFixedFee()) * (karOrani + firstConstant.getProfitConstant()));
        double mid = round((fiyat + secondConstant.getFixedFee()) * (karOrani + secondConstant.getProfitConstant()));

        if (base >= 1000) return mid;
        return base;
    }


    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}

