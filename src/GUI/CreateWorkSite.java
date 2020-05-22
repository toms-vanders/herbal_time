package GUI;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;

public class CreateWorkSite extends JPanel {
    
    public CreateWorkSite() {
        initComponents();
    }
    
    private void initComponents() {

        topBar = new JPanel();
        taskTitle = new JLabel();
        mainContainer = new JPanel();
        locationLabel = new JLabel();
        locationField = new JTextField();
        cityLabel = new JLabel();
        cityField = new JTextField();
        locationInfoTitle = new JLabel();
        streetLabel = new JLabel();
        streetField = new JTextField();
        streetNoField = new JTextField();
        postalCodeLabel = new JLabel();
        postalCodeField = new JTextField();
        townLabel = new JLabel();
        townField = new JTextField();
        descriptionLabel = new JLabel();
        descriptionField = new JTextField();
        collectedProduceBtn = new JButton();
        addWorkersBtn = new JButton();
        scrollableListContainer = new JScrollPane();
        workerList = new JList<>();
        cancelBtn = new JButton();
        createBtn = new JButton();

        setMinimumSize(new Dimension(1000, 720));
        setName(""); // NOI18N
        setLayout(new AbsoluteLayout());

        topBar.setBackground(new Color(120, 168, 252));
        topBar.setPreferredSize(new Dimension(1000, 110));

        taskTitle.setBackground(new Color(249, 249, 249));
        taskTitle.setFont(new Font("Dialog", Font.BOLD, 36)); // NOI18N
        taskTitle.setForeground(new Color(249, 249, 249));
        taskTitle.setText("Create new work site");
        taskTitle.setPreferredSize(new Dimension(450, 50));

        GroupLayout topBarLayout = new GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
                topBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(topBarLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(taskTitle, GroupLayout.PREFERRED_SIZE, 460, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(523, Short.MAX_VALUE))
        );
        topBarLayout.setVerticalGroup(
                topBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(topBarLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(taskTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(35, Short.MAX_VALUE))
        );

        add(topBar, new AbsoluteConstraints(0, 0, -1, -1));

        mainContainer.setBackground(new Color(71, 120, 197));

        locationLabel.setFont(new Font("Dialog", Font.BOLD, 18)); // NOI18N
        locationLabel.setForeground(new Color(249, 249, 249));
        locationLabel.setIcon(new ImageIcon(getClass().getResource("/icons8_place_marker_32px.png"))); // NOI18N
        locationLabel.setText("Name");
        locationLabel.setFocusable(false);

        configureField(locationField,"Name of the location");
        locationField.addActionListener((e) -> {});

        cityLabel.setFont(new Font("Dialog", Font.BOLD, 18)); // NOI18N
        cityLabel.setForeground(new Color(249, 249, 249));
        cityLabel.setIcon(new ImageIcon(getClass().getResource("/icons8_place_marker_32px.png"))); // NOI18N
        cityLabel.setText("City");
        cityLabel.setFocusable(false);

        configureField(cityField,"City");
        cityField.addActionListener((e) -> {});

        locationInfoTitle.setText("Location information:");
        locationInfoTitle.setForeground(Color.white);

        streetLabel.setFont(new Font("Dialog", Font.BOLD, 18)); // NOI18N
        streetLabel.setForeground(new Color(249, 249, 249));
        streetLabel.setIcon(new ImageIcon(getClass().getResource("/icons8_place_marker_32px.png"))); // NOI18N
        streetLabel.setText("Street");
        streetLabel.setFocusable(false);

        configureField(streetField,"Streetname");
        streetField.addActionListener((e) -> {});

        configureField(streetNoField,"No.");
        streetNoField.addActionListener((e) -> {});

        postalCodeLabel.setFont(new Font("Dialog", Font.BOLD, 18)); // NOI18N
        postalCodeLabel.setForeground(new Color(249, 249, 249));
        postalCodeLabel.setIcon(new ImageIcon(getClass().getResource("/icons8_place_marker_32px.png"))); // NOI18N
        postalCodeLabel.setText("Postal code");
        postalCodeLabel.setFocusable(false);

        configureField(postalCodeField,"Postal code");
        postalCodeField.addActionListener((e) -> {});

        townLabel.setFont(new Font("Dialog", Font.BOLD, 18)); // NOI18N
        townLabel.setForeground(new Color(249, 249, 249));
        townLabel.setIcon(new ImageIcon(getClass().getResource("/icons8_place_marker_32px.png"))); // NOI18N
        townLabel.setText("Town");
        townLabel.setFocusable(false);

        configureField(townField,"Town");
        townField.addActionListener((e) -> {});

        descriptionLabel.setFont(new Font("Dialog", Font.BOLD, 18)); // NOI18N
        descriptionLabel.setForeground(new Color(249, 249, 249));
        descriptionLabel.setIcon(new ImageIcon(getClass().getResource("/icons8_place_marker_32px.png"))); // NOI18N
        descriptionLabel.setText("Description");
        descriptionLabel.setFocusable(false);

        configureField(descriptionField,"Description");
        descriptionField.addActionListener((e) -> {});

        collectedProduceBtn.setText("Collected Produce");
        ComponentsConfigure.metroBtnConfig(collectedProduceBtn);

        addWorkersBtn.setText("Add/Edit workers");
        ComponentsConfigure.metroBtnConfig(addWorkersBtn);
        addWorkersBtn.addActionListener((e) -> {});

        workerList.setModel(new AbstractListModel<>() {
            final String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        scrollableListContainer.setViewportView(workerList);

        cancelBtn.setText("Cancel");
        ComponentsConfigure.metroBtnConfig(cancelBtn);

        createBtn.setText("Create");
        ComponentsConfigure.metroBtnConfig(createBtn);

        GroupLayout mainContainerLayout = new GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
                mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainContainerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(locationInfoTitle)
                                        .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(GroupLayout.Alignment.LEADING, mainContainerLayout.createSequentialGroup()
                                                        .addComponent(cityLabel)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(cityField, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(mainContainerLayout.createSequentialGroup()
                                                        .addComponent(locationLabel)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(locationField, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(GroupLayout.Alignment.LEADING, mainContainerLayout.createSequentialGroup()
                                                        .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(postalCodeLabel)
                                                                .addComponent(streetLabel))
                                                        .addGap(18, 18, 18)
                                                        .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(postalCodeField, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(townField, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(mainContainerLayout.createSequentialGroup()
                                                                        .addComponent(streetField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(streetNoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                                        .addComponent(townLabel)
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addComponent(descriptionLabel)
                                                .addGap(18, 18, 18)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(collectedProduceBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(descriptionField, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(addWorkersBtn, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(GroupLayout.Alignment.TRAILING, mainContainerLayout.createSequentialGroup()
                                                .addComponent(createBtn, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(scrollableListContainer, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49))
        );
        mainContainerLayout.setVerticalGroup(
                mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainContainerLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(locationLabel)
                                        .addComponent(locationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addWorkersBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addComponent(locationInfoTitle)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(cityLabel)
                                                        .addComponent(cityField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(40, 40, 40)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(streetLabel)
                                                        .addComponent(streetField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(streetNoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(40, 40, 40)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(postalCodeLabel)
                                                        .addComponent(postalCodeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(42, 42, 42)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(townLabel)
                                                        .addComponent(townField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(40, 40, 40)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(descriptionLabel)
                                                        .addComponent(descriptionField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(scrollableListContainer, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)))
                                .addGap(50, 50, 50)
                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(collectedProduceBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(createBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(56, Short.MAX_VALUE))
        );

        add(mainContainer, new AbsoluteConstraints(0, 110, 1000, 610));
    }

    public void configureField(JTextField field,String text){
        field.setMinimumSize(new Dimension(65, 26));
        field.setPreferredSize(new Dimension(65, 26));
        field.setUI(new JTextFieldHintUI(text,Color.GRAY));
    }
    
    private JButton addWorkersBtn;
    private JButton cancelBtn;
    private JTextField cityField;
    private JLabel cityLabel;
    private JButton collectedProduceBtn;
    private JButton createBtn;
    private JTextField descriptionField;
    private JLabel descriptionLabel;
    private JTextField locationField;
    private JLabel locationInfoTitle;
    private JLabel locationLabel;
    private JPanel mainContainer;
    private JTextField postalCodeField;
    private JLabel postalCodeLabel;
    private JScrollPane scrollableListContainer;
    private JTextField streetField;
    private JLabel streetLabel;
    private JTextField streetNoField;
    private JLabel taskTitle;
    private JPanel topBar;
    private JTextField townField;
    private JLabel townLabel;
    private JList<String> workerList;
}
