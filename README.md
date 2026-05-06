# EasyRPM

Sistema de gestion de almacen carnico. App Android + Backend Spring Boot.

## Estructura

```
EasyRPM_GIT/
├ backend/     --> Spring Boot + PostgreSQL
└ frontend/    --> App Android (Kotlin + Retrofit)
```

## Inicializar el backend

> Requisitos: PostgreSQL arrancado, se necesita la BD `EasyRMP_db` creada

**Paso 1:** Abrir Terminal e ir a la carpeta del backend:

```bash
# Si tienes la carpeta en Downloads
cd ~/Downloads/EasyRPM_GIT/backend/api

# Si la tienes en otra ubicacion, ajusta la ruta
# cd <ruta-a-tu-carpeta>/backend/api
```

**Paso 2:** Arrancar el servidor:

```bash
/opt/homebrew/bin/mvn spring-boot:run
```

**Paso 3:** Esperar hasta ver:

```
Started ApiApplication in X seconds
```

**Paso 4:** En otra Terminal nueva, insertar datos iniciales:

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

> Aviso: estos datos se pierden cada vez que se reinicia el servidor.

## Inicializar el frontend

Abrir la carpeta `frontend/` con Android Studio y pulsar Run.

> La IP del backend esta configurada como `10.0.2.2:8080` para el emulador Android. Si usas dispositivo fisico, cambia esa IP por la IP local del ordenador en RetrofitClient.kt
