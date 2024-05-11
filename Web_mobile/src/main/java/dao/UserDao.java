package dao;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;

public class UserDao {
	private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public UserDao() {
        this.connection = connection;
    }

    public User getUser(int userId) {
        User user = null;
        String query = "SELECT * FROM user WHERE id = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int phone = resultSet.getInt("phone");
                String address = resultSet.getString("address");
                // Timestamp createDate = resultSet.getTimestamp("createDate");
                
                user = new User(id, username, password, name, email, phone, address, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return user;
    }

    private void closeResources() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
