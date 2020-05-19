package GUI;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainScreen extends JFrame {
    
    public MainScreen() {
        initComponents();
        dashboard.setVisible(false);
        workSiteDashBoard.setVisible(false);
    }
    
    private void initComponents() {

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
        header = new JPanel();
        searchField = new JTextField();
        searchBtn = new JLabel();
        dashboard = new Dashboard(this);
        loginScreen = new loginScreen(this);
        workSiteDashBoard = new WorkSiteDashboard(this);

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
            public void mousePressed(MouseEvent evt) {
                sidePanelBtnHomeMousePressed(evt);
            }
        });

        ind.setBackground(new Color(204, 204, 204));
        ind.setOpaque(true);
        ind.setPreferredSize(new Dimension(3, 50));

        GroupLayout indLayout = new GroupLayout(ind);
        ind.setLayout(indLayout);
        indLayout.setHorizontalGroup(
            indLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        indLayout.setVerticalGroup(
            indLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        homeBtnLabel.setForeground(new Color(255, 255, 255));
        homeBtnLabel.setText("Home");

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

        sidePanel.add(sidePanelBtnHome, new AbsoluteConstraints(0, 170, 200, 50));

        sidePanelBtnUsers.setBackground(new Color(23, 35, 51));
        sidePanelBtnUsers.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                sidePanelBtnUsersMousePressed(evt);
            }
        });

        ind1.setBackground(new Color(204, 204, 204));
        ind1.setOpaque(false);
        ind1.setPreferredSize(new Dimension(3, 50));

        GroupLayout ind1Layout = new GroupLayout(ind1);
        ind1.setLayout(ind1Layout);
        ind1Layout.setHorizontalGroup(
            ind1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind1Layout.setVerticalGroup(
            ind1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        usersBtnLabel.setForeground(new Color(255, 255, 255));
        usersBtnLabel.setText("Employees");

        GroupLayout sidePanelBtnUsersLayout = new GroupLayout(sidePanelBtnUsers);
        sidePanelBtnUsers.setLayout(sidePanelBtnUsersLayout);
        sidePanelBtnUsersLayout.setHorizontalGroup(
            sidePanelBtnUsersLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelBtnUsersLayout.createSequentialGroup()
                .addComponent(ind1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(usersBtnLabel)
                .addGap(0, 130, Short.MAX_VALUE))
        );
        sidePanelBtnUsersLayout.setVerticalGroup(
            sidePanelBtnUsersLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(ind1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidePanelBtnUsersLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(usersBtnLabel)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        sidePanel.add(sidePanelBtnUsers, new AbsoluteConstraints(0, 220, -1, -1));

        sidePanelBtnProfile.setBackground(new Color(23, 35, 51));
        sidePanelBtnProfile.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                sidePanelBtnProfileMousePressed(evt);
            }
        });

        ind2.setBackground(new Color(204, 204, 204));
        ind2.setOpaque(false);
        ind2.setPreferredSize(new Dimension(3, 50));

        GroupLayout ind2Layout = new GroupLayout(ind2);
        ind2.setLayout(ind2Layout);
        ind2Layout.setHorizontalGroup(
            ind2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind2Layout.setVerticalGroup(
            ind2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        profileBtnLabel.setForeground(new Color(255, 255, 255));
        profileBtnLabel.setText("Work sites");

        GroupLayout sidePanelBtnProfileLayout = new GroupLayout(sidePanelBtnProfile);
        sidePanelBtnProfile.setLayout(sidePanelBtnProfileLayout);
        sidePanelBtnProfileLayout.setHorizontalGroup(
            sidePanelBtnProfileLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelBtnProfileLayout.createSequentialGroup()
                .addComponent(ind2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(profileBtnLabel)
                .addGap(0, 130, Short.MAX_VALUE))
        );
        sidePanelBtnProfileLayout.setVerticalGroup(
            sidePanelBtnProfileLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(ind2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidePanelBtnProfileLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(profileBtnLabel)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        sidePanel.add(sidePanelBtnProfile, new AbsoluteConstraints(0, 270, -1, -1));

        sidePanelBtnSettings.setBackground(new Color(23, 35, 51));
        sidePanelBtnSettings.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                sidePanelBtnSettingsMousePressed(evt);
            }
        });

        ind3.setBackground(new Color(204, 204, 204));
        ind3.setOpaque(false);
        ind3.setPreferredSize(new Dimension(3, 50));

        GroupLayout ind3Layout = new GroupLayout(ind3);
        ind3.setLayout(ind3Layout);
        ind3Layout.setHorizontalGroup(
            ind3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind3Layout.setVerticalGroup(
            ind3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        settingsBtnLabel.setForeground(new Color(255, 255, 255));
        settingsBtnLabel.setText("Settings");

        GroupLayout sidePanelBtnSettingsLayout = new GroupLayout(sidePanelBtnSettings);
        sidePanelBtnSettings.setLayout(sidePanelBtnSettingsLayout);
        sidePanelBtnSettingsLayout.setHorizontalGroup(
            sidePanelBtnSettingsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelBtnSettingsLayout.createSequentialGroup()
                .addComponent(ind3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(settingsBtnLabel)
                .addGap(0, 130, Short.MAX_VALUE))
        );
        sidePanelBtnSettingsLayout.setVerticalGroup(
            sidePanelBtnSettingsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(ind3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidePanelBtnSettingsLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(settingsBtnLabel)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        sidePanel.add(sidePanelBtnSettings, new AbsoluteConstraints(0, 320, -1, -1));

        getContentPane().add(sidePanel, new AbsoluteConstraints(0, 0, 200, 810));

        header.setBackground(new Color(71, 120, 197));
        header.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });
        header.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                headerMousePressed(evt);
            }
        });

        searchField.setBackground(new Color(123, 156, 225));
        searchField.setForeground(new Color(255, 255, 255));
        searchField.setBorder(null);
        searchField.addActionListener(this::searchFieldActionPerformed);

        searchBtn.setIcon(new ImageIcon(getClass().getResource("/icons8_search_32px.png"))); // NOI18N

        GroupLayout headerLayout = new GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(742, Short.MAX_VALUE)
                .addComponent(searchField, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchBtn)
                .addGap(12, 12, 12))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(headerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(searchField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        getContentPane().add(header, new AbsoluteConstraints(200, 0, 1000, 90));
        getContentPane().add(dashboard, new AbsoluteConstraints(200, 90, -1, -1));
        getContentPane().add(loginScreen, new AbsoluteConstraints(200, 90, -1, -1));
        getContentPane().add(workSiteDashBoard, new AbsoluteConstraints(200, 90, -1, -1));

        setIconImage(new ImageIcon(getClass().getResource("/icons8_potted_plant_50px_1.png")).getImage());
        pack();
        setLocationRelativeTo(null);
    }

    private void searchFieldActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void sidePanelBtnHomeMousePressed(MouseEvent evt) {
        setColor(sidePanelBtnHome);
        ind.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnUsers,sidePanelBtnProfile,sidePanelBtnSettings},new JPanel[]{ind1,ind2,ind3});
    }

    private void sidePanelBtnUsersMousePressed(MouseEvent evt) {
        setColor(sidePanelBtnUsers);
        ind1.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnHome,sidePanelBtnProfile,sidePanelBtnSettings},new JPanel[]{ind,ind2,ind3});
    }

    private void sidePanelBtnProfileMousePressed(MouseEvent evt) {
        setColor(sidePanelBtnProfile);
        ind2.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnUsers,sidePanelBtnHome,sidePanelBtnSettings},new JPanel[]{ind1,ind,ind3});
    }

    private void sidePanelBtnSettingsMousePressed(MouseEvent evt) {
        setColor(sidePanelBtnSettings);
        ind3.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnUsers,sidePanelBtnProfile,sidePanelBtnHome},new JPanel[]{ind1,ind2,ind});
    }

    int xx,xy;
    private void headerMouseDragged(MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-xx,y-xy);
    }

    private void headerMousePressed(MouseEvent evt) {
        xx = evt.getX();
        xy = evt.getY();
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
        EventQueue.invokeLater(() -> new MainScreen().setVisible(true));
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
    private JPanel header;
    private JLabel homeBtnLabel;
    private JPanel ind;
    private JPanel ind1;
    private JPanel ind2;
    private JPanel ind3;
    private loginScreen loginScreen;
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
}
