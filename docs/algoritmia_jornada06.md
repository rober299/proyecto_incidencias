# Algoritmia y Resolución Estructurada

## BLOQUE 1: Pseudocódigo de los 3 ejercicios

### Ejercicio 1: Validación de usuario

FUNCIÓN validar_usuario(email, password)
SI email NO CONTIENE "@" ENTONCES
RETORNAR Falso
FIN SI
SI LONGITUD(password) < 8 ENTONCES
RETORNAR Falso
FIN SI
RETORNAR Verdadero
FIN FUNCIÓN

### Ejercicio 2: Cálculo de prioridad de incidencia

FUNCIÓN calcular_prioridad(impacto, urgencia)
SI impacto == "Alto" Y urgencia == "Alta" ENTONCES
RETORNAR "Crítica"
SINO SI impacto == "Alto" Y urgencia == "Baja" ENTONCES
RETORNAR "Alta"
SINO SI impacto == "Bajo" Y urgencia == "Alta" ENTONCES
RETORNAR "Media"
SINO
RETORNAR "Baja"
FIN SI
FIN FUNCIÓN

### Ejercicio 3: Filtrado de tickets por estado y fecha

FUNCIÓN filtrar_tickets(lista_tickets, estado_buscado, fecha_buscada)
VARIABLE resultado = LISTA_VACIA
PARA CADA ticket EN lista_tickets
SI ticket.estado == estado_buscado Y ticket.fecha == fecha_buscada ENTONCES
AGREGAR ticket A resultado
FIN SI
FIN PARA
RETORNAR resultado
FIN FUNCIÓN

---

## BLOQUE 2: Tablas de Decisión y Justificaciones

### Ejercicio 1: Validación de usuario

**Tabla de Decisión:**

| Email contiene "@" | Longitud password >= 8 | Resultado de Validación |
| :----------------- | :--------------------- | :---------------------- |
| Sí                 | Sí                     | Verdadero               |
| Sí                 | No                     | Falso                   |
| No                 | Sí                     | Falso                   |
| No                 | No                     | Falso                   |

**Justificación de decisiones:**

- **Entradas:** `email` (texto), `password` (texto).
- **Salidas:** Booleano (Verdadero/Falso).
- **Decisiones:** Se estructura en una tabla de doble entrada lógica. La regla de negocio es estricta: solo si ambas condiciones se cumplen, el usuario es válido. Validar esto en memoria evita consultas inútiles a la base de datos.

### Ejercicio 2: Cálculo de prioridad de incidencia

**Tabla de Decisión:**

| Impacto | Urgencia | Prioridad Resultante |
| :------ | :------- | :------------------- |
| Alto    | Alta     | Crítica              |
| Alto    | Baja     | Alta                 |
| Bajo    | Alta     | Media                |
| Bajo    | Baja     | Baja                 |

**Justificación de decisiones:**

- **Entradas:** `impacto` (texto), `urgencia` (texto).
- **Salidas:** Nivel de prioridad calculado (texto).
- **Decisiones:** Las combinaciones son finitas (2x2). La tabla de decisión mapea directamente cada caso posible, evitando anidar sentencias "SI" complejas y reduciendo la posibilidad de errores lógicos.

### Ejercicio 3: Filtrado de tickets por estado y fecha

**Tabla de Decisión (Condición de filtrado por ticket):**

| Coincide Estado buscado | Coincide Fecha buscada | Acción sobre el ticket      |
| :---------------------- | :--------------------- | :-------------------------- |
| Sí                      | Sí                     | Agregar a lista 'resultado' |
| Sí                      | No                     | Ignorar ticket              |
| No                      | Sí                     | Ignorar ticket              |
| No                      | No                     | Ignorar ticket              |

**Justificación de decisiones:**

- **Entradas:** `lista_tickets` (colección), `estado_buscado` (texto), `fecha_buscada` (fecha).
- **Salidas:** Sublista de tickets filtrada.
- **Decisiones:** Para que un ticket pase el filtro, debe cumplir obligatoriamente las dos condiciones establecidas (operador lógico AND). La tabla muestra claramente que cualquier fallo en una de las condiciones descarta el ticket.
