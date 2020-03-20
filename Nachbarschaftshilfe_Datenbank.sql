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

create table angebot(
	id bigint not null auto_increment,
	name varchar(32),
	description varchar(1024),
	typ varchar(16),
	user varchar(32) not null,
	price varchar(16) not null,
	
	primary key(id),
	foreign key (user) references user (email)
);

insert into user (email, name, password, token) values
	("a","Name","0","0");

insert into angebot(name, description, typ, user, price) values
	("Eier", "ich brauche eier lol", "suche", "a", "2€"),
	("Klopapier", "Ich habe 50 Rollen Toilettenpapier zu verkaufen", "angebot", "a", "Verhandelbar"),
	("Klopapier", "Ich habe 50 Rollen Toilettenpapier zu verkaufen", "angebot", "a", "Verhandelbar"),
	("Klopapier", "Ich habe 50 Rollen Toilettenpapier zu verkaufen", "angebot", "a", "Verhandelbar")
;