## EJERCICIO # 1 mins 
Llegue tarde y solo alcance a 
PROMPT:  📝 Problema #1: El Videoclub de Don
Duración: Máximo 15 minutos
Don Mario acaba de abrir un videoclub moderno donde los clientes pueden alquilar peliculas fisicas o digitales. El problema es que su sistema anterior era un caos: todos los precios se calculaban igual sin importar el tipo de pelicula o membresia del cliente, y no habia forma de saber que peliculas estaban disponibles en tiempo real.
Tu Mision
Ayuda a Don Mario creando un sistema de alquiler que permita:

1. Registrar peliculas (fisicas o digitales) con su disponibilidad.
2. Que el cliente elija X peliculas para alquilar.
3. Calcular el precio total segun su tipo de membresia:
    * Basica: precio normal.
    * Premium: 20% de descuento.
4. Mostrar al finalizar un recibo con las peliculas, precio por unidad y total.
   Peliculas Disponibles

* [Fisica] Interestellar - $8.000 - Disponible
* [Fisica] El Padrino - $7.000 - No disponible
* [Digital] Inception - $5.000 - Disponible
* [Digital] Matrix - $6.000 - Disponible
  Caso de Ejemplo
  Membresia del cliente: Premium Seleccione peliculas (numeros separados por coma): 1,3

```
--- RECIBO DE ALQUILER ---
Cliente: Premium
Peliculas:
 - Interestellar (Fisica) - $8.000
 - Inception (Digital) - $5.000
Subtotal: $13.000
Descuento (20%): $2.600
Total a pagar: $10.400
--------------------------
¡Disfrute su pelicula!

```

Objetivos del Ejercicio

* Identificar cual o cuales patrones de diseno utilizar.
* Explicar que principios de SOLID se aplican.
* Aplicar polimorfismo y encapsulamiento.
* Colocar evidencia de la ejecucion del ejercicio (ejecucion por consola; no es necesario hacer front). ayudame a resolver este ejercicio
# SOLUCION.md — Problema #1: El VideoClub de Don Mario

---

## 1. Patrones de Diseño Identificados

### Patrón 1 — Strategy (Membresía)
| Elemento | Clase/Elemento |
|---|---|
| Contexto | `Main` (orquestador) |
| Estrategia (interfaz) | `MembershipType` (enum con comportamiento) |
| Estrategias concretas | `BASIC` (0 %), `PREMIUM` (20 %) |

**Por qué Strategy:** El algoritmo de descuento varía según la membresía. Con Strategy, cada `MembershipType` encapsula su propia fórmula (`applyDiscount`, `calculateDiscount`). Para agregar membresía VIP con 30% solo se agrega un enum constant, sin tocar nada más.

```
Main ──usa──► MembershipType.applyDiscount(subtotal)
                 ├── BASIC   → subtotal × 0%
                 └── PREMIUM → subtotal × 20%
```

---

### Patrón 2 — Template Method (Tipo de película)
| Elemento | Clase |
|---|---|
| Clase abstracta (esqueleto) | `Movie` |
| Método abstracto (paso variable) | `getType()` |
| Implementaciones concretas | `PhysicalMovie.getType()` → "Física"  /  `DigitalMovie.getType()` → "Digital" |

**Por qué Template Method:** `Movie` define la estructura general (título, precio, disponibilidad, `toString()`), pero delega a las subclases el dato que las diferencia: su tipo. Así `ReceiptPrinter` imprime cualquier película sin conocer si es física o digital.

```
Movie (abstract)
  ├── PhysicalMovie → getType() = "Física"
  └── DigitalMovie  → getType() = "Digital"
```

---

## 2. Principios SOLID Aplicados

| Principio | Aplicación concreta |
|---|---|
| **SRP** (Single Responsibility) | `Movie` = datos · `RentalService` = cálculo de precios · `ReceiptPrinter` = impresión · `Catalog` = inventario · `Main` = I/O |
| **OCP** (Open/Closed) | Nuevo tipo de película → nueva subclase de `Movie`, sin modificar código existente. Nueva membresía → nuevo enum constant en `MembershipType`. |
| **LSP** (Liskov Substitution) | `PhysicalMovie` y `DigitalMovie` son completamente sustituibles por `Movie` en `RentalService` y `ReceiptPrinter`. |
| **ISP** (Interface Segregation) | No se crean interfaces con métodos que no se usen; `Movie` expone sólo lo necesario. |
| **DIP** (Dependency Inversion) | `RentalService` depende de `Movie` (abstracto), no de `PhysicalMovie` ni `DigitalMovie`. |

---

## 3. Estructura del Proyecto

