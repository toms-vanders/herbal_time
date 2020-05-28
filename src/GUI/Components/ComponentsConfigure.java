package GUI.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.Serializable;

public class ComponentsConfigure implements Serializable {
    public static final ImageIcon minimizeIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_minimize_window_32px_1.png"));
    public static final ImageIcon maximizeIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_maximize_button_32px.png"));
    public static final ImageIcon exitIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_close_window_32px.png"));
    public static final ImageIcon locationIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_place_marker_32px.png"));
    public static final ImageIcon vegetableIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_vegetarian_mark_32px.png"));
    public static final ImageIcon watchIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_watch_32px.png"));
    public static final ImageIcon presentIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_present_32px.png"));
    public static final ImageIcon trolleyIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_trolley_32px.png"));
    public static final ImageIcon approveIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_approval_32px.png"));
    public static final ImageIcon saveIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_save_32px.png"));
    public static final ImageIcon trashIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_trash_can_32px.png"));
    public static final ImageIcon viewIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_eye_32px.png"));
    public static final ImageIcon plantIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_potted_plant_100px_1.png"));
    public static final ImageIcon infoIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_information_32px.png"));
    public static final ImageIcon sentIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_sent_32px.png"));
    public static final ImageIcon settingsIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_settings_32px_1.png"));
    public static final ImageIcon defaultProfile = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_github_96px.png"));
    public static final Font defaultFont = new Font("Dialog", Font.BOLD, 24);

    public static void metroBtnConfig(JButton button) {
        button.setBackground(new Color(71, 120, 197));
        button.setForeground(new Color(255, 255, 255));
        button.setContentAreaFilled(false);
        button.setBorder(null);
        button.setFocusPainted(false);
        button.setMaximumSize(new Dimension(70, 50));
        button.setMinimumSize(new Dimension(70, 50));
        button.setPreferredSize(new Dimension(70, 50));
        button.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if (model.isRollover()) {
                button.setBackground(new Color(120, 168, 252));
                button.setContentAreaFilled(true);
            } else {
                button.setBackground(new Color(71, 120, 197));
                button.setContentAreaFilled(false);
            }
        });
    }

    public static void createTopBar(JPanel topBar,JLabel frameTitle,JLabel minimizeBtn,JLabel maximizeBtn, JLabel exitBtn,Color colour,JFrame targetFrame){

        topBarConfig(topBar,targetFrame,colour);

        minimizeBtn.setIcon(minimizeIcon);
        minimizeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                targetFrame.setExtendedState(JFrame.ICONIFIED);
            }
        });
        maximizeBtn.setIcon(maximizeIcon);
        maximizeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                targetFrame.setExtendedState(JFrame.NORMAL);
            }
        });
        exitBtn.setIcon(exitIcon);
        exitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                targetFrame.dispose();
            }
        });

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
    }

    public static void topBarConfig(JPanel topBar, JFrame target,Color colour){
        final int[] xx = new int[1];
        final int[] xy = new int[1];

        topBar.setBackground(colour);
        topBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xx[0] = e.getX();
                xy[0] = e.getY();
            }
        });
        topBar.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                int x = evt.getXOnScreen();
                int y = evt.getYOnScreen();
                target.setLocation(x- xx[0],y- xy[0]);
            }
        });
    }

    public static void indicatorConfig(JPanel[] indicators){
        for(JPanel indicator : indicators){
            indicator.setBackground(new Color(204, 204, 204));
            indicator.setOpaque(false);
            indicator.setPreferredSize(new Dimension(3, 50));

            GroupLayout indLayout = new GroupLayout(indicator);
            indicator.setLayout(indLayout);
            indLayout.setHorizontalGroup(
                    indLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGap(0, 3, Short.MAX_VALUE)
            );
            indLayout.setVerticalGroup(
                    indLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGap(0, 0, Short.MAX_VALUE)
            );
        }
    }

    public static void configureWordWrapLabel(JTextArea label,String msg){
        label.setText(msg);
        label.setWrapStyleWord(true);
        label.setForeground(Color.WHITE);
        label.setLineWrap(true);
        label.setOpaque(false);
        label.setEditable(false);
        label.setFocusable(false);
        label.setBackground(UIManager.getColor("Label.background"));
        label.setFont(UIManager.getFont("Label.font"));
        label.setBorder(UIManager.getBorder("Label.border"));
    }
}