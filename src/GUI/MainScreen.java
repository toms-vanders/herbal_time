package GUI;

import Controller.DataAccessException;
import GUI.DBStatus.StatusThread;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainScreen extends JFrame {

    public MainScreen() throws DataAccessException {
        initComponents();
        StatusThread sT = new StatusThread();
        sT.start();
        dashboard.setVisible(false);
        workSiteDashboard.setVisible(false);
        createWorkSite.setVisible(false);
    }

    private ImageIcon connectionStatus() {
//        String statusIcon = true ? "/icons8_connection_status_on_24px.png" : "/icons8_connection_status_off_24px_1.png";
        String statusIcon = "/icons8_connection_status_off_24px.png";
        connectionStatusText = "UNCHECKED";
        return new ImageIcon(getClass().getResource(statusIcon));
    }

    private void initComponents() throws DataAccessException {

        isLogged = false;

        JPanel sidePanel = new JPanel();
        sidePanelBtnHome = new JPanel();
        ind = new JPanel();
        JLabel homeBtnLabel = new JLabel();
        sidePanelBtnEmployees = new JPanel();
        ind1 = new JPanel();
        JLabel usersBtnLabel = new JLabel();
        sidePanelBtnWorkSites = new JPanel();
        ind2 = new JPanel();
        sidePanelBtnWorkers = new JPanel();
        ind4 = new JPanel();
        JLabel workSitesLabel = new JLabel();
        JLabel workersBtnLabel = new JLabel();
        sidePanelBtnSettings = new JPanel();
        ind3 = new JPanel();
        ind4 = new JPanel();
        JLabel settingsBtnLabel = new JLabel();
        JPanel topBar = new JPanel();
        JTextField searchField = new JTextField();
        JLabel searchBtn = new JLabel();
        dashboard = new Dashboard(this);
        loginScreen = new LoginScreen(this);
        workSiteDashboard = new WorkSiteDashboard(this);
        workersScreen = new WorkersScreen();
        employeeScreen = new EmployeeDatabaseScreen();
        JPanel statusBar = new JPanel();
//        JLabel connectionStatusIcon = new JLabel();
//        JLabel connectionStatusLabel = new JLabel();
        createWorkSite = new CreateWorkSite();
        navigation = new Stack<>();
        navigation.push(loginScreen);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Herbal Time");
        setAutoRequestFocus(false);
        setBackground(new Color(123, 156, 225));
        setMinimumSize(new Dimension(1200, 800));
        setUndecorated(true);
        setResizable(false);
        setSize(new Dimension(1200, 800));
        getContentPane().setLayout(new AbsoluteLayout());

        sidePanel.setBackground(new Color(23, 35, 51));
        sidePanel.setLayout(new AbsoluteLayout());

        sidePanelBtnHome.setBackground(new Color(41,57,80));
        sidePanelBtnHome.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                sidePanelBtnHomeMousePressed();
            }
        });


        ComponentsConfigure.indicatorConfig(new JPanel[] {ind,ind1,ind2,ind3,ind4});
        ind.setOpaque(true);
        ComponentsConfigure.topBarConfig(topBar,this, new Color(71,120,197));

        homeBtnLabel.setForeground(new Color(255, 255, 255));
        homeBtnLabel.setText("Home");

        sidePanelButtonPosition(sidePanelBtnHome, ind, homeBtnLabel);

        sidePanel.add(sidePanelBtnHome, new AbsoluteConstraints(0, 170, 200, 50));

        sidePanelBtnEmployees.setBackground(new Color(23, 35, 51));
        sidePanelBtnEmployees.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if(isLogged) {
                    sidePanelBtnEmployeesMousePressed();
                    employeeScreen.start();
                }
            }
        });

        usersBtnLabel.setForeground(new Color(255, 255, 255));
        usersBtnLabel.setText("Employees");

        sidePanelButtonPosition(sidePanelBtnEmployees, ind1, usersBtnLabel);

        sidePanel.add(sidePanelBtnEmployees, new AbsoluteConstraints(0, 220, -1, -1));

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

        sidePanelButtonPosition(sidePanelBtnWorkSites, ind2, workSitesLabel);

        sidePanel.add(sidePanelBtnWorkSites, new AbsoluteConstraints(0, 270, -1, -1));

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

        sidePanelButtonPosition(sidePanelBtnWorkers,ind4, workersBtnLabel);
        sidePanel.add(sidePanelBtnWorkers,new AbsoluteConstraints(0,320,-1,-1));
        
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

        sidePanelButtonPosition(sidePanelBtnSettings, ind3, settingsBtnLabel);

        sidePanel.add(sidePanelBtnSettings, new AbsoluteConstraints(0, 370, -1, -1));

        getContentPane().add(sidePanel, new AbsoluteConstraints(0, 0, 200, 810));

        searchField.setBackground(new Color(123, 156, 225));
        searchField.setForeground(new Color(255, 255, 255));
        searchField.setBorder(null);
        searchField.addActionListener(this::searchFieldActionPerformed);
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
        getContentPane().add(dashboard, new AbsoluteConstraints(200, 90, -1, -1));
        getContentPane().add(loginScreen, new AbsoluteConstraints(200, 90, -1, -1));
        getContentPane().add(workSiteDashboard, new AbsoluteConstraints(200, 90, -1, -1));
        getContentPane().add(createWorkSite,new AbsoluteConstraints(200,90,-1,-1));

        setIconImage(new ImageIcon(getClass().getResource("/icons8_potted_plant_50px_1.png")).getImage());
        pack();
        setLocationRelativeTo(null);
    }

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

    private void searchFieldActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void sidePanelBtnHomeMousePressed() {
        setColor(sidePanelBtnHome);
        ind.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnEmployees,sidePanelBtnWorkSites,sidePanelBtnSettings,sidePanelBtnWorkers},new JPanel[]{ind1,ind2,ind3,ind4});
        navigation.empty();
        setView(dashboard);
    }

    private void sidePanelBtnEmployeesMousePressed() {
        setColor(sidePanelBtnEmployees);
        ind1.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnHome,sidePanelBtnWorkSites,sidePanelBtnSettings,sidePanelBtnWorkers},new JPanel[]{ind,ind2,ind3,ind4});
    }

    private void sidePanelBtnWorkSitesMousePressed() {
        setColor(sidePanelBtnWorkSites);
        ind2.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnEmployees,sidePanelBtnHome,sidePanelBtnSettings,sidePanelBtnWorkers},new JPanel[]{ind,ind1,ind3,ind4});
        navigation.push(workSiteDashboard);
        setView(workSiteDashboard);
    }

    private void sidePanelBtnSettingsMousePressed() {
        new StatusDialog(this,true,"error","error2");
        setColor(sidePanelBtnSettings);
        ind3.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnEmployees,sidePanelBtnWorkSites,sidePanelBtnHome,sidePanelBtnWorkers},new JPanel[]{ind,ind1,ind2,ind4});
    }
    
    private void sidePanelBtnWorkersMousePressed() {
        setColor(sidePanelBtnWorkers);
        ind4.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnEmployees,sidePanelBtnWorkSites,sidePanelBtnHome,sidePanelBtnSettings},new JPanel[]{ind,ind1,ind2,ind3});
    }

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
        EventQueue.invokeLater(() -> {
            try {
                new MainScreen().setVisible(true);
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        });
    }

    private void setColor(JPanel pane){
        pane.setBackground(new Color(41,57,80));
    }

    private void resetColor(JPanel[] pane, JPanel[] indicators) {
        for (JPanel panel : pane) {
            panel.setBackground(new Color(23,35,51));
        }
        for (JPanel indicator : indicators) {
            indicator.setOpaque(false);
        }
    }

    public void setView(JPanel view) {

        if(!isLogged){
            return;
        }

        if (view.equals(loginScreen)) {
            navigation.forEach(x -> x.setVisible(false));
            loginScreen.setVisible(true);
        }
        else if (view.equals(dashboard)) {
            navigation.forEach(x -> x.setVisible(false));
            dashboard.setVisible(true);
        }
        else if (view.equals(workSiteDashboard)) {
            navigation.forEach(x -> x.setVisible(false));
            workSiteDashboard.setVisible(true);
        }
        else if (view.equals(createWorkSite)) {
            navigation.forEach(x -> x.setVisible(false));
            createWorkSite.setVisible(true);
        }
    }

    public void login() {
        isLogged = true;
        navigation.push(dashboard);
        setView(dashboard);
    }

    public void logout() {
        navigation.empty();
        navigation.push(loginScreen);
        setView(loginScreen);
        isLogged = false;
    }

    public void createWorkSite() {
        navigation.push(createWorkSite);
        setView(createWorkSite);
    }

    private Dashboard dashboard;
    private JPanel ind;
    private JPanel ind1;
    private JPanel ind2;
    private JPanel ind3;
    private JPanel ind4;
    private LoginScreen loginScreen;
    private JPanel sidePanelBtnHome;
    private JPanel sidePanelBtnWorkSites;
    private JPanel sidePanelBtnSettings;
    private JPanel sidePanelBtnEmployees;
    private JPanel sidePanelBtnWorkers;
    private WorkSiteDashboard workSiteDashboard;
    private WorkersScreen workersScreen;
    private EmployeeDatabaseScreen employeeScreen;
    private String connectionStatusText;
    private CreateWorkSite createWorkSite;

    private Stack<JPanel> navigation;
    private static boolean isLogged;

    // Status

    private static JLabel connectionStatusIcon = new JLabel();
    private static JLabel connectionStatusLabel = new JLabel();

    public static JLabel getConnectionStatusIcon() {
        return connectionStatusIcon;
    }
    public static void setConnectionStatusIcon(JLabel connectionStatusIcon) {
        MainScreen.connectionStatusIcon = connectionStatusIcon;
    }

    public static JLabel getConnectionStatusLabel() {
        return connectionStatusLabel;
    }
    public static void setConnectionStatusLabel(JLabel connectionStatusLabel) {
        MainScreen.connectionStatusLabel = connectionStatusLabel;
    }
}
