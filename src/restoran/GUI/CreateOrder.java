package restoran.GUI;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import restoran.DAO.KategoriDAO;
import restoran.dao.DetailOrderDAO;
import restoran.dao.MenuDAO;
import restoran.dao.OrderDAO;
import restoran.model.DetailOrder;
import restoran.model.Kategori;
import restoran.model.Menu;
import restoran.model.Order;

public class CreateOrder extends javax.swing.JFrame {
    private int orderId;
    private double totalHarga;
    private DefaultTableModel keranjangModel;
    
    public CreateOrder() {
        initComponents();
        initKeranjangModel();
        createNewOrder(); // Otomatis buat order baru
        loadMenuTable(); // Memuat data menu dari database
    }
    
    private void createNewOrder() {
        Order order = new Order();
        order.setTanggalOrder(new java.util.Date());
        order.setStatus("Unpaid");
        order.setTotalHarga(0.0);

        OrderDAO orderDAO = new OrderDAO();
        orderId = orderDAO.createNewOrder(order);
        txtOrderId.setText(String.valueOf(orderId));
        txtTanggalOrder.setText(order.getTanggalOrder().toString());
        txtStatus.setText(order.getStatus());
        txtTotalHarga.setText(String.format("%.2f", order.getTotalHarga()));
    }


    private void initKeranjangModel() {
        keranjangModel = new DefaultTableModel(new String[]{"Nama", "Kategori", "Harga", "Jumlah", "Subtotal"}, 0);
        tblKeranjang.setModel(keranjangModel);
    }
    
    private String getKategoriById(int idKategori) {
        KategoriDAO kategoriDAO = new KategoriDAO();
        Kategori kategori = kategoriDAO.read(idKategori);
        return kategori != null ? kategori.getNamaKategori() : "Tidak Diketahui";
    }
    
