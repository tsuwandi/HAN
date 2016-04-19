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
-- Database: `projecto`
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

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
