drop table if exists user;

create table user (
  id identity,
  email varchar(50) not null,
  password varchar(10) not null,
  dateOfBirth varchar(10) not null,
  street varchar(50) not null,
  zipCode varchar(5) not null,
  city varchar(50) not null,
  country varchar(50) not null
);

