package ru.netology.ormhibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.netology.ormhibernate.entity.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByHumanId(long id);

    List<Person> findByHumanNameIgnoreCase(Optional<String> name);

    List<Person> findByHumanSurnameIgnoreCase(Optional<String> surname);

    List<Person> findByHumanNameAndHumanSurnameIgnoreCase(Optional<String> name, Optional<String> surname);

    List<Person> findByCityOfLivingIgnoreCase(String cityOfLiving);

    List<Person> findByHumanAgeLessThanOrderByHumanAgeDesc(int age);

    @Transactional
    @Modifying
    @Query("""
            UPDATE Person p SET
                p.human.name=:name,
                p.human.surname=:surname,
                p.human.age=:age,
                p.phoneNumber=:phone,
                p.cityOfLiving=:city
            WHERE p.human.id=:id
            """)
    void jpqlUpdateFullPersonByHumanId(
            @Param("name") String name,
            @Param("surname") String surname,
            @Param("age") int age,
            @Param("phone") String phone,
            @Param("city") String city,
            @Param("id") long id
    );
}
