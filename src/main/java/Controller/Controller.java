/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.*;
import Meal.Recipe;
import View.*;
import User.*;
import java.awt.Component;
import java.awt.Container;
import java.text.NumberFormat;
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
        } else if (name.length() > 25){
            registerPanel.getWarningLabel().setText("Name too long");
        }
        
        if (username.isEmpty()){
            registerPanel.getWarningLabel().setText("Username can't be empty");
        } else if (username.length() > 25) {
            registerPanel.getWarningLabel().setText("Username too long");
        }
        
        if (email.length() < 1){
            registerPanel.getWarningLabel().setText("Email can't be empty");
        } else if (!email.contains("@") || email.indexOf(".") <= email.indexOf("@")) {
            registerPanel.getWarningLabel().setText("Invalid email address");
        }
        
        if (password.isEmpty() || reTypePassword.isEmpty()){
            registerPanel.getWarningLabel().setText("Password can't be empty");
        } else if (password.length() < 8){
            registerPanel.getWarningLabel().setText("Password too short");
        } else if (!password.equals(reTypePassword)){
            registerPanel.getWarningLabel().setText("Password isn't match");
        }
        
        if (age < 1 && age > 150){
            registerPanel.getWarningLabel().setText("Invalid age");
        }
        
        if (height < 1 && height > 300){
            registerPanel.getWarningLabel().setText("Invalid height");
        }
        
        if (weight < 1 && weight > 300){
            registerPanel.getWarningLabel().setText("Invalid weight");
        }
        
        User user = new User(name, username, email, password, gender, age, height, weight);
        User temp = userDao.getUserByUsername(username);
        
        if (user.getUsername().equals(temp.getUsername())){
            registerPanel.getWarningLabel().setText("Username already exist");
        } else {
            userDao.createUser(user);
            JOptionPane.showMessageDialog(null, "Registration successful!");
        }
    }
    
    private void menuPanelSetUp(){
        user.setNutrition(user);
        menuPanel.getWelcomeLabel().setText("Welcome, " + user.getName() + "!");
        
        menuPanel.getCal().setText(""+user.getUserNutrition().getCal());
        menuPanel.getPro().setText(""+user.getUserNutrition().getProtein());
        menuPanel.getCarbs().setText(""+user.getUserNutrition().getCarbs());
        menuPanel.getFat().setText(""+user.getUserNutrition().getFat());
        
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
        
        if (menuPanel.getAge() > 1){
            age = menuPanel.getAge();
            isChanged = true;
        }
        
        if (menuPanel.getUserHeight() > 1){
            height = menuPanel.getUserHeight();
            isChanged = true;
        }
        
        if (menuPanel.getUserWeight() > 1){
            weight = menuPanel.getUserWeight();
            isChanged = true;
        }
        
        if(isChanged){
            userDao.updateUser(username, name, email, gender, age, height, weight);
            menuPanelSetUp();
            int result = JOptionPane.showOptionDialog(null, "Profile changed!", "Success",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"OK"}, null);
            if (result == JOptionPane.OK_OPTION) {
            }
        } else {
            JOptionPane.showMessageDialog(null, "There's no change");
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
    
    public void createSchedule(){
        
    }
    
    public void removeSchedule(){
        
    }
    
    public void removeScheduleMeal(){
        
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
        List<String> scheduleMeal = scheduleDao.getScheduleMeal(selectedDate);
        
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
        }
    }
    
    public void setMealDetail(String panel){
        if(panel.equals(menuPanel.getClass().getSimpleName())){
            
            String selectedRecipeName = menuPanel.getMealRecipeList().getSelectedValue();
            Recipe selectedRecipe = mealDao.getMeal(null, selectedRecipeName);

            if(selectedRecipe == null){
                selectedRecipe = mealDao.getMeal(user.getUserId(), selectedRecipeName);
            }

            String textArea = setRecipeDetail(selectedRecipe);
            menuPanel.getMealRecipeDetail().setText(textArea);
            
        } else if (panel.equals(removeRecipePanel.getClass().getSimpleName())){
            
            String selectedRecipeName = removeRecipePanel.getMealList().getSelectedValue();
            Recipe selectedRecipe = mealDao.getMeal(user.getUserId(), selectedRecipeName);
            
            String textArea = setRecipeDetail(selectedRecipe);
            removeRecipePanel.getMealDetailText().setText(textArea);
            
        } else if (panel.equals(createSchedulePanel.getClass().getSimpleName())){
            
            String selectedRecipeName = createSchedulePanel.getMealList().getSelectedValue();
            Recipe selectedRecipe = mealDao.getMeal(null, selectedRecipeName);

            if(selectedRecipe == null){
                selectedRecipe = mealDao.getMeal(user.getUserId(), selectedRecipeName);
            }

            String textArea = setRecipeDetail(selectedRecipe);
            createSchedulePanel.getMealDetailTextArea().setText(textArea);
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
    
    public void logout(){
        user = null;
        menuPanel.setVisible(false);
        showLoginView();
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
        
        popUpFrame.setAllVisibleFalse();
        showPanel(popUpFrame, popUpFrame.getCreateSchedulePanel());
        popUpFrame.pack();
        popUpFrame.setVisible(true);
    }
    
    public void showRemoveSchedulePanel(){
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
    
    public User getUser() { return user; }
}
