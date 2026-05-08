# Problema #2 — Tienda Virtual

## Estudiante

- Nombre: Jeyder Lancheros
- Curso: CVDS / DOSW
- Fecha: Mayo 2026

---

# Descripción

La tienda virtual requiere un sistema de pagos flexible y escalable que permita soportar múltiples métodos de pago:

- Tarjeta de crédito
- PayPal
- Criptomonedas

Cada método de pago posee su propio proceso de validación y ejecución. Además, el sistema debe notificar automáticamente diferentes módulos cuando un pago sea procesado exitosamente.

---

# Patrones de Diseño Utilizados

## Abstract Factory

Se implementó el patrón Abstract Factory para crear familias de objetos relacionadas sin depender de implementaciones concretas.

Cada fábrica concreta se encarga de crear:

- Un método de pago.
- Su validador correspondiente.

Esto permite agregar nuevos métodos de pago sin modificar la lógica principal del sistema.

### Fábricas implementadas

- CreditCardPaymentFactory
- PaypalPaymentFactory
- CryptoPaymentFactory

---

## Observer

Se implementó el patrón Observer para notificar automáticamente diferentes módulos cuando un pago es procesado exitosamente.

Los módulos observadores son:

- Inventory
- Facturation
- Notification

Gracias a este patrón, el sistema principal no depende directamente de los módulos secundarios.

---

# Principios SOLID Aplicados

## Single Responsibility Principle

Cada clase tiene una única responsabilidad.

Ejemplos:

- Los validadores solo validan pagos.
- Los métodos de pago solo procesan pagos.
- Los observadores solo reaccionan a eventos.

---

## Open/Closed Principle

El sistema puede extenderse fácilmente agregando nuevos métodos de pago sin modificar código existente.

---

## Liskov Substitution Principle

Las implementaciones concretas pueden sustituir sus abstracciones sin afectar el comportamiento del sistema.

---

## Interface Segregation Principle

Las interfaces están separadas según responsabilidades específicas.

---

## Dependency Inversion Principle

La lógica principal depende de interfaces y no de implementaciones concretas.

---

# Errores Identificados y Corregidos

## Problemas encontrados

- Interfaces incompletas.
- Métodos sin implementación.
- Relaciones incorrectas entre factories y validadores.
- Falta de desacoplamiento.
- Observadores no registrados correctamente.
- Problemas de compilación por dependencias faltantes.
- Clases que mezclaban responsabilidades incompatibles.
- Implementaciones incorrectas del patrón Abstract Factory.

---

## Correcciones realizadas

- Implementación completa de interfaces.
- Creación de fábricas concretas.
- Separación entre factories y métodos de pago.
- Implementación correcta del patrón Observer.
- Corrección de imports y relaciones entre clases.
- Ajuste de la lógica de notificaciones.
- Refactorización de clases para cumplir SRP.

---

# Estructura Final del Proyecto

```text
util
│
├── PaymentFactory.java
├── PaymentMethod.java
├── ValidatePayment.java
├── PaymentObserver.java
├── PaymentStatus.java
│
├── CreditCardPayment.java
├── PaypalPayment.java
├── CryptoPayment.java
│
├── CreditCardPaymentFactory.java
├── PaypalPaymentFactory.java
├── CryptoPaymentFactory.java
│
├── ECIPayment.java
├── PaymentEventObserver.java
├── Inventory.java
├── Facturation.java
├── Notification.java
├── Product.java
```

---

# Evidencia de Ejecución

```text
Processing Credit Card payment...
Payment approved for card: ****1111
Inventory updated successfully
Invoice generated successfully
Confirmation email sent to: juan@gmail.com
```

---

# Prompts Utilizados

## Prompt 1 — Identificación de patrones

```text
Analiza un sistema de pagos en Java que soporta tarjeta de crédito, PayPal y criptomonedas.

El sistema debe crear objetos relacionados de pago y validación, además de notificar automáticamente módulos externos cuando un pago sea exitoso.

¿Qué patrones de diseño son adecuados y por qué?
```

---

## Prompt 2 — Arquitectura desacoplada

```text
Diseña una arquitectura orientada a objetos en Java para un sistema de pagos escalable utilizando principios SOLID y desacoplamiento entre componentes.
```

---

## Prompt 3 — Implementación de Abstract Factory

```text
Explícame cómo implementar el patrón Abstract Factory en Java para crear familias de objetos relacionadas entre métodos de pago y validadores.
```

---

## Prompt 4 — Implementación de Observer

```text
Ayúdame a implementar el patrón Observer en Java para notificar automáticamente módulos de inventario, facturación y notificaciones cuando un pago sea procesado exitosamente.
```

---

## Prompt 5 — Refactorización de código

```text
Identifica problemas de diseño en un sistema Java donde las clases mezclan responsabilidades de factories, validación y procesamiento de pagos.

Propón una refactorización aplicando principios SOLID y patrones de diseño.
```

---

## Prompt 6 — Corrección de errores

```text
Ayúdame a corregir errores de compilación, dependencias e imports en un sistema Java basado en Abstract Factory y Observer.
```

---

## Prompt 7 — Validación de arquitectura

```text
Evalúa si la solución propuesta cumple correctamente con los patrones Abstract Factory y Observer y explica posibles mejoras de diseño.
```

---

# Conclusión

La implementación del sistema permitió aplicar correctamente los patrones Abstract Factory y Observer para desarrollar una arquitectura desacoplada, flexible y escalable.

El uso de principios SOLID mejoró la organización del código y facilitó la extensión del sistema.

La inteligencia artificial generativa permitió acelerar el análisis, diseño y corrección de errores durante el desarrollo de la solución.