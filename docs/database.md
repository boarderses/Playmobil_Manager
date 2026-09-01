# Base de datos

Playmobil Manager utiliza **MySQL** como sistema de gestión de base de datos y **JDBC** para realizar las operaciones de persistencia desde la aplicación Java.

## Base de datos

Nombre:

```text
playmobil_db
```

La base de datos contiene actualmente la tabla principal:

```text
playmobil
```

---

## Tabla `playmobil`

La tabla almacena la información de cada elemento de la colección.

| Campo           | Tipo            | Nulo | Clave  | Descripción                      |
| --------------- | --------------- | ---- | ------ | -------------------------------- |
| `id`            | `INT`           | No   | PK     | Identificador único del registro |
| `referencia`    | `VARCHAR(20)`   | No   | UNIQUE | Referencia del Playmobil         |
| `nombre`        | `VARCHAR(100)`  | No   | —      | Nombre del Playmobil             |
| `categoria`     | `VARCHAR(50)`   | Sí   | —      | Categoría a la que pertenece     |
| `precio_compra` | `DECIMAL(10,2)` | Sí   | —      | Precio de compra                 |
| `valor_actual`  | `DECIMAL(10,2)` | Sí   | —      | Valor actual estimado            |
| `observaciones` | `TEXT`          | Sí   | —      | Información adicional            |
| `ruta_imagen`   | `VARCHAR(255)`  | Sí   | —      | Ruta de la imagen asociada       |

---

## Claves y restricciones

### Clave primaria

El campo `id` actúa como clave primaria:

```sql
PRIMARY KEY (`id`)
```

Además, utiliza `AUTO_INCREMENT`, por lo que MySQL genera automáticamente el identificador de cada nuevo registro.

### Referencia única

La referencia de cada Playmobil debe ser única:

```sql
UNIQUE KEY `uk_referencia` (`referencia`)
```

Esto evita que puedan existir dos registros con la misma referencia.

La aplicación también comprueba previamente la existencia de la referencia antes de realizar determinadas operaciones de inserción o modificación, proporcionando una validación adicional a nivel de aplicación.

---

## Campos obligatorios y opcionales

Los siguientes campos son obligatorios:

```text
referencia
nombre
```

Los siguientes campos pueden contener `NULL`:

```text
categoria
precio_compra
valor_actual
observaciones
ruta_imagen
```

Esta estructura permite guardar un Playmobil incluso cuando todavía no se dispone de todos los datos opcionales.

---

## Tipos de datos

### Identificador

```text
INT AUTO_INCREMENT
```

Se utiliza para identificar internamente cada registro.

### Texto

Se utilizan diferentes tamaños según las necesidades de cada campo:

* `VARCHAR(20)` para referencias.
* `VARCHAR(100)` para nombres.
* `VARCHAR(50)` para categorías.
* `VARCHAR(255)` para rutas de imágenes.
* `TEXT` para observaciones.

### Valores monetarios

Los precios se almacenan utilizando:

```text
DECIMAL(10,2)
```

Esto permite almacenar valores monetarios con dos posiciones decimales evitando utilizar tipos de coma flotante para representar importes.

---

## Motor y codificación

La tabla utiliza:

```text
ENGINE=InnoDB
```

y:

```text
CHARSET=utf8mb4
```

con la intercalación:

```text
utf8mb4_0900_ai_ci
```

El motor **InnoDB** permite utilizar transacciones, característica utilizada por la aplicación durante la restauración de copias de seguridad.

---

## Definición SQL

La estructura actual de la tabla es equivalente a:

```sql
CREATE TABLE `playmobil` (
    `id` int NOT NULL AUTO_INCREMENT,
    `referencia` varchar(20) NOT NULL,
    `nombre` varchar(100) NOT NULL,
    `categoria` varchar(50) DEFAULT NULL,
    `precio_compra` decimal(10,2) DEFAULT NULL,
    `valor_actual` decimal(10,2) DEFAULT NULL,
    `observaciones` text,
    `ruta_imagen` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_referencia` (`referencia`)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;
```

---

## Acceso desde Java

La aplicación utiliza JDBC para comunicarse con MySQL.

La responsabilidad de gestionar la conexión está centralizada en:

```text
src/main/java/database/ConexionDB.java
```

Las operaciones relacionadas con la colección se gestionan principalmente mediante:

```text
src/main/java/dao/PlaymobilDAO.java
```

De esta forma, el acceso a la base de datos queda separado de los controladores y de la interfaz gráfica.

---

## Seguridad y configuración

Los datos de conexión no se incluyen directamente en el código fuente.

La configuración local se encuentra en:

```text
src/main/resources/config.properties
```

Este archivo está incluido en `.gitignore` para evitar publicar credenciales o información específica del entorno.

Cada instalación debe utilizar su propia configuración de conexión a MySQL.

---

## Copias de seguridad y transacciones

La aplicación permite crear y restaurar copias de seguridad.

Durante la restauración se utiliza una transacción JDBC para garantizar que la operación pueda confirmarse completamente o revertirse si se produce un error.

Conceptualmente:

```text
Inicio de transacción
        │
        ▼
Eliminar/restaurar datos
        │
        ├── Operación correcta ──► COMMIT
        │
        └── Error ───────────────► ROLLBACK
```

Esto evita dejar la base de datos en un estado parcialmente restaurado.

---

## Documentación relacionada

La evolución del proyecto se encuentra documentada en:

```text
docs/changelog.md
```

La planificación de futuras mejoras se encuentra en:

```text
docs/roadmap.md
```
