package GUI;

import Model.WorkSite;

import javax.swing.*;
import java.awt.*;

/**
 * Used when registering a new work task, to render a custom WorkSite combobox for choosing WorkSites
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
