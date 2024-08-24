package ru.netology.ormhibernate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.ormhibernate.entity.Person;
import ru.netology.ormhibernate.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrmHibernateService {
    private final PersonRepository personRepository;

    // POST - Save - CRUD - Create
    public int savePersons(List<Person> persons) {
        int count = 0;
        for (Person p : persons) {
            count += personRepository.savePerson(
                    p.getHuman().getId(),
                    p.getHuman().getName(),
                    p.getHuman().getSurname(),
                    p.getHuman().getAge(),
                    p.getPhoneNumber(),
                    p.getCityOfLiving()
            );
        }
        return count;
    }

    // GET - Get - CRUD - Read
    public Optional<Person> findByHumanId(long id) {
        return personRepository.findByHumanId(id);
    }

    // GET - Get - CRUD - Read
    public List<Person> findByCityOfLiving(String city) {
        return personRepository.findByCityOfLiving(city);
    }

    // GET - Get - CRUD - Read
    public List<Person> findByHumanAgeLessThanOrderByHumanAgeDesc(int age) {
        return personRepository.findByHumanAgeLessThanOrderByHumanAgeDesc(age);
    }

    // GET - Get - CRUD - Read
    public List<Person> findByHumanNameAndHumanSurname(Optional<String> name, Optional<String> surname) {
        if (name.isPresent() && surname.isEmpty())
            return personRepository.findByHumanName(name);
        if (name.isEmpty() && surname.isPresent())
            return personRepository.findByHumanSurname(surname);
        if (name.isEmpty())
            return personRepository.findAll();
        return personRepository.findByHumanNameAndHumanSurname(name, surname);
    }

    // PUT - Update - CRUD - Update
    public int updatePersonFieldsByHumanId(Person person, long id) {
        return personRepository.updatePersonFieldsByHumanId(
                person.getPhoneNumber(),
                person.getCityOfLiving(),
                id);
    }

    // PUT - Update - CRUD - Update
    public int updateFullPersonByHumanId(Person person, long id) {
        return personRepository.updateFullPersonByHumanId(
                person.getHuman().getName(),
                person.getHuman().getSurname(),
                person.getHuman().getAge(),
                person.getPhoneNumber(),
                person.getCityOfLiving(),
                id);
    }

    // PUT - Update - CRUD - Update
    public int updateOrReinsertPersonByHumanId(Person person, long id) {
        return updateFullPersonByHumanId(person, id);
    }

    // DELETE - Delete - CRUD - Delete
    public long deletePersonByHumanId(long id) {
        return personRepository.deletePersonByHumanId(id);
    }

    // DELETE - Delete - CRUD - Delete
    public int deleteAllPersons() {
        return personRepository.delete();
    }
}
