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

    @Transactional
    @Modifying
    @Query(value = """
            INSERT INTO Person (id, name, surname, age, phone_number, city_of_living)
            VALUES (:id, :name, :surname, :age, :phone, :city)
            ON CONFLICT (id, name, surname, age)
                DO UPDATE SET id = :id, name = :name, surname = :surname, age = :age, phone_number = :phone, city_of_living = :city;
            """,
            nativeQuery = true)
    int savePerson(
            @Param("id") long id,
            @Param("name") String name,
            @Param("surname") String surname,
            @Param("age") int age,
            @Param("phone") String phone,
            @Param("city") String city
    );

    @Query("SELECT p FROM Person p WHERE p.human.id = :id")
    Optional<Person> findByHumanId(@Param("id") long id);

    @Query("SELECT p FROM Person p WHERE lower(p.human.name) = lower(:name)")
    List<Person> findByHumanName(@Param("name") Optional<String> name);

    @Query("SELECT p FROM Person p WHERE lower(p.human.surname) = lower(:surname)")
    List<Person> findByHumanSurname(@Param("surname") Optional<String> surname);

    @Query("SELECT p FROM Person p WHERE lower(p.human.name) = lower(:name) AND lower(p.human.surname) = lower(:surname)")
    List<Person> findByHumanNameAndHumanSurname(@Param("name") Optional<String> name, @Param("surname") Optional<String> surname);

    @Query("SELECT p FROM Person p WHERE lower(p.cityOfLiving) = lower(:city)")
    List<Person> findByCityOfLiving(@Param("city") String cityOfLiving);

    @Query("SELECT p FROM Person p WHERE p.human.age < :age ORDER BY p.human.age")
    List<Person> findByHumanAgeLessThanOrderByHumanAgeDesc(int age);

    @Transactional
    @Modifying
    @Query("""
            UPDATE Person p SET
                p.phoneNumber=:phone,
                p.cityOfLiving=:city
            WHERE p.human.id=:id
            """)
    int updatePersonFieldsByHumanId(
            @Param("phone") String phone,
            @Param("city") String city,
            @Param("id") long id
    );

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
    int updateFullPersonByHumanId(
            @Param("name") String name,
            @Param("surname") String surname,
            @Param("age") int age,
            @Param("phone") String phone,
            @Param("city") String city,
            @Param("id") long id
    );

    @Transactional
    @Modifying
    @Query("DELETE FROM Person p WHERE p.human.id = :id")
    int deletePersonByHumanId(@Param("id") long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Person p")
    int delete();
}
