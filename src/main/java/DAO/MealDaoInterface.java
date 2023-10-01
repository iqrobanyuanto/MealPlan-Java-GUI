/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Meal.*;
import User.User;
import java.util.List;

/**
 *
 * @author alift
 */
public interface MealDaoInterface {
    public void createMeal(String userId, Recipe recipe);
    public Recipe getMeal(String userId, String mealName);
    public List<String> getMealName(String userId);
    public void updateMeal(String userId, Recipe recipe);
    public void deleteMeal(String userId, String recipeName);
}
