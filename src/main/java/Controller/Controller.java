/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.*;
import Meal.Recipe;
import Nutrition.UserNutrition;
import Schedule.Schedule;
import View.*;
import User.*;
import java.awt.Component;
import java.awt.Container;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import javax.swing.text.NumberFormatter;


/**
 *
 * @author alift
 */
public class Controller {
    
    UserDaoInterface userDao;
    MealDaoInterface mealDao;
    ScheduleDaoInterface scheduleDao;
    
    private User user;
    
    private PopUpFrame popUpFrame;
    private MainFrame mainFrame;
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private MenuPanel menuPanel;
    private CreateRecipePanel createRecipePanel;
    private RemoveRecipePanel removeRecipePanel;
    private CreateSchedulePanel createSchedulePanel;
    private RemoveSchedulePanel removeSchedulePanel;
    
    public Controller(){

        userDao = new UserDao();
        mealDao = new MealDao();
        scheduleDao = new ScheduleDao();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        mainFrame = new MainFrame(this);
        popUpFrame = new PopUpFrame(this);
        
        loginPanel = mainFrame.getLoginPanel();
        registerPanel = mainFrame.getRegisterPanel();
        menuPanel = mainFrame.getMenuPanel();
        createRecipePanel = popUpFrame.getCreateRecipePanel();
        removeRecipePanel = popUpFrame.getRemoveRecipePanel();
        createSchedulePanel = popUpFrame.getCreateSchedulePanel();
        removeSchedulePanel = popUpFrame.getRemoveSchedulePanel();
    }
        
