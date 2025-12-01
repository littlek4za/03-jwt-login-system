CREATE DATABASE  IF NOT EXISTS `jwt_login_system_directory`;
USE `jwt_login_system_directory`;

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `app_user`;
SET foreign_key_checks = 1;

CREATE TABLE app_user (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;