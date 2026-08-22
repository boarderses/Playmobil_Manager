package util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.Playmobil;

public class ExportadorBackup {

    public static void crearBackup(List<Playmobil> lista, File archivo)
            throws IOException {
    	
    	ExportadorCSV.exportar(lista, archivo);    }
}
