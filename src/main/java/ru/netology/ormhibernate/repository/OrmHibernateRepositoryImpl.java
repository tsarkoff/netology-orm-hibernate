package ru.netology.ormhibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.netology.ormhibernate.entity.Person;

import java.util.List;

@Repository
public class OrmHibernateRepositoryImpl implements OrmHibernateRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final List<Person.Human> humans = List.of(
            Person.Human.builder().name("Ivan").surname("Petrov").age(18).build(),
            Person.Human.builder().name("Peter").surname("Ivanov").age(19).build(),
            Person.Human.builder().name("Denis").surname("Egorov").age(20).build()
    );

    private final List<Person> persons = List.of(
            Person.builder().human(humans.getFirst()).phoneNumber("+7-495-777-888-99").cityOfLiving("Moscow").build(),
            Person.builder().human(humans.get(1)).phoneNumber("+7-812-777-888-99").cityOfLiving("SPb").build(),
            Person.builder().human(humans.getLast()).phoneNumber("+7-843-777-888-99").cityOfLiving("Moscow").build()
    );

    @Override
    @Transactional
    public List<Person> getPersonsByCity(String city) {
        Query jpqlQuery = entityManager.createQuery("SELECT p FROM Person p WHERE lower(p.cityOfLiving) = :city");
        jpqlQuery.setParameter("city", city.toLowerCase());
        return jpqlQuery.getResultList();
    }

    @Override
    @Transactional
    public void init() {
        for (Person person : persons)
            entityManager.persist(person);
    }
}
