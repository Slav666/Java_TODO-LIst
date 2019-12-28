create table TODOs(
    id int unsigned primary key auto_increment,
    text varchar(100) not null,
    done bit
);
insert into TODOs(text, done) values ('Done todo', 1);
insert into TODOs(text, done) values ('Undone todo', 0);