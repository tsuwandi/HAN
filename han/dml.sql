--commit dml disin
--insert
--update
--delete

ALTER TABLE `product_category` ADD `product_category_type_id` INT NULL AFTER `product_category`;
ALTER TABLE `product_category` CHANGE `id` `id` INT(3) NOT NULL AUTO_INCREMENT;

INSERT INTO `product_category` (`product_category`, `product_category_type_id`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('Household', '2', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);
INSERT INTO `product_category` (`product_category`, `product_category_type_id`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('Stasionary', '2', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);
INSERT INTO `product_category` (`product_category`, `product_category_type_id`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('Printing', '2', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);
INSERT INTO `product_category` (`product_category`, `product_category_type_id`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('Information Technology', '2', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);

ALTER TABLE `uom` ADD PRIMARY KEY(`id`);
ALTER TABLE `uom` CHANGE `id` `id` INT(3) NOT NULL AUTO_INCREMENT;

INSERT INTO `uom` (`uom`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('BATANG (piece)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);
INSERT INTO `uom` (`uom`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('PCS (piece)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);
INSERT INTO `uom` (`uom`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('BK (buku)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);
INSERT INTO `uom` (`uom`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('BTL (bottle)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);
INSERT INTO `uom` (`uom`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('KG (kilogram)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);
INSERT INTO `uom` (`uom`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('CAN (kaleng)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);
INSERT INTO `uom` (`uom`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('PAA (pair, pasang)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);
INSERT INTO `uom` (`uom`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('PAC (package)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);
INSERT INTO `uom` (`uom`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('RIM', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);
INSERT INTO `uom` (`uom`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('ROL (roll)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);
INSERT INTO `uom` (`uom`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('SET', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);
INSERT INTO `uom` (`uom`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('UN (unit)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);
INSERT INTO `uom` (`uom`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('L (liter)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);
INSERT INTO `uom` (`uom`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('G (gram)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);
INSERT INTO `uom` (`uom`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('M (meter)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);
INSERT INTO `uom` (`uom`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('SHT (sheet)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);
INSERT INTO `uom` (`uom`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('PAL (pallet)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);
INSERT INTO `uom` (`uom`, `input_date`, `input_by`, `edit_date`, `edit_by`, `delete_date`, `delete_by`) 
VALUES ('KRG (karung)', '2017-09-30', 'ADMIN', NULL, NULL, NULL, NULL);
