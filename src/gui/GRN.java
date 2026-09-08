package gui;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.GRNItem;
import model.MySQL2;
import java.sql.ResultSet;
import java.util.logging.Level;

public class GRN extends javax.swing.JFrame {

    HashMap<String, GRNItem> grnItemMap = new HashMap<>();

    public GRN(HashMap<String, String> x) {
        initComponents();
        generateGRNId(); //grn id genarate
        lblEmpEmail.setText(AdminLogin.getEmployeeEmail());
        lblEmpName.setText(x.get("firstName") + " " + x.get("lastName"));

    }

    private void generateGRNId() {
        long id = System.currentTimeMillis();
        grnNotxt.setText(String.valueOf(id));
    }

    //mobile
    public JTextField getjTextField2() {
        return txtSupMobile;
    }

    //name
    public JLabel getjLabel21() {
        return lblSuppName;
    }

    //product ID
    public JTextField getjTextField3() {
        return txtProductId;
    }

    //brand
    public JLabel getjLabel9() {
        return lblBrand;
    }
    //category

    public JLabel getjLabel22() {
        return lblCategory;

    }

    //product Name
    public JLabel getjLabel11() {
        return lblProductName;

    }

    private void reset() {
//        grnItemMap.clear();
        jFormattedTextField1.setText("");
        fomatTxtBuyingPrice.setText("");
        fomatTxtSellingPrice.setText("");
        fomattedTxtPayment.setText("");
//        grnNotxt.setText("");
        txtSupMobile.setText("");
        txtProductId.setText("");
        lblBrand.setText("Product Brand");
        lblCategory.setText("Product Catrgory");
        lblProductName.setText("Product Name");
        lblSuppName.setText("Supplier Name");
        dateChooserEXP.setDate(null);
        dateChooserMFD.setDate(null);
//        generateGRNId();
    }

    private void reset2() {
        grnItemMap.clear();
        grnNotxt.setText("");
        generateGRNId();
        txtSupMobile.setText("");
        txtProductId.setText("");
        lblBrand.setText("Product Brand");
        lblCategory.setText("Product Catrgory");
        lblProductName.setText("Product Name");
        lblSuppName.setText("Supplier Name");
        dateChooserEXP.setDate(null);
        dateChooserMFD.setDate(null);
        jFormattedTextField1.setText("");
        fomatTxtBuyingPrice.setText("");
        fomatTxtSellingPrice.setText("");
        fomattedTxtPayment.setText("");
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        lblTotal.setText("0.00");
        lblBalance.setText("0.00");

    }

