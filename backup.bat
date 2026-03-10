@echo off
chcp 65001 > nul
echo === Iniciando copia de seguridad ===
echo.
echo Utilizando rutas relativas (.\) para localizar los archivos...
echo. 

:: Comprime las carpetas principales y lo guarda en la carpeta evidencias
powershell -Command "Compress-Archive -Path '.\api', '.\bd', '.\docs', '.\frontend', '.\movil', '.\src', '.\tests' -DestinationPath '.\evidencias\backup_proyecto.zip' -Update"

echo Copia de seguridad completada con exito. El archivo backup_proyecto.zip esta en la carpeta de evidencias.
echo.
pause