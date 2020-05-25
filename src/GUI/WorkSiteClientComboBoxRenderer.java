package GUI;

import Model.Client;

import javax.swing.*;
import java.awt.*;

public class WorkSiteClientComboBoxRenderer extends JLabel implements ListCellRenderer<Client> {
    public WorkSiteClientComboBoxRenderer() {

    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Client> list, Client value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.getComboBoxInfo());
        return this;
    }
}
