/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Database.Database;
import Meal.Meal;
import Schedule.Schedule;
import Schedule.ScheduleMeal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alift
 */
public class ScheduleDao implements ScheduleDaoInterface{

    @Override
    public void createSchedule(String userId, Schedule schedule, String mealName) {
        try {
            String mealIdQuery = "SELECT meal_id FROM meal WHERE meal_name = ?";
            PreparedStatement mealIdStatement = Database.getConnection().prepareStatement(mealIdQuery);
            mealIdStatement.setString(1, mealName);
            ResultSet mealIdResult = mealIdStatement.executeQuery();

            String mealId = null;
            if (mealIdResult.next()) {
                mealId = mealIdResult.getString("meal_id");
            }

            mealIdResult.close();
            mealIdStatement.close();

            if (mealId != null) {
                String insertQuery = "INSERT INTO schedule (schedule_id, date, user_id, meal_id) VALUES (?, ?, ?, ?)";
                PreparedStatement insertStatement = Database.getConnection().prepareStatement(insertQuery);
                insertStatement.setString(1, schedule.getScheduleId());
                insertStatement.setString(2, schedule.getDate());
                insertStatement.setString(3, userId);
                insertStatement.setString(4, mealId);
                insertStatement.executeUpdate();

                insertStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getScheduleDate(String userId) {
        List<String> dates = new ArrayList<>();

        try {
            String query = "SELECT DISTINCT date FROM schedule WHERE user_id = ?";
            PreparedStatement statement = Database.getConnection().prepareStatement(query);
            statement.setString(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String date = resultSet.getString("date");
                dates.add(date);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dates;
    }

    @Override
    public List<String> getScheduleMeal(String date) {
        List<String> mealNames = new ArrayList<>();

        try {
            String query = "SELECT meal.meal_name FROM schedule " +
                           "JOIN meal ON schedule.meal_id = meal.meal_id " +
                           "WHERE schedule.date = ?";
            PreparedStatement statement = Database.getConnection().prepareStatement(query);
            statement.setString(1, date);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String mealName = resultSet.getString("meal_name");
                mealNames.add(mealName);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mealNames;
    }

    @Override
    public void updateScheduleMeal(String userId, String date, String mealName) {
        
    }

    @Override
    public void deleteSchedule(String userId, String date) {
        try {
            String query = "DELETE FROM schedule WHERE date = ?";
            PreparedStatement statement = Database.getConnection().prepareStatement(query);
            statement.setString(1, date);

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteScheduleMeal(String userId, String date, String mealName) {
        try {
            String query = "DELETE FROM schedulemeal WHERE user_id = ? AND schedule_id = ? AND meal_id = ?";
            PreparedStatement statement = Database.getConnection().prepareStatement(query);
            statement.setString(1, userId);
            statement.setString(2, date);
            statement.setString(3, mealName);

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
