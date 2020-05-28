package GUI.Components;

import javax.swing.*;
import java.awt.*;

public class StatusDialog extends JDialog {

    public static final ImageIcon WARNING = new ImageIcon(StatusDialog.class.getResource("/icons8_error_100px.png"));
    public static final ImageIcon CONFIRM = new ImageIcon(StatusDialog.class.getResource("/icons8_ok_100px_1.png"));
    public static final ImageIcon ABOUT = new ImageIcon(StatusDialog.class.getResource("/icons8_about_100px.png"));
    public static final ImageIcon LOADING = new ImageIcon(StatusDialog.class.getResource("/icons8_loading_100px.png"));

    private final String msg;
    private AnimatedIcon loadingIcon;

    public ImageIcon typeOfDialog;

    public StatusDialog(JFrame parent,boolean modal,ImageIcon typeOfDialog,String title, String msg) {
        super(parent,modal);
        setTitle(title);
        this.msg = msg;
        this.typeOfDialog = typeOfDialog;
        initComponents();
        loadingIcon.start();
        setVisible(true);
    }

    private void initComponents(){
        setUndecorated(true);
        getContentPane().setBackground(new Color(60,63,65));
        GridBagConstraints gridBagConstraints;

        JLabel errorIcon = new JLabel();
        JTextArea dialogText = new JTextArea();
        JButton okBtn = new JButton();
        loadingIcon = new AnimatedIcon(errorIcon,40,18);
        for(int angle = 0; angle <= 360; angle+=20){
            loadingIcon.addIcon(new RotatedIcon(typeOfDialog,angle));
        }
        setMaximumSize(new Dimension(400, 200));
        setMinimumSize(new Dimension(400, 200));
        setPreferredSize(new Dimension(400, 200));
        setLayout(new GridBagLayout());

        if(typeOfDialog.equals(LOADING)){
            errorIcon.setIcon(loadingIcon);
        }else{
            errorIcon.setIcon(typeOfDialog);
        }
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(60, 150, 0, 150);
        add(errorIcon, gridBagConstraints);

        ComponentsConfigure.configureWordWrapLabel(dialogText,msg);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(10, 30, 0, 30);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(dialogText, gridBagConstraints);
        gridBagConstraints.fill = GridBagConstraints.NONE;

        okBtn.setText("Ok");
        ComponentsConfigure.metroBtnConfig(okBtn);
        okBtn.setPreferredSize(new Dimension(100, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(10, 150, 80, 150);
        okBtn.addActionListener((e)-> this.dispose());
        if(typeOfDialog.equals(LOADING)){
            okBtn.setText("");
        }
        add(okBtn, gridBagConstraints);

        pack();
        setLocationRelativeTo(null);
    }
}
