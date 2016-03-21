CREATE TABLE IF NOT EXISTS `bookstorespring`.`event` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR (255) NOT NULL,
  `place` VARCHAR (255),
  `label` VARCHAR (255),
  `timefrom` TIMESTAMP NOT NULL,
  `timeto` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`)
) ;
