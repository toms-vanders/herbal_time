package GUI;

import Model.WorkTask;
import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;

public class ViewWorkTask extends JFrame {

    public ViewWorkTask(WorkTask currentTask) {
        this.currentTask = currentTask;
        initComponents();
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        topBar = new JPanel();
        maximizeBtn = new JLabel();
        exitBtn = new JLabel();
        minimizeBtn = new JLabel();
        frameTitle = new JLabel();
        profilePicture = new JLabel();
        fnameLabel = new JLabel();
        lnameLabel = new JLabel();
        fnameValue = new JLabel();
        lnameValue = new JLabel();
        locationLabel = new JLabel();
        locationList = new JComboBox<>();
        produceLabel = new JLabel();
        produceList = new JComboBox<>();
        startDateLabel = new JLabel();
        startDatePicker = new com.github.lgooddatepicker.components.DateTimePicker();
        endDatePicker = new com.github.lgooddatepicker.components.DateTimePicker();
        endDateLabel = new JLabel();
        quantityLabel = new JLabel();
        quanitySpinner = new JSpinner();
        quantityPicker = new JComboBox<>();
        statusLabel = new JLabel();
        statusPicker = new JComboBox<>();
        registerBtn = new JButton();
        cancelBtn = new JButton();
        locationDescriptorLabel = new JLabel();
        produceDescriptorLabel = new JLabel();
        registerBtn1 = new JButton();
        emailValue = new JLabel();
        emailLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 565));
        setMinimumSize(new java.awt.Dimension(800, 565));

        jPanel1.setBackground(new java.awt.Color(71, 120, 197));

        topBar.setBackground(new java.awt.Color(120, 168, 252));

        maximizeBtn.setIcon(new ImageIcon(getClass().getResource("/icons8_maximize_button_32px.png"))); // NOI18N

        exitBtn.setIcon(new ImageIcon(getClass().getResource("/icons8_close_window_32px.png"))); // NOI18N

        minimizeBtn.setIcon(new ImageIcon(getClass().getResource("/icons8_minimize_window_32px_1.png"))); // NOI18N

        frameTitle.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        frameTitle.setText("CS Works");

        GroupLayout topBarLayout = new GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
                topBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, topBarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(frameTitle)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(minimizeBtn)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(maximizeBtn)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exitBtn)
                                .addContainerGap())
        );
        topBarLayout.setVerticalGroup(
                topBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, topBarLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(topBarLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(frameTitle)
                                        .addGroup(topBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(minimizeBtn)
                                                .addComponent(exitBtn)
                                                .addComponent(maximizeBtn)))
                                .addContainerGap())
        );

        profilePicture.setIcon(new ImageIcon(getClass().getResource("/icons8_github_96px.png"))); // NOI18N
        profilePicture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                profilePictureMousePressed(evt);
            }
        });

        fnameLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        fnameLabel.setForeground(new java.awt.Color(255, 255, 255));
        fnameLabel.setText("Firstname:");

        lnameLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lnameLabel.setForeground(new java.awt.Color(255, 255, 255));
        lnameLabel.setText("Lastname:");

        fnameValue.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        fnameValue.setForeground(new java.awt.Color(255, 255, 255));
        fnameValue.setText("John");

        lnameValue.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lnameValue.setForeground(new java.awt.Color(255, 255, 255));
        lnameValue.setText("Doe");

        locationLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        locationLabel.setForeground(new java.awt.Color(249, 249, 249));
        locationLabel.setIcon(new ImageIcon(getClass().getResource("/icons8_place_marker_32px.png"))); // NOI18N
        locationLabel.setText("Location");
        locationLabel.setFocusable(false);

        locationList.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        produceLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        produceLabel.setForeground(new java.awt.Color(249, 249, 249));
        produceLabel.setIcon(new ImageIcon(getClass().getResource("/icons8_vegetarian_mark_32px.png"))); // NOI18N
        produceLabel.setText("Produce");

        produceList.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        startDateLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        startDateLabel.setForeground(new java.awt.Color(249, 249, 249));
        startDateLabel.setIcon(new ImageIcon(getClass().getResource("/icons8_watch_32px.png"))); // NOI18N
        startDateLabel.setText("Start");

        startDatePicker.setBackground(new java.awt.Color(120, 168, 252));

        endDatePicker.setBackground(new java.awt.Color(120, 168, 252));

        endDateLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        endDateLabel.setForeground(new java.awt.Color(249, 249, 249));
        endDateLabel.setIcon(new ImageIcon(getClass().getResource("/icons8_present_32px.png"))); // NOI18N
        endDateLabel.setText("End");

        quantityLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        quantityLabel.setForeground(new java.awt.Color(249, 249, 249));
        quantityLabel.setIcon(new ImageIcon(getClass().getResource("/icons8_trolley_32px.png"))); // NOI18N
        quantityLabel.setText("Quantity");

        quantityPicker.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        statusLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(249, 249, 249));
        statusLabel.setIcon(new ImageIcon(getClass().getResource("/icons8_approval_32px.png"))); // NOI18N
        statusLabel.setText("Status");

        statusPicker.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        registerBtn.setBackground(new java.awt.Color(71, 120, 197));
        registerBtn.setForeground(new java.awt.Color(60, 63, 65));
        registerBtn.setIcon(new ImageIcon(getClass().getResource("/icons8_save_32px.png"))); // NOI18N
        registerBtn.setText("Save");
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
            }
        });

        cancelBtn.setBackground(new java.awt.Color(71, 120, 197));
        cancelBtn.setForeground(new java.awt.Color(60, 63, 65));
        cancelBtn.setIcon(new ImageIcon(getClass().getResource("/icons8_trash_can_32px.png"))); // NOI18N
        cancelBtn.setText("Cancel");

        locationDescriptorLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        locationDescriptorLabel.setForeground(new java.awt.Color(249, 249, 249));
        locationDescriptorLabel.setText("Full-adress or something");
        locationDescriptorLabel.setFocusable(false);

        produceDescriptorLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        produceDescriptorLabel.setForeground(new java.awt.Color(249, 249, 249));
        produceDescriptorLabel.setText("Produce description");
        produceDescriptorLabel.setFocusable(false);

        registerBtn1.setBackground(new java.awt.Color(71, 120, 197));
        registerBtn1.setForeground(new java.awt.Color(60, 63, 65));
        registerBtn1.setIcon(new ImageIcon(getClass().getResource("/icons8_approval_32px.png"))); // NOI18N
        registerBtn1.setText("Approve");
        registerBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtn1ActionPerformed(evt);
            }
        });

        emailValue.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        emailValue.setForeground(new java.awt.Color(255, 255, 255));
        emailValue.setText("mymail@email.me");

        emailLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(255, 255, 255));
        emailLabel.setText("Email:");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(topBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(endDatePicker, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(135, 135, 135)
                                                                .addComponent(profilePicture, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(89, 89, 89)
                                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(fnameLabel)
                                                                        .addComponent(lnameLabel, GroupLayout.Alignment.TRAILING))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(fnameValue)
                                                                        .addComponent(lnameValue))))
                                                .addGap(100, 100, 100)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(locationLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(locationList, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(produceLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(produceList, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(locationDescriptorLabel, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(produceDescriptorLabel, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 13, Short.MAX_VALUE))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(registerBtn1, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(registerBtn, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(statusLabel)
                                                                .addGap(51, 51, 51)
                                                                .addComponent(statusPicker, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(emailLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(emailValue)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(startDatePicker, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(endDateLabel)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addComponent(quantityLabel)
                                                                        .addGap(31, 31, 31)
                                                                        .addComponent(quanitySpinner, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(11, 11, 11)
                                                                        .addComponent(quantityPicker, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(startDateLabel)))))
                                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(topBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(locationLabel)
                                                        .addComponent(locationList, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(locationDescriptorLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(produceList, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(produceLabel))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(produceDescriptorLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(startDateLabel)
                                                        .addComponent(startDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(endDateLabel)
                                                        .addComponent(endDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(quantityLabel)
                                                        .addComponent(quantityPicker, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(quanitySpinner, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(statusLabel)
                                                        .addComponent(statusPicker, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                                .addGap(30, 30, 30))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(profilePicture, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(fnameLabel)
                                                        .addComponent(fnameValue))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lnameValue)
                                                        .addComponent(lnameLabel))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(emailLabel)
                                                        .addComponent(emailValue))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(registerBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(registerBtn1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
        setUndecorated(true);
    }// </editor-fold>

    private void profilePictureMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void registerBtn1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewWorkTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new ViewWorkTask(null).setVisible(true));
    }

    private JButton cancelBtn;
    private JLabel emailLabel;
    private JLabel emailValue;
    private JLabel endDateLabel;
    private DateTimePicker endDatePicker;
    private JLabel exitBtn;
    private JLabel fnameLabel;
    private JLabel fnameValue;
    private JLabel frameTitle;
    private JPanel jPanel1;
    private JLabel lnameLabel;
    private JLabel lnameValue;
    private JLabel locationLabel;
    private JLabel locationDescriptorLabel;
    private JLabel produceDescriptorLabel;
    private JComboBox<String> locationList;
    private JLabel maximizeBtn;
    private JLabel minimizeBtn;
    private JLabel produceLabel;
    private JComboBox<String> produceList;
    private JLabel profilePicture;
    private JSpinner quanitySpinner;
    private JLabel quantityLabel;
    private JComboBox<String> quantityPicker;
    private JButton registerBtn;
    private JButton registerBtn1;
    private JLabel startDateLabel;
    private DateTimePicker startDatePicker;
    private JLabel statusLabel;
    private JComboBox<String> statusPicker;
    private JPanel topBar;

    private WorkTask currentTask;

}
