# Playmobil Manager

Aplicación de escritorio desarrollada con **Java 21 y JavaFX** para gestionar una colección de Playmobil mediante una base de datos **MySQL**.

El proyecto implementa una arquitectura organizada por capas, acceso a datos mediante **DAO y JDBC**, validación de información, gestión de imágenes, estadísticas, importación y exportación de datos, copias de seguridad y pruebas automatizadas con **JUnit 5**.

El objetivo del proyecto es aplicar en una aplicación completa conocimientos de desarrollo Java, persistencia de datos, interfaces gráficas, gestión de archivos, testing y control de versiones.

---

## ✨ Características

### 📦 Gestión de la colección

* Crear nuevos Playmobil.
* Modificar registros existentes.
* Eliminar Playmobil.
* Visualizar la colección mediante una tabla.
* Búsqueda por referencia y nombre.
* Gestión de categorías mediante `ComboBox`.
* Validación de los datos introducidos.
* Control de referencias duplicadas mediante restricción `UNIQUE` en MySQL.
* Gestión de observaciones.
* Gestión de imágenes asociadas a cada Playmobil.

### 🖼️ Gestión de imágenes

* Selección de imágenes mediante `FileChooser`.
* Visualización de imágenes en miniatura.
* Apertura de imágenes a tamaño completo.
* Visor independiente mediante `VisorImagen`.

### 📊 Estadísticas

El dashboard permite consultar información general de la colección:

* Número total de Playmobil.
* Total invertido en la colección.
* Valor actual estimado.
* Distribución de Playmobil por categorías.
* Porcentaje correspondiente a cada categoría.
* Número de unidades por categoría.

La distribución se representa mediante un gráfico `PieChart`.

### 📥 Importación y exportación

La aplicación permite trabajar con diferentes formatos:

* Importación desde CSV.
* Exportación a CSV.
* Exportación a Excel.
* Exportación a PDF.

Durante la importación se comprueban las referencias existentes para evitar introducir registros duplicados.

### 💾 Copias de seguridad

Incluye funcionalidades para:

* Crear copias de seguridad.
* Restaurar copias de seguridad.
* Seleccionar la carpeta de almacenamiento.
* Configurar confirmaciones antes de restaurar.
* Recuperar los datos mediante una transacción de base de datos.

La restauración utiliza transacciones JDBC mediante:

```java
conn.setAutoCommit(false);
```

y confirma o revierte los cambios mediante `commit()` y `rollback()` según el resultado de la operación.

### ⚙️ Configuración

La aplicación permite configurar:

* Carpeta utilizada para las copias de seguridad.
* Confirmación antes de eliminar registros.
* Confirmación antes de restaurar una copia de seguridad.

La configuración local se mantiene fuera del repositorio mediante `.gitignore`.

---

## 🛠️ Tecnologías utilizadas

| Tecnología                         | Uso                                 |
| ---------------------------------- | ----------------------------------- |
| **Java 21**                        | Lenguaje principal                  |
| **JavaFX 21.0.4**                  | Interfaz gráfica                    |
| **FXML**                           | Definición de interfaces            |
| **Scene Builder**                  | Diseño visual de interfaces         |
| **CSS**                            | Estilos de la aplicación            |
| **MySQL**                          | Persistencia de datos               |
| **JDBC / MySQL Connector/J 8.3.0** | Acceso a base de datos              |
| **Maven**                          | Gestión del proyecto y dependencias |
| **JUnit 5.10.2**                   | Pruebas automatizadas               |
| **Apache POI 5.4.1**               | Exportación a Excel                 |
| **OpenPDF 1.3.39**                 | Generación de PDF                   |
| **Git / GitHub**                   | Control de versiones                |

---

## 🏗️ Arquitectura

El proyecto está organizado siguiendo una separación de responsabilidades por capas:

```text
src/
├── main/
│   ├── java/
│   │   ├── application/
│   │   ├── controller/
│   │   ├── dao/
│   │   ├── database/
│   │   ├── model/
│   │   ├── util/
│   │   └── validation/
│   │
│   └── resources/
│       ├── css/
│       ├── view/
│       └── config.properties
│
└── test/
    ├── java/
    │   ├── controller/
    │   ├── dao/
    │   ├── database/
    │   ├── model/
    │   ├── util/
    │   └── validator/
    │
    └── resources/
```

### `application`

Contiene el punto de entrada de la aplicación:

* `Main.java`

### `controller`

Gestiona la interacción entre la interfaz JavaFX y la lógica de la aplicación:

* `MainController`
* `ConfiguracionController`

### `dao`

Centraliza el acceso a los datos de la colección:

* `PlaymobilDAO`

El DAO se encarga de las operaciones CRUD, consultas, estadísticas, importación y otras operaciones relacionadas con la persistencia.

### `database`

Gestiona la conexión con MySQL:

* `ConexionDB`

### `model`

Contiene las entidades utilizadas por la aplicación:

* `Playmobil`
* `Estadisticas`

### `validation`

Contiene la lógica de validación:

* `PlaymobilValidator`

### `util`

Contiene funcionalidades auxiliares:

* `Alertas`
* `CategoriasPlaymobil`
* `Configuracion`
* `ExportadorBackup`
* `ExportadorCSV`
* `ExportadorExcel`
* `ExportadorPDF`
* `ImportadorCSV`
* `VisorImagen`

### `resources`

Contiene los recursos de la aplicación:

* Interfaces FXML.
* Hoja de estilos CSS.
* Configuración de conexión.

---

## 🗄️ Base de datos

