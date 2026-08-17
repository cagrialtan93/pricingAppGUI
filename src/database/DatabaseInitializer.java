package database;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initialize(){
        String sql = """ 
                CREATE TABLE IF NOT EXISTS calculation_constants ( 
                    id INTEGER PRIMARY KEY AUTOINCREMENT, 
                    marketplace TEXT NOT NULL, 
                    calculation_name TEXT NOT NULL, 
                    fixed_fee REAL NOT NULL, 
                    multiplier REAL NOT NULL, 
                    UNIQUE(marketplace, calculation_name) ) 
                """;
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql); System.out.println("Database initialized successfully!");
        } catch (Exception e) { e.printStackTrace(); }
    }
}