```
src/main/java/videoclub/
│
├── Main.java                        ← Punto de entrada, I/O de consola
│
├── model/
│   ├── Movie.java                   ← Clase abstracta (Template Method)
│   ├── PhysicalMovie.java           ← Película física
│   ├── DigitalMovie.java            ← Película digital
│   └── MembershipType.java          ← Enum Strategy con descuentos
│
├── service/
│   ├── Catalog.java                 ← Inventario de películas
│   └── RentalService.java           ← Lógica de negocio (cálculo de precios)
│
└── util/
    └── ReceiptPrinter.java          ← Impresión del recibo
```

---

## 4. Evidencia de Ejecución

### Escenario 1 — Caso del enunciado (Premium, películas 1 y 3)
```
Membresía del cliente: Premium
Seleccione películas: 1,3

--- RECIBO DE ALQUILER ---
Cliente: Premium
Películas:
  - Interestellar (Física)  - $8.000
  - Inception (Digital)     - $5.000

Subtotal:              $13.000
Descuento (20%):       $2.600
Total a pagar:         $10.400
--------------------------
¡Disfrute su película!
```

### Escenario 2 — Membresía Básica (sin descuento), 3 películas
```
Membresía del cliente: Básica
Seleccione películas: 1,3,4

--- RECIBO DE ALQUILER ---
Cliente: Básica
Películas:
  - Interestellar (Física)  - $8.000
  - Inception (Digital)     - $5.000
  - Matrix (Digital)        - $6.000

Subtotal:              $19.000
Total a pagar:         $19.000
--------------------------
¡Disfrute su película!
```

### Escenario 3 — Intento de alquiler de película no disponible
```
Seleccione películas: 1,2
  ⚠ "El Padrino" no está disponible.
```

### Escenario 4 — Premium, solo películas digitales
```
Seleccione películas: 3,4

--- RECIBO DE ALQUILER ---
Cliente: Premium
Películas:
  - Inception (Digital)  - $5.000
  - Matrix (Digital)     - $6.000

Subtotal:              $11.000
Descuento (20%):       $2.200
Total a pagar:         $8.800
--------------------------
¡Disfrute su película!
```

---

## 5. Diagrama de Clases (resumen)

```
               ┌─────────────────┐
               │   <<abstract>>  │
               │     Movie       │
               │─────────────────│
               │ title: String   │
               │ price: double   │
               │ available: bool │
               │─────────────────│
               │ +getType(): Str │  ← abstracto (Template Method)
               │ +getPrice()     │
               │ +isAvailable()  │
               └────────┬────────┘
                        │ hereda
          ┌─────────────┴─────────────┐
          │                           │
  ┌───────┴────────┐         ┌────────┴───────┐
  │ PhysicalMovie  │         │  DigitalMovie  │
  │────────────────│         │────────────────│
  │ getType()="Fís"│         │ getType()="Dig"│
  └────────────────┘         └────────────────┘

  ┌──────────────────────────────────────┐
  │  <<enum>>  MembershipType            │  ← Strategy
  │──────────────────────────────────────│
  │  BASIC(0%)  /  PREMIUM(20%)          │
  │──────────────────────────────────────│
  │  +applyDiscount(subtotal): double    │
  │  +calculateDiscount(sub): double     │
  │  +discountLabel(): String            │
  └──────────────────────────────────────┘
```
## EJERCICIO # 25 mins
PROMPT: Tengo que resolver el siguiente ejercicio y necesito que me proporciones cada una de las cosas que se pide, te voy a adjuntar un poco de contexto (le adjunte las imagenes y el codigo)
🛒 Problema #2: Tienda Virtual
Duración: Máximo 25 minutos
Descripción del Problema
Una tienda virtual necesita implementar un sistema de pagos que soporte múltiples métodos de pago:

* Tarjeta de crédito
* PayPal
* Criptomonedas
  Cada método tiene su propio proceso de validación y ejecución. El sistema debe:

1. Crear objetos de pago y sus validadores correspondientes
2. No exponer los detalles internos a la lógica principal de compras
3. Notificar automáticamente a otros componentes cuando se procesa un pago exitoso:
    * 📦 Módulo de inventario: descontar del stock
    * 📄 Módulo de facturación: generar factura
    * 📧 Módulo de notificaciones: enviar correo al cliente
      Requisitos Técnicos
      La solución debe ser flexible para:

* ✅ Soportar nuevos métodos de pago sin modificar la lógica existente
* ✅ Permitir que nuevos módulos reaccionen a eventos de pago sin cambiar el core
  Pistas de patrones:

* Se requiere un mecanismo para crear familias de objetos relacionados (pago + validador)
* Se requiere un mecanismo para notificar automáticamente a múltiples observadores de eventos
  🎓 Objetivos de Aprendizaje
  Para el Problema #2 - Tienda Virtual

