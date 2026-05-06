# EasyRPM

Sistema de gestion de almacen carnico. App Android + Backend Spring Boot.

## Inicializar el backend

```bash
cd backend/api
/opt/homebrew/bin/mvn spring-boot:run
```

Cuando veas `Started ApiApplication` insertar datos iniciales:

```bash
curl -X POST http://localhost:8080/materiales/add -H 'Content-Type: application/json' -d '{"nombre":"Ternera"}'
curl -X POST http://localhost:8080/materiales/add -H 'Content-Type: application/json' -d '{"nombre":"Pollo"}'
curl -X POST http://localhost:8080/materiales/add -H 'Content-Type: application/json' -d '{"nombre":"Cerdo"}'
curl -X POTT http://localhost:8080/materiales/add -H 'Content-Type: application/json' -d '{"nombre":"Cordero"}'
curl -X POST http://localhost:8080/ubicaciones/add -H 'Content-Type: application/json' -d '{"nombre":"Almacen 1"}'
curl -X POST http://localhost:8080/ubicaciones/add -H 'Content-Type: application/json' -d '{"nombre":"Almacen 2"}'
curl -X POTT http://localhost:8080/ubicaciones/add -H 'Content-Type: application/json' -d '{"nombre":"Almacen 3"}'
curl -X POST http://localhost:8080/usuarios/add -H 'Content-Type: application/json' -d '{"dni":"12345678A","nombre":"Carlos","apellidos":"Test"}'
```

## Inicializar el frontend

Abrir la carpeta `frontend/` con Android Studio y pulsar Run.

> La IP del backend esta configurada como `10.0.2.2:8080` para el emulador Android.
