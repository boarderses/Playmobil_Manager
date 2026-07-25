package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import model.Playmobil;

public class ExportadorCSV {

    public static void exportar(List<Playmobil> lista, File archivo)
            throws IOException {

        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {

        writer.println("Referencia;Nombre;Categoría;Precio Compra;Valor Actual; Observaciones;Ruta Imagen");

        for (Playmobil p : lista) {

            writer.println(
                    p.getReferencia() + ";" +
                    p.getNombre() + ";" +
                    p.getCategoria() + ";" +
                    p.getPrecioCompra() + ";" +
                    p.getValorActual() + ";" +
                    p.getObservaciones() + ";" +
                    p.getRutaImagen()
            	);
        	}
        }
    }
}
