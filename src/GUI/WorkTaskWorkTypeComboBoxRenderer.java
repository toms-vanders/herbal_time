package GUI;

import Model.WorkType;

import javax.swing.*;
import java.awt.*;

/**
 * Used when registering a new work task, enables a good way to display work types.
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
public class WorkTaskWorkTypeComboBoxRenderer extends JLabel implements ListCellRenderer<WorkType> {
    public WorkTaskWorkTypeComboBoxRenderer() {

    }

    @Override
    public Component getListCellRendererComponent(JList<? extends WorkType> list, WorkType value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value == null) {
            return this;
        } else {
            setText(value.getTypeOfProduce() + ", " + value.getSalaryType() + ", " + value.getPay());
        }
        return this;
    }
}
