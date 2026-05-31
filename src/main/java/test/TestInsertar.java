package test;

import dao.PlaymobilDAO;
import model.Playmobil;

public class TestInsertar {

	public static void main(String[] args) {
		
		Playmobil p = new Playmobil(
				"70012", "Maletín oeste", "Oeste", 19.99, 24.99,
				"Maletín con oficina del sheriff y el banco", "imagen/maletinoeste.jpg");
		
		PlaymobilDAO dao = new PlaymobilDAO();

        boolean ok = dao.insertar(p);

        if (ok) {
            System.out.println("Insert realizado correctamente");
        } else {
            System.out.println("Error al insertar");
        }
    }
}
