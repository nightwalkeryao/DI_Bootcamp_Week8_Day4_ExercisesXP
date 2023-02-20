package exercice2;

import database.DBConnection;
import models.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Exercice2 {
    public static void insertCompany(List<Company> companies) {
        final String SQL = """
                    INSERT INTO company(id, name, age, address, salary)
                    VALUES(?, ?, ?, ?, ?)
                """;

        try {
            Connection connection = new DBConnection().getDBConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            int count = 0;

            for (Company company : companies) {
                statement.setInt(1, company.getId());
                statement.setString(2, company.getName());
                statement.setInt(3, company.getAge());
                statement.setString(4, company.getAddress());
                statement.setDouble(5, company.getSalary());

                statement.addBatch();
                count++;
                if (count == companies.size()) {
                    statement.executeBatch();
                }
            }
            System.out.println("Données insérées avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
