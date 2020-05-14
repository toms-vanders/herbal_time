package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class Dashboard extends JPanel {

    MainScreen mainScreen;

    public Dashboard(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        initComponents();
        toggleRegisterTask();
    }
    private void initComponents() {

        overviewPane = new JPanel();
        overviewProfile = new JPanel();
        overviewBtns = new JPanel();
        mailBtn = new JLabel();
        contactsBtn = new JLabel();
        calendarBtn = new JLabel();
        logoutBtn = new JLabel();
        jSeparator1 = new JSeparator();
        exitBtn = new JLabel();
        profileNameLabel = new JLabel();
        profilePicture = new JLabel();
        viewTaskBtn = new JButton();
        readableDayLabel = new JLabel();
        monthYearLabel = new JLabel();
        registerTaskBtn = new JButton();
        contactLeaderBtn = new JButton();
        jScrollPane1 = new JScrollPane();
        workTaskListTable = new JTable();
        registerWorkTask1 = new RegisterWorkTask(this);

        setEnabled(false);
        setMinimumSize(new java.awt.Dimension(1000, 720));
        setPreferredSize(new java.awt.Dimension(1000, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        overviewPane.setBackground(new java.awt.Color(71, 120, 197));

        overviewProfile.setBackground(new java.awt.Color(120, 168, 252));
        overviewProfile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        overviewBtns.setBackground(new java.awt.Color(84, 127, 206));

        mailBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_secured_letter_32px_3.png"))); // NOI18N

        contactsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_contacts_32px.png"))); // NOI18N

        calendarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_calendar_32px.png"))); // NOI18N

        logoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_lock_32px.png"))); // NOI18N
        logoutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                logoutBtnPressed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout overviewBtnsLayout = new javax.swing.GroupLayout(overviewBtns);
        overviewBtns.setLayout(overviewBtnsLayout);
        overviewBtnsLayout.setHorizontalGroup(
            overviewBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, overviewBtnsLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(overviewBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(overviewBtnsLayout.createSequentialGroup()
                        .addComponent(mailBtn)
                        .addGap(37, 37, 37)
                        .addComponent(contactsBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(calendarBtn)
                        .addGap(36, 36, 36)
                        .addComponent(logoutBtn)))
                .addGap(37, 37, 37))
        );
        overviewBtnsLayout.setVerticalGroup(
            overviewBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overviewBtnsLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(overviewBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mailBtn)
                    .addComponent(contactsBtn)
                    .addComponent(calendarBtn)
                    .addComponent(logoutBtn))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        overviewProfile.add(overviewBtns, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 111, -1, -1));

        exitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_exit_32px.png"))); // NOI18N
        exitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitBtnMousePressed(evt);
            }
        });
        overviewProfile.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, -1, -1));

        profileNameLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 14)); // NOI18N
        profileNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        profileNameLabel.setText("John Doe");
        overviewProfile.add(profileNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        profilePicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_github_96px.png"))); // NOI18N
        profilePicture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                profilePictureMousePressed(evt);
            }
        });
        overviewProfile.add(profilePicture, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 100, 100));

        viewTaskBtn.setBackground(new java.awt.Color(71, 120, 197));
        viewTaskBtn.setForeground(new java.awt.Color(255, 255, 255));
        viewTaskBtn.setText("View Task");
        viewTaskBtn.setBorder(null);
        viewTaskBtn.addActionListener(this::viewTaskBtnActionPerformed);

        readableDayLabel.setForeground(new java.awt.Color(255, 255, 255));
        readableDayLabel.setText("Wednesday 13");

        monthYearLabel.setForeground(new java.awt.Color(255, 255, 255));
        monthYearLabel.setText("May 2020");

        registerTaskBtn.setBackground(new java.awt.Color(71, 120, 197));
        registerTaskBtn.setForeground(new java.awt.Color(255, 255, 255));
        registerTaskBtn.setText("Register Task");
        registerTaskBtn.setBorder(null);
        registerTaskBtn.addActionListener(this::registerTaskBtnActionPerformed);

        contactLeaderBtn.setBackground(new java.awt.Color(71, 120, 197));
        contactLeaderBtn.setForeground(new java.awt.Color(255, 255, 255));
        contactLeaderBtn.setText("Contact Leader");
        contactLeaderBtn.setBorder(null);

        javax.swing.GroupLayout overviewPaneLayout = new javax.swing.GroupLayout(overviewPane);
        overviewPane.setLayout(overviewPaneLayout);
        overviewPaneLayout.setHorizontalGroup(
            overviewPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(overviewProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(overviewPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(overviewPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewTaskBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(overviewPaneLayout.createSequentialGroup()
                        .addComponent(readableDayLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(monthYearLabel))
                    .addComponent(contactLeaderBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(registerTaskBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        overviewPaneLayout.setVerticalGroup(
            overviewPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overviewPaneLayout.createSequentialGroup()
                .addComponent(overviewProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(overviewPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(readableDayLabel)
                    .addComponent(monthYearLabel))
                .addGap(18, 18, 18)
                .addComponent(registerTaskBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewTaskBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(contactLeaderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
        );

        add(overviewPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 720));

        workTaskListTable.setForeground(new java.awt.Color(102, 102, 102));
        workTaskListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"11/05/2020", "1", 120, Boolean.TRUE},
                {"12/05/2020", "1", 140, Boolean.FALSE},
                {"13/05/2020", "2", 100, Boolean.TRUE},
                {"14/05/2020", "3", 90, Boolean.FALSE}
            },
            new String [] {
                "Date", "Site", "Quantity", "Approved"
            }
        ) {
            final Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            final boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        workTaskListTable.setGridColor(new java.awt.Color(255, 255, 255));
        workTaskListTable.setRowHeight(32);
        workTaskListTable.setSelectionBackground(new java.awt.Color(0, 120, 215));
        jScrollPane1.setViewportView(workTaskListTable);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 690, 720));
        add(registerWorkTask1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, -1, -1));
    }

    private void exitBtnMousePressed(java.awt.event.MouseEvent evt) {
        System.exit(0);
    }

    private void profilePictureMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void registerTaskBtnActionPerformed(ActionEvent evt) {
        toggleViewTask();
        toggleRegisterTask();
    }

    private void viewTaskBtnActionPerformed(ActionEvent evt){
        toggleRegisterTask();
        toggleViewTask();
    }

    private void logoutBtnPressed(MouseEvent evt){
        mainScreen.logout();
    }

    private void toggleRegisterTask(){
        registerWorkTask1.setVisible(!registerWorkTask1.isVisible());
    }

    public void cancelRegisterWorkTask(){
        toggleRegisterTask();
        toggleViewTask();
    }

    private void toggleViewTask(){
        jScrollPane1.setVisible(!jScrollPane1.isVisible());
    }

    private javax.swing.JLabel calendarBtn;
    private javax.swing.JButton contactLeaderBtn;
    private javax.swing.JLabel contactsBtn;
    private javax.swing.JLabel exitBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel logoutBtn;
    private javax.swing.JLabel mailBtn;
    private javax.swing.JLabel monthYearLabel;
    private javax.swing.JPanel overviewBtns;
    private javax.swing.JPanel overviewPane;
    private javax.swing.JPanel overviewProfile;
    private javax.swing.JLabel profileNameLabel;
    private javax.swing.JLabel profilePicture;
    private javax.swing.JLabel readableDayLabel;
    private javax.swing.JButton registerTaskBtn;
    private RegisterWorkTask registerWorkTask1;
    private javax.swing.JButton viewTaskBtn;
    private javax.swing.JTable workTaskListTable;
}
