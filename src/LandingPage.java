import calculations.*;
import dao.CalculationConstantDAO;
import model.CalculationConstant;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LandingPage {

    JFrame frame = new JFrame("Fiyatlama");
    JTabbedPane tabbedPane = new JTabbedPane();
    private final CalculationConstantDAO calculationConstantDAO = new CalculationConstantDAO();
    private final TrendyolCalculator trendyolCalculator = new TrendyolCalculator(calculationConstantDAO);
    private final HepsiburadaCalculator hepsiburadaCalculator = new HepsiburadaCalculator(calculationConstantDAO);
    private final N11Calculator n11Calculator = new N11Calculator(calculationConstantDAO);
    private final PttCalculator pttCalculator = new PttCalculator(calculationConstantDAO);
    private final FarmazonCalculator farmazonCalculator = new FarmazonCalculator(calculationConstantDAO);
    private final TSoftCalculator tsoftCalculator = new TSoftCalculator(calculationConstantDAO);
    private final AmazonCalculator amazonCalculator = new AmazonCalculator(calculationConstantDAO);

    enum TabType {
        NORMAL,
        BEZLER,
        ADMIN
    }

    public LandingPage() {
        tabbedPane.addTab("Normal", createPricingPanel(TabType.NORMAL));
        tabbedPane.addTab("Bezler", createPricingPanel(TabType.BEZLER));
        tabbedPane.addTab("Admin", createAdminPanel());

        frame.add(tabbedPane);
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createAdminPanel() {
        JPanel adminPanel = new JPanel(new BorderLayout(10, 10));

        JPanel constantsPanel = new JPanel();
        constantsPanel.setLayout(new BoxLayout(constantsPanel, BoxLayout.Y_AXIS));

        List<CalculationConstant> constants = calculationConstantDAO.getAllConstants();

        Map<CalculationConstant, JTextField[]> fields = new LinkedHashMap<>();

        String currentMarketPlace = "";

        for (CalculationConstant constant : constants) {
            if (!currentMarketPlace.equals(constant.getMarketplace())) {
                currentMarketPlace = constant.getMarketplace();

                JLabel marketplaceLabel = new JLabel(currentMarketPlace);
                marketplaceLabel.setFont(marketplaceLabel.getFont().deriveFont(Font.BOLD, 16f));
                constantsPanel.add(Box.createVerticalStrut(10));
                constantsPanel.add(marketplaceLabel);
            }

            JPanel row = new JPanel(new GridLayout(1, 5, 5, 5));
            JLabel nameLabel = new JLabel(constant.getCalculationName());

            JTextField fixedFeeField =
                    new JTextField(String.valueOf(constant.getFixedFee()));

            JTextField multiplierField =
                    new JTextField(String.valueOf(constant.getMultiplier()));

            JTextField profitConstantField =
                    new JTextField(String.valueOf(
                            constant.getProfitConstant()
                    ));

            row.add(nameLabel);
            row.add(fixedFeeField);
            row.add(multiplierField);
            row.add(profitConstantField);

            constantsPanel.add(row);

            fields.put(constant, new JTextField[]{fixedFeeField, multiplierField, profitConstantField});
        }

        JPanel header = new JPanel(new GridLayout(1, 4));
        header.add(new JLabel("Calculation"));
        header.add(new JLabel("Fixed Fee"));
        header.add(new JLabel("Multiplier"));
        header.add(new JLabel("Profit Constant"));

        adminPanel.add(header, BorderLayout.NORTH);

        JScrollPane scrollPane =
                new JScrollPane(constantsPanel);

        adminPanel.add(scrollPane, BorderLayout.CENTER);

        JButton saveButton =
                new JButton("Save Changes");

        saveButton.addActionListener(e -> {

            try {

                for (Map.Entry<CalculationConstant, JTextField[]> entry
                        : fields.entrySet()) {

                    CalculationConstant constant = entry.getKey();
                    JTextField[] constantFields = entry.getValue();

                    double fixedFee =
                            Double.parseDouble(
                                    constantFields[0].getText()
                            );

                    double multiplier =
                            Double.parseDouble(
                                    constantFields[1].getText()
                            );

                    double profitConstant =
                            Double.parseDouble(
                                    constantFields[2].getText()
                            );


                    constant.setFixedFee(fixedFee);
                    constant.setMultiplier(multiplier);
                    constant.setProfitConstant(profitConstant);

                    calculationConstantDAO.updateConstant(constant);
                }

                JOptionPane.showMessageDialog(
                        adminPanel,
                        "Changes saved successfully!"
                );

            } catch (NumberFormatException error) {
                JOptionPane.showMessageDialog(
                        adminPanel,
                        "Please enter valid numbers",
                        "Invalid value",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
        adminPanel.add(saveButton, BorderLayout.SOUTH);
        return adminPanel;
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

                tr.setText(String.valueOf(trendyolCalculator.calculateTrendyol(f, k)));
                hb.setText(String.valueOf(hepsiburadaCalculator.calculateHepsiburada(f, k)));
                n11.setText(String.valueOf(n11Calculator.calculateN11(f, k)));
                ptt.setText(String.valueOf(pttCalculator.calculatePtt(f, k)));
                farmazon.setText(String.valueOf(farmazonCalculator.calculateFarmazon(f, k)));
                tsoft.setText(String.valueOf(tsoftCalculator.calculateTSoft(f, k)));
                amazon.setText(String.valueOf(amazonCalculator.calculateAmazon(f)));

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
                    case 1, 2, 3, 4 -> {
                        shortCalculation(tr, hb, ptt, farmazon, tsoft, f, k, paket);
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

    private void shortCalculation(JTextField tr, JTextField hb, JTextField ptt, JTextField farmazon, JTextField tsoft, double f, double k, int paket) {
        tr.setText(String.valueOf(trendyolCalculator.calculateCase(f, k, paket)));
        hb.setText(String.valueOf(hepsiburadaCalculator.calculateCase(f, k,paket)));
        ptt.setText(String.valueOf(pttCalculator.calculateCase(f, k,paket)));
        farmazon.setText(String.valueOf(farmazonCalculator.calculateCase(f, k,paket)));
        tsoft.setText(String.valueOf(tsoftCalculator.calculateCase(f, k,paket)));
    }

    public static void main(String[] args) {
        new LandingPage();
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
