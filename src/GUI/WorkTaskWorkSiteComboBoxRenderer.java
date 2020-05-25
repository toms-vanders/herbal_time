package GUI;

import Model.WorkSite;

import javax.swing.*;
import java.awt.*;

public class WorkTaskWorkSiteComboBoxRenderer extends JLabel implements ListCellRenderer<WorkSite> {
    public WorkTaskWorkSiteComboBoxRenderer() {

    }

    @Override
    public Component getListCellRendererComponent(JList<? extends WorkSite> list, WorkSite value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value == null) {
            return this;
        } else {
            setText(value.getName());
        }
        return this;
    }
}
