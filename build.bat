@echo off
echo ==========================================
echo   SISTEMA DE BUILD: PROYECTO INCIDENCIAS
echo ==========================================

echo [1/3] Limpiando compilaciones anteriores...
del /q src\*.class 2>nul
if %errorlevel% neq 0 echo [INFO] No habia archivos para limpiar.

echo [2/3] Compilando codigo fuente y pruebas...
javac src\*.java
if %errorlevel% neq 0 (
    echo [ERROR CRITICO] La compilacion ha fallado.
    exit /b %errorlevel%
)
echo [OK] Compilacion exitosa.

echo [3/3] Ejecutando Bateria de Pruebas Unitarias...
java -cp src TestTDD
if %errorlevel% neq 0 (
    echo [ERROR CRITICO] Algunos tests han fallado. El build no es estable.
    exit /b %errorlevel%
)

echo.
echo ==========================================
echo  [BUILD CORRECTO] Proyecto reconstruido.
echo ==========================================
pause