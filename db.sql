drop database if exists springboot_demo; -- 数据库名不能用中划线-
create database springboot_demo;
use springboot_demo;
create table `Admin`(
  id int auto_increment,
  loginName varchar(255),
  password varchar(64),
  lastLoginTime datetime,
  remark  varchar(255),
  primary key (id)
);
insert into Admin(loginName,password,remark) values ('admin','admin','测试数据:管理员用户');

create table `Book`(
  id int auto_increment,
  name varchar(255),
  price double,
  author varchar(255),
  creatorId int ,
  remark  varchar(255),
  primary key (id)
);


create table `student`(
  id int auto_increment,
   name varchar(255),
  primary key (id)

);