/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Database.Database;
import User.User;
import java.sql.*;

/**
 *
 * @author alift
 */
public class UserDao implements UserDaoInterface{
    User user;
    
    @Override
    public void createUser(User user){
        String query = "INSERT INTO user (user_id, name, username, email, password, gender, age, height, weight) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = Database.getConnection().prepareStatement(query)) {
            statement.setString(1, user.getUserId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getGender());
            statement.setInt(7, user.getAge());
            statement.setInt(8, user.getHeight());
            statement.setInt(9, user.getWeight());

            statement.executeUpdate();
            System.out.println("User inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByUsername(String username) {
        String query = "SELECT * FROM user WHERE username = ?";
        User user = null;

        try (PreparedStatement statement = Database.getConnection().prepareStatement(query)) {
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getString("user_id"));
                user.setName(resultSet.getString("name"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setGender(resultSet.getString("gender"));
                user.setAge(resultSet.getInt("age"));
                user.setHeight(resultSet.getInt("height"));
                user.setWeight(resultSet.getInt("weight"));
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void updateUser(String username, String name, String email, String gender, int age, int height, int weight) {
        String query = "UPDATE user SET name = ?, email = ?, gender = ?, age = ?, height = ?, weight = ? WHERE username = ?";

        try (PreparedStatement statement = Database.getConnection().prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, gender);
            statement.setInt(4, age);
            statement.setInt(5, height);
            statement.setInt(6, weight);
            statement.setString(7, username);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User updated successfully!");
            } else {
                System.out.println("User not found or no changes were made.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        String query = "DELETE FROM user WHERE username = ?";

        try (PreparedStatement statement = Database.getConnection().prepareStatement(query)) {
            statement.setString(1, user.getUsername());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User deleted successfully!");
            } else {
                System.out.println("User not found or no changes were made.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private boolean isExist(String username){
        String query = "SELECT COUNT(*) FROM user WHERE username = ?";

        try (PreparedStatement statement = Database.getConnection().prepareStatement(query)) {
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    @Override
    public boolean isMatch(String username, String password){
        String query = "SELECT COUNT(*) FROM user WHERE username = ? AND password = ?";

        try (PreparedStatement statement = Database.getConnection().prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public static int getLatestUserId(){
        String query = "SELECT user_id FROM user ORDER BY CAST(SUBSTRING(user_id, 5) AS UNSIGNED) DESC LIMIT 1";
        int latestUserId = 0;

        try (PreparedStatement statement = Database.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String userId = resultSet.getString("user_id");
                String numericPart = userId.substring(4);
                latestUserId = Integer.parseInt(numericPart);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return latestUserId;
    }
}
