package test;

import java.util.List;

import dao.PlaymobilDAO;
import model.Playmobil;

public class TestSelect {

	public static void main(String[] args) {
		
		PlaymobilDAO dao = new PlaymobilDAO();

        List<Playmobil> lista = dao.obtenerTodos();

        for (Playmobil p : lista) {
            System.out.println(p.getReferencia() + " - " + p.getNombre());
        }
    }
}
