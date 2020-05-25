package GUI;

import javax.swing.*;
import java.awt.*;

public class StatusDialog extends JDialog {

    public static final ImageIcon WARNING = new ImageIcon(StatusDialog.class.getResource("/icons8_error_100px.png"));
    public static final ImageIcon CONFIRM = new ImageIcon(StatusDialog.class.getResource("/icons8_ok_100px_1.png"));

    public ImageIcon typeOfDialog;

    public StatusDialog(JFrame parent,boolean modal,ImageIcon typeOfDialog,String title, String msg) {
        super(parent,modal);
        setTitle(title);
        this.msg = msg;
        this.typeOfDialog = typeOfDialog;
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

        errorIcon.setIcon(typeOfDialog); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(20, 150, 0, 150);
        add(errorIcon, gridBagConstraints);

        errorText.setText(msg);
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
    private String msg;
    // End of variables declaration
}
