package GUI;

import Controller.WorkSiteCtr;
import Controller.WorkSiteCtrIF;
import Controller.WorkTaskCtr;
import Controller.WorkTaskCtrIF;
import DB.DataAccessException;
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
import java.util.Objects;

/**
 * A view extending JFrame. Used for displaying details about a specific work task.
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
public class ViewWorkTask extends JFrame {
    private DateTimePicker endDatePicker;
    private JComboBox<WorkSite> locationList;
    private JComboBox<WorkType> produceList;
    private JSpinner quantitySpinner;
    private JComboBox<String> quantityPicker;
    private DateTimePicker startDatePicker;
    private JComboBox<String> statusPicker;
    private JTextArea produceDescriptorLabel;

    private final WorkTask currentTask;
    private final SeasonalWorker currentWorker;
    private final PendingTasks pendingTasks;

    private final WorkTaskCtrIF workTaskController;

    private final ArrayList<WorkSite> workSites;
    private ArrayList<WorkType> workTypes;

    /**
     * Constructor for the WorkTask frame
     * @param currentTask specific WorkTask
     * @param currentWorker specific SeasonalWorker
     * @param pendingTasks WorkTasks having "Pending" or "Awaiting approval" as their status
     * @throws DataAccessException
     */
    public ViewWorkTask(WorkTask currentTask,SeasonalWorker currentWorker,PendingTasks pendingTasks) throws DataAccessException {
        this.currentTask = currentTask;
        this.currentWorker = currentWorker;
        this.pendingTasks = pendingTasks;

        WorkSiteCtrIF workSiteController;
        workSiteController = new WorkSiteCtr();
        workTaskController = new WorkTaskCtr();

        try {
            workSites = new ArrayList<>();
            workSites.add(workSiteController.findByWorkerCPR(currentWorker.getCpr(), true));
        } catch (DataAccessException e) {
            throw new DataAccessException("Unable to retrieve list of locations", e);
        }

        initComponents();
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
                new ViewWorkTask(currentTask,currentWorker,pendingTasks).setVisible(true);
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Initialize all components and layouts part of the panel.
     */
    private void initComponents() {

        locationList = new javax.swing.JComboBox<>();
        DefaultComboBoxModel<WorkSite> locationComboBoxModel = getLocationComboBoxModel(workSites);
        locationList.setModel(locationComboBoxModel);
        locationList.setRenderer(new WorkTaskWorkSiteComboBoxRenderer());

        produceList = new javax.swing.JComboBox<>();
        workTypes = new ArrayList<>();

        updateProduceList();

        JPanel mainContainer = new JPanel();
        JPanel topBar = new JPanel();
        JLabel maximizeBtn = new JLabel();
        JLabel exitBtn = new JLabel();
        JLabel minimizeBtn = new JLabel();
        JLabel frameTitle = new JLabel();
        JLabel profilePicture = new JLabel();
        JLabel fnameLabel = new JLabel();
        JLabel lnameLabel = new JLabel();
        JLabel fnameValue = new JLabel();
        JLabel lnameValue = new JLabel();
        JLabel locationLabel = new JLabel();
        JLabel produceLabel = new JLabel();
        JLabel startDateLabel = new JLabel();
        startDatePicker = new DateTimePicker();
        endDatePicker = new DateTimePicker();
        JLabel endDateLabel = new JLabel();
        JLabel quantityLabel = new JLabel();
        quantitySpinner = new JSpinner();
        quantityPicker = new JComboBox<>();
        JLabel statusLabel = new JLabel();
        statusPicker = new JComboBox<>();
        JButton saveChangesBtn = new JButton();
        JButton cancelBtn = new JButton();
        JTextArea locationDescriptorLabel = new JTextArea();
        produceDescriptorLabel = new JTextArea();
        JButton approveBtn = new JButton();
        JLabel emailValue = new JLabel();
        JLabel emailLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new Dimension(800, 565));
        setMinimumSize(new Dimension(800, 565));

        mainContainer.setBackground(new Color(71, 120, 197));


        frameTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        frameTitle.setText("Reviewing work task");

        ComponentsConfigure.createTopBar(topBar,frameTitle,minimizeBtn,maximizeBtn,exitBtn,new Color(120,168,252),this);

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
        ComponentsConfigure.configureWordWrapLabel(locationDescriptorLabel,workSites.get(0).getDescription() +
                "\n" +
                workSites.get(0).getStreetName() +
                " " +
                workSites.get(0).getStreetNum());
        ComponentsConfigure.configureWordWrapLabel(produceDescriptorLabel,"Produce description unavailable");
        configureLabel(emailLabel,"Email");
        configureLabel(emailValue,currentWorker.getEmail());

        startDatePicker.setBackground(new Color(120, 168, 252));
        startDatePicker.setDateTimePermissive(currentTask.getDateStart().toLocalDate().atStartOfDay());
        endDatePicker.setBackground(new Color(120, 168, 252));
        endDatePicker.setDateTimePermissive(currentTask.getDateEnd().toLocalDate().atStartOfDay());

        quantitySpinner.setValue(currentTask.getQuantity());
        quantityPicker.setModel(new DefaultComboBoxModel<>(new String[]{"Kg", "Crates"}));
        statusPicker.setModel(new DefaultComboBoxModel<>(new String[]{"Pending approval", "Approved", "Rejected"}));
        statusPicker.setSelectedItem(currentTask.getStatus());

        locationList.addActionListener(this::locationListActionPerformed);
        produceList.addActionListener(this::configureQuantity);

        ComponentsConfigure.metroBtnConfig(approveBtn);
        approveBtn.setIcon(ComponentsConfigure.approveIcon);
        approveBtn.setText("Approve");
        approveBtn.addActionListener((e) -> {
            try {
                approveWorkTask();
            } catch (DataAccessException dataAccessException) {
                dataAccessException.printStackTrace();
            }
        });

        ComponentsConfigure.metroBtnConfig(saveChangesBtn);
        saveChangesBtn.setIcon(ComponentsConfigure.saveIcon);
        saveChangesBtn.setText("Save");
        saveChangesBtn.addActionListener((e) -> {
            try {
                saveChanges();
            } catch (DataAccessException dataAccessException) {
                dataAccessException.printStackTrace();
            }
        });

        ComponentsConfigure.metroBtnConfig(cancelBtn);
        cancelBtn.setIcon(ComponentsConfigure.trashIcon);
        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener((e) -> this.dispose());

        GroupLayout mainContainerLayout = new GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
                mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(topBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.TRAILING, mainContainerLayout.createSequentialGroup()
                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(endDatePicker, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                                .addGap(135, 135, 135)
                                                                .addComponent(profilePicture, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                                .addGap(89, 89, 89)
                                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(fnameLabel)
                                                                        .addComponent(lnameLabel, GroupLayout.Alignment.TRAILING))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(fnameValue)
                                                                        .addComponent(lnameValue))))
                                                .addGap(100, 100, 100)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                                .addComponent(locationLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(locationList, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                                .addComponent(produceLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(produceList, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(locationDescriptorLabel, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(produceDescriptorLabel, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 13, Short.MAX_VALUE))))
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                                .addComponent(approveBtn, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(saveChangesBtn, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                                .addComponent(statusLabel)
                                                                .addGap(51, 51, 51)
                                                                .addComponent(statusPicker, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(emailLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(emailValue)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(startDatePicker, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(GroupLayout.Alignment.TRAILING, mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(endDateLabel)
                                                                .addGroup(mainContainerLayout.createSequentialGroup()
                                                                        .addComponent(quantityLabel)
                                                                        .addGap(31, 31, 31)
                                                                        .addComponent(quantitySpinner, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(11, 11, 11)
                                                                        .addComponent(quantityPicker, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(startDateLabel)))))
                                .addGap(14, 14, 14))
        );
        mainContainerLayout.setVerticalGroup(
                mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainContainerLayout.createSequentialGroup()
                                .addComponent(topBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(locationLabel)
                                                        .addComponent(locationList, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(locationDescriptorLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(produceList, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(produceLabel))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(produceDescriptorLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(startDateLabel)
                                                        .addComponent(startDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(endDateLabel)
                                                        .addComponent(endDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(quantityLabel)
                                                        .addComponent(quantityPicker, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(quantitySpinner, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(statusLabel)
                                                        .addComponent(statusPicker, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                                .addGap(30, 30, 30))
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addComponent(profilePicture, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(fnameLabel)
                                                        .addComponent(fnameValue))
                                                .addGap(18, 18, 18)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lnameValue)
                                                        .addComponent(lnameLabel))
                                                .addGap(18, 18, 18)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(emailLabel)
                                                        .addComponent(emailValue))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(saveChangesBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(approveBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainContainer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainContainer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
    }


    /**
     * Setup all the labels for JTextFields
     * @param label
     * @param text
     */
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
            try{
                assert selectedWorkSite != null;
                workTypes = selectedWorkSite.getWorkTypes();
            }catch(NullPointerException e){
                new StatusDialog(this,true,StatusDialog.WARNING,"Unable to retrieve work types",
                        "The system is unable to retrieve the work types assigned to this work site. " +
                                "We are sorry for the inconvenience please try again later.");
                throw new NullPointerException("Unable to retrieve the work types of the selected work site.");
            }
            DefaultComboBoxModel<WorkType> produceComboBoxModel = getProduceComboBoxModel(workTypes);
            produceList.setModel(produceComboBoxModel);
            produceList.setRenderer(new WorkTaskWorkTypeComboBoxRenderer());
        }
    }

    private void configureQuantity(ActionEvent e) {
        try{
            WorkType currentType = (WorkType) produceList.getSelectedItem();
            assert currentType != null;
            String description = currentType.getDescOfJob() +
                    "\n" +
                    "Salary is by " +
                    currentType.getSalaryType() +
                    "\n" +
                    currentType.getTypeOfProduce();
            ComponentsConfigure.configureWordWrapLabel(produceDescriptorLabel, description);
            if ((Objects.requireNonNull(currentType)).getSalaryType().equalsIgnoreCase("hourly".trim())) {
                quantitySpinner.setEnabled(false);
                quantityPicker.setEnabled(false);
            } else {
                quantitySpinner.setEnabled(true);
                quantityPicker.setEnabled(true);
            }
        }catch (NullPointerException er){
            new StatusDialog(this,true,StatusDialog.WARNING,"Unable to retrieve work types",
                    "The system is unable to retrieve the salary type assigned to this work type. " +
                            "We are sorry for the inconvenience please try again later.");
            throw new NullPointerException("Unable to retrieve the salary type for this work type.");
        }
    }

    private void approveWorkTask() throws DataAccessException {
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(currentTask.getWorkTaskID());
        if(workTaskController.approveWorkTasks(idList)){
            new StatusDialog(this,true,StatusDialog.CONFIRM,"Approved","Work task has been successfully approved");
            pendingTasks.loadPendingTasks();
            this.dispose();
        }
    }
    private void saveChanges() throws DataAccessException {
        double quantity = Double.valueOf((Integer)quantitySpinner.getValue());
        Date dateStart = Date.valueOf(startDatePicker.getDateTimePermissive().toLocalDate());
        Date dateEnd = Date.valueOf(endDatePicker.getDateTimePermissive().toLocalDate());
        long hoursWorked = Duration.between(startDatePicker.getDateTimePermissive(),endDatePicker.getDateTimePermissive()).toHours();
        String status = (String)statusPicker.getSelectedItem();
        WorkTask newTask = new WorkTask(currentTask.getWorkTaskID(), hoursWorked, quantity, dateStart, dateEnd, status, currentTask.getWorkType());
        if(workTaskController.updateWorkTask(newTask,currentTask.getWorkTaskID())){
            new StatusDialog(this,true,StatusDialog.CONFIRM,"Changes have been saved!","Your changes to the work task have been saved.");
            pendingTasks.loadPendingTasks();
            this.dispose();
        }
    }
}