    private void loadGRNItems() {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        double total = 0;

        for (GRNItem grnItem : grnItemMap.values()) {
            Vector<String> vector = new Vector<>();
            vector.add(grnItem.getProductId());
            vector.add(grnItem.getBrandName());
            vector.add(grnItem.getCategoryName());
            vector.add(grnItem.getProductName());
            vector.add(String.valueOf(grnItem.getQty()));
            vector.add(String.valueOf(grnItem.getBuyingPrice()));
            vector.add(String.valueOf(grnItem.getSellingPrice()));
            vector.add(format.format(grnItem.getMfd()));
            vector.add(format.format(grnItem.getExp()));

            double itemTotal = grnItem.getQty() * grnItem.getBuyingPrice();
            total += itemTotal;
            vector.add(String.valueOf(itemTotal));

            model.addRow(vector);
        }

        lblTotal.setText(String.valueOf(total));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnAddGrn = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblBalance = new javax.swing.JLabel();
        btnSaveGrn = new javax.swing.JButton();
        fomattedTxtPayment = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        grnNotxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSupMobile = new javax.swing.JTextField();
        btnSupplier = new javax.swing.JButton();
        lblSuppName = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtProductId = new javax.swing.JTextField();
        btnStock = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lblBrand = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblCategory = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblProductName = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        fomatTxtSellingPrice = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        fomatTxtBuyingPrice = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        dateChooserMFD = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        dateChooserEXP = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblEmpEmail = new javax.swing.JLabel();
        lblEmpName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnAddGrn.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        btnAddGrn.setText("Add to GRN");
        btnAddGrn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddGrnActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddGrn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addComponent(btnAddGrn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        jPanel4.setForeground(new java.awt.Color(0, 0, 0));

        jLabel16.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel16.setText("Total");

        jLabel17.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel17.setText("Payment");

        lblTotal.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotal.setText("0.00");

        jLabel19.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel19.setText("Balance");

        lblBalance.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        lblBalance.setForeground(new java.awt.Color(0, 0, 0));
        lblBalance.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBalance.setText("0.00");

        btnSaveGrn.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnSaveGrn.setText("Save GRN");
        btnSaveGrn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveGrnActionPerformed(evt);
            }
        });

        fomattedTxtPayment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fomattedTxtPaymentKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnSaveGrn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(fomattedTxtPayment, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(lblTotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBalance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(14, 14, 14))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fomattedTxtPayment, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(btnSaveGrn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Brand", "Category", "Name", "Quantity", "Buying Price", "Selling Price", "MFD", "EXP", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel4.setText("GRN Number");

        grnNotxt.setEditable(false);
        grnNotxt.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        grnNotxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grnNotxtActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel5.setText("Supplier");

        txtSupMobile.setEditable(false);
        txtSupMobile.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        btnSupplier.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        btnSupplier.setText("Select");
        btnSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierActionPerformed(evt);
            }
        });

        lblSuppName.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblSuppName.setForeground(new java.awt.Color(0, 0, 0));
        lblSuppName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSuppName.setText("Supplier Name");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSuppName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSupMobile)
                            .addComponent(grnNotxt))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grnNotxt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSupMobile, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(btnSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblSuppName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel6.setText("Product ID");

        txtProductId.setEditable(false);
        txtProductId.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        btnStock.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        btnStock.setText("Select");
        btnStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel8.setText("Brand");

        lblBrand.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblBrand.setText("Product Brand  Here");

        jLabel23.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel23.setText("Category");

        lblCategory.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblCategory.setText("Product Category Here");

        jLabel10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel10.setText("Name");

        lblProductName.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblProductName.setText("Product Name Here ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblProductName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnStock, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(btnStock, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel13.setText("Selling Price");

        fomatTxtSellingPrice.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jLabel12.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel12.setText("Buying Price");

        fomatTxtBuyingPrice.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jLabel14.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel14.setText("MFD");

        dateChooserMFD.setDateFormatString("yyyy-MM-dd");

        jLabel15.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel15.setText("EXP");

        dateChooserEXP.setDateFormatString("yyyy-MM-dd");

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel7.setText("Quantity");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fomatTxtSellingPrice))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fomatTxtBuyingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateChooserMFD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(dateChooserEXP, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fomatTxtSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fomatTxtBuyingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(dateChooserMFD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(dateChooserEXP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34))
        );

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 36)); // NOI18N
        jLabel1.setText("GRN");

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/receipt-70.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(597, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(160, 160, 160))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel24)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        lblEmpEmail.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblEmpEmail.setForeground(new java.awt.Color(0, 0, 0));
        lblEmpEmail.setText("Employee Details");

        lblEmpName.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblEmpName.setForeground(new java.awt.Color(0, 0, 0));
        lblEmpName.setText("Employee Name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 229, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblEmpEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                            .addComponent(lblEmpName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblEmpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void grnNotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grnNotxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grnNotxtActionPerformed

    private void btnSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierActionPerformed
        //supplier tabel

//        UpdateAllSupplier us = new UpdateAllSupplier();
        SupplierView sv = new SupplierView();
        sv.setVisible(true);
        sv.setGrn(this);
    }//GEN-LAST:event_btnSupplierActionPerformed

    private void btnStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockActionPerformed
