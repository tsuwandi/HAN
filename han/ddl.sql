--commit ddl disini.
--1. Alter Table
--2. Drop Table
--3. Create Table

-- Buat Drop dan Create sepaket.

--timotius@20170903_start
DROP TABLE `product_supp`;
CREATE TABLE `product_supp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_code` varchar(20) NOT NULL DEFAULT '',
  `product_name` varchar(50) DEFAULT NULL,
  `product_category_id` int(11) DEFAULT NULL,
  `product_uom_id` int(11) DEFAULT NULL,
  `is_maintain_stock` int(11) DEFAULT NULL,
  `image_path` varchar(200) NOT NULL DEFAULT '',
  `brand` varchar(50) DEFAULT NULL,
  `barcode` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `is_fixed_asset` int(11) DEFAULT NULL,
  `warranty` int(11) DEFAULT NULL,
  `weight_net` decimal(7,2) DEFAULT NULL,
  `weight_gross` decimal(7,2) DEFAULT NULL,
  `weight_uom` int(3) DEFAULT NULL,
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
  `volume_uom` int(3) DEFAULT NULL,
  `tax_id` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`,`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `product_supp` CHANGE `volume_uom` `volume_uom_id` INT(3) NULL DEFAULT NULL;
ALTER TABLE `product_supp` CHANGE `weight_uom` `weight_uom_id` INT(3) NULL DEFAULT NULL;
--timotius@20170903_end