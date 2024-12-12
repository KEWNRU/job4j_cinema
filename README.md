
# Job4j. Cinema.

Проект кинотеатр реализует сайт с следующим функционалом:
- Просмотр сеансов и связнных с ними фильмов
- Просмотр списка всех фильмов
- Возможность покупки билета с выбором ряда и места
- Возможность регистрации/авторизации/выхода

Используемые инструменты:
- Java 17,
- CheckStyle 3.1.2,
- Mockito 5.2.0,
- Spring Boot 2.7.6,
- Thymeleaf,
- Liquibase 4.15.0,
- PostgreSQL 42.5.1,
- Sql2o 2.1.214
- h2 2.1.214,

Как запустить проект:
- Скачасть проект
- Создать базу данных cinema
- Через инструмент Maven пройдите по пути job4j_cinema/Plugins/liquibase и выберете функцию liquibase:update
- Пройдите по пути src/main/java/ru/job4j/cinema, найдите и запустите класс Main.java
- Передите на страницу http://localhost:8080/ в браузере

Скриншоты основных страниц сайта:

Страница входа на ресурс:
![1.png](screenshot%2F1.png)
Страница регистрации:
![2.png](screenshot%2F2.png)
Страница сесси и покупки:
![3.png](screenshot%2F3.png)

![4.png](screenshot%2F4.png)

![5.png](screenshot%2F5.png)
Страница и фильмах:
![6.png](screenshot%2F6.png)

![7.png](screenshot%2F7.png)
