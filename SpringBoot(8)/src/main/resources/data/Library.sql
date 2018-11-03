
CREATE SCHEMA IF NOT EXISTS db_spring DEFAULT CHARACTER SET utf8 ;
USE db_spring ;

CREATE TABLE IF NOT EXISTS FastFood (
  fastfood_id BIGINT NOT NULL AUTO_INCREMENT,
  fastfood_name VARCHAR(45) NOT NULL,
  author VARCHAR(45) NOT NULL,
  seller VARCHAR(50) NULL,
  year_of_creating INT NULL,
  amount INT NOT NULL,
  PRIMARY KEY (fastfood_id)
  ) ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS city (
  city_id BIGINT NOT NULL AUTO_INCREMENT,
  city VARCHAR(25) NOT NULL,
  PRIMARY KEY (city_id)
  ) ENGINE = InnoDB
AUTO_INCREMENT = 1 
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS FastFoodMarket (
  fastfoodmarket_id BIGINT NOT NULL AUTO_INCREMENT,
  fastfoodmarket_name VARCHAR(25) NOT NULL,
  fastfoodmarket_surname VARCHAR(25) NOT NULL,
  email VARCHAR(45) NULL,
  city_id BIGINT NULL,
  street VARCHAR(30) NULL,
  apartment VARCHAR(10) NULL,
  PRIMARY KEY (fastfoodmarket_id),
  CONSTRAINT fk_market_fastfood1
    FOREIGN KEY (city_id)
    REFERENCES db_spring.city (city_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS market_fastfood (
  fastfoodmarket_id BIGINT NOT NULL,
  fastfood_id BIGINT NOT NULL,
  PRIMARY KEY (fastfoodmarket_id, fastfood_id),
  CONSTRAINT marketfastfood_ibfk_1
    FOREIGN KEY (fastfoodmarket_id)
    REFERENCES db_spring.fastfoodmarket (fastfoodmarket_id),
  CONSTRAINT marketfastfood_ibfk_2
    FOREIGN KEY (fastfood_id)
    REFERENCES db_spring.fastfood (fastfood_id)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS logger (
  logger_id BIGINT NOT NULL AUTO_INCREMENT,
  fastfood VARCHAR(50) NOT NULL,
  fastfoodmarket VARCHAR(90) NOT NULL,
  action VARCHAR(10) NOT NULL,
  time_stamp DATETIME NOT NULL,
  user VARCHAR(50) NULL,
  PRIMARY KEY (logger_id)
) ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;









