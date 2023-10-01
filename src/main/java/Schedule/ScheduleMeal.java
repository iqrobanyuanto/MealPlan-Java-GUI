/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Schedule;

/**
 *
 * @author alift
 */
public class ScheduleMeal {
    private String scheduleMealId;
    private String userId;
    private String scheduleId;
    private String mealId;

    public ScheduleMeal(String scheduleMealId, String userId, String scheduleId, String mealId) {
        this.scheduleMealId = scheduleMealId;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.mealId = mealId;
    }

    public String getScheduleMealId() {
        return scheduleMealId;
    }

    public String getUserId() {
        return userId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public String getMealId() {
        return mealId;
    }

    public void setScheduleMealId(String scheduleMealId) {
        this.scheduleMealId = scheduleMealId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }
    
    
}
