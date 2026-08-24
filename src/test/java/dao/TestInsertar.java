package dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import model.Playmobil;

public class TestInsertar {
	
	 private final PlaymobilDAO dao = new PlaymobilDAO();

	@AfterEach
	void limpiarDatosPrueba() {
	    dao.eliminarPorReferencia("TEST-70012");
	    }

    @Test
    void insertarPlaymobilDebeFuncionar() {

        Playmobil p = new Playmobil(
                "TEST-70012",
                "Maletín oeste test",
                "Oeste",
                19.99,
                24.99,
                "Registro creado mediante JUnit",
                "imagen/maletinoeste.jpg");

        PlaymobilDAO dao = new PlaymobilDAO();

        boolean resultado = dao.insertar(p);

        assertTrue(resultado);
    }
}