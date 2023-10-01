/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Controller.Controller;
import javax.swing.JList;
import javax.swing.JTextArea;

/**
 *
 * @author alift
 */
public class CreateSchedulePanel extends javax.swing.JPanel {

    Controller controller;
    /**
     * Creates new form CreateSchedulePanel
     */
    public CreateSchedulePanel(Controller controller) {
        this.controller = controller;
        initComponents();
        
        Controller.numberOnlySpinner(daySpinner);
        Controller.numberOnlySpinner(monthSpinner);
        Controller.numberOnlySpinner(yearSpinner);
    }

    public JTextArea getMealDetailTextArea() { return mealDetailTextArea; }
    public JList<String> getMealList() { return mealList; }
    public int getDay() { return (int) daySpinner.getValue(); }
    public int getMonth() { return (int) monthSpinner.getValue(); }
    public int getYear() { return (int) yearSpinner.getValue(); }
    
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
        dateLabel = new javax.swing.JLabel();
        mealLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mealList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        mealDetailTextArea = new javax.swing.JTextArea();
        saveButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        createScheduleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        createScheduleLabel.setText("Create Schedule");

        dayLabel.setText("Day:");

        daySpinner.setPreferredSize(new java.awt.Dimension(65, 22));

        monthLabel.setText("Month:");

        monthSpinner.setPreferredSize(new java.awt.Dimension(65, 22));

        yearLabel.setText("Year:");

        yearSpinner.setPreferredSize(new java.awt.Dimension(65, 22));

        dateLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dateLabel.setText("Date");

        mealLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mealLabel.setText("Meal");

        mealList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saveButton)
                    .addComponent(createScheduleLabel)
                    .addComponent(mealLabel)
                    .addComponent(dateLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dayLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(daySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(monthLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(monthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(yearLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(yearSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(createScheduleLabel)
                .addGap(18, 18, 18)
                .addComponent(dateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(daySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dayLabel)
                    .addComponent(monthLabel)
                    .addComponent(monthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearLabel)
                    .addComponent(yearSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(mealLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addComponent(saveButton)
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mealListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mealListMouseClicked
        controller.setMealDetail(this.getClass().getSimpleName());        // TODO add your handling code here:
    }//GEN-LAST:event_mealListMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel createScheduleLabel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel dayLabel;
    private javax.swing.JSpinner daySpinner;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea mealDetailTextArea;
    private javax.swing.JLabel mealLabel;
    private javax.swing.JList<String> mealList;
    private javax.swing.JLabel monthLabel;
    private javax.swing.JSpinner monthSpinner;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel yearLabel;
    private javax.swing.JSpinner yearSpinner;
    // End of variables declaration//GEN-END:variables
}
