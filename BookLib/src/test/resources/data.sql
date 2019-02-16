insert into authors (id, name)
values (1, 'Isaac Asimov'),
       (2, 'Jack London'),
       (3, 'New author');

insert into genres (id, name)
values (1, 'Fantasy'),
       (2, 'Adventure');

insert into books (id, title, author_id, genre_id)
values (1, 'Bicentennial man', 1, 1),
       (2, 'White Fang', 2, 2),
       (3, 'New adventure book', 3, 2);

insert into comments (id, text, book_id)
values (1, 'Good book', 1)