/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Schedule;

import DAO.*;
import Meal.Recipe;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alift
 */
public class Schedule {
    
    private String scheduleId;
    private String date;
    private String userId;
    private List<Recipe> recipes;

    public Schedule(String date) {
        int lastScheduleId = ScheduleDao.getLatestScheduleId();
        if(lastScheduleId < 100){
            lastScheduleId = 100;
        }
        lastScheduleId++;
        scheduleId = String.format("sche%03d", lastScheduleId);
        
        this.date = date;
        recipes = new ArrayList<Recipe>();
    }

    public Schedule(String scheduleId, String date) {
        int lastScheduleId = ScheduleDao.getLatestScheduleId();
        if(lastScheduleId < 100){
            lastScheduleId = 100;
        }
        lastScheduleId++;
        scheduleId = String.format("sche%03d", lastScheduleId);
        
        this.scheduleId = scheduleId;
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }
    
    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDate() {
        return date;
    }
    
    public void addRecipe(Recipe recipe){
        recipes.add(recipe);
    }
    
    public void removeRecipe(Recipe recipe){
        recipes.removeIf(element -> element.getRecipeName().equals(recipe.getRecipeName()));
    }
    
    public void clearRecipe(){
        recipes.clear();
    }
    
    public Recipe searchRecipeInSchedule(String recipeName){
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
    
    public void showScheduleRecipeName(){
        boolean isNull = false;
        for(Recipe recipe: recipes){
            if(recipe == null){
                isNull = true;
                break;
            }
            recipe.showRecipeList();
            System.out.println();
        }
        if(isNull){
            System.out.println("Recipe empty");
        }
    }
    
    public void showScheduleDetails(){
        for(Recipe recipe: recipes){
            recipe.showRecipeDetails();
            System.out.println();
        }
    }
}
