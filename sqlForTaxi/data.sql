insert into client(first_name, last_name, age, rating)
VALUES ('Azat', 'Nasyrov', 19, 5.0),
       ('Amir', 'Shakurov', 25, 2.8),
       ('Elena', 'Morozova', 26, 4.7),
       ('Ahmed', 'Aliashabov', 30, 3.8);

insert into driver(first_name, last_name, age, rating)
VALUES ('Mihail', 'Tals', 55, 4.9),
       ('James', 'Bond', 53, 5.0),
       ('Naruto', 'Uzumaki', 32, 4.6),
       ('Valeryi', 'Opanevich', 21, 2.5);

insert into car(brand, dateofrelease, iselectrical, driversid)
VALUES ('Mercedes', '2013-04-01', false, 1),
       ('Mazda', '2020-01-01', true, 2),
       ('Lada', '2016-07-08', true, 3),
       ('BMW', '2000-03-09', false, 4);

insert into clientsorder(clientsid, driversid, start_time, finish_time, costofthetrip, clientsevaluation, driversevaluation, commentaries)
VALUES (1, 3, '11:21:22', '11:29:32', 325, 5, 3, 'That was best taxi in my life!'),
       (2, 4, '22:05:17', '22:10:52', 280, 2, 4, 'Bad driver'),
       (3, 1, '02:03:22', '02:25:32', 512, 5, 1, 'Thanks for service'),
       (4, 2, '18:58:45', '19:04:43', 160, 5, 5, 'It was really fast but safe');


update clientsorder
set driversevaluation = 4
where id = 1;