-- Tabel user
CREATE TABLE user (
	id int(11) AUTO_INCREMENT PRIMARY KEY,
	username varchar(50) NOT NULL,
	password varchar(255) NOT NULL
);

-- Tabel kategori
CREATE TABLE kategori (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama_kategori VARCHAR(100) NOT NULL
);

-- Tabel menu
CREATE TABLE menu (
    id INT AUTO_INCREMENT PRIMARY KEY,
	id_kategori INT NOT NULL,
    nama VARCHAR(255) NOT NULL,
    harga DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_kategori) REFERENCES kategori(id) ON DELETE CASCADE
);

-- Tabel order
CREATE TABLE `order` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tanggal_order DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL,
    total_harga DOUBLE NOT NULL
);

-- Tabel detail_order
CREATE TABLE detail_order (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_order INT NOT NULL,
    id_menu INT NOT NULL,
    jumlah INT NOT NULL,
    subtotal DOUBLE NOT NULL DEFAULT 0.0,
    FOREIGN KEY (id_order) REFERENCES `order`(id) ON DELETE CASCADE,
    FOREIGN KEY (id_menu) REFERENCES menu(id) ON DELETE CASCADE
);

-- Tabel transaksi
CREATE TABLE transaksi (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_order INT NOT NULL,
    tanggal_transaksi DATETIME NOT NULL,
    total_pembayaran DOUBLE NOT NULL,
    metode_pembayaran VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_order) REFERENCES `order`(id) ON DELETE CASCADE
);