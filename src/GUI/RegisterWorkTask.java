package GUI;

import Controller.SeasonalWorkerCtr;
import Controller.SeasonalWorkerCtrIF;
import Controller.WorkSiteCtr;
import Controller.WorkSiteCtrIF;
import Controller.WorkTaskCtr;
import Controller.WorkTaskCtrIF;
import DB.DataAccessException;
import GUI.Components.BackgroundWorker;
import GUI.Components.ComponentsConfigure;
import GUI.Components.StatusDialog;
import Model.SeasonalWorker;
import Model.WorkSite;
import Model.WorkTask;
import Model.WorkType;
import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * A view extending JPanel. Used for workers registering a new work task into the system
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
public class RegisterWorkTask extends JPanel {
    private JButton cancelBtn;
    private JLabel endDateLabel;
    private DateTimePicker endDatePicker;
    private JLabel locationLabel;
    private JComboBox<WorkSite> locationList;
    private JLabel produceLabel;
    private JComboBox<WorkType> produceList;
    private JSpinner quantitySpinner;
    private JLabel quantityLabel;
    private JComboBox<String> quantityPicker;
    private JButton registerBtn;
    private JPanel registerTaskPane;
    private JLabel startDateLabel;
    private DateTimePicker startDatePicker;
    private JLabel statusLabel;
    private JComboBox<String> statusPicker;
    private JLabel taskTitle;
    private JPanel subDashboard;

    private final MainScreen mainScreen;
    private final Dashboard dashboard;
    private final WorkSiteCtrIF workSiteController;
    private final WorkTaskCtrIF workTaskController;
    private final SeasonalWorkerCtrIF seasonalWorkerController;

    private final ArrayList<WorkSite> workSites;
    private ArrayList<WorkType> workTypes;
    private SeasonalWorker currentWorker;

    static final String RegisterTask = "registerTask";

    /**
     * Constructor for the RegisterWorkTask screen
     * @param dashboard Dashboard instance required for creating the view
     * @param mainScreen MainScreen instance required for creating the view
     * @throws DataAccessException
     */
    public RegisterWorkTask(Dashboard dashboard, MainScreen mainScreen) throws DataAccessException {
        this.dashboard = dashboard;
        this.mainScreen = mainScreen;

        seasonalWorkerController = new SeasonalWorkerCtr();

        workSiteController = new WorkSiteCtr();

        workTaskController = new WorkTaskCtr();

//        workSites = new ArrayList<>();
        try {
            workSites = new ArrayList<>();
            workSites.add(workSiteController.findByWorkerCPR(MainScreen.userCPR, true));
        } catch (DataAccessException e) {
            throw new DataAccessException("Unable to retrieve list of locations", e);
        }
        initComponents();
    }

