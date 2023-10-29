# Create Database
CREATE DATABASE IF NOT EXISTS `ecommapp`;
USE `ecommapp`;

# Create table for products
CREATE TABLE IF NOT EXISTS `product`(
    `product_id` INT NOT NULL AUTO_INCREMENT,
    `image` varchar(255) DEFAULT NULL,
    `name` VARCHAR(255) DEFAULT NULL,
    `price` DOUBLE DEFAULT 0.0,
    `owners` INT DEFAULT 0,
    PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

# Insert values into inventory

# Create table for users
CREATE TABLE IF NOT EXISTS `users`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(255) DEFAULT NULL,
    `password` VARCHAR(255) DEFAULT NULL,
    `email` VARCHAR(255) DEFAULT NULL,
    `role` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

# Insert values into users

# Create table for orders
CREATE TABLE IF NOT EXISTS `order`(
    `order_id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT DEFAULT 0,
    `quantity` INT DEFAULT 0,
    `product_id` INT DEFAULT 0,
    `bill` INT DEFAULT 0,
    PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;