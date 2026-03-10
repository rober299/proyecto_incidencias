@echo off
chcp 65001 > nul
echo === GENERADOR DE CARPETA DIARIA DE EVIDENCIAS ===
echo.

:: Obtenemos la ruta absoluta de forma segura
set "RUTA_BASE=%cd%"

:: Usamos PowerShell para sacar la fecha exacta sin fallos de idioma y la guardamos
for /f "delims=" %%a in ('powershell -Command "Get-Date -Format yyyy-MM-dd"') do set "FECHA=%%a"

:: Montamos la ruta final
set "RUTA_FINAL=%RUTA_BASE%\evidencias\jornada_%FECHA%"

echo Utilizando la ruta absoluta: 
echo %RUTA_FINAL%
echo.

:: Creamos la carpeta
mkdir "%RUTA_FINAL%" 2>nul

echo Carpeta de la jornada %FECHA% creada con exito
echo.
pause