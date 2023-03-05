# Дипломная работа «Облачное хранилище»

## Запуск проекта

- Первым делом нам надо собрать jar архивы с нашими spring boot приложениями. Для этого в терминале в корне нашего
  проект выполните команду:

**Для maven:** ```./mvnw clean package``` (если пишет Permission denied тогда сначала выполните chmod +x ./mvnw) или
```mvn clean package -Dskiptests```

```mvn clean package -Dmaven.test.skip```;

- После удачной сборки мы получим `Java-Diplom-Cloud-0.0.1-SNAPSHOT.jar` в `.\target`;
- В терминале выполнить команду по сборке images и containers: ```docker-compose up```;
- В докере запустятся 3 приложения:
- backend-server, Java 11 на порту: ```http://localhost:8085```;
- frontend-client, Node 15 на порту: ```http://localhost:8080``` или ```http://localhost:8081```;
- database-server на порту: ```http://localhost:3306```.

*Ручная сборка*
```docker build -t cloud-app-by-sobin:latest .```

```docker run -itd --name app1 cloud-app-by-sobin:latest```

- Запустить тесты можно:
  - Через терминал командой `mvn test`;
  - Во вкладке Maven отключаем иконку `Togger 'Skip Tests' Mode`, в катлоге `Lifecycle` активировать команды `test`;
 