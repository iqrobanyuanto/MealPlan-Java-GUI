/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Meal.Meal;
import Schedule.*;
import User.User;
import java.util.List;

/**
 *
 * @author alift
 */
public interface ScheduleDaoInterface {
    public void createSchedule(String userId, Schedule schedule, String mealName);
    public List<String> getScheduleDate(String userId);
    public List<String> getScheduleMeal(String date);
    public void updateScheduleMeal(String userId, String date, String mealName);
    public void deleteSchedule(String userId, String date);
    public void deleteScheduleMeal(String userId, String date, String mealName);
}
