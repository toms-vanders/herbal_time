package GUI;

import Controller.DataAccessException;
import Controller.WorkTaskCtr;
import Controller.WorkTaskCtrIF;
import Model.WorkTask;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Controller.DataModel.*;

public class Dashboard extends JPanel {

    private final MainScreen mainScreen;

    public Dashboard(MainScreen mainScreen) throws DataAccessException {
        this.mainScreen = mainScreen;
        initComponents();
        registerWorkTask.setVisible(false);
    }
     
    private void initComponents() throws DataAccessException {

        overviewPane = new JPanel();
        overviewProfile = new JPanel();
        overviewBtns = new JPanel();
        mailBtn = new JLabel();
        contactsBtn = new JLabel();
        calendarBtn = new JLabel();
        logoutBtn = new JLabel();
        buttonSeparator = new JSeparator();
        exitBtn = new JLabel();
        profileNameLabel = new JLabel();
        profilePicture = new JLabel();
        viewTaskBtn = new JButton();
        readableDayLabel = new JLabel();
        monthYearLabel = new JLabel();
        registerTaskBtn = new JButton();
        contactLeaderBtn = new JButton();
        registerWorkTask = new RegisterWorkTask();
        jScrollPane1 = new JScrollPane();
        workTaskListTable = new JTable();
        workTaskController = new WorkTaskCtr();
        workTaskModel = new WorkTaskDataModel(
                new ArrayList<WorkTask>(workTaskController.findAllWorkTasksOfWorker(true,"1451684849")),
                "1451684849");

        setEnabled(false);
        setMinimumSize(new Dimension(1000, 720));
        setPreferredSize(new Dimension(1000, 720));
        setLayout(new AbsoluteLayout());

        overviewPane.setBackground(new Color(71, 120, 197));

        overviewProfile.setBackground(new Color(120, 168, 252));
        overviewProfile.setLayout(new AbsoluteLayout());

        overviewBtns.setBackground(new Color(84, 127, 206));

        mailBtn.setIcon(new ImageIcon(getClass().getResource("/icons8_secured_letter_32px_3.png")));

        contactsBtn.setIcon(new ImageIcon(getClass().getResource("/icons8_contacts_32px.png")));

        calendarBtn.setIcon(new ImageIcon(getClass().getResource("/icons8_calendar_32px.png")));

        logoutBtn.setIcon(new ImageIcon(getClass().getResource("/icons8_lock_32px.png")));
        logoutBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mainScreen.logout();
            }
        });

        buttonSeparator.setBackground(new Color(255, 255, 255));
        buttonSeparator.setForeground(new Color(204, 204, 204));

        GroupLayout overviewBtnsLayout = new GroupLayout(overviewBtns);
        overviewBtns.setLayout(overviewBtnsLayout);
        overviewBtnsLayout.setHorizontalGroup(
                overviewBtnsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, overviewBtnsLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(overviewBtnsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(buttonSeparator)
                                        .addGroup(overviewBtnsLayout.createSequentialGroup()
                                                .addComponent(mailBtn)
                                                .addGap(37, 37, 37)
                                                .addComponent(contactsBtn)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                                .addComponent(calendarBtn)
                                                .addGap(36, 36, 36)
                                                .addComponent(logoutBtn)))
                                .addGap(37, 37, 37))
        );
        overviewBtnsLayout.setVerticalGroup(
                overviewBtnsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(overviewBtnsLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(overviewBtnsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(mailBtn)
                                        .addComponent(contactsBtn)
                                        .addComponent(calendarBtn)
                                        .addComponent(logoutBtn))
                                .addGap(18, 18, 18)
                                .addComponent(buttonSeparator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(76, Short.MAX_VALUE))
        );

        overviewProfile.add(overviewBtns, new AbsoluteConstraints(0, 111, -1, -1));

        exitBtn.setIcon(new ImageIcon(getClass().getResource("/icons8_exit_32px.png"))); // NOI18N
        exitBtn.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                exitBtnMousePressed();
            }
        });
        overviewProfile.add(exitBtn, new AbsoluteConstraints(270, 40, -1, -1));

        profileNameLabel.setFont(new Font("Dialog", Font.BOLD, 14)); // NOI18N
        profileNameLabel.setForeground(new Color(255, 255, 255));
        profileNameLabel.setText("John Doe");
        overviewProfile.add(profileNameLabel, new AbsoluteConstraints(110, 50, -1, -1));

        profilePicture.setIcon(new ImageIcon(getClass().getResource("/icons8_github_96px.png"))); // NOI18N
        profilePicture.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                profilePictureMousePressed();
            }
        });
        overviewProfile.add(profilePicture, new AbsoluteConstraints(0, 10, 100, 100));

        readableDayLabel.setForeground(new Color(255, 255, 255));
        readableDayLabel.setText("Wednesday 13");

        monthYearLabel.setForeground(new Color(255, 255, 255));
        monthYearLabel.setText("May 2020");

        registerTaskBtn.setText("Register Task");
        registerTaskBtn.addActionListener(this::registerTaskBtnActionPerformed);
        viewTaskBtn.setText("View Task");
        viewTaskBtn.addActionListener(this::viewTaskBtnActionPerformed);
        contactLeaderBtn.setText("Contact Leader");


        ComponentsConfigure.metroBtnConfig(registerTaskBtn);
        ComponentsConfigure.metroBtnConfig(viewTaskBtn);
        ComponentsConfigure.metroBtnConfig(contactLeaderBtn);

        GroupLayout overviewPaneLayout = new GroupLayout(overviewPane);
        overviewPane.setLayout(overviewPaneLayout);
        overviewPaneLayout.setHorizontalGroup(
                overviewPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(overviewProfile, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(overviewPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(overviewPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(viewTaskBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(overviewPaneLayout.createSequentialGroup()
                                                .addComponent(readableDayLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(monthYearLabel))
                                        .addComponent(contactLeaderBtn, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(registerTaskBtn, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        overviewPaneLayout.setVerticalGroup(
                overviewPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(overviewPaneLayout.createSequentialGroup()
                                .addComponent(overviewProfile, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addGroup(overviewPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(readableDayLabel)
                                        .addComponent(monthYearLabel))
                                .addGap(18, 18, 18)
                                .addComponent(registerTaskBtn, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(viewTaskBtn, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(contactLeaderBtn, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(244, Short.MAX_VALUE))
        );

        add(overviewPane, new AbsoluteConstraints(0, 0, 310, 720));
        add(registerWorkTask, new AbsoluteConstraints(310, 0, -1, -1));

        workTaskListTable.setForeground(new Color(102, 102, 102));
        workTaskListTable.setModel(workTaskModel);
        workTaskListTable.setGridColor(new Color(255, 255, 255));
        workTaskListTable.setRowHeight(32);
        workTaskListTable.setSelectionBackground(new Color(0, 120, 215));
        jScrollPane1.setViewportView(workTaskListTable);

        add(jScrollPane1, new AbsoluteConstraints(310, 0, 690, 720));
    }

    private void viewTaskBtnActionPerformed(ActionEvent actionEvent) {
        jScrollPane1.setVisible(true);
        registerWorkTask.setVisible(false);
    }

    private void exitBtnMousePressed() {
        System.exit(0);
    }

    private void profilePictureMousePressed() {
        // TODO add your handling code here:
    }

    private void registerTaskBtnActionPerformed(ActionEvent evt) {
        jScrollPane1.setVisible(false);
        viewRegisterTask();

    }

    private void viewRegisterTask(){
        registerWorkTask.setVisible(true);
    }

    private JLabel calendarBtn;
    private JButton contactLeaderBtn;
    private JLabel contactsBtn;
    private JLabel exitBtn;
    private JScrollPane jScrollPane1;
    private JSeparator buttonSeparator;
    private JLabel logoutBtn;
    private JLabel mailBtn;
    private JLabel monthYearLabel;
    private JPanel overviewBtns;
    private JPanel overviewPane;
    private JPanel overviewProfile;
    private JLabel profileNameLabel;
    private JLabel profilePicture;
    private JLabel readableDayLabel;
    private JButton registerTaskBtn;
    private RegisterWorkTask registerWorkTask;
    private JButton viewTaskBtn;
    private JTable workTaskListTable;
    private WorkTaskDataModel workTaskModel;
    private WorkTaskCtrIF workTaskController;
}
