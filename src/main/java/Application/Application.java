/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application;

import Meal.Meal;
import User.Admin;
import User.User;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author alift
 */
public class Application {
    private Meal dataMeal = new Meal();
    private List<User> userList = new ArrayList<User>();
    Admin admin = new Admin();
    User user = new User();
    
    public void clearConsole() {
        try {
            Robot rob = new Robot();
            rob.keyPress(KeyEvent.VK_CONTROL); // press "CTRL"
            rob.keyPress(KeyEvent.VK_L); // press "L"
            rob.keyRelease(KeyEvent.VK_L); // unpress "L"
            rob.keyRelease(KeyEvent.VK_CONTROL); // unpress "CTRL"
            Thread.sleep(1000); // add delay in milisecond, if not there will automatically stop after clear
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    
    public void menu(){
        newData();
        Scanner scanner = new Scanner(System.in);
        int mainChoice = -1;
        int homeChoice = -1;
        int profileChoice = -1;
        int profileSettingsChoice = -1;
        int scheduleChoice = -1;
        int recipeChoice = -1;
        int nutritionChoice = -1;
        int adminHomeChoice = -1;
        int createScheduleChoice = -1;
        
        while(mainChoice != 0){
            mainMenu();
            mainChoice = scanner.nextInt();
            
            switch(mainChoice){
                case 1:
                    user = new User();
                    user.register();
                    user.setNutrition(user);
                    break;
                case 2:
                    if(!user.login()){
                        break;
                    }
                    
                    while(homeChoice != 0){
                        homeMenu();
                        homeChoice = scanner.nextInt();
                        
                        switch(homeChoice){
                            case 1:
                                while(profileChoice != 0){
                                    profileMenu();
                                    profileChoice = scanner.nextInt();
                                    
                                    switch(profileChoice){
                                        case 1:
                                            while(profileSettingsChoice != 0){
                                                profileSettingsMenu();
                                                profileSettingsChoice = scanner.nextInt();
                                                
                                                switch(profileSettingsChoice){
                                                    case 1:
                                                        user.changeUsername();
                                                        break;
                                                    case 2:
                                                        user.changePassword();
                                                        break;
                                                    case 3:
                                                        user.changeName();
                                                        break;
                                                    case 4:
                                                        user.changeEmail();
                                                        break;
                                                    case 5:
                                                        user.changeGender();
                                                        break;
                                                    case 6:
                                                        user.changeAge();
                                                        break;
                                                    case 7:
                                                        user.changeHeightWeight();
                                                        break;
                                                    case 0:
                                                        break;
                                                    default:
                                                        System.out.println("Invalid");
                                                        break;
                                                }
                                            }
                                            profileSettingsChoice = -1;
                                        case 2:
                                            profileChoice = 0;
                                            break;
                                        case 0:
                                            break;
                                        default:
                                            System.out.println("Invalid");
                                    }
                                }
                                profileChoice = -1;
                                break;
                            case 2:
                                while(scheduleChoice != 0){
                                    scheduleMenu();
                                    scheduleChoice = scanner.nextInt();
                                    
                                    switch(scheduleChoice){
                                        case 1:
                                            while(createScheduleChoice != 0){
                                                createScheduleMenu();
                                                createScheduleChoice = scanner.nextInt();

                                                switch(createScheduleChoice){
                                                    case 1:
                                                        user.createScheduleCal(dataMeal);
                                                        break;
                                                    case 2:
                                                        user.createSchedule(dataMeal);
                                                        break;
                                                    case 0:
                                                        break;
                                                    default:
                                                        System.out.println("Invalid");
                                                }
                                            }
                                            createScheduleChoice = -1;
                                            break;
                                        case 2:
                                            user.createSchedule(dataMeal);
                                            break;
                                        case 3:
                                            user.removeSchedule();
                                            break;
                                        case 4:
                                            user.removeScheduleRecipe();
                                            break;
                                        case 5:
                                            user.showScheduleRecipeName();
                                            break;
                                        case 6:
                                            user.showScheduleDetails();
                                            break;
                                        case 0:
                                            break;
                                        default:
                                            System.out.println("Invalid");
                                            break;
                                    }
                                }
                                scheduleChoice = -1;
                                break;
                            case 3:

                                while(nutritionChoice != 0){
                                    nutritionMenu();
                                    nutritionChoice = scanner.nextInt();

                                    switch (nutritionChoice){
                                        case 1:
                                            user.displayNutritionNeed();
                                            break;
                                        case 0:
                                            break;
                                        default:
                                            System.out.println("Invalid");
                                            break;
                                    }
                                }
                                nutritionChoice = -1;
                                break;
                            case 4:
                                while(recipeChoice != 0){
                                    recipeMenu();
                                    recipeChoice = scanner.nextInt();

                                    switch(recipeChoice){
                                        case 1:
                                            user.addRecipe();
                                            break;
                                        case 2:
                                            user.searchRecipe(dataMeal);
                                            break;
                                        case 3:
                                            user.showRecipe(dataMeal);
                                            break;
                                        case 4:
                                            user.removeRecipe();
                                            break;
                                        case 0:
                                            break;
                                        default:
                                            System.out.println("Invalid");
                                            break;
                                    }
                                }
                                recipeChoice = -1;
                                break;
                            case 0:
                                System.out.println("Exiting program");
                                break;
                            default:
                                System.out.println("Invalid");
                                break;
                        }
                    }
                    homeChoice = -1;
                    break;
                case 3:
                    if(!admin.login()){
                        break;
                    }
                    while(adminHomeChoice != 0){
                        adminHomeMenu();
                        adminHomeChoice = scanner.nextInt();

                        switch (adminHomeChoice){
                            case 1:
                                admin.addRecipe(dataMeal);
                                break;
                            case 2:
                                admin.searchRecipe(dataMeal);
                                break;
                            case 3:
                                admin.deleteRecipe(dataMeal);
                                break;
                            case 4:
                                admin.showRecipe(dataMeal);
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Invalid");
                                break;
                        }
                    }
                    adminHomeChoice = -1;
                    break;
                default:
                    System.out.println("Invalid");
                    break;                    
            }
        }
    }
    
    private void mainMenu(){
        System.out.println("\nMENU");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Admin");
        System.out.println("0. Exit");
        System.out.print("\nEnter your choice: ");
    }
    
    private void homeMenu(){
        System.out.println("\nHOME");
        System.out.println("1. Profile");
        System.out.println("2. Schedule");
        System.out.println("3. Nutrition");
        System.out.println("4. Recipe");
        System.out.println("0. Logout");
        System.out.print("\nEnter your choice: ");
    }
    
    private void profileMenu(){
        System.out.println("\nPROFILE");
        user.showProfile();
        System.out.println("1. Settings");
        System.out.println("2. Logout");
        System.out.println("0. Back");
        System.out.print("\nEnter your choice: ");
    }
    
    private void profileSettingsMenu(){
        System.out.println("\nPROFILE SETTINGS");
        System.out.println("1. Change Username");
        System.out.println("2. Change Password");
        System.out.println("3. Change Name");
        System.out.println("4. Change E-mail");
        System.out.println("5. Change Gender");
        System.out.println("6. Change Age");
        System.out.println("7. Change Height & Weight");
        System.out.println("0. Back");
        System.out.print("\nEnter your choice: ");
    }
    
    private void scheduleMenu(){
        System.out.println("\nMEAL SCHEDULE");
        System.out.println("1. Create schedule");
        System.out.println("2. Add recipe");
        System.out.println("3. Remove schedule");
        System.out.println("4. Remove recipe in schedule");
        System.out.println("5. Show schedule");
        System.out.println("6. Show schedule with recipe details");
        System.out.println("0. Back");
        System.out.print("\nEnter your choice: ");
    }

    private void createScheduleMenu(){
        System.out.println("\nCREATE SCHEDULE");
        System.out.println("1. Choose meal based on calories");
        System.out.println("2. Input meal");
        System.out.println("0. Back");
        System.out.print("\nEnter your choice: ");
    }

    public void recipeMenu(){
        System.out.println("\nRECIPE");
        System.out.println("1. Create meal recipe");
        System.out.println("2. Search meal recipe");
        System.out.println("3. Show meal recipe");
        System.out.println("4. Remove meal recipe");
        System.out.println("0. Back");
        System.out.print("\nEnter your choice: ");
    }

    public void nutritionMenu(){
        System.out.println("\nNUTRITION");
        System.out.println("1. Nutrition needs");
        System.out.println("0. Back");
        System.out.print("\nEnter your choice: ");
    }

    public void adminHomeMenu(){
        System.out.println("\nADMIN HOME MENU");
        System.out.println("1. Add recipe");
        System.out.println("2. Search recipe");
        System.out.println("3. Remove recipe");
        System.out.println("4. Show recipe");
        System.out.println("0. Logout");
        System.out.print("\nEnter your choice: ");
    }
    
    public void newData(){
        
        List<String> ingredients1 = new ArrayList<String>();
        List<String> tools1 = new ArrayList<String>();
        List<String> steps1 = new ArrayList<String>();
        List<String> ingredients2 = new ArrayList<String>();
        List<String> tools2 = new ArrayList<String>();
        List<String> steps2 = new ArrayList<String>();
        List<String> ingredients3 = new ArrayList<String>();
        List<String> tools3 = new ArrayList<String>();
        List<String> steps3 = new ArrayList<String>();
        
        ingredients1.add("1 cup of uncooked rice");
        ingredients1.add("2 chicken breasts");
        ingredients1.add("8 cups of chicken stock");
        ingredients1.add("3 cloves of garlic, minced");
        ingredients1.add("2 stalks of green onion, thinly sliced");
        ingredients1.add("2 tablespoons of vegetable oil");
        ingredients1.add("1 tablespoon of sesame oil");
        ingredients1.add("Salt and pepper to taste");
        ingredients1.add("Fried shallots (optional)");
        ingredients1.add("Soy sauce (optional)");
        ingredients1.add("Hard-boiled eggs (optional)");
        
        tools1.add("Rice cooker or pot");
        tools1.add("Large pot");
        tools1.add("Cutting board");
        tools1.add("Knife");
        tools1.add("Mixing spoon");
        tools1.add("Bowls for serving");
        
        steps1.add("Rinse the rice in cold water until the water runs clear. Drain and set aside.");
        steps1.add("Cut the chicken breasts into small pieces and season with salt and pepper.");
        steps1.add("Heat the vegetable oil in a large pot over medium-high heat. Add the garlic and sauté until fragrant.");
        steps1.add("Add the chicken to the pot and cook until browned on all sides.");
        steps1.add("Pour in the chicken stock and bring to a boil.");
        steps1.add("Add the rice to the pot and stir well.");
        steps1.add("Reduce the heat to low and let the porridge simmer for about 30-40 minutes, stirring occasionally to prevent sticking to the bottom of the pot.");
        steps1.add("Once the porridge is thick and creamy, add the sesame oil and green onion. Stir well.");
        steps1.add("Taste and adjust seasoning if needed.");
        steps1.add("Ladle the porridge into bowls and top with fried shallots, soy sauce, and hard-boiled eggs, if desired.");

        dataMeal.addRecipeNoInput("Bubur Ayam", 340, 24, 38, 8, ingredients1, tools1, steps1);

        ingredients2.add("3 large eggs");
        ingredients2.add("1/4 cup diced bell peppers");
        ingredients2.add("1/4 cup diced onion");
        ingredients2.add("1/4 cup shredded cheddar cheese");
        ingredients2.add("1 tablespoon butter or oil");
        ingredients2.add("Salt and pepper to taste");
        
        tools2.add("Medium-sized non-stick frying pan");
        tools2.add("Mixing bowl");
        tools2.add("Whisk or fork");
        tools2.add("Spatula");
        
        steps2.add("In a mixing bowl, whisk the eggs until they are well beaten.");
        steps2.add("Add the diced bell peppers, onions, and shredded cheese to the eggs, and stir to combine.");
        steps2.add("Heat a non-stick frying pan over medium heat and add the butter or oil.");
        steps2.add("Once the butter has melted or the oil is hot, pour the egg mixture into the pan.");
        steps2.add("Use a spatula to spread the mixture evenly across the pan.");
        steps2.add("Let the omelette cook for 2-3 minutes, or until the bottom is golden brown.");
        steps2.add("Use a spatula to fold the omelette in half.");
        steps2.add("Let the omelette cook for an additional 1-2 minutes, or until the cheese is melted and the eggs are fully cooked.");
        steps2.add("Slide the omelette onto a plate and serve hot.");

        dataMeal.addRecipeNoInput("Omelette", 350, 23, 7, 26, ingredients2, tools2, steps2);

        ingredients3.add("1 ripe avocado");
        ingredients3.add("1 cup of milk (or almond milk for a dairy-free option)");
        ingredients3.add("1/2 cup of ice cubes");
        ingredients3.add("1 tablespoon of honey");
        ingredients3.add("1/4 teaspoon of vanilla extract");
        
        tools3.add("Blender");
        tools3.add("Knife");
        tools3.add("Spoon");
        
        steps3.add("Cut the avocado in half lengthwise and remove the pit. Use a spoon to scoop out the flesh into the blender.");
        steps3.add("Add the milk, ice cubes, honey, and vanilla extract to the blender.");
        steps3.add("Blend the mixture on high speed for about 30-45 seconds, or until smooth and creamy.");
        steps3.add("Pour the avocado juice into a glass and serve immediately.");

        dataMeal.addRecipeNoInput("Avocado Juice", 295, 7, 29, 18, ingredients3, tools3, steps3);
        
    }
}
