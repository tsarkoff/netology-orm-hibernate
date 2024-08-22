package ru.netology.ormhibernate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.netology.ormhibernate.entity.Person;
import ru.netology.ormhibernate.service.OrmHibernateService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class OrmHibernateController {
    private final OrmHibernateService ormHibernateService;

    @GetMapping("/init")
    public void init() {
        ormHibernateService.init();
    }

    @GetMapping("/by-city")
    public List<Person> getPersonsByCity(@RequestParam("city") String city) {
        return ormHibernateService.getPersonsByCity(city);
    }
}
