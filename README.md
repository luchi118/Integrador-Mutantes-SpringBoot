# **Integrador-SpringBoot**

\# Mutant Detector API

Tecnologías usadas: Java 21, Intellij Idea, SpringBoot 3.2.0, H2 DataBase, SwaggerUI

API REST desarrollada para detectar mutantes en base a una secuencia de ADN. El proyecto analiza una matriz de ADN (NxN) buscando secuencias de 4 letras iguales en direcciones horizontal, vertical y oblicua.

\# Características

\*Algoritmo Eficiente: Implementación optimizada con Early Termination y Single Pass para detectar mutantes rápidamente (O(N)).

\*API RESTful: Endpoints claros y documentados.

\*Persistencia: Uso de Base de Datos H2 embebida.

\*Deduplicación: Cálculo de Hash (SHA-256) para cada ADN analizado, evitando re-procesar secuencias ya conocidas (Caché de base de datos).

\*Calidad: Cobertura de código superior al 80% con JUnit 5 y Mockito.

\*Documentación: Swagger UI integrado.

\# Tecnologías Utilizadas

\*Lenguaje: Java 21

\*Framework: Spring Boot 3.2.0

\*Build Tool: Gradle.Kotlin (Wrapper incluido)

\*Base de Datos: H2 Database (En memoria)

\*Testing: JUnit 5, Mockito, JaCoCo

\*Herramientas: Lombok, SpringDoc OpenAPI (Swagger)

\# Instalación y Ejecución

\#\# Prerrequisitos

* Tener instalado Java 21 (JDK).

\# 1\. Clonar el repositorio

git clone https://github.com/luchi118/Integrador-Mutantes-SpringBoot.git

cd Integrador-Mutantes-SpringBoot

\#\# Para ejecutar la Aplicación

En Windows: ./gradlew.bat bootRun

En Linux/Mac: ./gradlew bootRun

La aplicación se iniciará en el puerto 8080

Uso de la API

Se puede probar la API visualmente usando Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

También a través de Render en: [https://integrador-springboot.onrender.com/swagger-ui/index.html](https://integrador-springboot.onrender.com/swagger-ui/index.html)

1\. Detectar Mutante

POST /mutant //Envía una secuencia de ADN para análisis.

Body (JSON):

// Secuencia de Prueba

{   "dna": \[

  "ATGCGA",

  "CAGTGC",

  "TTATGT",

  "AGAAGG",

  "CCCCTA",

  "TCACTG"

  \]

} Respuestas:

200 OK: Es un Mutante (se detectaron más de una secuencia).

403 Forbidden: Es un Humano.

400 Bad Request: ADN inválido (matriz no cuadrada, caracteres extraños o nulos).

Ver Estadísticas:

GET /stats

Devuelve el conteo de verificaciones de ADN.

Respuesta (JSON):

{

  "count\_mutant\_dna": 40,

  "count\_human\_dna": 100,

  "ratio": 0.4

}

Base de Datos: H2

Se puede comprobar que las secuencias de ADN se guardan en el siguiente link:

De manera local en:

[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

url=jdbc:h2:mem:mutantdb

driverClassName=org.h2.Driver

username=sa

password=

De manera remota desde Render:

[https://integrador-springboot.onrender.com/h2-console](https://integrador-springboot.onrender.com/h2-console)

url=jdbc:h2:mem:mutatntdb //Si no ponemos nombre, SpringBoot genera uno aleatorio

driverClassName=org.h2.Driver

username=sa

password=

Testing y Cobertura

El proyecto cuenta con tests unitarios y de integración exhaustivos.

Ejecutar todos los tests

./gradlew.bat test

Generar reporte de cobertura (JaCoCo)

./gradlew.bat test jacocoTestReport

El reporte HTML estará disponible en: build/reports/jacoco/test/html/index.html

Arquitectura del Proyecto

El código sigue una arquitectura en capas (Layered Architecture):

Controller Layer (/controller): Maneja las peticiones HTTP y valida la entrada (DTOs).

Service Layer (/service): Contiene la lógica de negocio (MutantDetector, StatsService) y la lógica de orquestación (MutantService).

Repository Layer (/repository): Interfaz con la base de datos usando Spring Data JPA.

Entity Layer (/entity): Modelo de datos (DnaRecord) con índices optimizados.

Model/DTO (/dto): Objetos de transferencia de datos para desacoplar la API de la BD.

Optimización de Base de Datos

Para cumplir con los requisitos de alto rendimiento, no se guarda el ADN completo como texto. Se calcula un Hash SHA-256 único para cada secuencia y se indexa.

Búsqueda O(1) para verificar si un ADN ya fue analizado.

Ahorro de espacio significativo en la base de datos.

Autor

Desarrollado por Rodriguez Lucía, legajo 50148 curso 3K9, para el integrador de la materia Desarrollo de Software

