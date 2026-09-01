# Changelog

Este documento recoge la evolución del proyecto **Playmobil Manager**, incluyendo las nuevas funcionalidades, mejoras y correcciones realizadas durante su desarrollo.

El proyecto utiliza una numeración de versiones incremental hasta alcanzar una primera versión estable **1.0**.

---

## v0.1 - Inicio del proyecto

### Proyecto

* Creación del proyecto Maven.
* Configuración de Git y GitHub.
* Configuración inicial de JavaFX.
* Configuración de MySQL.

### Base de datos

* Creación de la base de datos.
* Creación de la tabla `playmobil`.
* Configuración de la conexión mediante JDBC.

### Modelo

* Creación de la clase `Playmobil`.
* Creación del DAO.
* Implementación de la conexión a la base de datos.

---

## v0.2 - CRUD funcional

### Funcionalidad

* Implementada el alta de Playmobil.
* Implementada la modificación de registros.
* Implementada la eliminación de registros.
* Carga automática de la colección desde la base de datos.

### Interfaz

* Primera versión de la interfaz JavaFX.
* Integración del controlador con FXML.
* Configuración de la `TableView`.

---

## v0.3 - Rediseño de la interfaz

### Interfaz

* Nuevo diseño de la ventana principal.
* Añadido formulario completo para los datos del Playmobil.
* Añadido campo de observaciones.
* Organización mediante `VBox` y `HBox`.
* Incorporación de botones Guardar, Modificar y Eliminar.

### Código

* Refactorización de `MainController`.
* Sincronización entre FXML y controlador.

---

## v0.4 - Gestión de imágenes

### Imágenes

* Selección de imágenes mediante `FileChooser`.
* Visualización de imágenes mediante `ImageView`.
* Almacenamiento de la ruta de la imagen en la base de datos.
* Carga automática de la imagen al seleccionar un Playmobil.
* Conservación de la imagen al modificar un registro.

---

## v0.5 - Validaciones

### Validación

* Validación de referencia obligatoria.
* Validación de nombre obligatorio.
* Validación de categoría obligatoria.
* Validación de precios negativos.
* Validación del formato numérico.
* Validación de referencias duplicadas.

### Base de datos

* Añadida restricción `UNIQUE` para la referencia.

### Código

* Creación de la clase `PlaymobilValidator`.
* Mejora del tratamiento de errores mediante alertas.

---

## v0.6 - Mejoras de usabilidad

### Interfaz

* Sustitución del campo Categoría por un `ComboBox`.
* Centralización de categorías mediante `CategoriasPlaymobil`.
* Mejora del diseño mediante CSS.
* Mejor organización del formulario.

### Funcionalidad

* Implementación de búsqueda por referencia y nombre.
* Búsqueda mediante coincidencias parciales.
* Limpieza automática del formulario.
* Refactorización del controlador para reducir código duplicado.

---

## v0.7 - Experiencia de usuario

### Imágenes

* Implementado visor de imágenes ampliadas mediante doble clic.
* Corrección de la actualización de imágenes al modificar un Playmobil.
* Mejora de la carga de imágenes desde disco.

### Usabilidad

* Mejor gestión del estado del formulario.
* Corrección de diversos errores detectados durante las pruebas.
* Optimización del código del controlador.

### Código

* Refactorización de métodos auxiliares.
* Mejora de la organización general del proyecto.

---

## v0.8 - Dashboard y estadísticas

### Estadísticas

* Añadido dashboard de estadísticas.
* Tarjeta con el número total de Playmobil.
* Tarjeta con el valor total de compra.
* Tarjeta con el valor actual de la colección.
* Tarjeta con el beneficio potencial.
* Tarjeta con el número de categorías.

### Gráficos

* Añadido gráfico circular de distribución por categorías.
* Cálculo del porcentaje de cada categoría.
* Añadido resumen con el número de unidades por categoría.

### Interfaz

* Reorganización de la interfaz mediante `TabPane`.
* Mejoras generales de usabilidad.

---

## v0.9 - Importación y exportación

### CSV

* Añadida exportación a CSV.
* Añadida importación desde archivos CSV.
* Evitada la importación de referencias duplicadas.
* Corrección del importador para admitir campos vacíos mediante `split(";", -1)`.
* Actualización automática de la tabla, estadísticas y gráfico tras la importación.

### PDF

* Añadida exportación a PDF.
* Generación de informes con formato profesional.
* Inclusión de la colección en el informe.
* Inclusión de estadísticas en el informe.
* Cabeceras del informe en negrita.
* Formato de moneda.
* Fecha de generación del informe.

### Excel

* Añadida exportación a Excel (`.xlsx`).
* Cabeceras con formato.
* Ajuste automático de columnas.
* Formato de moneda.
* Fecha de exportación.
* Resumen final de la colección.

---

## v0.10 - Configuración, backups y pruebas

### Copias de seguridad

* Añadida creación de copias de seguridad.
* Añadida restauración de copias de seguridad.
* Selección de la carpeta de almacenamiento.
* Configuración de la carpeta de backups.
* Implementación de restauración mediante transacciones JDBC.
* Uso de `commit()` para confirmar la restauración.
* Uso de `rollback()` para revertir cambios cuando se produce un error.

### Configuración

* Añadida ventana de configuración.
* Configuración de la carpeta de backups.
* Configuración de confirmación antes de eliminar registros.
* Configuración de confirmación antes de restaurar backups.
* Persistencia de las opciones de configuración.

### Testing

* Incorporación de pruebas automatizadas mediante JUnit 5.
* Pruebas de conexión con la base de datos.
* Pruebas de inserción.
* Pruebas de consulta.
* Pruebas de validación.
* Resultado actual: **4/4 tests superados correctamente**.

### Estadísticas

* Mejora del gráfico de distribución por categorías.
* Inclusión del porcentaje directamente en las etiquetas del gráfico.
* Añadido resumen textual de unidades por categoría.
* Gestión del caso de colección vacía.

### Calidad y mantenimiento

* Refactorización de código.
* Mejora de la organización del proyecto.
* Revisión del control de archivos locales mediante `.gitignore`.
* Eliminación de backups y archivos de prueba del repositorio.
* Corrección del nombre `databse.md` a `database.md`.

---

## Próxima versión

### v1.0 - Primera versión estable

Pendiente de completar la revisión final del proyecto, documentación, capturas de pantalla y presentación del repositorio.
