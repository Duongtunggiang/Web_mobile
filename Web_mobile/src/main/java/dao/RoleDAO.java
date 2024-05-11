package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAO {
	private static Connection connection;

    public RoleDAO(Connection connection) {
        RoleDAO.connection = connection;
    }

    public static String getUserRole(int userId) {
        String roleName = null;

        try {
            String query = "SELECT roleName FROM role WHERE userId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                roleName = resultSet.getString("roleName");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roleName;
    }
}
