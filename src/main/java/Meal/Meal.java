/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Meal;

import Nutrition.FoodNutrition;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author alift
 */
public class Meal {
    private List<Recipe> recipes;

    public Meal() {
        recipes = new ArrayList<Recipe>();
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
    
    public void addRecipeInput(){
        String recipeName, ingredient, tool, step;
        int calories, protein, carbs, fat;
        FoodNutrition foodNutrition;
        List<String> ingredients = new ArrayList<String>();
        List<String> tools = new ArrayList<String>();
        List<String> steps = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("CREATE RECIPE\n");
        
        System.out.print("Enter recipe name: ");
        recipeName = scanner.nextLine();
        
        System.out.println("Enter the required ingredients (enter '-' if done): ");
        ingredient = scanner.nextLine();
        while(!ingredient.equals("-")){
            ingredients.add(ingredient);
            ingredient = scanner.nextLine();
        }
        
        System.out.println("\nEnter the required tools (enter '-' if done): ");
        tool = scanner.nextLine();
        while(!tool.equals("-")){
            tools.add(tool);
            tool = scanner.nextLine();
        }
        
        System.out.println("\nEnter the required steps (enter '-' if done): ");
        step = scanner.nextLine();
        while(!step.equals("-")){
            steps.add(step);
            step = scanner.nextLine();
        }
        
        System.out.print("\nEnter the calories: ");
        calories = scanner.nextInt();
        System.out.print("Enter the proteins: ");
        protein = scanner.nextInt();
        System.out.print("Enter the carbohydrates: ");
        carbs = scanner.nextInt();
        System.out.print("Enter the fats: ");
        fat = scanner.nextInt();
        
        foodNutrition = new FoodNutrition();
        foodNutrition.setNutrition(calories, protein, carbs, fat);
        Recipe recipe = new Recipe(recipeName, foodNutrition, ingredients, tools, steps);
        recipes.add(recipe);
    }
    
    public void addRecipeNoInput(String recipeName, int calories, int protein, int carbs, int fat, List<String> ingredients, List<String> tools, List<String> steps){
        FoodNutrition foodNutrition = new FoodNutrition();
        foodNutrition.setNutrition(calories, protein, carbs, fat);
        Recipe recipe = new Recipe(recipeName, foodNutrition, ingredients, tools, steps);
        recipes.add(recipe);
    }
    
    public void removeRecipe(Recipe recipe){
        recipes.removeIf(element -> element.getRecipeName().equals(recipe.getRecipeName()));
    }
    
    public Recipe searchRecipe(String recipeName){
        Recipe searchedRecipe = null;
        boolean searchStatus = false;
        for(Recipe recipe: recipes){
            if(recipe.getRecipeName().equals(recipeName)){
                searchedRecipe = recipe;
                searchStatus = true;
                break;
            }
        }
        if(searchStatus == false){
            System.out.println("Recipe not found");
        }
        return searchedRecipe;
    }

    public void showRecipeName(){
        for(Recipe recipe: recipes){
            System.out.println(recipe.getRecipeName());
        }
    }

    public List<Recipe> showRecipeNameBasedOnCalories(Meal meal, int cal) {
        List<Recipe> allRecipe = new ArrayList<Recipe>();

        allRecipe.addAll(recipes);
        allRecipe.addAll(meal.getRecipes());

        List<Recipe> temp = allRecipe.stream()
                .filter(e -> e.getCalories() <= cal)
                .collect(Collectors.toList());

        int i=1;
        for(Recipe r: temp){
            System.out.printf("%d. %s\n",i,r.getRecipeName());
            i++;
        }

        return temp;
    }
}
