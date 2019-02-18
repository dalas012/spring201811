insert into authors (name)
values ('Isaac Asimov'),
       ('Jack London');

insert into genres (name)
values ('Fantasy'),
       ('Adventure');

insert into books (title, author_id, genre_id)
values ('Bicentennial man', 1, 1),
       ('White Fang', 2, 2);