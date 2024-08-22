package ru.netology.ormhibernate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.ormhibernate.entity.Person;
import ru.netology.ormhibernate.repository.OrmHibernateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrmHibernateService {
    private final OrmHibernateRepository ormHibernateRepository;

    public void init() {
        ormHibernateRepository.init();
    }

    public List<Person> getPersonsByCity(String city) {
        return ormHibernateRepository.getPersonsByCity(city);
    }
}
