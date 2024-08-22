package ru.netology.ormhibernate.repository;

import ru.netology.ormhibernate.entity.Person;

import java.util.List;

public interface OrmHibernateRepository {
    void init();
    List<Person> getPersonsByCity(String city);
}
