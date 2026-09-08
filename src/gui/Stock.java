
package gui;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.MySQL2;

/**
 *
 * @author Dini
 */
public class Stock extends javax.swing.JFrame {

    private GRN grn;

    public void setGrn(GRN grn) {
        this.grn = grn;
    }

    private Invoice invoice;

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Stock() {
        initComponents();
        loadStock();
    }
    
    
    private void loadStock() {

    try {

        String query = "SELECT * FROM `stock` "
                + "INNER JOIN `product` ON `stock`.`product_id` = `product`.`id` "
                + "INNER JOIN `brand` ON `product`.`brand_id`=`brand`.`id` "
                + "INNER JOIN `category` ON `product`.`category_id`=`category`.`id` ";

        boolean hasWhere = false;

        // Product ID from table row
        int row = jTable1.getSelectedRow();
        if (row != -1) {
            String pid = String.valueOf(jTable1.getValueAt(row, 0));
            query += "WHERE `stock`.`product_id` = '" + pid + "' ";
            hasWhere = true;
        }

        // Price filter
        double min_price = 0;
        double max_price = 0;

        if (!jFormattedTextField1.getText().isEmpty()) {
            min_price = Double.parseDouble(jFormattedTextField1.getText());
        }

        if (!jFormattedTextField2.getText().isEmpty()) {
            max_price = Double.parseDouble(jFormattedTextField2.getText());
        }

        if (min_price > 0 || max_price > 0) {
            if (!hasWhere) {
                query += "WHERE ";
                hasWhere = true;
            } else {
                query += "AND ";
            }

            if (min_price > 0 && max_price == 0) {
                query += "`stock`.`price` > '" + min_price + "' ";
            } else if (min_price == 0 && max_price > 0) {
                query += "`stock`.`price` < '" + max_price + "' ";
            } else if (min_price > 0 && max_price > 0) {
                query += "`stock`.`price` BETWEEN '" + min_price + "' AND '" + max_price + "' ";
            }
        }

        // Expiry date filter
        Date start = null;
        Date end = null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        if (jDateChooser1.getDate() != null || jDateChooser2.getDate() != null) {
            if (!hasWhere) {
                query += "WHERE ";
                hasWhere = true;
            } else {
                query += "AND ";
            }

            if (jDateChooser1.getDate() != null && jDateChooser2.getDate() == null) {
                start = jDateChooser1.getDate();
                query += "`stock`.`exp` >= '" + format.format(start) + "' ";
            } else if (jDateChooser1.getDate() == null && jDateChooser2.getDate() != null) {
                end = jDateChooser2.getDate();
                query += "`stock`.`exp` <= '" + format.format(end) + "' ";
            } else if (jDateChooser1.getDate() != null && jDateChooser2.getDate() != null) {
                start = jDateChooser1.getDate();
                end = jDateChooser2.getDate();
                query += "`stock`.`exp` BETWEEN '" + format.format(start) + "' AND '" + format.format(end) + "' ";
            }
        }

        // Sort
        String sort = String.valueOf(jComboBox2.getSelectedItem());
        query += "ORDER BY ";
        switch (sort) {
            case "Stock ID ASC":
                query += "`stock`.`id` ASC";
                break;
            case "Stock ID DESC":
                query += "`stock`.`id` DESC";
                break;
            case "Product ID ASC":
                query += "`product`.`id` ASC";
                break;
            case "Product ID DESC":
                query += "`product`.`id` DESC";
                break;
            case "Brand ASC":
                query += "`brand`.`id` ASC";
                break;
            case "Brand DESC":
                query += "`brand`.`id` DESC";
                break;
            case "Category ASC":
                query += "`category`.`id` ASC";
                break;
            case "Category DESC":
                query += "`category`.`id` DESC";
                break;
            case "Name ASC":
                query += "`product`.`pname` ASC";
                break;
            case "Name DESC":
                query += "`product`.`pname` DESC";
                break;
            case "Selling Price ASC":
                query += "`stock`.`price` ASC";
                break;
            case "Selling Price DESC":
                query += "`stock`.`price` DESC";
                break;
            case "Quantity ASC":
                query += "`stock`.`qty` ASC";
                break;
            case "Quantity DESC":
                query += "`stock`.`qty` DESC";
                break;
        }

        // Execute query
        ResultSet resultSet = MySQL2.executeSearch(query);

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        while (resultSet.next()) {
            Vector<String> vector = new Vector<>();
            vector.add(resultSet.getString("stock.id"));
            vector.add(resultSet.getString("product.id"));
            vector.add(resultSet.getString("brand.brandname"));
            vector.add(resultSet.getString("category.name"));
            vector.add(resultSet.getString("product.pname"));
            vector.add(resultSet.getString("stock.price"));
            vector.add(resultSet.getString("qty"));
            vector.add(resultSet.getString("mfd"));
            vector.add(resultSet.getString("exp"));
            model.addRow(vector);
        }

    } catch (Exception e) {
        e.printStackTrace();
        SplashScreen.logger.log(Level.WARNING, e.getMessage(), e);
    }
}


