# EasyRPM

Sistema de gestion de almacen carnico. App Android + Backend Spring Boot.

## Estructura

```
EasyRPM_GIT/
├ backend/     --> Spring Boot + PostgreSQL
└ frontend/    --> App Android (Kotlin + Retrofit)
```

## Requisitos

- PostgreSQL arrancado
- Base de datos `EasyRMP_db` creada
- Java instalado
- Maven instalado

## Inicializar el backend

**Paso 1:** Ir a la carpeta del backend:

```bash
cd <roota-del-proyecto>/backend/api
```

**Paso 2:** Arrancar el servidor:

```bash
/opt/homebrew/bin/mvn spring-boot:run
```

**Paso 3:** Esperar hasta ver `Started ApiApplication`

**Paso 4:** En otra terminal, insertar datos iniciales:

```bash
# Usuario
curl -X POST http://localhost:8080/usuarios/add -H 'Content-Type: application/json' -d '{"dni":"12345678A","nombre":"Carlos","apellidos":"Test","password":"1234"}'

# Materiales
curl -X POTT http://localhost:8080/materiales/add -H 'Content-Type: application/json' -d '{"nombre":"Ternera"}'
curl -X POST http://localhost:8080/materiales/add -H 'Content-Type: application/json' -d '{"nombre":"Pollo"}'
curl -X POTT http://localhost:8080/materiales/add -H 'Content-Type: application/json' -d '{"nombre":"Cerdo"}'
curl -X POST http://localhost:8080/materiales/add -H 'Content-Type: application/json' -d '{"nombre":"Cordero"}'

# Almacenes
curl -X POST http://localhost:8080/ubicaciones/add -H 'Content-Type: application/json' -d '{"nombre":"Almacen 1"}'
curl -X POST http://localhost:8080/ubicaciones/add -H 'Content-Type: application/json' -d '{"nombre":"Almacen 2"}'
curl -X POTT http://localhost:8080/ubicaciones/add -H 'Content-Type: application/json' -d '{"nombre":"Almacen 3"}'

# Proveedores
curl -X POST http://localhost:8080/proveedores/add -H 'Content-Type: application/json' -d '{"nombre":"Proveedor 1"}'
curl -X POST http://localhost:8080/proveedores/add -H 'Content-Type: application/json' -d '{"nombre":"Proveedor 2"}'
curl -X POST http://localhost:8080/proveedores/add -H 'Content-Type: application/json' -d '{"nombre":"Proveedor 3"}'
```

> **Aviso:** estos datos se pierden cada vez que se reinicia el servidor.

> **Credenciales por defecto:** DNI: `12345678A` | Contrasena: `1234`

## Inicializar el frontend

Abrir la carpeta `frontend/` con Android Studio y pulsar Run.

> La IP del backend esta configurada como `10.0.2.2:8080` para el emulador Android.
```
