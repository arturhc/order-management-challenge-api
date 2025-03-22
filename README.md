# API de Gestión de Pedidos

Microservicio RESTful con Spring Boot 3.x para gestionar pedidos de e-commerce. Incluye autenticación JWT, autorización basada en roles, documentación Swagger y una integración simulada con CommerceTools.

---

## 🚀 Características

- ✅ Autenticación con JWT (`accessToken`, `refreshToken`)
- ✅ Autorización por roles (`USER`, `MODERATOR`, `ADMIN`)
- ✅ Documentación Swagger UI
- ✅ Crear, actualizar y consultar pedidos
- ✅ Validación y manejo centralizado de errores
- ✅ Usuarios en memoria para pruebas
- ✅ Integración simulada con CommerceTools

---

## 📦 Tecnologías Usadas

- Spring Boot 3.x
- Spring Security
- JWT (jjwt)
- Swagger (springdoc-openapi)
- JPA (con soporte para PostgreSQL o MySQL)

---

## 🛠️ Instrucciones de Configuración

### 1. Clona el repositorio

```bash
git clone https://github.com/tu-usuario/order-management-api.git
cd order-management-api
```

### 2. Ejecuta la aplicación

```bash
./mvnw spring-boot:run
```

### 3. Accede a Swagger UI

[http://localhost:8080/swagger-ui-custom.html](http://localhost:8080/swagger-ui-custom.html)

Usa el botón **Authorize** para ingresar el token JWT (`Bearer <token>`).

---

## 👤 Usuarios de Prueba (en memoria)

| Usuario     | Contraseña  | Rol               |
|-------------|-------------|-------------------|
| `admin`     | `admin123`  | `ROLE_ADMIN`      |
| `moderator` | `mod123`    | `ROLE_MODERATOR`  |
| `client`    | `client123` | `ROLE_USER`       |

---

## 🔐 Endpoints de Autenticación

### `POST /auth/login`

Retorna `accessToken`, `refreshToken` y los tiempos de expiración.

### `POST /auth/refresh`

Usa un `refreshToken` válido para obtener un nuevo `accessToken`.

---

## 📦 Endpoints de Pedidos

### `POST /orders`
**Roles**: `USER`  
Crea un nuevo pedido.

### `GET /orders/{orderId}`
**Roles**: `ADMIN`, `USER`  
Consulta un pedido específico.

### `PATCH /orders/{orderId}/status`
**Roles**: `ADMIN`, `MODERATOR`  
Actualiza el estado de un pedido.

### `GET /orders/customer/{customerId}`
**Roles**: `ADMIN`, `USER`  
Lista los pedidos de un cliente.

---

## ⚠️ Notas

- Los JWT incluyen los roles del usuario.
- El filtro personalizado valida los tokens.
- Swagger está configurado para aceptar autenticación JWT.
- Los precios y disponibilidad de productos están simulados con un `Map`.

---

## 👨‍💻 Autor

**Arturo Cordero** — [arturh.sw@gmail.com](mailto:arturh.sw@gmail.com)  
Sitio web: [easycommerce.com](https://easycommerce.com)

---

## 📄 Licencia

Licencia Apache 2.0