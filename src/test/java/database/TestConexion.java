package database;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.sql.Connection;
import org.junit.jupiter.api.Test;

public class TestConexion {

    @Test
    void conexionDebeFuncionar() throws Exception {

        try (Connection conn = ConexionDB.getConnection()) {

            assertNotNull(conn);
        }
    }
}