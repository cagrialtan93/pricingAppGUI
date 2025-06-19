//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import calculations.Calculation;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LandingPage implements Calculation{
    JFrame jFrame = new JFrame("Fiyatlama");
    JPanel jPanel = new JPanel(new GridLayout(10, 2));
    JLabel fiyatLabel = new JLabel("Fiyat giriniz", 0);
    JTextField fiyatJTextField = new JTextField();
    JLabel karLabel = new JLabel("Kar oranı giriniz.", 0);
    JTextField karJTextField = new JTextField();
    JLabel trLabel = new JLabel("Trendyol", 0);
    JTextField trTextField = new JTextField();

    JLabel hbLabel = new JLabel("Hepsiburada", 0);
    JTextField hbTextField = new JTextField();

    JLabel n11Label = new JLabel("N11", 0);
    JTextField n11TextField = new JTextField();

    JLabel amazonLabel = new JLabel("Amazon", 0);
    JTextField amazonTextField = new JTextField();
    JLabel pttLabel = new JLabel("Ptt", 0);
    JTextField pttTextField = new JTextField();
    JLabel eTicaretLabel = new JLabel("E-Ticaret", 0);
    JTextField eTicaretTextField = new JTextField();
    JLabel farmazonLabel = new JLabel("Farmazon", 0);
    JTextField farmazonLabel1 = new JTextField();
    JButton hesapla = new JButton("Hesapla");
    JButton temizle = new JButton("Temizle");
    private Calculation calculation;

    public LandingPage() {
        this.jPanel.add(this.fiyatLabel);
        this.fiyatJTextField.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    LandingPage.this.hesapla.doClick();
                }

            }

            public void keyReleased(KeyEvent e) {
            }
        });
        this.jPanel.add(this.fiyatJTextField);
        this.jPanel.add(this.karLabel);
        this.jPanel.add(this.karJTextField);
        this.karJTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    LandingPage.this.hesapla.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.jPanel.add(trLabel);
        this.jPanel.add(trTextField);

        this.jPanel.add(hbLabel);
        this.jPanel.add(hbTextField);

        this.jPanel.add(n11Label);
        this.jPanel.add(n11TextField);

        this.jPanel.add(this.amazonLabel);
        this.amazonTextField.setEditable(false);
        this.jPanel.add(this.amazonTextField);
        this.jPanel.add(this.pttLabel);
        this.pttTextField.setEditable(false);
        this.jPanel.add(this.pttTextField);
        this.jPanel.add(this.eTicaretLabel);
        this.eTicaretTextField.setEditable(false);
        this.jPanel.add(this.eTicaretTextField);
        this.jPanel.add(this.farmazonLabel);
        this.farmazonLabel1.setEditable(false);
        this.jPanel.add(this.farmazonLabel1);
        this.jPanel.add(this.hesapla);
        this.hesapla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == LandingPage.this.hesapla) {
                    if (!LandingPage.this.fiyatJTextField.getText().isEmpty() && !LandingPage.this.karJTextField.getText().isEmpty()) {
                        double karOranı;
                        if (LandingPage.this.karJTextField.getText().equals("")) {
                            karOranı = 1.0;
                        }

                        karOranı = Double.parseDouble(LandingPage.this.karJTextField.getText()) * 0.01 + 1.0;
                        double fiyat = Double.parseDouble(LandingPage.this.fiyatJTextField.getText());

                        double hb = Math.round(((fiyat + 85.84) * 1.18 * karOranı) * 100.0) / 100.0;
                        double ptt = Math.round(((fiyat + 65) * 1.17 * karOranı) * 100.0) / 100.0;
                        double amazon = Math.round(((fiyat + 75) * 1.15 * karOranı) * 100.0) / 100.0;
                        double farmazon = Math.round((fiyat * 1.1 * karOranı) * 100.0) / 100.0;
                        double eticaret = Math.round(((fiyat + 80) * karOranı) * 100.0) / 100.0;
                        double trendyolFiyat = calculateTrendyol(fiyat, karOranı);
                        double n11Fiyat= calculaten11(fiyat, karOranı);


                        LandingPage.this.trTextField.setText(String.valueOf(trendyolFiyat));
                        LandingPage.this.n11TextField.setText(String.valueOf(n11Fiyat));
                        LandingPage.this.hbTextField.setText(String.valueOf(hb));
                        LandingPage.this.amazonTextField.setText(String.valueOf(amazon));
                        LandingPage.this.pttTextField.setText(String.valueOf(ptt));
                        LandingPage.this.eTicaretTextField.setText(String.valueOf(eticaret));
                        LandingPage.this.farmazonLabel1.setText(String.valueOf(farmazon));
                    } else if (LandingPage.this.fiyatJTextField.getText().isEmpty()) {
                        JOptionPane.showConfirmDialog((Component) null, "Bir fiyat girmediniz.", "Uyarı", -1);
                    } else if (LandingPage.this.karJTextField.getText().isEmpty()) {
                        JOptionPane.showConfirmDialog((Component) null, "Bir kar oranı girmediniz.", "Uyarı", -1);
                    }
                }
            }
        });
        this.jPanel.add(this.temizle);
        this.temizle.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                LandingPage.this.fiyatJTextField.setText("");
                LandingPage.this.trTextField.setText("");
                LandingPage.this.hbTextField.setText("");
                LandingPage.this.n11TextField.setText("");
                LandingPage.this.amazonTextField.setText("");
                LandingPage.this.pttTextField.setText("");
                LandingPage.this.eTicaretTextField.setText("");
                LandingPage.this.farmazonLabel1.setText("");
                LandingPage.this.karJTextField.setText("");
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
        this.jFrame.add(this.jPanel);
        this.jFrame.setSize(275, 250);
        this.jFrame.setDefaultCloseOperation(3);
        this.jFrame.setLocationRelativeTo((Component) null);
        this.jFrame.setVisible(true);
    }

    @Override
    public double calculateTrendyol(double fiyat, double karOrani) {
        double trendyolFiyat = Math.round(((fiyat + 43) * 1.18 * karOrani) * 100.0) / 100.0;
        double trendyolFiyat250 = Math.round(((fiyat + 72) * 1.18 * karOrani) * 100.0) / 100.0;
        double trendyolFiyat251 = Math.round(((fiyat + 83.25) * 1.18 * karOrani) * 100.0) / 100.0;

        if (trendyolFiyat < 150) {

        }
        if (trendyolFiyat >= 150 && trendyolFiyat < 250) {
            trendyolFiyat = trendyolFiyat250;
        }
        if (trendyolFiyat >= 250) {
            trendyolFiyat = trendyolFiyat251;
        }

        return trendyolFiyat;
    }

    @Override
    public double calculaten11(double fiyat, double karOrani) {
        double n11Fiyat = Math.round(((fiyat + 43) * 1.18 * karOrani) * 100.0) / 100.0;
        double n11250 = Math.round(((fiyat + 75) * 1.18 * karOrani) * 100.0) / 100.0;
        double n11251 = Math.round(((fiyat + 82) * 1.18 * karOrani) * 100.0) / 100.0;

        if (n11Fiyat < 150) {

        }
        if (n11Fiyat >= 150 && n11Fiyat < 250) {
            n11Fiyat = n11250;
        }
        if (n11Fiyat >= 250) {
            n11Fiyat = n11251;
        }

        return n11Fiyat;
    }
}
