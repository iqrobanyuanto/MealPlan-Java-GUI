/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

import Interface.Loginable;
import Meal.Meal;
import Meal.Recipe;
import java.util.Scanner;

/**
 *
 * @author alift
 */
public class Admin implements Loginable{
    private final String username = "admin";
    private final String password = "admin";
    private boolean loginStatus = false;
    private Scanner scanner = new Scanner(System.in);
    
    public Admin(){}
    
    public boolean login(){
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if(this.username.equals(username)&&this.password.equals(password)){
            System.out.println("Admin login successfull");
            loginStatus = true;
            return true;
        }else{
            System.out.println("Admin login failed");
            return false;
        }
    }
    
    public boolean login(String username, String password){
        return true;
    }
    
    public void register(){
        System.out.println("Registration not allowed!");
    }
    
    public void searchRecipe(Meal meal){
        System.out.print("Enter recipe name to search: ");
        String search = scanner.nextLine();
        if(meal.searchRecipe(search)!=null){
            meal.searchRecipe(search).showRecipeDetails();
        }
    }
    
    public void addRecipe(Meal meal){
        meal.addRecipeInput();
    }
    
    public void deleteRecipe(Meal meal){
        System.out.println("Enter recipe name to delete: ");
        String search = scanner.nextLine();
        Recipe remove = meal.searchRecipe(search);
        if(remove != null){
            meal.removeRecipe(remove);
        }
    }

    public void showRecipe(Meal meal){
        System.out.println("RECIPES\n");
        meal.showRecipeName();
    }
}
