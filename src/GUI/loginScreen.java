package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class loginScreen extends JPanel {

    private final MainScreen mainScreen;

    public loginScreen(MainScreen mainScreen) {
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

        setForeground(new Color(60, 63, 65));
        setMinimumSize(new Dimension(1000, 720));
        setName("");
        setPreferredSize(new Dimension(1000, 720));
        setVerifyInputWhenFocusTarget(false);

        setBackground(new Color(60,63,65));
        loginPanel.setBackground(new Color(23, 35, 51));

        userTextField.setText("username");

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

        passwordField.setText("jPasswordField1");

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

        loginBtn.setBackground(new Color(23, 35, 51));
        loginBtn.setForeground(new Color(60, 63, 65));
        loginBtn.setText("Login");
        loginBtn.addActionListener(this::loginBtnActionPerformed);

        exitBtn.setBackground(new Color(23, 35, 51));
        exitBtn.setForeground(new Color(60, 63, 65));
        exitBtn.setText("Exit");
        exitBtn.addActionListener(this::exitBtnActionPerformed);

        loginLabel.setFont(new Font("Dialog", Font.BOLD, 24)); // NOI18N
        loginLabel.setText("Login");

        GroupLayout loginPanelLayout = new GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(userPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passwordPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(loginBtn, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(exitBtn, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 109, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(loginLabel)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(loginLabel)
                .addGap(18, 18, 18)
                .addComponent(userPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(loginBtn)
                    .addComponent(exitBtn))
                .addContainerGap(126, Short.MAX_VALUE))
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
    private JPanel loginPanel;
    private JPasswordField passwordField;
    private JPanel passwordPanel;
    private JPanel userPanel;
    private JTextField userTextField;
}
