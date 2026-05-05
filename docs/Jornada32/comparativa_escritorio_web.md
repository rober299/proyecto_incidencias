# Comparativa de Interfaces: Escritorio (Swing) vs Web (HTML/JS)

Durante el desarrollo del sistema de incidencias, hemos evaluado dos enfoques de interfaz de usuario. A continuación se detallan las diferencias, ventajas y casos de uso previstos para cada una.

## 1. Cliente de Escritorio (Interfaz Rica / Swing)

**Uso previsto:** Herramienta de uso interno exclusivo para el departamento de IT y administradores de sistemas.

- **Ventajas:**
  - **Atajos y Productividad:** Permite un uso intensivo del teclado y respuestas inmediatas sin tiempos de carga de red para la renderización.
  - **Integración con SO:** Acceso nativo más sencillo a recursos locales del equipo (impresoras locales, lectura de logs de sistema).
  - **Gestión de Estado:** Al mantener la conexión abierta (Stateful), es más fácil gestionar selecciones múltiples complejas o edición en masa.
- **Limitaciones:**
  - Requiere tener la Máquina Virtual de Java (JRE) instalada en cada equipo.
  - Las actualizaciones son complejas (hay que distribuir el nuevo ejecutable `.jar` a todos los técnicos).

## 2. Cliente Web (Frontend HTML/CSS/JS)

**Uso previsto:** Portal público para los usuarios y empleados generales que necesitan reportar incidencias.

- **Ventajas:**
  - **Accesibilidad universal:** Solo requiere un navegador web. Se puede usar desde el móvil, tablet o PC personal.
  - **Despliegue centralizado:** Si se corrige un bug en la interfaz, se actualiza en el servidor y todos los usuarios ven el cambio instantáneamente.
- **Limitaciones:**
  - Dependencia total de la conexión a internet/intranet.
  - Limitaciones de seguridad del navegador para interactuar con el hardware del usuario.

## Conclusión

Se recomienda mantener el **Frontend Web** para el reporte y seguimiento de incidencias por parte de los empleados (interfaz ligera), mientras que la **Interfaz de Escritorio** se perfila como un excelente panel de control interno para los administradores del sistema, garantizando máxima velocidad operativa.
