package ru.netology.ormhibernate.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import ru.netology.ormhibernate.entity.Person;
import ru.netology.ormhibernate.service.OrmHibernateService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/person-secured")
@RequiredArgsConstructor
@EnableMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class ControllerMethodSecured {
    private final OrmHibernateService ormHibernateService;

    @GetMapping
    @Secured("ROLE_READ")
    public List<Person> getPersons_Secured(@RequestParam Optional<String> username) {
        System.out.println(username);
        return ormHibernateService.findPersonsByHumanNameAndHumanSurname(Optional.empty(), Optional.empty());
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_READ", "ROLE_WRITE"})
    public List<Person> getPersonsById_Secured(@PathVariable Long id) {
        return ormHibernateService.findAllPersonsByHumanId(id);
    }

    @PostMapping
    @RolesAllowed("WRITE")
    public List<Person> savePersons_Secured(@Valid @RequestBody List<Person> persons) {
        return ormHibernateService.savePersons(persons);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('WRITE') or hasRole('DELETE')")
    public long deletePerson_Secured(@PathVariable long id) {
        return ormHibernateService.deletePersonByHumanId(id);
    }

    @DeleteMapping
    @Secured("ROLE_DELETE")
    @PostAuthorize("#username == authentication.principal.username")
    public long deleteAllPersons_Secured(@RequestParam String username) {
        System.out.println(username);
        return ormHibernateService.deleteAllPersons();
    }
}
