package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginScreen extends JPanel {

    private final MainScreen mainScreen;

    public LoginScreen(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        initComponents();
    }

    private void initComponents() {

        loginPanel = new JPanel();
        userPanel = new JPanel();
        userTextField = new JTextField();
        passwordPanel = new JPanel();
        passwordField = new JPasswordField();
        loginBtn = new JButton();
        exitBtn = new JButton();
        loginLabel = new JLabel();
        loginLogo = new JLabel();

        setForeground(new Color(60, 63, 65));
        setMinimumSize(new Dimension(1000, 720));
        setName("");
        setPreferredSize(new Dimension(1000, 720));
        setVerifyInputWhenFocusTarget(false);

        setBackground(new Color(60,63,65));
        loginPanel.setBackground(new Color(23, 35, 51));

        userTextField.setUI(new JTextFieldHintUI("Username...",Color.GRAY));

        GroupLayout userPanelLayout = new GroupLayout(userPanel);
        userPanel.setLayout(userPanelLayout);
        userPanelLayout.setHorizontalGroup(
            userPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(userTextField)
        );
        userPanelLayout.setVerticalGroup(
            userPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(userTextField, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        passwordField.setEchoChar('*');

        GroupLayout passwordPanelLayout = new GroupLayout(passwordPanel);
        passwordPanel.setLayout(passwordPanelLayout);
        passwordPanelLayout.setHorizontalGroup(
            passwordPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(passwordField)
        );
        passwordPanelLayout.setVerticalGroup(
            passwordPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(passwordField, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        loginBtn.setText("Login");
        loginBtn.setFont(ComponentsConfigure.defaultFont);
        ComponentsConfigure.metroBtnConfig(loginBtn);
        loginBtn.addActionListener(this::loginBtnActionPerformed);

        exitBtn.setText("Exit");
        exitBtn.setFont(ComponentsConfigure.defaultFont);
        ComponentsConfigure.metroBtnConfig(exitBtn);
        exitBtn.addActionListener(this::exitBtnActionPerformed);

        loginLogo.setIcon(new ImageIcon(getClass().getResource("/icons8_potted_plant_100px_1.png")));

        loginLabel.setFont(ComponentsConfigure.defaultFont);
        loginLabel.setForeground(Color.GRAY);
        loginLabel.setText("Login");

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
                loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(loginPanelLayout.createSequentialGroup()
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(userPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(passwordPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                                .addGap(100, 100, 100)
                                                                .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(38, 38, 38)
                                                                .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                                .addGap(191, 191, 191)
                                                                .addComponent(loginLogo)))
                                                .addGap(0, 104, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(loginPanelLayout.createSequentialGroup()
                                .addGap(207, 207, 207)
                                .addComponent(loginLabel)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
                loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(loginPanelLayout.createSequentialGroup()
                                .addComponent(loginLogo)
                                .addGap(18, 18, 18)
                                .addComponent(loginLabel)
                                .addGap(36, 36, 36)
                                .addComponent(userPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(passwordPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(72, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addComponent(loginPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(256, 256, 256))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(loginPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(156, 156, 156))
        );
    }

    private void loginBtnActionPerformed(ActionEvent evt) {
        mainScreen.login();
    }

    private void exitBtnActionPerformed(ActionEvent evt) {
        System.exit(0);
    }
    
    private JButton exitBtn;
    private JButton loginBtn;
    private JLabel loginLabel;
    private JLabel loginLogo;
    private JPanel loginPanel;
    private JPasswordField passwordField;
    private JPanel passwordPanel;
    private JPanel userPanel;
    private JTextField userTextField;
}
