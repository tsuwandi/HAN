-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 17, 2016 at 08:43 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `projectss`
--

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE IF NOT EXISTS `department` (
  `department_id` varchar(10) NOT NULL,
  `department_name` varchar(20) DEFAULT NULL,
  `division_id` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`department_id`, `department_name`, `division_id`) VALUES
('DEPT0001', 'Departemen 1', 'DIV0001');

-- --------------------------------------------------------

--
-- Table structure for table `division`
--

CREATE TABLE IF NOT EXISTS `division` (
  `division_id` varchar(10) NOT NULL,
  `division_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`division_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `division`
--

INSERT INTO `division` (`division_id`, `division_name`) VALUES
('DIV0001', 'Divisi 1');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `employee_id` varchar(10) NOT NULL,
  `employee_name` varchar(50) DEFAULT NULL,
  `employee_type_id` varchar(10) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `hire_date` date DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone_number` varchar(12) DEFAULT NULL,
  `salary` int(11) DEFAULT NULL,
  `department_id` varchar(10) DEFAULT NULL,
  `gender_id` varchar(10) DEFAULT NULL,
  `position_id` varchar(10) DEFAULT NULL,
  `marital_id` varchar(10) DEFAULT NULL,
  `employee_status_id` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employee_id`, `employee_name`, `employee_type_id`, `address`, `city`, `birth_date`, `hire_date`, `email`, `phone_number`, `salary`, `department_id`, `gender_id`, `position_id`, `marital_id`, `employee_status_id`) VALUES
('2013026132', 'Irvan Wilianto', 'EMPTYP01', 'Harapan Indah', 'Bekasi', '1991-08-14', '2013-12-02', 'irvan.wili@gmail.com', '081288370090', 10000000, 'DEPT0001', '1', 'POS0001', '1', '1');

-- --------------------------------------------------------

--
-- Table structure for table `employee_status`
--

