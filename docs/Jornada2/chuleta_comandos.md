# Chuleta de Comandos de Consola

### 1. Navegacion de directorios

- **`pwd`** (Print Working Directory): Muestra la ruta completa de la carpeta en la que te encuentras actualmente.
- **`ls`** (List): Muestra un listado con todos los archivos y carpetas que hay dentro del directorio actual.
- **`cd <ruta>`** (Change Directory): Sirve para cambiar de carpeta. Ejemplo: `cd src` te mete en la carpeta src. `cd ..` te sube un nivel hacia atrás.

### 2. Copia de archivos y carpetas

- **`cp <origen> <destino>`** (Copy): Copia un archivo de un sitio a otro. Ejemplo: `cp archivo.txt copia.txt`. Para copiar carpetas enteras con su contenido, se añade el parámetro `-Recurse`.

### 3. Borrado de archivos

- **`rm <archivo>`** (Remove): Elimina un archivo permanentemente. Ejemplo: `rm copia.txt`. Para borrar una carpeta que tiene cosas dentro se usa `rm <carpeta> -Recurse -Force`.

### 4. Gestión de permisos

- **`icacls <archivo>`**: En Windows, este comando permite ver y modificar las Listas de Control de Acceso (ACL).
- _Ejemplo para ver permisos:_ `icacls archivo.txt`
- _Ejemplo para dar control total al usuario actual:_ `icacls archivo.txt /grant "%USERNAME%":F`

### 5. Compresión de archivos

- **`Compress-Archive`**: Comando nativo de PowerShell para crear archivos ZIP.
- _Ejemplo:_ `Compress-Archive -Path .\mi_carpeta -DestinationPath .\copia_seguridad.zip`
- _Para descomprimir:_ `Expand-Archive -Path .\archivo.zip -DestinationPath .\destino`

### 6. Búsqueda de texto dentro de archivos

- **`Select-String`**: Busca un texto específico dentro del contenido de uno o varios archivos (equivalente al comando `grep` en Linux).
- _Ejemplo:_ `Select-String -Path .\diario.md -Pattern "Jornada"` (Busca la palabra "Jornada" dentro del archivo diario.md).

### 7. Revisión y gestión de procesos

- **`Get-Process`**: Muestra una lista en tiempo real de todos los programas y procesos que se están ejecutando en el ordenador y la memoria que consumen.
- **`Stop-Process -Name <nombre>`**: Fuerza el cierre de un programa. Ejemplo: `Stop-Process -Name chrome`.