    /**
     * Initialize all components and layouts part of the panel.
     */
    private void initComponents() throws DataAccessException {

        locationList = new javax.swing.JComboBox<>();
        DefaultComboBoxModel<WorkSite> locationComboBoxModel = getLocationComboBoxModel(workSites);
        locationList.setModel(locationComboBoxModel);
        locationList.setRenderer(new WorkTaskWorkSiteComboBoxRenderer());
        locationList.setEnabled(false);

        produceList = new javax.swing.JComboBox<>();
        workTypes = new ArrayList<>();

        updateProduceList();

        currentWorker = seasonalWorkerController.findSeasonalWorkerByCPR(MainScreen.userCPR);

        registerTaskPane = new JPanel();
        taskTitle = new JLabel();
        cancelBtn = new JButton();
        registerBtn = new JButton();
        locationLabel = new JLabel();
//        locationList = new JComboBox<>();
        produceLabel = new JLabel();
//        produceList = new JComboBox<>();
        startDateLabel = new JLabel();
        startDatePicker = new DateTimePicker();
        endDateLabel = new JLabel();
        endDatePicker = new DateTimePicker();
        quantityLabel = new JLabel();
        quantitySpinner = new JSpinner();
        quantityPicker = new JComboBox<>();
        statusLabel = new JLabel();
        statusPicker = new JComboBox<>();
        subDashboard = new JPanel();

        setBackground(new Color(71, 120, 197));
        setMinimumSize(new Dimension(690, 720));
        setPreferredSize(new Dimension(690, 720));
        setVerifyInputWhenFocusTarget(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        subDashboard.setLayout(new CardLayout());

        registerTaskPane.setBackground(new Color(71, 120, 197));
        registerTaskPane.setMinimumSize(new Dimension(690, 720));
        registerTaskPane.setPreferredSize(new Dimension(690, 720));

        taskTitle.setText("Registering new work task");
        taskTitle.setBackground(new Color(249, 249, 249));
        taskTitle.setFont(new Font("Dialog", Font.BOLD, 36)); // NOI18N
        taskTitle.setForeground(new Color(249, 249, 249));
        taskTitle.setPreferredSize(new Dimension(450, 50));

        ComponentsConfigure.metroBtnConfig(registerBtn);
        ComponentsConfigure.metroBtnConfig(cancelBtn);

        registerBtn.setText("Register");
        registerBtn.setIcon(ComponentsConfigure.saveIcon); // NOI18N
        registerBtn.addActionListener((e) -> {
            try {
                registerBtnActionPerformed(e);
            } catch (DataAccessException dataAccessException) {
                dataAccessException.printStackTrace();
            }
        });

        cancelBtn.setText("Cancel");
        cancelBtn.setIcon(ComponentsConfigure.trashIcon);
        cancelBtn.addActionListener((e) -> {
            new BackgroundWorker(() -> {
                try {
                    dashboard.showTaskListView();
                } catch (DataAccessException dataAccessException) {
                    dataAccessException.printStackTrace();
                }
            },"Cancel register task","Cleaning up and returning, please wait.");
        });
        configureLabel(locationLabel, "Location", ComponentsConfigure.locationIcon);
        configureLabel(produceLabel, "Produce", ComponentsConfigure.vegetableIcon);
        configureLabel(startDateLabel, "Start", ComponentsConfigure.watchIcon);
        configureLabel(endDateLabel, "End", ComponentsConfigure.presentIcon);
        configureLabel(quantityLabel, "Quantity", ComponentsConfigure.trolleyIcon);
        configureLabel(statusLabel, "Status", ComponentsConfigure.approveIcon);

        startDatePicker.setBackground(new Color(120, 168, 252));
        endDatePicker.setBackground(new Color(120, 168, 252));
        locationList.addActionListener(this::locationListActionPerformed);
        produceList.addActionListener(this::configureQuantity);

        quantityPicker.setModel(new DefaultComboBoxModel<>(new String[]{"Kg", "Crates"}));

        statusPicker.setModel(new DefaultComboBoxModel<>(new String[]{"Pending approval", "Approved", "Rejected"}));
        statusPicker.setEnabled(false);

        GroupLayout registerTaskPaneLayout = new GroupLayout(registerTaskPane);
        registerTaskPane.setLayout(registerTaskPaneLayout);
        registerTaskPaneLayout.setHorizontalGroup(
                registerTaskPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(registerTaskPaneLayout.createSequentialGroup()
                                .addGroup(registerTaskPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(registerTaskPaneLayout.createSequentialGroup()
                                                .addGap(150, 150, 150)
                                                .addComponent(locationLabel)
                                                .addGap(29, 29, 29)
                                                .addComponent(locationList, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(registerTaskPaneLayout.createSequentialGroup()
                                                .addGap(150, 150, 150)
                                                .addComponent(produceLabel)
                                                .addGap(31, 31, 31)
                                                .addComponent(produceList, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(registerTaskPaneLayout.createSequentialGroup()
                                                .addGap(150, 150, 150)
                                                .addComponent(startDateLabel)
                                                .addGap(63, 63, 63)
                                                .addComponent(startDatePicker, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(registerTaskPaneLayout.createSequentialGroup()
                                                .addGap(150, 150, 150)
                                                .addComponent(endDateLabel)
                                                .addGap(70, 70, 70)
                                                .addComponent(endDatePicker, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(registerTaskPaneLayout.createSequentialGroup()
                                                .addGap(150, 150, 150)
                                                .addComponent(quantityLabel)
                                                .addGap(30, 30, 30)
                                                .addComponent(quantitySpinner, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5, 5, 5)
                                                .addComponent(quantityPicker, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(registerTaskPaneLayout.createSequentialGroup()
                                                .addGap(150, 150, 150)
                                                .addComponent(statusLabel)
                                                .addGap(49, 49, 49)
                                                .addComponent(statusPicker, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(registerTaskPaneLayout.createSequentialGroup()
                                                .addGap(180, 180, 180)
                                                .addComponent(registerBtn, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20)
                                                .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(registerTaskPaneLayout.createSequentialGroup()
                                                .addGap(130, 130, 130)
                                                .addComponent(taskTitle, GroupLayout.PREFERRED_SIZE, 460, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(100, Short.MAX_VALUE))
        );
        registerTaskPaneLayout.setVerticalGroup(
                registerTaskPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(registerTaskPaneLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(taskTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addGroup(registerTaskPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(locationLabel)
                                        .addComponent(locationList, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addGroup(registerTaskPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(produceLabel)
                                        .addComponent(produceList, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addGroup(registerTaskPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(startDateLabel)
                                        .addComponent(startDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addGroup(registerTaskPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(endDateLabel)
                                        .addComponent(endDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addGroup(registerTaskPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(quantityLabel)
                                        .addComponent(quantitySpinner, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(quantityPicker, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addGroup(registerTaskPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(statusLabel)
                                        .addComponent(statusPicker, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(registerTaskPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(registerBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                 .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
        );

        add(subDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        subDashboard.add(registerTaskPane,RegisterTask);
    }

    /**
     * Attempt to create and insert the new work task into the database.
     * @param evt ActionEvent triggered by pressing the Register WorkTask button
     * @throws DataAccessException
     */
    private void registerBtnActionPerformed(ActionEvent evt) throws DataAccessException {
        try {
            WorkType currentWorkType = (WorkType) produceList.getSelectedItem();
            Integer workSiteID = ((WorkSite) locationList.getSelectedItem()).getWorkSiteID();
            if (startDatePicker.getDateTimePermissive() == null || endDatePicker.getDateTimePermissive() == null) {
                new StatusDialog(mainScreen,true, StatusDialog.WARNING,
                        "Date field cannot be empty",
                        "The date field cannot be empty. Please insert a correct Date value.");
                return;
            }
            Date startDate = Date.valueOf(startDatePicker.getDateTimePermissive().toLocalDate());
            Date endDate = Date.valueOf(endDatePicker.getDateTimePermissive().toLocalDate());
            long hoursWorked = Duration.between(startDatePicker.getDateTimePermissive(), endDatePicker.getDateTimePermissive()).toHours();
            int quantity;
            String status = statusPicker.getSelectedItem().toString().trim();
            if (currentWorkType.getSalaryType().equalsIgnoreCase("hourly".trim())) {
                quantity = 0;
            } else {
                quantity = (int) quantitySpinner.getValue();
                if (quantity == 0) {
                    new StatusDialog(mainScreen,true, StatusDialog.WARNING,
                            "Invalid quantity",
                            "The quantity field should not be zero, when selecting an hourly-paid work task.");
                    return;
                }
            }
            if(endDate.before(startDate)){
                new StatusDialog(mainScreen,true, StatusDialog.WARNING,
                        "Date error",
                        "Start Date cannot exceed End Date, please enter the correct date.");
                return;
            }
            // TODO (Maybe?) At least that's how it's in the report
            // should notify team leader

            if (workTaskController.insertWorkTask(new WorkTask(workSiteID,
                            hoursWorked,
                            quantity,
                            startDate,
                            endDate,
                            status,
                            currentWorkType),
                    currentWorker.getCpr())) {
                new StatusDialog(mainScreen,
                        true,
                        StatusDialog.CONFIRM,
                        "Registered work task.",
                        "Work task has been successfully added to the system!");
                dashboard.showTaskListView();
            }
        } catch (Exception e) {
            new StatusDialog(mainScreen,true, StatusDialog.WARNING,
                    "Incorrect data",
                    "There is a problem with the data in the work task to be registered.");
            e.printStackTrace();
            throw new DataAccessException("A problem has occured.", e);
        }


    }

    /**
     * Setup labels individually
     * @param label JLabel to be setup
     * @param labelText String text to go into the JLabel
     * @param icon Image to be used in the JLabel
     */
    private void configureLabel(JLabel label, String labelText, ImageIcon icon) {
        label.setIcon(icon);
        label.setText(labelText);
        label.setFocusable(false);
        label.setFont(new Font("Dialog", Font.BOLD, 18));
        label.setForeground(new Color(249, 249, 249));
    }

    /**
     * Currently unused
     * @return empty WorkSite object
     */
    private WorkSite getWorkLocation() {
        return new WorkSite();

    }

    /**
     * Create a combo box for choosing worksites
     * @param workSites List of WorkSites to choose from
     * @return The set up combo box containing the worksites
     */
    private DefaultComboBoxModel<WorkSite> getLocationComboBoxModel(List<WorkSite> workSites) {
        WorkSite[] comboBoxModel = workSites.toArray(new WorkSite[0]);
        return new DefaultComboBoxModel<>(comboBoxModel);
    }

    /**
     * Create a combo box for choosing worktypes
     * @param workTypes List of WorkTypes to choose from
     * @return The set up combo box containing the worktypes
     */
    private DefaultComboBoxModel<WorkType> getProduceComboBoxModel(List<WorkType> workTypes) {
        WorkType[] comboBoxModel = workTypes.toArray(new WorkType[0]);
        return new DefaultComboBoxModel<>(comboBoxModel);
    }

    /**
     * Call the updateProduceList function
     * @param evt
     */
    private void locationListActionPerformed(java.awt.event.ActionEvent evt) {
        updateProduceList();
    }

    /**
     *  Update the Produce list with newly fetched Produce from the database
     */
    private void updateProduceList() {
        if (locationList.getItemCount() > 0) {
            WorkSite selectedWorkSite = (WorkSite) locationList.getSelectedItem();
            try{
                workTypes = selectedWorkSite.getWorkTypes();
            }catch(NullPointerException e){
                new StatusDialog(mainScreen,true,StatusDialog.CONFIRM,"No location found",
                        "There are currently no locations available in the system.");
            }
            DefaultComboBoxModel<WorkType> produceComboBoxModel = getProduceComboBoxModel(workTypes);
            produceList.setModel(produceComboBoxModel);
            produceList.setRenderer(new WorkTaskWorkTypeComboBoxRenderer());
        }
    }

    /**
     * Enables or disables the Quantity Row in the form based on the selected WorkType
     * @param e action triggered on the produceList ComboBox
     */
    private void configureQuantity(ActionEvent e) {
        if (((WorkType) produceList.getSelectedItem()).getSalaryType().equalsIgnoreCase("hourly".trim())) {
            quantitySpinner.setEnabled(false);
            quantityPicker.setEnabled(false);
        } else {
            quantitySpinner.setEnabled(true);
            quantityPicker.setEnabled(true);
        }
    }
}
