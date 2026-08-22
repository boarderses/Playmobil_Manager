package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuracion {

    private static final String ARCHIVO = "playmobil.properties";
    
    private static final String CARPETA_BACKUPS = "carpeta.backups";
    private static final String CONFIRMAR_ELIMINAR = "confirmar.eliminar";
    private static final String CONFIRMAR_RESTAURAR = "confirmar.restaurar";

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
            		CARPETA_BACKUPS,
                    "backups");

            propiedades.setProperty(
            		CONFIRMAR_ELIMINAR,
                    "true");

            propiedades.setProperty(
            		CONFIRMAR_RESTAURAR,
                    "true");
        }
    }

    public static String getCarpetaBackups() {

        return propiedades.getProperty(
        		CARPETA_BACKUPS,
                "backups");
    }

    public static boolean confirmarEliminar() {

        return Boolean.parseBoolean(
                propiedades.getProperty(
                		CONFIRMAR_ELIMINAR,
                        "true"));
    }

    public static boolean confirmarRestaurar() {

        return Boolean.parseBoolean(
                propiedades.getProperty(
                		CONFIRMAR_RESTAURAR,
                        "true"));
    }

    public static void guardar(
            String carpetaBackups,
            boolean confirmarEliminar,
            boolean confirmarRestaurar)
            throws IOException {

        propiedades.setProperty(
        		CARPETA_BACKUPS,
                carpetaBackups);

        propiedades.setProperty(
        		CONFIRMAR_ELIMINAR,
                String.valueOf(confirmarEliminar));

        propiedades.setProperty(
        		CONFIRMAR_RESTAURAR,
                String.valueOf(confirmarRestaurar));

        try (FileOutputStream fos =
                     new FileOutputStream(ARCHIVO)) {

            propiedades.store(
                    fos,
                    "Configuración de Playmobil Manager");
        }
    }
}
