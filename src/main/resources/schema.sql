drop table if exists user;
drop table if exists query;

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

create table query (
  id identity,
  username varchar(50) not null,
  fromCurrency varchar(3) not null,
  toCurrency varchar(3) not null,
  queriedDate date not null,
  rate decimal not null,
  postedTime datetime not null,
  foreign key (username) references user(username)
);



