drop database if exists nachbarschaftshilfe;

create database nachbarschaftshilfe;

use nachbarschaftshilfe;

create table user(
	id bigint not null auto_increment,
	email varchar(32) not null,
	name varchar(32),
	password int not null,
	
	primary key(id)
);