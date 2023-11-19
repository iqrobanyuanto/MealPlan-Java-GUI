/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Database.Database;
import Meal.Meal;
import Meal.Recipe;
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
    public List<String> getScheduleMeal(String userId, String date) {
        List<String> mealNames = new ArrayList<>();

        try {
            String query = "SELECT meal.meal_name FROM schedule " +
                           "JOIN meal ON schedule.meal_id = meal.meal_id " +
                           "WHERE schedule.date = ? AND schedule.user_id = ?";
            PreparedStatement statement = Database.getConnection().prepareStatement(query);
            statement.setString(1, date);
            statement.setString(2, userId);

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
    public List<Recipe> getScheduleRecipe(String userId, String date) {
        List<Recipe> scheduleMeals = new ArrayList<>();

        try {
            String query = "SELECT m.meal_id, m.meal_name, m.ingredients, m.tools, m.steps, m.calories, m.proteins, m.carbohydrates, m.fats " +
                           "FROM meal m " +
                           "INNER JOIN schedule s ON m.meal_id = s.meal_id " +
                           "WHERE s.user_id = ? AND s.date = ?";
            PreparedStatement statement = Database.getConnection().prepareStatement(query);
            statement.setString(1, userId);
            statement.setString(2, date);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String mealId = resultSet.getString("meal_id");
                String mealName = resultSet.getString("meal_name");
                String ingredients = resultSet.getString("ingredients");
                String tools = resultSet.getString("tools");
                String steps = resultSet.getString("steps");
                int calories = resultSet.getInt("calories");
                int proteins = resultSet.getInt("proteins");
                int carbohydrates = resultSet.getInt("carbohydrates");
                int fats = resultSet.getInt("fats");

                Recipe recipe = new Recipe(mealId, mealName, ingredients, tools, steps, calories, proteins, carbohydrates, fats);
                scheduleMeals.add(recipe);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return scheduleMeals;
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
    public void deleteScheduleMeal(String userId, String date, String mealId) {
        try {
            String query = "DELETE FROM schedule WHERE user_id = ? AND date = ? AND meal_id = ? LIMIT 1";
            PreparedStatement statement = Database.getConnection().prepareStatement(query);
            statement.setString(1, userId);
            statement.setString(2, date);
            statement.setString(3, mealId);

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean isExist(String date){
        try {
            String query = "SELECT schedule_id FROM schedule WHERE date = ?";
            PreparedStatement statement = Database.getConnection().prepareStatement(query);
            statement.setString(1, date);

            ResultSet resultSet = statement.executeQuery();
            boolean scheduleExists = resultSet.next();

            resultSet.close();
            statement.close();

            return scheduleExists;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public static int getLatestScheduleId(){
        String query = "SELECT schedule_id FROM schedule ORDER BY CAST(SUBSTRING(schedule_id, 5) AS UNSIGNED) DESC LIMIT 1";
        int latestScheduleId = 0;

        try (PreparedStatement statement = Database.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String mealId = resultSet.getString("schedule_id");
                String numericPart = mealId.substring(4);
                latestScheduleId = Integer.parseInt(numericPart);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return latestScheduleId;
    }
}
