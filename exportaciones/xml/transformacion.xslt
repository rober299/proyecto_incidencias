<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/incidencias">
        <html>
            <head>
                <title>Informe de Incidencias XML</title>
                <style>
                    body { font-family: Arial, sans-serif; background-color: #f4f7f6; padding: 20px; }
                    h1 { color: #0056b3; }
                    table { width: 100%; border-collapse: collapse; margin-top: 20px; background: white; }
                    th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
                    th { background-color: #0056b3; color: white; }
                    .urgente { background-color: #ffeeba; }
                    .critica { background-color: #f8d7da; color: #721c24; font-weight: bold; }
                </style>
            </head>
            <body>
                <h1>Reporte Exportado de Incidencias</h1>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Título</th>
                        <th>Estado</th>
                        <th>Prioridad</th>
                        <th>Técnico</th>
                        <th>Fecha</th>
                    </tr>
                    <xsl:for-each select="incidencia">
                        <xsl:sort select="@id" data-type="number" order="ascending"/>
                        <tr>
                            <xsl:if test="@prioridad='Alta'">
                                <xsl:attribute name="class">urgente</xsl:attribute>
                            </xsl:if>
                            <xsl:if test="@prioridad='Critica'">
                                <xsl:attribute name="class">critica</xsl:attribute>
                            </xsl:if>
                            
                            <td><xsl:value-of select="@id"/></td>
                            <td><xsl:value-of select="titulo"/></td>
                            <td><xsl:value-of select="@estado"/></td>
                            <td><xsl:value-of select="@prioridad"/></td>
                            <td><xsl:value-of select="tecnico_asignado"/></td>
                            <td><xsl:value-of select="fecha_creacion"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>