drop table if exists car;
drop table if exists clientsorder;

create table client
(
    id bigserial primary key,
    first_name char(25) not null,
    last_name  char(30),
    age integer check (age >= 18),
    rating float check (rating > 0.0 and rating <= 5.0) not null
);

create table driver
(
    id bigserial primary key,
    first_name char(25) not null,
    last_name  char(30),
    age integer check (age >= 18),
    rating float check (rating > 0.0 and rating <= 5.0) not null
);

create table car
(
    id bigserial primary key,
    brand char(20),
    dateOfRelease date,
    isElectrical boolean,
    driversId bigint
);

alter table car add foreign key(driversId) references driver(id);

create table clientsOrder
(
    id bigserial primary key,
    clientsID bigint unique not null,
    driversID bigint unique not null,
    start_time time,
    finish_time time,
    costOfTheTrip integer check (costOfTheTrip > 0),
    clientsEvaluation integer check (clientsEvaluation >= 1 and clientsEvaluation <= 5),
    driversEvaluation integer check (driversEvaluation >= 1 and driversEvaluation <= 5),
    commentaries char(100)
);

alter table clientsOrder add foreign key (clientsID) references client(id);

alter table clientsOrder add foreign key (clientsID) references driver(id);

