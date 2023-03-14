# Дипломная работа «Облачное хранилище»

## Настройка сервера

### При использовании Docker - Kitematic

Необходимо заменить localhost на ip адрес в:
- [файл 1](netology-diplom-frontend/.env) настройку на `VUE_APP_BASE_URL=http://192.168.99.100:8085`.
- [файл 2](src/main/java/sobinda/javadiplomcloud/config/WebConf.java) наличии настройки в `.allowedOrigins` на `"http://192.168.99.100:8080"`
- [файл 3](src/main/resources/application.properties) настройку на `${MYSQL_HOST:192.168.99.100}`

### При использовании Docker Desktop
*для стандартного докера требуется проверить на установку `localhost`*  из прошлого пункта.

## Запуск проекта

- Первым делом нам надо собрать jar архивы с нашими spring boot приложениями. Для этого в терминале в корне нашего
  проект выполните команду:

**Для maven:** ```./mvnw clean package``` (если пишет Permission denied тогда сначала выполните chmod +x ./mvnw) или
```mvn clean package -Dskiptests```

```mvn clean package -Dmaven.test.skip```;

- После удачной сборки мы получим `Java-Diplom-Cloud-0.0.1-SNAPSHOT.jar` в `.\target`;
- В терминале выполнить команду по сборке images и containers: ```docker-compose up -d --build```;
- В докере запустятся 3 приложения:
- backend-server, Java 11 на порту: ```http://localhost:8085```;
- frontend-client, Node 14 на порту: ```http://localhost:8080``` или ```http://localhost:8081```;
- database-server на порту: ```http://localhost:3306```.

*Ручная сборка*
```docker build -t cloud-app-by-sobin:latest .```

```docker run -itd --name app1 cloud-app-by-sobin:latest```

- Запустить тесты можно:
    - Через терминал командой `mvn test`;
    - Во вкладке Maven отключаем иконку `Togger 'Skip Tests' Mode`, в катлоге `Lifecycle` активировать команды `test`;

## Добавление пользователей

[Добавление пользователей в БД](/src/main/resources/data.sql)

``` text
Логин:                       Пароль:

neo8745@yandex.ru          : 12345678
panya12@yandex.ru          : 1111222 
meloman7575@yandex.ru      : 12344321
```

