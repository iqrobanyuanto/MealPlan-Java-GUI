/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Meal;

import DAO.MealDao;
import Nutrition.FoodNutrition;
import java.util.List;

/**
 *
 * @author alift
 */
public class Recipe {
    private String mealId;
    private String recipeName;
    private String ingredientStr;
    private String toolStr;
    private String stepStr;
    private FoodNutrition foodNutrition;
    private List<String> ingredients;
    private List<String> tools;
    private List<String> steps;

    public Recipe(){
        int lastMealId = MealDao.getLatestMealId();
        lastMealId++;
        mealId = String.format("meal%03d", lastMealId);
        
        foodNutrition = new FoodNutrition();
    }

    public Recipe(String mealId, String recipeName, String ingredientStr, String toolStr, String stepStr, int cal, int pro, int car, int fat) {
        foodNutrition = new FoodNutrition();
        
        this.mealId = mealId;
        this.recipeName = recipeName;
        this.ingredientStr = ingredientStr;
        this.toolStr = toolStr;
        this.stepStr = stepStr;
        setCalories(cal);
        setProteins(pro);
        setCarbohydrates(car);
        setFats(fat);
    }
    
    

    public Recipe(String recipeName, String ingredientStr, String toolStr, String stepStr, int cal, int pro, int car, int fat) {
        int lastMealId = MealDao.getLatestMealId();
        lastMealId++;
        mealId = String.format("meal%03d", lastMealId);
        
        foodNutrition = new FoodNutrition();
        
        this.recipeName = recipeName;
        this.ingredientStr = ingredientStr;
        this.toolStr = toolStr;
        this.stepStr = stepStr;
        setCalories(cal);
        setProteins(pro);
        setCarbohydrates(car);
        setFats(fat);
    }
    
    public Recipe(String recipeName, FoodNutrition foodNutrition, List<String> ingredients, List<String> tools, List<String> steps) {
        int lastMealId = MealDao.getLatestMealId();
        lastMealId++;
        mealId = String.format("meal%03d", lastMealId);
        
        this.recipeName = recipeName;
        this.foodNutrition = foodNutrition;
        this.ingredients = ingredients;
        this.tools = tools;
        this.steps = steps;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getMealId() {
        return mealId;
    }
    
    public void setIngredientStr(String ingredientStr) {
        this.ingredientStr = ingredientStr;
    }

    public void setToolStr(String toolStr) {
        this.toolStr = toolStr;
    }

    public void setStepStr(String stepStr) {
        this.stepStr = stepStr;
    }
    
    public void setCalories(int cal){
        foodNutrition.setCalories(cal);
    }
    
    public void setProteins(int pro){
        foodNutrition.setProtein(pro);
    }
    
    public void setCarbohydrates(int car){
        foodNutrition.setCarbs(car);
    }
    
    public void setFats(int fat){
        foodNutrition.setFat(fat);
    }
    
    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
    
    public void showRecipeList(){
        System.out.printf("- %s",recipeName);
    }
    
    public void showRecipeDetails(){
        System.out.printf("Name: %s\n\n",recipeName);
        System.out.println("Ingredients:");
        int i=1;
        for(String ingredient: ingredients){
            System.out.printf("%d: %s\n", i, ingredient);
            i++;
        }
        System.out.println("\nTools:");
        i=1;
        for(String tool: tools){
            System.out.printf("%d: %s\n",i,tool);
            i++;
        }
        System.out.println("\nStep:");
        i=1;
        for(String step: steps){
            System.out.printf("%d: %s\n", i, step);
            i++;
        }
        foodNutrition.displayNutrition();
    }

    public int getCalories(){
        return foodNutrition.getCalories();
    }
    
    public int getProteins(){
        return foodNutrition.getProtein();
    }
    
    public int getCarbs(){
        return foodNutrition.getCarbs();
    }
    
    public int getFats(){
        return foodNutrition.getFat();
    }

    public String getIngredientStr() {
        return ingredientStr;
    }

    public String getToolStr() {
        return toolStr;
    }

    public String getStepStr() {
        return stepStr;
    }
    
    
}