    private void loadMenuTable() {
        MenuDAO menuDAO = new MenuDAO();
        List<Menu> menus = menuDAO.readAll();
        DefaultTableModel model = (DefaultTableModel) tblMenu.getModel();
        model.setRowCount(0); // Bersihkan data lama
        for (Menu menu : menus) {
            model.addRow(new Object[]{
                menu.getNama(),
                getKategoriById(menu.getIdKategori()), // Fungsi untuk mendapatkan nama kategori
                menu.getHarga()
            });
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        masterOrder = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMenu = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtOrderId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tambahKeranjang = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKeranjang = new javax.swing.JTable();
        hapusKeranjang = new javax.swing.JButton();
        konfirmasiOrder = new javax.swing.JButton();
        txtTanggalOrder = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        txtTotalHarga = new javax.swing.JTextField();
        Cancel = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        masterOrder.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        masterOrder.setForeground(new java.awt.Color(255, 255, 255));
        masterOrder.setText("MASTER ORDER");
        getContentPane().add(masterOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 24, -1, -1));

        tblMenu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nama", "Harga", "Kategori"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblMenu);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 225, -1, 275));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID ORDER");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 68, -1, -1));

        txtOrderId.setEditable(false);
        getContentPane().add(txtOrderId, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 67, 200, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tanggal");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 95, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Status");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 123, -1, -1));

        tambahKeranjang.setText("Tambah");
        tambahKeranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahKeranjangActionPerformed(evt);
            }
        });
        getContentPane().add(tambahKeranjang, new org.netbeans.lib.awtextra.AbsoluteConstraints(402, 190, -1, -1));

        tblKeranjang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nama", "Kategori", "Harga", "Jumlah", "Sub Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblKeranjang);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 225, -1, 275));

        hapusKeranjang.setText("Hapus");
        hapusKeranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusKeranjangActionPerformed(evt);
            }
        });
        getContentPane().add(hapusKeranjang, new org.netbeans.lib.awtextra.AbsoluteConstraints(873, 190, -1, -1));

        konfirmasiOrder.setText("Pesan");
        konfirmasiOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                konfirmasiOrderActionPerformed(evt);
            }
        });
        getContentPane().add(konfirmasiOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(873, 518, -1, -1));

        txtTanggalOrder.setEditable(false);
        getContentPane().add(txtTanggalOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 95, 200, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total Harga");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 151, -1, -1));

        txtStatus.setEditable(false);
        getContentPane().add(txtStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 123, 200, -1));

        txtTotalHarga.setEditable(false);
        getContentPane().add(txtTotalHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 151, 200, -1));

        Cancel.setText("Kembali");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });
        getContentPane().add(Cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(782, 518, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/wallpaperflare.com_wallpaper.jpg"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-830, -350, 1810, 980));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tambahKeranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahKeranjangActionPerformed
        int selectedRow = tblMenu.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih menu terlebih dahulu!");
            return;
        }

        String jumlahInput = JOptionPane.showInputDialog("Masukkan jumlah:");
        if (jumlahInput == null || jumlahInput.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Jumlah tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int jumlah = Integer.parseInt(jumlahInput);
            if (jumlah <= 0) {
                JOptionPane.showMessageDialog(this, "Jumlah harus lebih dari 0!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Ambil data dari tabel menu
            String namaMenu = (String) tblMenu.getValueAt(selectedRow, 0);
            String kategori = (String) tblMenu.getValueAt(selectedRow, 1);
            double harga = (double) tblMenu.getValueAt(selectedRow, 2);
            
            // Hitung subtotal
            double subtotal = harga * jumlah;

            // Tambahkan ke keranjang
            keranjangModel.addRow(new Object[]{namaMenu, kategori, harga, jumlah, subtotal});

            // Update total harga
            totalHarga += subtotal;
            txtTotalHarga.setText(String.format("%.2f", totalHarga));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tambahKeranjangActionPerformed

    private void konfirmasiOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_konfirmasiOrderActionPerformed
        if (keranjangModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Keranjang masih kosong! Tambahkan menu terlebih dahulu.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            OrderDAO orderDAO = new OrderDAO();
            DetailOrderDAO detailOrderDAO = new DetailOrderDAO();
            MenuDAO menuDAO = new MenuDAO();

            // Buat objek Order untuk memperbarui total harga
            Order order = new Order();
            order.setId(orderId);
            order.setTotalHarga(totalHarga);

            // Loop melalui keranjang untuk memproses setiap item
            for (int i = 0; i < keranjangModel.getRowCount(); i++) {
                String namaMenu = keranjangModel.getValueAt(i, 0).toString();
                String kategori = keranjangModel.getValueAt(i, 1).toString();

                // Gunakan fungsi `getIdByName` untuk mendapatkan ID menu
                int menuId = menuDAO.getIdByName(namaMenu, kategori);
                if (menuId == -1) {
                    JOptionPane.showMessageDialog(this, "Menu tidak ditemukan: " + namaMenu, "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                // Buat objek DetailOrder
                DetailOrder detailOrder = new DetailOrder();
                detailOrder.setIdOrder(orderId);
                detailOrder.setIdMenu(menuId);
                detailOrder.setJumlah(Integer.parseInt(keranjangModel.getValueAt(i, 3).toString()));
                detailOrder.setSubtotal(Double.parseDouble(keranjangModel.getValueAt(i, 4).toString()));

                // Simpan detail order ke database
                detailOrderDAO.addDetail(detailOrder);
            }

            // Perbarui total harga pesanan di database
            orderDAO.updateTotalHarga(order);

            JOptionPane.showMessageDialog(this, "Pesanan berhasil disimpan!");
            this.dispose();
            new Dashboard().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyimpan pesanan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_konfirmasiOrderActionPerformed

    private void hapusKeranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusKeranjangActionPerformed
        int selectedRow = tblKeranjang.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih menu di keranjang untuk dihapus!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double subtotal = (double) tblKeranjang.getValueAt(selectedRow, 4);
        totalHarga -= subtotal;

        keranjangModel.removeRow(selectedRow);
        txtTotalHarga.setText(String.valueOf(totalHarga));
    }//GEN-LAST:event_hapusKeranjangActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        new Dashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_CancelActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancel;
    private javax.swing.JButton hapusKeranjang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton konfirmasiOrder;
    private javax.swing.JLabel masterOrder;
    private javax.swing.JButton tambahKeranjang;
    private javax.swing.JTable tblKeranjang;
    private javax.swing.JTable tblMenu;
    private javax.swing.JTextField txtOrderId;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTanggalOrder;
    private javax.swing.JTextField txtTotalHarga;
    // End of variables declaration//GEN-END:variables
}
