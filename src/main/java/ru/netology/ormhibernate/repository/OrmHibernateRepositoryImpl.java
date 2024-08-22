package ru.netology.ormhibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.netology.ormhibernate.entity.Person;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrmHibernateRepositoryImpl implements OrmHibernateRepository {
    private final EntityManager entityManager;

    private final static int RECORDS = 3;

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
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < RECORDS; i++) {
            Person.Human human = humans.get(i);
            Person person = entityManager.find(Person.class, human);
            if (person.getCityOfLiving().equalsIgnoreCase(city)) {
                persons.add(person);
            }
        }
        return persons;
    }

    @Override
    @Transactional
    public void init() {
        for (Person person : persons)
            entityManager.persist(person);
    }
}
