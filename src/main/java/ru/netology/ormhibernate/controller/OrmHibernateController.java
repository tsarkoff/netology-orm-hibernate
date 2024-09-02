package ru.netology.ormhibernate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.netology.ormhibernate.entity.Person;
import ru.netology.ormhibernate.service.OrmHibernateService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class OrmHibernateController {
    private final OrmHibernateService ormHibernateService;

    /**
     * Push new person list exclude duplicates (CRUD - Create)
     *
     * @param persons person list to be saved
     * @return saved person list or empty list if was not saved
     */
    @PostMapping
    public List<Person> savePersons(@Valid @RequestBody List<Person> persons) {
        return ormHibernateService.savePersons(persons);
    }

    /**
     * Get persons (historical records with different TimeStamp) by Human ID (CRUD - Read)
     *
     * @param id person (human) identifier to be found
     * @return found person or null if was not found
     */
    @GetMapping("/{id}")
    public List<Person> getPersonsById(@PathVariable("id") long id) {
        return ormHibernateService.findAllPersonsByHumanId(id);
    }

    /**
     * Get full person list or person list by optional name and/or surname (CRUD - Read)
     *
     * @param name    optional person name
     * @param surname optional person surname
     * @return found person list or empty list if was not found
     */
    @GetMapping
    public List<Person> getPersons(
            @RequestParam("name") Optional<String> name,
            @RequestParam("surname") Optional<String> surname) {
        return ormHibernateService.findPersonsByHumanNameAndHumanSurname(name, surname);
    }

    /**
     * Get person list by city of living (CRUD - Read)
     *
     * @param city person's city of living
     * @return person list or empty list if was not found
     */
    @GetMapping("/by-city")
    public List<Person> getPersonsByCity(@RequestParam("city") String city) {
        return ormHibernateService.findPersonsByCityOfLiving(city);
    }

    /**
     * Get person list by age, where age is less than pointed in param (CRUD - Read)
     *
     * @param age person age is the top bound for search
     * @return person list or empty list if was not found
     */
    @GetMapping("/by-age")
    public List<Person> getPersonsByAge(@RequestParam("age") int age) {
        return ormHibernateService.findPersonsByHumanAgeLessThanOrderByHumanAgeDesc(age);
    }

    /**
     * Update person or create a new one if was not found
     *
     * @param person person to be updated, if was not found then create a new one
     * @return person updated or created
     */
    @PutMapping
    public Person putPerson(@Valid @RequestBody Person person) {
        return ormHibernateService.savePerson(person);
    }

    /**
     * Delete person (CRUD - Delete)
     *
     * @param person person to be deleted
     * @return deleted person Human ID of -1 if was not found
     */
    @DeleteMapping
    public long deletePerson(@Valid @RequestBody Person person) {
        return ormHibernateService.deletePerson(person);
    }

    /**
     * Delete all person records by Human ID (CRUD - Delete)
     *
     * @param id person (human) identifier to delete a person records
     * @return Human ID of deleted person or -1 if person was not found
     */
    @DeleteMapping("/{id}")
    public long deletePersonById(@PathVariable long id) {
        return ormHibernateService.deletePersonByHumanId(id);
    }

    /**
     * Delete all persons (CRUD - Delete)
     *
     * @return deleted person Human ID of -1 if was not found
     */
    @DeleteMapping("/all")
    public long deletePersons() {
        return ormHibernateService.deleteAllPersons();
    }
}