CREATE TABLE IF NOT EXISTS `employee_status` (
  `employee_status_id` varchar(10) NOT NULL,
  `employee_status_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`employee_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee_status`
--

INSERT INTO `employee_status` (`employee_status_id`, `employee_status_name`) VALUES
('1', 'Aktif'),
('2', 'Non Aktif');

-- --------------------------------------------------------

--
-- Table structure for table `employee_type`
--

CREATE TABLE IF NOT EXISTS `employee_type` (
  `employee_type_id` varchar(10) NOT NULL,
  `employee_type_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`employee_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee_type`
--

INSERT INTO `employee_type` (`employee_type_id`, `employee_type_name`) VALUES
('EMPTYP01', 'Employee Type 1'),
('EMPTYP02', 'Employee Type 2');

-- --------------------------------------------------------

--
-- Table structure for table `gender`
--

CREATE TABLE IF NOT EXISTS `gender` (
  `gender_id` varchar(1) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`gender_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gender`
--

INSERT INTO `gender` (`gender_id`, `name`) VALUES
('1', 'Laki-laki'),
('2', 'Perempuan');

-- --------------------------------------------------------

--
-- Table structure for table `marital`
--

CREATE TABLE IF NOT EXISTS `marital` (
  `marital_id` varchar(10) NOT NULL,
  `marital_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`marital_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `marital`
--

INSERT INTO `marital` (`marital_id`, `marital_name`) VALUES
('1', 'Lajang'),
('2', 'Menikah');

-- --------------------------------------------------------

--
-- Table structure for table `ms_position`
--

CREATE TABLE IF NOT EXISTS `ms_position` (
  `position_id` varchar(10) NOT NULL,
  `position_name` varchar(20) DEFAULT NULL,
  `min_salary` int(11) DEFAULT NULL,
  `max_salary` int(11) DEFAULT NULL,
  `department_id` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`position_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ms_position`
--

INSERT INTO `ms_position` (`position_id`, `position_name`, `min_salary`, `max_salary`, `department_id`) VALUES
('POS0001', 'Posisi 1', 5000000, 10000000, 'DEPT0001');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `employee_id` varchar(15) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `phone_no` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`employee_id`, `name`, `address`, `phone_no`, `email`, `password`) VALUES
('2013026132', 'Irvan Wilianto', 'Harapan Indah', '081288370090', 'irvan.wili@gmail.com', 'e4a4109fe0788b34f1cc8e0413c76cb6'),
('2013034022', 'Timotius Suwandi', 'Jalan Duyung 2 No. 6', '081807919152', 'timotius.suwandi@gmail.com', 'c4a66d36a9b756f36eecd8b54f28f440'),
('sfasfafsa', 'sfasfasfa', 'sfasfasfasfasfa', 'sfasfasfa', 'sfasfa', '411628f225595c8b46097e93b4833c58');

-- --------------------------------------------------------

--
-- Struktur dari tabel `bank`
--

CREATE TABLE IF NOT EXISTS `bank` (
  `id` int(3) NOT NULL,
  `bank_abbr` varchar(4) NOT NULL,
  `bank` varchar(50) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `bank`
--

INSERT INTO `bank` (`id`, `bank_abbr`, `bank`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'BCA', 'Bank Central Asia', '2016-04-16', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `city`
--

CREATE TABLE IF NOT EXISTS `city` (
  `id` int(3) NOT NULL,
  `city` varchar(150) NOT NULL,
  `province_id` int(3) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `city`
--

INSERT INTO `city` (`id`, `city`, `province_id`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'Jakarta Utara', 1, '2016-04-16', 'timotius', NULL, NULL, NULL, NULL),
(2, 'Jakarta Pusat', 1, '2016-04-16', '', NULL, NULL, NULL, NULL),
(3, 'Jakarta Timur', 1, '2016-04-16', 'timotius', NULL, NULL, NULL, NULL),
(4, 'Jakarta Barat', 1, '2016-04-16', 'timotius', NULL, NULL, NULL, NULL),
(5, 'Jakarta Selatan', 1, '2016-04-16', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `currency`
--

CREATE TABLE IF NOT EXISTS `currency` (
  `id` int(3) NOT NULL,
  `currency_abbr` varchar(3) NOT NULL,
  `currency_symbol` varchar(1) NOT NULL,
  `currency` varchar(50) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `currency`
--

INSERT INTO `currency` (`id`, `currency_abbr`, `currency_symbol`, `currency`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'USD', '$', 'US Dollar', '2016-04-16', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `province`
--

CREATE TABLE IF NOT EXISTS `province` (
  `id` int(3) NOT NULL,
  `province` varchar(150) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `province`
--

INSERT INTO `province` (`id`, `province`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'DKI Jakarta', '2016-04-16', 'timotius', NULL, NULL, NULL, NULL),
(2, 'Jawa Barat', '2016-04-16', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `supplier`
--

CREATE TABLE IF NOT EXISTS `supplier` (
  `id` int(3) NOT NULL,
  `supp_code` varchar(9) NOT NULL,
  `supp_name` varchar(200) NOT NULL,
  `pt` varchar(200) NOT NULL,
  `npwp` varchar(30) NOT NULL,
  `supp_type_id` int(3) NOT NULL,
  `supp_status` varchar(30) NOT NULL,
  `default_tax` decimal(5,2) NOT NULL,
  `account_no` varchar(30) NOT NULL,
  `bank_id` int(3) NOT NULL,
  `account_name` varchar(30) NOT NULL,
  `currency_id` int(3) NOT NULL,
  `top` int(3) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `supplier`
--

INSERT INTO `supplier` (`id`, `supp_code`, `supp_name`, `pt`, `npwp`, `supp_type_id`, `supp_status`, `default_tax`, `account_no`, `bank_id`, `account_name`, `currency_id`, `top`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(3, 'ASC000001', 'adsa', 'dsda', 'sadsad', 2, 'Nonaktif', 101, '324342', 1, 'dsfdfs', 1, 101, '2016-04-17', 'timotius', '2016-04-17', 'timotius', '2016-04-17', 'timotius'),
(4, 'ABS000001', 'PT ABS', 'ABS', '123456789', 1, 'Nonaktif Sementara', 10, '', 1, '', 1, 10, '2016-04-17', 'timotius', '2016-04-17', 'timotius', NULL, NULL),
(5, 'TEST', 'TEST', 'TEST', 'TEST', 1, 'Aktif', 1, 'TEST', 1, 'TEST', 1, 1, '2016-04-17', 'timotius', '2016-04-17', 'timotius', NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `supp_address`
--

CREATE TABLE IF NOT EXISTS `supp_address` (
  `id` int(9) NOT NULL,
  `supp_code` varchar(9) NOT NULL,
  `address_type` varchar(20) NOT NULL,
  `address` varchar(200) NOT NULL,
  `zip_code` varchar(5) NOT NULL,
  `city_id` int(9) DEFAULT NULL,
  `phone` varchar(15) NOT NULL,
  `fax` varchar(15) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `supp_address`
--

INSERT INTO `supp_address` (`id`, `supp_code`, `address_type`, `address`, `zip_code`, `city_id`, `phone`, `fax`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(14, 'TEST', 'Billing', 'TEST', 'TEST', 3, 'TEST', 'TEST', '2016-04-17', 'timotius', '2016-04-17', 'timotius', NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `supp_cp`
--

CREATE TABLE IF NOT EXISTS `supp_cp` (
  `id` int(3) NOT NULL,
  `supp_code` varchar(9) NOT NULL,
  `name` varchar(200) NOT NULL,
  `department` varchar(100) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `supp_cp`
--

INSERT INTO `supp_cp` (`id`, `supp_code`, `name`, `department`, `phone`, `email`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(8, 'TEST', 'TEST1', 'TEST1', 'TEST1', 'TEST@TEST.COM', '2016-04-17', 'timotius', '2016-04-17', 'timotius', NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `supp_type`
--

CREATE TABLE IF NOT EXISTS `supp_type` (
  `id` int(3) NOT NULL,
  `supp_type` varchar(50) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `supp_type`
--

INSERT INTO `supp_type` (`id`, `supp_type`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'Bahan Baku', '2016-04-16', 'timotius', NULL, NULL, NULL, NULL),
(2, 'Barang Jadi', '2016-04-16', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `supp_vehicle`
--

CREATE TABLE IF NOT EXISTS `supp_vehicle` (
  `id` int(3) NOT NULL,
  `license_plate` varchar(10) NOT NULL,
  `supp_code` varchar(9) NOT NULL,
  `vehicle_type_id` int(3) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `supp_vehicle`
--

INSERT INTO `supp_vehicle` (`id`, `license_plate`, `supp_code`, `vehicle_type_id`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(6, 'asd', 'ASC000001', 1, '2016-04-17', 'timotius', '2016-04-17', 'timotius', '2016-04-17', 'timotius'),
(10, '123', 'ASC000001', 2, '2016-04-17', 'timotius', NULL, NULL, '2016-04-17', 'timotius'),
(11, 'TEST', 'TEST', 1, '2016-04-17', 'timotius', '2016-04-17', 'timotius', NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(50) NOT NULL,
  `emp_code` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `vehicle_type`
--

CREATE TABLE IF NOT EXISTS `vehicle_type` (
  `id` int(3) NOT NULL,
  `vehicle_type` varchar(50) NOT NULL,
  `capacity` int(10) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `vehicle_type`
--

INSERT INTO `vehicle_type` (`id`, `vehicle_type`, `capacity`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'Mobil', 1500, '2016-04-16', 'timotius', NULL, NULL, NULL, NULL),
(2, 'Truk', 2000, '2016-04-16', 'timotius', NULL, NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bank`
--
ALTER TABLE `bank`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`);

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`);

--
-- Indexes for table `currency`
--
ALTER TABLE `currency`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`);

--
-- Indexes for table `province`
--
ALTER TABLE `province`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `supp_code_2` (`supp_code`), ADD KEY `supp_type_id` (`supp_type_id`), ADD KEY `bank_id` (`bank_id`), ADD KEY `supp_code` (`supp_code`), ADD KEY `input_by` (`input_by`,`edited_by`,`deleted_by`);

--
-- Indexes for table `supp_address`
--
ALTER TABLE `supp_address`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`), ADD KEY `supp_code` (`supp_code`);

--
-- Indexes for table `supp_cp`
--
ALTER TABLE `supp_cp`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`), ADD KEY `supp_code` (`supp_code`);

--
-- Indexes for table `supp_type`
--
ALTER TABLE `supp_type`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`);

--
-- Indexes for table `supp_vehicle`
--
ALTER TABLE `supp_vehicle`
  ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `license_plate` (`license_plate`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`), ADD KEY `supp_code` (`supp_code`), ADD KEY `vehicle_type_id` (`vehicle_type_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `emp_code` (`emp_code`);

--
-- Indexes for table `vehicle_type`
--
ALTER TABLE `vehicle_type`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bank`
--
ALTER TABLE `bank`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `city`
--
ALTER TABLE `city`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `currency`
--
ALTER TABLE `currency`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `province`
--
ALTER TABLE `province`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `supp_address`
--
ALTER TABLE `supp_address`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `supp_cp`
--
ALTER TABLE `supp_cp`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `supp_type`
--
ALTER TABLE `supp_type`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `supp_vehicle`
--
ALTER TABLE `supp_vehicle`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `vehicle_type`
--
ALTER TABLE `vehicle_type`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `supp_address`
--
ALTER TABLE `supp_address`
ADD CONSTRAINT `supp_address_ibfk_1` FOREIGN KEY (`supp_code`) REFERENCES `supplier` (`supp_code`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


--
-- Struktur dari tabel `chamber`
--

CREATE TABLE IF NOT EXISTS `chamber` (
  `id` int(5) NOT NULL,
  `chamber` varchar(50) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Struktur dari tabel `dry_in`
--

CREATE TABLE IF NOT EXISTS `dry_in` (
  `id` int(12) NOT NULL,
  `dry_in_code` varchar(14) NOT NULL,
  `date_in` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `chamber_id` int(5) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `dry_in_pallet`
--

CREATE TABLE IF NOT EXISTS `dry_in_pallet` (
  `id` int(12) NOT NULL,
  `dry_in_code` varchar(14) NOT NULL,
  `pallet_card_code` varchar(21) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `pic_tally` (
  `id` int(12) NOT NULL,
  `dry_in_code` varchar(14) NOT NULL,
  `emp_code` varchar(10) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `dry_out`
--

CREATE TABLE IF NOT EXISTS `dry_out` (
  `id` int(12) NOT NULL,
  `dry_out_code` varchar(14) NOT NULL,
  `date_out` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `chamber_id` int(5) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `dry_out_pallet`
--

CREATE TABLE IF NOT EXISTS `dry_out_pallet` (
  `id` int(12) NOT NULL,
  `dry_out_code` varchar(14) NOT NULL,
  `pallet_card_code` varchar(21) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `pallet_card` (
  `id` int(12) NOT NULL,
  `pallet_card_code` varchar(21) NOT NULL,
  `received_code` varchar(16) NOT NULL,
  `emp_code` varchar(10) NOT NULL,
  `grade_id` int(3) NOT NULL,
  `total_volume` decimal(7,2) NOT NULL DEFAULT '0.00',
  `total_log` int(5) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--
-- Indexes for dumped tables
--


--
-- Table structure for table `wood_resource`
--

CREATE TABLE IF NOT EXISTS `wood_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wood_resource` varchar(20) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `wood_type`
--

CREATE TABLE IF NOT EXISTS `wood_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wood_type` varchar(50) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

CREATE TABLE IF NOT EXISTS `delivery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `delivery_note` int(11) NOT NULL,
  `wood_domicile` varchar(50) NOT NULL,
  `wood_resource_id` int(11) NOT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  PRIMARY KEY (`id`,`delivery_note`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--


--
-- Table structure for table `grade`
--

CREATE TABLE IF NOT EXISTS `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `grade`
--

INSERT INTO `grade` (`id`, `grade`) VALUES
(1, 'A'),
(2, 'B');

-- --------------------------------------------------------


--
-- Indexes for table `chamber`
--
ALTER TABLE `chamber`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`);

--
-- Indexes for table `dry_in`
--
ALTER TABLE `dry_in`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`), ADD KEY `chamber_id` (`chamber_id`);

--
-- Indexes for table `dry_in_pallet`
--
ALTER TABLE `dry_in_pallet`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`), ADD KEY `dry_in_code` (`dry_in_code`), ADD KEY `pallet_card_code` (`pallet_card_code`);

--
-- Indexes for table `dry_out`
--
ALTER TABLE `dry_out`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`), ADD KEY `chamber_id` (`chamber_id`);

--
-- Indexes for table `dry_out_pallet`
--
ALTER TABLE `dry_out_pallet`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`), ADD KEY `dry_in_code` (`dry_out_code`), ADD KEY `pallet_card_code` (`pallet_card_code`);

--
-- Indexes for table `pic_tally`
--
ALTER TABLE `pic_tally`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`), ADD KEY `dry_in_code` (`dry_in_code`), ADD KEY `emp_code` (`emp_code`);

ALTER TABLE `pallet_card`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`);

--
-- AUTO_INCREMENT for table `dry_in`
--
ALTER TABLE `dry_in`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `dry_in_pallet`
--
ALTER TABLE `dry_in_pallet`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `dry_out`
--
ALTER TABLE `dry_out`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `dry_out_pallet`
--
ALTER TABLE `dry_out_pallet`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT;
  
--
-- AUTO_INCREMENT for table `pic_tally`
--
ALTER TABLE `pic_tally`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT;

ALTER TABLE `pallet_card`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chamber`
--
ALTER TABLE `chamber`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

CREATE TABLE thickness(
id	int(3) PRIMARY KEY ,
thickness	dec (7,2),
input_date	date,
input_by	varchar(25),
edit_date	date,
edited_by	varchar(25),
deleted_date	date,
deleted_by	varchar(25)
);

ALTER TABLE  `thickness` CHANGE  `id`  `id` INT( 3 ) NOT NULL AUTO_INCREMENT;

INSERT INTO `project`.`wood_type` (`id`, `wood_type`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES (NULL, 'Balken A', NULL, NULL, NULL, NULL, NULL, NULL), (NULL, 'Balken B', NULL, NULL, NULL, NULL, NULL, NULL);

ALTER TABLE `delivery` CHANGE `delivery_note` `delivery_note` VARCHAR(10) NOT NULL;


INSERT INTO `project`.`delivery` (`id`, `delivery_note`, `wood_domicile`, `wood_resource_id`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `input_date`) VALUES (NULL, '112131231', 'Hong Kong Forest', '1', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO `project`.`delivery` (`id`, `delivery_note`, `wood_domicile`, `wood_resource_id`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `input_date`) VALUES (NULL, '10329293', 'Tian Liu', '1', NULL, NULL, NULL, NULL, NULL, NULL); 

INSERT INTO `project`.`wood_resource` (`id`, `wood_resource`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES (NULL, 'Mangrove', NULL, NULL, NULL, NULL, NULL, NULL);


INSERT INTO `project`.`thickness` (`id`, `thickness`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES (NULL, '5', NULL, NULL, NULL, NULL, NULL, NULL), (NULL, '8', NULL, NULL, NULL, NULL, NULL, NULL);

-- Table structure for table `product` -- 
CREATE TABLE IF NOT EXISTS `product` ( `id` int(11) NOT NULL AUTO_INCREMENT, `product_code` varchar(20) NOT NULL DEFAULT '', `product_name` varchar(50) DEFAULT NULL, `product_category_id` int(11) DEFAULT NULL, `product_status` varchar(50) DEFAULT NULL, `product_uom_id` int(11) DEFAULT NULL, `is_maintain_stock` int(11) DEFAULT NULL, `image_path` varchar(200) NOT NULL DEFAULT '', `brand` varchar(50) DEFAULT NULL, `barcode` varchar(50) DEFAULT NULL, `description` varchar(200) DEFAULT NULL, `wood_type_id` int(11) DEFAULT NULL, `grade_id` int(11) DEFAULT NULL, `thickness_id` int(11) DEFAULT NULL, `condition_id` int(11) DEFAULT NULL, `is_has_serial` int(11) DEFAULT NULL, `is_fixed_asset` int(11) DEFAULT NULL, `warranty` int(11) DEFAULT NULL, `netto` decimal(7,2) DEFAULT NULL, `netto_uom_id` int(11) DEFAULT NULL, `is_purchase_item` int(11) DEFAULT NULL, `minor` int(11) DEFAULT NULL, `minor_uom_id` int(3) DEFAULT NULL, `lead_time` int(3) DEFAULT NULL, `buy_cost_center_id` int(5) DEFAULT NULL, `expense_acc_id` int(9) DEFAULT NULL, `main_supp_code` varchar(9) DEFAULT NULL, `manufacturer` varchar(50) DEFAULT NULL, `is_sales_item` int(1) DEFAULT NULL, `is_service_item` int(1) DEFAULT NULL, `sell_cost_center_id` int(5) DEFAULT NULL, `income_acc_id` int(9) DEFAULT NULL, `max_disc` decimal(5,2) DEFAULT NULL, `input_date` date DEFAULT NULL, `input_by` varchar(25) DEFAULT NULL, `edit_date` date DEFAULT NULL, `edited_by` varchar(25) DEFAULT NULL, `deleted_date` date DEFAULT NULL, `deleted_by` varchar(25) DEFAULT NULL, PRIMARY KEY (`id`,`product_code`) ) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ; -- 

-- -- Table structure for table `received` -- 
CREATE TABLE IF NOT EXISTS `received` ( `id` int(11) NOT NULL AUTO_INCREMENT, `received_code` varchar(16) NOT NULL, `received_date` date NOT NULL, `rit_no` varchar(4) NOT NULL, `license_plate` varchar(10) NOT NULL, `driver` varchar(50) NOT NULL, `delivery_note` varchar(20) NOT NULL, `wood_type_id` int(11) NOT NULL, `input_date` date DEFAULT NULL, `input_by` varchar(25) DEFAULT NULL, `edit_date` date DEFAULT NULL, `edited_by` varchar(25) DEFAULT NULL, `deleted_date` date DEFAULT NULL, `deleted_by` varchar(25) DEFAULT NULL, PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

INSERT INTO `project`.`product` (`id`, `product_code`, `product_name`, `product_category_id`, `product_status`, `product_uom_id`, `is_maintain_stock`, `image_path`, `brand`, `barcode`, `description`, `wood_type_id`, `grade_id`, `thickness_id`, `condition_id`, `is_has_serial`, `is_fixed_asset`, `warranty`, `netto`, `netto_uom_id`, `is_purchase_item`, `minor`, `minor_uom_id`, `lead_time`, `buy_cost_center_id`, `expense_acc_id`, `main_supp_code`, `manufacturer`, `is_sales_item`, `is_service_item`, `sell_cost_center_id`, `income_acc_id`, `max_disc`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES (NULL, 'P0001', 'Balken Excellent Quality', '1', 'Basah', '1', NULL, '', NULL, NULL, NULL, '1', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO `project`.`employee` (`employee_id`, `employee_name`, `employee_type_id`, `address`, `city`, `birth_date`, `hire_date`, `email`, `phone_number`, `salary`, `department_id`, `gender_id`, `position_id`, `marital_id`, `employee_status_id`) VALUES ('', 'Vinci', 'EMP0002', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'POS0002', NULL, NULL);

 -- Table structure for table `pic_docking`
 CREATE TABLE IF NOT EXISTS `pic_docking` ( `id` int(11) NOT NULL AUTO_INCREMENT, `received_code` varchar(16) NOT NULL, `emp_code` varchar(10) NOT NULL, `input_date` date DEFAULT NULL, `input_by` varchar(25) DEFAULT NULL, `edit_date` date DEFAULT NULL, `edited_by` varchar(25) DEFAULT NULL, `deleted_date` date DEFAULT NULL, `deleted_by` varchar(25) DEFAULT NULL, PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ; -- 

 INSERT INTO `project`.`received` (`id`, `received_code`, `received_date`, `rit_no`, `license_plate`, `driver`, `delivery_note`, `wood_type_id`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES (NULL, '120930123', '2016-05-11', '1212', 'asd', 'Udin', '12312', '1', NULL, NULL, NULL, NULL, NULL, NULL);
 
ALTER TABLE  `received` ADD  `driver_id` VARCHAR( 50 ) NOT NULL AFTER  `driver`;

ALTER TABLE  `received` ADD  `received_status` VARCHAR( 50 ) NOT NULL AFTER  `wood_type_id`;