drop database if exists nachbarschaftshilfe;

create database nachbarschaftshilfe;

use nachbarschaftshilfe;

create table user(
	email varchar(32) not null,
	name varchar(32),
	password varchar(64) not null,
	token varchar(64),
	
	primary key(email)
);

