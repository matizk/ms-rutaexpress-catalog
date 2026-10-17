# ms-rutaexpress-catalogo

> **Estás viendo main: versión estable de EV1.** El avance de EV2 y las instrucciones actuales para descargar las seis carpetas están en [desarrollo](https://github.com/matizk/ms-rutaexpress-catalogo/tree/desarrollo) y en la [guía central del equipo](https://github.com/matizk/ms-rutaexpress-envios/blob/desarrollo/CONTINUAR_EV2_EQUIPO.md). Esta actualización de main solo cambia documentos; no publica la implementación EV2 ni acredita despliegue cloud.

Los repositorios y carpetas se renombraron al español el 17-10-2026. Ver [COLABORACION.md](COLABORACION.md) para el flujo `matizk → desarrollo → entrega → main`. Las instrucciones EV1 que siguen son antecedentes de esta versión, no la configuración del sistema EV2 con los nuevos nombres. Para ejecutar la integración actual, seguir exclusivamente la guía de desarrollo.

Microservicio de catálogo de RutaExpress para la Entrega 1 de DSY1107.

## Responsabilidad

- Administrar servicios de envío.
- Gestionar tarifas y capacidad disponible.

## Endpoints definidos por el caso

- `GET /api/catalog/services`
- `POST /api/catalog/services`
- `PUT /api/catalog/services/{id}`

Para la integración interna de RutaExpress se incluye:

- `PUT /api/catalog/services/{id}/capacity/decrease`

Este último endpoint es consumido por el microservicio de envíos únicamente cuando
un envío pasa a estado `ACEPTADO`; evita que se acepte capacidad inexistente.

## Desarrollo local

La aplicación usa PostgreSQL. Las credenciales nunca se versionan: copia
`.env.example` como `.env` y configura `SPRING_DATASOURCE_PASSWORD` en tu entorno.
La base local esperada es `catalog_db`, con el usuario `rutaexpress`.

Ejecuta las pruebas con:

```powershell
.\mvnw.cmd test
```

Construye la imagen sin requerir un JAR local previo:

```powershell
docker build -t rutaexpress-catalog:local .
```

## Salud y preparación AWS

`GET /actuator/health` entrega el estado de la aplicación para Docker y AWS. La
respuesta esperada es `{ "status": "UP" }` cuando PostgreSQL está disponible.

## Estado

Implementado con Spring Boot, Java 21, PostgreSQL, validación, Docker y pruebas de la
regla de capacidad disponible. La rama `main` contiene la versión integrada para la
entrega, construida a partir del aporte inicial de Daniel.
