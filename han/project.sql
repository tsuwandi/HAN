-- phpMyAdmin SQL Dump
-- version 4.0.10.14
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Jun 09, 2016 at 08:56 PM
-- Server version: 5.5.49-cll
-- PHP Version: 5.4.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `onesolut_HAN`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `acc_no` varchar(50) NOT NULL,
  `account` varchar(50) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(25) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`,`acc_no`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `bank`
--

CREATE TABLE IF NOT EXISTS `bank` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `bank_abbr` varchar(4) NOT NULL,
  `bank` varchar(50) NOT NULL,
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `bank`
--

INSERT INTO `bank` (`id`, `bank_abbr`, `bank`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'BCA', 'Bank Central Asia', '2016-04-16', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `chamber`
--

CREATE TABLE IF NOT EXISTS `chamber` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `chamber` varchar(50) NOT NULL,
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `chamber`
--

INSERT INTO `chamber` (`id`, `chamber`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'OVEN 1', '0000-00-00', 'admin', NULL, NULL, NULL, NULL),
(2, 'OVEN 2', '0000-00-00', 'admin', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE IF NOT EXISTS `city` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `city` varchar(150) NOT NULL,
  `province_id` int(3) NOT NULL,
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`id`, `city`, `province_id`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'Jakarta Utara', 1, '2016-04-16', 'timotius', NULL, NULL, NULL, NULL),
(2, 'Jakarta Pusat', 1, '2016-04-16', '', NULL, NULL, NULL, NULL),
(3, 'Jakarta Timur', 1, '2016-04-16', 'timotius', NULL, NULL, NULL, NULL),
(4, 'Jakarta Barat', 1, '2016-04-16', 'timotius', NULL, NULL, NULL, NULL),
(5, 'Jakarta Selatan', 1, '2016-04-16', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `condition`
--

CREATE TABLE IF NOT EXISTS `condition` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `condition` varchar(50) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(25) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `cost_center`
--

CREATE TABLE IF NOT EXISTS `cost_center` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `cost_center` varchar(50) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(25) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `currency`
--

CREATE TABLE IF NOT EXISTS `currency` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `currency_abbr` varchar(3) NOT NULL,
  `currency_symbol` varchar(1) NOT NULL,
  `currency` varchar(50) NOT NULL,
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `currency`
--

INSERT INTO `currency` (`id`, `currency_abbr`, `currency_symbol`, `currency`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'USD', '$', 'US Dollar', '2016-04-16', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `delivery`
--

CREATE TABLE IF NOT EXISTS `delivery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `delivery_note` varchar(20) NOT NULL,
  `document_type_id` int(3) DEFAULT NULL,
  `wood_from` varchar(50) DEFAULT NULL,
  `wood_domicile` varchar(50) NOT NULL,
  `wood_resource_id` int(11) NOT NULL,
  `wood_type_id` int(3) DEFAULT NULL,
  `total_log` int(5) DEFAULT NULL,
  `total_volume` decimal(7,2) DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  PRIMARY KEY (`id`,`delivery_note`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `delivery`
--

INSERT INTO `delivery` (`id`, `delivery_note`, `document_type_id`, `wood_from`, `wood_domicile`, `wood_resource_id`, `wood_type_id`, `total_log`, `total_volume`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `input_date`) VALUES
(1, '123X_XYZ', 2, NULL, 'JAKARTA', 1, 1, 10, '1000.00', 'Michael', NULL, NULL, NULL, NULL, '2016-05-26');

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
-- Table structure for table `document_type`
--

CREATE TABLE IF NOT EXISTS `document_type` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `document_type` varchar(50) NOT NULL,
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `document_type`
--

INSERT INTO `document_type` (`id`, `document_type`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'Surat Jalan', '2016-04-16', 'timotius', NULL, NULL, NULL, NULL),
(2, 'Ijin Import', '2016-04-16', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `dry_in`
--

CREATE TABLE IF NOT EXISTS `dry_in` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `dry_in_code` varchar(14) NOT NULL,
  `date_in` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `chamber_id` int(5) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  `total_volume` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `input_by` (`input_by`),
  KEY `edited_by` (`edited_by`),
  KEY `deleted_by` (`deleted_by`),
  KEY `chamber_id` (`chamber_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `dry_in`
--

INSERT INTO `dry_in` (`id`, `dry_in_code`, `date_in`, `chamber_id`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `total_volume`) VALUES
(2, 'M/2016/05/0001', '2016-05-26 17:00:00', 1, '2016-05-27', 'timotius', NULL, NULL, NULL, NULL, '100.00');

-- --------------------------------------------------------

--
-- Table structure for table `dry_in_pallet`
--

CREATE TABLE IF NOT EXISTS `dry_in_pallet` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `dry_in_code` varchar(14) NOT NULL,
  `pallet_card_code` varchar(21) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `input_by` (`input_by`),
  KEY `edited_by` (`edited_by`),
  KEY `deleted_by` (`deleted_by`),
  KEY `dry_in_code` (`dry_in_code`),
  KEY `pallet_card_code` (`pallet_card_code`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `dry_in_pallet`
--

INSERT INTO `dry_in_pallet` (`id`, `dry_in_code`, `pallet_card_code`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'M/2016/05/0001', '1/0004/BL/26/05/16', '2016-05-27', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `dry_out`
--

CREATE TABLE IF NOT EXISTS `dry_out` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `dry_out_code` varchar(14) NOT NULL,
  `date_out` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `chamber_id` int(5) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  `total_volume` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `input_by` (`input_by`),
  KEY `edited_by` (`edited_by`),
  KEY `deleted_by` (`deleted_by`),
  KEY `chamber_id` (`chamber_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `dry_out_pallet`
--

CREATE TABLE IF NOT EXISTS `dry_out_pallet` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `dry_out_code` varchar(14) NOT NULL,
  `pallet_card_code` varchar(21) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `input_by` (`input_by`),
  KEY `edited_by` (`edited_by`),
  KEY `deleted_by` (`deleted_by`),
  KEY `dry_in_code` (`dry_out_code`),
  KEY `pallet_card_code` (`pallet_card_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

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
('2011111111', 'Iwan', 'POS0002', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'POS0002', NULL, NULL),
('2012345678', 'Mikael', 'EMPTYP01', 'Jatinegara', 'Jakarta', '2016-04-18', '2015-07-18', NULL, '123456798012', 0, 'DEPT0001', NULL, 'POS0001', '1', '1'),
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
('EMPTYP02', 'Employee Type 2'),
('POS0002', 'GRADER');

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
-- Table structure for table `grade`
--

CREATE TABLE IF NOT EXISTS `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade` varchar(50) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(25) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `grade`
--

INSERT INTO `grade` (`id`, `grade`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) VALUES
(1, 'A', NULL, NULL, NULL, NULL, NULL, NULL),
(2, 'B', NULL, NULL, NULL, NULL, NULL, NULL);

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
-- Table structure for table `ms_supplier`
--

CREATE TABLE IF NOT EXISTS `ms_supplier` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `supplier_id` varchar(9) DEFAULT NULL,
  `supplier_name` varchar(200) DEFAULT NULL,
  `supplier_code` varchar(50) DEFAULT NULL,
  `default_currency` int(11) DEFAULT NULL,
  `npwp` varchar(30) DEFAULT NULL,
  `default_tax` int(11) DEFAULT NULL,
  `pt` varchar(200) DEFAULT NULL,
  `credit_days` int(11) DEFAULT NULL,
  `supplier_type` int(3) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `is_deleted` int(8) DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  `last_edited` date DEFAULT NULL,
  `edited_by` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `ms_supplier`
--

INSERT INTO `ms_supplier` (`id`, `supplier_id`, `supplier_name`, `supplier_code`, `default_currency`, `npwp`, `default_tax`, `pt`, `credit_days`, `supplier_type`, `input_date`, `input_by`, `is_deleted`, `deleted_by`, `last_edited`, `edited_by`) VALUES
(1, '1', 'PT. ABC', 'ABC001', 1, '901.101.101.250', 10, 'PT ABC', 10, 1, '2016-04-12', NULL, 1, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pallet_card`
--

CREATE TABLE IF NOT EXISTS `pallet_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `received_detail_id` varchar(21) NOT NULL,
  `pallet_card_code` varchar(21) NOT NULL,
  `length` decimal(7,2) NOT NULL,
  `width` decimal(7,2) NOT NULL,
  `thickness` decimal(7,2) NOT NULL,
  `total` int(11) NOT NULL,
  `volume` int(11) NOT NULL,
  `product_code` varchar(20) NOT NULL,
  `description` varchar(500) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


--
-- Dumping data for table `pallet_card`
--


-- --------------------------------------------------------

--
-- Table structure for table `pallet_card_dtl`
--

CREATE TABLE IF NOT EXISTS `pallet_card_dtl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pallet_card_code` varchar(21) NOT NULL,
  `length` decimal(5,2) NOT NULL,
  `width` decimal(5,2) NOT NULL,
  `thickness` decimal(5,2) NOT NULL,
  `total` int(11) NOT NULL,
  `volume` int(11) NOT NULL,
  `product_code` varchar(20) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `pallet_card_dtl`
--

INSERT INTO `pallet_card_dtl` (`id`, `pallet_card_code`, `length`, `width`, `thickness`, `total`, `volume`, `product_code`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, '1/0004/BL/26/05/16', '10.00', '2.00', '10.00', 100, 200, '', '2016-05-27', 'Michael', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pic_docking`
--

CREATE TABLE IF NOT EXISTS `pic_docking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `received_code` varchar(16) NOT NULL,
  `emp_code` varchar(10) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `pic_docking`
--

INSERT INTO `pic_docking` (`id`, `received_code`, `emp_code`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, '0004/BL/26/05/16', '2012345678', '2016-05-27', 'Michael', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pic_tally`
--

CREATE TABLE IF NOT EXISTS `pic_tally` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `dry_in_code` varchar(14) NOT NULL,
  `emp_code` varchar(10) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `input_by` (`input_by`),
  KEY `edited_by` (`edited_by`),
  KEY `deleted_by` (`deleted_by`),
  KEY `dry_in_code` (`dry_in_code`),
  KEY `pallet_card_code` (`emp_code`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `pic_tally`
--

INSERT INTO `pic_tally` (`id`, `dry_in_code`, `emp_code`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'M/2016/05/0001', '2013026132', '2016-05-27', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------


--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_code` varchar(20) NOT NULL DEFAULT '',
  `product_name` varchar(50) DEFAULT NULL,
  `product_category_id` int(11) DEFAULT NULL,
  `product_status` varchar(50) DEFAULT NULL,
  `product_uom_id` int(11) DEFAULT NULL,
  `is_maintain_stock` int(11) DEFAULT NULL,
  `image_path` varchar(200) NOT NULL DEFAULT '',
  `brand` varchar(50) DEFAULT NULL,
  `barcode` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `wood_type_id` int(11) DEFAULT NULL,
  `grade_id` int(11) DEFAULT NULL,
  `thickness_id` int(11) DEFAULT NULL,
  `condition_id` int(11) DEFAULT NULL,
  `is_has_serial` int(11) DEFAULT NULL,
  `is_fixed_asset` int(11) DEFAULT NULL,
  `warranty` int(11) DEFAULT NULL,
  `netto` decimal(7,2) DEFAULT NULL,
  `netto_uom_id` int(11) DEFAULT NULL,
  `is_purchase_item` int(11) DEFAULT NULL,
  `minor` int(11) DEFAULT NULL,
  `minor_uom_id` int(3) DEFAULT NULL,
  `lead_time` int(3) DEFAULT NULL,
  `buy_cost_center_id` int(5) DEFAULT NULL,
  `expense_acc_id` int(9) DEFAULT NULL,
  `main_supp_code` varchar(9) DEFAULT NULL,
  `manufacturer` varchar(50) DEFAULT NULL,
  `is_sales_item` int(1) DEFAULT NULL,
  `is_service_item` int(1) DEFAULT NULL,
  `sell_cost_center_id` int(5) DEFAULT NULL,
  `income_acc_id` int(9) DEFAULT NULL,
  `max_disc` decimal(5,2) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  `minqty` int(5) DEFAULT NULL,
  `thickness` decimal(10,5) DEFAULT NULL,
  `length` decimal(10,5) DEFAULT NULL,
  `width` decimal(10,5) DEFAULT NULL,
  PRIMARY KEY (`id`,`product_code`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `product_code`, `product_name`, `product_category_id`, `product_status`, `product_uom_id`, `is_maintain_stock`, `image_path`, `brand`, `barcode`, `description`, `wood_type_id`, `grade_id`, `thickness_id`, `condition_id`, `is_has_serial`, `is_fixed_asset`, `warranty`, `netto`, `netto_uom_id`, `is_purchase_item`, `minor`, `minor_uom_id`, `lead_time`, `buy_cost_center_id`, `expense_acc_id`, `main_supp_code`, `manufacturer`, `is_sales_item`, `is_service_item`, `sell_cost_center_id`, `income_acc_id`, `max_disc`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `minqty`, `thickness`, `length`, `width`) VALUES
(1, 'BLK001', 'BALKEN 2CM', 2, 'Aktif', 2, 1, '', NULL, NULL, NULL, 2, 2, 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2016-06-11', 'Irvan', NULL, NULL, 12, 10.00000, 10.00000, 10.00000),
(2, 'BLK003', 'BALKEN 3CM', 3, 'Aktif', 1, 1, '', '', '', '', 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2016-06-11', 'Irvan', '2016-06-11', 'Irvan', NULL, NULL, 12, 10.00000, 10.00000, 12.00000),
(3, 'BLK004', 'BALKEN 4CM', 1, 'Aktif', 2, 1, '', NULL, NULL, NULL, 2, 2, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2016-06-11', 'Irvan', NULL, NULL, NULL, NULL, 11, 11.00000, 11.00000, 11.00000);

-- --------------------------------------------------------

--
-- Table structure for table `product_category`
--

CREATE TABLE IF NOT EXISTS `product_category` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `product_category` varchar(50) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(25) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `product_category`
--

INSERT INTO `product_category` (`id`, `product_category`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) VALUES
(1, 'Bahan Baku', '0000-00-00', 'Admin', NULL, NULL, NULL, NULL),
(2, 'Barang Pendukung', '0000-00-00', 'Admin', NULL, NULL, NULL, NULL),
(3, 'Aset', '0000-00-00', 'Admin', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `province`
--

CREATE TABLE IF NOT EXISTS `province` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `province` varchar(150) NOT NULL,
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `province`
--

INSERT INTO `province` (`id`, `province`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'DKI Jakarta', '2016-04-16', 'timotius', NULL, NULL, NULL, NULL),
(2, 'Jawa Barat', '2016-04-16', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `received`
--

CREATE TABLE IF NOT EXISTS `received` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `received_code` varchar(16) NOT NULL,
  `received_date` date NOT NULL,
  `rit_no` varchar(4) NOT NULL,
  `license_plate` varchar(10) NOT NULL,
  `driver` varchar(50) NOT NULL,
  `delivery_note` varchar(20) NOT NULL,
  `wood_type_id` int(11) NOT NULL,
  `driver_id` varchar(50) DEFAULT NULL,
  `received_status` varchar(10) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`,`received_code`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `received`
--

INSERT INTO `received` (`id`, `received_code`, `received_date`, `rit_no`, `license_plate`, `driver`, `delivery_note`, `wood_type_id`, `driver_id`, `received_status`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, '001/BL/2016/05/2', '0000-00-00', '001', 'B123CXZ', 'UCOK', 'BABABLESS', 1, NULL, NULL, '0000-00-00', 'ADMIN', NULL, NULL, NULL, NULL),
(2, '0002/BL/26/05/16', '2016-05-26', '0002', '123', 'BABA', 'B123/UMP', 1, '1230000001', 'Baru', '2016-05-26', 'Michael', NULL, NULL, NULL, NULL),
(4, '0003/BL/26/05/16', '2016-05-26', '0003', 'asd', 'BABA', '123_BL', 1, '12300000000', 'Baru', '2016-05-26', 'Michael', NULL, NULL, NULL, NULL),
(5, '0004/BL/26/05/16', '2016-05-26', '0004', '123', 'XXY', '123X_XYZ', 1, '123.0.000.1984.01', 'Diproses', '2016-05-26', 'Michael', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE IF NOT EXISTS `supplier` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `supp_code` varchar(9) NOT NULL,
  `supp_name` varchar(200) NOT NULL,
  `pt` varchar(200) NOT NULL,
  `npwp` varchar(30) NOT NULL,
  `supp_type_id` int(3) NOT NULL,
  `supp_status` varchar(30) NOT NULL,
  `default_tax` int(3) NOT NULL,
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
  `deleted_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `supp_code_2` (`supp_code`),
  KEY `supp_type_id` (`supp_type_id`),
  KEY `bank_id` (`bank_id`),
  KEY `supp_code` (`supp_code`),
  KEY `input_by` (`input_by`,`edited_by`,`deleted_by`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id`, `supp_code`, `supp_name`, `pt`, `npwp`, `supp_type_id`, `supp_status`, `default_tax`, `account_no`, `bank_id`, `account_name`, `currency_id`, `top`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'S0001', 'Agci', 'PT Indomarco', '', 0, '', 0, '', 0, '', 0, 0, '0000-00-00', '0', NULL, NULL, NULL, NULL),
(3, 'ASC000001', 'adsa', 'dsda', 'sadsad', 2, 'Nonaktif', 101, '324342', 1, 'dsfdfs', 1, 101, '2016-04-17', 'timotius', '2016-04-17', 'timotius', '2016-04-17', 'timotius'),
(4, 'ABS000001', 'PT ABS', 'ABS', '123456789', 1, 'Nonaktif Sementara', 10, '', 1, '', 1, 10, '2016-04-17', 'timotius', '2016-04-17', 'timotius', '2016-04-18', 'timotius'),
(5, 'TEST', 'TEST', 'TEST', 'TEST', 1, 'Aktif', 1, 'TEST', 1, 'TEST', 1, 1, '2016-04-17', 'timotius', '2016-04-17', 'timotius', NULL, NULL),
(6, 'SUPP-2016', 'SUPP TES !', 'maju sekawan', '101111111000000', 1, 'Aktif', 10, 'ZzXz', 1, 'ZxZx', 1, 1, '2016-04-18', 'timotius', NULL, NULL, NULL, NULL),
(7, 'B0000111', 'Test', 'Test', '1234567890', 1, 'Aktif', 1, '12345678', 0, 'test', 0, 1, '2016-05-21', 'timotius', '2016-05-21', 'timotius', NULL, NULL),
(8, 'aaaaa', 'aaaaa', 'aaaa', '', 1, 'Nonaktif Sementara', 0, '', 0, '', 0, 0, '2016-05-21', 'timotius', NULL, NULL, '2016-05-21', 'timotius');

-- --------------------------------------------------------

--
-- Table structure for table `supp_address`
--

CREATE TABLE IF NOT EXISTS `supp_address` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
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
  `deleted_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `input_by` (`input_by`),
  KEY `edited_by` (`edited_by`),
  KEY `deleted_by` (`deleted_by`),
  KEY `supp_code` (`supp_code`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=18 ;

--
-- Dumping data for table `supp_address`
--

INSERT INTO `supp_address` (`id`, `supp_code`, `address_type`, `address`, `zip_code`, `city_id`, `phone`, `fax`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(14, 'TEST', 'Billing', 'TEST', 'TEST', 3, 'TEST', 'TEST', '2016-04-17', 'timotius', '2016-04-17', 'timotius', NULL, NULL),
(15, 'SUPP-2016', 'Warehouse', 'pasar baru\npasar baru 3', '10710', 2, '123', '123', '2016-04-18', 'timotius', NULL, NULL, NULL, NULL),
(16, 'SUPP-2016', 'Warehouse', 'kalimantan', '11111', 2, '777777', '123', '2016-04-18', 'timotius', NULL, NULL, NULL, NULL),
(17, 'B0000111', 'Billing', 'aaaa', '13220', 4, 'asd', 'asd', '2016-05-21', 'timotius', '2016-05-21', 'timotius', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `supp_cp`
--

CREATE TABLE IF NOT EXISTS `supp_cp` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `supp_code` varchar(9) NOT NULL,
  `supp_address_id` int(9) DEFAULT NULL,
  `name` varchar(200) NOT NULL,
  `department` varchar(100) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `input_by` (`input_by`),
  KEY `edited_by` (`edited_by`),
  KEY `deleted_by` (`deleted_by`),
  KEY `supp_code` (`supp_code`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `supp_cp`
--

INSERT INTO `supp_cp` (`id`, `supp_code`, `supp_address_id`, `name`, `department`, `phone`, `email`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(8, 'TEST', 14, 'TEST1', 'TEST1', 'TEST1', 'TEST@TEST.COM', '2016-04-17', 'timotius', '2016-04-17', 'timotius', NULL, NULL),
(9, 'SUPP-2016', 15, 'SUGI', 'BILLING', '123', 'sdadad@yshhh.com', '2016-04-18', 'timotius', NULL, NULL, NULL, NULL),
(10, 'B0000111', 17, 'asd', 'asd', 'asd', 'asd@asd.com', '2016-05-21', 'timotius', '2016-05-21', 'timotius', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `supp_type`
--

CREATE TABLE IF NOT EXISTS `supp_type` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `supp_type` varchar(50) NOT NULL,
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `supp_type`
--

INSERT INTO `supp_type` (`id`, `supp_type`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'Bahan Baku', '2016-04-16', 'timotius', NULL, NULL, NULL, NULL),
(2, 'Barang Jadi', '2016-04-16', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `supp_vehicle`
--

CREATE TABLE IF NOT EXISTS `supp_vehicle` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `license_plate` varchar(10) NOT NULL,
  `supp_code` varchar(9) NOT NULL,
  `vehicle_type_id` int(3) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `license_plate` (`license_plate`),
  KEY `input_by` (`input_by`),
  KEY `edited_by` (`edited_by`),
  KEY `deleted_by` (`deleted_by`),
  KEY `supp_code` (`supp_code`),
  KEY `vehicle_type_id` (`vehicle_type_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `supp_vehicle`
--

INSERT INTO `supp_vehicle` (`id`, `license_plate`, `supp_code`, `vehicle_type_id`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(6, 'asd', 'ASC000001', 1, '2016-04-17', 'timotius', '2016-04-17', 'timotius', '2016-04-17', 'timotius'),
(10, '123', 'ASC000001', 2, '2016-04-17', 'timotius', NULL, NULL, '2016-04-17', 'timotius'),
(11, 'TEST', 'TEST', 1, '2016-04-17', 'timotius', '2016-04-17', 'timotius', NULL, NULL),
(12, '123456789', 'B0000111', 1, '2016-05-21', 'timotius', '2016-05-21', 'timotius', NULL, NULL),
(13, 'sdsdsd', 'B0000111', 1, '2016-05-21', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `thickness`
--

CREATE TABLE IF NOT EXISTS `thickness` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `thickness` decimal(7,2) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(25) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `thickness`
--

INSERT INTO `thickness` (`id`, `thickness`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) VALUES
(1, '10.00', NULL, NULL, NULL, NULL, NULL, NULL),
(2, '20.00', NULL, NULL, NULL, NULL, NULL, NULL),
(3, '30.00', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `uom`
--

CREATE TABLE IF NOT EXISTS `uom` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `uom` varchar(50) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(25) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `uom`
--

INSERT INTO `uom` (`id`, `uom`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) VALUES
(1, 'batang', NULL, NULL, NULL, NULL, NULL, NULL),
(2, 'm3', NULL, NULL, NULL, NULL, NULL, NULL),
(3, 'botol', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `password` varchar(50) NOT NULL,
  `emp_code` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `emp_code` (`emp_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

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
-- Table structure for table `vehicle_type`
--

CREATE TABLE IF NOT EXISTS `vehicle_type` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `vehicle_type` varchar(50) NOT NULL,
  `capacity` int(10) NOT NULL,
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `vehicle_type`
--

INSERT INTO `vehicle_type` (`id`, `vehicle_type`, `capacity`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'Mobil', 1500, '2016-04-16', 'timotius', NULL, NULL, NULL, NULL),
(2, 'Truk', 2000, '2016-04-16', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vsupp_addrcontact`
--
CREATE TABLE IF NOT EXISTS `vsupp_addrcontact` (
`supp_name` varchar(200)
,`supp_code` varchar(9)
,`supp_addres_id` int(9)
,`city_id` int(9)
,`name` varchar(200)
);
-- --------------------------------------------------------

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `wood_resource`
--

INSERT INTO `wood_resource` (`id`, `wood_resource`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'Import', NULL, NULL, NULL, NULL, NULL, NULL),
(2, 'Local', NULL, NULL, NULL, NULL, NULL, NULL);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `wood_type`
--

INSERT INTO `wood_type` (`id`, `wood_type`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'JATI', '2016-05-10', 'ADMIN', NULL, NULL, NULL, NULL),
(2, 'MERANTI', '2016-05-10', 'ADMIN', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure for view `vsupp_addrcontact`
--
DROP TABLE IF EXISTS `vsupp_addrcontact`;

CREATE ALGORITHM=UNDEFINED DEFINER=`onesolut_admin`@`116.0.3.139` SQL SECURITY DEFINER VIEW `vsupp_addrcontact` AS (select `ms`.`supp_name` AS `supp_name`,`ms`.`supp_code` AS `supp_code`,`addr`.`id` AS `supp_addres_id`,`addr`.`city_id` AS `city_id`,`contact`.`name` AS `name` from ((`supplier` `ms` join `supp_address` `addr`) join `supp_cp` `contact`) where ((`addr`.`supp_code` = `ms`.`supp_code`) and (`contact`.`supp_address_id` = `addr`.`id`)));

--
-- Constraints for dumped tables
--

--
-- Constraints for table `supp_address`
--
ALTER TABLE `supp_address`
  ADD CONSTRAINT `supp_address_ibfk_1` FOREIGN KEY (`supp_code`) REFERENCES `supplier` (`supp_code`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


ALTER TABLE  `received` ADD  `supplier_code` VARCHAR( 50 ) NOT NULL AFTER  `rit_no` ,
ADD  `supplier_cp_id` INT NOT NULL AFTER  `supplier_code`;


ALTER TABLE supplier MODIFY COLUMN account_no varchar(30) null;
ALTER TABLE supplier MODIFY COLUMN bank_id int(3) null;
ALTER TABLE supplier MODIFY COLUMN account_name varchar(30) null;
ALTER TABLE supp_cp MODIFY COLUMN department varchar(100) null;
ALTER TABLE supp_cp MODIFY COLUMN phone varchar(15) null;
ALTER TABLE supp_cp MODIFY COLUMN email varchar(50) null;

ALTER TABLE  `received` ADD  `emp_code` VARCHAR( 25 ) NOT NULL AFTER  `driver_id`;


CREATE TABLE IF NOT EXISTS `received_detail` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `received_code` varchar(16) NOT NULL,
  `total_volume` decimal(7,2) DEFAULT NULL,
  `total_log` int(5) DEFAULT NULL,
  `grade_id` int(11) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
 
  PRIMARY KEY (`id`)
);

ALTER TABLE `received` CHANGE `emp_code` `emp_code` VARCHAR(25) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT '';

ALTER TABLE  `received` ADD  `total_volume` DECIMAL( 7, 2 ) NOT NULL DEFAULT  '0' AFTER  `emp_code`;
ALTER TABLE  `pallet_card` CHANGE  `volume`  `volume` DECIMAL( 7, 2 ) NOT NULL;

/* INVENTORY */
ALTER TABLE  `received` CHANGE  `supplier_code` `supp_code` varchar(9) NULL AFTER  `rit_no`;
ALTER TABLE  `received` ADD  `send_to_finance_date` DATE NULL;
ALTER TABLE  `received` ADD  `confirm_date` DATE NULL;
ALTER TABLE  `dry_in` ADD  `confirm_date` DATE NULL;
ALTER TABLE  `dry_out` ADD  `confirm_date` DATE NULL;

CREATE TABLE inventory ( 
	id INT(9) NOT NULL AUTO_INCREMENT,
	product_code VARCHAR(20),
	qty DECIMAL(7,2),
	warehouse INT(1),
	stock_date DATE,
	inventory_log_id INT(12),
	input_date DATE,
	input_by VARCHAR(25),
	edit_date DATE,
	edited_by VARCHAR(25),
	deleted_date DATE,
	deleted_bu VARCHAR(25),
	PRIMARY KEY (`id`)
);


CREATE TABLE inventory_log
( 
	id INT(9) NOT NULL AUTO_INCREMENT,
	product_code VARCHAR(20),
	warehouse INT(1),
	prev_stock	DEC(7,5),	
	plus_stock	DEC(7,5),	
	min_stock	DEC(7,5),	
	curr_stock	DEC(7,5),	
	prev_stock_date	DATE,	
	curr_stock_date	DATE,	
	confirm_code	VARCHAR(5),
	input_date DATE,
	input_by VARCHAR(25),
	edit_date DATE,
	edited_by VARCHAR(25),
	deleted_date DATE,
	deleted_by VARCHAR(25),
	PRIMARY KEY (`id`)
);

CREATE TABLE inventory_log_temp
( 
	id INT(12) NOT NULL AUTO_INCREMENT,
	product_code VARCHAR(20),
	warehouse INT(1),
	qty	DEC(7,2),	
	mutasi VARCHAR(1),	
	srctable VARCHAR(50),
	confirm_code	VARCHAR(5),
	input_date DATE,
	input_by VARCHAR(25),
	edit_date DATE,
	edited_by VARCHAR(25),
	deleted_date DATE,
	deleted_by VARCHAR(25),
	PRIMARY KEY (`id`)
);

CREATE TABLE confirm
( 
	id INT(12) NOT NULL AUTO_INCREMENT,
	confirm_code VARCHAR(5),
	module varchar(50),
	daily_closing_date DATE,
	input_date DATE,
	input_by VARCHAR(25),
	edit_date DATE,
	edited_by VARCHAR(25),
	deleted_date DATE,
	deleted_by VARCHAR(25),
	PRIMARY KEY (`id`)
);

ALTER TABLE  `production` ADD  `production_type_code` VARCHAR( 9 ) NOT NULL AFTER  `shift_code`;

CREATE TABLE production_type
( 
	id INT(12) NOT NULL AUTO_INCREMENT,
	production_type_code VARCHAR(9),
	description varchar(200),
	input_date DATE,
	input_by VARCHAR(25),
	edit_date DATE,
	edited_by VARCHAR(25),
	deleted_date DATE,
	deleted_by VARCHAR(25),
	PRIMARY KEY (`id`)
);

INSERT INTO `project`.`production_type` (`id`, `production_type_code`, `description`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES (NULL, 'PT0001', 'Production Langsung', NULL, NULL, NULL, NULL, NULL, NULL), (NULL, 'PT0002', 'Production Berkala', NULL, NULL, NULL, NULL, NULL, NULL);


--Update 30-9-2106
--Perhatiin dulu untuk table production_type kalo udah ada column production_type nya gak perlu dijalanin
ALTER TABLE  `production_type` ADD  `production_type` VARCHAR( 50 ) NOT NULL AFTER  `production_type_code`;

ALTER TABLE  `delivery` CHANGE  `document_type_id`  `document_type` VARCHAR( 50 ) NULL DEFAULT NULL;
ALTER TABLE  `delivery` ADD  `doc_issued_date` DATE NOT NULL AFTER  `delivery_note`;
ALTER TABLE  `received` ADD  `received_by` VARCHAR( 50 ) NOT NULL DEFAULT  ' ' AFTER  `total_volume`;
ALTER TABLE  `received` CHANGE  `license_plate`  `license_plate` VARCHAR( 25 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL;
UPDATE DELIVERY SET DOC_ISSUED_DATE = NOW();
ALTER TABLE  `grade` ADD  `product_category_id` INT NOT NULL AFTER  `id`;
UPDATE  GRADE SET PRODUCT_CATEGORY_ID = 1;



INSERT INTO `supp_type` (`id`, `supp_type`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES (NULL, 'Hasil Produksi', '2016-10-16', 'ADMIN', NULL, NULL, NULL, NULL);

CREATE TABLE `purchase_prod_result` ( 
	`id` INT(12) NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	`ppr_code` VARCHAR(15) NOT NULL , 
	`supp_code` VARCHAR(9) NOT NULL , 
	`purchase_note` VARCHAR(50) NULL , 
	`purchase_date` DATE NOT NULL , 
	`due_date` DATE NOT NULL ,
	`currency_id` INT(3) NOT NULL , 
	`exchange_rate` DECIMAL(10,2) NULL DEFAULT '0.00' , 
	`status` VARCHAR(20) NULL , 
	`total` DECIMAL(10,2) NOT NULL DEFAULT '0.00' , 
	`discount` DECIMAL(10,2) NOT NULL DEFAULT '0.00' , 
	`tax` DECIMAL(10,2) NOT NULL DEFAULT '0.00' , 
	`grand_total` DECIMAL(10,2) NOT NULL DEFAULT '0.00' , 
	`confirm_code` VARCHAR(5) NULL , 
	`confirm_date` DATE NULL , 
	`input_date` DATE NULL , 
	`input_by` VARCHAR(25) NULL , 
	`edit_date` DATE NULL , 
	`edited_by` VARCHAR(25) NULL , 
	`deleted_date` DATE NULL , 
	`deleted_by` VARCHAR(25) NULL
) ENGINE = InnoDB;

CREATE TABLE `han20160830`.`ppr_product` ( 
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	`ppr_code` VARCHAR(15) NOT NULL , 
	`product_code` VARCHAR(20) NOT NULL , 
	`qty` INT NOT NULL , 
	`unit_price` DECIMAL(10,2) NULL DEFAULT '0.00' , 
	`sub_total` DECIMAL(10,2) NOT NULL DEFAULT '0.00' , 
	`input_date` DATE NULL , 
	`input_by` VARCHAR(25) NULL , 
	`edit_date` DATE NULL , 
	`edited_by` VARCHAR(25) NULL , 
	`deleted_date` DATE NULL , 
	`deleted_by` VARCHAR(25) NULL
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `production_waste` (
  `id` int(12) NOT NULL,
  `pw_code` varchar(15) NOT NULL,
  `production_date` date NOT NULL,
  `group_shift_code` varchar(9) NOT NULL,
  `shift_code` varchar(9) NOT NULL,
  `line_code` varchar(9) NOT NULL,
  `production_type_code` varchar(9) NOT NULL,
  `status` varchar(50) NOT NULL,
  `confirm_code` varchar(5) DEFAULT NULL,
  `confirm_date` date DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for table `production_waste`
--
ALTER TABLE `production_waste`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `production_waste`
--
ALTER TABLE `production_waste`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT;

CREATE TABLE IF NOT EXISTS `pw_product` (
  `id` int(11) NOT NULL,
  `pw_code` varchar(15) NOT NULL,
  `product_code` varchar(20) NOT NULL,
  `qty` int(11) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for table `pw_product`
--
ALTER TABLE `pw_product`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pw_product`
--
ALTER TABLE `pw_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
  
UPDATE `han20160830`.`production_type` SET `production_type_code` = 'BC' WHERE `production_type`.`id` = 1
UPDATE `han20160830`.`production_type` SET `production_type_code` = 'PW' WHERE `production_type`.`id` = 2




--New

INSERT INTO `project`.`grade` (`id`, `product_category_id`, `grade`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) VALUES ('100', '1', 'Broken', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO PRODUCT VALUES(1000, 'PDC019', 'JATI RUSAK', 1, NULL, 1, 0, '', NULL, NULL, NULL, 1, 100, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2016-08-31', 'Timotius', NULL, NULL, NULL, NULL, 0, '10.00000', '10.00000', '10.00000', NULL, NULL);

CREATE TABLE IF NOT EXISTS `prod_pk` (
  `id` int NOT NULL AUTO_INCREMENT,
  `prod_pk_code` varchar(20) NOT NULL,
  `group_shift_code` varchar(9) NOT NULL,
  `line_code` varchar(9) NOT NULL,
  `shift_code` varchar(9) NOT NULL,
  `production_date` date NOT NULL,
  `information` varchar(200) DEFAULT NULL,
  `total_material_protol` decimal(7,2),
  `total_material_klem` decimal(7,2),
  `status` varchar(10) NOT NULL,
  `confirm_code`varchar(5),
  `confirm_date`date,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`,`prod_pk_code`)
 );
  CREATE TABLE IF NOT EXISTS `prod_pk_material` (
  `id` int NOT NULL AUTO_INCREMENT,
  `prod_pk_code` varchar(20) NOT NULL,
  `product_code` varchar(20) NOT NULL,
  `qty` decimal(7,2),
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`,`prod_pk_code`)
);
CREATE TABLE IF NOT EXISTS `prod_pk_result_product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `prod_pk_result_id` int NOT NULL,
  `product_code` varchar(20) NOT NULL,
  `qty` decimal(7,2),
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `prod_pk_result` (
  `id` int NOT NULL AUTO_INCREMENT,
  `prod_pk_code` varchar(20) NOT NULL,
  `pressed_no` integer,
  `start_time` varchar(10),
  `total_protol` decimal(7,2),
  `total_klem` decimal(7,2),
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`,`prod_pk_code`)
);


ALTER TABLE  `prod_pk_result` ADD  `total_fine_a` DECIMAL( 7, 2 ) NOT NULL AFTER  `start_time` ,
ADD  `total_fine_b` DECIMAL( 7, 2 ) NOT NULL AFTER  `total_fine_a`;

ALTER TABLE `supp_address` CHANGE `city_id` `province_id` INT(9) NULL DEFAULT NULL;
ALTER TABLE `supp_address` ADD `city` VARCHAR(150) NULL AFTER `province_id`;

ALTER TABLE `grade` ADD `product_category_id` INT NOT NULL AFTER `id`, ADD INDEX (`product_category_id`) ;


--Kalo table prod_result masih ada jalanin query drop
DROP TABLE PROD_RESULT;

CREATE TABLE IF NOT EXISTS `prod_result_product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `prod_result_id` int NOT NULL,
  `product_code` varchar(20) NOT NULL,
  `qty` decimal(7,2),
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `prod_result` (
  `id` int NOT NULL AUTO_INCREMENT,
  `prod_code` varchar(20) NOT NULL,
  `pressed_no` integer,
  `start_time` varchar(10),
  `total_fine_a` decimal(7,2),
  `total_fine_b` decimal(7,2),
  `total_protol` decimal(7,2),
  `total_klem` decimal(7,2),
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`,`prod_code`)
);


--
-- Struktur dari tabel `wood_type`
--

CREATE TABLE IF NOT EXISTS `wood_type` (
  `id` int(11) NOT NULL,
  `wood_type` varchar(50) NOT NULL,
  `wood_genus_id` int(3) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `wood_type`
--

INSERT INTO `wood_type` (`id`, `wood_type`, `wood_genus_id`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'JATI', 0, '2016-05-10', 'ADMIN', NULL, NULL, NULL, NULL),
(2, 'MERANTI', 0, '2016-05-10', 'ADMIN', NULL, NULL, NULL, NULL),
(3, 'GHGGF', 2, '2016-09-28', 'timotius', NULL, NULL, '2016-09-28', 'timotius'),
(4, 'DFSDFS243', 2, '2016-09-28', 'timotius', '2016-09-28', 'timotius', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `wood_type`
--
ALTER TABLE `wood_type`
  ADD PRIMARY KEY (`id`), ADD KEY `wood_genus_id` (`wood_genus_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `wood_type`
--
ALTER TABLE `wood_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

--
-- Struktur dari tabel `wood_genus`
--

CREATE TABLE IF NOT EXISTS `wood_genus` (
  `id` int(11) NOT NULL,
  `wood_genus` varchar(50) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `wood_genus`
--

INSERT INTO `wood_genus` (`id`, `wood_genus`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'DFFDDSF', '2016-09-28', 'timotius', NULL, NULL, '2016-09-28', 'timotius'),
(2, 'CVXCVXXCV', '2016-09-28', 'timotius', '2016-09-28', 'timotius', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `wood_genus`
--
ALTER TABLE `wood_genus`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `wood_genus`
--
ALTER TABLE `wood_genus`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
