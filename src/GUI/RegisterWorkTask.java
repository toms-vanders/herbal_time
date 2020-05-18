package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterWorkTask extends javax.swing.JPanel {

    private Dashboard dashboard;

    public RegisterWorkTask(Dashboard dashboard) {
        this.dashboard = dashboard;
        initComponents();
    }

    private void initComponents() {

        registerTaskPane = new javax.swing.JPanel();
        taskTitle = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();
        registerBtn = new javax.swing.JButton();
        locationLabel = new javax.swing.JLabel();
        locationList = new javax.swing.JComboBox<>();
        produceLabel = new javax.swing.JLabel();
        produceList = new javax.swing.JComboBox<>();
        startDateLabel = new javax.swing.JLabel();
        startDatePicker = new com.github.lgooddatepicker.components.DateTimePicker();
        endDateLabel = new javax.swing.JLabel();
        endDatePicker = new com.github.lgooddatepicker.components.DateTimePicker();
        quantityLabel = new javax.swing.JLabel();
        quanitySpinner = new javax.swing.JSpinner();
        quantityPicker = new javax.swing.JComboBox<>();
        statusLabel = new javax.swing.JLabel();
        statusPicker = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(71, 120, 197));
        setMinimumSize(new java.awt.Dimension(690, 720));
        setPreferredSize(new java.awt.Dimension(690, 720));
        setVerifyInputWhenFocusTarget(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        registerTaskPane.setBackground(new java.awt.Color(71, 120, 197));
        registerTaskPane.setMinimumSize(new java.awt.Dimension(690, 720));
        registerTaskPane.setPreferredSize(new java.awt.Dimension(690, 720));

        taskTitle.setText("Registering new work task");
        taskTitle.setBackground(new java.awt.Color(249, 249, 249));
        taskTitle.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        taskTitle.setForeground(new java.awt.Color(249, 249, 249));
        taskTitle.setPreferredSize(new java.awt.Dimension(450, 50));

        cancelBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_trash_can_32px.png"))); // NOI18N
        cancelBtn.setText("Cancel");
        cancelBtn.setBackground(new java.awt.Color(71, 120, 197));
        cancelBtn.setForeground(new java.awt.Color(60, 63, 65));
        cancelBtn.addActionListener(this::cancelBtnActionPerformed);

        registerBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_save_32px.png"))); // NOI18N
        registerBtn.setText("Register");
        registerBtn.setBackground(new java.awt.Color(71, 120, 197));
        registerBtn.setForeground(new java.awt.Color(60, 63, 65));
        registerBtn.addActionListener(this::registerBtnActionPerformed);

        locationLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_place_marker_32px.png"))); // NOI18N
        locationLabel.setText("Location");
        locationLabel.setFocusable(false);
        locationLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        locationLabel.setForeground(new java.awt.Color(249, 249, 249));

        locationList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        produceLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_vegetarian_mark_32px.png"))); // NOI18N
        produceLabel.setText("Produce");
        produceLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        produceLabel.setForeground(new java.awt.Color(249, 249, 249));

        produceList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        startDateLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_watch_32px.png"))); // NOI18N
        startDateLabel.setText("Start");
        startDateLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        startDateLabel.setForeground(new java.awt.Color(249, 249, 249));

        startDatePicker.setBackground(new java.awt.Color(120, 168, 252));

        endDateLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_present_32px.png"))); // NOI18N
        endDateLabel.setText("End");
        endDateLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        endDateLabel.setForeground(new java.awt.Color(249, 249, 249));

        endDatePicker.setBackground(new Color(120, 168, 252));

        quantityLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_trolley_32px.png"))); // NOI18N
        quantityLabel.setText("Quantity");
        quantityLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        quantityLabel.setForeground(new java.awt.Color(249, 249, 249));

        quantityPicker.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        statusLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_approval_32px.png"))); // NOI18N
        statusLabel.setText("Status");
        statusLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(249, 249, 249));

        statusPicker.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout registerTaskPaneLayout = new javax.swing.GroupLayout(registerTaskPane);
        registerTaskPane.setLayout(registerTaskPaneLayout);
        registerTaskPaneLayout.setHorizontalGroup(
            registerTaskPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerTaskPaneLayout.createSequentialGroup()
                .addGroup(registerTaskPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registerTaskPaneLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(locationLabel)
                        .addGap(29, 29, 29)
                        .addComponent(locationList, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registerTaskPaneLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(produceLabel)
                        .addGap(31, 31, 31)
                        .addComponent(produceList, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registerTaskPaneLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(startDateLabel)
                        .addGap(63, 63, 63)
                        .addComponent(startDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registerTaskPaneLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(endDateLabel)
                        .addGap(70, 70, 70)
                        .addComponent(endDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registerTaskPaneLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(quantityLabel)
                        .addGap(30, 30, 30)
                        .addComponent(quanitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(quantityPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registerTaskPaneLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(statusLabel)
                        .addGap(49, 49, 49)
                        .addComponent(statusPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registerTaskPaneLayout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(registerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registerTaskPaneLayout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(taskTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        registerTaskPaneLayout.setVerticalGroup(
            registerTaskPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerTaskPaneLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(taskTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(registerTaskPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(locationLabel)
                    .addComponent(locationList, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(registerTaskPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(produceLabel)
                    .addComponent(produceList, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(registerTaskPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(startDateLabel)
                    .addComponent(startDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(registerTaskPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(endDateLabel)
                    .addComponent(endDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(registerTaskPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(quantityLabel)
                    .addComponent(quanitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantityPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(registerTaskPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusLabel)
                    .addComponent(statusPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(registerTaskPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(registerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        add(registerTaskPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }

    private void registerBtnActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void cancelBtnActionPerformed(ActionEvent evt){
        dashboard.cancelRegisterWorkTask();
    }


    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel endDateLabel;
    private com.github.lgooddatepicker.components.DateTimePicker endDatePicker;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JComboBox<String> locationList;
    private javax.swing.JLabel produceLabel;
    private javax.swing.JComboBox<String> produceList;
    private javax.swing.JSpinner quanitySpinner;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JComboBox<String> quantityPicker;
    private javax.swing.JButton registerBtn;
    private javax.swing.JPanel registerTaskPane;
    private javax.swing.JLabel startDateLabel;
    private com.github.lgooddatepicker.components.DateTimePicker startDatePicker;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JComboBox<String> statusPicker;
    private javax.swing.JLabel taskTitle;
}