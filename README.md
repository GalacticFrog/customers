## IMPLEMENTACION DE UN MICROSERVICIO USANDO SPRING BOOT.

Proyecto Gestionado con Maven  
Desarrollado en SpringBoot 2.7.10-SNAPSHOT con Java 11  
Test unitarios JUnit 5  
Base de datos embebida H2

## 0\. Prerequisitos

*   Se necesita JDK 11 o superior.
*   Maven 3.x.x

## 1\. Generar el ejecutable .jar

Situarse en el directorio del proyecto y ejecutar el comando siguiente via Maven.

```plaintext
mvn clean package
```

## 2\. Ejecutar el microservicio

Via linea de comando ejecutar el archivo jar generado en el paso anterior.

```plaintext
java -jar customers-0.0.1-SNAPSHOT.jar
```

## 3\. Probar los endponts via swagger o postman

Se puede probar por intermedio de swagger los endpoints en la url [http://localhost:8080/swagger-ui/index.html#/cliente-controller](http://localhost:8080/swagger-ui/index.html#/cliente-controller) o con postman por medio de la coleccion _**customers.postman\_collection.json**_

```xml
mvn clean package
```
