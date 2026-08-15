package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuracion {

    private static final String ARCHIVO = "config.properties";

    private static final Properties propiedades = new Properties();

    static {
        cargar();
    }

    private static void cargar() {

        try (FileInputStream fis = new FileInputStream(ARCHIVO)) {

            propiedades.load(fis);

        } catch (IOException e) {

            // Si no existe todavía, utilizamos los valores por defecto.
            propiedades.setProperty(
                    "carpeta.backups",
                    "backups");

            propiedades.setProperty(
                    "confirmar.eliminar",
                    "true");

            propiedades.setProperty(
                    "confirmar.restaurar",
                    "true");
        }
    }

    public static String getCarpetaBackups() {

        return propiedades.getProperty(
                "carpeta.backups",
                "backups");
    }

    public static boolean confirmarEliminar() {

        return Boolean.parseBoolean(
                propiedades.getProperty(
                        "confirmar.eliminar",
                        "true"));
    }

    public static boolean confirmarRestaurar() {

        return Boolean.parseBoolean(
                propiedades.getProperty(
                        "confirmar.restaurar",
                        "true"));
    }

    public static void guardar(
            String carpetaBackups,
            boolean confirmarEliminar,
            boolean confirmarRestaurar)
            throws IOException {

        propiedades.setProperty(
                "carpeta.backups",
                carpetaBackups);

        propiedades.setProperty(
                "confirmar.eliminar",
                String.valueOf(confirmarEliminar));

        propiedades.setProperty(
                "confirmar.restaurar",
                String.valueOf(confirmarRestaurar));

        try (FileOutputStream fos =
                     new FileOutputStream(ARCHIVO)) {

            propiedades.store(
                    fos,
                    "Configuración de Playmobil Manager");
        }
    }
}
