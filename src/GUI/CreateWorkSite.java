package GUI;

import Controller.*;
import DB.ProduceDB;
import DB.ProduceDBIF;
import DB.WorkSiteProduceDB;
import DB.WorkSiteProduceDBIF;
import Model.Client;
import Model.WorkSite;
import Model.WorkSiteProduce;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class CreateWorkSite extends JPanel {

    private WorkSiteCtrIF workSiteCtr;
    private MainScreen mainScreen;

    /**
     * Creates new form workSiteDashboard
     */
    public CreateWorkSite(MainScreen mainScreen) {
        try {
            this.mainScreen = mainScreen;
            initComponents();
        } catch (DataAccessException e) {
            System.err.println("Issue obtaining connection.");
//            e.printStackTrace();
            // Alert the user here with e.g JDialog saying there was an issue connecting to the database.
            // TODO
            // Add a refresh button.
        }
    }

    private void initComponents() throws DataAccessException {
        ClientCtrIF clientCtr;
        try {
            clientCtr = new ClientCtr();
        } catch (DataAccessException e) {
            throw new DataAccessException("Unable to obtain client controller instance.",e);
        }

        try {
            workSiteCtr = new WorkSiteCtr();
        } catch (DataAccessException e) {
            throw new DataAccessException("Unable to obtain work site controller instance.",e);
        }

        clients = new ArrayList<>();
        try {
            clients = new ArrayList<>(clientCtr.findAllClients(false)); // todo - set fullAssociation to
                                                                                    // todo - FALSE temporarily
        } catch (DataAccessException e) {
            throw new DataAccessException("Unable to retrieve list of clients.", e);
        }


        clientComboBox = new JComboBox<>();
        DefaultComboBoxModel<Client> comboBoxModel = getComboBoxModel(clients);
        clientComboBox.setModel(comboBoxModel);
        clientComboBox.setRenderer(new WorkSiteClientComboBoxRenderer());


        topBar = new JPanel();
        taskTitle = new JLabel();
        mainContainer = new JPanel();
        clientLabel = new JLabel();
        nameLabel = new JLabel();
        nameField = new JTextField();
        locationInfoTitle = new JLabel();
        streetNoField = new JTextField();
        streetLabel = new JLabel();
        streetNameField = new JTextField();
        postalCodeLabel = new JLabel();
        postalCodeField = new JTextField();
        typeOfJobLabel = new JLabel();
        typeOfJobField = new JTextField();
        collectedProduceBtn = new JButton();
        addWorkersBtn = new JButton();
        scrollableListContainer = new JScrollPane();
        workerList = new JList<>();
        cancelBtn = new JButton();
        createBtn = new JButton();
        pricePerWorkerLabel = new JLabel();
        pricePerWorkerField = new JTextField();
        descriptionField = new JTextField();
        descriptionLabel = new JLabel();

        setMinimumSize(new Dimension(1000, 720));
        setName(""); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        add(topBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        mainContainer.setBackground(new Color(71, 120, 197));

        configureField(nameField,"Name of the location");
        configureField(streetNameField,"Street name");
        configureField(streetNoField,"No.");
        configureField(postalCodeField,"Postal code");
        configureField(typeOfJobField, "Type of job");
        configureField(pricePerWorkerField,"Price per worker");
        configureField(descriptionField,"Description");

        nameField.addActionListener(this::nameFieldActionPerformed);
        streetNameField.addActionListener(this::streetNameFieldActionPerformed);
        streetNoField.addActionListener(this::streetNoFieldActionPerformed);
        postalCodeField.addActionListener(this::postalCodeFieldActionPerformed);
        typeOfJobField.addActionListener(this::typeOfJobFieldActionPerformed);
        pricePerWorkerField.addActionListener(this::pricePerWorkerFieldActionPerformed);
        descriptionField.addActionListener(this::descriptionFieldActionPerformed);

        configureLabel(clientLabel,"Client");
        configureLabel(nameLabel,"Name");
        configureLabel(streetLabel,"Street");
        configureLabel(postalCodeLabel,"Postal code");
        configureLabel(typeOfJobLabel,"Type of job");
        configureLabel(pricePerWorkerLabel,"Price p. worker");
        configureLabel(descriptionLabel,"Description");

        locationInfoTitle.setText("Location information:");

        //TODO: Worker selection

        // final String[] strings = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
        workerList.setModel(new AbstractListModel<>() {
            final String[] strings = {" ", " ", " ", " ", " "};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        scrollableListContainer.setViewportView(workerList);

        ComponentsConfigure.metroBtnConfig(collectedProduceBtn);
        ComponentsConfigure.metroBtnConfig(addWorkersBtn);
        ComponentsConfigure.metroBtnConfig(cancelBtn);
        ComponentsConfigure.metroBtnConfig(createBtn);

        collectedProduceBtn.setText("Collected Produce");
        collectedProduceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collectedProduceBtnActionPerformed(evt);
            }
        });
        addWorkersBtn.setText("Add/Edit workers");
        createBtn.setText("Create");
        cancelBtn.setText("Cancel");

        addWorkersBtn.addActionListener(this::addWorkersBtnActionPerformed);
        createBtn.addActionListener(this::createBtnActionPerformed);
        cancelBtn.addActionListener((e)-> mainScreen.returnNav());

        GroupLayout mainContainerLayout = new GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
            mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mainContainerLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addGroup(GroupLayout.Alignment.LEADING, mainContainerLayout.createSequentialGroup()
                            .addComponent(streetLabel)
                            .addGap(97, 97, 97)
                            .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(postalCodeField, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
                                .addGroup(mainContainerLayout.createSequentialGroup()
                                    .addComponent(streetNameField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(streetNoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                        .addGroup(mainContainerLayout.createSequentialGroup()
                            .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameLabel)
                                .addComponent(clientLabel))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(nameField, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                .addComponent(clientComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(postalCodeLabel)
                    .addGroup(mainContainerLayout.createSequentialGroup()
                        .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(typeOfJobLabel)
                            .addComponent(pricePerWorkerLabel)
                            .addComponent(descriptionLabel))
                        .addGap(18, 18, 18)
                        .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(collectedProduceBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(typeOfJobField, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(pricePerWorkerField, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(descriptionField, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)))
                    .addGroup(mainContainerLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(locationInfoTitle)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(GroupLayout.Alignment.TRAILING, mainContainerLayout.createSequentialGroup()
                        .addComponent(createBtn, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
                    .addComponent(addWorkersBtn, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                    .addComponent(scrollableListContainer, GroupLayout.Alignment.TRAILING))
                .addGap(49, 49, 49))
        );
        mainContainerLayout.setVerticalGroup(
            mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mainContainerLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(clientLabel)
                    .addComponent(addWorkersBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addComponent(clientComboBox, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(mainContainerLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(nameLabel)
                            .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(locationInfoTitle)
                        .addGap(20, 20, 20)
                        .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(streetLabel)
                            .addComponent(streetNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(streetNoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(postalCodeLabel)
                            .addComponent(postalCodeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(typeOfJobLabel)
                            .addComponent(typeOfJobField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(pricePerWorkerLabel)
                            .addComponent(pricePerWorkerField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(descriptionLabel)
                            .addComponent(descriptionField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(mainContainerLayout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollableListContainer, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(collectedProduceBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addComponent(createBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        add(mainContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1000, 610));
    }

    private DefaultComboBoxModel<Client> getComboBoxModel(List<Client> clients) {
        Client[] comboBoxModel = clients.toArray(new Client[0]);
        return new DefaultComboBoxModel<>(comboBoxModel);
    }

    private void pricePerWorkerFieldActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void addWorkersBtnActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void typeOfJobFieldActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }
    private void postalCodeFieldActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void streetNameFieldActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void streetNoFieldActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void nameFieldActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void collectedProduceBtnActionPerformed(java.awt.event.ActionEvent evt) {
        collectedProduce = s -> collectedProduceStringList = s;
                new ProduceCheckList(collectedProduce).setVisible(true);
    }

    private void createBtnActionPerformed(ActionEvent evt) {
        Client selectClient = (Client) clientComboBox.getSelectedItem();
        String clientCVR = selectClient.getCvr();
        String name = nameField.getText();
        String description = descriptionField.getText();
        String streetName = streetNameField.getText();
        String streetNum = streetNoField.getText();
        String zip = postalCodeField.getText();
        String typeOfJob = typeOfJobField.getText();
        double pricePerWorker = Double.parseDouble(pricePerWorkerField.getText());
        WorkSite newWorkSite = new WorkSite(name, description, streetName, streetNum, zip, "Denmark",
                "DK", typeOfJob, pricePerWorker);

        WorkSite lookingForDuplicate = null;
        try {
            lookingForDuplicate = workSiteCtr.findByName(name, false);
        } catch (DataAccessException e) {
            new StatusDialog(mainScreen,false, StatusDialog.WARNING,"Error","" +
                    "Issue looking for duplicates. Adding worksite terminated.");
            return;
        }


        if (lookingForDuplicate != null) {
            new StatusDialog(mainScreen, false, StatusDialog.WARNING, "Error adding a new work site",
                    "An error occurred while trying to add work site to database. Work site was not added " +
                            "to database. Make sure the work site you're trying to add does not exist already in " +
                            "database.");
            return;
        }

        Boolean result = false;
        try {
            result = workSiteCtr.insertWorkSite(clientCVR, newWorkSite);
        } catch (java.lang.NumberFormatException e) {
            new StatusDialog(mainScreen,false, StatusDialog.WARNING,"Error","" +
                    "Fields were filled incorrectly. Check provided fields and try again.");
            return;
        } catch (DataAccessException e ) {
            new StatusDialog(mainScreen,false, StatusDialog.WARNING,"Error","There was an error " +
                    "adding worksite to the database. Check your connection and try again.");
            return;
        }

        if (result) {
            // WorkSite was successfully added to database
            new StatusDialog(mainScreen, false, StatusDialog.CONFIRM, "SUCCESS", "Successfully " +
                    "added new work site.");
        } else {
            // WorkSite was NOT successfully added to database
            new StatusDialog(mainScreen, false, StatusDialog.WARNING, "Error adding a new work site",
                    "An error occurred while trying to add work site to database. Work site was not added " +
                            "to database.");
            return;
        }

        // Now we can find the newly created workSite by it's name,
        // as it is an unique value in the database.
        WorkSite createdWorkSite;
        try {
            createdWorkSite = workSiteCtr.findByName(name, false);
        } catch (DataAccessException e) {
            new StatusDialog(mainScreen,false, StatusDialog.WARNING,"Error","There was an issue " +
                    "fetching the newly created website to associate with the collected produce. Couldn't associate " +
                    "collected produce with the worksite");
            return;
        }

        WorkSiteProduceDBIF wspDB;
        try {
            wspDB = new WorkSiteProduceDB();
            Integer workSiteID = createdWorkSite.getWorkSiteID();
            // TODO Maybe could make a transaction for that, so that either all WorkSiteProduce object is added,
            //  or none
            for (String s: collectedProduceStringList) {
                WorkSiteProduce wsp = new WorkSiteProduce(workSiteID, s);
                try {
                    wspDB.insertWorkSiteProduce(workSiteID, s, wsp, WorkSiteProduce.class);
                } catch (DataAccessException e) {
                    new StatusDialog(mainScreen,false, StatusDialog.WARNING,"Error","Issue " +
                            "association single produce " + s + " with worksite of ID: " + workSiteID);
                }
            }
        } catch (DataAccessException e) {
            new StatusDialog(mainScreen,false, StatusDialog.WARNING,"Error","Couldn't associate " +
                    "collected produce with the worksite");
        }
    }

    private void descriptionFieldActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void configureField(JTextField field,String hintText){
        field.setUI(new JTextFieldHintUI(hintText,Color.GRAY));
        field.setMinimumSize(new Dimension(65, 26));
        field.setPreferredSize(new Dimension(65, 26));
    }

    private void configureLabel(JLabel label, String labelText){
        label.setText(labelText);
        label.setFont(new Font("Dialog", Font.BOLD, 18));
        label.setForeground(new Color(249, 249, 249));
        label.setIcon(ComponentsConfigure.locationIcon);
        label.setFocusable(false);
    }
    private JButton addWorkersBtn;
    private JButton cancelBtn;
    private JComboBox<Client> clientComboBox;
    private JLabel clientLabel;
    private JButton collectedProduceBtn;
    private JButton createBtn;
    private JTextField descriptionField;
    private JLabel descriptionLabel;
    private JLabel locationInfoTitle;
    private JPanel mainContainer;
    private JTextField nameField;
    private JLabel nameLabel;
    private JTextField postalCodeField;
    private JLabel postalCodeLabel;
    private JTextField pricePerWorkerField;
    private JLabel pricePerWorkerLabel;
    private JScrollPane scrollableListContainer;
    private JLabel streetLabel;
    private JTextField streetNameField;
    private JTextField streetNoField;
    private JLabel taskTitle;
    private JPanel topBar;
    private JTextField typeOfJobField;
    private JLabel typeOfJobLabel;
    private JList<String> workerList;

    private ArrayList<Client> clients;

    private Consumer<ArrayList<String>> collectedProduce;
    private ArrayList<String> collectedProduceStringList;
}
