package GUI;

import Model.Produce;

import javax.swing.*;
import java.awt.*;

public class CheckBoxListCellRenderer extends JCheckBox implements ListCellRenderer<Produce> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Produce> list, Produce value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {

        setComponentOrientation(list.getComponentOrientation());
        setFont(list.getFont());
        setBackground(list.getBackground());
        setForeground(list.getForeground());
        setEnabled(list.isEnabled());


        if (value.getCollectedOnWorksite() == null) {
            setSelected(isSelected);
        } else if (value.getCollectedOnWorksite() == true) {
            setSelected(true);
        } else {
            setSelected(false);
        }

        setText(value == null ? "" : value.getProduceName());
        return this;
    }
}