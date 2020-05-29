package GUI;

import Controller.SeasonalWorkerCtr;
import Controller.SeasonalWorkerCtrIF;
import Controller.WorkTaskCtr;
import Controller.WorkTaskCtrIF;
import DB.DataAccessException;
import GUI.Components.BackgroundWorker;
import GUI.Components.ComponentsConfigure;
import GUI.Components.StatusDialog;
import Model.SeasonalWorker;
import Model.WorkTask;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;

/**
 * A view extending JPanel. Allows a team-lead to approve currently pending work tasks
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
public class PendingTasks extends JPanel {
    private final MainScreen mainScreen;
    private JPanel listContainer;
    private JScrollPane scrollableListContainer;

    private GroupLayout listContainerLayout;
    private GroupLayout.ParallelGroup parallelGroup;
    private GroupLayout.SequentialGroup sequentialGroup;

    private int MAX_TASKS = 0;
    private WorkTaskCtrIF workTaskController;
    private SeasonalWorkerCtrIF seasonalWorkerController;
    private ArrayList<WorkTask> pendingTasks;

    /**
     * Constructor of the panel.
     *
     * @param mainScreen MainScreen instance used for navigation
     * @throws DataAccessException Thrown if there is no connection
     */
    public PendingTasks(MainScreen mainScreen) throws DataAccessException {
        this.mainScreen = mainScreen;
        initComponents();
    }

    /**
     * Initialize all components and layouts part of the panel.
     */
    private void initComponents() {

        workTaskController = new WorkTaskCtr();
        seasonalWorkerController = new SeasonalWorkerCtr();

        //TODO: finish loading workTasks
        scrollableListContainer = new JScrollPane();
        listContainer = new JPanel();
        JPanel topBar = new JPanel();
        JLabel frameTitle = new JLabel();

        setBackground(new Color(71, 120, 197));
        setEnabled(false);
        setMinimumSize(new Dimension(1000, 720));
        setPreferredSize(new Dimension(1000, 720));
        setLayout(new AbsoluteLayout());

        scrollableListContainer.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableListContainer.setMaximumSize(new Dimension(1000, 720));
        scrollableListContainer.setPreferredSize(new Dimension(1000, 720));

        listContainer.setBackground(new Color(71, 120, 197));
        listContainer.setMaximumSize(new Dimension(1000, 720));
        listContainer.setPreferredSize(new Dimension(1000, 720));
        listContainer = createListContainer();

        scrollableListContainer.setViewportView(listContainer);

        add(scrollableListContainer, new AbsoluteConstraints(10, 54, 980, 660));

        topBar.setBackground(new Color(120, 168, 252));
        topBar.setMaximumSize(new Dimension(1000, 44));
        topBar.setMinimumSize(new Dimension(1000, 44));

        frameTitle.setText("Pending work tasks");
        frameTitle.setForeground(Color.white);
        frameTitle.setFont(new Font("Dialog", Font.BOLD, 24));

        GroupLayout topBarLayout = new GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
                topBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, topBarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(frameTitle)
                                .addContainerGap(768, Short.MAX_VALUE))
        );
        topBarLayout.setVerticalGroup(
                topBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, topBarLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(frameTitle)
                                .addContainerGap())
        );

        add(topBar, new AbsoluteConstraints(0, 0, -1, -1));
    }

    /**
     * ActionListener method used to remove a work task from the system.
     *
     * @param workTaskID Integer of the work task ID to be removed
     * @throws DataAccessException Thrown if there is no internet connection
     */
    private void removeBtnActionPerformed(Integer workTaskID) throws DataAccessException {
        try{
            if(workTaskController.deleteWorkTask(workTaskID)){
                new StatusDialog(mainScreen,true,StatusDialog.CONFIRM,"Approved","Work task has been successfully deleted.");
                loadPendingTasks();
                scrollableListContainer.validate();
            }
        }catch(DataAccessException e){
            throw new DataAccessException("Unable to remove work task from the system.",e);
        }
    }

    /**
     * ActionListener method used to approve a work task in the system.
     *
     * @param workTaskID Integer of the work task ID to be approved
     */
    private void approveBtnActionPerformed(Integer workTaskID) {
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(workTaskID);
        new BackgroundWorker(() -> {
            try {
                if(workTaskController.approveWorkTasks(idList)){
                    SwingUtilities.invokeLater(() -> new StatusDialog(mainScreen,true,StatusDialog.CONFIRM,"Approved","Work task has been successfully approved"));
                    loadPendingTasks();
                    scrollableListContainer.validate();
                }
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        },"Approved","Work task is being processed");
    }

    /**
     * Configure JLabels to fit the metro style of the system.
     *
     * @param label JLabel to be configured
     * @param text String of text to be displayed inside the JLabel
     */
    private void configureLabel(JLabel label, String text) {
        label.setFont(new Font("Dialog", Font.BOLD, 24));
        label.setForeground(Color.white);
        label.setText(text);
    }

    /**
     * Configures the given elements to fit the metro style and appear as an advanced JListItem.
     *
     * @param listElement JPanel to be used as the container
     * @param currentTask WorkTask object from which to retrieve information
     * @param currentWorker SeasonalWorker object from which to retrieve information
     * @param profilePicture JLabel to display the worker profile picture
     * @param personName JLabel to display the worker name
     * @param nameValue String of the workers name
     * @param approveBtn JButton for approval
     * @param viewBtn JButton for viewing
     * @param removeBtn JButton for removing
     * @param workTaskNumberLabel JLabel of the work task number
     * @param taskNum Integer of the task number
     * @param hoursLabel JLabel of the hours
     * @param hourNum double type of the hours value
     * @param quantityLabel JLabel of the quantity
     * @param quantityNum double type of the quantity value
     * @param dateLabel JLabel of the date
     * @param dateValue Date value
     * @param workTypeLabel JLabel of the work type
     * @param workTypeValue String of the work type value
     */
    private void setElementComponents(JPanel listElement,
                                      WorkTask currentTask,
                                      SeasonalWorker currentWorker,
                                      JLabel profilePicture,
                                      JLabel personName,
                                      String nameValue,
                                      JButton approveBtn,
                                      JButton viewBtn,
                                      JButton removeBtn,
                                      JLabel workTaskNumberLabel,
                                      int taskNum,
                                      JLabel hoursLabel,
                                      double hourNum,
                                      JLabel quantityLabel,
                                      double quantityNum,
                                      JLabel dateLabel,
                                      Date dateValue,
                                      JLabel workTypeLabel,
                                      String workTypeValue) {
        listElement.setBackground(new Color(23, 35, 51));
        listElement.setMaximumSize(new Dimension(960, 112));
        listElement.setPreferredSize(new Dimension(960, 112));
        listElement.setMinimumSize(new Dimension(960, 112));

        profilePicture.setIcon(ComponentsConfigure.defaultProfile);

        ComponentsConfigure.metroBtnConfig(removeBtn);
        removeBtn.setIcon(ComponentsConfigure.trashIcon);
        removeBtn.addActionListener((e) -> {
            try {
                removeBtnActionPerformed(currentTask.getWorkTaskID());
            } catch (DataAccessException dataAccessException) {
                dataAccessException.printStackTrace();
            }
        });

        ComponentsConfigure.metroBtnConfig(approveBtn);
        approveBtn.setIcon(ComponentsConfigure.approveIcon);
        approveBtn.addActionListener((e) -> {
            approveBtnActionPerformed(currentTask.getWorkTaskID());
        });

        ComponentsConfigure.metroBtnConfig(viewBtn);
        viewBtn.setIcon(ComponentsConfigure.viewIcon);
        viewBtn.addActionListener((e) -> {
            try {
                new ViewWorkTask(currentTask,currentWorker,this).start(currentTask,currentWorker);
            } catch (DataAccessException dataAccessException) {
                dataAccessException.printStackTrace();
            }
        });

        configureLabel(workTaskNumberLabel, "Work task " + taskNum);
        configureLabel(personName, nameValue);
        configureLabel(hoursLabel, "Hours: " + hourNum);
        configureLabel(quantityLabel, "Quantity: " + quantityNum);
        configureLabel(dateLabel, "Date: " + dateValue);
        configureLabel(workTypeLabel, "Type: " + workTypeValue);

    }

    /**
     * Creates a container using the absolute layout and prepares it for
     * the dynamically created list of elements to be added to it.
     *
     * @return JPanel once configured
     */
    private JPanel createListContainer() {

        listContainer = new JPanel();
        listContainerLayout = new GroupLayout(listContainer);
        listContainer.setLayout(listContainerLayout);
        listContainer.setBackground(new Color(71, 120, 197));
        listContainerLayout.setAutoCreateGaps(true);

        parallelGroup = listContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING);
        listContainerLayout.setHorizontalGroup(listContainerLayout.createSequentialGroup().addGroup(parallelGroup));

        sequentialGroup = listContainerLayout.createSequentialGroup();
        listContainerLayout.setVerticalGroup(sequentialGroup);

        return listContainer;
    }

    /**
     * Takes given JPanel and adds it to the listContainer to be displayed.
     *
     * @param listElement JPanel to be added to the list
     */
    private void addElementToList(JPanel listElement){
        parallelGroup.addGroup(listContainerLayout.createSequentialGroup()
                .addComponent(listElement));
        sequentialGroup.addGroup(listContainerLayout.createParallelGroup()
                .addComponent(listElement));
    }

    /**
     * Configures the layout of the given JPanel to fit the various components in a JListItem like fashion.
     *
     * @param listElement JPanel to be used as the container
     * @param profilePicture JLabel to display the worker profile picture
     * @param personName JLabel to display the worker name
     * @param approveBtn JButton for approval
     * @param viewBtn JButton for viewing
     * @param removeBtn JButton for removing
     * @param workTaskNumberLabel JLabel of the work task number
     * @param hoursLabel JLabel of the hours
     * @param quantityLabel JLabel of the quantity
     * @param dateLabel JLabel of the date
     * @param workTypeLabel JLabel of the work type
     */
    private void setElementGroupsPosition(JPanel listElement,
                                          JLabel profilePicture,
                                          JLabel personName,
                                          JButton approveBtn,
                                          JButton viewBtn,
                                          JButton removeBtn,
                                          JLabel workTaskNumberLabel,
                                          JLabel hoursLabel,
                                          JLabel quantityLabel,
                                          JLabel dateLabel,
                                          JLabel workTypeLabel) {
        GroupLayout listElementLayout = new GroupLayout(listElement);
        listElement.setLayout(listElementLayout);
        listElementLayout.setHorizontalGroup(
                listElementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(listElementLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(profilePicture, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(listElementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(workTaskNumberLabel)
                                        .addComponent(personName))
                                .addGap(18, 18, 18)
                                .addGroup(listElementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(hoursLabel)
                                        .addComponent(dateLabel))
                                .addGap(18, 18, 18)
                                .addGroup(listElementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(quantityLabel)
                                        .addComponent(workTypeLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 287, Short.MAX_VALUE)
                                .addComponent(viewBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(approveBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        listElementLayout.setVerticalGroup(
                listElementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(listElementLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(listElementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, listElementLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(listElementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(removeBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(approveBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(viewBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(33, 33, 33))
                                        .addGroup(listElementLayout.createSequentialGroup()
                                                .addGroup(listElementLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(listElementLayout.createSequentialGroup()
                                                                .addGroup(listElementLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(personName)
                                                                        .addComponent(hoursLabel)
                                                                        .addComponent(quantityLabel))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(listElementLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(workTaskNumberLabel)
                                                                        .addComponent(dateLabel)
                                                                        .addComponent(workTypeLabel))
                                                                .addGap(18, 18, 18))
                                                        .addComponent(profilePicture, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }

    /**
     * Creates the elements that are part of the dynamic list and configures them accordingly.
     *
     * @throws DataAccessException Thrown if there is no connection
     */
    private void createElements() throws DataAccessException {
        if(MAX_TASKS > 0){
            for(int i = 0; i < MAX_TASKS; i++){
                JPanel elementToAdd = new JPanel();
                JLabel personName = new JLabel();
                JLabel hoursLabel = new JLabel();
                JLabel quantityLabel = new JLabel();
                JLabel dateLabel = new JLabel();
                JLabel workTaskNumberLabel = new JLabel();
                JLabel workTypeLabel = new JLabel();
                JLabel profilePicture = new JLabel();
                JButton approveBtn = new JButton();
                JButton viewBtn = new JButton();
                JButton removeBtn = new JButton();
                double hourNum = pendingTasks.get(i).getHoursWorked();
                double quantityNum = pendingTasks.get(i).getQuantity();
                Date dateValue = pendingTasks.get(i).getDateStart();
                String workTypeValue = pendingTasks.get(i).getWorkType().getSalaryType().trim();
                SeasonalWorker currentWorker = seasonalWorkerController.findSeasonalWorkerByWorkTask(pendingTasks.get(i).getWorkTaskID());
                String nameValue = currentWorker.getFname() + " " + currentWorker.getLname();
                setElementComponents(elementToAdd,
                        pendingTasks.get(i),
                        currentWorker,
                        profilePicture,
                        personName,
                        nameValue,
                        approveBtn,
                        viewBtn,
                        removeBtn,
                        workTaskNumberLabel,
                        i,
                        hoursLabel,
                        hourNum,
                        quantityLabel,
                        quantityNum,
                        dateLabel,
                        dateValue,
                        workTypeLabel,
                        workTypeValue);
                setElementGroupsPosition(elementToAdd,
                        profilePicture,
                        personName,
                        approveBtn,
                        viewBtn,
                        removeBtn,
                        workTaskNumberLabel,
                        hoursLabel,
                        quantityLabel,
                        dateLabel,
                        workTypeLabel);
                addElementToList(elementToAdd);
            }
        }
        scrollableListContainer.revalidate();
        listContainer.revalidate();
        scrollableListContainer.repaint();
        listContainer.repaint();
    }

    /**
     * Sets the visibility of the panel while also loading the tasks through
     * a background process operation.
     *
     * @param aFlag boolean to define visibility state, true is show, false is invisible
     */
    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        new BackgroundWorker(()->{
            if(aFlag){
                try {
                    loadPendingTasks();
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
            }else{
                if(!(pendingTasks == null)){
                    if(!pendingTasks.isEmpty()){
                        pendingTasks.clear();
                    }
                }
                listContainer.removeAll();
                listContainer.revalidate();
                listContainer.repaint();
            }
        }
        ,"Loading tasks","Loading pending tasks from the database, please wait.");
    }

    /**
     * Clears up variables and re-instantiates them to ensure consistency within the views.
     *
     * @throws DataAccessException If the database cannot be accessed to return a list of tasks
     */
    void loadPendingTasks() throws DataAccessException {
        if(!(pendingTasks == null)){
            if(!pendingTasks.isEmpty()){
                pendingTasks.clear();
            }
        }
        pendingTasks = new ArrayList<>(workTaskController.findAllPendingTasks(true));
        MAX_TASKS = pendingTasks.size();
        listContainer.removeAll();
        createElements();
    }
}
