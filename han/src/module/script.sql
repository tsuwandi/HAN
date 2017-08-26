-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 26, 2017 at 06:10 AM
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
-- Table structure for table `country`
--

CREATE TABLE `country` (
  `id` int(50) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `idx` int(8) DEFAULT NULL,
  `country_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date_format` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `time_zones` text COLLATE utf8mb4_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `country`
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

--
-- Indexes for dumped tables
--

--
-- Indexes for table `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `country`
--
ALTER TABLE `country`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=250;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
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

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `cust_code`, `cust_name`, `pt`, `npwp`, `cust_type`, `default_tax`, `account_no`, `bank_id`, `account_name`, `currency_id`, `top`, `country`, `note`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, '1', 'Lorem Ipsum', 'PT. Maju Mundur', '123123123123123', 'Individu', 5, '123123123123123', 1, 'Lorem Ipsum', 1, 10, 'Indonesia', 'Lorem Ipsum Dolor Sit Amet', '2016-04-16', 'Sandy', NULL, NULL, NULL, NULL),
(3, '12312', '31231QQ', '231231QQ', '23123QQ', 'Government', 10, '12312QQ', 1, '12312QQ', 1, 10, 'Zimbabwe', NULL, '2017-07-16', 'Sandy', '2017-07-16', 'Sandy', '2017-07-16', 'Sandy'),
(6, 'ABC', '12312', '312312', '3123123', 'Individu', 2, '123123', 1, '123123', 1, 123, 'Ã…land Islands', NULL, '2017-07-16', 'Sandy', NULL, NULL, '2017-07-16', 'Sandy'),
(7, 'ROOT', 'ROOTQ', '123123', '123123123', 'Government', 12, '123123', 1, '123123', 1, 123, 'Antarctica', NULL, '2017-07-16', 'Sandy', '2017-07-16', 'Sandy', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `supp_code_2` (`cust_code`),
  ADD KEY `bank_id` (`bank_id`),
  ADD KEY `supp_code` (`cust_code`),
  ADD KEY `input_by` (`input_by`,`edited_by`,`deleted_by`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- --------------------------------------------------------

--
-- Table structure for table `cust_addr`
--

CREATE TABLE `cust_addr` (
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
  `province` varchar(256) NOT NULL,
  `city` varchar(256) NOT NULL,
  `input_date` date NOT NULL,
  `input_by` varchar(25) NOT NULL,
  `edit_date` date DEFAULT NULL,
  `edited_by` varchar(25) DEFAULT NULL,
  `deleted_date` date DEFAULT NULL,
  `deleted_by` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cust_addr`
--

INSERT INTO `cust_addr` (`id`, `cust_code`, `cust_id`, `name`, `addr_type`, `phone`, `fax`, `address`, `zip_code`, `email`, `province`, `city`, `input_date`, `input_by`, `edit_date`, `edited_by`, `deleted_date`, `deleted_by`) VALUES
(1, '12312', 0, '312312', 'Billing', '123', '123', '12312', '3123', '3123123@YAH.COM', '12312', '312312', '2017-07-16', 'Sandy', '2017-07-16', 'Sandy', '2017-07-16', 'Sandy'),
(3, 'ABC', 0, '12312312', 'Billing', '12312', '123123', '12312312', '31231', '3123@ABC.COM', '2312312', '3123', '2017-07-16', 'Sandy', NULL, NULL, '2017-07-16', 'Sandy'),
(4, '12312', 0, 'QQ', 'Shipping', '123', '123', 'QQ', '123', 'QQ@Q.COM', 'QQ', 'QQ', '2017-07-16', 'Sandy', '2017-07-16', 'Sandy', '2017-07-16', 'Sandy'),
(5, 'ROOT', 0, '123123', 'Warehouse', '123', '123', '123123', '12312', 'QWEQW@A.COM', '3123', '123123', '2017-07-16', 'Sandy', '2017-07-16', 'Sandy', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cust_addr`
--
ALTER TABLE `cust_addr`
  ADD PRIMARY KEY (`id`),
  ADD KEY `input_by` (`input_by`),
  ADD KEY `edited_by` (`edited_by`),
  ADD KEY `deleted_by` (`deleted_by`),
  ADD KEY `supp_code` (`cust_code`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cust_addr`
--
ALTER TABLE `cust_addr`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;