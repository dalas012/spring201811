insert into authors (id, name)
values (1, 'Isaac Asimov'),
       (2, 'Jack London');

insert into genres (id, name)
values (1, 'Fantasy'),
       (2, 'Adventure');

insert into books (id, title, author_id, genre_id)
values (1, 'Bicentennial man', 1, 1),
       (2, 'White Fang', 2, 2);