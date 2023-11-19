/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Controller.Controller;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JTextArea;

/**
 *
 * @author alift
 */
public class CreateSchedulePanel extends javax.swing.JPanel {

    Controller controller;
    int day;
    int month;
    int year;
    /**
     * Creates new form CreateSchedulePanel
     */
    public CreateSchedulePanel(Controller controller) {
        this.controller = controller;
        initComponents();
        
        Controller.numberOnlySpinner(daySpinner);
        Controller.numberOnlySpinner(monthSpinner);
        Controller.numberOnlySpinner(yearSpinner);
        
        day = (int) daySpinner.getValue();
        month = (int) monthSpinner.getValue();
        year = (int) yearSpinner.getValue();
        
        mealDetailTextArea.setEditable(false);
    }

    public JTextArea getMealDetailTextArea() { return mealDetailTextArea; }
    public JList<String> getMealList() { return mealList; } 
    public JLabel getWarningLabel() { return warningLabel; } 
    public int getDay() { return (int) daySpinner.getValue(); }
    public int getMonth() { return (int) monthSpinner.getValue(); }
    public int getYear() { return (int) yearSpinner.getValue(); } 
    public JButton getRefreshButton() { return refreshButton; } 
    public JLabel getCal() { return cal; } 
    public JLabel getCarbs() { return carbs; } 
    public JLabel getFat() { return fat; } 
    public JLabel getPro() { return pro; } 
    public JLabel getDateLabel() { return dateLabel; } 
    public JSpinner getDaySpinner() { return daySpinner; } 
    public JSpinner getMonthSpinner() { return monthSpinner; } 
    public JSpinner getYearSpinner() { return yearSpinner; }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        createScheduleLabel = new javax.swing.JLabel();
        dayLabel = new javax.swing.JLabel();
        daySpinner = new javax.swing.JSpinner();
        monthLabel = new javax.swing.JLabel();
        monthSpinner = new javax.swing.JSpinner();
        yearLabel = new javax.swing.JLabel();
        yearSpinner = new javax.swing.JSpinner();
        date = new javax.swing.JLabel();
        mealLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mealList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        mealDetailTextArea = new javax.swing.JTextArea();
        saveButton = new javax.swing.JButton();
        warningLabel = new javax.swing.JLabel();
        todayButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        cal = new javax.swing.JLabel();
        carbs = new javax.swing.JLabel();
        calLabel = new javax.swing.JLabel();
        proLabel = new javax.swing.JLabel();
        carLabel = new javax.swing.JLabel();
        pro = new javax.swing.JLabel();
        fatLabel = new javax.swing.JLabel();
        fat = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        createScheduleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        createScheduleLabel.setText("Create Schedule");

        dayLabel.setText("Day:");

        daySpinner.setPreferredSize(new java.awt.Dimension(65, 22));
        daySpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                daySpinnerStateChanged(evt);
            }
        });

        monthLabel.setText("Month:");

        monthSpinner.setPreferredSize(new java.awt.Dimension(65, 22));
        monthSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                monthSpinnerStateChanged(evt);
            }
        });

        yearLabel.setText("Year:");

        yearSpinner.setPreferredSize(new java.awt.Dimension(65, 22));
        yearSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                yearSpinnerStateChanged(evt);
            }
        });

        date.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        date.setText("Date");

        mealLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mealLabel.setText("Meal");

        mealList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " ", " ", " ", " ", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        mealList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mealListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(mealList);

        mealDetailTextArea.setColumns(20);
        mealDetailTextArea.setRows(5);
        jScrollPane2.setViewportView(mealDetailTextArea);

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        warningLabel.setForeground(new java.awt.Color(255, 0, 0));

        todayButton.setText("Today");
        todayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todayButtonActionPerformed(evt);
            }
        });

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        cal.setText("0");

        carbs.setText("0");

        calLabel.setText("Calories:");

        proLabel.setText("Protein:");

        carLabel.setText("Carbs:");

        pro.setText("0");

        fatLabel.setText("Fat:");

        fat.setText("0");

        dateLabel.setText("dd-mm-yyyy");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(refreshButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(mealLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(todayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(saveButton)
                                    .addComponent(warningLabel)
                                    .addComponent(date)
                                    .addComponent(createScheduleLabel)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(calLabel)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(cal, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(proLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(carLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(carbs, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(fatLabel)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(fat, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(pro, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(dateLabel))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(dayLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(daySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(monthLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(monthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(yearLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(yearSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addContainerGap(18, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(createScheduleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(calLabel)
                    .addComponent(cal)
                    .addComponent(proLabel)
                    .addComponent(pro)
                    .addComponent(dateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(carLabel)
                    .addComponent(carbs)
                    .addComponent(fatLabel)
                    .addComponent(fat))
                .addGap(18, 18, 18)
                .addComponent(date)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(daySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dayLabel)
                    .addComponent(monthLabel)
                    .addComponent(monthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearLabel)
                    .addComponent(yearSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(todayButton)
                .addGap(12, 12, 12)
                .addComponent(mealLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refreshButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(saveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(warningLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mealListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mealListMouseClicked
        controller.setMealDetail(this.getClass().getSimpleName());        // TODO add your handling code here:
    }//GEN-LAST:event_mealListMouseClicked

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        controller.createSchedule();        // TODO add your handling code here:
    }//GEN-LAST:event_saveButtonActionPerformed

    private void todayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todayButtonActionPerformed
        LocalDate date = LocalDate.now();

        daySpinner.setValue(date.getDayOfMonth());
        monthSpinner.setValue(date.getMonthValue());
        yearSpinner.setValue(date.getYear());        // TODO add your handling code here:
    }//GEN-LAST:event_todayButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        controller.refreshCreateMealListNutrition();        // TODO add your handling code here:
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void daySpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_daySpinnerStateChanged
        day = (int) daySpinner.getValue();
        controller.refreshCreateMealListNutritionState(day, month, year);    // TODO add your handling code here:
    }//GEN-LAST:event_daySpinnerStateChanged

    private void monthSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_monthSpinnerStateChanged
        month = (int) monthSpinner.getValue();
        controller.refreshCreateMealListNutritionState(day, month, year);    // TODO add your handling code here:
    }//GEN-LAST:event_monthSpinnerStateChanged

    private void yearSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_yearSpinnerStateChanged
        year = (int) yearSpinner.getValue();
        controller.refreshCreateMealListNutritionState(day, month, year);    // TODO add your handling code here:
    }//GEN-LAST:event_yearSpinnerStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cal;
    private javax.swing.JLabel calLabel;
    private javax.swing.JLabel carLabel;
    private javax.swing.JLabel carbs;
    private javax.swing.JLabel createScheduleLabel;
    private javax.swing.JLabel date;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel dayLabel;
    private javax.swing.JSpinner daySpinner;
    private javax.swing.JLabel fat;
    private javax.swing.JLabel fatLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea mealDetailTextArea;
    private javax.swing.JLabel mealLabel;
    private javax.swing.JList<String> mealList;
    private javax.swing.JLabel monthLabel;
    private javax.swing.JSpinner monthSpinner;
    private javax.swing.JLabel pro;
    private javax.swing.JLabel proLabel;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton todayButton;
    private javax.swing.JLabel warningLabel;
    private javax.swing.JLabel yearLabel;
    private javax.swing.JSpinner yearSpinner;
    // End of variables declaration//GEN-END:variables
}
