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
    

insert into app_role (`name`) values
    ('USER'),
    ('ADMIN');

-- passwords are set to "P@ssw0rd!"
-- $2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa
-- ehCX7MexUCb&T7
-- $2a$10$OOzG0kG/hJ/2mHmq6nuPZ.0q71b3mm/sYthN93PgX6Q2XBDUNLu.K
insert into app_user (username, password_hash, disabled)
    values
    ('john@smith.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0),
    ('sally@jones.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0);

insert into app_user_role
    values
    (1, 2),
    (2, 1);
    
insert into meeting (group_name, description, book_id, organizer_id, start_date_time, end_date_time)
values
	("Book Wizards", "reading is fundamental", 1, 1, '2022-07-07 12:00:00', '2022-07-07 12:45:00'),
    ("Wine Moms", "Dostoyevski and alcohol", 1, 1, '2022-07-07 15:00:00', '2022-07-07 15:45:00'),
    ("Prose Before Bros", "reading.", 1, 1, '2022-07-07 16:00:00', '2022-07-07 16:45:00');
    
insert into meeting_attendee
values 
 (1, 2),
 (1, 1),
 (2, 1),
 (3, 2);
    
    
    