package GUI;

import Controller.ClientCtr;
import Controller.ClientCtrIF;
import DB.Exception.DataAccessException;
import GUI.Components.BackgroundWorker;
import GUI.Components.ComponentsConfigure;
import GUI.Components.StatusDialog;
import Model.Client;
import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Arrays;

public class CreateClient extends JFrame {

    private JLabel clientCVR;
    private JTextField clientCVRField;
    private JLabel clientEmail;
    private JTextField clientEmailField;
    private JPanel clientInformationContainer;
    private JLabel clientName;
    private JTextField clientNameField;
    private JLabel clientPhone;
    private JTextField clientPhoneField;
    private JLabel country;
    private JTextField countryField;
    private JLabel dateEnd;
    private DateTimePicker dateEndPicker;
    private JLabel dateStart;
    private DateTimePicker dateStartPicker;
    private JLabel exitBtn;
    private JLabel frameTitle;
    private JButton cancelBtn;
    private JButton createBtn;
    private JPanel mainContainer;
    private JLabel maximizeBtn;
    private JLabel minimizeBtn;
    private JLabel profilePicture;
    private JLabel streetName;
    private JTextField streetNameField;
    private JTextField streetNoField;
    private JPanel topBar;
    private JLabel zipCode;
    private JTextField zipCodeField;

    private ClientCtrIF clientController;
    private static ClientScreen clientScreen;

    public CreateClient(ClientScreen clientScreen) {
        CreateClient.clientScreen = clientScreen;
        clientController = new ClientCtr();
        initComponents();
    }

    public void start() {
        main(null);
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(() -> new CreateClient(clientScreen).setVisible(true));
    }

    private void configureLabel(JLabel... labels) {
        Arrays.stream(labels).forEach((l) -> {
            l.setFont(new Font("Dialog", Font.BOLD, 12));
            l.setForeground(new Color(255, 255, 255));
            l.setFocusable(false);
        });
    }

    private void configureField(JTextField field, String string) {
        field.setUI(new JTextFieldHintUI(string, Color.GRAY));
    }

    private void createClient() throws DataAccessException {
        Client toInsert = new Client();

        if (clientCVRField.getText().isEmpty()) {
            new StatusDialog(this, true, StatusDialog.WARNING, "No CVR inserted", "Please add the clients CVR, it cannot be blank.");
            return;
        }
        String CVR = clientCVRField.getText();
        toInsert.setCvr(CVR);

        if (clientNameField.getText().isEmpty()) {
            new StatusDialog(this, true, StatusDialog.WARNING, "No name inserted", "Please add the clients name, it cannot be blank.");
            return;
        }
        String name = clientNameField.getText();
        toInsert.setName(name);

        if (clientEmailField.getText().isEmpty()) {
            new StatusDialog(this, true, StatusDialog.WARNING, "No email inserted", "Please add the clients email, it cannot be blank.");
            return;
        }
        String email = clientEmailField.getText();
        toInsert.setEmail(email);

        if (clientPhoneField.getText().isEmpty()) {
            new StatusDialog(this, true, StatusDialog.WARNING, "No phone inserted", "Please add the clients phone number, it cannot be blank.");
            return;
        }
        String phone = clientPhoneField.getText();
        toInsert.setPhoneNum(phone);

        if (streetNameField.getText().isEmpty()) {
            new StatusDialog(this, true, StatusDialog.WARNING, "No street inserted", "Please add the clients address, it cannot be blank.");
            return;
        }
        String street = streetNameField.getText();
        toInsert.setStreetName(street);

        if (streetNoField.getText().isEmpty()) {
            new StatusDialog(this, true, StatusDialog.WARNING, "No street number inserted", "Please add the clients address number, it cannot be blank.");
            return;
        }
        String streetNum = streetNoField.getText();
        toInsert.setStreetNum(streetNum);

        if (zipCodeField.getText().isEmpty()) {
            new StatusDialog(this, true, StatusDialog.WARNING, "No postal code inserted", "Please add the clients postal code, it cannot be blank.");
            return;
        }
        String zip = zipCodeField.getText();
        toInsert.setZip(zip);

        if (countryField.getText().isEmpty()) {
            new StatusDialog(this, true, StatusDialog.WARNING, "No country inserted", "Please add the clients country, it cannot be blank.");
            return;
        }
        String country = countryField.getText();
        toInsert.setCountry(country);
        toInsert.setCountryCode("DK");

        if (dateStartPicker.getDateTimePermissive() == null) {
            new StatusDialog(this, true, StatusDialog.WARNING, "No start date inserted", "Please add the clients start date for the contract, it cannot be blank.");
            return;
        }
        LocalDateTime startDate = dateStartPicker.getDateTimePermissive();
        toInsert.setDateStart(java.sql.Date.valueOf(startDate.toLocalDate()));

        if (dateEndPicker.getDateTimePermissive() == null) {
            new StatusDialog(this, true, StatusDialog.WARNING, "No end date inserted", "Please add the clients end date for the contract, it cannot be blank.");
            return;
        }
        LocalDateTime endDate = dateEndPicker.getDateTimePermissive();
        toInsert.setDateEnd(java.sql.Date.valueOf(endDate.toLocalDate()));

        if (startDate.isAfter(endDate)) {
            new StatusDialog(this, true, StatusDialog.WARNING, "End date cannot be before start date",
                    "The contract end date cannot be before the contract start date, please check your information and try again.");
            return;
        }

        Client duplicateClient;
        try {
            duplicateClient = clientController.findClientByCVR(CVR, false, Client.class);
        }catch(DataAccessException e){
            new StatusDialog(this, true, StatusDialog.WARNING, "Unable to locate",
                    "The system cannot check if the client is already present in the database, cancelling the process.");
            return;
        }

        if(duplicateClient != null){
            new StatusDialog(this, true, StatusDialog.WARNING, "Duplicate client",
                    "There already exists a client with this CVR within the system please verify your information and try again.");
            return;
        }

        try {
            if (clientController.insertClient(toInsert)) {
                new StatusDialog(this, true, StatusDialog.CONFIRM, "Successfully created client",
                        "The client has been successfully created and added to the system.");
                new BackgroundWorker(() -> clientScreen.loadClients(), "Returning to client screen", "Updating clients list, please wait.");
                this.dispose();
            }
        } catch (DataAccessException e) {
            new StatusDialog(this, true, StatusDialog.WARNING, "Unable to insert",
                    "The system cannot create and insert the specified client into the database. Check your internet connection and try again.");
        }
    }