La aplicación utiliza **MySQL** como sistema de gestión de base de datos.

Base de datos principal:

```text
playmobil_db
```

Tabla principal:

```text
playmobil
```

Entre los campos utilizados se encuentran:

```text
id
referencia
nombre
categoria
precio_compra
valor_actual
observaciones
ruta_imagen
```

La referencia del Playmobil dispone de una restricción `UNIQUE` para impedir la existencia de referencias duplicadas.

La documentación relacionada con la base de datos se encuentra en:

```text
docs/database.md
```

---

## 🧪 Pruebas

El proyecto incorpora pruebas automatizadas mediante **JUnit 5**.

Actualmente se han implementado y probado:

* Pruebas de conexión con la base de datos.
* Pruebas de inserción.
* Pruebas de consulta.
* Pruebas de validación.

Resultado actual:

**4/4 tests superados correctamente. 🟢**

Las pruebas se ejecutan mediante Maven y `maven-surefire-plugin`.

Para ejecutar los tests:

```bash
mvn test
```

---

## 📂 Estructura del proyecto

```text
Playmobil_Manager/
│
├── docs/
│   ├── changelog.md
│   ├── database.md
│   ├── roadmap.md
│   └── screenshots/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── application/
│   │   │   ├── controller/
│   │   │   ├── dao/
│   │   │   ├── database/
│   │   │   ├── model/
│   │   │   ├── util/
│   │   │   └── validation/
│   │   │
│   │   └── resources/
│   │       ├── css/
│   │       └── view/
│   │
│   └── test/
│       ├── java/
│       └── resources/
│
├── .gitignore
├── pom.xml
└── README.md
```

---

## 🚀 Instalación y configuración

### Requisitos

Para ejecutar el proyecto es necesario disponer de:

* **JDK 21**
* **Maven**
* **MySQL**
* **Eclipse** u otro IDE compatible con Maven y JavaFX

### 1. Clonar el repositorio

```bash
git clone https://github.com/boarderses/Playmobil_Manager.git
```

### 2. Crear la base de datos

Crear la base de datos MySQL:

```sql
CREATE DATABASE playmobil_db;
```

La estructura de la tabla se encuentra documentada en:

```text
docs/database.md
```

### 3. Configurar la conexión

La configuración de conexión se encuentra en:

```text
src/main/resources/config.properties
```

Este archivo contiene configuración local y está incluido en `.gitignore` para evitar publicar información sensible.

> Cada instalación debe utilizar sus propios datos de conexión a MySQL.

### 4. Compilar el proyecto

Desde la carpeta raíz:

```bash
mvn clean package
```

### 5. Ejecutar la aplicación

El proyecto utiliza `javafx-maven-plugin` y tiene configurada como clase principal:

```text
application.Main
```

Puede ejecutarse mediante:

```bash
mvn javafx:run
```

---

## 📤 Exportaciones

### CSV

Permite exportar la colección a un archivo CSV.

### Excel

La exportación a Excel se realiza utilizando **Apache POI**.

### PDF

La generación de documentos PDF se realiza mediante **OpenPDF**.

---

## 💾 Backups

Las copias de seguridad se generan en formato CSV.

La carpeta de almacenamiento puede configurarse desde la aplicación.

Las carpetas utilizadas para almacenar copias de seguridad locales están excluidas del repositorio mediante `.gitignore`:

```text
backups/
pruebasCopias/
```

Esto evita publicar accidentalmente datos generados durante el uso de la aplicación.

---

## 🔐 Gestión de configuración

La configuración local de la aplicación no forma parte del repositorio.

Se utilizan archivos ignorados mediante `.gitignore`, entre ellos:

```text
playmobil.properties
src/main/resources/config.properties
```

Esto permite mantener separada la configuración específica de cada instalación del código fuente.

---

## 📸 Capturas de pantalla

Se añadirán capturas de las principales funcionalidades:

### Pantalla principal

![Pantalla principal](docs/screenshots/main.png)

### Colección

![Colección](docs/screenshots/coleccion.png)

### Estadísticas

![Estadísticas](docs/screenshots/estadisticas.png)

### Configuración

![Configuración](docs/screenshots/configuracion.png)

---

## 🗺️ Posibles mejoras futuras

Algunas funcionalidades que podrían incorporarse en futuras versiones:

* Modo oscuro.
* Mejoras visuales y de personalización de la interfaz.
* Ampliación de las estadísticas.
* Nuevos filtros y opciones de búsqueda.
* Gestión más avanzada de imágenes.
* Mejoras en la importación y exportación.
* Posible evolución hacia una arquitectura con backend y sincronización remota.

---

## 🎯 Objetivos de aprendizaje

Este proyecto ha servido para poner en práctica y consolidar conocimientos relacionados con:

* Desarrollo de aplicaciones Java.
* Programación orientada a objetos.
* JavaFX y FXML.
* Diseño de interfaces con Scene Builder.
* Arquitectura por capas.
* Patrón DAO.
* JDBC.
* Persistencia con MySQL.
* CRUD.
* Validación de datos.
* Gestión de archivos.
* Importación y exportación de información.
* Generación de documentos.
* Transacciones de base de datos.
* Pruebas automatizadas con JUnit.
* Gestión de dependencias con Maven.
* Control de versiones con Git y GitHub.

---

## 👨‍💻 Autor

Proyecto personal desarrollado para ampliar y demostrar conocimientos en **Java, JavaFX, MySQL, Maven, JDBC, testing y desarrollo de aplicaciones de escritorio**.

El proyecto forma parte de mi portafolio como desarrollador de software junior.
