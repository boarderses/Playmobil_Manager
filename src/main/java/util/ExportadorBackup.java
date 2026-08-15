package util;

import java.io.File;
import java.util.List;

import model.Playmobil;

public class ExportadorBackup {

    public static void crearBackup(List<Playmobil> lista, File archivo)
            throws Exception {
    	
    	ExportadorCSV.exportar(lista, archivo);    }
}
