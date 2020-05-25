package GUI;

import Model.WorkType;

import javax.swing.*;
import java.awt.*;

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
