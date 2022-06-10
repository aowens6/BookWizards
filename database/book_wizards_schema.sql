drop database if exists book_wizards;
create database book_wizards;
use book_wizards;

create table author (
	author_id int primary key auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null
);

create table genre (
	genre_id int primary key auto_increment,
    genre_name varchar(50) not null
);

create table book (
	book_id int primary key auto_increment,
    title varchar(75) not null,
    author_id int not null,
    genre_id int not null,
	constraint fk_book_author_author_id
		foreign key (author_id)
		references author(author_id),
	constraint fk_book_genre_genre_id
		foreign key (genre_id)
		references genre(genre_id)
);

create table app_user (
	app_user_id int primary key auto_increment,
	username varchar(50) not null unique,
	password_hash varchar(2048) not null,
	disabled bit not null default(0)
);

create table meeting (
	meeting_id int primary key auto_increment,
    group_name varchar(75) not null,
    `description` varchar(500) null,
    book_id int null,
    organizer_id int not null,
    start_date_time datetime not null,
    end_date_time datetime not null,
    constraint fk_meeting_book_book_id
		foreign key (book_id)
		references book(book_id),
	constraint fk_meeting_app_user_app_user_id
		foreign key (organizer_id)
		references app_user(app_user_id)
);

create table meeting_attendee (
	meeting_id int not null,
    app_user_id int not null,
    constraint fk_meeting_attendee_meeting_meeting_id
		foreign key (meeting_id)
		references meeting(meeting_id),
    constraint fk_meeting_attendee_app_user_app_user_id
		foreign key (app_user_id)
		references app_user(app_user_id),
    constraint uk_meeting_attendee
		unique (meeting_id, app_user_id)
);

create table app_role (
	app_role_id int primary key auto_increment,
	`name` varchar(50) not null unique
);

create table app_user_role (
	app_user_id int not null,
	app_role_id int not null,
	constraint pk_app_user_role
		primary key (app_user_id, app_role_id),
	constraint fk_app_user_role_user_id
		foreign key (app_user_id)
		references app_user(app_user_id),
	constraint fk_app_user_role_role_id
		foreign key (app_role_id)
		references app_role(app_role_id)
);