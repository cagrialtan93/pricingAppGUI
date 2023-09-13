//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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

public class LandingPage {
    JFrame jFrame = new JFrame("Fiyatlama");
    JPanel jPanel = new JPanel(new GridLayout(8, 2));
    JLabel fiyatLabel = new JLabel("Fiyat giriniz", 0);
    JTextField fiyatJTextField = new JTextField();
    JLabel karLabel = new JLabel("Kar oranı giriniz.", 0);
    JTextField karJTextField = new JTextField();
    JLabel trHbn11Label = new JLabel("Tr-HB-n11", 0);
    JTextField trHbn11TextField = new JTextField();
    JLabel amazonLabel = new JLabel("Amazon", 0);
    JTextField amazonTextField = new JTextField();
    JLabel pttLabel = new JLabel("Ptt", 0);
    JTextField pttTextField = new JTextField();
    JLabel eTicaretLabel = new JLabel("Eticaret", 0);
    JTextField eTicaretTextField = new JTextField();
    JLabel farmazonLabel = new JLabel("Farmazon", 0);
    JTextField farmazonLabel1 = new JTextField();
    JButton hesapla = new JButton("Hesapla");
    JButton temizle = new JButton("Temizle");

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
                if (e.getKeyCode() == 10){
                    LandingPage.this.hesapla.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.jPanel.add(this.trHbn11Label);
        this.trHbn11TextField.setEditable(false);
        this.jPanel.add(this.trHbn11TextField);
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
                        double trhbn11 = (fiyat + 40.0) / 0.83 * karOranı;
                        int trhbn11v2 = (int)trhbn11;
                        double amazon = (fiyat * karOranı + 29.0) / 0.89;
                        int amazonv2 = (int)amazon;
                        double ptt = (fiyat + 22.0) / 0.83 * karOranı;
                        int pttv2 = (int)ptt;
                        double eticaret = (fiyat + 25.0) / 0.97 * karOranı;
                        int eticaretv2 = (int)eticaret;
                        double farmazon = fiyat * 1.1 / 0.9;
                        int farmazonv2 = (int)farmazon;
                        LandingPage.this.trHbn11TextField.setText(String.valueOf(trhbn11v2));
                        LandingPage.this.amazonTextField.setText(String.valueOf(amazonv2));
                        LandingPage.this.pttTextField.setText(String.valueOf(pttv2));
                        LandingPage.this.eTicaretTextField.setText(String.valueOf(eticaretv2));
                        LandingPage.this.farmazonLabel1.setText(String.valueOf(farmazonv2));
                    } else if (LandingPage.this.fiyatJTextField.getText().isEmpty()) {
                        JOptionPane.showConfirmDialog((Component)null, "Bir fiyat girmediniz.", "Uyarı", -1);
                    } else if (LandingPage.this.karJTextField.getText().isEmpty()) {
                        JOptionPane.showConfirmDialog((Component)null, "Bir kar oranı girmediniz.", "Uyarı", -1);
                    }
                }

            }
        });
        this.jPanel.add(this.temizle);
        this.temizle.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                LandingPage.this.fiyatJTextField.setText("");
                LandingPage.this.trHbn11TextField.setText("");
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
        this.jFrame.setLocationRelativeTo((Component)null);
        this.jFrame.setVisible(true);
    }
}
