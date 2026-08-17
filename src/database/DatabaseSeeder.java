package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
public class DatabaseSeeder {
    public static void seed() {
        String sql = """ 
                INSERT OR IGNORE INTO calculation_constants (marketplace, calculation_name, fixed_fee, multiplier, profit_constant) VALUES (?, ?, ?, ?, ?) """;
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {



            System.out.println("Database seeded successfully!"); } catch (Exception e) { e.printStackTrace();
        }
    }
    private static void addConstant( PreparedStatement statement, String marketplace, String calculationName, double fixedFee, double multiplier, double profit_constant) throws Exception {
        statement.setString(1, marketplace);
        statement.setString(2, calculationName);
        statement.setDouble(3, fixedFee);
        statement.setDouble(4, multiplier);
        statement.setDouble(5, profit_constant);
        statement.executeUpdate();
    }
}