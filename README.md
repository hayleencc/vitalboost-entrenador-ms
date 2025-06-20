# entrenador-ms
## Descripción

Este proyecto implementa un microservicio REST para crear, consultar, actualizar y filtrar entrenadores, con manejo de DTOs, validaciones y pruebas unitarias.  

## Tecnologías

- Java 17
- Spring Boot
- Spring Data JPA
- Maven
- JUnit + Mockito para testing

## Requisitos

- Java 17 o superior
- Maven 3.6+
- Base de datos configurada (por ejemplo, PostgreSQL o H2 para pruebas)

## Cómo levantar el proyecto

1. Clonar el repositorio:
```git clone https://github.com/hayleencc/vitalboost-entrenador-ms.git```


2. Configurar la base de datos con las variables del archivo de ejemplo y crearlo en:
```src/main/java/resources/application-dev.properties```. 


3. Construir y ejecutar con Maven:
```mvn clean install```
```mvn spring-boot:run```


4. La aplicación arrancará en `http://localhost:8080` 


## Testing
Se puede usar el comando ```mvn test```


## Estructura del proyecto

- `controller/`: Controladores REST
- `service/`: Lógica de negocio
- `repository/`: Acceso a datos
- `dto/`: Objetos de transferencia de datos
- `model/`: Entidades JPA
- `test/`: Pruebas unitarias