package ru.netology.ormhibernate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "person", schema = "public")
public class Person {
    @Embeddable
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Human implements Serializable {
        @Column(name = "id", nullable = false)
        private long id;
        @Column(name = "name", nullable = false)
        private String name;
        @Column(name = "surname", nullable = false)
        private String surname;
        @Column(name = "age", nullable = false)
        private int age;
    }

    @EmbeddedId
    private Human human;

    @Column(name ="phone_number", nullable = false)
    private String phoneNumber;

    @Column(name ="city_of_living", nullable = false)
    private String cityOfLiving;

    @Column(name ="timestamp")
    @UpdateTimestamp
    private LocalDateTime timeStamp;
}
