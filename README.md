# Практическрая работа №4
### Вариант №15: Посуда

## Инструкция по сборке и запуску
Убедитесь, что на вашем компьютере присутствует [JDK](https://www.oracle.com/java/technologies/downloads/#jdk17-windows)

```
git clone https://github.com/waffflezz/rkis-4.git
cd rkis-4
psql -U postgres -h localhost -f create_db.sql
mvn package
java -jar target/rkis-4.jar
```

_Для сборки необходимо иметь [Maven](https://maven.apache.org/download.cgi) на компьютере_
