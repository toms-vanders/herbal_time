package GUI;

import DB.DataAccessException;
import Controller.SeasonalWorkerCtr;
import Controller.SeasonalWorkerCtrIF;
import GUI.Components.ComponentsConfigure;
import GUI.Components.StatusDialog;
import Model.SeasonalWorker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A pop-up window to display seasonal workers, along with the options to edit or delete individual entries
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
public class WorkersScreen extends JFrame {
    private static Integer MAX_WORKERS;
    private JPanel listContainer;

    private GroupLayout listContainerLayout;
    private GroupLayout.ParallelGroup parallelGroup;
    private GroupLayout.SequentialGroup sequentialGroup;

    private ArrayList<SeasonalWorker> seasonalWorkers;

    /**
     * Constructor for the Workers screen
     */
    public WorkersScreen() {
        try {
            initComponents();
        } catch (DataAccessException e) {
            System.err.println("Issue obtaining connection.");
//            e.printStackTrace();
            new GUI.Components.StatusDialog(this,true, StatusDialog.WARNING,"Error connecting",
            "There was an error obtaining connection. Please try again later.");
            // TODO need to discuss loading and also refresh button
            // probably should also return or something
        }
    }

    /**
     * Main thread for the frame to initialize the look and feel of the frame as well as
     * displaying it once everything has been created and added.
     *
     * @param args Java arguments array
     */
    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(WorkersScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> {
//            try {
//                new WorkersScreen().setVisible(true);
//            } catch (DataAccessException e) {
//                e.printStackTrace();
//            }
            new WorkersScreen().setVisible(true);
        });
    }

    public void start(){
        main(null);
    }

    /**
     * Initialize all components and layouts part of the frame.
     *
     * @throws DataAccessException if controllers cannot be instantiated
     */
    private void initComponents() throws DataAccessException {

        SeasonalWorkerCtrIF seasonalWorkerController;
        seasonalWorkerController = new SeasonalWorkerCtr();
        seasonalWorkers = new ArrayList<>();
        try {
            seasonalWorkers = new ArrayList<>(seasonalWorkerController.findAllSeasonalWorkers());
        }catch (DataAccessException e) {
            throw new DataAccessException("Unable to retrieve list of seasonal workers.", e);
        }
        MAX_WORKERS = seasonalWorkers.size();

        JPanel mainContainer = new JPanel();
        JPanel topBar = new JPanel();
        JLabel maximizeBtn = new JLabel();
        JLabel exitBtn = new JLabel();
        JLabel minimizeBtn = new JLabel();
        JLabel frameTitle = new JLabel();
        JButton addBtn = new JButton();
        JScrollPane scrollablePanel = new JScrollPane();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        mainContainer.setBackground(new Color(71, 120, 197));

        frameTitle.setFont(ComponentsConfigure.defaultFont);
        frameTitle.setText("CS Works");

        ComponentsConfigure.createTopBar(topBar,frameTitle,minimizeBtn,maximizeBtn,exitBtn,new Color(120,168,252),this);

        addBtn.setText("Add");
        ComponentsConfigure.metroBtnConfig(addBtn);
        //TODO: add button action listener
        addBtn.addActionListener(e -> {});



        scrollablePanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        listContainer = createListContainer();

        scrollablePanel.setViewportView(listContainer);

        GroupLayout mainContainerLayout = new GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
                mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(topBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(mainContainerLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addBtn, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addGap(588, 588, 588))
                        .addGroup(mainContainerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollablePanel, GroupLayout.PREFERRED_SIZE, 782, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainContainerLayout.setVerticalGroup(
                mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainContainerLayout.createSequentialGroup()
                                .addComponent(topBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollablePanel, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainContainer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainContainer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setIconImage(ComponentsConfigure.plantIcon.getImage());
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Configures the given elements to fit the metro style and appear as an advanced JListItem
     * @param listElement JPanel to be used as the container
     * @param profilePicture JLabel to display the client profile picture
     * @param personName JLabel to display the worker name
     * @param name String containing the worker name
     * @param editBtn JButton for editing the worker
     * @param removeBtn JButton for deleting the worker
     */
    private void setElementComponents(JPanel listElement, JLabel profilePicture,JLabel personName, String name,JButton editBtn,JButton removeBtn) {
        listElement.setBackground(new Color(23, 35, 51));
        listElement.setMaximumSize(new Dimension(770, 112));
        listElement.setPreferredSize(new Dimension(770, 112));
        listElement.setMinimumSize(new Dimension(770, 112));

        profilePicture.setIcon(ComponentsConfigure.defaultProfile); // NOI18N

        personName.setFont(ComponentsConfigure.defaultFont); // NOI18N
        personName.setForeground(Color.WHITE);
        personName.setText(name);

        editBtn.setBackground(new Color(23, 35, 51));
        editBtn.setIcon(ComponentsConfigure.editIcon); // NOI18N
        editBtn.setText("Edit");
        ComponentsConfigure.metroBtnConfig(editBtn);
        //TODO: edit button action listener
        editBtn.addActionListener(e -> {});

        removeBtn.setBackground(new Color(23, 35, 51));
        removeBtn.setIcon(ComponentsConfigure.trashIcon); // NOI18N
        removeBtn.setText("Remove");
        ComponentsConfigure.metroBtnConfig(removeBtn);
        //TODO: remove button action listener
        removeBtn.addActionListener((e -> {}));
    }

    /**
     * Creates a container using the absolute layout and prepares it for
     * the dynamically created list of elements to be added to it.
     * @return JPanel once configured
     */
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

        for(int i = 0; i < MAX_WORKERS; i++){
            JPanel elementToAdd = new JPanel();
            JLabel profilePicture = new JLabel();
            JLabel personName = new JLabel();
            JButton editBtn = new JButton();
            JButton removeBtn = new JButton();
            String name = seasonalWorkers.get(i).getFname() + " " + seasonalWorkers.get(i).getLname();
            setElementComponents(elementToAdd, profilePicture, personName, name, editBtn, removeBtn);
            setElementGroupsPosition(elementToAdd, profilePicture, personName, editBtn, removeBtn);
            addElementToList(elementToAdd);
        }
        return listContainer;
    }

    /**
     * Takes given JPanel and adds it to the listContainer to be displayed
     * @param listElement JPanel to be added to the list
     */
    private void addElementToList(JPanel listElement){
        parallelGroup.addGroup(listContainerLayout.createSequentialGroup()
                .addComponent(listElement));
        sequentialGroup.addGroup(listContainerLayout.createParallelGroup()
                .addComponent(listElement));
    }

    /**
     * Configures the layout of the given JPanel to fit the various components in a JListItem like fashion
     * @param listElement Configures the layout of the given JPanel to fit the various components in a JListItem like fashion
     * @param profilePicture JLabel to display the worker profile picture
     * @param personName JLabel to display the worker name
     * @param editBtn JButton to edit the worker
     * @param removeBtn JButton to remove the worker
     */
    private void setElementGroupsPosition(JPanel listElement, JLabel profilePicture, JLabel personName, JButton editBtn, JButton removeBtn) {

        GroupLayout listElementLayout = new GroupLayout(listElement);
        listElement.setLayout(listElementLayout);
        listElementLayout.setHorizontalGroup(
                listElementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(listElementLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(profilePicture, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(personName)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                                .addComponent(editBtn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeBtn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        listElementLayout.setVerticalGroup(
                listElementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(listElementLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(listElementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(listElementLayout.createSequentialGroup()
                                                .addComponent(profilePicture, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, listElementLayout.createSequentialGroup()
                                                .addGroup(listElementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(listElementLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(removeBtn)
                                                                .addComponent(editBtn))
                                                        .addComponent(personName))
                                                .addGap(33, 33, 33))))
        );
    }
}
