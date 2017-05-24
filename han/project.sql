
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