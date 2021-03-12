CREATE TABLE IF NOT EXISTS `payment`
(
    `id`     BIGINT(20) NOT NULL AUTO_INCREMENT,
    `serial` VARCHAR(200) DEFAULT NULL,
    PRIMARY KEY (`id`)
    )
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_general_ci;