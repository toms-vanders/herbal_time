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
        } else setSelected(value.getCollectedOnWorksite());

        setText(value.getProduceName());
        return this;
    }
}