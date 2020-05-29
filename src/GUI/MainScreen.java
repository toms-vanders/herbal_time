package GUI;

import DB.DataAccessException;
import Controller.SeasonalWorkerCtr;
import Controller.SeasonalWorkerCtrIF;
import GUI.Components.BackgroundWorker;
import GUI.Components.ComponentsConfigure;
import GUI.Components.StatusDialog;
import GUI.DBStatus.StatusThread;
import Model.SeasonalWorker;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Application's main window, is the center of all action in the program.
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
public class MainScreen extends JFrame {

    // Navigation strings for the cardLayout
    final static String LoginView = "LOGINSCREEN";
    final static String DashboardScreen ="DASHBOARD";
    final static String WorkSiteDashboardScreen = "WORKSITEDASHBOARD";
    final static String ClientsScreen = "CLIENTS";
    final static String CreateWorkSiteScreen = "CREATEWORKSITE";
    final static String PendingTasksScreen = "PENDINGTASKS";

    // Declarations for the status bar
    private static JLabel connectionStatusIcon = new JLabel();
    private static JLabel connectionStatusLabel = new JLabel();
    private static StatusThread sT;

    // Declarations for the various frames and panels
    private static boolean isLogged;
    public static final String userCPR = "1451684849";
    private JPanel dashboardContainer;
    static LoginScreen loginScreen;
    static WorkSiteDashboard workSiteDashboard;
    static WorkersScreen workersScreen;
    static EmployeeDatabaseScreen employeeScreen;
    static CreateWorkSite createWorkSite;
    static ClientScreen clientScreen;
    static PendingTasks pendingTasks;
    private Dashboard dashboard;

    // Declarations for the MainScreen components
    private JPanel ind,ind1,ind2,ind3,ind4,ind5,ind6,ind7;
    private JPanel sidePanelBtnHome;
    private JPanel sidePanelBtnWorkSites;
    private JPanel sidePanelBtnSettings;
    private JPanel sidePanelBtnEmployees;
    private JPanel sidePanelBtnWorkers;
    private JPanel sidePanelBtnClients;
    private JPanel sidePanelBtnPending;
    private JPanel sidePanelBtnAbout;
    private String connectionStatusText;

    // Declarations for the controllers
    private SeasonalWorkerCtrIF seasonalWorkerController;
    private SeasonalWorker currentWorker;

    /**
     * MainScreen constructor initializing the components
     * StatusThread for connectivity
     * Visibility status for all other components part of the MainScreen.
     *
     * @throws DataAccessException if controllers cannot be instantiated
     */
    public MainScreen() throws DataAccessException {
        try {
            sT = new StatusThread();
            sT.start();
            setUndecorated(true);
            initComponents();
        } catch (DataAccessException e) {
            System.err.println("Issue obtaining connection.");
            new StatusDialog(this,true,StatusDialog.WARNING,"Internet connection is required.",
                    "The system is unable to connect to the internet. " +
                            "We are sorry for the inconvenience please try again later.");
//            e.printStackTrace();
            // Alert the user here with e.g JDialog saying there was an issue connecting to the database.
            // TODO
            // Add a refresh button.
        }
    }

    /**
     * Main thread for the frame to initialize the look and feel of the frame as well as
     * displaying it once everything has been created and added.
     *
     * @param args Java arguments array
     */
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        new BackgroundWorker(() -> {
            try {
                new MainScreen().setVisible(true);
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        },"Loading herbal time","Herbal time is loading, please wait.");
    }

    /**
     * Retrieves the current connection status icon.
     *
     * @return ImageIcon for the current connection status
     */
    public static JLabel getConnectionStatusIcon() {
        return connectionStatusIcon;
    }

    /**
     * Sets the current connection status icon.
     *
     * @param connectionStatusIcon ImageIcon to be shown
     */
    public static void setConnectionStatusIcon(JLabel connectionStatusIcon) {
        MainScreen.connectionStatusIcon = connectionStatusIcon;
    }