    private void initComponents() {

        mainContainer = new JPanel();
        topBar = new JPanel();
        frameTitle = new JLabel();
        minimizeBtn = new JLabel();
        maximizeBtn = new JLabel();
        exitBtn = new JLabel();
        clientInformationContainer = new JPanel();
        profilePicture = new JLabel();
        clientName = new JLabel();
        clientNameField = new JTextField();
        clientCVR = new JLabel();
        clientCVRField = new JTextField();
        clientEmail = new JLabel();
        clientEmailField = new JTextField();
        clientPhoneField = new JTextField();
        clientPhone = new JLabel();
        streetName = new JLabel();
        streetNameField = new JTextField();
        zipCodeField = new JTextField();
        zipCode = new JLabel();
        countryField = new JTextField();
        country = new JLabel();
        dateStart = new JLabel();
        dateStartPicker = new DateTimePicker();
        dateEnd = new JLabel();
        dateEndPicker = new DateTimePicker();
        streetNoField = new JTextField();
        cancelBtn = new JButton();
        createBtn = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new Dimension(470, 600));
        setMinimumSize(new Dimension(470, 600));
        setUndecorated(true);
        setPreferredSize(new Dimension(470, 600));
        setResizable(false);

        mainContainer.setBackground(new Color(23, 35, 51));
        mainContainer.setMaximumSize(new Dimension(460, 600));
        mainContainer.setMinimumSize(new Dimension(460, 600));
        mainContainer.setPreferredSize(new Dimension(460, 600));

        frameTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        frameTitle.setText("CS Works client creation");

        ComponentsConfigure.createTopBar(topBar, frameTitle, minimizeBtn, maximizeBtn, exitBtn, new Color(120, 168, 252), this);

        clientInformationContainer.setBackground(new Color(23, 35, 51));
        clientInformationContainer.setMaximumSize(new Dimension(450, 530));
        clientInformationContainer.setMinimumSize(new Dimension(450, 530));
        clientInformationContainer.setPreferredSize(new Dimension(450, 530));

        profilePicture.setIcon(ComponentsConfigure.defaultProfile);
        clientCVR.setIcon(ComponentsConfigure.idIcon);
        clientEmail.setIcon(ComponentsConfigure.emailIcon);
        clientPhone.setIcon(ComponentsConfigure.phoneIcon);
        streetName.setIcon(ComponentsConfigure.locationIcon);
        zipCode.setIcon(ComponentsConfigure.locationIcon);
        country.setIcon(ComponentsConfigure.locationIcon);
        dateStart.setIcon(ComponentsConfigure.watchIcon);
        dateEnd.setIcon(ComponentsConfigure.presentIcon);

        configureLabel(clientName, clientCVR, clientEmail, clientPhone, streetName, zipCode, country, dateStart, dateEnd);

