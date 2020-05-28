package GUI;

import javax.swing.*;
import Controller.*;
import DB.ProduceDB;
import GUI.Components.ComponentsConfigure;
import Model.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ProduceCheckList extends javax.swing.JFrame {
    private static Consumer<ArrayList<String>> consumer;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel maximizeBtn;
    private javax.swing.JLabel exitBtn;
    private javax.swing.JLabel minimizeBtn;
    private javax.swing.JLabel frameTitle;
    private javax.swing.JList<Produce> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel topBar;
    private javax.swing.JScrollPane jScrollPane1;

    private WorkTypeCtr workTypeCtr;
    private List<WorkType> produceList;
    private String[] x;

    /**
     * Creates new form produceCheckList
     */
    public ProduceCheckList(Consumer<ArrayList<String>> consumer) {
        this.consumer = consumer;
        try {
            loadProduce();
        } catch (DataAccessException e) {
            e.printStackTrace();
            // TODO alert user
            // TODO need to discuss loading and also refresh button
            // probably should also return or something
        }
        initComponents();
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProduceCheckList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new ProduceCheckList(consumer).setVisible(true));
    }

    private void loadProduce() throws DataAccessException {
        workTypeCtr = new WorkTypeCtr();
        produceList = workTypeCtr.findAll();

        ProduceDB pDB;
        pDB = new ProduceDB();

        // Setting the list model.
        // Populating with all products found in the database.
        ArrayList<Produce> allProduces = new ArrayList<>(pDB.findAll(Produce.class));
        jList1 = new javax.swing.JList();
        for (Produce produce: allProduces) {
            produce.setCollectedOnWorksite(false);
        }
        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < allProduces.size(); i++) {
            listModel.addElement(allProduces.get(i));
        }
        jList1.setModel(listModel);

        jList1.setCellRenderer(new CheckBoxListCellRenderer());
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                JList<Produce> list =
                        (JList<Produce>) event.getSource();

                // Get index of item clicked

                int index = list.locationToIndex(event.getPoint());
                Produce item = list.getModel()
                        .getElementAt(index);

                // Toggle selected state

                item.setCollectedOnWorksite(!item.getCollectedOnWorksite());

                // Repaint cell

                list.repaint(list.getCellBounds(index, index));
            }
        });
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        topBar = new javax.swing.JPanel();
        maximizeBtn = new javax.swing.JLabel();
        exitBtn = new javax.swing.JLabel();
        minimizeBtn = new javax.swing.JLabel();
        frameTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
//        jList1 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 800));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(400, 800));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(71, 120, 197));

        ComponentsConfigure.createTopBar(topBar,frameTitle,minimizeBtn,maximizeBtn,exitBtn, new Color(120,168,252),this);

        frameTitle.setFont(new java.awt.Font("Dialog", Font.BOLD, 24)); // NOI18N
        frameTitle.setText("CS Works");

//        jList1.setModel(new javax.swing.AbstractListModel<String>(){
//            public int getSize () {
//                return x.length;
//            }
//            public String getElementAt ( int i){
//                return x[i];
//            }
//        });
        jScrollPane1.setViewportView(jList1);

        jButton1.setBackground(new java.awt.Color(71, 120, 197));
        jButton1.setText("Confirm");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(topBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setIconImage(new ImageIcon(getClass().getResource("/icons8_potted_plant_50px_1.png")).getImage());
        pack();
        setLocationRelativeTo(null);
    }

    // Always call this when you edit WorkSite's collected produce
    private void updateProduceCheckList(Integer workSiteID) throws DataAccessException {
        ProduceDB pDB;
        pDB = new ProduceDB();

        ArrayList<Produce> workSiteCollectedProduce = new ArrayList<>(pDB.findWorkSiteProduce(workSiteID,
                Produce.class));

        // TBC
    }

    // Confirm button
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<String> collectedProduce = new ArrayList<>();
        for (int i = 0;  i < jList1.getModel().getSize(); i++) {
            if (jList1.getModel().getElementAt(i).getCollectedOnWorksite()) {
                collectedProduce.add(jList1.getModel().getElementAt(i).getProduceName());
            }
        }
        consumer.accept(collectedProduce);
        dispose();
    }

//    public void getCollectedProduce(Consumer<HashMap<Object, Object>> consumer) {
//
//    }
}
