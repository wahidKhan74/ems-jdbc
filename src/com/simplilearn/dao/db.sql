create database 'emsdb';
use emsdb;

create table employees (
id int(11) NOT NULL AUTO_INCREMENT,
name varchar(30) NOT NULL,
age int(11),
email varchar(30) NOT NULL,
salary int(11),
dept varchar(30),
PRIMARY KEY(id)
);
