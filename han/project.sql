ALTER TABLE `prod_waste_result_product` CHANGE `id` `id` INT(12) NOT NULL AUTO_INCREMENT;
ALTER TABLE `delivery` ADD `received_code` VARCHAR(50) NOT NULL AFTER `id`;