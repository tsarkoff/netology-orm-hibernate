package ru.netology.ormhibernate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.ormhibernate.entity.Person;
import ru.netology.ormhibernate.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrmHibernateService {
    private final PersonRepository personRepository;

    // POST - Save - CRUD - Create
    public List<Person> savePersons(List<Person> persons) {
        List<Person> personList = new ArrayList<>();
        for (Person person : persons) {
            personRepository.findByHumanId(person.getHuman().getId()).ifPresent(personRepository::delete);
            personList.add(personRepository.save(person));
        }
        return personList;
    }

    // GET - Get - CRUD - Read
    public Optional<Person> findByHumanId(long id) {
        return personRepository.findByHumanId(id);
    }

    // GET - Get - CRUD - Read
    public List<Person> findByCityOfLiving(String city) {
        return personRepository.findByCityOfLivingIgnoreCase(city);
    }

    // GET - Get - CRUD - Read
    public List<Person> findByHumanAgeLessThanOrderByHumanAgeDesc(int age) {
        return personRepository.findByHumanAgeLessThanOrderByHumanAgeDesc(age);
    }

    // GET - Get - CRUD - Read
    public List<Person> findByHumanNameAndHumanSurname(Optional<String> name, Optional<String> surname) {
        if (name.isPresent() && surname.isEmpty())
            return personRepository.findByHumanNameIgnoreCase(name);
        if (name.isEmpty() && surname.isPresent())
            return personRepository.findByHumanSurnameIgnoreCase(surname);
        if (name.isEmpty())
            return personRepository.findAll();
        return personRepository.findByHumanNameAndHumanSurnameIgnoreCase(name, surname);
    }

    // PUT - Update - CRUD - Update
    public Optional<Person> updatePersonFieldsByHumanId(Person person, long id) {
        Optional<Person> personToUpdate = personRepository.findByHumanId(id);
        if (personToUpdate.isPresent()
                && personToUpdate.get().getHuman().getName().equals(person.getHuman().getName())
                && personToUpdate.get().getHuman().getSurname().equals(person.getHuman().getSurname())
                && personToUpdate.get().getHuman().getAge() == person.getHuman().getAge()) {
            personToUpdate = Optional.of(personRepository.save(person));
        }
        return personToUpdate;
    }

    // PUT - Update - CRUD - Update
    public void jpqlUpdateFullPersonByHumanId(Person person, long id) {
        personRepository.jpqlUpdateFullPersonByHumanId(
                person.getHuman().getName(),
                person.getHuman().getSurname(),
                person.getHuman().getAge(),
                person.getPhoneNumber(),
                person.getCityOfLiving(),
                id);
    }

    // PUT - Update - CRUD - Update
    public Optional<Person> updateOrReinsertPersonByHumanId(Person person, long id) {
        Optional<Person> p = personRepository.findByHumanId(id);
        p.ifPresent(personRepository::delete);
        return Optional.of(personRepository.save(person));
    }

    // DELETE - Delete - CRUD - Delete
    public long deletePersonByHumanId(long id) {
        Optional<Person> personToUpdate = personRepository.findByHumanId(id);
        if (personToUpdate.isPresent()) {
            personRepository.delete(personToUpdate.get());
            return id;
        }
        return -1L;
    }

    // DELETE - Delete - CRUD - Delete
    public boolean deleteAllPersons() {
        personRepository.deleteAll();
        return true;
    }
}
