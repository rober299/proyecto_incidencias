# Especificación Preliminar de Endpoints (Contratos JSON)

Para preparar el salto a la API real de la semana que viene, se definen los siguientes contratos:

## 1. Login de Usuario

- **Endpoint:** `POST /api/login`
- **Petición (Cliente):**
  { "email": "ejemplo@empresa.com", "password": "mipassword123" }
- **Respuesta (Servidor - 200 OK):**
  { "token": "abc123xyz", "rol": "Tecnico", "nombre": "Robert Chislea" }

## 2. Listado de Incidencias

- **Endpoint:** `GET /api/incidencias`
- **Respuesta (Servidor - 200 OK):** Devuelve un array de objetos con la estructura definida en frontend/data/incidencias.json.

## 3. Alta de Comentario

- **Endpoint:** `POST /api/incidencias/{id}/comentarios`
- **Petición (Cliente):**
  { "texto": "Se ha reiniciado el router", "idTecnico": 5 }
- **Respuesta (Servidor - 201 Created):**
  { "idComentario": 89, "fecha": "2026-04-24T10:30:00", "estado": "Guardado" }
