package GUI;

public class ClientScreen extends javax.swing.JPanel {
    private MainScreen mainScreen;

    public ClientScreen(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        initComponents();
    }

    private void initComponents() {

        topBar = new javax.swing.JPanel();
        taskTitle = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        listElement = new javax.swing.JPanel();
        profilePicture = new javax.swing.JLabel();
        personName = new javax.swing.JLabel();
        infoBtn = new javax.swing.JButton();
        msgBtn = new javax.swing.JButton();
        removeBtn = new javax.swing.JButton();
        settingsBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(71, 120, 197));
        setForeground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1000, 720));
        setMinimumSize(new java.awt.Dimension(1000, 720));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1000, 720));

        topBar.setBackground(new java.awt.Color(120, 168, 252));
        topBar.setPreferredSize(new java.awt.Dimension(1000, 110));

        taskTitle.setBackground(new java.awt.Color(249, 249, 249));
        taskTitle.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        taskTitle.setForeground(new java.awt.Color(249, 249, 249));
        taskTitle.setText("CSWorks work sites");
        taskTitle.setPreferredSize(new java.awt.Dimension(450, 50));

        javax.swing.GroupLayout topBarLayout = new javax.swing.GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
                topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(topBarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(taskTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(534, Short.MAX_VALUE))
        );
        topBarLayout.setVerticalGroup(
                topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(topBarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(taskTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        addBtn.setBackground(new java.awt.Color(71, 120, 197));
        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(71, 120, 197));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        listElement.setBackground(new java.awt.Color(23, 35, 51));
        listElement.setMaximumSize(new java.awt.Dimension(970, 112));
        listElement.setMinimumSize(new java.awt.Dimension(970, 112));
        listElement.setPreferredSize(new java.awt.Dimension(970, 112));

        profilePicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_github_96px.png"))); // NOI18N
        profilePicture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                profilePictureMousePressed(evt);
            }
        });

        personName.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        personName.setText("John Doe");

        infoBtn.setBackground(new java.awt.Color(23, 35, 51));
        infoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_information_32px.png"))); // NOI18N
        infoBtn.setMaximumSize(new java.awt.Dimension(70, 50));
        infoBtn.setMinimumSize(new java.awt.Dimension(70, 50));
        infoBtn.setPreferredSize(new java.awt.Dimension(70, 50));
        infoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoBtnActionPerformed(evt);
            }
        });

        msgBtn.setBackground(new java.awt.Color(23, 35, 51));
        msgBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_sent_32px.png"))); // NOI18N
        msgBtn.setMaximumSize(new java.awt.Dimension(70, 50));
        msgBtn.setMinimumSize(new java.awt.Dimension(70, 50));
        msgBtn.setPreferredSize(new java.awt.Dimension(70, 50));
        msgBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msgBtnActionPerformed(evt);
            }
        });

        removeBtn.setBackground(new java.awt.Color(23, 35, 51));
        removeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_trash_can_32px.png"))); // NOI18N
        removeBtn.setMaximumSize(new java.awt.Dimension(70, 50));
        removeBtn.setMinimumSize(new java.awt.Dimension(70, 50));
        removeBtn.setPreferredSize(new java.awt.Dimension(70, 50));
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });

        settingsBtn.setBackground(new java.awt.Color(23, 35, 51));
        settingsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_settings_32px_1.png"))); // NOI18N
        settingsBtn.setMaximumSize(new java.awt.Dimension(70, 50));
        settingsBtn.setMinimumSize(new java.awt.Dimension(70, 50));
        settingsBtn.setPreferredSize(new java.awt.Dimension(70, 50));
        settingsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout listElementLayout = new javax.swing.GroupLayout(listElement);
        listElement.setLayout(listElementLayout);
        listElementLayout.setHorizontalGroup(
                listElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(listElementLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(profilePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(personName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 534, Short.MAX_VALUE)
                                .addComponent(msgBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(settingsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(infoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        listElementLayout.setVerticalGroup(
                listElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(listElementLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(listElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(listElementLayout.createSequentialGroup()
                                                .addComponent(profilePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listElementLayout.createSequentialGroup()
                                                .addGroup(listElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(infoBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(msgBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(removeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(settingsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(personName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(33, 33, 33))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(listElement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 57, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(listElement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 1329, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(topBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(topBar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>

    private void profilePictureMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void infoBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void msgBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void settingsBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }


    // Variables declaration - do not modify
    private javax.swing.JButton addBtn;
    private javax.swing.JButton infoBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel listElement;
    private javax.swing.JButton msgBtn;
    private javax.swing.JLabel personName;
    private javax.swing.JLabel profilePicture;
    private javax.swing.JButton removeBtn;
    private javax.swing.JButton settingsBtn;
    private javax.swing.JLabel taskTitle;
    private javax.swing.JPanel topBar;
    // End of variables declaration
}
