package GUI;

import Controller.DataModel.WorkTaskDataModel;
import Controller.WorkTaskCtr;
import Controller.WorkTaskCtrIF;
import DB.DataAccessException;
import GUI.Components.ComponentsConfigure;
import Model.SeasonalWorker;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

/**
 * A view extending JPanel. Is the first screen encountered after login, displays current work tasks.
 *
 * @author Daniel Zoltan Ban
 * @author Mikuláš Dobrodej
 * @author Adrian Mihai Dohot
 * @author Damian Hrabąszcz
 * @author Toms Vanders
 * @version 1.0 (29.05.2020)
 *
 * Date: 29.05.2020
 */
public class Dashboard extends JPanel {
    private final MainScreen mainScreen;
    private RegisterWorkTask registerWorkTask;
    private WorkTaskDataModel workTaskModel;
    private final SeasonalWorker currentWorker;
    private JPanel subDashboard;
    static final String WorkListScreen = "WorkListScreen";
    static final String RegisterTask = "RegisterTask";

    /**
     * Constructor for the dashboard
     * @param mainScreen MainScreen instance for navigation
     * @param currentWorker SeasonalWorker object to retrieve information
     * @throws DataAccessException Thrown if there is no connection or information is incorrect within the SeasonalWorker object
     */
    public Dashboard(MainScreen mainScreen,SeasonalWorker currentWorker) throws DataAccessException {
        this.currentWorker = currentWorker;
        this.mainScreen = mainScreen;
        try {
            initComponents();
            registerWorkTask.setVisible(false);
        } catch (DataAccessException e) {
            System.err.println("Issue obtaining connection.");
//            e.printStackTrace();
            // Alert the user here with e.g JDialog saying there was an issue connecting to the database.
            // TODO
            // Add a refresh button.
        }

    }

    /**
     * Initialize all components and layouts part of the panel.
     * @throws DataAccessException Thrown if there is no connection or information is incorrect within the SeasonalWorker object
     */
    private void initComponents() throws DataAccessException {
        JPanel overviewPane = new JPanel();
        JPanel overviewProfile = new JPanel();
        JPanel overviewBtns = new JPanel();
        JLabel mailBtn = new JLabel();
        JLabel contactsBtn = new JLabel();
        JLabel calendarBtn = new JLabel();
        JLabel logoutBtn = new JLabel();
        JSeparator buttonSeparator = new JSeparator();
        JLabel exitBtn = new JLabel();
        JLabel profileNameLabel = new JLabel();
        JLabel profilePicture = new JLabel();
        JButton viewTaskBtn = new JButton();
        JLabel readableDayLabel = new JLabel();
        JLabel monthYearLabel = new JLabel();
        JButton registerTaskBtn = new JButton();
        JButton contactLeaderBtn = new JButton();
        registerWorkTask = new RegisterWorkTask(this,mainScreen);
        JScrollPane workListView = new JScrollPane();
        JTable workTaskListTable = new JTable();
        JPopupMenu taskListPopupMenu = new JPopupMenu();
        JMenuItem taskAdd = new JMenuItem("Register new work task.");
        JMenuItem taskView = new JMenuItem("View work task");
        JMenuItem taskEdit = new JMenuItem("Edit work task");
        WorkTaskCtrIF workTaskController = new WorkTaskCtr();
        workTaskModel = new WorkTaskDataModel(
                new ArrayList<>(workTaskController.findAllWorkTasksOfWorker(true, "1451684849")),
                "1451684849");
        subDashboard = new JPanel();

        setEnabled(false);
        setMinimumSize(new Dimension(1000, 720));
        setPreferredSize(new Dimension(1000, 720));
        setLayout(new AbsoluteLayout());

        subDashboard.setLayout(new CardLayout());

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
        profileNameLabel.setText(currentWorker.getFname() + " " + currentWorker.getLname());
        overviewProfile.add(profileNameLabel, new AbsoluteConstraints(110, 50, -1, -1));

        profilePicture.setIcon(new ImageIcon(getClass().getResource("/icons8_github_96px.png"))); // NOI18N
        overviewProfile.add(profilePicture, new AbsoluteConstraints(0, 10, 100, 100));

        // Damian code
        Date date = new Date();
        LocalDate today = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        String dayName = today.getDayOfWeek().name().toLowerCase();
        String formattedDayName = dayName.substring(0, 1).toUpperCase() + dayName.substring(1);

        String monthName = today.getMonth().name().toLowerCase();
        String formattedMonthName = monthName.substring(0, 1).toUpperCase() + monthName.substring(1);

        readableDayLabel.setForeground(new Color(255, 255, 255));
        readableDayLabel.setText(formattedDayName + " " + today.getDayOfMonth());

        monthYearLabel.setForeground(new Color(255, 255, 255));
        monthYearLabel.setText(formattedMonthName + " " + today.getYear());

        registerTaskBtn.setText("Register Task");
        registerTaskBtn.addActionListener(this::registerTaskBtnActionPerformed);
        viewTaskBtn.setText("View Task");
        viewTaskBtn.addActionListener((e) -> {
            try {
                ((CardLayout) subDashboard.getLayout()).show(subDashboard,WorkListScreen);
                workTaskModel.updateData();
            } catch (DataAccessException dataAccessException) {
                dataAccessException.printStackTrace();
            }
        });
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

        workTaskListTable.setForeground(new Color(102, 102, 102));
        workTaskListTable.setModel(workTaskModel);
        workTaskListTable.setGridColor(new Color(255, 255, 255));
        workTaskListTable.setRowHeight(32);
        workTaskListTable.setSelectionBackground(new Color(0, 120, 215));
        workListView.setViewportView(workTaskListTable);
        taskListPopupMenu.add(taskAdd);
        taskListPopupMenu.add(taskView);
        taskListPopupMenu.add(taskEdit);
        workTaskListTable.setComponentPopupMenu(taskListPopupMenu);
        taskAdd.addActionListener(this::registerTaskBtnActionPerformed);
        taskView.addActionListener((l) ->{

        });
        taskEdit.addActionListener((l) ->{

        });
        workTaskListTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int currentRow = workTaskListTable.rowAtPoint(point);
                workTaskListTable.setRowSelectionInterval(currentRow,currentRow);
            }
        });

        add(overviewPane, new AbsoluteConstraints(0, 0, 310, 720));
        add(subDashboard, new AbsoluteConstraints(310, 0, -1, -1));
        subDashboard.add(workListView, WorkListScreen);
        subDashboard.add(registerWorkTask, RegisterTask);
    }

    /**
     * ActionListener method for the exit button
     * used to quit from the application
     */
    private void exitBtnMousePressed() {
        System.exit(0);
    }

    /**
     * ActionListener method for displaying the register task panel
     */
    private void registerTaskBtnActionPerformed(ActionEvent e) {
        ((CardLayout) subDashboard.getLayout()).show(subDashboard,RegisterTask);
    }

    /**
     * ActionListener method to update the task list table and return the view for the user
     * @throws DataAccessException Thrown if there is no connection
     */
    void showTaskListView() throws DataAccessException {
        workTaskModel.updateData();
        ((CardLayout) subDashboard.getLayout()).show(subDashboard,WorkListScreen);
    }
}
