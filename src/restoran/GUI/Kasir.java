/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package restoran.GUI;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import restoran.dao.TransaksiDAO;
import restoran.model.Transaksi;

/**
 *
 * @author USER
 */
public class Kasir extends javax.swing.JFrame {

    private double totalHarga;

    public Kasir() {
        initComponents();
        loadPendingOrders();
    }

    private void loadPendingOrders() {
        TransaksiDAO transaksiDAO = new TransaksiDAO();
        List<Transaksi> transaksiList = transaksiDAO.getPendingOrders();

        DefaultTableModel model = new DefaultTableModel(new String[]{"ID Order", "Total Harga"}, 0);
        for (Transaksi transaksi : transaksiList) {
            model.addRow(new Object[]{
                transaksi.getIdOrder(),
                transaksi.getTotalPembayaran()
            });
        }
        tblOrder.setModel(model);
        tblOrder.setDefaultEditor(Object.class, null);
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        lblTotalHarga = new javax.swing.JLabel();
        txtPembayaran = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        konfirmBayar = new javax.swing.JButton();
        lblKembalian = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbMetodePembayaran = new javax.swing.JComboBox<>();
        backButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setText("KASIR");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, -1));

        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID Order", "Total Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrderMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblOrder);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 94, 616, 275));
        getContentPane().add(lblTotalHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 393, -1, -1));
        getContentPane().add(txtPembayaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 415, 189, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Total pembayaran:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, -1, -1));

        konfirmBayar.setText("Bayar");
        konfirmBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                konfirmBayarActionPerformed(evt);
            }
        });
        getContentPane().add(konfirmBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(591, 516, -1, -1));
        getContentPane().add(lblKembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(663, 498, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Metode Bayar");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 390, -1, -1));

        cbMetodePembayaran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Credit Card", "E-Wallet" }));
        getContentPane().add(cbMetodePembayaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(569, 415, -1, -1));

        backButton.setText("Kembali");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 516, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/depositphotos_11051482-stock-photo-well-dressed-black-waiter.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, -20, 750, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void konfirmBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_konfirmBayarActionPerformed
            try {
            double pembayaran = Double.parseDouble(txtPembayaran.getText());
            totalHarga = Double.parseDouble(lblTotalHarga.getText().split(": ")[1]);

            if (pembayaran < totalHarga) {
                JOptionPane.showMessageDialog(this, "Pembayaran kurang!");
            } else {
                // Hitung kembalian
                double kembalian = pembayaran - totalHarga;
                lblKembalian.setText(String.format("Kembalian: %.2f", kembalian));

                // Ambil ID order dari tabel
                int selectedRow = tblOrder.getSelectedRow();
                int orderId = Integer.parseInt(tblOrder.getValueAt(selectedRow, 0).toString());

                // Ambil metode pembayaran
                String metodePembayaran = cbMetodePembayaran.getSelectedItem().toString();

                // Buat objek Transaksi
                Transaksi transaksi = new Transaksi();
                transaksi.setIdOrder(orderId);
                transaksi.setTotalPembayaran(totalHarga);
                transaksi.setMetodePembayaran(metodePembayaran);

                // Kirim ke DAO untuk disimpan
                TransaksiDAO transaksiDAO = new TransaksiDAO();
                transaksiDAO.createTransaction(transaksi);

                // Update status order
                transaksiDAO.updateOrderStatusToPaid(transaksi);

                // Reload tabel pesanan
                loadPendingOrders();
                JOptionPane.showMessageDialog(this, "Pembayaran berhasil!");
                this.dispose();
                new Dashboard().setVisible(true);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid!");
        }
    }//GEN-LAST:event_konfirmBayarActionPerformed

    private void tblOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrderMouseClicked
        int selectedRow = tblOrder.getSelectedRow();
        if (selectedRow != -1) {
            totalHarga = Double.parseDouble(tblOrder.getValueAt(selectedRow, 1).toString());
            lblTotalHarga.setText("Total Harga: " + totalHarga);
        }
    }//GEN-LAST:event_tblOrderMouseClicked

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        new Dashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> cbMetodePembayaran;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton konfirmBayar;
    private javax.swing.JLabel lblKembalian;
    private javax.swing.JLabel lblTotalHarga;
    private javax.swing.JTable tblOrder;
    private javax.swing.JTextField txtPembayaran;
    // End of variables declaration//GEN-END:variables
}
