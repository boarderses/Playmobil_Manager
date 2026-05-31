package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import database.ConexionDB;
import model.Playmobil;

public class PlaymobilDAO {

	public boolean insertar(Playmobil p) {
		
		String sql = "INSERT INTO playmobil " +
                "(referencia, nombre, categoria, precio_compra, valor_actual, observaciones, ruta_imagen) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try (Connection conn = ConexionDB.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, p.getReferencia());
            stmt.setString(2, p.getNombre());
            stmt.setString(3, p.getCategoria());
            stmt.setDouble(4, p.getPrecioCompra());
            stmt.setDouble(5, p.getValorActual());
            stmt.setString(6, p.getObservaciones());
            stmt.setString(7, p.getRutaImagen());
            
            return stmt.executeUpdate() > 0;
            
		} catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
