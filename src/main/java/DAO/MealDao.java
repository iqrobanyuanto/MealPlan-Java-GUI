/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import User.User;
import java.util.List;
import Database.Database;
import Meal.Recipe;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author alift
 */
public class MealDao implements MealDaoInterface{

    @Override
    public void createMeal(String userId, Recipe recipe) {
        try {
            String query;
            PreparedStatement statement;

            if (userId == null) {
                query = "INSERT INTO meal (meal_id, meal_name, ingredients, tools, steps, calories, proteins, carbohydrates, fats) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                statement = Database.getConnection().prepareStatement(query);
            } else {
                query = "INSERT INTO meal (meal_id, meal_name, ingredients, tools, steps, calories, proteins, carbohydrates, fats, user_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                statement = Database.getConnection().prepareStatement(query);
                statement.setString(10, userId);
            }

            statement.setString(1, recipe.getMealId());
            statement.setString(2, recipe.getRecipeName());
            statement.setString(3, recipe.getIngredientStr());
            statement.setString(4, recipe.getToolStr());
            statement.setString(5, recipe.getStepStr());
            statement.setInt(6, recipe.getCalories());
            statement.setInt(7, recipe.getProteins());
            statement.setInt(8, recipe.getCarbs());
            statement.setInt(9, recipe.getFats());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Recipe getMeal(String userId, String mealName) {
        Recipe meal = null;

        try {
            String query;
            PreparedStatement statement;

            if (userId == null) {
                query = "SELECT meal_id, meal_name, ingredients, tools, steps, calories, proteins, carbohydrates, fats FROM meal WHERE meal_name = ?";
                statement = Database.getConnection().prepareStatement(query);
                statement.setString(1, mealName);
            } else if (mealName == null) {
                query = "SELECT meal_id, meal_name, ingredients, tools, steps, calories, proteins, carbohydrates, fats FROM meal WHERE user_id = ?";
                statement = Database.getConnection().prepareStatement(query);
                statement.setString(1, userId);
            } else {
                query = "SELECT meal_id, meal_name, ingredients, tools, steps, calories, proteins, carbohydrates, fats FROM meal WHERE meal_name = ? AND user_id = ?";
                statement = Database.getConnection().prepareStatement(query);
                statement.setString(1, mealName);
                statement.setString(2, userId);
            }

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                meal = new Recipe();
                meal.setMealId(resultSet.getString("meal_id"));
                meal.setRecipeName(resultSet.getString("meal_name"));
                meal.setIngredientStr(resultSet.getString("ingredients"));
                meal.setToolStr(resultSet.getString("tools"));
                meal.setStepStr(resultSet.getString("steps"));
                meal.setCalories(resultSet.getInt("calories"));
                meal.setProteins(resultSet.getInt("proteins"));
                meal.setCarbohydrates(resultSet.getInt("carbohydrates"));
                meal.setFats(resultSet.getInt("fats"));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return meal;
    }

    @Override
    public List<String> getMealName(String userId) {
        List<String> mealNames = new ArrayList<>();

        try {
            String query;
            PreparedStatement statement;

            if (userId == null) {
                query = "SELECT meal_name FROM meal WHERE user_id IS NULL";
                statement = Database.getConnection().prepareStatement(query);
            } else {
                query = "SELECT meal_name FROM meal WHERE user_id = ?";
                statement = Database.getConnection().prepareStatement(query);
                statement.setString(1, userId);
            }

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
    public List<String> getMealNameByNutrition(String userId, int calories) {
        List<String> mealNames = new ArrayList<>();

        try {
            String query;
            PreparedStatement statement;

            if (userId == null) {
                query = "SELECT meal_name FROM meal WHERE user_id IS NULL AND calories < ?";
                statement = Database.getConnection().prepareStatement(query);
                statement.setInt(1, calories);
            } else {
                query = "SELECT meal_name FROM meal WHERE user_id = ? AND calories < ?";
                statement = Database.getConnection().prepareStatement(query);
                statement.setString(1, userId);
                statement.setInt(2, calories);
            }
            
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
    public void updateMeal(String userId, Recipe recipe) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteMeal(String userId, String recipeName) {
        try {
            String query;
            PreparedStatement statement;

            if (userId == null) {
                query = "DELETE FROM meal WHERE meal_name = ?";
                statement = Database.getConnection().prepareStatement(query);
            } else {
                query = "DELETE FROM meal WHERE meal_name = ? AND user_id = ?";
                statement = Database.getConnection().prepareStatement(query);
                statement.setString(2, userId);
            }

            statement.setString(1, recipeName);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static int getLatestMealId(){
        String query = "SELECT meal_id FROM meal ORDER BY CAST(SUBSTRING(meal_id, 5) AS UNSIGNED) DESC LIMIT 1";
        int latestMealId = 0;

        try (PreparedStatement statement = Database.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String mealId = resultSet.getString("meal_id");
                String numericPart = mealId.substring(4);
                latestMealId = Integer.parseInt(numericPart);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return latestMealId;
    }
}
