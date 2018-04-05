-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema trucking
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema trucking
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `trucking` DEFAULT CHARACTER SET utf8 ;
USE `trucking` ;

-- -----------------------------------------------------
-- Table `trucking`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trucking`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trucking`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trucking`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NULL,
  `password` VARCHAR(250) NULL,
  `status` VARCHAR(45) NULL,
  `roles_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_users_roles1_idx` (`roles_id` ASC),
  CONSTRAINT `fk_users_roles1`
    FOREIGN KEY (`roles_id`)
    REFERENCES `trucking`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trucking`.`cities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trucking`.`cities` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trucking`.`detailsUsers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trucking`.`detailsUsers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `phoneNumber` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `users_id` INT NOT NULL,
  `cities_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_detailsUsers_users1_idx` (`users_id` ASC),
  INDEX `fk_detailsUsers_cities1_idx` (`cities_id` ASC),
  CONSTRAINT `fk_detailsUsers_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `trucking`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_detailsUsers_cities1`
    FOREIGN KEY (`cities_id`)
    REFERENCES `trucking`.`cities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trucking`.`addressFrom`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trucking`.`addressFrom` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `streets` VARCHAR(100) NULL,
  `houses` INT NULL,
  `flats` INT NULL,
  `housing` INT NULL,
  `cities_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_addressFrom_cities1_idx` (`cities_id` ASC),
  CONSTRAINT `fk_addressFrom_cities1`
    FOREIGN KEY (`cities_id`)
    REFERENCES `trucking`.`cities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trucking`.`addressTo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trucking`.`addressTo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `streets` VARCHAR(100) NULL,
  `houses` INT NULL,
  `flats` INT NULL,
  `housing` INT NULL,
  `cities_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_addressTo_cities1_idx` (`cities_id` ASC),
  CONSTRAINT `fk_addressTo_cities1`
    FOREIGN KEY (`cities_id`)
    REFERENCES `trucking`.`cities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trucking`.`ordersDetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trucking`.`ordersDetails` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NULL,
  `dateDelivery` DATE NULL,
  `priceShipment` DOUBLE NULL,
  `weight` INT NULL,
  `comment` TEXT(1000) NULL,
  `statusDelete` VARCHAR(45) NULL,
  `addressFrom_id` INT NOT NULL,
  `addressTo_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ordersDetails_addressFrom1_idx` (`addressFrom_id` ASC),
  INDEX `fk_ordersDetails_addressTo1_idx` (`addressTo_id` ASC),
  CONSTRAINT `fk_ordersDetails_addressFrom1`
    FOREIGN KEY (`addressFrom_id`)
    REFERENCES `trucking`.`addressFrom` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordersDetails_addressTo1`
    FOREIGN KEY (`addressTo_id`)
    REFERENCES `trucking`.`addressTo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trucking`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trucking`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dateCreate` DATE NULL,
  `users_id` INT NOT NULL,
  `ordersDetails_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_orders_users1_idx` (`users_id` ASC),
  INDEX `fk_orders_ordersDetails1_idx` (`ordersDetails_id` ASC),
  CONSTRAINT `fk_orders_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `trucking`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_ordersDetails1`
    FOREIGN KEY (`ordersDetails_id`)
    REFERENCES `trucking`.`ordersDetails` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;