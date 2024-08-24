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
     * @param persons person list to save
     * @return saved person list
     */
    @PostMapping
    public List<Person> savePersons(@Valid @RequestBody List<Person> persons) {
        return ormHibernateService.savePersons(persons);
    }

    /**
     * Get person by Human ID if found (CRUD - Read)
     *
     * @param id person (human) identifier
     * @return found person
     */
    @GetMapping("/{id}")
    public Optional<Person> getPersonsById(@PathVariable("id") long id) {
        return ormHibernateService.findByHumanId(id);
    }

    /**
     * Get person list by optional name and/or surname (CRUD - Read)
     *
     * @param name    person name
     * @param surname person surname
     * @return found person list
     */
    @GetMapping
    public List<Person> getPersonsByNameAndSurname(
            @RequestParam("name") Optional<String> name,
            @RequestParam("surname") Optional<String> surname) {
        return ormHibernateService.findByHumanNameAndHumanSurname(name, surname);
    }

    /**
     * Get person list by city of living (CRUD - Read)
     *
     * @param city person city of living
     * @return person list
     */
    @GetMapping("/by-city")
    public List<Person> getPersonsByCity(@RequestParam("city") String city) {
        return ormHibernateService.findByCityOfLiving(city);
    }

    /**
     * Get person list by age less than pointed (CRUD - Read)
     *
     * @param age person age
     * @return person list
     */
    @GetMapping("/by-age")
    public List<Person> getPersonsByAge(@RequestParam("age") int age) {
        return ormHibernateService.findByHumanAgeLessThanOrderByHumanAgeDesc(age);
    }

    /**
     * Update only person fields by Human ID or ignore if human fields updated (CRUD - Update)
     *
     * @param person input person
     * @param id     person (human) identifier
     * @return person updated
     */
    @PutMapping("/{id}/update-person-data")
    public Optional<Person> updatePersonFieldsByHumanId(@Valid @RequestBody Person person, @PathVariable long id) {
        return ormHibernateService.updatePersonFieldsByHumanId(person, id);
    }

    /**
     * JPQL Update all person fields by Human ID, including human fields except human id (CRUD - Update)
     *
     * @param person input person
     * @param id     person (human) identifier
     */
    @PutMapping("/{id}/pure-update-person-human-data")
    public void updatePerson(@Valid @RequestBody Person person, @PathVariable long id) {
        ormHibernateService.jpqlUpdateFullPersonByHumanId(person, id);
    }

    /**
     * Update by Human ID the person fields only or re-INSERT new person if human fields changed (CRUD - Update)
     *
     * @param person input person
     * @param id     person (human) identifier
     * @return person updated
     */
    @PutMapping("/{id}/reinsert-person-human")
    public Optional<Person> updateOrReinsertPersonByHumanId(@Valid @RequestBody Person person, @PathVariable long id) {
        return ormHibernateService.updateOrReinsertPersonByHumanId(person, id);
    }

    /**
     * Delete person by Human ID (CRUD - Delete)
     *
     * @param id person (human) identifier
     * @return Human ID of deleted person or -1 if person not found
     */
    @DeleteMapping("/{id}")
    public long deletePerson(@PathVariable long id) {
        return ormHibernateService.deletePersonByHumanId(id);
    }

    /**
     * Delete ALL persons (CRUD - Delete)
     *
     * @return operation result
     */
    @DeleteMapping
    public boolean deleteAllPersons() {
        return ormHibernateService.deleteAllPersons();
    }
}