1. Identificar patrones: ¿Qué dos patrones de diseño se están utilizando? ¿Son los adecuados o alguno debería cambiar?
2. Completar implementación: ¿Qué clases/interfaces hacen falta para que el código satisfaga correctamente los patrones utilizados?
    * Documfile:/D:/Documents/Semestre%206/DOSW/IAGenInsideEngineering/src/test/java/eci/edu/byteProgramming/ejercicio/paper/entar en el `SOLUCION.md` (no es necesario modificar los diagramas)
3. Validar diagrama: ¿El diagrama de contexto proporciona información suficiente y pertinente?file:/D:/Documents/Semestre%206/DOSW/IAGenInsideEngineering/src/main/java/eci/edu/byteProgramming/ejercicio/paper/
    * Si es necesario hacer cambios, documentarlos en el `SOLUCION.md`
4. Identificar errores:file:/D:/Documents/Semestfile:/D:/Documents/Semestre%206/DOSW/IAGenInsideEngineering/docs/uml/users.pngre%206/DOSW/IAGenInsideEngineering/docs/uml/clases.png ¿Qué errores del código proporcionado identificaste? ¿Por qué no compila?
5. Corregir código: Implementar las correcciones necesarias para que el sistema funcione correctamente
6. Ejecutar pruebas:
    * Ejecutar las pruebas unitarias prfile:/D:/Documents/Semestre%206/DOSW/IAGenInsideEngineering/docs/imagenes/contexto.pngoporcionadas
    * Corregir las pruebas en caso de que fallen
    * Verificar que todo compila y pasa las pruebas . Tengo que resolver el siguiente ejercicio y necesito que me proporciones cada una de las cosas que se pide, te voy a adjuntar un poco de contexto
# SOLUCION.md — Problema #2: Tienda Virtual

---

## 1. Identificación de Patrones de Diseño

### Patrones utilizados

| Patrón | Clase(s) involucradas | ¿Adecuado? |
|---|---|---|
| **Abstract Factory** | `PaymentMethod`, `CreditCardFactory`, `PaypalFactory`, `CryptoFactory`, `PaymentFactory` (faltante) | ⚠️ Parcialmente adecuado |
| **Observer** | `ECIPayment`, `PaymentObserver`, `PaymentEventObserver` | ✅ Adecuado |

### Análisis crítico

**Abstract Factory** — Se intenta usar para crear objetos de pago, pero la implementación está incompleta:
- Falta la interfaz `PaymentFactory` que defina el contrato para todas las fábricas.
- Las clases `CreditCardFactory`, `PaypalFactory` y `CryptoFactory` extienden directamente `PaymentMethod` en lugar de ser fábricas separadas. Esto mezcla la responsabilidad de "crear" con la de "ejecutar el pago".
- El nombre "Factory" en las clases concretas es engañoso: son productos, no fábricas.
- El patrón más preciso para este caso sería **Factory Method**, donde cada subclase concreta implementa la creación de su propio método de pago. El Abstract Factory aplica mejor cuando se crean *familias* de objetos relacionados (p.ej. pago + validador como objetos separados). Con la estructura actual —donde validación y pago están en la misma clase— el **Factory Method** es más apropiado.

**Observer** — Correctamente aplicado:
- `PaymentObserver` es la interfaz del observador.
- `ECIPayment` actúa como el sujeto (subject) que notifica.
- `PaymentEventObserver` es el observador concreto que reacciona coordinando inventario, facturación y notificaciones.

---

## 2. Clases/Interfaces Faltantes

Para que los patrones funcionen correctamente se necesitan:

### 2.1 Interfaz `PaymentFactory` (Abstract Factory / Factory Method)
Define el contrato que deben implementar todas las fábricas de métodos de pago.

```java
public interface PaymentFactory {
    PaymentMethod createPaymentMethod(double amount, String customerId, String description);
}
```

### 2.2 Fábricas concretas separadas
Las clases `CreditCardFactory`, `PaypalFactory`, `CryptoFactory` deben ser **fábricas** (que implementan `PaymentFactory`) separadas de los **productos** (`CreditCardPayment`, `PaypalPayment`, `CryptoPayment`).

Sin embargo, dado el tiempo del ejercicio y que el código existente ya mezcla fábrica+producto, la corrección mínima es hacer que las clases actuales implementen `PaymentFactory` además de extender `PaymentMethod`.

### 2.3 Corrección de `PaymentEventObserver`
Importa `javax.management.Notification` en lugar de la clase propia `Notification`. Se debe corregir el import.

---