        clientName.setText("Client name");
        clientCVR.setText("CVR");
        clientEmail.setText("Email");
        clientPhone.setText("Phone");
        streetName.setText("Street name");
        zipCode.setText("Postal code");
        country.setText("Country");
        dateStart.setText("Date start");
        dateEnd.setText("Date end");

        configureField(clientNameField, "Name of the client");
        configureField(clientCVRField, "CVR of the client");
        configureField(clientEmailField, "Email of the client");
        configureField(clientPhoneField, "Phone number of the client");
        configureField(streetNameField, "Address of the client");
        configureField(streetNoField, "No.");
        configureField(zipCodeField, "Postal code");
        configureField(countryField, "Country of the client");

        ComponentsConfigure.metroBtnConfig(createBtn);
        createBtn.setIcon(ComponentsConfigure.addIcon);
        createBtn.setText("Create");
        createBtn.addActionListener(e -> {
            try {
                createClient();
            } catch (DataAccessException dataAccessException) {
                dataAccessException.printStackTrace();
            }
        });

        ComponentsConfigure.metroBtnConfig(cancelBtn);
        cancelBtn.setIcon(ComponentsConfigure.trashIcon);
        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener((e) -> this.dispose());

        GroupLayout clientInformationContainerLayout = new GroupLayout(clientInformationContainer);
        clientInformationContainer.setLayout(clientInformationContainerLayout);
        clientInformationContainerLayout.setHorizontalGroup(
                clientInformationContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(clientInformationContainerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(clientInformationContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(clientInformationContainerLayout.createSequentialGroup()
                                                .addComponent(profilePicture, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(clientInformationContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(clientInformationContainerLayout.createSequentialGroup()
                                                                .addComponent(clientCVR, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(clientCVRField, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
                                                        .addGroup(clientInformationContainerLayout.createSequentialGroup()
                                                                .addComponent(clientName, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(clientNameField))))
                                        .addGroup(clientInformationContainerLayout.createSequentialGroup()
                                                .addComponent(clientEmail, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                .addGap(38, 38, 38)
                                                .addComponent(clientEmailField, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
                                        .addGroup(clientInformationContainerLayout.createSequentialGroup()
                                                .addComponent(clientPhone, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                .addGap(38, 38, 38)
                                                .addComponent(clientPhoneField, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
                                        .addGroup(clientInformationContainerLayout.createSequentialGroup()
                                                .addComponent(streetName, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(streetNameField)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(streetNoField, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(clientInformationContainerLayout.createSequentialGroup()
                                                .addComponent(zipCode, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(zipCodeField, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
                                        .addGroup(clientInformationContainerLayout.createSequentialGroup()
                                                .addGroup(clientInformationContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(country, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dateStart, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                                        .addComponent(dateEnd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(clientInformationContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(countryField, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                                                        .addComponent(dateStartPicker, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(dateEndPicker, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(GroupLayout.Alignment.TRAILING, clientInformationContainerLayout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(createBtn, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        clientInformationContainerLayout.setVerticalGroup(
                clientInformationContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(clientInformationContainerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(clientInformationContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(profilePicture, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(clientInformationContainerLayout.createSequentialGroup()
                                                .addGroup(clientInformationContainerLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(clientNameField, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                                        .addComponent(clientName, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(clientInformationContainerLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(clientCVRField, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                                        .addComponent(clientCVR, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(clientInformationContainerLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(clientEmailField, GroupLayout.Alignment.LEADING)
                                        .addComponent(clientEmail, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(clientInformationContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(clientPhoneField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(clientPhone, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(clientInformationContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(streetNameField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(streetName, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(streetNoField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(clientInformationContainerLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(zipCodeField, GroupLayout.Alignment.LEADING)
                                        .addComponent(zipCode, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(clientInformationContainerLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(countryField, GroupLayout.Alignment.LEADING)
                                        .addComponent(country, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(clientInformationContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(dateStart, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                        .addComponent(dateStartPicker, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(clientInformationContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(dateEnd, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                        .addComponent(dateEndPicker, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(clientInformationContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(cancelBtn, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                        .addComponent(createBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        GroupLayout mainContainerLayout = new GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
                mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(topBar, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                        .addGroup(mainContainerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(clientInformationContainer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainContainerLayout.setVerticalGroup(
                mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainContainerLayout.createSequentialGroup()
                                .addComponent(topBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(clientInformationContainer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainContainer, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainContainer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setIconImage(ComponentsConfigure.plantIcon.getImage());
        requestFocus();
        pack();
        setLocationRelativeTo(null);
    }
}
