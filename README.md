# Microservicio Tecnología

## Descripción
Microservicio para la gestión del catálogo de tecnologías. Implementa arquitectura hexagonal con Spring WebFlux.

## Tecnologías
- Java 17
- Spring Boot 3.2.1
- Spring WebFlux (Programación Reactiva)
- R2DBC MySQL
- Gradle 8.5
- Lombok
- MapStruct

## Puerto
**8081**

## API Endpoints

### Tecnologías
- `POST /api/tecnologia` - Crear nueva tecnología
- `GET /api/tecnologia/{id}` - Obtener tecnología por ID
- `DELETE /api/tecnologia/{id}` - Eliminar tecnología

## Ejecución

```bash
# Compilar
./gradlew clean build

# Ejecutar
./gradlew bootRun

# Ejecutar tests
./gradlew test
```

## Configuración Base de Datos
Editar `src/main/resources/application.properties`:

```properties
spring.r2dbc.url=r2dbc:mysql://localhost:3306/tecnologia_db
spring.r2dbc.username=root
spring.r2dbc.password=tu_password
```

## Documentación API
Swagger UI disponible en: `http://localhost:8081/webjars/swagger-ui/index.html`

* En el dominio va la logica de negocio, validaciones y todo lo relacionado con las funcionalidades
  basicas, se espera que el dominio este aisaldo de varias tecnologias a excepción de Lombok

* En el .yml o .properties se agregan Variables para la conexión a la base de datos
  o el puerto del proyecto configure los distintos entornos segun su necesidad **elimine el
  archivo de configuración que no necesita.**

* Por buenas practicas para conectarse entre capas se recomienta usar interfaces por ejemplo en la aplicación, crear
  una interfaz e implementarla en la clase de servicio correspondiente y luego en la infraestructura en el
  endpoint/controlador inyectar la interfaz y así se conectaría correctamente, esto aplica para:
    - Aplicación-infraestructura.
    - Aplicación de dominio.
    - Dominio de infraestructura.

* Si desea conectar microservicios hagalo en la infraestructura-adapters-external-feing y las url
  de los microservicios para conectar el cliente hagalo con variables de entorno en el .yml,
  en la capa de dominio cree una interfaz de persistencePort en la carpeta ports-out y la implementa en
  la clase con los metodos de feing

* En la clase BeanImportSelector en generalConfigurations importa 3 variables del main, las cuales hacen referencia a 
los paquetes que spring boot necesita mapear para genenerar los beans de manera automatica


⚠️ **No te conformes con lo que hay; si hay algo innecesario, cámbiele el nombre o elimínelo.**

⚠️ **El módulo de usuario es un ejemplo que puedes usar como base, si no lo necesitas puedes eliminarlo.**
