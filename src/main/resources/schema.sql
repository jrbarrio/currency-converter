drop table if exists user;

create table user (
  username varchar(50) primary key,
  password varchar(10) not null,
  email varchar(50) not null,
  dateOfBirth date not null,
  street varchar(50) not null,
  zipCode varchar(5) not null,
  city varchar(50) not null,
  country varchar(50) not null
);



