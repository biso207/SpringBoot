CREATE TABLE IF NOT EXISTS `identificators` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `idNodo` INT NOT NULL,
    `timestamp` BIGINT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `idNodi_UNIQUE` (`idNodo` ASC) VISIBLE
);
