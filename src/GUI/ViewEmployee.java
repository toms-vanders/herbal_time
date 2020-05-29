package GUI;

import GUI.Components.ComponentsConfigure;
import Model.Employee;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Arrays;

/**
 * A view extending JFrame. Used for displaying details about a specific employee.
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
public class ViewEmployee extends JFrame {

    // TODO a lot of unused components
    private JLabel addressLabel;
    private JLabel addressValue;
    private JLabel countryLabel;
    private JLabel countryValue;
    private JLabel cprLabel;
    private JLabel cprValue;
    private JLabel dobLabel;
    private JLabel dobValue;
    private JLabel emailLabel;
    private JLabel emailValue;
    private JLabel exitBtn;
    private JLabel fnameLabel;
    private JLabel fnameValue;
    private JLabel frameTitle;
    private JLabel lnameLabel;
    private JLabel lnameValue;
    private JPanel mainContainer;
    private JLabel maximizeBtn;
    private JLabel minimizeBtn;
    private JList<String> paymentslipsList;
    private JLabel phoneLabel;
    private JLabel phoneValue;
    private JLabel postalCodeLabel;
    private JLabel postalCodeValue;
    private JLabel profilePicture;
    private JScrollPane scrollabelPaymentSlips;
    private JScrollPane scrollableSeasonalWorkers;
    private JList<String> seasonalWorkersList;
    private JLabel sexLabel;
    private JLabel sexValue;
    private JPanel topBar;

    private Employee employee;

    /**
     * Creates new form ViewEmployee
     * @param employee specific employee
     */
    public ViewEmployee(Employee employee) {
        this.employee = employee;
        initComponents();
    }

    public ViewEmployee() {
    }

    public void start() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> new ViewEmployee(employee).setVisible(true));
    }

    /**
     * Initialize all components and layouts part of the panel.
     */
    private void initComponents() {

        mainContainer = new JPanel();
        topBar = new JPanel();
        maximizeBtn = new JLabel();
        exitBtn = new JLabel();
        minimizeBtn = new JLabel();
        frameTitle = new JLabel();
        profilePicture = new JLabel();
        fnameLabel = new JLabel();
        fnameValue = new JLabel();
        lnameLabel = new JLabel();
        lnameValue = new JLabel();
        emailLabel = new JLabel();
        emailValue = new JLabel();
        dobLabel = new JLabel();
        dobValue = new JLabel();
        sexLabel = new JLabel();
        sexValue = new JLabel();
        postalCodeLabel = new JLabel();
        postalCodeValue = new JLabel();
        phoneLabel = new JLabel();
        phoneValue = new JLabel();
        addressLabel = new JLabel();
        addressValue = new JLabel();
        countryLabel = new JLabel();
        countryValue = new JLabel();
        cprLabel = new JLabel();
        cprValue = new JLabel();
        scrollableSeasonalWorkers = new JScrollPane();
        seasonalWorkersList = new JList<>();
        scrollabelPaymentSlips = new JScrollPane();
        paymentslipsList = new JList<>();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("View Employee");
        setUndecorated(true);
        setPreferredSize(new Dimension(800, 600));

        mainContainer.setBackground(new Color(23, 35, 51));

        frameTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        frameTitle.setText("CS Works Employee view");

        ComponentsConfigure.createTopBar(topBar,frameTitle,minimizeBtn,maximizeBtn,exitBtn,new Color(120,168,252),this);

        profilePicture.setIcon(ComponentsConfigure.defaultProfile);

        configureLabel(fnameLabel,
                fnameValue,
                lnameLabel,
                lnameValue,
                emailLabel,
                emailValue,
                dobLabel,
                dobValue,
                sexLabel,
                sexValue,
                postalCodeLabel,
                postalCodeValue,
                phoneLabel,
                phoneValue,
                addressLabel,
                addressValue,
                countryLabel,
                countryValue,
                cprLabel,
                cprValue);

        fnameLabel.setText("Firstname:");
        fnameValue.setText(employee.getFname());

        lnameLabel.setText("Lastname:");
        lnameValue.setText(employee.getLname());

        emailLabel.setText("Email:");
        emailValue.setText(employee.getEmail());

        dobLabel.setText("Date of birth:");
        dobValue.setText(String.valueOf(employee.getDob()));

        sexLabel.setText("Sex:");
        sexValue.setText(String.valueOf(employee.getSex()));

        postalCodeLabel.setText("Postal code:");
        postalCodeValue.setText(String.valueOf(employee.getZip()));

        phoneLabel.setText("Phone:");
        phoneValue.setText(employee.getPhoneNum());

        addressLabel.setText("Address: ");
        addressValue.setText(employee.getStreetName() + " " + employee.getStreetNum());

        countryLabel.setText("Country:");
        countryValue.setText(employee.getCountry());

        cprLabel.setText("CPR:");
        cprValue.setText(String.valueOf(employee.getCpr()));

        seasonalWorkersList.setBackground(new Color(23, 35, 51));
        seasonalWorkersList.setBorder(BorderFactory.createTitledBorder(null, "Seasonal Workers", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(255, 255, 255)));
        seasonalWorkersList.setForeground(new Color(255, 255, 255));
        seasonalWorkersList.setModel(new AbstractListModel<String>() {
            // TODO
            final String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        scrollableSeasonalWorkers.setViewportView(seasonalWorkersList);

        paymentslipsList.setBackground(new Color(23, 35, 51));
        paymentslipsList.setBorder(BorderFactory.createTitledBorder(null, "Paymentslips", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(255, 255, 255)));
        paymentslipsList.setForeground(new Color(255, 255, 255));
        paymentslipsList.setModel(new AbstractListModel<String>() {
            // TODO
            final String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        scrollabelPaymentSlips.setViewportView(paymentslipsList);

        GroupLayout mainContainerLayout = new GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
                mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(topBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(mainContainerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addComponent(profilePicture, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(fnameLabel)
                                                        .addComponent(lnameLabel))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(fnameValue)
                                                        .addComponent(lnameValue)))
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addComponent(emailLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(emailValue))
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addComponent(dobLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(dobValue))
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addComponent(sexLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(sexValue))
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addComponent(phoneLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(phoneValue))
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addComponent(addressLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(addressValue))
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addComponent(postalCodeLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(postalCodeValue))
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addComponent(countryLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(countryValue))
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addComponent(cprLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cprValue)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(scrollabelPaymentSlips, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scrollableSeasonalWorkers, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        mainContainerLayout.setVerticalGroup(
                mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainContainerLayout.createSequentialGroup()
                                .addComponent(topBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(profilePicture, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(fnameLabel)
                                                                        .addComponent(fnameValue))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(lnameLabel)
                                                                        .addComponent(lnameValue))))
                                                .addGap(18, 18, 18)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(emailLabel)
                                                        .addComponent(emailValue))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(dobLabel)
                                                        .addComponent(dobValue))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(sexLabel)
                                                        .addComponent(sexValue)))
                                        .addComponent(scrollableSeasonalWorkers, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(mainContainerLayout.createSequentialGroup()
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(phoneValue)
                                                        .addComponent(phoneLabel))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(addressLabel)
                                                        .addComponent(addressValue))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(postalCodeLabel)
                                                        .addComponent(postalCodeValue))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(countryLabel)
                                                        .addComponent(countryValue))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(mainContainerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(cprLabel)
                                                        .addComponent(cprValue)))
                                        .addComponent(scrollabelPaymentSlips, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
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

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Setup all the labels for JTextFields
     * @param label JLabel to be added/**
     *      * Setup all the labels for JTextFields
     *      * @param label JLabel to be added
     *      * @param text String, place holder text for JLabel
     */
    private void configureLabel(JLabel ...label){
        Arrays.stream(label).forEach((l) -> {
            l.setFont(new Font("Dialog", Font.BOLD, 24));
            l.setForeground(new Color(255, 255, 255));
        });
    }

}
