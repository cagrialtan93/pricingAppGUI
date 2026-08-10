import calculations.Calculation;

import javax.swing.*;
import java.awt.*;

public class LandingPage implements Calculation {

    JFrame frame = new JFrame("Fiyatlama");
    JTabbedPane tabbedPane = new JTabbedPane();

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

                ptt.setText(String.valueOf(round((f + 81) * 1.18 * k)));
                amazon.setText(String.valueOf(round((f + 95) * 1.15 * 1.1)));
                farmazon.setText(String.valueOf(round(f * 1.1 * 1.12)));
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
                        tr.setText(String.valueOf(round((f + 170) * 1.19 * k)));
                        hb.setText(String.valueOf(round((f + 151) * 1.156 * k)));
                        ptt.setText(String.valueOf(round((f + 162) * 1.18 * k)));
                        tsoft.setText(String.valueOf(calculatenTSoftBezlerPaketBir(f,k)));
                        farmazon.setText(String.valueOf(round(f * 1.1 * 1.15)));
                    }
                    case 2 -> {
                        tr.setText(String.valueOf(round((f + 248) * 1.19 * k)));
                        hb.setText(String.valueOf(round((f + 217) * 1.156 * k)));
                        ptt.setText(String.valueOf(round((f + 154) * 1.18 * k)));
                        tsoft.setText(String.valueOf(calculatenTSoftBezlerPaketIki(f,k)));
                        farmazon.setText(String.valueOf(round(f * 1.1 * 1.15)));
                    }
                    case 3 -> {
                        tr.setText(String.valueOf(round((f + 326) * 1.19 * k)));
                        hb.setText(String.valueOf(round((f + 301) * 1.156 * k)));
                        ptt.setText(String.valueOf(round((f + 146) * 1.18 * k)));
                        tsoft.setText(String.valueOf(calculatenTSoftBezlerPaketUc(f,k)));
                        farmazon.setText(String.valueOf(round(f * 1.1 * 1.15)));
                    }
                    case 4 -> {
                        tr.setText(String.valueOf(round((f + 416) * 1.19 * k)));
                        hb.setText(String.valueOf(round((f + 398) * 1.156 * k)));
                        ptt.setText(String.valueOf(round((f + 168) * 1.18 * k)));
                        tsoft.setText(String.valueOf(calculatenTSoftBezlerPaketDort(f,k)));
                        farmazon.setText(String.valueOf(round(f * 1.1 * 1.15)));
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

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    @Override
    public double calculateTrendyol(double fiyat, double karOrani) {
        double base = round((fiyat + 57) * 1.19 * karOrani);
        double mid = round((fiyat + 95) * 1.19 * karOrani);
        double high = round((fiyat + 108) * 1.19 * karOrani);

        if (base >= 150 && base < 350) return mid;
        if (base >= 350) return high;
        return base;
    }

    public double calculateHepsiBurada(double fiyat, double karOrani) {
        double base = round((fiyat + 50) * 1.19 * karOrani);
        double mid = round((fiyat + 87) * 1.19 * karOrani);
        double high = round((fiyat + 95) * 1.19 * karOrani);

        if (base >= 200 && base < 400) return mid;
        if (base >= 400) return high;
        return base;
    }

    @Override
    public double calculaten11(double fiyat, double karOrani) {
        double base = round((fiyat + 58) * 1.18 * karOrani);
        double mid = round((fiyat + 95) * 1.18 * karOrani);
        double high = round((fiyat + 109) * 1.18 * karOrani);

        if (base >= 150 && base < 300) return mid;
        if (base >= 300) return high;
        return base;
    }

    public double calculatenTSoft(double fiyat, double karOrani) {
        double base = round((fiyat + 56) * (karOrani + 3));
        double mid = round((fiyat + 106) * (karOrani + 3));

        if (base >= 1000) return mid;
        return base;
    }

    public double calculatenTSoftBezlerPaketBir(double fiyat, double karOrani) {
        double base = round((fiyat + 112) * (karOrani + 3));
        double mid = round((fiyat + 162) * (karOrani + 3));

        if (base >= 1000) return mid;
        return base;
    }

    public double calculatenTSoftBezlerPaketIki(double fiyat, double karOrani) {
        double base = round((fiyat + 152) * (karOrani + 3));
        double mid = round((fiyat + 202) * (karOrani + 3));

        if (base >= 1000) return mid;
        return base;
    }

    public double calculatenTSoftBezlerPaketUc(double fiyat,double karOrani) {
        double base = round((fiyat + 231) * (karOrani + 3));
        double mid = round((fiyat + 281) * (karOrani + 3));

        if (base >= 1000) return mid;
        return base;
    }

    public double calculatenTSoftBezlerPaketDort(double fiyat,double karOrani) {
        double base = round((fiyat + 279) * (karOrani + 3));
        double mid = round((fiyat + 329) * (karOrani + 3));

        if (base >= 1000) return mid;
        return base;
    }

    public static void main(String[] args) {
        new LandingPage();
    }
}
