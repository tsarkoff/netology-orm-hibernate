package ru.netology.ormhibernate;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrmHibernateApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OrmHibernateApp.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
