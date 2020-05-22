package GUI;

import javax.swing.*;
import java.awt.*;

public class StatusDialog extends JDialog {

    public StatusDialog(JFrame parent,boolean modal,String title, String errorMsg) {
        super(parent,modal);
        setTitle(title);
        this.errorMsg = errorMsg;
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setUndecorated(true);
        getContentPane().setBackground(new Color(60,63,65));
        java.awt.GridBagConstraints gridBagConstraints;

        errorIcon = new javax.swing.JLabel();
        errorText = new javax.swing.JLabel();
        okBtn = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(400, 200));
        setMinimumSize(new java.awt.Dimension(400, 200));
        setPreferredSize(new java.awt.Dimension(400, 200));
        setLayout(new java.awt.GridBagLayout());

        errorIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_error_100px.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(20, 150, 0, 150);
        add(errorIcon, gridBagConstraints);

        errorText.setText(errorMsg);
        errorText.setForeground(Color.WHITE);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 0, 5);
        add(errorText, gridBagConstraints);

        okBtn.setText("Ok");
        ComponentsConfigure.metroBtnConfig(okBtn);
        okBtn.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new java.awt.Insets(30, 150, 80, 150);
        okBtn.addActionListener((e)->{this.dispose();});
        add(okBtn, gridBagConstraints);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>


    // Variables declaration - do not modify
    private javax.swing.JLabel errorText;
    private javax.swing.JLabel errorIcon;
    private javax.swing.JButton okBtn;
    private String errorMsg;
    // End of variables declaration
}
