package GUI;

import Controller.*;
import Model.WorkTask;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.ArrayList;

public class PendingTasks extends JPanel {

    public PendingTasks(MainScreen mainScreen) throws DataAccessException {
        this.mainScreen = mainScreen;
        initComponents();
    }

    private void initComponents() throws DataAccessException {

        try{
            workTaskController = new WorkTaskCtr();
        }catch(DataAccessException e){
            throw new DataAccessException("Unable to retrieve work task controller.",e);
        }
        try{
            seasonalWorkerController = new SeasonalWorkerCtr();
        }catch (DataAccessException e){
            throw new DataAccessException("Unable to retrieve seasonal worker controller.",e);
        }

        //TODO: finish loading workTasks
        pendingTasks = new ArrayList<>(workTaskController.findAllPendingTasks(false));
        MAX_TASKS = pendingTasks.size();
        scrollableListContainer = new JScrollPane();
        listContainer = new JPanel();
        topBar = new JPanel();
        frameTitle = new JLabel();

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
        frameTitle.setFont(new Font("Dialog", Font.BOLD, 24)); // NOI18N

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

    private void removeBtnActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void approveBtnActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void viewBtnActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void configureLabel(JLabel label, String text) {
        label.setFont(new Font("Dialog", Font.BOLD, 24));
        label.setForeground(Color.white);
        label.setText(text);
    }

    private void setElementComponents(JPanel listElement,
                                      WorkTask currentTask,
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

        profilePicture.setIcon(new ImageIcon(getClass().getResource("/icons8_github_96px.png"))); // NOI18N

        ComponentsConfigure.metroBtnConfig(removeBtn);
        removeBtn.setIcon(ComponentsConfigure.trashIcon);
        removeBtn.addActionListener(this::removeBtnActionPerformed);

        ComponentsConfigure.metroBtnConfig(approveBtn);
        approveBtn.setIcon(ComponentsConfigure.approveIcon);
        approveBtn.addActionListener(this::approveBtnActionPerformed);

        ComponentsConfigure.metroBtnConfig(viewBtn);
        viewBtn.setIcon(ComponentsConfigure.viewIcon); // NOI18N
        viewBtn.addActionListener((e) -> {
            new ViewWorkTask(currentTask);
        });

        configureLabel(workTaskNumberLabel, "Work task " + taskNum);
        configureLabel(personName, nameValue);
        configureLabel(hoursLabel, "Hours: " + hourNum);
        configureLabel(quantityLabel, "Quantity: " + quantityNum);
        configureLabel(dateLabel, "Date: " + dateValue);
        configureLabel(workTypeLabel, "Type: " + workTypeValue);

    }

    private JPanel createListContainer(){

        listContainer = new JPanel();
        listContainerLayout = new GroupLayout(listContainer);
        listContainer.setLayout(listContainerLayout);
        listContainer.setBackground(new Color(71, 120, 197));
        listContainerLayout.setAutoCreateGaps(true);

        parallelGroup = listContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING);
        listContainerLayout.setHorizontalGroup(listContainerLayout.createSequentialGroup().addGroup(parallelGroup));

        sequentialGroup = listContainerLayout.createSequentialGroup();
        listContainerLayout.setVerticalGroup(sequentialGroup);

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
            String workTypeValue = pendingTasks.get(i).getWorkType().getSalaryType();
            String nameValue = pendingTasks.get(i).getStatus();
            setElementComponents(elementToAdd,
                    pendingTasks.get(i),
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
        return listContainer;
    }

    private void addElementToList(JPanel listElement){
        parallelGroup.addGroup(listContainerLayout.createSequentialGroup()
                .addComponent(listElement));
        sequentialGroup.addGroup(listContainerLayout.createParallelGroup()
                .addComponent(listElement));
    }

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

    // Variables declaration - do not modify
    private JLabel frameTitle;
    private JScrollPane scrollableListContainer;
    private JPanel topBar;
    private MainScreen mainScreen;

    private JPanel listContainer;

    private GroupLayout listContainerLayout;
    private GroupLayout.ParallelGroup parallelGroup;
    private GroupLayout.SequentialGroup sequentialGroup;

    private int MAX_TASKS;
    private WorkTaskCtrIF workTaskController;
    private SeasonalWorkerCtrIF seasonalWorkerController;
    private ArrayList<WorkTask> pendingTasks;
}
