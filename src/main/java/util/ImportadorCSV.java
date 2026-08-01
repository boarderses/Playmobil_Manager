package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import model.Playmobil;

public class ImportadorCSV {
	
	public static List<Playmobil> importar(File archivo)
			throws Exception{
		
		List<Playmobil> lista = new ArrayList<>();
		
		BufferedReader br = new BufferedReader( new FileReader(archivo));
		
		br.readLine();
		String linea;
			
		while((linea = br.readLine()) !=null) {
			
			String[] datos = linea.split(";", -1);
			
			if (datos.length < 7) {
			    continue;
			}
			
			try{
				Playmobil p = new Playmobil();
			
			p.setReferencia(datos[0]);
			p.setNombre(datos[1]);
			p.setCategoria(datos[2]);
			p.setPrecioCompra(Double.parseDouble(datos[3]));
			p.setValorActual(Double.parseDouble(datos[4]));
			p.setObservaciones(datos[5]);
			p.setRutaImagen(datos[6]);
			
			lista.add(p);
			}
			catch(Exception e) {
				System.out.println("Línea ignorada: " + linea);
		}
	}	
		br.close();
			
		return lista;
	}
}
