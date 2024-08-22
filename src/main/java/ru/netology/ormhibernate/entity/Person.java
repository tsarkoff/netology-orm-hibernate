package ru.netology.ormhibernate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Person {
    @Embeddable
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Human implements Serializable {
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        private String name;
        private String surname;
        private int age;
    }

    @EmbeddedId
    private Human human;

    @Column
    private String phoneNumber;

    @Column
    private String cityOfLiving;
}
