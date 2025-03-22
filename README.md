# API de Gestión de Pedidos

Microservicio RESTful con Spring Boot 3.x para gestionar pedidos de e-commerce, con autenticación basada en JWT, autorización basada en roles, documentación Swagger, y simulación de integración con CommerceTools.

---

## 📌 Importante

**Nota de Arturo Cordero:**
Este proyecto contiene documentación JavaDoc, comentarios y descripciones Swagger generadas con OpenAI para brindar robustez, presentación y formalidad al proyecto, agilizando el desarrollo y facilitando su entendimiento.

**Beanstalk URL:**
http://order-management-api.us-east-1.elasticbeanstalk.com/swagger-ui/index.html#/Orders/placeOrder

---

## 🚀 Funcionalidades

- ✅ Autenticación JWT (`accessToken`, `refreshToken`)
- ✅ Autorización basada en roles (`USER`, `MODERATOR`, `ADMIN`)
- ✅ Integración con Swagger UI
- ✅ Creación, actualización y consulta de pedidos
- ✅ Validación de datos y manejo centralizado de errores
- ✅ Usuarios en memoria para pruebas
- ✅ Simulación de integración con CommerceTools

---

## 📦 Tecnologías Utilizadas

- Spring Boot 3.x
- Spring Security
- JWT (jjwt)
- Swagger (springdoc-openapi)
- JPA (con PostgreSQL o MySQL)

---

## 🛠️ Instrucciones de Configuración

### 1. Clonar el repositorio
```bash
git clone https://github.com/your-username/order-management-api.git
cd order-management-api
```

### 2. Ejecutar la aplicación
```bash
./mvnw spring-boot:run
```

### 3. Acceder a Swagger UI
[http://localhost:8080/swagger-ui-custom.html](http://localhost:8080/swagger-ui-custom.html)

Usa el botón `Authorize` para añadir un token JWT (Bearer).

---

## 👤 Usuarios de prueba (En memoria)
| Usuario     | Contraseña | Roles           |
|-------------|------------|-----------------|
| `admin`     | `admin123` | `ROLE_ADMIN`    |
| `moderator` | `mod123`   | `ROLE_MODERATOR`|
| `client`    | `client123`| `ROLE_USER`     |

---

## 🔐 Endpoints de Autenticación

### `POST /auth/login`
Devuelve `accessToken`, `refreshToken` y las fechas de expiración.

### `POST /auth/refresh`
Usa un `refreshToken` válido para obtener un nuevo `accessToken`.

---

## 📦 Endpoints de Pedido

### `POST /orders`
**Roles**: `USER`  
Crea un nuevo pedido.

### `GET /orders/{orderId}`
**Roles**: `ADMIN`, `USER`  
Consulta los detalles de un pedido específico.

### `PATCH /orders/{orderId}/status`
**Roles**: `ADMIN`, `MODERATOR`  
Actualiza el estado de un pedido existente.

### `GET /orders/customer/{customerId}`
**Roles**: `ADMIN`, `USER`  
Obtiene todos los pedidos realizados por un cliente.

---

## ⚠️ Notas

- Los JWT incluyen información de roles y se validan mediante un filtro personalizado.
- La configuración Swagger permite autenticarse con el botón `Authorize`.
- Los precios y disponibilidad de productos están simulados en memoria.

---

## 👨‍💻 Autor

Arturo Cordero — [arturh.sw@gmail.com](mailto:arturh.sw@gmail.com)  
Sitio web: [easycommerce.com](https://easycommerce.com)

---

## 📄 Licencia

Apache 2.0
