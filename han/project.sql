
ALTER TABLE `production` ADD `type` VARCHAR(5) NOT NULL AFTER `status`;
ALTER TABLE `production_waste` ADD `type` VARCHAR(10) NOT NULL AFTER `total_material_klem`;
ALTER TABLE `packing` ADD `type` VARCHAR(10) NOT NULL AFTER `status`;