//        Stock stock = new Stock();
//        stock.setVisible(true);
//        stock.setGrn(this);
        PnlProduct pp = new PnlProduct();
        pp.setVisible(true);
        pp.setGrn(this);
    }//GEN-LAST:event_btnStockActionPerformed

    private void btnAddGrnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddGrnActionPerformed
        //add to grn

        String qty = jFormattedTextField1.getText();
        String buying_price = fomatTxtBuyingPrice.getText();
        String selling_price = fomatTxtSellingPrice.getText();
        Date mfd = dateChooserEXP.getDate();
        Date exp = dateChooserMFD.getDate();

        if (qty.isEmpty() || buying_price.isEmpty() || selling_price.isEmpty() || mfd == null || exp == null) {
            JOptionPane.showMessageDialog(this, "All fields must be filled before adding to GRN.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {

            double quantity = Double.parseDouble(qty);
            double buyingPrice = Double.parseDouble(buying_price);
            double sellingPrice = Double.parseDouble(selling_price);

            if (quantity <= 0 || buyingPrice <= 0 || sellingPrice <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity and prices must be greater than zero.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            GRNItem grnItem = new GRNItem();
            grnItem.setProductId(txtProductId.getText());
            grnItem.setBrandName(lblBrand.getText());
            grnItem.setCategoryName(lblProductName.getText());
            grnItem.setProductName(lblProductName.getText());
            grnItem.setQty(Double.parseDouble(qty));
            grnItem.setBuyingPrice(Double.parseDouble(buying_price));
            grnItem.setSellingPrice(Double.parseDouble(selling_price));
            grnItem.setMfd(mfd);
            grnItem.setExp(exp);

            if (grnItemMap.get(txtProductId.getText()) == null) {
                grnItemMap.put(txtProductId.getText(), grnItem);
            } else {

                GRNItem found = grnItemMap.get(txtProductId.getText());

                if (found.getExp().compareTo(exp) == 0
                        && found.getMfd().compareTo(mfd) == 0
                        && found.getBuyingPrice() == Double.parseDouble(buying_price)
                        && found.getSellingPrice() == Double.parseDouble(selling_price)) {

                    found.setQty(found.getQty() + Double.parseDouble(qty));
//                    loadGRNItems();
                } else {
                    JOptionPane.showMessageDialog(this, "GRN item already exists with diffrent dates and prices", "Error", JOptionPane.ERROR_MESSAGE);
                }
                //chnges2 
                reset();
                loadGRNItems();
            }

        } catch (Exception e) {
            e.printStackTrace();
            SplashScreen.logger.log(Level.WARNING, e.getMessage(), e);

        }

    }//GEN-LAST:event_btnAddGrnActionPerformed

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void btnSaveGrnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveGrnActionPerformed
        // save grn

        // Validate that the GRN table has data
        if (jTable1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No items added to GRN. Please add items before saving.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String grnNumber = grnNotxt.getText();
            String supplierMobile = txtSupMobile.getText();
            String employeeEmail = lblEmpEmail.getText();
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String paidAmount = fomattedTxtPayment.getText();

//            String paidAmount = String.valueOf(jFormattedTextField4.getText());
//            double paidAmount = Double.parseDouble(jFormattedTextField4.getText());
            // Ensure paid amount is valid
            if (paidAmount.isEmpty() || !paidAmount.matches("^\\d+(\\.\\d{1,2})?$")) {
                JOptionPane.showMessageDialog(this, "Invalid paid amount. Please enter a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            MySQL2.executeIUD("INSERT INTO `grn` (`id` ,`date`,`paid_amount`,`supplier_mobile`,`employee_email`)"
                    + "VALUES ('" + grnNumber + "' ,'" + date + "','" + paidAmount + "','" + supplierMobile + "','" + employeeEmail + "')");

            for (GRNItem grnItem : grnItemMap.values()) {
                ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `stock` WHERE "
                        + "`product_id` = '" + grnItem.getProductId() + "' AND"
                        + "`price` = '" + grnItem.getSellingPrice() + "' AND"
                        + "`mfd`='" + sdf.format(grnItem.getMfd()) + "' AND"
                        + "`exp`='" + sdf.format(grnItem.getExp()) + "'");

                String sid = "";

                if (resultSet.next()) {
                    //existing stock (danatamth stock eke thiyena data tika
                    sid = resultSet.getString("id");

                    String currentQty = resultSet.getString("qty");
                    String updateQuantity = String.valueOf(Double.parseDouble(currentQty) + grnItem.getQty());
                    MySQL2.executeIUD("UPDATE `stock` SET `qty` ='" + updateQuantity + "'WHERE `id` = '" + sid + "'");

                } else {
                    //new stock
                    MySQL2.executeIUD("INSERT INTO `stock`(`product_id`,`qty`,`price`,`mfd`,`exp`)"
                            + " VALUES('" + grnItem.getProductId() + "','" + grnItem.getQty() + "','" + grnItem.getSellingPrice() + "',"
                            + "'" + sdf.format(grnItem.getMfd()) + "','" + sdf.format(grnItem.getExp()) + "')");

                    ResultSet resultSet2 = MySQL2.executeSearch("SELECT * FROM `stock` WHERE "
                            + "`product_id` = '" + grnItem.getProductId() + "' AND"
                            + "`price` = '" + grnItem.getSellingPrice() + "' AND"
                            + "`mfd`='" + sdf.format(grnItem.getMfd()) + "' AND"
                            + "`exp`='" + sdf.format(grnItem.getExp()) + "'");

                    if (resultSet2.next()) {
                        sid = resultSet2.getString("id");
                    }
                }
                //insert grn item

                MySQL2.executeIUD("INSERT INTO `grn_item`(`stock_id`,`qty`,`price`,`grn_id`) "
                        + "VALUES('" + sid + "','" + grnItem.getQty() + "','" + grnItem.getBuyingPrice() + "','" + grnNumber + "')");

            }
            JOptionPane.showMessageDialog(this, "GRN Added", "Done", JOptionPane.INFORMATION_MESSAGE);
//            grnItemMap.clear();
//            reset();
            reset2();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while saving the GRN.", "Error", JOptionPane.ERROR_MESSAGE);
            SplashScreen.logger.log(Level.WARNING, e.getMessage(), e);

        }


    }//GEN-LAST:event_btnSaveGrnActionPerformed

    private void fomattedTxtPaymentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fomattedTxtPaymentKeyReleased
        // TODO add your handling code here:
        String total = lblTotal.getText();
        String payment = fomattedTxtPayment.getText();

        if (payment.isEmpty()) {
            payment = "0";

        } else if (!payment.matches("^(0|[1-9]\\d*)?(\\.\\d+)?(?<=\\d)$")) {

            lblBalance.setText("INVALID");
            lblBalance.setForeground(Color.BLACK);

        } else {
            double balance = Double.parseDouble(payment) - Double.parseDouble(total);
            lblBalance.setText(String.valueOf(balance));
            lblBalance.setForeground(Color.BLACK);
        }


    }//GEN-LAST:event_fomattedTxtPaymentKeyReleased

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed

        JOptionPane.showMessageDialog(this, "Reset Your Data", "Done", JOptionPane.INFORMATION_MESSAGE);
        reset();
    }//GEN-LAST:event_btnResetActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        FlatIntelliJLaf.setup();
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new GRN().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddGrn;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSaveGrn;
    private javax.swing.JButton btnStock;
    private javax.swing.JButton btnSupplier;
    private com.toedter.calendar.JDateChooser dateChooserEXP;
    private com.toedter.calendar.JDateChooser dateChooserMFD;
    private javax.swing.JFormattedTextField fomatTxtBuyingPrice;
    private javax.swing.JFormattedTextField fomatTxtSellingPrice;
    private javax.swing.JFormattedTextField fomattedTxtPayment;
    private javax.swing.JTextField grnNotxt;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblBalance;
    private javax.swing.JLabel lblBrand;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblEmpEmail;
    private javax.swing.JLabel lblEmpName;
    private javax.swing.JLabel lblProductName;
    private javax.swing.JLabel lblSuppName;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTextField txtProductId;
    private javax.swing.JTextField txtSupMobile;
    // End of variables declaration//GEN-END:variables
}
