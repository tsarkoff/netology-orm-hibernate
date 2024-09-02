-- liquibase formatted sql

--changeset tsarkoff:004 failOnError:false
insert into public.users (username, password, enabled)
values ('teacher', '$2a$10$ws3480a0PB0leQ0opcY8/ujVrIkcArc5RKFheV2kmfGzuHYVrkSo6', true),
       ('postman', '$2a$10$Qnk.nhS/UYBPW3zcwLGOsuWD/PhrlHXLBwDdlPesEpZ.3VioJUlia', true),
--     To escape pwd encoding and using password '{noop}pwd' needs to exclude PasswordEncoder from WebConfig:
--     @Bean public PasswordEncoder encoder() {return new BCryptPasswordEncoder(); }
       ('policeman', '$2a$10$rgqkiQk8GD4/nqELkolze.OvQWfZPhkZmYB0o7DB7P43.m7oWNQg.', true),
       ('government', '$2a$10$042CHnIjYBMuitFgBvfoU.FHG0AzIUswCgeJrbjXBiDXswnT00W1e', true),
       ('security', '$2a$10$MNWJsRUF/t2ZjpzS1IXSGePIDBi6xBpwj6RpuJi3KFgEeLAou2swu', true),
       ('God', '$2a$10$06n6MbaCKVqJrwkb3MajxurUN8JOkSOJMVNyDIuQsTS/x8QTYjl6W', true),
       ('employee', '$2a$10$St0o0QxZNnfZY8V4bGfHyewssNhwch9VPM3KmHhtvhvk0vxobVqu6', true),
       ('employer', '$2a$10$6s7.w9bwvd1KVioaBl1LWud/U6Jcs/tn0nzuafP8wjBHHwonYGtpu', true),
       ('boss', '$2a$10$m8csaVIvbV6DQKRaGlfZ0.AdmuoDAXUuWdPt9crfUcoFkGeB7g2Te', true);

insert into public.authorities (username, authority)
values ('teacher', 'READ_AGE'),
       ('postman', 'READ_ADDRESS'),
       ('policeman', 'READ_AGE'),
       ('policeman', 'READ_ADDRESS'),
       ('policeman', 'READ_ALL'),
       ('government', 'UPDATE_ONE'),
       ('security', 'WRITE_MANY'),

       ('God', 'READ_AGE'),
       ('God', 'READ_ADDRESS'),
       ('God', 'READ_ALL'),
       ('God', 'UPDATE_ONE'),
       ('God', 'WRITE_MANY'),
       ('God', 'DELETE_ANY'),

       ('employee', 'ROLE_READ'),
       ('employer', 'ROLE_WRITE'),
       ('boss', 'ROLE_DELETE');
