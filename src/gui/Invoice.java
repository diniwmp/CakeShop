package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import model.InvoiceItem;
import model.MySQL2;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Dini
 */
public class Invoice extends javax.swing.JFrame {

    HashMap<String, InvoiceItem> invoiceItemMap = new HashMap<>();
    HashMap<String, String> paymentMethodMap = new HashMap<>();

    public Invoice(HashMap<String, String> x) {
        initComponents();
        loadPaymentMethods();
        generateInvoiceID();
        lblEmpName.setText(x.get("firstName") + " " + x.get("lastName"));
        lblEmpEmail.setText(x.get("email"));
        loadPaymentMethods();
        setDateTime();
        CheckWithdawPoint.setEnabled(false);
    }

    private void generateInvoiceID() {
        txtInvoiceNO.setEditable(false);
        long id = System.currentTimeMillis();
        txtInvoiceNO.setText(String.valueOf(id));
    }

    private void setDateTime() {
        // Create a timer to update the label every second
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat formatter = new SimpleDateFormat(" yyyy-MM-dd  HH:mm:ss ");
                Date date = new Date();
                lblDate.setText(formatter.format(date));
            }
        });
        timer.start();
    }

    private void loadPaymentMethods() {

        try {

            ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `payment_method`");

            Vector<String> vector = new Vector<>();

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                paymentMethodMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            paymentMethod1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            SplashScreen.logger.log(Level.WARNING, e.getMessage(), e);

        }
    }

    private void reset() {

        txtStockId.setText("");
        tfFormattedQty.setText("");
        lblAvailableQty.setText("0");
        lblBrandName.setText("Product  Brand Here");
        lblCateName.setText("Product Category  Here");
        lblProductName.setText("Product  Name Here");
        txtSellingPrice.setText("");
        lblMFD.setText("MFD Here");
        lblEXP.setText("EXP Here");
        txtInvoiceNO.grabFocus();
    }

    private void reset2() {
        invoiceItemMap.clear();
        txtInvoiceNO.setText("");
        generateInvoiceID();
        txtCusMobile.setText("");
        txtStockId.setText("");
        txtCusAvailablePoint.setText("");
        tfFormattedQty.setText("");
        lblAvailableQty.setText("0");
        lblBrandName.setText("Product  Brand Here");
        lblCateName.setText("Product Category  Here");
        lblProductName.setText("Product  Name Here");
        txtSellingPrice.setText("");
        lblMFD.setText("MFD Here");
        lblEXP.setText("EXP Here");
        fomattedTotal.setText("0");
        discountField.setText("0");
        formattedBalance.setText("0");
        lblCusName.setText("CUSTOMER NAME HERE");
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        txtInvoiceNO.grabFocus();
        paymentFieldtxt.setText("0");
    }

    private void loadInvoiceItems() {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        total = 0;

        for (InvoiceItem invoiceItem : invoiceItemMap.values()) {
            Vector<String> vector = new Vector<>();

            vector.add(invoiceItem.getStockID());
            vector.add(invoiceItem.getBrand());
            vector.add(invoiceItem.getCategoty());
            vector.add(invoiceItem.getName());
            vector.add(invoiceItem.getQty());
            vector.add(invoiceItem.getSellingPrice());
            vector.add(invoiceItem.getMfd());
            vector.add(invoiceItem.getExp());

            double itemTotal = Double.parseDouble(invoiceItem.getQty()) * Double.parseDouble(invoiceItem.getSellingPrice());
            total += itemTotal;
            vector.add(String.valueOf(itemTotal));

            model.addRow(vector);
        }

        fomattedTotal.setText(String.valueOf(total));
        calculate();
    }

    public JTextField getjTextField2() {
        return txtCusMobile;
    }

    public JLabel getjLabel6() {
        return lblCusName;
    }

    //stock id
    public JTextField getjTextField3() {
        return txtStockId;
    }

    // Brand Name
    public JLabel getjLabel10() {
        return lblBrandName;
    }

    // Category Name
    public JLabel getjLabel27() {
        return lblCateName;
    }

    // Product Name
    public JLabel getjLabel12() {
        return lblProductName;
    }

    //Selling Price 
    public JTextField getjTextField5() {
        return txtSellingPrice;
    }

    // MFD
    public JLabel getjLabel14() {
        return lblMFD;
    }

    // EXP
    public JLabel getjLabel16() {
        return lblEXP;
    }

    //QTY
    public JFormattedTextField getjFormattedTextField1() {
        return tfFormattedQty;
    }

    // Available Qty
    public JLabel getjLabel21() {
        return lblAvailableQty;
    }

    // Available points
    public JTextField getTextField4() {
        return txtCusAvailablePoint;
    }

    //payment Method
    private double total = 0;
    private double discount = 0;
    private double payment = 0;
    private boolean withdawPoints = false;
    private double balance = 0;
    private String paymentMethod = "Select";
    private double newPoints = 0;

    private void calculate() {

        if (discountField.getText().isEmpty()) {
            discount = 0;
        } else {
            discount = Double.parseDouble(discountField.getText());
        }

        if (paymentFieldtxt.getText().isEmpty()) {
            payment = 0;
        } else {
            payment = Double.parseDouble(paymentFieldtxt.getText());
        }
        total = Double.parseDouble(fomattedTotal.getText());

        if (CheckWithdawPoint.isSelected()) {
            withdawPoints = true;
        } else {
            withdawPoints = false;
        }
//        paymentMethod = String.valueOf(paymentMethod1.getSelectedItem());
        paymentMethod = String.valueOf(paymentMethod1.getSelectedIndex());

        //settings
        total -= discount;

        if (total < 0) {
            JOptionPane.showMessageDialog(this, "Total can't be less than 0", "Warning", JOptionPane.WARNING_MESSAGE);

        } else {
            //discount ok
            if (withdawPoints) {
                if (Double.parseDouble(txtCusAvailablePoint.getText()) == total) {
                    newPoints = 0;
                    total = 0;
                    // no payment required

                } else if (Double.parseDouble(txtCusAvailablePoint.getText()) < total) {
                    newPoints = 0;
                    total -= Double.parseDouble(txtCusAvailablePoint.getText());

                } else {
                    newPoints = Double.parseDouble(txtCusAvailablePoint.getText()) - total;
                    total = 0;
                    //no payment required
                }
            }
        }

//       if (paymentMethod.equals("Cash")) {
        if (paymentMethod1.getSelectedItem().equals(paymentMethod1.getItemAt(0))) {
            //cash
            paymentFieldtxt.setEnabled(true);
            paymentFieldtxt.setEditable(true);
            balance = payment - total;
//            paymentFieldtxt.setEditable(true);

            if (balance < 0) {
                printInvoiceButton.setEnabled(false);
            } else {
                printInvoiceButton.setEnabled(true);
            }

        } else {
            //card
            payment = total;
            balance = 0;
            paymentFieldtxt.setText(String.valueOf(payment));
            paymentFieldtxt.setEditable(false);
            printInvoiceButton.setEnabled(true);
        }

        formattedBalance.setText(String.valueOf(balance));

    }

    private void deleteSelectedInvoiceItem() {
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        String stockId = (String) model.getValueAt(selectedRow, 0);

        if (invoiceItemMap.containsKey(stockId)) {
            invoiceItemMap.remove(stockId);
            model.removeRow(selectedRow);
            recalculateInvoiceTotal();
            JOptionPane.showMessageDialog(this, "Item removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void recalculateInvoiceTotal() {
        total = 0;
        for (InvoiceItem invoiceItem : invoiceItemMap.values()) {
            total += Double.parseDouble(invoiceItem.getQty()) * Double.parseDouble(invoiceItem.getSellingPrice());
        }
        fomattedTotal.setText(String.valueOf(total));
        calculate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btnStock = new javax.swing.JButton();
        txtStockId = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfFormattedQty = new javax.swing.JFormattedTextField();
        jLabel28 = new javax.swing.JLabel();
        lblAvailableQty = new javax.swing.JLabel();
        lblBrandName = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lblCateName = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtSellingPrice = new javax.swing.JTextField();
        lblProductName = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblMFD = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblEXP = new javax.swing.JLabel();
        btnAddInvoice = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtInvoiceNO = new javax.swing.JTextField();
        btnDeleteItem = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblCusName = new javax.swing.JLabel();
        btnCustomerTable = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        txtCusAvailablePoint = new javax.swing.JTextField();
        txtCusMobile = new javax.swing.JTextField();
        btnAddNewCustomer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        paymentMethod1 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        fomattedTotal = new javax.swing.JFormattedTextField();
        formattedBalance = new javax.swing.JFormattedTextField();
        paymentFieldtxt = new javax.swing.JFormattedTextField();
        discountField = new javax.swing.JFormattedTextField();
        jLabel25 = new javax.swing.JLabel();
        CheckWithdawPoint = new javax.swing.JCheckBox();
        printInvoiceButton = new javax.swing.JButton();
        lblEmpName = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblEmpEmail = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1513, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 24)); // NOI18N
        jLabel1.setText("Invoice");

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel7.setText("Stock");

        btnStock.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        btnStock.setText("Select");
        btnStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockActionPerformed(evt);
            }
        });

        txtStockId.setEditable(false);
        txtStockId.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel8.setText("Quantity");

        tfFormattedQty.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel28.setText("Available Qty");

        lblAvailableQty.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblAvailableQty.setForeground(new java.awt.Color(0, 0, 0));
        lblAvailableQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAvailableQty.setText("0");

        lblBrandName.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblBrandName.setText("Product  Brand Here");

        jLabel26.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel26.setText("Brand");

        lblCateName.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblCateName.setText("Product Category  Here");

        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel9.setText("Category");

        jLabel13.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel13.setText("Selling Price");

        txtSellingPrice.setEditable(false);
        txtSellingPrice.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        txtSellingPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSellingPriceActionPerformed(evt);
            }
        });

        lblProductName.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblProductName.setText("Product  Name Here");

        jLabel11.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel11.setText("Name");

        jLabel15.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel15.setText("MFD");

        lblMFD.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblMFD.setText("MFD Here");

        jLabel17.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel17.setText("EXP");

        lblEXP.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblEXP.setText("EXP Here");

        btnAddInvoice.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        btnAddInvoice.setText("Add to Invoice");
        btnAddInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddInvoiceActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        btnClear.setText("Clear All");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel4.setText("Invoice Number");

        txtInvoiceNO.setEditable(false);
        txtInvoiceNO.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N

        btnDeleteItem.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        btnDeleteItem.setText("Delete Item");
        btnDeleteItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCateName, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                            .addComponent(lblProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSellingPrice)
                            .addComponent(lblMFD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEXP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBrandName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(lblAvailableQty, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tfFormattedQty, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtStockId, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnStock, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtInvoiceNO, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(btnDeleteItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAddInvoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInvoiceNO, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStockId, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(btnStock, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfFormattedQty, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblAvailableQty, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBrandName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCateName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMFD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEXP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnAddInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel5.setText("Customer");

        lblCusName.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblCusName.setForeground(new java.awt.Color(0, 0, 0));
        lblCusName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCusName.setText("Customer Name");

        btnCustomerTable.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        btnCustomerTable.setText("Select");
        btnCustomerTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerTableActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel24.setText("Available  Points");

        txtCusAvailablePoint.setEditable(false);
        txtCusAvailablePoint.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N

        txtCusMobile.setEditable(false);
        txtCusMobile.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N

        btnAddNewCustomer.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddNewCustomer.setText("ADD NEW COUSTOMER");
        btnAddNewCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewCustomerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCusMobile, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCusAvailablePoint, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCusName, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnCustomerTable, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAddNewCustomer)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCustomerTable, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCusAvailablePoint, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCusMobile, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCusName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAddNewCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTable1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock ID", "Brand", "Category", "Name", "Quantity", "Selling Price", "MFD", "EXD", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel18.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel18.setText("Total");

        jLabel19.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel19.setText("Discount");

        jLabel20.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel20.setText("Payment Method");

        paymentMethod1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        paymentMethod1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        paymentMethod1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                paymentMethod1ItemStateChanged(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel22.setText("Payment");

        jLabel23.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel23.setText("Balance");

        fomattedTotal.setEditable(false);
        fomattedTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        fomattedTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        fomattedTotal.setText("0");
        fomattedTotal.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        fomattedTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fomattedTotalActionPerformed(evt);
            }
        });

        formattedBalance.setEditable(false);
        formattedBalance.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        formattedBalance.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        formattedBalance.setText("0");
        formattedBalance.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N

        paymentFieldtxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        paymentFieldtxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        paymentFieldtxt.setText("0");
        paymentFieldtxt.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        paymentFieldtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paymentFieldtxtKeyReleased(evt);
            }
        });

        discountField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        discountField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        discountField.setText("0");
        discountField.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        discountField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountFieldActionPerformed(evt);
            }
        });
        discountField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                discountFieldKeyReleased(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel25.setText("Withdaw Points");

        CheckWithdawPoint.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CheckWithdawPointItemStateChanged(evt);
            }
        });

        printInvoiceButton.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        printInvoiceButton.setForeground(new java.awt.Color(0, 0, 0));
        printInvoiceButton.setText("PRINT INVOICE");
        printInvoiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printInvoiceButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(printInvoiceButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(paymentMethod1, 0, 174, Short.MAX_VALUE)
                            .addComponent(fomattedTotal)
                            .addComponent(formattedBalance, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(paymentFieldtxt)
                            .addComponent(discountField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CheckWithdawPoint, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fomattedTotal))
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(discountField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(paymentMethod1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(paymentFieldtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addGap(9, 9, 9)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CheckWithdawPoint)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(formattedBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(printInvoiceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        lblEmpName.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblEmpName.setText(" Employee Details");

        lblDate.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblDate.setText(" Date");

        lblEmpEmail.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblEmpEmail.setText(" emp email");

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-logout-40.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton9)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(lblEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(348, 348, 348)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblEmpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(665, 665, 665)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1011, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEmpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jButton9)))
                        .addGap(18, 18, 18)
                        .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(428, 428, 428)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(329, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockActionPerformed
        //stock View

        StockView stockView = new StockView();
        stockView.setVisible(true);
        stockView.setInvoice(this);

//        Stock stock = new Stock();
//        stock.setVisible(true);
//        stock.setInvoice(this);
    }//GEN-LAST:event_btnStockActionPerformed

    private void btnAddInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddInvoiceActionPerformed
        //add invoice

        String stockID = txtStockId.getText();
        String brand = lblBrandName.getText();
        String category = lblCateName.getText();
        String productName = lblProductName.getText();
        String qty = tfFormattedQty.getText();
        String mfd = lblMFD.getText();
        String exp = lblEXP.getText();
        String sellingPrice = txtSellingPrice.getText();
        String avaQty = lblAvailableQty.getText();

        // Validate quantity inputs
        if (qty.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Quantity fields cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
//        int requestedQty = Integer.parseInt(qty);
//        int availableQty = Integer.parseInt(avaQty);

            double requestedQty = Double.parseDouble(qty);
            double availableQty = Double.parseDouble(avaQty);

            // Check if requested quantity exceeds available quantity
            if (requestedQty > availableQty) {
                JOptionPane.showMessageDialog(this, "Requested quantity for " + productName + " exceeds available stock!", "Error", JOptionPane.ERROR_MESSAGE);
                return;

            }

            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setBrand(brand);
            invoiceItem.setCategoty(category);
            invoiceItem.setExp(exp);
            invoiceItem.setMfd(mfd);
            invoiceItem.setName(productName);
            invoiceItem.setQty(qty);
            invoiceItem.setSellingPrice(sellingPrice);
            invoiceItem.setStockID(stockID);

            if (invoiceItemMap.get(stockID) == null) {
                invoiceItemMap.put(stockID, invoiceItem);
            } else {

                InvoiceItem found = invoiceItemMap.get(stockID);

                int option = JOptionPane.showConfirmDialog(this, "Do you want to Update the Quantity of Product :" + productName, "Message",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (option == JOptionPane.YES_OPTION) {
                    found.setQty(String.valueOf(Double.parseDouble(found.getQty()) + Double.parseDouble(qty)));
                }
            }

            reset();
            tfFormattedQty.setText("");
            loadInvoiceItems();

        } catch (Exception e) {
            e.printStackTrace();
            SplashScreen.logger.log(Level.WARNING, e.getMessage(), e);

        }
    }//GEN-LAST:event_btnAddInvoiceActionPerformed

    private void btnCustomerTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerTableActionPerformed
        //customer table
        CustomerView cv = new CustomerView();
        cv.setVisible(true);
        cv.setInvoice(this);

        CheckWithdawPoint.setEnabled(true);
//
//        UpdateCustomer uc = new UpdateCustomer();
//        uc.setVisible(true);
//        uc.setInvoice(this);
    }//GEN-LAST:event_btnCustomerTableActionPerformed

    private void paymentMethod1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_paymentMethod1ItemStateChanged
        calculate();//3 paymentmethod

    }//GEN-LAST:event_paymentMethod1ItemStateChanged

    private void fomattedTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fomattedTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fomattedTotalActionPerformed

    private void paymentFieldtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paymentFieldtxtKeyReleased
        //4payment
        calculate();
    }//GEN-LAST:event_paymentFieldtxtKeyReleased

    private void discountFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountFieldActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_discountFieldActionPerformed

    private void discountFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_discountFieldKeyReleased
        calculate(); //1
    }//GEN-LAST:event_discountFieldKeyReleased

    private void CheckWithdawPointItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CheckWithdawPointItemStateChanged
        // 5 withdrwals
        calculate();
    }//GEN-LAST:event_CheckWithdawPointItemStateChanged

    private void printInvoiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printInvoiceButtonActionPerformed
        // TODO add your handling code here:
        try {

            // Validate essential fields
            if (invoiceItemMap.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No items added to the invoice.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String invoiceID = txtInvoiceNO.getText();
            String customerMobile = txtCusMobile.getText();
            String employeeEmail = lblEmpEmail.getText();
            String dateTime = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date());
            String paidAmount = paymentFieldtxt.getText();
            String paymentMethodID = paymentMethodMap.get(String.valueOf(paymentMethod1.getSelectedItem()));
            String discount = String.valueOf(discountField.getText());

            //insert to invoice
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            MySQL2.executeIUD("INSERT INTO `invoice` VALUES"
                    + "('" + invoiceID + "','" + dateTime + "','" + discount + "','" + paidAmount + "','" + employeeEmail + "','" + customerMobile + "','" + paymentMethodID + "')");

            for (InvoiceItem invoiceItem : invoiceItemMap.values()) {
                //insert to invoice item

                MySQL2.executeIUD("INSERT INTO `invoice_item`(`stock_id`,`qty`,`invoice_id`)"
                        + "VALUES('" + invoiceItem.getStockID() + "','" + invoiceItem.getQty() + "','" + invoiceID + "')");

                //stock update
                MySQL2.executeIUD("UPDATE `stock` SET `qty`=`qty`-'" + invoiceItem.getQty() + "' WHERE `id`='" + invoiceItem.getStockID() + "'");
                //danata thiyena stck qty eka aragena
            }

            double points = Double.parseDouble(fomattedTotal.getText()) / 100;

            //withdrw points
            if (withdawPoints) {
                newPoints += points;
                MySQL2.executeIUD("UPDATE `customer` SET `point` = '" + newPoints + "' WHERE `mobile`='" + customerMobile + "'");
            } else {
                MySQL2.executeIUD("UPDATE `customer` SET `point` = `point`+ '" + points + "' WHERE `mobile`='" + customerMobile + "'");

            }

            //view or print report
            //report code
            InputStream s = this.getClass().getResourceAsStream("/reports/Invoice.jasper");

            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", fomattedTotal.getText()); //total;
            params.put("Parameter2", discountField.getText());//discount
            params.put("Parameter3", String.valueOf(paymentMethod1.getSelectedItem())); // payment method
            params.put("Parameter4", paymentFieldtxt.getText());//payment
            params.put("Parameter5", formattedBalance.getText());//balence

            params.put("Parameter6", txtInvoiceNO.getText());//invoice no
            params.put("Parameter7", lblEmpName.getText());//emp name
            params.put("Parameter8", lblCusName.getText());//cusname
            params.put("Parameter9", dateTime);// date

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(s, params, dataSource);

            JasperViewer.viewReport(jasperPrint, false);
            reset2();

        } catch (Exception e) {
            e.printStackTrace();
            SplashScreen.logger.log(Level.WARNING, e.getMessage(), e);

        }

    }//GEN-LAST:event_printInvoiceButtonActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        reset();
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtSellingPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSellingPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSellingPriceActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:

        int userDecision = JOptionPane.showConfirmDialog(this, "Are you sure want to log out?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (userDecision == JOptionPane.YES_OPTION) {
            //            this.admin = null;
            //            LoginJunction loginJunction = new LoginJunction();
            //            loginJunction.setVisible(true);
            //            this.dispose();
            Login login = new Login();
            login.setVisible(true);
            this.dispose();

        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void btnAddNewCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewCustomerActionPerformed

        // add customer
        AddCustomer addc = new AddCustomer();
        addc.setVisible(true);

    }//GEN-LAST:event_btnAddNewCustomerActionPerformed

    private void btnDeleteItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteItemActionPerformed
        deleteSelectedInvoiceItem();

    }//GEN-LAST:event_btnDeleteItemActionPerformed

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
//                new Invoice().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CheckWithdawPoint;
    private javax.swing.JButton btnAddInvoice;
    private javax.swing.JButton btnAddNewCustomer;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnCustomerTable;
    private javax.swing.JButton btnDeleteItem;
    private javax.swing.JButton btnStock;
    private javax.swing.JFormattedTextField discountField;
    private javax.swing.JFormattedTextField fomattedTotal;
    private javax.swing.JFormattedTextField formattedBalance;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblAvailableQty;
    private javax.swing.JLabel lblBrandName;
    private javax.swing.JLabel lblCateName;
    private javax.swing.JLabel lblCusName;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblEXP;
    private javax.swing.JLabel lblEmpEmail;
    private javax.swing.JLabel lblEmpName;
    private javax.swing.JLabel lblMFD;
    private javax.swing.JLabel lblProductName;
    private javax.swing.JFormattedTextField paymentFieldtxt;
    private javax.swing.JComboBox<String> paymentMethod1;
    private javax.swing.JButton printInvoiceButton;
    private javax.swing.JFormattedTextField tfFormattedQty;
    private javax.swing.JTextField txtCusAvailablePoint;
    private javax.swing.JTextField txtCusMobile;
    private javax.swing.JTextField txtInvoiceNO;
    private javax.swing.JTextField txtSellingPrice;
    private javax.swing.JTextField txtStockId;
    // End of variables declaration//GEN-END:variables
}
