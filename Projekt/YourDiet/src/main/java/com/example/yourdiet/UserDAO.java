package com.example.yourdiet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends BaseDAO{

    UserDAO(Connection connection){
        super(connection);
    }

     boolean verifyLogin(String email, String password) throws SQLException {
        String query = "SELECT password FROM users WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                return password.equals(storedPassword);
            }
            return false;
        }
    }

    boolean createUser(String email, String password, String name) throws SQLException {
        String query = "INSERT INTO `users` (`name`, `email`, `password`) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    String getUserName(String email) throws SQLException {
        String query = "SELECT name from users WHERE email=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,email);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return resultSet.getString("name");
        } else {
            return null;
        }
    }

    void setDetails(String email) throws SQLException {
        String query = "INSERT INTO users_details (user_email, calorie_goal) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,email);
        preparedStatement.setDouble(2,0);

        preparedStatement.executeUpdate();
    }




    int getCalorieGoal(String email) throws SQLException {
        String query = "SELECT calorie_goal FROM users_details WHERE user_email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,email);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            return resultSet.getInt("calorie_goal");
        }else{
            return 0;
        }
    }

    void updateDetails(String email, int calorieGoal) throws SQLException {
        String query = "UPDATE users_details SET calorie_goal = ? WHERE user_email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,calorieGoal);
        preparedStatement.setString(2,email);

        preparedStatement.executeUpdate();
    }

}
