package GUI;

import DB.DataAccessException;
import Controller.EmployeeCtr;
import Controller.EmployeeCtrIF;
import GUI.Components.ComponentsConfigure;
import GUI.Components.StatusDialog;
import Model.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * A view extending JPanel. Allows for management of employees registered in Herbal Time database.
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
public class EmployeeDatabaseScreen extends JFrame {
    private static int MAX_EMPLOYEES;
    private JPanel listContainer;
    private ViewEmployee viewEmployee;

    private GroupLayout listContainerLayout;
    private GroupLayout.ParallelGroup parallelGroup;
    private GroupLayout.SequentialGroup sequentialGroup;

    private EmployeeCtrIF employeeCtr;
    private ArrayList<Employee> employees;


    /**
     * Constructor of the employee database frame
     */
    public EmployeeDatabaseScreen() {
        try {
            initComponents();
        } catch (DataAccessException e) {
            System.err.println("Issue obtaining connection.");
//            e.printStackTrace();
            new GUI.Components.StatusDialog(this,true, StatusDialog.WARNING,"Internet connection is required.",
                    "The system is unable to connect to the internet. " +
                            "We are sorry for the inconvenience please try again later.");
            // TODO need to discuss loading and also refresh button
            // probably should also return or something
        }
    }

    /**
     * Main method used to run the class directly, set up for catching errors
     * @param args Java arguments
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
            java.util.logging.Logger.getLogger(EmployeeDatabaseScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> {
            new EmployeeDatabaseScreen().setVisible(true);
        });
    }


    public void start(){
        main(null);
    }

    /**
     * Initialize all components and layouts part of the frame.
     * @throws DataAccessException Thrown if no connection to db is found.
     */
    private void initComponents() throws DataAccessException {

        employeeCtr = new EmployeeCtr();
        employees = new ArrayList<>();
        try {
            employees = new ArrayList<>(employeeCtr.findAllEmployees());
        }catch (DataAccessException e) {
            throw new DataAccessException("Unable to retrieve list of seasonal workers.", e);
        }
        MAX_EMPLOYEES = employees.size();

        JPanel mainContainer = new JPanel();
        JPanel topBar = new JPanel();
        JLabel maximizeBtn = new JLabel();
        JLabel exitBtn = new JLabel();
        JLabel minimizeBtn = new JLabel();
        JLabel frameTitle = new JLabel();
        JScrollPane scrollableListContainer = new JScrollPane();
        listContainer = new JPanel();
        JLabel screenTitle = new JLabel();
        JTextField searchField = new JTextField();
        JButton searchBtn = new JButton();

        viewEmployee = new ViewEmployee();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 720));
        setUndecorated(true);
        setResizable(false);

        mainContainer.setBackground(new Color(71, 120, 197));

        ComponentsConfigure.createTopBar(topBar,frameTitle,minimizeBtn,maximizeBtn,exitBtn,new Color(120,168,252),this);

        frameTitle.setFont(ComponentsConfigure.defaultFont); // NOI18N
        frameTitle.setText("CS Works");

        scrollableListContainer.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        listContainer = createListContainer();
        listContainer.setBackground(new Color(71, 120, 197));

        scrollableListContainer.setViewportView(listContainer);

        screenTitle.setFont(ComponentsConfigure.defaultFont); // NOI18N
        screenTitle.setText("Employee Database");

        searchField.setBackground(new Color(123, 156, 225));
        searchField.setForeground(new Color(255, 255, 255));
        searchField.setBorder(null);
        searchField.addActionListener((e) -> {});
        searchField.setUI(new JTextFieldHintUI("Search by name...",Color.GRAY));

        searchBtn.setText("Search");
        searchBtn.setIcon(new ImageIcon(getClass().getResource("/icons8_search_32px.png")));
        ComponentsConfigure.metroBtnConfig(searchBtn);

        GroupLayout mainContainerLayout = new GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
            mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(topBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(mainContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollableListContainer, GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
                    .addGroup(mainContainerLayout.createSequentialGroup()
                        .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(screenTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchField))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchBtn, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        mainContainerLayout.setVerticalGroup(
            mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mainContainerLayout.createSequentialGroup()
                .addComponent(topBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(screenTitle)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(searchBtn, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(searchField))
                .addGap(18, 18, 18)
                .addComponent(scrollableListContainer, GroupLayout.PREFERRED_SIZE, 556, GroupLayout.PREFERRED_SIZE)
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

        setIconImage(new ImageIcon(getClass().getResource("/icons8_potted_plant_50px_1.png")).getImage());
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Configure buttons of the frame to fit the metro style
     * @param button JButton to be configured
     * @param icon ImageIcon to set for the button
     */
    private void configureButton(JButton button, ImageIcon icon){
        button.setBackground(new Color(23, 35, 51));
        button.setMaximumSize(new Dimension(70, 50));
        button.setMinimumSize(new Dimension(70, 50));
        button.setPreferredSize(new Dimension(70, 50));
        button.setIcon(icon);
    }

    /**
     * Configures the given elements to fit the metro style and appear as an advanced JListItem
     * @param listElement JPanel to be used as the container
     * @param profilePicture JLabel to display the client profile picture
     * @param personName JLabel to display the client name
     * @param settingsBtn
     * @param generatedBtn
     * @param removeBtn
     * @param msgBtn
     * @param infoBtn
     * @param mailBtn
     * @param generateBtn
     */
    private void setElementGroupsPosition(JPanel listElement,
                                          JLabel profilePicture,
                                          JLabel personName,
                                          JButton settingsBtn,
                                          JButton generatedBtn,
                                          JButton removeBtn,
                                          JButton msgBtn,
                                          JButton infoBtn,
                                          JButton mailBtn,
                                          JButton generateBtn){
        GroupLayout listElementLayout = new GroupLayout(listElement);
        listElement.setLayout(listElementLayout);
        listElementLayout.setHorizontalGroup(
                listElementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(listElementLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(profilePicture, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(personName)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                                .addComponent(settingsBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(generatedBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(msgBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(infoBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mailBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(generateBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        listElementLayout.setVerticalGroup(
                listElementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(listElementLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(listElementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(listElementLayout.createSequentialGroup()
                                                .addComponent(profilePicture, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(12, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, listElementLayout.createSequentialGroup()
                                                .addGroup(listElementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(infoBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(personName)
                                                        .addComponent(mailBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(generateBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(msgBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(removeBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(generatedBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(settingsBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(33, 33, 33))))
        );
    }

    private void setElementComponents(JPanel listElement,
                                      JLabel profilePicture,
                                      JLabel personName,
                                      String name,
                                      String cpr,
                                      JButton settingsBtn,
                                      JButton generatedBtn,
                                      JButton removeBtn,
                                      JButton msgBtn,
                                      JButton infoBtn,
                                      JButton mailBtn,
                                      JButton generateBtn) {
        listElement.setBackground(new Color(23, 35, 51));
        listElement.setMaximumSize(new Dimension(975, 112));
        listElement.setPreferredSize(new Dimension(975, 112));
        listElement.setMinimumSize(new Dimension(975, 112));

        profilePicture.setIcon(new ImageIcon(getClass().getResource("/icons8_github_96px.png"))); // NOI18N

        personName.setFont(ComponentsConfigure.defaultFont);
        personName.setForeground(Color.WHITE);
        personName.setText(name);

        configureButton(settingsBtn,new ImageIcon(getClass().getResource("/icons8_settings_32px_1.png")));
        ComponentsConfigure.metroBtnConfig(settingsBtn);
        settingsBtn.addActionListener(this::settingsBtnActionPerformed);

        configureButton(generatedBtn,new ImageIcon(getClass().getResource("/icons8_add_file_32px.png")));
        ComponentsConfigure.metroBtnConfig(generatedBtn);
        generatedBtn.addActionListener(this::generatedBtnActionPerformed);

        configureButton(infoBtn,new ImageIcon(getClass().getResource("/icons8_information_32px.png")));
        ComponentsConfigure.metroBtnConfig(infoBtn);
        infoBtn.addActionListener(evt -> infoBtnActionPerformed(cpr));

        configureButton(mailBtn,new ImageIcon(getClass().getResource("/icons8_secured_letter_32px_3.png")));
        ComponentsConfigure.metroBtnConfig(mailBtn);
        mailBtn.addActionListener(this::mailBtnActionPerformed);

        configureButton(generateBtn,new ImageIcon(getClass().getResource("/icons8_add_file_32px.png")));
        ComponentsConfigure.metroBtnConfig(generateBtn);
        generateBtn.addActionListener(this::generateBtnActionPerformed);

        configureButton(msgBtn,new ImageIcon(getClass().getResource("/icons8_sent_32px.png")));
        ComponentsConfigure.metroBtnConfig(msgBtn);
        msgBtn.addActionListener(this::msgBtnActionPerformed);

        configureButton(removeBtn,new ImageIcon(getClass().getResource("/icons8_trash_can_32px.png")));
        ComponentsConfigure.metroBtnConfig(removeBtn);
        removeBtn.addActionListener(evt -> removeActionPerformed(cpr));
    }

    private void removeActionPerformed(String cpr) {
        try {
            employeeCtr.deleteEmployee(cpr);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    private void msgBtnActionPerformed(ActionEvent evt) {
        //  TODO
    }

    private void generateBtnActionPerformed(ActionEvent evt) {
        //  TODO
    }

    private void mailBtnActionPerformed(ActionEvent evt) {
        //  TODO
    }

    private void settingsBtnActionPerformed(ActionEvent evt) {
        //  TODO
    }

    private void generatedBtnActionPerformed(ActionEvent evt) {
        //  TODO
    }

    /**
     * ActionListener method for the information button
     * used to display a view for employee details
     *
     * @param cpr the cpr number of theemployee
     */
    private void infoBtnActionPerformed(String cpr) {
        try {
            new ViewEmployee(employeeCtr.findEmployeeByCPR(cpr)).start();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

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

        for (int i = 0; i < MAX_EMPLOYEES; i++) {
            JPanel listElement = new JPanel();
            JLabel profilePicture = new JLabel();
            JLabel personName = new JLabel();
            String name = employees.get(i).getFname() + " " + employees.get(i).getLname();
            String cpr = employees.get(i).getCpr();
            JButton settingsBtn = new JButton();
            JButton generatedBtn = new JButton();
            JButton removeBtn = new JButton();
            JButton msgBtn = new JButton();
            JButton infoBtn = new JButton();
            JButton mailBtn = new JButton();
            JButton generateBtn = new JButton();

            setElementComponents(listElement, profilePicture,personName, name,cpr,settingsBtn,generatedBtn,removeBtn,msgBtn,infoBtn,mailBtn,generateBtn);
            setElementGroupsPosition(listElement, profilePicture,personName,settingsBtn,generatedBtn,removeBtn,msgBtn,infoBtn,mailBtn,generateBtn);
            addElementToList(listElement);
        }
        return listContainer;
    }

    private void addElementToList(JPanel listElement) {
        parallelGroup.addGroup(listContainerLayout.createSequentialGroup()
                .addComponent(listElement));
        sequentialGroup.addGroup(listContainerLayout.createParallelGroup()
                .addComponent(listElement));
    }
}
