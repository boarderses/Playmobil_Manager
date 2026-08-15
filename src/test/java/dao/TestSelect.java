package dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Test;
import model.Playmobil;

public class TestSelect {

    @Test
    void obtenerTodosDebeFuncionar() {

        PlaymobilDAO dao = new PlaymobilDAO();

        List<Playmobil> lista = dao.obtenerTodos();

        assertNotNull(lista);
    }
}
