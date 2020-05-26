package GUI;

import javax.swing.*;
import java.awt.*;

public class StatusDialog extends JDialog {

    public static final ImageIcon WARNING = new ImageIcon(StatusDialog.class.getResource("/icons8_error_100px.png"));
    public static final ImageIcon CONFIRM = new ImageIcon(StatusDialog.class.getResource("/icons8_ok_100px_1.png"));

    private final String msg;

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
        GridBagConstraints gridBagConstraints;

        JLabel errorIcon = new JLabel();
        JTextArea dialogText = new JTextArea();
        JButton okBtn = new JButton();

        setMaximumSize(new Dimension(400, 200));
        setMinimumSize(new Dimension(400, 200));
        setPreferredSize(new Dimension(400, 200));
        setLayout(new GridBagLayout());

        errorIcon.setIcon(typeOfDialog); // NOI18N
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(20, 150, 0, 150);
        add(errorIcon, gridBagConstraints);

        dialogText.setText(msg);
        dialogText.setWrapStyleWord(true);
        dialogText.setForeground(Color.WHITE);
        dialogText.setLineWrap(true);
        dialogText.setOpaque(false);
        dialogText.setEditable(false);
        dialogText.setFocusable(false);
        dialogText.setBackground(UIManager.getColor("Label.background"));
        dialogText.setFont(UIManager.getFont("Label.font"));
        dialogText.setBorder(UIManager.getBorder("Label.border"));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(10, 15, 0, 15);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(dialogText, gridBagConstraints);
        gridBagConstraints.fill = GridBagConstraints.NONE;

        okBtn.setText("Ok");
        ComponentsConfigure.metroBtnConfig(okBtn);
        okBtn.setPreferredSize(new Dimension(100, 40));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(30, 150, 80, 150);
        okBtn.addActionListener((e)-> this.dispose());
        add(okBtn, gridBagConstraints);

        pack();
        setLocationRelativeTo(null);
    }
}
