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

        writer.println("Referencia;Nombre;Categoría;Precio Compra;Valor Actual;Observaciones;Ruta Imagen");

        for (Playmobil p : lista) {

            writer.println(
                    escapar(p.getReferencia()) + ";" +
                    escapar(p.getNombre()) + ";" +
                    escapar(p.getCategoria()) + ";" +
                    p.getPrecioCompra() + ";" +
                    p.getValorActual() + ";" +
                    escapar(p.getObservaciones()) + ";" +
                    escapar(p.getRutaImagen())
            	);
        	}
        }
    }
    private static String escapar(String valor) {

        if (valor == null) {
            return "";
        }

        return valor
                .replace("\"", "\"\"")
                .replace(";", "\";\"")
                .replace("\n", " ")
                .replace("\r", " ");
    }
}
