package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.Serializable;

public class ComponentsConfigure implements Serializable {

    static JFrame targetFrame;
    static ImageIcon minimizeIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_minimize_window_32px_1.png"));
    static ImageIcon maximizeIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_maximize_button_32px.png"));
    static ImageIcon exitIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_close_window_32px.png"));
    static ImageIcon locationIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_place_marker_32px.png"));
    static ImageIcon vegetableIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_vegetarian_mark_32px.png"));
    static ImageIcon watchIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_watch_32px.png"));
    static ImageIcon presentIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_present_32px.png"));
    static ImageIcon trolleyIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_trolley_32px.png"));
    static ImageIcon approveIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_approval_32px.png"));
    static ImageIcon saveIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_save_32px.png"));
    static ImageIcon trashIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_trash_can_32px.png"));
    static ImageIcon viewIcon = new ImageIcon(ComponentsConfigure.class.getResource("/icons8_eye_32px.png"));

    static Font defaultFont = new Font("Dialog", Font.BOLD, 24);

    static void metroBtnConfig(JButton button) {
        button.setBackground(new Color(71, 120, 197));
        button.setForeground(new Color(255, 255, 255));
        button.setContentAreaFilled(false);
        button.setBorder(null);
        button.setFocusPainted(false);
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

    static void topBarConfig(JPanel topBar, JFrame target,Color colour){
        //TODO: FIX - it overwrites when new frames are displayed
        targetFrame = target;

        topBar.setBackground(colour);
        topBar.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                topBarMouseDragged(evt);
            }
        });
        topBar.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                topBarMousePressed(evt);
            }
        });

    }

    static void topBarButtons(JLabel minimizeBtn, JLabel maximizeBtn, JLabel exitBtn, JFrame targetFrame){
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
    }

    static void indicatorConfig(JPanel[] indicators){
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

    static void configureFrame(){

    }

    static int xx,xy;
    private static void topBarMouseDragged(MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        targetFrame.setLocation(x-xx,y-xy);
    }

    private static void topBarMousePressed(MouseEvent evt) {
        xx = evt.getX();
        xy = evt.getY();
    }
}