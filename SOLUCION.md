# IA Generativa en Ingeniería de Software
## Taller de Diseño y Arquitectura Asistidos por IA

## Descripción General

Este repositorio contiene la solución a un taller práctico enfocado en el uso de Inteligencia Artificial Generativa para apoyar el diseño y la arquitectura de software. El objetivo principal es analizar cómo la IA puede asistir el proceso de desarrollo bajo diferentes niveles de contexto, manteniendo siempre el control y la validación por parte del desarrollador.

El taller se divide en dos problemas que permiten comparar los resultados obtenidos con información limitada frente a un contexto enriquecido.

---

## Objetivos de Aprendizaje

- Analizar el uso de IA generativa como apoyo al diseño de software.
- Formular prompts efectivos según el nivel de información disponible.
- Identificar y aplicar patrones de diseño.
- Aplicar principios SOLID.
- Integrar enunciados, código y diagramas UML.
- Validar y corregir soluciones generadas con IA.

---

## Problemas del Taller

### Problema 1: El Videoclub de Don Mario

Este problema se desarrolla con un contexto limitado, utilizando únicamente el enunciado proporcionado. No se cuenta con código previo ni diagramas UML.

El objetivo es diseñar desde cero un sistema de alquiler de películas que permita:
- Registrar películas físicas y digitales.
- Manejar disponibilidad.
- Calcular precios según tipo de membresía.
- Aplicar descuentos.
- Mostrar un recibo final por consola.

Se aplican principios de Programación Orientada a Objetos, encapsulamiento, polimorfismo y patrones de diseño como Strategy. La solución y los prompts utilizados se documentan en detalle en el archivo SOLUCION.md.

---

### Problema 2: Tienda Virtual

Este problema cuenta con un contexto enriquecido que incluye:
- Enunciado del problema.
- Código incompleto.
- Diagramas UML.
- Casos de uso.

El sistema debe soportar múltiples métodos de pago, validación independiente y notificación automática a distintos módulos cuando un pago es exitoso.

Se implementan los patrones Abstract Factory y Observer para lograr flexibilidad, extensibilidad y bajo acoplamiento. También se identifican y corrigen errores del código original, asegurando que el proyecto compile correctamente y que las pruebas unitarias pasen exitosamente.

---

## Arquitectura y Patrones Utilizados

Durante el desarrollo de ambos problemas se utilizaron los siguientes patrones y principios:

- Strategy
- Abstract Factory
- Observer
- Principios SOLID
- Inyección de dependencias
- Encapsulamiento
- Polimorfismo

La justificación y aplicación de cada patrón se encuentra documentada en SOLUCION.md.

---

## Estructura del Repositorio