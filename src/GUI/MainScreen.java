package GUI;

import Controller.DataAccessException;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainScreen extends JFrame {
    
    public MainScreen() throws DataAccessException {
        initComponents();
        dashboard.setVisible(false);
        workSiteDashBoard.setVisible(false);
    }
    
    private void initComponents() throws DataAccessException {

        sidePanel = new JPanel();
        sidePanelBtnHome = new JPanel();
        ind = new JPanel();
        homeBtnLabel = new JLabel();
        sidePanelBtnUsers = new JPanel();
        ind1 = new JPanel();
        usersBtnLabel = new JLabel();
        sidePanelBtnProfile = new JPanel();
        ind2 = new JPanel();
        profileBtnLabel = new JLabel();
        sidePanelBtnSettings = new JPanel();
        ind3 = new JPanel();
        settingsBtnLabel = new JLabel();
        topBar = new JPanel();
        searchField = new JTextField();
        searchBtn = new JLabel();
        dashboard = new Dashboard(this);
        loginScreen = new LoginScreen(this);
        workSiteDashBoard = new WorkSiteDashboard(this);
        workersScreen = new WorkersScreen();

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


        ComponentsConfigure.indicatorConfig(new JPanel[] {ind,ind1,ind2,ind3});
        ind.setOpaque(true);
        ComponentsConfigure.topBarConfig(topBar,this, new Color(71,120,197));

        homeBtnLabel.setForeground(new Color(255, 255, 255));
        homeBtnLabel.setText("Home");

        sidePanelButtonPosition(sidePanelBtnHome, ind, homeBtnLabel);

        sidePanel.add(sidePanelBtnHome, new AbsoluteConstraints(0, 170, 200, 50));

        sidePanelBtnUsers.setBackground(new Color(23, 35, 51));
        sidePanelBtnUsers.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                try {
                    sidePanelBtnUsersMousePressed();
                    workersScreen.start();
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        usersBtnLabel.setForeground(new Color(255, 255, 255));
        usersBtnLabel.setText("Employees");

        sidePanelButtonPosition(sidePanelBtnUsers, ind1, usersBtnLabel);

        sidePanel.add(sidePanelBtnUsers, new AbsoluteConstraints(0, 220, -1, -1));

        sidePanelBtnProfile.setBackground(new Color(23, 35, 51));
        sidePanelBtnProfile.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                sidePanelBtnProfileMousePressed();
            }
        });

        profileBtnLabel.setForeground(new Color(255, 255, 255));
        profileBtnLabel.setText("Work sites");

        sidePanelButtonPosition(sidePanelBtnProfile, ind2, profileBtnLabel);

        sidePanel.add(sidePanelBtnProfile, new AbsoluteConstraints(0, 270, -1, -1));

        sidePanelBtnSettings.setBackground(new Color(23, 35, 51));
        sidePanelBtnSettings.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                sidePanelBtnSettingsMousePressed();
            }
        });

        settingsBtnLabel.setForeground(new Color(255, 255, 255));
        settingsBtnLabel.setText("Settings");

        sidePanelButtonPosition(sidePanelBtnSettings, ind3, settingsBtnLabel);

        sidePanel.add(sidePanelBtnSettings, new AbsoluteConstraints(0, 320, -1, -1));

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
        getContentPane().add(workSiteDashBoard, new AbsoluteConstraints(200, 90, -1, -1));

        setIconImage(new ImageIcon(getClass().getResource("/icons8_potted_plant_50px_1.png")).getImage());
        pack();
        setLocationRelativeTo(null);
    }

    private void sidePanelButtonPosition(JPanel sidePanelBtnHome, JPanel ind, JLabel homeBtnLabel) {
        GroupLayout sidePanelBtnHomeLayout = new GroupLayout(sidePanelBtnHome);
        sidePanelBtnHome.setLayout(sidePanelBtnHomeLayout);
        sidePanelBtnHomeLayout.setHorizontalGroup(
            sidePanelBtnHomeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelBtnHomeLayout.createSequentialGroup()
                .addComponent(ind, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(homeBtnLabel)
                .addGap(0, 130, Short.MAX_VALUE))
        );
        sidePanelBtnHomeLayout.setVerticalGroup(
            sidePanelBtnHomeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(ind, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidePanelBtnHomeLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(homeBtnLabel)
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }

    private void searchFieldActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void sidePanelBtnHomeMousePressed() {
        setColor(sidePanelBtnHome);
        ind.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnUsers,sidePanelBtnProfile,sidePanelBtnSettings},new JPanel[]{ind1,ind2,ind3});
    }

    private void sidePanelBtnUsersMousePressed() throws DataAccessException {
        setColor(sidePanelBtnUsers);
        ind1.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnHome,sidePanelBtnProfile,sidePanelBtnSettings},new JPanel[]{ind,ind2,ind3});
    }

    private void sidePanelBtnProfileMousePressed() {
        setColor(sidePanelBtnProfile);
        ind2.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnUsers,sidePanelBtnHome,sidePanelBtnSettings},new JPanel[]{ind1,ind,ind3});
    }

    private void sidePanelBtnSettingsMousePressed() {
        setColor(sidePanelBtnSettings);
        ind3.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnUsers,sidePanelBtnProfile,sidePanelBtnHome},new JPanel[]{ind1,ind2,ind});
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

    private void resetColor(JPanel[] pane, JPanel[] indicators){
        for(JPanel panel : pane){
            panel.setBackground(new Color(23,35,51));
        }
        for(JPanel indicator : indicators){
            indicator.setOpaque(false);
        }
    }

    public void login(){
        toggleLogin();
        toggleDashboard();
    }

    public void logout(){
        toggleLogin();
        toggleDashboard();
    }

    private void toggleDashboard(){
        dashboard.setVisible(!dashboard.isVisible());
    }

    private void toggleLogin(){
        loginScreen.setVisible(!loginScreen.isVisible());
    }
    
    private Dashboard dashboard;
    private JPanel topBar;
    private JLabel homeBtnLabel;
    private JPanel ind;
    private JPanel ind1;
    private JPanel ind2;
    private JPanel ind3;
    private LoginScreen loginScreen;
    private JLabel profileBtnLabel;
    private JLabel searchBtn;
    private JTextField searchField;
    private JLabel settingsBtnLabel;
    private JPanel sidePanel;
    private JPanel sidePanelBtnHome;
    private JPanel sidePanelBtnProfile;
    private JPanel sidePanelBtnSettings;
    private JPanel sidePanelBtnUsers;
    private JLabel usersBtnLabel;
    private WorkSiteDashboard workSiteDashBoard;
    private WorkersScreen workersScreen;
}