    private void loadStock2() {

        try {

            String query = "SELECT * FROM `stock` "
                    + "INNER JOIN `product` ON `stock`.`product_id` = `product`.`id` "
                    + "INNER JOIN `brand` ON `product`.`brand_id`=`brand`.`id`"
                    + "INNER JOIN `category` ON `product`.`category_id`=`category`.`id`";

            
                    boolean hasWhere = false;

            int row = jTable1.getSelectedRow();

            if (row != -1) {
                String pid = String.valueOf(jTable1.getValueAt(row, 0));
                query += "WHERE `stock`.`product_id` = '" + pid + "' ";
                            hasWhere = true;

            }

            if (query.contains("WHERE")) {
                query += "AND ";
            } else {
                query += "WHERE ";
            }

            // price 
            double min_price = 0;
            double max_price = 0;

            if (!jFormattedTextField1.getText().isEmpty()) {
                min_price = Double.parseDouble(jFormattedTextField1.getText());
            }

            if (!jFormattedTextField2.getText().isEmpty()) {
                max_price = Double.parseDouble(jFormattedTextField2.getText());
            }

            if (min_price > 0 && max_price == 0) {
                query += "`stock`.`price` > '" + min_price + "' ";
            } else if (min_price == 0 && max_price > 0) {
                query += "`stock`.`price` < '" + max_price + "' ";
            } else if (min_price > 0 && max_price > 0) {
                query += "`stock`.`price` > '" + min_price + "' AND `stock`.`price` <  '" + max_price + "'";
            }

            //exp
            Date start = null;
            Date end = null;

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            if (jDateChooser1.getDate() != null) {
                start = jDateChooser1.getDate();
                query += "`stock`.`exp` > '" + format.format(start) + "' AND";
            }

            if (jDateChooser2.getDate() != null) {
                end = jDateChooser2.getDate();
                query += "`stock`.`exp` < '" + format.format(end) + "' ";
            }

            //sort
            String sort = String.valueOf(jComboBox2.getSelectedItem());
            query += "ORDER BY ";
            query = query.replace("WHERE ORDER BY ", "ORDER BY ");
            query = query.replace("AND ORDER BY ", "ORDER BY ");

            if (sort.equals("Stock ID ASC")) {
                query += "`stock`.`id` ASC";
            } else if (sort.equals("Stock ID DESC")) {
                query += "`stock`.`id` DESC";
            } else if (sort.equals("Product ID ASC")) {
                query += "`product`.`id` ASC";
            } else if (sort.equals("Product ID DESC")) {
                query += "`product`.`id` DESC";
            } else if (sort.equals("Brand ASC")) {
                query += "`brand`.`id` ASC";
            } else if (sort.equals("Brand DESC")) {
                query += "`brand`.`id` DESC";

            } else if (sort.equals("Category ASC")) {
                query += "`category`.`id` ASC";
            } else if (sort.equals("Category DESC")) {
                query += "`category`.`id` DESC";

            } else if (sort.equals("Name ASC")) {
                query += "`product`.`pname` ASC";
            } else if (sort.equals("Name DESC")) {
                query += "`product`.`pname` DESC";

            } else if (sort.equals("Selling Price ASC")) {
                query += "`stock`.`price` ASC";
            } else if (sort.equals("Selling Price DESC")) {
                query += "`stock`.`price` DESC";
            } else if (sort.equals("Quantity ASC")) {
                query += "`stock`.`qty` ASC";
            } else if (sort.equals("Quantity DESC")) {
                query += "`stock`.`qty` DESC";
            }

            //stock id
            ResultSet resultSet = MySQL2.executeSearch(query);

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("stock.id"));
                vector.add(resultSet.getString("product.id"));
                vector.add(resultSet.getString("brand.brandname"));
                vector.add(resultSet.getString("category.name"));
                vector.add(resultSet.getString("product.pname"));
                vector.add(resultSet.getString("stock.price"));
                vector.add(resultSet.getString("qty"));
                vector.add(resultSet.getString("mfd"));
                vector.add(resultSet.getString("exp"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
            SplashScreen.logger.log(Level.WARNING, e.getMessage(), e);

        }

    }

    private void deleteStock() {
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a stock to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String stockId = (String) jTable1.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete stock ID : " + stockId + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                String query = "DELETE FROM `stock` WHERE `id` = '" + stockId + "'";
                MySQL2.executeIUD(query);
                loadStock();
                JOptionPane.showMessageDialog(this, "Stock deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting stock " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        btnSearch = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        brnClear = new javax.swing.JButton();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 20)); // NOI18N
        jLabel2.setText("Manage Stocks");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-stock-70.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(379, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(38, 38, 38)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(420, 420, 420))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel1.setText("Sort By :");

        jComboBox2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Stock ID ASC", "Stock ID DESC", "Product ID ASC", "Product ID DESC", "Brand ASC", "Brand DESC", "Category ASC", "Category DESC", "Name ASC", "Name DESC", "Selling Price ASC", "Selling Price DESC", "Quantity ASC", "Quantity DESC" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel6.setText("Selling Price ");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField1.setText("0");
        jFormattedTextField1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel7.setText("TO");

        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField2.setText("0");
        jFormattedTextField2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jFormattedTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField2ActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-search-30.png"))); // NOI18N
        btnSearch.setText("Find");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel8.setText("Search by Expiry date :");

        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel9.setText("TO");

        brnClear.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        brnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/30close.png"))); // NOI18N
        brnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brnClearActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-delete-30.png"))); // NOI18N
        btnDelete.setText("         Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(100, 100, 100)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(brnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 26, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Product Id", "Brand", "Category", "Product Name", "Selling Price", "Quantity", "MFD", "EXP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        loadStock();
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        loadStock();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void brnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brnClearActionPerformed

        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jComboBox2.setSelectedIndex(0);
        jFormattedTextField1.setText("");
        jFormattedTextField2.setText("");
        loadStock();
    }//GEN-LAST:event_brnClearActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();

        if (evt.getClickCount() == 2) {
            if (invoice != null) {
                invoice.getjTextField3().setText(String.valueOf(jTable1.getValueAt(row, 0)));
                invoice.getjLabel10().setText(String.valueOf(jTable1.getValueAt(row, 2)));
                invoice.getjLabel27().setText(String.valueOf(jTable1.getValueAt(row, 3)));
                invoice.getjLabel12().setText(String.valueOf(jTable1.getValueAt(row, 4)));
                invoice.getjTextField5().setText(String.valueOf(jTable1.getValueAt(row, 5)));
                invoice.getjLabel21().setText(String.valueOf(jTable1.getValueAt(row, 6)));
                invoice.getjFormattedTextField1().grabFocus();
                invoice.getjLabel14().setText(String.valueOf(jTable1.getValueAt(row, 7)));
                invoice.getjLabel16().setText(String.valueOf(jTable1.getValueAt(row, 8)));

                this.dispose();
            } else if (grn != null) {
                grn.getjTextField3().setText(String.valueOf(jTable1.getValueAt(row, 1)));
                grn.getjLabel9().setText(String.valueOf(jTable1.getValueAt(row, 2)));
                grn.getjLabel22().setText(String.valueOf(jTable1.getValueAt(row, 3)));
                grn.getjLabel11().setText(String.valueOf(jTable1.getValueAt(row, 4)));

//                 grn.getjFormattedTextField3().setValue(jTable1.getValueAt(row, 5).toString());
//
//                grn.getjLabel21().setText(String.valueOf(jTable1.getValueAt(row, 6)));
//                grn.getjFormattedTextField1().grabFocus();
//                grn.getjLabel14().setText(String.valueOf(jTable1.getValueAt(row, 7)));
//                grn.getjLabel16().setText(String.valueOf(jTable1.getValueAt(row, 8)));
                this.dispose();
            }
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void jFormattedTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField2ActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        deleteStock();
        loadStock();
    }//GEN-LAST:event_btnDeleteActionPerformed
//
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        FlatIntelliJLaf.setup();
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Stock().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
