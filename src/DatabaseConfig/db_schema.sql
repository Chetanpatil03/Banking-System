show databases;

create database bank_sys;
use bank_sys;

create table accounts(
acc_number bigint primary key,
full_name varchar(255) not null,
email varchar(255) not null,
balance decimal(10,2),
security_pin char(4));

create table user(
full_name varchar(255) not null,
email varchar(255) primary key,
password varchar(255));

describe user;
describe accounts;
