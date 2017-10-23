CREATE TABLE IF NOT EXISTS Enviroment_Content (
`key` VARCHAR(20) NOT NULL,
`value` VARCHAR(100) NOT NULL,
PRIMARY KEY (`key`),
UNIQUE INDEX `key_UNIQUE` (`key` ASC));


CREATE TABLE IF NOT EXISTS User_Token (
`user_name` VARCHAR(20) NOT NULL,
`token` VARCHAR(500) NOT NULL,
PRIMARY KEY (`user_name`),
UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC));
  
