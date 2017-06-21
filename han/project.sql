
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