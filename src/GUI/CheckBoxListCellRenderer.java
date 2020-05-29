package GUI;

import Model.Produce;

import javax.swing.*;
import java.awt.*;

/**
 * Implements ListCellRenderer to render list with collected produce on the work site.
 *
 * @author Daniel Zoltan Ban
 * @author Mikuláš Dobrodej
 * @author Adrian Mihai Dohot
 * @author Damian Hrabąszcz
 * @author Toms Vanders
 * @version 1.0 (29.05.2020)
 *
 * Date: 29.05.2020
 */
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