package GUI;

import javax.swing.*;
import java.awt.*;

public class workersScreen extends javax.swing.JFrame {

    public workersScreen() {
        initComponents();
    }

    private void initComponents() {

        mainContainer = new JPanel();
        topBar = new javax.swing.JPanel();
        maximizeBtn = new javax.swing.JLabel();
        exitBtn = new javax.swing.JLabel();
        minimizeBtn = new javax.swing.JLabel();
        frameTitle = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        scrollablePanel = new javax.swing.JScrollPane();
        removeBtn = new javax.swing.JButton();
        removeBtn1 = new javax.swing.JButton();
        removeBtn2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        mainContainer.setBackground(new java.awt.Color(71, 120, 197));

        topBar.setBackground(new java.awt.Color(120, 168, 252));

        maximizeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_maximize_button_32px.png"))); // NOI18N

        exitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_close_window_32px.png"))); // NOI18N

        minimizeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_minimize_window_32px_1.png"))); // NOI18N

        frameTitle.setFont(new java.awt.Font("Dialog", Font.BOLD, 24));
        frameTitle.setText("CS Works");

        javax.swing.GroupLayout topBarLayout = new javax.swing.GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
                topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topBarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(frameTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(minimizeBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(maximizeBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exitBtn)
                                .addContainerGap())
        );
        topBarLayout.setVerticalGroup(
                topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topBarLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(frameTitle)
                                        .addGroup(topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(minimizeBtn)
                                                .addComponent(exitBtn)
                                                .addComponent(maximizeBtn)))
                                .addContainerGap())
        );

        addBtn.setBackground(new java.awt.Color(71, 120, 197));
        addBtn.setText("Add");
        addBtn.addActionListener(this::addBtnActionPerformed);

        scrollablePanel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        removeBtn.addActionListener(this::removeBtnActionPerformed);
        removeBtn1.addActionListener(this::removeBtn1ActionPerformed);
        removeBtn2.addActionListener(this::removeBtn2ActionPerformed);

        listContainer = createListContainer();
        listContainer.setBackground(new Color(71, 120, 197));
        scrollablePanel.setViewportView(listContainer);

        javax.swing.GroupLayout mainContainerLayout = new javax.swing.GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
                mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(topBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(mainContainerLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(588, 588, 588))
                        .addGroup(mainContainerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainContainerLayout.setVerticalGroup(
                mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainContainerLayout.createSequentialGroup()
                                .addComponent(topBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void setElementComponents(JPanel listElement, JLabel profilePicture,JLabel personName,JButton editBtn,JButton removeBtn) {
        listElement.setBackground(new java.awt.Color(23, 35, 51));

        profilePicture.setIcon(new ImageIcon(getClass().getResource("/icons8_github_96px.png"))); // NOI18N

        personName.setFont(new Font("Dialog", Font.BOLD, 24)); // NOI18N
        personName.setText("John Doe");

        editBtn.setBackground(new java.awt.Color(23, 35, 51));
        editBtn.setIcon(new ImageIcon(getClass().getResource("/icons8_edit_32px.png"))); // NOI18N
        editBtn.setText("Edit");

        removeBtn.setBackground(new java.awt.Color(23, 35, 51));
        removeBtn.setIcon(new ImageIcon(getClass().getResource("/icons8_trash_can_32px.png"))); // NOI18N
        removeBtn.setText("Remove");
    }

    private JPanel createListContainer(){

        listContainer = new JPanel();
        listContainerLayout = new GroupLayout(listContainer);
        listContainer.setLayout(listContainerLayout);
        listContainerLayout.setAutoCreateGaps(true);
        listContainerLayout.setAutoCreateContainerGaps(true);

        parallelGroup = listContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        listContainerLayout.setHorizontalGroup(listContainerLayout.createSequentialGroup().addGroup(parallelGroup));

        sequentialGroup = listContainerLayout.createSequentialGroup();
        listContainerLayout.setVerticalGroup(sequentialGroup);

        for(int i = 0; i < 3; i++){
            JPanel elementToAdd = new JPanel();
            JLabel profilePicture = new JLabel();
            JLabel personName = new JLabel();
            JButton editBtn = new JButton();
            JButton removeBtn = new JButton();
            setElementComponents(elementToAdd, profilePicture, personName, editBtn, removeBtn);
            setElementGroupsPosition(elementToAdd, profilePicture, personName, editBtn, removeBtn);
            addElementToList(elementToAdd);
        }

        return listContainer;
    }

    private void addElementToList(JPanel listElement){
        parallelGroup.addGroup(listContainerLayout.createSequentialGroup()
                .addComponent(listElement));
        sequentialGroup.addGroup(listContainerLayout.createParallelGroup()
                .addComponent(listElement));
    }

    private void setElementGroupsPosition(JPanel listElement, JLabel profilePicture, JLabel personName, JButton editBtn, JButton removeBtn) {

        GroupLayout listElementLayout = new GroupLayout(listElement);
        listElement.setLayout(listElementLayout);
        listElementLayout.setHorizontalGroup(
                listElementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(listElementLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(profilePicture, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(personName)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 298, Short.MAX_VALUE)
                                .addComponent(editBtn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeBtn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        listElementLayout.setVerticalGroup(
                listElementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(listElementLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(listElementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(listElementLayout.createSequentialGroup()
                                                .addComponent(profilePicture, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, listElementLayout.createSequentialGroup()
                                                .addGroup(listElementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(listElementLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(removeBtn)
                                                                .addComponent(editBtn))
                                                        .addComponent(personName))
                                                .addGap(33, 33, 33))))
        );
    }

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void profilePictureMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void profilePicture1MousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void removeBtn1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void profilePicture2MousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void removeBtn2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(workersScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> new workersScreen().setVisible(true));
    }

    private javax.swing.JButton addBtn;
    private javax.swing.JLabel exitBtn;
    private javax.swing.JLabel frameTitle;
    private javax.swing.JPanel listContainer;
    private javax.swing.JPanel mainContainer;
    private javax.swing.JLabel maximizeBtn;
    private javax.swing.JLabel minimizeBtn;
    private javax.swing.JButton removeBtn;
    private javax.swing.JButton removeBtn1;
    private javax.swing.JButton removeBtn2;
    private javax.swing.JScrollPane scrollablePanel;
    private javax.swing.JPanel topBar;

    private GroupLayout listContainerLayout;
    private GroupLayout.ParallelGroup parallelGroup;
    private GroupLayout.SequentialGroup sequentialGroup;
}