    public void login(){
        String username = loginPanel.getUsername();
        String password = loginPanel.getPassword();
        
        if(userDao.isMatch(username, password)){
            user = userDao.getUserByUsername(username);
            
            int result = JOptionPane.showOptionDialog(null, "Login successful!", "Success",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"OK"}, null);
            if (result == JOptionPane.OK_OPTION) {
                loginPanel.getWarningLabel().setText("");
                loginPanel.setVisible(false);
                mainFrame.getContentPane().add(menuPanel);
                menuPanel.setVisible(true);
                
                menuPanelSetUp();
            }
        } else {
            loginPanel.getWarningLabel().setText("Incorrect username and password");
        }
    }
    
    public void register(){
        String name = registerPanel.getNameUser();
        String username = registerPanel.getUsername();
        String email = registerPanel.getEmail();
        String password = registerPanel.getPassword();
        String reTypePassword = registerPanel.getRetypePassword();
        String gender = registerPanel.getGender();
        int age = registerPanel.getAge();
        int height = registerPanel.getUserHeight();
        int weight = registerPanel.getUserWeight();
        
        if (name.isEmpty()){
            registerPanel.getWarningLabel().setText("Name can't be empty");
            return;
        } else if (name.length() > 25){
            registerPanel.getWarningLabel().setText("Name too long");
            return;
        }
        
        if (username.isEmpty()){
            registerPanel.getWarningLabel().setText("Username can't be empty");
            return;
        } else if (username.length() > 9) {
            registerPanel.getWarningLabel().setText("Username too long");
            return;
        }
        
        if (email.length() < 1){
            registerPanel.getWarningLabel().setText("Email can't be empty");
            return;
        } else if (!email.contains("@") || email.indexOf(".") <= email.indexOf("@")) {
            registerPanel.getWarningLabel().setText("Invalid email address");
            return;
        }
        
        if (password.isEmpty() || reTypePassword.isEmpty()){
            registerPanel.getWarningLabel().setText("Password can't be empty");
            return;
        } else if (password.length() < 7){
            registerPanel.getWarningLabel().setText("Password too short");
            return;
        } else if (!password.equals(reTypePassword)){
            registerPanel.getWarningLabel().setText("Password isn't match");
            return;
        }
        
        if (age < 1 && age > 150){
            registerPanel.getWarningLabel().setText("Invalid age");
            return;
        }
        
        if (height < 1 && height > 300){
            registerPanel.getWarningLabel().setText("Invalid height");
            return;
        }
        
        if (weight < 1 && weight > 300){
            registerPanel.getWarningLabel().setText("Invalid weight");
            return;
        }
        
        User user = new User(name, username, email, password, gender, age, height, weight);
        User temp = userDao.getUserByUsername(username);
        
        if (temp != null) {
            if (user.getUsername().equals(temp.getUsername())){
                registerPanel.getWarningLabel().setText("Username already exist");
            } else {
                userDao.createUser(user);
                JOptionPane.showMessageDialog(null, "Registration successful!");
            }
        } else {
            userDao.createUser(user);
            JOptionPane.showMessageDialog(null, "Registration successful!");
        }
    }
    
    public void logout(){
        user = null;
        menuPanel.setVisible(false);
        showLoginView();
    }
    
    private void menuPanelSetUp(){
        user.setNutrition(user);
        
        LocalDate date = LocalDate.now();
        String today = date.getDayOfMonth()+"-"+date.getMonthValue()+"-"+date.getYear();
        
        menuPanel.getDateLabel().setText(today);
        menuPanel.getWelcomeLabel().setText("Welcome, " + user.getName() + "!");
        
        int totalCal = 0;
        int totalPro = 0;
        int totalCarb = 0;
        int totalFat = 0;

        List<Recipe> listedRecipes = scheduleDao.getScheduleRecipe(user.getUserId(), today);
        for(Recipe r: listedRecipes){
            totalCal += r.getCalories();
            totalPro += r.getProteins();
            totalCarb += r.getCarbs();
            totalFat += r.getFats();
        }
        
        totalCal = user.getUserNutrition().getCal() - totalCal;
        totalPro = user.getUserNutrition().getProtein() - totalPro;
        totalCarb = user.getUserNutrition().getCarbs() - totalCarb;
        totalFat = user.getUserNutrition().getFat() - totalFat;
        
        menuPanel.getCal().setText(""+totalCal);
        menuPanel.getPro().setText(""+totalPro);
        menuPanel.getCarbs().setText(""+totalCarb);
        menuPanel.getFat().setText(""+totalFat);
        
        menuPanel.getNameLabel().setText(user.getName());
        menuPanel.getUsernameLabel().setText(user.getUsername());
        menuPanel.getEmailLabel().setText(user.getEmail());
        menuPanel.getGenderLabel().setText(user.getGender());
        menuPanel.getAgeLabel().setText(""+user.getAge());
        menuPanel.getHeightLabel().setText(""+user.getHeight());
        menuPanel.getWeightLabel().setText(""+user.getWeight());
        
        setMealList(menuPanel.getClass().getSimpleName());
        setScheduleList();
    }
    
    public void updateProfile(){
        boolean isChanged = false;
        
        String name = menuPanel.getNameLabel().getText();
        String username = menuPanel.getUsernameLabel().getText();
        String email = menuPanel.getEmailTextField().getText();
        String gender = menuPanel.getGenderLabel().getText();
        int age = Integer.parseInt(menuPanel.getAgeLabel().getText());
        int height = Integer.parseInt(menuPanel.getHeightLabel().getText());
        int weight = Integer.parseInt(menuPanel.getWeightLabel().getText());
        
        if (menuPanel.getMaleRadio().isSelected() || menuPanel.getFemaleRadio().isSelected()){
            gender = menuPanel.getGender();
            isChanged = true;
        } 
        
        if (!menuPanel.getNameTextField().getText().isEmpty()){
            name = menuPanel.getNameTextField().getText();
            isChanged = true;
        }
        
        if (!menuPanel.getEmailTextField().getText().isEmpty()){
            email = menuPanel.getEmailTextField().getText();
            isChanged = true;
        }
        
        if (menuPanel.getAge() >= 0){
            age = menuPanel.getAge();
            isChanged = true;
        } else if (menuPanel.getAge() < 1 && menuPanel.getAge() != 0){
            isChanged = true;
        }
        
        if (menuPanel.getUserHeight() > 1){
            height = menuPanel.getUserHeight();
            isChanged = true;
        } else if (menuPanel.getUserHeight() <= 1 && menuPanel.getUserHeight() != 0){
            isChanged = false;
        }
        
        if (menuPanel.getUserWeight() > 1){
            weight = menuPanel.getUserWeight();
            isChanged = true;
        } else if (menuPanel.getUserWeight() <= 1 && menuPanel.getUserWeight() != 0){
            isChanged = false;
        }
        
        if(isChanged){
            userDao.updateUser(username, name, email, gender, age, height, weight);
            menuPanelSetUp();
            int result = JOptionPane.showOptionDialog(null, "Profile changed!", "Success",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"OK"}, null);
            if (result == JOptionPane.OK_OPTION) {
                menuPanelSetUp();
                menuPanel.setVisible(false);
                menuPanel.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Data");
        }
    }
    
    public void createRecipe(){
        String recipeName = createRecipePanel.getRecipeNameTextField().getText();
        String recipeIngrediens = createRecipePanel.getIngredientText().getText();
        String recipeTools = createRecipePanel.getToolText().getText();
        String recipeSteps = createRecipePanel.getStepText().getText();
        int cal = createRecipePanel.getCal();
        int pro = createRecipePanel.getPro();
        int car = createRecipePanel.getCar();
        int fat = createRecipePanel.getFat();
        
        if (recipeName.isEmpty() || recipeIngrediens.isEmpty() || recipeTools.isEmpty() || recipeSteps.isEmpty()){
            return;
        }
        
        if (cal < 1 || pro < 1 || car < 1 || fat < 1){
            return;
        }
        
        Recipe recipe = new Recipe(recipeName, recipeIngrediens, recipeTools, recipeSteps, cal, pro, car, fat);
        mealDao.createMeal(user.getUserId(), recipe);
        int result = JOptionPane.showOptionDialog(null, "Create recipe successful", "Success",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"OK"}, null);
        if (result == JOptionPane.OK_OPTION) {
            setMealList(menuPanel.getClass().getSimpleName());
            popUpFrame.setVisible(false);
        }
    }
    
    public void createSchedule(){
        int day = createSchedulePanel.getDay();
        int month = createSchedulePanel.getMonth();
        int year = createSchedulePanel.getYear();
        
        if (day < 0 || month < 1 || year < 1 || day > 31){
            createSchedulePanel.getWarningLabel().setText("Invalid date");
            return;
        }
        
        String date = day+"-"+month+"-"+year;
        String selectedMeal = createSchedulePanel.getMealList().getSelectedValue();
        Recipe selectedRecipe = mealDao.getMeal(user.getUserId(), selectedMeal);
        
        if (selectedRecipe == null){
            selectedRecipe = mealDao.getMeal(null, selectedMeal);
        }
        
        if (selectedMeal == null){
            createSchedulePanel.getWarningLabel().setText("Choose meal!");
            return;
        } else if (selectedMeal.isBlank()){
            createSchedulePanel.getWarningLabel().setText("Choose meal!");
            return;
        }
        
        Schedule schedule = new Schedule(date);
        scheduleDao.createSchedule(user.getUserId(), schedule, selectedMeal);
        int result = JOptionPane.showOptionDialog(null, "Schedule created!", "Success",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"OK"}, null);
        if (result == JOptionPane.OK_OPTION) {
            refreshInformation();
            refreshCreateMealListNutrition();
            setScheduleList();
            setScheduleMealList();
            
            if (Integer.parseInt(createSchedulePanel.getCal().getText()) < 0 || Integer.parseInt(createSchedulePanel.getPro().getText()) < 0
                    || Integer.parseInt(createSchedulePanel.getCarbs().getText()) < 0 || Integer.parseInt(createSchedulePanel.getFat().getText()) < 0){
                JOptionPane.showMessageDialog(null, "Exceed the daily nutritional limit!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    public void removeRecipe(){
        String selectedRecipeName = removeRecipePanel.getMealList().getSelectedValue();
        
        if (selectedRecipeName == null){
            removeRecipePanel.getWarningLabel().setText("Choose one meal recipe");
            return;
        }
        
        removeRecipePanel.getWarningLabel().setText("");
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            mealDao.deleteMeal(user.getUserId(), selectedRecipeName);
            setMealList(menuPanel.getClass().getSimpleName());
            popUpFrame.setAllVisibleFalse();
            popUpFrame.setVisible(false);
        }
    }
    
    public void removeSchedule(){
        int day = removeSchedulePanel.getDay();
        int month = removeSchedulePanel.getMonth();
        int year = removeSchedulePanel.getYear();
        String date = day + "-" + month + "-" + year;      
        
        if(day <= 0 || month <= 0 || year <= 0){
            removeSchedulePanel.getWarningLabel().setText("Invalid input");
        } else if (!ScheduleDao.isExist(date)){
            removeSchedulePanel.getWarningLabel().setText("Schedule doesn't exist");
            return;
        }
        
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if(choice == JOptionPane.YES_OPTION){
            scheduleDao.deleteSchedule(user.getUserId(), date);
            refreshInformation();
            setScheduleMealList();
            setScheduleList();
            popUpFrame.setAllVisibleFalse();
            popUpFrame.setVisible(false);
        }
         
    }
    
    public void removeScheduleMeal(){
        int day = removeSchedulePanel.getDay();
        int month = removeSchedulePanel.getMonth();
        int year = removeSchedulePanel.getYear();
        String date = day + "-" + month + "-" + year; 
        
        if(day < 1 || month < 1 || year < 1){
            removeSchedulePanel.getWarningLabel().setText("Invalid input");
            return;
        }  else if (!ScheduleDao.isExist(date)){
            removeSchedulePanel.getWarningLabel().setText("Schedule doesn't exist");
            return;
        }
        
        String selectedRecipe = removeSchedulePanel.getMealList().getSelectedValue();
        
        if (selectedRecipe == null){
            removeSchedulePanel.getWarningLabel().setText("Choose meal first!");
            return;
        }
        
        removeRecipePanel.getWarningLabel().setText("");
        
        Recipe recipe = mealDao.getMeal(null, selectedRecipe);
        
        if (recipe == null){
            recipe = mealDao.getMeal(user.getUserId(),selectedRecipe);
        }
        
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if(choice == JOptionPane.YES_OPTION){
            scheduleDao.deleteScheduleMeal(user.getUserId(), date, recipe.getMealId());
            setScheduleMealList();
            popUpFrame.setAllVisibleFalse();
            popUpFrame.setVisible(false);
        }
    }
    
    public void refreshRemoveList(){
        int day = removeSchedulePanel.getDay();
        int month = removeSchedulePanel.getMonth();
        int year = removeSchedulePanel.getYear();
        
        refreshRemoveListState(day, month, year);
    }
    
    public void refreshRemoveListState(int day, int month, int year){
        String date = day + "-" + month + "-" + year;
        
        if (day < 1 || month < 1 || year < 1){
            removeSchedulePanel.getWarningLabel().setText("Enter a valid date!");
            removeSchedulePanel.getMealList().setListData(new String[]{" "});
            return;
        } else if (!ScheduleDao.isExist(date)){
            removeSchedulePanel.getWarningLabel().setText("Schedule doesn't exist");
            removeSchedulePanel.getMealList().setListData(new String[]{" "});
            return;
        }
        
        removeSchedulePanel.getWarningLabel().setText("");
        DefaultListModel<String> mealListModel = new DefaultListModel<>();
        List<String> mealList = scheduleDao.getScheduleMeal(user.getUserId(),date);
        
        for (String str: mealList){
            mealListModel.addElement(str);
        }
        
        removeSchedulePanel.getMealList().setModel(mealListModel);
    }
    
    public void refreshCreateMealListNutrition(){
        int day = createSchedulePanel.getDay();
        int month = createSchedulePanel.getMonth();
        int year = createSchedulePanel.getYear();
        
        refreshCreateMealListNutritionState(day, month, year);
    }
    
    public void refreshCreateMealListNutritionState(int day, int month, int year){
        if (day < 1 || month < 1 || year < 1){
            createSchedulePanel.getWarningLabel().setText("Invalid date");
            return;
        }
        createSchedulePanel.getWarningLabel().setText("");
        String date = day+"-"+month+"-"+year;
        
        createSchedulePanel.getDateLabel().setText(date);
        
        int totalCal = 0;
        int totalPro = 0;
        int totalCarb = 0;
        int totalFat = 0;

        List<Recipe> listedRecipes = scheduleDao.getScheduleRecipe(user.getUserId(), date);
        for(Recipe r: listedRecipes){
            totalCal += r.getCalories();
            totalPro += r.getProteins();
            totalCarb += r.getCarbs();
            totalFat += r.getFats();
        }
        
        totalCal = user.getUserNutrition().getCal() - totalCal;
        totalPro = user.getUserNutrition().getProtein() - totalPro;
        totalCarb = user.getUserNutrition().getCarbs() - totalCarb;
        totalFat = user.getUserNutrition().getFat() - totalFat;
        
        createSchedulePanel.getCal().setText(""+totalCal);
        createSchedulePanel.getPro().setText(""+totalPro);
        createSchedulePanel.getCarbs().setText(""+totalCarb);
        createSchedulePanel.getFat().setText(""+totalFat);
        
        DefaultListModel<String> mealListModel = new DefaultListModel<>();
        List<String> userMealList = mealDao.getMealNameByNutrition(user.getUserId(), totalCal);
        List<String> mealList = mealDao.getMealNameByNutrition(null, totalCal);
        
        if (userMealList != null){
            for (String str: userMealList){
                mealListModel.addElement(str);
            }
        }
        
        for (String str: mealList){
            mealListModel.addElement(str);
        }
        
        createSchedulePanel.getMealList().setModel(mealListModel);
    }
    
    public void refreshInformation(){
        LocalDate localDate = LocalDate.now();
        
        int day = localDate.getDayOfMonth();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
                
        String date = day+"-"+month+"-"+year;
        
        int totalCal = 0;
        int totalPro = 0;
        int totalCarb = 0;
        int totalFat = 0;

        List<Recipe> listedRecipes = scheduleDao.getScheduleRecipe(user.getUserId(), date);
        for(Recipe r: listedRecipes){
            totalCal += r.getCalories();
            totalPro += r.getProteins();
            totalCarb += r.getCarbs();
            totalFat += r.getFats();
        }
        
        totalCal = user.getUserNutrition().getCal() - totalCal;
        totalPro = user.getUserNutrition().getProtein() - totalPro;
        totalCarb = user.getUserNutrition().getCarbs() - totalCarb;
        totalFat = user.getUserNutrition().getFat() - totalFat;
        
        menuPanel.getCal().setText(""+totalCal);
        menuPanel.getPro().setText(""+totalPro);
        menuPanel.getCarbs().setText(""+totalCarb);
        menuPanel.getFat().setText(""+totalFat);
        
        createSchedulePanel.getCal().setText(""+totalCal);
        createSchedulePanel.getPro().setText(""+totalPro);
        createSchedulePanel.getCarbs().setText(""+totalCarb);
        createSchedulePanel.getFat().setText(""+totalFat);
        
        menuPanel.getNameLabel().setText(user.getName());
        menuPanel.getUsernameLabel().setText(user.getUsername());
        menuPanel.getEmailLabel().setText(user.getEmail());
        menuPanel.getGenderLabel().setText(user.getGender());
        menuPanel.getAgeLabel().setText(""+user.getAge());
        menuPanel.getHeightLabel().setText(""+user.getHeight());
        menuPanel.getWeightLabel().setText(""+user.getWeight());
    }
    
    public void setScheduleList(){
        DefaultListModel<String> scheduleListModel = new DefaultListModel<>();
        List<String> scheduleDate = scheduleDao.getScheduleDate(user.getUserId());
        
        for (String str: scheduleDate){
            scheduleListModel.addElement(str);
        }
        
        menuPanel.getScheduleList().setModel(scheduleListModel);
    }
    
    public void setScheduleMealList(){
        String selectedDate = menuPanel.getScheduleList().getSelectedValue();
        
        DefaultListModel<String> scheduleMealListModel = new DefaultListModel<>();
        List<String> scheduleMeal = scheduleDao.getScheduleMeal(user.getUserId(),selectedDate);
        
        for (String str: scheduleMeal){
            scheduleMealListModel.addElement(str);
        }
        
        menuPanel.getMealScheduleList().setModel(scheduleMealListModel);
    }
    
    public void setScheduleMealDetail(){
        String selectedMealName = menuPanel.getMealScheduleList().getSelectedValue();
        Recipe selectedRecipe = mealDao.getMeal(null, selectedMealName);
        
        if (selectedRecipe == null){
            selectedRecipe = mealDao.getMeal(user.getUserId(), selectedMealName);
        }
        
        String textArea = setRecipeDetail(selectedRecipe);
        menuPanel.getMealScheduleDetail().setText(textArea);
    }
    
    public void setMealList(String panel){
        if(panel.equals(menuPanel.getClass().getSimpleName())){
            DefaultListModel<String> listMealRecipeModel = new DefaultListModel<>();
            List<String> recipeName = mealDao.getMealName(null);
            List<String> userRecipeName = mealDao.getMealName(user.getUserId());

            if (userRecipeName != null){
                for (String str: userRecipeName){
                    listMealRecipeModel.addElement(str);
                }
            }

            for (String str: recipeName){
                listMealRecipeModel.addElement(str);
            }

            menuPanel.getMealRecipeList().setModel(listMealRecipeModel);
            
        } else if (panel.equals(removeRecipePanel.getClass().getSimpleName())){
            DefaultListModel<String> mealListModel = new DefaultListModel<>();
            List<String> userRecipeName = mealDao.getMealName(user.getUserId());

            for (String str: userRecipeName){
                mealListModel.addElement(str);
            }

            removeRecipePanel.getMealList().setModel(mealListModel);
            
        } else if (panel.equals(createSchedulePanel.getClass().getSimpleName())){
            
            DefaultListModel<String> mealListModel = new DefaultListModel<>();
            List<String> recipeName = mealDao.getMealName(null);
            List<String> userRecipeName = mealDao.getMealName(user.getUserId());

            if (userRecipeName != null){
                for (String str: userRecipeName){
                    mealListModel.addElement(str);
                }
            }

            for (String str: recipeName){
                mealListModel.addElement(str);
            }
            
            createSchedulePanel.getMealList().setModel(mealListModel);
        } else if(panel.equals(removeRecipePanel.getClass().getSimpleName())){
            
        }
    }
    
    public void setMealDetail(String panel){
        if(panel.equals(menuPanel.getClass().getSimpleName())){
            
            String selectedRecipeName = menuPanel.getMealRecipeList().getSelectedValue();
            
            if (selectedRecipeName.isBlank()){
                return;
            }
            
            Recipe selectedRecipe = mealDao.getMeal(null, selectedRecipeName);

            if(selectedRecipe == null){
                selectedRecipe = mealDao.getMeal(user.getUserId(), selectedRecipeName);
            }

            String textArea = setRecipeDetail(selectedRecipe);
            menuPanel.getMealRecipeDetail().setText(textArea);
            
        } else if (panel.equals(removeRecipePanel.getClass().getSimpleName())){
            
            String selectedRecipeName = removeRecipePanel.getMealList().getSelectedValue();
            
            if (selectedRecipeName.isBlank()){
                return;
            }
            
            Recipe selectedRecipe = mealDao.getMeal(user.getUserId(), selectedRecipeName);
            
            String textArea = setRecipeDetail(selectedRecipe);
            removeRecipePanel.getMealDetailText().setText(textArea);
            
        } else if (panel.equals(createSchedulePanel.getClass().getSimpleName())){
            
            String selectedRecipeName = createSchedulePanel.getMealList().getSelectedValue();
            
            if (selectedRecipeName.isBlank()){
                return;
            }
            
            Recipe selectedRecipe = mealDao.getMeal(null, selectedRecipeName);

            if(selectedRecipe == null){
                selectedRecipe = mealDao.getMeal(user.getUserId(), selectedRecipeName);
            }

            String textArea = setRecipeDetail(selectedRecipe);
            createSchedulePanel.getMealDetailTextArea().setText(textArea);
            
        } else if (panel.equals(removeSchedulePanel.getClass().getSimpleName())){
            
            String selectedRecipeName = removeSchedulePanel.getMealList().getSelectedValue();
            
            if (selectedRecipeName.isBlank()){
                return;
            }
            
            Recipe selectedRecipe = mealDao.getMeal(null, selectedRecipeName);

            if(selectedRecipe == null){
                selectedRecipe = mealDao.getMeal(user.getUserId(), selectedRecipeName);
            }
            
            String textArea = setRecipeDetail(selectedRecipe);
            removeSchedulePanel.getMealDetail().setText(textArea);
        }
    }
    
    public String setRecipeDetail(Recipe selectedRecipe){
        String recipeName = selectedRecipe.getRecipeName();
        String recipeIngredients = selectedRecipe.getIngredientStr();
        String recipeTools = selectedRecipe.getToolStr();
        String recipeSteps = selectedRecipe.getStepStr();
        int recipeCal = selectedRecipe.getCalories();
        int recipePro = selectedRecipe.getProteins();
        int recipeCar = selectedRecipe.getCarbs();
        int recipeFat = selectedRecipe.getFats();
        recipeIngredients = recipeIngredients.replaceAll("\\, ", "\n");
        recipeTools = recipeTools.replaceAll("\\, ", "\n");
        recipeSteps = recipeSteps.replaceAll("\\. ", "\n");
        
        String textArea = String.format("%s\n\nIngredients:\n%s\n\nTools:\n%s\n\nSteps:\n%s\n\nCalories: %d\nProteins: %d\nCarbohydrates: %d\nFats: %d", 
                recipeName,recipeIngredients,recipeTools,recipeSteps,recipeCal,recipePro,recipeCar,recipeFat);
        
        return textArea;
    }
    
    public void searchRecipe(String searchTxt){
        DefaultListModel filteredItems = new DefaultListModel();
        List<String> userMeal = mealDao.getMealName(user.getUserId());
        List<String> sysMeal = mealDao.getMealName(null);
        
        if (userMeal != null){
            userMeal.stream().forEach((meal) -> {
                String mealName = meal.toString().toLowerCase();
                if (mealName.contains(searchTxt.toLowerCase())){
                    filteredItems.addElement(meal);
                }
            });
        }
        
        sysMeal.stream().forEach((meal) -> {
            String mealName = meal.toString().toLowerCase();
            if (mealName.contains(searchTxt.toLowerCase())){
                filteredItems.addElement(meal);
            }
        });
        
        menuPanel.getMealRecipeList().setModel(filteredItems);
    }
    
    private void showPanel(Container container, Component component){
        if(!isComponentAdded(container, component)){
            container.add(component);
        }
        component.setVisible(true);
    }
    
    public void showLoginView(){
        registerPanel.setVisible(false);
        loginPanel.setVisible(true);
    }
    
    public void showRegisterView(){
        
        loginPanel.setVisible(false);
        showPanel(mainFrame, registerPanel);
    }

    public void showCreateSchedulePanel(){
        setMealList(createSchedulePanel.getClass().getSimpleName());
        createSchedulePanel.getCal().setText(menuPanel.getCal().getText());
        createSchedulePanel.getPro().setText(menuPanel.getPro().getText());
        createSchedulePanel.getCarbs().setText(menuPanel.getCarbs().getText());
        createSchedulePanel.getFat().setText(menuPanel.getFat().getText());
        
        LocalDate date = LocalDate.now();
        String today = date.getDayOfMonth()+"-"+date.getMonthValue()+"-"+date.getYear();
        
        createSchedulePanel.getDateLabel().setText(today);
        
        String selectedDate = menuPanel.getScheduleList().getSelectedValue();
        int selectedIndex = menuPanel.getScheduleList().getSelectedIndex();
        boolean isSelected = menuPanel.getScheduleList().isSelectedIndex(selectedIndex);
        
        if (isSelected){
            if (selectedDate == null || !selectedDate.isBlank() ){
                String[] dates = selectedDate.split("-");
                int day = Integer.parseInt(dates[0]);
                int month = Integer.parseInt(dates[1]);
                int year = Integer.parseInt(dates[2]);

                createSchedulePanel.getDaySpinner().setValue(day);
                createSchedulePanel.getMonthSpinner().setValue(month);
                createSchedulePanel.getYearSpinner().setValue(year);

                refreshRemoveList();
            }
        }
        
        popUpFrame.setAllVisibleFalse();
        showPanel(popUpFrame, popUpFrame.getCreateSchedulePanel());
        popUpFrame.pack();
        popUpFrame.setVisible(true);
    }
    
    public void showRemoveSchedulePanel(){
        String selectedDate = menuPanel.getScheduleList().getSelectedValue();
        int selectedIndex = menuPanel.getScheduleList().getSelectedIndex();
        boolean isSelected = menuPanel.getScheduleList().isSelectedIndex(selectedIndex);
        
        if (isSelected){
            if (selectedDate == null || !selectedDate.isBlank() ){
                String[] date = selectedDate.split("-");
                int day = Integer.parseInt(date[0]);
                int month = Integer.parseInt(date[1]);
                int year = Integer.parseInt(date[2]);

                removeSchedulePanel.getDaySpinner().setValue(day);
                removeSchedulePanel.getMonthSpinner().setValue(month);
                removeSchedulePanel.getYearSpinner().setValue(year);

                refreshRemoveList();
            }
        }
        
        popUpFrame.setAllVisibleFalse();
        showPanel(popUpFrame, popUpFrame.getRemoveSchedulePanel());
        popUpFrame.pack();
        popUpFrame.setVisible(true);
    }
    
    public void showCreateRecipePanel(){
        popUpFrame.setAllVisibleFalse();
        showPanel(popUpFrame, popUpFrame.getCreateRecipePanel());
        popUpFrame.pack();
        popUpFrame.setVisible(true);
    }
    
    public void showRemoveRecipePanel(){
        setMealList(removeRecipePanel.getClass().getSimpleName());
        
        popUpFrame.setAllVisibleFalse();
        showPanel(popUpFrame, popUpFrame.getRemoveRecipePanel());
        popUpFrame.pack();
        popUpFrame.setVisible(true);
    }
    
    public static boolean isComponentAdded(Container container, Component component) {
        Component[] components = container.getComponents();
        for (Component comp : components) {
            if (comp == component) {
                return true;
            }
        }
        return false;
    }
    
    public static void numberOnlySpinner(JSpinner spinner){
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) spinner.getEditor();
        NumberFormat format = editor.getFormat();
        format.setGroupingUsed(false);
        format.setMaximumFractionDigits(0);
        format.setMinimumFractionDigits(0);
        ((NumberFormatter) editor.getTextField().getFormatter()).setAllowsInvalid(false);
    }
    
    public User getUser() { return user; }
}
