# 🌱 EcoMarket SPA - Microservicio Inventario & Productos

Bienvenido al microservicio de **EcoMarket SPA**. Este repositorio incluye todo lo necesario para ejecutar y probar la **gestión de productos, inventario y usuarios** de manera sencilla mediante una API REST construida con **Spring Boot** y **Maven**.

---

## 📦 Descripción del Proyecto

Este microservicio forma parte de la arquitectura modular de EcoMarket SPA. Ofrece endpoints RESTful para realizar operaciones CRUD sobre entidades como:

- 🛒 Productos  
- 📦 Inventario  
- 👤 Usuarios  
- 📑 Pedidos  
- 📤 Envíos  
- 🔐 Roles

Utiliza por defecto una base de datos **H2 en memoria** y permite conectar fácilmente a una base de datos externa como **PostgreSQL** si lo deseas.

---

## ⚙️ Requisitos Previos

- ✅ Java 17+ (`JAVA_HOME` correctamente configurado)
- ✅ Maven 3.6+ (o usar `mvnw` incluido)
- ✅ Git
- ✅ Postman o cURL
- ✅ IDE con soporte Spring Boot (recomendado: IntelliJ IDEA o VS Code)
- ☁️ Docker + Docker Compose *(opcional, para levantar PostgreSQL)*
- 🐘 Cliente PostgreSQL (pgAdmin, DBeaver, etc.) *(opcional)*

---

## 🔧 Configuración del Proyecto

```bash
git clone https://github.com/tu-usuario/ecomarket-spa.git
cd ecomarket-spa
```
---

## 👟 Ejecución del Servicio

En Linux/macOS
```bash
./mvnw clean install
./mvnw spring-boot:run
```
En Windows (CMD o PowerShell)
```bash
.\mvnw.cmd clean install
.\mvnw.cmd spring-boot:run
```
También puedes usar Maven instalado globalmente:
```bash
mvn clean install
mvn spring-boot:run
```

---

## 🚀 Endpoints Disponibles
Base URL: http://localhost:8080/api

| Método | Ruta                         | Descripción                                |
| ------ | ---------------------------- | ------------------------------------------ |
| GET    | /productos                   | Listar todos los productos                 |
| GET    | /productos/{codigo}          | Obtener producto por código                |
| POST   | /productos                   | Crear un nuevo producto                    |
| PUT    | /productos/{codigo}          | Actualizar producto                        |
| DELETE | /productos/{codigo}          | Eliminar producto                          |
| GET    | /inventario                  | Listar inventario                          |
| GET    | /inventario/{codigo}         | Obtener inventario por código              |
| POST   | /inventario                  | Crear nuevo registro de inventario         |
| PUT    | /inventario/{codigo}         | Actualizar inventario                      |
| DELETE | /inventario/{codigo}         | Eliminar inventario                        |
| GET    | /usuarios                    | Listar todos los usuarios                  |
| GET    | /usuarios/{id}               | Obtener usuario por ID                     |
| POST   | /usuarios                    | Crear nuevo usuario                        |
| PUT    | /usuarios/{id}               | Actualizar usuario                         |
| DELETE | /usuarios/{id}               | Eliminar usuario                           |
| POST   | /usuarios/registro           | Registrar usuario con código de validación |
| POST   | /usuarios/validar/{username} | Validar código de activación               |
| GET    | /pedidos                     | Listar pedidos                             |
| GET    | /pedidos/{id}                | Obtener pedido por ID                      |
| POST   | /pedidos                     | Crear pedido                               |
| PUT    | /pedidos/{id}                | Actualizar pedido                          |
| DELETE | /pedidos/{id}                | Eliminar pedido                            |
| GET    | /roles                       | Listar roles                               |
| POST   | /roles                       | Crear rol                                  |
| PUT    | /roles/{id}                  | Actualizar rol                             |
| DELETE | /roles/{id}                  | Eliminar rol                               |

---

## 🧪 Pruebas con Postman
1.- Abre Postman

2.- Importa la colección: postman/EcoMarketSPA.postman_collection.json

3.- Crea una variable de entorno:
baseUrl = http://localhost:8080/api

4.- Ejecuta las pruebas CRUD en orden:

- GET Listar todos

- GET Obtener por ID

- POST Crear nuevo

- PUT Actualizar existente

- DELETE Eliminar por ID

5.- Verifica códigos de respuesta (200 OK, 201 Created, 204 No Content, 404 Not Found, etc.)

---

## 🤝 Contribuciones
1.- Haz fork del repositorio

2.- Crea una nueva rama:
feature/nombre-funcionalidad o bugfix/arreglo-nombre

3.- Realiza los cambios con commits claros

4.- Abre un Pull Request a main

---

## 📬 Contacto
Correo: fe.galveza@duocuc.cl, tapiatapia.s@gmail.com
Proyecto para Desarrollo Fullstack I – Ingeniería Informática 2025
