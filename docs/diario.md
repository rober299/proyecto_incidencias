# Diario de Prácticas

## Jornada 01 - Lunes 9 de marzo de 2026

### 📝 ¿Qué he hecho hoy?

- Preparación del entorno de trabajo remoto.
- Instalación, verificación y documentación de herramientas: IntelliJ IDEA, Visual Studio Code, Android Studio, JDK, Git, MySQL Workbench, Postman y Google Chrome.
- Creación de la estructura base del proyecto (`proyecto_incidencias`) con las carpetas profesionales: `/docs`, `/src`, `/bd`, `/api`, `/frontend`, `/movil`, `/tests` y `/evidencias`.
- Redacción del archivo `README.md` inicial definiendo el propósito del proyecto, el stack tecnológico y las reglas de nomenclatura.
- Inicialización del repositorio Git local, creación de las ramas `main` y `desarrollo`, y configuración de archivos clave (`.gitignore`, `LICENSE` y `.gitmessage`).
- Subida del código al repositorio remoto en GitHub.

### ⚠️ ¿Qué problemas he tenido?

- Error en Git (`not a valid object name: 'main'`) al intentar crear la rama de desarrollo.
- Error de autenticación (`Connection refused` / `Password authentication is not supported`) al intentar hacer el `git push` al repositorio remoto en GitHub desde la terminal.

### 💡 ¿Cómo los he resuelto?

- El problema de la rama se resolvió haciendo un primer commit inicial con el archivo `README.md` para que la rama `main` existiera realmente en el historial antes de ramificar.
- El problema de GitHub se solucionó utilizando el inicio de sesión integrado del navegador ("Sign in with your browser") que ofrece Visual Studio Code para generar el acceso seguro sin usar contraseña plana.

### ⏳ ¿Qué queda pendiente?

- Mañana toca la Jornada 02: Repaso de sistemas y terminal (creación de chuleta de comandos, scripts de automatización para backups/evidencias y conceptos de red).

## Jornada 02 - Martes 10 de marzo de 2026

### 📝 Resumen del día

- **Bloque 1:** Creación de documento de comandos y pruebas prácticas de consola (navegación, copias, borrado, permisos, compresión y procesos).
- **Bloque 2:** Desarrollo de scripts de automatización en Batch (`backup.bat` y `carpeta_diaria.bat`). Diferenciación entre rutas relativas (`.\`) y rutas absolutas (`%cd%`).
- **Bloque 3:** Diagnóstico de red mediante comandos, `ping`, `tracert` y `netstat`. Revisar conceptos como localhost, IP privada y puertos.

### Bloque 4: Variables de Entorno (Registro y Explicación)

Se ha configurado la variable de entorno `JAVA_HOME` apuntando al directorio de instalación del JDK.

- **¿Qué son?** Las variables de entorno son unos "atajos" globales o etiquetas de texto que guarda el sistema operativo. Contienen información sobre cómo y dónde deben ejecutarse los programas.
- **¿Para qué sirven?** Sirven para que cualquier programa como Visual Studio Code pueda encontrar herramientas necesarias (como el compilador de Java) sin tener que decirle la ruta exacta absoluta cada vez. Si Java se actualiza y cambia de carpeta, solo cambias la variable y todos los programas se enteran a la vez.
- **¿Cómo comprobar que están activas?** Se puede verificar desde la terminal. En PowerShell, basta con ejecutar el comando: `echo $env:JAVA_HOME`. Si devuelve la ruta configurada, la variable está activa y lista para usarse.
