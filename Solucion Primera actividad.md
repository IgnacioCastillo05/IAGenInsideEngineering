# Problema #1 — Videoclub de Don Mario

## Objetivo

Desarrollar un sistema de alquiler de películas físicas y digitales que permita:

- Registrar películas disponibles.
- Gestionar alquileres según la membresía del cliente.
- Aplicar descuentos automáticamente.
- Generar un recibo final por consola.

---

# Patrones de Diseño Utilizados

## Strategy Pattern

Se utilizó para manejar la lógica de descuentos dependiendo del tipo de membresía del cliente.

Cada membresía implementa una estrategia diferente de cálculo:

- Membresía Básica → sin descuento.
- Membresía Premium → descuento del 20%.

Esto permite extender fácilmente el sistema para nuevas membresías sin modificar la lógica principal.

---

## Polimorfismo

Se aplicó mediante una clase base `Pelicula` y sus diferentes tipos:

- `PeliculaFisica`
- `PeliculaDigital`

Ambas pueden ser tratadas de manera uniforme por el sistema de alquiler.

---

# Principios SOLID Aplicados

## Single Responsibility Principle (SRP)

Cada clase tiene una única responsabilidad:

- Gestión de películas.
- Gestión de membresías.
- Generación de recibos.
- Cálculo de descuentos.

---

## Open/Closed Principle (OCP)

El sistema está abierto a extensión pero cerrado a modificación.

Es posible agregar:
- nuevas membresías,
- nuevos tipos de películas,
- nuevas estrategias de descuento

sin alterar el funcionamiento existente.

---

## Liskov Substitution Principle (LSP)

Las clases `PeliculaFisica` y `PeliculaDigital` pueden utilizarse como una `Pelicula` general sin afectar el comportamiento del sistema.

---

## Dependency Inversion Principle (DIP)

La lógica principal depende de abstracciones y no de implementaciones concretas.

El sistema trabaja sobre interfaces y clases abstractas para desacoplar componentes.

---

# Encapsulamiento

Se aplicó encapsulamiento mediante atributos privados y métodos públicos de acceso (`getters` y `setters`).

Esto protege el estado interno de los objetos y evita modificaciones indebidas desde otras clases.

---

# Evidencia de Ejecución

```text
--- RECIBO DE ALQUILER ---

Cliente: Premium

Películas:
- Interestellar (Física) - $8.000
- Inception (Digital) - $5.000

Subtotal: $13.000
Descuento (20%): $2.600
Total a pagar: $10.400

--------------------------
¡Disfrute su película!
```

---

# Prompts Utilizados

## Prompt 1 — Análisis del problema

```text
Analiza el siguiente problema de programación orientada a objetos en Java y recomienda qué patrones de diseño y principios SOLID podrían aplicarse.

El sistema debe permitir registrar películas físicas y digitales, validar disponibilidad, calcular descuentos según membresía y generar un recibo final.
```

---

## prompt 2 — Diseño orientado a objetos

```text
Ayúdame a diseñar una solución orientada a objetos en Java para un sistema de alquiler de películas utilizando encapsulamiento, herencia y polimorfismo.

Necesito identificar las clases principales, atributos, métodos y relaciones entre objetos.
```

---

## Prompt 3 — Aplicación de patrones

```text
¿Cómo puedo implementar un patrón Strategy o una alternativa basada en polimorfismo para manejar descuentos de membresías Premium y Básica en Java?
```

---

## Prompt 4 — Validación SOLID

```text
Evalúa si la solución propuesta cumple con los principios SOLID e identifica cuáles se aplican en el diseño.
```

---

## Prompt 5 — Evidencia de ejecución

```text
Genera un ejemplo de ejecución por consola para un sistema de alquiler de películas con membresía Premium y cálculo automático de descuentos.
```

---

# Conclusión

La implementación del sistema permitió aplicar conceptos fundamentales de programación orientada a objetos, principios SOLID y patrones de diseño.

El uso de inteligencia artificial generativa facilitó:
- el análisis del problema,
- la identificación de patrones adecuados,
- la estructuración del diseño,
- y la validación de buenas prácticas de desarrollo.

Sin embargo, fue necesario validar manualmente cada decisión para asegurar que la solución cumpliera correctamente con los requisitos planteados.