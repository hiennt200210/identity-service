CREATE DATABASE identity_service_db;

USE identity_service_db;

CREATE TABLE user (
	user_id CHAR(36),
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    gender ENUM("Male", "Female", "Other") NOT NULL,
    date_of_birth DATE NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id)
);