    /**
     * Retrieves the current connection status.
     *
     * @return JLabel of the current connection status
     */
    public static JLabel getConnectionStatusLabel() {
        return connectionStatusLabel;
    }

    /**
     * Sets the current connection status.
     *
     * @param connectionStatusLabel JLabel to be shown
     */
    public static void setConnectionStatusLabel(JLabel connectionStatusLabel) {
        MainScreen.connectionStatusLabel = connectionStatusLabel;
    }

    /**
     * Method is called only once during connection to establish the initial status bar.
     *
     * @return ImageIcon of the connection status upon initialization
     */
    private ImageIcon connectionStatus() {
        String statusIcon = "/icons8_connection_status_off_24px.png";
        connectionStatusText = "UNCHECKED";
        return new ImageIcon(getClass().getResource(statusIcon));
    }

    /**
     * Initialize all components and layouts part of the frame.
     *
     * @throws DataAccessException if controllers cannot be instantiated
     */
    private void initComponents() throws DataAccessException {

        isLogged = false;

        seasonalWorkerController = new SeasonalWorkerCtr();

        try {
            currentWorker = seasonalWorkerController.findSeasonalWorkerByCPR(userCPR);
        } catch (DataAccessException e) {
            throw new DataAccessException("Unable to retrieve seasonal worker from CPR", e);
        }

        JPanel sidePanel = new JPanel();
        ind = new JPanel();
        ind1 = new JPanel();
        ind2 = new JPanel();
        ind3 = new JPanel();
        ind4 = new JPanel();
        ind5 = new JPanel();
        ind6 = new JPanel();
        ind7 = new JPanel();
        sidePanelBtnHome = new JPanel();
        sidePanelBtnEmployees = new JPanel();
        sidePanelBtnClients = new JPanel();
        sidePanelBtnWorkers = new JPanel();
        sidePanelBtnPending = new JPanel();
        sidePanelBtnWorkSites = new JPanel();
        sidePanelBtnSettings = new JPanel();
        sidePanelBtnAbout = new JPanel();
        JLabel homeBtnLabel = new JLabel();
        JLabel employeeBtnLabel = new JLabel();
        JLabel workersBtnLabel = new JLabel();
        JLabel clientsBtnLabel = new JLabel();
        JLabel pendingBtnLabel = new JLabel();
        JLabel workSitesLabel = new JLabel();
        JLabel settingsBtnLabel = new JLabel();
        JLabel aboutBtnLabel = new JLabel();
        JPanel topBar = new JPanel();
        JPanel statusBar = new JPanel();
        JTextField searchField = new JTextField();
        JLabel searchBtn = new JLabel();
        dashboard = new Dashboard(this, currentWorker);
        loginScreen = new LoginScreen(this);
        workSiteDashboard = new WorkSiteDashboard(this);
        clientScreen = new ClientScreen(this);
        workersScreen = new WorkersScreen();
        employeeScreen = new EmployeeDatabaseScreen();
        pendingTasks = new PendingTasks(this);
        createWorkSite = new CreateWorkSite(this);
        dashboardContainer = new JPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Herbal Time");
        setAutoRequestFocus(false);
        setBackground(new Color(123, 156, 225));
        setMinimumSize(new Dimension(1200, 800));
        setResizable(false);
        setSize(new Dimension(1200, 800));
        getContentPane().setLayout(new AbsoluteLayout());

        sidePanel.setBackground(new Color(23, 35, 51));
        sidePanel.setLayout(new AbsoluteLayout());

        dashboardContainer.setLayout(new CardLayout());

        ComponentsConfigure.topBarConfig(topBar,this, new Color(71,120,197));

        statusBar.setBackground(new Color(23, 35, 51));
        statusBar.setMinimumSize(new Dimension(200, 25));
        statusBar.setPreferredSize(new Dimension(200, 25));

        connectionStatusIcon.setIcon(connectionStatus()); // NOI18N

        connectionStatusLabel.setForeground(new Color(255, 255, 255));
        connectionStatusLabel.setText(connectionStatusText);

        javax.swing.GroupLayout statusBarLayout = new javax.swing.GroupLayout(statusBar);
        statusBar.setLayout(statusBarLayout);
        statusBarLayout.setHorizontalGroup(
                statusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(statusBarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(connectionStatusIcon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(connectionStatusLabel)
                                .addContainerGap())
        );
        statusBarLayout.setVerticalGroup(
                statusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(statusBarLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(statusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(connectionStatusIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(connectionStatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(8, 8, 8))
        );

        sidePanel.add(statusBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 770, 200, 40));

        // Side panel buttons configuration
        ComponentsConfigure.indicatorConfig(new JPanel[] {ind,ind1,ind2,ind3,ind4,ind5,ind6,ind7});

        sidePanelBtnHome.setBackground(new Color(41,57,80));
        sidePanelBtnHome.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                sidePanelBtnHomeMousePressed();
            }
        });

        homeBtnLabel.setForeground(new Color(255, 255, 255));
        homeBtnLabel.setText("Home");

        ind.setOpaque(true);

        sidePanelBtnEmployees.setBackground(new Color(23, 35, 51));
        sidePanelBtnEmployees.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if(isLogged) {
                    sidePanelBtnEmployeesMousePressed();
                    employeeScreen.start();
                }
            }
        });

        employeeBtnLabel.setForeground(new Color(255, 255, 255));
        employeeBtnLabel.setText("Employees");

        sidePanelBtnWorkSites.setBackground(new Color(23, 35, 51));
        sidePanelBtnWorkSites.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if(isLogged){
                    sidePanelBtnWorkSitesMousePressed();
                }
            }
        });

        workSitesLabel.setForeground(new Color(255, 255, 255));
        workSitesLabel.setText("Work sites");

        sidePanelBtnWorkers.setBackground(new Color(23,35,51));
        sidePanelBtnWorkers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isLogged){
                    sidePanelBtnWorkersMousePressed();
                    workersScreen.start();
                }
            }
        });

        workersBtnLabel.setForeground(Color.white);
        workersBtnLabel.setText("Workers");

        sidePanelBtnSettings.setBackground(new Color(23, 35, 51));
        sidePanelBtnSettings.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if(isLogged){
                    sidePanelBtnSettingsMousePressed();
                }
            }
        });

        settingsBtnLabel.setForeground(new Color(255, 255, 255));
        settingsBtnLabel.setText("Settings");

        sidePanelBtnClients.setBackground(new Color(23, 35, 51));
        sidePanelBtnClients.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isLogged){
                    sidePanelBtnClientsMousePressed();
                }
            }
        });

        clientsBtnLabel.setForeground(Color.WHITE);
        clientsBtnLabel.setText("Clients");

        sidePanelBtnPending.setBackground(new Color(23,35,51));
        sidePanelBtnPending.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isLogged) {
                    sidePanelBtnPendingMousePressed();
                }
            }
        });

        pendingBtnLabel.setForeground(Color.WHITE);
        pendingBtnLabel.setText("Pending tasks");

        sidePanelBtnAbout.setBackground(new Color(23,35,51));
        sidePanelBtnAbout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isLogged) {
                    sidePanelBtnAboutMousePressed();
                }
            }
        });

        aboutBtnLabel.setForeground(Color.WHITE);
        aboutBtnLabel.setText("About");

        sidePanelButtonPosition(sidePanelBtnHome, ind, homeBtnLabel);
        sidePanelButtonPosition(sidePanelBtnEmployees, ind1, employeeBtnLabel);
        sidePanelButtonPosition(sidePanelBtnWorkSites, ind2, workSitesLabel);
        sidePanelButtonPosition(sidePanelBtnSettings, ind3, settingsBtnLabel);
        sidePanelButtonPosition(sidePanelBtnWorkers,ind4, workersBtnLabel);
        sidePanelButtonPosition(sidePanelBtnClients,ind5,clientsBtnLabel);
        sidePanelButtonPosition(sidePanelBtnPending, ind6,pendingBtnLabel);
        sidePanelButtonPosition(sidePanelBtnAbout, ind7, aboutBtnLabel);

        sidePanel.add(sidePanelBtnHome, new AbsoluteConstraints(0, 100, 200, 50));
        sidePanel.add(sidePanelBtnEmployees, new AbsoluteConstraints(0, 150, -1, -1));
        sidePanel.add(sidePanelBtnClients,new AbsoluteConstraints(0,200,-1,-1));
        sidePanel.add(sidePanelBtnWorkers,new AbsoluteConstraints(0,250,-1,-1));
        sidePanel.add(sidePanelBtnPending, new AbsoluteConstraints(0,300,-1,-1));
        sidePanel.add(sidePanelBtnWorkSites, new AbsoluteConstraints(0, 350, -1, -1));
        sidePanel.add(sidePanelBtnSettings, new AbsoluteConstraints(0, 400, -1, -1));
        sidePanel.add(sidePanelBtnAbout, new AbsoluteConstraints(0,450,-1,-1));



        getContentPane().add(sidePanel, new AbsoluteConstraints(0, 0, 200, 810));

        searchField.setBackground(new Color(123, 156, 225));
        searchField.setForeground(new Color(255, 255, 255));
        searchField.setBorder(null);
        searchField.setUI(new JTextFieldHintUI("Search..",Color.GRAY));

        searchBtn.setIcon(new ImageIcon(getClass().getResource("/icons8_search_32px.png"))); // NOI18N

        GroupLayout topBarLayout = new GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
            topBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, topBarLayout.createSequentialGroup()
                .addContainerGap(742, Short.MAX_VALUE)
                .addComponent(searchField, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchBtn)
                .addGap(12, 12, 12))
        );
        topBarLayout.setVerticalGroup(
            topBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(topBarLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(topBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(searchField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        getContentPane().add(topBar, new AbsoluteConstraints(200, 0, 1000, 90));
        getContentPane().add(dashboardContainer, new AbsoluteConstraints(200,90,-1,-1));
        dashboardContainer.add(dashboard, DashboardScreen);
        dashboardContainer.add(loginScreen,LoginView);
        dashboardContainer.add(workSiteDashboard,WorkSiteDashboardScreen);
        dashboardContainer.add(createWorkSite,CreateWorkSiteScreen);
        dashboardContainer.add(clientScreen,ClientsScreen);
        dashboardContainer.add(pendingTasks,PendingTasksScreen);
        ((CardLayout) dashboardContainer.getLayout()).show(dashboardContainer, LoginView);

        setIconImage(ComponentsConfigure.plantIcon.getImage());
        requestFocus();
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Configure the side panel buttons position and layout based on predefined variables
     * @param sidePanelBtn JPanel to be designed and positioned as a side panel button
     * @param ind JPanel indicator to denote the current selected button
     * @param sidePanelBtnLabel JLabel for the text of the button
     */
    private void sidePanelButtonPosition(JPanel sidePanelBtn, JPanel ind, JLabel sidePanelBtnLabel) {
        GroupLayout sidePanelLayout = new GroupLayout(sidePanelBtn);
        sidePanelBtn.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addComponent(ind, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(sidePanelBtnLabel)
                .addGap(0, 130, Short.MAX_VALUE))
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(ind, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(sidePanelBtnLabel)
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }

    /**
     * MouseEvent to detect when a side panel has been pressed.
     * Once pressed the button is toggled and displayed as such with a different colour and active indicator.
     * Other side panel buttons that were previously active are cycled through and toggled off as well as their
     * indicators. Finally the current view of the user is changed to the one that the button is assigned to through
     * the use of the navigation stack.
     */
    private void sidePanelBtnHomeMousePressed() {
        setColor(sidePanelBtnHome);
        ind.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnEmployees,sidePanelBtnWorkSites,sidePanelBtnSettings,sidePanelBtnWorkers,sidePanelBtnClients,sidePanelBtnAbout,sidePanelBtnPending},
                new JPanel[]{ind1,ind2,ind3,ind4,ind5,ind6,ind7});
        ((CardLayout) dashboardContainer.getLayout()).show(dashboardContainer,DashboardScreen);
    }

    /**
     * MouseEvent to detect when a side panel has been pressed.
     * Once pressed the button is toggled and displayed as such with a different colour and active indicator.
     * Other side panel buttons that were previously active are cycled through and toggled off as well as their
     * indicators. Finally the current view of the user is changed to the one that the button is assigned to through
     * the use of the navigation stack.
     */
    private void sidePanelBtnEmployeesMousePressed() {
        setColor(sidePanelBtnEmployees);
        ind1.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnHome,sidePanelBtnWorkSites,sidePanelBtnSettings,sidePanelBtnWorkers,sidePanelBtnClients,sidePanelBtnAbout,sidePanelBtnPending},
                new JPanel[]{ind,ind2,ind3,ind4,ind5,ind6,ind7});
    }

    /**
     * MouseEvent to detect when a side panel has been pressed.
     * Once pressed the button is toggled and displayed as such with a different colour and active indicator.
     * Other side panel buttons that were previously active are cycled through and toggled off as well as their
     * indicators. Finally the current view of the user is changed to the one that the button is assigned to through
     * the use of the navigation stack.
     */
    private void sidePanelBtnWorkSitesMousePressed() {
        setColor(sidePanelBtnWorkSites);
        ind2.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnEmployees,sidePanelBtnHome,sidePanelBtnSettings,sidePanelBtnWorkers,sidePanelBtnClients,sidePanelBtnAbout,sidePanelBtnPending},
                new JPanel[]{ind,ind1,ind3,ind4,ind5,ind6,ind7});
        showWorkSiteDashboard();
    }

    /**
     * MouseEvent to detect when a side panel has been pressed.
     * Once pressed the button is toggled and displayed as such with a different colour and active indicator.
     * Other side panel buttons that were previously active are cycled through and toggled off as well as their
     * indicators. Finally the current view of the user is changed to the one that the button is assigned to through
     * the use of the navigation stack.
     */
    private void sidePanelBtnSettingsMousePressed() {
        new StatusDialog(this,true, StatusDialog.WARNING,"Not implemented yet.",
                "Settings are not yet implemented.");
        setColor(sidePanelBtnSettings);
        ind3.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnEmployees,sidePanelBtnWorkSites,sidePanelBtnHome,sidePanelBtnWorkers,sidePanelBtnClients,sidePanelBtnAbout,sidePanelBtnPending},
                new JPanel[]{ind,ind1,ind2,ind4,ind5,ind6,ind7});
    }

    /**
     * MouseEvent to detect when a side panel has been pressed.
     * Once pressed the button is toggled and displayed as such with a different colour and active indicator.
     * Other side panel buttons that were previously active are cycled through and toggled off as well as their
     * indicators. Finally the current view of the user is changed to the one that the button is assigned to through
     * the use of the navigation stack.
     */
    private void sidePanelBtnWorkersMousePressed() {
        setColor(sidePanelBtnWorkers);
        ind4.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnEmployees,sidePanelBtnWorkSites,sidePanelBtnHome,sidePanelBtnSettings,sidePanelBtnClients,sidePanelBtnAbout,sidePanelBtnPending},
                new JPanel[]{ind,ind1,ind2,ind3,ind5,ind6,ind7});
    }

    /**
     * MouseEvent to detect when a side panel has been pressed.
     * Once pressed the button is toggled and displayed as such with a different colour and active indicator.
     * Other side panel buttons that were previously active are cycled through and toggled off as well as their
     * indicators. Finally the current view of the user is changed to the one that the button is assigned to through
     * the use of the navigation stack.
     */
    private void sidePanelBtnClientsMousePressed(){
        setColor(sidePanelBtnClients);
        ind5.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnEmployees,sidePanelBtnWorkSites,sidePanelBtnHome,sidePanelBtnSettings,sidePanelBtnWorkers,sidePanelBtnAbout,sidePanelBtnPending},
                new JPanel[]{ind,ind1,ind2,ind3,ind4,ind6,ind7});
        ((CardLayout) dashboardContainer.getLayout()).show(dashboardContainer,ClientsScreen);
    }

    /**
     * MouseEvent to detect when a side panel has been pressed.
     * Once pressed the button is toggled and displayed as such with a different colour and active indicator.
     * Other side panel buttons that were previously active are cycled through and toggled off as well as their
     * indicators. Finally the current view of the user is changed to the one that the button is assigned to through
     * the use of the navigation stack.
     */
    private void sidePanelBtnPendingMousePressed() {
        setColor(sidePanelBtnPending);
        ind6.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnEmployees,sidePanelBtnWorkSites,sidePanelBtnHome,sidePanelBtnSettings,sidePanelBtnWorkers,sidePanelBtnAbout,sidePanelBtnClients},
                new JPanel[]{ind,ind1,ind2,ind3,ind4,ind5,ind7});
        ((CardLayout) dashboardContainer.getLayout()).show(dashboardContainer,PendingTasksScreen);
    }

    /**
     * MouseEvent to detect when a side panel has been pressed.
     * Once pressed the button is toggled and displayed as such with a different colour and active indicator.
     * Other side panel buttons that were previously active are cycled through and toggled off as well as their
     * indicators. Finally the current view of the user is changed to the one that the button is assigned to through
     * the use of the navigation stack.
     */
    private void sidePanelBtnAboutMousePressed() {
        setColor(sidePanelBtnAbout);
        ind7.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnEmployees,sidePanelBtnWorkSites,sidePanelBtnHome,sidePanelBtnSettings,sidePanelBtnWorkers,sidePanelBtnPending,sidePanelBtnClients},
                new JPanel[]{ind,ind1,ind2,ind3,ind4,ind5,ind6});
        new StatusDialog(this,true,StatusDialog.ABOUT,"About",
                "The icons used within this software are property of their respective creators " +
                        "in this case https://icons8.com ." +
                "The icons have been used under their free license.");
    }

    /**
     * Change the color of the MouseEvent pressed panel to show it has been selected.
     *
     * @param pane JPanel to modify the color of.
     */
    private void setColor(JPanel pane){
        pane.setBackground(new Color(41,57,80));
    }

    /**
     * Iterates over the two arrays of panels resetting colors to their standard "off" and disables indicators
     * visibility.
     * @param pane JPanels to be reset to their original color
     * @param indicators JPanel indicators to be set invisible
     */
    private void resetColor(JPanel[] pane, JPanel[] indicators) {
        for (JPanel panel : pane) {
            panel.setBackground(new Color(23,35,51));
        }
        for (JPanel indicator : indicators) {
            indicator.setOpaque(false);
        }
    }

    /**
     * Navigation method which peeks at the top of the stack identifying specific views from which to return
     * otherwise it goes back one view through which the user has been through.
     */
    public void returnNav(){
        ((CardLayout) dashboardContainer.getLayout()).show(dashboardContainer,DashboardScreen);
    }

    /**
     * Stub of login method initializing the navigation stack and allowing the user to access other views.
     */
    public void login() {
        isLogged = true;
        ((CardLayout) dashboardContainer.getLayout()).show(dashboardContainer,DashboardScreen);
    }

    /**
     * Stub of log-out method that empties the navigation stack, disables user control over the GUI and returns the user
     * to the login screen where they may authenticate again or close the application.
     */
    public void logout() {
        ((CardLayout) dashboardContainer.getLayout()).show(dashboardContainer,LoginView);
        isLogged = false;
    }

    /**
     * Method to display the create worksite panel and push the view to the navigation stack.
     */
    public void showCreateWorkSite() {
        ((CardLayout) dashboardContainer.getLayout()).show(dashboardContainer,CreateWorkSiteScreen);
    }

    public void showWorkSiteDashboard(){
        ((CardLayout) dashboardContainer.getLayout()).show(dashboardContainer,WorkSiteDashboardScreen);
    }
}
