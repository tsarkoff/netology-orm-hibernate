-- liquibase formatted sql

--changeset tsarkoff:002 failOnError:false
create table public.person
(
    id             bigint       not null,
    name           varchar(255) not null,
    surname        varchar(255) not null,
    age            integer      not null,
    phone_number   varchar(255) not null,
    city_of_living varchar(255) not null,
    timestamp      timestamp default current_timestamp,
    primary key (id, name, surname, age)
)
--rollback drop table public.person;