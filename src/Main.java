import com.sun.jdi.event.ExceptionEvent;
import database.DatabaseConnection;

import javax.swing.*;
import java.sql.Connection;

public class Main {

    private static final String PASSWORD = "3435";

    public static void main(String[] args) {

        try (Connection connection = DatabaseConnection.getConnection()){
            System.out.println("Database connected.");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
           while (true) {

            JPasswordField passwordField = new JPasswordField();

            int result = JOptionPane.showConfirmDialog(
                    null,
                    passwordField,
                    "Şifre Giriniz",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            // User clicked Cancel / closed the window
            if (result != JOptionPane.OK_OPTION) {
                System.exit(0);
            }

            String enteredPassword =
                    new String(passwordField.getPassword());

            if (PASSWORD.equals(enteredPassword)) {
                // Correct password
                break;
            }

            JOptionPane.showMessageDialog(
                    null,
                    "Hatalı şifre!",
                    "Hata",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        SwingUtilities.invokeLater(LandingPage::new);
    }
}