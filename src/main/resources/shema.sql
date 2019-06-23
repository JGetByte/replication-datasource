create table USER(
  ID int not null AUTO_INCREMENT,
  USER_NAME varchar(100) not null,
  FIRST_NAME varchar(30),
  LAST_NAME varchar(30),
  PRIMARY KEY ( ID )
);

insert into user values(1, 'slave_user_name', 'slave_first_name', 'slave_last_name');