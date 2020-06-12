drop database if exists hotel_booking; -- 数据库名不能用中划线-
create database hotel_booking;
use hotel_booking;
/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/6/12 18:17:09                           */
/*==============================================================*/


drop table if exists hotel;

drop table if exists orders;

drop table if exists room;

drop table if exists user;

/*==============================================================*/
/* Table: hotel                                                 */
/*==============================================================*/
create table hotel
(
   hid                  varchar(255) not null,
   name                 varchar(255) not null,
   translated_name      varchar(255) not null,
   address              varchar(255) not null,
   city                 varchar(255) not null,
   state                varchar(255) not null,
   country              varchar(255) not null,
   star_rating          decimal not null,
   url                  varchar(2083) not null,
   overview             varchar(500) not null,
   rating_average       decimal not null,
   checkin              time not null,
   checkout             time not null,
   photo1               varchar(2083) not null,
   photo2               varchar(2083) not null,
   photo3               varchar(2083) not null,
   photo4               varchar(2083) not null,
   photo5               varchar(2083) not null,
   primary key (hid)
);

/*==============================================================*/
/* Table: "orders"                                               */
/*==============================================================*/
create table orders
  (
      hid                  varchar(255) not null,
      uid                  varchar(255) not null,
      rid                  varchar(255) not null,
      oid                  varchar(255) not null,
      checkinD             date not null,
      checkoutD            date not null,
      number               int not null,
      price                int not null,
      payed                int not null,
      ordertime            datetime not null,
      remark               varchar(500) not null,
      finished             int not null,
      primary key (hid, uid, rid, oid)
  );

/*==============================================================*/
/* Table: room                                                  */
/*==============================================================*/
create table room
(
   rid                  varchar(255) not null,
   hid                  varchar(255) not null,
   area                 varchar(255) not null,
   floor                varchar(255) not null,
   window               varchar(255) not null,
   wifi                 varchar(255) not null,
   capacity             int not null,
   bed_info             varchar(255) not null,
   fee_policy           varchar(255) not null,
   facility             varchar(255) not null,
   bathroom             varchar(255) not null,
   price                int not null,
   number               int not null,
   type                 varchar(255) not null,
   primary key (rid)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
  (
      uid                  varchar(255) not null,
      password             varchar(255) not null,
      name                 varchar(255),
      primary key (uid)
  );

alter table orders add constraint FK_Relationship_1 foreign key (hid)
      references hotel (hid) on delete restrict on update restrict;

alter table orders add constraint FK_Relationship_2 foreign key (uid)
    references user (uid) on delete restrict on update restrict;

alter table orders add constraint FK_Relationship_3 foreign key (rid)
    references room (rid) on delete restrict on update restrict;

alter table room add constraint FK_Relationship_4 foreign key (hid)
    references hotel (hid) on delete restrict on update restrict;


