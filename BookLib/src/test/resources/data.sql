insert into authors (name)
values ('Isaac Asimov'),
       ('Jack London'),
       ('New author');

insert into genres (name)
values ('Fantasy'),
       ('Adventure');

insert into books (title, author_id, genre_id)
values ('Bicentennial man', 1, 1),
       ('White Fang', 2, 2),
       ('New adventure book', 3, 2);

insert into comments (text, book_id)
values ('Good book', 1)