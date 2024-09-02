-- liquibase formatted sql

--changeset tsarkoff:003 failOnError:false
insert into public.person (id, name, surname, age, phone_number, city_of_living)
values (1, 'Анна', 'Оболонская', 16, '+7-4872-88-44-33', 'Ясная Поляна'),
       (2, 'Пётр', 'Иванов', 19, '+7-812-888-11-22', 'Санкт-Петербург'),
       (3, 'Иван', 'Петров', 25, '+7-495-444-99-55', 'Москва'),
       (4, 'Александр', 'Пушкин', 33, '+7-862-888-66-22', 'Сочи'),
       (4, 'Martin', 'Gor', 50, '+1-777-777-77-77', 'Boston');
--rollback drop table contacts.customers;