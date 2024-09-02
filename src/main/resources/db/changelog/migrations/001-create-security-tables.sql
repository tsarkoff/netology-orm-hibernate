-- liquibase formatted sql

--changeset tsarkoff:001 failOnError:false
create table public.users
(
    username varchar(50)  not null primary key,
    password varchar(500) not null,
    enabled  boolean      not null
);

create table public.authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);
create unique index ix_auth_username on public.authorities (username, authority);
--rollback drop table public.authorities;
-- rollback drop table public.users;
