
ALTER TABLE `production` ADD `type` VARCHAR(5) NOT NULL AFTER `status`;
ALTER TABLE `production_waste` ADD `type` VARCHAR(10) NOT NULL AFTER `total_material_klem`;
ALTER TABLE `packing` ADD `type` VARCHAR(10) NOT NULL AFTER `status`;


CREATE TABLE `import_fingerprint` (
  `id` int(3) NOT NULL,
  `import_code` varchar(4) NOT NULL,
  `file_name` varchar(200) NOT NULL,
  `date` date NOT NULL,
  `upload_status` varchar(9) NOT NULL,
  `salary_status` varchar(12) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE `import_fingerprint` ADD PRIMARY KEY(`id`);
ALTER TABLE `import_fingerprint` CHANGE `id` `id` INT NOT NULL AUTO_INCREMENT;

ALTER TABLE `set_so_schedule_prod` ADD PRIMARY KEY(`id`);
ALTER TABLE `set_so_schedule_prod` CHANGE `id` `id` INT(12) NOT NULL AUTO_INCREMENT;


ALTER TABLE `set_so_schedule` ADD PRIMARY KEY(`id`);
ALTER TABLE `set_so_schedule` CHANGE `id` `id` INT(3) NOT NULL AUTO_INCREMENT;


CREATE TABLE `completed_so_schedule` (
  `id` int(5) NOT NULL,
  `set_so_schedule_id` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
);

ALTER TABLE `completed_so_schedule` ADD PRIMARY KEY(`id`);
ALTER TABLE `completed_so_schedule` CHANGE `id` `id` INT(5) NOT NULL AUTO_INCREMENT;


ALTER TABLE `stock_opname` ADD PRIMARY KEY(`id`);
ALTER TABLE `stock_opname` CHANGE `id` `id` INT(12) NOT NULL AUTO_INCREMENT;

ALTER TABLE `stock_opname_prod` ADD PRIMARY KEY(`id`);
ALTER TABLE `stock_opname_prod` CHANGE `id` `id` INT(12) NOT NULL AUTO_INCREMENT;


--master shift
ALTER TABLE `shift` ADD `type` VARCHAR(10) NOT NULL AFTER `shift_name`;

CREATE TABLE `shift_detail` (
  `id` int(5) NOT NULL,
  `shift_id` int NOT NULL,
  `day` varchar(200) NOT NULL,
  `week` int NOT NULL,
  `in` varchar(10) NOT NULL,  
  `out` varchar(10) NOT NULL,
  `holiday` varchar(1) NOT NULL,
  `rest` int NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
);

ALTER TABLE `shift_detail` ADD PRIMARY KEY(`id`);
ALTER TABLE `shift_detail` CHANGE `id` `id` INT(5) NOT NULL AUTO_INCREMENT;
ALTER TABLE `shift` CHANGE `shift_code` `shift_code` VARCHAR(25) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL;
ALTER TABLE `shift` CHANGE `shift_name` `shift_name` VARCHAR(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL;



--attendance

ALTER TABLE `attendance` ADD `input_date` DATE NOT NULL AFTER `role`, ADD `inputed_by` VARCHAR(100) NOT NULL AFTER `input_date`, ADD `edit_date` DATE NOT NULL AFTER `inputed_by`, ADD `edited_by` VARCHAR(100) NOT NULL AFTER `edit_date`, ADD `delete_date` DATE NOT NULL AFTER `edited_by`, ADD `deleted_by` VARCHAR(100) NOT NULL AFTER `delete_date`;
ALTER TABLE `attendance` CHANGE `input_date` `input_date` DATE NULL, CHANGE `inputed_by` `inputed_by` VARCHAR(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL, CHANGE `edit_date` `edit_date` DATE NULL, CHANGE `edited_by` `edited_by` VARCHAR(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL, CHANGE `delete_date` `delete_date` DATE NULL, CHANGE `deleted_by` `deleted_by` VARCHAR(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL;
ALTER TABLE `import_fingerprint` DROP `import_code`, DROP `upload_status`, DROP `salary_status`;

--common config

CREATE TABLE common_config(
	id int AUTO_INCREMENT PRIMARY KEY,
    `key_config` varchar(100),
    `value_config` varchar(100)
);
INSERT INTO `common_config` (`id`, `key_config`, `value_config`) VALUES (NULL, 'salary_cycle', '20');

ALTER TABLE `attendance_log` CHANGE `nik` `nik` VARCHAR(10) NULL DEFAULT NULL, CHANGE `attendance_time` `attendance_time` VARCHAR(5) NULL DEFAULT NULL, CHANGE `attendance_out` `attendance_out` VARCHAR(5) NULL DEFAULT NULL, CHANGE `shift_in` `shift_in` VARCHAR(5) NULL DEFAULT NULL, CHANGE `shift_out` `shift_out` VARCHAR(5) NULL DEFAULT NULL;
ALTER TABLE `shift_detail` CHANGE `in` `in` VARCHAR(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL, CHANGE `out` `out` VARCHAR(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL;
ALTER TABLE `shift` CHANGE `shift_code` `shift_code` VARCHAR(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL;

create table shift_department(
	id int primary key AUTO_INCREMENT,
    shift_id int,
    dept_id int
);
INSERT INTO `shift_department` (`id`, `shift_id`, `dept_id`) VALUES (NULL, '1', '1'), (NULL, '2', '2'), (NULL, '3', '3');

-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 28, 2017 at 12:56 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project`
--

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `cust_addr_id` int(11) NOT NULL,
  `currency_id` int(11) NOT NULL,
  `freight_cost_currency_id` int(11) NOT NULL,
  `insurance_cost_currency_id` int(11) NOT NULL,
  `po_no` varchar(255) DEFAULT NULL,
  `po_date` date DEFAULT NULL,
  `so_no` varchar(18) NOT NULL,
  `so_date` date NOT NULL,
  `surcharge` double DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `freight_cost` double NOT NULL,
  `insurance_cost` double NOT NULL,
  `vat` double NOT NULL,
  `description` text,
  `delete_reason` varchar(255) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  `input_by` varchar(25) NOT NULL,
  `input_date` date NOT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sales`
--


--
-- Indexes for dumped tables
--

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `cust_addr_id` (`cust_addr_id`),
  ADD KEY `currency_id` (`currency_id`),
  ADD KEY `freight_cost_currency_id` (`freight_cost_currency_id`),
  ADD KEY `insurance_cost_currency_id` (`insurance_cost_currency_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `sales`
--
ALTER TABLE `sales`
  ADD CONSTRAINT `sales_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `sales_ibfk_2` FOREIGN KEY (`cust_addr_id`) REFERENCES `cust_addr` (`id`),
  ADD CONSTRAINT `sales_ibfk_3` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`id`),
  ADD CONSTRAINT `sales_ibfk_4` FOREIGN KEY (`freight_cost_currency_id`) REFERENCES `currency` (`id`),
  ADD CONSTRAINT `sales_ibfk_5` FOREIGN KEY (`insurance_cost_currency_id`) REFERENCES `currency` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 28, 2017 at 12:57 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project`
--

-- --------------------------------------------------------

--
-- Table structure for table `sales_detail`
--

CREATE TABLE `sales_detail` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `sales_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `nett_price` double NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `input_date` date NOT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sales_detail`
--


--
-- Indexes for dumped tables
--

--
-- Indexes for table `sales_detail`
--
ALTER TABLE `sales_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `sales_id` (`sales_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `sales_detail`
--
ALTER TABLE `sales_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `sales_detail`
--
ALTER TABLE `sales_detail`
  ADD CONSTRAINT `sales_detail_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

/*
SQLyog Enterprise - MySQL GUI v8.05 
MySQL - 5.6.24 : Database - julia4
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;



/*Table structure for table `bank_cust` */

CREATE TABLE `bank_cust` (
  `id` int(3) NOT NULL,
  `cust_id` int(5) DEFAULT NULL,
  `swiftcode` varchar(15) NOT NULL,
  `bankname` varchar(50) NOT NULL,
  `accountno` varchar(25) DEFAULT NULL,
  `currency_id` varchar(5) DEFAULT NULL,
  `accname` varchar(50) DEFAULT NULL,
  `note` varchar(25) DEFAULT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `input_by` (`input_by`),
  KEY `edited_by` (`edited_by`),
  KEY `deleted_by` (`deleted_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bank_cust` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;


