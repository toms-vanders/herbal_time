package GUI;

import Controller.*;
import Model.SeasonalWorker;
import Model.WorkSite;
import Model.WorkTask;
import Model.WorkType;
import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ViewWorkTask extends JFrame {

    public ViewWorkTask(WorkTask currentTask,SeasonalWorker currentWorker) throws DataAccessException {
        this.currentTask = currentTask;
        this.currentWorker = currentWorker;

        try {
            workSiteController = new WorkSiteCtr();
        } catch (DataAccessException e) {
            throw new DataAccessException("Unable to obtain work site controller instance.", e);
        }

        try {
            workTaskController = new WorkTaskCtr();
        } catch (DataAccessException e) {
            throw new DataAccessException("Unable to obtain work task controller instance.", e);
        }

        try {
            workSites = new ArrayList<>();
            workSites.add(workSiteController.findByWorkerCPR(currentWorker.getCpr(), true));
        } catch (DataAccessException e) {
            throw new DataAccessException("Unable to retrieve list of locations", e);
        }

        initComponents();
    }

    private void initComponents() {

        locationList = new javax.swing.JComboBox<>();
        DefaultComboBoxModel<WorkSite> locationComboBoxModel = getLocationComboBoxModel(workSites);
        locationList.setModel(locationComboBoxModel);
        locationList.setRenderer(new WorkTaskWorkSiteComboBoxRenderer());

        produceList = new javax.swing.JComboBox<>();
        workTypes = new ArrayList<>();

        updateProduceList();

        jPanel1 = new JPanel();
        topBar = new JPanel();
        maximizeBtn = new JLabel();
        exitBtn = new JLabel();
        minimizeBtn = new JLabel();
        frameTitle = new JLabel();
        profilePicture = new JLabel();
        fnameLabel = new JLabel();
        lnameLabel = new JLabel();
        fnameValue = new JLabel();
        lnameValue = new JLabel();
        locationLabel = new JLabel();
        produceLabel = new JLabel();
        startDateLabel = new JLabel();
        startDatePicker = new com.github.lgooddatepicker.components.DateTimePicker();
        endDatePicker = new com.github.lgooddatepicker.components.DateTimePicker();
        endDateLabel = new JLabel();
        quantityLabel = new JLabel();
        quantitySpinner = new JSpinner();
        quantityPicker = new JComboBox<>();
        statusLabel = new JLabel();
        statusPicker = new JComboBox<>();
        saveChangesBtn = new JButton();
        cancelBtn = new JButton();
        locationDescriptorLabel = new JLabel();
        produceDescriptorLabel = new JLabel();
        approveBtn = new JButton();
        emailValue = new JLabel();
        emailLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new Dimension(800, 565));
        setMinimumSize(new Dimension(800, 565));

        jPanel1.setBackground(new Color(71, 120, 197));

        ComponentsConfigure.topBarConfig(topBar,this, new Color(120,168,252));
        ComponentsConfigure.topBarButtons(minimizeBtn,maximizeBtn,exitBtn,this);

        frameTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        frameTitle.setText("Reviewing work task");

        GroupLayout topBarLayout = new GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
                topBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, topBarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(frameTitle)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(minimizeBtn)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(maximizeBtn)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exitBtn)
                                .addContainerGap())
        );
        topBarLayout.setVerticalGroup(
                topBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, topBarLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(topBarLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(frameTitle)
                                        .addGroup(topBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(minimizeBtn)
                                                .addComponent(exitBtn)
                                                .addComponent(maximizeBtn)))
                                .addContainerGap())
        );

        profilePicture.setIcon(new ImageIcon(getClass().getResource("/icons8_github_96px.png")));

        configureLabel(fnameLabel,"Firstname:");
        configureLabel(lnameLabel,"Lastname:");
        configureLabel(fnameValue,currentWorker.getFname());
        configureLabel(lnameValue,currentWorker.getLname());
        configureLabel(locationLabel,"Location");
        locationLabel.setIcon(ComponentsConfigure.locationIcon);
        configureLabel(produceLabel,"Produce");
        produceLabel.setIcon(ComponentsConfigure.vegetableIcon);
        configureLabel(startDateLabel,"Start");
        startDateLabel.setIcon(ComponentsConfigure.watchIcon);
        configureLabel(endDateLabel,"End");
        endDateLabel.setIcon(ComponentsConfigure.presentIcon);
        configureLabel(quantityLabel,"Quantity");
        quantityLabel.setIcon(ComponentsConfigure.trolleyIcon);
        configureLabel(statusLabel,"Status");
        statusLabel.setIcon(ComponentsConfigure.approveIcon);
        configureLabel(locationDescriptorLabel,"Full-address or something");//TODO: Query workSite for info
        configureLabel(produceDescriptorLabel,"Produce description");//TODO: Query workType for info
        configureLabel(emailLabel,"Email");
        configureLabel(emailValue,currentWorker.getEmail());

        startDatePicker.setBackground(new Color(120, 168, 252));
        endDatePicker.setBackground(new Color(120, 168, 252));

        quantityPicker.setModel(new DefaultComboBoxModel<>(new String[]{"Kg", "Crates"}));
        statusPicker.setModel(new DefaultComboBoxModel<>(new String[]{"Pending approval", "Approved", "Rejected"}));

        locationList.addActionListener(this::locationListActionPerformed);
        produceList.addActionListener(this::configureQuantity);

        ComponentsConfigure.metroBtnConfig(approveBtn);
        approveBtn.setIcon(ComponentsConfigure.approveIcon);
        approveBtn.setText("Approve");
        approveBtn.addActionListener((e) -> {});//TODO: ApproveBtn

        ComponentsConfigure.metroBtnConfig(saveChangesBtn);
        saveChangesBtn.setIcon(ComponentsConfigure.saveIcon);
        saveChangesBtn.setText("Save");
        saveChangesBtn.addActionListener((e) -> {});//TODO: saveChangesBtn

        ComponentsConfigure.metroBtnConfig(cancelBtn);
        cancelBtn.setIcon(ComponentsConfigure.trashIcon);
        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener((e) -> {});//TODO: cancelBtn

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(topBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(endDatePicker, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(135, 135, 135)
                                                                .addComponent(profilePicture, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(89, 89, 89)
                                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(fnameLabel)
                                                                        .addComponent(lnameLabel, GroupLayout.Alignment.TRAILING))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(fnameValue)
                                                                        .addComponent(lnameValue))))
                                                .addGap(100, 100, 100)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(locationLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(locationList, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(produceLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(produceList, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(locationDescriptorLabel, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(produceDescriptorLabel, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 13, Short.MAX_VALUE))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(approveBtn, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(saveChangesBtn, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(statusLabel)
                                                                .addGap(51, 51, 51)
                                                                .addComponent(statusPicker, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(emailLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(emailValue)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(startDatePicker, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(endDateLabel)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addComponent(quantityLabel)
                                                                        .addGap(31, 31, 31)
                                                                        .addComponent(quantitySpinner, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(11, 11, 11)
                                                                        .addComponent(quantityPicker, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(startDateLabel)))))
                                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(topBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(locationLabel)
                                                        .addComponent(locationList, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(locationDescriptorLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(produceList, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(produceLabel))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(produceDescriptorLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(startDateLabel)
                                                        .addComponent(startDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(endDateLabel)
                                                        .addComponent(endDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(quantityLabel)
                                                        .addComponent(quantityPicker, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(quantitySpinner, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(statusLabel)
                                                        .addComponent(statusPicker, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                                .addGap(30, 30, 30))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(profilePicture, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(fnameLabel)
                                                        .addComponent(fnameValue))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lnameValue)
                                                        .addComponent(lnameLabel))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(emailLabel)
                                                        .addComponent(emailValue))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(saveChangesBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(approveBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
    }

    private void saveChangesBtnActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void approveBtnActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    public void start(WorkTask currentTask, SeasonalWorker currentWorker) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewWorkTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> {
            try {
                new ViewWorkTask(currentTask,currentWorker).setVisible(true);
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        });
    }
    
    private void configureLabel(JLabel label, String text){
        label.setFont(new Font("Dialog", Font.BOLD, 24));
        label.setForeground(new Color(255, 255, 255));
        label.setText(text);
        label.setFocusable(false);
    }

    private DefaultComboBoxModel<WorkSite> getLocationComboBoxModel(java.util.List<WorkSite> workSites) {
        WorkSite[] comboBoxModel = workSites.toArray(new WorkSite[0]);
        return new DefaultComboBoxModel<>(comboBoxModel);
    }

    private DefaultComboBoxModel<WorkType> getProduceComboBoxModel(List<WorkType> workTypes) {
        WorkType[] comboBoxModel = workTypes.toArray(new WorkType[0]);
        return new DefaultComboBoxModel<>(comboBoxModel);
    }

    private void locationListActionPerformed(java.awt.event.ActionEvent evt) {
        updateProduceList();
    }

    private void updateProduceList() {
        if (locationList.getItemCount() > 0) {
            WorkSite selectedWorkSite = (WorkSite) locationList.getSelectedItem();
            workTypes = selectedWorkSite.getWorkTypes();
            DefaultComboBoxModel<WorkType> produceComboBoxModel = getProduceComboBoxModel(workTypes);
            produceList.setModel(produceComboBoxModel);
            produceList.setRenderer(new WorkTaskWorkTypeComboBoxRenderer());
        }
    }

    private void configureQuantity(ActionEvent e) {
        if (((WorkType) produceList.getSelectedItem()).getSalaryType().equalsIgnoreCase("hourly".trim())) {
            quantitySpinner.setEnabled(false);
            quantityPicker.setEnabled(false);
        } else {
            quantitySpinner.setEnabled(true);
            quantityPicker.setEnabled(true);
        }
    }

    private JButton cancelBtn;
    private JLabel emailLabel;
    private JLabel emailValue;
    private JLabel endDateLabel;
    private DateTimePicker endDatePicker;
    private JLabel exitBtn;
    private JLabel fnameLabel;
    private JLabel fnameValue;
    private JLabel frameTitle;
    private JPanel jPanel1;
    private JLabel lnameLabel;
    private JLabel lnameValue;
    private JLabel locationLabel;
    private JLabel locationDescriptorLabel;
    private JLabel produceDescriptorLabel;
    private JComboBox<WorkSite> locationList;
    private JLabel maximizeBtn;
    private JLabel minimizeBtn;
    private JLabel produceLabel;
    private JComboBox<WorkType> produceList;
    private JLabel profilePicture;
    private JSpinner quantitySpinner;
    private JLabel quantityLabel;
    private JComboBox<String> quantityPicker;
    private JButton saveChangesBtn;
    private JButton approveBtn;
    private JLabel startDateLabel;
    private DateTimePicker startDatePicker;
    private JLabel statusLabel;
    private JComboBox<String> statusPicker;
    private JPanel topBar;

    private WorkTask currentTask;
    private SeasonalWorker currentWorker;

    private WorkSiteCtrIF workSiteController;
    private WorkTaskCtrIF workTaskController;

    private ArrayList<WorkSite> workSites;
    private ArrayList<WorkType> workTypes;
}
