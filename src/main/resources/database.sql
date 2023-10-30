--
-- Create Database
--
CREATE DATABASE IF NOT EXISTS `ecommapp`;
USE `ecommapp`;

--
-- Create table for products
--
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`(
    `product_id` INT NOT NULL AUTO_INCREMENT,
    `image` varchar(255) DEFAULT NULL,
    `name` VARCHAR(255) DEFAULT NULL,
    `price` DOUBLE DEFAULT 0.0,
    `owners` INT DEFAULT 0,
    PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Insert values into inventory
--

--
-- Create table for users
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`(
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) DEFAULT NULL,
    `enabled` TINYINT NOT NULL,
    `email` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Insert values into users
--

INSERT INTO `users` VALUES
                       ('hikolu','{noop}test123',1,'test@mail.com'),
                       ('john','{noop}test123',1,'john@mail.com');

--
-- Create table for roles
--
DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities`(
    `username` VARCHAR(50) NOT NULL,
    `authority` VARCHAR(50) NOT NULL,
    UNIQUE KEY `authorities4_idx_1` (`username`,`authority`),
    CONSTRAINT `authorities4_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Insert values into roles
--
INSERT INTO `authorities` VALUES
                        ('hikolu','ROLE_ADMIN'),
                        ('john','ROLE_USER');

--
-- Create table for orders
--

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`(
    `order_id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT DEFAULT 0,
    `quantity` INT DEFAULT 0,
    `product_id` INT DEFAULT 0,
    `bill` DOUBLE DEFAULT 0,
    PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;