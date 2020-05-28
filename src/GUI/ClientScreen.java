package GUI;

import Controller.ClientCtr;
import Controller.ClientCtrIF;
import Controller.DataAccessException;
import GUI.Components.ComponentsConfigure;
import Model.Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ClientScreen extends JPanel {

    private MainScreen mainScreen;
    private JPanel listContainer;
    private ClientCtrIF clientController;
    private Client currentClient;
    private int MAX_CLIENTS;
    private ArrayList<Client> clientsList;

    private GroupLayout listContainerLayout;
    private GroupLayout.ParallelGroup parallelGroup;
    private GroupLayout.SequentialGroup sequentialGroup;

    public ClientScreen(MainScreen mainScreen) throws DataAccessException {
        this.mainScreen = mainScreen;
        initComponents();
    }

    private void initComponents() throws DataAccessException {

        clientController = new ClientCtr();

        JPanel topBar = new JPanel();
        JLabel taskTitle = new JLabel();
        JButton addBtn = new JButton();
        JScrollPane scrollableListContainer = new JScrollPane();

        setBackground(new Color(71, 120, 197));
        setForeground(new Color(255, 255, 255));
        setMaximumSize(new Dimension(1000, 720));
        setMinimumSize(new Dimension(1000, 720));
        setName("");
        setPreferredSize(new Dimension(1000, 720));

        topBar.setBackground(new Color(120, 168, 252));
        topBar.setPreferredSize(new Dimension(1000, 110));

        taskTitle.setBackground(new Color(249, 249, 249));
        taskTitle.setFont(new Font("Dialog", Font.BOLD, 36));
        taskTitle.setForeground(new Color(249, 249, 249));
        taskTitle.setText("CSWorks clients");
        taskTitle.setPreferredSize(new Dimension(450, 50));

        GroupLayout topBarLayout = new GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
                topBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(topBarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(taskTitle, GroupLayout.PREFERRED_SIZE, 460, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(534, Short.MAX_VALUE))
        );
        topBarLayout.setVerticalGroup(
                topBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(topBarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(taskTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrollableListContainer.setBorder(null);
        scrollableListContainer.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        listContainer = createListContainer();


        ComponentsConfigure.metroBtnConfig(addBtn);
        addBtn.setText("Add");
        addBtn.addActionListener(evt -> addBtnActionPerformed());

        scrollableListContainer.setViewportView(listContainer);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(topBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(scrollableListContainer, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(addBtn, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(topBar, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollableListContainer, GroupLayout.PREFERRED_SIZE, 590, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>

    private void infoBtnActionPerformed() {
        // TODO add your handling code here:
    }

    private void msgBtnActionPerformed() {
        // TODO add your handling code here:
    }

    private void removeBtnActionPerformed() {
        // TODO add your handling code here:
    }

    private void settingsBtnActionPerformed() {
        // TODO add your handling code here:
    }

    private void addBtnActionPerformed() {
        // TODO add your handling code here:
    }

    private void configureButton(JButton button, ImageIcon icon){
        button.setBackground(new Color(23, 35, 51));
        button.setMaximumSize(new Dimension(70, 50));
        button.setMinimumSize(new Dimension(70, 50));
        button.setPreferredSize(new Dimension(70, 50));
        button.setIcon(icon);
    }

    public JPanel createListContainer() {
        JPanel listContainer = new JPanel();
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

    private void addElementToList(JPanel listElement){
        parallelGroup.addGroup(listContainerLayout.createSequentialGroup()
                .addComponent(listElement));
        sequentialGroup.addGroup(listContainerLayout.createParallelGroup()
                .addComponent(listElement));
    }

    private void loadClients() throws DataAccessException {
        clientsList = new ArrayList<>(clientController.findAllClients(false));
        MAX_CLIENTS = clientsList.size();
        listContainer.removeAll();
        createElements();
    }

    private void createElements(){
        if(MAX_CLIENTS > 0){
            for(int i = 0; i < MAX_CLIENTS; i++){
                JPanel listElement = new JPanel();
                JLabel profilePicture = new JLabel();
                String pName = clientsList.get(i).getName();
                JLabel personName = new JLabel();
                JButton infoBtn = new JButton();
                JButton msgBtn = new JButton();
                JButton removeBtn = new JButton();
                JButton settingsBtn = new JButton();

                setElementComponents(listElement,profilePicture,personName,pName,infoBtn,msgBtn,removeBtn,settingsBtn);
                setElementGroupsPosition(listElement,profilePicture,personName,infoBtn,msgBtn,removeBtn,settingsBtn);
                addElementToList(listElement);

            }
        }
    }

    private void setElementComponents(JPanel listElement, JLabel profilePicture, JLabel personName, String pName, JButton infoBtn, JButton msgBtn, JButton removeBtn, JButton settingsBtn){

        listElement.setBackground(new Color(23, 35, 51));
        listElement.setMaximumSize(new Dimension(970, 112));
        listElement.setMinimumSize(new Dimension(970, 112));
        listElement.setPreferredSize(new Dimension(970, 112));

        profilePicture.setIcon(new ImageIcon(getClass().getResource("/icons8_github_96px.png")));

        personName.setFont(new Font("Dialog", Font.BOLD, 24));
        personName.setForeground(Color.WHITE);
        personName.setText(pName);

        ComponentsConfigure.metroBtnConfig(infoBtn);
        configureButton(infoBtn,ComponentsConfigure.infoIcon);
        infoBtn.addActionListener(evt -> infoBtnActionPerformed());

        ComponentsConfigure.metroBtnConfig(msgBtn);
        configureButton(msgBtn,ComponentsConfigure.sentIcon);
        msgBtn.addActionListener(evt -> msgBtnActionPerformed());

        ComponentsConfigure.metroBtnConfig(removeBtn);
        configureButton(removeBtn,ComponentsConfigure.trashIcon);
        removeBtn.addActionListener(evt -> removeBtnActionPerformed());

        ComponentsConfigure.metroBtnConfig(settingsBtn);
        configureButton(settingsBtn,ComponentsConfigure.settingsIcon);
        settingsBtn.addActionListener(evt -> settingsBtnActionPerformed());
    }

    private void setElementGroupsPosition(JPanel listElement,JLabel profilePicture, JLabel personName, JButton infoBtn, JButton msgBtn, JButton removeBtn, JButton settingsBtn){
        GroupLayout listElementLayout = new GroupLayout(listElement);
        listElement.setLayout(listElementLayout);
        listElementLayout.setHorizontalGroup(
                listElementLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(listElementLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(profilePicture, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(personName)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 534, Short.MAX_VALUE)
                                .addComponent(msgBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(settingsBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(infoBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
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
                                                .addGroup(listElementLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(infoBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(msgBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(removeBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(settingsBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(personName, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(33, 33, 33))))
        );
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if(aFlag){
            try {
                loadClients();
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
