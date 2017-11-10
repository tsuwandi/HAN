-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 10 Nov 2017 pada 16.28
-- Versi Server: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `han`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `id` int(3) NOT NULL,
  `acc_no` varchar(50) NOT NULL,
  `account` varchar(50) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(25) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `attendance`
--

CREATE TABLE IF NOT EXISTS `attendance` (
  `id` int(11) NOT NULL,
  `pin` smallint(6) DEFAULT NULL,
  `nik` smallint(6) DEFAULT NULL,
  `employee_name` varchar(50) DEFAULT NULL,
  `attendance_date` date DEFAULT NULL,
  `attendance_time` varchar(5) DEFAULT NULL,
  `machine_serial_number` varchar(16) DEFAULT NULL,
  `machine_name` varchar(32) DEFAULT NULL,
  `verification_type` varchar(5) DEFAULT NULL,
  `mode` varchar(32) DEFAULT NULL,
  `update_mode` varchar(32) DEFAULT NULL,
  `branch` varchar(64) DEFAULT NULL,
  `department` varchar(64) DEFAULT NULL,
  `role` varchar(64) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `inputed_by` varchar(100) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(100) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `deleted_by` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `bank`
--

INSERT INTO `bank` (`id`, `bank_abbr`, `bank`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'BCA', 'Bank Central Asia', '2016-04-16', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

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
-- Dumping data untuk tabel `chamber`
--

INSERT INTO `chamber` (`id`, `chamber`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, '1', '2017-01-07', 'admin', NULL, NULL, NULL, NULL),
(2, '2', '2017-01-07', 'admin', NULL, NULL, NULL, NULL),
(3, '3', '2017-01-07', 'admin', NULL, NULL, NULL, NULL),
(4, '4', '2017-01-07', 'admin', NULL, NULL, NULL, NULL),
(5, '5', '2017-01-07', 'admin', NULL, NULL, NULL, NULL),
(6, '6', '2017-01-07', 'admin', NULL, NULL, NULL, NULL),
(7, '7', '2017-01-07', 'admin', NULL, NULL, NULL, NULL),
(8, '8', '2017-01-07', 'admin', NULL, NULL, NULL, NULL),
(9, '9', '2017-01-07', 'admin', NULL, NULL, NULL, NULL),
(10, '10', '2017-01-07', 'admin', NULL, NULL, NULL, NULL),
(11, '11', '2017-01-07', 'admin', NULL, NULL, NULL, NULL),
(12, '12', '2017-01-07', 'admin', NULL, NULL, NULL, NULL);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `common_config`
--

CREATE TABLE IF NOT EXISTS `common_config` (
  `id` int(11) NOT NULL,
  `key_config` varchar(100) DEFAULT NULL,
  `value_config` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `common_config`
--

INSERT INTO `common_config` (`id`, `key_config`, `value_config`) VALUES
(1, 'salary_cycle', '20');

-- --------------------------------------------------------

--
-- Struktur dari tabel `completed_so_schedule`
--

CREATE TABLE IF NOT EXISTS `completed_so_schedule` (
  `id` int(5) NOT NULL,
  `set_so_schedule_id` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `completed_so_schedule`
--

INSERT INTO `completed_so_schedule` (`id`, `set_so_schedule_id`, `date`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, '1', '2017-05-03', '2017-05-03', 'admin', NULL, NULL, NULL, NULL),
(2, '3', '2017-05-06', '2017-05-06', 'admin', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `condition`
--

CREATE TABLE IF NOT EXISTS `condition` (
  `id` int(3) NOT NULL,
  `condition` varchar(50) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(25) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `condition`
--

INSERT INTO `condition` (`id`, `condition`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) VALUES
(1, 'basah', NULL, NULL, NULL, NULL, NULL, NULL),
(2, 'kering', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `confirm`
--

CREATE TABLE IF NOT EXISTS `confirm` (
  `id` int(12) NOT NULL,
  `confirm_code` varchar(5) DEFAULT NULL,
  `module` varchar(50) DEFAULT NULL,
  `daily_closing_date` date DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `confirm`
--

INSERT INTO `confirm` (`id`, `confirm_code`, `module`, `daily_closing_date`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'ZIHRD', 'PEMBELIAN', '2016-08-27', '2016-08-27', 'timotius', NULL, NULL, NULL, NULL),
(2, 'CT6RH', 'PEMBELIAN', '2016-08-27', '2016-08-27', 'timotius', NULL, NULL, NULL, NULL),
(3, 'KB7T9', 'PEMBELIAN', '2016-08-27', '2016-08-27', 'timotius', NULL, NULL, NULL, NULL),
(4, '9X4IB', 'PEMBELIAN', '2016-08-30', '2016-08-30', 'timotius', NULL, NULL, NULL, NULL),
(8, '9G12I', 'PEMBELIAN', '2016-09-01', '2016-09-01', 'timotius', NULL, NULL, NULL, NULL),
(9, '9F9IS', 'PEMBELIAN', '2016-09-01', '2016-09-01', 'timotius', NULL, NULL, NULL, NULL),
(10, '73N1H', 'PEMBELIAN', '2016-09-01', '2016-09-01', 'timotius', NULL, NULL, NULL, NULL),
(11, 'QDJ3G', 'PEMBELIAN', '2016-11-16', '2016-11-16', 'timotius', NULL, NULL, NULL, NULL),
(12, '0EZBZ', 'PEMBELIAN', '2016-11-30', '2016-11-30', 'timotius', NULL, NULL, NULL, NULL),
(13, '9S3FI', 'PEMBELIAN', '2016-11-30', '2016-11-30', 'timotius', NULL, NULL, NULL, NULL),
(14, '03O04', 'PEMBELIAN', '2016-11-30', '2016-11-30', 'timotius', NULL, NULL, NULL, NULL),
(15, '3G08C', 'PEMBELIAN', '2016-12-03', '2016-12-03', 'timotius', NULL, NULL, NULL, NULL),
(16, 'RSXW2', 'PEMBELIAN', '2017-01-03', '2017-01-03', 'timotius', NULL, NULL, NULL, NULL),
(17, 'YUHH7', 'PEMBELIAN', '2017-01-03', '2017-01-03', 'timotius', NULL, NULL, NULL, NULL),
(18, 'VF3P2', 'PENERIMAAN', '2017-01-19', '2017-01-19', 'timotius', NULL, NULL, NULL, NULL),
(19, 'SEMHV', 'PENERIMAAN', '2017-01-19', '2017-01-19', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `cost_center`
--

CREATE TABLE IF NOT EXISTS `cost_center` (
  `id` int(3) NOT NULL,
  `cost_center` varchar(50) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `cost_center`
--

INSERT INTO `cost_center` (`id`, `cost_center`, `input_date`, `input_by`, `edit_date`, `edit_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'default', '2017-10-18', 'ADMIN', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `country`
--

CREATE TABLE IF NOT EXISTS `country` (
  `id` int(50) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `idx` int(8) DEFAULT NULL,
  `country_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date_format` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `time_zones` text COLLATE utf8mb4_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data untuk tabel `country`
--

INSERT INTO `country` (`id`, `name`, `idx`, `country_name`, `date_format`, `code`, `time_zones`) VALUES
(1, 'Afghanistan', NULL, 'Afghanistan', 'dd-mm-yyyy', 'af', 'Asia/Kabul'),
(2, 'Åland Islands', NULL, 'Åland Islands', 'dd-mm-yyyy', 'ax', ''),
(3, 'Albania', NULL, 'Albania', 'dd-mm-yyyy', 'al', 'Europe/Tirane'),
(4, 'Algeria', NULL, 'Algeria', 'dd-mm-yyyy', 'dz', 'Africa/Algiers'),
(5, 'American Samoa', NULL, 'American Samoa', 'dd-mm-yyyy', 'as', ''),
(6, 'Andorra', NULL, 'Andorra', 'dd-mm-yyyy', 'ad', 'Europe/Andorra'),
(7, 'Angola', NULL, 'Angola', 'dd-mm-yyyy', 'ao', 'Africa/Luanda'),
(8, 'Anguilla', NULL, 'Anguilla', 'dd-mm-yyyy', 'ai', 'America/Anguilla'),
(9, 'Antarctica', NULL, 'Antarctica', 'dd-mm-yyyy', 'aq', 'Antarctica/Casey\nAntarctica/Davis\nAntarctica/DumontDUrville\nAntarctica/Macquarie\nAntarctica/Mawson\nAntarctica/McMurdo\nAntarctica/Palmer\nAntarctica/Rothera\nAntarctica/Syowa\nAntarctica/Vostok'),
(10, 'Antigua and Barbuda', NULL, 'Antigua and Barbuda', 'dd-mm-yyyy', 'ag', 'America/Antigua'),
(11, 'Argentina', NULL, 'Argentina', 'dd-mm-yyyy', 'ar', 'America/Argentina/Buenos_Aires\nAmerica/Argentina/Catamarca\nAmerica/Argentina/Cordoba\nAmerica/Argentina/Jujuy\nAmerica/Argentina/La_Rioja\nAmerica/Argentina/Mendoza\nAmerica/Argentina/Rio_Gallegos\nAmerica/Argentina/Salta\nAmerica/Argentina/San_Juan\nAmerica/Argentina/San_Luis\nAmerica/Argentina/Tucuman\nAmerica/Argentina/Ushuaia'),
(12, 'Armenia', NULL, 'Armenia', 'dd-mm-yyyy', 'am', 'Asia/Yerevan'),
(13, 'Aruba', NULL, 'Aruba', 'dd-mm-yyyy', 'aw', 'America/Aruba'),
(14, 'Australia', NULL, 'Australia', 'dd-mm-yyyy', 'au', 'Australia/Adelaide\nAustralia/Brisbane\nAustralia/Broken_Hill\nAustralia/Currie\nAustralia/Darwin\nAustralia/Eucla\nAustralia/Hobart\nAustralia/Lindeman\nAustralia/Lord_Howe\nAustralia/Melbourne\nAustralia/Perth\nAustralia/Sydney'),
(15, 'Austria', NULL, 'Austria', 'dd-mm-yyyy', 'at', 'Europe/Vienna'),
(16, 'Azerbaijan', NULL, 'Azerbaijan', 'dd-mm-yyyy', 'az', 'Asia/Baku'),
(17, 'Bahamas', NULL, 'Bahamas', 'dd-mm-yyyy', 'bs', 'America/Nassau'),
(18, 'Bahrain', NULL, 'Bahrain', 'dd-mm-yyyy', 'bh', 'Asia/Bahrain'),
(19, 'Bangladesh', NULL, 'Bangladesh', 'dd-mm-yyyy', 'bd', 'Asia/Dhaka'),
(20, 'Barbados', NULL, 'Barbados', 'dd-mm-yyyy', 'bb', 'America/Barbados'),
(21, 'Belarus', NULL, 'Belarus', 'dd-mm-yyyy', 'by', 'Europe/Minsk'),
(22, 'Belgium', NULL, 'Belgium', 'dd-mm-yyyy', 'be', 'Europe/Brussels'),
(23, 'Belize', NULL, 'Belize', 'mm-dd-yyyy', 'bz', 'America/Belize'),
(24, 'Benin', NULL, 'Benin', 'dd-mm-yyyy', 'bj', 'Africa/Porto-Novo'),
(25, 'Bermuda', NULL, 'Bermuda', 'dd-mm-yyyy', 'bm', 'Atlantic/Bermuda'),
(26, 'Bhutan', NULL, 'Bhutan', 'dd-mm-yyyy', 'bt', 'Asia/Thimphu'),
(27, 'Bolivia, Plurinational State of', NULL, 'Bolivia, Plurinational State of', 'dd-mm-yyyy', 'bo', ''),
(28, 'Bonaire, Sint Eustatius and Saba', NULL, 'Bonaire, Sint Eustatius and Saba', 'dd-mm-yyyy', 'bq', ''),
(29, 'Bosnia and Herzegovina', NULL, 'Bosnia and Herzegovina', 'dd-mm-yyyy', 'ba', 'Europe/Sarajevo'),
(30, 'Botswana', NULL, 'Botswana', 'dd-mm-yyyy', 'bw', 'Africa/Gaborone'),
(31, 'Bouvet Island', NULL, 'Bouvet Island', 'dd-mm-yyyy', 'bv', ''),
(32, 'Brazil', NULL, 'Brazil', 'dd/mm/yyyy', 'br', 'America/Araguaina\nAmerica/Bahia\nAmerica/Belem\nAmerica/Boa_Vista\nAmerica/Campo_Grande\nAmerica/Cuiaba\nAmerica/Eirunepe\nAmerica/Fortaleza\nAmerica/Maceio\nAmerica/Manaus\nAmerica/Noronha\nAmerica/Porto_Velho\nAmerica/Recife\nAmerica/Rio_Branco\nAmerica/Santarem\nAmerica/Sao_Paulo'),
(33, 'British Indian Ocean Territory', NULL, 'British Indian Ocean Territory', 'dd-mm-yyyy', 'io', 'Indian/Chagos'),
(34, 'Brunei Darussalam', NULL, 'Brunei Darussalam', 'dd-mm-yyyy', 'bn', 'Asia/Brunei'),
(35, 'Bulgaria', NULL, 'Bulgaria', 'dd-mm-yyyy', 'bg', 'Europe/Sofia'),
(36, 'Burkina Faso', NULL, 'Burkina Faso', 'dd-mm-yyyy', 'bf', 'Africa/Ouagadougou'),
(37, 'Burundi', NULL, 'Burundi', 'dd-mm-yyyy', 'bi', 'Africa/Bujumbura'),
(38, 'Cambodia', NULL, 'Cambodia', 'dd-mm-yyyy', 'kh', 'Asia/Phnom_Penh'),
(39, 'Cameroon', NULL, 'Cameroon', 'dd-mm-yyyy', 'cm', 'Africa/Douala'),
(40, 'Canada', NULL, 'Canada', 'mm-dd-yyyy', 'ca', 'America/Atikokan\nAmerica/Blanc-Sablon\nAmerica/Cambridge_Bay\nAmerica/Creston\nAmerica/Dawson\nAmerica/Dawson_Creek\nAmerica/Edmonton\nAmerica/Glace_Bay\nAmerica/Goose_Bay\nAmerica/Halifax\nAmerica/Inuvik\nAmerica/Iqaluit\nAmerica/Moncton\nAmerica/Montreal\nAmerica/Nipigon\nAmerica/Pangnirtung\nAmerica/Rainy_River\nAmerica/Rankin_Inlet\nAmerica/Regina\nAmerica/Resolute\nAmerica/St_Johns\nAmerica/Swift_Current\nAmerica/Thunder_Bay\nAmerica/Toronto\nAmerica/Vancouver\nAmerica/Whitehorse\nAmerica/Winnipeg\nAmerica/Yellowknife'),
(41, 'Cape Verde', NULL, 'Cape Verde', 'dd-mm-yyyy', 'cv', 'Atlantic/Cape_Verde'),
(42, 'Cayman Islands', NULL, 'Cayman Islands', 'dd-mm-yyyy', 'ky', 'America/Cayman'),
(43, 'Central African Republic', NULL, 'Central African Republic', 'dd-mm-yyyy', 'cf', 'Africa/Bangui'),
(44, 'Chad', NULL, 'Chad', 'dd-mm-yyyy', 'td', 'Africa/Ndjamena'),
(45, 'Chile', NULL, 'Chile', 'dd-mm-yyyy', 'cl', 'America/Santiago\nPacific/Easter'),
(46, 'China', NULL, 'China', 'yyyy-mm-dd', 'cn', 'Asia/Chongqing\nAsia/Harbin\nAsia/Kashgar\nAsia/Shanghai\nAsia/Urumqi'),
(47, 'Christmas Island', NULL, 'Christmas Island', 'dd-mm-yyyy', 'cx', 'Indian/Christmas'),
(48, 'Cocos (Keeling) Islands', NULL, 'Cocos (Keeling) Islands', 'dd-mm-yyyy', 'cc', 'Indian/Cocos'),
(49, 'Colombia', NULL, 'Colombia', 'dd-mm-yyyy', 'co', 'America/Bogota'),
(50, 'Comoros', NULL, 'Comoros', 'dd-mm-yyyy', 'km', 'Indian/Comoro'),
(51, 'Congo', NULL, 'Congo', 'dd-mm-yyyy', 'cg', ''),
(52, 'Congo, The Democratic Republic of the', NULL, 'Congo, The Democratic Republic of the', 'dd-mm-yyyy', 'cd', ''),
(53, 'Cook Islands', NULL, 'Cook Islands', 'dd-mm-yyyy', 'ck', 'Pacific/Rarotonga'),
(54, 'Costa Rica', NULL, 'Costa Rica', 'dd-mm-yyyy', 'cr', 'America/Costa_Rica'),
(55, 'Croatia', NULL, 'Croatia', 'dd-mm-yyyy', 'hr', 'Europe/Zagreb'),
(56, 'Cuba', NULL, 'Cuba', 'dd-mm-yyyy', 'cu', 'America/Havana'),
(57, 'Curaçao', NULL, 'Curaçao', 'dd-mm-yyyy', 'cw', ''),
(58, 'Cyprus', NULL, 'Cyprus', 'dd-mm-yyyy', 'cy', 'Asia/Nicosia'),
(59, 'Czech Republic', NULL, 'Czech Republic', 'dd-mm-yyyy', 'cz', 'Europe/Prague'),
(60, 'Denmark', NULL, 'Denmark', 'dd-mm-yyyy', 'dk', 'Europe/Copenhagen'),
(61, 'Djibouti', NULL, 'Djibouti', 'dd-mm-yyyy', 'dj', 'Africa/Djibouti'),
(62, 'Dominica', NULL, 'Dominica', 'dd-mm-yyyy', 'dm', 'America/Dominica'),
(63, 'Dominican Republic', NULL, 'Dominican Republic', 'dd-mm-yyyy', 'do', 'America/Santo_Domingo'),
(64, 'Ecuador', NULL, 'Ecuador', 'dd-mm-yyyy', 'ec', 'America/Guayaquil\nPacific/Galapagos'),
(65, 'Egypt', NULL, 'Egypt', 'dd-mm-yyyy', 'eg', 'Africa/Cairo'),
(66, 'El Salvador', NULL, 'El Salvador', 'dd-mm-yyyy', 'sv', 'America/El_Salvador'),
(67, 'Equatorial Guinea', NULL, 'Equatorial Guinea', 'dd-mm-yyyy', 'gq', 'Africa/Malabo'),
(68, 'Eritrea', NULL, 'Eritrea', 'dd-mm-yyyy', 'er', 'Africa/Asmara'),
(69, 'Estonia', NULL, 'Estonia', 'dd-mm-yyyy', 'ee', 'Europe/Tallinn'),
(70, 'Ethiopia', NULL, 'Ethiopia', 'dd-mm-yyyy', 'et', 'Africa/Addis_Ababa'),
(71, 'Falkland Islands (Malvinas)', NULL, 'Falkland Islands (Malvinas)', 'dd-mm-yyyy', 'fk', ''),
(72, 'Faroe Islands', NULL, 'Faroe Islands', 'dd-mm-yyyy', 'fo', 'Atlantic/Faroe'),
(73, 'Fiji', NULL, 'Fiji', 'dd-mm-yyyy', 'fj', 'Pacific/Fiji'),
(74, 'Finland', NULL, 'Finland', 'dd-mm-yyyy', 'fi', 'Europe/Helsinki'),
(75, 'France', NULL, 'France', 'dd-mm-yyyy', 'fr', 'Europe/Paris'),
(76, 'French Guiana', NULL, 'French Guiana', 'dd-mm-yyyy', 'gf', 'America/Cayenne'),
(77, 'French Polynesia', NULL, 'French Polynesia', 'dd-mm-yyyy', 'pf', 'Pacific/Gambier\nPacific/Marquesas\nPacific/Tahiti'),
(78, 'French Southern Territories', NULL, 'French Southern Territories', 'dd-mm-yyyy', 'tf', ''),
(79, 'Gabon', NULL, 'Gabon', 'dd-mm-yyyy', 'ga', 'Africa/Libreville'),
(80, 'Gambia', NULL, 'Gambia', 'dd-mm-yyyy', 'gm', 'Africa/Banjul'),
(81, 'Georgia', NULL, 'Georgia', 'dd-mm-yyyy', 'ge', 'Asia/Tbilisi'),
(82, 'Germany', NULL, 'Germany', 'dd-mm-yyyy', 'de', 'Europe/Berlin'),
(83, 'Ghana', NULL, 'Ghana', 'dd-mm-yyyy', 'gh', 'Africa/Accra'),
(84, 'Gibraltar', NULL, 'Gibraltar', 'dd-mm-yyyy', 'gi', 'Europe/Gibraltar'),
(85, 'Greece', NULL, 'Greece', 'dd-mm-yyyy', 'gr', 'Europe/Athens'),
(86, 'Greenland', NULL, 'Greenland', 'dd-mm-yyyy', 'gl', 'America/Danmarkshavn\nAmerica/Godthab\nAmerica/Scoresbysund\nAmerica/Thule'),
(87, 'Grenada', NULL, 'Grenada', 'dd-mm-yyyy', 'gd', 'America/Grenada'),
(88, 'Guadeloupe', NULL, 'Guadeloupe', 'dd-mm-yyyy', 'gp', 'America/Guadeloupe'),
(89, 'Guam', NULL, 'Guam', 'dd-mm-yyyy', 'gu', 'Pacific/Guam'),
(90, 'Guatemala', NULL, 'Guatemala', 'dd-mm-yyyy', 'gt', 'America/Guatemala'),
(91, 'Guernsey', NULL, 'Guernsey', 'dd-mm-yyyy', 'gg', 'Europe/London'),
(92, 'Guinea', NULL, 'Guinea', 'dd-mm-yyyy', 'gn', 'Africa/Conakry'),
(93, 'Guinea-Bissau', NULL, 'Guinea-Bissau', 'dd-mm-yyyy', 'gw', 'Africa/Bissau'),
(94, 'Guyana', NULL, 'Guyana', 'dd-mm-yyyy', 'gy', 'America/Guyana'),
(95, 'Haiti', NULL, 'Haiti', 'dd-mm-yyyy', 'ht', 'America/Guatemala\nAmerica/Port-au-Prince'),
(96, 'Heard Island and McDonald Islands', NULL, 'Heard Island and McDonald Islands', 'dd-mm-yyyy', 'hm', ''),
(97, 'Holy See (Vatican City State)', NULL, 'Holy See (Vatican City State)', 'dd-mm-yyyy', 'va', ''),
(98, 'Honduras', NULL, 'Honduras', 'dd-mm-yyyy', 'hn', 'America/Tegucigalpa'),
(99, 'Hong Kong', NULL, 'Hong Kong', 'dd-mm-yyyy', 'hk', 'Asia/Hong_Kong'),
(100, 'Hungary', NULL, 'Hungary', 'yyyy-mm-dd', 'hu', 'Europe/Budapest'),
(101, 'Iceland', NULL, 'Iceland', 'dd-mm-yyyy', 'is', 'Atlantic/Reykjavik'),
(102, 'India', NULL, 'India', 'dd-mm-yyyy', 'in', 'Asia/Kolkata'),
(103, 'Indonesia', NULL, 'Indonesia', 'dd-mm-yyyy', 'id', 'Asia/Jakarta\nAsia/Jayapura\nAsia/Makassar\nAsia/Pontianak'),
(104, 'Iran, Islamic Republic of', NULL, 'Iran, Islamic Republic of', 'dd-mm-yyyy', 'ir', ''),
(105, 'Iraq', NULL, 'Iraq', 'dd-mm-yyyy', 'iq', 'Asia/Baghdad'),
(106, 'Ireland', NULL, 'Ireland', 'dd-mm-yyyy', 'ie', 'Europe/Dublin'),
(107, 'Isle of Man', NULL, 'Isle of Man', 'dd-mm-yyyy', 'im', 'Europe/London'),
(108, 'Israel', NULL, 'Israel', 'dd-mm-yyyy', 'il', 'Asia/Jerusalem'),
(109, 'Italy', NULL, 'Italy', 'dd-mm-yyyy', 'it', 'Europe/Rome'),
(110, 'Ivory Coast', NULL, 'Ivory Coast', 'dd-mm-yyyy', 'ci', ''),
(111, 'Jamaica', NULL, 'Jamaica', 'dd-mm-yyyy', 'jm', 'America/Jamaica'),
(112, 'Japan', NULL, 'Japan', 'dd-mm-yyyy', 'jp', 'Asia/Tokyo'),
(113, 'Jersey', NULL, 'Jersey', 'dd-mm-yyyy', 'je', 'Europe/London'),
(114, 'Jordan', NULL, 'Jordan', 'dd-mm-yyyy', 'jo', 'Asia/Amman'),
(115, 'Kazakhstan', NULL, 'Kazakhstan', 'dd-mm-yyyy', 'kz', 'Asia/Almaty\nAsia/Aqtau\nAsia/Aqtobe\nAsia/Oral\nAsia/Qyzylorda'),
(116, 'Kenya', NULL, 'Kenya', 'dd-mm-yyyy', 'ke', 'Africa/Nairobi'),
(117, 'Kiribati', NULL, 'Kiribati', 'dd-mm-yyyy', 'ki', 'Pacific/Enderbury\nPacific/Kiritimati\nPacific/Tarawa'),
(118, 'Korea, Democratic Peoples Republic of', NULL, 'Korea, Democratic Peoples Republic of', 'dd-mm-yyyy', 'kp', ''),
(119, 'Korea, Republic of', NULL, 'Korea, Republic of', 'dd-mm-yyyy', 'kr', ''),
(120, 'Kuwait', NULL, 'Kuwait', 'dd-mm-yyyy', 'kw', 'Asia/Kuwait'),
(121, 'Kyrgyzstan', NULL, 'Kyrgyzstan', 'dd-mm-yyyy', 'kg', 'Asia/Bishkek'),
(122, 'Lao Peoples Democratic Republic', NULL, 'Lao Peoples Democratic Republic', 'dd-mm-yyyy', 'la', ''),
(123, 'Latvia', NULL, 'Latvia', 'dd-mm-yyyy', 'lv', 'Europe/Riga'),
(124, 'Lebanon', NULL, 'Lebanon', 'dd-mm-yyyy', 'lb', 'Asia/Beirut'),
(125, 'Lesotho', NULL, 'Lesotho', 'dd-mm-yyyy', 'ls', 'Africa/Maseru'),
(126, 'Liberia', NULL, 'Liberia', 'dd-mm-yyyy', 'lr', 'Africa/Monrovia'),
(127, 'Libya', NULL, 'Libya', 'dd-mm-yyyy', 'ly', 'Africa/Tripoli'),
(128, 'Liechtenstein', NULL, 'Liechtenstein', 'dd-mm-yyyy', 'li', 'Europe/Vaduz'),
(129, 'Lithuania', NULL, 'Lithuania', 'yyyy-mm-dd', 'lt', 'Europe/Vilnius'),
(130, 'Luxembourg', NULL, 'Luxembourg', 'dd-mm-yyyy', 'lu', 'Europe/Luxembourg'),
(131, 'Macao', NULL, 'Macao', 'dd-mm-yyyy', 'mo', ''),
(132, 'Macedonia, Republic of', NULL, 'Macedonia, Republic of', 'dd-mm-yyyy', 'mk', ''),
(133, 'Madagascar', NULL, 'Madagascar', 'dd-mm-yyyy', 'mg', 'Indian/Antananarivo'),
(134, 'Malawi', NULL, 'Malawi', 'dd-mm-yyyy', 'mw', 'Africa/Blantyre'),
(135, 'Malaysia', NULL, 'Malaysia', 'dd-mm-yyyy', 'my', 'Asia/Kuala_Lumpur\nAsia/Kuching'),
(136, 'Maldives', NULL, 'Maldives', 'dd-mm-yyyy', 'mv', 'Indian/Maldives'),
(137, 'Mali', NULL, 'Mali', 'dd-mm-yyyy', 'ml', 'Africa/Bamako'),
(138, 'Malta', NULL, 'Malta', 'dd-mm-yyyy', 'mt', 'Europe/Malta'),
(139, 'Marshall Islands', NULL, 'Marshall Islands', 'dd-mm-yyyy', 'mh', 'Pacific/Kwajalein\nPacific/Majuro'),
(140, 'Martinique', NULL, 'Martinique', 'dd-mm-yyyy', 'mq', 'America/Martinique'),
(141, 'Mauritania', NULL, 'Mauritania', 'dd-mm-yyyy', 'mr', 'Africa/Nouakchott'),
(142, 'Mauritius', NULL, 'Mauritius', 'dd-mm-yyyy', 'mu', 'Indian/Mauritius'),
(143, 'Mayotte', NULL, 'Mayotte', 'dd-mm-yyyy', 'yt', 'Indian/Mayotte'),
(144, 'Mexico', NULL, 'Mexico', 'dd-mm-yyyy', 'mx', 'America/Bahia_Banderas\nAmerica/Cancun\nAmerica/Chihuahua\nAmerica/Hermosillo\nAmerica/Matamoros\nAmerica/Mazatlan\nAmerica/Merida\nAmerica/Mexico_City\nAmerica/Monterrey\nAmerica/Ojinaga\nAmerica/Santa_Isabel\nAmerica/Tijuana'),
(145, 'Micronesia, Federated States of', NULL, 'Micronesia, Federated States of', 'dd-mm-yyyy', 'fm', ''),
(146, 'Moldova, Republic of', NULL, 'Moldova, Republic of', 'dd-mm-yyyy', 'md', ''),
(147, 'Monaco', NULL, 'Monaco', 'dd-mm-yyyy', 'mc', 'Europe/Monaco'),
(148, 'Mongolia', NULL, 'Mongolia', 'yyyy-mm-dd', 'mn', 'Asia/Choibalsan\nAsia/Hovd\nAsia/Ulaanbaatar'),
(149, 'Montenegro', NULL, 'Montenegro', 'dd-mm-yyyy', 'me', 'Europe/Belgrade'),
(150, 'Montserrat', NULL, 'Montserrat', 'dd-mm-yyyy', 'ms', 'America/Montserrat'),
(151, 'Morocco', NULL, 'Morocco', 'dd-mm-yyyy', 'ma', 'Africa/Casablanca'),
(152, 'Mozambique', NULL, 'Mozambique', 'dd-mm-yyyy', 'mz', 'Africa/Maputo'),
(153, 'Myanmar', NULL, 'Myanmar', 'dd-mm-yyyy', 'mm', 'Asia/Rangoon'),
(154, 'Namibia', NULL, 'Namibia', 'dd-mm-yyyy', 'na', 'Africa/Windhoek'),
(155, 'Nauru', NULL, 'Nauru', 'dd-mm-yyyy', 'nr', 'Pacific/Nauru'),
(156, 'Nepal', NULL, 'Nepal', 'dd-mm-yyyy', 'np', 'Asia/Kathmandu'),
(157, 'Netherlands', NULL, 'Netherlands', 'dd-mm-yyyy', 'nl', 'Europe/Amsterdam'),
(158, 'New Caledonia', NULL, 'New Caledonia', 'dd-mm-yyyy', 'nc', 'Pacific/Noumea'),
(159, 'New Zealand', NULL, 'New Zealand', 'dd-mm-yyyy', 'nz', 'Pacific/Auckland\nPacific/Chatham'),
(160, 'Nicaragua', NULL, 'Nicaragua', 'dd-mm-yyyy', 'ni', 'America/Managua'),
(161, 'Niger', NULL, 'Niger', 'dd-mm-yyyy', 'ne', 'Africa/Niamey'),
(162, 'Nigeria', NULL, 'Nigeria', 'dd-mm-yyyy', 'ng', 'Africa/Lagos'),
(163, 'Niue', NULL, 'Niue', 'dd-mm-yyyy', 'nu', 'Pacific/Niue'),
(164, 'Norfolk Island', NULL, 'Norfolk Island', 'dd-mm-yyyy', 'nf', 'Pacific/Norfolk'),
(165, 'Northern Mariana Islands', NULL, 'Northern Mariana Islands', 'dd-mm-yyyy', 'mp', 'Pacific/Saipan'),
(166, 'Norway', NULL, 'Norway', 'dd-mm-yyyy', 'no', 'Europe/Oslo'),
(167, 'Oman', NULL, 'Oman', 'dd-mm-yyyy', 'om', 'Asia/Muscat'),
(168, 'Pakistan', NULL, 'Pakistan', 'dd-mm-yyyy', 'pk', 'Asia/Karachi'),
(169, 'Palau', NULL, 'Palau', 'mm-dd-yyyy', 'pw', 'Pacific/Palau'),
(170, 'Palestinian Territory, Occupied', NULL, 'Palestinian Territory, Occupied', 'dd-mm-yyyy', 'ps', ''),
(171, 'Panama', NULL, 'Panama', 'dd-mm-yyyy', 'pa', 'America/Panama'),
(172, 'Papua New Guinea', NULL, 'Papua New Guinea', 'dd-mm-yyyy', 'pg', 'Pacific/Port_Moresby'),
(173, 'Paraguay', NULL, 'Paraguay', 'dd-mm-yyyy', 'py', 'America/Asuncion'),
(174, 'Peru', NULL, 'Peru', 'dd-mm-yyyy', 'pe', 'America/Lima'),
(175, 'Philippines', NULL, 'Philippines', 'mm-dd-yyyy', 'ph', 'Asia/Manila'),
(176, 'Pitcairn', NULL, 'Pitcairn', 'dd-mm-yyyy', 'pn', 'Pacific/Pitcairn'),
(177, 'Poland', NULL, 'Poland', 'dd-mm-yyyy', 'pl', 'Europe/Warsaw'),
(178, 'Portugal', NULL, 'Portugal', 'dd-mm-yyyy', 'pt', 'Atlantic/Azores\nAtlantic/Madeira\nEurope/Lisbon'),
(179, 'Puerto Rico', NULL, 'Puerto Rico', 'dd-mm-yyyy', 'pr', 'America/Puerto_Rico'),
(180, 'Qatar', NULL, 'Qatar', 'dd-mm-yyyy', 'qa', 'Asia/Qatar'),
(181, 'Réunion', NULL, 'Réunion', 'dd-mm-yyyy', 're', ''),
(182, 'Romania', NULL, 'Romania', 'dd-mm-yyyy', 'ro', 'Europe/Bucharest'),
(183, 'Russian Federation', NULL, 'Russian Federation', 'dd-mm-yyyy', 'ru', ''),
(184, 'Rwanda', NULL, 'Rwanda', 'dd-mm-yyyy', 'rw', 'Africa/Kigali'),
(185, 'Saint Barthélemy', NULL, 'Saint Barthélemy', 'dd-mm-yyyy', 'bl', ''),
(186, 'Saint Helena, Ascension and Tristan da Cunha', NULL, 'Saint Helena, Ascension and Tristan da Cunha', 'dd-mm-yyyy', 'sh', ''),
(187, 'Saint Kitts and Nevis', NULL, 'Saint Kitts and Nevis', 'dd-mm-yyyy', 'kn', 'America/St_Kitts'),
(188, 'Saint Lucia', NULL, 'Saint Lucia', 'dd-mm-yyyy', 'lc', 'America/St_Lucia'),
(189, 'Saint Martin (French part)', NULL, 'Saint Martin (French part)', 'dd-mm-yyyy', 'mf', ''),
(190, 'Saint Pierre and Miquelon', NULL, 'Saint Pierre and Miquelon', 'dd-mm-yyyy', 'pm', ''),
(191, 'Saint Vincent and the Grenadines', NULL, 'Saint Vincent and the Grenadines', 'dd-mm-yyyy', 'vc', 'America/St_Vincent'),
(192, 'Samoa', NULL, 'Samoa', 'dd-mm-yyyy', 'ws', 'Pacific/Apia'),
(193, 'San Marino', NULL, 'San Marino', 'dd-mm-yyyy', 'sm', 'Europe/Rome'),
(194, 'Sao Tome and Principe', NULL, 'Sao Tome and Principe', 'dd-mm-yyyy', 'st', ''),
(195, 'Saudi Arabia', NULL, 'Saudi Arabia', 'dd-mm-yyyy', 'sa', 'Asia/Riyadh'),
(196, 'Senegal', NULL, 'Senegal', 'dd-mm-yyyy', 'sn', 'Africa/Dakar'),
(197, 'Serbia', NULL, 'Serbia', 'dd-mm-yyyy', 'rs', 'Europe/Belgrade'),
(198, 'Seychelles', NULL, 'Seychelles', 'dd-mm-yyyy', 'sc', 'Indian/Mahe'),
(199, 'Sierra Leone', NULL, 'Sierra Leone', 'dd-mm-yyyy', 'sl', 'Africa/Freetown'),
(200, 'Singapore', NULL, 'Singapore', 'dd-mm-yyyy', 'sg', 'Asia/Singapore'),
(201, 'Sint Maarten (Dutch part)', NULL, 'Sint Maarten (Dutch part)', 'dd-mm-yyyy', 'sx', ''),
(202, 'Slovakia', NULL, 'Slovakia', 'dd-mm-yyyy', 'sk', 'Europe/Prague'),
(203, 'Slovenia', NULL, 'Slovenia', 'dd-mm-yyyy', 'si', 'Europe/Belgrade'),
(204, 'Solomon Islands', NULL, 'Solomon Islands', 'dd-mm-yyyy', 'sb', 'Pacific/Guadalcanal'),
(205, 'Somalia', NULL, 'Somalia', 'dd-mm-yyyy', 'so', 'Africa/Mogadishu'),
(206, 'South Africa', NULL, 'South Africa', 'yyyy-mm-dd', 'za', 'Africa/Johannesburg'),
(207, 'South Georgia and the South Sandwich Islands', NULL, 'South Georgia and the South Sandwich Islands', 'dd-mm-yyyy', 'gs', ''),
(208, 'South Sudan', NULL, 'South Sudan', 'dd-mm-yyyy', 'ss', 'Africa/Juba'),
(209, 'Spain', NULL, 'Spain', 'dd-mm-yyyy', 'es', 'Africa/Ceuta\nAtlantic/Canary\nEurope/Madrid'),
(210, 'Sri Lanka', NULL, 'Sri Lanka', 'dd-mm-yyyy', 'lk', 'Asia/Colombo'),
(211, 'Sudan', NULL, 'Sudan', 'dd-mm-yyyy', 'sd', 'Africa/Khartoum'),
(212, 'Suriname', NULL, 'Suriname', 'dd-mm-yyyy', 'sr', 'America/Paramaribo'),
(213, 'Svalbard and Jan Mayen', NULL, 'Svalbard and Jan Mayen', 'dd-mm-yyyy', 'sj', ''),
(214, 'Swaziland', NULL, 'Swaziland', 'dd-mm-yyyy', 'sz', 'Africa/Mbabane'),
(215, 'Sweden', NULL, 'Sweden', 'dd-mm-yyyy', 'se', 'Europe/Stockholm'),
(216, 'Switzerland', NULL, 'Switzerland', 'dd-mm-yyyy', 'ch', 'Europe/Zurich'),
(217, 'Syrian Arab Republic', NULL, 'Syrian Arab Republic', 'dd-mm-yyyy', 'sy', ''),
(218, 'Taiwan', NULL, 'Taiwan', 'yyyy-mm-dd', 'tw', ''),
(219, 'Tajikistan', NULL, 'Tajikistan', 'dd-mm-yyyy', 'tj', 'Asia/Dushanbe'),
(220, 'Tanzania, United Republic of', NULL, 'Tanzania, United Republic of', 'dd-mm-yyyy', 'tz', ''),
(221, 'Thailand', NULL, 'Thailand', 'dd-mm-yyyy', 'th', 'Asia/Bangkok'),
(222, 'Timor-Leste', NULL, 'Timor-Leste', 'dd-mm-yyyy', 'tl', ''),
(223, 'Togo', NULL, 'Togo', 'dd-mm-yyyy', 'tg', 'Africa/Lome'),
(224, 'Tokelau', NULL, 'Tokelau', 'dd-mm-yyyy', 'tk', 'Pacific/Fakaofo'),
(225, 'Tonga', NULL, 'Tonga', 'dd-mm-yyyy', 'to', 'Pacific/Tongatapu'),
(226, 'Trinidad and Tobago', NULL, 'Trinidad and Tobago', 'dd-mm-yyyy', 'tt', 'America/Port_of_Spain'),
(227, 'Tunisia', NULL, 'Tunisia', 'dd-mm-yyyy', 'tn', 'Africa/Tunis'),
(228, 'Turkey', NULL, 'Turkey', 'dd-mm-yyyy', 'tr', 'Europe/Istanbul'),
(229, 'Turkmenistan', NULL, 'Turkmenistan', 'dd-mm-yyyy', 'tm', 'Asia/Ashgabat'),
(230, 'Turks and Caicos Islands', NULL, 'Turks and Caicos Islands', 'dd-mm-yyyy', 'tc', ''),
(231, 'Tuvalu', NULL, 'Tuvalu', 'dd-mm-yyyy', 'tv', 'Pacific/Funafuti'),
(232, 'Uganda', NULL, 'Uganda', 'dd-mm-yyyy', 'ug', 'Africa/Kampala'),
(233, 'Ukraine', NULL, 'Ukraine', 'dd-mm-yyyy', 'ua', 'Europe/Kiev\nEurope/Simferopol\nEurope/Uzhgorod\nEurope/Zaporozhye'),
(234, 'United Arab Emirates', NULL, 'United Arab Emirates', 'dd-mm-yyyy', 'ae', 'Asia/Dubai'),
(235, 'United Kingdom', NULL, 'United Kingdom', 'dd-mm-yyyy', 'gb', 'Europe/London'),
(236, 'United States', NULL, 'United States', 'mm-dd-yyyy', 'us', 'America/Adak\nAmerica/Anchorage\nAmerica/Boise\nAmerica/Chicago\nAmerica/Denver\nAmerica/Detroit\nAmerica/Indiana/Indianapolis\nAmerica/Indiana/Knox\nAmerica/Indiana/Marengo\nAmerica/Indiana/Petersburg\nAmerica/Indiana/Tell_City\nAmerica/Indiana/Vevay\nAmerica/Indiana/Vincennes\nAmerica/Indiana/Winamac\nAmerica/Juneau\nAmerica/Kentucky/Louisville\nAmerica/Kentucky/Monticello\nAmerica/Los_Angeles\nAmerica/Menominee\nAmerica/Metlakatla\nAmerica/New_York\nAmerica/Nome\nAmerica/North_Dakota/Beulah\nAmerica/North_Dakota/Center\nAmerica/North_Dakota/New_Salem\nAmerica/Phoenix\nAmerica/Denver\nAmerica/Sitka\nAmerica/Yakutat\nPacific/Honolulu'),
(237, 'United States Minor Outlying Islands', NULL, 'United States Minor Outlying Islands', 'dd-mm-yyyy', 'um', ''),
(238, 'Uruguay', NULL, 'Uruguay', 'dd-mm-yyyy', 'uy', 'America/Montevideo'),
(239, 'Uzbekistan', NULL, 'Uzbekistan', 'dd-mm-yyyy', 'uz', 'Asia/Samarkand\nAsia/Tashkent'),
(240, 'Vanuatu', NULL, 'Vanuatu', 'dd-mm-yyyy', 'vu', 'Pacific/Efate'),
(241, 'Venezuela, Bolivarian Republic of', NULL, 'Venezuela, Bolivarian Republic of', 'dd-mm-yyyy', 've', ''),
(242, 'Vietnam', NULL, 'Vietnam', 'dd-mm-yyyy', 'vn', ''),
(243, 'Virgin Islands, British', NULL, 'Virgin Islands, British', 'dd-mm-yyyy', 'vg', ''),
(244, 'Virgin Islands, U.S.', NULL, 'Virgin Islands, U.S.', 'dd-mm-yyyy', 'vi', ''),
(245, 'Wallis and Futuna', NULL, 'Wallis and Futuna', 'dd-mm-yyyy', 'wf', ''),
(246, 'Western Sahara', NULL, 'Western Sahara', 'dd-mm-yyyy', 'eh', 'Africa/El_Aaiun'),
(247, 'Yemen', NULL, 'Yemen', 'dd-mm-yyyy', 'ye', 'Asia/Aden'),
(248, 'Zambia', NULL, 'Zambia', 'dd-mm-yyyy', 'zm', 'Africa/Lusaka'),
(249, 'Zimbabwe', NULL, 'Zimbabwe', 'dd-mm-yyyy', 'zw', 'Africa/Harare');

-- --------------------------------------------------------

--
-- Struktur dari tabel `currency`
--

CREATE TABLE IF NOT EXISTS `currency` (
  `id` int(3) NOT NULL,
  `currency_abbr` varchar(3) NOT NULL,
  `currency_symbol` varchar(2) NOT NULL,
  `currency` varchar(50) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `currency`
--

INSERT INTO `currency` (`id`, `currency_abbr`, `currency_symbol`, `currency`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'IDR', 'Rp', 'Rupiah', '2016-04-16', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `id` int(3) NOT NULL,
  `cust_code` varchar(9) NOT NULL,
  `cust_name` varchar(200) NOT NULL,
  `pt` varchar(200) NOT NULL,
  `npwp` varchar(30) NOT NULL,
  `cust_type` varchar(30) DEFAULT NULL,
  `default_tax` int(3) NOT NULL,
  `account_no` varchar(30) DEFAULT NULL,
  `bank_id` int(3) DEFAULT NULL,
  `account_name` varchar(30) DEFAULT NULL,
  `currency_id` int(3) NOT NULL,
  `top` int(3) NOT NULL,
  `country` varchar(30) DEFAULT NULL,
  `note` text,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `cust_addr`
--

CREATE TABLE IF NOT EXISTS `cust_addr` (
  `id` int(3) NOT NULL,
  `cust_code` varchar(9) NOT NULL,
  `cust_id` int(9) DEFAULT NULL,
  `name` varchar(200) NOT NULL,
  `addr_type` varchar(100) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `fax` varchar(15) DEFAULT NULL,
  `address` text,
  `zip_code` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `delivery`
--

CREATE TABLE IF NOT EXISTS `delivery` (
  `id` int(11) NOT NULL,
  `received_code` varchar(50) NOT NULL,
  `delivery_note` varchar(20) NOT NULL,
  `doc_issued_date` date NOT NULL,
  `document_type` varchar(50) DEFAULT NULL,
  `wood_from` varchar(50) DEFAULT NULL,
  `wood_domicile` varchar(50) NOT NULL,
  `wood_resource_id` int(11) NOT NULL,
  `wood_type_id` int(3) DEFAULT NULL,
  `total_log` int(5) DEFAULT NULL,
  `total_volume` decimal(20,4) DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `purchase_note` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `delivery`
--

INSERT INTO `delivery` (`id`, `received_code`, `delivery_note`, `doc_issued_date`, `document_type`, `wood_from`, `wood_domicile`, `wood_resource_id`, `wood_type_id`, `total_log`, `total_volume`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `input_date`, `purchase_note`) VALUES
(1, '1', '0001', '2016-10-20', '1', NULL, 'Kalimantan', 1, 1, 1000, '2000.0000', 'Michael', NULL, NULL, NULL, NULL, '2016-08-27', NULL),
(2, '2', '0002', '2016-10-20', '1', NULL, 'Kalimantan', 1, 1, 2000, '10000.0000', 'Michael', NULL, NULL, NULL, NULL, '2016-08-27', NULL),
(3, '3', '0009', '2016-10-20', '1', NULL, 'JAWA', 1, 1, 1000, '1000.0000', 'Michael', NULL, NULL, NULL, NULL, '2016-08-30', NULL),
(4, '4', '00P00', '2016-10-20', '1', NULL, 'MINANGKABAU', 1, 1, 0, '0.0000', 'Michael', NULL, NULL, NULL, NULL, '2016-08-31', NULL),
(5, '5', 'ABC001', '2016-10-20', '2', NULL, 'SUMATERA', 1, 1, 1000, '1000.0000', 'Michael', NULL, NULL, NULL, NULL, '2016-08-31', NULL),
(6, '6', '123_123', '2016-10-20', '1', NULL, 'LOCAL', 1, 1, 3200, '12000000.0000', 'Michael', NULL, NULL, NULL, NULL, '2016-09-03', NULL),
(7, '7', 'XYX_123_10', '2016-10-20', '1', NULL, 'LOCAL', 1, 1, 100, '1000000.0000', 'Michael', NULL, NULL, NULL, NULL, '2016-09-03', NULL),
(8, '8', '0008/BL/03/09/16', '2016-10-20', '1', NULL, 'JEMBER', 2, 1, 7399, '564609.0000', 'Michael', NULL, NULL, NULL, NULL, '2016-09-03', NULL),
(9, '9', '28102016NEW', '2016-10-28', 'INVOICE', NULL, 'KALIMANTAN', 2, 2, 100, '0.1000', 'Michael', NULL, NULL, NULL, NULL, '2016-10-28', NULL),
(10, '10', 'ABC31102016', '2016-10-31', 'INVOICE', NULL, 'KALIMANTAN', 1, 1, 10, '10.0000', 'Michael', '2016-12-03', 'Michael', NULL, NULL, '2016-10-31', NULL),
(11, '11', 'TESPRINT', '2016-11-04', 'INVOICE', NULL, 'SOLO', 1, 1, 100, '0.2000', 'Michael', NULL, NULL, NULL, NULL, '2016-11-04', NULL),
(12, '12', 'TESLAPORAN', '2016-11-14', 'INVOICE', NULL, 'JAKARTA', 1, 1, 10001, '0.4600', 'Michael', NULL, NULL, NULL, NULL, '2016-11-14', NULL),
(13, '13', '000112', '2016-11-30', 'INVOICE', NULL, 'MAKASSAR', 1, 1, 100, '0.8900', 'Michael', NULL, NULL, NULL, NULL, '2016-11-30', NULL),
(14, '14', 'TES', '2016-11-30', 'INVOICE', NULL, 'MAKASSAAR', 1, 1, 1, '10.0000', 'Michael', NULL, NULL, NULL, NULL, '2016-11-30', NULL),
(15, '15', '567890', '2016-12-03', 'INVOICE', NULL, 'MARITIM', 1, 1, 100, '1.0000', 'Michael', NULL, NULL, NULL, NULL, '2016-12-03', NULL),
(16, '16', '0002/BL/03/12/16', '2016-12-03', 'SURAT JALAN', NULL, 'JEMBER', 2, 1, 4113, '30.7700', 'Michael', NULL, NULL, NULL, NULL, '2016-12-03', NULL),
(17, '17', 'ABC', '2016-12-06', 'ABC', NULL, 'ABC', 2, 1, 0, '0.0000', 'Michael', NULL, NULL, NULL, NULL, '2016-12-06', NULL),
(18, '0001/BL/03/01/17', '001', '2017-01-03', 'INVOICE', NULL, 'JAWA TIMUR', 2, 1, 10, '3.0000', 'Michael', NULL, NULL, NULL, NULL, '2017-01-03', NULL),
(19, '0002/BL/03/01/17', '0001', '2017-01-03', 'SURAT JALAN', NULL, 'JAWA TIMUR', 2, 1, 100, '34.5000', 'Michael', NULL, NULL, NULL, NULL, '2017-01-03', NULL),
(20, '0003/BL/03/01/17', '1', '2017-01-03', '1', NULL, '1', 1, 1, 123, '123.0000', 'Michael', NULL, NULL, NULL, NULL, '2017-01-03', NULL),
(21, '0004/BL/07/01/17', 'ABC123', '2017-01-07', 'INVOICE', NULL, 'ANC', 1, 1, 999, '9000.0000', 'Michael', NULL, NULL, NULL, NULL, '2017-01-07', NULL),
(22, '0005/BL/08/01/17', '09124', '2017-01-08', 'INVOICE', NULL, 'KALIMANTAN', 1, 1, 0, '0.0000', 'Michael', NULL, NULL, NULL, NULL, '2017-01-08', NULL),
(23, '0001/BL/07/03/17', 'TESTING', '2017-03-07', 'TESTING', NULL, 'TESTING', 1, 2, 100, '200.0000', 'admin', '2017-03-07', 'admin', NULL, NULL, '2017-03-07', NULL),
(24, '0002/BL/08/03/17', 'TES', '2017-03-08', 'TES', NULL, 'ETS', 2, 1, 100, '1.0000', 'admin', NULL, NULL, NULL, NULL, '2017-03-08', NULL),
(25, '0003/BL/09/03/17', 'TESSUB', '2017-03-09', 'TESSUB', NULL, 'TESSUB', 1, 1, 1001, '2.0000', 'admin', NULL, NULL, NULL, NULL, '2017-03-09', NULL),
(26, '0004/BL/09/03/17', '12', '2017-03-09', '123', NULL, '12', 1, 1, 12, '12.0000', 'admin', NULL, NULL, NULL, NULL, '2017-03-09', NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `department`
--

CREATE TABLE IF NOT EXISTS `department` (
  `id` varchar(10) NOT NULL,
  `NAME` varchar(20) DEFAULT NULL,
  `division_id` varchar(10) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(30) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(30) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `division`
--

CREATE TABLE IF NOT EXISTS `division` (
  `id` varchar(10) NOT NULL,
  `NAME` varchar(20) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(30) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(30) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `division`
--

INSERT INTO `division` (`id`, `NAME`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) VALUES
('DIV001', 'Finance', '2017-01-09', '', '2017-01-09', '', NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `document_type`
--

CREATE TABLE IF NOT EXISTS `document_type` (
  `id` int(3) NOT NULL,
  `document_type` varchar(50) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `document_type`
--

INSERT INTO `document_type` (`id`, `document_type`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'Surat Jalan', '2016-04-16', 'timotius', NULL, NULL, NULL, NULL),
(2, 'Ijin Import', '2016-04-16', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

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
  `deleted_by` varchar(25) DEFAULT NULL,
  `total_volume` decimal(20,4) DEFAULT NULL,
  `confirm_date` date DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `dry_in`
--

INSERT INTO `dry_in` (`id`, `dry_in_code`, `date_in`, `chamber_id`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `total_volume`, `confirm_date`, `status`) VALUES
(7, 'M/2017/01/0001', '2017-01-03 12:48:42', 1, '2017-01-03', 'timotius', '2017-01-03', 'timotius', NULL, NULL, '250000.0000', '2017-01-03', 'FINAL'),
(8, 'M/2017/01/0002', '2017-01-19 12:34:46', 1, '2017-01-03', 'timotius', '2017-01-19', 'timotius', NULL, NULL, '307500.0300', '2017-01-19', 'FINAL'),
(9, 'M/2017/02/0003', '2017-02-20 14:08:00', 1, '2017-02-20', 'timotius', NULL, NULL, NULL, NULL, '0.0900', NULL, 'COMPLETED');

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

--
-- Dumping data untuk tabel `dry_in_pallet`
--

INSERT INTO `dry_in_pallet` (`id`, `dry_in_code`, `pallet_card_code`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(19, 'M/2017/01/0001', '0002/BL/03/01/17/0001', '2017-01-03', 'timotius', NULL, NULL, NULL, NULL),
(20, 'M/2017/01/0002', '0002/BL/03/01/17/0002', '2017-01-03', 'timotius', NULL, NULL, NULL, NULL),
(21, 'M/2017/01/0002', '0002/BL/03/01/17/0003', '2017-01-03', 'timotius', NULL, NULL, NULL, NULL),
(22, 'M/2017/01/0002', '0003/BL/03/01/17/0001', '2017-01-03', 'timotius', NULL, NULL, NULL, NULL),
(23, 'M/2017/01/0002', '0003/BL/03/01/17/0002', '2017-01-03', 'timotius', NULL, NULL, NULL, NULL),
(24, 'M/2017/02/0003', '0003/BL/03/01/17/0100', '2017-02-20', 'timotius', NULL, NULL, NULL, NULL),
(25, 'M/2017/02/0003', '0004/BL/07/01/17/0001', '2017-02-20', 'timotius', NULL, NULL, NULL, NULL),
(26, 'M/2017/02/0003', '0004/BL/07/01/17/0002', '2017-02-20', 'timotius', NULL, NULL, NULL, NULL),
(27, 'M/2017/02/0003', '0004/BL/07/01/17/0003', '2017-02-20', 'timotius', NULL, NULL, NULL, NULL);

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
  `deleted_by` varchar(25) DEFAULT NULL,
  `total_volume` decimal(20,4) DEFAULT NULL,
  `confirm_date` date DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `dry_out`
--

INSERT INTO `dry_out` (`id`, `dry_out_code`, `date_out`, `chamber_id`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `total_volume`, `confirm_date`, `status`) VALUES
(7, 'K/2017/00/0001', '2017-01-03 12:48:42', 1, '2017-01-03', 'timotius', '2017-01-03', 'timotius', NULL, NULL, '250000.0000', '2017-01-03', 'FINAL'),
(8, 'K/2017/00/0002', '2017-01-19 12:34:46', 1, '2017-01-03', 'timotius', '2017-01-19', 'timotius', NULL, NULL, '307500.0300', '2017-01-19', 'FINAL'),
(9, 'K/2017/01/0003', '2017-02-20 14:08:00', 1, '2017-02-20', 'timotius', '2017-02-20', 'timotius', NULL, NULL, '60250.0000', NULL, 'COMPLETED');

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

--
-- Dumping data untuk tabel `dry_out_pallet`
--

INSERT INTO `dry_out_pallet` (`id`, `dry_out_code`, `pallet_card_code`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(14, 'K/2017/00/0001', '0002/BL/03/01/17/0001', '2017-01-03', 'timotius', NULL, NULL, NULL, NULL),
(15, 'K/2017/00/0002', '0002/BL/03/01/17/0002', '2017-01-03', 'timotius', NULL, NULL, NULL, NULL),
(16, 'K/2017/00/0002', '0002/BL/03/01/17/0003', '2017-01-03', 'timotius', NULL, NULL, NULL, NULL),
(17, 'K/2017/00/0002', '0003/BL/03/01/17/0001', '2017-01-03', 'timotius', NULL, NULL, NULL, NULL),
(18, 'K/2017/00/0002', '0003/BL/03/01/17/0002', '2017-01-03', 'timotius', NULL, NULL, NULL, NULL),
(19, 'K/2017/01/0003', '0003/BL/03/01/17/0100', '2017-02-20', 'timotius', '2017-02-20', 'timotius', '2017-02-20', 'timotius'),
(20, 'K/2017/01/0003', '0004/BL/07/01/17/0001', '2017-02-20', 'timotius', '2017-02-20', 'timotius', NULL, NULL),
(21, 'K/2017/01/0003', '0004/BL/07/01/17/0002', '2017-02-20', 'timotius', '2017-02-20', 'timotius', NULL, NULL),
(22, 'K/2017/01/0003', '0004/BL/07/01/17/0003', '2017-02-20', 'timotius', '2017-02-20', 'timotius', NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `employee`
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
  `employee_status_id` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `employee`
--

INSERT INTO `employee` (`employee_id`, `employee_name`, `employee_type_id`, `address`, `city`, `birth_date`, `hire_date`, `email`, `phone_number`, `salary`, `department_id`, `gender_id`, `position_id`, `marital_id`, `employee_status_id`) VALUES
('2011111111', 'Iwan', 'POS0002', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'POS0002', NULL, NULL),
('2012345678', 'Mikael', 'EMPTYP01', 'Jatinegara', 'Jakarta', '2016-04-18', '2015-07-18', NULL, '123456798012', 0, 'DEPT0001', NULL, 'POS0001', '1', '1'),
('2013026132', 'Irvan Wilianto', 'EMPTYP01', 'Harapan Indah', 'Bekasi', '1991-08-14', '2013-12-02', 'irvan.wili@gmail.com', '081288370090', 10000000, 'DEPT0001', '1', 'POS0001', '1', '1');

-- --------------------------------------------------------

--
-- Struktur dari tabel `employee_status`
--

CREATE TABLE IF NOT EXISTS `employee_status` (
  `employee_status_id` varchar(10) NOT NULL,
  `employee_status_name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `employee_status`
--

INSERT INTO `employee_status` (`employee_status_id`, `employee_status_name`) VALUES
('1', 'Aktif'),
('2', 'Non Aktif');

-- --------------------------------------------------------

--
-- Struktur dari tabel `employee_type`
--

CREATE TABLE IF NOT EXISTS `employee_type` (
  `id` varchar(10) NOT NULL,
  `NAME` varchar(20) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(30) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(30) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `emp_position`
--

CREATE TABLE IF NOT EXISTS `emp_position` (
  `id` int(11) NOT NULL,
  `employee_id` varchar(10) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `probation` int(11) DEFAULT NULL,
  `position_id` varchar(10) DEFAULT NULL,
  `employee_type_id` varchar(10) DEFAULT NULL,
  `reference_doc` varchar(512) DEFAULT NULL,
  `note` varchar(512) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(30) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(30) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `gender`
--

CREATE TABLE IF NOT EXISTS `gender` (
  `gender_id` varchar(1) NOT NULL,
  `name` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `gender`
--

INSERT INTO `gender` (`gender_id`, `name`) VALUES
('1', 'Laki-laki'),
('2', 'Perempuan');

-- --------------------------------------------------------

--
-- Struktur dari tabel `grade`
--

CREATE TABLE IF NOT EXISTS `grade` (
  `id` int(11) NOT NULL,
  `product_category_id` int(11) NOT NULL,
  `grade` varchar(50) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(25) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `grade`
--

INSERT INTO `grade` (`id`, `product_category_id`, `grade`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) VALUES
(1, 1, 'A', NULL, NULL, NULL, NULL, NULL, NULL),
(2, 1, 'B', NULL, NULL, NULL, NULL, NULL, NULL),
(3, 1, 'All', NULL, NULL, NULL, NULL, NULL, NULL),
(4, 1, 'AFB', NULL, NULL, NULL, NULL, NULL, NULL),
(5, 2, 'A', NULL, NULL, NULL, NULL, NULL, NULL),
(6, 2, 'B', NULL, NULL, NULL, NULL, NULL, NULL),
(7, 2, 'All', NULL, NULL, NULL, NULL, NULL, NULL),
(8, 2, 'AFB', NULL, NULL, NULL, NULL, NULL, NULL),
(9, 3, 'A', NULL, NULL, NULL, NULL, NULL, NULL),
(10, 3, 'B', NULL, NULL, NULL, NULL, NULL, NULL),
(11, 3, 'A1', NULL, NULL, NULL, NULL, NULL, NULL),
(12, 3, 'BNP', NULL, NULL, NULL, NULL, NULL, NULL),
(13, 3, 'C', NULL, NULL, NULL, NULL, NULL, NULL),
(100, 1, 'Afkir', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `group_screen`
--

CREATE TABLE IF NOT EXISTS `group_screen` (
  `id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `screen_id` int(11) NOT NULL,
  `screen_name` varchar(30) NOT NULL,
  `right_access` varchar(1) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(30) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(30) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `group_shift`
--

CREATE TABLE IF NOT EXISTS `group_shift` (
  `id` int(3) NOT NULL,
  `group_shift_code` varchar(9) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL,
  `line_code` varchar(9) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `group_shift`
--

INSERT INTO `group_shift` (`id`, `group_shift_code`, `description`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `delete_by`, `line_code`) VALUES
(1, 'gs001', 'Group Shift 1', NULL, NULL, NULL, NULL, NULL, NULL, '1001'),
(2, 'gs002', 'Group Shift 2', NULL, NULL, NULL, NULL, NULL, NULL, '1002'),
(3, 'gs003', 'Group Shift 3', NULL, NULL, NULL, NULL, NULL, NULL, '1003'),
(4, 'gs004', 'Group Shift 4', NULL, NULL, NULL, NULL, NULL, NULL, '1004'),
(5, 'gs005', 'Downsize 1', NULL, NULL, NULL, NULL, NULL, NULL, '1001'),
(6, 'gs006', 'Downsize 2', NULL, NULL, NULL, NULL, NULL, NULL, '1002');

-- --------------------------------------------------------

--
-- Struktur dari tabel `group_shift_dtl`
--

CREATE TABLE IF NOT EXISTS `group_shift_dtl` (
  `id` int(3) NOT NULL,
  `group_shift_code` varchar(9) NOT NULL,
  `emp_code` varchar(9) DEFAULT NULL,
  `leader_flag` int(1) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `import_fingerprint`
--

CREATE TABLE IF NOT EXISTS `import_fingerprint` (
  `id` int(11) NOT NULL,
  `file_name` varchar(200) NOT NULL,
  `date` date NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `inventory`
--

CREATE TABLE IF NOT EXISTS `inventory` (
  `id` int(9) NOT NULL,
  `product_code` varchar(20) DEFAULT NULL,
  `qty` decimal(20,4) DEFAULT NULL,
  `warehouse` int(1) DEFAULT NULL,
  `stock_date` date DEFAULT NULL,
  `inventory_log_id` int(12) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_bu` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `inventory`
--

INSERT INTO `inventory` (`id`, `product_code`, `qty`, `warehouse`, `stock_date`, `inventory_log_id`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_bu`) VALUES
(1, 'B-0001              ', '0.0000', 0, '2017-01-19', 43, '2017-01-08', 'ADMIN', '2017-01-19', 'ADMIN', NULL, NULL),
(2, 'B-0002              ', '20.0000', 0, '2017-01-19', 47, '2017-01-08', 'ADMIN', '2017-01-19', 'ADMIN', NULL, NULL),
(3, 'B-0003              ', '40.0000', 0, '2017-01-19', 49, '2017-01-08', 'ADMIN', '2017-01-19', 'ADMIN', NULL, NULL),
(4, 'B-0001              ', '200.0000', 1, '2017-01-19', 51, '2017-01-08', 'ADMIN', '2017-01-19', 'ADMIN', NULL, NULL),
(13, 'PDC009-1', '90.0000', 0, '2016-11-30', 52, '2016-11-25', 'ADMIN', '2016-11-30', 'ADMIN', NULL, NULL),
(14, 'PDC009-2', '90.0000', 0, '2016-11-30', 52, '2016-11-25', 'ADMIN', '2016-11-30', 'ADMIN', NULL, NULL),
(15, 'PDC009-7', '90.0000', 0, '2016-11-30', 52, '2016-11-25', 'ADMIN', '2016-11-30', 'ADMIN', NULL, NULL),
(16, 'PDC009-8', '90.0000', 0, '2016-11-30', 52, '2016-11-25', 'ADMIN', '2016-11-30', 'ADMIN', NULL, NULL),
(17, 'B-0025              ', '2.0000', 0, '2017-01-19', 42, '2017-01-19', 'ADMIN', '2017-01-19', 'ADMIN', NULL, NULL),
(18, 'B-0002              ', '0.0000', 1, '2017-01-19', 55, '2017-01-19', 'ADMIN', '2017-01-19', 'ADMIN', NULL, NULL),
(19, 'B-0003              ', '0.0000', 1, '2017-01-19', 57, '2017-01-19', 'ADMIN', '2017-01-19', 'ADMIN', NULL, NULL),
(20, 'K-0001              ', '200.0000', 1, '2017-01-19', 52, '2017-01-19', 'ADMIN', '2017-01-19', 'ADMIN', NULL, NULL),
(21, 'K-0002              ', '40.0000', 1, '2017-01-19', 56, '2017-01-19', 'ADMIN', '2017-01-19', 'ADMIN', NULL, NULL),
(22, 'K-0003              ', '20.0000', 1, '2017-01-19', 58, '2017-01-19', 'ADMIN', '2017-01-19', 'ADMIN', NULL, NULL),
(23, 'PDC009-3', '400.0000', 0, '2017-01-19', 58, '2017-01-19', 'ADMIN', '2017-01-19', 'ADMIN', NULL, NULL),
(24, 'PDC009-19', '100.0000', 0, '2017-01-19', 58, '2017-01-19', 'ADMIN', '2017-01-19', 'ADMIN', NULL, NULL),
(25, 'PDC009-20', '15.1200', 0, '2017-01-19', 58, '2017-01-19', 'ADMIN', '2017-01-19', NULL, NULL, NULL),
(26, 'PDC009-4', '1.0000', 0, '2017-01-19', 58, '2017-01-19', 'ADMIN', '2017-01-19', NULL, NULL, NULL),
(27, 'PDC009-17', '123.1230', 0, '2017-01-19', 58, '2017-01-19', 'ADMIN', '2017-01-19', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `inventory_log`
--

CREATE TABLE IF NOT EXISTS `inventory_log` (
  `id` int(9) NOT NULL,
  `product_code` varchar(20) DEFAULT NULL,
  `warehouse` int(1) DEFAULT NULL,
  `prev_stock` decimal(20,4) DEFAULT NULL,
  `plus_stock` decimal(20,4) DEFAULT NULL,
  `min_stock` decimal(20,4) DEFAULT NULL,
  `curr_stock` decimal(20,4) DEFAULT NULL,
  `prev_stock_date` date DEFAULT NULL,
  `curr_stock_date` date DEFAULT NULL,
  `confirm_code` varchar(5) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `inventory_log`
--

INSERT INTO `inventory_log` (`id`, `product_code`, `warehouse`, `prev_stock`, `plus_stock`, `min_stock`, `curr_stock`, `prev_stock_date`, `curr_stock_date`, `confirm_code`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'B-0001              ', 0, '0.0000', '100.0000', '0.0000', '100.0000', '2017-01-08', '2017-01-08', 'D5L0W', '2017-01-08', 'ADMIN', NULL, NULL, NULL, NULL),
(2, 'B-0002              ', 0, '0.0000', '10.0000', '0.0000', '10.0000', '2017-01-08', '2017-01-08', '2LW9C', '2017-01-08', 'ADMIN', NULL, NULL, NULL, NULL),
(3, 'B-0003              ', 0, '0.0000', '10.0000', '0.0000', '10.0000', '2017-01-08', '2017-01-08', 'N7ERX', '2017-01-08', 'ADMIN', NULL, NULL, NULL, NULL),
(4, 'B-0001              ', 0, '100.0000', '0.0000', '100.0000', '0.0000', '2017-01-08', '2017-01-08', '2XKRL', '2017-01-08', 'ADMIN', NULL, NULL, NULL, NULL),
(5, 'B-0001              ', 1, '0.0000', '100.0000', '0.0000', '100.0000', '2017-01-08', '2017-01-08', 'VAGKG', '2017-01-08', 'ADMIN', NULL, NULL, NULL, NULL),
(6, 'B-0001              ', 1, '100.0000', '0.0000', '100.0000', '0.0000', '2017-01-08', '2017-01-08', 'DUFW1', '2017-01-08', 'ADMIN', NULL, NULL, NULL, NULL),
(7, 'B-0001              ', 1, '0.0000', '100.0000', '0.0000', '100.0000', '2017-01-08', '2017-01-08', 'MGNA3', '2017-01-08', 'ADMIN', NULL, NULL, NULL, NULL),
(8, 'B-0001              ', 0, '0.0000', '100.0000', '0.0000', '100.0000', '2017-01-08', '2017-01-08', 'NEW34', '2017-01-08', 'ADMIN', NULL, NULL, NULL, NULL),
(9, 'B-0002              ', 0, '10.0000', '10.0000', '0.0000', '20.0000', '2017-01-08', '2017-01-08', '3FHC0', '2017-01-08', 'ADMIN', NULL, NULL, NULL, NULL),
(10, 'B-0003              ', 0, '10.0000', '10.0000', '0.0000', '20.0000', '2017-01-08', '2017-01-08', 'LIP4H', '2017-01-08', 'ADMIN', NULL, NULL, NULL, NULL),
(11, 'B-0001              ', 0, '100.0000', '0.0000', '100.0000', '0.0000', '2017-01-08', '2017-01-08', 'DRCVU', '2017-01-08', 'ADMIN', NULL, NULL, NULL, NULL),
(12, 'B-0001              ', 1, '100.0000', '100.0000', '0.0000', '200.0000', '2017-01-08', '2017-01-08', 'THPU0', '2017-01-08', 'ADMIN', NULL, NULL, NULL, NULL),
(13, 'B-0001              ', 1, '200.0000', '0.0000', '100.0000', '100.0000', '2017-01-08', '2017-01-08', 'NFYYQ', '2017-01-08', 'ADMIN', NULL, NULL, NULL, NULL),
(14, 'B-0001              ', 1, '100.0000', '100.0000', '0.0000', '200.0000', '2017-01-08', '2017-01-08', 'T1TG8', '2017-01-08', 'ADMIN', NULL, NULL, NULL, NULL),
(15, 'B-0001              ', 0, '0.0000', '100.0000', '0.0000', '100.0000', '2017-01-08', '2017-01-19', '2HIP4', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(16, 'B-0002              ', 0, '20.0000', '10.0000', '0.0000', '30.0000', '2017-01-08', '2017-01-19', '40VOT', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(17, 'B-0002              ', 0, '30.0000', '10.0000', '0.0000', '40.0000', '2017-01-19', '2017-01-19', 'C3HK2', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(18, 'B-0003              ', 0, '20.0000', '10.0000', '0.0000', '30.0000', '2017-01-08', '2017-01-19', 'D76CN', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(19, 'B-0003              ', 0, '30.0000', '10.0000', '0.0000', '40.0000', '2017-01-19', '2017-01-19', '6XPBK', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(20, 'B-0025              ', 0, '0.0000', '1.0000', '0.0000', '1.0000', '2017-01-19', '2017-01-19', '0A3JJ', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(21, 'B-0001              ', 0, '100.0000', '0.0000', '100.0000', '0.0000', '2017-01-19', '2017-01-19', 'TATPI', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(22, 'B-0001              ', 1, '200.0000', '100.0000', '0.0000', '300.0000', '2017-01-08', '2017-01-19', '1HTAD', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(23, 'B-0002              ', 0, '40.0000', '0.0000', '10.0000', '30.0000', '2017-01-19', '2017-01-19', '502BC', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(24, 'B-0002              ', 1, '0.0000', '10.0000', '0.0000', '10.0000', '2017-01-19', '2017-01-19', '2C2NA', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(25, 'B-0002              ', 0, '30.0000', '0.0000', '10.0000', '20.0000', '2017-01-19', '2017-01-19', 'FTRUE', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(26, 'B-0002              ', 1, '10.0000', '10.0000', '0.0000', '20.0000', '2017-01-19', '2017-01-19', 'B4ZS8', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(27, 'B-0003              ', 0, '40.0000', '0.0000', '10.0000', '30.0000', '2017-01-19', '2017-01-19', 'UVDIE', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(28, 'B-0003              ', 1, '0.0000', '10.0000', '0.0000', '10.0000', '2017-01-19', '2017-01-19', 'H8LEG', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(29, 'B-0001              ', 1, '300.0000', '0.0000', '100.0000', '200.0000', '2017-01-19', '2017-01-19', 'ZRQLA', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(30, 'K-0001              ', 1, '0.0000', '100.0000', '0.0000', '100.0000', '2017-01-19', '2017-01-19', 'QWXX1', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(31, 'B-0002              ', 1, '20.0000', '0.0000', '10.0000', '10.0000', '2017-01-19', '2017-01-19', 'TGW8N', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(32, 'K-0002              ', 1, '0.0000', '10.0000', '0.0000', '10.0000', '2017-01-19', '2017-01-19', '0O813', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(33, 'B-0002              ', 1, '10.0000', '0.0000', '10.0000', '0.0000', '2017-01-19', '2017-01-19', 'Q28K2', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(34, 'K-0002              ', 1, '10.0000', '10.0000', '0.0000', '20.0000', '2017-01-19', '2017-01-19', 'N5MB7', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(35, 'B-0003              ', 1, '10.0000', '0.0000', '10.0000', '0.0000', '2017-01-19', '2017-01-19', '2EFVF', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(36, 'K-0003              ', 1, '0.0000', '10.0000', '0.0000', '10.0000', '2017-01-19', '2017-01-19', '564W2', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(37, 'B-0001              ', 0, '0.0000', '100.0000', '0.0000', '100.0000', '2017-01-19', '2017-01-19', 'PA1BM', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(38, 'B-0002              ', 0, '20.0000', '10.0000', '0.0000', '30.0000', '2017-01-19', '2017-01-19', 'AIXK1', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(39, 'B-0002              ', 0, '30.0000', '10.0000', '0.0000', '40.0000', '2017-01-19', '2017-01-19', '9T41P', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(40, 'B-0003              ', 0, '30.0000', '10.0000', '0.0000', '40.0000', '2017-01-19', '2017-01-19', 'RNDOL', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(41, 'B-0003              ', 0, '40.0000', '10.0000', '0.0000', '50.0000', '2017-01-19', '2017-01-19', 'TONKV', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(42, 'B-0025              ', 0, '1.0000', '1.0000', '0.0000', '2.0000', '2017-01-19', '2017-01-19', 'FG4SR', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(43, 'B-0001              ', 0, '100.0000', '0.0000', '100.0000', '0.0000', '2017-01-19', '2017-01-19', '3QIRE', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(44, 'B-0001              ', 1, '200.0000', '100.0000', '0.0000', '300.0000', '2017-01-19', '2017-01-19', '8DJYF', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(45, 'B-0002              ', 0, '40.0000', '0.0000', '10.0000', '30.0000', '2017-01-19', '2017-01-19', 'DPEPN', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(46, 'B-0002              ', 1, '0.0000', '10.0000', '0.0000', '10.0000', '2017-01-19', '2017-01-19', '27XQZ', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(47, 'B-0002              ', 0, '30.0000', '0.0000', '10.0000', '20.0000', '2017-01-19', '2017-01-19', 'KU486', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(48, 'B-0002              ', 1, '10.0000', '10.0000', '0.0000', '20.0000', '2017-01-19', '2017-01-19', 'D0QRD', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(49, 'B-0003              ', 0, '50.0000', '0.0000', '10.0000', '40.0000', '2017-01-19', '2017-01-19', 'JH2UO', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(50, 'B-0003              ', 1, '0.0000', '10.0000', '0.0000', '10.0000', '2017-01-19', '2017-01-19', '0V4AN', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(51, 'B-0001              ', 1, '300.0000', '0.0000', '100.0000', '200.0000', '2017-01-19', '2017-01-19', 'C70IO', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(52, 'K-0001              ', 1, '100.0000', '100.0000', '0.0000', '200.0000', '2017-01-19', '2017-01-19', 'HYS6R', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(53, 'B-0002              ', 1, '20.0000', '0.0000', '10.0000', '10.0000', '2017-01-19', '2017-01-19', 'RZ4BR', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(54, 'K-0002              ', 1, '20.0000', '10.0000', '0.0000', '30.0000', '2017-01-19', '2017-01-19', 'D1S3G', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(55, 'B-0002              ', 1, '10.0000', '0.0000', '10.0000', '0.0000', '2017-01-19', '2017-01-19', 'P53FB', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(56, 'K-0002              ', 1, '30.0000', '10.0000', '0.0000', '40.0000', '2017-01-19', '2017-01-19', 'FNMZO', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(57, 'B-0003              ', 1, '10.0000', '0.0000', '10.0000', '0.0000', '2017-01-19', '2017-01-19', 'DVLPI', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL),
(58, 'K-0003              ', 1, '10.0000', '10.0000', '0.0000', '20.0000', '2017-01-19', '2017-01-19', 'KBMYA', '2017-01-19', 'ADMIN', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `inventory_log_temp`
--

CREATE TABLE IF NOT EXISTS `inventory_log_temp` (
  `id` int(12) NOT NULL,
  `product_code` varchar(20) DEFAULT NULL,
  `warehouse` int(1) DEFAULT NULL,
  `qty` decimal(20,4) DEFAULT NULL,
  `mutasi` varchar(1) DEFAULT NULL,
  `srctable` varchar(50) DEFAULT NULL,
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
-- Dumping data untuk tabel `inventory_log_temp`
--

INSERT INTO `inventory_log_temp` (`id`, `product_code`, `warehouse`, `qty`, `mutasi`, `srctable`, `confirm_code`, `confirm_date`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(115, 'B-0001              ', 0, '100.0000', 'D', 'RECEIVED', 'RSXW2', '2017-01-08', '2017-01-03', 'timotius', '2017-01-08', 'timotius', NULL, NULL),
(116, 'B-0002              ', 0, '10.0000', 'D', 'RECEIVED', 'RSXW2', '2017-01-08', '2017-01-03', 'timotius', '2017-01-08', 'timotius', NULL, NULL),
(117, 'B-0003              ', 0, '10.0000', 'D', 'RECEIVED', 'RSXW2', '2017-01-08', '2017-01-03', 'timotius', '2017-01-08', 'timotius', NULL, NULL),
(118, 'B-0001              ', 0, '100.0000', 'C', 'DRY_IN', 'RSXW2', '2017-01-08', '2017-01-03', 'timotius', '2017-01-08', 'timotius', NULL, NULL),
(119, 'B-0001              ', 1, '100.0000', 'D', 'DRY_IN', 'RSXW2', '2017-01-08', '2017-01-03', 'timotius', '2017-01-08', 'timotius', NULL, NULL),
(120, 'B-0001              ', 1, '100.0000', 'C', 'DRY_OUT', 'RSXW2', '2017-01-08', '2017-01-03', 'timotius', '2017-01-08', 'timotius', NULL, NULL),
(121, 'B-0001              ', 1, '100.0000', 'D', 'DRY_OUT', 'RSXW2', '2017-01-08', '2017-01-03', 'timotius', '2017-01-08', 'timotius', NULL, NULL),
(122, 'B-0001              ', 0, '100.0000', 'D', 'RECEIVED', 'YUHH7', '2017-01-08', '2017-01-03', 'timotius', '2017-01-08', 'timotius', NULL, NULL),
(123, 'B-0002              ', 0, '10.0000', 'D', 'RECEIVED', 'YUHH7', '2017-01-08', '2017-01-03', 'timotius', '2017-01-08', 'timotius', NULL, NULL),
(124, 'B-0003              ', 0, '10.0000', 'D', 'RECEIVED', 'YUHH7', '2017-01-08', '2017-01-03', 'timotius', '2017-01-08', 'timotius', NULL, NULL),
(125, 'B-0001              ', 0, '100.0000', 'C', 'DRY_IN', 'YUHH7', '2017-01-08', '2017-01-03', 'timotius', '2017-01-08', 'timotius', NULL, NULL),
(126, 'B-0001              ', 1, '100.0000', 'D', 'DRY_IN', 'YUHH7', '2017-01-08', '2017-01-03', 'timotius', '2017-01-08', 'timotius', NULL, NULL),
(127, 'B-0001              ', 1, '100.0000', 'C', 'DRY_OUT', 'YUHH7', '2017-01-08', '2017-01-03', 'timotius', '2017-01-08', 'timotius', NULL, NULL),
(128, 'B-0001              ', 1, '100.0000', 'D', 'DRY_OUT', 'YUHH7', '2017-01-08', '2017-01-03', 'timotius', '2017-01-08', 'timotius', NULL, NULL),
(129, 'B-0001              ', 0, '100.0000', 'D', 'RECEIVED', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(130, 'B-0002              ', 0, '10.0000', 'D', 'RECEIVED', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(131, 'B-0002              ', 0, '10.0000', 'D', 'RECEIVED', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(132, 'B-0003              ', 0, '10.0000', 'D', 'RECEIVED', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(133, 'B-0003              ', 0, '10.0000', 'D', 'RECEIVED', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(134, 'B-0025              ', 0, '1.0000', 'D', 'RECEIVED', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(135, 'B-0001              ', 0, '100.0000', 'C', 'DRY_IN', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(136, 'B-0001              ', 1, '100.0000', 'D', 'DRY_IN', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(137, 'B-0002              ', 0, '10.0000', 'C', 'DRY_IN', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(138, 'B-0002              ', 1, '10.0000', 'D', 'DRY_IN', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(139, 'B-0002              ', 0, '10.0000', 'C', 'DRY_IN', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(140, 'B-0002              ', 1, '10.0000', 'D', 'DRY_IN', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(141, 'B-0003              ', 0, '10.0000', 'C', 'DRY_IN', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(142, 'B-0003              ', 1, '10.0000', 'D', 'DRY_IN', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(143, 'B-0001              ', 1, '100.0000', 'C', 'DRY_OUT', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(144, 'K-0001              ', 1, '100.0000', 'D', 'DRY_OUT', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(145, 'B-0002              ', 1, '10.0000', 'C', 'DRY_OUT', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(146, 'K-0002              ', 1, '10.0000', 'D', 'DRY_OUT', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(147, 'B-0002              ', 1, '10.0000', 'C', 'DRY_OUT', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(148, 'K-0002              ', 1, '10.0000', 'D', 'DRY_OUT', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(149, 'B-0003              ', 1, '10.0000', 'C', 'DRY_OUT', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(150, 'K-0003              ', 1, '10.0000', 'D', 'DRY_OUT', 'VF3P2', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(151, 'B-0001              ', 0, '100.0000', 'D', 'RECEIVED', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(152, 'B-0002              ', 0, '10.0000', 'D', 'RECEIVED', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(153, 'B-0002              ', 0, '10.0000', 'D', 'RECEIVED', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(154, 'B-0003              ', 0, '10.0000', 'D', 'RECEIVED', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(155, 'B-0003              ', 0, '10.0000', 'D', 'RECEIVED', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(156, 'B-0025              ', 0, '1.0000', 'D', 'RECEIVED', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(157, 'B-0001              ', 0, '100.0000', 'C', 'DRY_IN', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(158, 'B-0001              ', 1, '100.0000', 'D', 'DRY_IN', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(159, 'B-0002              ', 0, '10.0000', 'C', 'DRY_IN', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(160, 'B-0002              ', 1, '10.0000', 'D', 'DRY_IN', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(161, 'B-0002              ', 0, '10.0000', 'C', 'DRY_IN', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(162, 'B-0002              ', 1, '10.0000', 'D', 'DRY_IN', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(163, 'B-0003              ', 0, '10.0000', 'C', 'DRY_IN', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(164, 'B-0003              ', 1, '10.0000', 'D', 'DRY_IN', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(165, 'B-0001              ', 1, '100.0000', 'C', 'DRY_OUT', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(166, 'K-0001              ', 1, '100.0000', 'D', 'DRY_OUT', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(167, 'B-0002              ', 1, '10.0000', 'C', 'DRY_OUT', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(168, 'K-0002              ', 1, '10.0000', 'D', 'DRY_OUT', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(169, 'B-0002              ', 1, '10.0000', 'C', 'DRY_OUT', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(170, 'K-0002              ', 1, '10.0000', 'D', 'DRY_OUT', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(171, 'B-0003              ', 1, '10.0000', 'C', 'DRY_OUT', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL),
(172, 'K-0003              ', 1, '10.0000', 'D', 'DRY_OUT', 'SEMHV', '2017-01-19', '2017-01-19', 'timotius', '2017-01-19', 'timotius', NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `invoice_balken`
--

CREATE TABLE IF NOT EXISTS `invoice_balken` (
  `id` int(12) NOT NULL,
  `received_code` varchar(16) DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `rate` decimal(10,4) DEFAULT NULL,
  `payment_status` varchar(20) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `subtotal` decimal(20,4) DEFAULT NULL,
  `disc` decimal(20,4) DEFAULT NULL,
  `tax` decimal(20,4) DEFAULT NULL,
  `other_fee` decimal(20,4) DEFAULT NULL,
  `total` decimal(20,4) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `confirm_code` varchar(5) DEFAULT NULL,
  `confirm_date` date DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(20) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(20) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `invoice_balken`
--

INSERT INTO `invoice_balken` (`id`, `received_code`, `due_date`, `rate`, `payment_status`, `payment_date`, `subtotal`, `disc`, `tax`, `other_fee`, `total`, `status`, `confirm_code`, `confirm_date`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, '0002/BL/03/01/17', '2017-04-14', '1.0000', 'Ya', '2017-05-05', '0.0000', '1000.0000', '300.0000', '4000.0000', '3300.0000', 'FINAL', NULL, NULL, '2017-04-22', 'timotius', '2017-05-05', 'timotius', NULL, NULL),
(2, '0003/BL/03/01/17', '2017-05-11', '2.0000', 'Ya', '2017-05-05', '46000.0000', '0.0000', '0.0000', '0.0000', '46000.0000', 'FINAL', NULL, NULL, '2017-05-05', 'timotius', NULL, NULL, NULL, NULL),
(3, '0004/BL/07/01/17', '2017-05-09', '1.0000', 'Ya', '2017-05-06', '9200.0000', '100.0000', '930.0000', '200.0000', '10230.0000', 'FINAL', NULL, NULL, '2017-05-06', 'timotius', '2017-05-06', 'timotius', NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `invoice_prod_result`
--

CREATE TABLE IF NOT EXISTS `invoice_prod_result` (
  `id` int(12) NOT NULL,
  `rpr_code` varchar(50) DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `rate` decimal(10,4) DEFAULT NULL,
  `payment_status` varchar(20) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `subtotal` decimal(20,4) DEFAULT NULL,
  `disc` decimal(20,4) DEFAULT NULL,
  `tax` decimal(20,4) DEFAULT NULL,
  `other_fee` decimal(20,4) DEFAULT NULL,
  `total` decimal(20,4) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
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
-- Dumping data untuk tabel `invoice_prod_result`
--

INSERT INTO `invoice_prod_result` (`id`, `rpr_code`, `due_date`, `rate`, `payment_status`, `payment_date`, `subtotal`, `disc`, `tax`, `other_fee`, `total`, `status`, `confirm_code`, `confirm_date`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, '0002/STTB/3/04/17', '2017-04-03', '2.0000', 'Ya', '2017-04-02', '46000.0000', '1000.0000', '2000.0000', '3000.0000', '50000.0000', 'FINAL', NULL, NULL, '2017-04-02', 'timotius', NULL, NULL, NULL, NULL),
(2, '0001/STTB/29/03/17', '2017-04-06', '2.0000', 'Belum', NULL, '660000.0000', '0.0000', '6600.0000', '0.0000', '666600.0000', 'FINAL', NULL, NULL, '2017-04-02', 'timotius', NULL, NULL, NULL, NULL),
(3, '0003/STTB/5/05/17', '2017-05-05', '1.0000', 'Ya', '2017-05-05', '80000.0000', '0.0000', '0.0000', '0.0000', '80000.0000', 'FINAL', NULL, NULL, '2017-05-05', 'timotius', NULL, NULL, NULL, NULL),
(4, '0004/STTB/9/05/17', '2017-05-12', '2.0000', 'Ya', '2017-05-06', '1800000.0000', '0.0000', '0.0000', '0.0000', '1800000.0000', 'FINAL', NULL, NULL, '2017-05-06', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `inv_balken_product`
--

CREATE TABLE IF NOT EXISTS `inv_balken_product` (
  `id` int(12) NOT NULL,
  `inventory_balken_id` int(12) DEFAULT NULL,
  `product_code` varchar(20) DEFAULT NULL,
  `qty` decimal(20,4) DEFAULT NULL,
  `price` decimal(20,4) DEFAULT NULL,
  `idr_price` decimal(20,4) DEFAULT NULL,
  `subtotal` decimal(20,4) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(20) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(20) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `inv_balken_product`
--

INSERT INTO `inv_balken_product` (`id`, `inventory_balken_id`, `product_code`, `qty`, `price`, `idr_price`, `subtotal`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 1, 'B-0001              ', '100.0000', '9.0000', '9.0000', '900.0000', '2017-04-22', 'timotius', NULL, NULL, NULL, NULL),
(2, 1, 'B-0002              ', '10.0000', '90.0000', '90.0000', '900.0000', '2017-04-22', 'timotius', NULL, NULL, NULL, NULL),
(3, 1, 'B-0003              ', '10.0000', '300.0000', '300.0000', '3000.0000', '2017-04-22', 'timotius', NULL, NULL, NULL, NULL),
(4, 2, 'B-0001              ', '100.0000', '200.0000', '400.0000', '40000.0000', '2017-05-05', 'timotius', NULL, NULL, NULL, NULL),
(5, 2, 'B-0002              ', '10.0000', '200.0000', '400.0000', '4000.0000', '2017-05-05', 'timotius', NULL, NULL, NULL, NULL),
(6, 2, 'B-0003              ', '10.0000', '100.0000', '200.0000', '2000.0000', '2017-05-05', 'timotius', NULL, NULL, NULL, NULL),
(7, 3, 'B-0002              ', '10.0000', '500.0000', '500.0000', '5000.0000', '2017-05-06', 'timotius', '2017-05-06', 'timotius', NULL, NULL),
(8, 3, 'B-0003              ', '10.0000', '400.0000', '400.0000', '4000.0000', '2017-05-06', 'timotius', '2017-05-06', 'timotius', NULL, NULL),
(9, 3, 'B-0025              ', '1.0000', '200.0000', '200.0000', '200.0000', '2017-05-06', 'timotius', '2017-05-06', 'timotius', NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `inv_pr_product`
--

CREATE TABLE IF NOT EXISTS `inv_pr_product` (
  `id` int(11) NOT NULL,
  `id_inv_pr` int(12) DEFAULT NULL,
  `product_code` varchar(20) DEFAULT NULL,
  `qty` decimal(20,4) DEFAULT NULL,
  `price` decimal(20,4) DEFAULT NULL,
  `idr_price` decimal(20,4) DEFAULT NULL,
  `subtotal` decimal(20,4) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `inv_pr_product`
--

INSERT INTO `inv_pr_product` (`id`, `id_inv_pr`, `product_code`, `qty`, `price`, `idr_price`, `subtotal`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 1, 'PDC009-17', '230.0000', '100.0000', '200.0000', '46000.0000', '2017-04-02', 'timotius', NULL, NULL, NULL, NULL),
(2, 2, 'PDC009-2', '70.0000', '4000.0000', '8000.0000', '560000.0000', '2017-04-02', 'timotius', NULL, NULL, NULL, NULL),
(3, 2, 'PDC009-17', '100.0000', '500.0000', '1000.0000', '100000.0000', '2017-04-02', 'timotius', NULL, NULL, NULL, NULL),
(4, 3, 'PDC009-3', '0.0000', '500.0000', '500.0000', '0.0000', '2017-05-05', 'timotius', NULL, NULL, NULL, NULL),
(5, 3, 'PDC009-18', '200.0000', '400.0000', '400.0000', '80000.0000', '2017-05-05', 'timotius', NULL, NULL, NULL, NULL),
(6, 4, 'PDC009-4', '400.0000', '2000.0000', '4000.0000', '1600000.0000', '2017-05-06', 'timotius', NULL, NULL, NULL, NULL),
(7, 4, 'PDC009-17', '25.0000', '4000.0000', '8000.0000', '200000.0000', '2017-05-06', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `line`
--

CREATE TABLE IF NOT EXISTS `line` (
  `id` int(3) NOT NULL,
  `line_code` varchar(9) NOT NULL,
  `description` varchar(5) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `line`
--

INSERT INTO `line` (`id`, `line_code`, `description`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `delete_by`) VALUES
(1, '1001', 'line1', NULL, NULL, NULL, NULL, NULL, NULL),
(2, '1002', 'line2', NULL, NULL, NULL, NULL, NULL, NULL),
(3, '1003', 'line3', NULL, NULL, NULL, NULL, NULL, NULL),
(4, '1004', 'line4', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `id` int(11) NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  `employee_id` varchar(10) DEFAULT NULL,
  `last_login` date DEFAULT NULL,
  `login_status` varchar(1) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(30) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(30) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `login`
--

INSERT INTO `login` (`id`, `username`, `employee_id`, `last_login`, `login_status`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) VALUES
(1, 'admin', '', '2017-03-07', '1', '2017-03-07', 'admin', '2017-03-07', 'admin', NULL, NULL),
(2, 'root', '', '2017-03-07', '1', '2017-03-07', 'root', '2017-03-07', 'root', NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `machine`
--

CREATE TABLE IF NOT EXISTS `machine` (
  `id` int(3) NOT NULL,
  `machine_code` varchar(9) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `machine`
--

INSERT INTO `machine` (`id`, `machine_code`, `description`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `delete_by`) VALUES
(1, 'm001', 'Mesin Potong', NULL, NULL, NULL, NULL, NULL, NULL),
(2, 'm002', 'Mesin Press', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `marital`
--

CREATE TABLE IF NOT EXISTS `marital` (
  `marital_id` varchar(10) NOT NULL,
  `marital_name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `marital`
--

INSERT INTO `marital` (`marital_id`, `marital_name`) VALUES
('1', 'Lajang'),
('2', 'Menikah');

-- --------------------------------------------------------

--
-- Struktur dari tabel `menu`
--

CREATE TABLE IF NOT EXISTS `menu` (
  `id` int(11) NOT NULL,
  `menu_name` varchar(30) DEFAULT NULL,
  `menu_title` varchar(30) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(30) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(30) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `ms_position`
--

CREATE TABLE IF NOT EXISTS `ms_position` (
  `id` varchar(10) NOT NULL,
  `NAME` varchar(20) DEFAULT NULL,
  `min_salary` decimal(20,4) DEFAULT NULL,
  `max_salary` decimal(20,4) DEFAULT NULL,
  `department_id` varchar(10) DEFAULT NULL,
  `division_id` varchar(10) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(30) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(30) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `ms_supplier`
--

CREATE TABLE IF NOT EXISTS `ms_supplier` (
  `id` int(9) NOT NULL,
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
  `edited_by` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `packing`
--

CREATE TABLE IF NOT EXISTS `packing` (
  `id` int(12) NOT NULL,
  `packing_date` date DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `type` varchar(10) NOT NULL,
  `confirm_code` varchar(5) DEFAULT NULL,
  `confirm_date` date DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(20) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(20) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `packing`
--

INSERT INTO `packing` (`id`, `packing_date`, `status`, `type`, `confirm_code`, `confirm_date`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, '2017-01-14', 'BARU', '', NULL, NULL, '2017-01-14', 'Michael', NULL, NULL, NULL, NULL),
(2, '2017-01-14', 'BARU', '', NULL, NULL, '2017-01-14', 'Michael', NULL, NULL, NULL, NULL),
(3, '2017-03-07', 'BARU', '13', NULL, NULL, '2017-03-07', 'admin', NULL, NULL, NULL, NULL),
(4, '2017-03-08', 'BARU', '12', NULL, NULL, '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(5, '2017-03-08', 'BARU', '9', NULL, NULL, '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(6, '2017-05-13', 'BARU', '9', NULL, NULL, '2017-05-13', 'admin', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `packing_conversion`
--

CREATE TABLE IF NOT EXISTS `packing_conversion` (
  `id` int(10) NOT NULL,
  `product_code_from` varchar(20) DEFAULT NULL,
  `qty_from` int(10) DEFAULT NULL,
  `product_code_to` varchar(20) DEFAULT NULL,
  `qty_to` int(10) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(20) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(20) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(20) DEFAULT NULL,
  `effective_start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `effective_end_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `packing_conversion`
--

INSERT INTO `packing_conversion` (`id`, `product_code_from`, `qty_from`, `product_code_to`, `qty_to`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `deleted_by`, `effective_start_date`, `effective_end_date`) VALUES
(1, 'PDC009-9', 1, 'PDC009-1', 82, '2016-12-27', 'Timotius', NULL, NULL, NULL, NULL, '2017-01-11 15:18:28', '2020-12-30 17:00:00'),
(2, 'PDC009-10', 1, 'PDC009-1', 24, '2016-12-27', 'Timotius', NULL, NULL, NULL, NULL, '2017-01-11 15:18:28', '2020-12-30 17:00:00'),
(3, 'PDC009-11', 1, 'PDC009-1', 82, '2016-12-27', 'Timotius', NULL, NULL, NULL, NULL, '2017-01-11 15:18:28', '2020-12-30 17:00:00'),
(4, 'PDC009-12', 1, 'PDC009-1', 24, '2016-12-27', 'Timotius', NULL, NULL, NULL, NULL, '2017-01-11 15:18:28', '2020-12-30 17:00:00'),
(5, 'PDC009-13', 1, 'PDC009-2', 81, '2016-12-27', 'Timotius', NULL, NULL, NULL, NULL, '2017-01-11 15:18:28', '2020-12-30 17:00:00'),
(6, 'PDC009-14', 1, 'PDC009-2', 24, '2016-12-27', 'Timotius', NULL, NULL, NULL, NULL, '2017-01-11 15:18:28', '2020-12-30 17:00:00'),
(7, 'PDC009-15', 1, 'PDC009-2', 81, '2016-12-27', 'Timotius', NULL, NULL, NULL, NULL, '2017-01-11 15:18:28', '2020-12-30 17:00:00'),
(8, 'PDC009-16', 1, 'PDC009-2', 24, '2016-12-27', 'Timotius', NULL, NULL, NULL, NULL, '2017-01-11 15:18:28', '2020-12-30 17:00:00'),
(9, 'PDC009-3', 1, 'PDC009-1', 1, '2017-01-21', 'TIMOTIUS', NULL, NULL, NULL, NULL, '2017-01-11 15:18:28', '2020-12-30 17:00:00'),
(10, 'PDC009-4', 1, 'PDC009-2', 1, '2017-01-21', 'TIMOTIUS', NULL, NULL, NULL, NULL, '2017-03-08 16:35:37', '2020-12-30 17:00:00'),
(11, 'PDC009-23', 1, 'PDC009-17', 82, '2017-01-21', 'TIMOTIUS', NULL, NULL, NULL, NULL, '2017-01-11 15:18:28', '2020-12-30 17:00:00'),
(12, 'PDC009-24', 1, 'PDC009-17', 24, '2017-01-21', 'TIMOTIUS', NULL, NULL, NULL, NULL, '2017-01-11 15:18:28', '2020-12-30 17:00:00'),
(13, 'PDC009-25', 1, 'PDC009-17', 82, '2017-01-21', 'TIMOTIUS', NULL, NULL, NULL, NULL, '2017-01-11 15:18:28', '2020-12-30 17:00:00'),
(14, 'PDC009-26', 1, 'PDC009-17', 24, '2017-01-21', 'TIMOTIUS', NULL, NULL, NULL, NULL, '2017-01-11 15:18:28', '2020-12-30 17:00:00'),
(15, 'PDC009-27', 1, 'PDC009-18', 81, '2017-01-21', 'TIMOTIUS', NULL, NULL, NULL, NULL, '2017-01-11 15:18:28', '2020-12-30 17:00:00'),
(16, 'PDC009-28', 1, 'PDC009-18', 24, '2017-01-21', 'TIMOTIUS', NULL, NULL, NULL, NULL, '2017-01-11 15:18:28', '2020-12-30 17:00:00'),
(17, 'PDC009-29', 1, 'PDC009-18', 81, '2017-01-21', 'TIMOTIUS', NULL, NULL, NULL, NULL, '2017-01-11 15:18:28', '2020-12-30 17:00:00'),
(18, 'PDC009-30', 1, 'PDC009-18', 24, '2017-01-21', 'TIMOTIUS', NULL, NULL, NULL, NULL, '2017-01-11 15:18:28', '2020-12-30 17:00:00'),
(19, 'PDC009-19', 1, 'PDC009-17', 1, '2017-01-21', 'TIMOTIUS', NULL, NULL, NULL, NULL, '2017-01-11 15:18:28', '2020-12-30 17:00:00'),
(20, 'PDC009-20', 1, 'PDC009-18', 1, '2017-01-21', 'TIMOTIUS', NULL, NULL, NULL, NULL, '2017-01-11 15:18:28', '2020-12-30 17:00:00');

-- --------------------------------------------------------

--
-- Struktur dari tabel `packing_result`
--

CREATE TABLE IF NOT EXISTS `packing_result` (
  `id` int(12) NOT NULL,
  `packing_id` int(12) DEFAULT NULL,
  `product_code` varchar(20) DEFAULT NULL,
  `qty` decimal(20,4) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(20) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(20) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `packing_result`
--

INSERT INTO `packing_result` (`id`, `packing_id`, `product_code`, `qty`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(25, 2, 'PDC009-9', '1.0000', '2017-01-14', 'Michael', NULL, NULL, NULL, NULL),
(26, 2, 'PDC009-10', '1.0000', '2017-01-14', 'Michael', NULL, NULL, NULL, NULL),
(27, 2, 'PDC009-11', '1.0000', '2017-01-14', 'Michael', NULL, NULL, NULL, NULL),
(28, 2, 'PDC009-12', '1.0000', '2017-01-14', 'Michael', NULL, NULL, NULL, NULL),
(29, 2, 'PDC009-13', '1.0000', '2017-01-14', 'Michael', NULL, NULL, NULL, NULL),
(30, 2, 'PDC009-14', '1.0000', '2017-01-14', 'Michael', NULL, NULL, NULL, NULL),
(31, 2, 'PDC009-15', '1.0000', '2017-01-14', 'Michael', NULL, NULL, NULL, NULL),
(32, 2, 'PDC009-16', '1.0000', '2017-01-14', 'Michael', NULL, NULL, NULL, NULL),
(33, 1, 'PDC009-9', '2.0000', '2017-01-14', 'Michael', NULL, NULL, NULL, NULL),
(34, 1, 'PDC009-10', '2.0000', '2017-01-14', 'Michael', NULL, NULL, NULL, NULL),
(35, 1, 'PDC009-11', '2.0000', '2017-01-14', 'Michael', NULL, NULL, NULL, NULL),
(36, 1, 'PDC009-12', '2.0000', '2017-01-14', 'Michael', NULL, NULL, NULL, NULL),
(37, 1, 'PDC009-13', '2.0000', '2017-01-14', 'Michael', NULL, NULL, NULL, NULL),
(38, 1, 'PDC009-14', '2.0000', '2017-01-14', 'Michael', NULL, NULL, NULL, NULL),
(39, 1, 'PDC009-15', '2.0000', '2017-01-14', 'Michael', NULL, NULL, NULL, NULL),
(40, 1, 'PDC009-16', '2.0000', '2017-01-14', 'Michael', NULL, NULL, NULL, NULL),
(41, 3, 'PDC009-23', '1.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL),
(42, 3, 'PDC009-24', '2.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL),
(43, 3, 'PDC009-25', '3.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL),
(44, 3, 'PDC009-26', '4.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL),
(45, 3, 'PDC009-27', '5.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL),
(46, 3, 'PDC009-28', '6.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL),
(47, 3, 'PDC009-29', '7.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL),
(48, 3, 'PDC009-30', '8.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL),
(49, 3, 'PDC009-31', '9.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL),
(50, 3, 'PDC009-32', '10.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL),
(51, 3, 'PDC009-19', '11.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL),
(52, 3, 'PDC009-20', '12.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL),
(53, 4, 'PDC009-23', '1.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(54, 4, 'PDC009-24', '2.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(55, 4, 'PDC009-25', '3.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(56, 4, 'PDC009-26', '4.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(57, 4, 'PDC009-27', '5.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(58, 4, 'PDC009-28', '6.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(59, 4, 'PDC009-29', '7.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(60, 4, 'PDC009-30', '8.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(61, 4, 'PDC009-31', '9.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(62, 4, 'PDC009-32', '10.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(63, 4, 'PDC009-19', '11.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(64, 4, 'PDC009-20', '12.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(65, 5, 'PDC009-9', '1.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(66, 5, 'PDC009-10', '2.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(67, 5, 'PDC009-11', '3.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(68, 5, 'PDC009-12', '4.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(69, 5, 'PDC009-13', '5.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(70, 5, 'PDC009-14', '6.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(71, 5, 'PDC009-15', '7.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(72, 5, 'PDC009-16', '8.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(73, 5, 'PDC009-7', '9.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(74, 5, 'PDC009-8', '10.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(75, 5, 'PDC009-3', '11.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(76, 5, 'PDC009-4', '12.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(77, 6, 'PDC009-9', '1.0000', '2017-05-13', 'admin', NULL, NULL, NULL, NULL),
(78, 6, 'PDC009-10', '3.0000', '2017-05-13', 'admin', NULL, NULL, NULL, NULL),
(79, 6, 'PDC009-11', '4.0000', '2017-05-13', 'admin', NULL, NULL, NULL, NULL),
(80, 6, 'PDC009-12', '5.0000', '2017-05-13', 'admin', NULL, NULL, NULL, NULL),
(81, 6, 'PDC009-13', '7.0000', '2017-05-13', 'admin', NULL, NULL, NULL, NULL),
(82, 6, 'PDC009-14', '8.0000', '2017-05-13', 'admin', NULL, NULL, NULL, NULL),
(83, 6, 'PDC009-15', '9.0000', '2017-05-13', 'admin', NULL, NULL, NULL, NULL),
(84, 6, 'PDC009-16', '0.0000', '2017-05-13', 'admin', NULL, NULL, NULL, NULL),
(85, 6, 'PDC009-7', '0.0000', '2017-05-13', 'admin', NULL, NULL, NULL, NULL),
(86, 6, 'PDC009-8', '0.0000', '2017-05-13', 'admin', NULL, NULL, NULL, NULL),
(87, 6, 'PDC009-3', '0.0000', '2017-05-13', 'admin', NULL, NULL, NULL, NULL),
(88, 6, 'PDC009-4', '0.0000', '2017-05-13', 'admin', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `packing_rm`
--

CREATE TABLE IF NOT EXISTS `packing_rm` (
  `id` int(12) NOT NULL,
  `packing_id` int(12) DEFAULT NULL,
  `product_code` varchar(20) DEFAULT NULL,
  `qty` decimal(20,4) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(20) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(20) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `packing_rm`
--

INSERT INTO `packing_rm` (`id`, `packing_id`, `product_code`, `qty`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 3, 'PDC009-17', '483.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL),
(2, 3, 'PDC009-18', '1339.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL),
(3, 3, 'PDC009-31', '9.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL),
(4, 3, 'PDC009-32', '10.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL),
(5, 4, 'PDC009-17', '483.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(6, 4, 'PDC009-18', '1339.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(7, 4, 'PDC009-31', '9.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(8, 4, 'PDC009-32', '10.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(9, 5, 'PDC009-1', '483.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(10, 5, 'PDC009-2', '1339.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(11, 5, 'PDC009-7', '9.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(12, 5, 'PDC009-8', '10.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL),
(13, 6, 'PDC009-1', '602.0000', '2017-05-13', 'admin', NULL, NULL, NULL, NULL),
(14, 6, 'PDC009-2', '1488.0000', '2017-05-13', 'admin', NULL, NULL, NULL, NULL),
(15, 6, 'PDC009-7', '0.0000', '2017-05-13', 'admin', NULL, NULL, NULL, NULL),
(16, 6, 'PDC009-8', '0.0000', '2017-05-13', 'admin', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pallet_card`
--

CREATE TABLE IF NOT EXISTS `pallet_card` (
  `id` int(11) NOT NULL,
  `received_detail_id` varchar(21) NOT NULL,
  `pallet_card_code` varchar(21) NOT NULL,
  `length` decimal(20,4) NOT NULL,
  `width` decimal(20,4) NOT NULL,
  `thickness` decimal(20,4) NOT NULL,
  `total` int(11) NOT NULL,
  `volume` decimal(20,4) NOT NULL,
  `product_code` varchar(20) NOT NULL,
  `description` varchar(500) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pallet_card`
--

INSERT INTO `pallet_card` (`id`, `received_detail_id`, `pallet_card_code`, `length`, `width`, `thickness`, `total`, `volume`, `product_code`, `description`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(43, '24', '0002/BL/03/01/17/0001', '100.0000', '5.0000', '5.0000', 100, '250000.0000', 'B-0001              ', '', '2017-01-03', 'Michael', '2017-01-03', 'Michael', NULL, NULL),
(44, '24', '0002/BL/03/01/17/0002', '100.0000', '5.5000', '5.0000', 10, '0.0300', 'B-0002              ', '', '2017-01-03', 'Michael', NULL, NULL, NULL, NULL),
(45, '24', '0002/BL/03/01/17/0003', '100.0000', '6.0000', '5.0000', 10, '30000.0000', 'B-0003              ', '', '2017-01-03', 'Michael', NULL, NULL, NULL, NULL),
(46, '25', '0003/BL/03/01/17/0001', '100.0000', '5.0000', '5.0000', 100, '250000.0000', 'B-0001              ', '', '2017-01-03', 'Michael', NULL, NULL, NULL, NULL),
(47, '25', '0003/BL/03/01/17/0002', '100.0000', '5.5000', '5.0000', 10, '27500.0000', 'B-0002              ', '', '2017-01-03', 'Michael', NULL, NULL, NULL, NULL),
(48, '25', '0003/BL/03/01/17/0100', '100.0000', '6.0000', '5.0000', 10, '30000.0000', 'B-0003              ', '', '2017-01-03', 'Michael', NULL, NULL, NULL, NULL),
(49, '26', '0005/BL/08/01/17/0001', '100.0000', '5.0000', '5.0000', 10, '25000.0000', 'B-0001              ', '', '2017-01-08', 'Michael', '2017-01-08', 'Michael', NULL, NULL),
(50, '26', '0005/BL/08/01/17/0003', '100.0000', '6.0000', '6.0000', 10, '0.0400', 'B-0095              ', '', '2017-01-08', 'Michael', NULL, NULL, NULL, NULL),
(51, '26', '0005/BL/08/01/17/0004', '100.0000', '5.0000', '5.0000', 100, '0.2500', 'B-0001              ', '', '2017-01-08', 'Michael', NULL, NULL, NULL, NULL),
(52, '27', '0005/BL/08/01/17/0002', '100.0000', '5.0000', '5.0000', 9, '22500.0000', 'B-0024              ', '', '2017-01-08', 'Michael', NULL, NULL, NULL, NULL),
(53, '28', '0004/BL/07/01/17/0001', '100.0000', '5.5000', '5.0000', 10, '27500.0000', 'B-0002              ', '', '2017-01-14', 'Michael', '2017-01-14', 'Michael', NULL, NULL),
(54, '28', '0004/BL/07/01/17/0003', '100.0000', '6.0000', '5.0000', 10, '30000.0000', 'B-0003              ', '', '2017-01-14', 'Michael', NULL, NULL, NULL, NULL),
(55, '29', '0004/BL/07/01/17/0002', '100.0000', '5.5000', '5.0000', 1, '2750.0000', 'B-0025              ', '', '2017-01-14', 'Michael', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pallet_card_dtl`
--

CREATE TABLE IF NOT EXISTS `pallet_card_dtl` (
  `id` int(11) NOT NULL,
  `pallet_card_code` varchar(21) NOT NULL,
  `length` decimal(20,4) NOT NULL,
  `width` decimal(20,4) NOT NULL,
  `thickness` decimal(20,4) NOT NULL,
  `total` int(11) NOT NULL,
  `volume` decimal(20,4) NOT NULL,
  `product_code` varchar(20) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `payment_balken`
--

CREATE TABLE IF NOT EXISTS `payment_balken` (
  `id` int(12) DEFAULT NULL,
  `inventory_balken_id` int(12) DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `rate` decimal(10,4) DEFAULT NULL,
  `payment_status` varchar(20) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `subtotal` decimal(20,4) DEFAULT NULL,
  `disc` decimal(20,4) DEFAULT NULL,
  `tax` decimal(20,4) DEFAULT NULL,
  `other_fee` decimal(20,4) DEFAULT NULL,
  `total` decimal(20,4) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `confirm_code` varchar(50) DEFAULT NULL,
  `confirm_date` date DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(20) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(20) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `payment_balken`
--

INSERT INTO `payment_balken` (`id`, `inventory_balken_id`, `due_date`, `rate`, `payment_status`, `payment_date`, `subtotal`, `disc`, `tax`, `other_fee`, `total`, `status`, `confirm_code`, `confirm_date`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(NULL, 1, '2017-04-14', '1.0000', 'Ya', '2017-05-05', '4800.0000', '1000.0000', '300.0000', '4000.0000', '8100.0000', 'COMPLETED', NULL, NULL, '2017-05-05', 'timotius', NULL, NULL, NULL, NULL),
(NULL, 3, '2017-05-09', '2.0000', 'Ya', '2017-05-06', '18400.0000', '100.0000', '930.0000', '200.0000', '19430.0000', 'COMPLETED', NULL, NULL, '2017-05-06', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `payment_prod_result`
--

CREATE TABLE IF NOT EXISTS `payment_prod_result` (
  `id` int(12) NOT NULL,
  `pay_pr_code` varchar(50) DEFAULT NULL,
  `id_inv_pr` int(11) DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `rate` decimal(10,4) DEFAULT NULL,
  `payment_status` varchar(20) DEFAULT NULL,
  `subtotal` decimal(20,4) DEFAULT NULL,
  `disc` decimal(20,4) DEFAULT NULL,
  `tax` decimal(20,4) DEFAULT NULL,
  `other_fee` decimal(20,4) DEFAULT NULL,
  `total` decimal(20,4) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `confirm_code` varchar(5) DEFAULT NULL,
  `confirm_date` date DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  `payment_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `payment_prod_result`
--

INSERT INTO `payment_prod_result` (`id`, `pay_pr_code`, `id_inv_pr`, `due_date`, `rate`, `payment_status`, `subtotal`, `disc`, `tax`, `other_fee`, `total`, `status`, `confirm_code`, `confirm_date`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `payment_date`) VALUES
(1, '0001/PPYP/29/03/17', 1, '2017-03-30', '1.0000', 'Ya', '28000.0000', '100.0000', '100.0000', '400.0000', '28400.0000', 'COMPLETED', NULL, NULL, '2017-03-29', 'timotius', NULL, NULL, NULL, NULL, NULL),
(2, '0002/PPYP/2/04/17', 2, '2017-04-06', '1.0000', 'Ya', '330000.0000', '0.0000', '6600.0000', '0.0000', '336600.0000', 'COMPLETED', NULL, NULL, '2017-04-02', 'timotius', NULL, NULL, NULL, NULL, '2017-04-02'),
(3, '0003/PPYP/5/05/17', 3, '2017-05-05', '1.0000', 'Ya', '80000.0000', '0.0000', '0.0000', '0.0000', '80000.0000', 'COMPLETED', NULL, NULL, '2017-05-05', 'timotius', NULL, NULL, NULL, NULL, '2017-05-05');

-- --------------------------------------------------------

--
-- Struktur dari tabel `payroll_component`
--

CREATE TABLE IF NOT EXISTS `payroll_component` (
  `id` int(11) NOT NULL,
  `code` varchar(10) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `is_salary` int(11) DEFAULT NULL,
  `is_thr` int(11) DEFAULT NULL,
  `is_bonus` int(11) DEFAULT NULL,
  `reference_document` varchar(300) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(30) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(30) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pay_balken_product`
--

CREATE TABLE IF NOT EXISTS `pay_balken_product` (
  `id` int(12) DEFAULT NULL,
  `payment_balken_id` int(12) DEFAULT NULL,
  `product_code` varchar(20) DEFAULT NULL,
  `qty` decimal(20,4) DEFAULT NULL,
  `price` decimal(20,4) DEFAULT NULL,
  `idr_price` decimal(20,4) DEFAULT NULL,
  `subtotal` decimal(20,4) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(20) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(20) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pay_balken_product`
--

INSERT INTO `pay_balken_product` (`id`, `payment_balken_id`, `product_code`, `qty`, `price`, `idr_price`, `subtotal`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(NULL, 1, 'B-0001              ', '100.0000', '9.0000', '9.0000', '900.0000', '2017-05-05', 'timotius', NULL, NULL, NULL, NULL),
(NULL, 1, 'B-0002              ', '10.0000', '90.0000', '90.0000', '900.0000', '2017-05-05', 'timotius', NULL, NULL, NULL, NULL),
(NULL, 1, 'B-0003              ', '10.0000', '300.0000', '300.0000', '3000.0000', '2017-05-05', 'timotius', NULL, NULL, NULL, NULL),
(NULL, 3, 'B-0002              ', '10.0000', '500.0000', '1000.0000', '10000.0000', '2017-05-06', 'timotius', NULL, NULL, NULL, NULL),
(NULL, 3, 'B-0003              ', '10.0000', '400.0000', '800.0000', '8000.0000', '2017-05-06', 'timotius', NULL, NULL, NULL, NULL),
(NULL, 3, 'B-0025              ', '1.0000', '200.0000', '400.0000', '400.0000', '2017-05-06', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pay_pr_product`
--

CREATE TABLE IF NOT EXISTS `pay_pr_product` (
  `id` int(11) NOT NULL,
  `pay_pr_code` varchar(50) DEFAULT NULL,
  `product_code` varchar(20) DEFAULT NULL,
  `qty` decimal(20,4) DEFAULT NULL,
  `price` decimal(20,4) DEFAULT NULL,
  `idr_price` decimal(20,4) DEFAULT NULL,
  `subtotal` decimal(20,4) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pay_pr_product`
--

INSERT INTO `pay_pr_product` (`id`, `pay_pr_code`, `product_code`, `qty`, `price`, `idr_price`, `subtotal`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, '0001/PPYP/29/03/17', 'PDC009-2', '70.0000', '400.0000', '400.0000', '28000.0000', '2017-03-29', 'timotius', NULL, NULL, NULL, NULL),
(2, '0001/PPYP/29/03/17', 'PDC009-17', '100.0000', '0.0000', '0.0000', '0.0000', '2017-03-29', 'timotius', NULL, NULL, NULL, NULL),
(3, '0002/PPYP/2/04/17', 'PDC009-2', '70.0000', '4000.0000', '4000.0000', '280000.0000', '2017-04-02', 'timotius', NULL, NULL, NULL, NULL),
(4, '0002/PPYP/2/04/17', 'PDC009-17', '100.0000', '500.0000', '500.0000', '50000.0000', '2017-04-02', 'timotius', NULL, NULL, NULL, NULL),
(5, '0003/PPYP/5/05/17', 'PDC009-3', '0.0000', '500.0000', '500.0000', '0.0000', '2017-05-05', 'timotius', NULL, NULL, NULL, NULL),
(6, '0003/PPYP/5/05/17', 'PDC009-18', '200.0000', '400.0000', '400.0000', '80000.0000', '2017-05-05', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pic_docking`
--

CREATE TABLE IF NOT EXISTS `pic_docking` (
  `id` int(11) NOT NULL,
  `received_code` varchar(16) NOT NULL,
  `emp_code` varchar(10) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pic_docking`
--

INSERT INTO `pic_docking` (`id`, `received_code`, `emp_code`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, '0004/BL/26/05/16', '2012345678', '2016-05-27', 'Michael', NULL, NULL, NULL, NULL),
(2, '0011/BL/14/08/16', '2013026132', '2016-08-18', 'Michael', NULL, NULL, NULL, NULL),
(3, '0005/BL/31/08/16', '2013026132', '2016-09-03', 'Michael', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pic_tally`
--

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `pic_tally`
--

INSERT INTO `pic_tally` (`id`, `dry_in_code`, `emp_code`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'M/2016/05/0001', '2013026132', '2016-05-27', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `ppr_note`
--

CREATE TABLE IF NOT EXISTS `ppr_note` (
  `id` int(11) NOT NULL,
  `ppr_code` varchar(50) NOT NULL,
  `note` text NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `ppr_product`
--

CREATE TABLE IF NOT EXISTS `ppr_product` (
  `id` int(11) NOT NULL,
  `ppr_code` varchar(50) NOT NULL,
  `product_code` varchar(20) NOT NULL,
  `qty` decimal(20,4) NOT NULL,
  `unit_price` decimal(20,4) NOT NULL DEFAULT '0.0000',
  `sub_total` decimal(20,4) NOT NULL DEFAULT '0.0000',
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `ppr_product`
--

INSERT INTO `ppr_product` (`id`, `ppr_code`, `product_code`, `qty`, `unit_price`, `sub_total`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, '0001/STTB/9/01/17', 'PDC009-1', '9.0000', '0.0000', '0.0000', '2017-01-09', 'timotius', NULL, NULL, NULL, NULL),
(2, '0001/STTB/9/01/17', 'PDC009-2', '20.0000', '0.0000', '0.0000', '2017-01-09', 'timotius', NULL, NULL, NULL, NULL),
(3, '0002/POH/28/03/17', 'PDC009-17', '110.0000', '500.0000', '50000.0000', '2017-03-28', 'timotius', NULL, NULL, NULL, NULL),
(4, '0002/POH/28/03/17', 'PDC009-2', '30.0000', '4000.0000', '120000.0000', '2017-03-28', 'timotius', NULL, NULL, NULL, NULL),
(5, '0003/POH/29/03/17', 'PDC009-17', '140.0000', '700.0000', '58000.0000', '2017-03-29', 'timotius', NULL, NULL, NULL, NULL),
(6, '0003/POH/29/03/17', 'PDC009-2', '200.0000', '400.0000', '80000.0000', '2017-03-29', 'timotius', NULL, NULL, NULL, NULL),
(7, '0004/POH/30/03/17', 'PDC009-17', '300.0000', '100.0000', '30000.0000', '2017-03-30', 'timotius', NULL, NULL, NULL, NULL),
(8, '0005/POH/5/05/17', 'PDC009-18', '5.0000', '400.0000', '2000.0000', '2017-05-05', 'timotius', NULL, NULL, NULL, NULL),
(9, '0005/POH/5/05/17', 'PDC009-3', '30.0000', '500.0000', '15000.0000', '2017-05-05', 'timotius', NULL, NULL, NULL, NULL),
(10, '0006/POH/8/05/17', 'PDC009-17', '30.0000', '4000.0000', '120000.0000', '2017-05-06', 'timotius', NULL, NULL, NULL, NULL),
(11, '0006/POH/8/05/17', 'PDC009-4', '400.0000', '2000.0000', '800000.0000', '2017-05-06', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pps_product`
--

CREATE TABLE IF NOT EXISTS `pps_product` (
  `id` int(11) NOT NULL,
  `pps_code` varchar(50) NOT NULL,
  `product_code` varchar(20) NOT NULL,
  `qty` decimal(20,4) NOT NULL,
  `unit_price` decimal(20,4) NOT NULL DEFAULT '0.0000',
  `sub_total` decimal(20,4) NOT NULL DEFAULT '0.0000',
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pps_product`
--

INSERT INTO `pps_product` (`id`, `pps_code`, `product_code`, `qty`, `unit_price`, `sub_total`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, '0001/POB/29/10/17', 'SUPP-0001', '1.0000', '1000.0000', '1000.0000', '2017-10-29', 'ADMIN', '2017-11-01', 'ADMIN', NULL, NULL),
(2, '0001/POB/29/10/17', 'SUPP-0002', '200.0000', '200.0000', '40000.0000', '2017-10-29', 'ADMIN', '2017-11-01', 'ADMIN', NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL,
  `product_code` varchar(20) NOT NULL DEFAULT '',
  `product_name` varchar(50) DEFAULT NULL,
  `product_category_id` int(11) DEFAULT NULL,
  `product_status` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
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
  `thickness` decimal(20,4) DEFAULT NULL,
  `length` decimal(20,4) DEFAULT NULL,
  `width` decimal(20,4) DEFAULT NULL,
  `production_type_id` int(11) DEFAULT NULL,
  `production_quality_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `product`
--

INSERT INTO `product` (`id`, `product_code`, `product_name`, `product_category_id`, `product_status`, `product_uom_id`, `is_maintain_stock`, `image_path`, `brand`, `barcode`, `description`, `wood_type_id`, `grade_id`, `thickness_id`, `condition_id`, `is_has_serial`, `is_fixed_asset`, `warranty`, `netto`, `netto_uom_id`, `is_purchase_item`, `minor`, `minor_uom_id`, `lead_time`, `buy_cost_center_id`, `expense_acc_id`, `main_supp_code`, `manufacturer`, `is_sales_item`, `is_service_item`, `sell_cost_center_id`, `income_acc_id`, `max_disc`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `minqty`, `thickness`, `length`, `width`, `production_type_id`, `production_quality_id`) VALUES
(1, 'B-0001              ', 'SENGON BASAH A 100 X 5 X 5                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(2, 'B-0002              ', 'SENGON BASAH A 100 X 5 X 5.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(3, 'B-0003              ', 'SENGON BASAH A 100 X 5 X 6                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(4, 'B-0004              ', 'SENGON BASAH A 100 X 5 X 6.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(5, 'B-0005              ', 'SENGON BASAH A 100 X 5 X 7                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(6, 'B-0006              ', 'SENGON BASAH A 100 X 5 X 7.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(7, 'B-0007              ', 'SENGON BASAH A 100 X 5 X 8                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(8, 'B-0008              ', 'SENGON BASAH A 100 X 5 X 8.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(9, 'B-0009              ', 'SENGON BASAH A 100 X 5 X 9                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(10, 'B-0010              ', 'SENGON BASAH A 100 X 5 X 9.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(11, 'B-0011              ', 'SENGON BASAH A 100 X 5 X 10                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(12, 'B-0012              ', 'SENGON BASAH A 100 X 5 X 10.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(13, 'B-0013              ', 'SENGON BASAH A 100 X 5 X 11                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(14, 'B-0014              ', 'SENGON BASAH A 100 X 5 X 11.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(15, 'B-0015              ', 'SENGON BASAH A 100 X 5 X 12                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(16, 'B-0016              ', 'SENGON BASAH A 100 X 5 X 12.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(17, 'B-0017              ', 'SENGON BASAH A 100 X 5 X 13                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(18, 'B-0018              ', 'SENGON BASAH A 100 X 5 X 13.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(19, 'B-0019              ', 'SENGON BASAH A 100 X 5 X 14                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(20, 'B-0020              ', 'SENGON BASAH A 100 X 5 X 14.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(21, 'B-0021              ', 'SENGON BASAH A 100 X 5 X 15                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(22, 'B-0022              ', 'SENGON BASAH A 100 X 5 X 15.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(23, 'B-0023              ', 'SENGON BASAH A 100 X 5 X 16                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(24, 'B-0024              ', 'SENGON BASAH B 100 X 5 X 5                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(25, 'B-0025              ', 'SENGON BASAH B 100 X 5 X 5.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(26, 'B-0026              ', 'SENGON BASAH B 100 X 5 X 6                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(27, 'B-0027              ', 'SENGON BASAH B 100 X 5 X 6.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(28, 'B-0028              ', 'SENGON BASAH B 100 X 5 X 7                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(29, 'B-0029              ', 'SENGON BASAH B 100 X 5 X 7.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(30, 'B-0030              ', 'SENGON BASAH B 100 X 5 X 8                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(31, 'B-0031              ', 'SENGON BASAH B 100 X 5 X 8.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(32, 'B-0032              ', 'SENGON BASAH B 100 X 5 X 9                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(33, 'B-0033              ', 'SENGON BASAH B 100 X 5 X 9.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(34, 'B-0034              ', 'SENGON BASAH B 100 X 5 X 10                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(35, 'B-0035              ', 'SENGON BASAH B 100 X 5 X 10.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(36, 'B-0036              ', 'SENGON BASAH B 100 X 5 X 11                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(37, 'B-0037              ', 'SENGON BASAH B 100 X 5 X 11.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(38, 'B-0038              ', 'SENGON BASAH B 100 X 5 X 12                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(39, 'B-0039              ', 'SENGON BASAH B 100 X 5 X 12.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(40, 'B-0040              ', 'SENGON BASAH B 100 X 5 X 13                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(41, 'B-0041              ', 'SENGON BASAH B 100 X 5 X 13.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(42, 'B-0042              ', 'SENGON BASAH B 100 X 5 X 14                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(43, 'B-0043              ', 'SENGON BASAH B 100 X 5 X 14.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(44, 'B-0044              ', 'SENGON BASAH B 100 X 5 X 15                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(45, 'B-0045              ', 'SENGON BASAH B 100 X 5 X 15.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(46, 'B-0046              ', 'SENGON BASAH B 100 X 5 X 16                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(47, 'B-0047              ', 'SENGON BASAH ALL 100 X 5 X 5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(48, 'B-0048              ', 'SENGON BASAH ALL 100 X 5 X 5.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(49, 'B-0049              ', 'SENGON BASAH ALL 100 X 5 X 6                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(50, 'B-0050              ', 'SENGON BASAH ALL 100 X 5 X 6.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(51, 'B-0051              ', 'SENGON BASAH ALL 100 X 5 X 7                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(52, 'B-0052              ', 'SENGON BASAH ALL 100 X 5 X 7.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(53, 'B-0053              ', 'SENGON BASAH ALL 100 X 5 X 8                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(54, 'B-0054              ', 'SENGON BASAH ALL 100 X 5 X 8.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(55, 'B-0055              ', 'SENGON BASAH ALL 100 X 5 X 9                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(56, 'B-0056              ', 'SENGON BASAH ALL 100 X 5 X 9.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(57, 'B-0057              ', 'SENGON BASAH ALL 100 X 5 X 10                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(58, 'B-0058              ', 'SENGON BASAH ALL 100 X 5 X 10.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(59, 'B-0059              ', 'SENGON BASAH ALL 100 X 5 X 11                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(60, 'B-0060              ', 'SENGON BASAH ALL 100 X 5 X 11.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(61, 'B-0061              ', 'SENGON BASAH ALL 100 X 5 X 12                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(62, 'B-0062              ', 'SENGON BASAH ALL 100 X 5 X 12.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(63, 'B-0063              ', 'SENGON BASAH ALL 100 X 5 X 13                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(64, 'B-0064              ', 'SENGON BASAH ALL 100 X 5 X 13.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(65, 'B-0065              ', 'SENGON BASAH ALL 100 X 5 X 14                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(66, 'B-0066              ', 'SENGON BASAH ALL 100 X 5 X 14.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(67, 'B-0067              ', 'SENGON BASAH ALL 100 X 5 X 15                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(68, 'B-0068              ', 'SENGON BASAH ALL 100 X 5 X 15.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(69, 'B-0069              ', 'SENGON BASAH ALL 100 X 5 X 16                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(70, 'B-0070              ', 'SENGON BASAH AFB 100 X 5 X 5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(71, 'B-0071              ', 'SENGON BASAH AFB 100 X 5 X 5.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(72, 'B-0072              ', 'SENGON BASAH AFB 100 X 5 X 6                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(73, 'B-0073              ', 'SENGON BASAH AFB 100 X 5 X 6.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(74, 'B-0074              ', 'SENGON BASAH AFB 100 X 5 X 7                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(75, 'B-0075              ', 'SENGON BASAH AFB 100 X 5 X 7.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(76, 'B-0076              ', 'SENGON BASAH AFB 100 X 5 X 8                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(77, 'B-0077              ', 'SENGON BASAH AFB 100 X 5 X 8.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(78, 'B-0078              ', 'SENGON BASAH AFB 100 X 5 X 9                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(79, 'B-0079              ', 'SENGON BASAH AFB 100 X 5 X 9.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(80, 'B-0080              ', 'SENGON BASAH AFB 100 X 5 X 10                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(81, 'B-0081              ', 'SENGON BASAH AFB 100 X 5 X 10.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(82, 'B-0082              ', 'SENGON BASAH AFB 100 X 5 X 11                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(83, 'B-0083              ', 'SENGON BASAH AFB 100 X 5 X 11.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(84, 'B-0084              ', 'SENGON BASAH AFB 100 X 5 X 12                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(85, 'B-0085              ', 'SENGON BASAH AFB 100 X 5 X 12.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(86, 'B-0086              ', 'SENGON BASAH AFB 100 X 5 X 13                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(87, 'B-0087              ', 'SENGON BASAH AFB 100 X 5 X 13.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(88, 'B-0088              ', 'SENGON BASAH AFB 100 X 5 X 14                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(89, 'B-0089              ', 'SENGON BASAH AFB 100 X 5 X 14.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(90, 'B-0090              ', 'SENGON BASAH AFB 100 X 5 X 15                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(91, 'B-0091              ', 'SENGON BASAH AFB 100 X 5 X 15.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(92, 'B-0092              ', 'SENGON BASAH AFB 100 X 5 X 16                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(93, 'B-0093              ', 'SENGON BASAH A 100 X 6 X 5                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(94, 'B-0094              ', 'SENGON BASAH A 100 X 6 X 5.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(95, 'B-0095              ', 'SENGON BASAH A 100 X 6 X 6                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(96, 'B-0096              ', 'SENGON BASAH A 100 X 6 X 6.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(97, 'B-0097              ', 'SENGON BASAH A 100 X 6 X 7                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(98, 'B-0098              ', 'SENGON BASAH A 100 X 6 X 7.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(99, 'B-0099              ', 'SENGON BASAH A 100 X 6 X 8                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(100, 'B-0100              ', 'SENGON BASAH A 100 X 6 X 8.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(101, 'B-0101              ', 'SENGON BASAH A 100 X 6 X 9                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(102, 'B-0102              ', 'SENGON BASAH A 100 X 6 X 9.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(103, 'B-0103              ', 'SENGON BASAH A 100 X 6 X 10                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(104, 'B-0104              ', 'SENGON BASAH A 100 X 6 X 10.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(105, 'B-0105              ', 'SENGON BASAH A 100 X 6 X 11                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(106, 'B-0106              ', 'SENGON BASAH A 100 X 6 X 11.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(107, 'B-0107              ', 'SENGON BASAH A 100 X 6 X 12                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(108, 'B-0108              ', 'SENGON BASAH A 100 X 6 X 12.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(109, 'B-0109              ', 'SENGON BASAH A 100 X 6 X 13                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(110, 'B-0110              ', 'SENGON BASAH A 100 X 6 X 13.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(111, 'B-0111              ', 'SENGON BASAH A 100 X 6 X 14                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(112, 'B-0112              ', 'SENGON BASAH A 100 X 6 X 14.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(113, 'B-0113              ', 'SENGON BASAH A 100 X 6 X 15                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(114, 'B-0114              ', 'SENGON BASAH A 100 X 6 X 15.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(115, 'B-0115              ', 'SENGON BASAH A 100 X 6 X 16                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(116, 'B-0116              ', 'SENGON BASAH B 100 X 6 X 5                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(117, 'B-0117              ', 'SENGON BASAH B 100 X 6 X 5.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(118, 'B-0118              ', 'SENGON BASAH B 100 X 6 X 6                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(119, 'B-0119              ', 'SENGON BASAH B 100 X 6 X 6.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(120, 'B-0120              ', 'SENGON BASAH B 100 X 6 X 7                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(121, 'B-0121              ', 'SENGON BASAH B 100 X 6 X 7.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(122, 'B-0122              ', 'SENGON BASAH B 100 X 6 X 8                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(123, 'B-0123              ', 'SENGON BASAH B 100 X 6 X 8.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(124, 'B-0124              ', 'SENGON BASAH B 100 X 6 X 9                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(125, 'B-0125              ', 'SENGON BASAH B 100 X 6 X 9.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(126, 'B-0126              ', 'SENGON BASAH B 100 X 6 X 10                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(127, 'B-0127              ', 'SENGON BASAH B 100 X 6 X 10.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(128, 'B-0128              ', 'SENGON BASAH B 100 X 6 X 11                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(129, 'B-0129              ', 'SENGON BASAH B 100 X 6 X 11.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(130, 'B-0130              ', 'SENGON BASAH B 100 X 6 X 12                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(131, 'B-0131              ', 'SENGON BASAH B 100 X 6 X 12.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(132, 'B-0132              ', 'SENGON BASAH B 100 X 6 X 13                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(133, 'B-0133              ', 'SENGON BASAH B 100 X 6 X 13.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(134, 'B-0134              ', 'SENGON BASAH B 100 X 6 X 14                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(135, 'B-0135              ', 'SENGON BASAH B 100 X 6 X 14.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(136, 'B-0136              ', 'SENGON BASAH B 100 X 6 X 15                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(137, 'B-0137              ', 'SENGON BASAH B 100 X 6 X 15.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(138, 'B-0138              ', 'SENGON BASAH B 100 X 6 X 16                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(139, 'B-0139              ', 'SENGON BASAH ALL 100 X 6 X 5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(140, 'B-0140              ', 'SENGON BASAH ALL 100 X 6 X 5.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(141, 'B-0141              ', 'SENGON BASAH ALL 100 X 6 X 6                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(142, 'B-0142              ', 'SENGON BASAH ALL 100 X 6 X 6.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(143, 'B-0143              ', 'SENGON BASAH ALL 100 X 6 X 7                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(144, 'B-0144              ', 'SENGON BASAH ALL 100 X 6 X 7.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(145, 'B-0145              ', 'SENGON BASAH ALL 100 X 6 X 8                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(146, 'B-0146              ', 'SENGON BASAH ALL 100 X 6 X 8.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL);
INSERT INTO `product` (`id`, `product_code`, `product_name`, `product_category_id`, `product_status`, `product_uom_id`, `is_maintain_stock`, `image_path`, `brand`, `barcode`, `description`, `wood_type_id`, `grade_id`, `thickness_id`, `condition_id`, `is_has_serial`, `is_fixed_asset`, `warranty`, `netto`, `netto_uom_id`, `is_purchase_item`, `minor`, `minor_uom_id`, `lead_time`, `buy_cost_center_id`, `expense_acc_id`, `main_supp_code`, `manufacturer`, `is_sales_item`, `is_service_item`, `sell_cost_center_id`, `income_acc_id`, `max_disc`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `minqty`, `thickness`, `length`, `width`, `production_type_id`, `production_quality_id`) VALUES
(147, 'B-0147              ', 'SENGON BASAH ALL 100 X 6 X 9                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(148, 'B-0148              ', 'SENGON BASAH ALL 100 X 6 X 9.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(149, 'B-0149              ', 'SENGON BASAH ALL 100 X 6 X 10                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(150, 'B-0150              ', 'SENGON BASAH ALL 100 X 6 X 10.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(151, 'B-0151              ', 'SENGON BASAH ALL 100 X 6 X 11                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(152, 'B-0152              ', 'SENGON BASAH ALL 100 X 6 X 11.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(153, 'B-0153              ', 'SENGON BASAH ALL 100 X 6 X 12                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(154, 'B-0154              ', 'SENGON BASAH ALL 100 X 6 X 12.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(155, 'B-0155              ', 'SENGON BASAH ALL 100 X 6 X 13                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(156, 'B-0156              ', 'SENGON BASAH ALL 100 X 6 X 13.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(157, 'B-0157              ', 'SENGON BASAH ALL 100 X 6 X 14                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(158, 'B-0158              ', 'SENGON BASAH ALL 100 X 6 X 14.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(159, 'B-0159              ', 'SENGON BASAH ALL 100 X 6 X 15                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(160, 'B-0160              ', 'SENGON BASAH ALL 100 X 6 X 15.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(161, 'B-0161              ', 'SENGON BASAH ALL 100 X 6 X 16                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(162, 'B-0162              ', 'SENGON BASAH AFB 100 X 6 X 5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(163, 'B-0163              ', 'SENGON BASAH AFB 100 X 6 X 5.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(164, 'B-0164              ', 'SENGON BASAH AFB 100 X 6 X 6                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(165, 'B-0165              ', 'SENGON BASAH AFB 100 X 6 X 6.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(166, 'B-0166              ', 'SENGON BASAH AFB 100 X 6 X 7                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(167, 'B-0167              ', 'SENGON BASAH AFB 100 X 6 X 7.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(168, 'B-0168              ', 'SENGON BASAH AFB 100 X 6 X 8                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(169, 'B-0169              ', 'SENGON BASAH AFB 100 X 6 X 8.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(170, 'B-0170              ', 'SENGON BASAH AFB 100 X 6 X 9                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(171, 'B-0171              ', 'SENGON BASAH AFB 100 X 6 X 9.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(172, 'B-0172              ', 'SENGON BASAH AFB 100 X 6 X 10                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(173, 'B-0173              ', 'SENGON BASAH AFB 100 X 6 X 10.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(174, 'B-0174              ', 'SENGON BASAH AFB 100 X 6 X 11                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(175, 'B-0175              ', 'SENGON BASAH AFB 100 X 6 X 11.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(176, 'B-0176              ', 'SENGON BASAH AFB 100 X 6 X 12                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(177, 'B-0177              ', 'SENGON BASAH AFB 100 X 6 X 12.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(178, 'B-0178              ', 'SENGON BASAH AFB 100 X 6 X 13                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(179, 'B-0179              ', 'SENGON BASAH AFB 100 X 6 X 13.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(180, 'B-0180              ', 'SENGON BASAH AFB 100 X 6 X 14                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(181, 'B-0181              ', 'SENGON BASAH AFB 100 X 6 X 14.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(182, 'B-0182              ', 'SENGON BASAH AFB 100 X 6 X 15                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(183, 'B-0183              ', 'SENGON BASAH AFB 100 X 6 X 15.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(184, 'B-0184              ', 'SENGON BASAH AFB 100 X 6 X 16                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(185, 'B-0185              ', 'SENGON BASAH A 130 X 5 X 5                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(186, 'B-0186              ', 'SENGON BASAH A 130 X 5 X 5.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(187, 'B-0187              ', 'SENGON BASAH A 130 X 5 X 6                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(188, 'B-0188              ', 'SENGON BASAH A 130 X 5 X 6.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(189, 'B-0189              ', 'SENGON BASAH A 130 X 5 X 7                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(190, 'B-0190              ', 'SENGON BASAH A 130 X 5 X 7.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(191, 'B-0191              ', 'SENGON BASAH A 130 X 5 X 8                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(192, 'B-0192              ', 'SENGON BASAH A 130 X 5 X 8.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(193, 'B-0193              ', 'SENGON BASAH A 130 X 5 X 9                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(194, 'B-0194              ', 'SENGON BASAH A 130 X 5 X 9.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(195, 'B-0195              ', 'SENGON BASAH A 130 X 5 X 10                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(196, 'B-0196              ', 'SENGON BASAH A 130 X 5 X 10.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(197, 'B-0197              ', 'SENGON BASAH A 130 X 5 X 11                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(198, 'B-0198              ', 'SENGON BASAH A 130 X 5 X 11.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(199, 'B-0199              ', 'SENGON BASAH A 130 X 5 X 12                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(200, 'B-0200              ', 'SENGON BASAH A 130 X 5 X 12.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(201, 'B-0201              ', 'SENGON BASAH A 130 X 5 X 13                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(202, 'B-0202              ', 'SENGON BASAH A 130 X 5 X 13.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(203, 'B-0203              ', 'SENGON BASAH A 130 X 5 X 14                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(204, 'B-0204              ', 'SENGON BASAH A 130 X 5 X 14.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(205, 'B-0205              ', 'SENGON BASAH A 130 X 5 X 15                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(206, 'B-0206              ', 'SENGON BASAH A 130 X 5 X 15.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(207, 'B-0207              ', 'SENGON BASAH A 130 X 5 X 16                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(208, 'B-0208              ', 'SENGON BASAH B 130 X 5 X 5                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(209, 'B-0209              ', 'SENGON BASAH B 130 X 5 X 5.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(210, 'B-0210              ', 'SENGON BASAH B 130 X 5 X 6                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(211, 'B-0211              ', 'SENGON BASAH B 130 X 5 X 6.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(212, 'B-0212              ', 'SENGON BASAH B 130 X 5 X 7                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(213, 'B-0213              ', 'SENGON BASAH B 130 X 5 X 7.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(214, 'B-0214              ', 'SENGON BASAH B 130 X 5 X 8                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(215, 'B-0215              ', 'SENGON BASAH B 130 X 5 X 8.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(216, 'B-0216              ', 'SENGON BASAH B 130 X 5 X 9                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(217, 'B-0217              ', 'SENGON BASAH B 130 X 5 X 9.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(218, 'B-0218              ', 'SENGON BASAH B 130 X 5 X 10                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(219, 'B-0219              ', 'SENGON BASAH B 130 X 5 X 10.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(220, 'B-0220              ', 'SENGON BASAH B 130 X 5 X 11                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(221, 'B-0221              ', 'SENGON BASAH B 130 X 5 X 11.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(222, 'B-0222              ', 'SENGON BASAH B 130 X 5 X 12                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(223, 'B-0223              ', 'SENGON BASAH B 130 X 5 X 12.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(224, 'B-0224              ', 'SENGON BASAH B 130 X 5 X 13                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(225, 'B-0225              ', 'SENGON BASAH B 130 X 5 X 13.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(226, 'B-0226              ', 'SENGON BASAH B 130 X 5 X 14                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(227, 'B-0227              ', 'SENGON BASAH B 130 X 5 X 14.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(228, 'B-0228              ', 'SENGON BASAH B 130 X 5 X 15                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(229, 'B-0229              ', 'SENGON BASAH B 130 X 5 X 15.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(230, 'B-0230              ', 'SENGON BASAH B 130 X 5 X 16                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(231, 'B-0231              ', 'SENGON BASAH ALL 130 X 5 X 5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(232, 'B-0232              ', 'SENGON BASAH ALL 130 X 5 X 5.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(233, 'B-0233              ', 'SENGON BASAH ALL 130 X 5 X 6                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(234, 'B-0234              ', 'SENGON BASAH ALL 130 X 5 X 6.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(235, 'B-0235              ', 'SENGON BASAH ALL 130 X 5 X 7                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(236, 'B-0236              ', 'SENGON BASAH ALL 130 X 5 X 7.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(237, 'B-0237              ', 'SENGON BASAH ALL 130 X 5 X 8                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(238, 'B-0238              ', 'SENGON BASAH ALL 130 X 5 X 8.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(239, 'B-0239              ', 'SENGON BASAH ALL 130 X 5 X 9                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(240, 'B-0240              ', 'SENGON BASAH ALL 130 X 5 X 9.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(241, 'B-0241              ', 'SENGON BASAH ALL 130 X 5 X 10                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(242, 'B-0242              ', 'SENGON BASAH ALL 130 X 5 X 10.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(243, 'B-0243              ', 'SENGON BASAH ALL 130 X 5 X 11                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(244, 'B-0244              ', 'SENGON BASAH ALL 130 X 5 X 11.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(245, 'B-0245              ', 'SENGON BASAH ALL 130 X 5 X 12                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(246, 'B-0246              ', 'SENGON BASAH ALL 130 X 5 X 12.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(247, 'B-0247              ', 'SENGON BASAH ALL 130 X 5 X 13                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(248, 'B-0248              ', 'SENGON BASAH ALL 130 X 5 X 13.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(249, 'B-0249              ', 'SENGON BASAH ALL 130 X 5 X 14                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(250, 'B-0250              ', 'SENGON BASAH ALL 130 X 5 X 14.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(251, 'B-0251              ', 'SENGON BASAH ALL 130 X 5 X 15                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(252, 'B-0252              ', 'SENGON BASAH ALL 130 X 5 X 15.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(253, 'B-0253              ', 'SENGON BASAH ALL 130 X 5 X 16                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(254, 'B-0254              ', 'SENGON BASAH AFB 130 X 5 X 5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(255, 'B-0255              ', 'SENGON BASAH AFB 130 X 5 X 5.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(256, 'B-0256              ', 'SENGON BASAH AFB 130 X 5 X 6                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(257, 'B-0257              ', 'SENGON BASAH AFB 130 X 5 X 6.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(258, 'B-0258              ', 'SENGON BASAH AFB 130 X 5 X 7                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(259, 'B-0259              ', 'SENGON BASAH AFB 130 X 5 X 7.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(260, 'B-0260              ', 'SENGON BASAH AFB 130 X 5 X 8                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(261, 'B-0261              ', 'SENGON BASAH AFB 130 X 5 X 8.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(262, 'B-0262              ', 'SENGON BASAH AFB 130 X 5 X 9                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(263, 'B-0263              ', 'SENGON BASAH AFB 130 X 5 X 9.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(264, 'B-0264              ', 'SENGON BASAH AFB 130 X 5 X 10                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(265, 'B-0265              ', 'SENGON BASAH AFB 130 X 5 X 10.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(266, 'B-0266              ', 'SENGON BASAH AFB 130 X 5 X 11                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(267, 'B-0267              ', 'SENGON BASAH AFB 130 X 5 X 11.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(268, 'B-0268              ', 'SENGON BASAH AFB 130 X 5 X 12                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(269, 'B-0269              ', 'SENGON BASAH AFB 130 X 5 X 12.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(270, 'B-0270              ', 'SENGON BASAH AFB 130 X 5 X 13                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(271, 'B-0271              ', 'SENGON BASAH AFB 130 X 5 X 13.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(272, 'B-0272              ', 'SENGON BASAH AFB 130 X 5 X 14                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(273, 'B-0273              ', 'SENGON BASAH AFB 130 X 5 X 14.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(274, 'B-0274              ', 'SENGON BASAH AFB 130 X 5 X 15                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(275, 'B-0275              ', 'SENGON BASAH AFB 130 X 5 X 15.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(276, 'B-0276              ', 'SENGON BASAH AFB 130 X 5 X 16                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(277, 'B-0277              ', 'SENGON BASAH A 130 X 6 X 5                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(278, 'B-0278              ', 'SENGON BASAH A 130 X 6 X 5.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(279, 'B-0279              ', 'SENGON BASAH A 130 X 6 X 6                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(280, 'B-0280              ', 'SENGON BASAH A 130 X 6 X 6.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(281, 'B-0281              ', 'SENGON BASAH A 130 X 6 X 7                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(282, 'B-0282              ', 'SENGON BASAH A 130 X 6 X 7.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(283, 'B-0283              ', 'SENGON BASAH A 130 X 6 X 8                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(284, 'B-0284              ', 'SENGON BASAH A 130 X 6 X 8.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(285, 'B-0285              ', 'SENGON BASAH A 130 X 6 X 9                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(286, 'B-0286              ', 'SENGON BASAH A 130 X 6 X 9.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(287, 'B-0287              ', 'SENGON BASAH A 130 X 6 X 10                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(288, 'B-0288              ', 'SENGON BASAH A 130 X 6 X 10.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(289, 'B-0289              ', 'SENGON BASAH A 130 X 6 X 11                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(290, 'B-0290              ', 'SENGON BASAH A 130 X 6 X 11.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(291, 'B-0291              ', 'SENGON BASAH A 130 X 6 X 12                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(292, 'B-0292              ', 'SENGON BASAH A 130 X 6 X 12.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL);
INSERT INTO `product` (`id`, `product_code`, `product_name`, `product_category_id`, `product_status`, `product_uom_id`, `is_maintain_stock`, `image_path`, `brand`, `barcode`, `description`, `wood_type_id`, `grade_id`, `thickness_id`, `condition_id`, `is_has_serial`, `is_fixed_asset`, `warranty`, `netto`, `netto_uom_id`, `is_purchase_item`, `minor`, `minor_uom_id`, `lead_time`, `buy_cost_center_id`, `expense_acc_id`, `main_supp_code`, `manufacturer`, `is_sales_item`, `is_service_item`, `sell_cost_center_id`, `income_acc_id`, `max_disc`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `minqty`, `thickness`, `length`, `width`, `production_type_id`, `production_quality_id`) VALUES
(293, 'B-0293              ', 'SENGON BASAH A 130 X 6 X 13                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(294, 'B-0294              ', 'SENGON BASAH A 130 X 6 X 13.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(295, 'B-0295              ', 'SENGON BASAH A 130 X 6 X 14                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(296, 'B-0296              ', 'SENGON BASAH A 130 X 6 X 14.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(297, 'B-0297              ', 'SENGON BASAH A 130 X 6 X 15                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(298, 'B-0298              ', 'SENGON BASAH A 130 X 6 X 15.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(299, 'B-0299              ', 'SENGON BASAH A 130 X 6 X 16                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(300, 'B-0300              ', 'SENGON BASAH B 130 X 6 X 5                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(301, 'B-0301              ', 'SENGON BASAH B 130 X 6 X 5.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(302, 'B-0302              ', 'SENGON BASAH B 130 X 6 X 6                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(303, 'B-0303              ', 'SENGON BASAH B 130 X 6 X 6.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(304, 'B-0304              ', 'SENGON BASAH B 130 X 6 X 7                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(305, 'B-0305              ', 'SENGON BASAH B 130 X 6 X 7.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(306, 'B-0306              ', 'SENGON BASAH B 130 X 6 X 8                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(307, 'B-0307              ', 'SENGON BASAH B 130 X 6 X 8.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(308, 'B-0308              ', 'SENGON BASAH B 130 X 6 X 9                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(309, 'B-0309              ', 'SENGON BASAH B 130 X 6 X 9.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(310, 'B-0310              ', 'SENGON BASAH B 130 X 6 X 10                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(311, 'B-0311              ', 'SENGON BASAH B 130 X 6 X 10.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(312, 'B-0312              ', 'SENGON BASAH B 130 X 6 X 11                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(313, 'B-0313              ', 'SENGON BASAH B 130 X 6 X 11.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(314, 'B-0314              ', 'SENGON BASAH B 130 X 6 X 12                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(315, 'B-0315              ', 'SENGON BASAH B 130 X 6 X 12.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(316, 'B-0316              ', 'SENGON BASAH B 130 X 6 X 13                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(317, 'B-0317              ', 'SENGON BASAH B 130 X 6 X 13.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(318, 'B-0318              ', 'SENGON BASAH B 130 X 6 X 14                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(319, 'B-0319              ', 'SENGON BASAH B 130 X 6 X 14.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(320, 'B-0320              ', 'SENGON BASAH B 130 X 6 X 15                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(321, 'B-0321              ', 'SENGON BASAH B 130 X 6 X 15.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(322, 'B-0322              ', 'SENGON BASAH B 130 X 6 X 16                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(323, 'B-0323              ', 'SENGON BASAH ALL 130 X 6 X 5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(324, 'B-0324              ', 'SENGON BASAH ALL 130 X 6 X 5.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(325, 'B-0325              ', 'SENGON BASAH ALL 130 X 6 X 6                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(326, 'B-0326              ', 'SENGON BASAH ALL 130 X 6 X 6.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(327, 'B-0327              ', 'SENGON BASAH ALL 130 X 6 X 7                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(328, 'B-0328              ', 'SENGON BASAH ALL 130 X 6 X 7.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(329, 'B-0329              ', 'SENGON BASAH ALL 130 X 6 X 8                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(330, 'B-0330              ', 'SENGON BASAH ALL 130 X 6 X 8.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(331, 'B-0331              ', 'SENGON BASAH ALL 130 X 6 X 9                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(332, 'B-0332              ', 'SENGON BASAH ALL 130 X 6 X 9.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(333, 'B-0333              ', 'SENGON BASAH ALL 130 X 6 X 10                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(334, 'B-0334              ', 'SENGON BASAH ALL 130 X 6 X 10.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(335, 'B-0335              ', 'SENGON BASAH ALL 130 X 6 X 11                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(336, 'B-0336              ', 'SENGON BASAH ALL 130 X 6 X 11.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(337, 'B-0337              ', 'SENGON BASAH ALL 130 X 6 X 12                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(338, 'B-0338              ', 'SENGON BASAH ALL 130 X 6 X 12.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(339, 'B-0339              ', 'SENGON BASAH ALL 130 X 6 X 13                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(340, 'B-0340              ', 'SENGON BASAH ALL 130 X 6 X 13.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(341, 'B-0341              ', 'SENGON BASAH ALL 130 X 6 X 14                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(342, 'B-0342              ', 'SENGON BASAH ALL 130 X 6 X 14.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(343, 'B-0343              ', 'SENGON BASAH ALL 130 X 6 X 15                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(344, 'B-0344              ', 'SENGON BASAH ALL 130 X 6 X 15.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(345, 'B-0345              ', 'SENGON BASAH ALL 130 X 6 X 16                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(346, 'B-0346              ', 'SENGON BASAH AFB 130 X 6 X 5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(347, 'B-0347              ', 'SENGON BASAH AFB 130 X 6 X 5.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(348, 'B-0348              ', 'SENGON BASAH AFB 130 X 6 X 6                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(349, 'B-0349              ', 'SENGON BASAH AFB 130 X 6 X 6.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(350, 'B-0350              ', 'SENGON BASAH AFB 130 X 6 X 7                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(351, 'B-0351              ', 'SENGON BASAH AFB 130 X 6 X 7.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(352, 'B-0352              ', 'SENGON BASAH AFB 130 X 6 X 8                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(353, 'B-0353              ', 'SENGON BASAH AFB 130 X 6 X 8.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(354, 'B-0354              ', 'SENGON BASAH AFB 130 X 6 X 9                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(355, 'B-0355              ', 'SENGON BASAH AFB 130 X 6 X 9.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(356, 'B-0356              ', 'SENGON BASAH AFB 130 X 6 X 10                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(357, 'B-0357              ', 'SENGON BASAH AFB 130 X 6 X 10.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(358, 'B-0358              ', 'SENGON BASAH AFB 130 X 6 X 11                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(359, 'B-0359              ', 'SENGON BASAH AFB 130 X 6 X 11.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(360, 'B-0360              ', 'SENGON BASAH AFB 130 X 6 X 12                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(361, 'B-0361              ', 'SENGON BASAH AFB 130 X 6 X 12.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(362, 'B-0362              ', 'SENGON BASAH AFB 130 X 6 X 13                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(363, 'B-0363              ', 'SENGON BASAH AFB 130 X 6 X 13.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(364, 'B-0364              ', 'SENGON BASAH AFB 130 X 6 X 14                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(365, 'B-0365              ', 'SENGON BASAH AFB 130 X 6 X 14.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(366, 'B-0366              ', 'SENGON BASAH AFB 130 X 6 X 15                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(367, 'B-0367              ', 'SENGON BASAH AFB 130 X 6 X 15.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(368, 'B-0368              ', 'SENGON BASAH AFB 130 X 6 X 16                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(369, 'K-0001              ', 'SENGON KERING A 100 X 5 X 5                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(370, 'K-0002              ', 'SENGON KERING A 100 X 5 X 5.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(371, 'K-0003              ', 'SENGON KERING A 100 X 5 X 6                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(372, 'K-0004              ', 'SENGON KERING A 100 X 5 X 6.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(373, 'K-0005              ', 'SENGON KERING A 100 X 5 X 7                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(374, 'K-0006              ', 'SENGON KERING A 100 X 5 X 7.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(375, 'K-0007              ', 'SENGON KERING A 100 X 5 X 8                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(376, 'K-0008              ', 'SENGON KERING A 100 X 5 X 8.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(377, 'K-0009              ', 'SENGON KERING A 100 X 5 X 9                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(378, 'K-0010              ', 'SENGON KERING A 100 X 5 X 9.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(379, 'K-0011              ', 'SENGON KERING A 100 X 5 X 10                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(380, 'K-0012              ', 'SENGON KERING A 100 X 5 X 10.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(381, 'K-0013              ', 'SENGON KERING A 100 X 5 X 11                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(382, 'K-0014              ', 'SENGON KERING A 100 X 5 X 11.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(383, 'K-0015              ', 'SENGON KERING A 100 X 5 X 12                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(384, 'K-0016              ', 'SENGON KERING A 100 X 5 X 12.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(385, 'K-0017              ', 'SENGON KERING A 100 X 5 X 13                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(386, 'K-0018              ', 'SENGON KERING A 100 X 5 X 13.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(387, 'K-0019              ', 'SENGON KERING A 100 X 5 X 14                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(388, 'K-0020              ', 'SENGON KERING A 100 X 5 X 14.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(389, 'K-0021              ', 'SENGON KERING A 100 X 5 X 15                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(390, 'K-0022              ', 'SENGON KERING A 100 X 5 X 15.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(391, 'K-0023              ', 'SENGON KERING A 100 X 5 X 16                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(392, 'K-0024              ', 'SENGON KERING B 100 X 5 X 5                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(393, 'K-0025              ', 'SENGON KERING B 100 X 5 X 5.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(394, 'K-0026              ', 'SENGON KERING B 100 X 5 X 6                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(395, 'K-0027              ', 'SENGON KERING B 100 X 5 X 6.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(396, 'K-0028              ', 'SENGON KERING B 100 X 5 X 7                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(397, 'K-0029              ', 'SENGON KERING B 100 X 5 X 7.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(398, 'K-0030              ', 'SENGON KERING B 100 X 5 X 8                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(399, 'K-0031              ', 'SENGON KERING B 100 X 5 X 8.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(400, 'K-0032              ', 'SENGON KERING B 100 X 5 X 9                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(401, 'K-0033              ', 'SENGON KERING B 100 X 5 X 9.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(402, 'K-0034              ', 'SENGON KERING B 100 X 5 X 10                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(403, 'K-0035              ', 'SENGON KERING B 100 X 5 X 10.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(404, 'K-0036              ', 'SENGON KERING B 100 X 5 X 11                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(405, 'K-0037              ', 'SENGON KERING B 100 X 5 X 11.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(406, 'K-0038              ', 'SENGON KERING B 100 X 5 X 12                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(407, 'K-0039              ', 'SENGON KERING B 100 X 5 X 12.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(408, 'K-0040              ', 'SENGON KERING B 100 X 5 X 13                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(409, 'K-0041              ', 'SENGON KERING B 100 X 5 X 13.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(410, 'K-0042              ', 'SENGON KERING B 100 X 5 X 14                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(411, 'K-0043              ', 'SENGON KERING B 100 X 5 X 14.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(412, 'K-0044              ', 'SENGON KERING B 100 X 5 X 15                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(413, 'K-0045              ', 'SENGON KERING B 100 X 5 X 15.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(414, 'K-0046              ', 'SENGON KERING B 100 X 5 X 16                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(415, 'K-0047              ', 'SENGON KERING ALL 100 X 5 X 5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(416, 'K-0048              ', 'SENGON KERING ALL 100 X 5 X 5.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(417, 'K-0049              ', 'SENGON KERING ALL 100 X 5 X 6                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(418, 'K-0050              ', 'SENGON KERING ALL 100 X 5 X 6.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(419, 'K-0051              ', 'SENGON KERING ALL 100 X 5 X 7                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(420, 'K-0052              ', 'SENGON KERING ALL 100 X 5 X 7.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(421, 'K-0053              ', 'SENGON KERING ALL 100 X 5 X 8                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(422, 'K-0054              ', 'SENGON KERING ALL 100 X 5 X 8.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(423, 'K-0055              ', 'SENGON KERING ALL 100 X 5 X 9                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(424, 'K-0056              ', 'SENGON KERING ALL 100 X 5 X 9.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(425, 'K-0057              ', 'SENGON KERING ALL 100 X 5 X 10                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(426, 'K-0058              ', 'SENGON KERING ALL 100 X 5 X 10.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(427, 'K-0059              ', 'SENGON KERING ALL 100 X 5 X 11                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(428, 'K-0060              ', 'SENGON KERING ALL 100 X 5 X 11.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(429, 'K-0061              ', 'SENGON KERING ALL 100 X 5 X 12                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(430, 'K-0062              ', 'SENGON KERING ALL 100 X 5 X 12.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(431, 'K-0063              ', 'SENGON KERING ALL 100 X 5 X 13                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(432, 'K-0064              ', 'SENGON KERING ALL 100 X 5 X 13.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(433, 'K-0065              ', 'SENGON KERING ALL 100 X 5 X 14                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(434, 'K-0066              ', 'SENGON KERING ALL 100 X 5 X 14.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(435, 'K-0067              ', 'SENGON KERING ALL 100 X 5 X 15                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(436, 'K-0068              ', 'SENGON KERING ALL 100 X 5 X 15.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(437, 'K-0069              ', 'SENGON KERING ALL 100 X 5 X 16                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(438, 'K-0070              ', 'SENGON KERING AFB 100 X 5 X 5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL);
INSERT INTO `product` (`id`, `product_code`, `product_name`, `product_category_id`, `product_status`, `product_uom_id`, `is_maintain_stock`, `image_path`, `brand`, `barcode`, `description`, `wood_type_id`, `grade_id`, `thickness_id`, `condition_id`, `is_has_serial`, `is_fixed_asset`, `warranty`, `netto`, `netto_uom_id`, `is_purchase_item`, `minor`, `minor_uom_id`, `lead_time`, `buy_cost_center_id`, `expense_acc_id`, `main_supp_code`, `manufacturer`, `is_sales_item`, `is_service_item`, `sell_cost_center_id`, `income_acc_id`, `max_disc`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `minqty`, `thickness`, `length`, `width`, `production_type_id`, `production_quality_id`) VALUES
(439, 'K-0071              ', 'SENGON KERING AFB 100 X 5 X 5.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(440, 'K-0072              ', 'SENGON KERING AFB 100 X 5 X 6                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(441, 'K-0073              ', 'SENGON KERING AFB 100 X 5 X 6.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(442, 'K-0074              ', 'SENGON KERING AFB 100 X 5 X 7                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(443, 'K-0075              ', 'SENGON KERING AFB 100 X 5 X 7.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(444, 'K-0076              ', 'SENGON KERING AFB 100 X 5 X 8                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(445, 'K-0077              ', 'SENGON KERING AFB 100 X 5 X 8.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(446, 'K-0078              ', 'SENGON KERING AFB 100 X 5 X 9                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(447, 'K-0079              ', 'SENGON KERING AFB 100 X 5 X 9.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(448, 'K-0080              ', 'SENGON KERING AFB 100 X 5 X 10                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(449, 'K-0081              ', 'SENGON KERING AFB 100 X 5 X 10.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(450, 'K-0082              ', 'SENGON KERING AFB 100 X 5 X 11                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(451, 'K-0083              ', 'SENGON KERING AFB 100 X 5 X 11.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(452, 'K-0084              ', 'SENGON KERING AFB 100 X 5 X 12                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(453, 'K-0085              ', 'SENGON KERING AFB 100 X 5 X 12.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(454, 'K-0086              ', 'SENGON KERING AFB 100 X 5 X 13                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(455, 'K-0087              ', 'SENGON KERING AFB 100 X 5 X 13.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(456, 'K-0088              ', 'SENGON KERING AFB 100 X 5 X 14                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(457, 'K-0089              ', 'SENGON KERING AFB 100 X 5 X 14.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(458, 'K-0090              ', 'SENGON KERING AFB 100 X 5 X 15                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(459, 'K-0091              ', 'SENGON KERING AFB 100 X 5 X 15.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(460, 'K-0092              ', 'SENGON KERING AFB 100 X 5 X 16                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(461, 'K-0093              ', 'SENGON KERING A 100 X 6 X 5                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(462, 'K-0094              ', 'SENGON KERING A 100 X 6 X 5.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(463, 'K-0095              ', 'SENGON KERING A 100 X 6 X 6                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(464, 'K-0096              ', 'SENGON KERING A 100 X 6 X 6.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(465, 'K-0097              ', 'SENGON KERING A 100 X 6 X 7                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(466, 'K-0098              ', 'SENGON KERING A 100 X 6 X 7.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(467, 'K-0099              ', 'SENGON KERING A 100 X 6 X 8                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(468, 'K-0100              ', 'SENGON KERING A 100 X 6 X 8.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(469, 'K-0101              ', 'SENGON KERING A 100 X 6 X 9                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(470, 'K-0102              ', 'SENGON KERING A 100 X 6 X 9.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(471, 'K-0103              ', 'SENGON KERING A 100 X 6 X 10                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(472, 'K-0104              ', 'SENGON KERING A 100 X 6 X 10.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(473, 'K-0105              ', 'SENGON KERING A 100 X 6 X 11                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(474, 'K-0106              ', 'SENGON KERING A 100 X 6 X 11.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(475, 'K-0107              ', 'SENGON KERING A 100 X 6 X 12                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(476, 'K-0108              ', 'SENGON KERING A 100 X 6 X 12.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(477, 'K-0109              ', 'SENGON KERING A 100 X 6 X 13                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(478, 'K-0110              ', 'SENGON KERING A 100 X 6 X 13.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(479, 'K-0111              ', 'SENGON KERING A 100 X 6 X 14                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(480, 'K-0112              ', 'SENGON KERING A 100 X 6 X 14.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(481, 'K-0113              ', 'SENGON KERING A 100 X 6 X 15                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(482, 'K-0114              ', 'SENGON KERING A 100 X 6 X 15.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(483, 'K-0115              ', 'SENGON KERING A 100 X 6 X 16                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(484, 'K-0116              ', 'SENGON KERING B 100 X 6 X 5                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(485, 'K-0117              ', 'SENGON KERING B 100 X 6 X 5.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(486, 'K-0118              ', 'SENGON KERING B 100 X 6 X 6                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(487, 'K-0119              ', 'SENGON KERING B 100 X 6 X 6.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(488, 'K-0120              ', 'SENGON KERING B 100 X 6 X 7                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(489, 'K-0121              ', 'SENGON KERING B 100 X 6 X 7.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(490, 'K-0122              ', 'SENGON KERING B 100 X 6 X 8                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(491, 'K-0123              ', 'SENGON KERING B 100 X 6 X 8.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(492, 'K-0124              ', 'SENGON KERING B 100 X 6 X 9                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(493, 'K-0125              ', 'SENGON KERING B 100 X 6 X 9.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(494, 'K-0126              ', 'SENGON KERING B 100 X 6 X 10                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(495, 'K-0127              ', 'SENGON KERING B 100 X 6 X 10.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(496, 'K-0128              ', 'SENGON KERING B 100 X 6 X 11                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(497, 'K-0129              ', 'SENGON KERING B 100 X 6 X 11.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(498, 'K-0130              ', 'SENGON KERING B 100 X 6 X 12                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(499, 'K-0131              ', 'SENGON KERING B 100 X 6 X 12.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(500, 'K-0132              ', 'SENGON KERING B 100 X 6 X 13                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(501, 'K-0133              ', 'SENGON KERING B 100 X 6 X 13.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(502, 'K-0134              ', 'SENGON KERING B 100 X 6 X 14                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(503, 'K-0135              ', 'SENGON KERING B 100 X 6 X 14.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(504, 'K-0136              ', 'SENGON KERING B 100 X 6 X 15                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(505, 'K-0137              ', 'SENGON KERING B 100 X 6 X 15.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(506, 'K-0138              ', 'SENGON KERING B 100 X 6 X 16                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(507, 'K-0139              ', 'SENGON KERING ALL 100 X 6 X 5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(508, 'K-0140              ', 'SENGON KERING ALL 100 X 6 X 5.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(509, 'K-0141              ', 'SENGON KERING ALL 100 X 6 X 6                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(510, 'K-0142              ', 'SENGON KERING ALL 100 X 6 X 6.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(511, 'K-0143              ', 'SENGON KERING ALL 100 X 6 X 7                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(512, 'K-0144              ', 'SENGON KERING ALL 100 X 6 X 7.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(513, 'K-0145              ', 'SENGON KERING ALL 100 X 6 X 8                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(514, 'K-0146              ', 'SENGON KERING ALL 100 X 6 X 8.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(515, 'K-0147              ', 'SENGON KERING ALL 100 X 6 X 9                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(516, 'K-0148              ', 'SENGON KERING ALL 100 X 6 X 9.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(517, 'K-0149              ', 'SENGON KERING ALL 100 X 6 X 10                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(518, 'K-0150              ', 'SENGON KERING ALL 100 X 6 X 10.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(519, 'K-0151              ', 'SENGON KERING ALL 100 X 6 X 11                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(520, 'K-0152              ', 'SENGON KERING ALL 100 X 6 X 11.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(521, 'K-0153              ', 'SENGON KERING ALL 100 X 6 X 12                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(522, 'K-0154              ', 'SENGON KERING ALL 100 X 6 X 12.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(523, 'K-0155              ', 'SENGON KERING ALL 100 X 6 X 13                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(524, 'K-0156              ', 'SENGON KERING ALL 100 X 6 X 13.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(525, 'K-0157              ', 'SENGON KERING ALL 100 X 6 X 14                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(526, 'K-0158              ', 'SENGON KERING ALL 100 X 6 X 14.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(527, 'K-0159              ', 'SENGON KERING ALL 100 X 6 X 15                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(528, 'K-0160              ', 'SENGON KERING ALL 100 X 6 X 15.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(529, 'K-0161              ', 'SENGON KERING ALL 100 X 6 X 16                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(530, 'K-0162              ', 'SENGON KERING AFB 100 X 6 X 5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(531, 'K-0163              ', 'SENGON KERING AFB 100 X 6 X 5.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(532, 'K-0164              ', 'SENGON KERING AFB 100 X 6 X 6                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(533, 'K-0165              ', 'SENGON KERING AFB 100 X 6 X 6.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(534, 'K-0166              ', 'SENGON KERING AFB 100 X 6 X 7                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(535, 'K-0167              ', 'SENGON KERING AFB 100 X 6 X 7.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(536, 'K-0168              ', 'SENGON KERING AFB 100 X 6 X 8                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(537, 'K-0169              ', 'SENGON KERING AFB 100 X 6 X 8.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(538, 'K-0170              ', 'SENGON KERING AFB 100 X 6 X 9                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(539, 'K-0171              ', 'SENGON KERING AFB 100 X 6 X 9.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(540, 'K-0172              ', 'SENGON KERING AFB 100 X 6 X 10                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(541, 'K-0173              ', 'SENGON KERING AFB 100 X 6 X 10.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(542, 'K-0174              ', 'SENGON KERING AFB 100 X 6 X 11                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(543, 'K-0175              ', 'SENGON KERING AFB 100 X 6 X 11.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(544, 'K-0176              ', 'SENGON KERING AFB 100 X 6 X 12                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(545, 'K-0177              ', 'SENGON KERING AFB 100 X 6 X 12.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(546, 'K-0178              ', 'SENGON KERING AFB 100 X 6 X 13                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(547, 'K-0179              ', 'SENGON KERING AFB 100 X 6 X 13.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(548, 'K-0180              ', 'SENGON KERING AFB 100 X 6 X 14                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(549, 'K-0181              ', 'SENGON KERING AFB 100 X 6 X 14.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(550, 'K-0182              ', 'SENGON KERING AFB 100 X 6 X 15                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(551, 'K-0183              ', 'SENGON KERING AFB 100 X 6 X 15.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(552, 'K-0184              ', 'SENGON KERING AFB 100 X 6 X 16                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(553, 'K-0185              ', 'SENGON KERING A 130 X 5 X 5                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(554, 'K-0186              ', 'SENGON KERING A 130 X 5 X 5.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(555, 'K-0187              ', 'SENGON KERING A 130 X 5 X 6                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(556, 'K-0188              ', 'SENGON KERING A 130 X 5 X 6.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(557, 'K-0189              ', 'SENGON KERING A 130 X 5 X 7                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(558, 'K-0190              ', 'SENGON KERING A 130 X 5 X 7.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(559, 'K-0191              ', 'SENGON KERING A 130 X 5 X 8                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(560, 'K-0192              ', 'SENGON KERING A 130 X 5 X 8.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(561, 'K-0193              ', 'SENGON KERING A 130 X 5 X 9                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(562, 'K-0194              ', 'SENGON KERING A 130 X 5 X 9.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(563, 'K-0195              ', 'SENGON KERING A 130 X 5 X 10                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(564, 'K-0196              ', 'SENGON KERING A 130 X 5 X 10.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(565, 'K-0197              ', 'SENGON KERING A 130 X 5 X 11                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(566, 'K-0198              ', 'SENGON KERING A 130 X 5 X 11.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(567, 'K-0199              ', 'SENGON KERING A 130 X 5 X 12                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(568, 'K-0200              ', 'SENGON KERING A 130 X 5 X 12.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(569, 'K-0201              ', 'SENGON KERING A 130 X 5 X 13                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(570, 'K-0202              ', 'SENGON KERING A 130 X 5 X 13.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(571, 'K-0203              ', 'SENGON KERING A 130 X 5 X 14                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(572, 'K-0204              ', 'SENGON KERING A 130 X 5 X 14.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(573, 'K-0205              ', 'SENGON KERING A 130 X 5 X 15                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(574, 'K-0206              ', 'SENGON KERING A 130 X 5 X 15.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(575, 'K-0207              ', 'SENGON KERING A 130 X 5 X 16                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(576, 'K-0208              ', 'SENGON KERING B 130 X 5 X 5                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(577, 'K-0209              ', 'SENGON KERING B 130 X 5 X 5.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(578, 'K-0210              ', 'SENGON KERING B 130 X 5 X 6                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(579, 'K-0211              ', 'SENGON KERING B 130 X 5 X 6.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(580, 'K-0212              ', 'SENGON KERING B 130 X 5 X 7                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(581, 'K-0213              ', 'SENGON KERING B 130 X 5 X 7.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(582, 'K-0214              ', 'SENGON KERING B 130 X 5 X 8                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(583, 'K-0215              ', 'SENGON KERING B 130 X 5 X 8.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(584, 'K-0216              ', 'SENGON KERING B 130 X 5 X 9                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL);
INSERT INTO `product` (`id`, `product_code`, `product_name`, `product_category_id`, `product_status`, `product_uom_id`, `is_maintain_stock`, `image_path`, `brand`, `barcode`, `description`, `wood_type_id`, `grade_id`, `thickness_id`, `condition_id`, `is_has_serial`, `is_fixed_asset`, `warranty`, `netto`, `netto_uom_id`, `is_purchase_item`, `minor`, `minor_uom_id`, `lead_time`, `buy_cost_center_id`, `expense_acc_id`, `main_supp_code`, `manufacturer`, `is_sales_item`, `is_service_item`, `sell_cost_center_id`, `income_acc_id`, `max_disc`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `minqty`, `thickness`, `length`, `width`, `production_type_id`, `production_quality_id`) VALUES
(585, 'K-0217              ', 'SENGON KERING B 130 X 5 X 9.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(586, 'K-0218              ', 'SENGON KERING B 130 X 5 X 10                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(587, 'K-0219              ', 'SENGON KERING B 130 X 5 X 10.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(588, 'K-0220              ', 'SENGON KERING B 130 X 5 X 11                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(589, 'K-0221              ', 'SENGON KERING B 130 X 5 X 11.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(590, 'K-0222              ', 'SENGON KERING B 130 X 5 X 12                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(591, 'K-0223              ', 'SENGON KERING B 130 X 5 X 12.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(592, 'K-0224              ', 'SENGON KERING B 130 X 5 X 13                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(593, 'K-0225              ', 'SENGON KERING B 130 X 5 X 13.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(594, 'K-0226              ', 'SENGON KERING B 130 X 5 X 14                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(595, 'K-0227              ', 'SENGON KERING B 130 X 5 X 14.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(596, 'K-0228              ', 'SENGON KERING B 130 X 5 X 15                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(597, 'K-0229              ', 'SENGON KERING B 130 X 5 X 15.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(598, 'K-0230              ', 'SENGON KERING B 130 X 5 X 16                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(599, 'K-0231              ', 'SENGON KERING ALL 130 X 5 X 5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(600, 'K-0232              ', 'SENGON KERING ALL 130 X 5 X 5.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(601, 'K-0233              ', 'SENGON KERING ALL 130 X 5 X 6                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(602, 'K-0234              ', 'SENGON KERING ALL 130 X 5 X 6.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(603, 'K-0235              ', 'SENGON KERING ALL 130 X 5 X 7                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(604, 'K-0236              ', 'SENGON KERING ALL 130 X 5 X 7.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(605, 'K-0237              ', 'SENGON KERING ALL 130 X 5 X 8                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(606, 'K-0238              ', 'SENGON KERING ALL 130 X 5 X 8.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(607, 'K-0239              ', 'SENGON KERING ALL 130 X 5 X 9                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(608, 'K-0240              ', 'SENGON KERING ALL 130 X 5 X 9.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(609, 'K-0241              ', 'SENGON KERING ALL 130 X 5 X 10                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(610, 'K-0242              ', 'SENGON KERING ALL 130 X 5 X 10.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(611, 'K-0243              ', 'SENGON KERING ALL 130 X 5 X 11                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(612, 'K-0244              ', 'SENGON KERING ALL 130 X 5 X 11.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(613, 'K-0245              ', 'SENGON KERING ALL 130 X 5 X 12                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(614, 'K-0246              ', 'SENGON KERING ALL 130 X 5 X 12.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(615, 'K-0247              ', 'SENGON KERING ALL 130 X 5 X 13                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(616, 'K-0248              ', 'SENGON KERING ALL 130 X 5 X 13.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(617, 'K-0249              ', 'SENGON KERING ALL 130 X 5 X 14                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(618, 'K-0250              ', 'SENGON KERING ALL 130 X 5 X 14.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(619, 'K-0251              ', 'SENGON KERING ALL 130 X 5 X 15                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(620, 'K-0252              ', 'SENGON KERING ALL 130 X 5 X 15.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(621, 'K-0253              ', 'SENGON KERING ALL 130 X 5 X 16                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(622, 'K-0254              ', 'SENGON KERING AFB 130 X 5 X 5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(623, 'K-0255              ', 'SENGON KERING AFB 130 X 5 X 5.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(624, 'K-0256              ', 'SENGON KERING AFB 130 X 5 X 6                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(625, 'K-0257              ', 'SENGON KERING AFB 130 X 5 X 6.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(626, 'K-0258              ', 'SENGON KERING AFB 130 X 5 X 7                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(627, 'K-0259              ', 'SENGON KERING AFB 130 X 5 X 7.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(628, 'K-0260              ', 'SENGON KERING AFB 130 X 5 X 8                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(629, 'K-0261              ', 'SENGON KERING AFB 130 X 5 X 8.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(630, 'K-0262              ', 'SENGON KERING AFB 130 X 5 X 9                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(631, 'K-0263              ', 'SENGON KERING AFB 130 X 5 X 9.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(632, 'K-0264              ', 'SENGON KERING AFB 130 X 5 X 10                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(633, 'K-0265              ', 'SENGON KERING AFB 130 X 5 X 10.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(634, 'K-0266              ', 'SENGON KERING AFB 130 X 5 X 11                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(635, 'K-0267              ', 'SENGON KERING AFB 130 X 5 X 11.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(636, 'K-0268              ', 'SENGON KERING AFB 130 X 5 X 12                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(637, 'K-0269              ', 'SENGON KERING AFB 130 X 5 X 12.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(638, 'K-0270              ', 'SENGON KERING AFB 130 X 5 X 13                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(639, 'K-0271              ', 'SENGON KERING AFB 130 X 5 X 13.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(640, 'K-0272              ', 'SENGON KERING AFB 130 X 5 X 14                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(641, 'K-0273              ', 'SENGON KERING AFB 130 X 5 X 14.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(642, 'K-0274              ', 'SENGON KERING AFB 130 X 5 X 15                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(643, 'K-0275              ', 'SENGON KERING AFB 130 X 5 X 15.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(644, 'K-0276              ', 'SENGON KERING AFB 130 X 5 X 16                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(645, 'K-0277              ', 'SENGON KERING A 130 X 6 X 5                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(646, 'K-0278              ', 'SENGON KERING A 130 X 6 X 5.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(647, 'K-0279              ', 'SENGON KERING A 130 X 6 X 6                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(648, 'K-0280              ', 'SENGON KERING A 130 X 6 X 6.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(649, 'K-0281              ', 'SENGON KERING A 130 X 6 X 7                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(650, 'K-0282              ', 'SENGON KERING A 130 X 6 X 7.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(651, 'K-0283              ', 'SENGON KERING A 130 X 6 X 8                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(652, 'K-0284              ', 'SENGON KERING A 130 X 6 X 8.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(653, 'K-0285              ', 'SENGON KERING A 130 X 6 X 9                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(654, 'K-0286              ', 'SENGON KERING A 130 X 6 X 9.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(655, 'K-0287              ', 'SENGON KERING A 130 X 6 X 10                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(656, 'K-0288              ', 'SENGON KERING A 130 X 6 X 10.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(657, 'K-0289              ', 'SENGON KERING A 130 X 6 X 11                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(658, 'K-0290              ', 'SENGON KERING A 130 X 6 X 11.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(659, 'K-0291              ', 'SENGON KERING A 130 X 6 X 12                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(660, 'K-0292              ', 'SENGON KERING A 130 X 6 X 12.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(661, 'K-0293              ', 'SENGON KERING A 130 X 6 X 13                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(662, 'K-0294              ', 'SENGON KERING A 130 X 6 X 13.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(663, 'K-0295              ', 'SENGON KERING A 130 X 6 X 14                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(664, 'K-0296              ', 'SENGON KERING A 130 X 6 X 14.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(665, 'K-0297              ', 'SENGON KERING A 130 X 6 X 15                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(666, 'K-0298              ', 'SENGON KERING A 130 X 6 X 15.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(667, 'K-0299              ', 'SENGON KERING A 130 X 6 X 16                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(668, 'K-0300              ', 'SENGON KERING B 130 X 6 X 5                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(669, 'K-0301              ', 'SENGON KERING B 130 X 6 X 5.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(670, 'K-0302              ', 'SENGON KERING B 130 X 6 X 6                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(671, 'K-0303              ', 'SENGON KERING B 130 X 6 X 6.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(672, 'K-0304              ', 'SENGON KERING B 130 X 6 X 7                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(673, 'K-0305              ', 'SENGON KERING B 130 X 6 X 7.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(674, 'K-0306              ', 'SENGON KERING B 130 X 6 X 8                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(675, 'K-0307              ', 'SENGON KERING B 130 X 6 X 8.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(676, 'K-0308              ', 'SENGON KERING B 130 X 6 X 9                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(677, 'K-0309              ', 'SENGON KERING B 130 X 6 X 9.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(678, 'K-0310              ', 'SENGON KERING B 130 X 6 X 10                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(679, 'K-0311              ', 'SENGON KERING B 130 X 6 X 10.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(680, 'K-0312              ', 'SENGON KERING B 130 X 6 X 11                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(681, 'K-0313              ', 'SENGON KERING B 130 X 6 X 11.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(682, 'K-0314              ', 'SENGON KERING B 130 X 6 X 12                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(683, 'K-0315              ', 'SENGON KERING B 130 X 6 X 12.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(684, 'K-0316              ', 'SENGON KERING B 130 X 6 X 13                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(685, 'K-0317              ', 'SENGON KERING B 130 X 6 X 13.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(686, 'K-0318              ', 'SENGON KERING B 130 X 6 X 14                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(687, 'K-0319              ', 'SENGON KERING B 130 X 6 X 14.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(688, 'K-0320              ', 'SENGON KERING B 130 X 6 X 15                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(689, 'K-0321              ', 'SENGON KERING B 130 X 6 X 15.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(690, 'K-0322              ', 'SENGON KERING B 130 X 6 X 16                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(691, 'K-0323              ', 'SENGON KERING ALL 130 X 6 X 5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(692, 'K-0324              ', 'SENGON KERING ALL 130 X 6 X 5.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(693, 'K-0325              ', 'SENGON KERING ALL 130 X 6 X 6                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(694, 'K-0326              ', 'SENGON KERING ALL 130 X 6 X 6.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(695, 'K-0327              ', 'SENGON KERING ALL 130 X 6 X 7                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(696, 'K-0328              ', 'SENGON KERING ALL 130 X 6 X 7.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(697, 'K-0329              ', 'SENGON KERING ALL 130 X 6 X 8                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(698, 'K-0330              ', 'SENGON KERING ALL 130 X 6 X 8.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(699, 'K-0331              ', 'SENGON KERING ALL 130 X 6 X 9                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(700, 'K-0332              ', 'SENGON KERING ALL 130 X 6 X 9.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(701, 'K-0333              ', 'SENGON KERING ALL 130 X 6 X 10                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(702, 'K-0334              ', 'SENGON KERING ALL 130 X 6 X 10.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(703, 'K-0335              ', 'SENGON KERING ALL 130 X 6 X 11                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(704, 'K-0336              ', 'SENGON KERING ALL 130 X 6 X 11.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(705, 'K-0337              ', 'SENGON KERING ALL 130 X 6 X 12                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(706, 'K-0338              ', 'SENGON KERING ALL 130 X 6 X 12.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(707, 'K-0339              ', 'SENGON KERING ALL 130 X 6 X 13                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(708, 'K-0340              ', 'SENGON KERING ALL 130 X 6 X 13.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(709, 'K-0341              ', 'SENGON KERING ALL 130 X 6 X 14                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(710, 'K-0342              ', 'SENGON KERING ALL 130 X 6 X 14.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(711, 'K-0343              ', 'SENGON KERING ALL 130 X 6 X 15                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(712, 'K-0344              ', 'SENGON KERING ALL 130 X 6 X 15.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(713, 'K-0345              ', 'SENGON KERING ALL 130 X 6 X 16                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(714, 'K-0346              ', 'SENGON KERING AFB 130 X 6 X 5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(715, 'K-0347              ', 'SENGON KERING AFB 130 X 6 X 5.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(716, 'K-0348              ', 'SENGON KERING AFB 130 X 6 X 6                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(717, 'K-0349              ', 'SENGON KERING AFB 130 X 6 X 6.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(718, 'K-0350              ', 'SENGON KERING AFB 130 X 6 X 7                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(719, 'K-0351              ', 'SENGON KERING AFB 130 X 6 X 7.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(720, 'K-0352              ', 'SENGON KERING AFB 130 X 6 X 8                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(721, 'K-0353              ', 'SENGON KERING AFB 130 X 6 X 8.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(722, 'K-0354              ', 'SENGON KERING AFB 130 X 6 X 9                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(723, 'K-0355              ', 'SENGON KERING AFB 130 X 6 X 9.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(724, 'K-0356              ', 'SENGON KERING AFB 130 X 6 X 10                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(725, 'K-0357              ', 'SENGON KERING AFB 130 X 6 X 10.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(726, 'K-0358              ', 'SENGON KERING AFB 130 X 6 X 11                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(727, 'K-0359              ', 'SENGON KERING AFB 130 X 6 X 11.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(728, 'K-0360              ', 'SENGON KERING AFB 130 X 6 X 12                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(729, 'K-0361              ', 'SENGON KERING AFB 130 X 6 X 12.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(730, 'K-0362              ', 'SENGON KERING AFB 130 X 6 X 13                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL);
INSERT INTO `product` (`id`, `product_code`, `product_name`, `product_category_id`, `product_status`, `product_uom_id`, `is_maintain_stock`, `image_path`, `brand`, `barcode`, `description`, `wood_type_id`, `grade_id`, `thickness_id`, `condition_id`, `is_has_serial`, `is_fixed_asset`, `warranty`, `netto`, `netto_uom_id`, `is_purchase_item`, `minor`, `minor_uom_id`, `lead_time`, `buy_cost_center_id`, `expense_acc_id`, `main_supp_code`, `manufacturer`, `is_sales_item`, `is_service_item`, `sell_cost_center_id`, `income_acc_id`, `max_disc`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `minqty`, `thickness`, `length`, `width`, `production_type_id`, `production_quality_id`) VALUES
(731, 'K-0363              ', 'SENGON KERING AFB 130 X 6 X 13.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(732, 'K-0364              ', 'SENGON KERING AFB 130 X 6 X 14                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(733, 'K-0365              ', 'SENGON KERING AFB 130 X 6 X 14.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(734, 'K-0366              ', 'SENGON KERING AFB 130 X 6 X 15                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(735, 'K-0367              ', 'SENGON KERING AFB 130 X 6 X 15.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(736, 'K-0368              ', 'SENGON KERING AFB 130 X 6 X 16                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 1, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(737, 'B-0369              ', 'MERANTI BASAH A 100 X 5 X 5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(738, 'B-0370              ', 'MERANTI BASAH A 100 X 5 X 5.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(739, 'B-0371              ', 'MERANTI BASAH A 100 X 5 X 6                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(740, 'B-0372              ', 'MERANTI BASAH A 100 X 5 X 6.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(741, 'B-0373              ', 'MERANTI BASAH A 100 X 5 X 7                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(742, 'B-0374              ', 'MERANTI BASAH A 100 X 5 X 7.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(743, 'B-0375              ', 'MERANTI BASAH A 100 X 5 X 8                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(744, 'B-0376              ', 'MERANTI BASAH A 100 X 5 X 8.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(745, 'B-0377              ', 'MERANTI BASAH A 100 X 5 X 9                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(746, 'B-0378              ', 'MERANTI BASAH A 100 X 5 X 9.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(747, 'B-0379              ', 'MERANTI BASAH A 100 X 5 X 10                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(748, 'B-0380              ', 'MERANTI BASAH A 100 X 5 X 10.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(749, 'B-0381              ', 'MERANTI BASAH A 100 X 5 X 11                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(750, 'B-0382              ', 'MERANTI BASAH A 100 X 5 X 11.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(751, 'B-0383              ', 'MERANTI BASAH A 100 X 5 X 12                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(752, 'B-0384              ', 'MERANTI BASAH A 100 X 5 X 12.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(753, 'B-0385              ', 'MERANTI BASAH A 100 X 5 X 13                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(754, 'B-0386              ', 'MERANTI BASAH A 100 X 5 X 13.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(755, 'B-0387              ', 'MERANTI BASAH A 100 X 5 X 14                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(756, 'B-0388              ', 'MERANTI BASAH A 100 X 5 X 14.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(757, 'B-0389              ', 'MERANTI BASAH A 100 X 5 X 15                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(758, 'B-0390              ', 'MERANTI BASAH A 100 X 5 X 15.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(759, 'B-0391              ', 'MERANTI BASAH A 100 X 5 X 16                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(760, 'B-0392              ', 'MERANTI BASAH B 100 X 5 X 5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(761, 'B-0393              ', 'MERANTI BASAH B 100 X 5 X 5.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(762, 'B-0394              ', 'MERANTI BASAH B 100 X 5 X 6                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(763, 'B-0395              ', 'MERANTI BASAH B 100 X 5 X 6.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(764, 'B-0396              ', 'MERANTI BASAH B 100 X 5 X 7                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(765, 'B-0397              ', 'MERANTI BASAH B 100 X 5 X 7.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(766, 'B-0398              ', 'MERANTI BASAH B 100 X 5 X 8                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(767, 'B-0399              ', 'MERANTI BASAH B 100 X 5 X 8.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(768, 'B-0400              ', 'MERANTI BASAH B 100 X 5 X 9                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(769, 'B-0401              ', 'MERANTI BASAH B 100 X 5 X 9.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(770, 'B-0402              ', 'MERANTI BASAH B 100 X 5 X 10                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(771, 'B-0403              ', 'MERANTI BASAH B 100 X 5 X 10.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(772, 'B-0404              ', 'MERANTI BASAH B 100 X 5 X 11                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(773, 'B-0405              ', 'MERANTI BASAH B 100 X 5 X 11.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(774, 'B-0406              ', 'MERANTI BASAH B 100 X 5 X 12                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(775, 'B-0407              ', 'MERANTI BASAH B 100 X 5 X 12.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(776, 'B-0408              ', 'MERANTI BASAH B 100 X 5 X 13                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(777, 'B-0409              ', 'MERANTI BASAH B 100 X 5 X 13.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(778, 'B-0410              ', 'MERANTI BASAH B 100 X 5 X 14                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(779, 'B-0411              ', 'MERANTI BASAH B 100 X 5 X 14.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(780, 'B-0412              ', 'MERANTI BASAH B 100 X 5 X 15                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(781, 'B-0413              ', 'MERANTI BASAH B 100 X 5 X 15.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(782, 'B-0414              ', 'MERANTI BASAH B 100 X 5 X 16                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(783, 'B-0415              ', 'MERANTI BASAH ALL 100 X 5 X 5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(784, 'B-0416              ', 'MERANTI BASAH ALL 100 X 5 X 5.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(785, 'B-0417              ', 'MERANTI BASAH ALL 100 X 5 X 6                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(786, 'B-0418              ', 'MERANTI BASAH ALL 100 X 5 X 6.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(787, 'B-0419              ', 'MERANTI BASAH ALL 100 X 5 X 7                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(788, 'B-0420              ', 'MERANTI BASAH ALL 100 X 5 X 7.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(789, 'B-0421              ', 'MERANTI BASAH ALL 100 X 5 X 8                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(790, 'B-0422              ', 'MERANTI BASAH ALL 100 X 5 X 8.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(791, 'B-0423              ', 'MERANTI BASAH ALL 100 X 5 X 9                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(792, 'B-0424              ', 'MERANTI BASAH ALL 100 X 5 X 9.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(793, 'B-0425              ', 'MERANTI BASAH ALL 100 X 5 X 10                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(794, 'B-0426              ', 'MERANTI BASAH ALL 100 X 5 X 10.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(795, 'B-0427              ', 'MERANTI BASAH ALL 100 X 5 X 11                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(796, 'B-0428              ', 'MERANTI BASAH ALL 100 X 5 X 11.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(797, 'B-0429              ', 'MERANTI BASAH ALL 100 X 5 X 12                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(798, 'B-0430              ', 'MERANTI BASAH ALL 100 X 5 X 12.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(799, 'B-0431              ', 'MERANTI BASAH ALL 100 X 5 X 13                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(800, 'B-0432              ', 'MERANTI BASAH ALL 100 X 5 X 13.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(801, 'B-0433              ', 'MERANTI BASAH ALL 100 X 5 X 14                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(802, 'B-0434              ', 'MERANTI BASAH ALL 100 X 5 X 14.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(803, 'B-0435              ', 'MERANTI BASAH ALL 100 X 5 X 15                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(804, 'B-0436              ', 'MERANTI BASAH ALL 100 X 5 X 15.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(805, 'B-0437              ', 'MERANTI BASAH ALL 100 X 5 X 16                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(806, 'B-0438              ', 'MERANTI BASAH AFB 100 X 5 X 5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(807, 'B-0439              ', 'MERANTI BASAH AFB 100 X 5 X 5.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(808, 'B-0440              ', 'MERANTI BASAH AFB 100 X 5 X 6                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(809, 'B-0441              ', 'MERANTI BASAH AFB 100 X 5 X 6.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(810, 'B-0442              ', 'MERANTI BASAH AFB 100 X 5 X 7                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(811, 'B-0443              ', 'MERANTI BASAH AFB 100 X 5 X 7.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(812, 'B-0444              ', 'MERANTI BASAH AFB 100 X 5 X 8                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(813, 'B-0445              ', 'MERANTI BASAH AFB 100 X 5 X 8.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(814, 'B-0446              ', 'MERANTI BASAH AFB 100 X 5 X 9                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(815, 'B-0447              ', 'MERANTI BASAH AFB 100 X 5 X 9.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(816, 'B-0448              ', 'MERANTI BASAH AFB 100 X 5 X 10                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(817, 'B-0449              ', 'MERANTI BASAH AFB 100 X 5 X 10.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(818, 'B-0450              ', 'MERANTI BASAH AFB 100 X 5 X 11                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(819, 'B-0451              ', 'MERANTI BASAH AFB 100 X 5 X 11.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(820, 'B-0452              ', 'MERANTI BASAH AFB 100 X 5 X 12                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(821, 'B-0453              ', 'MERANTI BASAH AFB 100 X 5 X 12.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(822, 'B-0454              ', 'MERANTI BASAH AFB 100 X 5 X 13                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(823, 'B-0455              ', 'MERANTI BASAH AFB 100 X 5 X 13.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(824, 'B-0456              ', 'MERANTI BASAH AFB 100 X 5 X 14                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(825, 'B-0457              ', 'MERANTI BASAH AFB 100 X 5 X 14.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(826, 'B-0458              ', 'MERANTI BASAH AFB 100 X 5 X 15                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(827, 'B-0459              ', 'MERANTI BASAH AFB 100 X 5 X 15.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(828, 'B-0460              ', 'MERANTI BASAH AFB 100 X 5 X 16                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(829, 'B-0461              ', 'MERANTI BASAH A 100 X 6 X 5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(830, 'B-0462              ', 'MERANTI BASAH A 100 X 6 X 5.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(831, 'B-0463              ', 'MERANTI BASAH A 100 X 6 X 6                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(832, 'B-0464              ', 'MERANTI BASAH A 100 X 6 X 6.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(833, 'B-0465              ', 'MERANTI BASAH A 100 X 6 X 7                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(834, 'B-0466              ', 'MERANTI BASAH A 100 X 6 X 7.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(835, 'B-0467              ', 'MERANTI BASAH A 100 X 6 X 8                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(836, 'B-0468              ', 'MERANTI BASAH A 100 X 6 X 8.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(837, 'B-0469              ', 'MERANTI BASAH A 100 X 6 X 9                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(838, 'B-0470              ', 'MERANTI BASAH A 100 X 6 X 9.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(839, 'B-0471              ', 'MERANTI BASAH A 100 X 6 X 10                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(840, 'B-0472              ', 'MERANTI BASAH A 100 X 6 X 10.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(841, 'B-0473              ', 'MERANTI BASAH A 100 X 6 X 11                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(842, 'B-0474              ', 'MERANTI BASAH A 100 X 6 X 11.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(843, 'B-0475              ', 'MERANTI BASAH A 100 X 6 X 12                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(844, 'B-0476              ', 'MERANTI BASAH A 100 X 6 X 12.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(845, 'B-0477              ', 'MERANTI BASAH A 100 X 6 X 13                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(846, 'B-0478              ', 'MERANTI BASAH A 100 X 6 X 13.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(847, 'B-0479              ', 'MERANTI BASAH A 100 X 6 X 14                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(848, 'B-0480              ', 'MERANTI BASAH A 100 X 6 X 14.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(849, 'B-0481              ', 'MERANTI BASAH A 100 X 6 X 15                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(850, 'B-0482              ', 'MERANTI BASAH A 100 X 6 X 15.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(851, 'B-0483              ', 'MERANTI BASAH A 100 X 6 X 16                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(852, 'B-0484              ', 'MERANTI BASAH B 100 X 6 X 5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(853, 'B-0485              ', 'MERANTI BASAH B 100 X 6 X 5.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(854, 'B-0486              ', 'MERANTI BASAH B 100 X 6 X 6                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(855, 'B-0487              ', 'MERANTI BASAH B 100 X 6 X 6.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(856, 'B-0488              ', 'MERANTI BASAH B 100 X 6 X 7                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(857, 'B-0489              ', 'MERANTI BASAH B 100 X 6 X 7.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(858, 'B-0490              ', 'MERANTI BASAH B 100 X 6 X 8                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(859, 'B-0491              ', 'MERANTI BASAH B 100 X 6 X 8.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(860, 'B-0492              ', 'MERANTI BASAH B 100 X 6 X 9                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(861, 'B-0493              ', 'MERANTI BASAH B 100 X 6 X 9.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(862, 'B-0494              ', 'MERANTI BASAH B 100 X 6 X 10                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(863, 'B-0495              ', 'MERANTI BASAH B 100 X 6 X 10.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(864, 'B-0496              ', 'MERANTI BASAH B 100 X 6 X 11                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(865, 'B-0497              ', 'MERANTI BASAH B 100 X 6 X 11.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(866, 'B-0498              ', 'MERANTI BASAH B 100 X 6 X 12                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(867, 'B-0499              ', 'MERANTI BASAH B 100 X 6 X 12.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(868, 'B-0500              ', 'MERANTI BASAH B 100 X 6 X 13                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(869, 'B-0501              ', 'MERANTI BASAH B 100 X 6 X 13.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(870, 'B-0502              ', 'MERANTI BASAH B 100 X 6 X 14                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(871, 'B-0503              ', 'MERANTI BASAH B 100 X 6 X 14.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(872, 'B-0504              ', 'MERANTI BASAH B 100 X 6 X 15                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(873, 'B-0505              ', 'MERANTI BASAH B 100 X 6 X 15.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(874, 'B-0506              ', 'MERANTI BASAH B 100 X 6 X 16                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(875, 'B-0507              ', 'MERANTI BASAH ALL 100 X 6 X 5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(876, 'B-0508              ', 'MERANTI BASAH ALL 100 X 6 X 5.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL);
INSERT INTO `product` (`id`, `product_code`, `product_name`, `product_category_id`, `product_status`, `product_uom_id`, `is_maintain_stock`, `image_path`, `brand`, `barcode`, `description`, `wood_type_id`, `grade_id`, `thickness_id`, `condition_id`, `is_has_serial`, `is_fixed_asset`, `warranty`, `netto`, `netto_uom_id`, `is_purchase_item`, `minor`, `minor_uom_id`, `lead_time`, `buy_cost_center_id`, `expense_acc_id`, `main_supp_code`, `manufacturer`, `is_sales_item`, `is_service_item`, `sell_cost_center_id`, `income_acc_id`, `max_disc`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `minqty`, `thickness`, `length`, `width`, `production_type_id`, `production_quality_id`) VALUES
(877, 'B-0509              ', 'MERANTI BASAH ALL 100 X 6 X 6                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(878, 'B-0510              ', 'MERANTI BASAH ALL 100 X 6 X 6.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(879, 'B-0511              ', 'MERANTI BASAH ALL 100 X 6 X 7                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(880, 'B-0512              ', 'MERANTI BASAH ALL 100 X 6 X 7.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(881, 'B-0513              ', 'MERANTI BASAH ALL 100 X 6 X 8                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(882, 'B-0514              ', 'MERANTI BASAH ALL 100 X 6 X 8.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(883, 'B-0515              ', 'MERANTI BASAH ALL 100 X 6 X 9                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(884, 'B-0516              ', 'MERANTI BASAH ALL 100 X 6 X 9.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(885, 'B-0517              ', 'MERANTI BASAH ALL 100 X 6 X 10                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(886, 'B-0518              ', 'MERANTI BASAH ALL 100 X 6 X 10.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(887, 'B-0519              ', 'MERANTI BASAH ALL 100 X 6 X 11                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(888, 'B-0520              ', 'MERANTI BASAH ALL 100 X 6 X 11.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(889, 'B-0521              ', 'MERANTI BASAH ALL 100 X 6 X 12                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(890, 'B-0522              ', 'MERANTI BASAH ALL 100 X 6 X 12.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(891, 'B-0523              ', 'MERANTI BASAH ALL 100 X 6 X 13                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(892, 'B-0524              ', 'MERANTI BASAH ALL 100 X 6 X 13.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(893, 'B-0525              ', 'MERANTI BASAH ALL 100 X 6 X 14                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(894, 'B-0526              ', 'MERANTI BASAH ALL 100 X 6 X 14.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(895, 'B-0527              ', 'MERANTI BASAH ALL 100 X 6 X 15                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(896, 'B-0528              ', 'MERANTI BASAH ALL 100 X 6 X 15.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(897, 'B-0529              ', 'MERANTI BASAH ALL 100 X 6 X 16                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(898, 'B-0530              ', 'MERANTI BASAH AFB 100 X 6 X 5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(899, 'B-0531              ', 'MERANTI BASAH AFB 100 X 6 X 5.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(900, 'B-0532              ', 'MERANTI BASAH AFB 100 X 6 X 6                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(901, 'B-0533              ', 'MERANTI BASAH AFB 100 X 6 X 6.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(902, 'B-0534              ', 'MERANTI BASAH AFB 100 X 6 X 7                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(903, 'B-0535              ', 'MERANTI BASAH AFB 100 X 6 X 7.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(904, 'B-0536              ', 'MERANTI BASAH AFB 100 X 6 X 8                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(905, 'B-0537              ', 'MERANTI BASAH AFB 100 X 6 X 8.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(906, 'B-0538              ', 'MERANTI BASAH AFB 100 X 6 X 9                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(907, 'B-0539              ', 'MERANTI BASAH AFB 100 X 6 X 9.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(908, 'B-0540              ', 'MERANTI BASAH AFB 100 X 6 X 10                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(909, 'B-0541              ', 'MERANTI BASAH AFB 100 X 6 X 10.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(910, 'B-0542              ', 'MERANTI BASAH AFB 100 X 6 X 11                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(911, 'B-0543              ', 'MERANTI BASAH AFB 100 X 6 X 11.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(912, 'B-0544              ', 'MERANTI BASAH AFB 100 X 6 X 12                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(913, 'B-0545              ', 'MERANTI BASAH AFB 100 X 6 X 12.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(914, 'B-0546              ', 'MERANTI BASAH AFB 100 X 6 X 13                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(915, 'B-0547              ', 'MERANTI BASAH AFB 100 X 6 X 13.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(916, 'B-0548              ', 'MERANTI BASAH AFB 100 X 6 X 14                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(917, 'B-0549              ', 'MERANTI BASAH AFB 100 X 6 X 14.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(918, 'B-0550              ', 'MERANTI BASAH AFB 100 X 6 X 15                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(919, 'B-0551              ', 'MERANTI BASAH AFB 100 X 6 X 15.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(920, 'B-0552              ', 'MERANTI BASAH AFB 100 X 6 X 16                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(921, 'B-0553              ', 'MERANTI BASAH A 130 X 5 X 5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(922, 'B-0554              ', 'MERANTI BASAH A 130 X 5 X 5.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(923, 'B-0555              ', 'MERANTI BASAH A 130 X 5 X 6                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(924, 'B-0556              ', 'MERANTI BASAH A 130 X 5 X 6.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(925, 'B-0557              ', 'MERANTI BASAH A 130 X 5 X 7                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(926, 'B-0558              ', 'MERANTI BASAH A 130 X 5 X 7.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(927, 'B-0559              ', 'MERANTI BASAH A 130 X 5 X 8                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(928, 'B-0560              ', 'MERANTI BASAH A 130 X 5 X 8.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(929, 'B-0561              ', 'MERANTI BASAH A 130 X 5 X 9                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(930, 'B-0562              ', 'MERANTI BASAH A 130 X 5 X 9.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(931, 'B-0563              ', 'MERANTI BASAH A 130 X 5 X 10                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(932, 'B-0564              ', 'MERANTI BASAH A 130 X 5 X 10.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(933, 'B-0565              ', 'MERANTI BASAH A 130 X 5 X 11                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(934, 'B-0566              ', 'MERANTI BASAH A 130 X 5 X 11.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(935, 'B-0567              ', 'MERANTI BASAH A 130 X 5 X 12                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(936, 'B-0568              ', 'MERANTI BASAH A 130 X 5 X 12.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(937, 'B-0569              ', 'MERANTI BASAH A 130 X 5 X 13                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(938, 'B-0570              ', 'MERANTI BASAH A 130 X 5 X 13.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(939, 'B-0571              ', 'MERANTI BASAH A 130 X 5 X 14                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(940, 'B-0572              ', 'MERANTI BASAH A 130 X 5 X 14.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(941, 'B-0573              ', 'MERANTI BASAH A 130 X 5 X 15                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(942, 'B-0574              ', 'MERANTI BASAH A 130 X 5 X 15.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(943, 'B-0575              ', 'MERANTI BASAH A 130 X 5 X 16                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(944, 'B-0576              ', 'MERANTI BASAH B 130 X 5 X 5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(945, 'B-0577              ', 'MERANTI BASAH B 130 X 5 X 5.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(946, 'B-0578              ', 'MERANTI BASAH B 130 X 5 X 6                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(947, 'B-0579              ', 'MERANTI BASAH B 130 X 5 X 6.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(948, 'B-0580              ', 'MERANTI BASAH B 130 X 5 X 7                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(949, 'B-0581              ', 'MERANTI BASAH B 130 X 5 X 7.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(950, 'B-0582              ', 'MERANTI BASAH B 130 X 5 X 8                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(951, 'B-0583              ', 'MERANTI BASAH B 130 X 5 X 8.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(952, 'B-0584              ', 'MERANTI BASAH B 130 X 5 X 9                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(953, 'B-0585              ', 'MERANTI BASAH B 130 X 5 X 9.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(954, 'B-0586              ', 'MERANTI BASAH B 130 X 5 X 10                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(955, 'B-0587              ', 'MERANTI BASAH B 130 X 5 X 10.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(956, 'B-0588              ', 'MERANTI BASAH B 130 X 5 X 11                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(957, 'B-0589              ', 'MERANTI BASAH B 130 X 5 X 11.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(958, 'B-0590              ', 'MERANTI BASAH B 130 X 5 X 12                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(959, 'B-0591              ', 'MERANTI BASAH B 130 X 5 X 12.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(960, 'B-0592              ', 'MERANTI BASAH B 130 X 5 X 13                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(961, 'B-0593              ', 'MERANTI BASAH B 130 X 5 X 13.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(962, 'B-0594              ', 'MERANTI BASAH B 130 X 5 X 14                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(963, 'B-0595              ', 'MERANTI BASAH B 130 X 5 X 14.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(964, 'B-0596              ', 'MERANTI BASAH B 130 X 5 X 15                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(965, 'B-0597              ', 'MERANTI BASAH B 130 X 5 X 15.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(966, 'B-0598              ', 'MERANTI BASAH B 130 X 5 X 16                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(967, 'B-0599              ', 'MERANTI BASAH ALL 130 X 5 X 5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(968, 'B-0600              ', 'MERANTI BASAH ALL 130 X 5 X 5.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(969, 'B-0601              ', 'MERANTI BASAH ALL 130 X 5 X 6                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(970, 'B-0602              ', 'MERANTI BASAH ALL 130 X 5 X 6.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(971, 'B-0603              ', 'MERANTI BASAH ALL 130 X 5 X 7                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(972, 'B-0604              ', 'MERANTI BASAH ALL 130 X 5 X 7.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(973, 'B-0605              ', 'MERANTI BASAH ALL 130 X 5 X 8                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(974, 'B-0606              ', 'MERANTI BASAH ALL 130 X 5 X 8.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(975, 'B-0607              ', 'MERANTI BASAH ALL 130 X 5 X 9                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(976, 'B-0608              ', 'MERANTI BASAH ALL 130 X 5 X 9.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(977, 'B-0609              ', 'MERANTI BASAH ALL 130 X 5 X 10                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(978, 'B-0610              ', 'MERANTI BASAH ALL 130 X 5 X 10.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(979, 'B-0611              ', 'MERANTI BASAH ALL 130 X 5 X 11                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(980, 'B-0612              ', 'MERANTI BASAH ALL 130 X 5 X 11.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(981, 'B-0613              ', 'MERANTI BASAH ALL 130 X 5 X 12                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(982, 'B-0614              ', 'MERANTI BASAH ALL 130 X 5 X 12.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(983, 'B-0615              ', 'MERANTI BASAH ALL 130 X 5 X 13                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(984, 'B-0616              ', 'MERANTI BASAH ALL 130 X 5 X 13.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(985, 'B-0617              ', 'MERANTI BASAH ALL 130 X 5 X 14                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(986, 'B-0618              ', 'MERANTI BASAH ALL 130 X 5 X 14.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(987, 'B-0619              ', 'MERANTI BASAH ALL 130 X 5 X 15                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(988, 'B-0620              ', 'MERANTI BASAH ALL 130 X 5 X 15.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(989, 'B-0621              ', 'MERANTI BASAH ALL 130 X 5 X 16                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(990, 'B-0622              ', 'MERANTI BASAH AFB 130 X 5 X 5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(991, 'B-0623              ', 'MERANTI BASAH AFB 130 X 5 X 5.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(992, 'B-0624              ', 'MERANTI BASAH AFB 130 X 5 X 6                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(993, 'B-0625              ', 'MERANTI BASAH AFB 130 X 5 X 6.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(994, 'B-0626              ', 'MERANTI BASAH AFB 130 X 5 X 7                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(995, 'B-0627              ', 'MERANTI BASAH AFB 130 X 5 X 7.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(996, 'B-0628              ', 'MERANTI BASAH AFB 130 X 5 X 8                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(997, 'B-0629              ', 'MERANTI BASAH AFB 130 X 5 X 8.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(998, 'B-0630              ', 'MERANTI BASAH AFB 130 X 5 X 9                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(999, 'B-0631              ', 'MERANTI BASAH AFB 130 X 5 X 9.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(1000, '1000', 'BALKEN AFKIR', 1, NULL, 1, 0, '', NULL, NULL, NULL, 1, 100, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(1007, 'PDC009-1', 'BARECORE NORMAL A TIPE 9', 3, NULL, 2, 0, '', NULL, NULL, NULL, 1, 9, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2016-12-27', 'Timotius', '2017-01-21', 'Timotius', NULL, NULL, 0, '0.9000', '244.0000', '122.0000', 1, 1),
(1008, 'PDC009-2', 'BARECORE NORMAL B TIPE 9', 3, NULL, 2, 0, '', NULL, NULL, NULL, 1, 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2016-12-27', 'Timotius', '2017-01-21', 'Timotius', NULL, NULL, 0, '0.9000', '244.0000', '122.0000', 1, 1),
(1009, 'PDC009-3', 'BARECORE KLEM A TIPE 9', 3, NULL, 2, 0, '', NULL, NULL, NULL, 1, 9, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2016-12-27', 'Timotius', '2017-01-21', 'Timotius', NULL, NULL, 0, '0.9000', '244.0000', '122.0000', 1, 3),
(1010, 'PDC009-4', 'BARECORE KLEM B TIPE 9', 3, NULL, 2, 0, '', NULL, NULL, NULL, 1, 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2016-12-27', 'Timotius', '2017-01-21', 'Timotius', NULL, NULL, 0, '0.9000', '244.0000', '122.0000', 1, 3),
(1011, 'PDC009-5', 'BARECORE PROTOL A TIPE 9', 3, NULL, 2, 0, '', NULL, NULL, NULL, 1, 9, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2016-12-27', 'Timotius', '2017-01-21', 'Timotius', NULL, NULL, 0, '0.9000', '244.0000', '122.0000', 1, 2),
(1012, 'PDC009-6', 'BARECORE PROTOL B TIPE 9', 3, NULL, 2, 0, '', NULL, NULL, NULL, 1, 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2016-12-27', 'Timotius', '2017-01-21', 'Timotius', NULL, NULL, 0, '0.9000', '244.0000', '122.0000', 1, 2),
(1013, 'PDC009-7', 'CRATE BESAR C TIPE 9', 3, NULL, 3, 0, '', NULL, NULL, NULL, 1, 13, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2016-12-27', 'Timotius', '2017-01-21', 'Timotius', NULL, NULL, 0, NULL, NULL, NULL, 1, 1),
(1014, 'PDC009-8', 'CRATE KECIL C TIPE 9', 3, NULL, 3, 0, '', NULL, NULL, NULL, 1, 13, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2016-12-27', 'Timotius', '2017-01-21', 'Timotius', NULL, NULL, 0, NULL, NULL, NULL, 1, 1),
(1015, 'PDC009-9', 'CRATE BESAR A TIPE 9', 3, NULL, 3, 0, '', NULL, NULL, NULL, 1, 9, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2016-12-27', 'Timotius', NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, 1, 1),
(1016, 'PDC009-10', 'CRATE KECIL A TIPE 9', 3, NULL, 3, 0, '', NULL, NULL, NULL, 1, 9, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2016-12-27', 'Timotius', NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, 1, 1),
(1017, 'PDC009-11', 'CRATE BESAR A1 TIPE 9', 3, NULL, 3, 0, '', NULL, NULL, NULL, 1, 11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2016-12-27', 'Timotius', NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, 1, 1),
(1018, 'PDC009-12', 'CRATE KECIL A1 TIPE 9', 3, NULL, 3, 0, '', NULL, NULL, NULL, 1, 11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2016-12-27', 'Timotius', NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, 1, 1),
(1019, 'PDC009-13', 'CRATE BESAR B TIPE 9', 3, NULL, 3, 0, '', NULL, NULL, NULL, 1, 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2016-12-27', 'Timotius', NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, 1, 1),
(1020, 'PDC009-14', 'CRATE KECIL B TIPE 9', 3, NULL, 3, 0, '', NULL, NULL, NULL, 1, 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2016-12-27', 'Timotius', NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, 1, 1),
(1021, 'PDC009-15', 'CRATE BESAR BNP TIPE 9', 3, NULL, 3, 0, '', NULL, NULL, NULL, 1, 12, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2016-12-27', 'Timotius', NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, 1, 1),
(1022, 'PDC009-16', 'CRATE KECIL BNP TIPE 9', 3, NULL, 3, 0, '', NULL, NULL, NULL, 1, 12, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2016-12-27', 'Timotius', NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, 1, 1),
(1023, 'B-0632              ', 'MERANTI BASAH AFB 130 X 5 X 10                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(1024, 'B-0633              ', 'MERANTI BASAH AFB 130 X 5 X 10.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(1025, 'B-0634              ', 'MERANTI BASAH AFB 130 X 5 X 11                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(1026, 'B-0635              ', 'MERANTI BASAH AFB 130 X 5 X 11.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(1027, 'B-0636              ', 'MERANTI BASAH AFB 130 X 5 X 12                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(1028, 'B-0637              ', 'MERANTI BASAH AFB 130 X 5 X 12.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(1029, 'B-0638              ', 'MERANTI BASAH AFB 130 X 5 X 13                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(1030, 'B-0639              ', 'MERANTI BASAH AFB 130 X 5 X 13.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL);
INSERT INTO `product` (`id`, `product_code`, `product_name`, `product_category_id`, `product_status`, `product_uom_id`, `is_maintain_stock`, `image_path`, `brand`, `barcode`, `description`, `wood_type_id`, `grade_id`, `thickness_id`, `condition_id`, `is_has_serial`, `is_fixed_asset`, `warranty`, `netto`, `netto_uom_id`, `is_purchase_item`, `minor`, `minor_uom_id`, `lead_time`, `buy_cost_center_id`, `expense_acc_id`, `main_supp_code`, `manufacturer`, `is_sales_item`, `is_service_item`, `sell_cost_center_id`, `income_acc_id`, `max_disc`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `minqty`, `thickness`, `length`, `width`, `production_type_id`, `production_quality_id`) VALUES
(1031, 'B-0640              ', 'MERANTI BASAH AFB 130 X 5 X 14                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(1032, 'B-0641              ', 'MERANTI BASAH AFB 130 X 5 X 14.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(1033, 'B-0642              ', 'MERANTI BASAH AFB 130 X 5 X 15                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(1034, 'B-0643              ', 'MERANTI BASAH AFB 130 X 5 X 15.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(1035, 'B-0644              ', 'MERANTI BASAH AFB 130 X 5 X 16                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(1036, 'B-0645              ', 'MERANTI BASAH A 130 X 6 X 5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(1037, 'B-0646              ', 'MERANTI BASAH A 130 X 6 X 5.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(1038, 'B-0647              ', 'MERANTI BASAH A 130 X 6 X 6                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(1039, 'B-0648              ', 'MERANTI BASAH A 130 X 6 X 6.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(1040, 'B-0649              ', 'MERANTI BASAH A 130 X 6 X 7                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(1041, 'B-0650              ', 'MERANTI BASAH A 130 X 6 X 7.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(1042, 'B-0651              ', 'MERANTI BASAH A 130 X 6 X 8                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(1043, 'B-0652              ', 'MERANTI BASAH A 130 X 6 X 8.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(1044, 'B-0653              ', 'MERANTI BASAH A 130 X 6 X 9                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(1045, 'B-0654              ', 'MERANTI BASAH A 130 X 6 X 9.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(1046, 'B-0655              ', 'MERANTI BASAH A 130 X 6 X 10                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(1047, 'B-0656              ', 'MERANTI BASAH A 130 X 6 X 10.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(1048, 'B-0657              ', 'MERANTI BASAH A 130 X 6 X 11                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(1049, 'B-0658              ', 'MERANTI BASAH A 130 X 6 X 11.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(1050, 'B-0659              ', 'MERANTI BASAH A 130 X 6 X 12                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(1051, 'B-0660              ', 'MERANTI BASAH A 130 X 6 X 12.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(1052, 'B-0661              ', 'MERANTI BASAH A 130 X 6 X 13                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(1053, 'B-0662              ', 'MERANTI BASAH A 130 X 6 X 13.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(1054, 'B-0663              ', 'MERANTI BASAH A 130 X 6 X 14                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(1055, 'B-0664              ', 'MERANTI BASAH A 130 X 6 X 14.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(1056, 'B-0665              ', 'MERANTI BASAH A 130 X 6 X 15                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(1057, 'B-0666              ', 'MERANTI BASAH A 130 X 6 X 15.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(1058, 'B-0667              ', 'MERANTI BASAH A 130 X 6 X 16                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(1059, 'B-0668              ', 'MERANTI BASAH B 130 X 6 X 5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(1060, 'B-0669              ', 'MERANTI BASAH B 130 X 6 X 5.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(1061, 'B-0670              ', 'MERANTI BASAH B 130 X 6 X 6                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(1062, 'B-0671              ', 'MERANTI BASAH B 130 X 6 X 6.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(1063, 'B-0672              ', 'MERANTI BASAH B 130 X 6 X 7                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(1064, 'B-0673              ', 'MERANTI BASAH B 130 X 6 X 7.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(1065, 'B-0674              ', 'MERANTI BASAH B 130 X 6 X 8                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(1066, 'B-0675              ', 'MERANTI BASAH B 130 X 6 X 8.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(1067, 'B-0676              ', 'MERANTI BASAH B 130 X 6 X 9                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(1068, 'B-0677              ', 'MERANTI BASAH B 130 X 6 X 9.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(1069, 'B-0678              ', 'MERANTI BASAH B 130 X 6 X 10                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(1070, 'B-0679              ', 'MERANTI BASAH B 130 X 6 X 10.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(1071, 'B-0680              ', 'MERANTI BASAH B 130 X 6 X 11                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(1072, 'B-0681              ', 'MERANTI BASAH B 130 X 6 X 11.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(1073, 'B-0682              ', 'MERANTI BASAH B 130 X 6 X 12                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(1074, 'B-0683              ', 'MERANTI BASAH B 130 X 6 X 12.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(1075, 'B-0684              ', 'MERANTI BASAH B 130 X 6 X 13                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(1076, 'B-0685              ', 'MERANTI BASAH B 130 X 6 X 13.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(1077, 'B-0686              ', 'MERANTI BASAH B 130 X 6 X 14                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(1078, 'B-0687              ', 'MERANTI BASAH B 130 X 6 X 14.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(1079, 'B-0688              ', 'MERANTI BASAH B 130 X 6 X 15                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(1080, 'B-0689              ', 'MERANTI BASAH B 130 X 6 X 15.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(1081, 'B-0690              ', 'MERANTI BASAH B 130 X 6 X 16                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(1082, 'B-0691              ', 'MERANTI BASAH ALL 130 X 6 X 5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(1083, 'B-0692              ', 'MERANTI BASAH ALL 130 X 6 X 5.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(1084, 'B-0693              ', 'MERANTI BASAH ALL 130 X 6 X 6                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(1085, 'B-0694              ', 'MERANTI BASAH ALL 130 X 6 X 6.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(1086, 'B-0695              ', 'MERANTI BASAH ALL 130 X 6 X 7                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(1087, 'B-0696              ', 'MERANTI BASAH ALL 130 X 6 X 7.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(1088, 'B-0697              ', 'MERANTI BASAH ALL 130 X 6 X 8                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(1089, 'B-0698              ', 'MERANTI BASAH ALL 130 X 6 X 8.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(1090, 'B-0699              ', 'MERANTI BASAH ALL 130 X 6 X 9                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(1091, 'B-0700              ', 'MERANTI BASAH ALL 130 X 6 X 9.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(1092, 'B-0701              ', 'MERANTI BASAH ALL 130 X 6 X 10                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(1093, 'B-0702              ', 'MERANTI BASAH ALL 130 X 6 X 10.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(1094, 'B-0703              ', 'MERANTI BASAH ALL 130 X 6 X 11                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(1095, 'B-0704              ', 'MERANTI BASAH ALL 130 X 6 X 11.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(1096, 'B-0705              ', 'MERANTI BASAH ALL 130 X 6 X 12                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(1097, 'B-0706              ', 'MERANTI BASAH ALL 130 X 6 X 12.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(1098, 'B-0707              ', 'MERANTI BASAH ALL 130 X 6 X 13                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(1099, 'B-0708              ', 'MERANTI BASAH ALL 130 X 6 X 13.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(1100, 'B-0709              ', 'MERANTI BASAH ALL 130 X 6 X 14                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(1101, 'B-0710              ', 'MERANTI BASAH ALL 130 X 6 X 14.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(1102, 'B-0711              ', 'MERANTI BASAH ALL 130 X 6 X 15                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(1103, 'B-0712              ', 'MERANTI BASAH ALL 130 X 6 X 15.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(1104, 'B-0713              ', 'MERANTI BASAH ALL 130 X 6 X 16                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(1105, 'B-0714              ', 'MERANTI BASAH AFB 130 X 6 X 5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(1106, 'B-0715              ', 'MERANTI BASAH AFB 130 X 6 X 5.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(1107, 'B-0716              ', 'MERANTI BASAH AFB 130 X 6 X 6                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(1108, 'B-0717              ', 'MERANTI BASAH AFB 130 X 6 X 6.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(1109, 'B-0718              ', 'MERANTI BASAH AFB 130 X 6 X 7                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(1110, 'B-0719              ', 'MERANTI BASAH AFB 130 X 6 X 7.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(1111, 'B-0720              ', 'MERANTI BASAH AFB 130 X 6 X 8                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(1112, 'B-0721              ', 'MERANTI BASAH AFB 130 X 6 X 8.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(1113, 'B-0722              ', 'MERANTI BASAH AFB 130 X 6 X 9                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(1114, 'B-0723              ', 'MERANTI BASAH AFB 130 X 6 X 9.5                   ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(1115, 'B-0724              ', 'MERANTI BASAH AFB 130 X 6 X 10                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(1116, 'B-0725              ', 'MERANTI BASAH AFB 130 X 6 X 10.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(1117, 'B-0726              ', 'MERANTI BASAH AFB 130 X 6 X 11                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(1118, 'B-0727              ', 'MERANTI BASAH AFB 130 X 6 X 11.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(1119, 'B-0728              ', 'MERANTI BASAH AFB 130 X 6 X 12                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(1120, 'B-0729              ', 'MERANTI BASAH AFB 130 X 6 X 12.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(1121, 'B-0730              ', 'MERANTI BASAH AFB 130 X 6 X 13                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(1122, 'B-0731              ', 'MERANTI BASAH AFB 130 X 6 X 13.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(1123, 'B-0732              ', 'MERANTI BASAH AFB 130 X 6 X 14                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(1124, 'B-0733              ', 'MERANTI BASAH AFB 130 X 6 X 14.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(1125, 'B-0734              ', 'MERANTI BASAH AFB 130 X 6 X 15                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(1126, 'B-0735              ', 'MERANTI BASAH AFB 130 X 6 X 15.5                  ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(1127, 'B-0736              ', 'MERANTI BASAH AFB 130 X 6 X 16                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(1128, 'K-0369              ', 'MERANTI KERING A 100 X 5 X 5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(1129, 'K-0370              ', 'MERANTI KERING A 100 X 5 X 5.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(1130, 'K-0371              ', 'MERANTI KERING A 100 X 5 X 6                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(1131, 'K-0372              ', 'MERANTI KERING A 100 X 5 X 6.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(1132, 'K-0373              ', 'MERANTI KERING A 100 X 5 X 7                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(1133, 'K-0374              ', 'MERANTI KERING A 100 X 5 X 7.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(1134, 'K-0375              ', 'MERANTI KERING A 100 X 5 X 8                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(1135, 'K-0376              ', 'MERANTI KERING A 100 X 5 X 8.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(1136, 'K-0377              ', 'MERANTI KERING A 100 X 5 X 9                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(1137, 'K-0378              ', 'MERANTI KERING A 100 X 5 X 9.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(1138, 'K-0379              ', 'MERANTI KERING A 100 X 5 X 10                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(1139, 'K-0380              ', 'MERANTI KERING A 100 X 5 X 10.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(1140, 'K-0381              ', 'MERANTI KERING A 100 X 5 X 11                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(1141, 'K-0382              ', 'MERANTI KERING A 100 X 5 X 11.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(1142, 'K-0383              ', 'MERANTI KERING A 100 X 5 X 12                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(1143, 'K-0384              ', 'MERANTI KERING A 100 X 5 X 12.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(1144, 'K-0385              ', 'MERANTI KERING A 100 X 5 X 13                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(1145, 'K-0386              ', 'MERANTI KERING A 100 X 5 X 13.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(1146, 'K-0387              ', 'MERANTI KERING A 100 X 5 X 14                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(1147, 'K-0388              ', 'MERANTI KERING A 100 X 5 X 14.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(1148, 'K-0389              ', 'MERANTI KERING A 100 X 5 X 15                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(1149, 'K-0390              ', 'MERANTI KERING A 100 X 5 X 15.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(1150, 'K-0391              ', 'MERANTI KERING A 100 X 5 X 16                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(1151, 'K-0392              ', 'MERANTI KERING B 100 X 5 X 5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(1152, 'K-0393              ', 'MERANTI KERING B 100 X 5 X 5.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(1153, 'K-0394              ', 'MERANTI KERING B 100 X 5 X 6                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(1154, 'K-0395              ', 'MERANTI KERING B 100 X 5 X 6.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(1155, 'K-0396              ', 'MERANTI KERING B 100 X 5 X 7                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(1156, 'K-0397              ', 'MERANTI KERING B 100 X 5 X 7.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(1157, 'K-0398              ', 'MERANTI KERING B 100 X 5 X 8                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(1158, 'K-0399              ', 'MERANTI KERING B 100 X 5 X 8.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(1159, 'K-0400              ', 'MERANTI KERING B 100 X 5 X 9                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(1160, 'K-0401              ', 'MERANTI KERING B 100 X 5 X 9.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(1161, 'K-0402              ', 'MERANTI KERING B 100 X 5 X 10                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(1162, 'K-0403              ', 'MERANTI KERING B 100 X 5 X 10.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(1163, 'K-0404              ', 'MERANTI KERING B 100 X 5 X 11                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(1164, 'K-0405              ', 'MERANTI KERING B 100 X 5 X 11.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(1165, 'K-0406              ', 'MERANTI KERING B 100 X 5 X 12                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(1166, 'K-0407              ', 'MERANTI KERING B 100 X 5 X 12.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(1167, 'K-0408              ', 'MERANTI KERING B 100 X 5 X 13                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(1168, 'K-0409              ', 'MERANTI KERING B 100 X 5 X 13.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(1169, 'K-0410              ', 'MERANTI KERING B 100 X 5 X 14                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(1170, 'K-0411              ', 'MERANTI KERING B 100 X 5 X 14.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(1171, 'K-0412              ', 'MERANTI KERING B 100 X 5 X 15                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(1172, 'K-0413              ', 'MERANTI KERING B 100 X 5 X 15.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(1173, 'K-0414              ', 'MERANTI KERING B 100 X 5 X 16                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(1174, 'K-0415              ', 'MERANTI KERING ALL 100 X 5 X 5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(1175, 'K-0416              ', 'MERANTI KERING ALL 100 X 5 X 5.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL);
INSERT INTO `product` (`id`, `product_code`, `product_name`, `product_category_id`, `product_status`, `product_uom_id`, `is_maintain_stock`, `image_path`, `brand`, `barcode`, `description`, `wood_type_id`, `grade_id`, `thickness_id`, `condition_id`, `is_has_serial`, `is_fixed_asset`, `warranty`, `netto`, `netto_uom_id`, `is_purchase_item`, `minor`, `minor_uom_id`, `lead_time`, `buy_cost_center_id`, `expense_acc_id`, `main_supp_code`, `manufacturer`, `is_sales_item`, `is_service_item`, `sell_cost_center_id`, `income_acc_id`, `max_disc`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `minqty`, `thickness`, `length`, `width`, `production_type_id`, `production_quality_id`) VALUES
(1176, 'K-0417              ', 'MERANTI KERING ALL 100 X 5 X 6                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(1177, 'K-0418              ', 'MERANTI KERING ALL 100 X 5 X 6.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(1178, 'K-0419              ', 'MERANTI KERING ALL 100 X 5 X 7                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(1179, 'K-0420              ', 'MERANTI KERING ALL 100 X 5 X 7.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(1180, 'K-0421              ', 'MERANTI KERING ALL 100 X 5 X 8                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(1181, 'K-0422              ', 'MERANTI KERING ALL 100 X 5 X 8.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(1182, 'K-0423              ', 'MERANTI KERING ALL 100 X 5 X 9                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(1183, 'K-0424              ', 'MERANTI KERING ALL 100 X 5 X 9.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(1184, 'K-0425              ', 'MERANTI KERING ALL 100 X 5 X 10                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(1185, 'K-0426              ', 'MERANTI KERING ALL 100 X 5 X 10.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(1186, 'K-0427              ', 'MERANTI KERING ALL 100 X 5 X 11                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(1187, 'K-0428              ', 'MERANTI KERING ALL 100 X 5 X 11.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(1188, 'K-0429              ', 'MERANTI KERING ALL 100 X 5 X 12                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(1189, 'K-0430              ', 'MERANTI KERING ALL 100 X 5 X 12.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(1190, 'K-0431              ', 'MERANTI KERING ALL 100 X 5 X 13                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(1191, 'K-0432              ', 'MERANTI KERING ALL 100 X 5 X 13.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(1192, 'K-0433              ', 'MERANTI KERING ALL 100 X 5 X 14                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(1193, 'K-0434              ', 'MERANTI KERING ALL 100 X 5 X 14.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(1194, 'K-0435              ', 'MERANTI KERING ALL 100 X 5 X 15                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(1195, 'K-0436              ', 'MERANTI KERING ALL 100 X 5 X 15.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(1196, 'K-0437              ', 'MERANTI KERING ALL 100 X 5 X 16                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(1197, 'K-0438              ', 'MERANTI KERING AFB 100 X 5 X 5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(1198, 'K-0439              ', 'MERANTI KERING AFB 100 X 5 X 5.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(1199, 'K-0440              ', 'MERANTI KERING AFB 100 X 5 X 6                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(1200, 'K-0441              ', 'MERANTI KERING AFB 100 X 5 X 6.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(1201, 'K-0442              ', 'MERANTI KERING AFB 100 X 5 X 7                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(1202, 'K-0443              ', 'MERANTI KERING AFB 100 X 5 X 7.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(1203, 'K-0444              ', 'MERANTI KERING AFB 100 X 5 X 8                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(1204, 'K-0445              ', 'MERANTI KERING AFB 100 X 5 X 8.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(1205, 'K-0446              ', 'MERANTI KERING AFB 100 X 5 X 9                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(1206, 'K-0447              ', 'MERANTI KERING AFB 100 X 5 X 9.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(1207, 'K-0448              ', 'MERANTI KERING AFB 100 X 5 X 10                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(1208, 'K-0449              ', 'MERANTI KERING AFB 100 X 5 X 10.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(1209, 'K-0450              ', 'MERANTI KERING AFB 100 X 5 X 11                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(1210, 'K-0451              ', 'MERANTI KERING AFB 100 X 5 X 11.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(1211, 'K-0452              ', 'MERANTI KERING AFB 100 X 5 X 12                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(1212, 'K-0453              ', 'MERANTI KERING AFB 100 X 5 X 12.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(1213, 'K-0454              ', 'MERANTI KERING AFB 100 X 5 X 13                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(1214, 'K-0455              ', 'MERANTI KERING AFB 100 X 5 X 13.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(1215, 'K-0456              ', 'MERANTI KERING AFB 100 X 5 X 14                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(1216, 'K-0457              ', 'MERANTI KERING AFB 100 X 5 X 14.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(1217, 'K-0458              ', 'MERANTI KERING AFB 100 X 5 X 15                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(1218, 'K-0459              ', 'MERANTI KERING AFB 100 X 5 X 15.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(1219, 'K-0460              ', 'MERANTI KERING AFB 100 X 5 X 16                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(1220, 'K-0461              ', 'MERANTI KERING A 100 X 6 X 5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(1221, 'K-0462              ', 'MERANTI KERING A 100 X 6 X 5.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(1222, 'K-0463              ', 'MERANTI KERING A 100 X 6 X 6                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(1223, 'K-0464              ', 'MERANTI KERING A 100 X 6 X 6.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(1224, 'K-0465              ', 'MERANTI KERING A 100 X 6 X 7                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(1225, 'K-0466              ', 'MERANTI KERING A 100 X 6 X 7.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(1226, 'K-0467              ', 'MERANTI KERING A 100 X 6 X 8                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(1227, 'K-0468              ', 'MERANTI KERING A 100 X 6 X 8.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(1228, 'K-0469              ', 'MERANTI KERING A 100 X 6 X 9                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(1229, 'K-0470              ', 'MERANTI KERING A 100 X 6 X 9.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(1230, 'K-0471              ', 'MERANTI KERING A 100 X 6 X 10                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(1231, 'K-0472              ', 'MERANTI KERING A 100 X 6 X 10.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(1232, 'K-0473              ', 'MERANTI KERING A 100 X 6 X 11                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(1233, 'K-0474              ', 'MERANTI KERING A 100 X 6 X 11.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(1234, 'K-0475              ', 'MERANTI KERING A 100 X 6 X 12                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(1235, 'K-0476              ', 'MERANTI KERING A 100 X 6 X 12.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(1236, 'K-0477              ', 'MERANTI KERING A 100 X 6 X 13                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(1237, 'K-0478              ', 'MERANTI KERING A 100 X 6 X 13.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(1238, 'K-0479              ', 'MERANTI KERING A 100 X 6 X 14                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(1239, 'K-0480              ', 'MERANTI KERING A 100 X 6 X 14.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(1240, 'K-0481              ', 'MERANTI KERING A 100 X 6 X 15                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(1241, 'K-0482              ', 'MERANTI KERING A 100 X 6 X 15.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(1242, 'K-0483              ', 'MERANTI KERING A 100 X 6 X 16                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(1243, 'K-0484              ', 'MERANTI KERING B 100 X 6 X 5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(1244, 'K-0485              ', 'MERANTI KERING B 100 X 6 X 5.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(1245, 'K-0486              ', 'MERANTI KERING B 100 X 6 X 6                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(1246, 'K-0487              ', 'MERANTI KERING B 100 X 6 X 6.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(1247, 'K-0488              ', 'MERANTI KERING B 100 X 6 X 7                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(1248, 'K-0489              ', 'MERANTI KERING B 100 X 6 X 7.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(1249, 'K-0490              ', 'MERANTI KERING B 100 X 6 X 8                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(1250, 'K-0491              ', 'MERANTI KERING B 100 X 6 X 8.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(1251, 'K-0492              ', 'MERANTI KERING B 100 X 6 X 9                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(1252, 'K-0493              ', 'MERANTI KERING B 100 X 6 X 9.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(1253, 'K-0494              ', 'MERANTI KERING B 100 X 6 X 10                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(1254, 'K-0495              ', 'MERANTI KERING B 100 X 6 X 10.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(1255, 'K-0496              ', 'MERANTI KERING B 100 X 6 X 11                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(1256, 'K-0497              ', 'MERANTI KERING B 100 X 6 X 11.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(1257, 'K-0498              ', 'MERANTI KERING B 100 X 6 X 12                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(1258, 'K-0499              ', 'MERANTI KERING B 100 X 6 X 12.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(1259, 'K-0500              ', 'MERANTI KERING B 100 X 6 X 13                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(1260, 'K-0501              ', 'MERANTI KERING B 100 X 6 X 13.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(1261, 'K-0502              ', 'MERANTI KERING B 100 X 6 X 14                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(1262, 'K-0503              ', 'MERANTI KERING B 100 X 6 X 14.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(1263, 'K-0504              ', 'MERANTI KERING B 100 X 6 X 15                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(1264, 'K-0505              ', 'MERANTI KERING B 100 X 6 X 15.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(1265, 'K-0506              ', 'MERANTI KERING B 100 X 6 X 16                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(1266, 'K-0507              ', 'MERANTI KERING ALL 100 X 6 X 5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(1267, 'K-0508              ', 'MERANTI KERING ALL 100 X 6 X 5.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(1268, 'K-0509              ', 'MERANTI KERING ALL 100 X 6 X 6                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(1269, 'K-0510              ', 'MERANTI KERING ALL 100 X 6 X 6.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(1270, 'K-0511              ', 'MERANTI KERING ALL 100 X 6 X 7                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(1271, 'K-0512              ', 'MERANTI KERING ALL 100 X 6 X 7.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(1272, 'K-0513              ', 'MERANTI KERING ALL 100 X 6 X 8                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(1273, 'K-0514              ', 'MERANTI KERING ALL 100 X 6 X 8.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(1274, 'K-0515              ', 'MERANTI KERING ALL 100 X 6 X 9                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(1275, 'K-0516              ', 'MERANTI KERING ALL 100 X 6 X 9.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(1276, 'K-0517              ', 'MERANTI KERING ALL 100 X 6 X 10                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(1277, 'K-0518              ', 'MERANTI KERING ALL 100 X 6 X 10.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(1278, 'K-0519              ', 'MERANTI KERING ALL 100 X 6 X 11                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(1279, 'K-0520              ', 'MERANTI KERING ALL 100 X 6 X 11.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(1280, 'K-0521              ', 'MERANTI KERING ALL 100 X 6 X 12                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(1281, 'K-0522              ', 'MERANTI KERING ALL 100 X 6 X 12.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(1282, 'K-0523              ', 'MERANTI KERING ALL 100 X 6 X 13                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(1283, 'K-0524              ', 'MERANTI KERING ALL 100 X 6 X 13.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(1284, 'K-0525              ', 'MERANTI KERING ALL 100 X 6 X 14                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(1285, 'K-0526              ', 'MERANTI KERING ALL 100 X 6 X 14.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(1286, 'K-0527              ', 'MERANTI KERING ALL 100 X 6 X 15                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(1287, 'K-0528              ', 'MERANTI KERING ALL 100 X 6 X 15.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(1288, 'K-0529              ', 'MERANTI KERING ALL 100 X 6 X 16                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(1289, 'K-0530              ', 'MERANTI KERING AFB 100 X 6 X 5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(1290, 'K-0531              ', 'MERANTI KERING AFB 100 X 6 X 5.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(1291, 'K-0532              ', 'MERANTI KERING AFB 100 X 6 X 6                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(1292, 'K-0533              ', 'MERANTI KERING AFB 100 X 6 X 6.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(1293, 'K-0534              ', 'MERANTI KERING AFB 100 X 6 X 7                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(1294, 'K-0535              ', 'MERANTI KERING AFB 100 X 6 X 7.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(1295, 'K-0536              ', 'MERANTI KERING AFB 100 X 6 X 8                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(1296, 'K-0537              ', 'MERANTI KERING AFB 100 X 6 X 8.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(1297, 'K-0538              ', 'MERANTI KERING AFB 100 X 6 X 9                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(1298, 'K-0539              ', 'MERANTI KERING AFB 100 X 6 X 9.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(1299, 'K-0540              ', 'MERANTI KERING AFB 100 X 6 X 10                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(1300, 'K-0541              ', 'MERANTI KERING AFB 100 X 6 X 10.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(1301, 'K-0542              ', 'MERANTI KERING AFB 100 X 6 X 11                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(1302, 'K-0543              ', 'MERANTI KERING AFB 100 X 6 X 11.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(1303, 'K-0544              ', 'MERANTI KERING AFB 100 X 6 X 12                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(1304, 'K-0545              ', 'MERANTI KERING AFB 100 X 6 X 12.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(1305, 'K-0546              ', 'MERANTI KERING AFB 100 X 6 X 13                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(1306, 'K-0547              ', 'MERANTI KERING AFB 100 X 6 X 13.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(1307, 'K-0548              ', 'MERANTI KERING AFB 100 X 6 X 14                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(1308, 'K-0549              ', 'MERANTI KERING AFB 100 X 6 X 14.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(1309, 'K-0550              ', 'MERANTI KERING AFB 100 X 6 X 15                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(1310, 'K-0551              ', 'MERANTI KERING AFB 100 X 6 X 15.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(1311, 'K-0552              ', 'MERANTI KERING AFB 100 X 6 X 16                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(1312, 'K-0553              ', 'MERANTI KERING A 130 X 5 X 5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(1313, 'K-0554              ', 'MERANTI KERING A 130 X 5 X 5.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(1314, 'K-0555              ', 'MERANTI KERING A 130 X 5 X 6                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(1315, 'K-0556              ', 'MERANTI KERING A 130 X 5 X 6.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(1316, 'K-0557              ', 'MERANTI KERING A 130 X 5 X 7                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(1317, 'K-0558              ', 'MERANTI KERING A 130 X 5 X 7.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(1318, 'K-0559              ', 'MERANTI KERING A 130 X 5 X 8                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(1319, 'K-0560              ', 'MERANTI KERING A 130 X 5 X 8.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(1320, 'K-0561              ', 'MERANTI KERING A 130 X 5 X 9                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(1321, 'K-0562              ', 'MERANTI KERING A 130 X 5 X 9.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL);
INSERT INTO `product` (`id`, `product_code`, `product_name`, `product_category_id`, `product_status`, `product_uom_id`, `is_maintain_stock`, `image_path`, `brand`, `barcode`, `description`, `wood_type_id`, `grade_id`, `thickness_id`, `condition_id`, `is_has_serial`, `is_fixed_asset`, `warranty`, `netto`, `netto_uom_id`, `is_purchase_item`, `minor`, `minor_uom_id`, `lead_time`, `buy_cost_center_id`, `expense_acc_id`, `main_supp_code`, `manufacturer`, `is_sales_item`, `is_service_item`, `sell_cost_center_id`, `income_acc_id`, `max_disc`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `minqty`, `thickness`, `length`, `width`, `production_type_id`, `production_quality_id`) VALUES
(1322, 'K-0563              ', 'MERANTI KERING A 130 X 5 X 10                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(1323, 'K-0564              ', 'MERANTI KERING A 130 X 5 X 10.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(1324, 'K-0565              ', 'MERANTI KERING A 130 X 5 X 11                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(1325, 'K-0566              ', 'MERANTI KERING A 130 X 5 X 11.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(1326, 'K-0567              ', 'MERANTI KERING A 130 X 5 X 12                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(1327, 'K-0568              ', 'MERANTI KERING A 130 X 5 X 12.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(1328, 'K-0569              ', 'MERANTI KERING A 130 X 5 X 13                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(1329, 'K-0570              ', 'MERANTI KERING A 130 X 5 X 13.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(1330, 'K-0571              ', 'MERANTI KERING A 130 X 5 X 14                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(1331, 'K-0572              ', 'MERANTI KERING A 130 X 5 X 14.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(1332, 'K-0573              ', 'MERANTI KERING A 130 X 5 X 15                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(1333, 'K-0574              ', 'MERANTI KERING A 130 X 5 X 15.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(1334, 'K-0575              ', 'MERANTI KERING A 130 X 5 X 16                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(1335, 'K-0576              ', 'MERANTI KERING B 130 X 5 X 5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(1336, 'K-0577              ', 'MERANTI KERING B 130 X 5 X 5.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(1337, 'K-0578              ', 'MERANTI KERING B 130 X 5 X 6                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(1338, 'K-0579              ', 'MERANTI KERING B 130 X 5 X 6.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(1339, 'K-0580              ', 'MERANTI KERING B 130 X 5 X 7                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(1340, 'K-0581              ', 'MERANTI KERING B 130 X 5 X 7.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(1341, 'K-0582              ', 'MERANTI KERING B 130 X 5 X 8                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(1342, 'K-0583              ', 'MERANTI KERING B 130 X 5 X 8.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(1343, 'K-0584              ', 'MERANTI KERING B 130 X 5 X 9                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(1344, 'K-0585              ', 'MERANTI KERING B 130 X 5 X 9.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(1345, 'K-0586              ', 'MERANTI KERING B 130 X 5 X 10                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(1346, 'K-0587              ', 'MERANTI KERING B 130 X 5 X 10.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(1347, 'K-0588              ', 'MERANTI KERING B 130 X 5 X 11                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(1348, 'K-0589              ', 'MERANTI KERING B 130 X 5 X 11.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(1349, 'K-0590              ', 'MERANTI KERING B 130 X 5 X 12                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(1350, 'K-0591              ', 'MERANTI KERING B 130 X 5 X 12.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(1351, 'K-0592              ', 'MERANTI KERING B 130 X 5 X 13                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(1352, 'K-0593              ', 'MERANTI KERING B 130 X 5 X 13.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(1353, 'K-0594              ', 'MERANTI KERING B 130 X 5 X 14                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(1354, 'K-0595              ', 'MERANTI KERING B 130 X 5 X 14.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(1355, 'K-0596              ', 'MERANTI KERING B 130 X 5 X 15                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(1356, 'K-0597              ', 'MERANTI KERING B 130 X 5 X 15.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(1357, 'K-0598              ', 'MERANTI KERING B 130 X 5 X 16                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(1358, 'K-0599              ', 'MERANTI KERING ALL 130 X 5 X 5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(1359, 'K-0600              ', 'MERANTI KERING ALL 130 X 5 X 5.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(1360, 'K-0601              ', 'MERANTI KERING ALL 130 X 5 X 6                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(1361, 'K-0602              ', 'MERANTI KERING ALL 130 X 5 X 6.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(1362, 'K-0603              ', 'MERANTI KERING ALL 130 X 5 X 7                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(1363, 'K-0604              ', 'MERANTI KERING ALL 130 X 5 X 7.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(1364, 'K-0605              ', 'MERANTI KERING ALL 130 X 5 X 8                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(1365, 'K-0606              ', 'MERANTI KERING ALL 130 X 5 X 8.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(1366, 'K-0607              ', 'MERANTI KERING ALL 130 X 5 X 9                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(1367, 'K-0608              ', 'MERANTI KERING ALL 130 X 5 X 9.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(1368, 'K-0609              ', 'MERANTI KERING ALL 130 X 5 X 10                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(1369, 'K-0610              ', 'MERANTI KERING ALL 130 X 5 X 10.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(1370, 'K-0611              ', 'MERANTI KERING ALL 130 X 5 X 11                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(1371, 'K-0612              ', 'MERANTI KERING ALL 130 X 5 X 11.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(1372, 'K-0613              ', 'MERANTI KERING ALL 130 X 5 X 12                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(1373, 'K-0614              ', 'MERANTI KERING ALL 130 X 5 X 12.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(1374, 'K-0615              ', 'MERANTI KERING ALL 130 X 5 X 13                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(1375, 'K-0616              ', 'MERANTI KERING ALL 130 X 5 X 13.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(1376, 'K-0617              ', 'MERANTI KERING ALL 130 X 5 X 14                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(1377, 'K-0618              ', 'MERANTI KERING ALL 130 X 5 X 14.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(1378, 'K-0619              ', 'MERANTI KERING ALL 130 X 5 X 15                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(1379, 'K-0620              ', 'MERANTI KERING ALL 130 X 5 X 15.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(1380, 'K-0621              ', 'MERANTI KERING ALL 130 X 5 X 16                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(1381, 'K-0622              ', 'MERANTI KERING AFB 130 X 5 X 5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(1382, 'K-0623              ', 'MERANTI KERING AFB 130 X 5 X 5.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(1383, 'K-0624              ', 'MERANTI KERING AFB 130 X 5 X 6                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(1384, 'K-0625              ', 'MERANTI KERING AFB 130 X 5 X 6.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(1385, 'K-0626              ', 'MERANTI KERING AFB 130 X 5 X 7                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(1386, 'K-0627              ', 'MERANTI KERING AFB 130 X 5 X 7.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(1387, 'K-0628              ', 'MERANTI KERING AFB 130 X 5 X 8                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(1388, 'K-0629              ', 'MERANTI KERING AFB 130 X 5 X 8.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(1389, 'K-0630              ', 'MERANTI KERING AFB 130 X 5 X 9                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(1390, 'K-0631              ', 'MERANTI KERING AFB 130 X 5 X 9.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(1391, 'K-0632              ', 'MERANTI KERING AFB 130 X 5 X 10                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(1392, 'K-0633              ', 'MERANTI KERING AFB 130 X 5 X 10.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(1393, 'K-0634              ', 'MERANTI KERING AFB 130 X 5 X 11                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(1394, 'K-0635              ', 'MERANTI KERING AFB 130 X 5 X 11.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(1395, 'K-0636              ', 'MERANTI KERING AFB 130 X 5 X 12                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(1396, 'K-0637              ', 'MERANTI KERING AFB 130 X 5 X 12.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(1397, 'K-0638              ', 'MERANTI KERING AFB 130 X 5 X 13                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(1398, 'K-0639              ', 'MERANTI KERING AFB 130 X 5 X 13.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(1399, 'K-0640              ', 'MERANTI KERING AFB 130 X 5 X 14                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(1400, 'K-0641              ', 'MERANTI KERING AFB 130 X 5 X 14.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(1401, 'K-0642              ', 'MERANTI KERING AFB 130 X 5 X 15                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(1402, 'K-0643              ', 'MERANTI KERING AFB 130 X 5 X 15.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(1403, 'K-0644              ', 'MERANTI KERING AFB 130 X 5 X 16                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(1404, 'K-0645              ', 'MERANTI KERING A 130 X 6 X 5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(1405, 'K-0646              ', 'MERANTI KERING A 130 X 6 X 5.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(1406, 'K-0647              ', 'MERANTI KERING A 130 X 6 X 6                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(1407, 'K-0648              ', 'MERANTI KERING A 130 X 6 X 6.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(1408, 'K-0649              ', 'MERANTI KERING A 130 X 6 X 7                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(1409, 'K-0650              ', 'MERANTI KERING A 130 X 6 X 7.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(1410, 'K-0651              ', 'MERANTI KERING A 130 X 6 X 8                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(1411, 'K-0652              ', 'MERANTI KERING A 130 X 6 X 8.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(1412, 'K-0653              ', 'MERANTI KERING A 130 X 6 X 9                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(1413, 'K-0654              ', 'MERANTI KERING A 130 X 6 X 9.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(1414, 'K-0655              ', 'MERANTI KERING A 130 X 6 X 10                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(1415, 'K-0656              ', 'MERANTI KERING A 130 X 6 X 10.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(1416, 'K-0657              ', 'MERANTI KERING A 130 X 6 X 11                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(1417, 'K-0658              ', 'MERANTI KERING A 130 X 6 X 11.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(1418, 'K-0659              ', 'MERANTI KERING A 130 X 6 X 12                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(1419, 'K-0660              ', 'MERANTI KERING A 130 X 6 X 12.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(1420, 'K-0661              ', 'MERANTI KERING A 130 X 6 X 13                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(1421, 'K-0662              ', 'MERANTI KERING A 130 X 6 X 13.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(1422, 'K-0663              ', 'MERANTI KERING A 130 X 6 X 14                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(1423, 'K-0664              ', 'MERANTI KERING A 130 X 6 X 14.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(1424, 'K-0665              ', 'MERANTI KERING A 130 X 6 X 15                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(1425, 'K-0666              ', 'MERANTI KERING A 130 X 6 X 15.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(1426, 'K-0667              ', 'MERANTI KERING A 130 X 6 X 16                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(1427, 'K-0668              ', 'MERANTI KERING B 130 X 6 X 5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(1428, 'K-0669              ', 'MERANTI KERING B 130 X 6 X 5.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(1429, 'K-0670              ', 'MERANTI KERING B 130 X 6 X 6                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(1430, 'K-0671              ', 'MERANTI KERING B 130 X 6 X 6.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(1431, 'K-0672              ', 'MERANTI KERING B 130 X 6 X 7                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(1432, 'K-0673              ', 'MERANTI KERING B 130 X 6 X 7.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(1433, 'K-0674              ', 'MERANTI KERING B 130 X 6 X 8                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(1434, 'K-0675              ', 'MERANTI KERING B 130 X 6 X 8.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(1435, 'K-0676              ', 'MERANTI KERING B 130 X 6 X 9                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(1436, 'K-0677              ', 'MERANTI KERING B 130 X 6 X 9.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(1437, 'K-0678              ', 'MERANTI KERING B 130 X 6 X 10                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(1438, 'K-0679              ', 'MERANTI KERING B 130 X 6 X 10.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(1439, 'K-0680              ', 'MERANTI KERING B 130 X 6 X 11                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(1440, 'K-0681              ', 'MERANTI KERING B 130 X 6 X 11.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(1441, 'K-0682              ', 'MERANTI KERING B 130 X 6 X 12                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(1442, 'K-0683              ', 'MERANTI KERING B 130 X 6 X 12.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(1443, 'K-0684              ', 'MERANTI KERING B 130 X 6 X 13                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(1444, 'K-0685              ', 'MERANTI KERING B 130 X 6 X 13.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(1445, 'K-0686              ', 'MERANTI KERING B 130 X 6 X 14                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(1446, 'K-0687              ', 'MERANTI KERING B 130 X 6 X 14.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(1447, 'K-0688              ', 'MERANTI KERING B 130 X 6 X 15                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(1448, 'K-0689              ', 'MERANTI KERING B 130 X 6 X 15.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(1449, 'K-0690              ', 'MERANTI KERING B 130 X 6 X 16                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(1450, 'K-0691              ', 'MERANTI KERING ALL 130 X 6 X 5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(1451, 'K-0692              ', 'MERANTI KERING ALL 130 X 6 X 5.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(1452, 'K-0693              ', 'MERANTI KERING ALL 130 X 6 X 6                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(1453, 'K-0694              ', 'MERANTI KERING ALL 130 X 6 X 6.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(1454, 'K-0695              ', 'MERANTI KERING ALL 130 X 6 X 7                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(1455, 'K-0696              ', 'MERANTI KERING ALL 130 X 6 X 7.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(1456, 'K-0697              ', 'MERANTI KERING ALL 130 X 6 X 8                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(1457, 'K-0698              ', 'MERANTI KERING ALL 130 X 6 X 8.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(1458, 'K-0699              ', 'MERANTI KERING ALL 130 X 6 X 9                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(1459, 'K-0700              ', 'MERANTI KERING ALL 130 X 6 X 9.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(1460, 'K-0701              ', 'MERANTI KERING ALL 130 X 6 X 10                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(1461, 'K-0702              ', 'MERANTI KERING ALL 130 X 6 X 10.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(1462, 'K-0703              ', 'MERANTI KERING ALL 130 X 6 X 11                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(1463, 'K-0704              ', 'MERANTI KERING ALL 130 X 6 X 11.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(1464, 'K-0705              ', 'MERANTI KERING ALL 130 X 6 X 12                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(1465, 'K-0706              ', 'MERANTI KERING ALL 130 X 6 X 12.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(1466, 'K-0707              ', 'MERANTI KERING ALL 130 X 6 X 13                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL);
INSERT INTO `product` (`id`, `product_code`, `product_name`, `product_category_id`, `product_status`, `product_uom_id`, `is_maintain_stock`, `image_path`, `brand`, `barcode`, `description`, `wood_type_id`, `grade_id`, `thickness_id`, `condition_id`, `is_has_serial`, `is_fixed_asset`, `warranty`, `netto`, `netto_uom_id`, `is_purchase_item`, `minor`, `minor_uom_id`, `lead_time`, `buy_cost_center_id`, `expense_acc_id`, `main_supp_code`, `manufacturer`, `is_sales_item`, `is_service_item`, `sell_cost_center_id`, `income_acc_id`, `max_disc`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `minqty`, `thickness`, `length`, `width`, `production_type_id`, `production_quality_id`) VALUES
(1467, 'K-0708              ', 'MERANTI KERING ALL 130 X 6 X 13.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(1468, 'K-0709              ', 'MERANTI KERING ALL 130 X 6 X 14                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(1469, 'K-0710              ', 'MERANTI KERING ALL 130 X 6 X 14.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(1470, 'K-0711              ', 'MERANTI KERING ALL 130 X 6 X 15                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(1471, 'K-0712              ', 'MERANTI KERING ALL 130 X 6 X 15.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(1472, 'K-0713              ', 'MERANTI KERING ALL 130 X 6 X 16                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(1473, 'K-0714              ', 'MERANTI KERING AFB 130 X 6 X 5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(1474, 'K-0715              ', 'MERANTI KERING AFB 130 X 6 X 5.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(1475, 'K-0716              ', 'MERANTI KERING AFB 130 X 6 X 6                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(1476, 'K-0717              ', 'MERANTI KERING AFB 130 X 6 X 6.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(1477, 'K-0718              ', 'MERANTI KERING AFB 130 X 6 X 7                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(1478, 'K-0719              ', 'MERANTI KERING AFB 130 X 6 X 7.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(1479, 'K-0720              ', 'MERANTI KERING AFB 130 X 6 X 8                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(1480, 'K-0721              ', 'MERANTI KERING AFB 130 X 6 X 8.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(1481, 'K-0722              ', 'MERANTI KERING AFB 130 X 6 X 9                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(1482, 'K-0723              ', 'MERANTI KERING AFB 130 X 6 X 9.5                  ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(1483, 'K-0724              ', 'MERANTI KERING AFB 130 X 6 X 10                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(1484, 'K-0725              ', 'MERANTI KERING AFB 130 X 6 X 10.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(1485, 'K-0726              ', 'MERANTI KERING AFB 130 X 6 X 11                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(1486, 'K-0727              ', 'MERANTI KERING AFB 130 X 6 X 11.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(1487, 'K-0728              ', 'MERANTI KERING AFB 130 X 6 X 12                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(1488, 'K-0729              ', 'MERANTI KERING AFB 130 X 6 X 12.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(1489, 'K-0730              ', 'MERANTI KERING AFB 130 X 6 X 13                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(1490, 'K-0731              ', 'MERANTI KERING AFB 130 X 6 X 13.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(1491, 'K-0732              ', 'MERANTI KERING AFB 130 X 6 X 14                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(1492, 'K-0733              ', 'MERANTI KERING AFB 130 X 6 X 14.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(1493, 'K-0734              ', 'MERANTI KERING AFB 130 X 6 X 15                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(1494, 'K-0735              ', 'MERANTI KERING AFB 130 X 6 X 15.5                 ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(1495, 'K-0736              ', 'MERANTI KERING AFB 130 X 6 X 16                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 2, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(1496, 'B-0737              ', 'JABON BASAH A 100 X 5 X 5                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(1497, 'B-0738              ', 'JABON BASAH A 100 X 5 X 5.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(1498, 'B-0739              ', 'JABON BASAH A 100 X 5 X 6                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(1499, 'B-0740              ', 'JABON BASAH A 100 X 5 X 6.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(1500, 'B-0741              ', 'JABON BASAH A 100 X 5 X 7                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(1501, 'B-0742              ', 'JABON BASAH A 100 X 5 X 7.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(1502, 'B-0743              ', 'JABON BASAH A 100 X 5 X 8                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(1503, 'B-0744              ', 'JABON BASAH A 100 X 5 X 8.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(1504, 'B-0745              ', 'JABON BASAH A 100 X 5 X 9                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(1505, 'B-0746              ', 'JABON BASAH A 100 X 5 X 9.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(1506, 'B-0747              ', 'JABON BASAH A 100 X 5 X 10                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(1507, 'B-0748              ', 'JABON BASAH A 100 X 5 X 10.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(1508, 'B-0749              ', 'JABON BASAH A 100 X 5 X 11                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(1509, 'B-0750              ', 'JABON BASAH A 100 X 5 X 11.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(1510, 'B-0751              ', 'JABON BASAH A 100 X 5 X 12                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(1511, 'B-0752              ', 'JABON BASAH A 100 X 5 X 12.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(1512, 'B-0753              ', 'JABON BASAH A 100 X 5 X 13                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(1513, 'B-0754              ', 'JABON BASAH A 100 X 5 X 13.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(1514, 'B-0755              ', 'JABON BASAH A 100 X 5 X 14                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(1515, 'B-0756              ', 'JABON BASAH A 100 X 5 X 14.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(1516, 'B-0757              ', 'JABON BASAH A 100 X 5 X 15                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(1517, 'B-0758              ', 'JABON BASAH A 100 X 5 X 15.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(1518, 'B-0759              ', 'JABON BASAH A 100 X 5 X 16                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(1519, 'B-0760              ', 'JABON BASAH B 100 X 5 X 5                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(1520, 'B-0761              ', 'JABON BASAH B 100 X 5 X 5.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(1521, 'B-0762              ', 'JABON BASAH B 100 X 5 X 6                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(1522, 'B-0763              ', 'JABON BASAH B 100 X 5 X 6.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(1523, 'B-0764              ', 'JABON BASAH B 100 X 5 X 7                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(1524, 'B-0765              ', 'JABON BASAH B 100 X 5 X 7.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(1525, 'B-0766              ', 'JABON BASAH B 100 X 5 X 8                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(1526, 'B-0767              ', 'JABON BASAH B 100 X 5 X 8.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(1527, 'B-0768              ', 'JABON BASAH B 100 X 5 X 9                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(1528, 'B-0769              ', 'JABON BASAH B 100 X 5 X 9.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(1529, 'B-0770              ', 'JABON BASAH B 100 X 5 X 10                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(1530, 'B-0771              ', 'JABON BASAH B 100 X 5 X 10.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(1531, 'B-0772              ', 'JABON BASAH B 100 X 5 X 11                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(1532, 'B-0773              ', 'JABON BASAH B 100 X 5 X 11.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(1533, 'B-0774              ', 'JABON BASAH B 100 X 5 X 12                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(1534, 'B-0775              ', 'JABON BASAH B 100 X 5 X 12.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(1535, 'B-0776              ', 'JABON BASAH B 100 X 5 X 13                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(1536, 'B-0777              ', 'JABON BASAH B 100 X 5 X 13.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(1537, 'B-0778              ', 'JABON BASAH B 100 X 5 X 14                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(1538, 'B-0779              ', 'JABON BASAH B 100 X 5 X 14.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(1539, 'B-0780              ', 'JABON BASAH B 100 X 5 X 15                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(1540, 'B-0781              ', 'JABON BASAH B 100 X 5 X 15.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(1541, 'B-0782              ', 'JABON BASAH B 100 X 5 X 16                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(1542, 'B-0783              ', 'JABON BASAH ALL 100 X 5 X 5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(1543, 'B-0784              ', 'JABON BASAH ALL 100 X 5 X 5.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(1544, 'B-0785              ', 'JABON BASAH ALL 100 X 5 X 6                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(1545, 'B-0786              ', 'JABON BASAH ALL 100 X 5 X 6.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(1546, 'B-0787              ', 'JABON BASAH ALL 100 X 5 X 7                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(1547, 'B-0788              ', 'JABON BASAH ALL 100 X 5 X 7.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(1548, 'B-0789              ', 'JABON BASAH ALL 100 X 5 X 8                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(1549, 'B-0790              ', 'JABON BASAH ALL 100 X 5 X 8.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(1550, 'B-0791              ', 'JABON BASAH ALL 100 X 5 X 9                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(1551, 'B-0792              ', 'JABON BASAH ALL 100 X 5 X 9.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(1552, 'B-0793              ', 'JABON BASAH ALL 100 X 5 X 10                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(1553, 'B-0794              ', 'JABON BASAH ALL 100 X 5 X 10.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(1554, 'B-0795              ', 'JABON BASAH ALL 100 X 5 X 11                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(1555, 'B-0796              ', 'JABON BASAH ALL 100 X 5 X 11.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(1556, 'B-0797              ', 'JABON BASAH ALL 100 X 5 X 12                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(1557, 'B-0798              ', 'JABON BASAH ALL 100 X 5 X 12.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(1558, 'B-0799              ', 'JABON BASAH ALL 100 X 5 X 13                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(1559, 'B-0800              ', 'JABON BASAH ALL 100 X 5 X 13.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(1560, 'B-0801              ', 'JABON BASAH ALL 100 X 5 X 14                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(1561, 'B-0802              ', 'JABON BASAH ALL 100 X 5 X 14.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(1562, 'B-0803              ', 'JABON BASAH ALL 100 X 5 X 15                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(1563, 'B-0804              ', 'JABON BASAH ALL 100 X 5 X 15.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(1564, 'B-0805              ', 'JABON BASAH ALL 100 X 5 X 16                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(1565, 'B-0806              ', 'JABON BASAH AFB 100 X 5 X 5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(1566, 'B-0807              ', 'JABON BASAH AFB 100 X 5 X 5.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(1567, 'B-0808              ', 'JABON BASAH AFB 100 X 5 X 6                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(1568, 'B-0809              ', 'JABON BASAH AFB 100 X 5 X 6.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(1569, 'B-0810              ', 'JABON BASAH AFB 100 X 5 X 7                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(1570, 'B-0811              ', 'JABON BASAH AFB 100 X 5 X 7.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(1571, 'B-0812              ', 'JABON BASAH AFB 100 X 5 X 8                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(1572, 'B-0813              ', 'JABON BASAH AFB 100 X 5 X 8.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(1573, 'B-0814              ', 'JABON BASAH AFB 100 X 5 X 9                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(1574, 'B-0815              ', 'JABON BASAH AFB 100 X 5 X 9.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(1575, 'B-0816              ', 'JABON BASAH AFB 100 X 5 X 10                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(1576, 'B-0817              ', 'JABON BASAH AFB 100 X 5 X 10.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(1577, 'B-0818              ', 'JABON BASAH AFB 100 X 5 X 11                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(1578, 'B-0819              ', 'JABON BASAH AFB 100 X 5 X 11.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(1579, 'B-0820              ', 'JABON BASAH AFB 100 X 5 X 12                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(1580, 'B-0821              ', 'JABON BASAH AFB 100 X 5 X 12.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(1581, 'B-0822              ', 'JABON BASAH AFB 100 X 5 X 13                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(1582, 'B-0823              ', 'JABON BASAH AFB 100 X 5 X 13.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(1583, 'B-0824              ', 'JABON BASAH AFB 100 X 5 X 14                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(1584, 'B-0825              ', 'JABON BASAH AFB 100 X 5 X 14.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(1585, 'B-0826              ', 'JABON BASAH AFB 100 X 5 X 15                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(1586, 'B-0827              ', 'JABON BASAH AFB 100 X 5 X 15.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(1587, 'B-0828              ', 'JABON BASAH AFB 100 X 5 X 16                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(1588, 'B-0829              ', 'JABON BASAH A 100 X 6 X 5                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(1589, 'B-0830              ', 'JABON BASAH A 100 X 6 X 5.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(1590, 'B-0831              ', 'JABON BASAH A 100 X 6 X 6                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(1591, 'B-0832              ', 'JABON BASAH A 100 X 6 X 6.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(1592, 'B-0833              ', 'JABON BASAH A 100 X 6 X 7                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(1593, 'B-0834              ', 'JABON BASAH A 100 X 6 X 7.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(1594, 'B-0835              ', 'JABON BASAH A 100 X 6 X 8                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(1595, 'B-0836              ', 'JABON BASAH A 100 X 6 X 8.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(1596, 'B-0837              ', 'JABON BASAH A 100 X 6 X 9                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(1597, 'B-0838              ', 'JABON BASAH A 100 X 6 X 9.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(1598, 'B-0839              ', 'JABON BASAH A 100 X 6 X 10                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(1599, 'B-0840              ', 'JABON BASAH A 100 X 6 X 10.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(1600, 'B-0841              ', 'JABON BASAH A 100 X 6 X 11                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(1601, 'B-0842              ', 'JABON BASAH A 100 X 6 X 11.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(1602, 'B-0843              ', 'JABON BASAH A 100 X 6 X 12                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(1603, 'B-0844              ', 'JABON BASAH A 100 X 6 X 12.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(1604, 'B-0845              ', 'JABON BASAH A 100 X 6 X 13                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(1605, 'B-0846              ', 'JABON BASAH A 100 X 6 X 13.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(1606, 'B-0847              ', 'JABON BASAH A 100 X 6 X 14                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(1607, 'B-0848              ', 'JABON BASAH A 100 X 6 X 14.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(1608, 'B-0849              ', 'JABON BASAH A 100 X 6 X 15                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(1609, 'B-0850              ', 'JABON BASAH A 100 X 6 X 15.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(1610, 'B-0851              ', 'JABON BASAH A 100 X 6 X 16                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(1611, 'B-0852              ', 'JABON BASAH B 100 X 6 X 5                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL);
INSERT INTO `product` (`id`, `product_code`, `product_name`, `product_category_id`, `product_status`, `product_uom_id`, `is_maintain_stock`, `image_path`, `brand`, `barcode`, `description`, `wood_type_id`, `grade_id`, `thickness_id`, `condition_id`, `is_has_serial`, `is_fixed_asset`, `warranty`, `netto`, `netto_uom_id`, `is_purchase_item`, `minor`, `minor_uom_id`, `lead_time`, `buy_cost_center_id`, `expense_acc_id`, `main_supp_code`, `manufacturer`, `is_sales_item`, `is_service_item`, `sell_cost_center_id`, `income_acc_id`, `max_disc`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `minqty`, `thickness`, `length`, `width`, `production_type_id`, `production_quality_id`) VALUES
(1612, 'B-0853              ', 'JABON BASAH B 100 X 6 X 5.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(1613, 'B-0854              ', 'JABON BASAH B 100 X 6 X 6                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(1614, 'B-0855              ', 'JABON BASAH B 100 X 6 X 6.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(1615, 'B-0856              ', 'JABON BASAH B 100 X 6 X 7                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(1616, 'B-0857              ', 'JABON BASAH B 100 X 6 X 7.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(1617, 'B-0858              ', 'JABON BASAH B 100 X 6 X 8                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(1618, 'B-0859              ', 'JABON BASAH B 100 X 6 X 8.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(1619, 'B-0860              ', 'JABON BASAH B 100 X 6 X 9                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(1620, 'B-0861              ', 'JABON BASAH B 100 X 6 X 9.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(1621, 'B-0862              ', 'JABON BASAH B 100 X 6 X 10                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(1622, 'B-0863              ', 'JABON BASAH B 100 X 6 X 10.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(1623, 'B-0864              ', 'JABON BASAH B 100 X 6 X 11                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(1624, 'B-0865              ', 'JABON BASAH B 100 X 6 X 11.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(1625, 'B-0866              ', 'JABON BASAH B 100 X 6 X 12                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(1626, 'B-0867              ', 'JABON BASAH B 100 X 6 X 12.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(1627, 'B-0868              ', 'JABON BASAH B 100 X 6 X 13                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(1628, 'B-0869              ', 'JABON BASAH B 100 X 6 X 13.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(1629, 'B-0870              ', 'JABON BASAH B 100 X 6 X 14                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(1630, 'B-0871              ', 'JABON BASAH B 100 X 6 X 14.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(1631, 'B-0872              ', 'JABON BASAH B 100 X 6 X 15                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(1632, 'B-0873              ', 'JABON BASAH B 100 X 6 X 15.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(1633, 'B-0874              ', 'JABON BASAH B 100 X 6 X 16                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(1634, 'B-0875              ', 'JABON BASAH ALL 100 X 6 X 5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(1635, 'B-0876              ', 'JABON BASAH ALL 100 X 6 X 5.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(1636, 'B-0877              ', 'JABON BASAH ALL 100 X 6 X 6                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(1637, 'B-0878              ', 'JABON BASAH ALL 100 X 6 X 6.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(1638, 'B-0879              ', 'JABON BASAH ALL 100 X 6 X 7                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(1639, 'B-0880              ', 'JABON BASAH ALL 100 X 6 X 7.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(1640, 'B-0881              ', 'JABON BASAH ALL 100 X 6 X 8                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(1641, 'B-0882              ', 'JABON BASAH ALL 100 X 6 X 8.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(1642, 'B-0883              ', 'JABON BASAH ALL 100 X 6 X 9                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(1643, 'B-0884              ', 'JABON BASAH ALL 100 X 6 X 9.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(1644, 'B-0885              ', 'JABON BASAH ALL 100 X 6 X 10                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(1645, 'B-0886              ', 'JABON BASAH ALL 100 X 6 X 10.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(1646, 'B-0887              ', 'JABON BASAH ALL 100 X 6 X 11                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(1647, 'B-0888              ', 'JABON BASAH ALL 100 X 6 X 11.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(1648, 'B-0889              ', 'JABON BASAH ALL 100 X 6 X 12                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(1649, 'B-0890              ', 'JABON BASAH ALL 100 X 6 X 12.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(1650, 'B-0891              ', 'JABON BASAH ALL 100 X 6 X 13                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(1651, 'B-0892              ', 'JABON BASAH ALL 100 X 6 X 13.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(1652, 'B-0893              ', 'JABON BASAH ALL 100 X 6 X 14                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(1653, 'B-0894              ', 'JABON BASAH ALL 100 X 6 X 14.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(1654, 'B-0895              ', 'JABON BASAH ALL 100 X 6 X 15                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(1655, 'B-0896              ', 'JABON BASAH ALL 100 X 6 X 15.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(1656, 'B-0897              ', 'JABON BASAH ALL 100 X 6 X 16                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(1657, 'B-0898              ', 'JABON BASAH AFB 100 X 6 X 5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(1658, 'B-0899              ', 'JABON BASAH AFB 100 X 6 X 5.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(1659, 'B-0900              ', 'JABON BASAH AFB 100 X 6 X 6                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(1660, 'B-0901              ', 'JABON BASAH AFB 100 X 6 X 6.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(1661, 'B-0902              ', 'JABON BASAH AFB 100 X 6 X 7                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(1662, 'B-0903              ', 'JABON BASAH AFB 100 X 6 X 7.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(1663, 'B-0904              ', 'JABON BASAH AFB 100 X 6 X 8                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(1664, 'B-0905              ', 'JABON BASAH AFB 100 X 6 X 8.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(1665, 'B-0906              ', 'JABON BASAH AFB 100 X 6 X 9                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(1666, 'B-0907              ', 'JABON BASAH AFB 100 X 6 X 9.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(1667, 'B-0908              ', 'JABON BASAH AFB 100 X 6 X 10                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(1668, 'B-0909              ', 'JABON BASAH AFB 100 X 6 X 10.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(1669, 'B-0910              ', 'JABON BASAH AFB 100 X 6 X 11                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(1670, 'B-0911              ', 'JABON BASAH AFB 100 X 6 X 11.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(1671, 'B-0912              ', 'JABON BASAH AFB 100 X 6 X 12                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(1672, 'B-0913              ', 'JABON BASAH AFB 100 X 6 X 12.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(1673, 'B-0914              ', 'JABON BASAH AFB 100 X 6 X 13                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(1674, 'B-0915              ', 'JABON BASAH AFB 100 X 6 X 13.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(1675, 'B-0916              ', 'JABON BASAH AFB 100 X 6 X 14                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(1676, 'B-0917              ', 'JABON BASAH AFB 100 X 6 X 14.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(1677, 'B-0918              ', 'JABON BASAH AFB 100 X 6 X 15                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(1678, 'B-0919              ', 'JABON BASAH AFB 100 X 6 X 15.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(1679, 'B-0920              ', 'JABON BASAH AFB 100 X 6 X 16                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(1680, 'B-0921              ', 'JABON BASAH A 130 X 5 X 5                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(1681, 'B-0922              ', 'JABON BASAH A 130 X 5 X 5.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(1682, 'B-0923              ', 'JABON BASAH A 130 X 5 X 6                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(1683, 'B-0924              ', 'JABON BASAH A 130 X 5 X 6.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(1684, 'B-0925              ', 'JABON BASAH A 130 X 5 X 7                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(1685, 'B-0926              ', 'JABON BASAH A 130 X 5 X 7.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(1686, 'B-0927              ', 'JABON BASAH A 130 X 5 X 8                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(1687, 'B-0928              ', 'JABON BASAH A 130 X 5 X 8.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(1688, 'B-0929              ', 'JABON BASAH A 130 X 5 X 9                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(1689, 'B-0930              ', 'JABON BASAH A 130 X 5 X 9.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(1690, 'B-0931              ', 'JABON BASAH A 130 X 5 X 10                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(1691, 'B-0932              ', 'JABON BASAH A 130 X 5 X 10.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(1692, 'B-0933              ', 'JABON BASAH A 130 X 5 X 11                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(1693, 'B-0934              ', 'JABON BASAH A 130 X 5 X 11.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(1694, 'B-0935              ', 'JABON BASAH A 130 X 5 X 12                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(1695, 'B-0936              ', 'JABON BASAH A 130 X 5 X 12.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(1696, 'B-0937              ', 'JABON BASAH A 130 X 5 X 13                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(1697, 'B-0938              ', 'JABON BASAH A 130 X 5 X 13.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(1698, 'B-0939              ', 'JABON BASAH A 130 X 5 X 14                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(1699, 'B-0940              ', 'JABON BASAH A 130 X 5 X 14.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(1700, 'B-0941              ', 'JABON BASAH A 130 X 5 X 15                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(1701, 'B-0942              ', 'JABON BASAH A 130 X 5 X 15.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(1702, 'B-0943              ', 'JABON BASAH A 130 X 5 X 16                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(1703, 'B-0944              ', 'JABON BASAH B 130 X 5 X 5                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(1704, 'B-0945              ', 'JABON BASAH B 130 X 5 X 5.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(1705, 'B-0946              ', 'JABON BASAH B 130 X 5 X 6                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(1706, 'B-0947              ', 'JABON BASAH B 130 X 5 X 6.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(1707, 'B-0948              ', 'JABON BASAH B 130 X 5 X 7                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(1708, 'B-0949              ', 'JABON BASAH B 130 X 5 X 7.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(1709, 'B-0950              ', 'JABON BASAH B 130 X 5 X 8                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(1710, 'B-0951              ', 'JABON BASAH B 130 X 5 X 8.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(1711, 'B-0952              ', 'JABON BASAH B 130 X 5 X 9                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(1712, 'B-0953              ', 'JABON BASAH B 130 X 5 X 9.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(1713, 'B-0954              ', 'JABON BASAH B 130 X 5 X 10                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(1714, 'B-0955              ', 'JABON BASAH B 130 X 5 X 10.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(1715, 'B-0956              ', 'JABON BASAH B 130 X 5 X 11                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(1716, 'B-0957              ', 'JABON BASAH B 130 X 5 X 11.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(1717, 'B-0958              ', 'JABON BASAH B 130 X 5 X 12                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(1718, 'B-0959              ', 'JABON BASAH B 130 X 5 X 12.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(1719, 'B-0960              ', 'JABON BASAH B 130 X 5 X 13                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(1720, 'B-0961              ', 'JABON BASAH B 130 X 5 X 13.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(1721, 'B-0962              ', 'JABON BASAH B 130 X 5 X 14                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(1722, 'B-0963              ', 'JABON BASAH B 130 X 5 X 14.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(1723, 'B-0964              ', 'JABON BASAH B 130 X 5 X 15                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(1724, 'B-0965              ', 'JABON BASAH B 130 X 5 X 15.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(1725, 'B-0966              ', 'JABON BASAH B 130 X 5 X 16                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(1726, 'B-0967              ', 'JABON BASAH ALL 130 X 5 X 5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(1727, 'B-0968              ', 'JABON BASAH ALL 130 X 5 X 5.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(1728, 'B-0969              ', 'JABON BASAH ALL 130 X 5 X 6                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(1729, 'B-0970              ', 'JABON BASAH ALL 130 X 5 X 6.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(1730, 'B-0971              ', 'JABON BASAH ALL 130 X 5 X 7                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(1731, 'B-0972              ', 'JABON BASAH ALL 130 X 5 X 7.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(1732, 'B-0973              ', 'JABON BASAH ALL 130 X 5 X 8                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(1733, 'B-0974              ', 'JABON BASAH ALL 130 X 5 X 8.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(1734, 'B-0975              ', 'JABON BASAH ALL 130 X 5 X 9                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(1735, 'B-0976              ', 'JABON BASAH ALL 130 X 5 X 9.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(1736, 'B-0977              ', 'JABON BASAH ALL 130 X 5 X 10                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(1737, 'B-0978              ', 'JABON BASAH ALL 130 X 5 X 10.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(1738, 'B-0979              ', 'JABON BASAH ALL 130 X 5 X 11                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(1739, 'B-0980              ', 'JABON BASAH ALL 130 X 5 X 11.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(1740, 'B-0981              ', 'JABON BASAH ALL 130 X 5 X 12                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(1741, 'B-0982              ', 'JABON BASAH ALL 130 X 5 X 12.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(1742, 'B-0983              ', 'JABON BASAH ALL 130 X 5 X 13                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(1743, 'B-0984              ', 'JABON BASAH ALL 130 X 5 X 13.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(1744, 'B-0985              ', 'JABON BASAH ALL 130 X 5 X 14                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(1745, 'B-0986              ', 'JABON BASAH ALL 130 X 5 X 14.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(1746, 'B-0987              ', 'JABON BASAH ALL 130 X 5 X 15                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(1747, 'B-0988              ', 'JABON BASAH ALL 130 X 5 X 15.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(1748, 'B-0989              ', 'JABON BASAH ALL 130 X 5 X 16                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(1749, 'B-0990              ', 'JABON BASAH AFB 130 X 5 X 5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(1750, 'B-0991              ', 'JABON BASAH AFB 130 X 5 X 5.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(1751, 'B-0992              ', 'JABON BASAH AFB 130 X 5 X 6                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(1752, 'B-0993              ', 'JABON BASAH AFB 130 X 5 X 6.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(1753, 'B-0994              ', 'JABON BASAH AFB 130 X 5 X 7                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(1754, 'B-0995              ', 'JABON BASAH AFB 130 X 5 X 7.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(1755, 'B-0996              ', 'JABON BASAH AFB 130 X 5 X 8                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(1756, 'B-0997              ', 'JABON BASAH AFB 130 X 5 X 8.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(1757, 'B-0998              ', 'JABON BASAH AFB 130 X 5 X 9                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL);
INSERT INTO `product` (`id`, `product_code`, `product_name`, `product_category_id`, `product_status`, `product_uom_id`, `is_maintain_stock`, `image_path`, `brand`, `barcode`, `description`, `wood_type_id`, `grade_id`, `thickness_id`, `condition_id`, `is_has_serial`, `is_fixed_asset`, `warranty`, `netto`, `netto_uom_id`, `is_purchase_item`, `minor`, `minor_uom_id`, `lead_time`, `buy_cost_center_id`, `expense_acc_id`, `main_supp_code`, `manufacturer`, `is_sales_item`, `is_service_item`, `sell_cost_center_id`, `income_acc_id`, `max_disc`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `minqty`, `thickness`, `length`, `width`, `production_type_id`, `production_quality_id`) VALUES
(1758, 'B-0999              ', 'JABON BASAH AFB 130 X 5 X 9.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(1759, 'B-1000              ', 'JABON BASAH AFB 130 X 5 X 10                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(1760, 'B-1001              ', 'JABON BASAH AFB 130 X 5 X 10.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(1761, 'B-1002              ', 'JABON BASAH AFB 130 X 5 X 11                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(1762, 'B-1003              ', 'JABON BASAH AFB 130 X 5 X 11.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(1763, 'B-1004              ', 'JABON BASAH AFB 130 X 5 X 12                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(1764, 'B-1005              ', 'JABON BASAH AFB 130 X 5 X 12.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(1765, 'B-1006              ', 'JABON BASAH AFB 130 X 5 X 13                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(1766, 'B-1007              ', 'JABON BASAH AFB 130 X 5 X 13.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(1767, 'B-1008              ', 'JABON BASAH AFB 130 X 5 X 14                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(1768, 'B-1009              ', 'JABON BASAH AFB 130 X 5 X 14.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(1769, 'B-1010              ', 'JABON BASAH AFB 130 X 5 X 15                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(1770, 'B-1011              ', 'JABON BASAH AFB 130 X 5 X 15.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(1771, 'B-1012              ', 'JABON BASAH AFB 130 X 5 X 16                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(1772, 'B-1013              ', 'JABON BASAH A 130 X 6 X 5                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(1773, 'B-1014              ', 'JABON BASAH A 130 X 6 X 5.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(1774, 'B-1015              ', 'JABON BASAH A 130 X 6 X 6                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(1775, 'B-1016              ', 'JABON BASAH A 130 X 6 X 6.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(1776, 'B-1017              ', 'JABON BASAH A 130 X 6 X 7                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(1777, 'B-1018              ', 'JABON BASAH A 130 X 6 X 7.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(1778, 'B-1019              ', 'JABON BASAH A 130 X 6 X 8                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(1779, 'B-1020              ', 'JABON BASAH A 130 X 6 X 8.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(1780, 'B-1021              ', 'JABON BASAH A 130 X 6 X 9                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(1781, 'B-1022              ', 'JABON BASAH A 130 X 6 X 9.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(1782, 'B-1023              ', 'JABON BASAH A 130 X 6 X 10                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(1783, 'B-1024              ', 'JABON BASAH A 130 X 6 X 10.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(1784, 'B-1025              ', 'JABON BASAH A 130 X 6 X 11                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(1785, 'B-1026              ', 'JABON BASAH A 130 X 6 X 11.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(1786, 'B-1027              ', 'JABON BASAH A 130 X 6 X 12                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(1787, 'B-1028              ', 'JABON BASAH A 130 X 6 X 12.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(1788, 'B-1029              ', 'JABON BASAH A 130 X 6 X 13                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(1789, 'B-1030              ', 'JABON BASAH A 130 X 6 X 13.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(1790, 'B-1031              ', 'JABON BASAH A 130 X 6 X 14                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(1791, 'B-1032              ', 'JABON BASAH A 130 X 6 X 14.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(1792, 'B-1033              ', 'JABON BASAH A 130 X 6 X 15                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(1793, 'B-1034              ', 'JABON BASAH A 130 X 6 X 15.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(1794, 'B-1035              ', 'JABON BASAH A 130 X 6 X 16                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(1795, 'B-1036              ', 'JABON BASAH B 130 X 6 X 5                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(1796, 'B-1037              ', 'JABON BASAH B 130 X 6 X 5.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(1797, 'B-1038              ', 'JABON BASAH B 130 X 6 X 6                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(1798, 'B-1039              ', 'JABON BASAH B 130 X 6 X 6.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(1799, 'B-1040              ', 'JABON BASAH B 130 X 6 X 7                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(1800, 'B-1041              ', 'JABON BASAH B 130 X 6 X 7.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(1801, 'B-1042              ', 'JABON BASAH B 130 X 6 X 8                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(1802, 'B-1043              ', 'JABON BASAH B 130 X 6 X 8.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(1803, 'B-1044              ', 'JABON BASAH B 130 X 6 X 9                         ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(1804, 'B-1045              ', 'JABON BASAH B 130 X 6 X 9.5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(1805, 'B-1046              ', 'JABON BASAH B 130 X 6 X 10                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(1806, 'B-1047              ', 'JABON BASAH B 130 X 6 X 10.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(1807, 'B-1048              ', 'JABON BASAH B 130 X 6 X 11                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(1808, 'B-1049              ', 'JABON BASAH B 130 X 6 X 11.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(1809, 'B-1050              ', 'JABON BASAH B 130 X 6 X 12                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(1810, 'B-1051              ', 'JABON BASAH B 130 X 6 X 12.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(1811, 'B-1052              ', 'JABON BASAH B 130 X 6 X 13                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(1812, 'B-1053              ', 'JABON BASAH B 130 X 6 X 13.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(1813, 'B-1054              ', 'JABON BASAH B 130 X 6 X 14                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(1814, 'B-1055              ', 'JABON BASAH B 130 X 6 X 14.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(1815, 'B-1056              ', 'JABON BASAH B 130 X 6 X 15                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(1816, 'B-1057              ', 'JABON BASAH B 130 X 6 X 15.5                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(1817, 'B-1058              ', 'JABON BASAH B 130 X 6 X 16                        ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 2, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(1818, 'B-1059              ', 'JABON BASAH ALL 130 X 6 X 5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(1819, 'B-1060              ', 'JABON BASAH ALL 130 X 6 X 5.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(1820, 'B-1061              ', 'JABON BASAH ALL 130 X 6 X 6                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(1821, 'B-1062              ', 'JABON BASAH ALL 130 X 6 X 6.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(1822, 'B-1063              ', 'JABON BASAH ALL 130 X 6 X 7                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(1823, 'B-1064              ', 'JABON BASAH ALL 130 X 6 X 7.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(1824, 'B-1065              ', 'JABON BASAH ALL 130 X 6 X 8                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(1825, 'B-1066              ', 'JABON BASAH ALL 130 X 6 X 8.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(1826, 'B-1067              ', 'JABON BASAH ALL 130 X 6 X 9                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(1827, 'B-1068              ', 'JABON BASAH ALL 130 X 6 X 9.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(1828, 'B-1069              ', 'JABON BASAH ALL 130 X 6 X 10                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(1829, 'B-1070              ', 'JABON BASAH ALL 130 X 6 X 10.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(1830, 'B-1071              ', 'JABON BASAH ALL 130 X 6 X 11                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(1831, 'B-1072              ', 'JABON BASAH ALL 130 X 6 X 11.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(1832, 'B-1073              ', 'JABON BASAH ALL 130 X 6 X 12                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(1833, 'B-1074              ', 'JABON BASAH ALL 130 X 6 X 12.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(1834, 'B-1075              ', 'JABON BASAH ALL 130 X 6 X 13                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(1835, 'B-1076              ', 'JABON BASAH ALL 130 X 6 X 13.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(1836, 'B-1077              ', 'JABON BASAH ALL 130 X 6 X 14                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(1837, 'B-1078              ', 'JABON BASAH ALL 130 X 6 X 14.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(1838, 'B-1079              ', 'JABON BASAH ALL 130 X 6 X 15                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(1839, 'B-1080              ', 'JABON BASAH ALL 130 X 6 X 15.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(1840, 'B-1081              ', 'JABON BASAH ALL 130 X 6 X 16                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 3, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(1841, 'B-1082              ', 'JABON BASAH AFB 130 X 6 X 5                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(1842, 'B-1083              ', 'JABON BASAH AFB 130 X 6 X 5.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(1843, 'B-1084              ', 'JABON BASAH AFB 130 X 6 X 6                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(1844, 'B-1085              ', 'JABON BASAH AFB 130 X 6 X 6.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(1845, 'B-1086              ', 'JABON BASAH AFB 130 X 6 X 7                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(1846, 'B-1087              ', 'JABON BASAH AFB 130 X 6 X 7.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(1847, 'B-1088              ', 'JABON BASAH AFB 130 X 6 X 8                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(1848, 'B-1089              ', 'JABON BASAH AFB 130 X 6 X 8.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(1849, 'B-1090              ', 'JABON BASAH AFB 130 X 6 X 9                       ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(1850, 'B-1091              ', 'JABON BASAH AFB 130 X 6 X 9.5                     ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(1851, 'B-1092              ', 'JABON BASAH AFB 130 X 6 X 10                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(1852, 'B-1093              ', 'JABON BASAH AFB 130 X 6 X 10.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(1853, 'B-1094              ', 'JABON BASAH AFB 130 X 6 X 11                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(1854, 'B-1095              ', 'JABON BASAH AFB 130 X 6 X 11.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(1855, 'B-1096              ', 'JABON BASAH AFB 130 X 6 X 12                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(1856, 'B-1097              ', 'JABON BASAH AFB 130 X 6 X 12.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(1857, 'B-1098              ', 'JABON BASAH AFB 130 X 6 X 13                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(1858, 'B-1099              ', 'JABON BASAH AFB 130 X 6 X 13.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(1859, 'B-1100              ', 'JABON BASAH AFB 130 X 6 X 14                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(1860, 'B-1101              ', 'JABON BASAH AFB 130 X 6 X 14.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(1861, 'B-1102              ', 'JABON BASAH AFB 130 X 6 X 15                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(1862, 'B-1103              ', 'JABON BASAH AFB 130 X 6 X 15.5                    ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(1863, 'B-1104              ', 'JABON BASAH AFB 130 X 6 X 16                      ', 1, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 4, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(1864, 'K-0737              ', 'JABON KERING A 100 X 5 X 5                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(1865, 'K-0738              ', 'JABON KERING A 100 X 5 X 5.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(1866, 'K-0739              ', 'JABON KERING A 100 X 5 X 6                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(1867, 'K-0740              ', 'JABON KERING A 100 X 5 X 6.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(1868, 'K-0741              ', 'JABON KERING A 100 X 5 X 7                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(1869, 'K-0742              ', 'JABON KERING A 100 X 5 X 7.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(1870, 'K-0743              ', 'JABON KERING A 100 X 5 X 8                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(1871, 'K-0744              ', 'JABON KERING A 100 X 5 X 8.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(1872, 'K-0745              ', 'JABON KERING A 100 X 5 X 9                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(1873, 'K-0746              ', 'JABON KERING A 100 X 5 X 9.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(1874, 'K-0747              ', 'JABON KERING A 100 X 5 X 10                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(1875, 'K-0748              ', 'JABON KERING A 100 X 5 X 10.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(1876, 'K-0749              ', 'JABON KERING A 100 X 5 X 11                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(1877, 'K-0750              ', 'JABON KERING A 100 X 5 X 11.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(1878, 'K-0751              ', 'JABON KERING A 100 X 5 X 12                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(1879, 'K-0752              ', 'JABON KERING A 100 X 5 X 12.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(1880, 'K-0753              ', 'JABON KERING A 100 X 5 X 13                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(1881, 'K-0754              ', 'JABON KERING A 100 X 5 X 13.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(1882, 'K-0755              ', 'JABON KERING A 100 X 5 X 14                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(1883, 'K-0756              ', 'JABON KERING A 100 X 5 X 14.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(1884, 'K-0757              ', 'JABON KERING A 100 X 5 X 15                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(1885, 'K-0758              ', 'JABON KERING A 100 X 5 X 15.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(1886, 'K-0759              ', 'JABON KERING A 100 X 5 X 16                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(1887, 'K-0760              ', 'JABON KERING B 100 X 5 X 5                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(1888, 'K-0761              ', 'JABON KERING B 100 X 5 X 5.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(1889, 'K-0762              ', 'JABON KERING B 100 X 5 X 6                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(1890, 'K-0763              ', 'JABON KERING B 100 X 5 X 6.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(1891, 'K-0764              ', 'JABON KERING B 100 X 5 X 7                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(1892, 'K-0765              ', 'JABON KERING B 100 X 5 X 7.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(1893, 'K-0766              ', 'JABON KERING B 100 X 5 X 8                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(1894, 'K-0767              ', 'JABON KERING B 100 X 5 X 8.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(1895, 'K-0768              ', 'JABON KERING B 100 X 5 X 9                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(1896, 'K-0769              ', 'JABON KERING B 100 X 5 X 9.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(1897, 'K-0770              ', 'JABON KERING B 100 X 5 X 10                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(1898, 'K-0771              ', 'JABON KERING B 100 X 5 X 10.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(1899, 'K-0772              ', 'JABON KERING B 100 X 5 X 11                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(1900, 'K-0773              ', 'JABON KERING B 100 X 5 X 11.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(1901, 'K-0774              ', 'JABON KERING B 100 X 5 X 12                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(1902, 'K-0775              ', 'JABON KERING B 100 X 5 X 12.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL);
INSERT INTO `product` (`id`, `product_code`, `product_name`, `product_category_id`, `product_status`, `product_uom_id`, `is_maintain_stock`, `image_path`, `brand`, `barcode`, `description`, `wood_type_id`, `grade_id`, `thickness_id`, `condition_id`, `is_has_serial`, `is_fixed_asset`, `warranty`, `netto`, `netto_uom_id`, `is_purchase_item`, `minor`, `minor_uom_id`, `lead_time`, `buy_cost_center_id`, `expense_acc_id`, `main_supp_code`, `manufacturer`, `is_sales_item`, `is_service_item`, `sell_cost_center_id`, `income_acc_id`, `max_disc`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `minqty`, `thickness`, `length`, `width`, `production_type_id`, `production_quality_id`) VALUES
(1903, 'K-0776              ', 'JABON KERING B 100 X 5 X 13                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(1904, 'K-0777              ', 'JABON KERING B 100 X 5 X 13.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(1905, 'K-0778              ', 'JABON KERING B 100 X 5 X 14                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(1906, 'K-0779              ', 'JABON KERING B 100 X 5 X 14.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(1907, 'K-0780              ', 'JABON KERING B 100 X 5 X 15                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(1908, 'K-0781              ', 'JABON KERING B 100 X 5 X 15.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(1909, 'K-0782              ', 'JABON KERING B 100 X 5 X 16                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(1910, 'K-0783              ', 'JABON KERING ALL 100 X 5 X 5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(1911, 'K-0784              ', 'JABON KERING ALL 100 X 5 X 5.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(1912, 'K-0785              ', 'JABON KERING ALL 100 X 5 X 6                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(1913, 'K-0786              ', 'JABON KERING ALL 100 X 5 X 6.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(1914, 'K-0787              ', 'JABON KERING ALL 100 X 5 X 7                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(1915, 'K-0788              ', 'JABON KERING ALL 100 X 5 X 7.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(1916, 'K-0789              ', 'JABON KERING ALL 100 X 5 X 8                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(1917, 'K-0790              ', 'JABON KERING ALL 100 X 5 X 8.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(1918, 'K-0791              ', 'JABON KERING ALL 100 X 5 X 9                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(1919, 'K-0792              ', 'JABON KERING ALL 100 X 5 X 9.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(1920, 'K-0793              ', 'JABON KERING ALL 100 X 5 X 10                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(1921, 'K-0794              ', 'JABON KERING ALL 100 X 5 X 10.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(1922, 'K-0795              ', 'JABON KERING ALL 100 X 5 X 11                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(1923, 'K-0796              ', 'JABON KERING ALL 100 X 5 X 11.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(1924, 'K-0797              ', 'JABON KERING ALL 100 X 5 X 12                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(1925, 'K-0798              ', 'JABON KERING ALL 100 X 5 X 12.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(1926, 'K-0799              ', 'JABON KERING ALL 100 X 5 X 13                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(1927, 'K-0800              ', 'JABON KERING ALL 100 X 5 X 13.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(1928, 'K-0801              ', 'JABON KERING ALL 100 X 5 X 14                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(1929, 'K-0802              ', 'JABON KERING ALL 100 X 5 X 14.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(1930, 'K-0803              ', 'JABON KERING ALL 100 X 5 X 15                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(1931, 'K-0804              ', 'JABON KERING ALL 100 X 5 X 15.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(1932, 'K-0805              ', 'JABON KERING ALL 100 X 5 X 16                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(1933, 'K-0806              ', 'JABON KERING AFB 100 X 5 X 5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.0000', NULL, NULL),
(1934, 'K-0807              ', 'JABON KERING AFB 100 X 5 X 5.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '5.5000', NULL, NULL),
(1935, 'K-0808              ', 'JABON KERING AFB 100 X 5 X 6                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.0000', NULL, NULL),
(1936, 'K-0809              ', 'JABON KERING AFB 100 X 5 X 6.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '6.5000', NULL, NULL),
(1937, 'K-0810              ', 'JABON KERING AFB 100 X 5 X 7                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.0000', NULL, NULL),
(1938, 'K-0811              ', 'JABON KERING AFB 100 X 5 X 7.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '7.5000', NULL, NULL),
(1939, 'K-0812              ', 'JABON KERING AFB 100 X 5 X 8                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.0000', NULL, NULL),
(1940, 'K-0813              ', 'JABON KERING AFB 100 X 5 X 8.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '8.5000', NULL, NULL),
(1941, 'K-0814              ', 'JABON KERING AFB 100 X 5 X 9                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.0000', NULL, NULL),
(1942, 'K-0815              ', 'JABON KERING AFB 100 X 5 X 9.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '9.5000', NULL, NULL),
(1943, 'K-0816              ', 'JABON KERING AFB 100 X 5 X 10                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.0000', NULL, NULL),
(1944, 'K-0817              ', 'JABON KERING AFB 100 X 5 X 10.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '10.5000', NULL, NULL),
(1945, 'K-0818              ', 'JABON KERING AFB 100 X 5 X 11                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.0000', NULL, NULL),
(1946, 'K-0819              ', 'JABON KERING AFB 100 X 5 X 11.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '11.5000', NULL, NULL),
(1947, 'K-0820              ', 'JABON KERING AFB 100 X 5 X 12                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.0000', NULL, NULL),
(1948, 'K-0821              ', 'JABON KERING AFB 100 X 5 X 12.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '12.5000', NULL, NULL),
(1949, 'K-0822              ', 'JABON KERING AFB 100 X 5 X 13                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.0000', NULL, NULL),
(1950, 'K-0823              ', 'JABON KERING AFB 100 X 5 X 13.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '13.5000', NULL, NULL),
(1951, 'K-0824              ', 'JABON KERING AFB 100 X 5 X 14                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.0000', NULL, NULL),
(1952, 'K-0825              ', 'JABON KERING AFB 100 X 5 X 14.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '14.5000', NULL, NULL),
(1953, 'K-0826              ', 'JABON KERING AFB 100 X 5 X 15                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.0000', NULL, NULL),
(1954, 'K-0827              ', 'JABON KERING AFB 100 X 5 X 15.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '15.5000', NULL, NULL),
(1955, 'K-0828              ', 'JABON KERING AFB 100 X 5 X 16                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '100.0000', '16.0000', NULL, NULL),
(1956, 'K-0829              ', 'JABON KERING A 100 X 6 X 5                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(1957, 'K-0830              ', 'JABON KERING A 100 X 6 X 5.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(1958, 'K-0831              ', 'JABON KERING A 100 X 6 X 6                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(1959, 'K-0832              ', 'JABON KERING A 100 X 6 X 6.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(1960, 'K-0833              ', 'JABON KERING A 100 X 6 X 7                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(1961, 'K-0834              ', 'JABON KERING A 100 X 6 X 7.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(1962, 'K-0835              ', 'JABON KERING A 100 X 6 X 8                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(1963, 'K-0836              ', 'JABON KERING A 100 X 6 X 8.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(1964, 'K-0837              ', 'JABON KERING A 100 X 6 X 9                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(1965, 'K-0838              ', 'JABON KERING A 100 X 6 X 9.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(1966, 'K-0839              ', 'JABON KERING A 100 X 6 X 10                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(1967, 'K-0840              ', 'JABON KERING A 100 X 6 X 10.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(1968, 'K-0841              ', 'JABON KERING A 100 X 6 X 11                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(1969, 'K-0842              ', 'JABON KERING A 100 X 6 X 11.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(1970, 'K-0843              ', 'JABON KERING A 100 X 6 X 12                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(1971, 'K-0844              ', 'JABON KERING A 100 X 6 X 12.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(1972, 'K-0845              ', 'JABON KERING A 100 X 6 X 13                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(1973, 'K-0846              ', 'JABON KERING A 100 X 6 X 13.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(1974, 'K-0847              ', 'JABON KERING A 100 X 6 X 14                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(1975, 'K-0848              ', 'JABON KERING A 100 X 6 X 14.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(1976, 'K-0849              ', 'JABON KERING A 100 X 6 X 15                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(1977, 'K-0850              ', 'JABON KERING A 100 X 6 X 15.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(1978, 'K-0851              ', 'JABON KERING A 100 X 6 X 16                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(1979, 'K-0852              ', 'JABON KERING B 100 X 6 X 5                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(1980, 'K-0853              ', 'JABON KERING B 100 X 6 X 5.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(1981, 'K-0854              ', 'JABON KERING B 100 X 6 X 6                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(1982, 'K-0855              ', 'JABON KERING B 100 X 6 X 6.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(1983, 'K-0856              ', 'JABON KERING B 100 X 6 X 7                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(1984, 'K-0857              ', 'JABON KERING B 100 X 6 X 7.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(1985, 'K-0858              ', 'JABON KERING B 100 X 6 X 8                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(1986, 'K-0859              ', 'JABON KERING B 100 X 6 X 8.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(1987, 'K-0860              ', 'JABON KERING B 100 X 6 X 9                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(1988, 'K-0861              ', 'JABON KERING B 100 X 6 X 9.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(1989, 'K-0862              ', 'JABON KERING B 100 X 6 X 10                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(1990, 'K-0863              ', 'JABON KERING B 100 X 6 X 10.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(1991, 'K-0864              ', 'JABON KERING B 100 X 6 X 11                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(1992, 'K-0865              ', 'JABON KERING B 100 X 6 X 11.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(1993, 'K-0866              ', 'JABON KERING B 100 X 6 X 12                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(1994, 'K-0867              ', 'JABON KERING B 100 X 6 X 12.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(1995, 'K-0868              ', 'JABON KERING B 100 X 6 X 13                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(1996, 'K-0869              ', 'JABON KERING B 100 X 6 X 13.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(1997, 'K-0870              ', 'JABON KERING B 100 X 6 X 14                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(1998, 'K-0871              ', 'JABON KERING B 100 X 6 X 14.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(1999, 'K-0872              ', 'JABON KERING B 100 X 6 X 15                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(2000, 'K-0873              ', 'JABON KERING B 100 X 6 X 15.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(2001, 'K-0874              ', 'JABON KERING B 100 X 6 X 16                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(2002, 'K-0875              ', 'JABON KERING ALL 100 X 6 X 5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(2003, 'K-0876              ', 'JABON KERING ALL 100 X 6 X 5.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(2004, 'K-0877              ', 'JABON KERING ALL 100 X 6 X 6                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(2005, 'K-0878              ', 'JABON KERING ALL 100 X 6 X 6.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(2006, 'K-0879              ', 'JABON KERING ALL 100 X 6 X 7                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(2007, 'K-0880              ', 'JABON KERING ALL 100 X 6 X 7.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(2008, 'K-0881              ', 'JABON KERING ALL 100 X 6 X 8                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(2009, 'K-0882              ', 'JABON KERING ALL 100 X 6 X 8.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(2010, 'K-0883              ', 'JABON KERING ALL 100 X 6 X 9                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(2011, 'K-0884              ', 'JABON KERING ALL 100 X 6 X 9.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(2012, 'K-0885              ', 'JABON KERING ALL 100 X 6 X 10                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(2013, 'K-0886              ', 'JABON KERING ALL 100 X 6 X 10.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(2014, 'K-0887              ', 'JABON KERING ALL 100 X 6 X 11                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(2015, 'K-0888              ', 'JABON KERING ALL 100 X 6 X 11.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(2016, 'K-0889              ', 'JABON KERING ALL 100 X 6 X 12                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(2017, 'K-0890              ', 'JABON KERING ALL 100 X 6 X 12.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(2018, 'K-0891              ', 'JABON KERING ALL 100 X 6 X 13                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(2019, 'K-0892              ', 'JABON KERING ALL 100 X 6 X 13.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(2020, 'K-0893              ', 'JABON KERING ALL 100 X 6 X 14                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(2021, 'K-0894              ', 'JABON KERING ALL 100 X 6 X 14.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(2022, 'K-0895              ', 'JABON KERING ALL 100 X 6 X 15                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(2023, 'K-0896              ', 'JABON KERING ALL 100 X 6 X 15.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(2024, 'K-0897              ', 'JABON KERING ALL 100 X 6 X 16                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL),
(2025, 'K-0898              ', 'JABON KERING AFB 100 X 6 X 5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.0000', NULL, NULL),
(2026, 'K-0899              ', 'JABON KERING AFB 100 X 6 X 5.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '5.5000', NULL, NULL),
(2027, 'K-0900              ', 'JABON KERING AFB 100 X 6 X 6                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.0000', NULL, NULL),
(2028, 'K-0901              ', 'JABON KERING AFB 100 X 6 X 6.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '6.5000', NULL, NULL),
(2029, 'K-0902              ', 'JABON KERING AFB 100 X 6 X 7                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.0000', NULL, NULL),
(2030, 'K-0903              ', 'JABON KERING AFB 100 X 6 X 7.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '7.5000', NULL, NULL),
(2031, 'K-0904              ', 'JABON KERING AFB 100 X 6 X 8                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.0000', NULL, NULL),
(2032, 'K-0905              ', 'JABON KERING AFB 100 X 6 X 8.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '8.5000', NULL, NULL),
(2033, 'K-0906              ', 'JABON KERING AFB 100 X 6 X 9                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.0000', NULL, NULL),
(2034, 'K-0907              ', 'JABON KERING AFB 100 X 6 X 9.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '9.5000', NULL, NULL),
(2035, 'K-0908              ', 'JABON KERING AFB 100 X 6 X 10                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.0000', NULL, NULL),
(2036, 'K-0909              ', 'JABON KERING AFB 100 X 6 X 10.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '10.5000', NULL, NULL),
(2037, 'K-0910              ', 'JABON KERING AFB 100 X 6 X 11                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.0000', NULL, NULL),
(2038, 'K-0911              ', 'JABON KERING AFB 100 X 6 X 11.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '11.5000', NULL, NULL),
(2039, 'K-0912              ', 'JABON KERING AFB 100 X 6 X 12                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.0000', NULL, NULL),
(2040, 'K-0913              ', 'JABON KERING AFB 100 X 6 X 12.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '12.5000', NULL, NULL),
(2041, 'K-0914              ', 'JABON KERING AFB 100 X 6 X 13                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.0000', NULL, NULL),
(2042, 'K-0915              ', 'JABON KERING AFB 100 X 6 X 13.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '13.5000', NULL, NULL),
(2043, 'K-0916              ', 'JABON KERING AFB 100 X 6 X 14                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.0000', NULL, NULL),
(2044, 'K-0917              ', 'JABON KERING AFB 100 X 6 X 14.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '14.5000', NULL, NULL),
(2045, 'K-0918              ', 'JABON KERING AFB 100 X 6 X 15                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.0000', NULL, NULL),
(2046, 'K-0919              ', 'JABON KERING AFB 100 X 6 X 15.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '15.5000', NULL, NULL),
(2047, 'K-0920              ', 'JABON KERING AFB 100 X 6 X 16                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '100.0000', '16.0000', NULL, NULL);
INSERT INTO `product` (`id`, `product_code`, `product_name`, `product_category_id`, `product_status`, `product_uom_id`, `is_maintain_stock`, `image_path`, `brand`, `barcode`, `description`, `wood_type_id`, `grade_id`, `thickness_id`, `condition_id`, `is_has_serial`, `is_fixed_asset`, `warranty`, `netto`, `netto_uom_id`, `is_purchase_item`, `minor`, `minor_uom_id`, `lead_time`, `buy_cost_center_id`, `expense_acc_id`, `main_supp_code`, `manufacturer`, `is_sales_item`, `is_service_item`, `sell_cost_center_id`, `income_acc_id`, `max_disc`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `minqty`, `thickness`, `length`, `width`, `production_type_id`, `production_quality_id`) VALUES
(2048, 'K-0921              ', 'JABON KERING A 130 X 5 X 5                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(2049, 'K-0922              ', 'JABON KERING A 130 X 5 X 5.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(2050, 'K-0923              ', 'JABON KERING A 130 X 5 X 6                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(2051, 'K-0924              ', 'JABON KERING A 130 X 5 X 6.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(2052, 'K-0925              ', 'JABON KERING A 130 X 5 X 7                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(2053, 'K-0926              ', 'JABON KERING A 130 X 5 X 7.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(2054, 'K-0927              ', 'JABON KERING A 130 X 5 X 8                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(2055, 'K-0928              ', 'JABON KERING A 130 X 5 X 8.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(2056, 'K-0929              ', 'JABON KERING A 130 X 5 X 9                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(2057, 'K-0930              ', 'JABON KERING A 130 X 5 X 9.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(2058, 'K-0931              ', 'JABON KERING A 130 X 5 X 10                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(2059, 'K-0932              ', 'JABON KERING A 130 X 5 X 10.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(2060, 'K-0933              ', 'JABON KERING A 130 X 5 X 11                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(2061, 'K-0934              ', 'JABON KERING A 130 X 5 X 11.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(2062, 'K-0935              ', 'JABON KERING A 130 X 5 X 12                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(2063, 'K-0936              ', 'JABON KERING A 130 X 5 X 12.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(2064, 'K-0937              ', 'JABON KERING A 130 X 5 X 13                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(2065, 'K-0938              ', 'JABON KERING A 130 X 5 X 13.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(2066, 'K-0939              ', 'JABON KERING A 130 X 5 X 14                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(2067, 'K-0940              ', 'JABON KERING A 130 X 5 X 14.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(2068, 'K-0941              ', 'JABON KERING A 130 X 5 X 15                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(2069, 'K-0942              ', 'JABON KERING A 130 X 5 X 15.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(2070, 'K-0943              ', 'JABON KERING A 130 X 5 X 16                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(2071, 'K-0944              ', 'JABON KERING B 130 X 5 X 5                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(2072, 'K-0945              ', 'JABON KERING B 130 X 5 X 5.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(2073, 'K-0946              ', 'JABON KERING B 130 X 5 X 6                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(2074, 'K-0947              ', 'JABON KERING B 130 X 5 X 6.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(2075, 'K-0948              ', 'JABON KERING B 130 X 5 X 7                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(2076, 'K-0949              ', 'JABON KERING B 130 X 5 X 7.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(2077, 'K-0950              ', 'JABON KERING B 130 X 5 X 8                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(2078, 'K-0951              ', 'JABON KERING B 130 X 5 X 8.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(2079, 'K-0952              ', 'JABON KERING B 130 X 5 X 9                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(2080, 'K-0953              ', 'JABON KERING B 130 X 5 X 9.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(2081, 'K-0954              ', 'JABON KERING B 130 X 5 X 10                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(2082, 'K-0955              ', 'JABON KERING B 130 X 5 X 10.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(2083, 'K-0956              ', 'JABON KERING B 130 X 5 X 11                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(2084, 'K-0957              ', 'JABON KERING B 130 X 5 X 11.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(2085, 'K-0958              ', 'JABON KERING B 130 X 5 X 12                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(2086, 'K-0959              ', 'JABON KERING B 130 X 5 X 12.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(2087, 'K-0960              ', 'JABON KERING B 130 X 5 X 13                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(2088, 'K-0961              ', 'JABON KERING B 130 X 5 X 13.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(2089, 'K-0962              ', 'JABON KERING B 130 X 5 X 14                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(2090, 'K-0963              ', 'JABON KERING B 130 X 5 X 14.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(2091, 'K-0964              ', 'JABON KERING B 130 X 5 X 15                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(2092, 'K-0965              ', 'JABON KERING B 130 X 5 X 15.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(2093, 'K-0966              ', 'JABON KERING B 130 X 5 X 16                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(2094, 'K-0967              ', 'JABON KERING ALL 130 X 5 X 5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(2095, 'K-0968              ', 'JABON KERING ALL 130 X 5 X 5.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(2096, 'K-0969              ', 'JABON KERING ALL 130 X 5 X 6                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(2097, 'K-0970              ', 'JABON KERING ALL 130 X 5 X 6.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(2098, 'K-0971              ', 'JABON KERING ALL 130 X 5 X 7                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(2099, 'K-0972              ', 'JABON KERING ALL 130 X 5 X 7.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(2100, 'K-0973              ', 'JABON KERING ALL 130 X 5 X 8                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(2101, 'K-0974              ', 'JABON KERING ALL 130 X 5 X 8.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(2102, 'K-0975              ', 'JABON KERING ALL 130 X 5 X 9                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(2103, 'K-0976              ', 'JABON KERING ALL 130 X 5 X 9.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(2104, 'K-0977              ', 'JABON KERING ALL 130 X 5 X 10                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(2105, 'K-0978              ', 'JABON KERING ALL 130 X 5 X 10.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(2106, 'K-0979              ', 'JABON KERING ALL 130 X 5 X 11                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(2107, 'K-0980              ', 'JABON KERING ALL 130 X 5 X 11.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(2108, 'K-0981              ', 'JABON KERING ALL 130 X 5 X 12                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(2109, 'K-0982              ', 'JABON KERING ALL 130 X 5 X 12.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(2110, 'K-0983              ', 'JABON KERING ALL 130 X 5 X 13                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(2111, 'K-0984              ', 'JABON KERING ALL 130 X 5 X 13.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(2112, 'K-0985              ', 'JABON KERING ALL 130 X 5 X 14                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(2113, 'K-0986              ', 'JABON KERING ALL 130 X 5 X 14.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(2114, 'K-0987              ', 'JABON KERING ALL 130 X 5 X 15                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(2115, 'K-0988              ', 'JABON KERING ALL 130 X 5 X 15.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(2116, 'K-0989              ', 'JABON KERING ALL 130 X 5 X 16                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(2117, 'K-0990              ', 'JABON KERING AFB 130 X 5 X 5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.0000', NULL, NULL),
(2118, 'K-0991              ', 'JABON KERING AFB 130 X 5 X 5.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '5.5000', NULL, NULL),
(2119, 'K-0992              ', 'JABON KERING AFB 130 X 5 X 6                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.0000', NULL, NULL),
(2120, 'K-0993              ', 'JABON KERING AFB 130 X 5 X 6.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '6.5000', NULL, NULL),
(2121, 'K-0994              ', 'JABON KERING AFB 130 X 5 X 7                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.0000', NULL, NULL),
(2122, 'K-0995              ', 'JABON KERING AFB 130 X 5 X 7.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '7.5000', NULL, NULL),
(2123, 'K-0996              ', 'JABON KERING AFB 130 X 5 X 8                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.0000', NULL, NULL),
(2124, 'K-0997              ', 'JABON KERING AFB 130 X 5 X 8.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '8.5000', NULL, NULL),
(2125, 'K-0998              ', 'JABON KERING AFB 130 X 5 X 9                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.0000', NULL, NULL),
(2126, 'K-0999              ', 'JABON KERING AFB 130 X 5 X 9.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '9.5000', NULL, NULL),
(2127, 'K-1000              ', 'JABON KERING AFB 130 X 5 X 10                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.0000', NULL, NULL),
(2128, 'K-1001              ', 'JABON KERING AFB 130 X 5 X 10.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '10.5000', NULL, NULL),
(2129, 'K-1002              ', 'JABON KERING AFB 130 X 5 X 11                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.0000', NULL, NULL),
(2130, 'K-1003              ', 'JABON KERING AFB 130 X 5 X 11.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '11.5000', NULL, NULL),
(2131, 'K-1004              ', 'JABON KERING AFB 130 X 5 X 12                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.0000', NULL, NULL),
(2132, 'K-1005              ', 'JABON KERING AFB 130 X 5 X 12.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '12.5000', NULL, NULL),
(2133, 'K-1006              ', 'JABON KERING AFB 130 X 5 X 13                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.0000', NULL, NULL),
(2134, 'K-1007              ', 'JABON KERING AFB 130 X 5 X 13.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '13.5000', NULL, NULL),
(2135, 'K-1008              ', 'JABON KERING AFB 130 X 5 X 14                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.0000', NULL, NULL),
(2136, 'K-1009              ', 'JABON KERING AFB 130 X 5 X 14.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '14.5000', NULL, NULL),
(2137, 'K-1010              ', 'JABON KERING AFB 130 X 5 X 15                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.0000', NULL, NULL),
(2138, 'K-1011              ', 'JABON KERING AFB 130 X 5 X 15.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '15.5000', NULL, NULL),
(2139, 'K-1012              ', 'JABON KERING AFB 130 X 5 X 16                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '5.0000', '130.0000', '16.0000', NULL, NULL),
(2140, 'K-1013              ', 'JABON KERING A 130 X 6 X 5                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(2141, 'K-1014              ', 'JABON KERING A 130 X 6 X 5.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(2142, 'K-1015              ', 'JABON KERING A 130 X 6 X 6                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(2143, 'K-1016              ', 'JABON KERING A 130 X 6 X 6.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(2144, 'K-1017              ', 'JABON KERING A 130 X 6 X 7                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(2145, 'K-1018              ', 'JABON KERING A 130 X 6 X 7.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(2146, 'K-1019              ', 'JABON KERING A 130 X 6 X 8                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(2147, 'K-1020              ', 'JABON KERING A 130 X 6 X 8.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(2148, 'K-1021              ', 'JABON KERING A 130 X 6 X 9                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(2149, 'K-1022              ', 'JABON KERING A 130 X 6 X 9.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(2150, 'K-1023              ', 'JABON KERING A 130 X 6 X 10                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(2151, 'K-1024              ', 'JABON KERING A 130 X 6 X 10.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(2152, 'K-1025              ', 'JABON KERING A 130 X 6 X 11                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(2153, 'K-1026              ', 'JABON KERING A 130 X 6 X 11.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(2154, 'K-1027              ', 'JABON KERING A 130 X 6 X 12                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(2155, 'K-1028              ', 'JABON KERING A 130 X 6 X 12.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(2156, 'K-1029              ', 'JABON KERING A 130 X 6 X 13                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(2157, 'K-1030              ', 'JABON KERING A 130 X 6 X 13.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(2158, 'K-1031              ', 'JABON KERING A 130 X 6 X 14                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(2159, 'K-1032              ', 'JABON KERING A 130 X 6 X 14.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(2160, 'K-1033              ', 'JABON KERING A 130 X 6 X 15                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(2161, 'K-1034              ', 'JABON KERING A 130 X 6 X 15.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(2162, 'K-1035              ', 'JABON KERING A 130 X 6 X 16                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 5, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(2163, 'K-1036              ', 'JABON KERING B 130 X 6 X 5                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(2164, 'K-1037              ', 'JABON KERING B 130 X 6 X 5.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(2165, 'K-1038              ', 'JABON KERING B 130 X 6 X 6                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(2166, 'K-1039              ', 'JABON KERING B 130 X 6 X 6.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(2167, 'K-1040              ', 'JABON KERING B 130 X 6 X 7                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(2168, 'K-1041              ', 'JABON KERING B 130 X 6 X 7.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(2169, 'K-1042              ', 'JABON KERING B 130 X 6 X 8                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(2170, 'K-1043              ', 'JABON KERING B 130 X 6 X 8.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(2171, 'K-1044              ', 'JABON KERING B 130 X 6 X 9                        ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(2172, 'K-1045              ', 'JABON KERING B 130 X 6 X 9.5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(2173, 'K-1046              ', 'JABON KERING B 130 X 6 X 10                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(2174, 'K-1047              ', 'JABON KERING B 130 X 6 X 10.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(2175, 'K-1048              ', 'JABON KERING B 130 X 6 X 11                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(2176, 'K-1049              ', 'JABON KERING B 130 X 6 X 11.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(2177, 'K-1050              ', 'JABON KERING B 130 X 6 X 12                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(2178, 'K-1051              ', 'JABON KERING B 130 X 6 X 12.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(2179, 'K-1052              ', 'JABON KERING B 130 X 6 X 13                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(2180, 'K-1053              ', 'JABON KERING B 130 X 6 X 13.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(2181, 'K-1054              ', 'JABON KERING B 130 X 6 X 14                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(2182, 'K-1055              ', 'JABON KERING B 130 X 6 X 14.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(2183, 'K-1056              ', 'JABON KERING B 130 X 6 X 15                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(2184, 'K-1057              ', 'JABON KERING B 130 X 6 X 15.5                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(2185, 'K-1058              ', 'JABON KERING B 130 X 6 X 16                       ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 6, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(2186, 'K-1059              ', 'JABON KERING ALL 130 X 6 X 5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(2187, 'K-1060              ', 'JABON KERING ALL 130 X 6 X 5.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(2188, 'K-1061              ', 'JABON KERING ALL 130 X 6 X 6                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(2189, 'K-1062              ', 'JABON KERING ALL 130 X 6 X 6.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(2190, 'K-1063              ', 'JABON KERING ALL 130 X 6 X 7                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(2191, 'K-1064              ', 'JABON KERING ALL 130 X 6 X 7.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(2192, 'K-1065              ', 'JABON KERING ALL 130 X 6 X 8                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(2193, 'K-1066              ', 'JABON KERING ALL 130 X 6 X 8.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL);
INSERT INTO `product` (`id`, `product_code`, `product_name`, `product_category_id`, `product_status`, `product_uom_id`, `is_maintain_stock`, `image_path`, `brand`, `barcode`, `description`, `wood_type_id`, `grade_id`, `thickness_id`, `condition_id`, `is_has_serial`, `is_fixed_asset`, `warranty`, `netto`, `netto_uom_id`, `is_purchase_item`, `minor`, `minor_uom_id`, `lead_time`, `buy_cost_center_id`, `expense_acc_id`, `main_supp_code`, `manufacturer`, `is_sales_item`, `is_service_item`, `sell_cost_center_id`, `income_acc_id`, `max_disc`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `minqty`, `thickness`, `length`, `width`, `production_type_id`, `production_quality_id`) VALUES
(2194, 'K-1067              ', 'JABON KERING ALL 130 X 6 X 9                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(2195, 'K-1068              ', 'JABON KERING ALL 130 X 6 X 9.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(2196, 'K-1069              ', 'JABON KERING ALL 130 X 6 X 10                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(2197, 'K-1070              ', 'JABON KERING ALL 130 X 6 X 10.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(2198, 'K-1071              ', 'JABON KERING ALL 130 X 6 X 11                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(2199, 'K-1072              ', 'JABON KERING ALL 130 X 6 X 11.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(2200, 'K-1073              ', 'JABON KERING ALL 130 X 6 X 12                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(2201, 'K-1074              ', 'JABON KERING ALL 130 X 6 X 12.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(2202, 'K-1075              ', 'JABON KERING ALL 130 X 6 X 13                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(2203, 'K-1076              ', 'JABON KERING ALL 130 X 6 X 13.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(2204, 'K-1077              ', 'JABON KERING ALL 130 X 6 X 14                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(2205, 'K-1078              ', 'JABON KERING ALL 130 X 6 X 14.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(2206, 'K-1079              ', 'JABON KERING ALL 130 X 6 X 15                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(2207, 'K-1080              ', 'JABON KERING ALL 130 X 6 X 15.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(2208, 'K-1081              ', 'JABON KERING ALL 130 X 6 X 16                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 7, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(2209, 'K-1082              ', 'JABON KERING AFB 130 X 6 X 5                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.0000', NULL, NULL),
(2210, 'K-1083              ', 'JABON KERING AFB 130 X 6 X 5.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '5.5000', NULL, NULL),
(2211, 'K-1084              ', 'JABON KERING AFB 130 X 6 X 6                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.0000', NULL, NULL),
(2212, 'K-1085              ', 'JABON KERING AFB 130 X 6 X 6.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '6.5000', NULL, NULL),
(2213, 'K-1086              ', 'JABON KERING AFB 130 X 6 X 7                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.0000', NULL, NULL),
(2214, 'K-1087              ', 'JABON KERING AFB 130 X 6 X 7.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '7.5000', NULL, NULL),
(2215, 'K-1088              ', 'JABON KERING AFB 130 X 6 X 8                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.0000', NULL, NULL),
(2216, 'K-1089              ', 'JABON KERING AFB 130 X 6 X 8.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '8.5000', NULL, NULL),
(2217, 'K-1090              ', 'JABON KERING AFB 130 X 6 X 9                      ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.0000', NULL, NULL),
(2218, 'K-1091              ', 'JABON KERING AFB 130 X 6 X 9.5                    ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '9.5000', NULL, NULL),
(2219, 'K-1092              ', 'JABON KERING AFB 130 X 6 X 10                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.0000', NULL, NULL),
(2220, 'K-1093              ', 'JABON KERING AFB 130 X 6 X 10.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '10.5000', NULL, NULL),
(2221, 'K-1094              ', 'JABON KERING AFB 130 X 6 X 11                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.0000', NULL, NULL),
(2222, 'K-1095              ', 'JABON KERING AFB 130 X 6 X 11.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '11.5000', NULL, NULL),
(2223, 'K-1096              ', 'JABON KERING AFB 130 X 6 X 12                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.0000', NULL, NULL),
(2224, 'K-1097              ', 'JABON KERING AFB 130 X 6 X 12.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '12.5000', NULL, NULL),
(2225, 'K-1098              ', 'JABON KERING AFB 130 X 6 X 13                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.0000', NULL, NULL),
(2226, 'K-1099              ', 'JABON KERING AFB 130 X 6 X 13.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '13.5000', NULL, NULL),
(2227, 'K-1100              ', 'JABON KERING AFB 130 X 6 X 14                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.0000', NULL, NULL),
(2228, 'K-1101              ', 'JABON KERING AFB 130 X 6 X 14.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '14.5000', NULL, NULL),
(2229, 'K-1102              ', 'JABON KERING AFB 130 X 6 X 15                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.0000', NULL, NULL),
(2230, 'K-1103              ', 'JABON KERING AFB 130 X 6 X 15.5                   ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '15.5000', NULL, NULL),
(2231, 'K-1104              ', 'JABON KERING AFB 130 X 6 X 16                     ', 2, NULL, 2, NULL, '', NULL, NULL, NULL, 3, 8, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SYSTEM', NULL, NULL, NULL, NULL, NULL, '6.0000', '130.0000', '16.0000', NULL, NULL),
(2232, 'PDC009-17', 'BARECORE NORMAL A TIPE 12', 3, NULL, 2, 0, '', NULL, NULL, NULL, 1, 9, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2017-01-21', 'Timotius', NULL, NULL, 0, '1.2000', '244.0000', '122.0000', 2, 1),
(2233, 'PDC009-18', 'BARECORE NORMAL B TIPE 12', 3, NULL, 2, 0, '', NULL, NULL, NULL, 1, 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2017-01-21', 'Timotius', NULL, NULL, 0, '1.2000', '244.0000', '122.0000', 2, 1),
(2234, 'PDC009-19', 'BARECORE KLEM A TIPE 12', 3, NULL, 2, 0, '', NULL, NULL, NULL, 1, 9, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2017-01-21', 'Timotius', NULL, NULL, 0, '1.2000', '244.0000', '122.0000', 2, 3),
(2235, 'PDC009-20', 'BARECORE KLEM B TIPE 12', 3, NULL, 2, 0, '', NULL, NULL, NULL, 1, 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2017-01-21', 'Timotius', NULL, NULL, 0, '1.2000', '244.0000', '122.0000', 2, 3),
(2236, 'PDC009-21', 'BARECORE PROTOL A TIPE 12', 3, NULL, 2, 0, '', NULL, NULL, NULL, 1, 9, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2017-01-21', 'Timotius', NULL, NULL, 0, '1.2000', '244.0000', '122.0000', 2, 2),
(2237, 'PDC009-22', 'BARECORE PROTOL B TIPE 12', 3, NULL, 2, 0, '', NULL, NULL, NULL, 1, 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2017-01-21', 'Timotius', NULL, NULL, 0, '1.2000', '244.0000', '122.0000', 2, 2),
(2238, 'PDC009-23', 'CRATE BESAR A TIPE 12', 3, NULL, 3, NULL, '', NULL, NULL, NULL, 1, 9, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, 1),
(2239, 'PDC009-24', 'CRATE KECIL A TIPE 12', 3, NULL, 3, NULL, '', NULL, NULL, NULL, 1, 9, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, 1),
(2240, 'PDC009-25', 'CRATE BESAR A1 TIPE 12', 3, NULL, 3, NULL, '', NULL, NULL, NULL, 1, 11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, 1),
(2241, 'PDC009-26', 'CRATE KECIL A1 TIPE 12', 3, NULL, 3, NULL, '', NULL, NULL, NULL, 1, 11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, 1),
(2242, 'PDC009-27', 'CRATE BESAR B TIPE 12', 3, NULL, 3, NULL, '', NULL, NULL, NULL, 1, 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, 1),
(2243, 'PDC009-28', 'CRATE KECIL B TIPE 12', 3, NULL, 3, NULL, '', NULL, NULL, NULL, 1, 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, 1),
(2244, 'PDC009-29', 'CRATE BESAR BNP TIPE 12', 3, NULL, 3, NULL, '', NULL, NULL, NULL, 1, 12, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, 1),
(2245, 'PDC009-30', 'CRATE KECIL BNP TIPE 12', 3, NULL, 3, NULL, '', NULL, NULL, NULL, 1, 12, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, 1),
(2246, 'PDC009-31', 'CRATE BESAR C TIPE 12', 3, NULL, 3, NULL, '', NULL, NULL, NULL, 1, 13, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, 1),
(2247, 'PDC009-32', 'CRATE KECIL C TIPE 12', 3, NULL, 3, NULL, '', NULL, NULL, NULL, 1, 13, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `production`
--

CREATE TABLE IF NOT EXISTS `production` (
  `id` int(3) NOT NULL,
  `production_code` varchar(20) NOT NULL,
  `group_shift_code` varchar(9) NOT NULL,
  `line_code` varchar(9) NOT NULL,
  `shift_code` varchar(9) NOT NULL,
  `production_type_code` varchar(9) NOT NULL,
  `production_date` date NOT NULL,
  `information` varchar(200) DEFAULT NULL,
  `total_pallet_card` int(5) NOT NULL,
  `total_log` int(5) NOT NULL,
  `total_volume` decimal(20,4) NOT NULL,
  `status` varchar(10) NOT NULL,
  `type` varchar(5) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL,
  `confirm_code` varchar(5) DEFAULT NULL,
  `confirm_date` date DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `production`
--

INSERT INTO `production` (`id`, `production_code`, `group_shift_code`, `line_code`, `shift_code`, `production_type_code`, `production_date`, `information`, `total_pallet_card`, `total_log`, `total_volume`, `status`, `type`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `delete_by`, `confirm_code`, `confirm_date`) VALUES
(13, '0001/PD/03/01/17', 'gs001', 'l001', 's001', 'BC9', '2017-01-03', NULL, 100, 100, '0.2500', 'Complete', '9', '2017-01-03', 'Michael', '2017-01-21', 'Michael', '2017-01-03', 'Michael', NULL, NULL),
(14, '0001/PD/03/01/17', 'gs001', 'l001', 's001', 'BC9', '2017-01-03', NULL, 100, 100, '0.2500', 'Complete', '9', '2017-01-03', 'Michael', '2017-01-21', 'Michael', NULL, NULL, NULL, NULL),
(15, '0002/PD/03/01/17', 'gs004', 'l002', 's002', 'BC12', '2017-01-03', NULL, 120, 120, '0.2800', 'Complete', '13', '2017-01-03', 'Michael', '2017-03-07', 'Michael', NULL, NULL, NULL, NULL),
(16, '0003/PD/08/03/17', 'gs001', '1001', 's001', 'BC9', '2017-03-08', NULL, 0, 0, '0.0000', 'InComplete', '9', '2017-03-08', 'Michael', '2017-03-08', 'Michael', NULL, NULL, NULL, NULL),
(17, '0004/PD/08/03/17', 'gs001', '1001', 's001', 'BC12', '2017-03-08', NULL, 20, 20, '55000.0000', 'Complete', '12', '2017-03-08', 'Michael', NULL, NULL, NULL, NULL, NULL, NULL),
(18, '0005/PD/09/03/17', 'gs001', '1001', 's001', 'BC9', '2017-03-09', NULL, 10, 10, '30000.0000', 'Complete', '9', '2017-03-09', 'Michael', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `production_quality`
--

CREATE TABLE IF NOT EXISTS `production_quality` (
  `id` int(3) NOT NULL,
  `production_quality` varchar(50) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `production_quality`
--

INSERT INTO `production_quality` (`id`, `production_quality`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'Normal', '2016-08-23', 'ADMIN', NULL, NULL, NULL, NULL),
(2, 'Repair Protol', '2016-08-23', 'ADMIN', NULL, NULL, NULL, NULL),
(3, 'Klem', '2016-08-23', 'ADMIN', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `production_type`
--

CREATE TABLE IF NOT EXISTS `production_type` (
  `id` int(3) NOT NULL,
  `production_type` varchar(50) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `production_type_code` varchar(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `production_type`
--

INSERT INTO `production_type` (`id`, `production_type`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `description`, `production_type_code`) VALUES
(1, 'Barecore 2440 x 1220 x 9', '2016-08-23', 'ADMIN', NULL, NULL, NULL, NULL, NULL, 'BC9'),
(2, 'Barecore 2440 x 1220 x 12', '2016-08-23', 'ADMIN', NULL, NULL, NULL, NULL, NULL, 'BC12'),
(3, 'Plywood', '2017-01-21', 'ADMIN', NULL, NULL, NULL, NULL, NULL, 'PW');

-- --------------------------------------------------------

--
-- Struktur dari tabel `production_waste`
--

CREATE TABLE IF NOT EXISTS `production_waste` (
  `id` int(12) NOT NULL,
  `pw_code` varchar(50) NOT NULL,
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
  `deleted_by` varchar(25) DEFAULT NULL,
  `total_material_protol` decimal(20,4) DEFAULT NULL,
  `total_material_klem` decimal(20,4) DEFAULT NULL,
  `type` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `production_waste`
--

INSERT INTO `production_waste` (`id`, `pw_code`, `production_date`, `group_shift_code`, `shift_code`, `line_code`, `production_type_code`, `status`, `confirm_code`, `confirm_date`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `total_material_protol`, `total_material_klem`, `type`) VALUES
(1, '0001/PW/9/01/17', '2017-01-09', 'gs001', 's001', 'l001', 'BC12', 'COMPLETED', NULL, NULL, '2017-01-09', 'timotius', NULL, NULL, NULL, NULL, NULL, NULL, '13'),
(2, '0002/PW/7/03/17', '2017-03-07', 'gs004', 's001', 'l001', 'BC9', 'INCOMPLETE', NULL, NULL, '2017-03-07', 'admin', NULL, NULL, '2017-03-07', 'admin', NULL, NULL, '9'),
(3, '0003/PW/7/03/17', '2017-03-07', 'gs004', 's001', 'l002', 'BC9', 'COMPLETED', NULL, NULL, '2017-03-07', 'admin', NULL, NULL, NULL, NULL, NULL, NULL, '9'),
(4, '0004/PW/9/03/17', '2017-03-09', 'gs001', 's001', '1001', 'BC9', 'COMPLETED', NULL, NULL, '2017-03-09', 'admin', NULL, NULL, NULL, NULL, NULL, NULL, '9'),
(5, '0005/PW/13/05/17', '2017-05-13', 'gs001', 's001', '1001', 'BC12', 'COMPLETED', NULL, NULL, '2017-05-13', 'admin', NULL, NULL, NULL, NULL, NULL, NULL, '12');

-- --------------------------------------------------------

--
-- Struktur dari tabel `product_category`
--

CREATE TABLE IF NOT EXISTS `product_category` (
  `id` int(3) NOT NULL,
  `product_category` varchar(50) DEFAULT NULL,
  `product_category_type_id` int(11) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(25) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `product_category`
--

INSERT INTO `product_category` (`id`, `product_category`, `product_category_type_id`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) VALUES
(1, 'Balken Basah', 1, '2016-08-13', 'Admin', NULL, NULL, NULL, NULL),
(2, 'Balken Kering', 1, '2016-08-13', 'Admin', NULL, NULL, NULL, NULL),
(3, 'Hasil Produksi', 1, '2016-08-13', 'Admin', NULL, NULL, NULL, NULL),
(4, 'Barang Pendukung', 1, '2016-08-13', 'Admin', NULL, NULL, NULL, NULL),
(6, 'Household', 2, '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL),
(7, 'Stasionary', 2, '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL),
(8, 'Printing', 2, '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL),
(9, 'Information Technology', 2, '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `product_pp`
--

CREATE TABLE IF NOT EXISTS `product_pp` (
  `id` int(11) NOT NULL,
  `product_code` varchar(20) NOT NULL,
  `price` decimal(10,2) DEFAULT '0.00',
  `effective_start_date` date NOT NULL,
  `effective_end_date` date NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `product_supp`
--

CREATE TABLE IF NOT EXISTS `product_supp` (
  `id` int(11) NOT NULL,
  `product_code` varchar(20) NOT NULL DEFAULT '',
  `product_name` varchar(50) DEFAULT NULL,
  `product_category_id` int(11) DEFAULT NULL,
  `product_uom_id` int(11) DEFAULT NULL,
  `is_maintain_stock` int(11) DEFAULT NULL,
  `image_path` varchar(200) DEFAULT '',
  `brand` varchar(50) DEFAULT NULL,
  `barcode` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `is_fixed_asset` int(11) DEFAULT NULL,
  `warranty` int(11) DEFAULT NULL,
  `weight_net` decimal(7,2) DEFAULT NULL,
  `weight_gross` decimal(7,2) DEFAULT NULL,
  `weight_uom_id` int(3) DEFAULT NULL,
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
  `asset_id` int(9) DEFAULT NULL,
  `max_disc` decimal(5,2) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  `minqty` int(5) DEFAULT NULL,
  `thickness` decimal(20,4) DEFAULT NULL,
  `length` decimal(20,4) DEFAULT NULL,
  `width` decimal(20,4) DEFAULT NULL,
  `volume_uom_id` int(3) DEFAULT NULL,
  `tax_id` int(3) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `product_supp`
--

INSERT INTO `product_supp` (`id`, `product_code`, `product_name`, `product_category_id`, `product_uom_id`, `is_maintain_stock`, `image_path`, `brand`, `barcode`, `description`, `is_fixed_asset`, `warranty`, `weight_net`, `weight_gross`, `weight_uom_id`, `is_purchase_item`, `minor`, `minor_uom_id`, `lead_time`, `buy_cost_center_id`, `expense_acc_id`, `main_supp_code`, `manufacturer`, `is_sales_item`, `is_service_item`, `sell_cost_center_id`, `income_acc_id`, `asset_id`, `max_disc`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `minqty`, `thickness`, `length`, `width`, `volume_uom_id`, `tax_id`) VALUES
(1, 'SUPP-0001', 'BARANG PENDUKUNG', 6, 1, 0, NULL, NULL, '', '', 1, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '2017-09-09', 'ADMIN', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 'SUPP-0002', 'PLASTIK', 6, 2, 0, NULL, NULL, 'PLASTIK1', 'PLASTIK KRESEK', 0, 9, '4000.00', '5000.00', NULL, 0, 6, NULL, 7, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, '8.00', '2017-09-15', 'admin', NULL, NULL, NULL, NULL, NULL, '3.0000', '1.0000', '2.0000', NULL, NULL),
(3, 'SUPP-0003', 'AAAAAAA', 6, 1, 1, NULL, NULL, '', '', 1, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, '2017-10-18', 'ADMIN', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `prod_pk`
--

CREATE TABLE IF NOT EXISTS `prod_pk` (
  `id` int(11) NOT NULL,
  `prod_pk_code` varchar(20) NOT NULL,
  `group_shift_code` varchar(9) NOT NULL,
  `line_code` varchar(9) NOT NULL,
  `shift_code` varchar(9) NOT NULL,
  `production_date` date NOT NULL,
  `information` varchar(200) DEFAULT NULL,
  `status` varchar(10) NOT NULL,
  `confirm_code` varchar(5) DEFAULT NULL,
  `confirm_date` date DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  `production_type_code` varchar(5) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `prod_pk`
--

INSERT INTO `prod_pk` (`id`, `prod_pk_code`, `group_shift_code`, `line_code`, `shift_code`, `production_date`, `information`, `status`, `confirm_code`, `confirm_date`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `deleted_by`, `production_type_code`) VALUES
(1, '0001/PK/9/01/17', 'gs001', 'l001', 's001', '2017-01-09', NULL, 'COMPLETED', NULL, NULL, '2017-01-09', 'timotius', NULL, NULL, NULL, NULL, 'BC12'),
(2, '0002/PK12/13/05/17', 'gs002', '1002', 's001', '2017-05-13', NULL, 'COMPLETED', NULL, NULL, '2017-05-13', 'timotius', NULL, NULL, NULL, NULL, 'BC12'),
(5, '0001/PK9/1/11/17', 'gs001', '1001', 's001', '2017-11-01', NULL, 'COMPLETED', NULL, NULL, '2017-11-01', 'timotius', NULL, NULL, NULL, NULL, 'BC9');

-- --------------------------------------------------------

--
-- Struktur dari tabel `prod_pk_material`
--

CREATE TABLE IF NOT EXISTS `prod_pk_material` (
  `id` int(11) NOT NULL,
  `prod_pk_code` varchar(20) NOT NULL,
  `product_code` varchar(20) NOT NULL,
  `qty` decimal(20,4) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `prod_pk_material`
--

INSERT INTO `prod_pk_material` (`id`, `prod_pk_code`, `product_code`, `qty`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, '0001/PK/9/01/17', 'PDC009-3', '0.0000', '2017-01-09', 'timotius', NULL, NULL, NULL, NULL),
(2, '0001/PK/9/01/17', 'PDC009-4', '0.0000', '2017-01-09', 'timotius', NULL, NULL, NULL, NULL),
(3, '0002/PK12/13/05/17', 'PDC009-19', '100.0000', '2017-05-13', 'timotius', NULL, NULL, NULL, NULL),
(4, '0002/PK12/13/05/17', 'PDC009-20', '15.1200', '2017-05-13', 'timotius', NULL, NULL, NULL, NULL),
(7, '0001/PK9/1/11/17', 'PDC009-3', '400.0000', '2017-11-01', 'timotius', NULL, NULL, NULL, NULL),
(8, '0001/PK9/1/11/17', 'PDC009-4', '1.0000', '2017-11-01', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `prod_pk_result_product`
--

CREATE TABLE IF NOT EXISTS `prod_pk_result_product` (
  `id` int(12) NOT NULL,
  `prod_pk_code` varchar(20) NOT NULL,
  `product_code` varchar(20) DEFAULT NULL,
  `qty` decimal(20,4) DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `prod_pk_result_product`
--

INSERT INTO `prod_pk_result_product` (`id`, `prod_pk_code`, `product_code`, `qty`, `input_by`, `input_date`, `edited_by`, `edited_date`, `deleted_by`, `deleted_date`) VALUES
(1, '0001/PK/9/01/17', 'PDC009-7', '20.0000', 'timotius', '2017-01-09', NULL, NULL, NULL, NULL),
(2, '0001/PK/9/01/17', 'PDC009-8', '3.0000', 'timotius', '2017-01-09', NULL, NULL, NULL, NULL),
(3, '0002/PK12/13/05/17', 'PDC009-23', '100.0000', 'timotius', '2017-05-13', NULL, NULL, NULL, NULL),
(4, '0002/PK12/13/05/17', 'PDC009-24', '15.0000', 'timotius', '2017-05-13', NULL, NULL, NULL, NULL),
(5, '0001/PK9/1/11/17', 'PDC009-7', '10.0000', 'timotius', '2017-11-01', NULL, NULL, NULL, NULL),
(6, '0001/PK9/1/11/17', 'PDC009-8', '100.0000', 'timotius', '2017-11-01', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `prod_result`
--

CREATE TABLE IF NOT EXISTS `prod_result` (
  `id` int(11) NOT NULL,
  `prod_code` varchar(20) NOT NULL,
  `pressed_no` int(11) DEFAULT NULL,
  `start_time` varchar(10) DEFAULT NULL,
  `total_fine_a` decimal(20,4) DEFAULT NULL,
  `total_fine_b` decimal(20,4) DEFAULT NULL,
  `total_protol` decimal(20,4) DEFAULT NULL,
  `total_klem` decimal(20,4) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL,
  `confirm_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `prod_result`
--

INSERT INTO `prod_result` (`id`, `prod_code`, `pressed_no`, `start_time`, `total_fine_a`, `total_fine_b`, `total_protol`, `total_klem`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `delete_by`, `confirm_date`) VALUES
(1, '0001/PD/03/01/17', 1, '10:00', '1.0000', '1.0000', '1.0000', '2.0000', '2017-01-21', 'Michael', NULL, NULL, NULL, NULL, NULL),
(2, '0002/PD/03/01/17', 1, '11:00', '5.0000', '6.0000', '7.0000', '3.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL, NULL),
(3, '0004/PD/08/03/17', 10, '10:00', '5.0000', '6.0000', '7.0000', '3.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL, NULL),
(4, '0003/PD/08/03/17', 101, '10:00', '5.0000', '6.0000', '7.0000', '3.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL, NULL),
(5, '0005/PD/09/03/17', 1, '12:12', '11.0000', '11.0000', '122.0000', '223.0000', '2017-03-09', 'admin', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `prod_result_product`
--

CREATE TABLE IF NOT EXISTS `prod_result_product` (
  `id` int(11) NOT NULL,
  `prod_result_id` int(11) NOT NULL,
  `product_code` varchar(20) NOT NULL,
  `qty` decimal(20,4) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL,
  `confirm_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `prod_result_product`
--

INSERT INTO `prod_result_product` (`id`, `prod_result_id`, `product_code`, `qty`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `delete_by`, `confirm_date`) VALUES
(1, 1, 'PDC009-7', '1.0000', '2017-01-21', 'Michael', NULL, NULL, NULL, NULL, NULL),
(2, 1, 'PDC009-8', '1.0000', '2017-01-21', 'Michael', NULL, NULL, NULL, NULL, NULL),
(3, 1, 'PDC009-5', '1.0000', '2017-01-21', 'Michael', NULL, NULL, NULL, NULL, NULL),
(4, 1, 'PDC009-6', '0.0000', '2017-01-21', 'Michael', NULL, NULL, NULL, NULL, NULL),
(5, 1, 'PDC009-1', '1.0000', '2017-01-21', 'Michael', NULL, NULL, NULL, NULL, NULL),
(6, 1, 'PDC009-2', '1.0000', '2017-01-21', 'Michael', NULL, NULL, NULL, NULL, NULL),
(7, 2, 'PDC009-19', '1.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL, NULL),
(8, 2, 'PDC009-20', '2.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL, NULL),
(9, 2, 'PDC009-21', '3.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL, NULL),
(10, 2, 'PDC009-22', '4.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL, NULL),
(11, 2, 'PDC009-17', '5.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL, NULL),
(12, 2, 'PDC009-18', '6.0000', '2017-03-07', 'admin', NULL, NULL, NULL, NULL, NULL),
(13, 3, 'PDC009-19', '1.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL, NULL),
(14, 3, 'PDC009-20', '2.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL, NULL),
(15, 3, 'PDC009-21', '3.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL, NULL),
(16, 3, 'PDC009-22', '4.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL, NULL),
(17, 3, 'PDC009-17', '5.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL, NULL),
(18, 3, 'PDC009-18', '6.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL, NULL),
(19, 4, 'PDC009-3', '1.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL, NULL),
(20, 4, 'PDC009-4', '2.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL, NULL),
(21, 4, 'PDC009-5', '3.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL, NULL),
(22, 4, 'PDC009-6', '4.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL, NULL),
(23, 4, 'PDC009-1', '5.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL, NULL),
(24, 4, 'PDC009-2', '6.0000', '2017-03-08', 'admin', NULL, NULL, NULL, NULL, NULL),
(25, 5, 'PDC009-3', '112.0000', '2017-03-09', 'admin', NULL, NULL, NULL, NULL, NULL),
(26, 5, 'PDC009-4', '111.0000', '2017-03-09', 'admin', NULL, NULL, NULL, NULL, NULL),
(27, 5, 'PDC009-5', '111.0000', '2017-03-09', 'admin', NULL, NULL, NULL, NULL, NULL),
(28, 5, 'PDC009-6', '11.0000', '2017-03-09', 'admin', NULL, NULL, NULL, NULL, NULL),
(29, 5, 'PDC009-1', '11.0000', '2017-03-09', 'admin', NULL, NULL, NULL, NULL, NULL),
(30, 5, 'PDC009-2', '11.0000', '2017-03-09', 'admin', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `prod_rm`
--

CREATE TABLE IF NOT EXISTS `prod_rm` (
  `id` int(3) NOT NULL,
  `production_code` varchar(20) NOT NULL,
  `pallet_card_code` varchar(21) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL,
  `confirm_date` date DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `prod_rm`
--

INSERT INTO `prod_rm` (`id`, `production_code`, `pallet_card_code`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `delete_by`, `confirm_date`) VALUES
(16, '0001/PD/03/01/17', '0002/BL/03/01/17/0001', '2017-01-03', 'Michael', NULL, NULL, '2017-01-03', 'Michael', NULL),
(17, '0001/PD/03/01/17', '0002/BL/03/01/17/0001', '2017-01-03', 'Michael', NULL, NULL, NULL, NULL, NULL),
(18, '0002/PD/03/01/17', '0002/BL/03/01/17/0002', '2017-01-03', 'Michael', NULL, NULL, NULL, NULL, NULL),
(19, '0002/PD/03/01/17', '0002/BL/03/01/17/0003', '2017-03-07', 'Michael', NULL, NULL, NULL, NULL, NULL),
(20, '0002/PD/03/01/17', '0003/BL/03/01/17/0001', '2017-03-07', 'Michael', NULL, NULL, NULL, NULL, NULL),
(21, '0004/PD/08/03/17', '0003/BL/03/01/17/0002', '2017-03-08', 'Michael', NULL, NULL, NULL, NULL, NULL),
(22, '0004/PD/08/03/17', '0004/BL/07/01/17/0001', '2017-03-08', 'Michael', NULL, NULL, NULL, NULL, NULL),
(23, '0005/PD/09/03/17', '0004/BL/07/01/17/0003', '2017-03-09', 'Michael', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `prod_waste_result`
--

CREATE TABLE IF NOT EXISTS `prod_waste_result` (
  `id` int(12) NOT NULL,
  `pw_code` varchar(50) DEFAULT NULL,
  `pressed_no` int(11) DEFAULT NULL,
  `start_time` varchar(10) DEFAULT NULL,
  `total_fine_a` decimal(20,4) DEFAULT NULL,
  `total_fine_b` decimal(20,4) DEFAULT NULL,
  `total_protol` decimal(20,4) DEFAULT NULL,
  `total_klem` decimal(20,4) DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `confirm_date` date DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `prod_waste_result`
--

INSERT INTO `prod_waste_result` (`id`, `pw_code`, `pressed_no`, `start_time`, `total_fine_a`, `total_fine_b`, `total_protol`, `total_klem`, `input_by`, `input_date`, `edited_by`, `edited_date`, `deleted_by`, `deleted_date`, `confirm_date`, `status`) VALUES
(1, '0001/PW/9/01/17', 1, '10:00', '40.0000', '50.0000', '30.0000', '30.0000', 'Michael', '2017-01-09', NULL, NULL, NULL, NULL, NULL, NULL),
(2, '0003/PW/7/03/17', 10, '10:10', '5.0000', '6.0000', '7.0000', '3.0000', 'admin', '2017-03-07', NULL, NULL, NULL, NULL, NULL, NULL),
(3, '0004/PW/9/03/17', 1, '10:00', '5.0000', '6.0000', '7.0000', '3.0000', 'admin', '2017-03-09', NULL, NULL, NULL, NULL, NULL, NULL),
(4, '0005/PW/13/05/17', 1, '20:00', '5.0000', '6.0000', '7.0000', '3.0000', 'admin', '2017-05-13', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `prod_waste_result_product`
--

CREATE TABLE IF NOT EXISTS `prod_waste_result_product` (
  `id` int(12) NOT NULL,
  `prod_waste_result_id` int(12) DEFAULT NULL,
  `product_code` varchar(20) DEFAULT NULL,
  `qty` decimal(20,4) DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `prod_waste_result_product`
--

INSERT INTO `prod_waste_result_product` (`id`, `prod_waste_result_id`, `product_code`, `qty`, `input_by`, `input_date`, `edited_by`, `edited_date`, `deleted_by`, `deleted_date`) VALUES
(1, 1, 'PDC009-7', '10.0000', 'Michael', '2017-01-09', NULL, NULL, NULL, NULL),
(2, 1, 'PDC009-8', '20.0000', 'Michael', '2017-01-09', NULL, NULL, NULL, NULL),
(3, 1, 'PDC009-5', '30.0000', 'Michael', '2017-01-09', NULL, NULL, NULL, NULL),
(4, 1, 'PDC009-6', '0.0000', 'Michael', '2017-01-09', NULL, NULL, NULL, NULL),
(5, 1, 'PDC009-1', '40.0000', 'Michael', '2017-01-09', NULL, NULL, NULL, NULL),
(6, 1, 'PDC009-2', '50.0000', 'Michael', '2017-01-09', NULL, NULL, NULL, NULL),
(7, 2, 'PDC009-3', '1.0000', 'admin', '2017-03-07', NULL, NULL, NULL, NULL),
(8, 2, 'PDC009-4', '2.0000', 'admin', '2017-03-07', NULL, NULL, NULL, NULL),
(9, 2, 'PDC009-5', '3.0000', 'admin', '2017-03-07', NULL, NULL, NULL, NULL),
(10, 2, 'PDC009-6', '4.0000', 'admin', '2017-03-07', NULL, NULL, NULL, NULL),
(11, 2, 'PDC009-1', '5.0000', 'admin', '2017-03-07', NULL, NULL, NULL, NULL),
(12, 2, 'PDC009-2', '6.0000', 'admin', '2017-03-07', NULL, NULL, NULL, NULL),
(13, 3, 'PDC009-3', '1.0000', 'admin', '2017-03-09', NULL, NULL, NULL, NULL),
(14, 3, 'PDC009-4', '2.0000', 'admin', '2017-03-09', NULL, NULL, NULL, NULL),
(15, 3, 'PDC009-5', '3.0000', 'admin', '2017-03-09', NULL, NULL, NULL, NULL),
(16, 3, 'PDC009-6', '4.0000', 'admin', '2017-03-09', NULL, NULL, NULL, NULL),
(17, 3, 'PDC009-1', '5.0000', 'admin', '2017-03-09', NULL, NULL, NULL, NULL),
(18, 3, 'PDC009-2', '6.0000', 'admin', '2017-03-09', NULL, NULL, NULL, NULL),
(19, 4, 'PDC009-19', '1.0000', 'admin', '2017-05-13', NULL, NULL, NULL, NULL),
(20, 4, 'PDC009-20', '2.0000', 'admin', '2017-05-13', NULL, NULL, NULL, NULL),
(21, 4, 'PDC009-21', '3.0000', 'admin', '2017-05-13', NULL, NULL, NULL, NULL),
(22, 4, 'PDC009-22', '4.0000', 'admin', '2017-05-13', NULL, NULL, NULL, NULL),
(23, 4, 'PDC009-17', '5.0000', 'admin', '2017-05-13', NULL, NULL, NULL, NULL),
(24, 4, 'PDC009-18', '6.0000', 'admin', '2017-05-13', NULL, NULL, NULL, NULL);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `province`
--

INSERT INTO `province` (`id`, `province`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'DKI Jakarta', '2016-04-16', 'administrator', NULL, NULL, NULL, NULL),
(2, 'Jawa Barat', '2016-04-16', 'administrator', NULL, NULL, NULL, NULL),
(3, 'Jawa Timur', '2016-04-16', 'timotiu', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `purchase_prod_result`
--

CREATE TABLE IF NOT EXISTS `purchase_prod_result` (
  `id` int(12) NOT NULL,
  `ppr_code` varchar(50) NOT NULL,
  `supp_code` varchar(9) NOT NULL,
  `purchase_date` date NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  `confirm_code` varchar(5) DEFAULT NULL,
  `confirm_date` date DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  `total` decimal(20,4) DEFAULT NULL,
  `grand_total` decimal(20,4) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `purchase_prod_result`
--

INSERT INTO `purchase_prod_result` (`id`, `ppr_code`, `supp_code`, `purchase_date`, `status`, `confirm_code`, `confirm_date`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `total`, `grand_total`, `type`) VALUES
(1, '0001/STTB/9/01/17', 'BL0010', '2017-01-09', 'COMPLETED', NULL, NULL, '2017-01-09', 'timotius', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, '0002/POH/28/03/17', 'BCR001', '2017-03-28', 'FINAL', NULL, NULL, '2017-03-28', 'timotius', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(3, '0003/POH/29/03/17', 'SBR001', '2017-03-29', 'COMPLETED', NULL, NULL, '2017-03-29', 'timotius', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(4, '0004/POH/30/03/17', 'SBR001', '2017-03-30', 'FINAL', NULL, NULL, '2017-03-30', 'timotius', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(5, '0005/POH/5/05/17', 'SBR001', '2017-05-05', 'FINAL', NULL, NULL, '2017-05-05', 'timotius', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(6, '0006/POH/8/05/17', 'SBR001', '2017-05-08', 'FINAL', NULL, NULL, '2017-05-06', 'timotius', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `purchase_prod_supp`
--

CREATE TABLE IF NOT EXISTS `purchase_prod_supp` (
  `id` int(12) NOT NULL,
  `pps_code` varchar(50) NOT NULL,
  `supp_code` varchar(9) NOT NULL,
  `cost_center_id` int(11) NOT NULL,
  `purchase_date` date NOT NULL,
  `delivery_date` date NOT NULL,
  `note` text,
  `status` varchar(20) DEFAULT NULL,
  `confirm_code` varchar(5) DEFAULT NULL,
  `confirm_date` date DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  `total` decimal(20,4) DEFAULT NULL,
  `tax` decimal(20,4) DEFAULT NULL,
  `grand_total` decimal(20,4) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `purchase_prod_supp`
--

INSERT INTO `purchase_prod_supp` (`id`, `pps_code`, `supp_code`, `cost_center_id`, `purchase_date`, `delivery_date`, `note`, `status`, `confirm_code`, `confirm_date`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `total`, `tax`, `grand_total`) VALUES
(3, '0001/POB/29/10/17', 'BL002', 1, '2017-10-29', '2017-10-29', '', 'COMPLETED', NULL, NULL, '2017-10-29', 'ADMIN', '2017-11-01', 'ADMIN', NULL, NULL, '41000.0000', '56000.0000', '97000.0000');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pw_product`
--

CREATE TABLE IF NOT EXISTS `pw_product` (
  `id` int(11) NOT NULL,
  `pw_code` varchar(50) NOT NULL,
  `product_code` varchar(20) NOT NULL,
  `qty` decimal(20,4) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `received`
--

CREATE TABLE IF NOT EXISTS `received` (
  `id` int(11) NOT NULL,
  `received_code` varchar(16) NOT NULL,
  `received_date` date NOT NULL,
  `rit_no` varchar(4) NOT NULL,
  `supplier_code` varchar(50) NOT NULL,
  `supplier_cp_id` int(11) NOT NULL,
  `license_plate` varchar(25) NOT NULL,
  `driver` varchar(50) NOT NULL,
  `delivery_note` varchar(20) NOT NULL,
  `wood_type_id` int(11) NOT NULL,
  `driver_id` varchar(50) DEFAULT NULL,
  `emp_code` varchar(25) DEFAULT '',
  `total_volume` decimal(20,4) NOT NULL DEFAULT '0.0000',
  `received_by` varchar(50) NOT NULL DEFAULT ' ',
  `received_status` varchar(10) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  `send_to_finance_date` date DEFAULT NULL,
  `confirm_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `received`
--

INSERT INTO `received` (`id`, `received_code`, `received_date`, `rit_no`, `supplier_code`, `supplier_cp_id`, `license_plate`, `driver`, `delivery_note`, `wood_type_id`, `driver_id`, `emp_code`, `total_volume`, `received_by`, `received_status`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `send_to_finance_date`, `confirm_date`) VALUES
(18, '0001/BL/03/01/17', '2017-01-03', '0001', 'BKL001', 3, '', '', '001', 1, '', '', '0.0000', ' ', 'Baru', '2017-01-03', 'Michael', NULL, NULL, '2017-01-03', 'Michael', NULL, NULL),
(19, '0002/BL/03/01/17', '2017-01-03', '0002', 'BL002', 2, 'B 1234', '', '0001', 1, '', '2011111111', '35.0000', ' ', 'FINAL', '2017-01-03', 'Michael', '2017-01-03', 'timotius', NULL, NULL, '2017-01-03', '2017-01-03'),
(20, '0003/BL/03/01/17', '2017-01-03', '0003', 'BL001', 1, '', '', '1', 1, '', '2011111111', '222.0000', ' ', 'FINAL', '2017-01-03', 'Michael', '2017-01-19', 'timotius', NULL, NULL, '2017-01-08', '2017-01-19'),
(21, '0004/BL/07/01/17', '2017-01-07', '0004', 'R005', 4, '', '', 'ABC123', 1, '', '2011111111', '999.0000', ' ', 'FINAL', '2017-01-07', 'Michael', '2017-01-19', 'timotius', NULL, NULL, '2017-01-08', '2017-01-19'),
(22, '0005/BL/08/01/17', '2017-01-08', '0005', 'BL0909', 6, '', '', '09124', 1, '', '2011111111', '1000.0000', ' ', 'Completed', '2017-01-08', 'Michael', NULL, NULL, NULL, NULL, NULL, NULL),
(23, '0001/BL/07/03/17', '2017-03-07', '0001', 'R005', 4, '', '', 'TESTING', 2, '', '', '0.0000', ' ', 'Baru', '2017-03-07', 'admin', '2017-03-07', 'admin', NULL, NULL, NULL, NULL),
(24, '0002/BL/08/03/17', '2017-03-08', '0002', 'R005', 5, '', '', 'TES', 1, '', '', '0.0000', ' ', 'Baru', '2017-03-08', 'admin', NULL, NULL, NULL, NULL, NULL, NULL),
(25, '0003/BL/09/03/17', '2017-03-09', '0003', 'BL002', 2, '', '', 'TESSUB', 1, '', '', '0.0000', ' ', 'Baru', '2017-03-09', 'admin', NULL, NULL, NULL, NULL, NULL, NULL),
(26, '0004/BL/09/03/17', '2017-03-09', '0004', 'BL001', 1, '12', '12', '12', 1, '12', '', '0.0000', ' ', 'Baru', '2017-03-09', 'admin', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `received_detail`
--

CREATE TABLE IF NOT EXISTS `received_detail` (
  `id` int(12) NOT NULL,
  `received_code` varchar(16) NOT NULL,
  `total_volume` decimal(20,4) DEFAULT NULL,
  `total_log` int(5) DEFAULT NULL,
  `grade_id` int(11) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date NOT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `received_detail`
--

INSERT INTO `received_detail` (`id`, `received_code`, `total_volume`, `total_log`, `grade_id`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(24, '0002/BL/03/01/17', '280000.0300', 120, 1, '2017-01-03', 'Michael', '2017-01-03', 'Michael', '0000-00-00', NULL),
(25, '0003/BL/03/01/17', '307500.0000', 120, 1, '2017-01-03', 'Michael', NULL, NULL, '0000-00-00', NULL),
(26, '0005/BL/08/01/17', '25000.2900', 120, 1, '2017-01-08', 'Michael', '2017-01-08', 'Michael', '0000-00-00', NULL),
(27, '0005/BL/08/01/17', '22500.0000', 9, 2, '2017-01-08', 'Michael', NULL, NULL, '0000-00-00', NULL),
(28, '0004/BL/07/01/17', '57500.0000', 20, 1, '2017-01-14', 'Michael', '2017-01-14', 'Michael', '0000-00-00', NULL),
(29, '0004/BL/07/01/17', '2750.0000', 1, 2, '2017-01-14', 'Michael', NULL, NULL, '0000-00-00', NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `receive_prod_result`
--

CREATE TABLE IF NOT EXISTS `receive_prod_result` (
  `id` int(12) NOT NULL,
  `rpr_code` varchar(50) DEFAULT NULL,
  `ppr_code` varchar(50) DEFAULT NULL,
  `receive_date` date DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
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
-- Dumping data untuk tabel `receive_prod_result`
--

INSERT INTO `receive_prod_result` (`id`, `rpr_code`, `ppr_code`, `receive_date`, `status`, `confirm_code`, `confirm_date`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, '0001/STTB/29/03/17', '0002/POH/28/03/17', '2017-03-29', 'FINAL', NULL, NULL, '2017-03-29', 'timotius', NULL, NULL, NULL, NULL),
(2, '0002/STTB/3/04/17', '0004/POH/30/03/17', '2017-04-03', 'FINAL', NULL, NULL, '2017-04-02', 'timotius', NULL, NULL, NULL, NULL),
(3, '0003/STTB/5/05/17', '0005/POH/5/05/17', '2017-05-05', 'FINAL', NULL, NULL, '2017-05-05', 'timotius', NULL, NULL, NULL, NULL),
(4, '0004/STTB/9/05/17', '0006/POH/8/05/17', '2017-05-09', 'FINAL', NULL, NULL, '2017-05-06', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `rpr_note`
--

CREATE TABLE IF NOT EXISTS `rpr_note` (
  `id` int(11) NOT NULL,
  `rpr_code` varchar(50) DEFAULT NULL,
  `note` text,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `rpr_note`
--

INSERT INTO `rpr_note` (`id`, `rpr_code`, `note`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, '0001/STTB/29/03/17', 'abc123', '2017-03-29', 'timotius', NULL, NULL, NULL, NULL),
(2, '0001/STTB/29/03/17', 'acd123', '2017-03-29', 'timotius', NULL, NULL, NULL, NULL),
(3, '0001/STTB/29/03/17', 'tes123', '2017-03-29', 'timotius', NULL, NULL, NULL, NULL),
(4, '0001/STTB/29/03/17', '234', '2017-03-29', 'timotius', NULL, NULL, NULL, NULL),
(5, '0002/STTB/3/04/17', 'TES123', '2017-04-02', 'timotius', NULL, NULL, NULL, NULL),
(6, '0003/STTB/5/05/17', 'ABC', '2017-05-05', 'timotius', NULL, NULL, NULL, NULL),
(7, '0004/STTB/9/05/17', 'ABC', '2017-05-06', 'timotius', NULL, NULL, NULL, NULL),
(8, '0004/STTB/9/05/17', 'DEF', '2017-05-06', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `rpr_product`
--

CREATE TABLE IF NOT EXISTS `rpr_product` (
  `id` int(11) NOT NULL,
  `rpr_code` varchar(50) DEFAULT NULL,
  `product_code` varchar(20) DEFAULT NULL,
  `qty` decimal(20,4) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `rpr_product`
--

INSERT INTO `rpr_product` (`id`, `rpr_code`, `product_code`, `qty`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, '0001/STTB/29/03/17', 'PDC009-17', '100.0000', '2017-03-29', 'timotius', NULL, NULL, NULL, NULL),
(2, '0001/STTB/29/03/17', 'PDC009-2', '70.0000', '2017-03-29', 'timotius', NULL, NULL, NULL, NULL),
(3, '0001/STTB/29/03/17', 'PDC009-3', '20.0000', '2017-03-29', 'timotius', NULL, NULL, NULL, NULL),
(4, '0002/STTB/3/04/17', 'PDC009-17', '230.0000', '2017-04-02', 'timotius', NULL, NULL, NULL, NULL),
(5, '0003/STTB/5/05/17', 'PDC009-18', '200.0000', '2017-05-05', 'timotius', NULL, NULL, NULL, NULL),
(6, '0003/STTB/5/05/17', 'PDC009-3', '0.0000', '2017-05-05', 'timotius', NULL, NULL, NULL, NULL),
(7, '0004/STTB/9/05/17', 'PDC009-17', '25.0000', '2017-05-06', 'timotius', NULL, NULL, NULL, NULL),
(8, '0004/STTB/9/05/17', 'PDC009-4', '400.0000', '2017-05-06', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `salary_mapping`
--

CREATE TABLE IF NOT EXISTS `salary_mapping` (
  `id` int(11) NOT NULL,
  `code` varchar(10) DEFAULT NULL,
  `position_id` varchar(10) DEFAULT NULL,
  `payroll_component_code` varchar(10) DEFAULT NULL,
  `is_absent` int(11) DEFAULT NULL,
  `is_leave` int(11) DEFAULT NULL,
  `reference_document` varchar(300) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(30) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(30) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `salary_setting`
--

CREATE TABLE IF NOT EXISTS `salary_setting` (
  `id` int(11) NOT NULL,
  `employee_code` varchar(10) DEFAULT NULL,
  `effective_start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `effective_end_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `salary_bruto` decimal(15,2) DEFAULT NULL,
  `tax` decimal(15,2) DEFAULT NULL,
  `salary_net` decimal(15,2) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(30) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(30) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `sales`
--

CREATE TABLE IF NOT EXISTS `sales` (
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

-- --------------------------------------------------------

--
-- Struktur dari tabel `sales_detail`
--

CREATE TABLE IF NOT EXISTS `sales_detail` (
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

-- --------------------------------------------------------

--
-- Struktur dari tabel `screen`
--

CREATE TABLE IF NOT EXISTS `screen` (
  `id` int(11) NOT NULL,
  `menu_name` varchar(30) NOT NULL,
  `screen_name` varchar(50) NOT NULL,
  `screen_title` varchar(50) NOT NULL,
  `module_name` varchar(200) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(30) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(30) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `set_so_schedule`
--

CREATE TABLE IF NOT EXISTS `set_so_schedule` (
  `id` int(3) NOT NULL,
  `so_name` varchar(100) DEFAULT NULL,
  `reccurance` varchar(50) DEFAULT NULL,
  `day` varchar(20) DEFAULT NULL,
  `date` int(11) DEFAULT NULL,
  `so_type` varchar(50) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(20) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(20) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `set_so_schedule`
--

INSERT INTO `set_so_schedule` (`id`, `so_name`, `reccurance`, `day`, `date`, `so_type`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'HARIAN 1', 'Harian', NULL, 0, 'STOCK OPNAME TERJADWAL', '2017-05-03', 'admin', NULL, NULL, NULL, NULL),
(2, 'MINGGUAN', 'Mingguan', 'Senin', 0, 'STOCK OPNAME TERJADWAL', '2017-05-03', 'admin', NULL, NULL, NULL, NULL),
(3, 'BARECORE A', 'Harian', NULL, 0, 'STOCK OPNAME TERJADWAL', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(4, 'TES SO HARIAN', 'Harian', NULL, 0, 'STOCK OPNAME TERJADWAL', '2017-05-06', 'admin', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `set_so_schedule_prod`
--

CREATE TABLE IF NOT EXISTS `set_so_schedule_prod` (
  `id` int(12) NOT NULL,
  `set_so_schedule_id` int(3) DEFAULT NULL,
  `product_code` varchar(20) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(20) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(20) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `set_so_schedule_prod`
--

INSERT INTO `set_so_schedule_prod` (`id`, `set_so_schedule_id`, `product_code`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 1, 'B-0001              ', '2017-05-03', 'admin', NULL, NULL, NULL, NULL),
(2, 1, 'B-0002              ', '2017-05-03', 'admin', NULL, NULL, NULL, NULL),
(3, 2, 'B-0001              ', '2017-05-03', 'admin', NULL, NULL, NULL, NULL),
(4, 2, 'B-0002              ', '2017-05-03', 'admin', NULL, NULL, NULL, NULL),
(5, 3, 'PDC009-2', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(6, 3, 'PDC009-3', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(7, 3, 'PDC009-4', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(8, 3, 'PDC009-5', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(9, 3, 'PDC009-6', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(10, 3, 'PDC009-17', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(11, 3, 'PDC009-1', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(12, 4, 'B-0001              ', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(13, 4, 'B-0002              ', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(14, 4, 'B-0003              ', '2017-05-06', 'admin', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `shift`
--

CREATE TABLE IF NOT EXISTS `shift` (
  `id` int(3) NOT NULL,
  `shift_code` varchar(20) NOT NULL,
  `shift_name` varchar(50) DEFAULT NULL,
  `type` varchar(10) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `shift`
--

INSERT INTO `shift` (`id`, `shift_code`, `shift_name`, `type`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `delete_by`) VALUES
(1, 's001', 'PAGI', '', NULL, NULL, NULL, NULL, NULL, NULL),
(2, 's002', 'SORE', '', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `shift_department`
--

CREATE TABLE IF NOT EXISTS `shift_department` (
  `id` int(11) NOT NULL,
  `shift_id` int(11) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `shift_department`
--

INSERT INTO `shift_department` (`id`, `shift_id`, `dept_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3);

-- --------------------------------------------------------

--
-- Struktur dari tabel `shift_detail`
--

CREATE TABLE IF NOT EXISTS `shift_detail` (
  `id` int(5) NOT NULL,
  `shift_id` int(11) NOT NULL,
  `day` varchar(200) NOT NULL,
  `week` int(11) NOT NULL,
  `in` varchar(10) DEFAULT NULL,
  `out` varchar(10) DEFAULT NULL,
  `holiday` varchar(1) NOT NULL,
  `rest` int(11) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `ss_salary_comp`
--

CREATE TABLE IF NOT EXISTS `ss_salary_comp` (
  `id` int(11) NOT NULL,
  `salary_setting_id` int(11) DEFAULT NULL,
  `payroll_component_code` varchar(10) DEFAULT NULL,
  `nominal` decimal(15,2) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(30) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(30) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `ss_tax`
--

CREATE TABLE IF NOT EXISTS `ss_tax` (
  `id` int(11) NOT NULL,
  `salary_setting_id` int(11) DEFAULT NULL,
  `tax_id` int(11) DEFAULT NULL,
  `nominal` decimal(15,2) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(30) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(30) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `stock_opname`
--

CREATE TABLE IF NOT EXISTS `stock_opname` (
  `id` int(12) DEFAULT NULL,
  `so_name` varchar(100) DEFAULT NULL,
  `so_date` date DEFAULT NULL,
  `so_type` varchar(50) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `confirm_code` varchar(5) DEFAULT NULL,
  `confirm_date` date DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(20) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(20) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `stock_opname`
--

INSERT INTO `stock_opname` (`id`, `so_name`, `so_date`, `so_type`, `status`, `confirm_code`, `confirm_date`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'HARIAN 1', '2017-05-03', 'STOCK OPNAME TERJADWAL', 'Draft', NULL, NULL, '2017-05-03', 'admin', '2017-05-03', 'admin', NULL, NULL),
(2, 'BARECORE A', '2017-05-06', 'STOCK OPNAME TERJADWAL', 'Draft', NULL, NULL, '2017-05-06', 'admin', '2017-05-06', 'admin', NULL, NULL),
(3, 'SO TRIWULAN 1', '2017-05-06', 'STOCK OPNAME MANUAL', 'Completed', NULL, NULL, '2017-05-06', 'admin', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `stock_opname_prod`
--

CREATE TABLE IF NOT EXISTS `stock_opname_prod` (
  `id` int(12) DEFAULT NULL,
  `stock_opname_id` int(12) DEFAULT NULL,
  `product_code` varchar(20) DEFAULT NULL,
  `location` varchar(20) DEFAULT NULL,
  `qty_system` decimal(20,4) DEFAULT NULL,
  `qty_actual` decimal(20,4) DEFAULT NULL,
  `selisih_qty` decimal(20,4) DEFAULT NULL,
  `value_system` decimal(20,4) DEFAULT NULL,
  `value_actual` decimal(20,4) DEFAULT NULL,
  `selisih_value` decimal(20,4) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(20) DEFAULT NULL,
  `edited_date` date DEFAULT NULL,
  `edited_by` varchar(20) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `stock_opname_prod`
--

INSERT INTO `stock_opname_prod` (`id`, `stock_opname_id`, `product_code`, `location`, `qty_system`, `qty_actual`, `selisih_qty`, `value_system`, `value_actual`, `selisih_value`, `input_date`, `input_by`, `edited_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(NULL, 1, 'B-0001              ', NULL, '200.0000', '20.0000', '180.0000', '0.0000', '0.0000', '0.0000', '2017-05-03', 'admin', NULL, NULL, NULL, NULL),
(NULL, 1, 'B-0002              ', NULL, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '2017-05-03', 'admin', NULL, NULL, NULL, NULL),
(NULL, 1, 'B-0001              ', NULL, '200.0000', '20.0000', '180.0000', '0.0000', '0.0000', '0.0000', '2017-05-03', 'admin', NULL, NULL, NULL, NULL),
(NULL, 1, 'B-0002              ', NULL, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '2017-05-03', 'admin', NULL, NULL, NULL, NULL),
(NULL, 2, 'PDC009-1', NULL, '90.0000', '90.0000', '0.0000', '0.0000', '0.0000', '0.0000', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(NULL, 2, 'PDC009-2', NULL, '90.0000', '85.0000', '5.0000', '0.0000', '0.0000', '0.0000', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(NULL, 2, 'PDC009-3', NULL, '400.0000', '400.0000', '0.0000', '0.0000', '0.0000', '0.0000', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(NULL, 2, 'PDC009-4', NULL, '1.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(NULL, 2, 'PDC009-5', NULL, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(NULL, 2, 'PDC009-6', NULL, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(NULL, 2, 'PDC009-17', NULL, '123.1230', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(NULL, 2, 'PDC009-1', NULL, '90.0000', '90.0000', '0.0000', '0.0000', '0.0000', '0.0000', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(NULL, 2, 'PDC009-2', NULL, '90.0000', '85.0000', '5.0000', '0.0000', '0.0000', '0.0000', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(NULL, 2, 'PDC009-3', NULL, '400.0000', '400.0000', '0.0000', '0.0000', '0.0000', '0.0000', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(NULL, 2, 'PDC009-4', NULL, '1.0000', '2.0000', '-1.0000', '0.0000', '0.0000', '0.0000', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(NULL, 2, 'PDC009-5', NULL, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(NULL, 2, 'PDC009-6', NULL, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(NULL, 2, 'PDC009-17', NULL, '123.1230', '120.0000', '3.1230', '0.0000', '0.0000', '0.0000', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(NULL, 3, 'B-0001              ', NULL, '0.0000', '1.0000', '-1.0000', '0.0000', '0.0000', '0.0000', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(NULL, 3, 'B-0002              ', NULL, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(NULL, 3, 'B-0003              ', NULL, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(NULL, 3, 'B-0004              ', NULL, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(NULL, 3, 'B-0005              ', NULL, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(NULL, 3, 'B-0006              ', NULL, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '2017-05-06', 'admin', NULL, NULL, NULL, NULL),
(NULL, 3, 'B-0007              ', NULL, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', '2017-05-06', 'admin', NULL, NULL, NULL, NULL);

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
  `supp_status` varchar(30) DEFAULT NULL,
  `default_tax` int(3) NOT NULL,
  `account_no` varchar(30) DEFAULT NULL,
  `bank_id` int(3) DEFAULT NULL,
  `account_name` varchar(30) DEFAULT NULL,
  `currency_id` int(3) NOT NULL,
  `top` int(3) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `supplier`
--

INSERT INTO `supplier` (`id`, `supp_code`, `supp_name`, `pt`, `npwp`, `supp_type_id`, `supp_status`, `default_tax`, `account_no`, `bank_id`, `account_name`, `currency_id`, `top`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(17, 'BL001', 'Kayu A', 'PT. Kayu A', '527.123.123.0006', 1, NULL, 0, NULL, 0, NULL, 1, 30, '2016-08-27', 'timotius', NULL, NULL, NULL, NULL),
(18, 'BL002', 'KAYU B', 'PT KAYU B', '123.123.0009.123', 1, NULL, 0, NULL, 0, NULL, 1, 12, '2016-08-27', 'timotius', '2016-10-28', 'timotius', NULL, NULL),
(19, 'BKL001', 'SUPRIYADI', 'MAKMUR SEJAHTERA', '111.0000.0000123', 1, NULL, 0, NULL, 0, NULL, 1, 0, '2016-09-03', 'timotius', NULL, NULL, NULL, NULL),
(20, 'R005', 'YANI', '', '', 1, NULL, 0, NULL, 0, NULL, 1, 0, '2016-09-03', 'timotius', '2016-09-03', 'timotius', NULL, NULL),
(21, 'BL0909', 'IBRA TADJU', 'MAJU BERSAMA KITA', '10.10053721', 1, NULL, 0, NULL, 0, NULL, 1, 0, '2016-10-20', 'timotius', NULL, NULL, NULL, NULL),
(22, 'BCR001', 'SUPPLIER BARECORE', 'BARECORE SEJAHTERA', '133U492932403', 3, NULL, 0, NULL, 0, NULL, 1, 10, '2016-10-26', 'timotius', '2017-03-06', 'timotius', NULL, NULL),
(23, 'BL0010', 'SUPPLIER SOLO', 'SOLO SEJAHTERA', '1234556009291', 3, NULL, 0, NULL, 0, NULL, 1, 30, '2016-12-03', 'timotius', NULL, NULL, NULL, NULL),
(24, 'BL0003', 'YANI', 'CV MGM', '1234567890', 1, NULL, 0, NULL, 0, NULL, 1, 1, '2016-12-03', 'timotius', NULL, NULL, NULL, NULL),
(25, 'SBR001', 'BARECORE 001', 'PT BARECORE', '12321.123213', 2, NULL, 0, NULL, 0, NULL, 1, 30, '2017-03-29', 'timotius', NULL, NULL, NULL, NULL);

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
  `phone` varchar(15) NOT NULL,
  `fax` varchar(15) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `province_id` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `supp_address`
--

INSERT INTO `supp_address` (`id`, `supp_code`, `address_type`, `address`, `zip_code`, `phone`, `fax`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`, `city`, `province_id`) VALUES
(1, 'BL001', 'Warehouse', 'Jln Supplier A', '1234', '', '', '2016-08-27', 'timotius', NULL, NULL, NULL, NULL, 'JAKARTA TIMUR', 1),
(2, 'BL002', 'Warehouse', 'JLN SUPPLIER B', '09123', '', '', '2016-08-27', 'timotius', '2016-10-28', 'timotius', NULL, NULL, 'JAKARTA BARAT', 1),
(3, 'BKL001', 'Warehouse', 'SUKAJADI 1 NO 48', '1171', '', '', '2016-09-03', 'timotius', NULL, NULL, NULL, NULL, 'KEPULAUAN SERIBU', 1),
(4, 'R005', 'Warehouse', 'BARATAN, PATRANG', '', '', '', '2016-09-03', 'timotius', '2016-09-03', 'timotius', NULL, NULL, 'JAKARTA PUSAT', 1),
(5, 'R005', 'Warehouse', 'XXX', '', '', '', '2016-09-03', 'timotius', NULL, NULL, NULL, NULL, 'JAKARTA UTARA', 1),
(6, 'BL0909', 'Warehouse', 'SERIBU RAYA DEKAT KM 20\nJOGLO\n', '9100', '', '', '2016-10-20', 'timotius', NULL, NULL, NULL, NULL, 'JAKARTA SELATAN', 1),
(7, 'BL0010', 'Billing', 'JLN. KEMENANGAN NO 1', '', '', '', '2016-12-03', 'timotius', NULL, NULL, NULL, NULL, 'WONOSOBO', 2),
(8, 'BL0003', 'Warehouse', 'JEMBER', '', '', '', '2016-12-03', 'timotius', NULL, NULL, NULL, NULL, 'JEMBER', 2),
(9, 'BCR001', 'Warehouse', 'ALAMAT', '12012', '', '', '2017-03-06', 'timotius', NULL, NULL, NULL, NULL, 'GROGOL', 1),
(10, 'SBR001', 'Warehouse', 'KEBUMEN', '', '', '', '2017-03-29', 'timotius', NULL, NULL, NULL, NULL, 'JEMBER', 3);

-- --------------------------------------------------------

--
-- Struktur dari tabel `supp_cp`
--

CREATE TABLE IF NOT EXISTS `supp_cp` (
  `id` int(3) NOT NULL,
  `supp_code` varchar(9) NOT NULL,
  `supp_address_id` int(9) DEFAULT NULL,
  `name` varchar(200) NOT NULL,
  `department` varchar(100) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `supp_cp`
--

INSERT INTO `supp_cp` (`id`, `supp_code`, `supp_address_id`, `name`, `department`, `phone`, `email`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'BL001', 1, 'Sumantri', NULL, NULL, '', '2016-08-27', 'timotius', NULL, NULL, NULL, NULL),
(2, 'BL002', 2, 'JEKRY', NULL, NULL, '', '2016-08-27', 'timotius', '2016-10-28', 'timotius', NULL, NULL),
(3, 'BKL001', 3, 'WONG', NULL, NULL, '', '2016-09-03', 'timotius', NULL, NULL, NULL, NULL),
(4, 'R005', 4, 'YANI', NULL, NULL, '', '2016-09-03', 'timotius', '2016-09-03', 'timotius', NULL, NULL),
(5, 'R005', 5, 'YANI', NULL, NULL, '', '2016-09-03', 'timotius', NULL, NULL, NULL, NULL),
(6, 'BL0909', 6, 'UCOK', NULL, NULL, '', '2016-10-20', 'timotius', NULL, NULL, NULL, NULL),
(7, 'BL0010', 7, 'MENIK', NULL, NULL, '', '2016-12-03', 'timotius', NULL, NULL, NULL, NULL),
(8, 'BL0003', 8, 'YANI', NULL, NULL, '', '2016-12-03', 'timotius', NULL, NULL, NULL, NULL),
(9, 'BCR001', 9, 'SOPO', NULL, NULL, '', '2017-03-06', 'timotius', NULL, NULL, NULL, NULL),
(10, 'SBR001', 10, 'WATI', NULL, NULL, '', '2017-03-29', 'timotius', NULL, NULL, NULL, NULL);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `supp_type`
--

INSERT INTO `supp_type` (`id`, `supp_type`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'Balken', '2016-04-16', 'timotius', NULL, NULL, NULL, NULL),
(2, 'Barecore', '2016-04-16', 'timotius', NULL, NULL, NULL, NULL),
(3, 'Veneer', '2016-10-16', 'ADMIN', NULL, NULL, NULL, NULL),
(4, 'Faceback', '2017-01-07', 'ADMIN', NULL, NULL, NULL, NULL),
(5, 'Pendukung Produksi', '2017-01-07', 'ADMIN', NULL, NULL, NULL, NULL);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `supp_vehicle`
--

INSERT INTO `supp_vehicle` (`id`, `license_plate`, `supp_code`, `vehicle_type_id`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'B1234', 'BL001', 1, '2016-08-27', 'timotius', NULL, NULL, NULL, NULL),
(2, 'BK8972CJ', 'R005', 2, '2016-09-03', 'timotius', '2016-09-03', 'timotius', NULL, NULL),
(3, 'AD 1234 FB', 'BL0010', 2, '2016-12-03', 'timotius', NULL, NULL, NULL, NULL),
(4, 'AD 1417 MA', 'BL0003', 2, '2016-12-03', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `system_group`
--

CREATE TABLE IF NOT EXISTS `system_group` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `system_group`
--

INSERT INTO `system_group` (`id`, `name`, `description`) VALUES
(2, 'admin', 'admin group'),
(3, 'admin 2', 'Admin group 2');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tax`
--

CREATE TABLE IF NOT EXISTS `tax` (
  `id` int(11) NOT NULL,
  `tax` varchar(25) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(30) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(30) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `thickness`
--

CREATE TABLE IF NOT EXISTS `thickness` (
  `id` int(3) NOT NULL,
  `thickness` decimal(7,2) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(25) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `thickness`
--

INSERT INTO `thickness` (`id`, `thickness`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) VALUES
(1, '10.00', NULL, NULL, NULL, NULL, NULL, NULL),
(2, '20.00', NULL, NULL, NULL, NULL, NULL, NULL),
(3, '30.00', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tnr`
--

CREATE TABLE IF NOT EXISTS `tnr` (
  `id` int(11) NOT NULL,
  `tnr` varchar(100) DEFAULT NULL,
  `tnr_type_id` int(11) DEFAULT NULL,
  `ref_document` varchar(300) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(30) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(30) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tnr_trx`
--

CREATE TABLE IF NOT EXISTS `tnr_trx` (
  `id` int(11) NOT NULL,
  `employee_id` varchar(10) DEFAULT NULL,
  `effective_start_month` int(11) DEFAULT NULL,
  `effective_start_year` int(11) DEFAULT NULL,
  `effective_end_month` int(11) DEFAULT NULL,
  `effective_end_year` int(11) DEFAULT NULL,
  `tnr_type_id` int(11) DEFAULT NULL,
  `tnr_id` int(11) DEFAULT NULL,
  `nominal` decimal(20,4) DEFAULT NULL,
  `ref_number` varchar(25) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(30) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(30) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tnr_type`
--

CREATE TABLE IF NOT EXISTS `tnr_type` (
  `id` int(11) NOT NULL,
  `tnr_type` varchar(100) DEFAULT NULL,
  `tax_id` int(11) DEFAULT NULL,
  `ref_document` varchar(300) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(30) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(30) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `uom`
--

CREATE TABLE IF NOT EXISTS `uom` (
  `id` int(3) NOT NULL,
  `uom` varchar(50) DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(25) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(25) DEFAULT NULL
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `uom`
--

INSERT INTO `uom` (`id`, `uom`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) VALUES
(1, 'BATANG (piece)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL),
(2, 'PCS (piece)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL),
(3, 'BK (buku)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL),
(4, 'BTL (bottle)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL),
(5, 'KG (kilogram)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL),
(6, 'CAN (kaleng)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL),
(7, 'PAA (pair, pasang)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL),
(8, 'PAC (package)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL),
(9, 'RIM', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL),
(10, 'ROL (roll)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL),
(11, 'SET', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL),
(12, 'UN (unit)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL),
(13, 'L (liter)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL),
(14, 'G (gram)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL),
(15, 'M (meter)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL),
(16, 'SHT (sheet)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL),
(17, 'PAL (pallet)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL),
(18, 'KRG (karung)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `uom_convertion`
--

CREATE TABLE IF NOT EXISTS `uom_convertion` (
  `id` int(5) NOT NULL,
  `product_code_from` varchar(20) DEFAULT NULL,
  `product_code_to` varchar(20) DEFAULT NULL,
  `qty` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL,
  `group_id` int(11) DEFAULT NULL,
  `username` varchar(25) DEFAULT NULL,
  `password` varchar(2024) DEFAULT NULL,
  `employee_id` varchar(30) DEFAULT NULL,
  `last_login` date DEFAULT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(30) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edit_by` varchar(30) DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `delete_by` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id`, `group_id`, `username`, `password`, `employee_id`, `last_login`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) VALUES
(1, 1, 'root', '99adc231b045331e514a516b4b7680f588e3823213abe901738bc3ad67b2f6fcb3c64efb93d18002588d3ccc1a49efbae1ce20cb43df36b38651f11fa75678e8', '1', '2017-03-03', '2017-03-03', 'root', '2017-03-03', 'root', NULL, NULL),
(2, 1, 'admin', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', '', '2017-03-03', '2017-03-03', 'admin', '2017-03-03', 'admin', NULL, NULL),
(3, 3, 'syaiful', '7a8bf2249d0363dd25ba6c93b9a0a1918de72dfca7ed01b34617af493e99bd634c4888c460acea10be682207788c3e68637d61727f7047fdb82be9eed37fb22a', '', '2017-03-04', '2017-03-04', 'admin', '2017-03-04', 'admin', NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `employee_id` varchar(15) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `phone_no` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`employee_id`, `name`, `address`, `phone_no`, `email`, `password`) VALUES
('2013026132', 'Irvan Wilianto', 'Harapan Indah', '081288370090', 'irvan.wili@gmail.com', 'e4a4109fe0788b34f1cc8e0413c76cb6'),
('2013034022', 'Timotius Suwandi', 'Jalan Duyung 2 No. 6', '081807919152', 'timotius.suwandi@gmail.com', 'c4a66d36a9b756f36eecd8b54f28f440'),
('sfasfafsa', 'sfasfasfa', 'sfasfasfasfasfa', 'sfasfasfa', 'sfasfa', '411628f225595c8b46097e93b4833c58');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `vehicle_type`
--

INSERT INTO `vehicle_type` (`id`, `vehicle_type`, `capacity`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'Mobil', 1500, '2016-04-16', 'timotius', NULL, NULL, NULL, NULL),
(2, 'Truk', 2000, '2016-04-16', 'timotius', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `wood_genus`
--

INSERT INTO `wood_genus` (`id`, `wood_genus`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'Falcata', '2016-12-31', 'administrator', NULL, NULL, NULL, NULL),
(2, 'Shorea', '2016-12-31', 'administrator', NULL, NULL, NULL, NULL),
(3, 'Anthocepalus', '2016-12-31', 'administrator', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `wood_resource`
--

CREATE TABLE IF NOT EXISTS `wood_resource` (
  `id` int(11) NOT NULL,
  `wood_resource` varchar(20) NOT NULL,
  `input_date` date DEFAULT NULL,
  `input_by` varchar(25) DEFAULT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `wood_resource`
--

INSERT INTO `wood_resource` (`id`, `wood_resource`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'HA', '2017-01-02', 'administrator', NULL, NULL, NULL, NULL),
(2, 'HT', '2017-01-02', 'administrator', NULL, NULL, NULL, NULL),
(3, 'HR', '2017-01-02', 'administrator', NULL, NULL, NULL, NULL),
(4, 'Import', '2017-01-02', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `wood_type`
--

INSERT INTO `wood_type` (`id`, `wood_type`, `wood_genus_id`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, 'SENGON', 1, '2016-05-10', 'ADMIN', '2016-10-28', NULL, NULL, NULL),
(2, 'MERANTI', 2, '2016-05-10', 'ADMIN', '2016-10-28', NULL, NULL, NULL),
(5, 'JABON', 3, '2016-12-31', 'timotius', NULL, NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`,`acc_no`);

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bank`
--
ALTER TABLE `bank`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`);

--
-- Indexes for table `chamber`
--
ALTER TABLE `chamber`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`);

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`);

--
-- Indexes for table `common_config`
--
ALTER TABLE `common_config`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `completed_so_schedule`
--
ALTER TABLE `completed_so_schedule`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `condition`
--
ALTER TABLE `condition`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `confirm`
--
ALTER TABLE `confirm`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cost_center`
--
ALTER TABLE `cost_center`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `currency`
--
ALTER TABLE `currency`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `supp_code_2` (`cust_code`), ADD KEY `bank_id` (`bank_id`), ADD KEY `supp_code` (`cust_code`), ADD KEY `input_by` (`input_by`,`edited_by`,`deleted_by`);

--
-- Indexes for table `cust_addr`
--
ALTER TABLE `cust_addr`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`), ADD KEY `supp_code` (`cust_code`);

--
-- Indexes for table `delivery`
--
ALTER TABLE `delivery`
  ADD PRIMARY KEY (`id`,`delivery_note`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `division`
--
ALTER TABLE `division`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `document_type`
--
ALTER TABLE `document_type`
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
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`);

--
-- Indexes for table `employee_status`
--
ALTER TABLE `employee_status`
  ADD PRIMARY KEY (`employee_status_id`);

--
-- Indexes for table `employee_type`
--
ALTER TABLE `employee_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `emp_position`
--
ALTER TABLE `emp_position`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `gender`
--
ALTER TABLE `gender`
  ADD PRIMARY KEY (`gender_id`);

--
-- Indexes for table `grade`
--
ALTER TABLE `grade`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `group_screen`
--
ALTER TABLE `group_screen`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `group_shift`
--
ALTER TABLE `group_shift`
  ADD PRIMARY KEY (`id`,`group_shift_code`);

--
-- Indexes for table `group_shift_dtl`
--
ALTER TABLE `group_shift_dtl`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `import_fingerprint`
--
ALTER TABLE `import_fingerprint`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `inventory_log`
--
ALTER TABLE `inventory_log`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `inventory_log_temp`
--
ALTER TABLE `inventory_log_temp`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `invoice_balken`
--
ALTER TABLE `invoice_balken`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `invoice_prod_result`
--
ALTER TABLE `invoice_prod_result`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `inv_balken_product`
--
ALTER TABLE `inv_balken_product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `inv_pr_product`
--
ALTER TABLE `inv_pr_product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `line`
--
ALTER TABLE `line`
  ADD PRIMARY KEY (`id`,`line_code`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `machine`
--
ALTER TABLE `machine`
  ADD PRIMARY KEY (`id`,`machine_code`);

--
-- Indexes for table `marital`
--
ALTER TABLE `marital`
  ADD PRIMARY KEY (`marital_id`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ms_position`
--
ALTER TABLE `ms_position`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ms_supplier`
--
ALTER TABLE `ms_supplier`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `packing`
--
ALTER TABLE `packing`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `packing_conversion`
--
ALTER TABLE `packing_conversion`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `packing_result`
--
ALTER TABLE `packing_result`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `packing_rm`
--
ALTER TABLE `packing_rm`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pallet_card`
--
ALTER TABLE `pallet_card`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pallet_card_dtl`
--
ALTER TABLE `pallet_card_dtl`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment_prod_result`
--
ALTER TABLE `payment_prod_result`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payroll_component`
--
ALTER TABLE `payroll_component`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pay_pr_product`
--
ALTER TABLE `pay_pr_product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pic_docking`
--
ALTER TABLE `pic_docking`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pic_tally`
--
ALTER TABLE `pic_tally`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`), ADD KEY `dry_in_code` (`dry_in_code`), ADD KEY `pallet_card_code` (`emp_code`);

--
-- Indexes for table `ppr_note`
--
ALTER TABLE `ppr_note`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ppr_product`
--
ALTER TABLE `ppr_product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pps_product`
--
ALTER TABLE `pps_product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`,`product_code`), ADD KEY `production_type_id` (`production_type_id`), ADD KEY `production_quality_id` (`production_quality_id`);

--
-- Indexes for table `production`
--
ALTER TABLE `production`
  ADD PRIMARY KEY (`id`,`production_code`);

--
-- Indexes for table `production_quality`
--
ALTER TABLE `production_quality`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `production_type`
--
ALTER TABLE `production_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `production_waste`
--
ALTER TABLE `production_waste`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_category`
--
ALTER TABLE `product_category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_supp`
--
ALTER TABLE `product_supp`
  ADD PRIMARY KEY (`id`,`product_code`);

--
-- Indexes for table `prod_pk`
--
ALTER TABLE `prod_pk`
  ADD PRIMARY KEY (`id`,`prod_pk_code`);

--
-- Indexes for table `prod_pk_material`
--
ALTER TABLE `prod_pk_material`
  ADD PRIMARY KEY (`id`,`prod_pk_code`);

--
-- Indexes for table `prod_pk_result_product`
--
ALTER TABLE `prod_pk_result_product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `prod_result`
--
ALTER TABLE `prod_result`
  ADD PRIMARY KEY (`id`,`prod_code`);

--
-- Indexes for table `prod_result_product`
--
ALTER TABLE `prod_result_product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `prod_rm`
--
ALTER TABLE `prod_rm`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `prod_waste_result`
--
ALTER TABLE `prod_waste_result`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `prod_waste_result_product`
--
ALTER TABLE `prod_waste_result_product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `province`
--
ALTER TABLE `province`
  ADD PRIMARY KEY (`id`), ADD KEY `input_by` (`input_by`), ADD KEY `edited_by` (`edited_by`), ADD KEY `deleted_by` (`deleted_by`);

--
-- Indexes for table `purchase_prod_result`
--
ALTER TABLE `purchase_prod_result`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `purchase_prod_supp`
--
ALTER TABLE `purchase_prod_supp`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`id`), ADD KEY `customer_id` (`customer_id`), ADD KEY `cust_addr_id` (`cust_addr_id`), ADD KEY `currency_id` (`currency_id`), ADD KEY `freight_cost_currency_id` (`freight_cost_currency_id`), ADD KEY `insurance_cost_currency_id` (`insurance_cost_currency_id`);

--
-- Indexes for table `sales_detail`
--
ALTER TABLE `sales_detail`
  ADD PRIMARY KEY (`id`), ADD KEY `product_id` (`product_id`), ADD KEY `sales_id` (`sales_id`);

--
-- Indexes for table `uom`
--
ALTER TABLE `uom`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cost_center`
--
ALTER TABLE `cost_center`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `pps_product`
--
ALTER TABLE `pps_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `product_category`
--
ALTER TABLE `product_category`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `product_supp`
--
ALTER TABLE `product_supp`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `prod_pk`
--
ALTER TABLE `prod_pk`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `prod_pk_material`
--
ALTER TABLE `prod_pk_material`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `prod_pk_result_product`
--
ALTER TABLE `prod_pk_result_product`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `purchase_prod_supp`
--
ALTER TABLE `purchase_prod_supp`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `sales_detail`
--
ALTER TABLE `sales_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `uom`
--
ALTER TABLE `uom`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `sales`
--
ALTER TABLE `sales`
ADD CONSTRAINT `sales_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
ADD CONSTRAINT `sales_ibfk_2` FOREIGN KEY (`cust_addr_id`) REFERENCES `cust_addr` (`id`),
ADD CONSTRAINT `sales_ibfk_3` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`id`),
ADD CONSTRAINT `sales_ibfk_4` FOREIGN KEY (`freight_cost_currency_id`) REFERENCES `currency` (`id`),
ADD CONSTRAINT `sales_ibfk_5` FOREIGN KEY (`insurance_cost_currency_id`) REFERENCES `currency` (`id`);

--
-- Ketidakleluasaan untuk tabel `sales_detail`
--
ALTER TABLE `sales_detail`
ADD CONSTRAINT `sales_detail_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
