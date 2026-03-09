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
