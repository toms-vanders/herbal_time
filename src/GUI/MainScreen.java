package GUI;

import org.netbeans.lib.awtextra.AbsoluteConstraints;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.logging.Logger;
import javax.swing.*;

public class MainScreen extends javax.swing.JFrame {

    public MainScreen() {
        initComponents();
        toggleDashboard();
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
        loginScreen1 = new loginScreen(this);
        dashboard2 = new Dashboard(this);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Herbal Time");
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(123, 156, 225));
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(1200, 800));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(1200, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidePanel.setBackground(new java.awt.Color(23, 35, 51));
        sidePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidePanelBtnHome.setBackground(new Color(41,57,80));
        sidePanelBtnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sidePanelBtnHomeMousePressed(evt);
            }
        });

        ind.setBackground(new java.awt.Color(204, 204, 204));
        ind.setOpaque(true);
        ind.setPreferredSize(new java.awt.Dimension(3, 50));

        javax.swing.GroupLayout indLayout = new javax.swing.GroupLayout(ind);
        ind.setLayout(indLayout);
        indLayout.setHorizontalGroup(
            indLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        indLayout.setVerticalGroup(
            indLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        homeBtnLabel.setForeground(new java.awt.Color(255, 255, 255));
        homeBtnLabel.setText("Home");

        javax.swing.GroupLayout sidePanelBtnHomeLayout = new javax.swing.GroupLayout(sidePanelBtnHome);
        sidePanelBtnHome.setLayout(sidePanelBtnHomeLayout);
        sidePanelBtnHomeLayout.setHorizontalGroup(
            sidePanelBtnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelBtnHomeLayout.createSequentialGroup()
                .addComponent(ind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(homeBtnLabel)
                .addGap(0, 130, Short.MAX_VALUE))
        );
        sidePanelBtnHomeLayout.setVerticalGroup(
            sidePanelBtnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ind, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidePanelBtnHomeLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(homeBtnLabel)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        sidePanel.add(sidePanelBtnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 200, 50));

        sidePanelBtnUsers.setBackground(new java.awt.Color(23, 35, 51));
        sidePanelBtnUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sidePanelBtnUsersMousePressed(evt);
            }
        });

        ind1.setBackground(new java.awt.Color(204, 204, 204));
        ind1.setOpaque(false);
        ind1.setPreferredSize(new java.awt.Dimension(3, 50));

        javax.swing.GroupLayout ind1Layout = new javax.swing.GroupLayout(ind1);
        ind1.setLayout(ind1Layout);
        ind1Layout.setHorizontalGroup(
            ind1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind1Layout.setVerticalGroup(
            ind1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        usersBtnLabel.setForeground(new java.awt.Color(255, 255, 255));
        usersBtnLabel.setText("Users");

        javax.swing.GroupLayout sidePanelBtnUsersLayout = new javax.swing.GroupLayout(sidePanelBtnUsers);
        sidePanelBtnUsers.setLayout(sidePanelBtnUsersLayout);
        sidePanelBtnUsersLayout.setHorizontalGroup(
            sidePanelBtnUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelBtnUsersLayout.createSequentialGroup()
                .addComponent(ind1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(usersBtnLabel)
                .addGap(0, 130, Short.MAX_VALUE))
        );
        sidePanelBtnUsersLayout.setVerticalGroup(
            sidePanelBtnUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ind1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidePanelBtnUsersLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(usersBtnLabel)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        sidePanel.add(sidePanelBtnUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, -1, -1));

        sidePanelBtnProfile.setBackground(new java.awt.Color(23, 35, 51));
        sidePanelBtnProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sidePanelBtnProfileMousePressed(evt);
            }
        });

        ind2.setBackground(new java.awt.Color(204, 204, 204));
        ind2.setOpaque(false);
        ind2.setPreferredSize(new java.awt.Dimension(3, 50));

        javax.swing.GroupLayout ind2Layout = new javax.swing.GroupLayout(ind2);
        ind2.setLayout(ind2Layout);
        ind2Layout.setHorizontalGroup(
            ind2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind2Layout.setVerticalGroup(
            ind2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        profileBtnLabel.setForeground(new java.awt.Color(255, 255, 255));
        profileBtnLabel.setText("Profile");

        javax.swing.GroupLayout sidePanelBtnProfileLayout = new javax.swing.GroupLayout(sidePanelBtnProfile);
        sidePanelBtnProfile.setLayout(sidePanelBtnProfileLayout);
        sidePanelBtnProfileLayout.setHorizontalGroup(
            sidePanelBtnProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelBtnProfileLayout.createSequentialGroup()
                .addComponent(ind2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(profileBtnLabel)
                .addGap(0, 130, Short.MAX_VALUE))
        );
        sidePanelBtnProfileLayout.setVerticalGroup(
            sidePanelBtnProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ind2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidePanelBtnProfileLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(profileBtnLabel)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        sidePanel.add(sidePanelBtnProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, -1, -1));

        sidePanelBtnSettings.setBackground(new java.awt.Color(23, 35, 51));
        sidePanelBtnSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sidePanelBtnSettingsMousePressed(evt);
            }
        });

        ind3.setBackground(new java.awt.Color(204, 204, 204));
        ind3.setOpaque(false);
        ind3.setPreferredSize(new java.awt.Dimension(3, 50));

        javax.swing.GroupLayout ind3Layout = new javax.swing.GroupLayout(ind3);
        ind3.setLayout(ind3Layout);
        ind3Layout.setHorizontalGroup(
            ind3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        ind3Layout.setVerticalGroup(
            ind3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        settingsBtnLabel.setForeground(new java.awt.Color(255, 255, 255));
        settingsBtnLabel.setText("Settings");

        javax.swing.GroupLayout sidePanelBtnSettingsLayout = new javax.swing.GroupLayout(sidePanelBtnSettings);
        sidePanelBtnSettings.setLayout(sidePanelBtnSettingsLayout);
        sidePanelBtnSettingsLayout.setHorizontalGroup(
            sidePanelBtnSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelBtnSettingsLayout.createSequentialGroup()
                .addComponent(ind3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(settingsBtnLabel)
                .addGap(0, 130, Short.MAX_VALUE))
        );
        sidePanelBtnSettingsLayout.setVerticalGroup(
            sidePanelBtnSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ind3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        searchField.setBackground(new java.awt.Color(123, 156, 225));
        searchField.setForeground(new java.awt.Color(255, 255, 255));
        searchField.setBorder(null);
        searchField.addActionListener(this::searchFieldActionPerformed);

        searchBtn.setIcon(new ImageIcon(getClass().getResource("/icons8_search_32px.png"))); // NOI18N

        javax.swing.GroupLayout headerLayout = new GroupLayout(header);
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
        getContentPane().add(loginScreen1, new AbsoluteConstraints(200, 90, -1, 720));
        getContentPane().add(dashboard2, new AbsoluteConstraints(200, 90, -1, -1));

        pack();
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new MainScreen().setVisible(true));
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

    private void toggleDashboard(){
        dashboard2.setVisible(!dashboard2.isVisible());
    }

    private void toggleLogin(){
        loginScreen1.setVisible(!loginScreen1.isVisible());
    }

    public void logout(){
        toggleDashboard();
        toggleLogin();
    }


    private Dashboard dashboard2;
    private javax.swing.JPanel header;
    private javax.swing.JLabel homeBtnLabel;
    private javax.swing.JPanel ind;
    private javax.swing.JPanel ind1;
    private javax.swing.JPanel ind2;
    private javax.swing.JPanel ind3;
    private loginScreen loginScreen1;
    private javax.swing.JLabel profileBtnLabel;
    private javax.swing.JLabel searchBtn;
    private javax.swing.JTextField searchField;
    private javax.swing.JLabel settingsBtnLabel;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JPanel sidePanelBtnHome;
    private javax.swing.JPanel sidePanelBtnProfile;
    private javax.swing.JPanel sidePanelBtnSettings;
    private javax.swing.JPanel sidePanelBtnUsers;
    private javax.swing.JLabel usersBtnLabel;
}
