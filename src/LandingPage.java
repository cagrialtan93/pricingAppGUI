import calculations.Calculation;
import dao.CalculationConstantDAO;
import model.CalculationConstant;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;

public class LandingPage implements Calculation {

    JFrame frame = new JFrame("Fiyatlama");
    JTabbedPane tabbedPane = new JTabbedPane();
    private static CalculationConstantDAO calculationConstantDAO = new CalculationConstantDAO();
    CalculationConstant basePTT = calculationConstantDAO.getConstant("PTT", "Normal");
    CalculationConstant firstCasePTT = calculationConstantDAO.getConstant("PTT", "Case_1");
    CalculationConstant secondCasePTT = calculationConstantDAO.getConstant("PTT", "Case_2");
    CalculationConstant thirdCasePTT = calculationConstantDAO.getConstant("PTT", "Case_3");
    CalculationConstant fourthCasePTT = calculationConstantDAO.getConstant("PTT", "Case_4");

    CalculationConstant baseTrendyol =  calculationConstantDAO.getConstant("Trendyol", "Base");
    CalculationConstant midTrendyol =  calculationConstantDAO.getConstant("Trendyol", "Mid");
    CalculationConstant highTrendyol =  calculationConstantDAO.getConstant("Trendyol", "High");
    CalculationConstant firstCaseTrendyol =  calculationConstantDAO.getConstant("Trendyol", "Case_1");
    CalculationConstant secondCaseTrendyol =  calculationConstantDAO.getConstant("Trendyol", "Case_2");
    CalculationConstant thirdCaseTrendyol =  calculationConstantDAO.getConstant("Trendyol", "Case_3");
    CalculationConstant fourthCaseTrendyol =  calculationConstantDAO.getConstant("Trendyol", "Case_4");

    CalculationConstant baseHepsiburada =  calculationConstantDAO.getConstant("Hepsiburada", "Base");
    CalculationConstant midHepsiburada =  calculationConstantDAO.getConstant("Hepsiburada", "Mid");
    CalculationConstant highHepsiburada =  calculationConstantDAO.getConstant("Hepsiburada", "High");
    CalculationConstant firstCaseHepsiburada =  calculationConstantDAO.getConstant("Hepsiburada", "Case_1");
    CalculationConstant secondCaseHepsiburada =  calculationConstantDAO.getConstant("Hepsiburada", "Case_2");
    CalculationConstant thirdCaseHepsiburada =  calculationConstantDAO.getConstant("Hepsiburada", "Case_3");
    CalculationConstant fourthCaseHepsiburada =  calculationConstantDAO.getConstant("Hepsiburada", "Case_4");

    CalculationConstant baseN11 = calculationConstantDAO.getConstant("N11", "Base");
    CalculationConstant midN11 = calculationConstantDAO.getConstant("N11", "Mid");
    CalculationConstant highN11 = calculationConstantDAO.getConstant("N11", "High");

    CalculationConstant baseFarmazon =  calculationConstantDAO.getConstant("Farmazon", "Base");
    CalculationConstant firstCaseFarmazon =  calculationConstantDAO.getConstant("Farmazon", "Case_1");
    CalculationConstant secondCaseFarmazon =  calculationConstantDAO.getConstant("Farmazon", "Case_2");
    CalculationConstant thirdCaseFarmazon =  calculationConstantDAO.getConstant("Farmazon", "Case_3");
    CalculationConstant fourthCaseFarmazon =  calculationConstantDAO.getConstant("Farmazon", "Case_4");

    CalculationConstant firstBaseTSoft =  calculationConstantDAO.getConstant("TSoft", "Base_1");
    CalculationConstant firstMidTSoft =  calculationConstantDAO.getConstant("TSoft", "Mid_1");
    CalculationConstant secondBaseTSoft =  calculationConstantDAO.getConstant("TSoft", "Base_2");
    CalculationConstant secondMidTSoft =  calculationConstantDAO.getConstant("TSoft", "Mid_2");
    CalculationConstant thirdBaseTSoft =  calculationConstantDAO.getConstant("TSoft", "Base_3");
    CalculationConstant thirdMidTSoft =  calculationConstantDAO.getConstant("TSoft", "Mid_3");
    CalculationConstant fourthBaseTSoft =  calculationConstantDAO.getConstant("TSoft", "Base_4");
    CalculationConstant fourthMidTSoft =  calculationConstantDAO.getConstant("TSoft", "Mid_4");
    CalculationConstant fifthBaseTSoft =  calculationConstantDAO.getConstant("TSoft", "Base_5");
    CalculationConstant fifthMidTSoft =  calculationConstantDAO.getConstant("TSoft", "Mid_5");

    CalculationConstant baseAmazon =  calculationConstantDAO.getConstant("Amazon", "Base");

    enum TabType {
        NORMAL,
        BEZLER
    }