## 3. Validación del Diagrama de Contexto

### Evaluación
El diagrama de contexto (contexto.png) **proporciona información suficiente a nivel conceptual**, pero tiene las siguientes omisiones:

| Aspecto | Problema | Propuesta de mejora |
|---|---|---|
| Actor "Sistema de pago" aparece como actor UML (figura humana) | El sistema de pago no es un actor humano | Representarlo como sistema/componente |
| No muestra los **métodos de pago** como componentes del sistema | El diagrama no refleja que hay 3 métodos distintos | Agregar un sub-módulo de métodos de pago |
| La "Notificación" aparece como componente independiente separado del "Módulo notificación" | Genera confusión: son el mismo concepto | Unificarlos en un solo módulo |
| No muestra el flujo cuando el pago **falla** | El Observer también maneja pagos fallidos | Agregar flujo alternativo de fallo |
| No incluye el patrón de diseño en el contexto | Dificulta entender la arquitectura | Mencionar Observer y Factory en notas |

### Cambios documentados
- La "Notificación" del diagrama representa el evento del patrón Observer, no un módulo separado. Se recomienda renombrarla a "Evento de Pago (Observer)" para claridad.
- El "Módulo notificación" y la "Notificación" son distintos: uno es el evento interno, el otro es el servicio de correo. El diagrama debe diferenciarlos mejor.

---

## 4. Errores Identificados en el Código

### Error 1 — `PaymentEventObserver.java`: Import incorrecto
```java
// ❌ INCORRECTO
import javax.management.Notification;

// ✅ CORRECTO
import eci.edu.byteProgramming.ejercicio.paper.util.Notification;
```
**Causa:** Se importó la clase `Notification` del paquete JMX de Java en lugar de la clase propia del proyecto. Esto hace que el campo `notification` sea del tipo incorrecto y no compile.

---

### Error 2 — `PaymentMethod.java`: Constructor asigna mal `customerID`
```java
// ❌ INCORRECTO
public PaymentMethod(double amount, String transactionID, String description) {
    this.amount = amount;
    this.customerID = customerID;  // ← customerID es null (no viene del parámetro)
    this.description = description;
    ...
}
```
El segundo parámetro se llama `transactionID` pero debería llamarse `customerID`, y la asignación `this.customerID = customerID` usa la variable de instancia no el parámetro.

```java
// ✅ CORRECTO
public PaymentMethod(double amount, String customerID, String description) {
    this.amount = amount;
    this.customerID = customerID;
    this.description = description;
    ...
}
```

---

### Error 3 — Falta la interfaz `PaymentFactory`
`ECIPayment.processPayment()` recibe un parámetro de tipo `PaymentFactory` que no existe en el proyecto:
```java
public boolean processPayment(PaymentFactory factory, ...) // ← PaymentFactory no existe
```
Se debe crear la interfaz `PaymentFactory`.

---

### Error 4 — `CryptoFactory.java`: Campo `token` asignado con referencia nula
```java
// ❌ INCORRECTO
this.token = token; // token no es parámetro del constructor, siempre es null
```
El constructor no recibe `token` como parámetro pero intenta asignarlo. Debe eliminarse o agregarse como parámetro.

---

### Error 5 — `ECIPayment.java` hace referencia a `PaymentFactory` inexistente
La clase `ECIPayment` llama a `factory.createPaymentMethod(...)` pero `PaymentFactory` no está definida, por lo que no compila.

---

### Error 6 — Las clases Factory no implementan `PaymentFactory`
`CreditCardFactory`, `PaypalFactory` y `CryptoFactory` deben implementar la interfaz `PaymentFactory` para que el polimorfismo funcione en `ECIPayment`.

---

## 5. Correcciones Implementadas

Ver archivos `.java` corregidos en la carpeta `src/`. Los cambios principales son:

1. ✅ Creada interfaz `PaymentFactory.java`
2. ✅ Corregido `PaymentMethod.java` (parámetro `customerID`)
3. ✅ Corregido `PaymentEventObserver.java` (import `Notification`)
4. ✅ `CreditCardFactory`, `PaypalFactory`, `CryptoFactory` implementan `PaymentFactory`
5. ✅ Corregido `CryptoFactory.java` (campo `token` nulo)
6. ✅ Corregidas pruebas unitarias en `auxiliaryTest.java`

---

## 6. Pruebas

Ver `auxiliaryTest.java` corregido con casos de prueba para:
- Pago exitoso con tarjeta de crédito
- Pago exitoso con PayPal
- Pago exitoso con criptomonedas
- Validación fallida de tarjeta inválida
- Validación fallida de PayPal sin token
- Verificación del patrón Observer (notificación a observadores)
