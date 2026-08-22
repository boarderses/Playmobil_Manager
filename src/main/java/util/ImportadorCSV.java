package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Playmobil;

public class ImportadorCSV {
	
	public static List<Playmobil> importar(File archivo)
			throws IOException{
		
		List<Playmobil> lista = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader( new FileReader(archivo))){ 
		//saltar cabecera
		br.readLine();
		String linea;
			
		while((linea = br.readLine()) !=null) {
			
			String[] datos = separarCSV(linea);
			
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
			catch(NumberFormatException e) {
				System.out.println("Línea ignorada por formáto numérico: " + linea);
				}
			}	
		}		
		return lista;
	}
	private static String[] separarCSV(String linea) {

        List<String> campos = new ArrayList<>();

        StringBuilder campo = new StringBuilder();

        boolean dentroComillas = false;

        for (int i = 0; i < linea.length(); i++) {

            char caracter = linea.charAt(i);

            if (caracter == '"') {

                // Comillas dobles dentro de un campo
                if (dentroComillas
                        && i + 1 < linea.length()
                        && linea.charAt(i + 1) == '"') {

                    campo.append('"');
                    i++;

                } else {

                    dentroComillas = !dentroComillas;
                }

            } else if (caracter == ';' && !dentroComillas) {

                campos.add(campo.toString());
                campo.setLength(0);

            } else {

                campo.append(caracter);
            }
        }

        campos.add(campo.toString());

        return campos.toArray(new String[0]);
    }
}
