package ru.netology.ormhibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netology.ormhibernate.entity.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Person.Human> {
    List<Person> findAllPersonsByHumanId(long id);
    List<Person> findPersonsByHumanNameAndHumanSurnameIgnoreCase(Optional<String> name, Optional<String> surname);
    List<Person> findPersonsByHumanNameIgnoreCase(Optional<String> name);
    List<Person> findPersonsByHumanSurnameIgnoreCase(Optional<String> surname);
    List<Person> findPersonsByCityOfLiving(String cityOfLiving);
    List<Person> findPersonsByHumanAgeLessThanOrderByHumanAgeDesc(int age);
}
