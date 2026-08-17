package dao;

import database.DatabaseConnection;
import model.CalculationConstant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CalculationConstantDAO {

    public CalculationConstant getConstant(
            String marketplace,
            String calculationName) {

        String sql = """
                SELECT id,
                       marketplace,
                       calculation_name,
                       fixed_fee,
                       multiplier,
                       profit_constant
                FROM calculation_constants
                WHERE marketplace = ?
                  AND calculation_name = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, marketplace);
            statement.setString(2, calculationName);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return new CalculationConstant(
                            resultSet.getInt("id"),
                            resultSet.getString("marketplace"),
                            resultSet.getString("calculation_name"),
                            resultSet.getDouble("fixed_fee"),
                            resultSet.getDouble("multiplier"),
                            resultSet.getDouble("profit_constant")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}