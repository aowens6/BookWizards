use book_wizards;

set sql_safe_updates=0;
delete from book;
delete from author;
delete from genre;
delete from app_user_role;
delete from app_role;
delete from app_user;
set sql_safe_updates=1;

alter table book auto_increment=1;
alter table author auto_increment=1;
alter table genre auto_increment=1;
alter table app_role auto_increment=1;
alter table app_user auto_increment=1;

insert into genre (genre_name)
values 
	("Self Help"),
	("Romance"),
	("Horror");
    
insert into author (first_name, last_name)
values
	("Steven", "King"),
    ("Angela", "Lansbury"),
    ("JK", "Rowling");
    
insert into book (title, author_id, genre_id)
values
	("It", 1, 3),
    ("Murder She Wrote", 2, 1),
    ("Harry Potter", 3, 1);

