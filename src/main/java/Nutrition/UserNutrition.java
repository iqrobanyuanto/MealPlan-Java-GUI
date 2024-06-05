/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Nutrition;

import User.User;

/**
 *
 * @author alift
 */
public class UserNutrition extends Nutrition {

    @Override
    public void setNutrition() { }

    public void setNutrition(User user) {
        this.calories = (int) caloriesNeeded(user);
        this.protein = (int) proteinNeeded(user);
        this.carbs = (int) carbsNeeded(user);
        this.fat = (int) fatNeeded(user);
    }

    @Override
    public void displayNutrition() {
        System.out.println("\nNutrition needed: ");
        System.out.printf("Calories: %d\n",this.calories);
        System.out.printf("Protein: %d\n",this.protein);
        System.out.printf("Carbs: %d\n",this.carbs);
        System.out.printf("Fat: %d\n",this.fat);
    }

    private double caloriesNeeded(User user){
        double calories;
        double weight = (double) user.getWeight() * 2.20462;
        double height = (double) user.getHeight() * 0.393701;
        if(user.getGender().equals("Male")){
            calories = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * user.getAge());
        }else{
            calories = 447.593 + (9.247 * weight) + (3.098 * height) - (6.8 * user.getAge());
        }
        return calories;
    }

    private double proteinNeeded(User user){
        double weight = (double) user.getWeight() * 2.20462;//new comment
        return weight*2.2;//new code here
    }

    private double fatNeeded(User user){
        double fatPercentage;
        if(user.getAge() >= 19 && user.getAge() <= 30){
            fatPercentage = 0.25;
        }else if(user.getAge() >= 31 && user.getAge() <= 50){
            fatPercentage = 0.30;
        }else{
            fatPercentage = 0.35;
        }
        double caloriesNeeded = caloriesNeeded(user);
        double fatNeeded = (caloriesNeeded * fatPercentage) / 9;
        return fatNeeded;
    }

    private double carbsNeeded(User user){
        double proteinNeeded = proteinNeeded(user);
        double fatNeeded = fatNeeded(user);
        double carbsNeeded = (caloriesNeeded(user) - (proteinNeeded*4) - (fatNeeded*9))/4;
        return carbsNeeded;
    }

    public void caloriesDecrease(int cal){
        this.calories -= cal;
    }
    
    public void proteinDecrease(int pro){
        this.protein -= pro;
    }
    
    public void carboDec(int carb){
        this.carbs -= carb;
    }
    
    public void fatDecrease(int fat){
        this.fat -= fat;
    }

    public int getCal(){
        return this.calories;
    }

    public int getProtein() {
        return this.protein;
    }

    public int getCarbs() {
        return this.carbs;
    }

    public int getFat() {
        return this.fat;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }
}
