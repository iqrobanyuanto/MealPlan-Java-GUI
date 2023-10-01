/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Schedule;

import Meal.Meal;
import Meal.Recipe;
import Nutrition.UserNutrition;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author alift
 */
public class ScheduleList {
    
    private List<Schedule> schedules;
    private Schedule temp;
    private Scanner scanner = new Scanner(System.in);
    
    public ScheduleList() {
        schedules = new ArrayList<Schedule>();
    }
    
    public void addSchedule(Meal meal, UserNutrition userNutrition, String date, String search){
        
        boolean isExist = false;
        int i=0;
        

        
        Recipe recipe = meal.searchRecipe(search);
        userNutrition.caloriesDecrease(recipe.getCalories());
        
        for(Schedule schedule: schedules){
            if(schedule.getDate().equals(date)){
                isExist = true;
                break;
            }
            i++;
        }
        
        if(isExist){
            temp = schedules.get(i);
            temp.addRecipe(recipe);
            System.out.println("Add recipe successful");
        }else{
            temp = new Schedule(date);
            temp.addRecipe(recipe);
            schedules.add(temp);
            System.out.println("Add schedule successful");
        }
    }
    
    public Schedule searchSchedule(String date){
        Schedule searchedSchedule = null;
        Boolean isFind = false;
        
        for(Schedule schedule:schedules){
            if(schedule.getDate().equals(date)){
                searchedSchedule = schedule;
                isFind = true;
                break;
            }
        }
        
        if(isFind==false){
            System.out.println("Schedule not found");
        }
        
        return searchedSchedule;
    }
    
    public void removeSchedule(){
        System.out.print("Enter schedule date to delete: ");
        String date = scanner.nextLine();
        
        Schedule searchedSchedule = searchSchedule(date);        
        schedules.remove(searchedSchedule);
    }
    
    public void removeScheduleRecipe(){
        String date, recipeName;
        
        System.out.print("Enter schedule date: ");
        date = scanner.nextLine();
        System.out.print("Enter recipe name to remove: ");
        recipeName = scanner.nextLine();
        
        Schedule searchedSchedule = searchSchedule(date);
        searchedSchedule.removeRecipe(searchedSchedule.searchRecipeInSchedule(recipeName));
    }
    
    public void showScheduleRecipeName(){
        for (Schedule schedule: schedules){
            System.out.printf("Date: %s\n",schedule.getDate());
            schedule.showScheduleRecipeName();
        }
    }
    
    public void showScheduleDetails(){
        for (Schedule schedule: schedules){
            System.out.printf("Date: %s",schedule.getDate());
            schedule.showScheduleDetails();
        }
    }
    
}
