# ms-rutaexpress-catalog

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

La aplicación usa Oracle. Las credenciales nunca se versionan: copia
`.env.example` como `.env` y configura `SPRING_DATASOURCE_PASSWORD` en tu entorno.

La configuración local espera el servicio `FREEPDB1` de Oracle Free y el usuario
`RUTAEXPRESS`, igual que `ms-rutaexpress-shipments`.

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
respuesta esperada es `{ "status": "UP" }` cuando Oracle está disponible.

## Estado

Implementado con Spring Boot, Java 21, Oracle, validación, Docker y pruebas de la
regla de capacidad disponible. La rama `matizk` contiene la versión integrada a
partir del aporte inicial de Daniel.
