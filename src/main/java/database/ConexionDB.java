package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {
	
	private static final Properties PROPIEDADES = cargarPropiedades();

    private static Properties cargarPropiedades() {

        Properties propiedades = new Properties();

        try (InputStream input =
                     ConexionDB.class.getClassLoader()
                             .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new IllegalStateException(
                        "No se encontró config.properties");
            }

            propiedades.load(input);

            return propiedades;

        } catch (IOException e) {

            throw new IllegalStateException(
                    "No se pudo cargar la configuración de la base de datos",
                    e);
        }
    }
	
	public static Connection getConnection() throws SQLException {
		
		String url = PROPIEDADES.getProperty("db.url");
        String user = PROPIEDADES.getProperty("db.user");
        String password = PROPIEDADES.getProperty("db.password");
        
		return DriverManager.getConnection(url, user, password);
	}

}
