package GUI;

import javax.swing.*;
import java.awt.*;

public class sidePanelBtn extends JPanel {
    private JPanel indicator = new JPanel();
    private JPanel sidePanelBtn = new JPanel();
    private JLabel sidePanelBtnLabel = new JLabel();

    public sidePanelBtn(String text) {

        sidePanelBtnLabel.setText(text);
        sidePanelBtnLabel.setForeground(Color.WHITE);

        sidePanelBtn.setBackground(new Color(23, 35, 51));

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

        GroupLayout sidePanelLayout = new GroupLayout(sidePanelBtn);
        sidePanelBtn.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
                sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(sidePanelLayout.createSequentialGroup()
                                .addComponent(indicator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(sidePanelBtnLabel)
                                .addGap(0, 130, Short.MAX_VALUE))
        );
        sidePanelLayout.setVerticalGroup(
                sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(indicator, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(sidePanelLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(sidePanelBtnLabel)
                                .addContainerGap(18, Short.MAX_VALUE))
        );
    }

    public void setIndicatorOpaque(boolean value){
        indicator.setOpaque(value);
    }

    public JPanel getIndicator(){
        return indicator;
    }

    public JPanel getSidePanelBtn(){
        return sidePanelBtn;
    }
}
