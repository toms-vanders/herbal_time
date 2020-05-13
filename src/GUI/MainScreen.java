package GUI;

import java.awt.Color;
import javax.swing.*;

/**
 *
 * @author dmich
 */
public class MainScreen extends javax.swing.JFrame {

    /**
     * Creates new form MainScreen
     */
    public MainScreen() {
         initComponents();
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
        overviewPane = new JPanel();
        overviewProfile = new JPanel();
        overviewBtns = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jSeparator1 = new JSeparator();
        exitBtn = new JLabel();
        jLabel7 = new JLabel();
        jButton1 = new JButton();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();

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

        sidePanelBtnHome.setBackground(new java.awt.Color(23, 35, 51));
        sidePanelBtnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sidePanelBtnHomeMousePressed(evt);
            }
        });

        ind.setBackground(new Color(204, 204, 204));
        ind.setOpaque(false);
        ind.setPreferredSize(new java.awt.Dimension(3, 50));

        javax.swing.GroupLayout indLayout = new GroupLayout(ind);
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

        javax.swing.GroupLayout sidePanelBtnHomeLayout = new GroupLayout(sidePanelBtnHome);
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

        javax.swing.GroupLayout ind1Layout = new GroupLayout(ind1);
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

        javax.swing.GroupLayout sidePanelBtnUsersLayout = new GroupLayout(sidePanelBtnUsers);
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

        javax.swing.GroupLayout ind2Layout = new GroupLayout(ind2);
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

        javax.swing.GroupLayout sidePanelBtnProfileLayout = new GroupLayout(sidePanelBtnProfile);
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

        javax.swing.GroupLayout ind3Layout = new GroupLayout(ind3);
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

        javax.swing.GroupLayout sidePanelBtnSettingsLayout = new GroupLayout(sidePanelBtnSettings);
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

        sidePanel.add(sidePanelBtnSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, -1, -1));

        getContentPane().add(sidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 800));

        header.setBackground(new java.awt.Color(71, 120, 197));
        header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerMousePressed(evt);
            }
        });

        searchField.setBackground(new java.awt.Color(123, 156, 225));
        searchField.setForeground(new java.awt.Color(255, 255, 255));
        searchField.setBorder(null);
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });

        searchBtn.setIcon(new ImageIcon(getClass().getResource("/icons8_search_32px.png"))); // NOI18N

        javax.swing.GroupLayout headerLayout = new GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(742, Short.MAX_VALUE)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchBtn)
                .addGap(12, 12, 12))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        getContentPane().add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1000, 90));

        overviewPane.setBackground(new java.awt.Color(71, 120, 197));

        overviewProfile.setBackground(new java.awt.Color(120, 168, 252));
        overviewProfile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        overviewBtns.setBackground(new java.awt.Color(84, 127, 206));

        jLabel1.setIcon(new ImageIcon(getClass().getResource("/icons8_secured_letter_32px_3.png"))); // NOI18N

        jLabel2.setIcon(new ImageIcon(getClass().getResource("/icons8_contacts_32px.png"))); // NOI18N

        jLabel3.setIcon(new ImageIcon(getClass().getResource("/icons8_calendar_32px.png"))); // NOI18N

        jLabel4.setIcon(new ImageIcon(getClass().getResource("/icons8_lock_32px.png"))); // NOI18N

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout overviewBtnsLayout = new GroupLayout(overviewBtns);
        overviewBtns.setLayout(overviewBtnsLayout);
        overviewBtnsLayout.setHorizontalGroup(
            overviewBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, overviewBtnsLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(overviewBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(overviewBtnsLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel4)))
                .addGap(37, 37, 37))
        );
        overviewBtnsLayout.setVerticalGroup(
            overviewBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overviewBtnsLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(overviewBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        overviewProfile.add(overviewBtns, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 111, -1, -1));

        exitBtn.setIcon(new ImageIcon(getClass().getResource("/icons8_exit_32px.png"))); // NOI18N
        exitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitBtnMousePressed(evt);
            }
        });
        overviewProfile.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, -1, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("John Doe");
        overviewProfile.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        jButton1.setBackground(new java.awt.Color(71, 120, 197));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("View Task");
        jButton1.setBorder(null);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Wednesday 13");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("May 2020");

        jButton2.setBackground(new java.awt.Color(71, 120, 197));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Register Task");
        jButton2.setBorder(null);

        jButton3.setBackground(new java.awt.Color(71, 120, 197));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Contact Leader");
        jButton3.setBorder(null);

        javax.swing.GroupLayout overviewPaneLayout = new GroupLayout(overviewPane);
        overviewPane.setLayout(overviewPaneLayout);
        overviewPaneLayout.setHorizontalGroup(
            overviewPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(overviewProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(overviewPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(overviewPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(overviewPaneLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6))
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        overviewPaneLayout.setVerticalGroup(
            overviewPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overviewPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(overviewProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(overviewPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
        );

        getContentPane().add(overviewPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 310, 720));

        jTable1.setForeground(new java.awt.Color(102, 102, 102));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setRowHeight(32);
        jTable1.setSelectionBackground(new java.awt.Color(0, 120, 215));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, 690, 710));

        pack();
    }

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO: Add search function
    }

    private void sidePanelBtnHomeMousePressed(java.awt.event.MouseEvent evt) {
        setColor(sidePanelBtnHome);
        ind.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnUsers,sidePanelBtnProfile,sidePanelBtnSettings},new JPanel[]{ind1,ind2,ind3});
    }

    private void sidePanelBtnUsersMousePressed(java.awt.event.MouseEvent evt) {
        setColor(sidePanelBtnUsers);
        ind1.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnHome,sidePanelBtnProfile,sidePanelBtnSettings},new JPanel[]{ind,ind2,ind3});
    }

    private void sidePanelBtnProfileMousePressed(java.awt.event.MouseEvent evt) {
        setColor(sidePanelBtnProfile);
        ind2.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnUsers,sidePanelBtnHome,sidePanelBtnSettings},new JPanel[]{ind1,ind,ind3});
    }

    private void sidePanelBtnSettingsMousePressed(java.awt.event.MouseEvent evt) {
        setColor(sidePanelBtnSettings);
        ind3.setOpaque(true);
        resetColor(new JPanel[]{sidePanelBtnUsers,sidePanelBtnProfile,sidePanelBtnHome},new JPanel[]{ind1,ind2,ind});
    }

    private void exitBtnMousePressed(java.awt.event.MouseEvent evt) {
        System.exit(0);
    }
    
    int xx,xy;
    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-xx,y-xy);
    }

    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xx = evt.getX();
        xy = evt.getY();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JLabel exitBtn;
    private javax.swing.JPanel header;
    private javax.swing.JLabel homeBtnLabel;
    private javax.swing.JPanel ind;
    private javax.swing.JPanel ind1;
    private javax.swing.JPanel ind2;
    private javax.swing.JPanel ind3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel overviewBtns;
    private javax.swing.JPanel overviewPane;
    private javax.swing.JPanel overviewProfile;
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
