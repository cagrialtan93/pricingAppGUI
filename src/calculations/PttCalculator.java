package calculations;

import dao.CalculationConstantDAO;
import model.CalculationConstant;

public class PttCalculator {
    private final CalculationConstantDAO dao;

    public PttCalculator(CalculationConstantDAO dao) {
        this.dao = dao;
    }

    public double calculatePtt(double fiyat, double karOrani) {
        return calculateNormal(fiyat, karOrani, "Normal");
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
        CalculationConstant constant = dao.getConstant("PTT",  calculationName);
        System.out.println(constant.getMarketplace());
        return round((fiyat + constant.getFixedFee()) * constant.getMultiplier() * karOrani);
    }

    public double calculateCase(double fiyat, double karOrani, int paket){
        CalculationConstant constant = dao.getConstant("PTT", "Case_" + paket);

        return round((fiyat + constant.getFixedFee()) * constant.getMultiplier() * karOrani);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

}
