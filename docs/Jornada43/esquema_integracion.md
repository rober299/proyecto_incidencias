# Esquema de Integración de Sistemas Distribuidos

**Fecha:** Miércoles 13 de mayo de 2026

La API REST de Gestión IT se ha diseñado como un nodo central (Backend-as-a-Service) preparado para interactuar con diversos consumidores en una arquitectura distribuida.

## Topología de Integraciones Futuras

### 1. Cliente Web (Frontend SPA)

- **Tecnología:** React / Angular / Vue.
- **Integración:** Consumirá los endpoints a través de peticiones AJAX (`fetch` o `axios`). Gestionará el estado de la sesión guardando el JWT en memoria o `sessionStorage` y lo inyectará en la cabecera `Authorization` en cada ruta.

### 2. Cliente Móvil (App Nativos/Híbrida)

- **Tecnología:** Flutter / React Native.
- **Integración:** Orientado principalmente al personal técnico en movilidad (para cerrar incidencias in-situ). Utilizará almacenamiento seguro del dispositivo (Keychain/Keystore) para guardar los tokens de acceso prolongado.

### 3. Procesos Batch (Mantenimiento Nocturno)

- **Tecnología:** Scripts en Python / Tareas Cron en Java.
- **Integración:** Consumidores automatizados que interactúan con la API de madrugada para generar reportes, purgar incidencias cerradas o hacer volcados masivos mediante endpoints protegidos para roles `ADMIN` o de máquina.

### 4. Integraciones de Terceros (Webhooks)

- **Escenario:** Sistemas de RRHH (Ej: SAP o Workday).
- **Integración:** Cuando un nuevo empleado entra en la empresa, el sistema de RRHH consumirá nuestro endpoint `POST /api/v1/usuarios` para aprovisionarlo automáticamente en el sistema de tickets IT sin intervención manual.
