package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
	public boolean actualizar(Playmobil p) {

	    String sql = """
	        UPDATE playmobil
	        SET referencia=?,
	            nombre=?,
	            categoria=?,
	            precio_compra=?,
	            valor_actual=?
	        WHERE id=?
	        """;

	    try (Connection conn = ConexionDB.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, p.getReferencia());
	        stmt.setString(2, p.getNombre());
	        stmt.setString(3, p.getCategoria());
	        stmt.setDouble(4, p.getPrecioCompra());
	        stmt.setDouble(5, p.getValorActual());
	        stmt.setInt(6, p.getId());

	        return stmt.executeUpdate() > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public boolean eliminar(int id) {

	    String sql = "DELETE FROM playmobil WHERE id = ?";

	    try (Connection conn = ConexionDB.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, id);

	        return stmt.executeUpdate() > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public List<Playmobil> obtenerTodos() {

	    List<Playmobil> lista = new ArrayList<>();

	    String sql = "SELECT * FROM playmobil";

	    try (Connection conn = ConexionDB.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {

	            Playmobil p = new Playmobil();

	            p.setId(rs.getInt("id"));
	            p.setReferencia(rs.getString("referencia"));
	            p.setNombre(rs.getString("nombre"));
	            p.setCategoria(rs.getString("categoria"));
	            p.setPrecioCompra(rs.getDouble("precio_compra"));
	            p.setValorActual(rs.getDouble("valor_actual"));
	            p.setObservaciones(rs.getString("observaciones"));
	            p.setRutaImagen(rs.getString("ruta_imagen"));

	            lista.add(p);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return lista;
	}
}