    public LandingPage() {
        tabbedPane.addTab("Normal", createPricingPanel(TabType.NORMAL));
        tabbedPane.addTab("Bezler", createPricingPanel(TabType.BEZLER));

        frame.add(tabbedPane);
        frame.setSize(400, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createPricingPanel(TabType type) {

        JPanel panel = new JPanel(new GridLayout(0, 2, 6, 6));

        JTextField fiyat = new JTextField();
        JTextField kar = new JTextField();
        JTextField paketAdediField = null;

        JTextField tr = new JTextField();
        JTextField hb = new JTextField();
        JTextField n11 = new JTextField();
        JTextField amazon = new JTextField();
        JTextField ptt = new JTextField();
        JTextField eticaret = new JTextField();
        JTextField farmazon = new JTextField();
        JTextField tsoft = new JTextField();

        tr.setEditable(false);
        hb.setEditable(false);
        n11.setEditable(false);
        amazon.setEditable(false);
        ptt.setEditable(false);
        eticaret.setEditable(false);
        farmazon.setEditable(false);
        tsoft.setEditable(false);

        panel.add(new JLabel("Fiyat"));
        panel.add(fiyat);

        panel.add(new JLabel("Kar (%)"));
        panel.add(kar);

        if (type == TabType.BEZLER) {
            paketAdediField = new JTextField();
            panel.add(new JLabel("Paket Adedi (1-4)"));
            panel.add(paketAdediField);
        }

        panel.add(new JLabel("Trendyol"));
        panel.add(tr);

        panel.add(new JLabel("Hepsiburada"));
        panel.add(hb);

        panel.add(new JLabel("N11"));
        panel.add(n11);

        panel.add(new JLabel("Amazon"));
        panel.add(amazon);

        panel.add(new JLabel("Ptt"));
        panel.add(ptt);

        panel.add(new JLabel("Farmazon"));
        panel.add(farmazon);

        panel.add(new JLabel("Tsoft"));
        panel.add(tsoft);

        JButton hesapla = new JButton("Hesapla");
        JButton temizle = new JButton("Temizle");

        panel.add(hesapla);
        panel.add(temizle);

        fiyat.addActionListener(e -> hesapla.doClick());
        kar.addActionListener(e -> hesapla.doClick());
        if (paketAdediField != null) {
            paketAdediField.addActionListener(e -> hesapla.doClick());
        }

        JTextField finalPaketField = paketAdediField;

        hesapla.addActionListener(e -> {

            if (fiyat.getText().isEmpty() || kar.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fiyat ve kar giriniz");
                return;
            }

            double f, k;
            try {
                f = Double.parseDouble(fiyat.getText());
                k = Double.parseDouble(kar.getText()) * 0.01 + 1;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Sayısal değer giriniz");
                return;
            }

            if (type == TabType.NORMAL) {

                tr.setText(String.valueOf(calculateTrendyol(f, k)));
                n11.setText(String.valueOf(calculaten11(f, k)));
                hb.setText(String.valueOf(calculateHepsiBurada(f, k)));
                ptt.setText(String.valueOf(round((f + basePTT.getFixedFee()) * basePTT.getMultiplier() * k)));
                amazon.setText(String.valueOf(round((f + baseAmazon.getFixedFee()) * baseAmazon.getMultiplier())));
                farmazon.setText(String.valueOf(round(f * baseFarmazon.getMultiplier())));
                tsoft.setText(String.valueOf(calculatenTSoft(f, k)));
            }

            else {

                if (finalPaketField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Paket adedi giriniz (1-4)");
                    return;
                }

                int paket;
                try {
                    paket = Integer.parseInt(finalPaketField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Paket adedi sadece sayı olmalı (1-4)");
                    return;
                }

                if (paket < 1 || paket > 4) {
                    JOptionPane.showMessageDialog(null, "Paket adedi 1-4 arasında olmalı");
                    return;
                }

                switch (paket) {
                    case 1 -> {
                        shortCalculation(tr, hb, ptt, f, k, firstCaseTrendyol, firstCaseHepsiburada, firstCasePTT);
                        tsoft.setText(String.valueOf(calculatenTSoftBezlerPaketBir(f,k)));
                        farmazon.setText(String.valueOf(round(f * firstCaseFarmazon.getMultiplier())));
                    }
                    case 2 -> {
                        shortCalculation(tr, hb, ptt, f, k, secondCaseTrendyol, secondCaseHepsiburada, secondCasePTT);
                        tsoft.setText(String.valueOf(calculatenTSoftBezlerPaketIki(f,k)));
                        farmazon.setText(String.valueOf(round(f * secondCaseFarmazon.getMultiplier())));
                    }
                    case 3 -> {
                        shortCalculation(tr, hb, ptt, f, k, thirdCaseTrendyol, thirdCaseHepsiburada, thirdCasePTT);
                        tsoft.setText(String.valueOf(calculatenTSoftBezlerPaketUc(f,k)));
                        farmazon.setText(String.valueOf(round(f * thirdCaseFarmazon.getMultiplier())));
                    }
                    case 4 -> {
                        shortCalculation(tr, hb, ptt, f, k, fourthCaseTrendyol, fourthCaseHepsiburada, fourthCasePTT);
                        tsoft.setText(String.valueOf(calculatenTSoftBezlerPaketDort(f,k)));
                        farmazon.setText(String.valueOf(round(f * fourthCaseFarmazon.getMultiplier())));
                    }
                }
            }
        });

        temizle.addActionListener(e -> {
            fiyat.setText("");
            kar.setText("");
            if (finalPaketField != null) finalPaketField.setText("");
            tr.setText("");
            hb.setText("");
            n11.setText("");
            amazon.setText("");
            ptt.setText("");
            eticaret.setText("");
            farmazon.setText("");
            tsoft.setText("");
        });

        return panel;
    }

    private void shortCalculation(JTextField tr, JTextField hb, JTextField ptt, double f, double k, CalculationConstant firstCaseTrendyol, CalculationConstant firstCaseHepsiburada, CalculationConstant firstCasePTT) {
        tr.setText(String.valueOf(round((f + firstCaseTrendyol.getFixedFee()) * firstCaseTrendyol.getMultiplier() * k)));
        hb.setText(String.valueOf(round((f + firstCaseHepsiburada.getFixedFee()) * firstCaseHepsiburada.getMultiplier() * k)));
        ptt.setText(String.valueOf(round((f + firstCasePTT.getFixedFee()) * firstCasePTT.getMultiplier() * k)));
    }

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    private double result = 0;

    @Override
    public double calculateTrendyol(double fiyat, double karOrani) {
        double base = round((fiyat + baseTrendyol.getFixedFee()) * baseTrendyol.getMultiplier() * karOrani);
        double result = base;
        double mid = round((fiyat + midTrendyol.getFixedFee()) * midTrendyol.getMultiplier() * karOrani);
        double high = round((fiyat + highTrendyol.getFixedFee()) * highTrendyol.getMultiplier() * karOrani);

        if (base >= 150 && base < 350) result = mid;
        if (result >= 350) result = high;

        return result;
    }

    public double calculateHepsiBurada(double fiyat, double karOrani) {
        double base = round((fiyat + baseHepsiburada.getFixedFee()) * baseHepsiburada.getMultiplier() * karOrani);
        double result = base;
        double mid = round((fiyat + midHepsiburada.getFixedFee()) * midHepsiburada.getMultiplier() * karOrani);
        double high = round((fiyat + highHepsiburada.getFixedFee()) * highHepsiburada.getMultiplier() * karOrani);

        if (base >= 200 && base < 400) result = mid;
        if (result >= 400) result = high;
        return result;
    }

    @Override
    public double calculaten11(double fiyat, double karOrani) {
        double base = round((fiyat + baseN11.getFixedFee()) * baseN11.getMultiplier() * karOrani);
        double result = base;
        double mid = round((fiyat + midN11.getFixedFee()) * midN11.getMultiplier() * karOrani);
        double high = round((fiyat + highN11.getFixedFee()) * highN11.getMultiplier() * karOrani);


        if (base >= 150 && base < 300) result = mid;
        if (result >= 300) result = high;
        return result;
    }

    public double calculatenTSoft(double fiyat, double karOrani) {
        double base = round((fiyat + firstBaseTSoft.getFixedFee()) * (karOrani + firstBaseTSoft.getProfitConstant()));
        double mid = round((fiyat + firstMidTSoft.getFixedFee()) * (karOrani + firstMidTSoft.getProfitConstant()));

        if (base >= 1000) return mid;
        return base;
    }

    public double calculatenTSoftBezlerPaketBir(double fiyat, double karOrani) {
        double base = round((fiyat + secondBaseTSoft.getFixedFee()) * (karOrani + secondBaseTSoft.getProfitConstant()));
        double mid = round((fiyat + secondMidTSoft.getFixedFee()) * (karOrani + secondMidTSoft.getProfitConstant()));

        if (base >= 1000) return mid;
        return base;
    }

    public double calculatenTSoftBezlerPaketIki(double fiyat, double karOrani) {
        double base = round((fiyat + thirdBaseTSoft.getFixedFee()) * (karOrani + thirdBaseTSoft.getProfitConstant()));
        double mid = round((fiyat + thirdMidTSoft.getFixedFee()) * (karOrani + thirdMidTSoft.getProfitConstant()));

        if (base >= 1000) return mid;
        return base;
    }

    public double calculatenTSoftBezlerPaketUc(double fiyat,double karOrani) {
        double base = round((fiyat + fourthBaseTSoft.getFixedFee()) * (karOrani + fourthBaseTSoft.getProfitConstant()));
        double mid = round((fiyat + fourthMidTSoft.getFixedFee()) * (karOrani + fourthMidTSoft.getProfitConstant()));

        if (base >= 1000) return mid;
        return base;
    }

    public double calculatenTSoftBezlerPaketDort(double fiyat,double karOrani) {
        double base = round((fiyat + fifthBaseTSoft.getFixedFee()) * (karOrani + fifthBaseTSoft.getProfitConstant()));
        double mid = round((fiyat + fifthMidTSoft.getFixedFee()) * (karOrani + fifthMidTSoft.getProfitConstant()));

        if (base >= 1000) return mid;
        return base;
    }

    public static void main(String[] args) {
        new LandingPage();
    }
}
