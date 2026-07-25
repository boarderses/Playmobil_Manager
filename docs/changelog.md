# Changelog

Este documento recoge la evolución del proyecto **Playmobil Manager**, incluyendo las nuevas funcionalidades, mejoras y correcciones realizadas en cada versión.

El proyecto sigue una numeración de versiones incremental mientras se desarrolla hasta alcanzar la versión estable **1.0**.

# v0.1 - Inicio del proyecto

##### Proyecto
- Creación del proyecto Maven.
- Configuración de Git y GitHub.
- Configuración inicial de JavaFX.
- Configuración de MySQL.

##### Base de datos
- Creación de la base de datos.
- Creación de la tabla `playmobil`.
- Configuración de la conexión mediante JDBC.

##### Modelo
- Creación de la clase `Playmobil`.
- Creación del DAO.
- Implementación de la conexión a la base de datos.

# v0.2 - CRUD funcional

##### Funcionalidad
- Implementado el alta de Playmobil.
- Implementada la modificación de registros.
- Implementada la eliminación de registros.
- Carga automática de la colección desde la base de datos.

##### Interfaz
- Primera versión de la interfaz JavaFX.
- Integración del controlador con FXML.
- Configuración de la TableView.

# v0.3 - Rediseño de la interfaz

##### Interfaz
- Nuevo diseño de la ventana principal.
- Añadido formulario completo para los datos del Playmobil.
- Añadido campo de observaciones.
- Organización mediante VBox y HBox.
- Incorporación de botones Guardar, Modificar y Eliminar.

##### Código
- Refactorización del MainController.
- Sincronización entre FXML y controlador.

# v0.4 - Gestión de imágenes

##### Imágenes
- Selección de imágenes mediante FileChooser.
- Visualización de imágenes en ImageView.
- Almacenamiento de la ruta de la imagen en la base de datos.
- Carga automática de la imagen al seleccionar un Playmobil.
- Conservación de la imagen al modificar un registro.

# v0.5 - Validaciones

##### Validación
- Validación de referencia obligatoria.
- Validación de nombre obligatorio.
- Validación de categoría obligatoria.
- Validación de precios negativos.
- Validación del formato numérico.
- Validación de referencias duplicadas.

##### Base de datos
- Restricción UNIQUE para la referencia.

##### Código
- Creación de la clase `PlaymobilValidator`.
- Mejora del tratamiento de errores mediante alertas.

# v0.6 - Mejoras de usabilidad

##### Interfaz
- Sustitución del campo Categoría por un ComboBox.
- Centralización de categorías mediante la clase `CategoriasPlaymobil`.
- Mejora del diseño con CSS.
- Mejor organización del formulario.

##### Funcionalidad
- Implementación de búsqueda en tiempo real por referencia y nombre.
- Limpieza automática del formulario.
- Refactorización del controlador para reducir código duplicado.

# v0.7 - Experiencia de usuario

##### Imágenes
- Implementado visor de imágenes ampliadas mediante doble clic.
- Corrección de la actualización de imágenes al modificar un Playmobil.
- Mejora de la carga de imágenes desde disco.

##### Usabilidad
- Mejor gestión del estado del formulario.
- Corrección de diversos errores detectados durante las pruebas.
- Optimización del código del controlador.

##### Código
- Refactorización de métodos auxiliares.
- Mejora de la organización general del proyecto.

# Versión 0.8

- Dashboard de estadísticas.
- Tarjetas con total de Playmobil.
- Valor de compra de la colección.
- Valor actual de la colección.
- Beneficio potencial.
- Número de categorías distintas.
- Gráfico circular de distribución por categorías.
- Reorganización de la interfaz mediante TabPane.
- Visor de imágenes mediante doble clic.
- Corrección de la actualización de imágenes al modificar.
- Mejoras generales de usabilidad.

# Versión 0.9

- Añadida exportación a PDF.
- Informe con formato profesional.
- Tabla con la colección.
- Estadísticas de la colección.
- Cabeceras en negrita.
- Formato de moneda.