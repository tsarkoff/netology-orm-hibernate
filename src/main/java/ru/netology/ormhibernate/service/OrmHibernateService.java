package ru.netology.ormhibernate.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.ormhibernate.entity.Person;
import ru.netology.ormhibernate.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrmHibernateService {
    private final PersonRepository personRepository;

    // POST - Save - CRUD - Create
    public List<Person> savePersons(List<Person> persons) {
        return personRepository.saveAll(persons);
    }

    // GET - Get - CRUD - Read
    public List<Person> findAllPersonsByHumanId(long id) {
        return personRepository.findAllPersonsByHumanId(id);
    }

    // GET - Get - CRUD - Read
    public List<Person> findPersonsByHumanNameAndHumanSurname(Optional<String> name, Optional<String> surname) {
        if (name.isPresent() && surname.isEmpty())
            return personRepository.findPersonsByHumanNameIgnoreCase(name);
        if (name.isEmpty() && surname.isPresent())
            return personRepository.findPersonsByHumanSurnameIgnoreCase(surname);
        if (name.isEmpty())
            return personRepository.findAll();
        return personRepository.findPersonsByHumanNameAndHumanSurnameIgnoreCase(name, surname);
    }

    // GET - Get - CRUD - Read
    public List<Person> findPersonsByCityOfLiving(String city) {
        return personRepository.findPersonsByCityOfLiving(city);
    }

    // GET - Get - CRUD - Read
    public List<Person> findPersonsByHumanAgeLessThanOrderByHumanAgeDesc(int age) {
        return personRepository.findPersonsByHumanAgeLessThanOrderByHumanAgeDesc(age);
    }

    // PUT - Update - CRUD - Update
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    // DELETE - Delete - CRUD - Delete
    public long deletePerson(Person person) {
        Optional<Person> personToDelete = personRepository.findById(person.getHuman());
        personToDelete.ifPresent(personRepository::delete);
        return personToDelete.map(value -> value.getHuman().getId()).orElse(-1L);
    }

    // DELETE - Delete - CRUD - Delete
    public long deletePersonByHumanId(long id) {
        List<Person> persons = personRepository.findAllPersonsByHumanId(id);
        personRepository.deleteAll(persons);
        return persons.isEmpty() ? -1L : id;
    }

    // DELETE - Delete - CRUD - Delete
    public long deleteAllPersons() {
        long count = personRepository.findAll().size();
        personRepository.deleteAll();
        return count;
    }
}
