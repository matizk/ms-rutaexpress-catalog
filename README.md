# ms-rutaexpress-catalogo

> Entrega de código EV2 del 22-10-2026: publicación mediante `release/ev2` → PR a `main`. Ramas del equipo: `matizk` para trabajo personal, `desarrollo` para integración y `main` para la entrega estable. Ver [COLABORACION.md](COLABORACION.md) y la [guía EV2](https://github.com/matizk/ms-rutaexpress-envios/blob/desarrollo/CONTINUAR_EV2_EQUIPO.md). Catálogo conserva su código funcional; el cambio respecto de la entrega anterior es documental.

Catálogo participa en el AWS existente informado por Daniel. El flujo Azure Duoc → aceptación → ticket → tracking del caso `RX-1792623980566` se completó, con confirmación interna de RDS/RabbitMQ atribuida al responsable de infraestructura. La documentación del [PR #7](https://github.com/matizk/ms-rutaexpress-catalogo/pull/7) ya quedó integrada en `desarrollo` mediante revisión manual; este repositorio no tiene CI configurado. [Estado y pendientes de cierre](https://github.com/matizk/ms-rutaexpress-envios/blob/desarrollo/CONTINUAR_EV2_EQUIPO.md). Publicar el código no certifica por sí solo el despliegue ni termina la evaluación.

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
regla de capacidad disponible. La entrega EV2 conserva la implementación construida a partir del aporte inicial de Daniel, con documentación actualizada; publicar en `main` no sustituye el informe ni la presentación del equipo.
