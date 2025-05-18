Perfecto, Emilio. Vamos a arrancar con una serie de ejercicios prácticos de **Spring Security — Nivel Básico**, diseñados para avanzar de forma progresiva. Cada ejercicio va acompañado de sus objetivos, tips y qué deberías haber entendido al terminarlo. Usaremos un microservicio simple con CRUD y base en memoria (H2), como preferís.

---

## 🧪 **Nivel Básico — Ejercicio 1: Microservicio CRUD con rutas públicas y protegidas**

### 🎯 Objetivo:

* Crear un microservicio REST con Spring Boot.
* Implementar Spring Security con configuración mínima.
* Proteger algunas rutas y dejar otras públicas.

---

### 🧱 Requisitos técnicos:

* Spring Boot 3+
* Spring Web
* Spring Security
* H2 Database
* Spring Data JPA

---

### 🛠️ **Instrucciones**

#### Paso 1: Crea el proyecto (si querés te doy comando `spring init`)

* Entidad: `Producto` (id, nombre, precio).
* Controlador REST: CRUD básico (`/productos`).
* Repositorio JPA con datos ficticios en memoria.
* Base H2 con `data.sql` para poblar productos.

#### Paso 2: Agregá Spring Security

En `application.yml`:

```yaml
spring:
  security:
    user:
      name: emilio
      password: password123
  h2:
    console:
      enabled: true
```

#### Paso 3: Configuración de seguridad

```java
@Configuration
@EnableWebSecurity
public class SeguridadBasicaConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // no se necesita CSRF para APIs REST

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll() // consola H2 libre
                .requestMatchers(HttpMethod.GET, "/api/productos/**").permitAll() // solo GET públicos
                .anyRequest().authenticated() // el resto requiere login
            )
            .headers(headers -> headers.frameOptions().disable()) // permite usar H2 en navegador
            .httpBasic(); // autenticación básica

        return http.build();
    }
}
```

---

### 🧪 **Lo que tenés que lograr:**

1. Correr la app.
2. Ir a `/h2-console` sin login.
3. Hacer GET a `/productos` sin login.
4. Intentar hacer POST/PUT/DELETE y que requiera login.
5. Hacerlo con autenticación básica (usuario: `emilio`, pass: `pass123`).

---

### ✅ **Checklist del ejercicio**

* ✅CRUD funcional de `Producto`
* [ ] Consola H2 accesible sin login
* ✅ Seguridad básica configurada
* ✅ GET públicos, otros métodos protegidos
* ✅ Autenticación básica HTTP funcionando

---

### 🧠 ¿Qué aprendiste acá?

* Qué es `SecurityFilterChain`.
* Cómo proteger rutas específicas (por método o path).
* Cómo usar HTTP Basic (usuario/contraseña).
* Cómo excluir recursos (como H2) de la seguridad.
* Cuándo desactivar CSRF.

---

### 🔐 Seguridad H2 (la consola web)
🧠 ¿Qué es H2?

* H2 es una base de datos en memoria. Eso significa que se borra cada vez que reiniciás la app. Es útil para pruebas rápidas. Spring Boot puede levantar una consola web (/h2-console) para verla en el navegador.
🎯 El problema de seguridad

* Por defecto, Spring Security bloquea el acceso a /h2-console, aunque lo pongas en el navegador.
🔑 ¿Qué hacer?

* Hay que permitir explícitamente esa ruta en SecurityFilterChain, y además desactivar los headers "frame options", porque H2 usa <iframe> y eso lo bloquea el navegador por seguridad.

* url: http://localhost:8085/h2-console/login.do

---

### 🛡️ ¿Qué es CSRF?
🧠 Explicación simple:

* CSRF = Cross Site Request Forgery = Falsificación de solicitudes entre sitios.

* Es un tipo de ataque donde un sitio externo te hace enviar un formulario a tu backend sin que vos lo sepas. Ej: estás logueado en tu app y otra página te hace enviar una petición POST para borrar tus datos. ¡Grave!
🔐 ¿Qué hace Spring Security?

* Spring protege contra CSRF agregando un token oculto en formularios HTML. Solo si ese token está presente y es válido, permite la acción.
🤔 ¿Por qué lo desactivamos?

* En APIs REST, no usamos formularios HTML, sino fetch(), Postman, o curl. No tiene sentido proteger con CSRF ahí, así que normalmente lo desactivamos:
* En apps web (HTML + Thymeleaf, por ejemplo), no deberías desactivarlo.