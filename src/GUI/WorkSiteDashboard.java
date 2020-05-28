package GUI;

import Controller.DataAccessException;
import Controller.WorkSiteCtr;
import Controller.WorkSiteCtrIF;
import GUI.Components.ComponentsConfigure;
import Model.WorkSite;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class WorkSiteDashboard extends JPanel {
    private static int MAX_SITES;
    private final MainScreen mainScreen;
    private JPanel listContainer;

    private GroupLayout listContainerLayout;
    private GroupLayout.ParallelGroup parallelGroup;
    private GroupLayout.SequentialGroup sequentialGroup;

    private ArrayList<WorkSite> workSites;

    public WorkSiteDashboard(MainScreen mainScreen) throws DataAccessException {
        this.mainScreen = mainScreen;
        try {
            initComponents();
        } catch (DataAccessException e) {
            System.err.println("Issue obtaining connection.");
//            e.printStackTrace();
            // Alert the user here with e.g JDialog saying there was an issue connecting to the database.
            // TODO
            // Add a refresh button. (in this case should reload dashboard)
        }
    }
    
    private void initComponents() throws DataAccessException {

        WorkSiteCtrIF workSitesCtr;
        try {
            workSitesCtr = new WorkSiteCtr();
        }catch(DataAccessException e) {
            throw new DataAccessException("Unable to obtain seasonal worker controller instance.",e);
        }
        workSites = new ArrayList<>();
        try {
            workSites = new ArrayList<>(workSitesCtr.listAllWorkSites(false));
        }catch (DataAccessException e) {
            throw new DataAccessException("Unable to retrieve list of seasonal workers.", e);
        }
        MAX_SITES = workSites.size();

        JPanel topBar = new JPanel();
        JLabel taskTitle = new JLabel();
        JPanel mainContainer = new JPanel();
        JScrollPane scrollablePanel = new JScrollPane();
        listContainer = new JPanel();
        JButton createWorkSite = new JButton();
        JButton searchWorkSite = new JButton();
        JButton editWorkSite = new JButton();
        JButton removeWorkSite = new JButton();

        setLayout(new AbsoluteLayout());

        topBar.setBackground(new Color(120, 168, 252));
        topBar.setPreferredSize(new Dimension(1000, 110));

        taskTitle.setBackground(new Color(249, 249, 249));
        taskTitle.setFont(new Font("Dialog", Font.BOLD, 36)); // NOI18N
        taskTitle.setForeground(new Color(249, 249, 249));
        taskTitle.setText("CSWorks work sites");
        taskTitle.setPreferredSize(new Dimension(450, 50));

        GroupLayout topBarLayout = new GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
                topBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(topBarLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(taskTitle, GroupLayout.PREFERRED_SIZE, 460, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(520, Short.MAX_VALUE))
        );
        topBarLayout.setVerticalGroup(
                topBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(topBarLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(taskTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(30, Short.MAX_VALUE))
        );

        add(topBar, new AbsoluteConstraints(0, 0, -1, -1));

        mainContainer.setBackground(new Color(71, 120, 197));

        scrollablePanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollablePanel.setMinimumSize(new Dimension(900, 600));

        listContainer.setBackground(new Color(71, 120, 197));
        listContainer.setMinimumSize(new Dimension(900, 600));
        listContainer.setPreferredSize(new Dimension(1000, 700));

        listContainer = createListContainer();

        scrollablePanel.setViewportView(listContainer);

        ComponentsConfigure.metroBtnConfig(createWorkSite);
        createWorkSite.setText("Create work site");
        createWorkSite.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                mainScreen.showCreateWorkSite();
            }
        });

        ComponentsConfigure.metroBtnConfig(searchWorkSite);
        searchWorkSite.setText("Search work site");
        searchWorkSite.addActionListener((e) -> {});

        ComponentsConfigure.metroBtnConfig(editWorkSite);
        editWorkSite.setText("Edit work site");
        editWorkSite.addActionListener((e) -> {});

        ComponentsConfigure.metroBtnConfig(removeWorkSite);
        removeWorkSite.setText("Remove work site");
        removeWorkSite.addActionListener((e) -> {});

        GroupLayout mainContainerLayout = new GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
                mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainContainerLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addComponent(createWorkSite, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(scrollablePanel, GroupLayout.PREFERRED_SIZE, 658, GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10))
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(searchWorkSite, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(editWorkSite, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(removeWorkSite, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE))
                                                .addGap(693, 693, 693))))
        );
        mainContainerLayout.setVerticalGroup(
                mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainContainerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollablePanel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(mainContainerLayout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(createWorkSite, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(searchWorkSite, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(editWorkSite, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(removeWorkSite, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(160, Short.MAX_VALUE))
        );

        add(mainContainer, new AbsoluteConstraints(0, 110, 1000, 610));
    }

    private void setElementComponents(JPanel listElement, JLabel workSiteName,String name, JButton viewBtn,JButton editBtn,JButton removeBtn){
        listElement.setBackground(new Color(23, 35, 51));
        listElement.setMaximumSize(new java.awt.Dimension(650, 60));
        listElement.setMinimumSize(new java.awt.Dimension(650, 60));
        listElement.setPreferredSize(new java.awt.Dimension(650, 60));

        workSiteName.setFont(new Font("Dialog", Font.BOLD, 24));
        workSiteName.setForeground(Color.white);
        workSiteName.setText(name);

        ComponentsConfigure.metroBtnConfig(viewBtn);
        viewBtn.setIcon(new ImageIcon(getClass().getResource("/icons8_eye_32px.png"))); // NOI18N
        viewBtn.setText("View");

        ComponentsConfigure.metroBtnConfig(editBtn);
        editBtn.setIcon(new ImageIcon(getClass().getResource("/icons8_edit_32px.png"))); // NOI18N
        editBtn.setText("Edit");

        ComponentsConfigure.metroBtnConfig(removeBtn);
        removeBtn.setIcon(new ImageIcon(getClass().getResource("/icons8_trash_can_32px.png"))); // NOI18N
        removeBtn.setText("Remove");
    }
    
    private void setElementGroupsPosition(JPanel listElement, JLabel workSiteName, JButton viewBtn,JButton editBtn,JButton removeBtn){
        javax.swing.GroupLayout listElementLayout = new javax.swing.GroupLayout(listElement);
        listElement.setLayout(listElementLayout);
        listElementLayout.setHorizontalGroup(
                listElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(listElementLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(workSiteName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                                .addComponent(viewBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        listElementLayout.setVerticalGroup(
                listElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(listElementLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(listElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(workSiteName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(removeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(editBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(viewBtn)))
                                .addContainerGap())
        );
    }

    private void addElementToList(JPanel elementToAdd){
        parallelGroup.addGroup(listContainerLayout.createSequentialGroup()
                .addComponent(elementToAdd));
        sequentialGroup.addGroup(listContainerLayout.createParallelGroup()
                .addComponent(elementToAdd));
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

        for(int i = 0; i < MAX_SITES; i++){
            JPanel elementToAdd = new JPanel();
            JLabel workSiteName = new JLabel();
            JButton viewBtn = new JButton();
            JButton editBtn = new JButton();
            JButton removeBtn = new JButton();
            String name = workSites.get(i).getName();
            setElementComponents(elementToAdd, workSiteName, name, viewBtn, editBtn, removeBtn);
            setElementGroupsPosition(elementToAdd, workSiteName, viewBtn, editBtn, removeBtn);
            addElementToList(elementToAdd);
        }
        return listContainer;
    }
}
