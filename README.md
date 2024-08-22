# Использование приложения "Просмотр людей из Москвы"
**Пререквизиты**: приложение запускает контейнер postres, должен быть предустановлен Docker.
1. Запустить приложение (IntelliJ IDEA Ultimate 2024.1.4)
2. Открыть файл resources/request.http
3. Выплонить запрос наполнения БД данными : GET http://127.0.0.1:8080/persons/init
4. Выплонить запрос получения людей из Москвы : GET http://127.0.0.1:8080/persons/by-city?city=moscow
5. Полученные данные просмотреть в консоли выполнения запроса
   - Альтернатива: выполнить запрос GET http://127.0.0.1:8080/persons/by-city?city=moscow из браузера
---

# Задача «Слой DAO c Hibernate»

## Описание

Попрактикуемся в работе с Hibernate через Spring, параллельно закрепляя уже пройденные темы.

Вам надо написать приложение для работы с БД, используя средства Spring по конфигурации и работе с Hibernate, адаптировав таблицы из задания [«Таблица пользователей»](../../sql-basic/task/README.md).

**Что нужно сделать**

1. Создайте Spring Boot приложение с зависимостями на два стартера — `spring-boot-starter-data-jpa` и `spring-boot-starter-web`.

2. Создайте Entity, которая соответствует таблице из условий задачи [«Таблица пользователей»](../../sql-basic/task/README.md).

3. Напишите репозиторий для работы с БД.

- создайте класс и пометьте его аннотацией Repository, либо создайте бин репозитория в Java Config классе;
- правильно инжектируйте EntityManager;
- создайте метод `getPersonsByCity(String city)`, который будет принимать название города и возвращать ваше Entity из базы данных, соответствующие этому `city`. Сделать это можно, например, получив всех пользователей и отфильтровав их по городу.

4. Напишите контроллер с методом-обработчиком GET-метода запроса с маппингом на endpoint `/persons/by-city`. В query params запроса будет приходить строковый параметр `city`, который вам надо будет передавать дальше в репозиторий. То есть, ваш метод должен уметь обрабатывать запрос вида `localhost:8080/persons/by-city?city=Moscow`.
   Контроллер должен будет возвращать всех людей, которых он получит от репозитория.

5. Написанные код выложите в отдельный репозиторий на GitHub и прикрепите ссылку на него в комментарий к домашнему заданию.