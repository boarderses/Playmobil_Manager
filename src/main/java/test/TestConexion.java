package test;

import java.sql.Connection;

import database.ConexionDB;

public class TestConexion {

	public static void main(String[] args) {

	        try (Connection conn = ConexionDB.getConnection()) {
	            System.out.println("Conexión correcta con MySQL");
	        } catch (Exception e) {
	            System.out.println("Error de conexión");
	            e.printStackTrace();
	        }
	    }

	}
