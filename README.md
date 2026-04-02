# 🛒 Commerce Backend

Backend desarrollado con **Spring Boot** para la gestión de usuarios, autenticación y funcionalidades base de un sistema de comercio.

---

## 🧠 Descripción

Este proyecto implementa una API REST con buenas prácticas de backend:

- 🔐 Autenticación con JWT
- 👤 Gestión de usuarios
- ⚙️ Manejo global de excepciones
- 🧱 Separación por capas (Clean Architecture)

Pensado como base escalable para aplicaciones reales.

---

## 🚀 Tecnologías

* Java 17+
* Spring Boot
* Spring Security
* JWT (Autenticación)
* PostgreSQL
* Docker

---

## 🧱 Arquitectura

El proyecto sigue una arquitectura basada en capas:

* `domain` → entidades y lógica de negocio
* `application` → casos de uso
* `infrastructure` → controladores, configuración, persistencia
* `shared` → excepciones y utilidades comunes

---

## 📦 Instalación

1. Clonar el repositorio:

```bash
git clone https://github.com/cristianlag97/commerce-back.git
cd commerce-back
```

2. Crear archivo de configuración:

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

3. Configurar tus credenciales en `application.properties`

---

## ▶️ Ejecución

### Con Maven:

```bash
./mvnw spring-boot:run
```

### Con Docker:

```bash
docker-compose up --build
```

---

## 🔐 Autenticación

El sistema utiliza **JWT** para la autenticación.

### Endpoints principales:

* `POST /api/auth/register` → Registro de usuario
* `POST /api/auth/login` → Login

---

## ⚠️ Variables de entorno

Ejemplo de configuración:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/db_name
spring.datasource.username=your_user
spring.datasource.password=your_password

jwt.secret=your_jwt_secret
jwt.expiration=3600000
```

---

## 🧪 Testing

Puedes probar los endpoints con:

* Postman
* curl

Ejemplo:

```bash
curl -X POST http://localhost:8080/api/auth/register \
-H "Content-Type: application/json" \
-d '{
  "name": "Cristian",
  "email": "test@test.com",
  "password": "123456"
}'
```

---

